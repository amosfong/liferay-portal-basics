<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalEditDDMTemplateDisplayContext journalEditDDMTemplateDisplayContext = (JournalEditDDMTemplateDisplayContext)request.getAttribute(JournalEditDDMTemplateDisplayContext.class.getName());

DDMTemplate ddmTemplate = journalEditDDMTemplateDisplayContext.getDDMTemplate();

DDMStructure ddmStructure = journalEditDDMTemplateDisplayContext.getDDMStructure();
%>

<aui:model-context bean="<%= ddmTemplate %>" model="<%= DDMTemplate.class %>" />

<div class="form-group">
	<label class="control-label" for="<portlet:namespace />ddmStructure">
		<liferay-ui:message key="structure" />
	</label>

	<span class="icon-help lfr-portal-tooltip" tabindex="0" title="<%= LanguageUtil.get(request, "structure-help") %>">
		<clay:icon
			symbol="question-circle-full"
		/>
	</span>

	<div class="input-group">
		<div class="input-group-item">
			<input placeholder="<%= LanguageUtil.format(locale, "no-x-selected", "structure") %>" id="<%= liferayPortletResponse.getNamespace() %>ddmStructure" name="structure" readonly value="<%= (ddmStructure != null) ? HtmlUtil.escape(ddmStructure.getName(locale)) : StringPool.BLANK %>" class="form-control lfr-input-resource" />
		</div>

		<c:if test="<%= (ddmTemplate == null) || (ddmTemplate.getClassPK() == 0) %>">
			<div class="input-group-item input-group-item-shrink">
				<clay:button
					displayType="secondary"
					icon="plus"
					id='<%= liferayPortletResponse.getNamespace() + "selectDDMStructure" %>'
					small="<%= true %>"
					title='<%= LanguageUtil.format(locale, "select-x", "structure") %>'
				/>
			</div>
		</c:if>
	</div>
</div>

<c:if test="<%= !journalEditDDMTemplateDisplayContext.autogenerateDDMTemplateKey() && (ddmTemplate == null) %>">
	<aui:input helpMessage="template-key-help" name="templateKey" type="text" />
</c:if>

<aui:input defaultLanguageId="<%= (ddmTemplate == null) ? LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()): ddmTemplate.getDefaultLanguageId() %>" name="description" />

<c:if test="<%= ddmTemplate != null %>">
	<aui:input helpMessage="template-key-help" name="ddmTemplateKey" type="resource" value="<%= ddmTemplate.getTemplateKey() %>" />

	<portlet:resourceURL id="/journal/get_ddm_template" var="getDDMTemplateURL">
		<portlet:param name="ddmTemplateId" value="<%= String.valueOf(ddmTemplate.getTemplateId()) %>" />
	</portlet:resourceURL>

	<aui:input name="url" type="resource" value="<%= getDDMTemplateURL %>" />

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(portletDisplay.getId());
	%>

	<aui:input name="webDavURL" type="resource" value="<%= ddmTemplate.getWebDavURL(themeDisplay, WebDAVUtil.getStorageToken(portlet)) %>" />
</c:if>

<div class="form-group form-inline">
	<clay:checkbox
		checked="<%= journalEditDDMTemplateDisplayContext.isCacheable() %>"
		id='<%= liferayPortletResponse.getNamespace() + "cacheable" %>'
		label="cacheable"
		name='<%= liferayPortletResponse.getNamespace() + "cacheable" %>'
	/>

	<span class="c-ml-1 icon-help lfr-portal-tooltip" tabindex="0" title="<%= LanguageUtil.get(request, "journal-template-cacheable-help") %>">
		<clay:icon
			symbol="question-circle-full"
		/>
	</span>
</div>

<aui:script>
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />cacheable',
		'<portlet:namespace />-cacheableWarningMessage'
	);
</aui:script>

<c:if test="<%= (ddmTemplate == null) || (ddmTemplate.getClassPK() == 0) %>">
	<aui:script sandbox="<%= true %>">
		const selectDDMStructure = document.getElementById(
			'<portlet:namespace />selectDDMStructure'
		);

		const onClick = (event) => {
			Liferay.Util.openSelectionModal({
				onSelect: function (selectedItem) {
					const itemValue = JSON.parse(selectedItem.value);

					if (
						document.<portlet:namespace />fm.<portlet:namespace />classPK
							.value != itemValue.ddmstructureid
					) {
						document.<portlet:namespace />fm.<portlet:namespace />classPK.value =
							itemValue.ddmstructureid;

						Liferay.fire('<portlet:namespace />refreshEditor');
					}
				},
				selectEventName: '<portlet:namespace />selectDDMStructure',
				title: Liferay.Util.sub(
					'<%= UnicodeLanguageUtil.get(request, "select-x") %>',
					'<%= UnicodeLanguageUtil.get(request, "structure") %>'
				),
				url: '<%= journalDisplayContext.getSelectDDMStructureURL() %>>',
			});
		};

		if (selectDDMStructure) {
			selectDDMStructure.addEventListener('click', onClick);
		}
	</aui:script>
</c:if>