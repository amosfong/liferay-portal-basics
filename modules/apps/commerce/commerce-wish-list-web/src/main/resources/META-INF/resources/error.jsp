<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchWishListException.class %>" message="the-wish-list-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchWishListItemException.class %>" message="the-item-could-not-be-found" />

<liferay-ui:error-principal />