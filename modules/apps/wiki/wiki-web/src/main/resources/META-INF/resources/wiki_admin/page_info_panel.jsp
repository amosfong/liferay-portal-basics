<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiEngineRenderer wikiEngineRenderer = (WikiEngineRenderer)request.getAttribute(WikiWebKeys.WIKI_ENGINE_RENDERER);

WikiPageInfoPanelDisplayContext wikiPageInfoPanelDisplayContext = new WikiPageInfoPanelDisplayContext(request);

request.setAttribute("page_info_panel.jsp-wikiPage", wikiPageInfoPanelDisplayContext.getFirstPage());
%>

<c:choose>
	<c:when test="<%= wikiPageInfoPanelDisplayContext.isShowSidebarHeader() %>">
		<div class="sidebar-header">
			<c:choose>
				<c:when test="<%= wikiPageInfoPanelDisplayContext.isSinglePageSelection() %>">
					<div class="autofit-row sidebar-section">
						<div class="autofit-col autofit-col-expand">

							<%
							WikiPage wikiPage = wikiPageInfoPanelDisplayContext.getFirstPage();
							%>

							<div class="component-title">
								<%= HtmlUtil.escape(wikiPage.getTitle()) %>
							</div>

							<div class="component-subtitle">
								<liferay-ui:message key="page" />
							</div>
						</div>

						<div class="autofit-col">
							<ul class="autofit-padded-no-gutters autofit-row">
								<li class="autofit-col">
									<liferay-util:include page="/wiki/subscribe.jsp" servletContext="<%= application %>" />
								</li>
								<li class="autofit-col">
									<liferay-util:include page="/wiki/page_action.jsp" servletContext="<%= application %>" />
								</li>
							</ul>
						</div>
					</div>
				</c:when>
				<c:when test="<%= wikiPageInfoPanelDisplayContext.isMultiplePageSelection() %>">
					<div class="autofit-row sidebar-section">
						<div class="autofit-col autofit-col-expand">
							<div class="component-title"><liferay-ui:message arguments="<%= wikiPageInfoPanelDisplayContext.getSelectedPagesCount() %>" key="x-items-are-selected" /></div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="autofit-row sidebar-section">
						<div class="autofit-col autofit-col-expand">
							<div class="component-title"><liferay-ui:message key="pages" /></div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<div class="d-block d-sm-none sidebar-header">

			<%
			WikiPage wikiPage = wikiPageInfoPanelDisplayContext.getFirstPage();
			%>

			<div class="autofit-row sidebar-section">
				<div class="autofit-col autofit-col-expand">
					<div class="component-title">
						<%= HtmlUtil.escape(wikiPage.getTitle()) %>
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<%
String sections = "details";

if (wikiPageInfoPanelDisplayContext.isSinglePageSelection()) {
	sections += ",versions,activity,links";
}
%>

<liferay-ui:tabs
	cssClass="navbar-no-collapse"
	names="<%= sections %>"
	refresh="<%= false %>"
	type="dropdown"
>
	<liferay-ui:section>
		<div class="sidebar-body">
			<c:choose>
				<c:when test="<%= wikiPageInfoPanelDisplayContext.isSinglePageSelection() %>">

					<%
					WikiPage wikiPage = wikiPageInfoPanelDisplayContext.getFirstPage();
					%>

					<dl class="sidebar-dl sidebar-section">
						<c:if test="<%= Validator.isNotNull(wikiPage.getSummary()) %>">
							<dt class="sidebar-dt">
								<liferay-ui:message key="summary" />
							</dt>
							<dd class="sidebar-dd">
								<%= HtmlUtil.escape(wikiPage.getSummary()) %>
							</dd>
						</c:if>

						<dt class="sidebar-dt">
							<liferay-ui:message key="format" />
						</dt>
						<dd class="sidebar-dd">
							<liferay-ui:message key="<%= WikiUtil.getFormatLabel(wikiEngineRenderer, wikiPage.getFormat(), locale) %>" />
						</dd>
						<dt class="sidebar-dt">
							<liferay-ui:message key="latest-version" />
						</dt>
						<dd class="sidebar-dd">
							<%= wikiPage.getVersion() %>

							<c:if test="<%= wikiPage.isMinorEdit() %>">
								(<liferay-ui:message key="minor-edit" />)
							</c:if>
						</dd>
						<dt class="sidebar-dt">
							<liferay-ui:message key="create-date" />
						</dt>
						<dd class="sidebar-dd">
							<%= dateTimeFormat.format(wikiPage.getCreateDate()) %>
						</dd>
						<dt class="sidebar-dt">
							<liferay-ui:message key="last-modified" />
						</dt>
						<dd class="sidebar-dd">
							<%= dateTimeFormat.format(wikiPage.getModifiedDate()) %>
						</dd>
						<dt class="sidebar-dt">
							<liferay-ui:message key="attachments" />
						</dt>
						<dd class="sidebar-dd">
							<%= wikiPage.getAttachmentsFileEntriesCount() %>
						</dd>
						<dt class="sidebar-dt">
							<liferay-ui:message key="rss" />
						</dt>
						<dd class="sidebar-dd">
							<aui:a href="<%= wikiPageInfoPanelDisplayContext.getPageRSSURL(wikiPage) %>" target="_blank">
								<liferay-ui:message key="feed" />
							</aui:a>
						</dd>
					</dl>

					<div class="lfr-asset-categories sidebar-section">
						<liferay-asset:asset-categories-summary
							className="<%= WikiPage.class.getName() %>"
							classPK="<%= wikiPage.getResourcePrimKey() %>"
							message="categories"
						/>
					</div>

					<div class="lfr-asset-tags sidebar-section">
						<liferay-asset:asset-tags-summary
							className="<%= WikiPage.class.getName() %>"
							classPK="<%= wikiPage.getResourcePrimKey() %>"
							message="tags"
						/>
					</div>

					<c:if test="<%= wikiPortletInstanceSettingsHelper.isEnablePageRatings() %>">
						<liferay-ratings:ratings
							className="<%= WikiPage.class.getName() %>"
							classPK="<%= wikiPage.getResourcePrimKey() %>"
							inTrash="<%= wikiPage.isInTrash() %>"
						/>
					</c:if>

					<liferay-expando:custom-attributes-available
						className="<%= WikiPage.class.getName() %>"
					>
						<liferay-expando:custom-attribute-list
							className="<%= WikiPage.class.getName() %>"
							classPK="<%= wikiPage.getResourcePrimKey() %>"
							editable="<%= false %>"
							label="<%= true %>"
						/>
					</liferay-expando:custom-attributes-available>

					<%
					AssetEntry wikiPageAssetEntry = AssetEntryLocalServiceUtil.fetchEntry(WikiPage.class.getName(), wikiPage.getPrimaryKey());
					%>

					<c:if test="<%= (wikiPageAssetEntry != null) && wikiPortletInstanceSettingsHelper.isEnableRelatedAssets() %>">
						<div class="entry-links">
							<liferay-asset:asset-links
								assetEntryId="<%= wikiPageAssetEntry.getEntryId() %>"
							/>
						</div>
					</c:if>
				</c:when>
				<c:when test="<%= wikiPageInfoPanelDisplayContext.isMultiplePageSelection() %>">
					<div class="h5"><liferay-ui:message arguments="<%= wikiPageInfoPanelDisplayContext.getSelectedPagesCount() %>" key="x-items-are-selected" /></div>
				</c:when>
				<c:otherwise>
					<dl class="sidebar-dl sidebar-section">
						<dt class="sidebar-dt">
							<liferay-ui:message key="num-of-items" />
						</dt>
						<dd class="h6 sidebar-dd">
							<%= wikiPageInfoPanelDisplayContext.getPagesCount() %>
						</dd>
					</dl>
				</c:otherwise>
			</c:choose>
		</div>
	</liferay-ui:section>

	<c:if test="<%= wikiPageInfoPanelDisplayContext.isSinglePageSelection() %>">

		<%
		WikiPage wikiPage = wikiPageInfoPanelDisplayContext.getFirstPage();
		%>

		<liferay-ui:section>
			<div class="sidebar-body">
				<ul class="list-group sidebar-list-group">

					<%
					for (WikiPage curPage : WikiPageLocalServiceUtil.getPages(wikiPage.getNodeId(), wikiPage.getTitle(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, PageVersionComparator.getInstance(false))) {
					%>

						<li class="list-group-item list-group-item-flex">
							<div class="autofit-col autofit-col-expand">
								<div class="h5">
									<liferay-ui:message arguments="<%= curPage.getVersion() %>" key="version-x" />
								</div>

								<div class="h6 sidebar-caption">
									<liferay-ui:message arguments='<%= new Object[] {HtmlUtil.escape(Validator.isNotNull(curPage.getUserName()) ? curPage.getUserName() : "Liferay"), dateTimeFormat.format(curPage.getStatusDate())} %>' key="by-x-on-x" />
								</div>
							</div>

							<div class="autofit-col">

								<%
								request.setAttribute("page_info_panel.jsp-wikiPage", curPage);
								%>

								<liferay-util:include page="/wiki/page_history_action.jsp" servletContext="<%= application %>" />
							</div>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="sidebar-body">
				<ul class="list-group sidebar-list-group">

					<%
					WikiSocialActivityHelper wikiSocialActivityHelper = new WikiSocialActivityHelper(wikiRequestHelper);

					List<SocialActivity> socialActivities = SocialActivityLocalServiceUtil.getActivities(0, WikiPage.class.getName(), wikiPage.getResourcePrimKey(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SocialActivity socialActivity : socialActivities) {
						JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(socialActivity.getExtraData());

						String path = wikiSocialActivityHelper.getSocialActivityActionJSP(socialActivity, extraDataJSONObject);
					%>

						<li class="list-group-item list-group-item-flex">
							<div class="autofit-col autofit-col-expand">
								<div class="h5">
									<%= wikiSocialActivityHelper.getSocialActivityDescription(wikiPage, socialActivity, extraDataJSONObject, resourceBundle) %>
								</div>

								<div class="h6 sidebar-caption">
									<%= dateTimeFormat.format(socialActivity.getCreateDate()) %>
								</div>
							</div>

							<c:if test="<%= Validator.isNotNull(path) %>">
								<div class="autofit-col">

									<%
									request.setAttribute(WikiWebKeys.WIKI_PAGE, wikiPage);
									request.setAttribute("page_info_panel.jsp-socialActivity", socialActivity);
									%>

									<liferay-util:include page="<%= path %>" servletContext="<%= application %>" />
								</div>
							</c:if>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="sidebar-body">
				<liferay-util:include page="/wiki/page_links.jsp" servletContext="<%= application %>" />
			</div>
		</liferay-ui:section>
	</c:if>
</liferay-ui:tabs>