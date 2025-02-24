<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ObjectDefinition objectDefinition = (ObjectDefinition)request.getAttribute(ObjectWebKeys.OBJECT_DEFINITION);

ObjectDefinitionsLayoutsDisplayContext objectDefinitionsLayoutsDisplayContext = (ObjectDefinitionsLayoutsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(
	ParamUtil.getString(
		request, "backURL",
		URLBuilder.create(
			String.valueOf(renderResponse.createRenderURL())
		).setParameter(
			"objectFolderName", objectDefinitionsLayoutsDisplayContext.getObjectFolderName()
		).build()));

renderResponse.setTitle(objectDefinition.getLabel(locale, true));
%>

<div>
	<react:component
		module="{Layouts} from object-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"apiURL", objectDefinitionsLayoutsDisplayContext.getAPIURL()
			).put(
				"backURL", portletDisplay.getURLBack()
			).put(
				"creationMenu", objectDefinitionsLayoutsDisplayContext.getCreationMenu()
			).put(
				"formName", "fm"
			).put(
				"id", ObjectDefinitionsFDSNames.OBJECT_LAYOUTS
			).put(
				"items", objectDefinitionsLayoutsDisplayContext.getFDSActionDropdownItems()
			).put(
				"objectDefinitionExternalReferenceCode", objectDefinition.getExternalReferenceCode()
			).put(
				"style", "fluid"
			).put(
				"url", objectDefinitionsLayoutsDisplayContext.getEditObjectLayoutsURL()
			).build()
		%>'
	/>
</div>

<div id="<portlet:namespace />AddObjectLayout">
	<react:component
		module="{ModalAddObjectLayout} from object-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"apiURL", objectDefinitionsLayoutsDisplayContext.getAPIURL()
			).build()
		%>'
	/>
</div>