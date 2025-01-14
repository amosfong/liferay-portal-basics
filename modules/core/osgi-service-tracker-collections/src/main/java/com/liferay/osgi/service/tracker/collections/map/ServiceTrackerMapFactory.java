/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osgi.service.tracker.collections.map;

import com.liferay.osgi.service.tracker.collections.internal.map.MultiValueServiceTrackerBucketFactory;
import com.liferay.osgi.service.tracker.collections.internal.map.ServiceTrackerMapImpl;
import com.liferay.osgi.service.tracker.collections.internal.map.SingleValueServiceTrackerBucketFactory;
import com.liferay.osgi.service.tracker.collections.internal.osgi.util.tracker.DefaultServiceTrackerCustomizer;

import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Carlos Sierra Andrés
 */
public class ServiceTrackerMapFactory {

	public static <S> ServiceTrackerMap<String, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<S> clazz, String propertyKey) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, "(" + propertyKey + "=*)",
			new PropertyServiceReferenceMapper<String, S>(propertyKey),
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new MultiValueServiceTrackerBucketFactory<S, S>(), null);
	}

	public static <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new MultiValueServiceTrackerBucketFactory<S, S>(), null);
	}

	public static <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		Comparator<ServiceReference<S>> comparator) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new MultiValueServiceTrackerBucketFactory<S, S>(comparator), null);
	}

	public static <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		Comparator<ServiceReference<S>> comparator,
		ServiceTrackerMapListener<K, S, List<S>> serviceTrackerMapListener) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new MultiValueServiceTrackerBucketFactory<S, S>(comparator),
			serviceTrackerMapListener);
	}

	public static <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, S> serviceReferenceMapper,
		ServiceTrackerMapListener<K, S, List<S>> serviceTrackerMapListener) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new MultiValueServiceTrackerBucketFactory<S, S>(),
			serviceTrackerMapListener);
	}

	public static <K, SR, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			serviceTrackerCustomizer,
			new MultiValueServiceTrackerBucketFactory<SR, S>(), null);
	}

	public static <K, SR, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer,
		Comparator<ServiceReference<SR>> comparator) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			serviceTrackerCustomizer,
			new MultiValueServiceTrackerBucketFactory<SR, S>(comparator), null);
	}

	public static <K, SR, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer,
		Comparator<ServiceReference<SR>> comparator,
		ServiceTrackerMapListener<K, S, List<S>> serviceTrackerMapListener) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			serviceTrackerCustomizer,
			new MultiValueServiceTrackerBucketFactory<SR, S>(comparator),
			serviceTrackerMapListener);
	}

	public static <SR, S> ServiceTrackerMap<String, List<S>> openMultiValueMap(
		BundleContext bundleContext, Class<SR> clazz, String propertyKey,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, "(" + propertyKey + "=*)",
			new PropertyServiceReferenceMapper<String, SR>(propertyKey),
			serviceTrackerCustomizer,
			new MultiValueServiceTrackerBucketFactory<SR, S>(), null);
	}

	public static <S> ServiceTrackerMap<String, S> openSingleValueMap(
		BundleContext bundleContext, Class<S> clazz, String propertyKey) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, "(" + propertyKey + "=*)",
			new PropertyServiceReferenceMapper<String, S>(propertyKey),
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new SingleValueServiceTrackerBucketFactory<S, S>(), null);
	}

	public static <K, S> ServiceTrackerMap<K, S> openSingleValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new SingleValueServiceTrackerBucketFactory<S, S>(), null);
	}

	public static <K, S> ServiceTrackerMap<K, S> openSingleValueMap(
		BundleContext bundleContext, Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		Comparator<ServiceReference<S>> comparator) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new SingleValueServiceTrackerBucketFactory<S, S>(comparator), null);
	}

	public static <S> ServiceTrackerMap<String, S> openSingleValueMap(
		BundleContext bundleContext, Class<S> clazz, String propertyKey,
		ServiceTrackerMapListener<String, S, S> serviceTrackerMapListener) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, "(" + propertyKey + "=*)",
			new PropertyServiceReferenceMapper<String, S>(propertyKey),
			new DefaultServiceTrackerCustomizer<S>(bundleContext),
			new SingleValueServiceTrackerBucketFactory<S, S>(),
			serviceTrackerMapListener);
	}

	public static <K, SR, S> ServiceTrackerMap<K, S> openSingleValueMap(
		BundleContext bundleContext, Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			serviceTrackerCustomizer,
			new SingleValueServiceTrackerBucketFactory<SR, S>(), null);
	}

	public static <K, SR, S> ServiceTrackerMap<K, S> openSingleValueMap(
		BundleContext bundleContext, Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer,
		Comparator<ServiceReference<SR>> comparator) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, filterString, serviceReferenceMapper,
			serviceTrackerCustomizer,
			new SingleValueServiceTrackerBucketFactory<SR, S>(comparator),
			null);
	}

	public static <SR, S> ServiceTrackerMap<String, S> openSingleValueMap(
		BundleContext bundleContext, Class<SR> clazz, String propertyKey,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer) {

		return new ServiceTrackerMapImpl<>(
			bundleContext, clazz, "(" + propertyKey + "=*)",
			new PropertyServiceReferenceMapper<String, SR>(propertyKey),
			serviceTrackerCustomizer,
			new SingleValueServiceTrackerBucketFactory<SR, S>(), null);
	}

}