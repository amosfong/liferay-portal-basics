<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %>

<%@ page import="com.liferay.fragment.collection.filter.date.display.context.FragmentCollectionFilterDateDisplayContext" %>

<%
FragmentCollectionFilterDateDisplayContext fragmentCollectionFilterDateDisplayContext = (FragmentCollectionFilterDateDisplayContext)request.getAttribute(FragmentCollectionFilterDateDisplayContext.class.getName());
%>