/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.svg4everybody.web.internal.servlet.taglib;

import com.liferay.portal.kernel.content.security.policy.ContentSecurityPolicyNonceProviderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilderFactory;
import com.liferay.portal.url.builder.BundleScriptAbsolutePortalURLBuilder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryce Osterhaus
 */
@Component(
	property = "service.ranking:Integer=" + Integer.MAX_VALUE,
	service = DynamicInclude.class
)
public class SVG4EverybodyTopHeadDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		boolean cdnDynamicResourcesEnabled = true;

		try {
			cdnDynamicResourcesEnabled = _portal.isCDNDynamicResourcesEnabled(
				httpServletRequest);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to verify if CDN dynamic resources are enabled",
					portalException);
			}
		}

		boolean cdnHostEnabled = false;

		try {
			String cdnHost = _portal.getCDNHost(httpServletRequest);

			cdnHostEnabled = !cdnHost.isEmpty();
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get CDN host", portalException);
			}
		}

		if (!cdnHostEnabled) {
			return;
		}

		PrintWriter printWriter = httpServletResponse.getWriter();

		AbsolutePortalURLBuilder absolutePortalURLBuilder =
			_absolutePortalURLBuilderFactory.getAbsolutePortalURLBuilder(
				httpServletRequest);

		for (String jsFileName : _JS_FILE_NAMES) {
			printWriter.print("<script");
			printWriter.write(
				ContentSecurityPolicyNonceProviderUtil.getNonceAttribute(
					httpServletRequest));
			printWriter.print(" data-senna-track=\"permanent\" src=\"");

			BundleScriptAbsolutePortalURLBuilder
				bundleScriptAbsolutePortalURLBuilder =
					absolutePortalURLBuilder.forBundleScript(
						_bundleContext.getBundle(), jsFileName);

			if (!cdnDynamicResourcesEnabled) {
				bundleScriptAbsolutePortalURLBuilder.ignoreCDNHost();
			}

			printWriter.print(bundleScriptAbsolutePortalURLBuilder.build());

			printWriter.println("\" type=\"text/javascript\"></script>");
		}
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register("/html/common/themes/top_head.jsp#pre");
	}

	@Activate
	@Modified
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private static final String[] _JS_FILE_NAMES = {"/index.js"};

	private static final Log _log = LogFactoryUtil.getLog(
		SVG4EverybodyTopHeadDynamicInclude.class);

	@Reference
	private AbsolutePortalURLBuilderFactory _absolutePortalURLBuilderFactory;

	private volatile BundleContext _bundleContext;

	@Reference
	private Portal _portal;

}