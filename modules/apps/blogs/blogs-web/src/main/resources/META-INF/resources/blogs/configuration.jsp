<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs/init.jsp" %>

<%
BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(BlogsPortletInstanceConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource())));

BlogsPortletInstanceSettingsHelper blogsPortletInstanceSettingsHelper = new BlogsPortletInstanceSettingsHelper(request, blogsPortletInstanceConfiguration);
%>

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
		<liferay-frontend:fieldset
			collapsible="<%= false %>"
			label="general-settings"
		>
			<aui:input name="preferences--enableRatings--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableRatings() %>" />

			<c:if test="<%= PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED %>">
				<aui:input name="preferences--enableComments--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableComments() %>" />
			</c:if>

			<aui:input label="show-view-count" name="preferences--enableViewCount--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableViewCount() %>" />
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset
			collapsible="<%= false %>"
			label="social-bookmarks"
		>
			<liferay-social-bookmarks:bookmarks-settings
				displayStyle="<%= blogsPortletInstanceConfiguration.socialBookmarksDisplayStyle() %>"
				types="<%= SocialBookmarksUtil.getSocialBookmarksTypes(blogsPortletInstanceConfiguration) %>"
			/>
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset
			collapsible="<%= false %>"
			label="list-view"
		>
			<aui:select label="maximum-items-to-display" name="preferences--pageDelta--">

				<%
				for (int pageDeltaValue : PropsValues.BLOGS_ENTRY_PAGE_DELTA_VALUES) {
				%>

					<aui:option label="<%= pageDeltaValue %>" selected="<%= GetterUtil.getInteger(blogsPortletInstanceConfiguration.pageDelta()) == pageDeltaValue %>" />

				<%
				}
				%>

			</aui:select>

			<div class="display-template">

				<%
				List<String> displayStyles = new ArrayList<String>();

				displayStyles.add(BlogsUtil.DISPLAY_STYLE_ABSTRACT);
				displayStyles.add(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT);
				displayStyles.add(BlogsUtil.DISPLAY_STYLE_TITLE);
				%>

				<liferay-template:template-selector
					className="<%= BlogsEntry.class.getName() %>"
					displayStyle="<%= blogsPortletInstanceConfiguration.displayStyle() %>"
					displayStyleGroupId="<%= blogsPortletInstanceSettingsHelper.getDisplayStyleGroupId() %>"
					displayStyles="<%= displayStyles %>"
					refreshURL="<%= configurationRenderURL %>"
				/>
			</div>
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset
			collapsible="<%= false %>"
			label="detail-view"
		>
			<aui:input label="enable-report-inappropriate-content" name="preferences--enableFlags--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableFlags() %>" />

			<c:if test="<%= PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED %>">
				<aui:input label="enable-ratings-for-comments" name="preferences--enableCommentRatings--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableCommentRatings() %>" />
			</c:if>

			<aui:input label="show-related-assets" name="preferences--enableRelatedAssets--" type="checkbox" value="<%= blogsPortletInstanceConfiguration.enableRelatedAssets() %>" />
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>