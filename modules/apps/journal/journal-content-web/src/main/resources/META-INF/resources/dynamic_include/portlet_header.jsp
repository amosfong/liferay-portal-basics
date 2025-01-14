<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<div class="visible-interaction">

	<%
	PortletHeaderActionDropdownItemsProvider portletHeaderActionDropdownItemsProvider = new PortletHeaderActionDropdownItemsProvider(request, journalContentDisplayContext);
	%>

	<clay:dropdown-actions
		aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
		dropdownItems="<%= portletHeaderActionDropdownItemsProvider.getActionDropdownItems() %>"
		propsTransformer="{PortletHeaderDefaultPropsTransformer} from journal-content-web"
	/>
</div>