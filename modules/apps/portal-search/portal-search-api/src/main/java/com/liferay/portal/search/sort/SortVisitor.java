/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.sort;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface SortVisitor<T> {

	public T visit(FieldSort fieldSort);

	public T visit(GeoDistanceSort geoDistanceSort);

	public T visit(ScoreSort scoreSort);

	public T visit(ScriptSort scriptSort);

}