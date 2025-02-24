<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
DLSizeLimitConfigurationDisplayContext dlSizeLimitConfigurationDisplayContext = (DLSizeLimitConfigurationDisplayContext)request.getAttribute(DLSizeLimitConfigurationDisplayContext.class.getName());
%>

<aui:form action="<%= dlSizeLimitConfigurationDisplayContext.getEditDLSizeLimitConfigurationURL() %>" method="post" name="fm">
	<clay:sheet>
		<clay:sheet-header
			cssClass="c-mb-4"
		>
			<liferay-ui:error exception="<%= ConfigurationModelListenerException.class %>" message="mime-type-size-limit-error" />

			<h2>
				<liferay-ui:message key="dl-size-limit-configuration-name" />
			</h2>
		</clay:sheet-header>

		<clay:alert
			dismissible="<%= true %>"
			displayType="info"
			message="changes-will-only-apply-to-new-documents-uploaded"
		/>

		<clay:sheet-section>
			<h3 class="c-mb-2 sheet-subtitle text-secondary"><liferay-ui:message key="upload-limit" /></h3>

			<p class="c-mb-4 text-3 text-secondary">
				<liferay-ui:message arguments="<%= dlSizeLimitConfigurationDisplayContext.getFileMaxSizeHelpArguments() %>" key="maximum-file-upload-size-help" />
			</p>

			<aui:input label="maximum-file-upload-size" name="fileMaxSize" type="number" value="<%= dlSizeLimitConfigurationDisplayContext.getFileMaxSize() %>" />
		</clay:sheet-section>

		<clay:sheet-section>
			<h3 class="c-mb-2 sheet-subtitle text-secondary"><liferay-ui:message key="mime-type-limit" /></h3>

			<div>
				<span aria-hidden="true" class="loading-animation"></span>

				<react:component
					module="{FileSizeMimetypes} from document-library-web"
					props="<%= dlSizeLimitConfigurationDisplayContext.getFileSizePerMimeTypeData() %>"
				/>
			</div>
		</clay:sheet-section>

		<clay:sheet-section>
			<h3 class="c-mb-2 sheet-subtitle text-secondary"><liferay-ui:message key="copy-limit-title" /></h3>

			<p class="c-mb-4 text-3 text-secondary">
				<liferay-ui:message key="copy-limit-help" />
			</p>

			<aui:input label="size-limit-copy-files" name="maxSizeToCopy" type="number" value="<%= dlSizeLimitConfigurationDisplayContext.getMaxSizeToCopy() %>" />
		</clay:sheet-section>

		<clay:sheet-footer>
			<aui:button primary="<%= true %>" type="submit" />
		</clay:sheet-footer>
	</clay:sheet>
</aui:form>