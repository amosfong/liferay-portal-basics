<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/common/vertical_menu.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/knowledge_base/restore_kb_object" var="restoreTrashEntriesURL" />

<liferay-trash:undo
	portletURL="<%= restoreTrashEntriesURL %>"
/>

<div class="knowledge-base-admin-content">
	<liferay-util:include page="/admin/common/view_kb_article.jsp" servletContext="<%= application %>" />
</div>