<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-frontend:fieldset>
	<liferay-asset:asset-tags-error />

	<%
	DuplicateQueryRuleException dqre = null;
	%>

	<liferay-ui:error exception="<%= DuplicateQueryRuleException.class %>">

		<%
		dqre = (DuplicateQueryRuleException)errorException;

		String name = "categories";

		if (Objects.equals(dqre.getName(), "assetTags")) {
			name = "tags";
		}
		else if (Objects.equals(dqre.getName(), "keywords")) {
			name = "keywords";
		}
		%>

		<liferay-util:buffer
			var="messageArgument"
		>
			<em>(<liferay-ui:message key='<%= dqre.isContains() ? "contains" : "does-not-contain" %>' /> - <liferay-ui:message key='<%= dqre.isAndOperator() ? "all" : "any" %>' /> - <liferay-ui:message key="<%= name %>" />)</em>
		</liferay-util:buffer>

		<liferay-ui:message arguments="<%= messageArgument %>" key="only-one-rule-with-the-combination-x-is-supported" translateArguments="<%= false %>" />
	</liferay-ui:error>

	<p><liferay-ui:message key="displayed-items-must-match-these-rules" /></p>

	<div id="<portlet:namespace />ConditionForm"></div>

	<div>
		<react:component
			module="{AssetFilterBuilder} from asset-publisher-web"
			props='<%=
				HashMapBuilder.<String, Object>put(
					"categorySelectorURL", assetPublisherDisplayContext.getCategorySelectorURL()
				).put(
					"groupIds", ListUtil.fromArray(assetPublisherDisplayContext.getReferencedModelsGroupIds())
				).put(
					"namespace", liferayPortletResponse.getNamespace()
				).put(
					"pathThemeImages", themeDisplay.getPathThemeImages()
				).put(
					"rules", assetPublisherDisplayContext.getAutoFieldRulesJSONArray()
				).put(
					"tagSelectorURL", assetPublisherDisplayContext.getTagSelectorURL()
				).put(
					"vocabularyIds", assetPublisherDisplayContext.getVocabularyIds()
				).build()
			%>'
		/>
	</div>
</liferay-frontend:fieldset>