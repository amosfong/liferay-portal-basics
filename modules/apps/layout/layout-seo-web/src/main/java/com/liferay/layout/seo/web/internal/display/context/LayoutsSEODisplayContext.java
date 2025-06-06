/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.web.internal.display.context;

import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.DDMStorageEngineManager;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import com.liferay.layout.admin.kernel.model.LayoutTypePortletConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.seo.canonical.url.LayoutSEOCanonicalURLProvider;
import com.liferay.layout.seo.kernel.LayoutSEOLinkManager;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.model.LayoutSEOSite;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalServiceUtil;
import com.liferay.layout.seo.service.LayoutSEOSiteLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.site.display.context.GroupDisplayContextHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alicia García
 */
public class LayoutsSEODisplayContext {

	public LayoutsSEODisplayContext(
		DDMStorageEngineManager ddmStorageEngineManager,
		DLAppService dlAppService, DLURLHelper dlurlHelper,
		LayoutLocalService layoutLocalService,
		LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService,
		LayoutSEOCanonicalURLProvider layoutSEOCanonicalURLProvider,
		LayoutSEOLinkManager layoutSEOLinkManager,
		LayoutSEOSiteLocalService layoutSEOSiteLocalService,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_ddmStorageEngineManager = ddmStorageEngineManager;
		_dlAppService = dlAppService;
		_dlurlHelper = dlurlHelper;
		_layoutLocalService = layoutLocalService;
		_layoutPageTemplateEntryLocalService =
			layoutPageTemplateEntryLocalService;
		_layoutSEOCanonicalURLProvider = layoutSEOCanonicalURLProvider;
		_layoutSEOLinkManager = layoutSEOLinkManager;
		_layoutSEOSiteLocalService = layoutSEOSiteLocalService;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(liferayPortletRequest);

		_groupDisplayContextHelper = new GroupDisplayContextHelper(
			httpServletRequest);

		_httpServletRequest = httpServletRequest;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getBackURL() throws PortalException {
		if (Validator.isNotNull(_backURL)) {
			return _backURL;
		}

		String backURL = ParamUtil.getString(
			_httpServletRequest, "backURL", _getRedirect());

		if (Validator.isNull(backURL)) {
			backURL = PortalUtil.getLayoutFullURL(
				getSelLayout(), _themeDisplay);
		}

		_backURL = backURL;

		return _backURL;
	}

	public DDMFormValues getDDMFormValues() throws StorageException {
		LayoutSEOEntry selLayoutSEOEntry = getSelLayoutSEOEntry();

		if ((selLayoutSEOEntry == null) ||
			(selLayoutSEOEntry.getDDMStorageId() == 0)) {

			return null;
		}

		try {
			return _ddmStorageEngineManager.getDDMFormValues(
				selLayoutSEOEntry.getDDMStorageId());
		}
		catch (PortalException portalException) {
			throw new StorageException(portalException);
		}
	}

	public long getDDMStructurePrimaryKey() throws PortalException {
		if (_ddmStructure != null) {
			return _ddmStructure.getPrimaryKey();
		}

		Company company = _themeDisplay.getCompany();

		_ddmStructure = DDMStructureServiceUtil.getStructure(
			company.getGroupId(),
			ClassNameLocalServiceUtil.getClassNameId(
				LayoutSEOEntry.class.getName()),
			"custom-meta-tags");

		return _ddmStructure.getPrimaryKey();
	}

	public String getDefaultCanonicalURL() throws PortalException {
		return URLCodec.decodeURL(
			_layoutSEOCanonicalURLProvider.getDefaultCanonicalURL(
				_selLayout, _themeDisplay));
	}

	public Map<Locale, String> getDefaultCanonicalURLMap()
		throws PortalException {

		return _layoutSEOCanonicalURLProvider.getCanonicalURLMap(
			_selLayout, _themeDisplay);
	}

	public String getDefaultOpenGraphImageURL() throws Exception {
		LayoutSEOSite layoutSEOSite =
			_layoutSEOSiteLocalService.fetchLayoutSEOSiteByGroupId(
				getGroupId());

		if ((layoutSEOSite == null) ||
			(layoutSEOSite.getOpenGraphImageFileEntryId() == 0) ||
			!layoutSEOSite.isOpenGraphEnabled()) {

			return StringPool.BLANK;
		}

		try {
			FileEntry fileEntry = _dlAppService.getFileEntry(
				layoutSEOSite.getOpenGraphImageFileEntryId());

			if (fileEntry.isInTrash()) {
				return StringPool.BLANK;
			}

			return _dlurlHelper.getImagePreviewURL(fileEntry, _themeDisplay);
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return StringPool.BLANK;
		}
	}

	public Map<Locale, String> getDefaultPageTitleMap() {
		return HashMapBuilder.putAll(
			_selLayout.getNameMap()
		).putAll(
			_selLayout.getTitleMap()
		).build();
	}

	public Map<Locale, String> getDefaultPageTitleWithSuffixMap()
		throws PortalException {

		Map<Locale, String> defaultPageTitleMap = getDefaultPageTitleMap();

		String pageTitleSuffix = getPageTitleSuffix();

		if (Validator.isNull(pageTitleSuffix)) {
			return defaultPageTitleMap;
		}

		Map<Locale, String> defaultPageTitleWithSuffixMap = new HashMap<>();

		for (Map.Entry<Locale, String> entry : defaultPageTitleMap.entrySet()) {
			defaultPageTitleWithSuffixMap.put(
				entry.getKey(), entry.getValue() + " - " + pageTitleSuffix);
		}

		return defaultPageTitleWithSuffixMap;
	}

	public PortletURL getEditCustomMetaTagsURL() {
		return PortletURLBuilder.createLiferayPortletURL(
			_liferayPortletResponse, _liferayPortletRequest.getPlid(),
			_liferayPortletRequest.getPortletName(),
			PortletRequest.ACTION_PHASE, MimeResponse.Copy.ALL
		).setActionName(
			"/layout/edit_custom_meta_tags"
		).setMVCRenderCommandName(
			_liferayPortletRequest.getParameter("mvcRenderCommandName")
		).setTabs1(
			_liferayPortletRequest.getParameter("tabs1")
		).setParameter(
			"displayStyle", _liferayPortletRequest.getParameter("displayStyle")
		).setParameter(
			"privateLayout",
			_liferayPortletRequest.getParameter("privateLayout")
		).setParameter(
			"screenNavigationCategoryKey",
			_liferayPortletRequest.getParameter("screenNavigationCategoryKey")
		).setParameter(
			"screenNavigationEntryKey",
			_liferayPortletRequest.getParameter("screenNavigationEntryKey")
		).setParameter(
			"selPlid", _liferayPortletRequest.getParameter("selPlid")
		).buildPortletURL();
	}

	public long getGroupId() {
		LayoutSEOEntry selLayoutSEOEntry = getSelLayoutSEOEntry();

		if (selLayoutSEOEntry == null) {
			return _groupDisplayContextHelper.getGroupId();
		}

		return selLayoutSEOEntry.getGroupId();
	}

	public Long getLayoutId() {
		if (_layoutId != null) {
			return _layoutId;
		}

		_layoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

		Layout selLayout = getSelLayout();

		if (selLayout != null) {
			_layoutId = selLayout.getLayoutId();
		}

		return _layoutId;
	}

	public String getOpenGraphImageTitle() {
		LayoutSEOEntry layoutSEOEntry = getSelLayoutSEOEntry();

		if ((layoutSEOEntry == null) ||
			(layoutSEOEntry.getOpenGraphImageFileEntryId() == 0)) {

			return StringPool.BLANK;
		}

		try {
			FileEntry fileEntry = _dlAppService.getFileEntry(
				layoutSEOEntry.getOpenGraphImageFileEntryId());

			if (fileEntry.isInTrash()) {
				return StringPool.BLANK;
			}

			return fileEntry.getTitle();
		}
		catch (Exception exception) {
			_log.error(exception);

			return StringPool.BLANK;
		}
	}

	public String getOpenGraphImageURL() {
		LayoutSEOEntry layoutSEOEntry = getSelLayoutSEOEntry();

		if ((layoutSEOEntry == null) ||
			(layoutSEOEntry.getOpenGraphImageFileEntryId() == 0)) {

			return StringPool.BLANK;
		}

		try {
			FileEntry fileEntry = _dlAppService.getFileEntry(
				layoutSEOEntry.getOpenGraphImageFileEntryId());

			if (fileEntry.isInTrash()) {
				return StringPool.BLANK;
			}

			return _dlurlHelper.getImagePreviewURL(fileEntry, _themeDisplay);
		}
		catch (Exception exception) {
			_log.error(exception);

			return StringPool.BLANK;
		}
	}

	public HashMap<String, Object> getOpenGraphMappingData()
		throws PortalException {

		return HashMapBuilder.<String, Object>putAll(
			_getBaseSEOMappingData()
		).put(
			"openGraphDescription",
			_selLayout.getTypeSettingsProperty(
				"mapped-openGraphDescription", "${description}")
		).put(
			"openGraphImage",
			_selLayout.getTypeSettingsProperty("mapped-openGraphImage", null)
		).put(
			"openGraphImageAlt",
			_selLayout.getTypeSettingsProperty("mapped-openGraphImageAlt", null)
		).put(
			"openGraphTitle",
			_selLayout.getTypeSettingsProperty(
				"mapped-openGraphTitle", "${title}")
		).build();
	}

	public Map<String, Object> getOpenGraphPreviewSeoProperties()
		throws Exception {

		return HashMapBuilder.<String, Object>put(
			"displayType", "og"
		).put(
			"targets",
			HashMapBuilder.<String, Object>put(
				"description",
				HashMapBuilder.<String, Object>put(
					"defaultValue",
					() -> {
						Layout selLayout = getSelLayout();

						return selLayout.getDescriptionMap();
					}
				).put(
					"id", "openGraphDescription"
				).build()
			).put(
				"imgUrl",
				HashMapBuilder.<String, Object>put(
					"defaultValue", getDefaultOpenGraphImageURL()
				).put(
					"value", getOpenGraphImageURL()
				).build()
			).put(
				"title",
				HashMapBuilder.<String, Object>put(
					"defaultValue", getDefaultPageTitleWithSuffixMap()
				).put(
					"id", "openGraphTitle"
				).build()
			).put(
				"url",
				Collections.singletonMap(
					"defaultValue", getDefaultCanonicalURLMap())
			).build()
		).build();
	}

	public String getPageTitleSuffix() throws PortalException {
		Company company = _themeDisplay.getCompany();

		return _layoutSEOLinkManager.getPageTitleSuffix(
			_selLayout, company.getName());
	}

	public PortletURL getRedirectURL() {
		return PortletURLBuilder.createLiferayPortletURL(
			_liferayPortletResponse, _liferayPortletRequest.getPlid(),
			_liferayPortletRequest.getPortletName(),
			PortletRequest.RENDER_PHASE, MimeResponse.Copy.ALL
		).setMVCRenderCommandName(
			_liferayPortletRequest.getParameter("mvcRenderCommandName")
		).setTabs1(
			_liferayPortletRequest.getParameter("tabs1")
		).setParameter(
			"displayStyle", _liferayPortletRequest.getParameter("displayStyle")
		).setParameter(
			"privateLayout",
			_liferayPortletRequest.getParameter("privateLayout")
		).setParameter(
			"screenNavigationCategoryKey",
			_liferayPortletRequest.getParameter("screenNavigationCategoryKey")
		).setParameter(
			"screenNavigationEntryKey",
			_liferayPortletRequest.getParameter("screenNavigationEntryKey")
		).setParameter(
			"selPlid", _liferayPortletRequest.getParameter("selPlid")
		).buildPortletURL();
	}

	public Group getSelGroup() {
		return _groupDisplayContextHelper.getSelGroup();
	}

	public Layout getSelLayout() {
		if (_selLayout != null) {
			return _selLayout;
		}

		if (_getSelPlid() != LayoutConstants.DEFAULT_PLID) {
			_selLayout = LayoutLocalServiceUtil.fetchLayout(_getSelPlid());
		}

		return _selLayout;
	}

	public LayoutSEOEntry getSelLayoutSEOEntry() {
		Layout layout = getSelLayout();

		if (layout == null) {
			return null;
		}

		return LayoutSEOEntryLocalServiceUtil.fetchLayoutSEOEntry(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getLayoutId());
	}

	public Map<String, Object> getSEOMappingData() throws PortalException {
		return HashMapBuilder.<String, Object>putAll(
			_getBaseSEOMappingData()
		).put(
			"description",
			_selLayout.getTypeSettingsProperty(
				"mapped-description", "${description}")
		).put(
			"title",
			_selLayout.getTypeSettingsProperty("mapped-title", "${title}")
		).build();
	}

	public Map<String, Object> getSEOPreviewSeoProperties()
		throws PortalException {

		return HashMapBuilder.<String, Object>put(
			"targets",
			HashMapBuilder.<String, Object>put(
				"description",
				HashMapBuilder.put(
					"defaultValue",
					() -> {
						Layout selLayout = getSelLayout();

						return selLayout.getDescription(
							_themeDisplay.getLocale());
					}
				).put(
					"id", "descriptionSEO"
				).build()
			).put(
				"title",
				HashMapBuilder.<String, Object>put(
					"defaultValue", getDefaultPageTitleMap()
				).put(
					"id", "title"
				).build()
			).put(
				"url",
				() -> {
					if (isLayoutUtilityPageEntry()) {
						return null;
					}

					return HashMapBuilder.<String, Object>put(
						"defaultValue", getDefaultCanonicalURLMap()
					).put(
						"id", "canonicalURL"
					).build();
				}
			).build()
		).put(
			"titleSuffix", getPageTitleSuffix()
		).build();
	}

	public List<SelectOption> getSitemapChangeFrequencySelectOptions() {
		Layout selLayout = getSelLayout();

		UnicodeProperties layoutTypeSettingsUnicodeProperties =
			selLayout.getTypeSettingsProperties();

		String selectedSitemapChangeFrequencyOption =
			layoutTypeSettingsUnicodeProperties.getProperty(
				"sitemap-changefreq",
				PropsValues.SITES_SITEMAP_DEFAULT_CHANGE_FREQUENCY);

		return TransformUtil.transform(
			Arrays.asList(
				"always", "hourly", "daily", "weekly", "monthly", "yearly",
				"never"),
			sitemapChangeFrequencyOption -> new SelectOption(
				LanguageUtil.get(
					_httpServletRequest, sitemapChangeFrequencyOption),
				sitemapChangeFrequencyOption,
				Objects.equals(
					sitemapChangeFrequencyOption,
					selectedSitemapChangeFrequencyOption)));
	}

	public List<SelectOption> getSitemapIncludeSelectOptions() {
		Layout selLayout = getSelLayout();

		UnicodeProperties layoutTypeSettingsUnicodeProperties =
			selLayout.getTypeSettingsProperties();

		boolean sitemapInclude = GetterUtil.getBoolean(
			layoutTypeSettingsUnicodeProperties.getProperty(
				LayoutTypePortletConstants.SITEMAP_INCLUDE),
			true);

		return Arrays.asList(
			new SelectOption(
				LanguageUtil.get(_httpServletRequest, "yes"), "1",
				sitemapInclude),
			new SelectOption(
				LanguageUtil.get(_httpServletRequest, "no"), "0",
				!sitemapInclude));
	}

	public boolean isIncludeChildLayoutsInSitemap() {
		Layout selLayout = getSelLayout();

		UnicodeProperties layoutTypeSettingsUnicodeProperties =
			selLayout.getTypeSettingsProperties();

		if (GetterUtil.getBoolean(
				layoutTypeSettingsUnicodeProperties.getProperty(
					"sitemap-include-child-layouts",
					Boolean.TRUE.toString()))) {

			return true;
		}

		return false;
	}

	public boolean isLayoutUtilityPageEntry() {
		Layout layout = getSelLayout();

		if (layout.isTypeUtility()) {
			return true;
		}

		return false;
	}

	public boolean isPrivateLayout() {
		if (_privateLayout != null) {
			return _privateLayout;
		}

		if (getSelLayout() != null) {
			Layout selLayout = getSelLayout();

			_privateLayout = selLayout.isPrivateLayout();

			return _privateLayout;
		}

		Layout layout = _themeDisplay.getLayout();

		if (!layout.isTypeControlPanel()) {
			_privateLayout = layout.isPrivateLayout();

			return _privateLayout;
		}

		_privateLayout = ParamUtil.getBoolean(
			_liferayPortletRequest, "privateLayout");

		return _privateLayout;
	}

	public boolean showIncludeChildLayoutsInSitemap() {
		Layout selLayout = getSelLayout();

		if (selLayout.isTypeAssetDisplay()) {
			return false;
		}

		return true;
	}

	private HashMap<String, Object> _getBaseSEOMappingData()
		throws PortalException {

		return HashMapBuilder.<String, Object>put(
			"defaultLanguageId", _selLayout.getDefaultLanguageId()
		).put(
			"selectedSource",
			JSONUtil.put(
				"className", _getClassName()
			).put(
				"classNameLabel", _getTypeLabel()
			).put(
				"classTypeId", _getClassTypeId()
			).put(
				"classTypeLabel", _getSubtypeLabel()
			)
		).build();
	}

	private String _getClassName() {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_getLayoutPageTemplateEntry();

		return layoutPageTemplateEntry.getClassName();
	}

	private long _getClassTypeId() {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_getLayoutPageTemplateEntry();

		return layoutPageTemplateEntry.getClassTypeId();
	}

	private LayoutPageTemplateEntry _getLayoutPageTemplateEntry() {
		if (_layoutPageTemplateEntry != null) {
			return _layoutPageTemplateEntry;
		}

		_layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(_getSelPlid());

		if (_layoutPageTemplateEntry != null) {
			return _layoutPageTemplateEntry;
		}

		Layout layout = _layoutLocalService.fetchLayout(_getSelPlid());

		if (layout.isDraftLayout()) {
			_layoutPageTemplateEntry =
				_layoutPageTemplateEntryLocalService.
					fetchLayoutPageTemplateEntryByPlid(layout.getClassPK());
		}
		else {
			Layout draftLayout = layout.fetchDraftLayout();

			if (draftLayout != null) {
				_layoutPageTemplateEntry =
					_layoutPageTemplateEntryLocalService.
						fetchLayoutPageTemplateEntryByPlid(
							draftLayout.getPlid());
			}
		}

		return _layoutPageTemplateEntry;
	}

	private String _getRedirect() {
		if (Validator.isNotNull(_redirect)) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		return _redirect;
	}

	private Long _getSelPlid() {
		if (_selPlid != null) {
			return _selPlid;
		}

		_selPlid = ParamUtil.getLong(
			_liferayPortletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);

		return _selPlid;
	}

	private String _getSubtypeLabel() throws PortalException {
		return StringPool.BLANK;
	}

	private String _getTypeLabel() {
		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutsSEODisplayContext.class);

	private String _backURL;
	private final DDMStorageEngineManager _ddmStorageEngineManager;
	private DDMStructure _ddmStructure;
	private final DLAppService _dlAppService;
	private final DLURLHelper _dlurlHelper;
	private final GroupDisplayContextHelper _groupDisplayContextHelper;
	private final HttpServletRequest _httpServletRequest;
	private Long _layoutId;
	private final LayoutLocalService _layoutLocalService;
	private LayoutPageTemplateEntry _layoutPageTemplateEntry;
	private final LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;
	private final LayoutSEOCanonicalURLProvider _layoutSEOCanonicalURLProvider;
	private final LayoutSEOLinkManager _layoutSEOLinkManager;
	private final LayoutSEOSiteLocalService _layoutSEOSiteLocalService;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private Boolean _privateLayout;
	private String _redirect;
	private Layout _selLayout;
	private Long _selPlid;
	private final ThemeDisplay _themeDisplay;

}