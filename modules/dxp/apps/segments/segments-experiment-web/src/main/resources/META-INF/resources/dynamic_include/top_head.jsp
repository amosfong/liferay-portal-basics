<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SegmentsExperiment segmentsExperiment = (SegmentsExperiment)request.getAttribute(SegmentsExperimentWebKeys.SEGMENTS_EXPERIMENT);
%>

<aui:script sandbox="<%= true %>">
	<c:if test='<%= (segmentsExperiment != null) && Objects.equals(segmentsExperiment.getGoal(), "click") && Validator.isNotNull(segmentsExperiment.getGoalTarget()) %>'>
		var elements = [];

		var targetableCollectionElements = document.querySelectorAll(
			'[id^=analytics-targetable-collection]'
		);

		if (targetableCollectionElements.length) {
			targetableCollectionElements.forEach((element, index) => {
				if ('#' + element.id === '<%= segmentsExperiment.getGoalTarget() %>') {
					elements.push(element);
				}
			});
		}
		else {
			elements.push(
				document.querySelector('<%= segmentsExperiment.getGoalTarget() %>')
			);
		}

		if (elements.length) {
			elements.forEach((element) => {
				element.addEventListener('click', () => {
					if (window.Analytics) {
						Analytics.send('ctaClicked', 'Page', {elementId: element.id});
					}
				});
			});
		}
	</c:if>
</aui:script>