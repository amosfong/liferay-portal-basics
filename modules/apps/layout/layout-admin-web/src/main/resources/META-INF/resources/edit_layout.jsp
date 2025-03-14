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

long selPlid = ParamUtil.getLong(request, "selPlid");

Layout selLayout = LayoutLocalServiceUtil.fetchLayout(selPlid);

if (Validator.isNull(backURL)) {
	backURL = PortalUtil.getLayoutFullURL(selLayout, themeDisplay);
}

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
	portletDisplay.setURLBackTitle(ParamUtil.getString(request, "backURLTitle"));
}

renderResponse.setTitle(layoutsAdminDisplayContext.getConfigurationTitle(selLayout, locale));
%>


<liferay-ui:success key='<%= portletResource + "layoutUpdated" %>' message='<%= LanguageUtil.get(resourceBundle, "the-page-was-updated-successfully") %>' />

<liferay-frontend:screen-navigation
	containerCssClass="col-lg-8"
	containerWrapperCssClass="container-fluid container-fluid-max-xl container-form-lg"
	context="<%= selLayout %>"
	inverted="<%= true %>"
	key="<%= LayoutScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_LAYOUT %>"
	menubarCssClass="menubar menubar-transparent menubar-vertical-expand-lg"
	navCssClass="col-lg-3"
	portletURL="<%= layoutsAdminDisplayContext.getLayoutScreenNavigationPortletURL(selPlid) %>"
/>

<%@ include file="/friendly_url_warning_message.jspf" %>