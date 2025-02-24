<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
EditClientExtensionEntryDisplayContext<GlobalJSCET> editClientExtensionEntryDisplayContext = (EditClientExtensionEntryDisplayContext)renderRequest.getAttribute(ClientExtensionAdminWebKeys.EDIT_CLIENT_EXTENSION_ENTRY_DISPLAY_CONTEXT);

GlobalJSCET globalJSCET = editClientExtensionEntryDisplayContext.getCET();
%>

<aui:field-wrapper cssClass="form-group">
	<aui:input ignoreRequestValue="<%= true %>" label="js-url" name="url" required="<%= true %>" type="text" value="<%= globalJSCET.getURL() %>" />

	<div class="form-text">
		<liferay-ui:message key="this-javascript-is-appended-to-main-js" />
	</div>
</aui:field-wrapper>

<aui:field-wrapper cssClass="form-group">
	<react:component
		module="{ScriptElementAttributesFormField} from client-extension-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"scriptElementAttributesJSON", globalJSCET.getScriptElementAttributesJSON()
			).build()
		%>'
	/>
</aui:field-wrapper>