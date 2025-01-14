<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE_DISPLAY);

MBMessage message = messageDisplay.getMessage();

MBThread thread = messageDisplay.getThread();

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(MBMessage.class.getName(), message.getMessageId());

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, layoutAssetEntry);

LinkedAssetEntryIdsUtil.addLinkedAssetEntryId(request, layoutAssetEntry.getEntryId());

AssetEntryServiceUtil.incrementViewCounter(layoutAssetEntry);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

MBBreadcrumbUtil.addPortletBreadcrumbEntries(message, request, renderResponse);
%>

<liferay-editor:resources
	editorName="<%= MBUtil.getEditorName(messageFormat) %>"
/>

<div <%= portletTitleBasedNavigation ? "class=\"container-fluid container-fluid-max-xl\"" : StringPool.BLANK %>>
	<c:if test="<%= !portletTitleBasedNavigation %>">

		<%
		String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName", "/message_boards/view");
		%>

		<%@ include file="/message_boards/nav.jspf" %>
	</c:if>

	<div <%= !portletTitleBasedNavigation ? "class=\"main-content-body mt-4\"" : StringPool.BLANK %>>
		<c:if test="<%= !portletTitleBasedNavigation %>">
			<liferay-site-navigation:breadcrumb
				breadcrumbEntries="<%= BreadcrumbEntriesUtil.getBreadcrumbEntries(request, false, false, false, false, true) %>"
			/>
		</c:if>

		<liferay-util:include page="/message_boards/view_message_content.jsp" servletContext="<%= application %>" />
	</div>
</div>

<aui:script sandbox="<%= true %>">
	window['<portlet:namespace />addReplyToMessage'] = function (messageId, quote) {
		var addQuickReplyContainer = document.querySelector(
			'#<portlet:namespace />addReplyToMessage' + messageId + ' .panel'
		);

		if (addQuickReplyContainer) {
			var editorName = '<portlet:namespace />replyMessageBody' + messageId;

			if (window[editorName]) {
				addQuickReplyContainer.classList.remove('hide');
				addQuickReplyContainer.scrollIntoView(true);

				Liferay.Util.toggleDisabled(
					'#<portlet:namespace />replyMessageButton' + messageId,
					true
				);

				return;
			}

			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/message_boards/get_edit_message_quick" var="editMessageQuickURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:resourceURL>

			var editMessageQuickURL = Liferay.Util.addParams(
				'<portlet:namespace />messageId=' + messageId,
				'<%= editMessageQuickURL.toString() %>'
			);

			if (quote) {
				editMessageQuickURL = Liferay.Util.addParams(
					'<portlet:namespace />quote=true',
					editMessageQuickURL
				);
			}

			var addQuickReplyLoadingMask = document.querySelector(
				'#<portlet:namespace />addReplyToMessage' +
					messageId +
					' .loading-animation'
			);

			addQuickReplyContainer.classList.add('hide');
			addQuickReplyLoadingMask.classList.remove('hide');

			Liferay.Util.fetch(editMessageQuickURL)
				.then((response) => {
					return response.text();
				})
				.then((response) => {
					addQuickReplyContainer.innerHTML = response;

					Liferay.Util.runScriptsInElement(addQuickReplyContainer);

					addQuickReplyContainer.classList.remove('hide');
					addQuickReplyLoadingMask.classList.add('hide');

					var parentMessageIdInput = addQuickReplyContainer.querySelector(
						'#<portlet:namespace />parentMessageId'
					);

					if (parentMessageIdInput) {
						parentMessageIdInput.value = messageId;
					}

					Liferay.componentReady(editorName).then((editor) => {
						editor.focus();
					});

					if (addQuickReplyContainer) {
						addQuickReplyContainer.scrollIntoView(true);
					}

					Liferay.Util.toggleDisabled(
						'#<portlet:namespace />replyMessageButton' + messageId,
						true
					);
				});
		}
	};
</aui:script>

<aui:script>
	function <portlet:namespace />hideReplyMessage(messageId) {
		var addQuickReplyContainer = document.querySelector(
			'#<portlet:namespace />addReplyToMessage' + messageId + ' .panel'
		);

		if (addQuickReplyContainer) {
			addQuickReplyContainer.classList.add('hide');
		}

		Liferay.Util.toggleDisabled(
			'#<portlet:namespace />replyMessageButton' + messageId,
			false
		);
	}

	<c:if test="<%= thread.getRootMessageId() != message.getMessageId() %>">
		var message = document.getElementById(
			'<portlet:namespace />message_' + <%= message.getMessageId() %>
		);

		if (message) {
			message.scrollIntoView(true);
		}
	</c:if>
</aui:script>

<%
MBThreadFlagLocalServiceUtil.addThreadFlag(themeDisplay.getUserId(), thread, new ServiceContext());

PortalUtil.setPageSubtitle(message.getSubject(), request);
PortalUtil.setPageDescription(message.getSubject(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(MBMessage.class.getName(), message.getMessageId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);
%>