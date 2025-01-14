/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.uad.exporter;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.uad.constants.CalendarUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the calendar booking UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link CalendarBookingUADExporter}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @generated
 */
public abstract class BaseCalendarBookingUADExporter
	extends DynamicQueryUADExporter<CalendarBooking> {

	@Override
	public Class<CalendarBooking> getTypeClass() {
		return CalendarBooking.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return calendarBookingLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return CalendarUADConstants.USER_ID_FIELD_NAMES_CALENDAR_BOOKING;
	}

	@Override
	protected String toXmlString(CalendarBooking calendarBooking) {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarBooking");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(calendarBooking.getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(calendarBooking.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected CalendarBookingLocalService calendarBookingLocalService;

}