<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="sheet sheet-lg">
	<div class="h5"><liferay-ui:message key="build-date" /></div>

	<p>
		<%= System.getenv("LABEL_BUILD_DATE") %>
	</p>

	<div class="h5"><liferay-ui:message key="vcs-hash" /></div>

	<p>
		<%= System.getenv("LABEL_VCS_REF") %>
	</p>
</div>