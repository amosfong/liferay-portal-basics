<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPPublisherConfigurationDisplayContext cpPublisherConfigurationDisplayContext = (CPPublisherConfigurationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<aui:fieldset markupView="lexicon">
	<aui:input checked="<%= cpPublisherConfigurationDisplayContext.isRenderSelectionADT() %>" id="renderSelectionADT" label="use-adt" name="preferences--renderSelection--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseRenderSelection();" %>' type="radio" value="adt" />

	<aui:input checked="<%= cpPublisherConfigurationDisplayContext.isRenderSelectionCustomRenderer() %>" id="renderSelectionCustomRenderer" label="use-custom-renderer" name="preferences--renderSelection--" onChange='<%= liferayPortletResponse.getNamespace() + "chooseRenderSelection();" %>' type="radio" value="custom" />
</aui:fieldset>

<aui:script>
	function <portlet:namespace />chooseRenderSelection() {
		var form = window.document['<portlet:namespace />fm'];

		form['<portlet:namespace /><%= Constants.CMD %>'].value =
			'render-selection';

		form.submit();
	}
</aui:script>