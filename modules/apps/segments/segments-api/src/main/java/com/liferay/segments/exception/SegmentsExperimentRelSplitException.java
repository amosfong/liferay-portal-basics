/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Eduardo García
 */
public class SegmentsExperimentRelSplitException extends PortalException {

	public SegmentsExperimentRelSplitException() {
	}

	public SegmentsExperimentRelSplitException(String msg) {
		super(msg);
	}

	public SegmentsExperimentRelSplitException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public SegmentsExperimentRelSplitException(Throwable throwable) {
		super(throwable);
	}

}