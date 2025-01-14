<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPOptionDisplayContext cpOptionDisplayContext = (CPOptionDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div id="<portlet:namespace />optionsContainer">
	<frontend-data-set:headless-display
		apiURL="/o/headless-commerce-admin-catalog/v1.0/options"
		creationMenu="<%= cpOptionDisplayContext.getCreationMenu() %>"
		fdsActionDropdownItems="<%= cpOptionDisplayContext.getOptionFDSActionDropdownItems() %>"
		id="<%= CommerceOptionFDSNames.OPTIONS %>"
		itemsPerPage="<%= 10 %>"
		style="fluid"
	/>
</div>