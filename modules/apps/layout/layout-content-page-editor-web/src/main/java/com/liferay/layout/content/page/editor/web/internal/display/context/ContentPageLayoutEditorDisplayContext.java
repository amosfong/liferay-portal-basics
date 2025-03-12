/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.collection.provider.SingleFormVariationInfoCollectionProvider;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType;
import com.liferay.info.search.InfoSearchClassMapperRegistry;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.criteria.InfoListItemSelectorReturnType;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration;
import com.liferay.layout.content.page.editor.web.internal.constants.ContentPageEditorActionKeys;
import com.liferay.layout.content.page.editor.web.internal.manager.ContentManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentCollectionManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalService;
import com.liferay.learn.LearnMessage;
import com.liferay.learn.LearnMessageUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.style.book.service.StyleBookEntryLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ContentPageLayoutEditorDisplayContext
	extends ContentPageEditorDisplayContext {

	public ContentPageLayoutEditorDisplayContext(
		List<ContentPageEditorSidebarPanel> contentPageEditorSidebarPanels,
		ContentManager contentManager,
		FragmentCollectionManager fragmentCollectionManager,
		FragmentEntryLinkManager fragmentEntryLinkManager,
		FragmentEntryLinkLocalService fragmentEntryLinkLocalService,
		FragmentEntryLocalService fragmentEntryLocalService,
		FrontendTokenDefinitionRegistry frontendTokenDefinitionRegistry,
		GroupLocalService groupLocalService,
		HttpServletRequest httpServletRequest,
		InfoItemServiceRegistry infoItemServiceRegistry,
		InfoSearchClassMapperRegistry infoSearchClassMapperRegistry,
		ItemSelector itemSelector, JSONFactory jsonFactory, Language language,
		LayoutLocalService layoutLocalService,
		LayoutLockManager layoutLockManager,
		LayoutSetLocalService layoutSetLocalService,
		LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService,
		LayoutPageTemplateEntryService layoutPageTemplateEntryService,
		LayoutPageTemplateStructureLocalService
			layoutPageTemplateStructureLocalService,
		LayoutPageTemplateStructureRelLocalService
			layoutPageTemplateStructureRelLocalService,
		LayoutPermission layoutPermission,
		PageEditorConfiguration pageEditorConfiguration, Portal portal,
		PortletRequest portletRequest, PortletURLFactory portletURLFactory,
		RenderResponse renderResponse, Staging staging,
		StagingGroupHelper stagingGroupHelper,
		StyleBookEntryLocalService styleBookEntryLocalService,
		UserLocalService userLocalService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		super(
			contentPageEditorSidebarPanels, contentManager,
			fragmentCollectionManager, fragmentEntryLinkManager,
			fragmentEntryLinkLocalService, fragmentEntryLocalService,
			frontendTokenDefinitionRegistry, httpServletRequest,
			infoItemServiceRegistry, infoSearchClassMapperRegistry,
			itemSelector, jsonFactory, language, layoutLocalService,
			layoutLockManager, layoutPageTemplateEntryLocalService,
			layoutPageTemplateEntryService, layoutPermission,
			layoutSetLocalService, pageEditorConfiguration, portal,
			portletRequest, portletURLFactory, renderResponse, staging,
			stagingGroupHelper, styleBookEntryLocalService, userLocalService,
			workflowDefinitionLinkLocalService);

		_groupLocalService = groupLocalService;
		_layoutPageTemplateStructureLocalService =
			layoutPageTemplateStructureLocalService;
		_layoutPageTemplateStructureRelLocalService =
			layoutPageTemplateStructureRelLocalService;
	}

	@Override
	public Map<String, Object> getEditorContext() throws Exception {
		Map<String, Object> editorContext = super.getEditorContext();
		
		return editorContext;
	}

	@Override
	protected long getSegmentsExperienceId() {
		return 0;
	}

	private InfoCollectionProvider<?> _getInfoCollectionProvider(
		String collectionPK) {

		for (InfoCollectionProvider<?> infoCollectionProvider :
				(List<InfoCollectionProvider<?>>)
					(List<?>)infoItemServiceRegistry.getAllInfoItemServices(
						InfoCollectionProvider.class)) {

			if (Objects.equals(infoCollectionProvider.getKey(), collectionPK)) {
				return infoCollectionProvider;
			}
		}

		return null;
	}

	private String _getInfoCollectionProviderItemTypeLabel(
		InfoCollectionProvider<?> infoCollectionProvider) {

		String className = infoCollectionProvider.getCollectionItemClassName();

		if (Validator.isNotNull(className)) {
			return ResourceActionsUtil.getModelResource(
				themeDisplay.getLocale(), className);
		}

		return StringPool.BLANK;
	}

	private JSONArray _getInfoCollectionProviderLinkedCollectionJSONArray(
		InfoCollectionProvider<?> infoCollectionProvider) {

		return JSONUtil.put(
			JSONUtil.put(
				"itemSubtype",
				() -> {
					if (infoCollectionProvider instanceof
							SingleFormVariationInfoCollectionProvider) {

						SingleFormVariationInfoCollectionProvider<?>
							singleFormVariationInfoCollectionProvider =
								(SingleFormVariationInfoCollectionProvider<?>)
									infoCollectionProvider;

						return singleFormVariationInfoCollectionProvider.
							getFormVariationKey();
					}

					return null;
				}
			).put(
				"itemType", infoCollectionProvider.getCollectionItemClassName()
			).put(
				"key", infoCollectionProvider.getKey()
			).put(
				"title",
				infoCollectionProvider.getLabel(LocaleUtil.getDefault())
			).put(
				"type", InfoListProviderItemSelectorReturnType.class.getName()
			));
	}

	private List<Map<String, Object>> _getLayoutDataList() throws Exception {
		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					themeDisplay.getScopeGroupId(), themeDisplay.getPlid());

		if (layoutPageTemplateStructure == null) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> layoutDataList = new ArrayList<>();

		List<LayoutPageTemplateStructureRel> layoutPageTemplateStructureRels =
			_layoutPageTemplateStructureRelLocalService.
				getLayoutPageTemplateStructureRels(
					layoutPageTemplateStructure.
						getLayoutPageTemplateStructureId());

		for (LayoutPageTemplateStructureRel layoutPageTemplateStructureRel :
				layoutPageTemplateStructureRels) {

			layoutDataList.add(
				HashMapBuilder.<String, Object>put(
					"layoutData",
					JSONFactoryUtil.createJSONObject(
						layoutPageTemplateStructureRel.getData())
				).put(
					"segmentsExperienceId", 0
				).build());
		}

		return layoutDataList;
	}

	private Map<String, Object> _getSelectedMappingTypes() throws Exception {
		Layout layout = themeDisplay.getLayout();

		if (!Objects.equals(
				layout.getType(), LayoutConstants.TYPE_COLLECTION)) {

			return Collections.emptyMap();
		}

		String collectionPK = layout.getTypeSettingsProperty("collectionPK");
		String collectionType = layout.getTypeSettingsProperty(
			"collectionType");

		if (Validator.isNull(collectionPK) ||
			Validator.isNull(collectionType)) {

			return Collections.emptyMap();
		}

		String itemTypeLabel = StringPool.BLANK;
		JSONArray linkedCollectionJSONArray = JSONFactoryUtil.createJSONArray();
		String subtypeLabel = StringPool.BLANK;
		String typeLabel = StringPool.BLANK;
		String subtypeURL = StringPool.BLANK;

		if (Objects.equals(
				collectionType,
				InfoListProviderItemSelectorReturnType.class.getName())) {

			InfoCollectionProvider<?> infoCollectionProvider =
				_getInfoCollectionProvider(collectionPK);

			if (infoCollectionProvider != null) {
				itemTypeLabel = _getInfoCollectionProviderItemTypeLabel(
					infoCollectionProvider);
				linkedCollectionJSONArray =
					_getInfoCollectionProviderLinkedCollectionJSONArray(
						infoCollectionProvider);
				subtypeLabel = infoCollectionProvider.getLabel(
					themeDisplay.getLocale());
			}

			typeLabel = language.get(httpServletRequest, "collection-provider");
		}
		else if (Objects.equals(
					collectionType,
					InfoListItemSelectorReturnType.class.getName())) {

			typeLabel = language.get(
				httpServletRequest, "manual-collection");
		}

		return HashMapBuilder.<String, Object>put(
			"itemType",
			HashMapBuilder.<String, Object>put(
				"groupItemTypeTitle",
				language.get(httpServletRequest, "item-type")
			).put(
				"label", itemTypeLabel
			).build()
		).put(
			"linkedCollection", linkedCollectionJSONArray
		).put(
			"mappingDescription",
			language.get(
				httpServletRequest,
				"this-page-is-associated-to-the-following-collection")
		).put(
			"subtype",
			HashMapBuilder.<String, Object>put(
				"groupSubtypeTitle", language.get(httpServletRequest, "name")
			).put(
				"label", subtypeLabel
			).put(
				"url", subtypeURL
			).build()
		).put(
			"type",
			HashMapBuilder.<String, Object>put(
				"groupTypeTitle", language.get(httpServletRequest, "type")
			).put(
				"label", typeLabel
			).build()
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentPageLayoutEditorDisplayContext.class);

	private final GroupLocalService _groupLocalService;
	private final LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;
	private final LayoutPageTemplateStructureRelLocalService
		_layoutPageTemplateStructureRelLocalService;

}