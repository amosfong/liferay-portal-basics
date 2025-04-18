/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskDueDateException extends WorkflowException {

	public WorkflowTaskDueDateException() {
	}

	public WorkflowTaskDueDateException(String msg) {
		super(msg);
	}

	public WorkflowTaskDueDateException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public WorkflowTaskDueDateException(Throwable throwable) {
		super(throwable);
	}

}