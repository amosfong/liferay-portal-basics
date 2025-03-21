<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DisplayPageTypeSiteNavigationMenuTypeDisplayContext displayPageTypeSiteNavigationMenuTypeDisplayContext = (DisplayPageTypeSiteNavigationMenuTypeDisplayContext)request.getAttribute(DisplayPageTypeSiteNavigationMenuTypeDisplayContext.class.getName());
%>

<div>
	<react:component
		module="{DisplayPageItemContextualSidebar} from site-navigation-menu-item-display-page"
		props="<%= displayPageTypeSiteNavigationMenuTypeDisplayContext.getDisplayPageItemContextualSidebarContext() %>"
	/>
</div>