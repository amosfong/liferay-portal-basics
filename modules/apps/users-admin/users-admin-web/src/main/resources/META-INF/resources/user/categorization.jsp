<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:model-context bean="<%= (User)request.getAttribute(UsersAdminWebKeys.SELECTED_USER) %>" model="<%= User.class %>" />

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="categorization"
/>