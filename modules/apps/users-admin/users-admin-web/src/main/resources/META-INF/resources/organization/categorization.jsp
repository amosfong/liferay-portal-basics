<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long organizationId = ParamUtil.getLong(request, "organizationId");

Organization organization = OrganizationServiceUtil.fetchOrganization(organizationId);
%>

<aui:model-context bean="<%= organization %>" model="<%= Organization.class %>" />

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="categorization"
/>

<label class="control-label">
	<liferay-ui:message key="categorization" />
</label>