/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for GenericMethodsEntry. This utility wraps
 * <code>com.liferay.portal.tools.service.builder.test.service.impl.GenericMethodsEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GenericMethodsEntryLocalService
 * @generated
 */
public class GenericMethodsEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.tools.service.builder.test.service.impl.GenericMethodsEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static <E extends Exception> void typeParameterAndBoundMethod(
			java.util.function.BiConsumer<String, E> biConsumer)
		throws E {

		getService().typeParameterAndBoundMethod(biConsumer);
	}

	public static <T> void typeParameterMethod(
			java.util.function.Consumer<T> consumer)
		throws Exception {

		getService().typeParameterMethod(consumer);
	}

	public static <T, E extends Exception> List<T> typeParametersAndBoundMethod(
		java.util.function.BiFunction<Long, T, E> biFunction,
		java.util.function.BiConsumer<Long, E> biConsumer) {

		return getService().typeParametersAndBoundMethod(
			biFunction, biConsumer);
	}

	public static <N extends Number, E extends Exception> List<N>
		typeParametersAndBoundsMethod(
			java.util.function.BiFunction<Long, N, E> biFunction,
			java.util.function.BiConsumer<Long, N> biConsumer) {

		return getService().typeParametersAndBoundsMethod(
			biFunction, biConsumer);
	}

	public static
		<N extends Number & java.util.function.ObjIntConsumer,
		 E extends Exception & Serializable> List<N>
			 typeParametersAndMultipleBoundsMethod(
			 	java.util.function.BiFunction<Long, N, E> biFunction,
			 	java.util.function.BiConsumer<Long, N> biConsumer) {

		return getService().typeParametersAndMultipleBoundsMethod(
			biFunction, biConsumer);
	}

	public static GenericMethodsEntryLocalService getService() {
		return _service;
	}

	public static void setService(GenericMethodsEntryLocalService service) {
		_service = service;
	}

	private static volatile GenericMethodsEntryLocalService _service;

}