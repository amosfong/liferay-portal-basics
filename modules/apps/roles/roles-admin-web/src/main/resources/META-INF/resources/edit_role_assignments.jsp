<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long roleId = ParamUtil.getLong(request, "roleId");

Role role = RoleServiceUtil.fetchRole(roleId);

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(RolesAdminPortletKeys.ROLES_ADMIN, "assignees-display-style", "list");
}
else {
	portalPreferences.setValue(RolesAdminPortletKeys.ROLES_ADMIN, "assignees-display-style", displayStyle);

	request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
}

EditRoleAssignmentsManagementToolbarDisplayContext editRoleAssignmentsManagementToolbarDisplayContext = new EditRoleAssignmentsManagementToolbarDisplayContext(request, renderRequest, renderResponse, displayStyle, "current");

SearchContainer<?> searchContainer = editRoleAssignmentsManagementToolbarDisplayContext.getSearchContainer();
PortletURL portletURL = editRoleAssignmentsManagementToolbarDisplayContext.getPortletURL();
String tabs2 = editRoleAssignmentsManagementToolbarDisplayContext.getTabs2();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);
portletDisplay.setURLBackTitle(portletDisplay.getPortletDisplayName());

renderResponse.setTitle(role.getTitle(locale));
%>

<liferay-util:include page="/edit_role_tabs.jsp" servletContext="<%= application %>" />

<clay:navigation-bar
	navigationItems="<%= roleDisplayContext.getRoleAssignmentsNavigationItems(portletURL) %>"
/>

<portlet:actionURL name="editRoleAssignments" var="editRoleAssignmentsURL">
	<portlet:param name="mvcPath" value="/edit_role_assignments.jsp" />
	<portlet:param name="tabs1" value="assignees" />
</portlet:actionURL>

<portlet:renderURL var="selectAssigneesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/select_assignees.jsp" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="available" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="roleId" value="<%= String.valueOf(roleId) %>" />
	<portlet:param name="displayStyle" value="<%= displayStyle %>" />
</portlet:renderURL>

<clay:management-toolbar
	actionDropdownItems="<%= editRoleAssignmentsManagementToolbarDisplayContext.getActionDropdownItems() %>"
	additionalProps='<%=
		HashMapBuilder.<String, Object>put(
			"assigneeType", HtmlUtil.escapeJS(tabs2)
		).put(
			"editRoleAssignmentsURL", editRoleAssignmentsURL.toString()
		).put(
			"modalSegmentState", RolesAdminWebKeys.MODAL_SEGMENT_STATE
		).put(
			"portletURL", portletURL.toString()
		).put(
			"roleName", role.getName()
		).put(
			"selectAssigneesURL", selectAssigneesURL.toString()
		).build()
	%>'
	clearResultsURL="<%= editRoleAssignmentsManagementToolbarDisplayContext.getClearResultsURL() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	orderDropdownItems="<%= editRoleAssignmentsManagementToolbarDisplayContext.getOrderByDropDownItems() %>"
	propsTransformer="{EditRoleAssignmentsManagementToolbarPropsTransformer} from roles-admin-web"
	searchActionURL="<%= editRoleAssignmentsManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="assigneesSearch"
	searchFormName="searchFm"
	selectable="<%= true %>"
	showCreationMenu="<%= true %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= editRoleAssignmentsManagementToolbarDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= editRoleAssignmentsManagementToolbarDisplayContext.getViewTypeItems() %>"
/>

<aui:form action="<%= portletURL %>" cssClass="container-fluid container-fluid-max-xl container-form-view" method="post" name="fm">
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="current" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="roleId" type="hidden" value="<%= role.getRoleId() %>" />
	<aui:input name="addUserIds" type="hidden" />
	<aui:input name="removeUserIds" type="hidden" />
	<aui:input name="addGroupIds" type="hidden" />
	<aui:input name="removeGroupIds" type="hidden" />

	<%
	request.setAttribute("edit_role_assignments.jsp-displayStyle", displayStyle);
	request.setAttribute("edit_role_assignments.jsp-searchContainer", searchContainer);
	%>

	<c:choose>
		<c:when test='<%= tabs2.equals("users") %>'>
			<liferay-util:include page="/edit_role_assignments_users.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("sites") %>'>
			<liferay-util:include page="/edit_role_assignments_sites.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("organizations") %>'>
			<liferay-util:include page="/edit_role_assignments_organizations.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("user-groups") %>'>
			<liferay-util:include page="/edit_role_assignments_user_groups.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
</aui:form>

<liferay-frontend:component
	context='<%=
		HashMapBuilder.<String, Object>put(
			"editRoleAssignmentsURL", editRoleAssignmentsURL.toString()
		).put(
			"modalSegmentState", RolesAdminWebKeys.MODAL_SEGMENT_STATE
		).put(
			"namespace", liferayPortletResponse.getNamespace()
		).put(
			"portletURL", portletURL.toString()
		).put(
			"roleName", HtmlUtil.escapeJS(role.getName())
		).put(
			"selectAssigneesURL", selectAssigneesURL.toString()
		).build()
	%>'
	module="{editRoleAssignmentsMain} from roles-admin-web"
/>