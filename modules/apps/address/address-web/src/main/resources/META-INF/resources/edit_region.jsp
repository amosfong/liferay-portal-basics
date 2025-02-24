<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long regionId = ParamUtil.getLong(request, "regionId");

Region region = RegionLocalServiceUtil.fetchRegion(regionId);

String backURL = ParamUtil.getString(request, "backURL", String.valueOf(renderResponse.createRenderURL()));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle((region == null) ? LanguageUtil.get(request, "add-region") : LanguageUtil.format(request, "edit-x", region.getTitle(LocaleUtil.toLanguageId(locale)), false));
%>

<portlet:actionURL name="/address/edit_region" var="editRegionURL" />

<liferay-frontend:edit-form
	action="<%= editRegionURL %>"
>
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="countryId" type="hidden" value='<%= ParamUtil.getString(request, "countryId") %>' />
	<aui:input name="regionId" type="hidden" value="<%= String.valueOf(regionId) %>" />

	<liferay-ui:error exception="<%= DuplicateRegionException.class %>" message="the-region-code-is-already-used" />
	<liferay-ui:error exception="<%= RegionCodeException.class %>" message="please-enter-a-valid-region-code" />
	<liferay-ui:error exception="<%= RegionNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= region %>" model="<%= Region.class %>" />

	<liferay-frontend:edit-form-body>
		<div class="form-group">
			<label>
				<liferay-ui:message key="name" />

				<liferay-ui:icon-help message="region-name-field-help" />
			</label>

			<liferay-ui:input-localized
				cssClass="form-group"
				name="title"
				xml="<%= (region == null) ? StringPool.BLANK : region.getTitleMapAsXML() %>"
			/>
		</div>

		<aui:input helpMessage="region-key-field-help" label="key" name="name" />

		<aui:input label="region-code" name="regionCode" />

		<aui:input id="priority" label="priority" name="position" />

		<aui:input checked="<%= (region == null) ? true : region.isActive() %>" inlineLabel="right" labelCssClass="simple-toggle-switch" name="active" type="toggle-switch" />
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			redirect="<%= backURL %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<c:if test="<%= region == null %>">
	<aui:script sandbox="<%= true %>">
		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var nameInput = form.querySelector('#<portlet:namespace />name');
			var titleInput = form.querySelector('#<portlet:namespace />title');

			if (nameInput && titleInput) {
				var handleOnTitleInput = function (event) {
					var value = event.target.value;

					if (nameInput.hasAttribute('maxLength')) {
						value = value.substring(0, nameInput.getAttribute('maxLength'));
					}

					nameInput.value = value;
				};

				titleInput.addEventListener(
					'input',
					Liferay.Util.debounce(handleOnTitleInput, 200)
				);
			}
		}
	</aui:script>
</c:if>