/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Contains a list of the available routes and handles conversion of URLs to
 * parameter maps and vice versa.
 *
 * <p>
 * The priority of a route is based on when it was added to the router. The
 * first route has the highest priority and a URL path will always be matched
 * against it first. If the first route does not match, the second will be
 * tried, continuing until either a match is found or all the routes have been
 * tested.
 * </p>
 *
 * <p>
 * When choosing the order in which to place a list of routes, the general rule
 * is to sort the routes from least general to most general. The simplest way of
 * determining the generality of a route is by counting the number of capturing
 * fragments in it.
 * </p>
 *
 * @author Connor McKay
 * @author Brian Wing Shun Chan
 * @see    Route
 * @see    DefaultFriendlyURLMapper
 */
@ProviderType
public interface Router {

	/**
	 * Generates a new route from its pattern string.
	 *
	 * @param  pattern the route pattern string
	 * @return the generated route
	 */
	public Route addRoute(String pattern);

	public List<Route> getRoutes();

	/**
	 * Generates a URL from the parameter map using the available routes.
	 *
	 * @param  parameters the parameter map
	 * @return the URL path, or <code>null</code> if an applicable route was not
	 *         found
	 */
	public String parametersToUrl(Map<String, String> parameters);

	/**
	 * Parses a URL into a parameter map using the available routes.
	 *
	 * @param  url the URL to be parsed
	 * @param  parameters the parameter map to be populated
	 * @return <code>true</code> if a match was found and
	 *         <code>parameters</code> was populated; <code>false</code>
	 *         otherwise
	 */
	public boolean urlToParameters(String url, Map<String, String> parameters);

}