<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountGroupDisplay accountGroupDisplay = (AccountGroupDisplay)request.getAttribute(AccountWebKeys.ACCOUNT_GROUP_DISPLAY);
%>

<clay:content-row>
	<clay:content-col
		expand="<%= true %>"
	>
		<span class="sheet-tertiary-title">
			<liferay-ui:message key="custom-fields" />
		</span>
	</clay:content-col>

	<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, PortletKeys.EXPANDO, ActionKeys.ACCESS_IN_CONTROL_PANEL) %>">
		<clay:content-col>

			<%
			boolean hasVisibleAttributes = ExpandoAttributesUtil.hasVisibleAttributes(company.getCompanyId(), AccountGroup.class);
			%>

			<liferay-ui:icon
				cssClass="modify-link"
				label="<%= true %>"
				linkCssClass="btn btn-secondary btn-sm"
				message='<%= hasVisibleAttributes ? "manage" : "add" %>'
				method="get"
				url='<%=
					PortletURLBuilder.create(
						PortletProviderUtil.getPortletURL(request, ExpandoColumn.class.getName(), hasVisibleAttributes ? PortletProvider.Action.MANAGE : PortletProvider.Action.EDIT)
					).setRedirect(
						currentURL
					).setParameter(
						"modelResource", AccountGroup.class.getName()
					).buildString()
				%>'
			/>
		</clay:content-col>
	</c:if>
</clay:content-row>

<liferay-expando:custom-attribute-list
	className="<%= AccountGroup.class.getName() %>"
	classPK="<%= accountGroupDisplay.getAccountGroupId() %>"
	editable="<%= true %>"
	label="<%= true %>"
/>