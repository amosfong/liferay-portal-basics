<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ExportImportConfiguration exportImportConfiguration = (ExportImportConfiguration)request.getAttribute("liferay-staging:configuration-header:exportImportConfiguration");
String label = GetterUtil.getString(request.getAttribute("liferay-staging:configuration-header:label"));
%>