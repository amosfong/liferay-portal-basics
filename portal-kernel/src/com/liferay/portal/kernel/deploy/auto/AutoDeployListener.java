/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Miguel Pastor
 * @author Manuel de la Peña
 */
public interface AutoDeployListener {

	public int deploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException;

	public boolean isDeployable(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException;

}