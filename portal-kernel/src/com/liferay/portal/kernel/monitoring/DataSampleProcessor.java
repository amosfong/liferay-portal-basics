/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.monitoring;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface DataSampleProcessor<T extends DataSample> {

	public void processDataSample(T dataSample) throws MonitoringException;

}