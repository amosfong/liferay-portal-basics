/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.manager.v1_0;

/**
 * @author Feliphe Marinho
 */
public class DefaultObjectEntryManagerProvider {

	public static DefaultObjectEntryManager provide(
		ObjectEntryManager objectEntryManager) {

		if (!(objectEntryManager instanceof DefaultObjectEntryManager)) {
			throw new UnsupportedOperationException();
		}

		return (DefaultObjectEntryManager)objectEntryManager;
	}

}