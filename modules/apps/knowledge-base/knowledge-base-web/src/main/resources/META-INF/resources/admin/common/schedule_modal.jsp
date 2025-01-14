<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/common/init.jsp" %>

<div>
	<react:component
		module="{ScheduleModal} from knowledge-base-web"
		props='<%=
			HashMapBuilder.<String, Object>put(
				"displayDate", ParamUtil.getString(request, "displayDate")
			).put(
				"isScheduled", ParamUtil.getBoolean(request, "scheduled")
			).put(
				"timeZone", timeZone.getID()
			).build()
		%>'
	/>
</div>