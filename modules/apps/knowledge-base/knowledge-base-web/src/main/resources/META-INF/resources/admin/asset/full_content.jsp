<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/asset/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<%= kbArticle.getContent() %>

<%
List<FileEntry> attachmentsFileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	attachmentsFileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<c:if test="<%= !attachmentsFileEntries.isEmpty() %>">
	<div class="kb-attachments">
		<div class="h5"><liferay-ui:message key="attachments" /></div>

		<clay:row>

			<%
			for (FileEntry fileEntry : attachmentsFileEntries) {
			%>

				<clay:col
					md="4"
				>
					<clay:horizontal-card
						horizontalCard="<%= new KBArticleAttachmentHorizontalCard(fileEntry, request) %>"
					/>
				</clay:col>

			<%
			}
			%>

		</clay:row>
	</div>
</c:if>