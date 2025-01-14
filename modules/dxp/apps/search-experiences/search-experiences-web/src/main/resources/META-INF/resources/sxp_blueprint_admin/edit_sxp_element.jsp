<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = PortletURLBuilder.createRenderURL(
		renderResponse
	).setMVCRenderCommandName(
		"/sxp_blueprint_admin/view_sxp_elements"
	).setTabs1(
		"sxpElements"
	).buildString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
portletDisplay.setURLBackTitle(portletDisplay.getPortletDisplayName());

renderResponse.setTitle(LanguageUtil.get(request, "view-element"));
%>

<div>
	<span aria-hidden="true" class="loading-animation"></span>

	<react:component
		module="{EditElement} from search-experiences-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"defaultLocale", LocaleUtil.toLanguageId(LocaleUtil.getDefault())
			).put(
				"learnMessages", LearnMessageUtil.getJSONObject("search-experiences-web")
			).put(
				"locale", themeDisplay.getLanguageId()
			).put(
				"namespace", liferayPortletResponse.getNamespace()
			).put(
				"redirectURL", redirect
			).put(
				"sxpElementId", ParamUtil.getLong(renderRequest, "sxpElementId")
			).build()
		%>'
	/>
</div>