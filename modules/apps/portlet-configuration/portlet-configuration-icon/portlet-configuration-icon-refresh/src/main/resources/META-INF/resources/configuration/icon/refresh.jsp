<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/configuration/icon/init.jsp" %>

<aui:script>
	Liferay.Util.setPortletConfigurationIconAction(
		'<portlet:namespace />refresh',
		() => {
			Liferay.Portlet.refresh('#p_p_id_<%= portletDisplay.getId() %>_');
		}
	);
</aui:script>