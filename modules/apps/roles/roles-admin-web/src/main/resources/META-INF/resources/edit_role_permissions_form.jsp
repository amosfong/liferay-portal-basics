<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs3 = ParamUtil.getString(request, "tabs3", "current");

long roleId = ParamUtil.getLong(request, "roleId");

Role role = RoleServiceUtil.fetchRole(roleId);

String portletResource = ParamUtil.getString(request, "portletResource");

Portlet portlet = null;
String portletResourceLabel = null;

if (Validator.isNotNull(portletResource)) {
	portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);

	String portletId = portlet.getPortletId();

	if (portletId.equals(PortletKeys.PORTAL)) {
		portletResourceLabel = LanguageUtil.get(request, "general-permissions");
	}
	else {
		portletResourceLabel = PortalUtil.getPortletLongTitle(portlet, application, locale);
	}
}

List<String> modelResources = null;

if (Validator.isNotNull(portletResource)) {
	modelResources = ResourceActionsUtil.getPortletModelResources(portletResource);
}
%>

<portlet:actionURL name="updateActions" var="editRolePermissionsURL">
	<portlet:param name="mvcPath" value="/edit_role_permissions_form.jsp" />
</portlet:actionURL>

<aui:form action="<%= editRolePermissionsURL %>" method="post" name="fm">
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />
	<aui:input name="redirect" type="hidden" />
	<aui:input name="roleId" type="hidden" value="<%= role.getRoleId() %>" />
	<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
	<aui:input name="modelResources" type="hidden" value='<%= (modelResources == null) ? "" : StringUtil.merge(modelResources) %>' />
	<aui:input name="accountRoleGroupScope" type="hidden" value="<%= roleDisplayContext.isAccountRoleGroupScope() %>" />
	<aui:input name="selectedTargets" type="hidden" />
	<aui:input name="unselectedTargets" type="hidden" />

	<clay:sheet>
		<clay:sheet-header>
			<h3 class="sheet-title" data-qa-id="portletResourceLabel"><%= HtmlUtil.escape(portletResourceLabel) %></h3>
		</clay:sheet-header>

		<%
		request.setAttribute("edit_role_permissions.jsp-curPortletResource", portletResource);

		String applicationPermissionsLabel = "application-permissions";

		PanelCategoryHelper panelCategoryHelper = (PanelCategoryHelper)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_HELPER);

		if (portletResource.equals(PortletKeys.PORTAL)) {
			applicationPermissionsLabel = StringPool.BLANK;
		}
		else if ((portlet != null) && panelCategoryHelper.containsPortlet(portlet.getPortletId(), PanelCategoryKeys.ROOT)) {
			applicationPermissionsLabel = "general-permissions";
		}
		%>

		<clay:sheet-section>
			<c:if test="<%= Validator.isNotNull(applicationPermissionsLabel) %>">
				<div class="sheet-subtitle">
					<liferay-ui:message key="<%= applicationPermissionsLabel %>" />

					<clay:icon
						aria-label='<%= LanguageUtil.get(request, applicationPermissionsLabel + "-help") %>'
						cssClass="lfr-portal-tooltip"
						data-title='<%= LanguageUtil.get(request, applicationPermissionsLabel + "-help") %>'
						symbol="question-circle-full"
						tabindex="0"
					/>
				</div>
			</c:if>

			<liferay-util:include page="/edit_role_permissions_resource.jsp" servletContext="<%= application %>" />
		</clay:sheet-section>

		<c:if test="<%= (modelResources != null) && !modelResources.isEmpty() %>">
			<clay:sheet-section>
				<div class="sheet-subtitle">
					<liferay-ui:message key="resource-permissions" />

					<clay:icon
						aria-label='<%= LanguageUtil.get(request, "resource-permissions-help") %>'
						cssClass="lfr-portal-tooltip"
						data-title='<%= LanguageUtil.get(request, "resource-permissions-help") %>'
						symbol="question-circle-full"
						tabindex="0"
					/>
				</div>

				<div class="permission-group">

					<%
					modelResources = ListUtil.sort(modelResources, new ModelResourceWeightComparator());

					for (int i = 0; i < modelResources.size(); i++) {
						String curModelResource = modelResources.get(i);

						String curModelResourceName = ResourceActionsUtil.getModelResource(request, curModelResource);
					%>

						<div class="sheet-tertiary-title" id="<%= roleDisplayContext.getResourceHtmlId(curModelResource) %>"><%= curModelResourceName %></div>

						<%
						request.setAttribute("edit_role_permissions.jsp-curModelResource", curModelResource);
						request.setAttribute("edit_role_permissions.jsp-curModelResourceName", curModelResourceName);
						%>

						<liferay-util:include page="/edit_role_permissions_resource.jsp" servletContext="<%= application %>" />

					<%
					}
					%>

				</div>
			</clay:sheet-section>
		</c:if>

		<c:if test="<%= portletResource.equals(PortletKeys.PORTLET_DISPLAY_TEMPLATE) || portletResource.equals(TemplatePortletKeys.TEMPLATE) %>">
			<clay:sheet-section>
				<div class="sheet-subtitle"><liferay-ui:message key="related-application-permissions" /></div>

				<div class="related-permissions">

					<%
					EditRolePermissionsFormDisplayContext editRolePermissionsFormDisplayContext = new EditRolePermissionsFormDisplayContext(request, response, liferayPortletRequest, liferayPortletResponse, roleDisplayContext, application);
					%>

					<aui:input name="relatedPortletResources" type="hidden" value="<%= StringUtil.merge(editRolePermissionsFormDisplayContext.getRelatedPortletResources()) %>" />

					<liferay-ui:search-iterator
						markupView="deprecated"
						paginate="<%= false %>"
						searchContainer="<%= editRolePermissionsFormDisplayContext.getSearchContainer() %>"
					/>
				</div>
			</clay:sheet-section>
		</c:if>

		<clay:sheet-footer>
			<aui:button onClick='<%= liferayPortletResponse.getNamespace() + "updateActions();" %>' primary="<%= true %>" value="save" />
		</clay:sheet-footer>
	</clay:sheet>
</aui:form>