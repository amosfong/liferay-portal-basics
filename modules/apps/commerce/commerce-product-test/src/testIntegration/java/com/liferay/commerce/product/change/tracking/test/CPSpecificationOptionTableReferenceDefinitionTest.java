/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.change.tracking.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.test.util.BaseTableReferenceDefinitionTestCase;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPOptionCategoryLocalService;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class CPSpecificationOptionTableReferenceDefinitionTest
	extends BaseTableReferenceDefinitionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_cpOptionCategory = _cpOptionCategoryLocalService.addCPOptionCategory(
			RandomTestUtil.randomString(), TestPropsValues.getUserId(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomDouble(),
			CPOptionCategoryTableReferenceDefinitionTest.class.getSimpleName(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));
	}

	@Override
	protected CTModel<?> addCTModel() throws Exception {
		_cpSpecificationOption =
			_cpSpecificationOptionLocalService.addCPSpecificationOption(
				RandomTestUtil.randomString(), TestPropsValues.getUserId(),
				_cpOptionCategory.getCPOptionCategoryId(), null,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomBoolean(),
				CPSpecificationOptionTableReferenceDefinitionTest.class.
					getSimpleName(),
				RandomTestUtil.randomDouble(),
				ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		return _cpSpecificationOption;
	}

	private CPOptionCategory _cpOptionCategory;

	@Inject
	private CPOptionCategoryLocalService _cpOptionCategoryLocalService;

	@DeleteAfterTestRun
	private CPSpecificationOption _cpSpecificationOption;

	@Inject
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

}