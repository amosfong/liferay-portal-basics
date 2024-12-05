<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<FaroProjectAdminDisplay> faroProjectAdminDisplays = (List<FaroProjectAdminDisplay>)request.getAttribute(FaroAdminWebKeys.FARO_PROJECT_ENTRIES);

if (faroProjectAdminDisplays == null) {
	faroProjectAdminDisplays = Collections.emptyList();
}

String tab = GetterUtil.getString(request.getAttribute("tab"), "details");

List<NavigationItem> navigationItems =
	new JSPNavigationItemList(pageContext) {
		{
			add(
				navigationItem -> {
					navigationItem.setActive(tab.equals("details"));
					navigationItem.setHref(currentURL);
					navigationItem.setLabel(LanguageUtil.get(request, "details"));
				});
		}
	};

request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
%>

<c:choose>
	<c:when test="<%= faroProjectAdminDisplays.size() == 1 %>">

		<%
		FaroProjectAdminDisplay faroProjectAdminDisplay = faroProjectAdminDisplays.get(0);
		%>

		<div class="sidebar-header">
			<div class="h4"><%= HtmlUtil.escape(faroProjectAdminDisplay.getName()) %></div>
		</div>

		<clay:navigation-bar
			navigationItems="<%= navigationItems %>"
		/>

		<c:choose>
			<c:when test='<%= tab.equals("details") %>'>
				<div class="sidebar-body">
					<div class="h5"><liferay-ui:message key="group-id" /></div>

					<p>
						<%= faroProjectAdminDisplay.getGroupId() %>
					</p>

					<div class="h5"><liferay-ui:message key="corp-project-uuid" /></div>

					<p>
						<%= faroProjectAdminDisplay.getCorpProjectUuid() %>
					</p>

					<div class="h5"><liferay-ui:message key="corp-project-name" /></div>

					<p>
						<%= faroProjectAdminDisplay.getCorpProjectName() %>
					</p>

					<div class="h5"><liferay-ui:message key="wedeploy-key" /></div>

					<p>
						<%= faroProjectAdminDisplay.getWeDeployKey() %>
					</p>

					<div class="h5"><liferay-ui:message key="last-access-date" /></div>

					<p>
						<%= faroProjectAdminDisplay.getLastAccessDate() %>
					</p>

					<div class="h5"><liferay-ui:message key="individuals-usage" /></div>

					<p>
						<%= StringBundler.concat(faroProjectAdminDisplay.getIndividualsCount(), " / ", faroProjectAdminDisplay.getIndividualsLimit(), " (", faroProjectAdminDisplay.getIndividualsUsage(), "%)") %>
					</p>

					<div class="h5"><liferay-ui:message key="page-views-usage" /></div>

					<p>
						<%= StringBundler.concat(faroProjectAdminDisplay.getPageViewsCount(), " / ", faroProjectAdminDisplay.getPageViewsLimit(), " (", faroProjectAdminDisplay.getPageViewsUsage(), "%)") %>
					</p>

					<div class="h5"><liferay-ui:message key="subscription" /></div>

					<p>
						<%= faroProjectAdminDisplay.getSubscription() %>
					</p>

					<div class="h5"><liferay-ui:message key="serverLocation" /></div>

					<p>
						<%= faroProjectAdminDisplay.getServerLocation() %>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="sidebar-header">
					<div class="h4"><liferay-ui:message arguments="<%= faroProjectAdminDisplays.size() %>" key="x-items-are-selected" /></div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<div class="h4"><liferay-ui:message arguments="<%= (int)request.getAttribute(FaroAdminWebKeys.FARO_PROJECT_ENTRIES_COUNT) %>" key="x-items-are-selected" /></div>
		</div>
	</c:otherwise>
</c:choose>