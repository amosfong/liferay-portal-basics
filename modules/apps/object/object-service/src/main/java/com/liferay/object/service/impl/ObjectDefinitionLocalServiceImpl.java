/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service.impl;

import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.fragment.cache.FragmentEntryLinkCache;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.definition.util.ObjectDefinitionThreadLocal;
import com.liferay.object.definition.util.ObjectDefinitionUtil;
import com.liferay.object.deployer.InactiveObjectDefinitionDeployer;
import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.exception.NoSuchObjectFieldException;
import com.liferay.object.exception.ObjectDefinitionAccountEntryRestrictedException;
import com.liferay.object.exception.ObjectDefinitionAccountEntryRestrictedObjectFieldIdException;
import com.liferay.object.exception.ObjectDefinitionActiveException;
import com.liferay.object.exception.ObjectDefinitionClassNameException;
import com.liferay.object.exception.ObjectDefinitionEnableCategorizationException;
import com.liferay.object.exception.ObjectDefinitionEnableCommentsException;
import com.liferay.object.exception.ObjectDefinitionEnableLocalizationException;
import com.liferay.object.exception.ObjectDefinitionEnableObjectEntryHistoryException;
import com.liferay.object.exception.ObjectDefinitionExternalReferenceCodeException;
import com.liferay.object.exception.ObjectDefinitionLabelException;
import com.liferay.object.exception.ObjectDefinitionModifiableException;
import com.liferay.object.exception.ObjectDefinitionNameException;
import com.liferay.object.exception.ObjectDefinitionPanelCategoryKeyException;
import com.liferay.object.exception.ObjectDefinitionPluralLabelException;
import com.liferay.object.exception.ObjectDefinitionPortletException;
import com.liferay.object.exception.ObjectDefinitionRootObjectDefinitionIdException;
import com.liferay.object.exception.ObjectDefinitionScopeException;
import com.liferay.object.exception.ObjectDefinitionStatusException;
import com.liferay.object.exception.ObjectDefinitionSystemException;
import com.liferay.object.exception.ObjectDefinitionVersionException;
import com.liferay.object.exception.ObjectFieldRelationshipTypeException;
import com.liferay.object.exception.ObjectRelationshipEdgeException;
import com.liferay.object.exception.RequiredObjectDefinitionException;
import com.liferay.object.field.util.ObjectFieldUtil;
import com.liferay.object.internal.dao.db.ObjectDBManagerUtil;
import com.liferay.object.internal.deployer.InactiveObjectDefinitionDeployerImpl;
import com.liferay.object.internal.deployer.ObjectDefinitionDeployerImpl;
import com.liferay.object.internal.security.permission.resource.util.ObjectDefinitionResourcePermissionUtil;
import com.liferay.object.model.ObjectAction;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryTable;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFieldModel;
import com.liferay.object.model.ObjectFolder;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.model.impl.ObjectDefinitionImpl;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionLocalizationTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionLocalizationTableFactory;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTableFactory;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistrarHelper;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFolderItemLocalService;
import com.liferay.object.service.ObjectFolderLocalService;
import com.liferay.object.service.ObjectLayoutLocalService;
import com.liferay.object.service.ObjectLayoutTabLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectValidationRuleLocalService;
import com.liferay.object.service.ObjectViewLocalService;
import com.liferay.object.service.base.ObjectDefinitionLocalServiceBaseImpl;
import com.liferay.object.service.persistence.ObjectEntryPersistence;
import com.liferay.object.service.persistence.ObjectFieldPersistence;
import com.liferay.object.service.persistence.ObjectFolderPersistence;
import com.liferay.object.service.persistence.ObjectRelationshipPersistence;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.tree.Node;
import com.liferay.object.tree.ObjectDefinitionTreeFactory;
import com.liferay.object.tree.Tree;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.dao.jdbc.CurrentConnection;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dependency.manager.DependencyManagerSyncUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mass.delete.MassDeleteCacheThreadLocal;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.language.override.service.PLOEntryLocalService;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.localization.SearchLocalizationHelper;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.sql.PreparedStatement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Marco Leo
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.object.model.ObjectDefinition",
	service = AopService.class
)
public class ObjectDefinitionLocalServiceImpl
	extends ObjectDefinitionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition addCustomObjectDefinition(
			long userId, long objectFolderId, String className,
			boolean enableComments, boolean enableIndexSearch,
			boolean enableLocalization, boolean enableObjectEntryDraft,
			Map<Locale, String> labelMap, String name, String panelAppOrder,
			String panelCategoryKey, Map<Locale, String> pluralLabelMap,
			boolean portlet, String scope, String storageType,
			List<ObjectField> objectFields)
		throws PortalException {

		return _addObjectDefinition(
			null, userId, objectFolderId, className, null, enableComments,
			enableIndexSearch, enableLocalization, enableObjectEntryDraft,
			labelMap, true, name, panelAppOrder, panelCategoryKey, null, null,
			pluralLabelMap, portlet, scope, storageType, false, null, 0,
			WorkflowConstants.STATUS_DRAFT, objectFields);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition addObjectDefinition(
			String externalReferenceCode, long userId, long objectFolderId,
			boolean modifiable, boolean system)
		throws PortalException {

		_validateExternalReferenceCode(externalReferenceCode, system);

		ObjectDefinition objectDefinition = objectDefinitionPersistence.create(
			counterLocalService.increment());

		objectDefinition.setExternalReferenceCode(externalReferenceCode);

		User user = _userLocalService.getUser(userId);

		objectDefinition.setCompanyId(user.getCompanyId());
		objectDefinition.setUserId(user.getUserId());
		objectDefinition.setUserName(user.getFullName());
		objectDefinition.setObjectFolderId(
			_getObjectFolderId(user.getCompanyId(), objectFolderId));

		objectDefinition.setActive(false);
		objectDefinition.setLabel(externalReferenceCode);
		objectDefinition.setModifiable(modifiable);
		objectDefinition.setName(externalReferenceCode);
		objectDefinition.setPluralLabel(externalReferenceCode);
		objectDefinition.setScope(ObjectDefinitionConstants.SCOPE_COMPANY);
		objectDefinition.setStorageType(
			ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT);
		objectDefinition.setSystem(system);
		objectDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);

		if (objectDefinition.isUnmodifiableSystemObject() || !modifiable) {
			throw new ObjectDefinitionModifiableException.MustBeModifiable();
		}

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		_addOrUpdateObjectDefinitionPLOEntries(objectDefinition);

		_resourceLocalService.addResources(
			objectDefinition.getCompanyId(), 0, objectDefinition.getUserId(),
			ObjectDefinition.class.getName(),
			objectDefinition.getObjectDefinitionId(), false, true, true);

		_objectFolderItemLocalService.addObjectFolderItem(
			userId, objectDefinition.getObjectDefinitionId(),
			objectDefinition.getObjectFolderId(), 0, 0);

		_addSystemObjectFields(
			ObjectEntryTable.INSTANCE.getTableName(), objectDefinition,
			ObjectEntryTable.INSTANCE.objectEntryId.getName(), userId);

		return _updateTitleObjectFieldId(objectDefinition, null);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition addOrUpdateSystemObjectDefinition(
			long companyId, long objectFolderId,
			SystemObjectDefinitionManager systemObjectDefinitionManager)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.fetchByC_N(
				companyId, systemObjectDefinitionManager.getName());

		long userId = _userLocalService.getGuestUserId(companyId);

		if (objectDefinition == null) {
			Table table = systemObjectDefinitionManager.getTable();
			Column<?, Long> primaryKeyColumn =
				systemObjectDefinitionManager.getPrimaryKeyColumn();

			objectDefinition = addSystemObjectDefinition(
				systemObjectDefinitionManager.getExternalReferenceCode(),
				userId, objectFolderId,
				systemObjectDefinitionManager.getModelClassName(),
				table.getTableName(), false, true, false,
				systemObjectDefinitionManager.getLabelMap(), false,
				systemObjectDefinitionManager.getName(), null, null,
				primaryKeyColumn.getName(), primaryKeyColumn.getName(),
				systemObjectDefinitionManager.getPluralLabelMap(), false,
				systemObjectDefinitionManager.getScope(),
				systemObjectDefinitionManager.getTitleObjectFieldName(),
				systemObjectDefinitionManager.getVersion(),
				WorkflowConstants.STATUS_APPROVED,
				systemObjectDefinitionManager.getObjectFields());

			_addOrUpdateObjectActions(
				userId, objectDefinition.getObjectDefinitionId(),
				systemObjectDefinitionManager.getObjectActions());

			return objectDefinition;
		}

		objectDefinition.setObjectFolderId(objectFolderId);
		objectDefinition.setLabelMap(
			systemObjectDefinitionManager.getLabelMap());
		objectDefinition.setPluralLabelMap(
			systemObjectDefinitionManager.getPluralLabelMap());
		objectDefinition.setVersion(systemObjectDefinitionManager.getVersion());

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		List<ObjectField> newObjectFields =
			systemObjectDefinitionManager.getObjectFields();

		List<ObjectField> oldObjectFields =
			_objectFieldPersistence.findByODI_DTN(
				objectDefinition.getObjectDefinitionId(),
				objectDefinition.getDBTableName());

		for (ObjectField oldObjectField : oldObjectFields) {
			if (oldObjectField.isSystem() &&
				!ObjectFieldUtil.isMetadata(oldObjectField.getName()) &&
				!_hasObjectField(newObjectFields, oldObjectField)) {

				_objectFieldPersistence.remove(oldObjectField);
			}
		}

		for (ObjectField newObjectField : newObjectFields) {
			ObjectField oldObjectField = _objectFieldPersistence.fetchByODI_N(
				objectDefinition.getObjectDefinitionId(),
				newObjectField.getName());

			if (oldObjectField == null) {
				_objectFieldLocalService.addSystemObjectField(
					newObjectField.getExternalReferenceCode(), userId,
					newObjectField.getListTypeDefinitionId(),
					objectDefinition.getObjectDefinitionId(),
					newObjectField.getBusinessType(),
					newObjectField.getDBColumnName(),
					objectDefinition.getDBTableName(),
					newObjectField.getDBType(), false, false, "",
					newObjectField.getLabelMap(), newObjectField.isLocalized(),
					newObjectField.getName(), newObjectField.getReadOnly(),
					newObjectField.getReadOnlyConditionExpression(),
					newObjectField.isRequired(), newObjectField.isState(),
					newObjectField.getObjectFieldSettings());
			}
			else {
				if (!Objects.equals(
						oldObjectField.getDBType(),
						newObjectField.getDBType()) ||
					!Objects.equals(
						oldObjectField.isRequired(),
						newObjectField.isRequired())) {

					oldObjectField.setBusinessType(
						newObjectField.getBusinessType());
					oldObjectField.setDBType(newObjectField.getDBType());
					oldObjectField.setRequired(newObjectField.isRequired());

					_objectFieldPersistence.update(oldObjectField);
				}
			}
		}

		_addOrUpdateObjectActions(
			userId, objectDefinition.getObjectDefinitionId(),
			systemObjectDefinitionManager.getObjectActions());

		return objectDefinition;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition addSystemObjectDefinition(
			String externalReferenceCode, long userId, long objectFolderId,
			String className, String dbTableName, boolean enableComments,
			boolean enableIndexSearch, boolean enableLocalization,
			Map<Locale, String> labelMap, boolean modifiable, String name,
			String panelAppOrder, String panelCategoryKey,
			String pkObjectFieldDBColumnName, String pkObjectFieldName,
			Map<Locale, String> pluralLabelMap, boolean portlet, String scope,
			String titleObjectFieldName, int version, int status,
			List<ObjectField> objectFields)
		throws PortalException {

		return _addObjectDefinition(
			externalReferenceCode, userId, objectFolderId, className,
			dbTableName, enableComments, enableIndexSearch, enableLocalization,
			false, labelMap, modifiable, name, panelAppOrder, panelCategoryKey,
			pkObjectFieldDBColumnName, pkObjectFieldName, pluralLabelMap,
			portlet, scope, ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT,
			true, titleObjectFieldName, version, status, objectFields);
	}

	@Override
	public void deleteCompanyObjectDefinitions(long companyId)
		throws PortalException {

		List<ObjectDefinition> objectDefinitions =
			objectDefinitionPersistence.findByCompanyId(companyId);

		for (ObjectDefinition objectDefinition : objectDefinitions) {
			objectDefinitionLocalService.deleteObjectDefinition(
				objectDefinition);
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public ObjectDefinition deleteObjectDefinition(long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		return deleteObjectDefinition(objectDefinition);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public ObjectDefinition deleteObjectDefinition(
			ObjectDefinition objectDefinition)
		throws PortalException {

		if (!PortalInstances.isCurrentCompanyInDeletionProcess() &&
			!PortalRunMode.isTestMode() &&
			objectDefinition.isUnmodifiableSystemObject()) {

			throw new RequiredObjectDefinitionException();
		}

		if (objectDefinition.getRootObjectDefinitionId() != 0) {
			throw new ObjectDefinitionRootObjectDefinitionIdException(
				"To delete this object, you must first disable inheritance " +
					"and delete its relationships",
				"to-delete-this-object-you-must-first-disable-inheritance-" +
					"and-delete-its-relationships");
		}

		if (objectDefinition.isSystem() &&
			!ObjectDefinitionUtil.isInvokerBundleAllowed()) {

			throw new ObjectDefinitionSystemException(
				"Only allowed bundles can delete system object definitions");
		}

		_objectActionLocalService.deleteObjectActions(
			objectDefinition.getObjectDefinitionId());

		if (!objectDefinition.isUnmodifiableSystemObject()) {
			_deleteObjectDefinitionPLOEntries(objectDefinition);

			ActionableDynamicQuery actionableDynamicQuery =
				new DefaultActionableDynamicQuery() {

					@Override
					protected void intervalCompleted(
							long startPrimaryKey, long endPrimaryKey)
						throws PortalException {

						Session portletSession =
							_objectEntryPersistence.openSession();

						portletSession.flush();

						portletSession.clear();

						Session portalSession =
							_resourcePermissionPersistence.openSession();

						portalSession.flush();

						portalSession.clear();
					}

				};

			actionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property nameProperty = PropertyFactoryUtil.forName(
						"objectDefinitionId");

					dynamicQuery.add(
						nameProperty.eq(
							objectDefinition.getObjectDefinitionId()));
				});
			actionableDynamicQuery.setBaseLocalService(
				_objectEntryLocalService);
			actionableDynamicQuery.setClassLoader(getClassLoader());
			actionableDynamicQuery.setModelClass(ObjectEntry.class);

			AtomicBoolean deletedMarker = new AtomicBoolean();

			actionableDynamicQuery.setPerformActionMethod(
				(ObjectEntry objectEntry) -> {
					deletedMarker.set(true);

					_objectEntryLocalService.deleteObjectEntry(objectEntry);
				});

			actionableDynamicQuery.setPrimaryKeyPropertyName("objectEntryId");

			try (SafeCloseable safeCloseable =
					ObjectDefinitionThreadLocal.
						setDeleteObjectDefinitionIdWithSafeCloseable(
							objectDefinition.getObjectDefinitionId())) {

				actionableDynamicQuery.performActions();

				if (deletedMarker.get()) {
					_resourcePermissionLocalService.deleteResourcePermissions(
						objectDefinition.getCompanyId(),
						objectDefinition.getClassName(),
						ResourceConstants.SCOPE_INDIVIDUAL);

					_assetEntryLocalService.deleteEntries(
						objectDefinition.getCompanyId(),
						objectDefinition.getClassName());

					_deleteFromTable(objectDefinition.getDBTableName());

					_deleteFromTable(
						objectDefinition.getExtensionDBTableName());

					if (objectDefinition.isEnableLocalization()) {
						_deleteFromTable(
							objectDefinition.getLocalizationDBTableName());
					}
				}
			}
		}

		for (ObjectRelationship objectRelationship :
				_objectRelationshipPersistence.findByODI1_R(
					objectDefinition.getObjectDefinitionId(), false)) {

			_objectRelationshipLocalService.deleteObjectRelationship(
				objectRelationship);
		}

		for (ObjectRelationship objectRelationship :
				_objectRelationshipPersistence.findByODI2_R(
					objectDefinition.getObjectDefinitionId(), false)) {

			_objectRelationshipLocalService.deleteObjectRelationship(
				objectRelationship);
		}

		_objectFieldLocalService.deleteObjectFieldByObjectDefinitionId(
			objectDefinition.getObjectDefinitionId());

		_objectFolderItemLocalService.
			deleteObjectFolderItemByObjectDefinitionId(
				objectDefinition.getObjectDefinitionId());

		_objectLayoutLocalService.deleteObjectLayouts(
			objectDefinition.getObjectDefinitionId());

		_objectValidationRuleLocalService.deleteObjectValidationRules(
			objectDefinition.getObjectDefinitionId());

		_objectViewLocalService.deleteObjectViews(
			objectDefinition.getObjectDefinitionId());

		_resourceLocalService.deleteResource(
			objectDefinition.getCompanyId(), ObjectDefinition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			objectDefinition.getObjectDefinitionId());

		if (objectDefinition.isUnmodifiableSystemObject()) {
			_dropTable(objectDefinition.getExtensionDBTableName());
		}
		else if (objectDefinition.isApproved()) {
			try (SafeCloseable safeCloseable = CompanyThreadLocal.lock(
					objectDefinition.getCompanyId())) {

				ObjectDefinitionResourcePermissionUtil.removeResourceActions(
					_objectActionLocalService, objectDefinition,
					objectDefinitionPersistence,
					new ObjectDefinitionTreeFactory(
						objectDefinitionLocalService,
						_objectRelationshipLocalService),
					_resourceActions);
			}
			catch (Exception exception) {
				throw new PortalException(exception);
			}

			_dropTable(objectDefinition.getDBTableName());
			_dropTable(objectDefinition.getExtensionDBTableName());

			if (objectDefinition.isEnableLocalization()) {
				_dropTable(objectDefinition.getLocalizationDBTableName());
			}

			undeployObjectDefinition(objectDefinition);

			_registerTransactionCallbackForCluster(
				_undeployObjectDefinitionMethodKey, objectDefinition);
		}

		objectDefinitionPersistence.remove(objectDefinition);

		Set<String> names = MassDeleteCacheThreadLocal.getMassDeleteCache(
			ResourcePermissionLocalService.class.getName(), HashSet::new);

		if (names != null) {
			for (String name : names) {
				_resourcePermissionLocalService.deleteResourcePermissions(name);
			}
		}

		return objectDefinition;
	}

	@Override
	public void deployInactiveObjectDefinition(
		ObjectDefinition objectDefinition) {

		undeployObjectDefinition(objectDefinition);

		for (Map.Entry
				<InactiveObjectDefinitionDeployer,
				 Map<Long, List<ServiceRegistration<?>>>> entry :
					_inactiveObjectDefinitionsServiceRegistrationsMaps.
						entrySet()) {

			InactiveObjectDefinitionDeployer inactiveObjectDefinitionDeployer =
				entry.getKey();
			Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
				entry.getValue();

			try (SafeCloseable safeCloseable =
					CompanyThreadLocal.setCompanyIdWithSafeCloseable(
						objectDefinition.getCompanyId())) {

				serviceRegistrationsMap.computeIfAbsent(
					objectDefinition.getObjectDefinitionId(),
					objectDefinitionId ->
						inactiveObjectDefinitionDeployer.deploy(
							objectDefinition));
			}
		}
	}

	@Override
	public void deployObjectDefinition(ObjectDefinition objectDefinition) {
		undeployObjectDefinition(objectDefinition);

		for (Map.Entry
				<ObjectDefinitionDeployer,
				 Map<Long, List<ServiceRegistration<?>>>> entry :
					_serviceRegistrationsMaps.entrySet()) {

			ObjectDefinitionDeployer objectDefinitionDeployer = entry.getKey();
			Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
				entry.getValue();

			try (SafeCloseable safeCloseable = CompanyThreadLocal.lock(
					objectDefinition.getCompanyId())) {

				serviceRegistrationsMap.computeIfAbsent(
					objectDefinition.getObjectDefinitionId(),
					objectDefinitionId -> objectDefinitionDeployer.deploy(
						objectDefinition));
			}
		}
	}

	@Override
	public ObjectDefinition enableAccountEntryRestricted(
			ObjectRelationship objectRelationship)
		throws PortalException {

		ObjectDefinition objectDefinition1 = getObjectDefinition(
			objectRelationship.getObjectDefinitionId1());

		if (!Objects.equals(objectDefinition1.getShortName(), "AccountEntry")) {
			throw new ObjectDefinitionAccountEntryRestrictedException(
				"Custom object definitions can only be restricted by account " +
					"entry");
		}

		ObjectDefinition objectDefinition2 = getObjectDefinition(
			objectRelationship.getObjectDefinitionId2());

		if (objectDefinition2.isAccountEntryRestricted()) {
			return objectDefinition2;
		}

		objectDefinition2.setAccountEntryRestrictedObjectFieldId(
			objectRelationship.getObjectFieldId2());

		objectDefinition2.setAccountEntryRestricted(true);

		return objectDefinitionPersistence.update(objectDefinition2);
	}

	@Override
	public ObjectDefinition
			enableAccountEntryRestrictedForNondefaultStorageType(
				ObjectField objectField)
		throws PortalException {

		if (!objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_INTEGER) &&
			!objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_LONG_INTEGER) &&
			!objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_TEXT)) {

			throw new ObjectDefinitionAccountEntryRestrictedException(
				"Custom object definitions can only be restricted by an " +
					"integer, long integer, or text field");
		}

		ObjectDefinition objectDefinition = getObjectDefinition(
			objectField.getObjectDefinitionId());

		if (objectDefinition.isDefaultStorageType()) {
			throw new UnsupportedOperationException();
		}

		objectDefinition.setAccountEntryRestrictedObjectFieldId(
			objectField.getObjectFieldId());
		objectDefinition.setAccountEntryRestricted(true);

		return objectDefinitionPersistence.update(objectDefinition);
	}

	@Override
	public ObjectDefinition fetchObjectDefinition(long companyId, String name) {
		return objectDefinitionPersistence.fetchByC_N(companyId, name);
	}

	@Override
	public ObjectDefinition fetchObjectDefinitionByClassName(
		long companyId, String className) {

		return objectDefinitionPersistence.fetchByC_C(companyId, className);
	}

	@Override
	public ObjectDefinition fetchSystemObjectDefinition(String name) {
		for (ObjectDefinition systemObjectDefinition :
				getSystemObjectDefinitions()) {

			if (Objects.equals(systemObjectDefinition.getName(), name)) {
				return systemObjectDefinition;
			}
		}

		return null;
	}

	@Override
	public List<ObjectDefinition> getBoundObjectDefinitions(
		long companyId, long rootObjectDefinitionId) {

		return objectDefinitionPersistence.findByC_RODI(
			companyId, rootObjectDefinitionId);
	}

	@Override
	public List<ObjectDefinition> getCustomObjectDefinitions(int status) {
		return objectDefinitionPersistence.findByS_S(false, status);
	}

	@Override
	public ObjectDefinition getObjectDefinition(long objectDefinitionId)
		throws PortalException {

		return objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);
	}

	@Override
	public ObjectDefinition getObjectDefinition(long companyId, String name)
		throws PortalException {

		return objectDefinitionPersistence.findByC_N(companyId, name);
	}

	@Override
	public List<ObjectDefinition> getObjectDefinitions(
		boolean accountEntryRestricted) {

		return objectDefinitionPersistence.findByAccountEntryRestricted(
			accountEntryRestricted);
	}

	@Override
	public List<ObjectDefinition> getObjectDefinitions(
		long companyId, boolean active, boolean system, int status) {

		return objectDefinitionPersistence.findByC_A_S_S(
			companyId, active, system, status);
	}

	@Override
	public List<ObjectDefinition> getObjectDefinitions(
		long companyId, boolean active, int status) {

		return objectDefinitionPersistence.findByC_A_S(
			companyId, active, status);
	}

	@Override
	public List<ObjectDefinition> getObjectDefinitions(
		long companyId, int status) {

		return objectDefinitionPersistence.findByC_S(companyId, status);
	}

	@Override
	public int getObjectDefinitionsCount(long companyId)
		throws PortalException {

		return objectDefinitionPersistence.countByCompanyId(companyId);
	}

	@Override
	public List<ObjectDefinition> getObjectFolderObjectDefinitions(
		long objectFolderId) {

		return objectDefinitionPersistence.findByObjectFolderId(objectFolderId);
	}

	@Override
	public int getObjectFolderObjectDefinitionsCount(long objectFolderId)
		throws PortalException {

		return objectDefinitionPersistence.countByObjectFolderId(
			objectFolderId);
	}

	@Override
	public List<ObjectDefinition> getSystemObjectDefinitions() {
		return objectDefinitionPersistence.findBySystem(true);
	}

	@Override
	public List<ObjectDefinition> getUnmodifiableSystemObjectDefinitions(
		long companyId) {

		return objectDefinitionPersistence.findByC_M_S(companyId, false, true);
	}

	@Override
	public boolean hasObjectRelationship(long objectDefinitionId) {
		int countByObjectDefinitionId1 =
			_objectRelationshipPersistence.countByObjectDefinitionId1(
				objectDefinitionId);
		int countByObjectDefinitionId2 =
			_objectRelationshipPersistence.countByObjectDefinitionId2(
				objectDefinitionId);

		if ((countByObjectDefinitionId1 > 0) ||
			(countByObjectDefinitionId2 > 0)) {

			return true;
		}

		return false;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition publishCustomObjectDefinition(
			long userId, long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (objectDefinition.isUnmodifiableSystemObject()) {
			throw new ObjectDefinitionStatusException(
				"Unmodifiable system object definition cannot be published");
		}

		return _publishObjectDefinition(userId, objectDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition publishSystemObjectDefinition(
			long userId, long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		return _publishObjectDefinition(userId, objectDefinition);
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		super.setAopProxy(aopProxy);

		Map<Long, List<ServiceRegistration<?>>> activeServiceRegistrationsMap =
			new ConcurrentHashMap<>();
		InactiveObjectDefinitionDeployer inactiveObjectDefinitionDeployer =
			new InactiveObjectDefinitionDeployerImpl(
				_bundleContext, _objectEntryService, _objectFieldLocalService,
				_objectRelatedModelsProviderRegistrarHelper,
				_objectRelationshipLocalService);
		Map<Long, List<ServiceRegistration<?>>>
			inactiveServiceRegistrationsMap = new ConcurrentHashMap<>();
		ObjectDefinitionDeployer objectDefinitionDeployer =
			new ObjectDefinitionDeployerImpl(
				_accountEntryLocalService,
				_accountEntryOrganizationRelLocalService,
				_assetEntryLocalService, _bundleContext,
				_dynamicQueryBatchIndexingActionableFactory, _groupLocalService,
				_listTypeLocalService, _objectActionLocalService,
				objectDefinitionLocalService, _objectEntryLocalService,
				_objectEntryService, _objectFieldLocalService,
				_objectLayoutLocalService, _objectLayoutTabLocalService,
				_objectRelatedModelsProviderRegistrarHelper,
				_objectRelationshipLocalService, _objectScopeProviderRegistry,
				_objectViewLocalService, _organizationLocalService,
				_ploEntryLocalService, _portal, _portletLocalService,
				_resourceActions, _userLocalService,
				_resourcePermissionLocalService, _searchLocalizationHelper,
				_workflowStatusModelPreFilterContributor,
				_userGroupRoleLocalService);

		_companyLocalService.forEachCompanyId(
			companyId -> {
				for (ObjectDefinition objectDefinition :
						objectDefinitionLocalService.getObjectDefinitions(
							companyId, WorkflowConstants.STATUS_APPROVED)) {

					if (objectDefinition.isActive()) {
						activeServiceRegistrationsMap.put(
							objectDefinition.getObjectDefinitionId(),
							objectDefinitionDeployer.deploy(objectDefinition));
					}
					else {
						inactiveServiceRegistrationsMap.put(
							objectDefinition.getObjectDefinitionId(),
							inactiveObjectDefinitionDeployer.deploy(
								objectDefinition));
					}
				}
			});

		_inactiveObjectDefinitionsServiceRegistrationsMaps.put(
			inactiveObjectDefinitionDeployer, inactiveServiceRegistrationsMap);
		_serviceRegistrationsMaps.put(
			objectDefinitionDeployer, activeServiceRegistrationsMap);

		_objectDefinitionDeployerServiceTracker = new ServiceTracker<>(
			_bundleContext, ObjectDefinitionDeployer.class,
			new ServiceTrackerCustomizer
				<ObjectDefinitionDeployer, ObjectDefinitionDeployer>() {

				@Override
				public ObjectDefinitionDeployer addingService(
					ServiceReference<ObjectDefinitionDeployer>
						serviceReference) {

					return _addingObjectDefinitionDeployer(
						_bundleContext.getService(serviceReference));
				}

				@Override
				public void modifiedService(
					ServiceReference<ObjectDefinitionDeployer> serviceReference,
					ObjectDefinitionDeployer objectDefinitionDeployer) {
				}

				@Override
				public void removedService(
					ServiceReference<ObjectDefinitionDeployer> serviceReference,
					ObjectDefinitionDeployer objectDefinitionDeployer) {

					_companyLocalService.forEachCompanyId(
						companyId -> {
							for (ObjectDefinition objectDefinition :
									objectDefinitionLocalService.
										getObjectDefinitions(
											companyId,
											WorkflowConstants.
												STATUS_APPROVED)) {

								if (objectDefinition.isActive()) {
									objectDefinitionDeployer.undeploy(
										objectDefinition);
								}
							}
						});

					Map<Long, List<ServiceRegistration<?>>>
						serviceRegistrationsMap =
							_serviceRegistrationsMaps.remove(
								objectDefinitionDeployer);

					for (List<ServiceRegistration<?>> serviceRegistrations :
							serviceRegistrationsMap.values()) {

						for (ServiceRegistration<?> serviceRegistration :
								serviceRegistrations) {

							serviceRegistration.unregister();
						}
					}

					_bundleContext.ungetService(serviceReference);
				}

			});

		DependencyManagerSyncUtil.registerSyncCallable(
			() -> {
				_objectDefinitionDeployerServiceTracker.open();

				return null;
			});
	}

	@Override
	public void undeployObjectDefinition(ObjectDefinition objectDefinition) {
		if (objectDefinition.isUnmodifiableSystemObject()) {
			return;
		}

		for (Map.Entry
				<ObjectDefinitionDeployer,
				 Map<Long, List<ServiceRegistration<?>>>> entry :
					_serviceRegistrationsMaps.entrySet()) {

			ObjectDefinitionDeployer objectDefinitionDeployer = entry.getKey();

			objectDefinitionDeployer.undeploy(objectDefinition);

			Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
				entry.getValue();

			List<ServiceRegistration<?>> serviceRegistrations =
				serviceRegistrationsMap.remove(
					objectDefinition.getObjectDefinitionId());

			if (serviceRegistrations != null) {
				for (ServiceRegistration<?> serviceRegistration :
						serviceRegistrations) {

					serviceRegistration.unregister();
				}
			}
		}

		_invalidatePortalCache(objectDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateCustomObjectDefinition(
			String externalReferenceCode, long objectDefinitionId,
			long accountEntryRestrictedObjectFieldId,
			long descriptionObjectFieldId, long objectFolderId,
			long titleObjectFieldId, boolean accountEntryRestricted,
			boolean active, String className, boolean enableCategorization,
			boolean enableComments, boolean enableIndexSearch,
			boolean enableLocalization, boolean enableObjectEntryDraft,
			boolean enableObjectEntryHistory, Map<Locale, String> labelMap,
			String name, String panelAppOrder, String panelCategoryKey,
			boolean portlet, Map<Locale, String> pluralLabelMap, String scope,
			int status)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (objectDefinition.isUnmodifiableSystemObject()) {
			throw new ObjectDefinitionStatusException(
				"Object definition " + objectDefinition);
		}

		if (objectDefinition.isRootDescendantNode()) {
			String errorMessage =
				"cannot be changed when the object definition is a root " +
					"descendant node";

			if (!Objects.equals(
					objectDefinition.getAccountEntryRestrictedObjectFieldId(),
					accountEntryRestrictedObjectFieldId)) {

				throw new ObjectDefinitionAccountEntryRestrictedObjectFieldIdException(
					"Account entry restriction object field ID " +
						errorMessage);
			}
			else if (!Objects.equals(
						objectDefinition.isAccountEntryRestricted(),
						accountEntryRestricted)) {

				throw new ObjectDefinitionAccountEntryRestrictedException(
					"Account entry restriction " + errorMessage);
			}
			else if (!Objects.equals(
						objectDefinition.getPanelCategoryKey(),
						GetterUtil.getString(panelCategoryKey))) {

				throw new ObjectDefinitionPanelCategoryKeyException(
					"Panel category key " + errorMessage);
			}
			else if (!Objects.equals(objectDefinition.isPortlet(), portlet)) {
				throw new ObjectDefinitionPortletException(
					"Portlet " + errorMessage);
			}
			else if (!Objects.equals(objectDefinition.getScope(), scope)) {
				throw new ObjectDefinitionScopeException(
					"Scope " + errorMessage);
			}
		}

		return _updateObjectDefinition(
			externalReferenceCode, objectDefinition,
			accountEntryRestrictedObjectFieldId, descriptionObjectFieldId,
			objectFolderId, titleObjectFieldId, accountEntryRestricted, active,
			className, null, enableCategorization, enableComments,
			enableIndexSearch, enableLocalization, enableObjectEntryDraft,
			enableObjectEntryHistory, labelMap, name, panelAppOrder,
			panelCategoryKey, portlet, null, null, pluralLabelMap, scope,
			status);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateExternalReferenceCode(
			long objectDefinitionId, String externalReferenceCode)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		_validateExternalReferenceCode(
			externalReferenceCode, objectDefinition.isSystem());

		objectDefinition.setExternalReferenceCode(externalReferenceCode);

		return objectDefinitionPersistence.update(objectDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateObjectFolderId(
			long objectDefinitionId, long objectFolderId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		long oldObjectFolderId = objectDefinition.getObjectFolderId();

		objectDefinition.setObjectFolderId(
			_getObjectFolderId(
				objectDefinition.getCompanyId(), objectFolderId));

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		_objectFolderItemLocalService.updateObjectFolderObjectFolderItem(
			objectDefinitionId, objectDefinition.getObjectFolderId(),
			oldObjectFolderId);

		return objectDefinition;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updatePortlet(long objectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (objectDefinition.isPortlet() &&
			objectDefinition.isRootDescendantNode()) {

			objectDefinition.setPortlet(false);

			return objectDefinitionPersistence.update(objectDefinition);
		}

		return objectDefinition;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateRootObjectDefinitionId(
			long objectDefinitionId, long rootObjectDefinitionId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(objectDefinitionId);

		if (rootObjectDefinitionId == 0) {
			objectDefinition.setRootObjectDefinitionId(0);

			return objectDefinitionPersistence.update(objectDefinition);
		}

		ObjectDefinition rootObjectDefinition =
			objectDefinitionPersistence.findByPrimaryKey(
				rootObjectDefinitionId);

		if (!rootObjectDefinition.isRootNode() &&
			(objectDefinitionId != rootObjectDefinitionId)) {

			throw new ObjectDefinitionRootObjectDefinitionIdException(
				"Object definition " + rootObjectDefinitionId +
					" is not a root object definition");
		}

		objectDefinition.setRootObjectDefinitionId(rootObjectDefinitionId);

		return objectDefinitionPersistence.update(objectDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateSystemObjectDefinition(
			String externalReferenceCode, long objectDefinitionId,
			long objectFolderId, long titleObjectFieldId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.fetchByPrimaryKey(objectDefinitionId);

		_validateExternalReferenceCode(
			externalReferenceCode, objectDefinition.isSystem());
		_validateObjectFieldId(objectDefinition, titleObjectFieldId);

		long oldObjectFolderId = objectDefinition.getObjectFolderId();

		objectDefinition.setExternalReferenceCode(externalReferenceCode);
		objectDefinition.setObjectFolderId(
			_getObjectFolderId(
				objectDefinition.getCompanyId(), objectFolderId));
		objectDefinition.setTitleObjectFieldId(titleObjectFieldId);

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		_objectFolderItemLocalService.updateObjectFolderObjectFolderItem(
			objectDefinitionId, objectDefinition.getObjectFolderId(),
			oldObjectFolderId);

		return objectDefinition;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ObjectDefinition updateTitleObjectFieldId(
			long objectDefinitionId, long titleObjectFieldId)
		throws PortalException {

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.fetchByPrimaryKey(objectDefinitionId);

		_validateObjectFieldId(objectDefinition, titleObjectFieldId);

		objectDefinition.setTitleObjectFieldId(titleObjectFieldId);

		return objectDefinitionPersistence.update(objectDefinition);
	}

	@Override
	public void updateUserId(long companyId, long oldUserId, long newUserId)
		throws PortalException {

		for (ObjectDefinition objectDefinition :
				objectDefinitionPersistence.findByC_U(companyId, oldUserId)) {

			objectDefinition.setUserId(newUserId);

			objectDefinitionPersistence.update(objectDefinition);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Deactivate
	@Override
	protected void deactivate() {
		super.deactivate();

		if (_objectDefinitionDeployerServiceTracker != null) {
			_objectDefinitionDeployerServiceTracker.close();
		}
	}

	@Override
	protected void runSQL(String sql) {
		ObjectDBManagerUtil.runSQL(
			objectDefinitionPersistence.getDataSource(), _log, sql);
	}

	private ObjectDefinitionDeployer _addingObjectDefinitionDeployer(
		ObjectDefinitionDeployer objectDefinitionDeployer) {

		Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
			new ConcurrentHashMap<>();

		_companyLocalService.forEachCompanyId(
			companyId -> {
				for (ObjectDefinition objectDefinition :
						objectDefinitionLocalService.getObjectDefinitions(
							companyId, WorkflowConstants.STATUS_APPROVED)) {

					if (objectDefinition.isActive()) {
						serviceRegistrationsMap.put(
							objectDefinition.getObjectDefinitionId(),
							objectDefinitionDeployer.deploy(objectDefinition));
					}
				}
			});

		_serviceRegistrationsMaps.put(
			objectDefinitionDeployer, serviceRegistrationsMap);

		return objectDefinitionDeployer;
	}

	private ObjectDefinition _addObjectDefinition(
			String externalReferenceCode, long userId, long objectFolderId,
			String className, String dbTableName, boolean enableComments,
			boolean enableIndexSearch, boolean enableLocalization,
			boolean enableObjectEntryDraft, Map<Locale, String> labelMap,
			boolean modifiable, String name, String panelAppOrder,
			String panelCategoryKey, String pkObjectFieldDBColumnName,
			String pkObjectFieldName, Map<Locale, String> pluralLabelMap,
			boolean portlet, String scope, String storageType, boolean system,
			String titleObjectFieldName, int version, int status,
			List<ObjectField> objectFields)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		name = _getName(name, system);

		String shortName = ObjectDefinitionImpl.getShortName(name);

		dbTableName = _getDBTableName(
			dbTableName, modifiable, name, system, user.getCompanyId(),
			shortName);

		pkObjectFieldName = _getPKObjectFieldName(
			pkObjectFieldName, modifiable, system, shortName);

		pkObjectFieldDBColumnName = _getPKObjectFieldDBColumnName(
			pkObjectFieldDBColumnName, pkObjectFieldName, modifiable, system);

		storageType = Validator.isNotNull(storageType) ? storageType :
			ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT;

		_validateExternalReferenceCode(externalReferenceCode, system);
		_validateClassName(
			0, user.getCompanyId(), className, modifiable, system);
		_validateEnableComments(
			enableComments, modifiable, storageType, system);
		_validateLabel(labelMap);
		_validateName(0, user.getCompanyId(), modifiable, name, system);
		_validatePluralLabel(pluralLabelMap);
		_validateScope(scope, storageType);
		_validateVersion(system, version);

		ObjectDefinition objectDefinition = objectDefinitionPersistence.create(
			counterLocalService.increment());

		objectDefinition.setExternalReferenceCode(externalReferenceCode);
		objectDefinition.setCompanyId(user.getCompanyId());
		objectDefinition.setUserId(user.getUserId());
		objectDefinition.setUserName(user.getFullName());
		objectDefinition.setObjectFolderId(
			_getObjectFolderId(user.getCompanyId(), objectFolderId));
		objectDefinition.setActive(
			_isUnmodifiableSystemObject(modifiable, system));
		objectDefinition.setClassName(
			_getClassName(user.getCompanyId(), className, modifiable, system));
		objectDefinition.setDBTableName(dbTableName);
		objectDefinition.setEnableCategorization(
			!objectDefinition.isUnmodifiableSystemObject() &&
			StringUtil.equals(
				storageType, ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT));
		objectDefinition.setEnableComments(enableComments);
		objectDefinition.setEnableIndexSearch(enableIndexSearch);
		objectDefinition.setEnableLocalization(enableLocalization);
		objectDefinition.setEnableObjectEntryDraft(enableObjectEntryDraft);
		objectDefinition.setLabelMap(labelMap, LocaleUtil.getSiteDefault());
		objectDefinition.setModifiable(modifiable);
		objectDefinition.setName(name);
		objectDefinition.setPanelAppOrder(panelAppOrder);
		objectDefinition.setPanelCategoryKey(panelCategoryKey);
		objectDefinition.setPKObjectFieldDBColumnName(
			pkObjectFieldDBColumnName);
		objectDefinition.setPKObjectFieldName(pkObjectFieldName);
		objectDefinition.setPluralLabelMap(pluralLabelMap);
		objectDefinition.setPortlet(portlet);
		objectDefinition.setScope(scope);
		objectDefinition.setStorageType(
			Validator.isNotNull(storageType) ? storageType :
				ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT);
		objectDefinition.setSystem(system);
		objectDefinition.setVersion(version);
		objectDefinition.setStatus(status);

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		_resourceLocalService.addResources(
			objectDefinition.getCompanyId(), 0, objectDefinition.getUserId(),
			ObjectDefinition.class.getName(),
			objectDefinition.getObjectDefinitionId(), false, true, true);

		if (objectDefinition.isModifiable() ||
			!objectDefinition.isUnmodifiableSystemObject()) {

			_addOrUpdateObjectDefinitionPLOEntries(objectDefinition);

			dbTableName = "ObjectEntry";
		}

		_addSystemObjectFields(
			dbTableName, objectDefinition, pkObjectFieldName, userId);

		if (objectFields != null) {
			for (ObjectField objectField : objectFields) {
				if (objectDefinition.isUnmodifiableSystemObject() ||
					objectField.isSystem()) {

					_objectFieldLocalService.addOrUpdateSystemObjectField(
						objectField.getExternalReferenceCode(), userId,
						objectField.getListTypeDefinitionId(),
						objectDefinition.getObjectDefinitionId(),
						objectField.getBusinessType(),
						objectField.getDBColumnName(),
						objectDefinition.getDBTableName(),
						objectField.getDBType(), objectField.isIndexed(),
						objectField.isIndexedAsKeyword(),
						objectField.getIndexedLanguageId(),
						objectField.getLabelMap(), objectField.isLocalized(),
						objectField.getName(), objectField.getReadOnly(),
						objectField.getReadOnlyConditionExpression(),
						objectField.isRequired(), objectField.isState(),
						objectField.getObjectFieldSettings());
				}
				else {
					_objectFieldLocalService.addCustomObjectField(
						objectField.getExternalReferenceCode(), userId,
						objectField.getListTypeDefinitionId(),
						objectDefinition.getObjectDefinitionId(),
						objectField.getBusinessType(), objectField.getDBType(),
						objectField.isIndexed(),
						objectField.isIndexedAsKeyword(),
						objectField.getIndexedLanguageId(),
						objectField.getLabelMap(), objectField.isLocalized(),
						objectField.getName(), objectField.getReadOnly(),
						objectField.getReadOnlyConditionExpression(),
						objectField.isRequired(), objectField.isState(),
						objectField.getObjectFieldSettings());
				}
			}
		}

		_objectFolderItemLocalService.addObjectFolderItem(
			userId, objectDefinition.getObjectDefinitionId(),
			objectDefinition.getObjectFolderId(), 0, 0);

		objectDefinition = _updateTitleObjectFieldId(
			objectDefinition, titleObjectFieldName);

		if (objectDefinition.isUnmodifiableSystemObject()) {
			_createTable(
				DynamicObjectDefinitionTableFactory.createExtension(
					objectDefinition, _objectFieldLocalService));
		}

		return objectDefinition;
	}

	private void _addOrUpdateObjectActions(
			long userId, long objectDefinitionId,
			List<ObjectAction> objectActions)
		throws PortalException {

		for (ObjectAction objectAction : objectActions) {
			_objectActionLocalService.addOrUpdateObjectAction(
				objectAction.getExternalReferenceCode(), 0, userId,
				objectDefinitionId, objectAction.isActive(),
				objectAction.getConditionExpression(),
				objectAction.getDescription(),
				objectAction.getErrorMessageMap(), objectAction.getLabelMap(),
				objectAction.getName(),
				objectAction.getObjectActionExecutorKey(),
				objectAction.getObjectActionTriggerKey(),
				objectAction.getParametersUnicodeProperties(),
				objectAction.isSystem());
		}
	}

	private void _addOrUpdateObjectDefinitionPLOEntries(
			ObjectDefinition objectDefinition)
		throws PortalException {

		for (Locale locale : _language.getAvailableLocales()) {
			String languageId = LocaleUtil.toLanguageId(locale);

			_ploEntryLocalService.addOrUpdatePLOEntry(
				objectDefinition.getCompanyId(), objectDefinition.getUserId(),
				"model.resource." + objectDefinition.getClassName(), languageId,
				objectDefinition.getLabel(locale));
			_ploEntryLocalService.addOrUpdatePLOEntry(
				objectDefinition.getCompanyId(), objectDefinition.getUserId(),
				"model.resource." + objectDefinition.getResourceName(),
				languageId, objectDefinition.getPluralLabel(locale));
		}
	}

	private void _addSystemObjectFields(
			String dbTableName, ObjectDefinition objectDefinition,
			String pkObjectFieldName, long userId)
		throws PortalException {

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_TEXT,
			ObjectEntryTable.INSTANCE.userName.getName(), dbTableName,
			ObjectFieldConstants.DB_TYPE_STRING, false, false, null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(LocaleUtil.getDefault(), "author")),
			false, "creator", ObjectFieldConstants.READ_ONLY_FALSE, null, false,
			false, null);

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_DATE,
			ObjectEntryTable.INSTANCE.createDate.getName(), dbTableName,
			ObjectFieldConstants.DB_TYPE_DATE, false, false, null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(LocaleUtil.getDefault(), "create-date")),
			false, "createDate", ObjectFieldConstants.READ_ONLY_FALSE, null,
			false, false, null);

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_TEXT,
			ObjectEntryTable.INSTANCE.externalReferenceCode.getName(),
			dbTableName, ObjectFieldConstants.DB_TYPE_STRING, false, false,
			null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(
					LocaleUtil.getDefault(), "external-reference-code")),
			false, "externalReferenceCode",
			ObjectFieldConstants.READ_ONLY_FALSE, null, false, false, null);

		String dbColumnName = ObjectEntryTable.INSTANCE.objectEntryId.getName();

		if (objectDefinition.isUnmodifiableSystemObject()) {
			dbColumnName = pkObjectFieldName;
		}

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_LONG_INTEGER, dbColumnName,
			dbTableName, ObjectFieldConstants.DB_TYPE_LONG, true, true, null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(LocaleUtil.getDefault(), "id")),
			false, "id", ObjectFieldConstants.READ_ONLY_FALSE, null, false,
			false, null);

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_DATE,
			ObjectEntryTable.INSTANCE.modifiedDate.getName(), dbTableName,
			ObjectFieldConstants.DB_TYPE_DATE, false, false, null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(LocaleUtil.getDefault(), "modified-date")),
			false, "modifiedDate", ObjectFieldConstants.READ_ONLY_FALSE, null,
			false, false, null);

		_objectFieldLocalService.addSystemObjectField(
			null, userId, 0, objectDefinition.getObjectDefinitionId(),
			ObjectFieldConstants.BUSINESS_TYPE_TEXT,
			ObjectEntryTable.INSTANCE.status.getName(), dbTableName,
			ObjectFieldConstants.DB_TYPE_INTEGER, false, false, null,
			LocalizedMapUtil.getLocalizedMap(
				_language.get(LocaleUtil.getDefault(), "status")),
			false, "status", ObjectFieldConstants.READ_ONLY_FALSE, null, false,
			false, null);
	}

	private void _createLocalizationTable(
		DynamicObjectDefinitionLocalizationTable
			dynamicObjectDefinitionLocalizedTable) {

		if (dynamicObjectDefinitionLocalizedTable == null) {
			return;
		}

		runSQL(dynamicObjectDefinitionLocalizedTable.getCreateTableSQL());
	}

	private void _createTable(
			DynamicObjectDefinitionTable dynamicObjectDefinitionTable)
		throws PortalException {

		runSQL(dynamicObjectDefinitionTable.getCreateTableSQL());

		for (ObjectField objectField :
				dynamicObjectDefinitionTable.getObjectFields()) {

			if (!StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_RELATIONSHIP)) {

				continue;
			}

			ObjectDBManagerUtil.createIndexMetadata(
				_currentConnection.getConnection(
					objectDefinitionPersistence.getDataSource()),
				dynamicObjectDefinitionTable.getTableName(), false,
				objectField.getDBColumnName());
		}
	}

	private void _deleteFromTable(String dbTableName) throws PortalException {
		Session session = objectDefinitionPersistence.openSession();

		try {
			session.apply(
				connection -> {
					try (PreparedStatement preparedStatement =
							connection.prepareStatement(
								"delete from " + dbTableName)) {

						preparedStatement.executeUpdate();
					}
				});
		}
		finally {
			objectDefinitionPersistence.closeSession(session);
		}

		FinderCacheUtil.clearDSLQueryCache(dbTableName);
	}

	private void _deleteObjectDefinitionPLOEntries(
		ObjectDefinition objectDefinition) {

		_ploEntryLocalService.deletePLOEntries(
			objectDefinition.getCompanyId(),
			"model.resource." + objectDefinition.getResourceName());
		_ploEntryLocalService.deletePLOEntries(
			objectDefinition.getCompanyId(),
			"model.resource." + objectDefinition.getClassName());
	}

	private void _dropTable(String dbTableName) {
		runSQL("DROP_TABLE_IF_EXISTS(" + dbTableName + ")");
	}

	private String _getClassName(
		long companyId, String className, boolean modifiable, boolean system) {

		if (Validator.isNotNull(className) ||
			_isUnmodifiableSystemObject(modifiable, system)) {

			return className;
		}

		while (true) {
			StringBuilder sb = new StringBuilder();

			sb.append(
				ObjectDefinitionConstants.
					CLASS_NAME_PREFIX_CUSTOM_OBJECT_DEFINITION);
			sb.append(StringUtil.toUpperCase(StringUtil.randomId(1)));
			sb.append(RandomUtil.nextInt(10));
			sb.append(StringUtil.toUpperCase(StringUtil.randomId(1)));
			sb.append(RandomUtil.nextInt(10));

			ObjectDefinition existingObjectDefinition =
				objectDefinitionPersistence.fetchByC_C(
					companyId, sb.toString());

			if (existingObjectDefinition == null) {
				className = sb.toString();

				break;
			}
		}

		return className;
	}

	private String _getDBTableName(
		String dbTableName, boolean modifiable, String name, boolean system,
		Long companyId, String shortName) {

		if (Validator.isNotNull(dbTableName)) {
			return dbTableName;
		}

		if (_isUnmodifiableSystemObject(modifiable, system)) {
			return name;
		}

		// See DBInspector.java#isObjectTable

		String prefix = "O_";

		if (modifiable && system) {
			prefix =
				ObjectDefinitionConstants.
					EXTERNAL_REFERENCE_CODE_PREFIX_SYSTEM_OBJECT_DEFINITION;
		}

		return StringBundler.concat(
			prefix, companyId, StringPool.UNDERLINE, shortName);
	}

	private String _getName(String name, boolean system) {
		name = StringUtil.trim(name);

		if (!system) {
			name = "C_" + name;
		}

		return name;
	}

	private long _getObjectFolderId(long companyId, long objectFolderId)
		throws PortalException {

		if (objectFolderId == 0) {
			ObjectFolder objectFolder =
				_objectFolderLocalService.getDefaultObjectFolder(companyId);

			return objectFolder.getObjectFolderId();
		}

		_objectFolderPersistence.findByPrimaryKey(objectFolderId);

		return objectFolderId;
	}

	private String _getPKObjectFieldDBColumnName(
		String pkObjectFieldDBColumnName, String pkObjectFieldName,
		boolean modifiable, boolean system) {

		if (Validator.isNotNull(pkObjectFieldDBColumnName)) {
			return pkObjectFieldDBColumnName;
		}

		if (_isUnmodifiableSystemObject(modifiable, system)) {
			return pkObjectFieldName;
		}

		return pkObjectFieldName + StringPool.UNDERLINE;
	}

	private String _getPKObjectFieldName(
		String pkObjectFieldName, boolean modifiable, boolean system,
		String shortName) {

		if (Validator.isNotNull(pkObjectFieldName)) {
			return pkObjectFieldName;
		}

		pkObjectFieldName = TextFormatter.format(
			shortName + "Id", TextFormatter.I);

		if (_isUnmodifiableSystemObject(modifiable, system)) {
			return pkObjectFieldName;
		}

		if (modifiable && system) {
			String prefix =
				ObjectDefinitionConstants.
					EXTERNAL_REFERENCE_CODE_PREFIX_SYSTEM_OBJECT_DEFINITION;

			return StringUtil.toLowerCase(prefix) + pkObjectFieldName;
		}

		return "c_" + pkObjectFieldName;
	}

	private boolean _hasObjectField(
		List<ObjectField> newObjectFields, ObjectField oldObjectField) {

		for (ObjectField newObjectField : newObjectFields) {
			if (Objects.equals(
					newObjectField.getName(), oldObjectField.getName())) {

				return true;
			}
		}

		return false;
	}

	private void _invalidatePortalCache(ObjectDefinition objectDefinition) {
		List<LayoutClassedModelUsage> layoutClassedModelUsages =
			_layoutClassedModelUsageLocalService.getLayoutClassedModelUsages(
				objectDefinition.getCompanyId(),
				_classNameLocalService.getClassNameId(
					objectDefinition.getClassName()),
				_portal.getClassNameId(FragmentEntryLink.class));

		for (LayoutClassedModelUsage layoutClassedModelUsage :
				layoutClassedModelUsages) {

			_fragmentEntryLinkCache.removeFragmentEntryLinkCache(
				GetterUtil.getLong(layoutClassedModelUsage.getContainerKey()));
		}
	}

	private boolean _isUnmodifiableSystemObject(
		boolean modifiable, boolean system) {

		if (!modifiable && system) {
			return true;
		}

		return false;
	}

	private ObjectDefinition _publishObjectDefinition(
			long userId, ObjectDefinition objectDefinition)
		throws PortalException {

		if (objectDefinition.isApproved()) {
			throw new ObjectDefinitionStatusException(
				"The object definition is already published",
				"the-object-definition-is-already-published");
		}

		List<ObjectField> objectFields =
			_objectFieldPersistence.findByObjectDefinitionId(
				objectDefinition.getObjectDefinitionId());

		if (!objectDefinition.isEnableLocalization() &&
			ListUtil.exists(objectFields, ObjectFieldModel::isLocalized)) {

			throw new ObjectDefinitionEnableLocalizationException(
				"You cannot disable entry translation for the object " +
					"definition because translation is enabled for custom " +
						"fields",
				"you-cannot-disable-entry-translation-for-the-object-" +
					"definition-because-translation-is-enabled-for-custom-" +
						"fields");
		}

		if (!ListUtil.exists(
				objectFields, objectField -> !objectField.isMetadata())) {

			throw new ObjectDefinitionStatusException(
				"At least one object field must be added when publishing the " +
					"object definition",
				"at-least-one-object-field-must-be-added");
		}

		if ((objectDefinition.getStatus() == WorkflowConstants.STATUS_DRAFT) &&
			objectDefinition.isRootNode()) {

			for (ObjectRelationship objectRelationship :
					_objectRelationshipLocalService.getObjectRelationships(
						objectDefinition.getObjectDefinitionId())) {

				int objectEntriesCount =
					_objectEntryLocalService.getObjectEntriesCount(
						objectRelationship.getObjectDefinitionId2());

				if (objectEntriesCount > 0) {
					throw new ObjectRelationshipEdgeException(
						StringBundler.concat(
							"There must be no unrelated object entries when ",
							"both object definitions are published so that ",
							"the object relationship can be an edge to a root ",
							"context"),
						StringBundler.concat(
							"there-must-be-no-unrelated-object-entries-when-",
							"both-object-definitions-are-published-so-that-",
							"the-object-relationship-can-be-an-edge-to-a-root",
							"context"));
				}
			}
		}

		objectDefinition.setActive(true);

		if (objectDefinition.isRootDescendantNode()) {
			objectDefinition.setPanelCategoryKey(StringPool.BLANK);
		}

		objectDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		objectDefinition = _updateNodeObjectDefinition(objectDefinition);

		_updateDescendantNodeObjectDefinitions(objectDefinition);

		_createLocalizationTable(
			DynamicObjectDefinitionLocalizationTableFactory.create(
				objectDefinition, _objectFieldLocalService));
		_createTable(
			DynamicObjectDefinitionTableFactory.create(
				objectDefinition, _objectFieldLocalService));
		_createTable(
			DynamicObjectDefinitionTableFactory.createExtension(
				objectDefinition, _objectFieldLocalService));

		for (ObjectRelationship objectRelationship :
				_objectRelationshipLocalService.getObjectRelationships(
					objectDefinition.getObjectDefinitionId(),
					ObjectRelationshipConstants.TYPE_MANY_TO_MANY)) {

			_objectRelationshipLocalService.
				createManyToManyObjectRelationshipTable(
					userId, objectRelationship);
		}

		deployObjectDefinition(objectDefinition);

		_registerTransactionCallbackForCluster(
			_deployObjectDefinitionMethodKey, objectDefinition);

		return objectDefinition;
	}

	private void _registerTransactionCallbackForCluster(
		MethodKey methodKey, ObjectDefinition objectDefinition) {

		if (ClusterExecutorUtil.isEnabled()) {
			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					ClusterRequest clusterRequest =
						ClusterRequest.createMulticastRequest(
							new MethodHandler(methodKey, objectDefinition),
							true);

					clusterRequest.setFireAndForget(true);

					ClusterExecutorUtil.execute(clusterRequest);

					return null;
				});
		}
	}

	private void _updateDescendantNodeObjectDefinitions(
			ObjectDefinition objectDefinition1)
		throws PortalException {

		List<ObjectRelationship> objectRelationships =
			_objectRelationshipPersistence.findByODI1_E(
				objectDefinition1.getObjectDefinitionId(), true);

		if (objectRelationships.isEmpty()) {
			return;
		}

		deployObjectDefinition(objectDefinition1);

		boolean containsDraftDescendantNodeObjectDefinitions = false;
		ObjectDefinitionTreeFactory objectDefinitionTreeFactory =
			new ObjectDefinitionTreeFactory(
				objectDefinitionLocalService, _objectRelationshipLocalService);

		for (ObjectRelationship objectRelationship : objectRelationships) {
			ObjectDefinition objectDefinition2 =
				objectDefinitionPersistence.findByPrimaryKey(
					objectRelationship.getObjectDefinitionId2());

			if (!objectDefinition2.isApproved()) {
				containsDraftDescendantNodeObjectDefinitions = true;

				continue;
			}

			Tree tree = objectDefinitionTreeFactory.create(
				objectRelationship.getObjectDefinitionId2());

			Iterator<Node> iterator = tree.iterator();

			while (iterator.hasNext()) {
				Node node = iterator.next();

				ObjectDefinition nodeObjectDefinition =
					objectDefinitionLocalService.getObjectDefinition(
						node.getPrimaryKey());

				String previousRESTContextPath =
					nodeObjectDefinition.getRESTContextPath();

				nodeObjectDefinition.setRootObjectDefinitionId(
					objectDefinition1.getRootObjectDefinitionId());
				nodeObjectDefinition.setPortlet(false);

				nodeObjectDefinition = objectDefinitionPersistence.update(
					nodeObjectDefinition);

				nodeObjectDefinition.setPreviousRESTContextPath(
					previousRESTContextPath);

				deployObjectDefinition(nodeObjectDefinition);
			}
		}

		if (containsDraftDescendantNodeObjectDefinitions) {
			Tree tree = objectDefinitionTreeFactory.create(
				false, objectDefinition1.getObjectDefinitionId());

			Node rootNode = tree.getRootNode();

			for (Node childNode : rootNode.getChildNodes()) {
				Iterator<Node> iterator = tree.iterator(
					childNode.getPrimaryKey());

				while (iterator.hasNext()) {
					Node node = iterator.next();

					ObjectDefinition nodeObjectDefinition =
						objectDefinitionLocalService.getObjectDefinition(
							node.getPrimaryKey());

					nodeObjectDefinition.setRootObjectDefinitionId(
						childNode.getPrimaryKey());

					objectDefinitionPersistence.update(nodeObjectDefinition);
				}
			}
		}
	}

	private ObjectDefinition _updateNodeObjectDefinition(
			ObjectDefinition objectDefinition2)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipPersistence.fetchByODI2_E(
				objectDefinition2.getObjectDefinitionId(), true);

		if (objectRelationship == null) {
			return objectDefinition2;
		}

		ObjectDefinition objectDefinition1 =
			objectDefinitionLocalService.getObjectDefinition(
				objectRelationship.getObjectDefinitionId1());

		String previousRESTContextPath = objectDefinition2.getRESTContextPath();

		if (objectDefinition1.isApproved()) {
			objectDefinition2.setRootObjectDefinitionId(
				objectDefinition1.getRootObjectDefinitionId());
		}
		else {
			objectDefinition2.setRootObjectDefinitionId(
				objectDefinition2.getObjectDefinitionId());
		}

		objectDefinition2 = objectDefinitionPersistence.update(
			objectDefinition2);

		objectDefinition2.setPreviousRESTContextPath(previousRESTContextPath);

		return objectDefinition2;
	}

	private ObjectDefinition _updateObjectDefinition(
			String externalReferenceCode, ObjectDefinition objectDefinition,
			long accountEntryRestrictedObjectFieldId,
			long descriptionObjectFieldId, long objectFolderId,
			long titleObjectFieldId, boolean accountEntryRestricted,
			boolean active, String className, String dbTableName,
			boolean enableCategorization, boolean enableComments,
			boolean enableIndexSearch, boolean enableLocalization,
			boolean enableObjectEntryDraft, boolean enableObjectEntryHistory,
			Map<Locale, String> labelMap, String name, String panelAppOrder,
			String panelCategoryKey, boolean portlet,
			String pkObjectFieldDBColumnName, String pkObjectFieldName,
			Map<Locale, String> pluralLabelMap, String scope, int status)
		throws PortalException {

		long oldObjectFolderId = objectDefinition.getObjectFolderId();
		boolean oldActive = objectDefinition.isActive();
		String oldClassName = objectDefinition.getClassName();

		_validateExternalReferenceCode(
			externalReferenceCode, objectDefinition.isSystem());
		_validateAccountEntryRestrictedObjectFieldId(
			accountEntryRestrictedObjectFieldId, accountEntryRestricted,
			objectDefinition);
		_validateObjectFieldId(objectDefinition, descriptionObjectFieldId);
		_validateObjectFieldId(objectDefinition, titleObjectFieldId);
		_validateActive(active, status);

		if (Validator.isNull(oldClassName)) {
			_validateClassName(
				objectDefinition.getObjectDefinitionId(),
				objectDefinition.getCompanyId(), className,
				objectDefinition.isModifiable(), objectDefinition.isSystem());
		}

		_validateEnableCategorization(
			enableCategorization, objectDefinition.isModifiable(),
			objectDefinition.getStorageType(), objectDefinition.isSystem());
		_validateEnableComments(
			enableComments, objectDefinition.isModifiable(),
			objectDefinition.getStorageType(), objectDefinition.isSystem());
		_validateEnableObjectEntryHistory(
			objectDefinition.isEnableObjectEntryHistory() !=
				enableObjectEntryHistory,
			objectDefinition.isModifiable(), objectDefinition.getStorageType(),
			objectDefinition.isSystem());
		_validateLabel(labelMap);
		_validatePluralLabel(pluralLabelMap);

		if (objectDefinition.getAccountEntryRestrictedObjectFieldId() != 0) {
			_objectFieldLocalService.updateRequired(
				objectDefinition.getAccountEntryRestrictedObjectFieldId(),
				false);
		}

		if (accountEntryRestricted &&
			(accountEntryRestrictedObjectFieldId > 0)) {

			_objectFieldLocalService.updateRequired(
				accountEntryRestrictedObjectFieldId, true);
		}

		objectDefinition.setExternalReferenceCode(externalReferenceCode);
		objectDefinition.setAccountEntryRestrictedObjectFieldId(
			accountEntryRestrictedObjectFieldId);
		objectDefinition.setDescriptionObjectFieldId(descriptionObjectFieldId);
		objectDefinition.setObjectFolderId(
			_getObjectFolderId(
				objectDefinition.getCompanyId(), objectFolderId));
		objectDefinition.setTitleObjectFieldId(titleObjectFieldId);
		objectDefinition.setAccountEntryRestricted(accountEntryRestricted);
		objectDefinition.setActive(active);

		if (Validator.isNull(oldClassName)) {
			objectDefinition.setClassName(
				_getClassName(
					objectDefinition.getCompanyId(), className,
					objectDefinition.isModifiable(),
					objectDefinition.isSystem()));
		}

		objectDefinition.setEnableCategorization(enableCategorization);
		objectDefinition.setEnableComments(enableComments);
		objectDefinition.setEnableObjectEntryDraft(enableObjectEntryDraft);
		objectDefinition.setEnableObjectEntryHistory(enableObjectEntryHistory);
		objectDefinition.setLabelMap(labelMap, LocaleUtil.getSiteDefault());
		objectDefinition.setPanelAppOrder(panelAppOrder);
		objectDefinition.setPanelCategoryKey(panelCategoryKey);
		objectDefinition.setPluralLabelMap(pluralLabelMap);
		objectDefinition.setPortlet(portlet);

		if (!objectDefinition.isUnmodifiableSystemObject()) {
			_addOrUpdateObjectDefinitionPLOEntries(objectDefinition);
		}

		if (objectDefinition.isApproved()) {
			if (!active && oldActive) {
				objectDefinitionLocalService.deployInactiveObjectDefinition(
					objectDefinition);
			}
			else if (active) {
				objectDefinitionLocalService.deployObjectDefinition(
					objectDefinition);
			}

			if (active != oldActive) {
				_updateWorkflowInstances(objectDefinition);
			}

			objectDefinition = objectDefinitionPersistence.update(
				objectDefinition);

			_objectFolderItemLocalService.updateObjectFolderObjectFolderItem(
				objectDefinition.getObjectDefinitionId(),
				objectDefinition.getObjectFolderId(), oldObjectFolderId);

			return objectDefinition;
		}

		name = _getName(name, objectDefinition.isSystem());

		String shortName = ObjectDefinitionImpl.getShortName(name);

		dbTableName = _getDBTableName(
			dbTableName, objectDefinition.isModifiable(), name,
			objectDefinition.isSystem(), objectDefinition.getCompanyId(),
			shortName);

		pkObjectFieldName = _getPKObjectFieldName(
			pkObjectFieldName, objectDefinition.isModifiable(),
			objectDefinition.isSystem(), shortName);

		pkObjectFieldDBColumnName = _getPKObjectFieldDBColumnName(
			pkObjectFieldDBColumnName, pkObjectFieldName,
			objectDefinition.isModifiable(), objectDefinition.isSystem());

		_validateName(
			objectDefinition.getObjectDefinitionId(),
			objectDefinition.getCompanyId(), objectDefinition.isModifiable(),
			name, objectDefinition.isSystem());
		_validateScope(scope, objectDefinition.getStorageType());

		objectDefinition.setDBTableName(dbTableName);
		objectDefinition.setEnableIndexSearch(enableIndexSearch);
		objectDefinition.setEnableLocalization(enableLocalization);
		objectDefinition.setName(name);
		objectDefinition.setPKObjectFieldDBColumnName(
			pkObjectFieldDBColumnName);
		objectDefinition.setPKObjectFieldName(pkObjectFieldName);
		objectDefinition.setScope(scope);

		objectDefinition = objectDefinitionPersistence.update(objectDefinition);

		for (ObjectField objectField :
				_objectFieldLocalService.getObjectFields(
					objectDefinition.getObjectDefinitionId(),
					StringPool.BLANK)) {

			objectField.setDBTableName(objectDefinition.getDBTableName());

			_objectFieldLocalService.updateObjectField(objectField);
		}

		_objectFolderItemLocalService.updateObjectFolderObjectFolderItem(
			objectDefinition.getObjectDefinitionId(),
			objectDefinition.getObjectFolderId(), oldObjectFolderId);

		return objectDefinition;
	}

	private ObjectDefinition _updateTitleObjectFieldId(
			ObjectDefinition objectDefinition, String titleObjectFieldName)
		throws PortalException {

		if (Validator.isNull(titleObjectFieldName)) {
			titleObjectFieldName = "id";
		}

		ObjectField objectField = _objectFieldPersistence.findByODI_N(
			objectDefinition.getObjectDefinitionId(), titleObjectFieldName);

		_validateObjectFieldId(
			objectDefinition, objectField.getObjectFieldId());

		objectDefinition.setTitleObjectFieldId(objectField.getObjectFieldId());

		return objectDefinitionPersistence.update(objectDefinition);
	}

	private void _updateWorkflowInstances(ObjectDefinition objectDefinition)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			_objectEntryLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property objectDefinitionIdProperty =
					PropertyFactoryUtil.forName("objectDefinitionId");

				dynamicQuery.add(
					objectDefinitionIdProperty.eq(
						objectDefinition.getObjectDefinitionId()));

				Property statusProperty = PropertyFactoryUtil.forName("status");

				dynamicQuery.add(
					statusProperty.ne(WorkflowConstants.STATUS_APPROVED));
			});
		actionableDynamicQuery.setParallel(true);
		actionableDynamicQuery.setPerformActionMethod(
			(ObjectEntry objectEntry) -> {
				WorkflowInstanceLink workflowInstanceLink =
					_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
						objectEntry.getCompanyId(),
						objectEntry.getNonzeroGroupId(),
						objectDefinition.getClassName(),
						objectEntry.getObjectEntryId());

				if (workflowInstanceLink != null) {
					_workflowInstanceManager.updateActive(
						objectDefinition.getUserId(),
						objectDefinition.getCompanyId(),
						workflowInstanceLink.getWorkflowInstanceId(),
						objectDefinition.isActive());
				}
			});

		actionableDynamicQuery.performActions();
	}

	private void _validateAccountEntryRestrictedObjectFieldId(
			long accountEntryRestrictedObjectFieldId,
			boolean accountEntryRestricted, ObjectDefinition objectDefinition)
		throws ObjectDefinitionAccountEntryRestrictedException,
			   ObjectDefinitionAccountEntryRestrictedObjectFieldIdException {

		if (accountEntryRestricted &&
			(accountEntryRestrictedObjectFieldId == 0)) {

			throw new ObjectDefinitionAccountEntryRestrictedObjectFieldIdException();
		}

		if (objectDefinition.isApproved() &&
			objectDefinition.isAccountEntryRestricted() &&
			!accountEntryRestricted) {

			throw new ObjectDefinitionAccountEntryRestrictedException(
				"Account entry restriction cannot be disabled when the " +
					"object definition is published");
		}
	}

	private void _validateActive(boolean active, int status)
		throws PortalException {

		if (active &&
			!Objects.equals(WorkflowConstants.STATUS_APPROVED, status)) {

			throw new ObjectDefinitionActiveException(
				"Object definitions must be published before being activated");
		}
	}

	private void _validateClassName(
			long objectDefinitionId, long companyId, String className,
			boolean modifiable, boolean system)
		throws PortalException {

		if (Validator.isNull(className) ||
			_isUnmodifiableSystemObject(modifiable, system)) {

			return;
		}

		ObjectDefinition existingObjectDefinition =
			objectDefinitionPersistence.fetchByC_C(companyId, className);

		if ((existingObjectDefinition != null) &&
			(existingObjectDefinition.getObjectDefinitionId() !=
				objectDefinitionId)) {

			throw new ObjectDefinitionClassNameException.MustNotBeDuplicate(
				className);
		}

		if (!StringUtil.startsWith(
				className,
				ObjectDefinitionConstants.
					CLASS_NAME_PREFIX_CUSTOM_OBJECT_DEFINITION)) {

			throw new ObjectDefinitionClassNameException.MustStartWithPrefix();
		}
	}

	private void _validateEnableCategorization(
			boolean enableCategorization, boolean modifiable,
			String storageType, boolean system)
		throws PortalException {

		if (enableCategorization &&
			_isUnmodifiableSystemObject(modifiable, system)) {

			throw new ObjectDefinitionEnableCategorizationException(
				"Enable categorization is not allowed for system object " +
					"definitions");
		}

		if (enableCategorization &&
			!StringUtil.equals(
				storageType, ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT)) {

			throw new ObjectDefinitionEnableCategorizationException(
				"Enable categorization is only allowed for object " +
					"definitions with the default storage type");
		}
	}

	private void _validateEnableComments(
			boolean enableComments, boolean modifiable, String storageType,
			boolean system)
		throws PortalException {

		if (enableComments && _isUnmodifiableSystemObject(modifiable, system)) {
			throw new ObjectDefinitionEnableCommentsException(
				"Enable comments is not allowed for system object definitions");
		}

		if (enableComments &&
			!StringUtil.equals(
				storageType, ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT)) {

			throw new ObjectDefinitionEnableCategorizationException(
				"Enable comments is only allowed for object definitions with " +
					"the default storage type");
		}
	}

	private void _validateEnableObjectEntryHistory(
			boolean enableObjectEntryHistoryChanged, boolean modifiable,
			String storageType, boolean system)
		throws PortalException {

		if (!enableObjectEntryHistoryChanged) {
			return;
		}

		if (_isUnmodifiableSystemObject(modifiable, system)) {
			throw new ObjectDefinitionEnableObjectEntryHistoryException(
				"Enable object entry history is not allowed for system " +
					"object definitions");
		}

		if (!StringUtil.equals(
				storageType, ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT)) {

			throw new ObjectDefinitionEnableObjectEntryHistoryException(
				"Enable object entry history is only allowed for object " +
					"definitions with the default storage type");
		}
	}

	private void _validateExternalReferenceCode(
			String externalReferenceCode, boolean system)
		throws PortalException {

		if (Validator.isNull(externalReferenceCode)) {
			return;
		}

		char[] externalReferenceCodeCharArray =
			externalReferenceCode.toCharArray();

		if (externalReferenceCodeCharArray.length > 75) {
			throw new ObjectDefinitionExternalReferenceCodeException.
				MustBeLessThan75Characters();
		}

		if (!system &&
			externalReferenceCode.startsWith(
				ObjectDefinitionConstants.
					EXTERNAL_REFERENCE_CODE_PREFIX_SYSTEM_OBJECT_DEFINITION)) {

			throw new ObjectDefinitionExternalReferenceCodeException.
				MustNotStartWithPrefix();
		}
	}

	private void _validateLabel(Map<Locale, String> labelMap)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		if ((labelMap == null) || Validator.isNull(labelMap.get(locale))) {
			throw new ObjectDefinitionLabelException(
				"Label is null for locale " + locale.getDisplayName());
		}
	}

	private void _validateName(
			long objectDefinitionId, long companyId, boolean modifiable,
			String name, boolean system)
		throws PortalException {

		if (modifiable && system &&
			!ObjectDefinitionUtil.isAllowedModifiableSystemObjectDefinitionName(
				name)) {

			throw new ObjectDefinitionNameException.
				ForbiddenModifiableSystemObjectDefinitionName(name);
		}

		if (Validator.isNull(name) || (!system && name.equals("C_"))) {
			throw new ObjectDefinitionNameException.MustNotBeNull();
		}

		if (_isUnmodifiableSystemObject(modifiable, system) &&
			(name.startsWith("C_") || name.startsWith("c_"))) {

			throw new ObjectDefinitionNameException.
				MustNotStartWithCAndUnderscoreForSystemObject();
		}
		else if (!system && !name.startsWith("C_")) {
			throw new ObjectDefinitionNameException.
				MustStartWithCAndUnderscoreForCustomObject();
		}

		char[] nameCharArray = name.toCharArray();

		for (int i = 0; i < nameCharArray.length; i++) {
			if (modifiable || !system) {

				// Skip C_

				if ((i == 0) || (i == 1)) {
					continue;
				}
			}

			char c = nameCharArray[i];

			if (!Validator.isChar(c) && !Validator.isDigit(c)) {
				throw new ObjectDefinitionNameException.
					MustOnlyContainLettersAndDigits();
			}
		}

		if ((system && !Character.isUpperCase(nameCharArray[0])) ||
			(!system && !Character.isUpperCase(nameCharArray[2]))) {

			throw new ObjectDefinitionNameException.
				MustBeginWithUpperCaseLetter();
		}

		if ((system && (nameCharArray.length > 41)) ||
			(!system && (nameCharArray.length > 43))) {

			throw new ObjectDefinitionNameException.
				MustBeLessThan41Characters();
		}

		ObjectDefinition objectDefinition =
			objectDefinitionPersistence.fetchByC_N(companyId, name);

		if ((objectDefinition != null) &&
			(objectDefinition.getObjectDefinitionId() != objectDefinitionId)) {

			throw new ObjectDefinitionNameException.MustNotBeDuplicate(name);
		}
	}

	private void _validateObjectFieldId(
			ObjectDefinition objectDefinition, long objectFieldId)
		throws PortalException {

		if (objectFieldId <= 0) {
			return;
		}

		ObjectField objectField = _objectFieldLocalService.fetchObjectField(
			objectFieldId);

		if ((objectField == null) ||
			(objectField.getObjectDefinitionId() !=
				objectDefinition.getObjectDefinitionId())) {

			throw new NoSuchObjectFieldException();
		}

		if (Validator.isNotNull(objectField.getRelationshipType())) {
			throw new ObjectFieldRelationshipTypeException(
				"Description and title object fields cannot have a " +
					"relationship type");
		}
	}

	private void _validatePluralLabel(Map<Locale, String> pluralLabelMap)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		if ((pluralLabelMap == null) ||
			Validator.isNull(pluralLabelMap.get(locale))) {

			throw new ObjectDefinitionPluralLabelException(
				"Plural label is null for locale " + locale.getDisplayName());
		}
	}

	private void _validateScope(String scope, String storageType)
		throws PortalException {

		if (Validator.isNull(scope)) {
			throw new ObjectDefinitionScopeException("Scope is null");
		}

		try {
			_objectScopeProviderRegistry.getObjectScopeProvider(scope);
		}
		catch (IllegalArgumentException illegalArgumentException) {
			throw new ObjectDefinitionScopeException(
				illegalArgumentException.getMessage());
		}

		if (StringUtil.equals(scope, ObjectDefinitionConstants.SCOPE_SITE) &&
			StringUtil.equals(
				storageType,
				ObjectDefinitionConstants.STORAGE_TYPE_SALESFORCE)) {

			throw new ObjectDefinitionScopeException(
				StringBundler.concat(
					"Scope \"", ObjectDefinitionConstants.SCOPE_SITE,
					"\" cannot be associated with storage type \"",
					ObjectDefinitionConstants.STORAGE_TYPE_SALESFORCE));
		}
	}

	private void _validateVersion(boolean system, int version)
		throws PortalException {

		if (system) {
			if (version <= 0) {
				throw new ObjectDefinitionVersionException(
					"System object definition versions must greater than 0");
			}
		}
		else {
			if (version != 0) {
				throw new ObjectDefinitionVersionException(
					"Custom object definition versions must be 0");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectDefinitionLocalServiceImpl.class);

	private static final MethodKey _deployObjectDefinitionMethodKey =
		new MethodKey(
			ObjectDefinitionLocalServiceUtil.class, "deployObjectDefinition",
			ObjectDefinition.class);
	private static final MethodKey _undeployObjectDefinitionMethodKey =
		new MethodKey(
			ObjectDefinitionLocalServiceUtil.class, "undeployObjectDefinition",
			ObjectDefinition.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	private BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private CurrentConnection _currentConnection;

	@Reference
	private DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

	@Reference
	private FragmentEntryLinkCache _fragmentEntryLinkCache;

	@Reference
	private GroupLocalService _groupLocalService;

	private final Map
		<InactiveObjectDefinitionDeployer,
		 Map<Long, List<ServiceRegistration<?>>>>
			_inactiveObjectDefinitionsServiceRegistrationsMaps =
				Collections.synchronizedMap(new LinkedHashMap<>());

	@Reference
	private Language _language;

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private ObjectActionLocalService _objectActionLocalService;

	private ServiceTracker<ObjectDefinitionDeployer, ObjectDefinitionDeployer>
		_objectDefinitionDeployerServiceTracker;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryPersistence _objectEntryPersistence;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectFieldPersistence _objectFieldPersistence;

	@Reference
	private ObjectFolderItemLocalService _objectFolderItemLocalService;

	@Reference
	private ObjectFolderLocalService _objectFolderLocalService;

	@Reference
	private ObjectFolderPersistence _objectFolderPersistence;

	@Reference
	private ObjectLayoutLocalService _objectLayoutLocalService;

	@Reference
	private ObjectLayoutTabLocalService _objectLayoutTabLocalService;

	@Reference
	private ObjectRelatedModelsProviderRegistrarHelper
		_objectRelatedModelsProviderRegistrarHelper;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectRelationshipPersistence _objectRelationshipPersistence;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private ObjectValidationRuleLocalService _objectValidationRuleLocalService;

	@Reference
	private ObjectViewLocalService _objectViewLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PLOEntryLocalService _ploEntryLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private ResourceActions _resourceActions;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	@Reference
	private SearchLocalizationHelper _searchLocalizationHelper;

	private final Map
		<ObjectDefinitionDeployer, Map<Long, List<ServiceRegistration<?>>>>
			_serviceRegistrationsMaps = Collections.synchronizedMap(
				new LinkedHashMap<>());

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

	@Reference
	private WorkflowInstanceManager _workflowInstanceManager;

	@Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
	private ModelPreFilterContributor _workflowStatusModelPreFilterContributor;

}