<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

String target = (String)objArray[3];
long[] groupIdsArray = (long[])objArray[7];
List<String> groupNames = (List<String>)objArray[8];
String portletId = (String)objArray[9];
%>

<aui:input name='<%= "groupIds" + HtmlUtil.escapeAttribute(target) %>' type="hidden" value="<%= StringUtil.merge(groupIdsArray) %>" />
<aui:input name='<%= "groupNames" + HtmlUtil.escapeAttribute(target) %>' type="hidden" value='<%= StringUtil.merge(groupNames, "@@") %>' />

<div id="<portlet:namespace />groupDiv<%= HtmlUtil.escapeAttribute(target) %>">
	<span id="<portlet:namespace />groupHTML<%= HtmlUtil.escapeAttribute(target) %>">
		<c:choose>
			<c:when test="<%= roleDisplayContext.isAllowGroupScope() %>">
				<liferay-ui:message key="all-sites-and-asset-libraries" />
			</c:when>
		</c:choose>
	</span>
</div>