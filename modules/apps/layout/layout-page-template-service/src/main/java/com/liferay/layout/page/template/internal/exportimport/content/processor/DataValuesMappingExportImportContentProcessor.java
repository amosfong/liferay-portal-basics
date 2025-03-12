/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.exportimport.content.processor;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.exception.ExportImportContentProcessorException;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.info.search.InfoSearchClassMapperRegistry;
import com.liferay.info.staging.InfoStagingClassMapperRegistry;
import com.liferay.item.selector.criteria.InfoListItemSelectorReturnType;
import com.liferay.layout.util.constants.LayoutDataItemTypeConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "content.processor.type=LayoutPageTemplateStructureRelReferences",
	service = ExportImportContentProcessor.class
)
public class DataValuesMappingExportImportContentProcessor
	implements ExportImportContentProcessor<String> {

	@Override
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String data, boolean exportReferencedContent, boolean escapeContent)
		throws Exception {

		if (!JSONUtil.isJSONObject(data)) {
			return data;
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(data);

		_exportContentReferences(
			exportReferencedContent, jsonObject, portletDataContext,
			stagedModel);

		return jsonObject.toString();
	}

	@Override
	public String replaceImportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String data)
		throws Exception {

		if (!JSONUtil.isJSONObject(data)) {
			return data;
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(data);

		_replaceImportContentReferences(
			jsonObject, portletDataContext, stagedModel);

		return jsonObject.toString();
	}

	@Override
	public void validateContentReferences(long groupId, String data) {
	}

	private void _exportCollectionContentReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext,
		StagedModel stagedModel) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (!configJSONObject.has("collection")) {
			return;
		}

		JSONObject collectionJSONObject = configJSONObject.getJSONObject(
			"collection");

		String type = collectionJSONObject.getString("type");

		if (!Objects.equals(
				type, InfoListItemSelectorReturnType.class.getName())) {

			return;
		}
	}

	private void _exportContainerContentReferences(
			boolean exportReferencedContent, JSONObject itemJSONObject,
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (configJSONObject.has("link")) {
			JSONObject linkJSONObject = configJSONObject.getJSONObject("link");

			_exportMappedFieldContentReference(
				exportReferencedContent, linkJSONObject, portletDataContext,
				stagedModel);

			if (linkJSONObject.has("layout")) {
				_exportLayoutContentReference(
					exportReferencedContent,
					linkJSONObject.getJSONObject("layout"), portletDataContext,
					stagedModel);
			}
		}

		if (!configJSONObject.has("styles")) {
			return;
		}

		JSONObject stylesJSONObject = configJSONObject.getJSONObject("styles");

		if (stylesJSONObject.has("backgroundImage")) {
			_exportMappedFieldContentReference(
				exportReferencedContent,
				stylesJSONObject.getJSONObject("backgroundImage"),
				portletDataContext, stagedModel);
		}
	}

	private void _exportContentReferences(
			boolean exportReferencedContent, JSONObject jsonObject,
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!jsonObject.has("items")) {
			return;
		}

		JSONObject itemsJSONObject = jsonObject.getJSONObject("items");

		if (itemsJSONObject == null) {
			return;
		}

		for (String key : itemsJSONObject.keySet()) {
			JSONObject itemJSONObject = itemsJSONObject.getJSONObject(key);

			if (Objects.equals(
					itemJSONObject.get("type"),
					LayoutDataItemTypeConstants.TYPE_CONTAINER)) {

				_exportContainerContentReferences(
					exportReferencedContent, itemJSONObject, portletDataContext,
					stagedModel);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_COLLECTION)) {

				_exportCollectionContentReferences(
					itemJSONObject, portletDataContext, stagedModel);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_FORM)) {

				_exportFormReferences(
					itemJSONObject, portletDataContext, stagedModel);
				_exportFormOrRowContentReferences(
					exportReferencedContent, itemJSONObject, portletDataContext,
					stagedModel);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_ROW)) {

				_exportFormOrRowContentReferences(
					exportReferencedContent, itemJSONObject, portletDataContext,
					stagedModel);
			}
		}
	}

	private void _exportDDMTemplateReference(
			JSONObject editableJSONObject,
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		String mappedField = editableJSONObject.getString(
			"mappedField", editableJSONObject.getString("fieldId"));

		if (!mappedField.startsWith(_DDM_TEMPLATE)) {
			return;
		}

		String ddmTemplateKey = mappedField.substring(_DDM_TEMPLATE.length());

		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchTemplate(
			portletDataContext.getScopeGroupId(),
			_portal.getClassNameId(DDMStructure.class), ddmTemplateKey);

		if (ddmTemplate != null) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, ddmTemplate,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
		}
	}

	private void _exportFormOrRowContentReferences(
			boolean exportReferencedContent, JSONObject itemJSONObject,
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (!configJSONObject.has("styles")) {
			return;
		}

		JSONObject stylesJSONObject = configJSONObject.getJSONObject("styles");

		if (stylesJSONObject.has("backgroundImage")) {
			_exportMappedFieldContentReference(
				exportReferencedContent,
				stylesJSONObject.getJSONObject("backgroundImage"),
				portletDataContext, stagedModel);
		}
	}

	private void _exportFormReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext,
		StagedModel stagedModel) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (!configJSONObject.has("classNameId")) {
			return;
		}

		long classNameId = configJSONObject.getLong("classNameId");

		String className = StringPool.BLANK;

		try {
			className = _portal.getClassName(classNameId);
		}
		catch (RuntimeException runtimeException) {
			if (_log.isDebugEnabled()) {
				_log.debug(runtimeException);
			}

			return;
		}

		Element element = portletDataContext.getExportDataElement(stagedModel);

		element.addAttribute(
			"formClassName",
			_infoStagingClassMapperRegistry.getStagingClassName(className));
	}

	private void _exportLayoutContentReference(
			boolean exportReferencedContent, JSONObject layoutJSONObject,
			PortletDataContext portletDataContext,
			StagedModel referrerStagedModel)
		throws Exception {

		if (layoutJSONObject.length() == 0) {
			return;
		}

		Layout layout = _layoutLocalService.fetchLayout(
			layoutJSONObject.getLong("groupId"),
			layoutJSONObject.getBoolean("privateLayout"),
			layoutJSONObject.getLong("layoutId"));

		if (layout == null) {
			return;
		}

		layoutJSONObject.put("plid", layout.getPlid());

		if (exportReferencedContent) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, referrerStagedModel, layout,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
		}
		else {
			Element entityElement = portletDataContext.getExportDataElement(
				referrerStagedModel);

			portletDataContext.addReferenceElement(
				referrerStagedModel, entityElement, layout,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}
	}

	private void _exportMappedFieldContentReference(
			boolean exportReferencedContent, JSONObject jsonObject,
			PortletDataContext portletDataContext, StagedModel stagedModel)
		throws Exception {

		long classNameId = jsonObject.getLong("classNameId");
		long classPK = jsonObject.getLong("classPK");

		if ((classNameId == 0) || (classPK == 0)) {
			return;
		}

		_exportDDMTemplateReference(
			jsonObject, portletDataContext, stagedModel);

		String className = _portal.getClassName(classNameId);

		jsonObject.put("className", className);
	}

	private void _replaceCollectionImportContentReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext,
		StagedModel stagedModel) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (!configJSONObject.has("collection")) {
			return;
		}

		JSONObject collectionJSONObject = configJSONObject.getJSONObject(
			"collection");

		String type = collectionJSONObject.getString("type");

		if (!Objects.equals(
				type, InfoListItemSelectorReturnType.class.getName())) {

			return;
		}
	}

	private void _replaceContainerImportContentReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (configJSONObject.has("link")) {
			JSONObject linkJSONObject = configJSONObject.getJSONObject("link");

			_replaceMappedFieldImportContentReferences(
				linkJSONObject, portletDataContext);

			if (linkJSONObject.has("layout")) {
				_replaceImportLayoutReferences(
					linkJSONObject.getJSONObject("layout"), portletDataContext);
			}
		}

		if (!configJSONObject.has("styles")) {
			return;
		}

		JSONObject stylesJSONObject = configJSONObject.getJSONObject("styles");

		if (stylesJSONObject.has("backgroundImage")) {
			_replaceMappedFieldImportContentReferences(
				stylesJSONObject.getJSONObject("backgroundImage"),
				portletDataContext);
		}
	}

	private void _replaceFormImportContentReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext,
		StagedModel stagedModel) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		Element element = portletDataContext.getImportDataStagedModelElement(
			stagedModel);

		String formClassName = GetterUtil.getString(
			element.attributeValue("formClassName"));

		configJSONObject.put(
			"classNameId",
			_portal.getClassNameId(
				_infoStagingClassMapperRegistry.getClassName(formClassName)));
	}

	private void _replaceFormOrRowImportContentReferences(
		JSONObject itemJSONObject, PortletDataContext portletDataContext) {

		if (!itemJSONObject.has("config")) {
			return;
		}

		JSONObject configJSONObject = itemJSONObject.getJSONObject("config");

		if (!configJSONObject.has("styles")) {
			return;
		}

		JSONObject stylesJSONObject = configJSONObject.getJSONObject("styles");

		if (stylesJSONObject.has("backgroundImage")) {
			_replaceMappedFieldImportContentReferences(
				stylesJSONObject.getJSONObject("backgroundImage"),
				portletDataContext);
		}
	}

	private void _replaceImportContentReferences(
		JSONObject jsonObject, PortletDataContext portletDataContext,
		StagedModel stagedModel) {

		if (!jsonObject.has("items")) {
			return;
		}

		JSONObject itemsJSONObject = jsonObject.getJSONObject("items");

		if (itemsJSONObject == null) {
			return;
		}

		for (String key : itemsJSONObject.keySet()) {
			JSONObject itemJSONObject = itemsJSONObject.getJSONObject(key);

			if (Objects.equals(
					itemJSONObject.get("type"),
					LayoutDataItemTypeConstants.TYPE_CONTAINER)) {

				_replaceContainerImportContentReferences(
					itemJSONObject, portletDataContext);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_COLLECTION)) {

				_replaceCollectionImportContentReferences(
					itemJSONObject, portletDataContext, stagedModel);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_FORM)) {

				_replaceFormImportContentReferences(
					itemJSONObject, portletDataContext, stagedModel);
				_replaceFormOrRowImportContentReferences(
					itemJSONObject, portletDataContext);
			}
			else if (Objects.equals(
						itemJSONObject.get("type"),
						LayoutDataItemTypeConstants.TYPE_ROW)) {

				_replaceFormOrRowImportContentReferences(
					itemJSONObject, portletDataContext);
			}
		}
	}

	private void _replaceImportLayoutReferences(
		JSONObject layoutJSONObject, PortletDataContext portletDataContext) {

		if (layoutJSONObject.length() == 0) {
			return;
		}

		long plid = GetterUtil.getLong(layoutJSONObject.remove("plid"));

		Map<Long, Long> layoutNewPrimaryKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class.getName());

		Layout layout = _layoutLocalService.fetchLayout(
			layoutNewPrimaryKeys.getOrDefault(plid, 0L));

		if (layout == null) {
			return;
		}

		layoutJSONObject.put(
			"groupId", layout.getGroupId()
		).put(
			"layoutId", layout.getLayoutId()
		).put(
			"layoutUuid", layout.getUuid()
		).put(
			"privateLayout", layout.isPrivateLayout()
		);
	}

	private void _replaceMappedFieldImportContentReferences(
		JSONObject jsonObject, PortletDataContext portletDataContext) {

		String className = jsonObject.getString("className");

		if (Validator.isNull(className)) {
			return;
		}

		String mappedField = jsonObject.getString(
			"mappedField", jsonObject.getString("fieldId"));

		if (mappedField.startsWith(_DDM_TEMPLATE)) {
			String ddmTemplateKey = mappedField.substring(
				_DDM_TEMPLATE.length());

			Map<String, String> ddmTemplateKeys =
				(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
					DDMTemplate.class + ".ddmTemplateKey");

			String importedDDMTemplateKey = MapUtil.getString(
				ddmTemplateKeys, ddmTemplateKey, ddmTemplateKey);

			if (jsonObject.has("mappedField")) {
				jsonObject.put(
					"mappedField", _DDM_TEMPLATE + importedDDMTemplateKey);
			}
			else {
				jsonObject.put(
					"fieldId", _DDM_TEMPLATE + importedDDMTemplateKey);
			}
		}
	}

	private static final String _DDM_TEMPLATE = "ddmTemplate_";

	private static final Log _log = LogFactoryUtil.getLog(
		DataValuesMappingExportImportContentProcessor.class);

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private InfoSearchClassMapperRegistry _infoSearchClassMapperRegistry;

	@Reference
	private InfoStagingClassMapperRegistry _infoStagingClassMapperRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}