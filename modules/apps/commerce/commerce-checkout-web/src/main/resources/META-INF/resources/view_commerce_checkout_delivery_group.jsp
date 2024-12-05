<%--
/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DeliveryGroupDisplayContext deliveryGroupDisplayContext = (DeliveryGroupDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div>
	<div class="panel-body">
		<div class="h5">
			<liferay-ui:message key="shipping-address" />
		</div>

		<%
		CommerceAddress commerceAddress = deliveryGroupDisplayContext.getCommerceAddress(request);
		%>

		<c:if test="<%= commerceAddress != null %>">
			<p>
				<span><%= commerceAddress.getName() %></span>
			</p>

			<p>
				<span><%= commerceAddress.getStreet1() %></span>
			</p>

			<c:if test="<%= Validator.isNotNull(commerceAddress.getStreet2()) %>">
				<p>
					<span><%= commerceAddress.getStreet2() %></span>
				</p>
			</c:if>

			<c:if test="<%= Validator.isNotNull(commerceAddress.getStreet3()) %>">
				<p>
					<span><%= commerceAddress.getStreet3() %></span>
				</p>
			</c:if>

			<%
			Region region = commerceAddress.getRegion();
			%>

			<p>
				<span><%= commerceAddress.getCity() %></span>

				<c:if test="<%= region != null %>">
					<span><%= StringPool.COMMA_AND_SPACE %></span>
					<span><%= region.getName() %></span>
				</c:if>
			</p>

			<%
			Country country = commerceAddress.getCountry();
			%>

			<p>
				<span><%= commerceAddress.getZip() %></span>

				<c:if test="<%= country != null %>">
					<span><%= StringPool.COMMA_AND_SPACE %></span>
					<span><%= country.getName(locale) %></span>
				</c:if>
			</p>
		</c:if>
	</div>

	<div class="panel-body">
		<div class="h5">
			<liferay-ui:message key="delivery-date" />
		</div>

		<%
		Format format = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d, yyyy", locale, timeZone);
		%>

		<div class="commerce-value"><%= format.format(deliveryGroupDisplayContext.getDeliveryGroupDate(request)) %></div>
	</div>
</div>