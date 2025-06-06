/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.template;

import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.osgi.service.tracker.collections.EagerServiceTrackerCustomizer;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.resource.bundle.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.resource.bundle.ClassResourceBundleLoader;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoader;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.language.LanguageResources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = TemplateHandlerRegistry.class)
public class TemplateHandlerRegistryImpl implements TemplateHandlerRegistry {

	@Override
	public long[] getClassNameIds() {
		return TransformUtil.transformToLongArray(
			_serviceTrackerMap.keySet(),
			_classNameLocalService::getClassNameId);
	}

	@Override
	public TemplateHandler getTemplateHandler(long classNameId) {
		ClassName className = _classNameLocalService.fetchByClassNameId(
			classNameId);

		if (className == null) {
			return null;
		}

		return _serviceTrackerMap.getService(className.getValue());
	}

	@Override
	public TemplateHandler getTemplateHandler(String className) {
		return _serviceTrackerMap.getService(className);
	}

	@Override
	public List<TemplateHandler> getTemplateHandlers() {
		return new ArrayList<>(_serviceTrackerMap.values());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, TemplateHandler.class, null,
			(serviceReference, emitter) -> {
				TemplateHandler templateHandler = bundleContext.getService(
					serviceReference);

				emitter.emit(templateHandler.getClassName());

				bundleContext.ungetService(serviceReference);
			},
			new TemplateHandlerServiceTrackerCustomizer());
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();

		_bundleContext = null;
	}

	private BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	private final Map<TemplateHandler, ServiceRegistration<?>>
		_serviceRegistrations = new ConcurrentHashMap<>();
	private ServiceTrackerMap<String, TemplateHandler> _serviceTrackerMap;

	@Reference
	private UserLocalService _userLocalService;

	private class TemplateHandlerPortalInstanceLifecycleListener
		extends BasePortalInstanceLifecycleListener {

		@Override
		public long getLastModifiedTime() {
			return _lastModifiedTime;
		}

		@Override
		public String getName() {
			return _name;
		}

		@Override
		public void portalInstanceRegistered(Company company) throws Exception {
			List<Element> templateElements =
				_templateHandler.getDefaultTemplateElements();

			if (templateElements.isEmpty()) {
				return;
			}

			long classNameId = _portal.getClassNameId(
				_templateHandler.getClassName());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGuestPermissions(true);

			Group group = _groupLocalService.getCompanyGroup(
				company.getCompanyId());

			serviceContext.setScopeGroupId(group.getGroupId());

			long userId = _userLocalService.getGuestUserId(
				company.getCompanyId());

			serviceContext.setUserId(userId);

			for (Element templateElement : templateElements) {
				String templateKey = templateElement.elementText(
					"template-key");

				DDMTemplate ddmTemplate =
					_ddmTemplateLocalService.fetchTemplate(
						group.getGroupId(), classNameId, templateKey);

				if ((ddmTemplate != null) &&
					((ddmTemplate.getUserId() != userId) ||
					 (ddmTemplate.getVersionUserId() != userId))) {

					continue;
				}

				Class<?> clazz = _templateHandler.getClass();

				String scriptFileName = templateElement.elementText(
					"script-file");

				String script = StringUtil.read(
					clazz.getClassLoader(), scriptFileName);

				if ((ddmTemplate != null) &&
					StringUtil.equals(script, ddmTemplate.getScript())) {

					continue;
				}

				ResourceBundleLoader resourceBundleLoader = null;

				Bundle bundle = FrameworkUtil.getBundle(clazz);

				if (bundle != null) {
					resourceBundleLoader = _getResourceBundleLoader(
						bundle.getSymbolicName());
				}
				else {
					resourceBundleLoader = new AggregateResourceBundleLoader(
						new ClassResourceBundleLoader(
							"content.Language", clazz.getClassLoader()),
						LanguageResources.PORTAL_RESOURCE_BUNDLE_LOADER);
				}

				Map<Locale, String> nameMap = getLocalizationMap(
					resourceBundleLoader, group.getGroupId(),
					templateElement.elementText("name"));
				Map<Locale, String> descriptionMap = getLocalizationMap(
					resourceBundleLoader, group.getGroupId(),
					templateElement.elementText("description"));

				String type = templateElement.elementText("type");

				if (type == null) {
					type = DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
				}

				String language = templateElement.elementText("language");

				boolean cacheable = GetterUtil.getBoolean(
					templateElement.elementText("cacheable"));

				if (ddmTemplate == null) {
					_ddmTemplateLocalService.addTemplate(
						null, userId, group.getGroupId(), classNameId, 0,
						_portal.getClassNameId(
							_CLASS_NAME_PORTLET_DISPLAY_TEMPLATE),
						templateKey, nameMap, descriptionMap, type, null,
						language, script, cacheable, false, null, null,
						serviceContext);
				}
				else {
					_ddmTemplateLocalService.updateTemplate(
						userId, ddmTemplate.getTemplateId(), 0, nameMap,
						descriptionMap, type, null, language, script, cacheable,
						false, null, null, serviceContext);
				}
			}
		}

		@Override
		public void portalInstanceUnregistered(Company company)
			throws Exception {
		}

		protected Map<Locale, String> getLocalizationMap(
			ResourceBundleLoader resourceBundleLoader, long groupId,
			String key) {

			Map<Locale, String> map = new HashMap<>();

			for (Locale locale : _language.getAvailableLocales(groupId)) {
				ResourceBundle resourceBundle =
					resourceBundleLoader.loadResourceBundle(locale);

				map.put(locale, _language.get(resourceBundle, key));
			}

			return map;
		}

		private TemplateHandlerPortalInstanceLifecycleListener(
			TemplateHandler templateHandler) {

			_templateHandler = templateHandler;

			Class<?> clazz = templateHandler.getClass();

			Bundle bundle = FrameworkUtil.getBundle(clazz);

			_lastModifiedTime = bundle.getLastModified();

			_name = StringBundler.concat(
				super.getName(), StringPool.POUND, clazz.getName());
		}

		private ResourceBundleLoader _getResourceBundleLoader(
			String bundleSymbolicName) {

			ResourceBundleLoader resourceBundleLoader =
				ResourceBundleLoaderUtil.
					getResourceBundleLoaderByBundleSymbolicName(
						bundleSymbolicName);

			if (resourceBundleLoader == null) {
				return ResourceBundleLoaderUtil.getPortalResourceBundleLoader();
			}

			return new AggregateResourceBundleLoader(
				resourceBundleLoader,
				ResourceBundleLoaderUtil.getPortalResourceBundleLoader());
		}

		private static final String _CLASS_NAME_PORTLET_DISPLAY_TEMPLATE =
			"com.liferay.portlet.display.template.PortletDisplayTemplate";

		private final long _lastModifiedTime;
		private final String _name;
		private final TemplateHandler _templateHandler;

	}

	private class TemplateHandlerServiceTrackerCustomizer
		implements EagerServiceTrackerCustomizer
			<TemplateHandler, TemplateHandler> {

		@Override
		public TemplateHandler addingService(
			ServiceReference<TemplateHandler> serviceReference) {

			TemplateHandler templateHandler = _bundleContext.getService(
				serviceReference);

			int serviceRanking = GetterUtil.getInteger(
				serviceReference.getProperty(Constants.SERVICE_RANKING));

			ServiceRegistration<?> serviceRegistration =
				_serviceRegistrations.put(
					templateHandler,
					_bundleContext.registerService(
						PortalInstanceLifecycleListener.class,
						new TemplateHandlerPortalInstanceLifecycleListener(
							templateHandler),
						MapUtil.singletonDictionary(
							Constants.SERVICE_RANKING, serviceRanking)));

			if (serviceRegistration != null) {
				serviceRegistration.unregister();
			}

			return templateHandler;
		}

		@Override
		public void modifiedService(
			ServiceReference<TemplateHandler> serviceReference,
			TemplateHandler templateHandler) {
		}

		@Override
		public void removedService(
			ServiceReference<TemplateHandler> serviceReference,
			TemplateHandler templateHandler) {

			ServiceRegistration<?> serviceRegistration =
				_serviceRegistrations.remove(templateHandler);

			if (serviceRegistration != null) {
				serviceRegistration.unregister();
			}

			_bundleContext.ungetService(serviceReference);
		}

	}

}