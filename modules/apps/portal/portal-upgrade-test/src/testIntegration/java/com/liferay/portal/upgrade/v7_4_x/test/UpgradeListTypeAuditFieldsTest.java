/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_4_x.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.v7_4_x.UpgradeListTypeAuditFields;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Matyas Wollner
 */
@RunWith(Arquillian.class)
public class UpgradeListTypeAuditFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		ListType listType = _listTypeLocalService.getListType(
			CompanyThreadLocal.getCompanyId(), _LIST_TYPE_NAME,
			_LIST_TYPE_TYPE);

		listType.setUuid(null);

		_listTypeLocalService.updateListType(listType);
	}

	@Test
	public void testUpgrade() throws UpgradeException {
		UpgradeProcess upgradeProcess = new UpgradeListTypeAuditFields();

		upgradeProcess.upgrade();

		ListType listType = _listTypeLocalService.getListType(
			CompanyThreadLocal.getCompanyId(), _LIST_TYPE_NAME,
			_LIST_TYPE_TYPE);

		Assert.assertNotNull(listType.getUuid());
	}

	private static final String _LIST_TYPE_NAME = "other";

	private static final String _LIST_TYPE_TYPE =
		"com.liferay.portal.kernel.model.Company.address";

	@Inject
	private ListTypeLocalService _listTypeLocalService;

}