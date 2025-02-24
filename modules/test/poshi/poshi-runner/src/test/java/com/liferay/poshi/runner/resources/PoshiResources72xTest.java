/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.poshi.runner.resources;

import org.junit.Test;

/**
 * @author Kenji Heigel
 */
public class PoshiResources72xTest extends BasePoshiResourcesTestCase {

	@Override
	@Test
	public void testLatestVersion() throws Exception {
		runTest("7.2.x");
	}

}