<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/control_menu/init.jsp" %>

<%
boolean applicationsMenuApp = GetterUtil.getBoolean(request.getAttribute("liferay-product-navigation:control-menu:applicationsMenuApp"));

ProductNavigationControlMenuTagDisplayContext productNavigationControlMenuTagDisplayContext = new ProductNavigationControlMenuTagDisplayContext(request, pageContext);
%>

<c:if test="<%= productNavigationControlMenuTagDisplayContext.hasControlMenuEntries() %>">
	<nav aria-label="<%= LanguageUtil.get(request, "control-menu") %>" class="cadmin control-menu-container">
		<liferay-util:dynamic-include key="com.liferay.product.navigation.taglib#/page.jsp#pre" />

		<div class="control-menu control-menu-level-1 control-menu-level-1-<%= applicationsMenuApp ? "light" : "dark" %> d-print-none" data-qa-id="controlMenu" id="<portlet:namespace />ControlMenu">
			<clay:container-fluid
				fullWidth='<%= FeatureFlagManagerUtil.isEnabled("LPS-184404") %>'
			>
				<div class="control-menu-level-1-nav control-menu-nav" data-namespace="<portlet:namespace />" data-qa-id="header" id="<portlet:namespace />controlMenu">

					<%
					productNavigationControlMenuTagDisplayContext.writeProductNavigationControlMenuEntries(pageContext.getOut());
					%>

				</div>
			</clay:container-fluid>

			<div class="control-menu-body">

				<%
				Map<String, List<ProductNavigationControlMenuEntry>> productNavigationControlMenuEntriesMap = productNavigationControlMenuTagDisplayContext.getProductNavigationControlMenuEntriesMap();

				for (List<ProductNavigationControlMenuEntry> productNavigationControlMenuEntries : productNavigationControlMenuEntriesMap.values()) {
					for (ProductNavigationControlMenuEntry productNavigationControlMenuEntry : productNavigationControlMenuEntries) {
						productNavigationControlMenuEntry.includeBody(request, PipingServletResponseFactory.createPipingServletResponse(pageContext));
					}
				}
				%>

			</div>

			<div id="controlMenuAlertsContainer"></div>
		</div>

		<liferay-util:dynamic-include key="com.liferay.product.navigation.taglib#/page.jsp#post" />
	</nav>

	<liferay-frontend:component
		module="{ProductNavigationControlMenu} from product-navigation-taglib"
	/>
</c:if>