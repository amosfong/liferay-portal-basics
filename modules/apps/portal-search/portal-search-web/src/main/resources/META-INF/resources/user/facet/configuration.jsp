<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/template" prefix="liferay-template" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.user.facet.configuration.UserFacetPortletInstanceConfiguration" %><%@
page import="com.liferay.portal.search.web.internal.user.facet.portlet.UserFacetPortlet" %><%@
page import="com.liferay.portal.search.web.internal.user.facet.portlet.UserFacetPortletPreferences" %><%@
page import="com.liferay.portal.search.web.internal.user.facet.portlet.UserFacetPortletPreferencesImpl" %><%@
page import="com.liferay.portal.search.web.internal.util.PortletPreferencesJspUtil" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
UserSearchFacetDisplayContext userSearchFacetDisplayContext = (UserSearchFacetDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));

UserFacetPortletInstanceConfiguration userFacetPortletInstanceConfiguration = userSearchFacetDisplayContext.getUserFacetPortletInstanceConfiguration();

UserFacetPortletPreferences userFacetPortletPreferences = new UserFacetPortletPreferencesImpl(portletPreferences);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset
			collapsible="<%= true %>"
			label="display-settings"
		>
			<div class="display-template">
				<liferay-template:template-selector
					className="<%= UserFacetPortlet.class.getName() %>"
					displayStyle="<%= userFacetPortletInstanceConfiguration.displayStyle() %>"
					displayStyleGroupId="<%= userSearchFacetDisplayContext.getDisplayStyleGroupId() %>"
					refreshURL="<%= configurationRenderURL %>"
					showEmptyOption="<%= true %>"
				/>
			</div>
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset
			collapsible="<%= true %>"
			label="advanced-configuration"
		>
			<aui:input label="user-parameter-name" name="<%= PortletPreferencesJspUtil.getInputName(UserFacetPortletPreferences.PREFERENCE_KEY_PARAMETER_NAME) %>" value="<%= userFacetPortletPreferences.getParameterName() %>" />

			<aui:input label="max-terms" name="<%= PortletPreferencesJspUtil.getInputName(UserFacetPortletPreferences.PREFERENCE_KEY_MAX_TERMS) %>" value="<%= userFacetPortletPreferences.getMaxTerms() %>" />

			<aui:input label="frequency-threshold" name="<%= PortletPreferencesJspUtil.getInputName(UserFacetPortletPreferences.PREFERENCE_KEY_FREQUENCY_THRESHOLD) %>" value="<%= userFacetPortletPreferences.getFrequencyThreshold() %>" />

			<aui:select label="order-terms-by" name="<%= PortletPreferencesJspUtil.getInputName(UserFacetPortletPreferences.PREFERENCE_KEY_ORDER) %>" value="<%= userFacetPortletPreferences.getOrder() %>">
				<aui:option label="term-frequency-descending" value="count:desc" />
				<aui:option label="term-frequency-ascending" value="count:asc" />
				<aui:option label="term-value-ascending" value="key:asc" />
				<aui:option label="term-value-descending" value="key:desc" />
			</aui:select>

			<aui:input label="display-frequencies" name="<%= PortletPreferencesJspUtil.getInputName(UserFacetPortletPreferences.PREFERENCE_KEY_FREQUENCIES_VISIBLE) %>" type="checkbox" value="<%= userFacetPortletPreferences.isFrequenciesVisible() %>" />
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>