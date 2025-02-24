<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
KBAdminNavigationDisplayContext kbAdminNavigationDisplayContext = new KBAdminNavigationDisplayContext(request, renderRequest, renderResponse, trashHelper);
%>

<div>
	<react:component
		componentId="moveObjectModalId"
		module="{MoveModal} from knowledge-base-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"items", kbAdminNavigationDisplayContext.getKBFolderDataJSONArray()
			).put(
				"moveParentKBObjectId", kbAdminNavigationDisplayContext.getMoveParentKBObjectId()
			).build()
		%>'
	/>
</div>