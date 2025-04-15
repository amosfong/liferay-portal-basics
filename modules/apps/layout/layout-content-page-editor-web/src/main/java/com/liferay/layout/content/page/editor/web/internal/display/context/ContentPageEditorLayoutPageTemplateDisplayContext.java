/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration;
import com.liferay.layout.content.page.editor.web.internal.manager.ContentManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentCollectionManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.style.book.service.StyleBookEntryLocalService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class ContentPageEditorLayoutPageTemplateDisplayContext
	extends ContentPageEditorDisplayContext {

	public ContentPageEditorLayoutPageTemplateDisplayContext(
		List<ContentPageEditorSidebarPanel> contentPageEditorSidebarPanels,
		ContentManager contentManager,
		FragmentCollectionManager fragmentCollectionManager,
		FragmentEntryLinkManager fragmentEntryLinkManager,
		FragmentEntryLinkLocalService fragmentEntryLinkLocalService,
		FragmentEntryLocalService fragmentEntryLocalService,
		FrontendTokenDefinitionRegistry frontendTokenDefinitionRegistry,
		HttpServletRequest httpServletRequest, JSONFactory jsonFactory,
		Language language, LayoutLocalService layoutLocalService,
		LayoutLockManager layoutLockManager,
		LayoutSetLocalService layoutSetLocalService,
		LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService,
		LayoutPageTemplateEntryService layoutPageTemplateEntryService,
		LayoutPermission layoutPermission,
		PageEditorConfiguration pageEditorConfiguration,
		boolean pageIsDisplayPage, Portal portal, PortletRequest portletRequest,
		PortletURLFactory portletURLFactory, RenderResponse renderResponse,
		Staging staging, StagingGroupHelper stagingGroupHelper,
		StyleBookEntryLocalService styleBookEntryLocalService,
		UserLocalService userLocalService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		super(
			contentPageEditorSidebarPanels, contentManager,
			fragmentCollectionManager, fragmentEntryLinkManager,
			fragmentEntryLinkLocalService, fragmentEntryLocalService,
			frontendTokenDefinitionRegistry, httpServletRequest, jsonFactory,
			language, layoutLocalService, layoutLockManager,
			layoutPageTemplateEntryLocalService, layoutPageTemplateEntryService,
			layoutPermission, layoutSetLocalService, pageEditorConfiguration,
			portal, portletRequest, portletURLFactory, renderResponse, staging,
			stagingGroupHelper, styleBookEntryLocalService, userLocalService,
			workflowDefinitionLinkLocalService);

		_pageIsDisplayPage = pageIsDisplayPage;
	}

	@Override
	public Map<String, Object> getEditorContext() throws Exception {
		Map<String, Object> editorContext = super.getEditorContext();

		if (!_pageIsDisplayPage) {
			return editorContext;
		}

		Map<String, Object> configContext =
			(Map<String, Object>)editorContext.get("config");

		configContext.put(
			"infoItemPreviewSelectorURL", _getInfoItemPreviewSelectorURL());

		Map<String, Object> stateContext =
			(Map<String, Object>)editorContext.get("state");

		stateContext.put(
			"mappingFields",
			_addDisplayPageMappingFields(
				(JSONObject)stateContext.get("mappingFields")));

		configContext.put("selectedMappingTypes", _getSelectedMappingTypes());

		return editorContext;
	}

	@Override
	public String getPublishURL() {
		return getFragmentEntryActionURL(
			"/layout_content_page_editor/publish_layout_page_template_entry");
	}

	@Override
	public boolean isWorkflowEnabled() {
		return false;
	}

	private JSONObject _addDisplayPageMappingFields(
			JSONObject mappingFieldsJSONObject)
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_getLayoutPageTemplateEntry();

		String key =
			layoutPageTemplateEntry.getClassNameId() + StringPool.DASH +
				layoutPageTemplateEntry.getClassTypeId();

		if (!mappingFieldsJSONObject.has(key)) {
			mappingFieldsJSONObject.put(key, JSONFactoryUtil.createJSONArray());
		}

		return mappingFieldsJSONObject;
	}

	private String _getInfoItemPreviewSelectorURL() {
		return StringPool.BLANK;
	}

	private String _getItemSubtype(long classTypeId) {
		if (classTypeId <= 0) {
			return StringPool.BLANK;
		}

		return String.valueOf(classTypeId);
	}

	private LayoutPageTemplateEntry _getLayoutPageTemplateEntry() {
		if (_layoutPageTemplateEntry != null) {
			return _layoutPageTemplateEntry;
		}

		Layout draftLayout = themeDisplay.getLayout();

		_layoutPageTemplateEntry =
			layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(draftLayout.getClassPK());

		return _layoutPageTemplateEntry;
	}

	private String _getMappingSubtypeLabel() {
		return null;
	}

	private String _getMappingTypeLabel() {
		return null;
	}

	private Map<String, Object> _getSelectedMappingTypes() {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_getLayoutPageTemplateEntry();

		if ((layoutPageTemplateEntry == null) ||
			(layoutPageTemplateEntry.getClassNameId() <= 0)) {

			return Collections.emptyMap();
		}

		return HashMapBuilder.<String, Object>put(
			"mappingDescription",
			language.get(
				httpServletRequest,
				"content-source-selected-for-this-display-page-template")
		).put(
			"subtype",
			() -> {
				String subtypeLabel = _getMappingSubtypeLabel();

				if (Validator.isNull(subtypeLabel)) {
					return StringPool.BLANK;
				}

				return HashMapBuilder.<String, Object>put(
					"groupSubtypeTitle",
					language.get(httpServletRequest, "subtype")
				).put(
					"id", layoutPageTemplateEntry.getClassTypeId()
				).put(
					"label", subtypeLabel
				).build();
			}
		).put(
			"type",
			HashMapBuilder.<String, Object>put(
				"groupTypeTitle",
				language.get(httpServletRequest, "content-type")
			).put(
				"id", layoutPageTemplateEntry.getClassNameId()
			).put(
				"label", _getMappingTypeLabel()
			).build()
		).build();
	}

	private LayoutPageTemplateEntry _layoutPageTemplateEntry;
	private final boolean _pageIsDisplayPage;

}