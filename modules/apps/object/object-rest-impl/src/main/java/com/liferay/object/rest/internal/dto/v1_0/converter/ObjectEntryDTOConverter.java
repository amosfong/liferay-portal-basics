/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.dto.v1_0.converter;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.constants.ObjectActionKeys;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectFieldSettingConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.entry.util.ObjectEntryDTOConverterUtil;
import com.liferay.object.entry.util.ObjectEntryValuesUtil;
import com.liferay.object.field.setting.util.ObjectFieldSettingUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.models.ObjectRelatedModelsProvider;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistry;
import com.liferay.object.rest.dto.v1_0.AuditEvent;
import com.liferay.object.rest.dto.v1_0.AuditFieldChange;
import com.liferay.object.rest.dto.v1_0.FileEntry;
import com.liferay.object.rest.dto.v1_0.Folder;
import com.liferay.object.rest.dto.v1_0.ListEntry;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.dto.v1_0.Scope;
import com.liferay.object.rest.dto.v1_0.Status;
import com.liferay.object.rest.dto.v1_0.TaxonomyCategoryBrief;
import com.liferay.object.rest.dto.v1_0.util.CreatorUtil;
import com.liferay.object.rest.dto.v1_0.util.LinkUtil;
import com.liferay.object.rest.internal.dto.v1_0.util.TaxonomyCategoryBriefUtil;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PermissionService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.security.audit.storage.service.AuditEventLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.extension.EntityExtensionHandler;
import com.liferay.portal.vulcan.extension.ExtensionProviderRegistry;
import com.liferay.portal.vulcan.extension.util.ExtensionUtil;
import com.liferay.portal.vulcan.fields.NestedFieldsSupplier;
import com.liferay.portal.vulcan.jaxrs.extension.ExtendedEntity;
import com.liferay.portal.vulcan.permission.Permission;
import com.liferay.portal.vulcan.permission.PermissionUtil;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Javier de Arcos
 */
@Component(
	property = "dto.class.name=com.liferay.object.model.ObjectEntry",
	service = DTOConverter.class
)
public class ObjectEntryDTOConverter
	implements DTOConverter<com.liferay.object.model.ObjectEntry, ObjectEntry> {

	@Override
	public String getContentType() {
		return ObjectEntry.class.getSimpleName();
	}

	@Override
	public ObjectEntry toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		ObjectDefinition objectDefinition =
			(ObjectDefinition)dtoConverterContext.getAttribute(
				"objectDefinition");

		ObjectEntry objectEntry = ObjectEntry.unsafeToDTO(
			(String)dtoConverterContext.getAttribute("payload"));

		User user = dtoConverterContext.getUser();

		objectEntry.setActions(dtoConverterContext::getActions);

		if (objectEntry.getStatus() == null) {
			objectEntry.setStatus(
				() -> new Status() {
					{
						setCode(() -> WorkflowConstants.STATUS_APPROVED);
						setLabel(() -> WorkflowConstants.LABEL_APPROVED);
						setLabel_i18n(
							() -> _language.get(
								user.getLocale(),
								WorkflowConstants.LABEL_APPROVED));
					}
				});
		}

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				objectDefinition.getObjectDefinitionId());

		for (ObjectField objectField : objectFields) {
			if (!Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_PICKLIST)) {

				continue;
			}

			Map<String, Object> properties = objectEntry.getProperties();

			Map<String, String> map = (Map<String, String>)properties.get(
				objectField.getName());

			properties.put(
				objectField.getName(),
				_getListEntry(
					dtoConverterContext, map.get("key"),
					objectField.getListTypeDefinitionId()));

			objectEntry.setProperties(() -> properties);
		}

		return objectEntry;
	}

	@Override
	public ObjectEntry toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		ObjectDefinition objectDefinition = _getObjectDefinition(
			dtoConverterContext, objectEntry);

		return new ObjectEntry() {
			{
				setActions(dtoConverterContext::getActions);
				setAuditEvents(
					() -> _toAuditEvents(
						dtoConverterContext, objectDefinition, objectEntry));
				setCreator(
					() -> CreatorUtil.toCreator(
						_portal, dtoConverterContext.getUriInfo(),
						_userLocalService.fetchUser(objectEntry.getUserId())));
				setDateCreated(objectEntry::getCreateDate);
				setDateModified(objectEntry::getModifiedDate);
				setExternalReferenceCode(objectEntry::getExternalReferenceCode);
				setId(objectEntry::getObjectEntryId);
				setKeywords(
					() -> {
						if (!objectDefinition.isEnableCategorization()) {
							return null;
						}

						return ListUtil.toArray(
							_assetTagLocalService.getTags(
								objectDefinition.getClassName(),
								objectEntry.getObjectEntryId()),
							AssetTag.NAME_ACCESSOR);
					});
				setPermissions(
					() -> _toPermissions(objectDefinition, objectEntry));
				setProperties(
					() -> _toProperties(
						dtoConverterContext, objectDefinition, objectEntry));
				setScopeKey(() -> _getScopeKey(objectDefinition, objectEntry));
				setStatus(
					() -> new Status() {
						{
							setCode(objectEntry::getStatus);
							setLabel(
								() -> WorkflowConstants.getStatusLabel(
									objectEntry.getStatus()));
							setLabel_i18n(
								() -> _language.get(
									LanguageResources.getResourceBundle(
										dtoConverterContext.getLocale()),
									WorkflowConstants.getStatusLabel(
										objectEntry.getStatus())));
						}
					});
				setTaxonomyCategoryBriefs(
					() -> {
						if (!objectDefinition.isEnableCategorization()) {
							return null;
						}

						return TransformUtil.transformToArray(
							_assetCategoryLocalService.getCategories(
								objectDefinition.getClassName(),
								objectEntry.getObjectEntryId()),
							assetCategory ->
								TaxonomyCategoryBriefUtil.
									toTaxonomyCategoryBrief(
										assetCategory, dtoConverterContext),
							TaxonomyCategoryBrief.class);
					});
			}
		};
	}

	private void _addManyToOneObjectRelationshipNames(
		ObjectField objectField, String objectFieldName,
		ObjectRelationship objectRelationship, long primaryKey,
		Map<String, UnsafeSupplier<Object, Exception>> unsafeSuppliers,
		Map<String, Serializable> values) {

		String objectRelationshipERCObjectFieldName =
			ObjectFieldSettingUtil.getValue(
				ObjectFieldSettingConstants.
					NAME_OBJECT_RELATIONSHIP_ERC_OBJECT_FIELD_NAME,
				objectField);

		String relatedObjectEntryERC = GetterUtil.getString(
			values.get(objectRelationshipERCObjectFieldName));

		if (unsafeSuppliers.get(objectRelationship.getName()) == null) {
			unsafeSuppliers.put(
				objectRelationship.getName() + "ERC",
				() -> relatedObjectEntryERC);
		}

		unsafeSuppliers.put(objectFieldName, () -> primaryKey);
		unsafeSuppliers.put(
			objectRelationshipERCObjectFieldName, () -> relatedObjectEntryERC);
	}

	private void _addManyToOneRelatedObjectEntries(
			DTOConverterContext dtoConverterContext, String objectFieldName,
			ObjectRelationship objectRelationship, long primaryKey,
			Map<String, UnsafeSupplier<Object, Exception>> unsafeSuppliers)
		throws Exception {

		String relatedObjectDefinitionName = StringUtil.replaceLast(
			objectFieldName.substring(
				objectFieldName.lastIndexOf(StringPool.UNDERLINE) + 1),
			"Id", "");

		String manyToOneRelationshipName = StringUtil.removeLast(
			objectFieldName, "Id");

		AtomicReference<Serializable> relatedObjectEntryAtomicReference =
			new AtomicReference<>();

		Map<String, Serializable> nestedFieldValues =
			NestedFieldsSupplier.supply(
				nestedFieldName -> {
					if (!nestedFieldName.contains(
							relatedObjectDefinitionName) &&
						!StringUtil.equals(
							nestedFieldName, objectRelationship.getName())) {

						return null;
					}

					if (!StringUtil.equals(
							nestedFieldName, manyToOneRelationshipName) &&
						!StringUtil.equals(
							nestedFieldName, objectRelationship.getName()) &&
						!StringUtil.equals(
							nestedFieldName, relatedObjectDefinitionName) &&
						_log.isWarnEnabled()) {

						_log.warn(
							StringBundler.concat(
								"Replace the deprecated nested field \"",
								nestedFieldName, "\" with \"",
								objectRelationship.getName()));
					}

					if (relatedObjectEntryAtomicReference.get() != null) {
						return relatedObjectEntryAtomicReference.get();
					}

					ObjectDefinition objectDefinition =
						_objectDefinitionLocalService.getObjectDefinition(
							objectRelationship.getObjectDefinitionId1());

					if (objectDefinition.isUnmodifiableSystemObject()) {
						SystemObjectDefinitionManager
							systemObjectDefinitionManager =
								_systemObjectDefinitionManagerRegistry.
									getSystemObjectDefinitionManager(
										objectDefinition.getName());

						BaseModel<?> baseModel =
							systemObjectDefinitionManager.
								getBaseModelByExternalReferenceCode(
									systemObjectDefinitionManager.
										getBaseModelExternalReferenceCode(
											primaryKey),
									objectDefinition.getCompanyId());

						Map<String, Object> values =
							ObjectEntryDTOConverterUtil.toValues(
								baseModel,
								dtoConverterContext.getDTOConverterRegistry(),
								objectDefinition.getName(),
								_systemObjectDefinitionManagerRegistry,
								dtoConverterContext.getUser());

						if (MapUtil.isNotEmpty(values)) {
							ObjectField objectField =
								_objectFieldLocalService.fetchObjectField(
									objectDefinition.getTitleObjectFieldId());

							if (objectField == null) {
								objectField =
									_objectFieldLocalService.getObjectField(
										objectDefinition.
											getObjectDefinitionId(),
										"id");
							}

							values.put(
								objectField.getName(),
								ObjectEntryValuesUtil.getTitleFieldValue(
									objectField.getBusinessType(),
									baseModel.getModelAttributes(), objectField,
									dtoConverterContext.getUser(), values));

							values.putAll(
								_objectEntryLocalService.
									getExtensionDynamicObjectDefinitionTableValues(
										objectDefinition, primaryKey));
						}

						relatedObjectEntryAtomicReference.set(
							(Serializable)values);
					}
					else {
						relatedObjectEntryAtomicReference.set(
							toDTO(
								_getDTOConverterContext(
									dtoConverterContext, primaryKey),
								_objectEntryLocalService.getObjectEntry(
									primaryKey)));
					}

					return relatedObjectEntryAtomicReference.get();
				});

		if (nestedFieldValues == null) {
			return;
		}

		for (Map.Entry<String, Serializable> entry :
				nestedFieldValues.entrySet()) {

			String nestedFieldName = entry.getKey();

			if (StringUtil.equals(
					nestedFieldName, objectRelationship.getName())) {

				unsafeSuppliers.put(
					objectRelationship.getName(), entry::getValue);
			}

			if (nestedFieldName.contains(relatedObjectDefinitionName) ||
				StringUtil.equals(
					nestedFieldName, objectRelationship.getName())) {

				unsafeSuppliers.put(manyToOneRelationshipName, entry::getValue);
			}
		}
	}

	private String _getDateString(
		ObjectField objectField, Timestamp timestamp) {

		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

		if (objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_DATE) ||
			StringUtil.equals(
				ObjectFieldSettingUtil.getValue(
					ObjectFieldSettingConstants.NAME_TIME_STORAGE, objectField),
				ObjectFieldSettingConstants.VALUE_CONVERT_TO_UTC)) {

			pattern += "'Z'";
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(timestamp);
	}

	private DTOConverterContext _getDTOConverterContext(
		DTOConverterContext dtoConverterContext, long objectEntryId) {

		UriInfo uriInfo = dtoConverterContext.getUriInfo();

		return new DefaultDTOConverterContext(
			dtoConverterContext.isAcceptAllLanguages(), null,
			dtoConverterContext.getDTOConverterRegistry(),
			dtoConverterContext.getHttpServletRequest(), objectEntryId,
			dtoConverterContext.getLocale(), uriInfo,
			dtoConverterContext.getUser());
	}

	private FileEntry _getFileEntry(
			ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry,
			ObjectField objectField, long fileEntryId, String objectFieldName)
		throws Exception {

		FileEntry fileEntry = new FileEntry();

		DLFileEntry dlFileEntry = _dLFileEntryLocalService.fetchDLFileEntry(
			fileEntryId);

		if (dlFileEntry == null) {
			return fileEntry;
		}

		fileEntry.setExternalReferenceCode(
			dlFileEntry::getExternalReferenceCode);

		fileEntry.setFileBase64(
			() -> (String)NestedFieldsSupplier.supply(
				objectFieldName + ".fileBase64",
				fieldName -> Base64.encode(
					_file.getBytes(dlFileEntry.getContentStream()))));
		fileEntry.setFolder(
			() -> (Folder)NestedFieldsSupplier.supply(
				objectFieldName + ".folder",
				fieldName -> {
					if (!Objects.equals(
							ObjectFieldSettingConstants.VALUE_DOCS_AND_MEDIA,
							ObjectFieldSettingUtil.getValue(
								ObjectFieldSettingConstants.NAME_FILE_SOURCE,
								objectField))) {

						return null;
					}

					Folder folder = new Folder();

					folder.setExternalReferenceCode(
						() -> {
							if (dlFileEntry.getFolderId() == 0) {
								return null;
							}

							DLFolder dlFolder = dlFileEntry.getFolder();

							return dlFolder.getExternalReferenceCode();
						});
					folder.setSiteId(dlFileEntry::getGroupId);

					return folder;
				}));

		fileEntry.setId(dlFileEntry::getFileEntryId);
		fileEntry.setLink(
			() -> LinkUtil.toLink(
				_dlAppService, dlFileEntry, _dlURLHelper,
				objectDefinition.getExternalReferenceCode(),
				objectEntry.getExternalReferenceCode(), _portal));
		fileEntry.setName(dlFileEntry::getFileName);
		fileEntry.setScope(
			() -> {
				if ((objectEntry.getGroupId() == dlFileEntry.getGroupId()) &&
					!Objects.equals(
						objectDefinition.getScope(),
						ObjectDefinitionConstants.SCOPE_COMPANY)) {

					return null;
				}

				Scope scope = new Scope();

				Group group = _groupLocalService.getGroup(
					dlFileEntry.getGroupId());

				scope.setExternalReferenceCode(group::getExternalReferenceCode);
				scope.setType(
					() -> {
						if (group.getType() == GroupConstants.TYPE_DEPOT) {
							return Scope.Type.ASSET_LIBRARY;
						}

						return Scope.Type.SITE;
					});

				return scope;
			});

		return fileEntry;
	}

	private ListEntry _getListEntry(
		DTOConverterContext dtoConverterContext, String key,
		long listTypeDefinitionId) {

		if (StringUtil.equals(key, StringPool.BLANK)) {
			return new ListEntry() {
				{
					setKey(() -> StringPool.BLANK);
				}
			};
		}

		ListTypeEntry listTypeEntry =
			_listTypeEntryLocalService.fetchListTypeEntry(
				listTypeDefinitionId, key);

		if (listTypeEntry == null) {
			return null;
		}

		return new ListEntry() {
			{
				setKey(listTypeEntry::getKey);
				setName(
					() -> listTypeEntry.getName(
						dtoConverterContext.getLocale()));
				setName_i18n(
					() -> LocalizedMapUtil.getI18nMap(
						dtoConverterContext.isAcceptAllLanguages(),
						listTypeEntry.getNameMap()));
			}
		};
	}

	private String _getLocalizedValue(
			DTOConverterContext dtoConverterContext, Long groupId,
			Map<String, Serializable> objectField_i18n)
		throws Exception {

		String serializable = GetterUtil.getString(
			objectField_i18n.get(
				String.valueOf(dtoConverterContext.getLocale())));

		if (Validator.isNotNull(serializable)) {
			return serializable;
		}

		User user = dtoConverterContext.getUser();

		if (user != null) {
			serializable = GetterUtil.getString(
				objectField_i18n.get(String.valueOf(user.getLocale())));

			if (Validator.isNotNull(serializable)) {
				return serializable;
			}
		}

		return GetterUtil.getString(
			objectField_i18n.get(
				String.valueOf(_portal.getSiteDefaultLocale(groupId))));
	}

	private Map<String, UnsafeSupplier<Object, Exception>>
			_getNestedFieldsRelatedProperties(
				DTOConverterContext dtoConverterContext, long groupId,
				ObjectDefinition objectDefinition, long primaryKey)
		throws Exception {

		return NestedFieldsSupplier.supplyUnsafeSupplier(
			nestedFieldName -> {
				ObjectRelationship objectRelationship =
					_objectRelationshipLocalService.
						fetchObjectRelationshipByObjectDefinitionId1(
							objectDefinition.getObjectDefinitionId(),
							nestedFieldName);

				if ((objectRelationship == null) ||
					!objectRelationship.isAllowedObjectRelationshipType(
						objectRelationship.getType())) {

					return null;
				}

				ObjectDefinition relatedObjectDefinition =
					_objectDefinitionLocalService.getObjectDefinition(
						objectRelationship.getObjectDefinitionId2());

				if (!relatedObjectDefinition.isActive()) {
					return null;
				}

				ObjectRelatedModelsProvider objectRelatedModelsProvider =
					_objectRelatedModelsProviderRegistry.
						getObjectRelatedModelsProvider(
							relatedObjectDefinition.getClassName(),
							relatedObjectDefinition.getCompanyId(),
							objectRelationship.getType());

				List<?> relatedModels =
					objectRelatedModelsProvider.getRelatedModels(
						groupId, objectRelationship.getObjectRelationshipId(),
						primaryKey, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				if (relatedObjectDefinition.isUnmodifiableSystemObject()) {
					SystemObjectDefinitionManager
						systemObjectDefinitionManager =
							_systemObjectDefinitionManagerRegistry.
								getSystemObjectDefinitionManager(
									relatedObjectDefinition.getName());

					return () -> TransformUtil.transformToArray(
						relatedModels,
						relatedModel -> _toExtendedEntity(
							(BaseModel<?>)relatedModel, dtoConverterContext,
							relatedObjectDefinition,
							systemObjectDefinitionManager),
						Object.class);
				}

				return () -> TransformUtil.transformToArray(
					relatedModels,
					relatedModel -> {
						com.liferay.object.model.ObjectEntry objectEntry =
							(com.liferay.object.model.ObjectEntry)relatedModel;

						return toDTO(
							_getDTOConverterContext(
								dtoConverterContext,
								objectEntry.getObjectEntryId()),
							objectEntry);
					},
					ObjectEntry.class);
			});
	}

	private ObjectDefinition _getObjectDefinition(
			DTOConverterContext dtoConverterContext,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		ObjectDefinition objectDefinition =
			(ObjectDefinition)dtoConverterContext.getAttribute(
				"objectDefinition");

		if (objectDefinition == null) {
			objectDefinition =
				_objectDefinitionLocalService.getObjectDefinition(
					objectEntry.getObjectDefinitionId());
		}

		return objectDefinition;
	}

	private String _getScopeKey(
		ObjectDefinition objectDefinition,
		com.liferay.object.model.ObjectEntry objectEntry) {

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		if (objectScopeProvider.isGroupAware()) {
			Group group = _groupLocalService.fetchGroup(
				objectEntry.getGroupId());

			if (group == null) {
				return null;
			}

			return group.getGroupKey();
		}

		return null;
	}

	private AuditEvent[] _toAuditEvents(
			DTOConverterContext dtoConverterContext,
			ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		return NestedFieldsSupplier.supply(
			"auditEvents",
			nestedFieldNames -> {
				if (!objectDefinition.isEnableObjectEntryHistory() ||
					!_objectEntryService.hasModelResourcePermission(
						objectDefinition.getObjectDefinitionId(),
						objectEntry.getObjectEntryId(),
						ObjectActionKeys.OBJECT_ENTRY_HISTORY)) {

					return null;
				}

				return TransformUtil.transformToArray(
					_auditEventLocalService.getAuditEvents(
						0, 0, 0, null, null, null, null, null,
						String.valueOf(objectEntry.getObjectEntryId()), null,
						null, null, 0, null, false, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS),
					auditEvent -> {
						AuditEvent newAuditEvent = new AuditEvent();

						newAuditEvent.setAuditFieldChanges(
							() -> _toAuditFieldChanges(
								auditEvent.getAdditionalInfo(),
								auditEvent.getEventType()));
						newAuditEvent.setCreator(
							() -> CreatorUtil.toCreator(
								_portal, dtoConverterContext.getUriInfo(),
								_userLocalService.fetchUser(
									auditEvent.getUserId())));
						newAuditEvent.setDateCreated(auditEvent::getCreateDate);
						newAuditEvent.setEventType(auditEvent::getEventType);

						return newAuditEvent;
					},
					AuditEvent.class);
			});
	}

	private AuditFieldChange[] _toAuditFieldChanges(
			String additionalInfo, String eventType)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject(additionalInfo);

		if (StringUtil.equals(eventType, EventTypes.ADD)) {
			Map<String, Object> map = jsonObject.toMap();

			return TransformUtil.transformToArray(
				map.keySet(),
				key -> new AuditFieldChange() {
					{
						setName(() -> key);
						setNewValue(() -> map.get(key));
					}
				},
				AuditFieldChange.class);
		}

		return JSONUtil.toArray(
			jsonObject.getJSONArray("attributes"),
			attributeJSONObject -> new AuditFieldChange() {
				{
					setName(() -> attributeJSONObject.getString("name"));
					setNewValue(() -> attributeJSONObject.get("newValue"));
					setOldValue(() -> attributeJSONObject.get("oldValue"));
				}
			},
			AuditFieldChange.class);
	}

	private ExtendedEntity _toExtendedEntity(
			BaseModel<?> baseModel, DTOConverterContext dtoConverterContext,
			ObjectDefinition objectDefinition,
			SystemObjectDefinitionManager systemObjectDefinitionManager)
		throws Exception {

		DTOConverter<BaseModel<?>, ?> dtoConverter =
			ObjectEntryDTOConverterUtil.getDTOConverter(
				dtoConverterContext.getDTOConverterRegistry(),
				systemObjectDefinitionManager);

		Object dto = ObjectEntryDTOConverterUtil.toDTO(
			baseModel, dtoConverterContext.getDTOConverterRegistry(),
			systemObjectDefinitionManager, dtoConverterContext.getUser());

		Map<String, Serializable> nestedFieldsRelatedProperties = null;

		EntityExtensionHandler entityExtensionHandler =
			ExtensionUtil.getEntityExtensionHandler(
				dtoConverter.getExternalDTOClassName(),
				objectDefinition.getCompanyId(), _extensionProviderRegistry);

		if (entityExtensionHandler != null) {
			nestedFieldsRelatedProperties =
				entityExtensionHandler.getExtendedProperties(
					objectDefinition.getCompanyId(),
					dtoConverterContext.getUserId(), dto);
		}

		return ExtendedEntity.extend(dto, nestedFieldsRelatedProperties, null);
	}

	private Permission[] _toPermissions(
			ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		return NestedFieldsSupplier.supply(
			"permissions",
			nestedFieldNames -> {
				_permissionService.checkPermission(
					objectEntry.getGroupId(), objectDefinition.getClassName(),
					objectEntry.getObjectEntryId());

				Collection<Permission> permissions =
					PermissionUtil.getPermissions(
						objectDefinition.getCompanyId(),
						_resourceActionLocalService.getResourceActions(
							objectDefinition.getClassName()),
						objectEntry.getObjectEntryId(),
						objectDefinition.getClassName(), null);

				return permissions.toArray(new Permission[0]);
			});
	}

	private Map<String, Object> _toProperties(
			DTOConverterContext dtoConverterContext,
			ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		Map<String, UnsafeSupplier<Object, Exception>> unsafeSuppliers =
			new HashMap<>();

		Map<String, Serializable> values = objectEntry.getValues();

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				objectDefinition.getObjectDefinitionId());

		for (ObjectField objectField : objectFields) {
			if (objectField.isMetadata()) {
				continue;
			}

			String objectFieldName = objectField.getName();

			Serializable serializable = values.get(objectFieldName);

			if (objectField.isLocalized()) {
				String i18nObjectFieldName =
					objectField.getI18nObjectFieldName();

				Map<String, Serializable> objectField_i18n =
					(Map<String, Serializable>)values.get(i18nObjectFieldName);

				unsafeSuppliers.put(
					i18nObjectFieldName, () -> objectField_i18n);

				if (objectField_i18n != null) {
					serializable = _getLocalizedValue(
						dtoConverterContext, objectEntry.getGroupId(),
						objectField_i18n);
				}
			}

			if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

				long fileEntryId = GetterUtil.getLong(
					values.get(objectField.getName()));

				if (fileEntryId == 0) {
					continue;
				}

				unsafeSuppliers.put(
					objectFieldName,
					() -> _getFileEntry(
						objectDefinition, objectEntry, objectField, fileEntryId,
						objectFieldName));
			}
			else if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_DATE) ||
					 objectField.compareBusinessType(
						 ObjectFieldConstants.BUSINESS_TYPE_DATE_TIME)) {

				Timestamp timestamp = (Timestamp)serializable;

				if (timestamp == null) {
					continue;
				}

				unsafeSuppliers.put(
					objectFieldName,
					() -> _getDateString(objectField, timestamp));
			}
			else if (objectField.compareBusinessType(
						ObjectFieldConstants.
							BUSINESS_TYPE_MULTISELECT_PICKLIST)) {

				if (objectField.getListTypeDefinitionId() == 0) {
					continue;
				}

				Serializable finalSerializable = serializable;

				unsafeSuppliers.put(
					objectFieldName,
					() -> TransformUtil.transformToList(
						StringUtil.split(
							(String)finalSerializable,
							StringPool.COMMA_AND_SPACE),
						key -> _getListEntry(
							dtoConverterContext, key,
							objectField.getListTypeDefinitionId())));
			}
			else if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_PICKLIST)) {

				if (objectField.getListTypeDefinitionId() == 0) {
					continue;
				}

				Serializable finalSerializable = serializable;

				unsafeSuppliers.put(
					objectFieldName,
					() -> _getListEntry(
						dtoConverterContext, (String)finalSerializable,
						objectField.getListTypeDefinitionId()));
			}
			else if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_RICH_TEXT)) {

				Serializable finalSerializable = serializable;

				unsafeSuppliers.put(objectFieldName, () -> finalSerializable);
				unsafeSuppliers.put(
					objectFieldName + "RawText",
					() -> HtmlParserUtil.extractText(
						GetterUtil.getString(finalSerializable)));
			}
			else if (Objects.equals(
						objectField.getRelationshipType(),
						ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

				long primaryKey = GetterUtil.getLong(serializable);

				ObjectRelationship objectRelationship =
					_objectRelationshipLocalService.
						fetchObjectRelationshipByObjectFieldId2(
							objectField.getObjectFieldId());

				if (primaryKey > 0) {
					_addManyToOneRelatedObjectEntries(
						dtoConverterContext, objectFieldName,
						objectRelationship, primaryKey, unsafeSuppliers);
				}

				_addManyToOneObjectRelationshipNames(
					objectField, objectFieldName, objectRelationship,
					primaryKey, unsafeSuppliers, values);
			}
			else {
				Serializable finalSerializable = serializable;

				unsafeSuppliers.put(objectFieldName, () -> finalSerializable);
			}
		}

		values.remove(objectDefinition.getPKObjectFieldName());

		Map<String, UnsafeSupplier<Object, Exception>>
			nestedFieldsRelatedProperties = _getNestedFieldsRelatedProperties(
				dtoConverterContext, objectEntry.getGroupId(), objectDefinition,
				objectEntry.getObjectEntryId());

		if (nestedFieldsRelatedProperties != null) {
			unsafeSuppliers.putAll(nestedFieldsRelatedProperties);
		}

		return (Map<String, Object>)(Map)unsafeSuppliers;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryDTOConverter.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private AuditEventLocalService _auditEventLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLFileEntryLocalService _dLFileEntryLocalService;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference
	private ExtensionProviderRegistry _extensionProviderRegistry;

	@Reference
	private File _file;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private ListTypeEntryLocalService _listTypeEntryLocalService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelatedModelsProviderRegistry
		_objectRelatedModelsProviderRegistry;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private PermissionService _permissionService;

	@Reference
	private Portal _portal;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private SystemObjectDefinitionManagerRegistry
		_systemObjectDefinitionManagerRegistry;

	@Reference
	private UserLocalService _userLocalService;

}