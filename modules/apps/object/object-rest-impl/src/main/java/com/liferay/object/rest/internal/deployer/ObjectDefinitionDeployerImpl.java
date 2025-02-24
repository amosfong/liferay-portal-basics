/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.deployer;

import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.exception.NoSuchObjectDefinitionException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistry;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.internal.graphql.dto.v1_0.ObjectDefinitionGraphQLDTOContributor;
import com.liferay.object.rest.internal.jaxrs.application.ObjectEntryApplication;
import com.liferay.object.rest.internal.jaxrs.context.provider.ObjectDefinitionContextProvider;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectAssetCategoryExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryCountExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryManagerHttpExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryStatusExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectEntryValuesExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.ObjectRelationshipDeletionTypeExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.RequiredObjectRelationshipExceptionMapper;
import com.liferay.object.rest.internal.jaxrs.exception.mapper.UnsupportedOperationExceptionMapper;
import com.liferay.object.rest.internal.manager.v1_0.ObjectEntry1toMObjectRelationshipElementsParserImpl;
import com.liferay.object.rest.internal.manager.v1_0.ObjectEntryMtoMObjectRelationshipElementsParserImpl;
import com.liferay.object.rest.internal.manager.v1_0.SystemObjectEntry1toMObjectRelationshipElementsParserImpl;
import com.liferay.object.rest.internal.manager.v1_0.SystemObjectEntryMtoMObjectRelationshipElementsParserImpl;
import com.liferay.object.rest.internal.openapi.v1_0.ObjectEntryOpenAPIResourceImpl;
import com.liferay.object.rest.internal.resource.v1_0.BaseObjectEntryResourceImpl;
import com.liferay.object.rest.internal.resource.v1_0.ObjectEntryRelatedObjectsResourceImpl;
import com.liferay.object.rest.internal.resource.v1_0.ObjectEntryResourceFactoryImpl;
import com.liferay.object.rest.internal.resource.v1_0.ObjectEntryResourceImpl;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManagerRegistry;
import com.liferay.object.rest.manager.v1_0.ObjectRelationshipElementsParser;
import com.liferay.object.rest.odata.entity.v1_0.provider.EntityModelProvider;
import com.liferay.object.rest.openapi.v1_0.ObjectEntryOpenAPIResource;
import com.liferay.object.rest.openapi.v1_0.ObjectEntryOpenAPIResourceProvider;
import com.liferay.object.rest.resource.v1_0.ObjectEntryResource;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectRelationshipService;
import com.liferay.object.system.JaxRsApplicationDescriptor;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.extension.ExtensionProviderRegistry;
import com.liferay.portal.vulcan.graphql.dto.GraphQLDTOContributor;
import com.liferay.portal.vulcan.resource.OpenAPIResource;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.cxf.jaxrs.ext.ContextProvider;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = ObjectDefinitionDeployer.class)
public class ObjectDefinitionDeployerImpl implements ObjectDefinitionDeployer {

	@Override
	public synchronized List<ServiceRegistration<?>> deploy(
		ObjectDefinition objectDefinition) {

		if (objectDefinition.isUnmodifiableSystemObject()) {
			_initSystemObjectDefinition(
				objectDefinition,
				_systemObjectDefinitionManagerRegistry.
					getSystemObjectDefinitionManager(
						objectDefinition.getName()));

			return Collections.emptyList();
		}

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(objectDefinition.getRESTContextPath());

		if (objectDefinitions == null) {
			objectDefinitions = new HashMap<>();

			_objectDefinitionsMap.put(
				objectDefinition.getRESTContextPath(), objectDefinitions);
		}

		_excludeMethods(objectDefinition, objectScopeProvider);

		_initCustomObjectDefinition(objectDefinition);

		objectDefinitions.put(
			objectDefinition.getCompanyId(), objectDefinition);

		return Collections.singletonList(
			_bundleContext.registerService(
				GraphQLDTOContributor.class,
				ObjectDefinitionGraphQLDTOContributor.of(
					_entityModelProvider, _extensionProviderRegistry,
					objectDefinition, _objectDefinitionLocalService,
					_objectEntryManagerRegistry.getObjectEntryManager(
						objectDefinition.getStorageType()),
					_objectFieldLocalService, _objectRelationshipLocalService,
					objectScopeProvider,
					_systemObjectDefinitionManagerRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"dto.name", objectDefinition.getDBTableName()
				).build()));
	}

	public ObjectDefinition getObjectDefinition(
			long companyId, String restContextPath)
		throws Exception {

		ObjectDefinition objectDefinition = null;

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(restContextPath);

		if (objectDefinitions != null) {
			objectDefinition = objectDefinitions.get(companyId);
		}

		if (objectDefinition == null) {
			throw new NoSuchObjectDefinitionException();
		}

		return objectDefinition;
	}

	@Override
	public synchronized void undeploy(ObjectDefinition objectDefinition) {
		if (objectDefinition.isUnmodifiableSystemObject()) {
			_undeploySystemObjectDefinition(objectDefinition);
		}
		else {
			_undeployCustomObjectDefinition(objectDefinition);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private ObjectEntryResourceImpl _createObjectEntryResourceImpl(
		ObjectDefinition objectDefinition) {

		return new ObjectEntryResourceImpl(
			_dtoConverterRegistry, _entityModelProvider, objectDefinition,
			_objectDefinitionLocalService, _objectEntryLocalService,
			_objectEntryManagerRegistry, _objectFieldLocalService,
			_objectRelationshipService, _objectScopeProviderRegistry,
			_systemObjectDefinitionManagerRegistry);
	}

	private void _disposeComponentInstances(String restContextPath) {
		List<ComponentInstance> componentInstances =
			_componentInstancesMap.remove(restContextPath);

		if (componentInstances != null) {
			for (ComponentInstance componentInstance : componentInstances) {
				componentInstance.dispose();
			}
		}
	}

	private void _excludeMethods(
		ObjectDefinition objectDefinition,
		ObjectScopeProvider objectScopeProvider) {

		try {
			String factoryPid =
				"com.liferay.portal.vulcan.internal.configuration." +
					"VulcanCompanyConfiguration";

			Configuration configuration =
				_configurationAdmin.createFactoryConfiguration(
					factoryPid, StringPool.QUESTION);

			Method[] methods = BaseObjectEntryResourceImpl.class.getMethods();

			List<String> excludedOperationIds = new ArrayList<>();

			for (Method method : methods) {
				Path path = method.getAnnotation(Path.class);

				if (path == null) {
					continue;
				}

				String value = path.value();

				boolean groupAware = objectScopeProvider.isGroupAware();
				boolean hasScope = value.contains("scopes");

				if ((!groupAware && hasScope) ||
					(groupAware && !hasScope &&
					 !value.startsWith("/{objectEntryId}")) ||
					(objectDefinition.isRootDescendantNode() &&
					 value.endsWith("/permissions"))) {

					excludedOperationIds.add(method.getName());
				}
			}

			configuration.update(
				HashMapDictionaryBuilder.put(
					ExtendedObjectClassDefinition.Scope.COMPANY.
						getPropertyKey(),
					String.valueOf(objectDefinition.getCompanyId())
				).put(
					"excludedOperationIds",
					StringUtil.merge(excludedOperationIds, ",")
				).put(
					"path", objectDefinition.getRESTContextPath()
				).build());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}
	}

	private void _initCustomObjectDefinition(
		ObjectDefinition objectDefinition) {

		String restContextPath = objectDefinition.getRESTContextPath();

		List<String> companyIds = _restContextPathCompanyIds.computeIfAbsent(
			restContextPath, key -> new ArrayList<>());

		companyIds.add(String.valueOf(objectDefinition.getCompanyId()));

		String osgiJaxRsName = objectDefinition.getOSGiJaxRsName();

		Dictionary<String, Object> properties =
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", companyIds
			).put(
				"liferay.filter.disabled", true
			).put(
				"liferay.jackson", false
			).put(
				"liferay.objects", true
			).put(
				"osgi.jaxrs.application.base",
				objectDefinition.getRESTContextPath()
			).put(
				"osgi.jaxrs.extension.select",
				"(osgi.jaxrs.name=Liferay.Vulcan)"
			).put(
				"osgi.jaxrs.name", osgiJaxRsName
			).build();

		_applicationPropertiesMap.put(restContextPath, properties);

		ServiceRegistration<Application> applicationServiceRegistration =
			_applicationServiceRegistrationsMap.get(restContextPath);

		if (applicationServiceRegistration == null) {
			_applicationServiceRegistrationsMap.put(
				restContextPath,
				_bundleContext.registerService(
					Application.class,
					new ObjectEntryApplication(
						_objectEntryOpenAPIResourceProvider),
					properties));
		}
		else {
			applicationServiceRegistration.setProperties(properties);
		}

		_scopedServiceRegistrationsMap.compute(
			restContextPath,
			(key1, serviceRegistrationsMap) -> {
				if (serviceRegistrationsMap == null) {
					serviceRegistrationsMap = new HashMap<>();
				}

				serviceRegistrationsMap.computeIfAbsent(
					objectDefinition.getCompanyId(),
					key2 -> Arrays.asList(
						_bundleContext.registerService(
							ObjectEntryOpenAPIResource.class,
							new ObjectEntryOpenAPIResourceImpl(
								_bundleContext, _dtoConverterRegistry,
								_objectActionLocalService, objectDefinition,
								_objectDefinitionLocalService,
								_objectEntryOpenAPIResourceProvider,
								_objectFieldLocalService,
								_objectRelationshipLocalService,
								_openAPIResource,
								_systemObjectDefinitionManagerRegistry),
							HashMapDictionaryBuilder.<String, Object>put(
								"companyId",
								String.valueOf(objectDefinition.getCompanyId())
							).put(
								"openapi.resource", "true"
							).put(
								"openapi.resource.key",
								objectDefinition.getName()
							).put(
								"openapi.resource.path",
								objectDefinition.getRESTContextPath()
							).build()),
						_bundleContext.registerService(
							ObjectEntryResource.class,
							new PrototypeServiceFactory<ObjectEntryResource>() {

								@Override
								public ObjectEntryResource getService(
									Bundle bundle,
									ServiceRegistration<ObjectEntryResource>
										serviceRegistration) {

									return _createObjectEntryResourceImpl(
										objectDefinition);
								}

								@Override
								public void ungetService(
									Bundle bundle,
									ServiceRegistration<ObjectEntryResource>
										serviceRegistration,
									ObjectEntryResource objectEntryResource) {
								}

							},
							HashMapDictionaryBuilder.<String, Object>put(
								"batch.engine.entity.class.name",
								ObjectEntry.class.getName() + "#" +
									objectDefinition.getName()
							).put(
								"batch.engine.task.item.delegate", "true"
							).put(
								"batch.engine.task.item.delegate.name",
								objectDefinition.getName()
							).put(
								"batch.planner.export.enabled", "true"
							).put(
								"batch.planner.import.enabled", "true"
							).put(
								"companyId", objectDefinition.getCompanyId()
							).build()),
						_bundleContext.registerService(
							ObjectRelationshipElementsParser.class,
							new ObjectEntry1toMObjectRelationshipElementsParserImpl(
								objectDefinition),
							HashMapDictionaryBuilder.<String, Object>put(
								"companyId", objectDefinition.getCompanyId()
							).build()),
						_bundleContext.registerService(
							ObjectRelationshipElementsParser.class,
							new ObjectEntryMtoMObjectRelationshipElementsParserImpl(
								objectDefinition),
							HashMapDictionaryBuilder.<String, Object>put(
								"companyId", objectDefinition.getCompanyId()
							).build())));

				return serviceRegistrationsMap;
			});

		properties = HashMapDictionaryBuilder.<String, Object>put(
			"api.version", "v1.0"
		).put(
			"companyId", companyIds
		).put(
			"entity.class.name",
			ObjectEntry.class.getName() + "#" +
				StringUtil.toLowerCase(objectDefinition.getName())
		).put(
			"osgi.jaxrs.application.select",
			"(osgi.jaxrs.name=" + osgiJaxRsName + ")"
		).put(
			"osgi.jaxrs.resource", "true"
		).build();

		_objectEntryResourcePropertiesMap.put(restContextPath, properties);

		ServiceRegistration<ObjectEntryResource>
			objectEntryResourceServiceRegistration =
				_objectEntryResourceServiceRegistrationsMap.get(
					restContextPath);

		if (objectEntryResourceServiceRegistration == null) {
			_objectEntryResourceServiceRegistrationsMap.put(
				restContextPath,
				_bundleContext.registerService(
					ObjectEntryResource.class,
					new PrototypeServiceFactory<ObjectEntryResource>() {

						@Override
						public ObjectEntryResource getService(
							Bundle bundle,
							ServiceRegistration<ObjectEntryResource>
								serviceRegistration) {

							return _createObjectEntryResourceImpl(null);
						}

						@Override
						public void ungetService(
							Bundle bundle,
							ServiceRegistration<ObjectEntryResource>
								serviceRegistration,
							ObjectEntryResource objectEntryResource) {
						}

					},
					properties));
		}
		else {
			objectEntryResourceServiceRegistration.setProperties(properties);
		}

		_serviceRegistrationsMap.computeIfAbsent(
			restContextPath,
			key -> ListUtil.concat(
				Arrays.asList(
					_bundleContext.registerService(
						ContextProvider.class,
						new ObjectDefinitionContextProvider(this, _portal),
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", "false"
						).put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJaxRsName + ")"
						).put(
							"osgi.jaxrs.extension", "true"
						).put(
							"osgi.jaxrs.name",
							objectDefinition.getOSGiJaxRsName(
								"ObjectDefinitionContextProvider")
						).build()),
					_bundleContext.registerService(
						ObjectEntryRelatedObjectsResourceImpl.class,
						new PrototypeServiceFactory
							<ObjectEntryRelatedObjectsResourceImpl>() {

							@Override
							public ObjectEntryRelatedObjectsResourceImpl
								getService(
									Bundle bundle,
									ServiceRegistration
										<ObjectEntryRelatedObjectsResourceImpl>
											serviceRegistration) {

								return new ObjectEntryRelatedObjectsResourceImpl(
									_objectDefinitionLocalService,
									_objectEntryManagerRegistry,
									_objectRelatedModelsProviderRegistry,
									_objectRelationshipLocalService);
							}

							@Override
							public void ungetService(
								Bundle bundle,
								ServiceRegistration
									<ObjectEntryRelatedObjectsResourceImpl>
										serviceRegistration,
								ObjectEntryRelatedObjectsResourceImpl
									objectEntryRelatedObjectsResourceImpl) {
							}

						},
						HashMapDictionaryBuilder.<String, Object>put(
							"api.version", "v1.0"
						).put(
							"entity.class.name",
							() -> {
								String lowerCaseName = StringUtil.toLowerCase(
									objectDefinition.getName());

								return ObjectEntry.class.getName() + "#" +
									lowerCaseName;
							}
						).put(
							"osgi.jaxrs.application.select",
							"(osgi.jaxrs.name=" + osgiJaxRsName + ")"
						).put(
							"osgi.jaxrs.resource", "true"
						).build()),
					_bundleContext.registerService(
						ObjectEntryResource.Factory.class,
						new ObjectEntryResourceFactoryImpl(
							_companyLocalService,
							_defaultPermissionCheckerFactory,
							_expressionConvert, _filterParserProvider,
							_groupLocalService, objectDefinition,
							() -> _createObjectEntryResourceImpl(null),
							_resourceActionLocalService,
							_resourcePermissionLocalService, _roleLocalService,
							_sortParserProvider, _userLocalService),
						HashMapDictionaryBuilder.<String, Object>put(
							"resource.locator.key",
							objectDefinition.getRESTContextPath() + "/" +
								objectDefinition.getShortName()
						).build())),
				_registerExceptionMappers(osgiJaxRsName)));
	}

	private void _initSystemObjectDefinition(
		ObjectDefinition objectDefinition,
		SystemObjectDefinitionManager systemObjectDefinitionManager) {

		if (systemObjectDefinitionManager == null) {
			return;
		}

		JaxRsApplicationDescriptor jaxRsApplicationDescriptor =
			systemObjectDefinitionManager.getJaxRsApplicationDescriptor();

		_componentInstancesMap.computeIfAbsent(
			jaxRsApplicationDescriptor.getRESTContextPath(),
			key -> Arrays.asList(
				_relatedObjectEntryResourceImplComponentFactory.newInstance(
					HashMapDictionaryBuilder.<String, Object>put(
						"api.version", "v1.0"
					).put(
						"osgi.jaxrs.application.select",
						() -> {
							String applicationName =
								jaxRsApplicationDescriptor.getApplicationName();

							return "(osgi.jaxrs.name=" + applicationName + ")";
						}
					).put(
						"osgi.jaxrs.resource", "true"
					).build())));

		_scopedServiceRegistrationsMap.compute(
			jaxRsApplicationDescriptor.getRESTContextPath(),
			(key1, serviceRegistrationsMap) -> {
				if (serviceRegistrationsMap == null) {
					serviceRegistrationsMap = new HashMap<>();
				}

				serviceRegistrationsMap.computeIfAbsent(
					objectDefinition.getCompanyId(),
					key2 -> Arrays.asList(
						_bundleContext.registerService(
							ObjectRelationshipElementsParser.class,
							new SystemObjectEntry1toMObjectRelationshipElementsParserImpl(
								objectDefinition),
							HashMapDictionaryBuilder.<String, Object>put(
								"companyId", objectDefinition.getCompanyId()
							).build()),
						_bundleContext.registerService(
							ObjectRelationshipElementsParser.class,
							new SystemObjectEntryMtoMObjectRelationshipElementsParserImpl(
								objectDefinition),
							HashMapDictionaryBuilder.<String, Object>put(
								"companyId", objectDefinition.getCompanyId()
							).build())));

				return serviceRegistrationsMap;
			});

		_serviceRegistrationsMap.computeIfAbsent(
			jaxRsApplicationDescriptor.getRESTContextPath(),
			key -> _registerExceptionMappers(
				jaxRsApplicationDescriptor.getApplicationName()));
	}

	private List<ServiceRegistration<?>> _registerExceptionMappers(
		String jaxRsApplicationName) {

		return TransformUtil.transform(
			Arrays.<Supplier<ExceptionMapper<?>>>asList(
				ObjectAssetCategoryExceptionMapper::new,
				ObjectEntryManagerHttpExceptionMapper::new,
				() -> new ObjectEntryCountExceptionMapper(_language),
				() -> new ObjectEntryStatusExceptionMapper(_language),
				() -> new ObjectEntryValuesExceptionMapper(_language),
				() -> new ObjectRelationshipDeletionTypeExceptionMapper(
					_language),
				() -> new RequiredObjectRelationshipExceptionMapper(_language),
				UnsupportedOperationExceptionMapper::new),
			exceptionMapperSupplier -> _bundleContext.registerService(
				(Class<ExceptionMapper<?>>)(Class<?>)ExceptionMapper.class,
				new PrototypeServiceFactory<ExceptionMapper<?>>() {

					@Override
					public ExceptionMapper<?> getService(
						Bundle bundle,
						ServiceRegistration<ExceptionMapper<?>>
							serviceRegistration) {

						return exceptionMapperSupplier.get();
					}

					@Override
					public void ungetService(
						Bundle bundle,
						ServiceRegistration<ExceptionMapper<?>>
							serviceRegistration,
						ExceptionMapper<?> exceptionMapper) {
					}

				},
				HashMapDictionaryBuilder.<String, Object>put(
					"osgi.jaxrs.application.select",
					StringBundler.concat(
						"(|(liferay.objects.exception.mapper=true)",
						"(osgi.jaxrs.name=", jaxRsApplicationName, "))")
				).put(
					"osgi.jaxrs.extension", "true"
				).build()));
	}

	private boolean _shouldUnregisterApplication(String restContextPath) {
		List<String> companyIds = _restContextPathCompanyIds.get(
			restContextPath);

		if (ListUtil.isNotEmpty(companyIds)) {
			return false;
		}

		Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
			_scopedServiceRegistrationsMap.get(restContextPath);

		if (MapUtil.isNotEmpty(serviceRegistrationsMap)) {
			return false;
		}

		return true;
	}

	private void _undeployCustomObjectDefinition(
		ObjectDefinition objectDefinition) {

		long companyId = objectDefinition.getCompanyId();

		String restContextPath = objectDefinition.getRESTContextPath();

		if (objectDefinition.getPreviousRESTContextPath() != null) {
			restContextPath = objectDefinition.getPreviousRESTContextPath();
		}

		_undeployObjectDefinitions(companyId, restContextPath);
		_undeployRestContextPathCompanyIds(companyId, restContextPath);
		_undeployScopedServiceRegistrationsMap(companyId, restContextPath);

		if (_shouldUnregisterApplication(restContextPath)) {
			_unregisterApplication(restContextPath);
		}
	}

	private void _undeployObjectDefinitions(
		long companyId, String restContextPath) {

		Map<Long, ObjectDefinition> objectDefinitions =
			_objectDefinitionsMap.get(restContextPath);

		if (objectDefinitions != null) {
			objectDefinitions.remove(companyId);

			if (objectDefinitions.isEmpty()) {
				_objectDefinitionsMap.remove(restContextPath);
			}
		}
	}

	private void _undeployRestContextPathCompanyIds(
		long companyId, String restContextPath) {

		List<String> companyIds = _restContextPathCompanyIds.get(
			restContextPath);

		if (companyIds != null) {
			companyIds.remove(String.valueOf(companyId));

			if (!companyIds.isEmpty()) {
				_updateServiceRegistrationProperties(
					restContextPath, _applicationPropertiesMap,
					(Map)_applicationServiceRegistrationsMap);
				_updateServiceRegistrationProperties(
					restContextPath, _objectEntryResourcePropertiesMap,
					(Map)_objectEntryResourceServiceRegistrationsMap);
			}
		}
	}

	private void _undeployScopedServiceRegistrationsMap(
		long companyId, String restContextPath) {

		Map<Long, List<ServiceRegistration<?>>> serviceRegistrationsMap =
			_scopedServiceRegistrationsMap.get(restContextPath);

		if (serviceRegistrationsMap != null) {
			List<ServiceRegistration<?>> serviceRegistrations =
				serviceRegistrationsMap.remove(companyId);

			if (serviceRegistrations != null) {
				for (ServiceRegistration<?> serviceRegistration :
						serviceRegistrations) {

					serviceRegistration.unregister();
				}
			}

			if (serviceRegistrationsMap.isEmpty()) {
				_scopedServiceRegistrationsMap.remove(restContextPath);
			}
		}
	}

	private void _undeploySystemObjectDefinition(
		ObjectDefinition objectDefinition) {

		SystemObjectDefinitionManager systemObjectDefinitionManager =
			_systemObjectDefinitionManagerRegistry.
				getSystemObjectDefinitionManager(objectDefinition.getName());

		if (systemObjectDefinitionManager == null) {
			return;
		}

		JaxRsApplicationDescriptor jaxRsApplicationDescriptor =
			systemObjectDefinitionManager.getJaxRsApplicationDescriptor();

		String restContextPath =
			jaxRsApplicationDescriptor.getRESTContextPath();

		_disposeComponentInstances(restContextPath);

		_undeployScopedServiceRegistrationsMap(
			objectDefinition.getCompanyId(), restContextPath);

		if (_shouldUnregisterApplication(restContextPath)) {
			_unregisterApplication(restContextPath);
		}
	}

	private void _unregisterApplication(String restContextPath) {
		ServiceRegistration<?> serviceRegistration1 =
			_applicationServiceRegistrationsMap.remove(restContextPath);

		if (serviceRegistration1 != null) {
			serviceRegistration1.unregister();
		}

		serviceRegistration1 =
			_objectEntryResourceServiceRegistrationsMap.remove(restContextPath);

		if (serviceRegistration1 != null) {
			serviceRegistration1.unregister();
		}

		List<ServiceRegistration<?>> serviceRegistrations =
			_serviceRegistrationsMap.remove(restContextPath);

		if (serviceRegistrations != null) {
			for (ServiceRegistration<?> serviceRegistration2 :
					serviceRegistrations) {

				serviceRegistration2.unregister();
			}
		}
	}

	private void _updateServiceRegistrationProperties(
		String key, Map<String, Dictionary<String, Object>> propertiesMap,
		Map<String, ServiceRegistration<?>> serviceRegistrationsMap) {

		ServiceRegistration<?> serviceRegistration =
			serviceRegistrationsMap.get(key);

		serviceRegistration.setProperties(propertiesMap.get(key));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectDefinitionDeployerImpl.class);

	private final Map<String, Dictionary<String, Object>>
		_applicationPropertiesMap = new HashMap<>();
	private final Map<String, ServiceRegistration<Application>>
		_applicationServiceRegistrationsMap = new HashMap<>();
	private BundleContext _bundleContext;

	@Reference
	private CompanyLocalService _companyLocalService;

	private final Map<String, List<ComponentInstance>> _componentInstancesMap =
		new HashMap<>();

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private PermissionCheckerFactory _defaultPermissionCheckerFactory;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private EntityModelProvider _entityModelProvider;

	@Reference(
		target = "(result.class.name=com.liferay.portal.kernel.search.filter.Filter)"
	)
	private ExpressionConvert<Filter> _expressionConvert;

	@Reference
	private ExtensionProviderRegistry _extensionProviderRegistry;

	@Reference
	private FilterParserProvider _filterParserProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private ObjectActionLocalService _objectActionLocalService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	private final Map<String, Map<Long, ObjectDefinition>>
		_objectDefinitionsMap = new HashMap<>();

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryManagerRegistry _objectEntryManagerRegistry;

	@Reference
	private ObjectEntryOpenAPIResourceProvider
		_objectEntryOpenAPIResourceProvider;

	private final Map<String, Dictionary<String, Object>>
		_objectEntryResourcePropertiesMap = new HashMap<>();
	private final Map<String, ServiceRegistration<ObjectEntryResource>>
		_objectEntryResourceServiceRegistrationsMap = new HashMap<>();

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelatedModelsProviderRegistry
		_objectRelatedModelsProviderRegistry;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectRelationshipService _objectRelationshipService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private OpenAPIResource _openAPIResource;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(component.factory=com.liferay.object.rest.internal.resource.v1_0.RelatedObjectEntryResourceImpl)"
	)
	private ComponentFactory _relatedObjectEntryResourceImplComponentFactory;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	private final Map<String, List<String>> _restContextPathCompanyIds =
		new HashMap<>();

	@Reference
	private RoleLocalService _roleLocalService;

	private final Map<String, Map<Long, List<ServiceRegistration<?>>>>
		_scopedServiceRegistrationsMap = new HashMap<>();
	private final Map<String, List<ServiceRegistration<?>>>
		_serviceRegistrationsMap = new HashMap<>();

	@Reference
	private SortParserProvider _sortParserProvider;

	@Reference
	private SystemObjectDefinitionManagerRegistry
		_systemObjectDefinitionManagerRegistry;

	@Reference
	private UserLocalService _userLocalService;

}