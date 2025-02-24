/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.frontend.data.set.filter;

import com.liferay.change.tracking.web.internal.constants.PublicationsFDSNames;
import com.liferay.frontend.data.set.filter.BaseSelectionFDSFilter;
import com.liferay.frontend.data.set.filter.FDSFilter;
import com.liferay.frontend.data.set.filter.SelectionFDSFilterItem;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "frontend.data.set.name=" + PublicationsFDSNames.PUBLICATIONS_CHANGES,
	service = FDSFilter.class
)
public class WorkflowStatusSelectionFDSFilter extends BaseSelectionFDSFilter {

	@Override
	public String getId() {
		return "status";
	}

	@Override
	public String getLabel() {
		return "status";
	}

	@Override
	public List<SelectionFDSFilterItem> getSelectionFDSFilterItems(
		Locale locale) {

		return ListUtil.fromArray(
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_APPROVED,
				WorkflowConstants.STATUS_APPROVED),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_DENIED,
				WorkflowConstants.STATUS_DENIED),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_DRAFT, WorkflowConstants.STATUS_DRAFT),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_EXPIRED,
				WorkflowConstants.STATUS_EXPIRED),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_IN_TRASH,
				WorkflowConstants.STATUS_IN_TRASH),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_INACTIVE,
				WorkflowConstants.STATUS_INACTIVE),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_INCOMPLETE,
				WorkflowConstants.STATUS_INCOMPLETE),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_PENDING,
				WorkflowConstants.STATUS_PENDING),
			new SelectionFDSFilterItem(
				WorkflowConstants.LABEL_SCHEDULED,
				WorkflowConstants.STATUS_SCHEDULED));
	}

}