/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.display.context;

import com.liferay.change.tracking.spi.history.util.CTTimelineUtil;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.frontend.js.loader.modules.extender.esm.ESImportUtil;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.web.internal.configuration.JournalWebConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.servlet.taglib.aui.ESImport;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilderFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalEditDDMStructuresDisplayContext {

	public JournalEditDDMStructuresDisplayContext(
			Portal portal, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws ConfigurationException {

		_httpServletRequest = portal.getHttpServletRequest(renderRequest);
		_liferayPortletResponse = portal.getLiferayPortletResponse(
			renderResponse);

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_journalServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				JournalServiceConfiguration.class,
				_themeDisplay.getCompanyId());

		_journalWebConfiguration =
			(JournalWebConfiguration)renderRequest.getAttribute(
				JournalWebConfiguration.class.getName());
	}

	public List<Map<String, Object>> getAdditionalPanels() {
		ESImport esImport = ESImportUtil.getESImport(
			_absolutePortalURLBuilderFactorySnapshot.get(
			).getAbsolutePortalURLBuilder(
				_httpServletRequest
			),
			"{BasicInfoPanel} from journal-web");

		return ListUtil.fromArray(
			HashMapBuilder.<String, Object>put(
				"icon", "cog"
			).put(
				"label", LanguageUtil.get(_httpServletRequest, "properties")
			).put(
				"pluginEntryPoint",
				StringBundler.concat(
					"{", esImport.getSymbol(), "} from ", esImport.getModule())
			).put(
				"sidebarPanelId", "properties"
			).put(
				"url",
				() -> PortletURLBuilder.createRenderURL(
					_liferayPortletResponse
				).setMVCPath(
					"/data_engine/basic_info.jsp"
				).setParameter(
					"ddmStructureId", getDDMStructureId()
				).setWindowState(
					LiferayWindowState.EXCLUSIVE
				).buildString()
			).build());
	}

	public List<String> getAvailableLanguageIds() {
		if (_ddmStructure == null) {
			return StringUtil.asList(getDefaultLanguageId());
		}

		return Arrays.asList(_ddmStructure.getAvailableLanguageIds());
	}

	public Map<String, Object> getDataEngineLayoutBuilderHandlerContext() {
		return HashMapBuilder.<String, Object>put(
			"defaultLanguageId", getDefaultLanguageId()
		).put(
			"originalStructureKey",
			() -> {
				DDMStructure ddmStructure = getDDMStructure();

				if (ddmStructure == null) {
					return null;
				}

				return ddmStructure.getStructureKey();
			}
		).put(
			"showStructureKeyChangesWarning",
			() -> {
				if (_isAutogenerateDDMStructureKey() ||
					(getDDMStructure() == null)) {

					return false;
				}

				return true;
			}
		).build();
	}

	public DDMForm getDDMForm() {
		try {
			return DDMUtil.getDDMForm(getScript());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return null;
	}

	public DDMStructure getDDMStructure() {
		if (_ddmStructure != null) {
			return _ddmStructure;
		}

		_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			getDDMStructureId());

		return _ddmStructure;
	}

	public long getDDMStructureId() {
		if (_ddmStructureId != null) {
			return _ddmStructureId;
		}

		_ddmStructureId = ParamUtil.getLong(
			_httpServletRequest, "ddmStructureId");

		CTTimelineUtil.setCTTimelineKeys(
			_httpServletRequest, DDMStructure.class, _ddmStructureId);

		return _ddmStructureId;
	}

	public String getDefaultLanguageId() {
		DDMForm ddmForm = getDDMForm();

		if ((ddmForm == null) || (ddmForm.getDefaultLocale() == null)) {
			return LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault());
		}

		return LocaleUtil.toLanguageId(ddmForm.getDefaultLocale());
	}

	public boolean getDisplayFieldName() {
		if (_journalServiceConfiguration == null) {
			return false;
		}

		return _journalServiceConfiguration.displayFieldName();
	}

	public Map<String, Object> getLocaleChangedHandlerContext() {
		return HashMapBuilder.<String, Object>put(
			"contentTitle", "name"
		).put(
			"defaultLanguageId", getDefaultLanguageId()
		).build();
	}

	public String getScript() throws PortalException {
		if (_script != null) {
			return _script;
		}

		_script = ParamUtil.getString(_httpServletRequest, "dataDefinition");

		if (Validator.isNotNull(_script)) {
			return _script;
		}

		DDMStructure ddmStructure = getDDMStructure();

		if (ddmStructure != null) {
			DDMStructureVersion ddmStructureVersion =
				ddmStructure.getLatestStructureVersion();

			_script = ddmStructureVersion.getDefinition();
		}

		return _script;
	}

	public boolean isStructureFieldIndexableEnable() {
		return _journalWebConfiguration.structureFieldIndexableEnable();
	}

	public boolean isStructureKeyInputDisabled() {
		if (_isAutogenerateDDMStructureKey()) {
			return true;
		}

		DDMStructure ddmStructure = getDDMStructure();

		if ((ddmStructure != null) &&
			Objects.equals(
				ddmStructure.getStructureKey(), "BASIC-WEB-CONTENT")) {

			return true;
		}

		return false;
	}

	private boolean _isAutogenerateDDMStructureKey() {
		if (_journalServiceConfiguration == null) {
			return true;
		}

		return _journalServiceConfiguration.autogenerateDDMStructureKey();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalEditDDMStructuresDisplayContext.class);

	private static final Snapshot<AbsolutePortalURLBuilderFactory>
		_absolutePortalURLBuilderFactorySnapshot = new Snapshot<>(
			JournalEditDDMStructuresDisplayContext.class,
			AbsolutePortalURLBuilderFactory.class);

	private DDMStructure _ddmStructure;
	private Long _ddmStructureId;
	private final HttpServletRequest _httpServletRequest;
	private final JournalServiceConfiguration _journalServiceConfiguration;
	private final JournalWebConfiguration _journalWebConfiguration;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _script;
	private final ThemeDisplay _themeDisplay;

}