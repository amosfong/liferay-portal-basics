<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();
%>

<liferay-frontend:fieldset
	collapsed="<%= false %>"
	collapsible="<%= true %>"
	label="custom-css"
>
	<aui:input label="css" name="regularCss" type="textarea" value="<%= selLayoutSet.getCss() %>" wrapperCssClass="c-mb-0 c-mt-4" />

	<p class="text-secondary">
		<liferay-ui:message key="this-css-is-loaded-after-the-theme" />
	</p>
</liferay-frontend:fieldset>