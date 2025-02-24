/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.bean;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.lang.ThreadContextClassLoaderUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class VelocityBeanHandler implements InvocationHandler {

	public VelocityBeanHandler(Object bean, ClassLoader classLoader) {
		_bean = bean;
		_classLoader = classLoader;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try (SafeCloseable safeCloseable = ThreadContextClassLoaderUtil.swap(
				_classLoader)) {

			return method.invoke(_bean, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			if (_log.isDebugEnabled()) {
				_log.debug(invocationTargetException);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		VelocityBeanHandler.class);

	private final Object _bean;
	private final ClassLoader _classLoader;

}