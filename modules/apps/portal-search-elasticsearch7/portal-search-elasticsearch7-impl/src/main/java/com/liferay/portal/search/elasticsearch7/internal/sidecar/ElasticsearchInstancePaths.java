/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.sidecar;

import java.nio.file.Path;

/**
 * @author André de Oliveira
 */
public interface ElasticsearchInstancePaths {

	public Path getDataPath();

	public Path getHomePath();

	public Path getWorkPath();

}