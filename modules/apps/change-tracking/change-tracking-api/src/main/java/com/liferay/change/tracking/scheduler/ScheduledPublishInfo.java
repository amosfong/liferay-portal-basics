/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.scheduler;

import com.liferay.change.tracking.model.CTCollection;

import java.util.Date;

/**
 * @author Preston Crary
 */
public class ScheduledPublishInfo {

	public ScheduledPublishInfo(
		CTCollection ctCollection, String jobName, Date startDate,
		long userId) {

		_ctCollection = ctCollection;
		_jobName = jobName;
		_startDate = startDate;
		_userId = userId;
	}

	public CTCollection getCTCollection() {
		return _ctCollection;
	}

	public String getJobName() {
		return _jobName;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public long getUserId() {
		return _userId;
	}

	private final CTCollection _ctCollection;
	private final String _jobName;
	private final Date _startDate;
	private final long _userId;

}