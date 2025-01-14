<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcRenderCommandName" value="/message_boards/search" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="searchFm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
	<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

	<div class="input-group">
		<div class="input-group-item">
			<input aria-label="<%= LanguageUtil.get(request, "search") %>" class="form-control input-group-inset input-group-inset-after search-query" data-qa-id="searchInput" id="<%= (PortalUtil.getLiferayPortletResponse(portletResponse)).getNamespace() %>keywords1" name="<%= (PortalUtil.getLiferayPortletResponse(portletResponse)).getNamespace() %>keywords" placeholder="<%= LanguageUtil.get(request, "keywords") %>" title="<%= LanguageUtil.get(request, "search") %>" type="text" value="<%= HtmlUtil.escapeAttribute(ParamUtil.getString(request, "keywords")) %>" />

			<div class="input-group-inset-item input-group-inset-item-after">
				<clay:button
					data-qa-id="searchButton"
					displayType="unstyled"
					icon="search"
					monospaced="<%= false %>"
					type="submit"
				/>
			</div>
		</div>
	</div>
</aui:form>