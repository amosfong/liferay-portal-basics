<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset/init.jsp" %>

<%
DDMFormViewFormInstanceRecordDisplayContext ddmFormViewFormInstanceRecordDisplayContext = (DDMFormViewFormInstanceRecordDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:container-fluid>
	<react:component
		module="{FormView} from dynamic-data-mapping-form-web"
		props="<%= ddmFormViewFormInstanceRecordDisplayContext.getDDMFormContext(renderRequest) %>"
	/>
</clay:container-fluid>