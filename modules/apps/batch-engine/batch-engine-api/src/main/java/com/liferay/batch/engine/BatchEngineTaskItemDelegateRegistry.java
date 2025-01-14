/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Igor Beslic
 */
@ProviderType
public interface BatchEngineTaskItemDelegateRegistry {

	public BatchEngineTaskItemDelegate<?> getBatchEngineTaskItemDelegate(
		long companyId, String itemClassName, String taskItemDelegateName);

}