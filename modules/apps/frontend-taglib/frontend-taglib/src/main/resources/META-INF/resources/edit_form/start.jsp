<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/edit_form/init.jsp" %>

<%
String fullName = namespace.concat(HtmlUtil.escapeAttribute(name));
%>

<form action="<%= HtmlUtil.escapeAttribute(action) %>" class="container-fluid container-fluid-max-xl container-form-lg container-no-gutters form <%= cssClass %> <%= inlineLabels ? "field-labels-inline" : StringPool.BLANK %>" data-fm-namespace="<%= namespace %>" id="<%= fullName %>" method="<%= method %>" name="<%= fullName %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<c:if test="<%= wrappedFormContent %>">
		<div class="sheet <%= fluid ? StringPool.BLANK : "sheet-lg" %>">
	</c:if>

		<div class="panel-group panel-group-flush">
			<c:if test="<%= Validator.isNotNull(onSubmit) %>">
				<div aria-label="<%= HtmlUtil.escape(Validator.isNotNull(title) ? title : portletDisplay.getTitle()) %>" class="input-container" role="group">
			</c:if>

			<aui:input name="formDate" type="hidden" value="<%= System.currentTimeMillis() %>" />