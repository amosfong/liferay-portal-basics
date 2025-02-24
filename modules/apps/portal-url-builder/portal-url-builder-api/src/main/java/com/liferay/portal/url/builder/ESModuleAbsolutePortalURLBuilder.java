/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.url.builder;

import com.liferay.portal.url.builder.facet.BuildableAbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.facet.CDNAwareAbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.facet.PathProxyAwareAbsolutePortalURLBuilder;

/**
 * Builds a URL to retrieve an ECMAScript Module file living inside a bundle.
 *
 * <p>
 * ESM scripts sometimes have additional parameters to account for RTL
 * support, language, etc. Those are automagically inferred from the request.
 * </p>
 *
 * @author Iván Zaera Avellón
 */
public interface ESModuleAbsolutePortalURLBuilder
	extends BuildableAbsolutePortalURLBuilder,
			CDNAwareAbsolutePortalURLBuilder<ESModuleAbsolutePortalURLBuilder>,
			PathProxyAwareAbsolutePortalURLBuilder
				<ESModuleAbsolutePortalURLBuilder> {
}