<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<AssetRendererFactory<?>> classTypesAssetRendererFactories = (List<AssetRendererFactory<?>>)request.getAttribute("configuration.jsp-classTypesAssetRendererFactories");
List<Map<String, Object>> classTypesList = new ArrayList<>();
%>

<liferay-frontend:fieldset
	cssClass="source-container"
>

	<%
	Set<Long> availableClassNameIdsSet = SetUtil.fromArray(assetPublisherDisplayContext.getAvailableClassNameIds());

	// Left list

	List<KeyValuePair> typesLeftList = new ArrayList<KeyValuePair>();

	long[] classNameIds = assetPublisherDisplayContext.getClassNameIds();

	for (long classNameId : classNameIds) {
		typesLeftList.add(new KeyValuePair(String.valueOf(classNameId), ResourceActionsUtil.getModelResource(locale, PortalUtil.getClassName(classNameId))));
	}

	// Right list

	List<KeyValuePair> typesRightList = new ArrayList<KeyValuePair>();

	Arrays.sort(classNameIds);
	%>

	<aui:select label="asset-type" name="preferences--anyAssetType--" title="asset-type">
		<aui:option label="any" selected="<%= assetPublisherDisplayContext.isAnyAssetType() %>" value="<%= true %>" />
		<aui:option label='<%= LanguageUtil.get(request, "select-more-than-one") %>' selected="<%= !assetPublisherDisplayContext.isAnyAssetType() && (classNameIds.length > 1) %>" value="false" />

		<optgroup label="<liferay-ui:message key="asset-type" />">

			<%
			for (long classNameId : availableClassNameIdsSet) {
				ClassName className = ClassNameLocalServiceUtil.getClassName(classNameId);

				if (Arrays.binarySearch(classNameIds, classNameId) < 0) {
					typesRightList.add(new KeyValuePair(String.valueOf(classNameId), ResourceActionsUtil.getModelResource(locale, className.getValue())));
				}
			%>

				<aui:option label="<%= ResourceActionsUtil.getModelResource(locale, className.getValue()) %>" selected="<%= (classNameIds.length == 1) && (classNameId == classNameIds[0]) %>" value="<%= classNameId %>" />

			<%
			}
			%>

		</optgroup>
	</aui:select>

	<aui:input name="preferences--classNameIds--" type="hidden" />

	<%
	typesRightList = ListUtil.sort(typesRightList, new KeyValuePairComparator(false, true));
	%>

	<div class="<%= assetPublisherDisplayContext.isAnyAssetType() ? "hide" : "" %>" id="<portlet:namespace />classNamesBoxes">
		<liferay-ui:input-move-boxes
			leftBoxName="currentClassNameIds"
			leftList="<%= typesLeftList %>"
			leftReorder="<%= Boolean.TRUE.toString() %>"
			leftTitle="selected"
			rightBoxName="availableClassNameIds"
			rightList="<%= typesRightList %>"
			rightTitle="available"
		/>
	</div>

	<%
	List<AssetRendererFactory<?>> assetRendererFactories = ListUtil.sort(AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId()), new AssetRendererFactoryTypeNameComparator(locale));

	for (AssetRendererFactory<?> assetRendererFactory : assetRendererFactories) {
		ClassTypeReader classTypeReader = assetRendererFactory.getClassTypeReader();

		List<ClassType> classTypes = assetPublisherDisplayContext.getClassTypes(classTypeReader);

		if (classTypes.isEmpty()) {
			continue;
		}

		classTypesAssetRendererFactories.add(assetRendererFactory);

		String className = assetPublisherWebHelper.getClassName(assetRendererFactory);

		Long[] assetSelectedClassTypeIds = assetPublisherWebHelper.getClassTypeIds(portletPreferences, className, classTypes);

		// Left list

		List<KeyValuePair> subtypesLeftList = new ArrayList<KeyValuePair>();

		for (long subtypeId : assetSelectedClassTypeIds) {
			try {
				ClassType classType = classTypeReader.getClassType(subtypeId, locale);

				subtypesLeftList.add(new KeyValuePair(String.valueOf(subtypeId), HtmlUtil.escape(classType.getName())));
			}
			catch (NoSuchModelException nsme) {
			}
		}

		// Right list

		List<KeyValuePair> subtypesRightList = new ArrayList<KeyValuePair>();

		boolean anyAssetSubtype = GetterUtil.getBoolean(portletPreferences.getValue("anyClassType" + className, Boolean.TRUE.toString()));
	%>

		<div class='asset-subtype <%= (assetSelectedClassTypeIds.length < 1) ? StringPool.BLANK : "hide" %>' id="<portlet:namespace /><%= className %>Options">
			<aui:select label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) + StringPool.SPACE + assetRendererFactory.getSubtypeTitle(themeDisplay.getLocale()) %>" name='<%= "preferences--anyClassType" + className + "--" %>'>
				<aui:option label="any" selected="<%= anyAssetSubtype %>" value="<%= true %>" />
				<aui:option label='<%= LanguageUtil.get(request, "select-more-than-one") %>' selected="<%= !anyAssetSubtype && (assetSelectedClassTypeIds.length > 1) %>" value="false" />

				<optgroup label="<%= assetRendererFactory.getSubtypeTitle(themeDisplay.getLocale()) %>">

					<%
					for (ClassType classType : classTypes) {
						if (Arrays.binarySearch(assetSelectedClassTypeIds, classType.getClassTypeId()) < 0) {
							subtypesRightList.add(new KeyValuePair(String.valueOf(classType.getClassTypeId()), HtmlUtil.escape(classType.getName())));
						}
					%>

						<aui:option label="<%= HtmlUtil.escapeAttribute(classType.getName()) %>" selected="<%= !anyAssetSubtype && (assetSelectedClassTypeIds.length == 1) && (assetSelectedClassTypeIds[0]).equals(classType.getClassTypeId()) %>" value="<%= classType.getClassTypeId() %>" />

					<%
					}
					%>

				</optgroup>
			</aui:select>

			<aui:input name='<%= "preferences--classTypeIds" + className + "--" %>' type="hidden" />

			<c:if test="<%= assetPublisherDisplayContext.isShowSubtypeFieldsFilter() %>">
				<div class="asset-subtypefields-wrapper-enable hide" id="<portlet:namespace /><%= className %>subtypeFieldsFilterEnableWrapper">
					<aui:input inlineLabel="right" label="filter-by-field" labelCssClass="simple-toggle-switch" name='<%= "preferences--subtypeFieldsFilterEnabled" + className + "--" %>' type="toggle-switch" value="<%= assetPublisherDisplayContext.isSubtypeFieldsFilterEnabled() %>" />
				</div>

				<span class="asset-subtypefields-message" id="<portlet:namespace /><%= className %>ddmStructureFieldMessage">
					<c:if test="<%= Validator.isNotNull(assetPublisherDisplayContext.getDDMStructureFieldLabel()) && (classNameIds[0] == PortalUtil.getClassNameId(assetRendererFactory.getClassName())) %>">
						<%= HtmlUtil.escape(assetPublisherDisplayContext.getDDMStructureFieldLabel()) %>: <%= HtmlUtil.escape(assetPublisherDisplayContext.getDDMStructureDisplayFieldValue()) %>
					</c:if>
				</span>

				<div class="asset-subtypefields-wrapper hide" id="<portlet:namespace /><%= className %>subtypeFieldsWrapper">

					<%
					for (ClassType classType : classTypes) {
						if (classType.getClassTypeFieldsCount() == 0) {
							continue;
						}
					%>

						<span class="asset-subtypefields hide" id="<portlet:namespace /><%= classType.getClassTypeId() %>_<%= className %>Options">
							<liferay-portlet:renderURL portletName="<%= assetPublisherDisplayContext.getPortletResource() %>" var="selectStructureFieldURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
								<portlet:param name="mvcRenderCommandName" value="/asset_publisher/select_structure_field" />
								<portlet:param name="portletResource" value="<%= HtmlUtil.escapeJS(assetPublisherDisplayContext.getPortletResource()) %>" />
								<portlet:param name="className" value="<%= assetRendererFactory.getClassName() %>" />
								<portlet:param name="classTypeId" value="<%= String.valueOf(classType.getClassTypeId()) %>" />
								<portlet:param name="eventName" value='<%= liferayPortletResponse.getNamespace() + "selectDDMStructureField" %>' />
							</liferay-portlet:renderURL>

							<span class="asset-subtypefields-popup" id="<portlet:namespace /><%= classType.getClassTypeId() %>_<%= className %>PopUpButton">
								<clay:button
									data-href="<%= selectStructureFieldURL.toString() %>"
									disabled="<%= !assetPublisherDisplayContext.isSubtypeFieldsFilterEnabled() %>"
									displayType="secondary"
									label="select"
								/>
							</span>
						</span>

					<%
					}

					typesRightList = ListUtil.sort(typesRightList, new KeyValuePairComparator(false, true));
					%>

				</div>
			</c:if>

			<div class="<%= (assetSelectedClassTypeIds.length > 1) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace /><%= className %>Boxes">
				<liferay-ui:input-move-boxes
					leftBoxName='<%= className + "currentClassTypeIds" %>'
					leftList="<%= subtypesLeftList %>"
					leftReorder="<%= Boolean.TRUE.toString() %>"
					leftTitle="selected"
					rightBoxName='<%= className + "availableClassTypeIds" %>'
					rightList="<%= subtypesRightList %>"
					rightTitle="available"
				/>
			</div>
		</div>

	<%
	}

	for (AssetRendererFactory<?> curAssetRendererFactory : classTypesAssetRendererFactories) {
		List<ClassType> assetAvailableClassTypes = assetPublisherDisplayContext.getClassTypes(curAssetRendererFactory.getClassTypeReader());

		if (assetAvailableClassTypes.isEmpty()) {
			continue;
		}

		List<Map<String, Object>> classSubtypes = new ArrayList<>();

		for (ClassType classType : assetAvailableClassTypes) {
			List<ClassTypeField> classTypeFields = classType.getClassTypeFields();

			if (classTypeFields.isEmpty()) {
				continue;
			}

			List<Map<String, Object>> classTypeFieldsList = new ArrayList<>();
			String orderByColumn1 = assetPublisherDisplayContext.getOrderByColumn1();
			String orderByColumn2 = assetPublisherDisplayContext.getOrderByColumn2();

			for (ClassTypeField classTypeField : classTypeFields) {
				String value = assetPublisherWebHelper.encodeName(classTypeField.getClassTypeId(), classTypeField.getFieldReference(), null);
				String selectedOrderByColumn1 = StringPool.BLANK;
				String selectedOrderByColumn2 = StringPool.BLANK;

				if (orderByColumn1.equals(value)) {
					selectedOrderByColumn1 = "selected";
				}

				if (orderByColumn2.equals(value)) {
					selectedOrderByColumn2 = "selected";
				}

				classTypeFieldsList.add(
					HashMapBuilder.<String, Object>put(
						"label", HtmlUtil.escape(classTypeField.getLabel())
					).put(
						"selectedOrderByColumn1", selectedOrderByColumn1
					).put(
						"selectedOrderByColumn2", selectedOrderByColumn2
					).put(
						"value", value
					).build());
			}

			classSubtypes.add(
				HashMapBuilder.<String, Object>put(
					"classTypeFields", classTypeFieldsList
				).put(
					"classTypeId", classType.getClassTypeId()
				).put(
					"name", HtmlUtil.escape(classType.getName())
				).build());
		}

		classTypesList.add(
			HashMapBuilder.<String, Object>put(
				"className", assetPublisherWebHelper.getClassName(curAssetRendererFactory)
			).put(
				"classNameId", curAssetRendererFactory.getClassNameId()
			).put(
				"classSubtypes", classSubtypes
			).build());
	}
	%>

	<c:if test="<%= assetPublisherDisplayContext.isShowSubtypeFieldsFilter() %>">
		<div class="asset-subtypefield-selected <%= Validator.isNull(assetPublisherDisplayContext.getDDMStructureFieldName()) ? "hide" : StringPool.BLANK %>">
			<aui:input name="preferences--ddmStructureFieldName--" type="hidden" value="<%= assetPublisherDisplayContext.getDDMStructureFieldName() %>" />

			<aui:input name="preferences--ddmStructureFieldValue--" type="hidden" value="<%= assetPublisherDisplayContext.getDDMStructureFieldValue() %>" />

			<aui:input name="preferences--ddmStructureDisplayFieldValue--" type="hidden" value="<%= assetPublisherDisplayContext.getDDMStructureDisplayFieldValue() %>" />
		</div>
	</c:if>

	<liferay-frontend:component
		componentId='<%= liferayPortletResponse.getNamespace() + "selectDDMStructureField" %>'
		context='<%=
			HashMapBuilder.<String, Object>put(
				"assetPublisherNamespace", HtmlUtil.escapeJS(assetPublisherDisplayContext.getPortletResource())
			).put(
				"classTypes", classTypesList
			).build()
		%>'
		module="{Source} from asset-publisher-web"
	/>
</liferay-frontend:fieldset>