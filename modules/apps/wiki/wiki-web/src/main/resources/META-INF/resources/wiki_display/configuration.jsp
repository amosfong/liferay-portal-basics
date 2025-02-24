<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki_display/init.jsp" %>

<%
nodeId = ParamUtil.getLong(request, "nodeId", nodeId);

List<WikiNode> nodes = WikiNodeServiceUtil.getNodes(scopeGroupId);

boolean nodeInGroup = false;
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
		<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />

		<liferay-frontend:fieldset>
			<c:choose>
				<c:when test="<%= !nodes.isEmpty() %>">
					<aui:select label="node" name="preferences--nodeId--">
						<aui:option value="" />

						<%
						for (WikiNode node : nodes) {
							int pagesCount = WikiPageLocalServiceUtil.getPagesCount(node.getNodeId(), true);

							if (pagesCount == 0) {
								continue;
							}

							node = node.toEscapedModel();

							if (nodeId == node.getNodeId()) {
								nodeInGroup = true;
							}
						%>

							<aui:option label="<%= node.getName() %>" selected="<%= nodeId == node.getNodeId() %>" value="<%= node.getNodeId() %>" />

						<%
						}
						%>

					</aui:select>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<liferay-ui:message key="there-are-no-available-nodes-for-selection" />
					</div>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="<%= nodeInGroup %>">
					<div id="<portlet:namespace />pageSelectorContainer">
						<aui:select label="page" name="preferences--title--">

							<%
							int total = WikiPageLocalServiceUtil.getPagesCount(nodeId, true);

							List<WikiPage> pages = WikiPageLocalServiceUtil.getPages(nodeId, true, 0, total);

							for (int i = 0; i < pages.size(); i++) {
								WikiPage wikiPage = pages.get(i);
							%>

								<aui:option label="<%= wikiPage.getTitle() %>" selected="<%= wikiPage.getTitle().equals(title) || (Validator.isNull(title) && wikiPage.getTitle().equals(wikiGroupServiceConfiguration.frontPageName())) %>" />

							<%
							}
							%>

						</aui:select>
					</div>
				</c:when>
				<c:otherwise>
					<aui:input name="preferences--title--" type="hidden" value="<%= wikiGroupServiceConfiguration.frontPageName() %>" />
				</c:otherwise>
			</c:choose>

			<aui:script>
				var nodeIdSelect = document.getElementById('<portlet:namespace />nodeId');
				var pageSelectorContainer = document.getElementById(
					'<portlet:namespace />pageSelectorContainer'
				);

				if (nodeIdSelect) {
					var nodeId = nodeIdSelect.value;

					nodeIdSelect.addEventListener('change', () => {
						if (pageSelectorContainer) {
							if (nodeIdSelect.value === nodeId) {
								pageSelectorContainer.classList.remove('hide');
							}
							else {
								pageSelectorContainer.classList.add('hide');
							}
						}

						if (nodeIdSelect.value && nodeIdSelect.value !== nodeId) {
							var renderURL = Liferay.Util.PortletURL.createRenderURL(
								'<%= configurationRenderURL %>',
								{
									nodeId: nodeIdSelect.value,
								}
							);

							document.<portlet:namespace />fm.action = renderURL;
							document.<portlet:namespace />fm.submit();
						}
					});
				}
			</aui:script>
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			submitDisabled="<%= nodes.isEmpty() %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>