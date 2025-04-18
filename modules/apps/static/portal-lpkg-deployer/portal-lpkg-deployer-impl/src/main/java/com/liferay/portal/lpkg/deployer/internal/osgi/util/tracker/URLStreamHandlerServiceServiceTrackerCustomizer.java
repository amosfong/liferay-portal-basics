/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lpkg.deployer.internal.osgi.util.tracker;

import com.liferay.osgi.util.bundle.BundleStartLevelUtil;
import com.liferay.portal.lpkg.deployer.internal.WABWrapperUtil;

import java.net.URL;
import java.net.URLConnection;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.url.AbstractURLStreamHandlerService;
import org.osgi.service.url.URLStreamHandlerService;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Shuyang Zhou
 */
public class URLStreamHandlerServiceServiceTrackerCustomizer
	implements ServiceTrackerCustomizer<URLStreamHandlerService, Bundle> {

	public URLStreamHandlerServiceServiceTrackerCustomizer(
		BundleContext bundleContext, String contextName, URL lpkgURL,
		int startLevel) {

		_bundleContext = bundleContext;
		_contextName = contextName;
		_lpkgURL = lpkgURL;
		_startLevel = startLevel;
	}

	@Override
	public Bundle addingService(
		ServiceReference<URLStreamHandlerService> serviceReference) {

		// Both org.eclipse.osgi.internal.url.URLStreamHandlerFactoryImpl and
		// WARBundleWrapperBundleActivator are both tracking
		// com.liferay.portal.osgi.web.wab.generator.internal.handler.
		// WabURLStreamHandlerService. In the case where
		// WARBundleWrapperBundleActivator is notified before
		// URLStreamHandlerFactoryImpl, then a MalformedURLException will be
		// thrown. To overcome this race condition, we must construct the WAB
		// URL without validation and without opening the URL directly.

		AbstractURLStreamHandlerService abstractURLStreamHandlerService =
			(AbstractURLStreamHandlerService)_bundleContext.getService(
				serviceReference);

		try {

			// The WAB URL must not change over reboots. See
			// LPKGBundleTrackerCustomizer#_toWARWrapperBundle.

			Bundle bundle = _bundleContext.getBundle();

			URL wabURL = WABWrapperUtil.generateWABLocationURL(
				_lpkgURL, bundle.getVersion(), _contextName,
				abstractURLStreamHandlerService);

			URLConnection urlConnection =
				abstractURLStreamHandlerService.openConnection(wabURL);

			String location = wabURL.toExternalForm();

			Bundle newBundle = _bundleContext.getBundle(location);

			if (newBundle == null) {
				newBundle = _bundleContext.installBundle(
					location, urlConnection.getInputStream());

				if (newBundle.getState() != Bundle.UNINSTALLED) {
					BundleStartLevelUtil.setStartLevelAndStart(
						newBundle, _startLevel, _bundleContext);
				}
			}

			return newBundle;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifiedService(
		ServiceReference<URLStreamHandlerService> serviceReference,
		Bundle bundle) {
	}

	@Override
	public void removedService(
		ServiceReference<URLStreamHandlerService> serviceReference,
		Bundle bundle) {
	}

	private final BundleContext _bundleContext;
	private final String _contextName;
	private final URL _lpkgURL;
	private final int _startLevel;

}