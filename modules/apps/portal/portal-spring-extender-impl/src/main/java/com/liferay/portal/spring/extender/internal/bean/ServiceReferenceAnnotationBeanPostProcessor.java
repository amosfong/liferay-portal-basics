/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.extender.internal.bean;

import com.liferay.portal.spring.extender.service.ServiceReference;

import java.lang.reflect.Field;

import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.util.ReflectionUtils;

/**
 * @author Miguel Pastor
 */
public class ServiceReferenceAnnotationBeanPostProcessor
	implements ApplicationListener<ContextClosedEvent>, BeanPostProcessor {

	public ServiceReferenceAnnotationBeanPostProcessor(
		BundleContext bundleContext) {

		_bundleContext = bundleContext;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		for (org.osgi.framework.ServiceReference<?> serviceReference :
				_serviceReferences) {

			_bundleContext.ungetService(serviceReference);
		}

		_serviceReferences.clear();
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
		throws BeansException {

		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
		throws BeansException {

		_autoInject(bean, bean.getClass());

		return bean;
	}

	private void _autoInject(Object targetBean, Class<?> beanClass) {
		if ((beanClass == null) || beanClass.isInterface()) {
			return;
		}

		Field[] fields = beanClass.getDeclaredFields();

		for (Field field : fields) {
			ServiceReference serviceReference = field.getAnnotation(
				ServiceReference.class);

			if (serviceReference == null) {
				continue;
			}

			org.osgi.framework.ServiceReference<?> osgiServiceReference = null;

			try {
				String filterString = serviceReference.filterString();

				if (filterString.isEmpty()) {
					Class<?> typeClass = serviceReference.type();

					osgiServiceReference = _bundleContext.getServiceReference(
						typeClass.getName());
				}
				else {
					Class<?> typeClass = serviceReference.type();

					org.osgi.framework.ServiceReference<?>[] serviceReferences =
						_bundleContext.getServiceReferences(
							typeClass.getName(), filterString);

					if (serviceReferences != null) {
						osgiServiceReference = serviceReferences[0];
					}
				}

				ReflectionUtils.makeAccessible(field);

				field.set(
					targetBean,
					_bundleContext.getService(osgiServiceReference));
			}
			catch (Throwable throwable) {
				throw new BeanCreationException(
					beanClass.getName(),
					"Unable to inject bean reference fields", throwable);
			}

			_serviceReferences.add(osgiServiceReference);
		}

		_autoInject(targetBean, beanClass.getSuperclass());
	}

	private final BundleContext _bundleContext;
	private final Set<org.osgi.framework.ServiceReference<?>>
		_serviceReferences = new HashSet<>();

}