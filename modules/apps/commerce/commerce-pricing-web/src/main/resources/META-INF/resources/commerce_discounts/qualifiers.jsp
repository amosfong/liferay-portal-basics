<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountQualifiersDisplayContext commerceDiscountQualifiersDisplayContext = (CommerceDiscountQualifiersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDiscount commerceDiscount = commerceDiscountQualifiersDisplayContext.getCommerceDiscount();
long commerceDiscountId = commerceDiscountQualifiersDisplayContext.getCommerceDiscountId();

String accountQualifiers = ParamUtil.getString(request, "accountQualifiers", commerceDiscountQualifiersDisplayContext.getActiveAccountEligibility());
String channelQualifiers = ParamUtil.getString(request, "channelQualifiers", commerceDiscountQualifiersDisplayContext.getActiveChannelEligibility());
String orderTypeQualifiers = ParamUtil.getString(request, "orderTypeQualifiers", commerceDiscountQualifiersDisplayContext.getActiveOrderTypeEligibility());

boolean hasPermission = commerceDiscountQualifiersDisplayContext.hasPermission(ActionKeys.UPDATE);
%>

<portlet:actionURL name="/commerce_discount/edit_commerce_discount_qualifiers" var="editCommerceDiscountQualifiersActionURL" />

<aui:form action="<%= editCommerceDiscountQualifiersActionURL %>" cssClass="pt-4" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceDiscount == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="accountQualifiers" type="hidden" value="<%= accountQualifiers %>" />
	<aui:input name="channelQualifiers" type="hidden" value="<%= channelQualifiers %>" />
	<aui:input name="commerceDiscountId" type="hidden" value="<%= commerceDiscountId %>" />
	<aui:input name="externalReferenceCode" type="hidden" value="<%= commerceDiscount.getExternalReferenceCode() %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />

	<aui:model-context bean="<%= commerceDiscount %>" model="<%= CommerceDiscount.class %>" />

	<div class="row">
		<div class="col-12">
			<commerce-ui:panel
				bodyClasses="flex-fill"
				collapsed="<%= false %>"
				collapsible="<%= false %>"
				title='<%= LanguageUtil.get(request, "account-eligibility") %>'
			>
				<div class="row">
					<aui:fieldset markupView="lexicon">
						<aui:input checked='<%= Objects.equals(accountQualifiers, "all") %>' label="all-accounts" name="qualifiers--accountQualifiersSelection--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseAccountQualifiers('all');" %>' type="radio" />
						<aui:input checked='<%= Objects.equals(accountQualifiers, "accountGroups") %>' label="specific-account-groups" name="qualifiers--accountQualifiersSelection--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseAccountQualifiers('accountGroups');" %>' type="radio" />
						<aui:input checked='<%= Objects.equals(accountQualifiers, "accounts") %>' label="specific-accounts" name="qualifiers--accountQualifiersSelection--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseAccountQualifiers('accounts');" %>' type="radio" />
					</aui:fieldset>
				</div>
			</commerce-ui:panel>
		</div>
	</div>

	<c:if test='<%= Objects.equals(accountQualifiers, "accounts") %>'>
		<%@ include file="/commerce_discounts/qualifier/accounts.jspf" %>
	</c:if>

	<c:if test='<%= Objects.equals(accountQualifiers, "accountGroups") %>'>
		<%@ include file="/commerce_discounts/qualifier/account_groups.jspf" %>
	</c:if>

	<div class="row">
		<div class="col-12">
			<commerce-ui:panel
				bodyClasses="flex-fill"
				collapsed="<%= false %>"
				collapsible="<%= false %>"
				title='<%= LanguageUtil.get(request, "channel-eligibility") %>'
			>
				<div class="row">
					<aui:fieldset markupView="lexicon">
						<aui:input checked='<%= Objects.equals(channelQualifiers, "all") %>' label="all-channels" name="qualifiers--channel--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseChannelQualifiers('all');" %>' type="radio" />
						<aui:input checked='<%= Objects.equals(channelQualifiers, "channels") %>' label="specific-channels" name="qualifiers--channel--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseChannelQualifiers('channels');" %>' type="radio" />
					</aui:fieldset>
				</div>
			</commerce-ui:panel>
		</div>
	</div>

	<c:if test='<%= Objects.equals(channelQualifiers, "channels") %>'>
		<%@ include file="/commerce_discounts/qualifier/channels.jspf" %>
	</c:if>

	<div class="row">
		<div class="col-12">
			<commerce-ui:panel
				bodyClasses="flex-fill"
				collapsed="<%= false %>"
				collapsible="<%= false %>"
				title='<%= LanguageUtil.get(request, "order-type-eligibility") %>'
			>
				<div class="row">
					<aui:fieldset markupView="lexicon">
						<aui:input checked='<%= Objects.equals(orderTypeQualifiers, "all") %>' label="all-order-types" name="qualifiers--orderType--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseOrderTypeQualifiers('all');" %>' type="radio" />
						<aui:input checked='<%= Objects.equals(orderTypeQualifiers, "orderTypes") %>' label="specific-order-types" name="qualifiers--orderType--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseOrderTypeQualifiers('orderTypes');" %>' type="radio" />
					</aui:fieldset>
				</div>
			</commerce-ui:panel>
		</div>
	</div>

	<c:if test='<%= Objects.equals(orderTypeQualifiers, "orderTypes") %>'>
		<%@ include file="/commerce_discounts/qualifier/order_types.jspf" %>
	</c:if>
</aui:form>

<aui:script sandbox="<%= true %>">
	Liferay.provide(
		window,
		'<portlet:namespace />chooseAccountQualifiers',
		(value) => {
			const portletURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= currentURLObj %>',
				{
					accountQualifiers: value,
				}
			);

			window.location.replace(portletURL.toString());
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />chooseChannelQualifiers',
		(value) => {
			const portletURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= currentURLObj %>',
				{
					channelQualifiers: value,
				}
			);

			window.location.replace(portletURL.toString());
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />chooseOrderTypeQualifiers',
		(value) => {
			const portletURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= currentURLObj %>',
				{
					orderTypeQualifiers: value,
				}
			);

			window.location.replace(portletURL.toString());
		}
	);
</aui:script>