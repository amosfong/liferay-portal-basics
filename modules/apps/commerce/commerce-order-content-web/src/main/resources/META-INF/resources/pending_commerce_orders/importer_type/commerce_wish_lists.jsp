<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<frontend-data-set:classic-display
	contextParams='<%=
		HashMapBuilder.<String, String>put(
			"commerceOrderId", String.valueOf(commerceOrderContentDisplayContext.getCommerceOrderId())
		).put(
			"orderDetailURL", ParamUtil.getString(request, "orderDetailURL")
		).build()
	%>'
	dataProviderKey="<%= CommerceOrderFDSNames.WISH_LISTS %>"
	id="<%= CommerceOrderFDSNames.WISH_LISTS %>"
	itemsPerPage="<%= 10 %>"
	style="fluid"
/>