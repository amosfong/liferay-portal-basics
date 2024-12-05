/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.system.info.item.provider;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.layout.page.template.info.item.provider.DisplayPageInfoItemFieldSetProvider;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.entry.util.ObjectEntryDTOConverterUtil;
import com.liferay.object.info.field.converter.ObjectFieldInfoFieldConverter;
import com.liferay.object.info.item.ObjectEntryInfoItemFields;
import com.liferay.object.info.item.provider.util.ObjectEntryInfoItemValuesProviderUtil;
import com.liferay.object.info.item.util.ObjectEntryInfoItemUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManagerRegistry;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.extension.EntityExtensionHandler;
import com.liferay.portal.vulcan.extension.ExtensionProviderRegistry;
import com.liferay.portal.vulcan.extension.util.ExtensionUtil;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Carolina Barbosa
 */
public class SystemObjectEntryInfoItemFieldValuesProvider
	implements InfoItemFieldValuesProvider<SystemObjectEntry> {

	public SystemObjectEntryInfoItemFieldValuesProvider(
		DisplayPageInfoItemFieldSetProvider displayPageInfoItemFieldSetProvider,
		DLAppLocalService dlAppLocalService, DLURLHelper dlURLHelper,
		DTOConverterRegistry dtoConverterRegistry,
		ExtensionProviderRegistry extensionProviderRegistry,
		InfoItemFieldReaderFieldSetProvider infoItemFieldReaderFieldSetProvider,
		String itemClassName,
		ListTypeEntryLocalService listTypeEntryLocalService,
		ObjectActionLocalService objectActionLocalService,
		ObjectDefinition objectDefinition,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectEntryManagerRegistry objectEntryManagerRegistry,
		ObjectFieldInfoFieldConverter objectFieldInfoFieldConverter,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectRelationshipLocalService objectRelationshipLocalService,
		ObjectScopeProviderRegistry objectScopeProviderRegistry,
		SystemObjectDefinitionManager systemObjectDefinitionManager,
		TemplateInfoItemFieldSetProvider templateInfoItemFieldSetProvider) {

		_displayPageInfoItemFieldSetProvider =
			displayPageInfoItemFieldSetProvider;
		_dlAppLocalService = dlAppLocalService;
		_dlURLHelper = dlURLHelper;
		_dtoConverterRegistry = dtoConverterRegistry;
		_extensionProviderRegistry = extensionProviderRegistry;
		_infoItemFieldReaderFieldSetProvider =
			infoItemFieldReaderFieldSetProvider;
		_itemClassName = itemClassName;
		_listTypeEntryLocalService = listTypeEntryLocalService;
		_objectActionLocalService = objectActionLocalService;
		_objectDefinition = objectDefinition;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectEntryLocalService = objectEntryLocalService;
		_objectEntryManagerRegistry = objectEntryManagerRegistry;
		_objectFieldInfoFieldConverter = objectFieldInfoFieldConverter;
		_objectFieldLocalService = objectFieldLocalService;
		_objectRelationshipLocalService = objectRelationshipLocalService;
		_objectScopeProviderRegistry = objectScopeProviderRegistry;
		_systemObjectDefinitionManager = systemObjectDefinitionManager;
		_templateInfoItemFieldSetProvider = templateInfoItemFieldSetProvider;
	}

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(
		SystemObjectEntry systemObjectEntry) {

		try {
			InfoItemReference infoItemReference = new InfoItemReference(
				_itemClassName,
				new ClassPKInfoItemIdentifier(systemObjectEntry.getClassPK()));

			ThemeDisplay themeDisplay =
				ObjectEntryInfoItemUtil.getThemeDisplay();

			return InfoItemFieldValues.builder(
			).infoFieldValues(
				_getInfoFieldValues(themeDisplay, systemObjectEntry.getValues())
			).infoFieldValues(
				_displayPageInfoItemFieldSetProvider.getInfoFieldValues(
					infoItemReference, StringPool.BLANK,
					SystemObjectEntry.class.getSimpleName(), systemObjectEntry,
					themeDisplay)
			).infoFieldValues(
				_infoItemFieldReaderFieldSetProvider.getInfoFieldValues(
					_itemClassName, systemObjectEntry)
			).infoFieldValues(
				_templateInfoItemFieldSetProvider.getInfoFieldValues(
					_itemClassName, systemObjectEntry)
			).infoItemReference(
				infoItemReference
			).build();
		}
		catch (Exception exception) {
			throw new RuntimeException("Unexpected exception", exception);
		}
	}

	private List<InfoFieldValue<Object>> _getInfoFieldValues(
			ThemeDisplay themeDisplay, Map<String, Object> values)
		throws Exception {

		if (MapUtil.isEmpty(values)) {
			return Collections.emptyList();
		}

		List<InfoFieldValue<Object>> objectEntryFieldValues = new ArrayList<>();

		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.objectEntryIdInfoField,
				GetterUtil.getLong(values.get("id"))));

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(_objectDefinition.getClassName());

		BaseModel<?> baseModel =
			(BaseModel<?>)persistedModelLocalService.getPersistedModel(
				GetterUtil.getLong(values.get("id")));

		Map<String, Object> baseModelAttributes =
			baseModel.getModelAttributes();

		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.authorInfoField,
				baseModelAttributes.get("userName")));
		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.createDateInfoField,
				baseModelAttributes.get("createDate")));
		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.externalReferenceCodeInfoField,
				baseModelAttributes.get("externalReferenceCode")));
		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.modifiedDateInfoField,
				baseModelAttributes.get("modifiedDate")));
		objectEntryFieldValues.add(
			new InfoFieldValue<>(
				ObjectEntryInfoItemFields.statusInfoField,
				WorkflowConstants.getStatusLabel(
					GetterUtil.getInteger(baseModelAttributes.get("status")))));

		DTOConverter<?, ?> dtoConverter =
			ObjectEntryDTOConverterUtil.getDTOConverter(
				_dtoConverterRegistry, _systemObjectDefinitionManager);

		EntityExtensionHandler entityExtensionHandler =
			ExtensionUtil.getEntityExtensionHandler(
				dtoConverter.getExternalDTOClassName(),
				_objectDefinition.getCompanyId(), _extensionProviderRegistry);

		if ((entityExtensionHandler != null) && (themeDisplay != null)) {
			values.putAll(
				entityExtensionHandler.getExtendedProperties(
					_objectDefinition.getCompanyId(), themeDisplay.getUserId(),
					values));
		}

		objectEntryFieldValues.addAll(
			ObjectEntryInfoItemValuesProviderUtil.getInfoFieldValues(
				_dlAppLocalService, _dlURLHelper, _listTypeEntryLocalService,
				_objectActionLocalService, _objectDefinition,
				_objectDefinitionLocalService, _objectEntryLocalService,
				_objectEntryManagerRegistry, _objectFieldInfoFieldConverter,
				_objectFieldLocalService,
				_objectFieldLocalService.getObjectFields(
					_objectDefinition.getObjectDefinitionId()),
				_objectRelationshipLocalService, _objectScopeProviderRegistry,
				themeDisplay, values));

		return objectEntryFieldValues;
	}

	private final DisplayPageInfoItemFieldSetProvider
		_displayPageInfoItemFieldSetProvider;
	private final DLAppLocalService _dlAppLocalService;
	private final DLURLHelper _dlURLHelper;
	private final DTOConverterRegistry _dtoConverterRegistry;
	private final ExtensionProviderRegistry _extensionProviderRegistry;
	private final InfoItemFieldReaderFieldSetProvider
		_infoItemFieldReaderFieldSetProvider;
	private final String _itemClassName;
	private final ListTypeEntryLocalService _listTypeEntryLocalService;
	private final ObjectActionLocalService _objectActionLocalService;
	private final ObjectDefinition _objectDefinition;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectEntryManagerRegistry _objectEntryManagerRegistry;
	private final ObjectFieldInfoFieldConverter _objectFieldInfoFieldConverter;
	private final ObjectFieldLocalService _objectFieldLocalService;
	private final ObjectRelationshipLocalService
		_objectRelationshipLocalService;
	private final ObjectScopeProviderRegistry _objectScopeProviderRegistry;
	private final SystemObjectDefinitionManager _systemObjectDefinitionManager;
	private final TemplateInfoItemFieldSetProvider
		_templateInfoItemFieldSetProvider;

}