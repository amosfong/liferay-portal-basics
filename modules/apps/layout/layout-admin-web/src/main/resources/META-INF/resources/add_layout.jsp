<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean copyPermissions = ParamUtil.getBoolean(request, "copyPermissions");
long sourcePlid = ParamUtil.getLong(request, "sourcePlid");

List<SiteNavigationMenu> autoSiteNavigationMenus = layoutsAdminDisplayContext.getAutoSiteNavigationMenus();
%>

<clay:container-fluid>
	<liferay-frontend:edit-form
		action="<%= (sourcePlid <= 0) ? layoutsAdminDisplayContext.getAddLayoutURL() : layoutsAdminDisplayContext.getCopyLayoutActionURL(copyPermissions, sourcePlid) %>"
		cssClass="add-layout-form d-none"
		method="post"
		name="fm"
		onSubmit="event.preventDefault();"
		validateOnBlur="<%= false %>"
	>
		<liferay-frontend:edit-form-body>
			<aui:input data-qa-id="addPageNameInput" label="name" name="name" placeholder='<%= LanguageUtil.get(request, "add-page-name") %>' required="<%= true %>" />

			<c:choose>
				<c:when test="<%= autoSiteNavigationMenus.size() > 1 %>">
					<div class="h3 sheet-subtitle"><liferay-ui:message key="navigation-menus" /></div>

					<liferay-ui:message key="add-this-page-to-the-following-menus" />

					<clay:container-fluid
						cssClass="auto-site-navigation-menus c-mt-3"
					>
						<clay:row>

							<%
							for (SiteNavigationMenu autoSiteNavigationMenu : autoSiteNavigationMenus) {
							%>

								<clay:col
									size="6"
								>
									<aui:input id='<%= "menu_" + autoSiteNavigationMenu.getSiteNavigationMenuId() %>' label="<%= HtmlUtil.escape(autoSiteNavigationMenu.getName()) %>" name="TypeSettingsProperties--siteNavigationMenuId--" type="checkbox" value="<%= autoSiteNavigationMenu.getSiteNavigationMenuId() %>" />
								</clay:col>

							<%
							}
							%>

						</clay:row>
					</clay:container-fluid>
				</c:when>
				<c:when test="<%= autoSiteNavigationMenus.size() == 1 %>">

					<%
					SiteNavigationMenu autoSiteNavigationMenu = autoSiteNavigationMenus.get(0);
					%>

					<clay:container-fluid
						cssClass="auto-site-navigation-menus c-mt-3"
					>
						<clay:row>
							<aui:input id='<%= "menu_" + autoSiteNavigationMenu.getSiteNavigationMenuId() %>' label='<%= LanguageUtil.format(request, "add-this-page-to-x", HtmlUtil.escape(autoSiteNavigationMenu.getName())) %>' name="TypeSettingsProperties--siteNavigationMenuId--" type="checkbox" value="<%= autoSiteNavigationMenu.getSiteNavigationMenuId() %>" />
						</clay:row>
					</clay:container-fluid>
				</c:when>
			</c:choose>

			</div>
		</liferay-frontend:edit-form-body>

		<liferay-frontend:edit-form-footer>
			<div data-qa-id="addLayoutFooter">
				<liferay-frontend:edit-form-buttons
					submitId="addButton"
					submitLabel="add"
				/>
			</div>
		</liferay-frontend:edit-form-footer>
	</liferay-frontend:edit-form>
</clay:container-fluid>

<liferay-frontend:component
	componentId='<%= liferayPortletResponse.getNamespace() + "addLayout" %>'
	context="<%= layoutsAdminDisplayContext.getProps() %>"
	module="{AddLayout} from layout-admin-web"
/>