<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrder commerceOrder = commerceOrderContentDisplayContext.getCommerceOrder();

long paymentCommerceTermEntryId = commerceOrder.getPaymentCommerceTermEntryId();
%>

<commerce-ui:modal-content
	showSubmitButton="<%= false %>"
	title='<%= LanguageUtil.get(request, "payment-terms") %>'
>
	<label class="control-label <%= (paymentCommerceTermEntryId == 0) ? " d-none" : "" %>" id="name-label"><liferay-ui:message key="name" /></label>

	<div>
		<%= commerceOrder.getPaymentCommerceTermEntryName() %>
	</div>

	<label class="control-label <%= (paymentCommerceTermEntryId == 0) ? " d-none" : "" %>" id="description-label"><liferay-ui:message key="description" /></label>

	<div id="description-container">
		<%= commerceOrder.getPaymentCommerceTermEntryDescription() %>
	</div>
</commerce-ui:modal-content>