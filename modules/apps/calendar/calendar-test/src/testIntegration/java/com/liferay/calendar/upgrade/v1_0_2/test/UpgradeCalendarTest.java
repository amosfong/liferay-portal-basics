/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.upgrade.v1_0_2.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.calendar.test.util.CalendarTestUtil;
import com.liferay.calendar.test.util.CalendarUpgradeTestUtil;
import com.liferay.calendar.test.util.UpgradeDatabaseTestHelper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class UpgradeCalendarTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_upgradeDatabaseTestHelper =
			CalendarUpgradeTestUtil.getUpgradeDatabaseTestHelper();
		_upgradeProcess = CalendarUpgradeTestUtil.getUpgradeStep(
			_upgradeStepRegistrator,
			"com.liferay.calendar.internal.upgrade.v1_0_2." +
				"CalendarUpgradeProcess");
		_user = UserTestUtil.addUser();
	}

	@After
	public void tearDown() throws Exception {
		_upgradeDatabaseTestHelper.close();
	}

	@Test
	public void testUpgradeCreatesCalendarTimeZoneId() throws Exception {
		_upgradeProcess.upgrade();

		assertHasColumn("timeZoneId");
	}

	@Test
	public void testUpgradeSetsSiteCalendarTimeZoneId() throws Exception {
		Calendar calendar = CalendarTestUtil.addCalendar(_group);

		_upgradeProcess.upgrade();

		assertCalendarTimeZoneId(
			calendar, PropsUtil.get(PropsKeys.COMPANY_DEFAULT_TIME_ZONE));
	}

	@Test
	public void testUpgradeSetsUserCalendarTimeZoneId() throws Exception {
		setUserTimeZoneId("Asia/Shangai");

		Calendar calendar = CalendarTestUtil.addCalendar(_user);

		_upgradeProcess.upgrade();

		assertCalendarTimeZoneId(calendar, "Asia/Shangai");
	}

	protected void assertCalendarTimeZoneId(
			Calendar calendar, String timeZoneId)
		throws PortalException {

		EntityCacheUtil.clearCache();

		calendar = _calendarLocalService.getCalendar(calendar.getCalendarId());

		Assert.assertEquals(timeZoneId, calendar.getTimeZoneId());
	}

	protected void assertHasColumn(String columnName) throws Exception {
		Assert.assertTrue(
			_upgradeDatabaseTestHelper.hasColumn("Calendar", columnName));
	}

	protected void setUserTimeZoneId(String timeZoneId) {
		_user.setTimeZoneId(timeZoneId);

		_userLocalService.updateUser(_user);
	}

	@Inject
	private CalendarLocalService _calendarLocalService;

	private Group _group;
	private UpgradeDatabaseTestHelper _upgradeDatabaseTestHelper;
	private UpgradeProcess _upgradeProcess;

	@Inject(
		filter = "component.name=com.liferay.calendar.internal.upgrade.registry.CalendarServiceUpgradeStepRegistrator"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}