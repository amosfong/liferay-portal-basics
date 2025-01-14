<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.util.CPMedia" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %>

<liferay-theme:defineObjects />

<%
CPMedia cpMedia = (CPMedia)request.getAttribute(CPContentWebKeys.CP_MEDIA);
%>