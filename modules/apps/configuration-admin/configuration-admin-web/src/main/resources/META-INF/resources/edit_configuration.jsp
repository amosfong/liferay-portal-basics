<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

PortletURL portletURL = renderResponse.createRenderURL();

if (Validator.isNull(redirect)) {
	redirect = portletURL.toString();
}

String bindRedirectURL = currentURL;

ConfigurationModel configurationModel = (ConfigurationModel)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_MODEL);

PortletURL viewFactoryInstancesURL = PortletURLBuilder.createRenderURL(
	renderResponse
).setMVCRenderCommandName(
	"/configuration_admin/view_factory_instances"
).setParameter(
	"factoryPid", configurationModel.getFactoryPid()
).buildPortletURL();

if (configurationModel.isFactory()) {
	bindRedirectURL = viewFactoryInstancesURL.toString();
}

PortalUtil.addPortletBreadcrumbEntry(request, portletDisplay.getPortletDisplayName(), String.valueOf(renderResponse.createRenderURL()));

ConfigurationCategoryMenuDisplay configurationCategoryMenuDisplay = (ConfigurationCategoryMenuDisplay)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_CATEGORY_MENU_DISPLAY);

ConfigurationCategoryDisplay configurationCategoryDisplay = configurationCategoryMenuDisplay.getConfigurationCategoryDisplay();

String categoryDisplayName = configurationCategoryDisplay.getCategoryLabel(locale);

String viewCategoryHREF = ConfigurationCategoryUtil.getHREF(configurationCategoryMenuDisplay, liferayPortletResponse, renderRequest, renderResponse);

PortalUtil.addPortletBreadcrumbEntry(request, categoryDisplayName, viewCategoryHREF);

ResourceBundleLoader resourceBundleLoader = ResourceBundleLoaderProviderUtil.getResourceBundleLoader(configurationModel.getBundleSymbolicName());

ResourceBundle componentResourceBundle = resourceBundleLoader.loadResourceBundle(PortalUtil.getLocale(request));

String configurationModelName = (componentResourceBundle != null) ? LanguageUtil.get(componentResourceBundle, configurationModel.getName()) : configurationModel.getName();

if (configurationModel.isFactory()) {
	PortalUtil.addPortletBreadcrumbEntry(request, configurationModelName, viewFactoryInstancesURL.toString());
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(categoryDisplayName);
%>

<liferay-ui:error exception="<%= ConfigurationModelListenerException.class %>">

	<%
	ConfigurationModelListenerException cmle = (ConfigurationModelListenerException)errorException;
	%>

	<liferay-ui:message key="<%= HtmlUtil.escape(cmle.causeMessage) %>" localizeKey="<%= false %>" />
</liferay-ui:error>

<portlet:actionURL name="/configuration_admin/bind_configuration" var="bindConfigurationActionURL" />
<portlet:actionURL name="/configuration_admin/delete_configuration" var="deleteConfigurationActionURL" />

<clay:container-fluid>
	<clay:col
		size="12"
	>
		<liferay-site-navigation:breadcrumb
			breadcrumbEntries="<%= BreadcrumbEntriesUtil.getBreadcrumbEntries(request, false, false, false, false, true) %>"
		/>
	</clay:col>
</clay:container-fluid>

<clay:container-fluid>
	<clay:row>
		<clay:col
			md="3"
		>
			<liferay-util:include page="/configuration_category_menu.jsp" servletContext="<%= application %>" />
		</clay:col>

		<clay:col
			md="9"
		>
			<clay:sheet
				size="full"
			>
				<aui:form action="<%= bindConfigurationActionURL %>" method="post" name="fm">
					<aui:input name="redirect" type="hidden" value="<%= bindRedirectURL %>" />
					<aui:input name="factoryPid" type="hidden" value="<%= configurationModel.getFactoryPid() %>" />
					<aui:input name="pid" type="hidden" value="<%= configurationModel.getID() %>" />

					<%
					String configurationTitle = null;

					ConfigurationScopeDisplayContext configurationScopeDisplayContext = ConfigurationScopeDisplayContextFactory.create(renderRequest);

					if (configurationModel.isFactory()) {
						if (configurationModel.hasScopeConfiguration(configurationScopeDisplayContext.getScope())) {
							configurationTitle = configurationModel.getLabel();
						}
						else {
							configurationTitle = LanguageUtil.get(request, "add");
						}
					}
					else {
						configurationTitle = (componentResourceBundle != null) ? LanguageUtil.format(componentResourceBundle, configurationModel.getName(), configurationModel.getNameArguments()) : configurationModel.getName();
					}
					%>

					<h2>
						<%= HtmlUtil.escape(configurationTitle) %>

						<c:if test="<%= configurationModel.hasScopeConfiguration(configurationScopeDisplayContext.getScope()) %>">
							<liferay-ui:icon-menu
								cssClass="float-right"
								direction="right"
								markupView="lexicon"
								showWhenSingleIcon="<%= true %>"
							>
								<c:choose>
									<c:when test="<%= configurationModel.isFactory() %>">
										<portlet:actionURL name="/configuration_admin/delete_configuration" var="deleteConfigActionURL">
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="factoryPid" value="<%= configurationModel.getFactoryPid() %>" />
											<portlet:param name="pid" value="<%= configurationModel.getID() %>" />
										</portlet:actionURL>

										<liferay-ui:icon
											message="delete"
											method="post"
											url="<%= deleteConfigActionURL %>"
										/>
									</c:when>
									<c:otherwise>
										<portlet:actionURL name="/configuration_admin/delete_configuration" var="deleteConfigActionURL">
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="factoryPid" value="<%= configurationModel.getFactoryPid() %>" />
											<portlet:param name="pid" value="<%= configurationModel.getID() %>" />
										</portlet:actionURL>

										<liferay-ui:icon
											message="reset-default-values"
											method="post"
											url="<%= deleteConfigActionURL %>"
										/>
									</c:otherwise>
								</c:choose>

								<portlet:resourceURL id="/configuration_admin/export_configuration" var="exportURL">
									<portlet:param name="factoryPid" value="<%= configurationModel.getFactoryPid() %>" />
									<portlet:param name="pid" value="<%= configurationModel.getID() %>" />
								</portlet:resourceURL>

								<liferay-ui:icon
									message="export"
									method="get"
									url="<%= exportURL %>"
								/>

								<%
								List<ConfigurationMenuItem> configurationMenuItems = (List<ConfigurationMenuItem>)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_MENU_ITEMS);
								%>

								<c:if test="<%= ListUtil.isNotEmpty(configurationMenuItems) %>">

									<%
									for (ConfigurationMenuItem configurationMenuItem : configurationMenuItems) {
										Configuration configuration = configurationModel.getConfiguration();
									%>

										<liferay-ui:icon
											message="<%= configurationMenuItem.getLabel(locale) %>"
											url="<%= configurationMenuItem.getURL(renderRequest, renderResponse, configurationModel.getID(), configurationModel.getFactoryPid(), configuration.getProperties()) %>"
											useDialog="<%= true %>"
										/>

									<%
									}
									%>

								</c:if>
							</liferay-ui:icon-menu>
						</c:if>
					</h2>

					<c:if test="<%= configurationModel.hasScopeConfiguration(configurationScopeDisplayContext.getScope()) && configurationModel.isReadOnly() %>">
						<clay:alert
							message="this-configuration-is-read-only"
						/>
					</c:if>

					<c:if test="<%= !configurationModel.hasScopeConfiguration(configurationScopeDisplayContext.getScope()) %>">
						<clay:alert
							message="this-configuration-is-not-saved-yet.-the-values-shown-are-the-default"
						/>
					</c:if>

					<liferay-util:dynamic-include key='<%= "com.liferay.configuration.admin.web#/edit_configuration.jsp#" + configurationModel.getFactoryPid() + "#pre" %>' />

					<%
					String configurationModelDescription = (componentResourceBundle != null) ? LanguageUtil.format(componentResourceBundle, configurationModel.getDescription(), configurationModel.getDescriptionArguments()) : configurationModel.getDescription();
					%>

					<c:if test="<%= !Validator.isBlank(configurationModelDescription) %>">
						<p class="text-default">
							<strong><%= configurationModelDescription %></strong>
						</p>
					</c:if>

					<%
					ConfigurationFormRenderer configurationFormRenderer = (ConfigurationFormRenderer)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_FORM_RENDERER);

					configurationFormRenderer.render(request, PipingServletResponseFactory.createPipingServletResponse(pageContext));
					%>

					<liferay-util:dynamic-include key='<%= "com.liferay.configuration.admin.web#/edit_configuration.jsp#" + configurationModel.getFactoryPid() + "#post" %>' />

					<c:if test="<%= !configurationModel.isReadOnly() %>">
						<div class="align-items-center d-flex justify-content-between">
							<aui:button-row>
								<c:choose>
									<c:when test="<%= configurationModel.hasScopeConfiguration(configurationScopeDisplayContext.getScope()) %>">
										<aui:button data-qa-id="submitConfiguration" name="update" type="submit" value="update" />
									</c:when>
									<c:otherwise>
										<aui:button data-qa-id="submitConfiguration" name="save" type="submit" value="save" />
									</c:otherwise>
								</c:choose>

								<aui:button cssClass="ml-3" href="<%= redirect %>" name="cancel" type="cancel" />
							</aui:button-row>

							<c:if test="<%= Validator.isNotNull(configurationModel.getLiferayLearnMessageKey()) && Validator.isNotNull(configurationModel.getLiferayLearnMessageResource()) %>">
								<liferay-learn:message
									key="<%= configurationModel.getLiferayLearnMessageKey() %>"
									resource="<%= configurationModel.getLiferayLearnMessageResource() %>"
								/>
							</c:if>
						</div>
					</c:if>
				</aui:form>
			</clay:sheet>
		</clay:col>
	</clay:row>
</clay:container-fluid>