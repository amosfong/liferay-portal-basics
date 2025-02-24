<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.commerce.product.catalog.CPCatalogEntry" %><%@
page import="com.liferay.commerce.product.constants.CPWebKeys" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
CPCatalogEntry cpCatalogEntry = (CPCatalogEntry)request.getAttribute(CPWebKeys.CP_CATALOG_ENTRY);

String namespace = (String)request.getAttribute("liferay-commerce:price:namespace");
%>