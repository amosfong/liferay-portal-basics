<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<ScopeDisplay> scopeDisplays = (List<ScopeDisplay>)request.getAttribute(UADWebKeys.SCOPE_DISPLAYS);
int totalReviewableUADEntitiesCount = (int)request.getAttribute(UADWebKeys.TOTAL_UAD_ENTITIES_COUNT);
List<UADApplicationSummaryDisplay> uadApplicationSummaryDisplays = (List<UADApplicationSummaryDisplay>)request.getAttribute(UADWebKeys.UAD_APPLICATION_SUMMARY_DISPLAY_LIST);
List<UADDisplay<?>> uadDisplays = (List<UADDisplay<?>>)request.getAttribute(UADWebKeys.APPLICATION_UAD_DISPLAYS);

ViewUADEntitiesDisplay viewUADEntitiesDisplay = (ViewUADEntitiesDisplay)request.getAttribute(UADWebKeys.VIEW_UAD_ENTITIES_DISPLAY);

long[] groupIds = viewUADEntitiesDisplay.getGroupIds();
String scope = viewUADEntitiesDisplay.getScope();

portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure")));
%>

<liferay-util:include page="/uad_data_navigation_bar.jsp" servletContext="<%= application %>" />

<clay:container-fluid
	cssClass="container-form-lg"
>
	<clay:row>
		<clay:col
			lg="3"
		>
			<div class="mb-3">
				<clay:panel-group>
					<clay:panel
						displayTitle="scope"
						displayType="secondary"
						expanded="<%= true %>"
					>
						<div class="collapse panel-collapse show" id="<portlet:namespace />scopePanelBody">
							<div class="panel-body">

								<%
								for (ScopeDisplay scopeDisplay : scopeDisplays) {
								%>

									<clay:radio
										checked="<%= scopeDisplay.isActive() %>"
										disabled="<%= !scopeDisplay.hasItems() %>"
										label="<%= LanguageUtil.get(request, scopeDisplay.getScopeName()) %>"
										name="scope"
										value="<%= scopeDisplay.getScopeName() %>"
									/>

								<%
								}
								%>

							</div>
						</div>
					</clay:panel>
				</clay:panel-group>
			</div>

			<%
			UADApplicationSummaryDisplay firstUADApplicationSummaryDisplay = uadApplicationSummaryDisplays.get(0);
			%>

			<div class="mb-3">
				<clay:panel-group>
					<clay:panel
						displayTitle='<%= StringUtil.appendParentheticalSuffix(LanguageUtil.get(request, "applications"), firstUADApplicationSummaryDisplay.getCount()) %>'
						displayType="secondary"
						expanded="<%= true %>"
					>
						<div class="collapse panel-collapse show" id="<portlet:namespace />applicationPanelBody">
							<div class="panel-body">

								<%
								for (UADApplicationSummaryDisplay uadApplicationSummaryDisplay : uadApplicationSummaryDisplays) {
									String applicationName = UADLanguageUtil.getApplicationName(uadApplicationSummaryDisplay.getApplicationKey(), locale);
								%>

									<clay:radio
										checked="<%= Objects.equals(uadApplicationSummaryDisplay.getApplicationKey(), viewUADEntitiesDisplay.getApplicationKey()) %>"
										disabled="<%= !uadApplicationSummaryDisplay.hasItems() %>"
										label="<%= StringUtil.appendParentheticalSuffix(applicationName, uadApplicationSummaryDisplay.getCount()) %>"
										name="applicationKey"
										value="<%= uadApplicationSummaryDisplay.getApplicationKey() %>"
									/>

								<%
								}
								%>

							</div>
						</div>
					</clay:panel>
				</clay:panel-group>
			</div>

			<c:if test="<%= !Objects.equals(viewUADEntitiesDisplay.getApplicationKey(), UADConstants.ALL_APPLICATIONS) %>">
				<clay:panel-group>
					<clay:panel
						displayTitle="<%= UADLanguageUtil.getApplicationName(viewUADEntitiesDisplay.getApplicationKey(), locale) %>"
						displayType="secondary"
						expanded="<%= true %>"
					>
						<div class="collapse panel-collapse show" id="<portlet:namespace />entitiesTypePanelBody">
							<div class="panel-body">
								<c:choose>
									<c:when test="<%= viewUADEntitiesDisplay.isHierarchy() %>">

										<%
										UADHierarchyDisplay uadHierarchyDisplay = (UADHierarchyDisplay)request.getAttribute(UADWebKeys.UAD_HIERARCHY_DISPLAY);
										%>

										<clay:radio
											checked="<%= true %>"
											label="<%= StringUtil.appendParentheticalSuffix(uadHierarchyDisplay.getEntitiesTypeLabel(locale), (int)uadHierarchyDisplay.searchCount(selectedUser.getUserId(), groupIds, null)) %>"
											name="uadRegistryKey"
											value="<%= viewUADEntitiesDisplay.getApplicationKey() %>"
										/>
									</c:when>
									<c:otherwise>

										<%
										for (UADDisplay<?> uadDisplay : uadDisplays) {
											long count = uadDisplay.searchCount(selectedUser.getUserId(), groupIds, null);
										%>

											<clay:radio
												checked="<%= Objects.equals(uadDisplay.getTypeName(locale), viewUADEntitiesDisplay.getTypeName()) %>"
												disabled="<%= count == 0 %>"
												label="<%= StringUtil.appendParentheticalSuffix(uadDisplay.getTypeName(locale), (int)count) %>"
												name="uadRegistryKey"
												value="<%= uadDisplay.getTypeKey() %>"
											/>

										<%
										}
										%>

									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</clay:panel>
				</clay:panel-group>
			</c:if>
		</clay:col>

		<clay:col
			lg="9"
		>
			<clay:sheet
				size="full"
			>
				<clay:sheet-header>
					<h2 class="sheet-title"><liferay-ui:message key="review-data" /></h2>
				</clay:sheet-header>

				<clay:sheet-section>
					<h3 class="sheet-subtitle">
						<liferay-ui:message key="status-summary" />
					</h3>

					<strong><liferay-ui:message key="remaining-items" />: </strong><%= totalReviewableUADEntitiesCount %>
				</clay:sheet-section>

				<clay:sheet-section>
					<c:choose>
						<c:when test="<%= totalReviewableUADEntitiesCount == 0 %>">
							<liferay-frontend:empty-result-message
								title='<%= LanguageUtil.get(resourceBundle, "all-data-that-requires-review-has-been-anonymized") %>'
							/>
						</c:when>
						<c:otherwise>
							<h3 class="sheet-subtitle"><liferay-ui:message key="view-data" /></h3>

							<liferay-util:include page="/view_uad_entities.jsp" servletContext="<%= application %>" />
						</c:otherwise>
					</c:choose>
				</clay:sheet-section>
			</clay:sheet>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<portlet:renderURL var="reviewUADDataURL">
	<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	<portlet:param name="mvcRenderCommandName" value="/user_associated_data/review_uad_data" />
	<portlet:param name="applicationKey" value="<%= viewUADEntitiesDisplay.getApplicationKey() %>" />
	<portlet:param name="scope" value="<%= scope %>" />
</portlet:renderURL>

<aui:script sandbox="<%= true %>">
	var baseURL = '<%= reviewUADDataURL %>';

	var clickListeners = [];

	var registerClickHandler = function (element, clickHandlerFn) {
		clickListeners.push(
			Liferay.Util.delegate(element, 'click', 'input', clickHandlerFn)
		);
	};

	registerClickHandler(<portlet:namespace />applicationPanelBody, (event) => {
		var url = new URL(baseURL, window.location.origin);

		url.searchParams.set(
			'<portlet:namespace />applicationKey',
			event.target.value
		);

		Liferay.Util.navigate(url.toString());
	});

	<c:if test="<%= !Objects.equals(viewUADEntitiesDisplay.getApplicationKey(), UADConstants.ALL_APPLICATIONS) %>">
		registerClickHandler(<portlet:namespace />entitiesTypePanelBody, (event) => {
			var url = new URL(baseURL, window.location.origin);

			url.searchParams.set(
				'<portlet:namespace />uadRegistryKey',
				event.target.value
			);

			Liferay.Util.navigate(url.toString());
		});
	</c:if>

	registerClickHandler(<portlet:namespace />scopePanelBody, (event) => {
		var url = new URL(baseURL, window.location.origin);

		url.searchParams.set('<portlet:namespace />applicationKey', '');
		url.searchParams.set('<portlet:namespace />scope', event.target.value);

		Liferay.Util.navigate(url.toString());
	});

	function handleDestroyPortlet() {
		for (var i = 0; i < clickListeners.length; i++) {
			clickListeners[i].dispose();
		}

		Liferay.detach('destroyPortlet', handleDestroyPortlet);
	}

	Liferay.on('destroyPortlet', handleDestroyPortlet);
</aui:script>