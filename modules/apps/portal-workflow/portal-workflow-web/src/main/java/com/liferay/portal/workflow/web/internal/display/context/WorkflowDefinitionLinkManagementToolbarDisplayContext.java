/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.constants.WorkflowPortletKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class WorkflowDefinitionLinkManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public WorkflowDefinitionLinkManagementToolbarDisplayContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		SearchContainer<?> searchContainer) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			searchContainer);
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).buildString();
	}

	@Override
	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			httpServletRequest, WorkflowPortletKeys.CONTROL_PANEL_WORKFLOW,
			"definition-link-order-by-col", "resource");

		return _orderByCol;
	}

	@Override
	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			httpServletRequest, WorkflowPortletKeys.CONTROL_PANEL_WORKFLOW,
			"definition-link-order-by-type", "asc");

		return _orderByType;
	}

	@Override
	public String getSearchActionURL() {
		return String.valueOf(getPortletURL());
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"resource", "workflow"};
	}

	private String _orderByCol;
	private String _orderByType;

}