<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

DispatchTrigger dispatchTrigger = (DispatchTrigger)request.getAttribute(DispatchWebKeys.DISPATCH_TRIGGER);
%>

<liferay-portlet:actionURL name="/dispatch_talend/edit_dispatch_talend_job_archive" portletName="<%= DispatchPortletKeys.DISPATCH %>" var="editDispatchTalendJobArchiveActionURL" />

<div class="closed container-fluid container-fluid-max-xl container-form-lg" id="<portlet:namespace />editDispatchTriggerId">
	<div class="sheet">
		<aui:form action="<%= editDispatchTalendJobArchiveActionURL %>" enctype="multipart/form-data" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="dispatchTriggerId" type="hidden" value="<%= String.valueOf(dispatchTrigger.getDispatchTriggerId()) %>" />

			<aui:model-context bean="<%= dispatchTrigger %>" model="<%= DispatchTrigger.class %>" />

			<liferay-ui:error exception="<%= TalendArchiveException.class %>">
				<liferay-ui:message key="the-file-must-be-a-valid-talend-job-archive" />
			</liferay-ui:error>

			<%
			TalendDispatchDisplayContext talendDispatchDisplayContext = (TalendDispatchDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

			String talendArchiveFileName = talendDispatchDisplayContext.getTalendArchiveFileName();
			%>

			<p class="<%= Objects.equals(talendArchiveFileName, StringPool.BLANK) ? "hide" : StringPool.BLANK %> text-default" id="<portlet:namespace />talendArchiveFileName">
				<span id="<portlet:namespace />removeIconId">
					<liferay-ui:icon
						icon="times"
						markupView="lexicon"
						message="remove"
					/>
				</span>
				<span>
					<%= talendArchiveFileName %>
				</span>
			</p>

			<c:if test="<%= (dispatchTrigger == null) || !dispatchTrigger.isSystem() %>">
				<div class="<%= Objects.equals(talendArchiveFileName, StringPool.BLANK) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />talendArchiveFile">
					<aui:input label="upload-your-talend-job-file" name="jobArchive" required="<%= true %>" type="file" />
				</div>
			</c:if>

			<div class="sheet-footer">
				<aui:button type="submit" value="save" />

				<aui:button href="<%= backURL %>" type="cancel" value="back" />
			<div>
		</aui:form>
	</div>
</div>

<aui:script>
	document
		.getElementById('<portlet:namespace />removeIconId')
		.addEventListener('click', (event) => {
			event.preventDefault();

			document
				.getElementById('<portlet:namespace />talendArchiveFile')
				.classList.remove('hide');

			document
				.getElementById('<portlet:namespace />talendArchiveFileName')
				.classList.add('hide');
		});
</aui:script>