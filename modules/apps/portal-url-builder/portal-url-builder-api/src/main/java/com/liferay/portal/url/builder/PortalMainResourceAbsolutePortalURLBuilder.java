/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.url.builder;

import com.liferay.portal.url.builder.facet.BuildableAbsolutePortalURLBuilder;

/**
 * Builds a legacy portal main resource URL.
 *
 * <p>
 * Main resources live in {@code
 * com.liferay.portal.kernel.util.Portal#getPathMain()}.
 * </p>
 *
 * @author Iván Zaera Avellón
 */
public interface PortalMainResourceAbsolutePortalURLBuilder
	extends BuildableAbsolutePortalURLBuilder {
}