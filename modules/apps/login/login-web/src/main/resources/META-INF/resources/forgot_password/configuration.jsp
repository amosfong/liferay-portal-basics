<%--
/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String emailFromName = ParamUtil.getString(request, "preferences--emailFromName--", LoginUtil.getEmailFromName(portletPreferences, company.getCompanyId()));
String emailFromAddress = ParamUtil.getString(request, "preferences--emailFromAddress--", LoginUtil.getEmailFromAddress(portletPreferences, company.getCompanyId()));

String emailPasswordResetSubject = LoginUtil.getEmailTemplateXML(portletPreferences, renderRequest, company.getCompanyId(), "emailPasswordResetSubject", "adminEmailPasswordResetSubject", PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_SUBJECT);
String emailPasswordResetBody = LoginUtil.getEmailTemplateXML(portletPreferences, renderRequest, company.getCompanyId(), "emailPasswordResetBody", "adminEmailPasswordResetBody", PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_BODY);
String emailPasswordSentSubject = LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "emailPasswordSentSubject", "preferences", StringPool.BLANK);
String emailPasswordSentBody = LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "emailPasswordSentBody", "preferences", StringPool.BLANK);

ForgotPasswordConfigurationDisplayContext forgotPasswordConfigurationDisplayContext = (ForgotPasswordConfigurationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

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
		<clay:tabs
			tabsItems="<%= forgotPasswordConfigurationDisplayContext.getTabsItems() %>"
		>
			<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
			<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

			<clay:tabs-panel>
				<liferay-frontend:fieldset>
					<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" value="<%= emailFromName %>" />

					<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" value="<%= emailFromAddress %>" />
				</liferay-frontend:fieldset>
			</clay:tabs-panel>

			<c:if test="<%= Validator.isNotNull(emailPasswordSentSubject) || Validator.isNotNull(emailPasswordSentBody) %>">
				<clay:tabs-panel>
					<liferay-frontend:fieldset
						collapsed="<%= true %>"
						collapsible="<%= true %>"
						label="legacy-template-no-longer-used"
						markupView="lexicon"
					>
						<aui:input checked="<%= false %>" label="discard" name="discardLegacyKey" type="checkbox" value="emailPasswordSentSubject,emailPasswordSentBody" />

						<div class="alert alert-info">
							<liferay-ui:message key="sending-of-passwords-by-email-is-no-longer-supported-the-template-below-is-not-used-and-can-be-discarded" />
						</div>

						<c:if test="<%= Validator.isNotNull(emailPasswordSentSubject) %>">
							<aui:field-wrapper label="subject">
								<liferay-ui:input-localized
									fieldPrefix="settings"
									fieldPrefixSeparator="--"
									name="emailPasswordSentSubject"
									readonly="<%= true %>"
									xml="<%= emailPasswordSentSubject %>"
								/>
							</aui:field-wrapper>
						</c:if>

						<c:if test="<%= Validator.isNotNull(emailPasswordSentBody) %>">
							<aui:field-wrapper label="body">
								<liferay-ui:input-localized
									fieldPrefix="settings"
									fieldPrefixSeparator="--"
									name="emailPasswordSentBody"
									readonly="<%= true %>"
									type="textarea"
									xml="<%= emailPasswordSentBody %>"
								/>
							</aui:field-wrapper>
						</c:if>
					</liferay-frontend:fieldset>
				</clay:tabs-panel>
			</c:if>

			<clay:tabs-panel>
				<div class="alert alert-info">
					<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
				</div>

				<liferay-frontend:fieldset>
					<liferay-frontend:email-notification-settings
						emailBody="<%= emailPasswordResetBody %>"
						emailDefinitionTerms="<%= LoginUtil.getEmailDefinitionTerms(renderRequest, emailFromAddress, emailFromName, true) %>"
						emailParam="emailPasswordReset"
						emailSubject="<%= emailPasswordResetSubject %>"
						showEmailEnabled="<%= false %>"
					/>
				</liferay-frontend:fieldset>
			</clay:tabs-panel>
		</clay:tabs>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>