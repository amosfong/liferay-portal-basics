/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.fragment.model.FragmentComposition;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.DefaultFragmentRendererContext;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.layout.admin.constants.LayoutScreenNavigationEntryConstants;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration;
import com.liferay.layout.content.page.editor.web.internal.constants.ContentPageEditorActionKeys;
import com.liferay.layout.content.page.editor.web.internal.manager.ContentManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentCollectionManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.content.page.editor.web.internal.util.StyleBookEntryUtil;
import com.liferay.layout.content.page.editor.web.internal.util.layout.structure.LayoutStructureUtil;
import com.liferay.layout.converter.PaddingConverter;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.util.comparator.LayoutPageTemplateEntryNameComparator;
import com.liferay.layout.responsive.ViewportSize;
import com.liferay.layout.util.structure.CommonStylesUtil;
import com.liferay.layout.util.structure.DropZoneLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.editor.configuration.EditorConfiguration;
import com.liferay.portal.kernel.editor.configuration.EditorConfigurationFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.servlet.MultiSessionMessages;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.util.PropsValues;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.service.StyleBookEntryLocalService;
import com.liferay.style.book.util.DefaultStyleBookEntryUtil;
import com.liferay.style.book.util.comparator.StyleBookEntryNameComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Eudaldo Alonso
 */
public class ContentPageEditorDisplayContext {

	public ContentPageEditorDisplayContext(
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
		LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService,
		LayoutPageTemplateEntryService layoutPageTemplateEntryService,
		LayoutPermission layoutPermission,
		LayoutSetLocalService layoutSetLocalService,
		PageEditorConfiguration pageEditorConfiguration, Portal portal,
		PortletRequest portletRequest, PortletURLFactory portletURLFactory,
		RenderResponse renderResponse, Staging staging,
		StagingGroupHelper stagingGroupHelper,
		StyleBookEntryLocalService styleBookEntryLocalService,
		UserLocalService userLocalService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_contentPageEditorSidebarPanels = contentPageEditorSidebarPanels;
		_contentManager = contentManager;
		_fragmentCollectionManager = fragmentCollectionManager;
		_fragmentEntryLinkManager = fragmentEntryLinkManager;
		_fragmentEntryLinkLocalService = fragmentEntryLinkLocalService;
		_fragmentEntryLocalService = fragmentEntryLocalService;
		_frontendTokenDefinitionRegistry = frontendTokenDefinitionRegistry;
		_jsonFactory = jsonFactory;
		this.language = language;
		_layoutLocalService = layoutLocalService;
		this.layoutLockManager = layoutLockManager;
		this.layoutPageTemplateEntryLocalService =
			layoutPageTemplateEntryLocalService;
		_layoutPageTemplateEntryService = layoutPageTemplateEntryService;
		_layoutPermission = layoutPermission;
		_layoutSetLocalService = layoutSetLocalService;
		_pageEditorConfiguration = pageEditorConfiguration;
		this.portal = portal;
		_portletURLFactory = portletURLFactory;
		this.renderResponse = renderResponse;
		_staging = staging;
		_styleBookEntryLocalService = styleBookEntryLocalService;
		_userLocalService = userLocalService;
		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;

		this.httpServletRequest = httpServletRequest;
		this.portletRequest = portletRequest;
		this.stagingGroupHelper = stagingGroupHelper;

		themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getEditorContext() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"config",
			HashMapBuilder.<String, Object>put(
				"addFragmentCompositionURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_fragment_composition")
			).put(
				"addFragmentEntryLinkCommentURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/add_fragment_entry_link_comment")
			).put(
				"addFragmentEntryLinksURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_fragment_entry_links")
			).put(
				"addFragmentEntryLinkURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_fragment_entry_link")
			).put(
				"addItemURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_item")
			).put(
				"addPortletURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_portlet")
			).put(
				"addRuleURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/add_rule")
			).put(
				"addStepperFragmentEntryLinkURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/add_stepper_fragment_entry_link")
			).put(
				"autoExtendSessionEnabled",
				_pageEditorConfiguration.autoExtendSessionEnabled()
			).put(
				"availableLanguages", _getAvailableLanguages()
			).put(
				"availableViewportSizes", _getAvailableViewportSizes()
			).put(
				"changeMasterLayoutURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/change_master_layout")
			).put(
				"changeStyleBookEntryURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/change_style_book_entry")
			).put(
				"collectionSelectorURL", _getCollectionSelectorURL()
			).put(
				"commonStyles",
				CommonStylesUtil.getCommonStylesJSONArray(
					LanguageResources.getResourceBundle(
						themeDisplay.getLocale()))
			).put(
				"copyItemsURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/copy_items")
			).put(
				"createLayoutPageTemplateEntryURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/create_layout_page_template_entry")
			).put(
				"defaultEditorConfigurations", _getDefaultConfigurations()
			).put(
				"defaultLanguageId",
				LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale())
			).put(
				"defaultSegmentsExperienceId", 0
			).put(
				"defaultStyleBookEntryImagePreviewURL",
				() -> {
					StyleBookEntry defaultStyleBookEntry =
						_getDefaultMasterStyleBookEntry();

					if (defaultStyleBookEntry != null) {
						return defaultStyleBookEntry.getImagePreviewURL(
							themeDisplay);
					}

					return StringPool.BLANK;
				}
			).put(
				"defaultStyleBookEntryName",
				() -> {
					StyleBookEntry defaultStyleBookEntry =
						_getDefaultMasterStyleBookEntry();

					if (defaultStyleBookEntry != null) {
						return defaultStyleBookEntry.getName();
					}

					return null;
				}
			).put(
				"deleteFragmentEntryLinkCommentURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/delete_fragment_entry_link_comment")
			).put(
				"deleteRuleURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/delete_rule")
			).put(
				"discardDraftURL", _getDiscardDraftURL()
			).put(
				"duplicateItemURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/duplicate_item")
			).put(
				"editFragmentEntryLinkCommentURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/edit_fragment_entry_link_comment",
					Constants.UPDATE)
			).put(
				"editFragmentEntryLinkURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/edit_fragment_entry_link")
			).put(
				"fragmentCompositionDescriptionMaxLength",
				() -> ModelHintsUtil.getMaxLength(
					FragmentComposition.class.getName(), "description")
			).put(
				"fragmentCompositionNameMaxLength",
				() -> ModelHintsUtil.getMaxLength(
					FragmentComposition.class.getName(), "name")
			).put(
				"frontendTokens",
				() -> {
					Group group = themeDisplay.getScopeGroup();

					FrontendTokenDefinition frontendTokenDefinition =
						_frontendTokenDefinitionRegistry.
							getFrontendTokenDefinition(
								_layoutSetLocalService.fetchLayoutSet(
									themeDisplay.getSiteGroupId(),
									group.isLayoutSetPrototype()));

					if (frontendTokenDefinition == null) {
						return _jsonFactory.createJSONObject();
					}

					return StyleBookEntryUtil.getFrontendTokensValues(
						frontendTokenDefinition, themeDisplay.getLocale(),
						_getDefaultStyleBookEntry());
				}
			).put(
				"getAvailableImageConfigurationsURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_available_image_configurations")
			).put(
				"getAvailableListItemRenderersURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_available_list_item_renderers")
			).put(
				"getCollectionFieldURL",
				_getResourceURL(
					"/layout_content_page_editor/get_collection_field")
			).put(
				"getCollectionSupportedFiltersURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_collection_supported_filters")
			).put(
				"getCollectionVariationsURL",
				_getResourceURL(
					"/layout_content_page_editor/get_collection_variations")
			).put(
				"getCollectionWarningMessageURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_collection_warning_message")
			).put(
				"getExperienceDataURL",
				_getResourceURL(
					"/layout_content_page_editor/get_experience_data")
			).put(
				"getFileEntryURL",
				_getResourceURL(
					"/layout_content_page_editor/get_file_entry_url")
			).put(
				"getFormConfigURL",
				_getResourceURL("/layout_content_page_editor/get_form_config")
			).put(
				"getFormFieldsURL",
				_getResourceURL("/layout_content_page_editor/get_form_fields")
			).put(
				"getFragmentEntryInputFieldTypesURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_fragment_entry_input_field_types")
			).put(
				"getIframeContentCssURL",
				portal.getStaticResourceURL(
					httpServletRequest,
					portal.getPathModule() +
						"/layout-content-page-editor-web/page_editor/app" +
							"/components/App.css")
			).put(
				"getIframeContentURL",
				() -> {
					String layoutURL = portal.getLayoutFriendlyURL(
						themeDisplay.getLayout(), themeDisplay);

					layoutURL = HttpComponentsUtil.addParameter(
						layoutURL, "p_l_mode", Constants.PREVIEW);

					return HttpComponentsUtil.addParameter(
						layoutURL, "disableCommonStyles", Boolean.TRUE);
				}
			).put(
				"getInfoItemOneToManyRelationshipsURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_info_item_one_to_many_relationships")
			).put(
				"getLayoutFriendlyURL",
				_getResourceURL(
					"/layout_content_page_editor/get_layout_friendly_url")
			).put(
				"getLayoutPageTemplateCollectionsURL",
				_getResourceURL(
					"/layout_content_page_editor" +
						"/get_layout_page_template_collections")
			).put(
				"getPageContentsURL",
				_getResourceURL("/layout_content_page_editor/get_page_content")
			).put(
				"getPortletsURL",
				_getResourceURL("/layout_content_page_editor/get_portlets")
			).put(
				"getRolesURL",
				_getResourceURL("/layout_content_page_editor/get_roles")
			).put(
				"getUsersURL",
				_getResourceURL("/layout_content_page_editor/get_users")
			).put(
				"imagesPath",
				portal.getPathContext(httpServletRequest) + "/images"
			).put(
				"isConversionDraft", _isConversionDraft()
			).put(
				"isPrivateLayoutsEnabled",
				() -> {
					Group group = themeDisplay.getScopeGroup();

					return group.isPrivateLayoutsEnabled();
				}
			).put(
				"isSegmentationEnabled", false
			).put(
				"layoutConversionWarningMessages",
				MultiSessionMessages.get(
					portletRequest, "layoutConversionWarningMessages")
			).put(
				"layoutType", String.valueOf(_getLayoutType())
			).put(
				"lookAndFeelURL", getLookAndFeelURL()
			).put(
				"mappingFieldsURL",
				_getResourceURL(
					"/layout_content_page_editor/get_mapping_fields")
			).put(
				"markItemForDeletionURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/mark_item_for_deletion")
			).put(
				"masterLayouts", _getMasterLayouts()
			).put(
				"masterUsed", _isMasterUsed()
			).put(
				"maxNumberOfItemsInEditMode",
				_pageEditorConfiguration.maxNumberOfItemsInEditMode()
			).put(
				"moveItemsURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/move_fragment_entry_link")
			).put(
				"moveStepperFragmentEntryLinkURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/move_stepper_fragment_entry_link")
			).put(
				"paddingOptions",
				() -> {
					Set<Map.Entry<String, String>> entrySet =
						PaddingConverter.externalToInternalValuesMap.entrySet();

					List<Map<String, String>> list = new ArrayList<>();

					for (Map.Entry<String, String> entry : entrySet) {
						list.add(
							HashMapBuilder.put(
								"label", entry.getKey()
							).put(
								"value", entry.getValue()
							).build());
					}

					return list;
				}
			).put(
				"pending",
				() -> {
					Layout draftLayout = themeDisplay.getLayout();

					if (draftLayout.isDenied() || draftLayout.isPending()) {
						return true;
					}

					Layout publishedLayout = _getPublishedLayout();

					if ((publishedLayout != null) &&
						(publishedLayout.isDenied() ||
						 publishedLayout.isPending())) {

						return true;
					}

					return false;
				}
			).put(
				"portletNamespace", getPortletNamespace()
			).put(
				"publishURL", getPublishURL()
			).put(
				"redirectURL", _getRedirect()
			).put(
				"renderFragmentEntriesURL",
				_getResourceURL(
					"/layout_content_page_editor/get_fragment_entry_links")
			).put(
				"restoreCollectionDisplayConfigURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/restore_collection_display_config")
			).put(
				"searchContainerPageMaxDelta",
				PropsValues.SEARCH_CONTAINER_PAGE_MAX_DELTA
			).put(
				"sidebarPanels", getSidebarPanels()
			).put(
				"styleBookEnabled",
				() -> {
					Layout layout = themeDisplay.getLayout();

					Theme theme = layout.getTheme();

					LayoutSet layoutSet = _layoutSetLocalService.fetchLayoutSet(
						themeDisplay.getSiteGroupId(), false);

					if (Objects.equals(
							theme.getThemeId(), layoutSet.getThemeId())) {

						return true;
					}

					return false;
				}
			).put(
				"styleBookEntryId",
				() -> {
					Layout layout = themeDisplay.getLayout();

					return layout.getStyleBookEntryId();
				}
			).put(
				"styleBooks", _getStyleBooks()
			).put(
				"themeColorsCssClasses", _getThemeColorsCssClasses()
			).put(
				"undoUpdateFormConfigURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/undo_form_item_config")
			).put(
				"unmarkItemsForDeletionURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/unmark_items_for_deletion")
			).put(
				"updateCollectionDisplayConfigURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/update_collection_display_config")
			).put(
				"updateConfigurationValuesURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/update_configuration_values")
			).put(
				"updateFormItemConfigURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/update_form_item_config")
			).put(
				"updateFragmentPortletSetsSortURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/update_fragment_portlet_sets_sort_configuration")
			).put(
				"updateFragmentsHighlightedConfigurationURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/update_fragments_highlighted_configuration")
			).put(
				"updateItemConfigURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/update_item_config")
			).put(
				"updateLayoutPageTemplateDataURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/update_layout_page_template_data")
			).put(
				"updatePortletsHighlightedConfigurationURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor" +
						"/update_portlets_highlighted_configuration")
			).put(
				"updateRowColumnsURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/update_row_columns")
			).put(
				"updateRuleURL",
				getFragmentEntryActionURL(
					"/layout_content_page_editor/update_rule")
			).put(
				"workflowEnabled", isWorkflowEnabled()
			).build()
		).put(
			"state",
			HashMapBuilder.<String, Object>put(
				"collections",
				_fragmentCollectionManager.getFragmentCollectionMapsList(
					getGroupId(), httpServletRequest, true, false,
					_getMasterDropZoneLayoutStructureItem(), themeDisplay)
			).put(
				"draft",
				() -> {
					Layout layout = themeDisplay.getLayout();

					return layout.isDraft();
				}
			).put(
				"fragmentEntryLinks", _getFragmentEntryLinks()
			).put(
				"fragments",
				_fragmentCollectionManager.getFragmentCollectionMapsList(
					getGroupId(), httpServletRequest, false, true,
					_getMasterDropZoneLayoutStructureItem(), themeDisplay)
			).put(
				"languageId",
				LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale())
			).put(
				"layoutData",
				() -> {
					LayoutStructure layoutStructure = _getLayoutStructure();

					return layoutStructure.toJSONObject();
				}
			).put(
				"mappingFields", _getMappingFieldsJSONObject()
			).put(
				"masterLayout", _getMasterLayoutJSONObject()
			).put(
				"permissions",
				() -> {
					boolean hasUpdatePermission = _hasPermissions(
						ActionKeys.UPDATE);

					return HashMapBuilder.<String, Object>put(
						ContentPageEditorActionKeys.UPDATE, hasUpdatePermission
					).put(
						ContentPageEditorActionKeys.
							UPDATE_LAYOUT_ADVANCED_OPTIONS,
						() -> {
							if (!hasUpdatePermission) {
								return _hasPermissions(
									ContentPageEditorActionKeys.
										UPDATE_LAYOUT_ADVANCED_OPTIONS);
							}

							return false;
						}
					).put(
						ContentPageEditorActionKeys.UPDATE_LAYOUT_BASIC,
						() -> {
							if (!hasUpdatePermission) {
								return _hasPermissions(
									ContentPageEditorActionKeys.
										UPDATE_LAYOUT_BASIC);
							}

							return false;
						}
					).put(
						ContentPageEditorActionKeys.UPDATE_LAYOUT_CONTENT,
						() -> _hasPermissions(
							ContentPageEditorActionKeys.UPDATE_LAYOUT_CONTENT)
					).put(
						ContentPageEditorActionKeys.UPDATE_LAYOUT_LIMITED,
						() -> {
							if (!hasUpdatePermission) {
								return _hasPermissions(
									ContentPageEditorActionKeys.
										UPDATE_LAYOUT_LIMITED);
							}

							return false;
						}
					).build();
				}
			).put(
				"restrictedItemIds", _getRestrictedItemIds()
			).build()
		).build();
	}

	public String getPortletNamespace() {
		return renderResponse.getNamespace();
	}

	public String getPublishURL() {
		return getFragmentEntryActionURL(
			"/layout_content_page_editor/publish_layout");
	}

	public List<Map<String, Object>> getSidebarPanels() {
		return getSidebarPanels(_getLayoutType());
	}

	public boolean isContentLayout() {
		if (_getLayoutType() == -1) {
			return true;
		}

		return false;
	}

	public boolean isMasterLayout() {
		if (_getLayoutType() ==
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT) {

			return true;
		}

		return false;
	}

	public boolean isWorkflowEnabled() {
		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			Layout.class.getName());
	}

	protected String getFragmentEntryActionURL(String action) {
		return getFragmentEntryActionURL(action, null);
	}

	protected String getFragmentEntryActionURL(String action, String command) {
		return HttpComponentsUtil.addParameter(
			PortletURLBuilder.createActionURL(
				renderResponse
			).setActionName(
				action
			).setCMD(
				() -> {
					if (Validator.isNotNull(command)) {
						return command;
					}

					return null;
				}
			).setBackURL(
				ParamUtil.getString(
					portal.getOriginalServletRequest(httpServletRequest),
					"p_l_back_url", themeDisplay.getURLCurrent())
			).buildString(),
			"p_l_mode", Constants.EDIT);
	}

	protected long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		_groupId = ParamUtil.getLong(
			httpServletRequest, "groupId", themeDisplay.getScopeGroupId());

		return _groupId;
	}

	protected String getLookAndFeelURL() throws Exception {
		return layoutLockManager.getUnlockDraftLayoutURL(
			portal.getLiferayPortletResponse(renderResponse),
			() -> {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)httpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				Layout layout = themeDisplay.getLayout();

				return PortletURLBuilder.create(
					portal.getControlPanelPortletURL(
						httpServletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
						PortletRequest.RENDER_PHASE)
				).setMVCRenderCommandName(
					"/layout_admin/edit_layout"
				).setRedirect(
					themeDisplay.getURLCurrent()
				).setBackURL(
					themeDisplay.getURLCurrent()
				).setParameter(
					"backURLTitle", layout.getName(themeDisplay.getLocale())
				).setParameter(
					"groupId", layout.getGroupId()
				).setParameter(
					"privateLayout", layout.isPrivateLayout()
				).setParameter(
					"screenNavigationEntryKey",
					LayoutScreenNavigationEntryConstants.ENTRY_KEY_DESIGN
				).setParameter(
					"selPlid",
					() -> {
						if (layout.isDraftLayout()) {
							return layout.getClassPK();
						}

						return layout.getPlid();
					}
				).buildString();
			});
	}

	protected long getSegmentsExperienceId() {
		return 0;
	}

	protected List<Map<String, Object>> getSidebarPanels(int layoutType) {
		if (_sidebarPanels != null) {
			return _sidebarPanels;
		}

		List<Map<String, Object>> sidebarPanels = new ArrayList<>();

		for (ContentPageEditorSidebarPanel contentPageEditorSidebarPanel :
				_contentPageEditorSidebarPanels) {

			if (!contentPageEditorSidebarPanel.isVisible(
					themeDisplay.getPermissionChecker(), themeDisplay.getPlid(),
					layoutType)) {

				continue;
			}

			sidebarPanels.add(
				HashMapBuilder.<String, Object>put(
					"icon", contentPageEditorSidebarPanel.getIcon()
				).put(
					"label",
					contentPageEditorSidebarPanel.getLabel(
						themeDisplay.getLocale())
				).put(
					"sidebarPanelId", contentPageEditorSidebarPanel.getId()
				).build());
		}

		_sidebarPanels = sidebarPanels;

		return _sidebarPanels;
	}

	protected final HttpServletRequest httpServletRequest;
	protected final Language language;
	protected final LayoutLockManager layoutLockManager;
	protected final LayoutPageTemplateEntryLocalService
		layoutPageTemplateEntryLocalService;
	protected final Portal portal;
	protected final PortletRequest portletRequest;
	protected final RenderResponse renderResponse;
	protected final StagingGroupHelper stagingGroupHelper;
	protected final ThemeDisplay themeDisplay;

	private Map<String, Object> _getAvailableLanguages() {
		Map<String, Object> availableLanguages = new LinkedHashMap<>();

		for (Locale locale :
				language.getAvailableLocales(themeDisplay.getSiteGroupId())) {

			availableLanguages.put(
				LocaleUtil.toLanguageId(locale),
				HashMapBuilder.<String, Object>put(
					"languageIcon",
					StringUtil.toLowerCase(LocaleUtil.toW3cLanguageId(locale))
				).put(
					"w3cLanguageId", LocaleUtil.toW3cLanguageId(locale)
				).build());
		}

		return availableLanguages;
	}

	private Map<String, Map<String, Object>> _getAvailableViewportSizes() {
		Map<String, Map<String, Object>> availableViewportSizesMap =
			new LinkedHashMap<>();

		for (ViewportSize viewportSize :
				ListUtil.sort(
					Arrays.asList(ViewportSize.values()),
					Comparator.comparingInt(ViewportSize::getOrder))) {

			availableViewportSizesMap.put(
				viewportSize.getViewportSizeId(),
				HashMapBuilder.<String, Object>put(
					"icon", viewportSize.getIcon()
				).put(
					"label",
					language.get(httpServletRequest, viewportSize.getLabel())
				).put(
					"maxWidth", viewportSize.getMaxWidth()
				).put(
					"minWidth", viewportSize.getMinWidth()
				).put(
					"sizeId", viewportSize.getViewportSizeId()
				).build());
		}

		return availableViewportSizesMap;
	}

	private String _getCollectionSelectorURL() {
		return StringPool.BLANK;
	}

	private Map<String, Object> _getDefaultConfigurations() {
		if (_defaultConfigurations != null) {
			return _defaultConfigurations;
		}

		_defaultConfigurations = HashMapBuilder.<String, Object>put(
			"comment",
			() -> {
				EditorConfiguration commentEditorConfiguration =
					EditorConfigurationFactoryUtil.getEditorConfiguration(
						ContentPageEditorPortletKeys.
							CONTENT_PAGE_EDITOR_PORTLET,
						"pageEditorCommentEditor", StringPool.BLANK,
						Collections.emptyMap(), themeDisplay,
						RequestBackedPortletURLFactoryUtil.create(
							httpServletRequest));

				return commentEditorConfiguration.getData();
			}
		).put(
			"rich-text",
			() -> {
				EditorConfiguration richTextEditorConfiguration =
					EditorConfigurationFactoryUtil.getEditorConfiguration(
						ContentPageEditorPortletKeys.
							CONTENT_PAGE_EDITOR_PORTLET,
						"fragmentEntryLinkRichTextEditor", StringPool.BLANK,
						Collections.emptyMap(), themeDisplay,
						RequestBackedPortletURLFactoryUtil.create(
							httpServletRequest));

				return richTextEditorConfiguration.getData();
			}
		).put(
			"text",
			() -> {
				EditorConfiguration editorConfiguration =
					EditorConfigurationFactoryUtil.getEditorConfiguration(
						ContentPageEditorPortletKeys.
							CONTENT_PAGE_EDITOR_PORTLET,
						"fragmentEntryLinkEditor", StringPool.BLANK,
						Collections.emptyMap(), themeDisplay,
						RequestBackedPortletURLFactoryUtil.create(
							httpServletRequest));

				return editorConfiguration.getData();
			}
		).build();

		return _defaultConfigurations;
	}

	private StyleBookEntry _getDefaultMasterStyleBookEntry() {
		if (_defaultMasterStyleBookEntry != null) {
			return _defaultMasterStyleBookEntry;
		}

		_defaultMasterStyleBookEntry =
			DefaultStyleBookEntryUtil.getDefaultMasterStyleBookEntry(
				themeDisplay.getLayout());

		return _defaultMasterStyleBookEntry;
	}

	private StyleBookEntry _getDefaultStyleBookEntry() {
		if (_defaultStyleBookEntry != null) {
			return _defaultStyleBookEntry;
		}

		_defaultStyleBookEntry =
			DefaultStyleBookEntryUtil.getDefaultStyleBookEntry(
				themeDisplay.getLayout());

		return _defaultStyleBookEntry;
	}

	private String _getDiscardDraftURL() {
		Layout publishedLayout = _getPublishedLayout();

		if ((publishedLayout != null) &&
			!Objects.equals(
				publishedLayout.getType(), LayoutConstants.TYPE_PORTLET)) {

			return PortletURLBuilder.create(
				_portletURLFactory.create(
					httpServletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
					PortletRequest.ACTION_PHASE)
			).setActionName(
				"/layout_admin/discard_draft_layout"
			).setRedirect(
				_getRedirect()
			).setParameter(
				"selPlid", themeDisplay.getPlid()
			).buildString();
		}

		return PortletURLBuilder.create(
			portal.getControlPanelPortletURL(
				httpServletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
				PortletRequest.ACTION_PHASE)
		).setActionName(
			"/layout_admin/delete_layout"
		).setRedirect(
			PortletURLBuilder.create(
				portal.getControlPanelPortletURL(
					httpServletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
					PortletRequest.RENDER_PHASE)
			).setParameter(
				"selPlid",
				() -> {
					if (publishedLayout != null) {
						return publishedLayout.getPlid();
					}

					Layout draftLayout = themeDisplay.getLayout();

					return draftLayout.getClassPK();
				}
			).buildString()
		).setParameter(
			"selPlid", themeDisplay.getPlid()
		).buildString();
	}

	private Map<String, Object> _getFragmentEntryLinks() throws Exception {
		if (_fragmentEntryLinks != null) {
			return _fragmentEntryLinks;
		}

		List<FragmentEntryLink> fragmentEntryLinks =
			_fragmentEntryLinkLocalService.
				getFragmentEntryLinksBySegmentsExperienceId(
					getGroupId(), 0, themeDisplay.getPlid(), false);

		LayoutStructure layoutStructure = _getLayoutStructure();

		Map<String, Object> fragmentEntryLinksMap = new HashMap<>(
			_getFragmentEntryLinksMap(
				fragmentEntryLinks, false, layoutStructure));

		Layout layout = themeDisplay.getLayout();

		if (layout.getMasterLayoutPlid() > 0) {
			LayoutPageTemplateEntry masterLayoutPageTemplateEntry =
				layoutPageTemplateEntryLocalService.
					fetchLayoutPageTemplateEntryByPlid(
						layout.getMasterLayoutPlid());

			if (masterLayoutPageTemplateEntry != null) {
				fragmentEntryLinks =
					_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
						getGroupId(), masterLayoutPageTemplateEntry.getPlid());

				fragmentEntryLinksMap.putAll(
					_getFragmentEntryLinksMap(
						fragmentEntryLinks, true, _getMasterLayoutStructure()));
			}
		}

		Map<Long, LayoutStructureItem> fragmentLayoutStructureItems =
			layoutStructure.getFragmentLayoutStructureItems();

		for (Map.Entry<Long, LayoutStructureItem> fragmentLayoutStructureItem :
				fragmentLayoutStructureItems.entrySet()) {

			if (fragmentEntryLinksMap.containsKey(
					String.valueOf(fragmentLayoutStructureItem.getKey()))) {

				continue;
			}

			LayoutStructureItem layoutStructureItem =
				fragmentLayoutStructureItem.getValue();

			if (layoutStructure.isItemMarkedForDeletion(
					layoutStructureItem.getItemId())) {

				continue;
			}

			fragmentEntryLinksMap.put(
				String.valueOf(fragmentLayoutStructureItem.getKey()),
				JSONUtil.put(
					"configuration", _jsonFactory.createJSONObject()
				).put(
					"content", StringPool.BLANK
				).put(
					"defaultConfigurationValues",
					_jsonFactory.createJSONObject()
				).put(
					"editableValues", _jsonFactory.createJSONObject()
				).put(
					"error", Boolean.TRUE
				).put(
					"fragmentEntryLinkId",
					String.valueOf(fragmentLayoutStructureItem.getKey())
				));
		}

		_fragmentEntryLinks = fragmentEntryLinksMap;

		return _fragmentEntryLinks;
	}

	private Map<String, Object> _getFragmentEntryLinksMap(
			List<FragmentEntryLink> fragmentEntryLinks, boolean masterLayout,
			LayoutStructure layoutStructure)
		throws Exception {

		Map<String, Object> fragmentEntryLinksMap = new HashMap<>();

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			DefaultFragmentRendererContext defaultFragmentRendererContext =
				new DefaultFragmentRendererContext(fragmentEntryLink);

			JSONObject jsonObject =
				_fragmentEntryLinkManager.getFragmentEntryLinkJSONObject(
					defaultFragmentRendererContext, fragmentEntryLink,
					httpServletRequest,
					portal.getHttpServletResponse(renderResponse),
					layoutStructure);

			jsonObject.put(
				"error",
				() -> {
					if (SessionErrors.contains(
							httpServletRequest,
							"fragmentEntryContentInvalid")) {

						SessionErrors.clear(httpServletRequest);

						return true;
					}

					return false;
				}
			).put(
				"masterLayout", masterLayout
			);

			FragmentEntry fragmentEntry =
				_fragmentEntryLocalService.fetchFragmentEntry(
					fragmentEntryLink.getFragmentEntryId());

			if ((fragmentEntry == null) &&
				(fragmentEntryLink.getRendererKey() == null)) {

				String portletId = _getPortletId(
					jsonObject.getString("content"));

				PortletConfig portletConfig = PortletConfigFactoryUtil.get(
					portletId);

				if (portletConfig != null) {
					jsonObject.put(
						"name",
						portal.getPortletTitle(
							portletId, themeDisplay.getLocale())
					).put(
						"portletId", portletId
					);
				}
			}

			fragmentEntryLinksMap.put(
				String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
				jsonObject);
		}

		return fragmentEntryLinksMap;
	}

	private LayoutStructure _getLayoutStructure() throws Exception {
		if (_layoutStructure != null) {
			return _layoutStructure;
		}

		_layoutStructure = LayoutStructureUtil.getLayoutStructure(
			themeDisplay.getScopeGroupId(), themeDisplay.getPlid(), 0);

		return _layoutStructure;
	}

	private int _getLayoutType() {
		if (_layoutType != null) {
			return _layoutType;
		}

		Layout layout = themeDisplay.getLayout();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			layoutPageTemplateEntryLocalService.
				fetchLayoutPageTemplateEntryByPlid(layout.getPlid());

		if (layoutPageTemplateEntry == null) {
			layoutPageTemplateEntry =
				layoutPageTemplateEntryLocalService.
					fetchLayoutPageTemplateEntryByPlid(layout.getClassPK());
		}

		if (layoutPageTemplateEntry == null) {
			_layoutType = -1;
		}
		else {
			_layoutType = layoutPageTemplateEntry.getType();
		}

		return _layoutType;
	}

	private JSONObject _getMappingFieldsJSONObject() throws Exception {
		JSONObject mappingFieldsJSONObject = _jsonFactory.createJSONObject();

		Set<LayoutDisplayPageObjectProvider<?>>
			layoutDisplayPageObjectProviders =
				_contentManager.getMappedLayoutDisplayPageObjectProviders(
					getGroupId(), themeDisplay.getPlid());

		Layout layout = themeDisplay.getLayout();

		if (layout.getMasterLayoutPlid() > 0) {
			layoutDisplayPageObjectProviders.addAll(
				_contentManager.getMappedLayoutDisplayPageObjectProviders(
					getGroupId(), layout.getMasterLayoutPlid()));
		}

		for (LayoutDisplayPageObjectProvider<?>
				layoutDisplayPageObjectProvider :
					layoutDisplayPageObjectProviders) {

			String uniqueMappingFieldKey =
				layoutDisplayPageObjectProvider.getClassNameId() +
					StringPool.DASH +
						layoutDisplayPageObjectProvider.getClassTypeId();

			if (mappingFieldsJSONObject.has(uniqueMappingFieldKey)) {
				continue;
			}

			mappingFieldsJSONObject.put(
				uniqueMappingFieldKey, _jsonFactory.createJSONArray());
		}

		return mappingFieldsJSONObject;
	}

	private DropZoneLayoutStructureItem
		_getMasterDropZoneLayoutStructureItem() {

		LayoutStructure masterLayoutStructure = _getMasterLayoutStructure();

		if (masterLayoutStructure == null) {
			return null;
		}

		LayoutStructureItem layoutStructureItem =
			masterLayoutStructure.getDropZoneLayoutStructureItem();

		if (layoutStructureItem == null) {
			return null;
		}

		return (DropZoneLayoutStructureItem)layoutStructureItem;
	}

	private JSONObject _getMasterLayoutJSONObject() {
		return JSONUtil.put(
			"masterLayoutData",
			() -> {
				LayoutStructure layoutStructure = _getMasterLayoutStructure();

				if (layoutStructure != null) {
					return layoutStructure.toJSONObject();
				}

				return null;
			}
		).put(
			"masterLayoutPlid",
			() -> {
				Layout layout = themeDisplay.getLayout();

				return String.valueOf(layout.getMasterLayoutPlid());
			}
		);
	}

	private List<Map<String, Object>> _getMasterLayouts() {
		ArrayList<Map<String, Object>> masterLayouts = new ArrayList<>();

		masterLayouts.add(
			HashMapBuilder.<String, Object>put(
				"imagePreviewURL", StringPool.BLANK
			).put(
				"masterLayoutPlid", "0"
			).put(
				"name", language.get(httpServletRequest, "blank")
			).build());

		List<LayoutPageTemplateEntry> layoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				themeDisplay.getScopeGroupId(),
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				LayoutPageTemplateEntryNameComparator.getInstance(true));

		for (LayoutPageTemplateEntry layoutPageTemplateEntry :
				layoutPageTemplateEntries) {

			masterLayouts.add(
				HashMapBuilder.<String, Object>put(
					"imagePreviewURL",
					layoutPageTemplateEntry.getImagePreviewURL(themeDisplay)
				).put(
					"masterLayoutPlid",
					String.valueOf(layoutPageTemplateEntry.getPlid())
				).put(
					"name", layoutPageTemplateEntry.getName()
				).build());
		}

		return masterLayouts;
	}

	private LayoutStructure _getMasterLayoutStructure() {
		if (_masterLayoutStructure != null) {
			return _masterLayoutStructure;
		}

		Layout layout = themeDisplay.getLayout();

		if (layout.getMasterLayoutPlid() <= 0) {
			return _masterLayoutStructure;
		}

		try {
			_masterLayoutStructure = LayoutStructureUtil.getLayoutStructure(
				layout.getGroupId(), layout.getMasterLayoutPlid(), "DEFAULT");

			return _masterLayoutStructure;
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get master layout structure", exception);
			}
		}

		return _masterLayoutStructure;
	}

	private String _getPortletId(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getId();
	}

	private String _getPortletId(String content) {
		Document document = Jsoup.parse(content);

		Elements elements = document.getElementsByAttributeValueStarting(
			"id", "portlet_");

		if (elements.size() != 1) {
			return StringPool.BLANK;
		}

		Element element = elements.get(0);

		String id = element.id();

		return PortletIdCodec.decodePortletName(id.substring(8));
	}

	private Layout _getPublishedLayout() {
		if (_publishedLayout != null) {
			return _publishedLayout;
		}

		Layout draftLayout = themeDisplay.getLayout();

		_publishedLayout = _layoutLocalService.fetchLayout(
			draftLayout.getClassPK());

		return _publishedLayout;
	}

	private String _getRedirect() {
		if (Validator.isNotNull(_redirect)) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNull(_redirect)) {
			_redirect = portal.escapeRedirect(
				ParamUtil.getString(
					portal.getOriginalServletRequest(httpServletRequest),
					"p_l_back_url", themeDisplay.getURLCurrent()));
		}

		return _redirect;
	}

	private String _getResourceURL(String resourceID) {
		ResourceURL resourceURL = renderResponse.createResourceURL();

		resourceURL.setResourceID(resourceID);

		return HttpComponentsUtil.addParameter(
			resourceURL.toString(), "p_l_mode", Constants.EDIT);
	}

	private List<String> _getRestrictedItemIds() throws Exception {
		if (_restrictedItemIds != null) {
			return _restrictedItemIds;
		}

		_restrictedItemIds = _contentManager.getRestrictedItemIds(
			httpServletRequest, _getLayoutStructure(), themeDisplay);

		return _restrictedItemIds;
	}

	private List<Map<String, Object>> _getStyleBooks() {
		ArrayList<Map<String, Object>> styleBooks = new ArrayList<>();

		List<StyleBookEntry> styleBookEntries =
			_styleBookEntryLocalService.getStyleBookEntries(
				themeDisplay.getScopeGroupId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				StyleBookEntryNameComparator.getInstance(true));

		for (StyleBookEntry styleBookEntry : styleBookEntries) {
			styleBooks.add(
				HashMapBuilder.<String, Object>put(
					"imagePreviewURL",
					styleBookEntry.getImagePreviewURL(themeDisplay)
				).put(
					"name", styleBookEntry.getName()
				).put(
					"styleBookEntryId", styleBookEntry.getStyleBookEntryId()
				).build());
		}

		return styleBooks;
	}

	private String[] _getThemeColorsCssClasses() {
		Theme theme = themeDisplay.getTheme();

		String colorPalette = theme.getSetting("color-palette");

		if (Validator.isNotNull(colorPalette)) {
			return StringUtil.split(colorPalette);
		}

		return new String[] {
			"primary", "success", "danger", "warning", "info", "dark",
			"gray-dark", "secondary", "light", "lighter", "white"
		};
	}

	private boolean _hasPermissions(String actionId) {
		try {
			if (_layoutPermission.contains(
					themeDisplay.getPermissionChecker(), themeDisplay.getPlid(),
					actionId)) {

				return true;
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return false;
	}

	private boolean _isConversionDraft() {
		Layout publishedLayout = _getPublishedLayout();

		if ((publishedLayout != null) &&
			Objects.equals(
				publishedLayout.getType(), LayoutConstants.TYPE_PORTLET)) {

			return true;
		}

		return false;
	}

	private boolean _isMasterUsed() {
		if (_getLayoutType() !=
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT) {

			return false;
		}

		Layout draftLayout = themeDisplay.getLayout();

		int masterUsagesCount = _layoutLocalService.getMasterLayoutsCount(
			themeDisplay.getScopeGroupId(), draftLayout.getClassPK());

		if (masterUsagesCount > 0) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentPageEditorDisplayContext.class);

	private final ContentManager _contentManager;
	private final List<ContentPageEditorSidebarPanel>
		_contentPageEditorSidebarPanels;
	private Map<String, Object> _defaultConfigurations;
	private StyleBookEntry _defaultMasterStyleBookEntry;
	private StyleBookEntry _defaultStyleBookEntry;
	private final FragmentCollectionManager _fragmentCollectionManager;
	private final FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;
	private final FragmentEntryLinkManager _fragmentEntryLinkManager;
	private Map<String, Object> _fragmentEntryLinks;
	private final FragmentEntryLocalService _fragmentEntryLocalService;
	private final FrontendTokenDefinitionRegistry
		_frontendTokenDefinitionRegistry;
	private Long _groupId;
	private final JSONFactory _jsonFactory;
	private final LayoutLocalService _layoutLocalService;
	private final LayoutPageTemplateEntryService
		_layoutPageTemplateEntryService;
	private final LayoutPermission _layoutPermission;
	private final LayoutSetLocalService _layoutSetLocalService;
	private LayoutStructure _layoutStructure;
	private Integer _layoutType;
	private LayoutStructure _masterLayoutStructure;
	private final PageEditorConfiguration _pageEditorConfiguration;
	private final PortletURLFactory _portletURLFactory;
	private Layout _publishedLayout;
	private String _redirect;
	private List<String> _restrictedItemIds;
	private List<Map<String, Object>> _sidebarPanels;
	private final Staging _staging;
	private final StyleBookEntryLocalService _styleBookEntryLocalService;
	private final UserLocalService _userLocalService;
	private final WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}