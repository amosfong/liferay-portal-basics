/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.product.exception.NoSuchCPSpecificationOptionException;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalService;
import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.service.ListTypeDefinitionLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Beslic
 */
@RunWith(Arquillian.class)
public class CPSpecificationOptionLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), _user.getUserId());
	}

	@After
	public void tearDown() throws Exception {
		_cpSpecificationOptionLocalService.deleteCPSpecificationOptions(
			_serviceContext.getCompanyId());
	}

	@Test
	public void testAddSpecficationOption() throws Exception {
		frutillaRule.scenario(
			"Add Specification option"
		).given(
			"There is no any specifications"
		).when(
			"Specification option is added"
		).then(
			"Specification options should be created"
		);

		CPSpecificationOption cpSpecificationOption1 =
			_addCPSpecificationOptions(_serviceContext);

		CPSpecificationOption cpSpecificationOption2 =
			_cpSpecificationOptionLocalService.getCPSpecificationOption(
				_serviceContext.getCompanyId(),
				cpSpecificationOption1.getKey());

		Assert.assertEquals(
			cpSpecificationOption1.getKey(), cpSpecificationOption2.getKey());
	}

	@Test
	public void testAddSpecificationOptionWithMultiplePicklist()
		throws Exception {

		frutillaRule.scenario(
			"Add Specification option with multiple picklist"
		).given(
			"A specification is created"
		).when(
			"Adding picklist to the specification"
		).then(
			"Multiple picklist can be added"
		);

		ListTypeDefinition listTypeDefinition1 =
			_listTypeDefinitionLocalService.addListTypeDefinition(
				RandomTestUtil.randomString(), _user.getUserId(), false);
		ListTypeDefinition listTypeDefinition2 =
			_listTypeDefinitionLocalService.addListTypeDefinition(
				RandomTestUtil.randomString(), _user.getUserId(), false);

		CPSpecificationOption cpSpecificationOption =
			_cpSpecificationOptionLocalService.addCPSpecificationOption(
				RandomTestUtil.randomString(), _serviceContext.getUserId(), 0L,
				new long[] {
					listTypeDefinition1.getListTypeDefinitionId(),
					listTypeDefinition2.getListTypeDefinitionId()
				},
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomBoolean(), RandomTestUtil.randomString(),
				RandomTestUtil.randomDouble(), _serviceContext);

		List<ListTypeDefinition> listTypeDefinitions =
			cpSpecificationOption.getListTypeDefinitions();

		Assert.assertEquals(
			listTypeDefinition1.toString(), 2, listTypeDefinitions.size());
	}

	@Test(expected = NoSuchCPSpecificationOptionException.class)
	public void testGetSpecficationOption() throws Exception {
		frutillaRule.scenario(
			"Get Specification option"
		).given(
			"There is no any specifications"
		).when(
			"Specification option is searched"
		).then(
			"NoSuchCPSpecificationOptionException is thrown"
		);

		_cpSpecificationOptionLocalService.getCPSpecificationOption(
			_serviceContext.getCompanyId(), RandomTestUtil.randomString());
	}

	@Rule
	public final FrutillaRule frutillaRule = new FrutillaRule();

	private CPSpecificationOption _addCPSpecificationOptions(
			ServiceContext serviceContext)
		throws Exception {

		return _cpSpecificationOptionLocalService.addCPSpecificationOption(
			RandomTestUtil.randomString(), serviceContext.getUserId(), 0L, null,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomBoolean(), RandomTestUtil.randomString(),
			RandomTestUtil.randomDouble(), serviceContext);
	}

	private static User _user;

	@Inject
	private CPSpecificationOptionLocalService
		_cpSpecificationOptionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private ListTypeDefinitionLocalService _listTypeDefinitionLocalService;

	private ServiceContext _serviceContext;

}