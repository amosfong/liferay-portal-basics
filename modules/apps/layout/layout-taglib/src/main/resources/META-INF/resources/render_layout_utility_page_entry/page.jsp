<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/render_layout_utility_page_entry/init.jsp" %>

<%
LayoutStructure layoutStructure = (LayoutStructure)request.getAttribute("liferay-layout:render-layout-utility-page-entry:layoutStructure");

LayoutUtilityPageEntry layoutUtilityPageEntry = (LayoutUtilityPageEntry)request.getAttribute("liferay-layout:render-layout-utility-page-entry:layoutUtilityPageEntry");

RenderLayoutUtilityPageEntryDisplayContext renderLayoutUtilityPageEntryDisplayContext = new RenderLayoutUtilityPageEntryDisplayContext(layoutUtilityPageEntry);
%>

<c:if test="<%= layoutStructure != null %>">
	<aui:link href="<%= renderLayoutUtilityPageEntryDisplayContext.getHref() %>" rel="stylesheet" senna="temporary" type="text/css" />

	<%
	try {
		request.setAttribute(WebKeys.OUTPUT_DATA, new OutputData());
		request.setAttribute(WebKeys.SHOW_PORTLET_TOPPER, Boolean.FALSE);
	%>

		<liferay-util:buffer
			var="content"
		>
			<liferay-layout:render-layout-structure
				layoutStructure="<%= layoutStructure %>"
			/>
		</liferay-util:buffer>

		<%= content %>

	<%
	}
	finally {
		request.removeAttribute(WebKeys.SHOW_PORTLET_TOPPER);
	}
	%>

</c:if>