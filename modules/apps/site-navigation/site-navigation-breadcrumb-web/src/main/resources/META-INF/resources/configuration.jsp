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
		<clay:row>
			<clay:col
				md="6"
			>
				<liferay-frontend:fieldset>
					<div class="display-template">
						<liferay-template:template-selector
							className="<%= BreadcrumbEntry.class.getName() %>"
							displayStyle="<%= siteNavigationBreadcrumbDisplayContext.getDisplayStyle() %>"
							displayStyleGroupKey="<%= siteNavigationBreadcrumbDisplayContext.getDisplayStyleGroupKey() %>"
							refreshURL="<%= configurationRenderURL %>"
						/>
					</div>
				</liferay-frontend:fieldset>

				<liferay-frontend:fieldset
					id='<%= liferayPortletResponse.getNamespace() + "checkBoxes" %>'
				>
					<clay:col
						md="6"
					>
						<aui:input data-key='<%= "_" + HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) + "_showCurrentGroup" %>' label="show-current-site" name="preferences--showCurrentGroup--" type="toggle-switch" value="<%= siteNavigationBreadcrumbDisplayContext.isShowCurrentGroup() %>" />
						<aui:input data-key='<%= "_" + HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) + "_showGuestGroup" %>' label="show-guest-site" name="preferences--showGuestGroup--" type="toggle-switch" value="<%= siteNavigationBreadcrumbDisplayContext.isShowGuestGroup() %>" />
					</clay:col>

					<clay:col
						md="6"
					>
						<aui:input data-key='<%= "_" + HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) + "_showLayout" %>' label="show-page" name="preferences--showLayout--" type="toggle-switch" value="<%= siteNavigationBreadcrumbDisplayContext.isShowLayout() %>" />
						<aui:input data-key='<%= "_" + HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) + "_showParentGroups" %>' label="show-parent-sites" name="preferences--showParentGroups--" type="toggle-switch" value="<%= siteNavigationBreadcrumbDisplayContext.isShowParentGroups() %>" />
						<aui:input data-key='<%= "_" + HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) + "_showPortletBreadcrumb" %>' label="show-application-breadcrumb" name="preferences--showPortletBreadcrumb--" type="toggle-switch" value="<%= siteNavigationBreadcrumbDisplayContext.isShowPortletBreadcrumb() %>" />
					</clay:col>
				</liferay-frontend:fieldset>
			</clay:col>

			<clay:col
				md="6"
			>
				<liferay-portlet:preview
					portletName="<%= siteNavigationBreadcrumbDisplayContext.getPortletResource() %>"
					showBorders="<%= true %>"
				/>
			</clay:col>
		</clay:row>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script sandbox="<%= true %>">
	var data = {};

	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_displayStyle'
	] = '<%= siteNavigationBreadcrumbDisplayContext.getDisplayStyle() %>';
	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_showCurrentGroup'
	] = <%= siteNavigationBreadcrumbDisplayContext.isShowCurrentGroup() %>;
	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_showGuestGroup'
	] = <%= siteNavigationBreadcrumbDisplayContext.isShowGuestGroup() %>;
	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_showLayout'
	] = <%= siteNavigationBreadcrumbDisplayContext.isShowLayout() %>;
	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_showParentGroups'
	] = <%= siteNavigationBreadcrumbDisplayContext.isShowParentGroups() %>;
	data[
		'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_showPortletBreadcrumb'
	] = <%= siteNavigationBreadcrumbDisplayContext.isShowPortletBreadcrumb() %>;

	Liferay.on('templateSelector:changedTemplate', (event) => {
		const displayStyle = event.value;

		if (displayStyle) {
			data[
				'_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_displayStyle'
			] = displayStyle;

			Liferay.Portlet.refresh(
				'#p_p_id_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_',
				data
			);
		}
	});

	var checkBoxes = document.getElementById('<portlet:namespace />checkBoxes');

	if (checkBoxes) {
		checkBoxes.addEventListener('change', (event) => {
			if (event.target.classList.contains('toggle-switch-check')) {
				var target = event.target;

				data[target.dataset.key] = target.checked;

				Liferay.Portlet.refresh(
					'#p_p_id_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_',
					data
				);
			}
		});
	}

	var handler = Liferay.on('portletReady', (event) => {
		Liferay.Portlet.refresh(
			'#p_p_id_<%= HtmlUtil.escapeJS(siteNavigationBreadcrumbDisplayContext.getPortletResource()) %>_',
			data
		);

		handler.detach();

		handler = null;
	});

	var destroyHandler = Liferay.on('destroyHandler', (event) => {
		if (handler) {
			handler.detach();

			handler = null;
		}
	});
</aui:script>