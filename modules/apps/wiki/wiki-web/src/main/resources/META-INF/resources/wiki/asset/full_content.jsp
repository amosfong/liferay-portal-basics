<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/asset/init.jsp" %>

<%
final WikiPage wikiPage = (WikiPage)request.getAttribute(WikiWebKeys.WIKI_PAGE);

PortletURL viewPageURL = PortletURLBuilder.create(
	PortletURLFactoryUtil.create(request, WikiPortletKeys.WIKI, PortletRequest.ACTION_PHASE)
).setActionName(
	"/wiki/view"
).setParameter(
	"nodeId", wikiPage.getNodeId()
).setPortletMode(
	PortletMode.VIEW
).setWindowState(
	WindowState.MAXIMIZED
).buildPortletURL();

StringBundler sb = new StringBundler(8);

sb.append(themeDisplay.getPathMain());
sb.append("/wiki/get_page_attachment?p_l_id=");
sb.append(themeDisplay.getPlid());
sb.append("&nodeId=");
sb.append(wikiPage.getNodeId());
sb.append("&title=");
sb.append(URLCodec.encodeURL(wikiPage.getTitle()));
sb.append("&fileName=");

final String redirectURL = currentURL;

final HttpServletRequest httpServletRequest = request;

WikiPageDisplay pageDisplay = WikiPageLocalServiceUtil.getPageDisplay(
	wikiPage, viewPageURL,
	new Supplier<PortletURL>() {

		public PortletURL get() {
			PortletURL editPageURL = PortletURLBuilder.create(
				PortletURLFactoryUtil.create(httpServletRequest, WikiPortletKeys.WIKI, PortletRequest.ACTION_PHASE)
			).setActionName(
				"/wiki/edit_page"
			).setRedirect(
				redirectURL
			).setParameter(
				"nodeId", wikiPage.getNodeId()
			).buildPortletURL();

			try {
				editPageURL.setPortletMode(PortletMode.VIEW);
				editPageURL.setWindowState(WindowState.MAXIMIZED);
			}
			catch (Exception e) {
				ReflectionUtil.throwException(e);
			}

			return editPageURL;
		}

	},
	sb.toString(), ServiceContextFactory.getInstance(request));
%>

<%= pageDisplay.getFormattedContent() %>

<c:if test="<%= wikiPage.getAttachmentsFileEntriesCount() > 0 %>">
	<div class="page-attachments">
		<div class="h5"><liferay-ui:message key="attachments" /></div>

		<clay:row>

			<%
			for (FileEntry fileEntry : wikiPage.getAttachmentsFileEntries()) {
			%>

				<clay:col
					md="4"
				>
					<clay:horizontal-card
						horizontalCard="<%= new WikiPageAttachmentHorizontalCard(fileEntry, request) %>"
					/>
				</clay:col>

			<%
			}
			%>

		</clay:row>
	</div>
</c:if>

<liferay-expando:custom-attributes-available
	className="<%= WikiPage.class.getName() %>"
>
	<liferay-expando:custom-attribute-list
		className="<%= WikiPage.class.getName() %>"
		classPK="<%= wikiPage.getPrimaryKey() %>"
		editable="<%= false %>"
		label="<%= true %>"
	/>
</liferay-expando:custom-attributes-available>