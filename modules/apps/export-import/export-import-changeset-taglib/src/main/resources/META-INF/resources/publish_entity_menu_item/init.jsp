<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String changesetUuid = GetterUtil.getString(request.getAttribute("liferay-export-import-changeset:publish-entity-menu-item:changesetUuid"));
String className = GetterUtil.getString(request.getAttribute("liferay-export-import-changeset:publish-entity-menu-item:className"));
long entityGroupId = GetterUtil.getLong(request.getAttribute("liferay-export-import-changeset:publish-entity-menu-item:groupId"));
String uuid = GetterUtil.getString(request.getAttribute("liferay-export-import-changeset:publish-entity-menu-item:uuid"));

boolean showMenuItem = false;

if (entityGroupId != 0) {
	Group entityGroup = GroupLocalServiceUtil.fetchGroup(entityGroupId);

	showMenuItem = ChangesetTaglibDisplayContext.isShowPublishMenuItem(entityGroup, portletDisplay.getId(), className, uuid);
}
else {
	showMenuItem = ChangesetTaglibDisplayContext.isShowPublishMenuItem(group, portletDisplay.getId(), className, uuid);
}
%>