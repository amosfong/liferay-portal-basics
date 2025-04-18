/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.extender.internal.bean;

import com.liferay.petra.reflect.AnnotationLocator;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ModuleFrameworkPropsValues;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.spring.aop.AopInvocationHandler;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Miguel Pastor
 * @author Preston Crary
 */
public class ApplicationContextServicePublisherUtil {

	public static List<ServiceRegistration<?>> registerContext(
		ConfigurableApplicationContext configurableApplicationContext,
		BundleContext bundleContext) {

		List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();

		ConfigurableListableBeanFactory configurableListableBeanFactory =
			configurableApplicationContext.getBeanFactory();

		Iterator<String> iterator =
			configurableListableBeanFactory.getBeanNamesIterator();

		iterator.forEachRemaining(
			beanName -> {
				try {
					ServiceRegistration<?> serviceRegistration =
						_registerService(
							bundleContext, beanName,
							configurableApplicationContext.getBean(beanName));

					if (serviceRegistration != null) {
						serviceRegistrations.add(serviceRegistration);
					}
				}
				catch (Exception exception) {
					_log.error(
						"Unable to register service " + beanName, exception);
				}
			});

		Bundle bundle = bundleContext.getBundle();

		ServiceRegistration<ApplicationContext> serviceRegistration =
			bundleContext.registerService(
				ApplicationContext.class, configurableApplicationContext,
				HashMapDictionaryBuilder.<String, Object>put(
					"org.springframework.context.service.name",
					bundle.getSymbolicName()
				).build());

		serviceRegistrations.add(serviceRegistration);

		return serviceRegistrations;
	}

	public static void unregisterContext(
		List<ServiceRegistration<?>> serviceRegistrations) {

		if (serviceRegistrations != null) {
			for (ServiceRegistration<?> serviceRegistration :
					serviceRegistrations) {

				serviceRegistration.unregister();
			}

			serviceRegistrations.clear();
		}
	}

	private static ServiceRegistration<?> _registerService(
		BundleContext bundleContext, String beanName, Object bean) {

		Class<?> clazz = bean.getClass();

		if (ProxyUtil.isProxyClass(clazz)) {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(bean);

			if (invocationHandler instanceof AopInvocationHandler) {
				AopInvocationHandler aopInvocationHandler =
					(AopInvocationHandler)invocationHandler;

				Object target = aopInvocationHandler.getTarget();

				clazz = target.getClass();
			}
		}

		OSGiBeanProperties osgiBeanProperties = AnnotationLocator.locate(
			clazz, OSGiBeanProperties.class);

		Set<String> names = OSGiBeanProperties.Service.interfaceNames(
			bean, osgiBeanProperties,
			ModuleFrameworkPropsValues.
				MODULE_FRAMEWORK_SERVICES_IGNORED_INTERFACES);

		if (names.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping registration because of an empty list of " +
						"interfaces");
			}

			return null;
		}

		Bundle bundle = bundleContext.getBundle();

		String symbolicName = bundle.getSymbolicName();

		HashMapDictionary<String, Object> properties =
			HashMapDictionaryBuilder.<String, Object>put(
				"bean.id", beanName
			).put(
				"origin.bundle.symbolic.name", symbolicName
			).build();

		if (osgiBeanProperties != null) {
			properties.putAll(
				OSGiBeanProperties.Convert.toMap(osgiBeanProperties));
		}

		if (bean instanceof BaseService) {
			Class<?> beanClass = bean.getClass();

			JSONWebService jsonWebService = beanClass.getAnnotation(
				JSONWebService.class);

			if (jsonWebService == null) {
				for (Class<?> interfaceClass : beanClass.getInterfaces()) {
					if ((interfaceClass == BaseService.class) ||
						!BaseService.class.isAssignableFrom(interfaceClass)) {

						continue;
					}

					jsonWebService = interfaceClass.getAnnotation(
						JSONWebService.class);

					if (jsonWebService != null) {
						break;
					}
				}
			}

			if (jsonWebService != null) {
				if (properties.get("json.web.service.context.name") == null) {
					properties.put(
						"json.web.service.context.name", symbolicName);
				}

				if (properties.get("json.web.service.context.path") == null) {
					String path = beanClass.getSimpleName();

					if (path.endsWith("ServiceImpl")) {
						path = path.substring(0, path.length() - 11);
					}

					properties.put("json.web.service.context.path", path);
				}
			}
		}

		return bundleContext.registerService(
			names.toArray(new String[0]), bean, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationContextServicePublisherUtil.class);

}