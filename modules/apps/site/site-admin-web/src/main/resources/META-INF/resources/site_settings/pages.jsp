<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group siteGroup = (Group)request.getAttribute("site.group");

Group liveGroup = (Group)request.getAttribute("site.liveGroup");

List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);

LayoutSetPrototype publicLayoutSetPrototype = null;
boolean publicLayoutSetPrototypeLinkEnabled = true;

LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.fetchLayoutSet(siteGroup.getGroupId(), false);

if (publicLayoutSet != null) {
	publicLayoutSetPrototypeLinkEnabled = publicLayoutSet.isLayoutSetPrototypeLinkEnabled();

	String layoutSetPrototypeUuid = publicLayoutSet.getLayoutSetPrototypeUuid();

	if (Validator.isNotNull(layoutSetPrototypeUuid)) {
		publicLayoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.fetchLayoutSetPrototypeByUuidAndCompanyId(layoutSetPrototypeUuid, company.getCompanyId());
	}
}
%>

<aui:model-context bean="<%= liveGroup %>" model="<%= Group.class %>" />

<%
boolean disableLayoutSetPrototypeInput = false;

SiteAdminConfiguration siteAdminConfiguration = ConfigurationProviderUtil.getSystemConfiguration(SiteAdminConfiguration.class);

if (!LanguageUtil.isInheritLocales(siteGroup.getGroupId()) && !siteAdminConfiguration.enableCustomLanguagesWithTemplatePropagation()) {
	disableLayoutSetPrototypeInput = true;
}

boolean hasUnlinkLayoutSetPrototypePermission = PortalPermissionUtil.contains(permissionChecker, ActionKeys.UNLINK_LAYOUT_SET_PROTOTYPE);
%>

<div class="sheet-subtitle"><liferay-ui:message key="pages" /></div>

<aui:field-wrapper cssClass="form-group">
	<c:choose>
		<c:when test="<%= (publicLayoutSetPrototype == null) && (siteGroup.getPublicLayoutsPageCount() == 0) && !layoutSetPrototypes.isEmpty() %>">
			<c:if test="<%= disableLayoutSetPrototypeInput %>">
				<clay:alert
					displayType="info"
					message="you-cannot-apply-a-site-template-because-you-modified-the-display-settings-of-this-site"
				/>
			</c:if>

			<aui:select disabled="<%= disableLayoutSetPrototypeInput %>" helpMessage="site-templates-with-an-incompatible-application-adapter-are-disabled" label="site-template" name="publicLayoutSetPrototypeId">
				<aui:option label="none" selected="<%= true %>" value="" />

				<%
				for (LayoutSetPrototype curLayoutSetPrototype : layoutSetPrototypes) {
					UnicodeProperties settingsUnicodeProperties = curLayoutSetPrototype.getSettingsProperties();
				%>

					<aui:option data-servletContextName='<%= settingsUnicodeProperties.getProperty("customJspServletContextName", StringPool.BLANK) %>' value="<%= curLayoutSetPrototype.getLayoutSetPrototypeId() %>"><%= HtmlUtil.escape(curLayoutSetPrototype.getName(locale)) %></aui:option>

				<%
				}
				%>

			</aui:select>

			<c:if test="<%= !siteGroup.isStaged() %>">
				<c:choose>
					<c:when test="<%= hasUnlinkLayoutSetPrototypePermission %>">
						<div class="hide" id="<portlet:namespace />publicLayoutSetPrototypeIdOptions">
							<c:if test="<%= disableLayoutSetPrototypeInput %>">
								<clay:alert
									displayType="info"
									message="you-cannot-enable-the-propagation-of-changes-because-you-modified-the-display-settings-of-this-site"
								/>
							</c:if>

							<aui:input disabled="<%= disableLayoutSetPrototypeInput %>" helpMessage="enable-propagation-of-changes-from-the-site-template-help" inlineLabel="right" label="enable-propagation-of-changes-from-the-site-template" labelCssClass="simple-toggle-switch" name="publicLayoutSetPrototypeLinkEnabled" type="toggle-switch" value="<%= publicLayoutSetPrototypeLinkEnabled %>" />
						</div>
					</c:when>
					<c:otherwise>
						<aui:input name="publicLayoutSetPrototypeLinkEnabled" type="hidden" value="<%= true %>" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="<%= siteGroup.getPublicLayoutsPageCount() > 0 %>">
					<aui:a href="<%= siteGroup.getDisplayURL(themeDisplay, false) %>" label="open-pages" target="_blank" />
				</c:when>
				<c:otherwise>
					<p class="small text-secondary">
						<liferay-ui:message key="this-site-does-not-have-any-pages" />
					</p>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="<%= (publicLayoutSetPrototype != null) && !siteGroup.isStaged() && hasUnlinkLayoutSetPrototypePermission %>">
					<c:if test="<%= disableLayoutSetPrototypeInput %>">
						<clay:alert
							displayType="info"
							message="you-cannot-enable-the-propagation-of-changes-because-you-modified-the-display-settings-of-this-site"
						/>
					</c:if>

					<aui:input disabled="<%= disableLayoutSetPrototypeInput %>" inlineLabel="right" label='<%= LanguageUtil.format(request, "enable-propagation-of-changes-from-the-site-template-x", HtmlUtil.escape(publicLayoutSetPrototype.getName(locale)), false) %>' labelCssClass="simple-toggle-switch" name="publicLayoutSetPrototypeLinkEnabled" type="toggle-switch" value="<%= publicLayoutSetPrototypeLinkEnabled %>" />

					<div class="<%= publicLayoutSetPrototypeLinkEnabled ? "" : "hide" %>" id="<portlet:namespace />publicLayoutSetPrototypeMergeAlert">

						<%
						request.setAttribute("edit_layout_set_prototype.jsp-groupId", String.valueOf(siteGroup.getGroupId()));
						request.setAttribute("edit_layout_set_prototype.jsp-layoutSet", publicLayoutSet);
						request.setAttribute("edit_layout_set_prototype.jsp-layoutSetPrototype", publicLayoutSetPrototype);
						request.setAttribute("edit_layout_set_prototype.jsp-redirect", currentURL);
						%>

						<liferay-util:include page="/layout_set_merge_alert.jsp" servletContext="<%= application %>" />
					</div>
				</c:when>
				<c:when test="<%= publicLayoutSetPrototype != null %>">
					<liferay-ui:message arguments="<%= HtmlUtil.escape(publicLayoutSetPrototype.getName(locale)) %>" key="these-pages-are-linked-to-site-template-x" translateArguments="<%= false %>" />

					<aui:input name="publicLayoutSetPrototypeLinkEnabled" type="hidden" value="<%= publicLayoutSetPrototypeLinkEnabled %>" />
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>
</aui:field-wrapper>

<aui:script>
	function <portlet:namespace />isVisible(currentValue, value) {
		return currentValue != '';
	}

	Liferay.Util.toggleSelectBox(
		'<portlet:namespace />publicLayoutSetPrototypeId',
		<portlet:namespace />isVisible,
		'<portlet:namespace />publicLayoutSetPrototypeIdOptions'
	);

	Liferay.Util.toggleBoxes(
		'<portlet:namespace />publicLayoutSetPrototypeLinkEnabled',
		'<portlet:namespace />publicLayoutSetPrototypeMergeAlert'
	);
</aui:script>