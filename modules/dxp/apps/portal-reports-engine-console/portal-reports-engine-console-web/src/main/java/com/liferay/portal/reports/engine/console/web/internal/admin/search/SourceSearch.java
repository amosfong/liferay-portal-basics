/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.web.internal.admin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.util.comparator.SourceCreateDateComparator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Rafael Praxedes
 */
public class SourceSearch extends SearchContainer<Source> {

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-sources";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("source-name");
			add("create-date");
		}
	};

	public SourceSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new SourceDisplayTerms(portletRequest),
			new SourceSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		SourceDisplayTerms definitionDisplayTerms =
			(SourceDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			SourceDisplayTerms.NAME, definitionDisplayTerms.getName());
		iteratorURL.setParameter(
			SourceDisplayTerms.DRIVER_URL,
			definitionDisplayTerms.getDriverUrl());

		setOrderByCol(
			SearchOrderByUtil.getOrderByCol(
				portletRequest, ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
				"create-date"));

		String orderByType = SearchOrderByUtil.getOrderByType(
			portletRequest, ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
			"asc");

		setOrderByComparator(_getSourceOrderByComparator(orderByType));
		setOrderByType(orderByType);
	}

	private OrderByComparator<Source> _getSourceOrderByComparator(
		String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		return SourceCreateDateComparator.getInstance(orderByAsc);
	}

}