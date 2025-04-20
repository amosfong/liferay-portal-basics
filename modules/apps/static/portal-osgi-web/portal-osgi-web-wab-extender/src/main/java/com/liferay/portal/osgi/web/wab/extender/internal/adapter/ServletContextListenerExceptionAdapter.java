/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.wab.extender.internal.adapter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Raymond Augé
 */
public class ServletContextListenerExceptionAdapter
	implements ServletContextListener {

	public ServletContextListenerExceptionAdapter(
		ServletContextListener servletContextListener,
		ServletContext servletContext) {

		_servletContextListener = servletContextListener;
		_servletContext = servletContext;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		_destroyContext();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_initializeContext();
	}

	public Exception getException() {
		return _exception;
	}

	private void _destroyContext() {
		try {
			_servletContextListener.contextDestroyed(
				new ServletContextEvent(_servletContext));
		}
		catch (Exception exception) {
			_exception = exception;
		}
	}

	private void _initializeContext() {
		try {
			_servletContextListener.contextInitialized(
				new ServletContextEvent(_servletContext));
		}
		catch (Exception exception) {
			_exception = exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServletContextListenerExceptionAdapter.class);

	private Exception _exception;
	private final ServletContext _servletContext;
	private final ServletContextListener _servletContextListener;

}