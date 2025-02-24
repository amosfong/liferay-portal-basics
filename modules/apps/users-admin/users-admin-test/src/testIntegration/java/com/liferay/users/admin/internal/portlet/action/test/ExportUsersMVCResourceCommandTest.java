/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.portlet.action.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.expando.test.util.ExpandoTestUtil;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.PropsValuesTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pei-Jung Lan
 */
@RunWith(Arquillian.class)
public class ExportUsersMVCResourceCommandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetUserCSVWithExpando() throws Exception {
		try (SafeCloseable safeCloseable1 =
				PropsValuesTestUtil.swapWithSafeCloseable(
					"PERMISSIONS_CUSTOM_ATTRIBUTE_READ_CHECK_BY_DEFAULT",
					false)) {

			Company company1 = CompanyTestUtil.addCompany();

			User user1 = UserTestUtil.addUser(company1);

			ExpandoTable expandoTable =
				_expandoTableLocalService.addDefaultTable(
					company1.getCompanyId(), User.class.getName());

			ExpandoColumn expandoColumn = ExpandoTestUtil.addColumn(
				expandoTable, RandomTestUtil.randomString(),
				ExpandoColumnConstants.STRING);

			ExpandoValue expandoValue = _expandoValueLocalService.addValue(
				company1.getCompanyId(), User.class.getName(),
				expandoTable.getName(), expandoColumn.getName(),
				user1.getUserId(), RandomTestUtil.randomString());

			try (SafeCloseable safeCloseable2 =
					PropsValuesTestUtil.swapWithSafeCloseable(
						"USERS_EXPORT_CSV_FIELDS",
						new String[] {
							"fullName", "expando:" + expandoColumn.getName()
						})) {

				Assert.assertEquals(
					StringBundler.concat(
						CSVUtil.encode(user1.getFullName()), StringPool.COMMA,
						CSVUtil.encode(expandoValue.getString()),
						StringPool.NEW_LINE),
					_getUserCSV(user1));

				Company company2 = CompanyTestUtil.addCompany();

				User user2 = UserTestUtil.addUser(company2);

				Assert.assertEquals(
					StringBundler.concat(
						CSVUtil.encode(user2.getFullName()), StringPool.COMMA,
						StringPool.BLANK, StringPool.NEW_LINE),
					_getUserCSV(user2));
			}
		}
	}

	private String _getUserCSV(User user) {
		return ReflectionTestUtil.invoke(
			_mvcResourceCommand, "_getUserCSV", new Class<?>[] {User.class},
			user);
	}

	@Inject
	private ExpandoTableLocalService _expandoTableLocalService;

	@Inject
	private ExpandoValueLocalService _expandoValueLocalService;

	@Inject(filter = "mvc.command.name=/users_admin/export_users")
	private MVCResourceCommand _mvcResourceCommand;

}