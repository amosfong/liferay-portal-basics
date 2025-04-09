<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String portletNamespace = PortalUtil.getPortletNamespace(ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU);
%>

<li class="control-menu-nav-item">
	<clay:button
		aria-label='<%= LanguageUtil.get(request, "add") %>'
		cssClass="control-menu-nav-link lfr-portal-tooltip product-menu-toggle sidenav-toggler"
		data-content="body"
		data-open-class="open open-admin-panel"
		data-qa-id="add"
		data-target='<%= "#" + portletNamespace + "addPanelId" %>'
		data-title='<%= LanguageUtil.get(request, "add") %>'
		data-toggle="liferay-sidenav"
		data-type="fixed-push"
		data-type-mobile="fixed"
		data-url='<%=
			PortletURLBuilder.create(
				PortletURLFactoryUtil.create(request, ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU, PortletRequest.RESOURCE_PHASE)
			).setMVCPath(
				"/add_panel.jsp"
			).setParameter(
				"stateMaximized", themeDisplay.isStateMaximized()
			).setParameter(
				"status", WorkflowConstants.STATUS_ANY
			).setWindowState(
				LiferayWindowState.EXCLUSIVE
			).buildString()
		%>'
		displayType="unstyled"
		icon="plus"
		id='<%= portletNamespace + "addToggleId" %>'
		small="<%= true %>"
	/>
</li>