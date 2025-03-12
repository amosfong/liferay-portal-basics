<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectLayoutCollectionDisplayContext selectLayoutCollectionDisplayContext = (SelectLayoutCollectionDisplayContext)request.getAttribute(LayoutAdminWebKeys.SELECT_LAYOUT_COLLECTION_DISPLAY_CONTEXT);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(layoutsAdminDisplayContext.getBackURL());
portletDisplay.setURLBackTitle(portletDisplay.getPortletDisplayName());

renderResponse.setTitle(LanguageUtil.get(request, "select-collection"));
%>

<clay:navigation-bar
	cssClass="border-bottom"
	inverted="<%= false %>"
	navigationItems="<%= selectLayoutCollectionDisplayContext.getNavigationItems() %>"
/>

<clay:container-fluid
	id='<%= liferayPortletResponse.getNamespace() + "collections" %>'
>
	<liferay-util:include page="/select_collection_providers.jsp" servletContext="<%= application %>" />
</clay:container-fluid>

<aui:script sandbox="<%= true %>">
	var collections = document.getElementById('<portlet:namespace />collections');

	var selectLayoutMasterLayoutActionOptionQueryClickHandler =
		Liferay.Util.delegate(
			collections,
			'click',
			'.select-collection-action-option',
			(event) => {
				Liferay.Util.navigate(
					event.delegateTarget.dataset.selectLayoutMasterLayoutUrl
				);
			}
		);

	function handleDestroyPortlet() {
		selectLayoutMasterLayoutActionOptionQueryClickHandler.dispose();

		Liferay.detach('destroyPortlet', handleDestroyPortlet);
	}

	Liferay.on('destroyPortlet', handleDestroyPortlet);
</aui:script>