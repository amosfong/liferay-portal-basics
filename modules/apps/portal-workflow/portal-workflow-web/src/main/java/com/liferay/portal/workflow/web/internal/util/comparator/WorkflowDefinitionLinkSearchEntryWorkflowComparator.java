/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.web.internal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.web.internal.search.WorkflowDefinitionLinkSearchEntry;

/**
 * @author Leonardo Barros
 */
public class WorkflowDefinitionLinkSearchEntryWorkflowComparator
	extends OrderByComparator<WorkflowDefinitionLinkSearchEntry> {

	public static WorkflowDefinitionLinkSearchEntryWorkflowComparator
		getInstance(boolean ascending) {

		if (ascending) {
			return _INSTANCE_ASCENDING;
		}

		return _INSTANCE_DESCENDING;
	}

	@Override
	public int compare(
		WorkflowDefinitionLinkSearchEntry workflowDefinitionLinkSearchEntry1,
		WorkflowDefinitionLinkSearchEntry workflowDefinitionLinkSearchEntry2) {

		String workflowDefinitionLabel1 = StringUtil.toLowerCase(
			workflowDefinitionLinkSearchEntry1.getWorkflowDefinitionLabel());
		String workflowDefinitionLabel2 = StringUtil.toLowerCase(
			workflowDefinitionLinkSearchEntry2.getWorkflowDefinitionLabel());

		int value = workflowDefinitionLabel1.compareTo(
			workflowDefinitionLabel2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	private WorkflowDefinitionLinkSearchEntryWorkflowComparator(
		boolean ascending) {

		_ascending = ascending;
	}

	private static final WorkflowDefinitionLinkSearchEntryWorkflowComparator
		_INSTANCE_ASCENDING =
			new WorkflowDefinitionLinkSearchEntryWorkflowComparator(true);

	private static final WorkflowDefinitionLinkSearchEntryWorkflowComparator
		_INSTANCE_DESCENDING =
			new WorkflowDefinitionLinkSearchEntryWorkflowComparator(false);

	private final boolean _ascending;

}