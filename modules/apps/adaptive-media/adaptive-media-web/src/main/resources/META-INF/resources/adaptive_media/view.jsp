<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/adaptive_media/init.jsp" %>

<%
SearchContainer<?> amSearchContainer = new SearchContainer<>(renderRequest, renderResponse.createRenderURL(), null, "there-are-no-image-resolutions");

amSearchContainer.setId("imageConfigurationEntries");
amSearchContainer.setResultsAndTotal((List)request.getAttribute(AMWebKeys.CONFIGURATION_ENTRIES_LIST));
amSearchContainer.setRowChecker(new ImageConfigurationEntriesChecker(liferayPortletResponse));

int totalImages = GetterUtil.getInteger(request.getAttribute(AMWebKeys.TOTAL_IMAGES));
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new AMManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, amSearchContainer) %>"
/>

<div class="closed sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/adaptive_media/info_panel" var="sidebarPanelURL">
		<portlet:param name="totalImages" value="<%= String.valueOf(totalImages) %>" />
	</liferay-portlet:resourceURL>

	<liferay-frontend:sidebar-panel
		resourceURL="<%= sidebarPanelURL %>"
		searchContainerId="imageConfigurationEntries"
	>
		<liferay-util:include page="/adaptive_media/info_panel.jsp" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<clay:container-fluid
		cssClass="sidenav-content"
	>
		<liferay-util:include page="/adaptive_media/success_messages.jsp" servletContext="<%= application %>" />

		<c:choose>
			<c:when test='<%= SessionMessages.contains(request, "configurationEntryUpdated") %>'>

				<%
				AMImageConfigurationEntry amImageConfigurationEntry = (AMImageConfigurationEntry)SessionMessages.get(request, "configurationEntryUpdated");
				%>

				<div class="alert alert-success">
					<liferay-ui:message arguments="<%= HtmlUtil.escape(amImageConfigurationEntry.getName()) %>" key="x-saved-successfully" translateArguments="<%= false %>" />
				</div>
			</c:when>
		</c:choose>

		<portlet:actionURL name="/adaptive_media/delete_image_configuration_entry" var="deleteImageConfigurationEntryURL" />

		<%
		int optimizeImagesAllConfigurationsBackgroundTasksCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(CompanyConstants.SYSTEM, OptimizeImagesAllConfigurationsBackgroundTaskExecutor.class.getName(), false);

		List<BackgroundTask> optimizeImageSingleBackgroundTasks = BackgroundTaskManagerUtil.getBackgroundTasks(CompanyConstants.SYSTEM, OptimizeImagesSingleConfigurationBackgroundTaskExecutor.class.getName(), BackgroundTaskConstants.STATUS_IN_PROGRESS);

		request.setAttribute("view.jsp-optimizeImageSingleBackgroundTasks", optimizeImageSingleBackgroundTasks);

		List<String> currentBackgroundTaskConfigurationEntryUuids = new ArrayList<>();

		for (BackgroundTask optimizeImageSingleBackgroundTask : optimizeImageSingleBackgroundTasks) {
			Map<String, Serializable> taskContextMap = optimizeImageSingleBackgroundTask.getTaskContextMap();

			String configurationEntryUuid = (String)taskContextMap.get("configurationEntryUuid");

			currentBackgroundTaskConfigurationEntryUuids.add(configurationEntryUuid);
		}
		%>

		<aui:form action="<%= deleteImageConfigurationEntryURL %>" method="post" name="fm">
			<liferay-ui:search-container
				searchContainer="<%= amSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry"
					modelVar="amImageConfigurationEntry"
				>

					<%
					row.setPrimaryKey(String.valueOf(amImageConfigurationEntry.getUUID()));
					%>

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="mvcRenderCommandName" value="/adaptive_media/edit_image_configuration_entry" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="entryUuid" value="<%= String.valueOf(amImageConfigurationEntry.getUUID()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200 table-title"
						href="<%= rowURL %>"
						name="name"
						orderable="<%= false %>"
						value="<%= HtmlUtil.escape(amImageConfigurationEntry.getName()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
						name="state"
						orderable="<%= false %>"
						value='<%= LanguageUtil.get(request, amImageConfigurationEntry.isEnabled() ? "enabled" : "disabled") %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200"
						name="adapted-images"
					>

						<%
						String rowId = row.getRowId();
						String uuid = String.valueOf(amImageConfigurationEntry.getUUID());

						int adaptedImages = AMImageEntryLocalServiceUtil.getAMImageEntriesCount(themeDisplay.getCompanyId(), amImageConfigurationEntry.getUUID());
						%>

						<div id="<portlet:namespace />AdaptRemainingContainer_<%= rowId %>">
							<portlet:resourceURL id="/adaptive_media/adapted_images_percentage" var="adaptedImagesPercentageURL">
								<portlet:param name="entryUuid" value="<%= uuid %>" />
							</portlet:resourceURL>

							<react:component
								module="{AdaptiveMediaProgress} from adaptive-media-web"
								props='<%=
									HashMapBuilder.<String, Object>put(
										"adaptedImages", Math.min(adaptedImages, totalImages)
									).put(
										"adaptiveMediaProgressComponentId", liferayPortletResponse.getNamespace() + "AdaptRemaining" + uuid
									).put(
										"autoStartProgress", ((optimizeImagesAllConfigurationsBackgroundTasksCount > 0) && amImageConfigurationEntry.isEnabled()) || currentBackgroundTaskConfigurationEntryUuids.contains(uuid)
									).put(
										"disabled", !amImageConfigurationEntry.isEnabled()
									).put(
										"namespace", liferayPortletResponse.getNamespace()
									).put(
										"percentageUrl", adaptedImagesPercentageURL.toString()
									).put(
										"totalImages", totalImages
									).put(
										"uuid", uuid
									).build()
								%>'
							/>
						</div>
					</liferay-ui:search-container-column-text>

					<%
					Map<String, String> properties = amImageConfigurationEntry.getProperties();

					String maxWidth = properties.get("max-width");
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-ws-nowrap"
						name="max-width"
						orderable="<%= false %>"
						value='<%= (Validator.isNull(maxWidth) || maxWidth.equals("0")) ? LanguageUtil.get(request, "auto") : maxWidth + "px" %>'
					/>

					<%
					String maxHeight = properties.get("max-height");
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-ws-nowrap"
						name="max-height"
						orderable="<%= false %>"
						value='<%= (Validator.isNull(maxHeight) || maxHeight.equals("0")) ? LanguageUtil.get(request, "auto") : maxHeight + "px" %>'
					/>

					<liferay-ui:search-container-column-jsp
						path="/adaptive_media/image_configuration_entry_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					displayStyle="list"
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</clay:container-fluid>
</div>

<aui:script>
	function <portlet:namespace />adaptRemaining(uuid, backgroundTaskUrl) {
		var component = Liferay.component(
			'<portlet:namespace />AdaptRemaining' + uuid
		);

		component.startProgress(backgroundTaskUrl);
	}
</aui:script>