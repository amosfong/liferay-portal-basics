/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.internal.security.permission.resource;

import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.constants.CalendarConstants;
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.BaseModelResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	property = "model.class.name=com.liferay.calendar.model.Calendar",
	service = ModelResourcePermission.class
)
public class CalendarModelResourcePermissionWrapper
	extends BaseModelResourcePermissionWrapper<Calendar> {

	@Override
	protected ModelResourcePermission<Calendar> doGetModelResourcePermission() {
		return ModelResourcePermissionFactory.create(
			Calendar.class, Calendar::getCalendarId,
			_calendarLocalService::getCalendar, _portletResourcePermission,
			(modelResourcePermission, consumer) -> consumer.accept(
				new StagingModelResourcePermissionLogic(_stagingPermission)));
	}

	@Reference
	private CalendarLocalService _calendarLocalService;

	@Reference(
		target = "(resource.name=" + CalendarConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private StagingPermission _stagingPermission;

	private static class StagingModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<Calendar> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				Calendar calendar, String actionId)
			throws PortalException {

			if (CalendarActionKeys.VIEW_BOOKING_DETAILS.equals(actionId)) {
				return null;
			}

			return _stagingPermission.hasPermission(
				permissionChecker, calendar.getGroupId(),
				Calendar.class.getName(), calendar.getCalendarId(),
				CalendarPortletKeys.CALENDAR_ADMIN, actionId);
		}

		private StagingModelResourcePermissionLogic(
			StagingPermission stagingPermission) {

			_stagingPermission = stagingPermission;
		}

		private final StagingPermission _stagingPermission;

	}

}