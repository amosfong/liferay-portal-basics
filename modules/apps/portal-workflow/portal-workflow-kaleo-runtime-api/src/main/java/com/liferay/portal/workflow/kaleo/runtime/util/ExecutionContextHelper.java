/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

/**
 * @author Michael C. Han
 */
public interface ExecutionContextHelper {

	public void checkKaleoInstanceComplete(ExecutionContext executionContext)
		throws PortalException;

	public void completeKaleoTimerInstances(ExecutionContext executionContext)
		throws PortalException;

	public String convert(ExecutionContext executionContext);

	public ExecutionContext convert(String json) throws Exception;

	public boolean isKaleoInstanceBlocked(ExecutionContext executionContext);

}