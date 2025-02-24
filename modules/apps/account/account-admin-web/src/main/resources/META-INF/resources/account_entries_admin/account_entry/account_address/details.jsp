<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AddressDisplay addressDisplay = (AddressDisplay)request.getAttribute(AccountWebKeys.ADDRESS_DISPLAY);

Address address = AddressLocalServiceUtil.fetchAddress(addressDisplay.getAddressId());

String defaultType = ParamUtil.getString(request, "defaultType");

AccountEntryDisplay accountEntryDisplay = (AccountEntryDisplay)request.getAttribute(AccountWebKeys.ACCOUNT_ENTRY_DISPLAY);

String backURL = ParamUtil.getString(request, "backURL");

if (Validator.isNull(backURL)) {
	backURL = PortletURLBuilder.createRenderURL(
		renderResponse
	).setMVCRenderCommandName(
		"/account_admin/edit_account_entry"
	).setParameter(
		"accountEntryId", accountEntryDisplay.getAccountEntryId()
	).setParameter(
		"screenNavigationCategoryKey", "addresses"
	).buildString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle((addressDisplay.getAddressId() == 0) ? LanguageUtil.get(request, "add-address") : LanguageUtil.get(request, "edit-address"));
%>

<portlet:actionURL name="/account_admin/edit_account_entry_address" var="editAccountEntryAddressURL" />

<liferay-frontend:edit-form
	action="<%= editAccountEntryAddressURL %>"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (addressDisplay.getAddressId() == 0) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= backURL %>" />
	<aui:input name="accountEntryAddressId" type="hidden" value="<%= addressDisplay.getAddressId() %>" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryDisplay.getAccountEntryId() %>" />
	<aui:input name="defaultType" type="hidden" value="<%= defaultType %>" />

	<liferay-frontend:edit-form-body>
		<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />

		<aui:input name="name" />

		<aui:input name="description" type="textarea" />

		<aui:select label="type" name="addressListTypeId">

			<%
			String[] types = null;

			if (Objects.equals(defaultType, "billing") || Objects.equals(defaultType, "shipping")) {
				types = new String[] {defaultType, AccountListTypeConstants.ACCOUNT_ENTRY_ADDRESS_TYPE_BILLING_AND_SHIPPING};
			}
			else {
				types = new String[] {AccountListTypeConstants.ACCOUNT_ENTRY_ADDRESS_TYPE_BILLING_AND_SHIPPING, AccountListTypeConstants.ACCOUNT_ENTRY_ADDRESS_TYPE_BILLING, AccountListTypeConstants.ACCOUNT_ENTRY_ADDRESS_TYPE_SHIPPING};
			}

			ListType addressListType = null;

			if (address != null) {
				addressListType = address.getListType();
			}

			for (String type : types) {
				ListType listType = ListTypeLocalServiceUtil.getListType(themeDisplay.getCompanyId(), type, AccountEntry.class.getName() + ListTypeConstants.ADDRESS);
			%>

				<aui:option label="<%= LanguageUtil.get(request, type) %>" selected="<%= (address != null) ? Objects.equals(addressListType.getListTypeId(), listType.getListTypeId()) : false %>" value="<%= listType.getListTypeId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select label="country" name="addressCountryId" required="<%= true %>">
			<aui:validator errorMessage='<%= LanguageUtil.get(request, "this-field-is-required") %>' name="custom">
				function(val) {
					if (Number(val) !== 0) {
						return true;
					}

					return false;
				}
			</aui:validator>
		</aui:select>

		<aui:input name="street1" required="<%= true %>" />

		<aui:input name="street2" />

		<aui:input name="street3" />

		<div class="form-group-autofit">
			<div class="form-group-item">
				<aui:input name="city" required="<%= true %>" />
			</div>

			<div class="form-group-item">
				<aui:select label="region" name="addressRegionId">
					<aui:validator errorMessage='<%= LanguageUtil.get(request, "this-field-is-required") %>' name="custom">
						function(val, fieldNode) {
							if (fieldNode.length === 1) {
								return true;
							}

							if (Number(val) !== 0) {
								return true;
							}

							return false;
						}
					</aui:validator>
				</aui:select>
			</div>
		</div>

		<div class="form-group-autofit">
			<div class="form-group-item">
				<aui:input label="postal-code" name="zip" required="<%= true %>" />
			</div>

			<div class="form-group-item">
				<aui:input maxlength='<%= ModelHintsUtil.getMaxLength(Phone.class.getName(), "number") %>' name="phoneNumber" type="text" />
			</div>
		</div>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			redirect="<%= backURL %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<liferay-frontend:component
	componentId="CountryRegionDynamicSelect"
	context='<%=
		HashMapBuilder.<String, Object>put(
			"countrySelect", portletDisplay.getNamespace() + "addressCountryId"
		).put(
			"countrySelectVal", (address == null) ? 0L : address.getCountryId()
		).put(
			"regionSelect", portletDisplay.getNamespace() + "addressRegionId"
		).put(
			"regionSelectVal", (address == null) ? 0L : address.getRegionId()
		).build()
		%>'
	module="{CountryRegionDynamicSelect} from account-admin-web"
/>