<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "active");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AccountEntryDisplay accountEntryDisplay = (AccountEntryDisplay)row.getObject();

long accountEntryId = accountEntryDisplay.getAccountEntryId();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= AccountEntryPermission.contains(permissionChecker, accountEntryId, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editAccountURL">
			<portlet:param name="mvcRenderCommandName" value="/account_admin/edit_account_entry" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editAccountURL %>"
		/>
	</c:if>

	<c:if test="<%= Objects.equals(AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS, accountEntryDisplay.getType()) && AccountEntryPermission.contains(permissionChecker, accountEntryId, ActionKeys.MANAGE_USERS) %>">
		<portlet:renderURL var="manageUsersURL">
			<portlet:param name="mvcRenderCommandName" value="/account_admin/edit_account_entry" />
			<portlet:param name="screenNavigationCategoryKey" value="<%= AccountScreenNavigationEntryConstants.CATEGORY_KEY_USERS %>" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="manage-users"
			url="<%= manageUsersURL %>"
		/>
	</c:if>

	<c:if test="<%= portletName.equals(AccountPortletKeys.ACCOUNT_ENTRIES_MANAGEMENT) && accountEntryDisplay.isApproved() && !accountEntryDisplay.isSelectedAccountEntry(themeDisplay.getScopeGroupId(), user.getUserId()) %>">
		<portlet:actionURL name="/account_admin/select_account_entry" var="selectAccountURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="select-account"
			url="<%= selectAccountURL %>"
		/>
	</c:if>

	<c:if test="<%= AccountEntryPermission.hasEditOrManageOrganizationsPermission(permissionChecker, accountEntryId) %>">
		<portlet:renderURL var="manageOrganizationsURL">
			<portlet:param name="mvcRenderCommandName" value="/account_admin/edit_account_entry" />
			<portlet:param name="screenNavigationCategoryKey" value="<%= AccountScreenNavigationEntryConstants.CATEGORY_KEY_ORGANIZATIONS %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="manage-organizations"
			url="<%= manageOrganizationsURL %>"
		/>
	</c:if>

	<c:if test="<%= AccountEntryPermission.contains(permissionChecker, accountEntryId, ActionKeys.DELETE) %>">
		<c:if test="<%= accountEntryDisplay.isApproved() %>">
			<portlet:actionURL name="/account_admin/update_account_entry_status" var="deactivateAccountURL">
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DEACTIVATE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="navigation" value="<%= navigation %>" />
				<portlet:param name="accountEntryIds" value="<%= String.valueOf(accountEntryId) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-deactivate
				url="<%= deactivateAccountURL %>"
			/>
		</c:if>

		<c:if test="<%= accountEntryDisplay.isInactive() %>">
			<portlet:actionURL name="/account_admin/update_account_entry_status" var="activateAccountURL">
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
				<portlet:param name="navigation" value="<%= navigation %>" />
				<portlet:param name="accountEntryIds" value="<%= String.valueOf(accountEntryId) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				message="activate"
				url="<%= activateAccountURL %>"
			/>
		</c:if>

		<portlet:actionURL name="/account_admin/delete_account_entry" var="deleteAccountURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountEntryIds" value="<%= String.valueOf(accountEntryId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteAccountURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>