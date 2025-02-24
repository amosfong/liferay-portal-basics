<%--
/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %>

<liferay-theme:defineObjects />

<%
long commerceOrderId = (long)request.getAttribute("liferay-commerce:inline-editable-order-field:commerceOrderId");
String field = (String)request.getAttribute("liferay-commerce:inline-editable-order-field:field");
String fieldHelpMessage = (String)request.getAttribute("liferay-commerce:inline-editable-order-field:fieldHelpMessage");
String fieldValue = (String)request.getAttribute("liferay-commerce:inline-editable-order-field:fieldValue");
boolean hasPermission = (boolean)request.getAttribute("liferay-commerce:inline-editable-order-field:hasPermission");
String label = (String)request.getAttribute("liferay-commerce:inline-editable-order-field:label");
String namespace = (String)request.getAttribute("liferay-commerce:inline-editable-order-field:namespace");
boolean open = (boolean)request.getAttribute("liferay-commerce:inline-editable-order-field:isOpenOrder");
%>