<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset>
			<div class="display-template">

				<%
				List<String> displayStyles = new ArrayList<String>();

				displayStyles.add("number");
				displayStyles.add("cloud");
				%>

				<liferay-template:template-selector
					className="<%= AssetTag.class.getName() %>"
					displayStyle="<%= displayStyle %>"
					displayStyleGroupId="<%= displayStyleGroupId %>"
					displayStyles="<%= displayStyles %>"
					refreshURL="<%= configurationRenderURL %>"
				/>
			</div>

			<aui:input label="max-num-of-tags" name="preferences--maxAssetTags--" type="text" value="<%= maxAssetTags %>" />

			<aui:input inlineLabel="right" label="show-unused-tags" labelCssClass="simple-toggle-switch" name="preferences--showZeroAssetCount--" type="toggle-switch" value="<%= showZeroAssetCount %>" />

			<aui:input inlineLabel="right" labelCssClass="simple-toggle-switch" name="preferences--showAssetCount--" type="toggle-switch" value="<%= showAssetCount %>" />

			<div class="<%= showAssetCount ? "" : "hide" %>" id="<portlet:namespace />assetCountOptions">
				<aui:select helpMessage="asset-type-asset-count-help" label="asset-type" name="preferences--classNameId--">
					<aui:option label="any" value="<%= classNameId == 0 %>" />

					<%
					List<AssetRendererFactory<?>> assetRendererFactories = ListUtil.sort(AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId(), true), new AssetRendererFactoryTypeNameComparator(locale));

					for (AssetRendererFactory<?> assetRendererFactory : assetRendererFactories) {
					%>

						<aui:option label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) %>" selected="<%= classNameId == assetRendererFactory.getClassNameId() %>" value="<%= assetRendererFactory.getClassNameId() %>" />

					<%
					}
					%>

				</aui:select>
			</div>
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script>
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />showAssetCount',
		'<portlet:namespace />assetCountOptions'
	);
</aui:script>