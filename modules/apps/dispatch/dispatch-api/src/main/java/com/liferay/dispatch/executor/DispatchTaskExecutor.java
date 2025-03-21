/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dispatch.executor;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

/**
 * @author Matija Petanjek
 */
public interface DispatchTaskExecutor {

	/**
	 * This method execute the scheduled task
	 *
	 * @param dispatchTriggerId
	 * @throws IOException
	 * @throws PortalException
	 */
	public void execute(long dispatchTriggerId)
		throws IOException, PortalException;

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	public String getName();

	public default boolean isClusterModeSingle() {
		return false;
	}

	public default boolean isHiddenInUI() {
		return false;
	}

}