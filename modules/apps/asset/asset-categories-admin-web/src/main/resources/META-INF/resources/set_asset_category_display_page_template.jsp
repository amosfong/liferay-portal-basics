<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
portletDisplay.setURLBackTitle(portletDisplay.getPortletDisplayName());

long[] categoryIds = ParamUtil.getLongValues(request, "categoryIds");

renderResponse.setTitle(LanguageUtil.format(request, "assign-display-page-template-for-x-categories", categoryIds.length));
%>

<portlet:actionURL name="/asset_categories_admin/set_asset_category_display_page_template" var="setCategoryDisplayPageTemplateURL">
	<portlet:param name="categoryIds" value="<%= StringUtil.merge(categoryIds) %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<liferay-frontend:edit-form
	action="<%= setCategoryDisplayPageTemplateURL %>"
	name="fm"
>
	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset
			label="display-page"
		>
			<liferay-asset:select-asset-display-page
				classNameId="<%= PortalUtil.getClassNameId(AssetCategory.class) %>"
				classPK="<%= 0 %>"
				classTypeId="<%= 0 %>"
				groupId="<%= scopeGroupId %>"
				parentClassPK='<%= ParamUtil.getLong(request, "parentCategoryId") %>'
				showViewInContextLink="<%= true %>"
			/>
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			redirect="<%= redirect %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>