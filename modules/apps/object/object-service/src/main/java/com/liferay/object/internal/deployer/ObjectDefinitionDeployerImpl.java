/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.deployer;

import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.notification.handler.NotificationHandler;
import com.liferay.notification.term.evaluator.NotificationTermEvaluator;
import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.internal.layout.tab.screen.navigation.category.ObjectLayoutTabScreenNavigationCategory;
import com.liferay.object.internal.notification.handler.ObjectDefinitionNotificationHandler;
import com.liferay.object.internal.notification.term.contributor.ObjectDefinitionNotificationTermEvaluator;
import com.liferay.object.internal.related.models.ObjectEntry1to1ObjectRelatedModelsProviderImpl;
import com.liferay.object.internal.related.models.ObjectEntry1toMObjectRelatedModelsPredicateProviderImpl;
import com.liferay.object.internal.related.models.ObjectEntry1toMObjectRelatedModelsProviderImpl;
import com.liferay.object.internal.related.models.ObjectEntryMtoMObjectRelatedModelsPredicateProviderImpl;
import com.liferay.object.internal.related.models.ObjectEntryMtoMObjectRelatedModelsProviderImpl;
import com.liferay.object.internal.rest.context.path.RESTContextPathResolverImpl;
import com.liferay.object.internal.search.spi.model.index.contributor.ObjectEntryModelDocumentContributor;
import com.liferay.object.internal.search.spi.model.index.contributor.ObjectEntryModelIndexerWriterContributor;
import com.liferay.object.internal.search.spi.model.query.contributor.ObjectEntryKeywordQueryContributor;
import com.liferay.object.internal.search.spi.model.query.contributor.ObjectEntryModelPreFilterContributor;
import com.liferay.object.internal.search.spi.model.result.contributor.ObjectEntryModelSummaryContributor;
import com.liferay.object.internal.security.permission.resource.ObjectEntryModelResourcePermission;
import com.liferay.object.internal.security.permission.resource.ObjectEntryPortletResourcePermissionLogic;
import com.liferay.object.internal.security.permission.resource.util.ObjectDefinitionResourcePermissionUtil;
import com.liferay.object.internal.uad.anonymizer.ObjectEntryUADAnonymizer;
import com.liferay.object.internal.uad.display.ObjectEntryUADDisplay;
import com.liferay.object.internal.uad.exporter.ObjectEntryUADExporter;
import com.liferay.object.internal.workflow.ObjectEntryWorkflowHandler;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectLayout;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.models.ObjectRelatedModelsPredicateProvider;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistrarHelper;
import com.liferay.object.rest.context.path.RESTContextPathResolver;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectLayoutLocalService;
import com.liferay.object.service.ObjectLayoutTabLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectViewLocalService;
import com.liferay.object.service.persistence.ObjectDefinitionPersistence;
import com.liferay.object.tree.Edge;
import com.liferay.object.tree.Node;
import com.liferay.object.tree.ObjectDefinitionTreeFactory;
import com.liferay.object.tree.Tree;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.language.override.service.PLOEntryLocalService;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.localization.SearchLocalizationHelper;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;
import com.liferay.user.associated.data.display.UADDisplay;
import com.liferay.user.associated.data.exporter.UADExporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Brian Wing Shun Chan
 * @author Marco Leo
 */
public class ObjectDefinitionDeployerImpl implements ObjectDefinitionDeployer {

	public ObjectDefinitionDeployerImpl(
		AccountEntryLocalService accountEntryLocalService,
		AccountEntryOrganizationRelLocalService
			accountEntryOrganizationRelLocalService,
		AssetEntryLocalService assetEntryLocalService,
		BundleContext bundleContext,
		DynamicQueryBatchIndexingActionableFactory
			dynamicQueryBatchIndexingActionableFactory,
		GroupLocalService groupLocalService,
		ListTypeLocalService listTypeLocalService,
		ObjectActionLocalService objectActionLocalService,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectEntryService objectEntryService,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectLayoutLocalService objectLayoutLocalService,
		ObjectLayoutTabLocalService objectLayoutTabLocalService,
		ObjectRelatedModelsProviderRegistrarHelper
			objectRelatedModelsProviderRegistrarHelper,
		ObjectRelationshipLocalService objectRelationshipLocalService,
		ObjectScopeProviderRegistry objectScopeProviderRegistry,
		ObjectViewLocalService objectViewLocalService,
		OrganizationLocalService organizationLocalService,
		PLOEntryLocalService ploEntryLocalService, Portal portal,
		PortletLocalService portletLocalService,
		ResourceActions resourceActions, UserLocalService userLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService,
		SearchLocalizationHelper searchLocalizationHelper,
		ModelPreFilterContributor workflowStatusModelPreFilterContributor,
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_accountEntryLocalService = accountEntryLocalService;
		_accountEntryOrganizationRelLocalService =
			accountEntryOrganizationRelLocalService;
		_assetEntryLocalService = assetEntryLocalService;
		_bundleContext = bundleContext;
		_dynamicQueryBatchIndexingActionableFactory =
			dynamicQueryBatchIndexingActionableFactory;
		_groupLocalService = groupLocalService;
		_listTypeLocalService = listTypeLocalService;
		_objectActionLocalService = objectActionLocalService;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectEntryLocalService = objectEntryLocalService;
		_objectEntryService = objectEntryService;
		_objectFieldLocalService = objectFieldLocalService;
		_objectLayoutLocalService = objectLayoutLocalService;
		_objectLayoutTabLocalService = objectLayoutTabLocalService;
		_objectRelatedModelsProviderRegistrarHelper =
			objectRelatedModelsProviderRegistrarHelper;
		_objectRelationshipLocalService = objectRelationshipLocalService;
		_objectScopeProviderRegistry = objectScopeProviderRegistry;
		_objectViewLocalService = objectViewLocalService;
		_organizationLocalService = organizationLocalService;
		_ploEntryLocalService = ploEntryLocalService;
		_portal = portal;
		_portletLocalService = portletLocalService;
		_resourceActions = resourceActions;
		_userLocalService = userLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_searchLocalizationHelper = searchLocalizationHelper;
		_workflowStatusModelPreFilterContributor =
			workflowStatusModelPreFilterContributor;
		_userGroupRoleLocalService = userGroupRoleLocalService;

		_objectDefinitionTreeFactory = new ObjectDefinitionTreeFactory(
			_objectDefinitionLocalService, _objectRelationshipLocalService);
	}

	@Override
	public List<ServiceRegistration<?>> deploy(
		ObjectDefinition objectDefinition) {

		if (objectDefinition.isUnmodifiableSystemObject()) {
			return Collections.emptyList();
		}

		try {
			ObjectDefinitionResourcePermissionUtil.populateResourceActions(
				_objectActionLocalService, objectDefinition,
				(ObjectDefinitionPersistence)
					_objectDefinitionLocalService.getBasePersistence(),
				_objectDefinitionTreeFactory, _portletLocalService,
				_resourceActions);
		}
		catch (Exception exception) {
			return ReflectionUtil.throwException(exception);
		}

		List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();

		if (objectDefinition.isEnableIndexSearch()) {
			ObjectEntryModelIndexerWriterContributor
				objectEntryModelIndexerWriterContributor =
					new ObjectEntryModelIndexerWriterContributor(
						_dynamicQueryBatchIndexingActionableFactory,
						objectDefinition.getObjectDefinitionId(),
						_objectEntryLocalService);
			ObjectEntryModelSummaryContributor
				objectEntryModelSummaryContributor =
					new ObjectEntryModelSummaryContributor();

			Collections.addAll(
				serviceRegistrations,
				_bundleContext.registerService(
					KeywordQueryContributor.class,
					new ObjectEntryKeywordQueryContributor(
						objectDefinition, _objectFieldLocalService,
						_objectViewLocalService, _searchLocalizationHelper),
					HashMapDictionaryBuilder.<String, Object>put(
						"component.name",
						ObjectEntryKeywordQueryContributor.class.getName()
					).put(
						"indexer.class.name", objectDefinition.getClassName()
					).build()),
				_bundleContext.registerService(
					ModelDocumentContributor.class,
					new ObjectEntryModelDocumentContributor(
						_accountEntryOrganizationRelLocalService,
						objectDefinition.getClassName(),
						_objectDefinitionLocalService, _objectEntryLocalService,
						_objectFieldLocalService),
					HashMapDictionaryBuilder.<String, Object>put(
						"indexer.class.name", objectDefinition.getClassName()
					).build()),
				_bundleContext.registerService(
					ModelPreFilterContributor.class,
					new ObjectEntryModelPreFilterContributor(
						_workflowStatusModelPreFilterContributor),
					HashMapDictionaryBuilder.<String, Object>put(
						"indexer.class.name", objectDefinition.getClassName()
					).build()),
				_bundleContext.registerService(
					ModelSearchConfigurator.class,
					new ModelSearchConfigurator<ObjectEntry>() {

						@Override
						public String getClassName() {
							return objectDefinition.getClassName();
						}

						@Override
						public long getCompanyId() {
							return objectDefinition.getCompanyId();
						}

						@Override
						public ModelIndexerWriterContributor<ObjectEntry>
							getModelIndexerWriterContributor() {

							return objectEntryModelIndexerWriterContributor;
						}

						@Override
						public ModelSummaryContributor
							getModelSummaryContributor() {

							return objectEntryModelSummaryContributor;
						}

					},
					null));
		}

		Collections.addAll(
			serviceRegistrations,
			_bundleContext.registerService(
				NotificationHandler.class,
				new ObjectDefinitionNotificationHandler(objectDefinition),
				HashMapDictionaryBuilder.<String, Object>put(
					"class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				NotificationTermEvaluator.class,
				new ObjectDefinitionNotificationTermEvaluator(
					_listTypeLocalService, objectDefinition,
					_objectDefinitionLocalService, _objectEntryLocalService,
					_objectFieldLocalService, _objectRelationshipLocalService,
					_userLocalService),
				HashMapDictionaryBuilder.<String, Object>put(
					"class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				ObjectRelatedModelsPredicateProvider.class,
				new ObjectEntry1toMObjectRelatedModelsPredicateProviderImpl(
					objectDefinition, _objectFieldLocalService),
				null),
			_bundleContext.registerService(
				ObjectRelatedModelsPredicateProvider.class,
				new ObjectEntryMtoMObjectRelatedModelsPredicateProviderImpl(
					objectDefinition, _objectFieldLocalService),
				null),
			_bundleContext.registerService(
				PersistedModelLocalService.class, _objectEntryLocalService,
				MapUtil.singletonDictionary(
					"model.class.name", objectDefinition.getClassName())),
			_bundleContext.registerService(
				RESTContextPathResolver.class,
				new RESTContextPathResolverImpl(
					objectDefinition,
					_objectScopeProviderRegistry.getObjectScopeProvider(
						objectDefinition.getScope()),
					false),
				HashMapDictionaryBuilder.<String, Object>put(
					"model.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				UADAnonymizer.class,
				new ObjectEntryUADAnonymizer(
					_assetEntryLocalService, objectDefinition,
					_objectEntryLocalService, _resourcePermissionLocalService),
				null),
			_bundleContext.registerService(
				UADDisplay.class,
				new ObjectEntryUADDisplay(
					_groupLocalService, objectDefinition,
					_objectEntryLocalService, _objectScopeProviderRegistry,
					_portal),
				null),
			_bundleContext.registerService(
				UADExporter.class,
				new ObjectEntryUADExporter(
					objectDefinition, _objectEntryLocalService),
				null),
			_bundleContext.registerService(
				WorkflowHandler.class,
				new ObjectEntryWorkflowHandler(
					objectDefinition, _objectEntryLocalService),
				HashMapDictionaryBuilder.<String, Object>put(
					"model.class.name", objectDefinition.getClassName()
				).build()),
			_objectRelatedModelsProviderRegistrarHelper.register(
				_bundleContext, objectDefinition,
				new ObjectEntryMtoMObjectRelatedModelsProviderImpl(
					objectDefinition, _objectEntryService,
					_objectRelationshipLocalService)),
			_objectRelatedModelsProviderRegistrarHelper.register(
				_bundleContext, objectDefinition,
				new ObjectEntry1toMObjectRelatedModelsProviderImpl(
					objectDefinition, _objectEntryService,
					_objectFieldLocalService, _objectRelationshipLocalService)),
			_objectRelatedModelsProviderRegistrarHelper.register(
				_bundleContext, objectDefinition,
				new ObjectEntry1to1ObjectRelatedModelsProviderImpl(
					objectDefinition, _objectEntryService,
					_objectFieldLocalService,
					_objectRelationshipLocalService)));

		if (!objectDefinition.isRootDescendantNode()) {
			PortletResourcePermission portletResourcePermission =
				PortletResourcePermissionFactory.create(
					objectDefinition.getResourceName(),
					new ObjectEntryPortletResourcePermissionLogic(
						_accountEntryLocalService, _groupLocalService,
						_objectDefinitionLocalService,
						_organizationLocalService));

			serviceRegistrations.add(
				_bundleContext.registerService(
					ModelResourcePermission.class,
					new ObjectEntryModelResourcePermission(
						_accountEntryLocalService,
						_accountEntryOrganizationRelLocalService,
						_groupLocalService, objectDefinition.getClassName(),
						_objectActionLocalService,
						_objectDefinitionLocalService, _objectEntryLocalService,
						_objectFieldLocalService, portletResourcePermission,
						_resourcePermissionLocalService,
						_userGroupRoleLocalService),
					HashMapDictionaryBuilder.<String, Object>put(
						"com.liferay.object", "true"
					).put(
						"model.class.name", objectDefinition.getClassName()
					).build()));

			serviceRegistrations.add(
				_bundleContext.registerService(
					PortletResourcePermission.class, portletResourcePermission,
					HashMapDictionaryBuilder.<String, Object>put(
						"com.liferay.object", "true"
					).put(
						"resource.name", objectDefinition.getResourceName()
					).build()));
		}

		ObjectLayout objectLayout =
			_objectLayoutLocalService.fetchDefaultObjectLayout(
				objectDefinition.getObjectDefinitionId());

		if (objectLayout != null) {
			_objectLayoutTabLocalService.
				registerObjectLayoutTabScreenNavigationCategories(
					objectDefinition, objectLayout.getObjectLayoutTabs());
		}

		_objectRelationshipLocalService.
			registerObjectRelationshipsRelatedInfoCollectionProviders(
				objectDefinition, _objectDefinitionLocalService);

		try {
			if (objectDefinition.isRootNode()) {
				_registerRootObjectLayoutTabScreenNavigationCategories(
					objectDefinition.getRootObjectDefinitionId());
			}
		}
		catch (PortalException portalException) {
			return ReflectionUtil.throwException(portalException);
		}

		return serviceRegistrations;
	}

	private String _getServiceRegistrationKey(
		ObjectDefinition objectDefinition,
		ObjectRelationship objectRelationship) {

		String serviceRegistrationKey = StringBundler.concat(
			"ROOT_OBJECT_LAYOUT_TAB#", objectDefinition.getCompanyId(),
			StringPool.POUND, objectDefinition.getObjectDefinitionId());

		if (objectRelationship == null) {
			return serviceRegistrationKey;
		}

		return StringBundler.concat(
			serviceRegistrationKey, StringPool.POUND,
			objectRelationship.getObjectRelationshipId());
	}

	private void _registerRootObjectLayoutTabScreenNavigationCategories(
			long rootObjectDefinitionId)
		throws PortalException {

		Tree tree = _objectDefinitionTreeFactory.create(rootObjectDefinitionId);

		Iterator<Node> iterator = tree.iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();

			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.fetchObjectDefinition(
					node.getPrimaryKey());

			if (objectDefinition == null) {
				continue;
			}

			List<Node> childNodes = node.getChildNodes();

			if (ListUtil.isEmpty(childNodes)) {
				_registerRootObjectLayoutTabScreenNavigationCategory(
					objectDefinition, null);

				continue;
			}

			for (int i = childNodes.size() - 1; i >= 0; i--) {
				Node childNode = childNodes.get(i);

				Edge edge = childNode.getEdge();

				_registerRootObjectLayoutTabScreenNavigationCategory(
					objectDefinition,
					_objectRelationshipLocalService.fetchObjectRelationship(
						edge.getObjectRelationshipId()));
			}

			_registerRootObjectLayoutTabScreenNavigationCategory(
				objectDefinition, null);
		}
	}

	private void _registerRootObjectLayoutTabScreenNavigationCategory(
		ObjectDefinition objectDefinition,
		ObjectRelationship objectRelationship) {

		_serviceRegistrations.computeIfAbsent(
			_getServiceRegistrationKey(objectDefinition, objectRelationship),
			serviceRegistrationKey -> _bundleContext.registerService(
				new String[] {
					ScreenNavigationCategory.class.getName(),
					ScreenNavigationEntry.class.getName()
				},
				new ObjectLayoutTabScreenNavigationCategory(
					objectDefinition, null, objectRelationship),
				null));
	}

	private final AccountEntryLocalService _accountEntryLocalService;
	private final AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;
	private final AssetEntryLocalService _assetEntryLocalService;
	private final BundleContext _bundleContext;
	private final DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;
	private final GroupLocalService _groupLocalService;
	private final ListTypeLocalService _listTypeLocalService;
	private final ObjectActionLocalService _objectActionLocalService;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final ObjectDefinitionTreeFactory _objectDefinitionTreeFactory;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectEntryService _objectEntryService;
	private final ObjectFieldLocalService _objectFieldLocalService;
	private final ObjectLayoutLocalService _objectLayoutLocalService;
	private final ObjectLayoutTabLocalService _objectLayoutTabLocalService;
	private final ObjectRelatedModelsProviderRegistrarHelper
		_objectRelatedModelsProviderRegistrarHelper;
	private final ObjectRelationshipLocalService
		_objectRelationshipLocalService;
	private final ObjectScopeProviderRegistry _objectScopeProviderRegistry;
	private final ObjectViewLocalService _objectViewLocalService;
	private final OrganizationLocalService _organizationLocalService;
	private final PLOEntryLocalService _ploEntryLocalService;
	private final Portal _portal;
	private final PortletLocalService _portletLocalService;
	private final ResourceActions _resourceActions;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final SearchLocalizationHelper _searchLocalizationHelper;
	private final Map<String, ServiceRegistration<?>> _serviceRegistrations =
		new ConcurrentHashMap<>();
	private final UserGroupRoleLocalService _userGroupRoleLocalService;
	private final UserLocalService _userLocalService;
	private final ModelPreFilterContributor
		_workflowStatusModelPreFilterContributor;

}