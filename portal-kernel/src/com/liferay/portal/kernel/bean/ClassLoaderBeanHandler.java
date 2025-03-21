/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.bean;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.lang.ThreadContextClassLoaderUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class ClassLoaderBeanHandler implements InvocationHandler {

	public ClassLoaderBeanHandler(Object bean, ClassLoader classLoader) {
		_bean = bean;
		_classLoader = classLoader;
	}

	public Object getBean() {
		return _bean;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		ClassLoader classLoader = _classLoader;

		if (classLoader == null) {
			Thread currentThread = Thread.currentThread();

			classLoader = currentThread.getContextClassLoader();
		}

		try (SafeCloseable safeCloseable = ThreadContextClassLoaderUtil.swap(
				classLoader)) {

			if (method.getDeclaringClass() == Object.class) {
				String methodName = method.getName();

				if (methodName.equals("equals")) {
					if (proxy == arguments[0]) {
						return true;
					}

					return false;
				}
			}

			return method.invoke(_bean, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
	}

	private final Object _bean;
	private final ClassLoader _classLoader;

}