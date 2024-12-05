/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service.impl;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntryOrganizationRelTable;
import com.liferay.account.model.AccountEntryTable;
import com.liferay.account.model.AccountEntryUserRelTable;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.link.constants.AssetLinkConstants;
import com.liferay.asset.link.service.AssetLinkLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.expression.CreateExpressionRequest;
import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;
import com.liferay.dynamic.data.mapping.util.NumberUtil;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.action.engine.ObjectActionEngine;
import com.liferay.object.action.util.ObjectActionThreadLocal;
import com.liferay.object.configuration.ObjectConfiguration;
import com.liferay.object.constants.ObjectActionTriggerConstants;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectFieldSettingConstants;
import com.liferay.object.constants.ObjectFieldValidationConstants;
import com.liferay.object.constants.ObjectFilterConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.definition.util.ObjectDefinitionThreadLocal;
import com.liferay.object.entry.ObjectEntryContext;
import com.liferay.object.entry.contributor.ObjectEntryValuesContributor;
import com.liferay.object.entry.util.ObjectEntryThreadLocal;
import com.liferay.object.exception.DuplicateObjectEntryExternalReferenceCodeException;
import com.liferay.object.exception.NoSuchObjectDefinitionException;
import com.liferay.object.exception.NoSuchObjectFieldException;
import com.liferay.object.exception.ObjectDefinitionScopeException;
import com.liferay.object.exception.ObjectEntryStatusException;
import com.liferay.object.exception.ObjectEntryValuesException;
import com.liferay.object.exception.ObjectRelationshipDeletionTypeException;
import com.liferay.object.field.attachment.AttachmentManager;
import com.liferay.object.field.business.type.ObjectFieldBusinessType;
import com.liferay.object.field.business.type.ObjectFieldBusinessTypeRegistry;
import com.liferay.object.field.setting.util.ObjectFieldSettingUtil;
import com.liferay.object.field.util.ObjectFieldUtil;
import com.liferay.object.internal.entry.util.ObjectEntrySearchUtil;
import com.liferay.object.internal.entry.util.ObjectEntryUtil;
import com.liferay.object.internal.filter.parser.CurrentUserObjectFilterParser;
import com.liferay.object.internal.filter.parser.DateRangeObjectFilterParser;
import com.liferay.object.internal.filter.parser.EqualityOperatorsObjectFilterParser;
import com.liferay.object.internal.filter.parser.InclusionOperatorsObjectFilterParser;
import com.liferay.object.internal.filter.parser.ObjectFilterParser;
import com.liferay.object.internal.sort.SortDSLQueryVisitor;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryTable;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.object.model.ObjectFilter;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.model.ObjectState;
import com.liferay.object.model.ObjectStateFlow;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionLocalizationTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionLocalizationTableFactory;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTableUtil;
import com.liferay.object.petra.sql.dsl.DynamicObjectRelationshipMappingTable;
import com.liferay.object.related.models.ObjectRelatedModelsProvider;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistry;
import com.liferay.object.relationship.util.ObjectRelationshipUtil;
import com.liferay.object.rest.filter.factory.FilterFactory;
import com.liferay.object.scope.CompanyScoped;
import com.liferay.object.scope.ObjectDefinitionScoped;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.search.StrictObjectReindexThreadLocal;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFieldSettingLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectStateFlowLocalService;
import com.liferay.object.service.ObjectStateLocalService;
import com.liferay.object.service.base.ObjectEntryLocalServiceBaseImpl;
import com.liferay.object.service.persistence.ObjectDefinitionPersistence;
import com.liferay.object.service.persistence.ObjectFieldPersistence;
import com.liferay.object.service.persistence.ObjectFieldSettingPersistence;
import com.liferay.object.service.persistence.ObjectRelationshipPersistence;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.object.tree.Edge;
import com.liferay.object.tree.Node;
import com.liferay.object.tree.ObjectDefinitionTreeFactory;
import com.liferay.object.tree.ObjectEntryTreeFactory;
import com.liferay.object.tree.Tree;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.expression.Alias;
import com.liferay.petra.sql.dsl.expression.Expression;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.expression.ScalarDSLQueryAlias;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.petra.sql.dsl.spi.expression.Scalar;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.dao.jdbc.postgresql.PostgreSQLJDBCUtil;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.CurrentConnection;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.encryptor.Encryptor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.OrganizationTable;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Users_OrgsTable;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelper;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PermissionService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.BigDecimalUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;

import java.math.BigDecimal;

import java.nio.charset.StandardCharsets;

import java.security.Key;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Brian Wing Shun Chan
 */
@Component(
	configurationPid = "com.liferay.object.configuration.ObjectConfiguration",
	property = "model.class.name=com.liferay.object.model.ObjectEntry",
	service = AopService.class
)
public class ObjectEntryLocalServiceImpl
	extends ObjectEntryLocalServiceBaseImpl {

	@Override
	public ObjectEntry addObjectEntry(
			long userId, long groupId, long objectDefinitionId,
			Map<String, Serializable> values, ServiceContext serviceContext)
		throws PortalException {

		serviceContext.setStrictAdd(true);

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				serviceContext.setStrictAdd(false);

				return null;
			});

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		_validateGroupId(groupId, objectDefinition.getScope());

		int workflowAction = serviceContext.getWorkflowAction();

		_validateWorkflowAction(
			objectDefinition.isEnableObjectEntryDraft(), null, workflowAction);

		User user = _userLocalService.getUser(userId);

		_fillDefaultValue(objectDefinitionId, values);

		_contributeValues(groupId, objectDefinition, userId, values);

		long objectEntryId = counterLocalService.increment();

		_validateValues(
			null, user.isGuestUser(), groupId, objectDefinition, objectEntryId,
			serviceContext, userId, values);

		Map<String, Serializable> insertedValues = new HashMap<>();

		_insertIntoLocalizationTable(
			insertedValues, objectDefinition, objectEntryId, values,
			workflowAction);

		boolean dynamicObjectDefinitionStaticValues = _insertIntoTable(
			_getDynamicObjectDefinitionTable(objectDefinitionId),
			insertedValues, objectEntryId, values, workflowAction);
		boolean extensionDynamicObjectDefinitionStaticValues = _insertIntoTable(
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId),
			insertedValues, objectEntryId, values, workflowAction);

		ObjectEntry objectEntry = objectEntryPersistence.create(objectEntryId);

		objectEntry.setGroupId(groupId);
		objectEntry.setCompanyId(user.getCompanyId());
		objectEntry.setUserId(user.getUserId());
		objectEntry.setUserName(user.getFullName());
		objectEntry.setCreateDate(new Date());
		objectEntry.setObjectDefinitionId(objectDefinitionId);

		_setExternalReferenceCode(objectEntry, values);
		_setRootObjectEntryId(objectDefinition, objectEntry, values);

		objectEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		objectEntry.setStatusByUserId(user.getUserId());
		objectEntry.setStatusDate(serviceContext.getModifiedDate(null));

		if (dynamicObjectDefinitionStaticValues &&
			extensionDynamicObjectDefinitionStaticValues) {

			_addObjectRelationshipERCFieldValue(
				objectEntry.getObjectDefinitionId(), insertedValues);

			objectEntry.setValues(insertedValues);
		}

		if (!objectDefinition.isRootDescendantNode()) {
			_resourcePermissionLocalService.addResourcePermissions(
				objectEntry.getCompanyId(), objectEntry.getGroupId(),
				objectEntry.getUserId(), objectDefinition.getClassName(),
				String.valueOf(objectEntry.getPrimaryKey()), false,
				new ServiceContext() {
					{
						setIndexingEnabled(false);
						setStrictAdd(true);
					}
				});
		}

		if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {
			try {
				ObjectEntryThreadLocal.setSkipObjectValidationRules(true);

				objectEntry = objectEntryPersistence.update(objectEntry);
			}
			finally {
				ObjectEntryThreadLocal.setSkipObjectValidationRules(false);
			}

			_updateAsset(
				serviceContext.getUserId(), objectEntry,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(),
				serviceContext.getAssetLinkEntryIds(),
				serviceContext.getAssetPriority(), serviceContext);
		}

		_startWorkflowInstance(userId, objectEntry, serviceContext, false);

		_updateResourcePermissions(
			objectDefinition, objectEntry, serviceContext);

		boolean clearObjectEntryIdsMap =
			ObjectActionThreadLocal.isClearObjectEntryIdsMap();

		try {
			if (clearObjectEntryIdsMap) {
				ObjectActionThreadLocal.clearObjectEntryIdsMap();
			}

			ObjectActionThreadLocal.setClearObjectEntryIdsMap(false);

			_executeObjectActions(
				objectEntry.getCompanyId(),
				ObjectActionTriggerConstants.KEY_ON_AFTER_ADD, objectDefinition,
				objectEntry, null, serviceContext.getLanguageId(), user);
		}
		finally {
			ObjectActionThreadLocal.setClearObjectEntryIdsMap(
				clearObjectEntryIdsMap);
		}

		return objectEntry;
	}

	@Override
	public ObjectEntry addObjectEntry(
			String externalReferenceCode, long userId,
			ObjectDefinition objectDefinition)
		throws PortalException {

		ObjectEntry objectEntry = objectEntryPersistence.create(
			counterLocalService.increment());

		objectEntry.setExternalReferenceCode(externalReferenceCode);

		User user = _userLocalService.getUser(userId);

		objectEntry.setCompanyId(user.getCompanyId());
		objectEntry.setUserId(user.getUserId());
		objectEntry.setUserName(user.getFullName());

		objectEntry.setObjectDefinitionId(
			objectDefinition.getObjectDefinitionId());
		objectEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		objectEntry.setStatusDate(new Date());

		return objectEntryPersistence.updateImpl(objectEntry);
	}

	@Override
	public void addOrUpdateExtensionDynamicObjectDefinitionTableValues(
			long userId, ObjectDefinition objectDefinition, long primaryKey,
			Map<String, Serializable> values, ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		_validateValues(
			null, user.isGuestUser(), 0, objectDefinition, primaryKey,
			serviceContext, userId, values);

		insertIntoOrUpdateExtensionTable(
			userId, objectDefinition.getObjectDefinitionId(), primaryKey,
			values);
	}

	@Override
	public ObjectEntry addOrUpdateObjectEntry(
			String externalReferenceCode, long userId, long groupId,
			long objectDefinitionId, Map<String, Serializable> values,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		if (groupId != 0) {
			Group group = _groupLocalService.getGroup(groupId);

			if (user.getCompanyId() != group.getCompanyId()) {
				throw new PrincipalException();
			}
		}

		ObjectEntry objectEntry = null;

		if (Validator.isNotNull(externalReferenceCode)) {
			objectEntry = objectEntryPersistence.fetchByERC_C_ODI(
				externalReferenceCode, user.getCompanyId(), objectDefinitionId);

			if (objectEntry != null) {
				return updateObjectEntry(
					userId, objectEntry.getObjectEntryId(), values,
					serviceContext);
			}
		}

		objectEntry = addObjectEntry(
			userId, groupId, objectDefinitionId, values, serviceContext);

		if (Validator.isNotNull(externalReferenceCode)) {
			objectEntry.setExternalReferenceCode(externalReferenceCode);

			try {
				if (serviceContext.getWorkflowAction() ==
						WorkflowConstants.ACTION_SAVE_DRAFT) {

					ObjectEntryThreadLocal.setSkipObjectValidationRules(true);
				}

				objectEntry = objectEntryPersistence.update(objectEntry);
			}
			finally {
				ObjectEntryThreadLocal.setSkipObjectValidationRules(false);
			}
		}

		_reindex(objectEntry);

		return objectEntry;
	}

	@Override
	public void deleteExtensionDynamicObjectDefinitionTableValues(
			ObjectDefinition objectDefinition, long primaryKey)
		throws PortalException {

		Map<String, Serializable> extensionDynamicObjectDefinitionTableValues =
			getExtensionDynamicObjectDefinitionTableValues(
				objectDefinition, primaryKey);

		_deleteFromTable(
			objectDefinition.getExtensionDBTableName(),
			objectDefinition.getPKObjectFieldDBColumnName(), primaryKey);

		deleteRelatedObjectEntries(
			0, objectDefinition.getObjectDefinitionId(), primaryKey);

		_deleteFileEntries(
			Collections.emptyMap(), objectDefinition.getObjectDefinitionId(),
			extensionDynamicObjectDefinitionTableValues);
	}

	@Override
	public ObjectEntry deleteObjectEntry(long objectEntryId)
		throws PortalException {

		ObjectEntry objectEntry = objectEntryPersistence.findByPrimaryKey(
			objectEntryId);

		return objectEntryLocalService.deleteObjectEntry(objectEntry);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public ObjectEntry deleteObjectEntry(ObjectEntry objectEntry)
		throws PortalException {

		ObjectActionThreadLocal.clearObjectEntryIdsMap();

		objectEntry = objectEntryPersistence.remove(objectEntry);

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectEntry.getObjectDefinitionId());

		_workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			objectEntry.getCompanyId(), objectEntry.getNonzeroGroupId(),
			objectDefinition.getClassName(), objectEntry.getObjectEntryId());

		_deleteFileEntries(
			Collections.emptyMap(), objectDefinition.getObjectDefinitionId(),
			objectEntry::getValues);

		if (!ObjectDefinitionThreadLocal.isDeleteObjectDefinitionId(
				objectDefinition.getObjectDefinitionId())) {

			_resourceLocalService.deleteResource(
				objectEntry.getCompanyId(), objectDefinition.getClassName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				objectEntry.getObjectEntryId());

			_assetEntryLocalService.deleteEntry(
				objectDefinition.getClassName(),
				objectEntry.getObjectEntryId());

			_deleteFromTable(
				objectDefinition.getDBTableName(),
				objectDefinition.getPKObjectFieldDBColumnName(),
				objectEntry.getObjectEntryId());
			_deleteFromTable(
				objectDefinition.getExtensionDBTableName(),
				objectDefinition.getPKObjectFieldDBColumnName(),
				objectEntry.getObjectEntryId());

			if (objectDefinition.isEnableLocalization()) {
				_deleteFromTable(
					objectDefinition.getLocalizationDBTableName(),
					objectDefinition.getPKObjectFieldDBColumnName(),
					objectEntry.getObjectEntryId());
			}
		}

		deleteRelatedObjectEntries(
			objectEntry.getGroupId(), objectDefinition.getObjectDefinitionId(),
			objectEntry.getPrimaryKey());

		if (!objectDefinition.isActive() ||
			!objectDefinition.isEnableIndexSearch()) {

			return objectEntry;
		}

		Indexer<ObjectEntry> indexer = IndexerRegistryUtil.getIndexer(
			objectDefinition.getClassName());

		indexer.delete(objectEntry);

		return objectEntry;
	}

	@Override
	public ObjectEntry deleteObjectEntry(
			String externalReferenceCode, long companyId, long groupId)
		throws PortalException {

		ObjectEntry objectEntry = objectEntryPersistence.findByERC_G_C(
			externalReferenceCode, groupId, companyId);

		return objectEntryLocalService.deleteObjectEntry(objectEntry);
	}

	@Override
	public void deleteRelatedObjectEntries(
			long groupId, long objectDefinitionId, long primaryKey)
		throws PortalException {

		List<ObjectRelationship> objectRelationships =
			_objectRelationshipPersistence.findByObjectDefinitionId1(
				objectDefinitionId);

		for (ObjectRelationship objectRelationship : objectRelationships) {
			ObjectDefinition objectDefinition2 =
				_objectDefinitionPersistence.findByPrimaryKey(
					objectRelationship.getObjectDefinitionId2());

			if (WorkflowConstants.STATUS_DRAFT ==
					objectDefinition2.getStatus()) {

				continue;
			}

			ObjectRelatedModelsProvider objectRelatedModelsProvider =
				_objectRelatedModelsProviderRegistry.
					getObjectRelatedModelsProvider(
						objectDefinition2.getClassName(),
						objectDefinition2.getCompanyId(),
						objectRelationship.getType());

			try {
				ObjectEntryThreadLocal.setSkipObjectEntryResourcePermission(
					true);

				String deletionType = objectRelationship.getDeletionType();

				if (ObjectEntryThreadLocal.isDisassociateRelatedModels()) {
					deletionType =
						ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE;
				}

				objectRelatedModelsProvider.deleteRelatedModel(
					PrincipalThreadLocal.getUserId(), groupId,
					objectRelationship.getObjectRelationshipId(), primaryKey,
					deletionType);
			}
			catch (PrincipalException principalException) {
				throw new ObjectRelationshipDeletionTypeException(
					principalException.getMessage());
			}
			finally {
				ObjectEntryThreadLocal.setSkipObjectEntryResourcePermission(
					false);
			}
		}
	}

	@Override
	public ObjectEntry fetchManyToOneObjectEntry(
			long groupId, long objectRelationshipId, long primaryKey)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.findByPrimaryKey(
				objectRelationshipId);

		if (!Objects.equals(
				objectRelationship.getType(),
				ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

			throw new UnsupportedOperationException();
		}

		DynamicObjectDefinitionTable dynamicObjectDefinitionTable = null;

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectRelationship.getObjectDefinitionId1());

		ObjectDefinition relatedObjectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectRelationship.getObjectDefinitionId2());

		if (relatedObjectDefinition.isUnmodifiableSystemObject()) {
			dynamicObjectDefinitionTable =
				_getExtensionDynamicObjectDefinitionTable(
					objectRelationship.getObjectDefinitionId2());
		}
		else {
			dynamicObjectDefinitionTable = _getDynamicObjectDefinitionTable(
				relatedObjectDefinition.getObjectDefinitionId());

			Column<DynamicObjectDefinitionTable, Long> column =
				(Column<DynamicObjectDefinitionTable, Long>)
					dynamicObjectDefinitionTable.getColumn(
						StringBundler.concat(
							"r_", objectRelationship.getName(), "_",
							objectDefinition.getPKObjectFieldName()));

			if (column == null) {
				dynamicObjectDefinitionTable =
					_getExtensionDynamicObjectDefinitionTable(
						objectRelationship.getObjectDefinitionId2());
			}
		}

		DSLQuery dslQuery = _getFetchManyToOneObjectEntryDSLQuery(
			dynamicObjectDefinitionTable, groupId, objectRelationship,
			primaryKey, dynamicObjectDefinitionTable.getPrimaryKeyColumn());

		if (_log.isDebugEnabled()) {
			_log.debug("Get one to many related object entries: " + dslQuery);
		}

		List<ObjectEntry> objectEntries = objectEntryPersistence.dslQuery(
			dslQuery);

		if (objectEntries.isEmpty()) {
			return null;
		}

		return objectEntries.get(0);
	}

	@Override
	public ObjectEntry fetchObjectEntry(
		String externalReferenceCode, long objectDefinitionId) {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.fetchByPrimaryKey(objectDefinitionId);

		if (objectDefinition == null) {
			return null;
		}

		return objectEntryPersistence.fetchByERC_C_ODI(
			externalReferenceCode, objectDefinition.getCompanyId(),
			objectDefinitionId);
	}

	@Override
	public Map<Object, Long> getAggregationCounts(
			long groupId, long objectDefinitionId, String aggregationTerm,
			Predicate predicate, int start, int end)
		throws PortalException {

		Map<Object, Long> aggregationCounts = new HashMap<>();

		Table table = _objectFieldLocalService.getTable(
			objectDefinitionId, aggregationTerm);

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectDefinitionId, aggregationTerm);

		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(objectDefinitionId);
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId);

		DSLQuery dslQuery = DSLQueryFactoryUtil.select(
			table.getColumn(objectField.getDBColumnName()),
			DSLFunctionFactoryUtil.countDistinct(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()
			).as(
				"aggregationCount"
			)
		).from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn())
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()
			)
		).where(
			ObjectEntryTable.INSTANCE.objectDefinitionId.eq(
				objectDefinitionId
			).and(
				Predicate.withParentheses(predicate)
			).and(
				_getPermissionWherePredicate(
					dynamicObjectDefinitionTable, groupId)
			)
		).groupBy(
			table.getColumn(objectField.getDBColumnName())
		).limit(
			start, end
		);

		for (Object[] values : (List<Object[]>)dslQuery(dslQuery)) {
			aggregationCounts.put(
				GetterUtil.getObject(values[0]), GetterUtil.getLong(values[1]));
		}

		return aggregationCounts;
	}

	@Override
	public Map<String, Serializable>
			getExtensionDynamicObjectDefinitionTableValues(
				ObjectDefinition objectDefinition, long primaryKey)
		throws PortalException {

		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(
				objectDefinition.getObjectDefinitionId());

		Expression<?>[] selectExpressions = _getSelectExpressions(
			extensionDynamicObjectDefinitionTable, null);

		List<Object[]> rows = _list(
			_getExtensionDynamicObjectDefinitionTableSelectDSLQuery(
				extensionDynamicObjectDefinitionTable, primaryKey,
				selectExpressions),
			objectDefinition.getObjectDefinitionId(), selectExpressions);

		Object[] row = null;

		if (!rows.isEmpty()) {
			row = rows.get(0);
		}

		Map<String, Serializable> values = _getValues(
			objectDefinition.getObjectDefinitionId(), row, selectExpressions);

		values.remove(objectDefinition.getPKObjectFieldName());

		_addObjectRelationshipERCFieldValue(
			objectDefinition.getObjectDefinitionId(), values);

		return values;
	}

	@Override
	public List<ObjectEntry> getManyToManyObjectEntries(
			long groupId, long objectRelationshipId, long primaryKey,
			boolean related, boolean reverse, String search, int start, int end)
		throws PortalException {

		DSLQuery dslQuery = _getManyToManyObjectEntriesGroupByStep(
			DSLQueryFactoryUtil.selectDistinct(ObjectEntryTable.INSTANCE),
			groupId, objectRelationshipId, primaryKey, related, reverse, search
		).orderBy(
			ObjectEntryTable.INSTANCE.objectEntryId.ascending()
		).limit(
			start, end
		);

		if (_log.isDebugEnabled()) {
			_log.debug("Get many to many related object entries: " + dslQuery);
		}

		return objectEntryPersistence.dslQuery(dslQuery);
	}

	@Override
	public int getManyToManyObjectEntriesCount(
			long groupId, long objectRelationshipId, long primaryKey,
			boolean related, boolean reverse, String search)
		throws PortalException {

		DSLQuery dslQuery = _getManyToManyObjectEntriesGroupByStep(
			DSLQueryFactoryUtil.countDistinct(
				ObjectEntryTable.INSTANCE.objectEntryId),
			groupId, objectRelationshipId, primaryKey, related, reverse,
			search);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Get many to many related object entries count: " + dslQuery);
		}

		return objectEntryPersistence.dslQueryCount(dslQuery);
	}

	@Override
	public List<ObjectEntry> getObjectEntries(
		long groupId, long objectDefinitionId, int start, int end) {

		return objectEntryPersistence.findByG_ODI(
			groupId, objectDefinitionId, start, end);
	}

	@Override
	public List<ObjectEntry> getObjectEntries(
		long groupId, long objectDefinitionId, int status, int start, int end) {

		return objectEntryPersistence.findByG_ODI_S(
			groupId, objectDefinitionId, status, start, end);
	}

	@Override
	public int getObjectEntriesCount(long objectDefinitionId) {
		return objectEntryPersistence.countByObjectDefinitionId(
			objectDefinitionId);
	}

	@Override
	public long getObjectEntriesCount(
			long userId, Date createDate, long objectDefinitionId)
		throws PortalException {

		return objectEntryPersistence.countByU_GtCD_ODI(
			userId, createDate, objectDefinitionId);
	}

	@Override
	public int getObjectEntriesCount(long groupId, long objectDefinitionId) {
		return objectEntryPersistence.countByG_ODI(groupId, objectDefinitionId);
	}

	@Override
	public long getObjectEntriesCount(
			long groupId, ObjectDefinition objectDefinition,
			Predicate predicate)
		throws PortalException {

		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(
				objectDefinition.getObjectDefinitionId());
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(
				objectDefinition.getObjectDefinitionId());

		JoinStep joinStep = DSLQueryFactoryUtil.countDistinct(
			dynamicObjectDefinitionTable.getPrimaryKeyColumn()
		).from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()
			)
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn())
		);

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		if (!objectScopeProvider.isGroupAware()) {
			return dslQueryCount(joinStep.where(predicate));
		}

		return dslQueryCount(
			joinStep.where(
				predicate.and(ObjectEntryTable.INSTANCE.groupId.eq(groupId))));
	}

	@Override
	public ObjectEntry getObjectEntry(
			String externalReferenceCode, long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		return objectEntryPersistence.findByERC_C_ODI(
			externalReferenceCode, objectDefinition.getCompanyId(),
			objectDefinitionId);
	}

	@Override
	public ObjectEntry getObjectEntry(
			String externalReferenceCode, long companyId, long groupId)
		throws PortalException {

		return objectEntryPersistence.findByERC_G_C(
			externalReferenceCode, groupId, companyId);
	}

	@Override
	public List<ObjectEntry> getOneToManyObjectEntries(
			long groupId, long objectRelationshipId, long primaryKey,
			boolean related, String search, int start, int end)
		throws PortalException {

		DSLQuery dslQuery = _getOneToManyObjectEntriesGroupByStep(
			DSLQueryFactoryUtil.selectDistinct(ObjectEntryTable.INSTANCE),
			groupId, objectRelationshipId, primaryKey, related, search
		).orderBy(
			ObjectEntryTable.INSTANCE.objectEntryId.ascending()
		).limit(
			start, end
		);

		if (_log.isDebugEnabled()) {
			_log.debug("Get one to many related object entries: " + dslQuery);
		}

		return objectEntryPersistence.dslQuery(dslQuery);
	}

	@Override
	public int getOneToManyObjectEntriesCount(
			long groupId, long objectRelationshipId, long primaryKey,
			boolean related, String search)
		throws PortalException {

		DSLQuery dslQuery = _getOneToManyObjectEntriesGroupByStep(
			DSLQueryFactoryUtil.countDistinct(
				ObjectEntryTable.INSTANCE.objectEntryId),
			groupId, objectRelationshipId, primaryKey, related, search);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Get one to many related object entries count: " + dslQuery);
		}

		return objectEntryPersistence.dslQueryCount(dslQuery);
	}

	public List<Long> getPrimaryKeys(
			long groupId, long companyId, long userId, long objectDefinitionId,
			Predicate predicate, String search, int start, int end,
			Sort[] sorts)
		throws PortalException {

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					_objectDefinitionPersistence.findByPrimaryKey(
						objectDefinitionId),
					_objectFieldLocalService);
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(objectDefinitionId);
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId);
		DynamicObjectDefinitionTable rootDynamicObjectDefinitionTable =
			_getRootDynamicObjectDefinitionTable(objectDefinitionId);

		DSLQuery dslQuery = DSLQueryFactoryUtil.select(
			ObjectEntryTable.INSTANCE.objectEntryId
		).from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()
			)
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn())
		).innerJoinON(
			rootDynamicObjectDefinitionTable,
			_getInnerJoinRootObjectDefinitionTablePredicate(
				rootDynamicObjectDefinitionTable)
		).leftJoinOn(
			dynamicObjectDefinitionLocalizationTable,
			ObjectEntrySearchUtil.getLeftJoinLocalizationTablePredicate(
				dynamicObjectDefinitionLocalizationTable,
				dynamicObjectDefinitionTable)
		).where(
			ObjectEntryTable.INSTANCE.objectDefinitionId.eq(
				objectDefinitionId
			).and(
				() -> {
					if (groupId == 0) {
						return null;
					}

					return ObjectEntryTable.INSTANCE.groupId.eq(groupId);
				}
			).and(
				Predicate.withParentheses(
					_fillPredicate(objectDefinitionId, predicate, search))
			).and(
				_getPermissionWherePredicate(
					dynamicObjectDefinitionTable, groupId)
			)
		).limit(
			start, end
		);

		if (sorts != null) {
			SortDSLQueryVisitor sortDSLQueryVisitor = new SortDSLQueryVisitor(
				_objectFieldLocalService,
				_objectRelationshipLocalServiceSnapshot.get());

			for (Sort sort : sorts) {
				dslQuery = sortDSLQueryVisitor.visit(
					dslQuery,
					new com.liferay.object.internal.sort.Sort(
						_objectDefinitionPersistence.findByPrimaryKey(
							objectDefinitionId),
						sort));
			}
		}

		return TransformUtil.transform(
			objectEntryPersistence.dslQuery(dslQuery),
			value -> (Long)_getResult(
				value, objectDefinitionId,
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()));
	}

	@Override
	public Map<String, Object> getSystemModelAttributes(
			ObjectDefinition objectDefinition, long primaryKey)
		throws PortalException {

		if (!objectDefinition.isUnmodifiableSystemObject()) {
			return new HashMap<>();
		}

		Map<String, Object> baseModelAttributes = new HashMap<>();

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(objectDefinition.getClassName());

		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(
				objectDefinition.getObjectDefinitionId());

		Column<DynamicObjectDefinitionTable, Long> primaryKeyColumn =
			dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		List<BaseModel<?>> baseModels = persistedModelLocalService.dslQuery(
			DSLQueryFactoryUtil.select(
			).from(
				dynamicObjectDefinitionTable
			).where(
				primaryKeyColumn.eq(primaryKey)
			));

		if (!baseModels.isEmpty()) {
			BaseModel<?> baseModel = baseModels.get(0);

			baseModelAttributes = baseModel.getModelAttributes();
		}

		Map<String, Object> modelAttributes =
			HashMapBuilder.<String, Object>put(
				"createDate",
				GetterUtil.get(
					baseModelAttributes.get("createDate"), primaryKey)
			).put(
				"externalReferenceCode",
				GetterUtil.get(
					baseModelAttributes.get("externalReferenceCode"),
					primaryKey)
			).put(
				"modifiedDate",
				GetterUtil.get(
					baseModelAttributes.get("modifiedDate"), primaryKey)
			).put(
				"objectDefinitionId", objectDefinition.getObjectDefinitionId()
			).put(
				"uuid",
				GetterUtil.get(baseModelAttributes.get("uuid"), primaryKey)
			).build();

		for (ObjectField objectField :
				_objectFieldLocalService.getObjectFields(
					objectDefinition.getObjectDefinitionId())) {

			if (!objectField.isSystem()) {
				continue;
			}

			Object value = GetterUtil.getObject(
				baseModelAttributes.get(objectField.getDBColumnName()),
				primaryKey);

			if (value instanceof String) {
				value = _localization.getLocalization(
					(String)value, null, true);
			}

			modelAttributes.put(objectField.getName(), value);
		}

		modelAttributes.putAll(
			objectEntryLocalService.
				getExtensionDynamicObjectDefinitionTableValues(
					objectDefinition, primaryKey));

		return modelAttributes;
	}

	@Override
	public Map<String, Serializable> getSystemValues(ObjectEntry objectEntry)
		throws PortalException {

		List<Object[]> rows = _list(
			DSLQueryFactoryUtil.select(
				_EXPRESSIONS
			).from(
				ObjectEntryTable.INSTANCE
			).where(
				ObjectEntryTable.INSTANCE.objectEntryId.eq(
					objectEntry.getObjectEntryId())
			),
			objectEntry.getObjectDefinitionId(), _EXPRESSIONS);

		return _getValues(
			objectEntry.getObjectDefinitionId(), rows.get(0), _EXPRESSIONS);
	}

	@Override
	public String getTitleValue(long objectDefinitionId, long primaryKey)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (!objectDefinition.isUnmodifiableSystemObject()) {
			ObjectEntry objectEntry = getObjectEntry(primaryKey);

			return objectEntry.getTitleValue();
		}

		ObjectField titleObjectField =
			_objectFieldLocalService.fetchObjectField(
				objectDefinition.getTitleObjectFieldId());

		if (Objects.isNull(titleObjectField)) {
			titleObjectField = _objectFieldLocalService.getObjectField(
				objectDefinitionId, "id");
		}

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(objectDefinition.getClassName());

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			String.valueOf(
				persistedModelLocalService.getPersistedModel(primaryKey)));

		return jsonObject.getString(titleObjectField.getDBColumnName());
	}

	@Override
	public Map<String, Serializable> getValues(long objectEntryId)
		throws PortalException {

		ObjectEntry objectEntry = objectEntryPersistence.findByPrimaryKey(
			objectEntryId);

		return getValues(objectEntry);
	}

	@Override
	public Map<String, Serializable> getValues(ObjectEntry objectEntry)
		throws PortalException {

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					_objectDefinitionPersistence.findByPrimaryKey(
						objectEntry.getObjectDefinitionId()),
					_objectFieldLocalService);
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(
				objectEntry.getObjectDefinitionId());
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(
				objectEntry.getObjectDefinitionId());

		Expression<?>[] selectExpressions = ArrayUtil.append(
			_getSelectExpressions(dynamicObjectDefinitionLocalizationTable),
			_getSelectExpressions(dynamicObjectDefinitionTable, null),
			ArrayUtil.remove(
				_getSelectExpressions(
					extensionDynamicObjectDefinitionTable, null),
				extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn()));

		List<Object[]> rows = _list(
			DSLQueryFactoryUtil.select(
				selectExpressions
			).from(
				dynamicObjectDefinitionTable
			).innerJoinON(
				extensionDynamicObjectDefinitionTable,
				dynamicObjectDefinitionTable.getPrimaryKeyColumn(
				).eq(
					extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn()
				)
			).leftJoinOn(
				dynamicObjectDefinitionLocalizationTable,
				ObjectEntrySearchUtil.getLeftJoinLocalizationTablePredicate(
					dynamicObjectDefinitionLocalizationTable,
					dynamicObjectDefinitionTable)
			).where(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn(
				).eq(
					objectEntry.getObjectEntryId()
				)
			),
			objectEntry.getObjectDefinitionId(), selectExpressions);

		if (ListUtil.isEmpty(rows)) {
			return Collections.emptyMap();
		}

		Map<String, Serializable> values = _getValues(
			objectEntry.getObjectDefinitionId(), rows.get(0),
			selectExpressions);

		_addLocalizedObjectFieldValues(
			dynamicObjectDefinitionLocalizationTable,
			objectEntry.getObjectEntryId(), values);
		_addObjectRelationshipERCFieldValue(
			objectEntry.getObjectDefinitionId(), values);

		return values;
	}

	@Override
	public List<Map<String, Serializable>> getValuesList(
			long groupId, long companyId, long userId, long objectDefinitionId,
			Predicate predicate, String search, int start, int end,
			Sort[] sorts)
		throws PortalException {

		return TransformUtil.transform(
			getPrimaryKeys(
				groupId, companyId, userId, objectDefinitionId, predicate,
				search, start, end, sorts),
			this::getValues);
	}

	@Override
	public int getValuesListCount(
			long groupId, long companyId, long userId, long objectDefinitionId,
			Predicate predicate, String search)
		throws PortalException {

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					_objectDefinitionPersistence.findByPrimaryKey(
						objectDefinitionId),
					_objectFieldLocalService);
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(objectDefinitionId);
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId);
		DynamicObjectDefinitionTable rootDynamicObjectDefinitionTable =
			_getRootDynamicObjectDefinitionTable(objectDefinitionId);

		DSLQuery dslQuery = DSLQueryFactoryUtil.countDistinct(
			ObjectEntryTable.INSTANCE.objectEntryId
		).from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn()
			)
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn())
		).innerJoinON(
			rootDynamicObjectDefinitionTable,
			_getInnerJoinRootObjectDefinitionTablePredicate(
				rootDynamicObjectDefinitionTable)
		).leftJoinOn(
			dynamicObjectDefinitionLocalizationTable,
			ObjectEntrySearchUtil.getLeftJoinLocalizationTablePredicate(
				dynamicObjectDefinitionLocalizationTable,
				dynamicObjectDefinitionTable)
		).where(
			ObjectEntryTable.INSTANCE.objectDefinitionId.eq(
				objectDefinitionId
			).and(
				() -> {
					if (groupId == 0) {
						return null;
					}

					return ObjectEntryTable.INSTANCE.groupId.eq(groupId);
				}
			).and(
				Predicate.withParentheses(
					_fillPredicate(objectDefinitionId, predicate, search))
			).and(
				_getPermissionWherePredicate(
					dynamicObjectDefinitionTable, groupId)
			)
		);

		return objectEntryPersistence.dslQueryCount(dslQuery);
	}

	@Override
	public void insertIntoOrUpdateExtensionTable(
			long userId, long objectDefinitionId, long primaryKey,
			Map<String, Serializable> values)
		throws PortalException {

		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId);

		/*int count = objectEntryPersistence.dslQueryCount(
			_getExtensionDynamicObjectDefinitionTableCountDSLQuery(
				dynamicObjectDefinitionTable, primaryKey));*/

		// TODO Temporary workaround for LPS-178639

		int count = 0;

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"select count(*) from ",
					dynamicObjectDefinitionTable.getTableName(), " where ",
					dynamicObjectDefinitionTable.getPrimaryKeyColumnName(),
					" = ", primaryKey));
			ResultSet resultSet = preparedStatement.executeQuery()) {

			resultSet.next();

			count = resultSet.getInt(1);
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}

		if (count > 0) {
			_updateTable(
				dynamicObjectDefinitionTable, primaryKey, values,
				WorkflowConstants.ACTION_PUBLISH);
		}
		else {
			_insertIntoTable(
				dynamicObjectDefinitionTable, new HashMap<>(), primaryKey,
				values, WorkflowConstants.ACTION_PUBLISH);
		}
	}

	@Override
	public BaseModelSearchResult<ObjectEntry> searchObjectEntries(
			long groupId, long objectDefinitionId, String keywords, int cur,
			int delta)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		SearchRequestBuilder searchRequestBuilder =
			_searchRequestBuilderFactory.builder();

		searchRequestBuilder.entryClassNames(
			objectDefinition.getClassName()
		).emptySearchEnabled(
			true
		).from(
			cur
		).size(
			delta
		).sorts(
			_sorts.field(Field.ENTRY_CLASS_PK, SortOrder.ASC)
		).withSearchContext(
			searchContext -> {
				searchContext.setAttribute(
					Field.STATUS, WorkflowConstants.STATUS_ANY);
				searchContext.setAttribute(
					"objectDefinitionId",
					objectDefinition.getObjectDefinitionId());
				searchContext.setCompanyId(objectDefinition.getCompanyId());

				if (objectScopeProvider.isGroupAware()) {
					searchContext.setGroupIds(new long[] {groupId});
				}
				else {
					searchContext.setGroupIds(new long[] {0});
				}

				searchContext.setKeywords(keywords);
			}
		);

		SearchResponse searchResponse = _searcher.search(
			searchRequestBuilder.build());

		SearchHits searchHits = searchResponse.getSearchHits();

		return new BaseModelSearchResult<>(
			(List<ObjectEntry>)TransformUtil.transform(
				searchHits.getSearchHits(),
				searchHit -> {
					Document document = searchHit.getDocument();

					return objectEntryPersistence.fetchByPrimaryKey(
						document.getLong(Field.ENTRY_CLASS_PK));
				}),
			searchResponse.getTotalHits());
	}

	@Override
	public void updateAsset(
			long userId, ObjectEntry objectEntry, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		_updateAsset(
			userId, objectEntry, assetCategoryIds, assetTagNames,
			assetLinkEntryIds, priority, null);
	}

	@Override
	public ObjectEntry updateObjectEntry(
			long userId, long objectEntryId, Map<String, Serializable> values,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ObjectEntry objectEntry = objectEntryPersistence.findByPrimaryKey(
			objectEntryId);

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectEntry.getObjectDefinitionId());

		_contributeValues(
			objectEntry.getGroupId(), objectDefinition, userId, values);

		_validateValues(
			objectEntry, user.isGuestUser(), objectEntry.getGroupId(),
			objectDefinition, objectEntryId, serviceContext, userId, values);

		int workflowAction = serviceContext.getWorkflowAction();

		_validateWorkflowAction(
			objectDefinition.isEnableObjectEntryDraft(),
			objectEntry.getStatus(), workflowAction);

		Map<String, Serializable> transientValues = objectEntry.getValues();

		_deleteFromLocalizationTable(objectDefinition, objectEntryId);
		_insertIntoLocalizationTable(
			new HashMap<>(), objectDefinition, objectEntryId, values,
			workflowAction);
		_updateTable(
			_getDynamicObjectDefinitionTable(
				objectEntry.getObjectDefinitionId()),
			objectEntryId, values, workflowAction);
		_updateTable(
			_getExtensionDynamicObjectDefinitionTable(
				objectEntry.getObjectDefinitionId()),
			objectEntryId, values, workflowAction);

		objectEntryPersistence.clearCache(SetUtil.fromArray(objectEntryId));

		objectEntry = objectEntryPersistence.findByPrimaryKey(objectEntryId);

		_setExternalReferenceCode(objectEntry, values);

		objectEntry.setModifiedDate(serviceContext.getModifiedDate(null));

		_setRootObjectEntryId(objectDefinition, objectEntry, values);

		objectEntry.setTransientValues(transientValues);

		ObjectEntry originalObjectEntry = objectEntry.cloneWithOriginalValues();

		try {
			if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {
				ObjectEntryThreadLocal.setSkipObjectValidationRules(true);
			}

			objectEntry = objectEntryPersistence.update(objectEntry);
		}
		finally {
			ObjectEntryThreadLocal.setSkipObjectValidationRules(false);
		}

		_updateAsset(
			serviceContext.getUserId(), objectEntry,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority(), serviceContext);

		_startWorkflowInstance(userId, objectEntry, serviceContext, true);

		_updateResourcePermissions(
			objectDefinition, objectEntry, serviceContext);

		_deleteFileEntries(
			objectEntry.getValues(), objectEntry.getObjectDefinitionId(),
			transientValues);

		_executeObjectActions(
			objectEntry.getCompanyId(),
			ObjectActionTriggerConstants.KEY_ON_AFTER_UPDATE, objectDefinition,
			objectEntry, originalObjectEntry, serviceContext.getLanguageId(),
			user);

		return objectEntry;
	}

	@Override
	public void updateRootObjectEntryIds(
			ObjectDefinition objectDefinition1,
			ObjectDefinition objectDefinition2,
			ObjectRelationship objectRelationship)
		throws PortalException {

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		AtomicBoolean objectDefinition1RootNode = new AtomicBoolean(false);
		ObjectField objectField = _objectFieldPersistence.findByPrimaryKey(
			objectRelationship.getObjectFieldId2());

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select ", objectDefinition2.getPKObjectFieldDBColumnName(),
					" from ", objectField.getDBTableName(), " where ",
					objectField.getDBColumnName(), " = ?"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update ObjectEntry set rootObjectEntryId = ? where " +
						"objectEntryId = ?");
			PreparedStatement preparedStatement3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update ObjectEntry set rootObjectEntryId = ? where " +
						"rootObjectEntryId = ?")) {

			long objectDefinition2RootObjectDefinitionId =
				objectDefinition2.getRootObjectDefinitionId();

			_performActions(
				objectDefinition1.getObjectDefinitionId(),
				(ObjectEntry objectEntry) -> {
					try {
						_updateRootObjectEntryIds(
							objectDefinition1RootNode, objectDefinition2,
							objectDefinition2RootObjectDefinitionId,
							objectEntry, preparedStatement1, preparedStatement2,
							preparedStatement3);
					}
					catch (SQLException sqlException) {
						throw new SystemException(sqlException);
					}
				});

			preparedStatement2.executeBatch();

			if (objectDefinition2RootObjectDefinitionId != 0) {
				preparedStatement3.executeBatch();
			}
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}

		long rootObjectDefinitionId =
			objectRelationship.getObjectDefinitionId2();

		if (objectDefinition1RootNode.get()) {
			rootObjectDefinitionId = objectDefinition1.getObjectDefinitionId();
		}

		ObjectDefinitionTreeFactory objectDefinitionTreeFactory =
			new ObjectDefinitionTreeFactory(
				_objectDefinitionPersistence,
				_objectRelationshipLocalServiceSnapshot.get());

		Tree objectDefinitionTree = objectDefinitionTreeFactory.create(
			rootObjectDefinitionId);

		Iterator<Node> iterator = objectDefinitionTree.iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();

			ObjectDefinition objectDefinition =
				_objectDefinitionPersistence.findByPrimaryKey(
					node.getPrimaryKey());

			Indexer<ObjectEntry> indexer = IndexerRegistryUtil.getIndexer(
				objectDefinition.getClassName());

			_performActions(
				objectDefinition.getObjectDefinitionId(),
				(ObjectEntry objectEntry) -> indexer.reindex(objectEntry));
		}

		objectEntryPersistence.clearCache();
	}

	@Override
	public ObjectEntry updateStatus(
			long userId, long objectEntryId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		return updateStatus(
			userId, objectEntryPersistence.findByPrimaryKey(objectEntryId),
			status, serviceContext);
	}

	@Override
	public ObjectEntry updateStatus(
			long userId, ObjectEntry objectEntry, int status,
			ServiceContext serviceContext)
		throws PortalException {

		if (objectEntry.getStatus() == status) {
			return objectEntry;
		}

		objectEntry.setStatus(status);

		User user = _userLocalService.getUser(userId);

		objectEntry.setStatusByUserId(user.getUserId());
		objectEntry.setStatusByUserName(user.getFullName());

		objectEntry.setStatusDate(serviceContext.getModifiedDate(null));

		if (_skipModelListeners.get()) {
			while (objectEntry instanceof ModelWrapper) {
				ModelWrapper<ObjectEntry> modelWrapper =
					(ModelWrapper<ObjectEntry>)objectEntry;

				objectEntry = modelWrapper.getWrappedModel();
			}

			objectEntry = objectEntryPersistence.updateImpl(objectEntry);
		}
		else {
			objectEntry = objectEntryPersistence.update(objectEntry);
		}

		if (serviceContext.isStrictAdd()) {
			boolean indexingEnabled = serviceContext.isIndexingEnabled();

			serviceContext.setIndexingEnabled(false);

			try {
				_updateAsset(
					serviceContext.getUserId(), objectEntry,
					serviceContext.getAssetCategoryIds(),
					serviceContext.getAssetTagNames(),
					serviceContext.getAssetLinkEntryIds(),
					serviceContext.getAssetPriority(), serviceContext);
			}
			finally {
				serviceContext.setIndexingEnabled(indexingEnabled);
			}
		}
		else {
			ObjectDefinition objectDefinition =
				_objectDefinitionPersistence.fetchByPrimaryKey(
					objectEntry.getObjectDefinitionId());

			_assetEntryLocalService.updateEntry(
				objectDefinition.getClassName(), objectEntry.getObjectEntryId(),
				null, null, true, objectEntry.isApproved());
		}

		_reindex(objectEntry);

		return objectEntry;
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		modified(properties);

		_putObjectFilterParser(
			new CurrentUserObjectFilterParser(),
			ObjectFilterConstants.TYPE_CURRENT_USER);
		_putObjectFilterParser(
			new DateRangeObjectFilterParser(),
			ObjectFilterConstants.TYPE_DATE_RANGE);
		_putObjectFilterParser(
			new EqualityOperatorsObjectFilterParser(),
			ObjectFilterConstants.TYPE_EQUALS,
			ObjectFilterConstants.TYPE_NOT_EQUALS);
		_putObjectFilterParser(
			new InclusionOperatorsObjectFilterParser(),
			ObjectFilterConstants.TYPE_EXCLUDES,
			ObjectFilterConstants.TYPE_INCLUDES);

		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, ObjectEntryValuesContributor.class);
	}

	@Deactivate
	@Override
	protected void deactivate() {
		super.deactivate();

		_serviceTrackerList.close();
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		_objectConfiguration = ConfigurableUtil.createConfigurable(
			ObjectConfiguration.class, properties);
	}

	private void _addFileEntry(
			DLFileEntry dlFileEntry, Map.Entry<String, Serializable> entry,
			ObjectDefinition objectDefinition, long objectEntryId,
			long objectFieldId, List<ObjectFieldSetting> objectFieldSettings,
			ServiceContext serviceContext, long userId)
		throws PortalException {

		try {
			String fileSource = ObjectFieldSettingUtil.getValue(
				"fileSource", objectFieldSettings);

			if (Objects.equals(fileSource, "documentsAndMedia")) {
				return;
			}

			DLFolder dlFileEntryFolder = dlFileEntry.getFolder();

			DLFolder dlFolder = _attachmentManager.getDLFolder(
				dlFileEntry.getCompanyId(), dlFileEntry.getGroupId(),
				objectFieldId, serviceContext, userId);

			if (Objects.equals(
					dlFileEntryFolder.getFolderId(), dlFolder.getFolderId())) {

				return;
			}

			String originalFileName = TempFileEntryUtil.getOriginalTempFileName(
				dlFileEntry.getFileName());

			serviceContext.setAttribute(
				"className", objectDefinition.getClassName());
			serviceContext.setAttribute("classPK", objectEntryId);

			FileEntry fileEntry = _dlAppLocalService.addFileEntry(
				null, userId, dlFolder.getRepositoryId(),
				dlFolder.getFolderId(),
				DLUtil.getUniqueFileName(
					dlFileEntry.getGroupId(), dlFolder.getFolderId(),
					originalFileName, true),
				dlFileEntry.getMimeType(),
				DLUtil.getUniqueTitle(
					dlFileEntry.getGroupId(), dlFolder.getFolderId(),
					FileUtil.stripExtension(originalFileName)),
				StringPool.BLANK, null, null, dlFileEntry.getContentStream(),
				dlFileEntry.getSize(), null, null, null, serviceContext);

			entry.setValue(fileEntry.getFileEntryId());
		}
		finally {
			if (dlFileEntry != null) {
				TempFileEntryUtil.deleteTempFileEntry(
					dlFileEntry.getFileEntryId());
			}
		}
	}

	private JoinStep _addInnerJoinON(
		Column<?, ?> column,
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable,
		JoinStep joinStep, Column<?, Long> primaryKeyColumn1,
		Set<String> tableNames) {

		Table<?> table = column.getTable();

		if (tableNames.contains(table.getName())) {
			return joinStep;
		}

		tableNames.add(table.getName());

		Column<?, Long> primaryKeyColumn2 = _getPrimaryKeyColumn(
			dynamicObjectDefinitionTable, extensionDynamicObjectDefinitionTable,
			table.getTableName());

		return joinStep.innerJoinON(
			table, primaryKeyColumn2.eq(primaryKeyColumn1));
	}

	private void _addLocalizedObjectFieldValues(
		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable,
		long objectEntryId, Map<String, Serializable> values) {

		if (dynamicObjectDefinitionLocalizationTable == null) {
			return;
		}

		List<Object[]> rows = objectEntryPersistence.dslQuery(
			DSLQueryFactoryUtil.select(
				ArrayUtil.append(
					_getSelectExpressions(
						dynamicObjectDefinitionLocalizationTable),
					dynamicObjectDefinitionLocalizationTable.
						getLanguageIdColumn())
			).from(
				dynamicObjectDefinitionLocalizationTable
			).where(
				dynamicObjectDefinitionLocalizationTable.getForeignKeyColumn(
				).eq(
					objectEntryId
				)
			));

		if (ListUtil.isEmpty(rows)) {
			return;
		}

		List<Column<DynamicObjectDefinitionLocalizationTable, ?>>
			objectFieldColumns =
				dynamicObjectDefinitionLocalizationTable.
					getObjectFieldColumns();

		for (int i = 0; i < objectFieldColumns.size(); i++) {
			Map<String, String> localizedValues = new HashMap<>();

			for (Object[] row : rows) {
				Object localizedValue = row[i];

				if (Validator.isNull(localizedValue)) {
					continue;
				}

				localizedValues.put(
					String.valueOf(row[objectFieldColumns.size()]),
					String.valueOf(localizedValue));
			}

			Column<DynamicObjectDefinitionLocalizationTable, ?>
				objectFieldColumn = objectFieldColumns.get(i);

			_putLocalizedValues(
				objectFieldColumn.getName(), (Serializable)localizedValues,
				values);
		}
	}

	private void _addObjectRelationshipERCFieldValue(
		long objectDefinitionId, Map<String, Serializable> values) {

		for (ObjectField objectField :
				_objectFieldLocalService.getObjectFields(objectDefinitionId)) {

			if (!Objects.equals(
					objectField.getRelationshipType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

				continue;
			}

			ObjectRelationship objectRelationship =
				_objectRelationshipPersistence.fetchByObjectFieldId2(
					objectField.getObjectFieldId());

			ObjectDefinition objectDefinition =
				_objectDefinitionPersistence.fetchByPrimaryKey(
					objectRelationship.getObjectDefinitionId1());

			if (objectDefinition == null) {
				continue;
			}

			long primaryKey = GetterUtil.getLong(
				values.get(objectField.getName()));

			String objectRelationshipERCObjectFieldName =
				ObjectFieldSettingUtil.getValue(
					ObjectFieldSettingConstants.
						NAME_OBJECT_RELATIONSHIP_ERC_OBJECT_FIELD_NAME,
					objectField);

			if (objectDefinition.isUnmodifiableSystemObject()) {
				SystemObjectDefinitionManager systemObjectDefinitionManager =
					_systemObjectDefinitionManagerRegistry.
						getSystemObjectDefinitionManager(
							objectDefinition.getName());

				try {
					values.put(
						objectRelationshipERCObjectFieldName,
						systemObjectDefinitionManager.
							getBaseModelExternalReferenceCode(primaryKey));
				}
				catch (PortalException portalException) {
					if (_log.isDebugEnabled()) {
						_log.debug(portalException);
					}
				}

				continue;
			}

			ObjectEntry objectEntry = objectEntryPersistence.fetchByPrimaryKey(
				primaryKey);

			String externalReferenceCode = StringPool.BLANK;

			if (objectEntry != null) {
				externalReferenceCode = objectEntry.getExternalReferenceCode();
			}

			values.put(
				objectRelationshipERCObjectFieldName, externalReferenceCode);
		}
	}

	private void _contributeValues(
		long groupId, ObjectDefinition objectDefinition, long userId,
		Map<String, Serializable> values) {

		for (ObjectEntryValuesContributor objectEntryValuesContributor :
				_serviceTrackerList) {

			if (objectEntryValuesContributor instanceof CompanyScoped) {
				CompanyScoped companyScoped =
					(CompanyScoped)objectEntryValuesContributor;

				if (!companyScoped.isAllowedCompany(
						objectDefinition.getCompanyId())) {

					continue;
				}
			}

			if (objectEntryValuesContributor instanceof
					ObjectDefinitionScoped) {

				ObjectDefinitionScoped objectDefinitionScoped =
					(ObjectDefinitionScoped)objectEntryValuesContributor;

				if (!objectDefinitionScoped.isAllowedObjectDefinition(
						objectDefinition.getName())) {

					continue;
				}
			}

			objectEntryValuesContributor.contribute(
				new ObjectEntryContext(
					groupId, objectDefinition.getObjectDefinitionId(), userId,
					values));
		}
	}

	private void _deleteFileEntries(
		Map<String, Serializable> newValues, long objectDefinitionId,
		Map<String, Serializable> oldValues) {

		_deleteFileEntries(newValues, objectDefinitionId, () -> oldValues);
	}

	private void _deleteFileEntries(
		Map<String, Serializable> newValues, long objectDefinitionId,
		Supplier<Map<String, Serializable>> oldValuesSupplier) {

		List<ObjectField> objectFields =
			_objectFieldPersistence.findByObjectDefinitionId(
				objectDefinitionId);

		Map<String, Serializable> oldValues = null;

		for (ObjectField objectField : objectFields) {
			if (objectField.isSystem() ||
				!Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

				continue;
			}

			if (oldValues == null) {
				oldValues = oldValuesSupplier.get();
			}

			String objectFieldName = objectField.getName();

			if (Objects.equals(
					GetterUtil.getLong(newValues.get(objectFieldName)),
					GetterUtil.getLong(oldValues.get(objectFieldName)))) {

				continue;
			}

			ObjectFieldSetting objectFieldSetting =
				_objectFieldSettingPersistence.fetchByOFI_N(
					objectField.getObjectFieldId(), "fileSource");

			if (!Objects.equals(
					objectFieldSetting.getValue(), "userComputer")) {

				continue;
			}

			objectFieldSetting = _objectFieldSettingPersistence.fetchByOFI_N(
				objectField.getObjectFieldId(), "showFilesInDocumentsAndMedia");

			if ((objectFieldSetting != null) &&
				GetterUtil.getBoolean(objectFieldSetting.getValue())) {

				continue;
			}

			try {
				_dlFileEntryLocalService.deleteFileEntry(
					GetterUtil.getLong(oldValues.get(objectFieldName)));
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}
	}

	private void _deleteFromLocalizationTable(
			ObjectDefinition objectDefinition, long objectEntryId)
		throws PortalException {

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					objectDefinition, _objectFieldLocalService);

		if (dynamicObjectDefinitionLocalizationTable == null) {
			return;
		}

		_deleteFromTable(
			dynamicObjectDefinitionLocalizationTable.getTableName(),
			dynamicObjectDefinitionLocalizationTable.getForeignKeyColumnName(),
			objectEntryId);
	}

	private void _deleteFromTable(
			String dbTableName, String pkObjectFieldDBColumnName,
			long primaryKey)
		throws PortalException {

		Session session = objectEntryPersistence.openSession();

		try {
			session.apply(
				connection -> {
					try (PreparedStatement preparedStatement =
							connection.prepareStatement(
								StringBundler.concat(
									"delete from ", dbTableName, " where ",
									pkObjectFieldDBColumnName, " = ?"))) {

						preparedStatement.setLong(1, primaryKey);

						preparedStatement.executeUpdate();
					}
				});
		}
		finally {
			objectEntryPersistence.closeSession(session);
		}

		FinderCacheUtil.clearDSLQueryCache(dbTableName);
	}

	private void _executeObjectActions(
			long companyId, String objectActionTrigger,
			ObjectDefinition objectDefinition, ObjectEntry objectEntry,
			ObjectEntry originalObjectEntry, String preferredLanguageId,
			User user)
		throws NoSuchObjectDefinitionException {

		ObjectActionEngine objectActionEngine =
			_objectActionEngineSnapshot.get();

		objectActionEngine.executeObjectActions(
			objectDefinition.getClassName(), companyId, objectActionTrigger,
			() -> ObjectEntryUtil.getPayloadJSONObject(
				_dtoConverterRegistry, _jsonFactory, objectActionTrigger,
				objectDefinition, objectEntry, originalObjectEntry,
				preferredLanguageId, user),
			user.getUserId());

		if (!FeatureFlagManagerUtil.isEnabled(
				objectDefinition.getCompanyId(), "LPS-187142") ||
			(!objectDefinition.isRootDescendantNode() &&
			 (!objectDefinition.isRootNode() ||
			  StringUtil.equals(
				  objectActionTrigger,
				  ObjectActionTriggerConstants.KEY_ON_AFTER_ADD)))) {

			return;
		}

		ObjectEntry rootObjectEntry = fetchObjectEntry(
			objectEntry.getRootObjectEntryId());

		if (rootObjectEntry == null) {
			return;
		}

		objectActionEngine.executeObjectActions(
			rootObjectEntry.getModelClassName(), companyId,
			ObjectActionTriggerConstants.KEY_ON_AFTER_ROOT_UPDATE,
			() -> ObjectEntryUtil.getPayloadJSONObject(
				_dtoConverterRegistry, _jsonFactory,
				ObjectActionTriggerConstants.KEY_ON_AFTER_ROOT_UPDATE,
				_objectDefinitionPersistence.findByPrimaryKey(
					rootObjectEntry.getObjectDefinitionId()),
				rootObjectEntry, null, preferredLanguageId, user),
			user.getUserId());
	}

	private void _fillDefaultValue(
		long objectDefinitionId, Map<String, Serializable> values) {

		for (ObjectField objectField :
				_objectFieldPersistence.findByObjectDefinitionId(
					objectDefinitionId)) {

			if (values.get(objectField.getName()) != null) {
				continue;
			}

			objectField.setObjectFieldSettings(
				_objectFieldSettingLocalService.
					getObjectFieldObjectFieldSettings(
						objectField.getObjectFieldId()));

			String value = ObjectFieldSettingUtil.getDefaultValueAsString(
				_ddmExpressionFactory, objectField,
				_objectFieldSettingLocalService, (Map)values);

			if (value != null) {
				values.put(objectField.getName(), value);
			}
		}
	}

	private Predicate _fillPredicate(
			long objectDefinitionId, Predicate predicate, String search)
		throws PortalException {

		if (Validator.isNull(search)) {
			return predicate;
		}

		List<ObjectField> objectFields = _objectFieldPersistence.findByODI_I(
			objectDefinitionId, true);

		if (objectFields.isEmpty()) {
			return predicate;
		}

		Predicate searchPredicate = null;

		for (ObjectField objectField : objectFields) {
			Table<?> table = _objectFieldLocalService.getTable(
				objectDefinitionId, objectField.getName());

			Column<?, ?> column = table.getColumn(
				objectField.getDBColumnName());

			if (column == null) {
				continue;
			}

			Predicate objectFieldPredicate = null;

			if (Objects.equals(
					objectField.getRelationshipType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

				objectFieldPredicate = _getRelationshipObjectFieldPredicate(
					column, objectField, search);
			}
			else {
				objectFieldPredicate =
					ObjectEntrySearchUtil.getObjectFieldPredicate(
						column, objectField.getDBType(), search);
			}

			if (objectFieldPredicate == null) {
				continue;
			}

			if (searchPredicate == null) {
				searchPredicate = objectFieldPredicate;
			}
			else {
				searchPredicate = searchPredicate.or(objectFieldPredicate);
			}
		}

		if (predicate == null) {
			if (searchPredicate == null) {
				return null;
			}

			return searchPredicate.withParentheses();
		}

		return Predicate.withParentheses(
			predicate
		).and(
			searchPredicate.withParentheses()
		);
	}

	private DSLQuery _getAccountEntriesDSLQuery(long companyId, long userId)
		throws PortalException {

		JoinStep joinStep = DSLQueryFactoryUtil.select(
			AccountEntryTable.INSTANCE.accountEntryId
		).from(
			AccountEntryTable.INSTANCE
		);

		if (_roleLocalService.hasUserRole(
				userId, companyId, RoleConstants.ADMINISTRATOR, true)) {

			return joinStep.where(
				AccountEntryTable.INSTANCE.companyId.eq(
					companyId
				).and(
					AccountEntryTable.INSTANCE.status.eq(
						WorkflowConstants.STATUS_APPROVED)
				));
		}

		Table<OrganizationTable> tempOrganizationTable =
			DSLQueryFactoryUtil.select(
				OrganizationTable.INSTANCE.companyId,
				OrganizationTable.INSTANCE.treePath
			).from(
				OrganizationTable.INSTANCE
			).innerJoinON(
				Users_OrgsTable.INSTANCE,
				Users_OrgsTable.INSTANCE.organizationId.eq(
					OrganizationTable.INSTANCE.organizationId)
			).where(
				Users_OrgsTable.INSTANCE.userId.eq(userId)
			).as(
				"tempOrganizationTable", OrganizationTable.INSTANCE
			);

		return joinStep.innerJoinON(
			AccountEntryOrganizationRelTable.INSTANCE,
			AccountEntryOrganizationRelTable.INSTANCE.accountEntryId.eq(
				AccountEntryTable.INSTANCE.accountEntryId)
		).where(
			AccountEntryOrganizationRelTable.INSTANCE.organizationId.in(
				DSLQueryFactoryUtil.selectDistinct(
					OrganizationTable.INSTANCE.organizationId
				).from(
					OrganizationTable.INSTANCE
				).innerJoinON(
					tempOrganizationTable,
					OrganizationTable.INSTANCE.companyId.eq(
						tempOrganizationTable.getColumn("companyId", Long.class)
					).and(
						OrganizationTable.INSTANCE.treePath.like(
							DSLFunctionFactoryUtil.concat(
								DSLFunctionFactoryUtil.castText(
									tempOrganizationTable.getColumn(
										"treePath", String.class)),
								new Scalar<>(StringPool.PERCENT)))
					)
				)
			).and(
				_getAccountEntryWherePredicate()
			)
		).union(
			joinStep.where(
				AccountEntryTable.INSTANCE.userId.eq(
					userId
				).and(
					_getAccountEntryWherePredicate()
				))
		).union(
			joinStep.innerJoinON(
				AccountEntryUserRelTable.INSTANCE,
				AccountEntryUserRelTable.INSTANCE.accountEntryId.eq(
					AccountEntryTable.INSTANCE.accountEntryId)
			).where(
				AccountEntryUserRelTable.INSTANCE.accountUserId.eq(
					userId
				).and(
					_getAccountEntryWherePredicate()
				)
			)
		);
	}

	private Predicate _getAccountEntryWherePredicate() {
		return AccountEntryTable.INSTANCE.parentAccountEntryId.eq(
			AccountConstants.PARENT_ACCOUNT_ENTRY_ID_DEFAULT
		).and(
			AccountEntryTable.INSTANCE.status.eq(
				WorkflowConstants.STATUS_APPROVED)
		).and(
			AccountEntryTable.INSTANCE.type.in(
				new String[] {
					AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS,
					AccountConstants.ACCOUNT_ENTRY_TYPE_PERSON
				})
		);
	}

	private DSLQuery _getAggregationObjectFieldDSLQuery(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			ObjectDefinition objectDefinition,
			Map<String, Object> objectFieldSettingsValues)
		throws PortalException {

		ObjectRelationship objectRelationship =
			ObjectRelationshipUtil.getObjectRelationship(
				_objectRelationshipPersistence.findByODI1_N(
					objectDefinition.getObjectDefinitionId(),
					GetterUtil.getString(
						objectFieldSettingsValues.get(
							"objectRelationshipName"))));

		ObjectDefinition relatedObjectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectRelationship.getObjectDefinitionId2());

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				relatedObjectDefinition.getObjectDefinitionId());

		DynamicObjectDefinitionTable relatedDynamicObjectDefinitionTable =
			new DynamicObjectDefinitionTable(
				relatedObjectDefinition, objectFields,
				relatedObjectDefinition.getDBTableName());
		DynamicObjectDefinitionTable
			relatedExtensionDynamicObjectDefinitionTable =
				new DynamicObjectDefinitionTable(
					relatedObjectDefinition, objectFields,
					relatedObjectDefinition.getExtensionDBTableName());

		if (objectRelationship.isSelf()) {
			relatedDynamicObjectDefinitionTable =
				relatedDynamicObjectDefinitionTable.as(
					"aliasDynamicObjectDefinitionTable");
			relatedExtensionDynamicObjectDefinitionTable =
				relatedExtensionDynamicObjectDefinitionTable.as(
					"aliasExtensionDynamicObjectDefinitionTable");
		}

		Column<?, ?> column =
			relatedDynamicObjectDefinitionTable.getPrimaryKeyColumn();

		String function = GetterUtil.getString(
			objectFieldSettingsValues.get(
				ObjectFieldSettingConstants.NAME_FUNCTION));

		if (!Objects.equals(
				function, ObjectFieldSettingConstants.VALUE_COUNT)) {

			column = _objectFieldLocalService.getColumn(
				relatedObjectDefinition.getObjectDefinitionId(),
				GetterUtil.getString(
					objectFieldSettingsValues.get(
						ObjectFieldSettingConstants.NAME_OBJECT_FIELD_NAME)));

			if (objectRelationship.isSelf()) {
				Table table = column.getTable();

				if (Objects.equals(
						table.getTableName(),
						relatedDynamicObjectDefinitionTable.getTableName())) {

					column = relatedDynamicObjectDefinitionTable.getColumn(
						column.getName());
				}
				else {
					column =
						relatedExtensionDynamicObjectDefinitionTable.getColumn(
							column.getName());
				}
			}
		}

		Table<?> table = column.getTable();

		JoinStep joinStep = DSLQueryFactoryUtil.select(
			_getFunctionExpression(column, function)
		).from(
			table
		);

		Column<?, Long> primaryKeyColumn = _getPrimaryKeyColumn(
			relatedDynamicObjectDefinitionTable,
			relatedExtensionDynamicObjectDefinitionTable, table.getTableName());

		Set<String> tableNames = SetUtil.fromArray(table.getName());

		Predicate predicate = null;

		if (Objects.equals(
				objectRelationship.getType(),
				ObjectRelationshipConstants.TYPE_MANY_TO_MANY)) {

			String pkObjectFieldDBColumnName =
				objectDefinition.getPKObjectFieldDBColumnName();
			String relatedPKObjectFieldDBColumnName =
				relatedObjectDefinition.getPKObjectFieldDBColumnName();

			if (objectRelationship.isSelf()) {
				pkObjectFieldDBColumnName += "1";
				relatedPKObjectFieldDBColumnName += "2";
			}

			DynamicObjectRelationshipMappingTable
				dynamicObjectRelationshipMappingTable =
					new DynamicObjectRelationshipMappingTable(
						pkObjectFieldDBColumnName,
						relatedPKObjectFieldDBColumnName,
						objectRelationship.getDBTableName());

			joinStep = joinStep.innerJoinON(
				dynamicObjectRelationshipMappingTable,
				dynamicObjectRelationshipMappingTable.getPrimaryKeyColumn2(
				).eq(
					primaryKeyColumn
				));

			predicate =
				dynamicObjectRelationshipMappingTable.getPrimaryKeyColumn1(
				).eq(
					dynamicObjectDefinitionTable.getPrimaryKeyColumn()
				);
		}
		else if (Objects.equals(
					objectRelationship.getType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

			ObjectField relationshipObjectField =
				_objectFieldLocalService.getObjectField(
					objectRelationship.getObjectFieldId2());

			Column<DynamicObjectDefinitionTable, Long>
				relationshipObjectFieldColumn =
					(Column<DynamicObjectDefinitionTable, Long>)
						_objectFieldLocalService.getColumn(
							relatedObjectDefinition.getObjectDefinitionId(),
							relationshipObjectField.getName());

			joinStep = _addInnerJoinON(
				relationshipObjectFieldColumn,
				relatedDynamicObjectDefinitionTable,
				relatedExtensionDynamicObjectDefinitionTable, joinStep,
				primaryKeyColumn, tableNames);

			predicate = relationshipObjectFieldColumn.eq(
				dynamicObjectDefinitionTable.getPrimaryKeyColumn());
		}

		for (ObjectFilter objectFilter :
				(List<ObjectFilter>)objectFieldSettingsValues.get("filters")) {

			joinStep = _addInnerJoinON(
				_objectFieldLocalService.getColumn(
					relatedObjectDefinition.getObjectDefinitionId(),
					objectFilter.getFilterBy()),
				relatedDynamicObjectDefinitionTable,
				relatedExtensionDynamicObjectDefinitionTable, joinStep,
				primaryKeyColumn, tableNames);

			if (StringUtil.equals(
					objectFilter.getFilterType(),
					ObjectFilterConstants.TYPE_CURRENT_USER)) {

				objectFilter.setJSON(
					JSONUtil.put(
						"currentUserId", PrincipalThreadLocal.getUserId()
					).toString());
			}

			ObjectFilterParser objectFilterParser = _objectFilterParsers.get(
				objectFilter.getFilterType());

			predicate = predicate.and(
				_filterFactory.create(
					objectFilterParser.parse(objectFilter),
					relatedObjectDefinition));
		}

		return joinStep.where(predicate);
	}

	private String _getAutoIncrementSortableValue(
		String prefix, String suffix, String value) {

		return StringUtil.removeLast(
			StringUtil.removeFirst(value, prefix), suffix);
	}

	private String _getAutoIncrementValue(
		String counterName, String initialValue, String prefix, String suffix,
		String valueString) {

		long currentId = counterLocalService.getCurrentId(counterName);

		long value = GetterUtil.getLong(
			_getAutoIncrementSortableValue(prefix, suffix, valueString));

		if ((currentId == 0) || (value > currentId)) {
			currentId = Math.max(value, GetterUtil.getLong(initialValue));

			counterLocalService.reset(counterName, currentId);
		}
		else if (value == 0) {
			currentId = counterLocalService.increment(counterName);
		}
		else {
			currentId = value;
		}

		StringBuilder sb = new StringBuilder(String.valueOf(currentId));

		while (sb.length() < initialValue.length()) {
			sb.insert(0, CharPool.NUMBER_0);
		}

		return StringBundler.concat(
			GetterUtil.getString(prefix), sb, GetterUtil.getString(suffix));
	}

	private Map<String, Object> _getColumns(
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
		ObjectDefinition objectDefinition) {

		Map<String, Object> columns = new HashMap<>();

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				objectDefinition.getObjectDefinitionId());

		for (ObjectField objectField : objectFields) {
			if (Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_AGGREGATION) ||
				Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT) ||
				Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_FORMULA) ||
				Objects.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_RICH_TEXT)) {

				continue;
			}

			if (Objects.equals(objectField.getName(), "id")) {
				columns.put(
					objectField.getName(),
					dynamicObjectDefinitionTable.getPrimaryKeyColumn());

				continue;
			}

			Column<?, Object> column =
				(Column<?, Object>)_objectFieldLocalService.getColumn(
					objectDefinition.getObjectDefinitionId(),
					objectField.getName());

			columns.put(objectField.getName(), column);
		}

		return columns;
	}

	private String _getDBType(Alias<?> alias, long objectDefinitionId)
		throws PortalException {

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectDefinitionId, alias.getName());

		ObjectFieldSetting objectFieldSetting =
			_objectFieldSettingPersistence.fetchByOFI_N(
				objectField.getObjectFieldId(), "output");

		ObjectFieldBusinessType objectFieldBusinessType =
			_objectFieldBusinessTypeRegistry.getObjectFieldBusinessType(
				objectFieldSetting.getValue());

		return objectFieldBusinessType.getDBType();
	}

	private DynamicObjectDefinitionTable _getDynamicObjectDefinitionTable(
			long objectDefinitionId)
		throws PortalException {

		// TODO Cache this across the cluster with proper invalidation when the
		// object definition or its object fields are updated

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		return new DynamicObjectDefinitionTable(
			objectDefinition,
			_objectFieldLocalService.getObjectFields(
				objectDefinitionId, objectDefinition.getDBTableName()),
			objectDefinition.getDBTableName());
	}

	private DynamicObjectDefinitionTable
			_getExtensionDynamicObjectDefinitionTable(long objectDefinitionId)
		throws PortalException {

		// TODO Cache this across the cluster with proper invalidation when the
		// object definition or its object fields are updated

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		return new DynamicObjectDefinitionTable(
			objectDefinition,
			_objectFieldLocalService.getObjectFields(
				objectDefinitionId, objectDefinition.getExtensionDBTableName()),
			objectDefinition.getExtensionDBTableName());
	}

	private DSLQuery _getExtensionDynamicObjectDefinitionTableSelectDSLQuery(
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable,
		long primaryKey, Expression<?>[] selectExpressions) {

		return DSLQueryFactoryUtil.select(
			selectExpressions
		).from(
			extensionDynamicObjectDefinitionTable
		).where(
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				primaryKey
			)
		);
	}

	private DSLQuery _getFetchManyToOneObjectEntryDSLQuery(
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable, long groupId,
		ObjectRelationship objectRelationship, long primaryKey,
		Column<DynamicObjectDefinitionTable, Long> primaryKeyColumn) {

		FromStep fromStep = DSLQueryFactoryUtil.selectDistinct(
			ObjectEntryTable.INSTANCE);
		ObjectField objectField = _objectFieldPersistence.fetchByPrimaryKey(
			objectRelationship.getObjectFieldId2());

		return fromStep.from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				(Expression<Long>)dynamicObjectDefinitionTable.getColumn(
					objectField.getDBColumnName()))
		).where(
			primaryKeyColumn.eq(
				primaryKey
			).and(
				ObjectEntryTable.INSTANCE.groupId.eq(groupId)
			)
		);
	}

	private Expression<?> _getFunctionExpression(
		Column<?, ?> column, String function) {

		if (function.equals("AVERAGE")) {
			return DSLFunctionFactoryUtil.avg(
				(Expression<? extends Number>)column);
		}

		if (function.equals("COUNT")) {
			return DSLFunctionFactoryUtil.count(column);
		}

		if (function.equals("MAX")) {
			return DSLFunctionFactoryUtil.max(
				(Expression<? extends Comparable>)column);
		}

		if (function.equals("MIN")) {
			return DSLFunctionFactoryUtil.min(
				(Expression<? extends Comparable>)column);
		}

		if (function.equals("SUM")) {
			return DSLFunctionFactoryUtil.sum(
				(Expression<? extends Number>)column);
		}

		throw new IllegalArgumentException("Invalid function " + function);
	}

	private Predicate _getInnerJoinRootObjectDefinitionTablePredicate(
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable) {

		if (dynamicObjectDefinitionTable == null) {
			return null;
		}

		return dynamicObjectDefinitionTable.getPrimaryKeyColumn(
		).eq(
			ObjectEntryTable.INSTANCE.rootObjectEntryId
		);
	}

	private Key _getKey() throws PortalException {
		return new SecretKeySpec(
			Base64.decode(PropsValues.OBJECT_ENCRYPTION_KEY),
			PropsValues.OBJECT_ENCRYPTION_ALGORITHM);
	}

	private Set<Locale> _getLocales(
		long companyId, List<ObjectField> objectFields,
		Map<String, Serializable> values) {

		Set<Locale> locales = new HashSet<>();

		for (ObjectField objectField : objectFields) {
			Map<String, String> localizedValues =
				(Map<String, String>)values.get(
					objectField.getI18nObjectFieldName());

			if (MapUtil.isEmpty(localizedValues)) {
				continue;
			}

			for (String languageId : localizedValues.keySet()) {
				locales.add(LocaleUtil.fromLanguageId(languageId, true, false));
			}
		}

		return SetUtil.intersect(
			locales, _language.getCompanyAvailableLocales(companyId));
	}

	private String _getLocalizedValue(
		String languageId, Map<String, String> localizedValues) {

		if (localizedValues == null) {
			return StringPool.BLANK;
		}

		return Objects.toString(
			localizedValues.get(languageId), StringPool.BLANK);
	}

	private GroupByStep _getManyToManyObjectEntriesGroupByStep(
			FromStep fromStep, long groupId, long objectRelationshipId,
			long primaryKey, boolean related, boolean reverse, String search)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.findByPrimaryKey(
				objectRelationshipId);

		long objectDefinitionId1 = objectRelationship.getObjectDefinitionId1();

		long objectDefinitionId2 = objectRelationship.getObjectDefinitionId2();

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					_objectDefinitionPersistence.findByPrimaryKey(
						objectDefinitionId2),
					_objectFieldLocalService);
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(objectDefinitionId2);
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(objectDefinitionId2);

		Column<DynamicObjectDefinitionTable, Long>
			dynamicObjectDefinitionTablePrimaryKeyColumn =
				dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		ObjectDefinition objectDefinition1 =
			_objectDefinitionPersistence.fetchByPrimaryKey(objectDefinitionId1);
		ObjectDefinition objectDefinition2 =
			_objectDefinitionPersistence.fetchByPrimaryKey(objectDefinitionId2);

		Map<String, String> pkObjectFieldDBColumnNames =
			ObjectRelationshipUtil.getPKObjectFieldDBColumnNames(
				objectDefinition1, objectDefinition2, reverse);

		DynamicObjectRelationshipMappingTable
			dynamicObjectRelationshipMappingTable =
				new DynamicObjectRelationshipMappingTable(
					pkObjectFieldDBColumnNames.get(
						"pkObjectFieldDBColumnName1"),
					pkObjectFieldDBColumnNames.get(
						"pkObjectFieldDBColumnName2"),
					objectRelationship.getDBTableName());

		Column<DynamicObjectRelationshipMappingTable, Long> primaryKeyColumn1 =
			dynamicObjectRelationshipMappingTable.getPrimaryKeyColumn1();
		Column<DynamicObjectRelationshipMappingTable, Long> primaryKeyColumn2 =
			dynamicObjectRelationshipMappingTable.getPrimaryKeyColumn2();

		return fromStep.from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(
				dynamicObjectDefinitionTablePrimaryKeyColumn)
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				dynamicObjectDefinitionTablePrimaryKeyColumn
			)
		).leftJoinOn(
			dynamicObjectDefinitionLocalizationTable,
			ObjectEntrySearchUtil.getLeftJoinLocalizationTablePredicate(
				dynamicObjectDefinitionLocalizationTable,
				dynamicObjectDefinitionTable)
		).leftJoinOn(
			dynamicObjectRelationshipMappingTable,
			primaryKeyColumn2.eq(dynamicObjectDefinitionTablePrimaryKeyColumn)
		).where(
			ObjectEntryTable.INSTANCE.companyId.eq(
				objectRelationship.getCompanyId()
			).and(
				() -> {
					if (groupId == 0) {
						return null;
					}

					return ObjectEntryTable.INSTANCE.groupId.eq(groupId);
				}
			).and(
				ObjectEntryTable.INSTANCE.objectDefinitionId.eq(
					objectDefinitionId2)
			).and(
				() -> {
					if (ObjectEntryThreadLocal.
							isSkipObjectEntryResourcePermission() ||
						(PermissionThreadLocal.getPermissionChecker() ==
							null)) {

						return null;
					}

					return _getPermissionWherePredicate(
						dynamicObjectDefinitionTable, groupId);
				}
			).and(
				() -> {
					if (related) {
						return primaryKeyColumn1.eq(primaryKey);
					}

					return dynamicObjectDefinitionTablePrimaryKeyColumn.notIn(
						DSLQueryFactoryUtil.select(
							primaryKeyColumn2
						).from(
							dynamicObjectRelationshipMappingTable
						).where(
							primaryKeyColumn1.eq(primaryKey)
						));
				}
			).and(
				() -> {
					if (objectDefinition1.getObjectDefinitionId() ==
							objectDefinition2.getObjectDefinitionId()) {

						return dynamicObjectDefinitionTablePrimaryKeyColumn.neq(
							primaryKey);
					}

					return null;
				}
			).and(
				ObjectEntrySearchUtil.getRelatedModelsPredicate(
					objectDefinition2, _objectFieldLocalService, search,
					dynamicObjectDefinitionTable)
			)
		);
	}

	private GroupByStep _getOneToManyObjectEntriesGroupByStep(
			FromStep fromStep, long groupId, long objectRelationshipId,
			long primaryKey, boolean related, String search)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.findByPrimaryKey(
				objectRelationshipId);

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					_objectDefinitionPersistence.findByPrimaryKey(
						objectRelationship.getObjectDefinitionId2()),
					_objectFieldLocalService);
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable =
			_getDynamicObjectDefinitionTable(
				objectRelationship.getObjectDefinitionId2());
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable =
			_getExtensionDynamicObjectDefinitionTable(
				objectRelationship.getObjectDefinitionId2());
		ObjectField objectField = _objectFieldPersistence.fetchByPrimaryKey(
			objectRelationship.getObjectFieldId2());
		DynamicObjectDefinitionTable rootDynamicObjectDefinitionTable =
			_getRootDynamicObjectDefinitionTable(
				objectRelationship.getObjectDefinitionId2());

		Column<DynamicObjectDefinitionTable, Long> primaryKeyColumn =
			dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		return fromStep.from(
			dynamicObjectDefinitionTable
		).innerJoinON(
			extensionDynamicObjectDefinitionTable,
			extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn(
			).eq(
				primaryKeyColumn
			)
		).innerJoinON(
			ObjectEntryTable.INSTANCE,
			ObjectEntryTable.INSTANCE.objectEntryId.eq(primaryKeyColumn)
		).innerJoinON(
			rootDynamicObjectDefinitionTable,
			_getInnerJoinRootObjectDefinitionTablePredicate(
				rootDynamicObjectDefinitionTable)
		).leftJoinOn(
			dynamicObjectDefinitionLocalizationTable,
			ObjectEntrySearchUtil.getLeftJoinLocalizationTablePredicate(
				dynamicObjectDefinitionLocalizationTable,
				dynamicObjectDefinitionTable)
		).where(
			ObjectEntryTable.INSTANCE.companyId.eq(
				objectRelationship.getCompanyId()
			).and(
				() -> {
					if (!related && (groupId > 0)) {
						return ObjectEntryTable.INSTANCE.groupId.eq(groupId);
					}

					return null;
				}
			).and(
				ObjectEntryTable.INSTANCE.objectDefinitionId.eq(
					objectRelationship.getObjectDefinitionId2())
			).and(
				() -> {
					Column<DynamicObjectDefinitionTable, Long> column = null;

					if (Objects.equals(
							objectField.getDBTableName(),
							dynamicObjectDefinitionTable.getName())) {

						column =
							(Column<DynamicObjectDefinitionTable, Long>)
								dynamicObjectDefinitionTable.getColumn(
									objectField.getDBColumnName());
					}
					else {
						column =
							(Column<DynamicObjectDefinitionTable, Long>)
								extensionDynamicObjectDefinitionTable.getColumn(
									objectField.getDBColumnName());
					}

					return column.eq(related ? primaryKey : 0L);
				}
			).and(
				() ->
					objectRelationship.isSelf() ?
						primaryKeyColumn.neq(primaryKey) : null
			).and(
				() -> {
					if (ObjectEntryThreadLocal.
							isSkipObjectEntryResourcePermission() ||
						(PermissionThreadLocal.getPermissionChecker() ==
							null)) {

						return null;
					}

					return _getPermissionWherePredicate(
						dynamicObjectDefinitionTable, groupId);
				}
			).and(
				ObjectEntrySearchUtil.getRelatedModelsPredicate(
					_objectDefinitionPersistence.fetchByPrimaryKey(
						objectRelationship.getObjectDefinitionId2()),
					_objectFieldLocalService, search,
					dynamicObjectDefinitionTable)
			)
		);
	}

	private Predicate _getPermissionWherePredicate(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			long groupId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			dynamicObjectDefinitionTable.getObjectDefinition();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((permissionChecker == null) ||
			!_inlineSQLHelper.isEnabled(
				objectDefinition.getCompanyId(), groupId)) {

			return null;
		}

		Column<?, Long> primaryKeyColumn =
			dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		if (objectDefinition.isRootDescendantNode()) {
			objectDefinition = _objectDefinitionPersistence.findByPrimaryKey(
				objectDefinition.getRootObjectDefinitionId());

			primaryKeyColumn = ObjectEntryTable.INSTANCE.rootObjectEntryId;
		}

		Predicate individualScopePredicate =
			_inlineSQLHelper.getPermissionWherePredicate(
				objectDefinition.getClassName(), primaryKeyColumn, groupId);

		if (individualScopePredicate == null) {
			return null;
		}

		if (!objectDefinition.isAccountEntryRestricted()) {
			return individualScopePredicate;
		}

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectDefinition.getAccountEntryRestrictedObjectFieldId());

		Table<?> table = _objectFieldLocalService.getTable(
			objectDefinition.getObjectDefinitionId(), objectField.getName());

		Column<?, Long> column = (Column<?, Long>)table.getColumn(
			objectField.getDBColumnName());

		return individualScopePredicate.or(
			column.in(
				_getAccountEntriesDSLQuery(
					objectDefinition.getCompanyId(),
					permissionChecker.getUserId())
			).withParentheses()
		).withParentheses();
	}

	private Column<?, Long> _getPrimaryKeyColumn(
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
		DynamicObjectDefinitionTable extensionDynamicObjectDefinitionTable,
		String tableName) {

		if (tableName.equals(dynamicObjectDefinitionTable.getTableName())) {
			return dynamicObjectDefinitionTable.getPrimaryKeyColumn();
		}
		else if (tableName.equals(
					extensionDynamicObjectDefinitionTable.getTableName())) {

			return extensionDynamicObjectDefinitionTable.getPrimaryKeyColumn();
		}

		return ObjectEntryTable.INSTANCE.objectEntryId;
	}

	private Predicate _getRelationshipObjectFieldPredicate(
			Column<?, ?> column, ObjectField objectField, String search)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.fetchByObjectFieldId2(
				objectField.getObjectFieldId());

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectRelationship.getObjectDefinitionId1());

		ObjectField titleObjectField =
			_objectFieldLocalService.fetchObjectField(
				objectDefinition.getTitleObjectFieldId());

		Table<?> table = _objectFieldLocalService.getTable(
			objectDefinition.getObjectDefinitionId(),
			titleObjectField.getName());

		if (Objects.equals(
				table.getColumn(titleObjectField.getDBColumnName()),
				ObjectEntryTable.INSTANCE.objectEntryId)) {

			return ObjectEntrySearchUtil.getObjectFieldPredicate(
				column, objectField.getDBType(), search);
		}

		Predicate relatedModelsPredicate =
			ObjectEntrySearchUtil.getRelatedModelsPredicate(
				objectDefinition, _objectFieldLocalService, search, table);

		if (relatedModelsPredicate == null) {
			return null;
		}

		Column<?, Long> primaryKeyColumn =
			ObjectEntrySearchUtil.getPrimaryKeyColumn(
				objectDefinition.getPKObjectFieldDBColumnName(), table);

		return column.in(
			DSLQueryFactoryUtil.select(
				table.getColumn(primaryKeyColumn.getName())
			).from(
				table
			).where(
				relatedModelsPredicate
			));
	}

	private Object _getResult(
			Object entryValues, long objectDefinitionId,
			Expression<?> selectExpression)
		throws PortalException {

		Object result = null;

		try {
			if (selectExpression instanceof Alias) {
				Alias<?> alias = (Alias<?>)selectExpression;

				result = _getValue(
					entryValues,
					DynamicObjectDefinitionTableUtil.getSQLType(
						_getDBType(alias, objectDefinitionId)));
			}
			else if (selectExpression instanceof Column) {
				Column<?, ?> column = (Column<?, ?>)selectExpression;

				result = _getValue(entryValues, column.getSQLType());
			}
			else if (selectExpression instanceof ScalarDSLQueryAlias) {
				ScalarDSLQueryAlias scalarDSLQueryAlias =
					(ScalarDSLQueryAlias)selectExpression;

				result = _getValue(
					entryValues, scalarDSLQueryAlias.getSQLType());

				if (result == null) {
					result = "0";
				}
				else {
					BigDecimal bigDecimal = new BigDecimal(result.toString());

					result = String.valueOf(
						BigDecimalUtil.stripTrailingZeros(bigDecimal));
				}
			}
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}

		return result;
	}

	private DynamicObjectDefinitionTable _getRootDynamicObjectDefinitionTable(
			long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (!objectDefinition.isRootDescendantNode()) {
			return null;
		}

		ObjectDefinition rootObjectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectDefinition.getRootObjectDefinitionId());

		if (!rootObjectDefinition.isAccountEntryRestricted()) {
			return null;
		}

		ObjectField objectField = _objectFieldPersistence.findByPrimaryKey(
			rootObjectDefinition.getAccountEntryRestrictedObjectFieldId());

		if (Objects.equals(
				objectField.getDBTableName(),
				rootObjectDefinition.getDBTableName())) {

			return _getDynamicObjectDefinitionTable(
				rootObjectDefinition.getObjectDefinitionId());
		}

		return _getExtensionDynamicObjectDefinitionTable(
			rootObjectDefinition.getObjectDefinitionId());
	}

	private Expression<?>[] _getSelectExpressions(
		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable) {

		if (dynamicObjectDefinitionLocalizationTable == null) {
			return new Expression<?>[0];
		}

		List<Expression<?>> selectExpressions = new ArrayList<>(
			dynamicObjectDefinitionLocalizationTable.getObjectFieldColumns());

		return selectExpressions.toArray(new Expression<?>[0]);
	}

	private Expression<?>[] _getSelectExpressions(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			String[] selectedObjectFieldNames)
		throws PortalException {

		List<Expression<?>> selectExpressions = new ArrayList<>();

		for (Column<DynamicObjectDefinitionTable, ?> column :
				dynamicObjectDefinitionTable.getColumns()) {

			if ((selectedObjectFieldNames != null) &&
				!ArrayUtil.contains(
					selectedObjectFieldNames,
					StringUtil.removeLast(
						column.getName(), StringPool.UNDERLINE)) &&
				!Objects.equals(
					column.getName(),
					dynamicObjectDefinitionTable.getPrimaryKeyColumnName())) {

				continue;
			}

			selectExpressions.add(column);
		}

		Map<String, Object> columns = null;

		for (ObjectField objectField :
				dynamicObjectDefinitionTable.getObjectFields()) {

			if (!objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_AGGREGATION) &&
				!objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_FORMULA)) {

				continue;
			}

			if ((selectedObjectFieldNames != null) &&
				!ArrayUtil.contains(
					selectedObjectFieldNames, objectField.getName())) {

				continue;
			}

			Map<String, Object> objectFieldSettingsValues = new HashMap<>();

			List<ObjectFieldSetting> objectFieldSettings =
				_objectFieldSettingLocalService.
					getObjectFieldObjectFieldSettings(
						objectField.getObjectFieldId());

			for (ObjectFieldSetting objectFieldSetting : objectFieldSettings) {
				if (StringUtil.equals(
						objectFieldSetting.getName(), "filters")) {

					objectFieldSettingsValues.put(
						objectFieldSetting.getName(),
						objectFieldSetting.getObjectFilters());
				}
				else {
					objectFieldSettingsValues.put(
						objectFieldSetting.getName(),
						objectFieldSetting.getValue());
				}
			}

			ObjectDefinition objectDefinition =
				dynamicObjectDefinitionTable.getObjectDefinition();

			if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_AGGREGATION)) {

				selectExpressions.add(
					DSLQueryFactoryUtil.scalarSubDSLQuery(
						_getAggregationObjectFieldDSLQuery(
							dynamicObjectDefinitionTable, objectDefinition,
							objectFieldSettingsValues),
						DynamicObjectDefinitionTableUtil.getJavaClass(
							objectField.getDBType()),
						objectField.getName(),
						DynamicObjectDefinitionTableUtil.getSQLType(
							objectField.getDBType())));
			}
			else if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_FORMULA)) {

				Object script = objectFieldSettingsValues.get("script");

				if (script == null) {
					continue;
				}

				if (columns == null) {
					columns = _getColumns(
						dynamicObjectDefinitionTable, objectDefinition);
				}

				DDMExpression<Expression<?>> ddmExpression =
					_ddmExpressionFactory.createExpression(
						CreateExpressionRequest.Builder.newBuilder(
							String.valueOf(script)
						).build());

				ddmExpression.setVariables(columns);

				Class<?> clazz = Double.class;

				String output = GetterUtil.getString(
					objectFieldSettingsValues.get("output"));

				if (Objects.equals(output, "Integer")) {
					clazz = Integer.class;
				}

				try {
					Expression<?> expression = ddmExpression.getDSLExpression();

					selectExpressions.add(
						expression.as(objectField.getName(), clazz));
				}
				catch (Exception exception) {
					_log.error(exception);
				}
			}
		}

		return selectExpressions.toArray(new Expression<?>[0]);
	}

	/**
	 * @see com.liferay.portal.upgrade.util.Table#getValue
	 */
	private Object _getValue(Object object, int sqlType) throws SQLException {
		if (sqlType == Types.BIGINT) {
			return GetterUtil.getLong(object);
		}
		else if (sqlType == Types.BOOLEAN) {
			return GetterUtil.getBoolean(object);
		}
		else if (sqlType == Types.CLOB) {
			return GetterUtil.getString(object);
		}
		else if ((sqlType == Types.DATE) || (sqlType == Types.TIMESTAMP)) {
			if (object == null) {
				return null;
			}

			Date date = (Date)object;

			return new Timestamp(date.getTime());
		}
		else if (sqlType == Types.DECIMAL) {
			return object;
		}
		else if (sqlType == Types.DOUBLE) {
			return GetterUtil.getDouble(object);
		}
		else if (sqlType == Types.INTEGER) {
			return GetterUtil.getInteger(object);
		}
		else if (sqlType == Types.VARCHAR) {
			return object;
		}

		throw new IllegalArgumentException(
			"Unable to get value with SQL type " + sqlType);
	}

	private Map<String, Serializable> _getValues(
			long objectDefinitionId, Object[] objects,
			Expression<?>[] selectExpressions)
		throws PortalException {

		Map<String, Serializable> values = new HashMap<>();

		for (int i = 0; i < selectExpressions.length; i++) {
			Expression<?> selectExpression = selectExpressions[i];

			String columnName = null;
			Class<?> javaTypeClass = null;

			Object object = null;

			if (objects != null) {
				object = objects[i];
			}

			if (selectExpression instanceof Alias) {
				Alias<?> alias = (Alias<?>)selectExpression;

				columnName = alias.getName();

				javaTypeClass = DynamicObjectDefinitionTableUtil.getJavaClass(
					_getDBType(alias, objectDefinitionId));
			}
			else if (selectExpression instanceof Column) {
				Column<?, ?> column = (Column<?, ?>)selectExpressions[i];

				columnName = column.getName();
				javaTypeClass = column.getJavaType();
			}
			else if (selectExpression instanceof ScalarDSLQueryAlias) {
				ScalarDSLQueryAlias scalarDSLQueryAlias =
					(ScalarDSLQueryAlias)selectExpressions[i];

				columnName = scalarDSLQueryAlias.getName();
				javaTypeClass = scalarDSLQueryAlias.getJavaType();
			}

			if (columnName.endsWith(StringPool.UNDERLINE)) {
				columnName = columnName.substring(0, columnName.length() - 1);

				ObjectField objectField =
					_objectFieldLocalService.fetchObjectField(
						objectDefinitionId, columnName);

				if ((object != null) && (objectField != null) &&
					objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_ENCRYPTED)) {

					try {
						object = _encryptor.decrypt(_getKey(), (String)object);
					}
					catch (IllegalArgumentException illegalArgumentException) {
						throw new IllegalArgumentException(
							"Please insert an encryption key or remove the " +
								"object's encryption field to recover these " +
									"entries.",
							illegalArgumentException);
					}
					catch (Exception exception) {
						throw new PortalException(exception);
					}
				}
			}

			_putValue(javaTypeClass, columnName, object, values);
		}

		return values;
	}

	private void _insertIntoLocalizationTable(
			Map<String, Serializable> insertedValues,
			ObjectDefinition objectDefinition, long objectEntryId,
			Map<String, Serializable> values, int workflowAction)
		throws PortalException {

		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizationTable =
				DynamicObjectDefinitionLocalizationTableFactory.create(
					objectDefinition, _objectFieldLocalService);

		if (dynamicObjectDefinitionLocalizationTable == null) {
			return;
		}

		List<ObjectField> objectFields =
			dynamicObjectDefinitionLocalizationTable.getObjectFields();

		if (objectFields.isEmpty()) {
			return;
		}

		Set<Locale> locales = _getLocales(
			objectDefinition.getCompanyId(), objectFields, values);

		if (locales.isEmpty()) {
			return;
		}

		List<String> columnNames = new ArrayList<>();

		columnNames.add(
			dynamicObjectDefinitionLocalizationTable.getForeignKeyColumnName());
		columnNames.add("languageId");

		int count = 2;

		StringBundler sb = new StringBundler();

		sb.append("insert into ");
		sb.append(dynamicObjectDefinitionLocalizationTable.getName());
		sb.append(" (");
		sb.append(
			dynamicObjectDefinitionLocalizationTable.getForeignKeyColumnName());
		sb.append(", languageId");

		for (ObjectField objectField : objectFields) {
			if (objectField.isRequired() &&
				!values.containsKey(objectField.getI18nObjectFieldName()) &&
				(workflowAction != WorkflowConstants.ACTION_SAVE_DRAFT)) {

				throw new ObjectEntryValuesException.Required(
					objectField.getName());
			}

			columnNames.add(objectField.getDBColumnName());

			count++;

			sb.append(", ");
			sb.append(objectField.getDBColumnName());
		}

		sb.append(") values (?");

		for (int i = 1; i < count; i++) {
			sb.append(", ?");
		}

		sb.append(")");

		String sql = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("SQL: " + sql);
		}

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql)) {

			for (Locale locale : locales) {
				String languageId = LocaleUtil.toLanguageId(locale);

				int index = 1;

				_setColumn(
					columnNames, index++, insertedValues, preparedStatement,
					Types.BIGINT, objectEntryId);
				_setColumn(
					columnNames, index++, new HashMap<>(), preparedStatement,
					Types.VARCHAR, languageId);

				for (ObjectField objectField : objectFields) {
					Column<?, ?> column =
						dynamicObjectDefinitionLocalizationTable.getColumn(
							objectField.getDBColumnName());

					_setColumn(
						columnNames, index++, new HashMap<>(),
						preparedStatement, column.getSQLType(),
						_getLocalizedValue(
							languageId,
							(Map<String, String>)values.get(
								objectField.getI18nObjectFieldName())));
				}

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();

			FinderCacheUtil.clearDSLQueryCache(
				dynamicObjectDefinitionLocalizationTable.getTableName());

			for (ObjectField objectField : objectFields) {
				_putLocalizedValues(
					objectField.getDBColumnName(),
					values.get(objectField.getDBColumnName() + "i18n"),
					insertedValues);
			}
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private boolean _insertIntoTable(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			Map<String, Serializable> insertedValues, long objectEntryId,
			Map<String, Serializable> values, int workflowAction)
		throws PortalException {

		List<String> columnNames = new ArrayList<>();

		Column<DynamicObjectDefinitionTable, Long> primaryKeyColumn =
			dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		columnNames.add(primaryKeyColumn.getName());

		int count = 1;
		List<ObjectField> objectFields =
			dynamicObjectDefinitionTable.getObjectFields();

		StringBundler sb = new StringBundler();

		sb.append("insert into ");
		sb.append(dynamicObjectDefinitionTable.getName());
		sb.append(" (");
		sb.append(primaryKeyColumn.getName());

		boolean staticValues = true;

		for (ObjectField objectField : objectFields) {
			if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_AGGREGATION) ||
				objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_FORMULA)) {

				staticValues = false;
			}

			if (!objectField.hasInsertValues() || objectField.isLocalized()) {
				continue;
			}

			if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_AUTO_INCREMENT)) {

				_validateAutoIncrementValue(
					objectField,
					GetterUtil.getString(values.get(objectField.getName())));

				sb.append(", ");
				sb.append(objectField.getDBColumnName());
				sb.append(", ");
				sb.append(objectField.getSortableDBColumnName());

				columnNames.add(objectField.getDBColumnName());
				columnNames.add(objectField.getSortableDBColumnName());

				count += 2;

				continue;
			}

			if (!values.containsKey(objectField.getName())) {
				if (objectField.isRequired() &&
					(workflowAction != WorkflowConstants.ACTION_SAVE_DRAFT)) {

					throw new ObjectEntryValuesException.Required(
						objectField.getName());
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"No value was provided for object field \"" +
							objectField.getName() + "\"");
				}

				continue;
			}

			if (Objects.equals(
					objectField.getRelationshipType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_ONE)) {

				_validateOneToOneInsert(
					objectField.getDBColumnName(),
					GetterUtil.getLong(values.get(objectField.getName())),
					dynamicObjectDefinitionTable);
			}

			columnNames.add(objectField.getDBColumnName());

			count++;

			sb.append(", ");
			sb.append(objectField.getDBColumnName());
		}

		sb.append(") values (?");

		for (int i = 1; i < count; i++) {
			sb.append(", ?");
		}

		sb.append(")");

		String sql = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("SQL: " + sql);
		}

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql)) {

			int index = 1;

			_setColumn(
				columnNames, index++, insertedValues, preparedStatement,
				Types.BIGINT, objectEntryId);

			for (ObjectField objectField : objectFields) {
				if (!objectField.hasInsertValues() ||
					objectField.isLocalized()) {

					continue;
				}

				if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_AUTO_INCREMENT)) {

					Column<?, ?> column =
						dynamicObjectDefinitionTable.getColumn(
							objectField.getDBColumnName());

					String prefix = ObjectFieldSettingUtil.getValue(
						ObjectFieldSettingConstants.NAME_PREFIX, objectField);
					String suffix = ObjectFieldSettingUtil.getValue(
						ObjectFieldSettingConstants.NAME_SUFFIX, objectField);

					String value = _getAutoIncrementValue(
						ObjectFieldUtil.getCounterName(objectField),
						ObjectFieldSettingUtil.getValue(
							ObjectFieldSettingConstants.NAME_INITIAL_VALUE,
							objectField),
						prefix, suffix,
						GetterUtil.getString(
							values.get(objectField.getName())));

					_setColumn(
						columnNames, index++, insertedValues, preparedStatement,
						column.getSQLType(), value);

					column = dynamicObjectDefinitionTable.getColumn(
						objectField.getSortableDBColumnName());

					_setColumn(
						columnNames, index++, insertedValues, preparedStatement,
						column.getSQLType(),
						_getAutoIncrementSortableValue(prefix, suffix, value));

					continue;
				}

				if (!values.containsKey(objectField.getName())) {
					continue;
				}

				_setColumn(
					columnNames, dynamicObjectDefinitionTable, index++,
					insertedValues, objectField, preparedStatement, values);
			}

			preparedStatement.executeUpdate();

			FinderCacheUtil.clearDSLQueryCache(
				dynamicObjectDefinitionTable.getTableName());
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}

		return staticValues;
	}

	private List<Object[]> _list(
			DSLQuery dslQuery, long objectDefinitionId,
			Expression<?>[] selectExpressions)
		throws PortalException {

		List<Object[]> results = new ArrayList<>();

		List<Object> entriesValues = objectEntryPersistence.dslQuery(dslQuery);

		for (Object entryValues : entriesValues) {
			Object[] result = new Object[selectExpressions.length];

			if (selectExpressions.length == 1) {
				result[0] = _getResult(
					entryValues, objectDefinitionId, selectExpressions[0]);
			}
			else {
				for (int i = 0; i < selectExpressions.length; i++) {
					result[i] = _getResult(
						((Object[])entryValues)[i], objectDefinitionId,
						selectExpressions[i]);
				}
			}

			results.add(result);
		}

		return results;
	}

	private void _performActions(
			long objectDefinitionId,
			ActionableDynamicQuery.PerformActionMethod<?> performActionMethod)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property objectDefinitionIdProperty =
					PropertyFactoryUtil.forName("objectDefinitionId");

				dynamicQuery.add(
					objectDefinitionIdProperty.eq(objectDefinitionId));
			});
		actionableDynamicQuery.setParallel(true);
		actionableDynamicQuery.setPerformActionMethod(performActionMethod);

		actionableDynamicQuery.performActions();
	}

	private void _putInsertedValue(
		Map<String, Serializable> insertedValues, String key,
		Serializable serializable) {

		if (!key.isEmpty() &&
			(key.charAt(key.length() - 1) == CharPool.UNDERLINE)) {

			key = key.substring(0, key.length() - 1);
		}

		insertedValues.put(key, serializable);
	}

	private void _putLocalizedValues(
		String columnName, Serializable localizedValues,
		Map<String, Serializable> values) {

		values.put(columnName + "i18n", localizedValues);
		values.putIfAbsent(
			StringUtil.removeLast(columnName, StringPool.UNDERLINE),
			StringPool.BLANK);
	}

	private void _putObjectFilterParser(
		ObjectFilterParser objectFilterParser, String... types) {

		for (String type : types) {
			_objectFilterParsers.put(type, objectFilterParser);
		}
	}

	private void _putValue(
		Class<?> javaTypeClass, String name, Object object,
		Map<String, Serializable> values) {

		if (javaTypeClass == BigDecimal.class) {
			values.put(
				name, BigDecimalUtil.stripTrailingZeros((BigDecimal)object));
		}
		else if (javaTypeClass == Blob.class) {
			byte[] bytes = null;

			if (object != null) {
				if (object instanceof Blob) {

					// Hypersonic

					Blob blob = (Blob)object;

					try {
						bytes = blob.getBytes(1, (int)blob.length());
					}
					catch (SQLException sqlException) {
						throw new SystemException(sqlException);
					}
				}
				else if (object instanceof byte[]) {

					// MySQL

					bytes = (byte[])object;
				}
				else {
					Class<?> objectClass = object.getClass();

					throw new IllegalArgumentException(
						StringBundler.concat(
							"Unable to put \"", name,
							"\" with unknown object class ",
							objectClass.getName()));
				}
			}

			values.put(name, bytes);
		}
		else if (javaTypeClass == Boolean.class) {
			if (object == null) {
				object = Boolean.FALSE;
			}

			if (object instanceof Byte) {
				Byte byteObject = (Byte)object;

				if (byteObject.intValue() == 0) {
					object = Boolean.FALSE;
				}
				else {
					object = Boolean.TRUE;
				}
			}

			values.put(name, (Boolean)object);
		}
		else if (javaTypeClass == Clob.class) {
			if (object == null) {
				values.put(name, StringPool.BLANK);
			}
			else {
				if (DBManagerUtil.getDBType() == DBType.POSTGRESQL) {
					values.put(name, (String)object);
				}
				else {
					Clob clob = (Clob)object;

					try {
						InputStream inputStream = clob.getAsciiStream();

						values.put(
							name,
							GetterUtil.getString(
								IOUtils.toString(
									inputStream, StandardCharsets.UTF_8)));
					}
					catch (IOException | SQLException exception) {
						throw new SystemException(exception);
					}
				}
			}
		}
		else if (javaTypeClass == Date.class) {
			values.put(name, (Date)object);
		}
		else if (javaTypeClass == Double.class) {
			Number number = (Number)object;

			if (number == null) {
				number = Double.valueOf(0D);
			}
			else if (!(number instanceof Double)) {
				number = number.doubleValue();
			}

			values.put(name, number);
		}
		else if (javaTypeClass == Integer.class) {
			Number number = (Number)object;

			if (number == null) {
				number = Integer.valueOf(0);
			}
			else if (!(number instanceof Integer)) {
				number = number.intValue();
			}

			values.put(name, number);
		}
		else if (javaTypeClass == Long.class) {
			Number number = (Number)object;

			if (number == null) {
				number = Long.valueOf(0L);
			}
			else if (!(number instanceof Long)) {
				number = number.longValue();
			}

			values.put(name, number);
		}
		else if (javaTypeClass == String.class) {
			values.put(name, (String)object);
		}
		else if (javaTypeClass == Timestamp.class) {
			values.put(name, (Timestamp)object);
		}
		else {
			throw new IllegalArgumentException(
				"Unable to put value with class " + javaTypeClass.getName());
		}
	}

	private void _reindex(ObjectEntry objectEntry) throws PortalException {
		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectEntry.getObjectDefinitionId());

		if (!objectDefinition.isEnableIndexSearch()) {
			return;
		}

		Indexer<ObjectEntry> indexer = IndexerRegistryUtil.getIndexer(
			objectDefinition.getClassName());

		try (SafeCloseable safeCloseable =
				StrictObjectReindexThreadLocal.
					setStrictObjectReindexWithSafeCloseable(true)) {

			indexer.reindex(objectEntry);
		}
	}

	private void _setColumn(
			List<String> columnNames,
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			int index, Map<String, Serializable> insertedValues,
			ObjectField objectField, PreparedStatement preparedStatement,
			Map<String, Serializable> values)
		throws Exception {

		Column<?, ?> column = dynamicObjectDefinitionTable.getColumn(
			objectField.getDBColumnName());

		Object value = values.get(objectField.getName());

		if (objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_ENCRYPTED)) {

			_setColumn(
				columnNames, index, insertedValues, preparedStatement,
				column.getSQLType(),
				_encryptor.encrypt(_getKey(), (String)value));
		}
		else if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_MULTISELECT_PICKLIST)) {

			String valueString = String.valueOf(value);

			// Remove the first [ and the last ] in
			// "[pickListEntryKey1, pickListEntryKey2, pickListEntryKey3]"

			if (StringUtil.endsWith(valueString, StringPool.CLOSE_BRACKET) &&
				StringUtil.startsWith(valueString, StringPool.OPEN_BRACKET)) {

				valueString = valueString.substring(
					1, valueString.length() - 1);
			}

			_setColumn(
				columnNames, index, insertedValues, preparedStatement,
				column.getSQLType(), valueString);
		}
		else {
			_setColumn(
				columnNames, index, insertedValues, preparedStatement,
				column.getSQLType(), value);
		}
	}

	/**
	 * @see com.liferay.portal.upgrade.util.Table#setColumn
	 */
	private void _setColumn(
			List<String> columnNames, int index,
			Map<String, Serializable> insertedValues,
			PreparedStatement preparedStatement, int sqlType, Object value)
		throws Exception {

		if (sqlType == Types.BIGINT) {
			Long longValue = GetterUtil.getLong(value);

			preparedStatement.setLong(index, longValue);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), longValue);
		}
		else if (sqlType == Types.BLOB) {
			if (PostgreSQLJDBCUtil.isPGStatement(preparedStatement)) {
				PostgreSQLJDBCUtil.setLargeObject(
					preparedStatement, index, (byte[])value);
			}
			else {
				preparedStatement.setBytes(index, (byte[])value);
			}

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), (byte[])value);
		}
		else if (sqlType == Types.BOOLEAN) {
			Boolean booleanValue = GetterUtil.getBoolean(value);

			preparedStatement.setBoolean(index, booleanValue);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), booleanValue);
		}
		else if (sqlType == Types.CLOB) {
			String valueString = String.valueOf(value);

			if (valueString.isEmpty() ||
				(DBManagerUtil.getDBType() == DBType.POSTGRESQL)) {

				preparedStatement.setString(index, valueString);
			}
			else {
				preparedStatement.setClob(
					index, new StringReader(valueString), valueString.length());
			}

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), valueString);
		}
		else if ((sqlType == Types.DATE) || (sqlType == Types.TIMESTAMP)) {
			Timestamp timestamp = null;

			String valueString = GetterUtil.getString(value);

			if (value instanceof Date) {
				Date date = (Date)value;

				timestamp = new Timestamp(date.getTime());

				preparedStatement.setTimestamp(index, timestamp);
			}
			else if (value instanceof LocalDateTime) {
				LocalDateTime localDateTime = (LocalDateTime)value;

				timestamp = Timestamp.valueOf(localDateTime);

				preparedStatement.setTimestamp(index, timestamp);
			}
			else if (valueString.isEmpty()) {
				preparedStatement.setTimestamp(index, null);
			}
			else {
				Date date = DateUtil.parseDate(
					"yyyy-MM-dd", valueString, LocaleUtil.getSiteDefault());

				timestamp = new Timestamp(date.getTime());

				preparedStatement.setTimestamp(index, timestamp);
			}

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), timestamp);
		}
		else if (sqlType == Types.DECIMAL) {
			if (Validator.isNull(String.valueOf(value))) {
				value = BigDecimal.ZERO;
			}

			BigDecimal bigDecimal = new BigDecimal(
				_toPeriodSeparator(String.valueOf(value)));

			preparedStatement.setBigDecimal(index, bigDecimal);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1),
				BigDecimalUtil.stripTrailingZeros(bigDecimal));
		}
		else if (sqlType == Types.DOUBLE) {
			Double doubleValue = GetterUtil.getDouble(
				_toPeriodSeparator(String.valueOf(value)));

			preparedStatement.setDouble(index, doubleValue);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), doubleValue);
		}
		else if (sqlType == Types.INTEGER) {
			Integer integer = GetterUtil.getInteger(value);

			preparedStatement.setInt(index, integer);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), integer);
		}
		else if (sqlType == Types.VARCHAR) {
			String string = null;

			if (value != null) {
				string = String.valueOf(value);
			}

			preparedStatement.setString(index, string);

			_putInsertedValue(
				insertedValues, columnNames.get(index - 1), string);
		}
		else {
			throw new IllegalArgumentException(
				"Unable to set column with SQL type " + sqlType);
		}
	}

	private void _setExternalReferenceCode(
		ObjectEntry objectEntry, Map<String, Serializable> values) {

		for (Map.Entry<String, Serializable> entry : values.entrySet()) {
			if (StringUtil.equals(entry.getKey(), "externalReferenceCode")) {
				String externalReferenceCode = String.valueOf(entry.getValue());

				if (Validator.isNull(externalReferenceCode)) {
					externalReferenceCode = objectEntry.getUuid();
				}

				_validateExternalReferenceCode(
					externalReferenceCode, objectEntry.getCompanyId(),
					objectEntry.getObjectDefinitionId(),
					objectEntry.getObjectEntryId());

				objectEntry.setExternalReferenceCode(externalReferenceCode);
			}
		}
	}

	private void _setRootObjectEntryId(
			ObjectDefinition objectDefinition, ObjectEntry objectEntry,
			Map<String, Serializable> values)
		throws PortalException {

		if (objectDefinition.getRootObjectDefinitionId() == 0) {
			objectEntry.setRootObjectEntryId(0);

			return;
		}

		if (objectDefinition.isRootNode()) {
			objectEntry.setRootObjectEntryId(objectEntry.getObjectEntryId());

			return;
		}

		ObjectDefinitionTreeFactory objectDefinitionTreeFactory =
			new ObjectDefinitionTreeFactory(
				_objectDefinitionPersistence,
				_objectRelationshipLocalServiceSnapshot.get());

		Tree objectDefinitionTree = objectDefinitionTreeFactory.create(
			objectDefinition.getRootObjectDefinitionId());

		Node objectDefinitionNode = objectDefinitionTree.getNode(
			objectDefinition.getObjectDefinitionId());

		Edge edge = objectDefinitionNode.getEdge();

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.findByPrimaryKey(
				edge.getObjectRelationshipId());

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectRelationship.getObjectFieldId2());

		ObjectEntry parentObjectEntry = getObjectEntry(
			MapUtil.getLong(values, objectField.getName()));

		if ((objectEntry.getRootObjectEntryId() !=
				parentObjectEntry.getRootObjectEntryId()) &&
			(objectEntry.getRootObjectEntryId() != 0)) {

			ObjectEntryTreeFactory objectEntryTreeFactory =
				new ObjectEntryTreeFactory(
					objectEntryLocalService,
					_objectRelationshipLocalServiceSnapshot.get());

			Tree objectEntryTree = objectEntryTreeFactory.create(
				objectEntry.getObjectEntryId());

			Iterator<Node> iterator = objectEntryTree.iterator();

			while (iterator.hasNext()) {
				Node objectEntryNode = iterator.next();

				ObjectEntry nodeObjectEntry = getObjectEntry(
					objectEntryNode.getPrimaryKey());

				nodeObjectEntry.setRootObjectEntryId(
					parentObjectEntry.getRootObjectEntryId());

				updateObjectEntry(nodeObjectEntry);
			}
		}

		objectEntry.setRootObjectEntryId(
			parentObjectEntry.getRootObjectEntryId());
	}

	private void _startWorkflowInstance(
			long userId, ObjectEntry objectEntry, ServiceContext serviceContext,
			boolean skipModelListener)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectEntry.getObjectDefinitionId());

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			_skipModelListeners.set(skipModelListener);

			WorkflowThreadLocal.setEnabled(true);

			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				objectEntry.getCompanyId(), objectEntry.getNonzeroGroupId(),
				userId, objectDefinition.getClassName(),
				objectEntry.getObjectEntryId(), objectEntry, serviceContext);
		}
		finally {
			_skipModelListeners.set(false);

			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	private String _toPeriodSeparator(String value) {
		if (Validator.isNull(value) || !NumberUtil.hasDecimalSeparator(value)) {
			return value;
		}

		return StringUtil.replace(
			value, value.charAt(NumberUtil.getDecimalSeparatorIndex(value)),
			'.');
	}

	private void _updateAsset(
			long userId, ObjectEntry objectEntry, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority,
			ServiceContext serviceContext)
		throws PortalException {

		ObjectDefinition objectDefinition =
			_objectDefinitionPersistence.findByPrimaryKey(
				objectEntry.getObjectDefinitionId());

		if (!objectDefinition.isEnableCategorization()) {
			assetCategoryIds = null;
			assetTagNames = null;
		}

		String title = StringPool.BLANK;

		try {
			title = objectEntry.getTitleValue();
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException);
			}
		}

		AssetEntry assetEntry = _assetEntryLocalService.updateEntry(
			userId, objectEntry.getNonzeroGroupId(),
			objectEntry.getCreateDate(), objectEntry.getModifiedDate(),
			objectDefinition.getClassName(), objectEntry.getObjectEntryId(),
			objectEntry.getUuid(), 0, assetCategoryIds, assetTagNames, true,
			objectEntry.isApproved(), null, null, null, null,
			ContentTypes.TEXT_PLAIN, title,
			String.valueOf(objectEntry.getObjectEntryId()), null, null, null, 0,
			0, priority, serviceContext);

		if (assetLinkEntryIds != null) {
			_assetLinkLocalService.updateLinks(
				userId, assetEntry.getEntryId(), assetLinkEntryIds,
				AssetLinkConstants.TYPE_RELATED);
		}
	}

	private void _updateResourcePermissions(
			ObjectDefinition objectDefinition, ObjectEntry objectEntry,
			ServiceContext serviceContext)
		throws PortalException {

		ModelPermissions modelPermissions =
			serviceContext.getModelPermissions();

		if (modelPermissions == null) {
			return;
		}

		_permissionService.checkPermission(
			objectEntry.getGroupId(), objectDefinition.getClassName(),
			String.valueOf(objectEntry.getObjectEntryId()));

		Collection<String> roleNames = modelPermissions.getRoleNames();

		for (ResourcePermission resourcePermission :
				_resourcePermissionLocalService.getResourcePermissions(
					objectDefinition.getCompanyId(),
					objectDefinition.getClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(objectEntry.getObjectEntryId()))) {

			Role role = _roleLocalService.fetchRole(
				resourcePermission.getRoleId());

			if ((role == null) || roleNames.contains(role.getName())) {
				continue;
			}

			for (ResourceAction resourceAction :
					_resourceActionLocalService.getResourceActions(
						objectDefinition.getClassName())) {

				_resourcePermissionLocalService.removeResourcePermission(
					objectDefinition.getCompanyId(),
					objectDefinition.getClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(objectEntry.getObjectEntryId()),
					role.getRoleId(), resourceAction.getActionId());
			}
		}

		_resourcePermissionLocalService.updateResourcePermissions(
			objectEntry.getCompanyId(), objectEntry.getGroupId(),
			objectDefinition.getClassName(),
			String.valueOf(objectEntry.getObjectEntryId()), modelPermissions);
	}

	private void _updateRootObjectEntryIds(
			AtomicBoolean objectDefinition1RootNode,
			ObjectDefinition objectDefinition2,
			long objectDefinition2RootObjectDefinitionId,
			ObjectEntry objectEntry, PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2,
			PreparedStatement preparedStatement3)
		throws SQLException {

		long rootObjectEntryId = objectEntry.getRootObjectEntryId();

		if (rootObjectEntryId == 0) {
			rootObjectEntryId = objectEntry.getObjectEntryId();

			preparedStatement2.setLong(1, rootObjectEntryId);

			preparedStatement2.setLong(2, objectEntry.getObjectEntryId());

			preparedStatement2.addBatch();

			objectDefinition1RootNode.set(true);
		}

		preparedStatement1.setLong(1, objectEntry.getObjectEntryId());

		try (ResultSet resultSet = preparedStatement1.executeQuery()) {
			while (resultSet.next()) {
				long relatedObjectEntryId = resultSet.getLong(
					objectDefinition2.getPKObjectFieldDBColumnName());

				if (objectDefinition2RootObjectDefinitionId == 0) {
					preparedStatement2.setLong(1, rootObjectEntryId);
					preparedStatement2.setLong(2, relatedObjectEntryId);

					preparedStatement2.addBatch();

					continue;
				}

				preparedStatement3.setLong(1, rootObjectEntryId);
				preparedStatement3.setLong(2, relatedObjectEntryId);

				preparedStatement3.addBatch();
			}
		}
	}

	private void _updateTable(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			long objectEntryId, Map<String, Serializable> values,
			int workflowAction)
		throws PortalException {

		List<String> columnNames = new ArrayList<>();
		int count = 0;
		ObjectEntry objectEntry = fetchObjectEntry(objectEntryId);

		StringBundler sb = new StringBundler();

		sb.append("update ");
		sb.append(dynamicObjectDefinitionTable.getName());
		sb.append(" set ");

		List<ObjectField> objectFields =
			dynamicObjectDefinitionTable.getObjectFields();

		for (ObjectField objectField : objectFields) {
			if (!objectField.hasUpdateValues() || objectField.isLocalized()) {
				continue;
			}

			if (!values.containsKey(objectField.getName())) {
				if (objectField.isRequired() &&
					(workflowAction != WorkflowConstants.ACTION_SAVE_DRAFT) &&
					(objectEntry != null) &&
					Validator.isNull(
						MapUtil.getString(
							objectEntry.getValues(), objectField.getName()))) {

					throw new ObjectEntryValuesException.Required(
						objectField.getName());
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"No value was provided for object field \"" +
							objectField.getName() + "\"");
				}

				continue;
			}

			if (Objects.equals(
					objectField.getRelationshipType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_ONE)) {

				_validateOneToOneUpdate(
					objectField.getDBColumnName(),
					GetterUtil.getLong(values.get(objectField.getName())),
					dynamicObjectDefinitionTable, objectEntryId);
			}

			columnNames.add(objectField.getDBColumnName());

			count++;

			if (count > 1) {
				sb.append(", ");
			}

			sb.append(objectField.getDBColumnName());
			sb.append(" = ?");
		}

		if (count == 0) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No values were provided for object entry " +
						objectEntryId);
			}

			return;
		}

		Column<DynamicObjectDefinitionTable, Long> primaryKeyColumn =
			dynamicObjectDefinitionTable.getPrimaryKeyColumn();

		columnNames.add(primaryKeyColumn.getName());

		Map<String, Serializable> insertedValues = new HashMap<>();

		sb.append(" where ");
		sb.append(primaryKeyColumn.getName());
		sb.append(" = ?");

		String sql = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("SQL: " + sql);
		}

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql)) {

			int index = 1;

			for (ObjectField objectField : objectFields) {
				if (!objectField.hasUpdateValues() ||
					objectField.isLocalized() ||
					!values.containsKey(objectField.getName())) {

					continue;
				}

				_setColumn(
					columnNames, dynamicObjectDefinitionTable, index++,
					insertedValues, objectField, preparedStatement, values);
			}

			_setColumn(
				columnNames, index++, insertedValues, preparedStatement,
				Types.BIGINT, objectEntryId);

			preparedStatement.executeUpdate();

			FinderCacheUtil.clearDSLQueryCache(
				dynamicObjectDefinitionTable.getTableName());
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _validateAutoIncrementValue(
			ObjectField objectField, String value)
		throws PortalException {

		if (Validator.isNull(value)) {
			return;
		}

		String prefix = ObjectFieldSettingUtil.getValue(
			ObjectFieldSettingConstants.NAME_PREFIX, objectField);
		String suffix = ObjectFieldSettingUtil.getValue(
			ObjectFieldSettingConstants.NAME_SUFFIX, objectField);

		if ((Validator.isNotNull(prefix) &&
			 !StringUtil.startsWith(value, prefix)) ||
			(Validator.isNotNull(suffix) &&
			 !StringUtil.endsWith(value, suffix))) {

			throw new ObjectEntryValuesException.InvalidValue(
				objectField.getName());
		}

		String initialValue = ObjectFieldSettingUtil.getValue(
			ObjectFieldSettingConstants.NAME_INITIAL_VALUE, objectField);
		String sortableValue = _getAutoIncrementSortableValue(
			prefix, suffix, value);

		if ((initialValue.length() > sortableValue.length()) ||
			((initialValue.length() < sortableValue.length()) &&
			 StringUtil.startsWith(sortableValue, CharPool.NUMBER_0))) {

			throw new ObjectEntryValuesException.InvalidValue(
				objectField.getName());
		}

		long parsedValue = 0;

		try {
			parsedValue = Long.parseUnsignedLong(sortableValue);
		}
		catch (NumberFormatException numberFormatException) {
			if (_log.isDebugEnabled()) {
				_log.debug(numberFormatException);
			}
		}

		if (parsedValue < GetterUtil.getLong(initialValue)) {
			throw new ObjectEntryValuesException.InvalidValue(
				objectField.getName());
		}
	}

	private void _validateExternalReferenceCode(
		String externalReferenceCode, long companyId, long objectDefinitionId,
		long objectEntryId) {

		ObjectEntry objectEntry = objectEntryPersistence.fetchByERC_C_ODI(
			externalReferenceCode, companyId, objectDefinitionId);

		if ((objectEntry != null) &&
			(objectEntry.getObjectEntryId() != objectEntryId)) {

			throw new DuplicateObjectEntryExternalReferenceCodeException(
				StringBundler.concat(
					"Duplicate object entry with external reference code ",
					externalReferenceCode, " and object definition ID ",
					objectDefinitionId));
		}
	}

	private void _validateFileExtension(
			String fileExtension, long objectFieldId, String objectFieldName)
		throws PortalException {

		if (!ArrayUtil.contains(
				_attachmentManager.getAcceptedFileExtensions(objectFieldId),
				fileExtension, true)) {

			throw new ObjectEntryValuesException.InvalidFileExtension(
				fileExtension, objectFieldName);
		}
	}

	private void _validateFileSize(
			boolean guestUser, long fileSize, long objectFieldId,
			String objectFieldName)
		throws PortalException {

		long maximumFileSize = _attachmentManager.getMaximumFileSize(
			objectFieldId, !guestUser);

		if ((maximumFileSize > 0) && (fileSize > maximumFileSize)) {
			throw new ObjectEntryValuesException.ExceedsMaxFileSize(
				maximumFileSize / (1024 * 1024), objectFieldName);
		}
	}

	private void _validateGroupId(long groupId, String scope)
		throws PortalException {

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(scope);

		if (!objectScopeProvider.isValidGroupId(groupId)) {
			throw new ObjectDefinitionScopeException(
				StringBundler.concat(
					"Group ID ", groupId, " is not valid for scope \"", scope,
					"\""));
		}
	}

	private void _validateListTypeEntryKey(
			String listTypeEntryKey, ObjectField objectField)
		throws PortalException {

		ListTypeEntry listTypeEntry =
			_listTypeEntryLocalService.fetchListTypeEntry(
				objectField.getListTypeDefinitionId(), listTypeEntryKey);

		if ((listTypeEntry == null) &&
			(Validator.isNotNull(listTypeEntryKey) ||
			 objectField.isRequired())) {

			throw new ObjectEntryValuesException.ListTypeEntry(
				objectField.getName());
		}
	}

	private void _validateObjectStateTransition(
			Map.Entry<String, Serializable> entry, long listTypeDefinitionId,
			ObjectEntry objectEntry, long objectFieldId, long userId)
		throws PortalException {

		Map<String, Serializable> values = objectEntry.getValues();

		Serializable value = values.get(entry.getKey());

		if (value == null) {
			return;
		}

		ListTypeEntry originalListTypeEntry =
			_listTypeEntryLocalService.getListTypeEntry(
				listTypeDefinitionId, String.valueOf(value));

		ObjectStateFlow objectStateFlow =
			_objectStateFlowLocalService.fetchObjectFieldObjectStateFlow(
				objectFieldId);

		ObjectState sourceObjectState =
			_objectStateLocalService.getObjectStateFlowObjectState(
				originalListTypeEntry.getListTypeEntryId(),
				objectStateFlow.getObjectStateFlowId());

		ListTypeEntry listTypeEntry =
			_listTypeEntryLocalService.getListTypeEntry(
				listTypeDefinitionId, String.valueOf(entry.getValue()));

		ObjectState targetObjectState =
			_objectStateLocalService.getObjectStateFlowObjectState(
				listTypeEntry.getListTypeEntryId(),
				objectStateFlow.getObjectStateFlowId());

		if (sourceObjectState.getObjectStateId() ==
				targetObjectState.getObjectStateId()) {

			return;
		}

		boolean invalidObjectStateTransition = true;

		for (ObjectState nextObjectState :
				_objectStateLocalService.getNextObjectStates(
					sourceObjectState.getObjectStateId())) {

			if (nextObjectState.getListTypeEntryId() ==
					targetObjectState.getListTypeEntryId()) {

				invalidObjectStateTransition = false;
			}
		}

		if (invalidObjectStateTransition) {
			User user = _userLocalService.getUser(userId);

			throw new ObjectEntryValuesException.InvalidObjectStateTransition(
				originalListTypeEntry.getName(user.getLocale()),
				sourceObjectState, listTypeEntry.getName(user.getLocale()),
				targetObjectState);
		}
	}

	private void _validateOneToOneInsert(
			String dbColumnName, long dbColumnValue,
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable)
		throws PortalException {

		if (dbColumnValue == 0) {
			return;
		}

		int count = 0;

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"select count(*) from ",
					dynamicObjectDefinitionTable.getTableName(), " where ",
					dbColumnName, " = ", dbColumnValue));
			ResultSet resultSet = preparedStatement.executeQuery()) {

			resultSet.next();

			count = resultSet.getInt(1);
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}

		if (count > 0) {
			throw new ObjectEntryValuesException.OneToOneConstraintViolation(
				dbColumnName, dbColumnValue,
				dynamicObjectDefinitionTable.getTableName());
		}
	}

	private void _validateOneToOneUpdate(
			String dbColumnName, long dbColumnValue,
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
			long objectEntryId)
		throws PortalException {

		if (dbColumnValue == 0) {
			return;
		}

		int count = 0;

		Connection connection = _currentConnection.getConnection(
			objectEntryPersistence.getDataSource());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"select count(*) from ",
					dynamicObjectDefinitionTable.getTableName(), " where ",
					dynamicObjectDefinitionTable.getPrimaryKeyColumnName(),
					" != ", objectEntryId, " and ", dbColumnName, " = ",
					dbColumnValue));
			ResultSet resultSet = preparedStatement.executeQuery()) {

			resultSet.next();

			count = resultSet.getInt(1);
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}

		if (count > 0) {
			throw new ObjectEntryValuesException.OneToOneConstraintViolation(
				dbColumnName, dbColumnValue,
				dynamicObjectDefinitionTable.getTableName());
		}
	}

	private void _validateTextMaxLength(
			int defaultMaxLength, String objectEntryValue, long objectFieldId,
			String objectFieldName)
		throws PortalException {

		int maxLength = defaultMaxLength;

		ObjectFieldSetting objectFieldSetting =
			_objectFieldSettingPersistence.fetchByOFI_N(
				objectFieldId, "maxLength");

		if (objectFieldSetting != null) {
			maxLength = GetterUtil.getInteger(objectFieldSetting.getValue());
		}

		if (objectEntryValue.length() > maxLength) {
			throw new ObjectEntryValuesException.ExceedsTextMaxLength(
				maxLength, objectFieldName);
		}
	}

	private void _validateTextMaxLength280(
			ObjectField objectField, String value)
		throws PortalException {

		_validateTextMaxLength(
			280, value, objectField.getObjectFieldId(), objectField.getName());
	}

	private void _validateUniqueValues(
			long groupId, ObjectEntry existingObjectEntry,
			ObjectDefinition objectDefinition, ObjectField objectField,
			long userId, Object value)
		throws PortalException {

		long objectEntriesCount = 0;
		Table<?> table = null;

		try {
			table = _objectFieldLocalService.getTable(
				objectDefinition.getObjectDefinitionId(),
				objectField.getName());

			Column<?, Object> column = (Column<?, Object>)table.getColumn(
				objectField.getDBColumnName());

			Predicate predicate = null;

			if (objectField.isLocalized()) {
				Map.Entry<String, String> entry =
					(Map.Entry<String, String>)value;

				DynamicObjectDefinitionLocalizationTable
					dynamicObjectDefinitionLocalizationTable =
						(DynamicObjectDefinitionLocalizationTable)table;

				predicate = ObjectEntryTable.INSTANCE.objectEntryId.in(
					DSLQueryFactoryUtil.select(
						dynamicObjectDefinitionLocalizationTable.
							getForeignKeyColumn()
					).from(
						dynamicObjectDefinitionLocalizationTable
					).where(
						dynamicObjectDefinitionLocalizationTable.
							getLanguageIdColumn(
							).eq(
								entry.getKey()
							).and(
								column.eq(entry.getValue())
							)
					));

				value = entry.getValue();
			}
			else {
				predicate =
					ObjectEntrySearchUtil.
						getUniqueCompositeKeyObjectFieldPredicate(
							column, objectField.getDBType(), value);
			}

			if (existingObjectEntry != null) {
				predicate = predicate.and(
					ObjectEntryTable.INSTANCE.objectEntryId.neq(
						existingObjectEntry.getObjectEntryId()));
			}

			objectEntriesCount = getObjectEntriesCount(
				groupId, objectDefinition, predicate);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}

		if (objectEntriesCount == 0) {
			return;
		}

		User user = _userLocalService.getUser(userId);

		throw new ObjectEntryValuesException.UniqueValueConstraintViolation(
			objectField.getDBColumnName(), (Serializable)value,
			objectField.getLabel(user.getLocale()), table.getTableName(), null);
	}

	private void _validateValues(
			Map.Entry<String, Serializable> entry,
			ObjectEntry existingObjectEntry, boolean guestUser, long groupId,
			ObjectDefinition objectDefinition, long objectEntryId,
			ServiceContext serviceContext, long userId,
			Map<String, Serializable> values)
		throws PortalException {

		ObjectField objectField = null;

		try {
			objectField = _objectFieldLocalService.getObjectField(
				objectDefinition.getObjectDefinitionId(), entry.getKey());
		}
		catch (NoSuchObjectFieldException noSuchObjectFieldException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchObjectFieldException);
			}

			return;
		}

		if (Validator.isNull(values.get(objectField.getName())) &&
			objectField.isRequired() &&
			(serviceContext.getWorkflowAction() !=
				WorkflowConstants.ACTION_SAVE_DRAFT)) {

			throw new ObjectEntryValuesException.Required(
				objectField.getName());
		}
		else if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

			DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchDLFileEntry(
				GetterUtil.getLong(entry.getValue()));

			if (dlFileEntry != null) {
				_validateFileExtension(
					dlFileEntry.getExtension(), objectField.getObjectFieldId(),
					objectField.getName());
				_validateFileSize(
					guestUser, dlFileEntry.getSize(),
					objectField.getObjectFieldId(), objectField.getName());

				if (existingObjectEntry != null) {
					Map<String, Serializable> existingValues =
						existingObjectEntry.getValues();

					if (dlFileEntry.getFileEntryId() == GetterUtil.getLong(
							existingValues.get(entry.getKey()))) {

						return;
					}
				}

				_addFileEntry(
					dlFileEntry, entry, objectDefinition, objectEntryId,
					objectField.getObjectFieldId(),
					objectField.getObjectFieldSettings(), serviceContext,
					userId);

				return;
			}

			if (Validator.isNotNull(entry.getValue())) {
				throw new ObjectEntryValuesException.InvalidValue(
					objectField.getName());
			}
			else if (objectField.isRequired() &&
					 (serviceContext.getWorkflowAction() !=
						 WorkflowConstants.ACTION_SAVE_DRAFT)) {

				throw new ObjectEntryValuesException.Required(
					objectField.getName());
			}
		}
		else if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_BOOLEAN)) {

			if (!GetterUtil.getBoolean(entry.getValue()) &&
				objectField.isRequired()) {

				throw new ObjectEntryValuesException.Required(
					objectField.getName());
			}
		}
		else if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_ENCRYPTED)) {

			_validateTextMaxLength280(
				objectField, GetterUtil.getString(entry.getValue()));
		}
		else if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_LONG_TEXT)) {

			_validateTextMaxLength(
				65000, GetterUtil.getString(entry.getValue()),
				objectField.getObjectFieldId(), objectField.getName());
		}
		else if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_RELATIONSHIP)) {

			if (!objectDefinition.isAccountEntryRestricted() ||
				!Objects.equals(
					objectField.getObjectFieldId(),
					objectDefinition.
						getAccountEntryRestrictedObjectFieldId()) ||
				(existingObjectEntry == null)) {

				return;
			}
		}
		else if (StringUtil.equals(
					objectField.getDBType(),
					ObjectFieldConstants.DB_TYPE_INTEGER)) {

			String entryValueString = String.valueOf(entry.getValue());

			if (!entryValueString.isEmpty()) {
				int value = GetterUtil.getInteger(entryValueString);

				if ((value == 0) &&
					!StringUtil.equals(
						String.valueOf(value), entryValueString)) {

					throw new ObjectEntryValuesException.ExceedsIntegerSize(
						9, objectField.getName());
				}
			}
		}
		else if (StringUtil.equals(
					objectField.getDBType(),
					ObjectFieldConstants.DB_TYPE_LONG)) {

			String entryValueString = String.valueOf(entry.getValue());

			if (!entryValueString.isEmpty()) {
				long value = GetterUtil.getLong(entryValueString);

				if ((value == 0) &&
					!StringUtil.equals(
						String.valueOf(value), entryValueString)) {

					throw new ObjectEntryValuesException.ExceedsLongSize(
						16, objectField.getName());
				}
				else if (value > ObjectFieldValidationConstants.
							BUSINESS_TYPE_LONG_VALUE_MAX) {

					throw new ObjectEntryValuesException.ExceedsLongMaxSize(
						ObjectFieldValidationConstants.
							BUSINESS_TYPE_LONG_VALUE_MAX,
						objectField.getName());
				}
				else if (value < ObjectFieldValidationConstants.
							BUSINESS_TYPE_LONG_VALUE_MIN) {

					throw new ObjectEntryValuesException.ExceedsLongMinSize(
						ObjectFieldValidationConstants.
							BUSINESS_TYPE_LONG_VALUE_MIN,
						objectField.getName());
				}
			}
		}
		else if (StringUtil.equals(
					objectField.getDBType(),
					ObjectFieldConstants.DB_TYPE_STRING)) {

			_validateTextMaxLength280(
				objectField, GetterUtil.getString(entry.getValue()));
		}

		if (objectField.getListTypeDefinitionId() != 0) {
			Serializable value = entry.getValue();

			if (objectField.compareBusinessType(
					ObjectFieldConstants.BUSINESS_TYPE_MULTISELECT_PICKLIST)) {

				List<String> listTypeEntryKeys = null;

				if (value instanceof List) {
					listTypeEntryKeys = (List<String>)value;
				}
				else {
					listTypeEntryKeys = ListUtil.fromString(
						GetterUtil.getString(String.valueOf(value)),
						StringPool.COMMA_AND_SPACE);
				}

				if (listTypeEntryKeys.isEmpty() && objectField.isRequired() &&
					(serviceContext.getWorkflowAction() !=
						WorkflowConstants.ACTION_SAVE_DRAFT)) {

					throw new ObjectEntryValuesException.Required(
						objectField.getName());
				}

				for (String listTypeEntryKey : listTypeEntryKeys) {
					_validateListTypeEntryKey(listTypeEntryKey, objectField);
				}
			}
			else {
				_validateListTypeEntryKey(String.valueOf(value), objectField);

				if ((existingObjectEntry != null) && objectField.isState()) {
					_validateObjectStateTransition(
						entry, objectField.getListTypeDefinitionId(),
						existingObjectEntry, objectField.getObjectFieldId(),
						userId);
				}
			}
		}

		if (!objectField.hasUniqueValues() || objectField.isLocalized()) {
			return;
		}

		_validateUniqueValues(
			groupId, existingObjectEntry, objectDefinition, objectField, userId,
			entry.getValue());
	}

	private void _validateValues(
			ObjectEntry existingObjectEntry, boolean guestUser, long groupId,
			ObjectDefinition objectDefinition, long objectEntryId,
			ServiceContext serviceContext, long userId,
			Map<String, Serializable> values)
		throws PortalException {

		List<ObjectField> objectFields =
			_objectFieldLocalService.getLocalizedObjectFields(
				objectDefinition.getObjectDefinitionId());

		for (ObjectField objectField : objectFields) {
			Map<String, String> localizedValues =
				(Map<String, String>)values.get(
					objectField.getI18nObjectFieldName());

			if (MapUtil.isEmpty(localizedValues)) {
				continue;
			}

			for (Map.Entry<String, String> entry : localizedValues.entrySet()) {
				if (objectField.compareBusinessType(
						ObjectFieldConstants.BUSINESS_TYPE_LONG_TEXT)) {

					_validateTextMaxLength(
						65000, entry.getValue(), objectField.getObjectFieldId(),
						objectField.getI18nObjectFieldName());
				}
				else if (objectField.compareBusinessType(
							ObjectFieldConstants.BUSINESS_TYPE_TEXT)) {

					_validateTextMaxLength(
						280, entry.getValue(), objectField.getObjectFieldId(),
						objectField.getI18nObjectFieldName());
				}

				if (!objectField.hasUniqueValues()) {
					continue;
				}

				_validateUniqueValues(
					groupId, existingObjectEntry, objectDefinition, objectField,
					userId, entry);
			}
		}

		for (Map.Entry<String, Serializable> entry : values.entrySet()) {
			_validateValues(
				entry, existingObjectEntry, guestUser, groupId,
				objectDefinition, objectEntryId, serviceContext, userId,
				values);
		}
	}

	private void _validateWorkflowAction(
			boolean enableObjectEntryDraft, Integer status,
			Integer workflowAction)
		throws PortalException {

		if ((!enableObjectEntryDraft ||
			 ((status != null) &&
			  (status != WorkflowConstants.STATUS_DRAFT))) &&
			(workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

			throw new ObjectEntryStatusException("Draft status is not allowed");
		}
	}

	private static final Expression<?>[] _EXPRESSIONS = {
		ObjectEntryTable.INSTANCE.objectEntryId,
		ObjectEntryTable.INSTANCE.userName,
		ObjectEntryTable.INSTANCE.createDate,
		ObjectEntryTable.INSTANCE.modifiedDate,
		ObjectEntryTable.INSTANCE.externalReferenceCode,
		ObjectEntryTable.INSTANCE.status
	};

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryLocalServiceImpl.class);

	private static final Snapshot<ObjectActionEngine>
		_objectActionEngineSnapshot = new Snapshot<>(
			ObjectEntryLocalServiceImpl.class, ObjectActionEngine.class, null);
	private static final Snapshot<ObjectRelationshipLocalService>
		_objectRelationshipLocalServiceSnapshot = new Snapshot<>(
			ObjectEntryLocalServiceImpl.class,
			ObjectRelationshipLocalService.class, null);
	private static final ThreadLocal<Boolean> _skipModelListeners =
		new CentralizedThreadLocal<>(
			ObjectEntryLocalServiceImpl.class + "._skipModelListeners",
			() -> false);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private AttachmentManager _attachmentManager;

	@Reference
	private CurrentConnection _currentConnection;

	@Reference
	private DDMExpressionFactory _ddmExpressionFactory;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private Encryptor _encryptor;

	@Reference(
		target = "(filter.factory.key=" + ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT + ")"
	)
	private FilterFactory<Predicate> _filterFactory;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private InlineSQLHelper _inlineSQLHelper;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private ListTypeEntryLocalService _listTypeEntryLocalService;

	@Reference
	private Localization _localization;

	private volatile ObjectConfiguration _objectConfiguration;

	@Reference
	private ObjectDefinitionPersistence _objectDefinitionPersistence;

	@Reference
	private ObjectFieldBusinessTypeRegistry _objectFieldBusinessTypeRegistry;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectFieldPersistence _objectFieldPersistence;

	@Reference
	private ObjectFieldSettingLocalService _objectFieldSettingLocalService;

	@Reference
	private ObjectFieldSettingPersistence _objectFieldSettingPersistence;

	private final Map<String, ObjectFilterParser> _objectFilterParsers =
		new HashMap<>();

	@Reference
	private ObjectRelatedModelsProviderRegistry
		_objectRelatedModelsProviderRegistry;

	@Reference
	private ObjectRelationshipPersistence _objectRelationshipPersistence;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private ObjectStateFlowLocalService _objectStateFlowLocalService;

	@Reference
	private ObjectStateLocalService _objectStateLocalService;

	@Reference
	private PermissionService _permissionService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private Searcher _searcher;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	private ServiceTrackerList<ObjectEntryValuesContributor>
		_serviceTrackerList;

	@Reference
	private Sorts _sorts;

	@Reference
	private SystemObjectDefinitionManagerRegistry
		_systemObjectDefinitionManagerRegistry;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

}