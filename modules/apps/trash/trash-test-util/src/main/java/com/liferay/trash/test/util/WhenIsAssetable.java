/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.test.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.model.ClassedModel;

/**
 * @author Cristina González
 */
public interface WhenIsAssetable {

	public AssetEntry fetchAssetEntry(ClassedModel classedModel)
		throws Exception;

	public boolean isAssetEntryVisible(ClassedModel classedModel, long classPK)
		throws Exception;

}