/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.constants.MBThreadConstants;
import com.liferay.message.boards.exception.DuplicateMBCategoryExternalReferenceCodeException;
import com.liferay.message.boards.exception.NoSuchCategoryException;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBCategoryLocalService;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Beslic
 * @author Roberto Díaz
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class MBCategoryLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test(expected = DuplicateMBCategoryExternalReferenceCodeException.class)
	public void testAddCategoryWithExistingExternalReferenceCode()
		throws Exception {

		String externalReferenceCode = RandomTestUtil.randomString();

		addCategory(externalReferenceCode);
		addCategory(externalReferenceCode);
	}

	@Test
	public void testAddCategoryWithExternalReferenceCode() throws Exception {
		String externalReferenceCode = RandomTestUtil.randomString();

		MBCategory category1 = addCategory(externalReferenceCode);

		Assert.assertNotNull(category1);

		Assert.assertEquals(
			externalReferenceCode, category1.getExternalReferenceCode());

		MBCategory category2 =
			_mbCategoryLocalService.getMBCategoryByExternalReferenceCode(
				externalReferenceCode, _group.getGroupId());

		Assert.assertEquals(category1, category2);
	}

	@Test(expected = NoSuchCategoryException.class)
	public void testDeleteCategoryByExternalReferenceCode() throws Exception {
		String externalReferenceCode = RandomTestUtil.randomString();

		MBCategory category = addCategory(externalReferenceCode);

		Assert.assertNotNull(category);

		Assert.assertEquals(
			externalReferenceCode, category.getExternalReferenceCode());

		_mbCategoryLocalService.deleteCategoryByExternalReferenceCode(
			externalReferenceCode, _group.getGroupId());

		_mbCategoryLocalService.getMBCategoryByExternalReferenceCode(
			externalReferenceCode, _group.getGroupId());
	}

	@Test
	public void testGetCategoriesAndThreadsCountInRootCategory()
		throws Exception {

		addMessage(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		MBCategory category1 = addCategory();

		addMessage(category1.getCategoryId());

		MBCategory category2 = addCategory();

		addMessage(category2.getCategoryId());

		addMessage(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		Assert.assertEquals(
			4,
			_mbCategoryLocalService.getCategoriesAndThreadsCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesAndThreadsCountInRootCategoryWithOnlyCategories()
		throws Exception {

		MBCategory category1 = addCategory();

		addCategory(category1.getCategoryId());

		addCategory();

		Assert.assertEquals(
			2,
			_mbCategoryLocalService.getCategoriesAndThreadsCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesAndThreadsCountInRootCategoryWithOnlyThreads()
		throws Exception {

		addMessage(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		addMessage(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		Assert.assertEquals(
			2,
			_mbCategoryLocalService.getCategoriesAndThreadsCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesAndThreadsCountWithOnlyCategories()
		throws Exception {

		MBCategory category1 = addCategory();

		addCategory(category1.getCategoryId());

		addCategory();

		Assert.assertEquals(
			1,
			_mbCategoryLocalService.getCategoriesAndThreadsCount(
				_group.getGroupId(), category1.getCategoryId(),
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesAndThreadsInRootCategory() throws Exception {
		MBMessage message1 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		MBCategory category1 = addCategory();

		addMessage(category1.getCategoryId());

		MBCategory category2 = addCategory();

		addMessage(category2.getCategoryId());

		MBMessage message2 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		List<Object> categoriesAndThreads =
			_mbCategoryLocalService.getCategoriesAndThreads(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			categoriesAndThreads.toString(), 4, categoriesAndThreads.size());
		Assert.assertEquals(category1, categoriesAndThreads.get(0));
		Assert.assertEquals(category2, categoriesAndThreads.get(1));
		Assert.assertEquals(message1.getThread(), categoriesAndThreads.get(2));
		Assert.assertEquals(message2.getThread(), categoriesAndThreads.get(3));
	}

	@Test
	public void testGetCategoriesAndThreadsInRootCategoryWithOnlyCategories()
		throws Exception {

		MBCategory category1 = addCategory();

		addCategory(category1.getCategoryId());

		MBCategory category2 = addCategory();

		List<Object> categoriesAndThreads =
			_mbCategoryLocalService.getCategoriesAndThreads(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			categoriesAndThreads.toString(), 2, categoriesAndThreads.size());
		Assert.assertEquals(category1, categoriesAndThreads.get(0));
		Assert.assertEquals(category2, categoriesAndThreads.get(1));
	}

	@Test
	public void testGetCategoriesAndThreadsInRootCategoryWithOnlyThreads()
		throws Exception {

		MBMessage message1 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		MBMessage message2 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		List<Object> categoriesAndThreads =
			_mbCategoryLocalService.getCategoriesAndThreads(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			categoriesAndThreads.toString(), 2, categoriesAndThreads.size());
		Assert.assertEquals(message1.getThread(), categoriesAndThreads.get(0));
		Assert.assertEquals(message2.getThread(), categoriesAndThreads.get(1));
	}

	@Test
	public void testGetCategoriesAndThreadsWithOnlyCategories()
		throws Exception {

		MBCategory category1 = addCategory();

		MBCategory subcategory1 = addCategory(category1.getCategoryId());

		addCategory();

		List<Object> categoriesAndThreads =
			_mbCategoryLocalService.getCategoriesAndThreads(
				_group.getGroupId(), category1.getCategoryId(),
				WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			categoriesAndThreads.toString(), 1, categoriesAndThreads.size());
		Assert.assertEquals(subcategory1, categoriesAndThreads.get(0));
	}

	@Test
	public void testGetCategoriesAndThreadsWithPriorityThread()
		throws Exception {

		MBMessage message1 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		MBCategory category1 = addCategory();

		addMessage(category1.getCategoryId());

		MBMessage priorityMessage = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, 2.0);

		MBMessage message2 = addMessage(
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

		List<Object> categoriesAndThreads =
			_mbCategoryLocalService.getCategoriesAndThreads(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			categoriesAndThreads.toString(), 4, categoriesAndThreads.size());
		Assert.assertEquals(category1, categoriesAndThreads.get(0));
		Assert.assertEquals(
			priorityMessage.getThread(), categoriesAndThreads.get(1));
		Assert.assertEquals(message1.getThread(), categoriesAndThreads.get(2));
		Assert.assertEquals(message2.getThread(), categoriesAndThreads.get(3));
	}

	@Test
	public void testGetCategoriesCountWithExcludedCategories()
		throws Exception {

		addCategory();

		MBCategory excludedCategory1 = addCategory();
		MBCategory excludedCategory2 = addCategory();

		Assert.assertEquals(
			3,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_ANY));
		Assert.assertEquals(
			1,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				new long[] {
					excludedCategory1.getCategoryId(),
					excludedCategory2.getCategoryId()
				},
				new long[] {MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID},
				WorkflowConstants.STATUS_ANY));
	}

	@Test
	public void testGetCategoriesCountWithExcludedCategory() throws Exception {
		addCategory();
		addCategory();

		MBCategory excludedCategory = addCategory();

		Assert.assertEquals(
			3,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_ANY));
		Assert.assertEquals(
			2,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(), excludedCategory.getCategoryId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_ANY));
	}

	@Test
	public void testGetCategoriesCountWithStatusApprovedAndExcludedCategories()
		throws Exception {

		addCategory();

		MBCategory excludedCategory1 = addCategory();
		MBCategory excludedCategory2 = addCategory();

		MBCategory draftCategory = addCategory();

		_mbCategoryLocalService.updateStatus(
			draftCategory.getUserId(), draftCategory.getCategoryId(),
			WorkflowConstants.STATUS_DRAFT);

		Assert.assertEquals(
			3,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
		Assert.assertEquals(
			1,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				new long[] {
					excludedCategory1.getCategoryId(),
					excludedCategory2.getCategoryId()
				},
				new long[] {MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID},
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesCountWithStatusApprovedAndExcludedCategory()
		throws Exception {

		addCategory();

		MBCategory excludedCategory = addCategory();

		MBCategory draftCategory = addCategory();

		_mbCategoryLocalService.updateStatus(
			draftCategory.getUserId(), draftCategory.getCategoryId(),
			WorkflowConstants.STATUS_DRAFT);

		Assert.assertEquals(
			2,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
		Assert.assertEquals(
			1,
			_mbCategoryLocalService.getCategoriesCount(
				_group.getGroupId(), excludedCategory.getCategoryId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED));
	}

	@Test
	public void testGetCategoriesWithExcludedCategories() throws Exception {
		List<MBCategory> expectedCategories = new ArrayList<>();

		expectedCategories.add(addCategory());

		MBCategory excludedCategory1 = addCategory();

		expectedCategories.add(excludedCategory1);

		MBCategory excludedCategory2 = addCategory();

		expectedCategories.add(excludedCategory2);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(_group.getGroupId()));

		expectedCategories.remove(excludedCategory1);
		expectedCategories.remove(excludedCategory2);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(),
				new long[] {
					excludedCategory1.getCategoryId(),
					excludedCategory2.getCategoryId()
				},
				new long[] {MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID},
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS));
	}

	@Test
	public void testGetCategoriesWithExcludedCategory() throws Exception {
		List<MBCategory> expectedCategories = new ArrayList<>();

		expectedCategories.add(addCategory());
		expectedCategories.add(addCategory());

		MBCategory excludedCategory = addCategory();

		expectedCategories.add(excludedCategory);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(_group.getGroupId()));

		expectedCategories.remove(excludedCategory);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(), excludedCategory.getCategoryId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS));
	}

	@Test
	public void testGetCategoriesWithStatusApprovedAndExcludedCategories()
		throws Exception {

		List<MBCategory> expectedCategories = new ArrayList<>();

		expectedCategories.add(addCategory());

		MBCategory excludedCategory1 = addCategory();

		expectedCategories.add(excludedCategory1);

		MBCategory excludedCategory2 = addCategory();

		expectedCategories.add(excludedCategory2);

		MBCategory draftCategory = addCategory();

		_mbCategoryLocalService.updateStatus(
			draftCategory.getUserId(), draftCategory.getCategoryId(),
			WorkflowConstants.STATUS_DRAFT);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(), WorkflowConstants.STATUS_APPROVED));

		expectedCategories.remove(excludedCategory1);
		expectedCategories.remove(excludedCategory2);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(),
				new long[] {
					excludedCategory1.getCategoryId(),
					excludedCategory2.getCategoryId()
				},
				new long[] {MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID},
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS));
	}

	@Test
	public void testGetCategoriesWithStatusApprovedAndExcludedCategory()
		throws Exception {

		List<MBCategory> expectedCategories = new ArrayList<>();

		expectedCategories.add(addCategory());

		MBCategory excludedCategory = addCategory();

		expectedCategories.add(excludedCategory);

		MBCategory draftCategory = addCategory();

		_mbCategoryLocalService.updateStatus(
			draftCategory.getUserId(), draftCategory.getCategoryId(),
			WorkflowConstants.STATUS_DRAFT);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(), WorkflowConstants.STATUS_APPROVED));

		expectedCategories.remove(excludedCategory);

		AssertUtils.assertEquals(
			expectedCategories,
			_mbCategoryLocalService.getCategories(
				_group.getGroupId(), excludedCategory.getCategoryId(),
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS));
	}

	@Test
	public void testGetParentCategory() throws Exception {
		MBCategory parentCategory = addCategory();

		MBCategory category = addCategory(parentCategory.getCategoryId());

		Assert.assertNotNull(category.getParentCategory());

		Assert.assertNull(parentCategory.getParentCategory());
	}

	@Test
	public void testGetParentDiscussionCategory() throws Exception {
		MBCategory discussionCategory = _mbCategoryLocalService.getCategory(
			MBCategoryConstants.DISCUSSION_CATEGORY_ID);

		Assert.assertNotNull(discussionCategory);
		Assert.assertNull(discussionCategory.getParentCategory());
	}

	protected MBCategory addCategory() throws Exception {
		return addCategory(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
	}

	protected MBCategory addCategory(long parentCategoryId) throws Exception {
		return addCategory(null, parentCategoryId);
	}

	protected MBCategory addCategory(String externalReferenceCode)
		throws Exception {

		return addCategory(
			externalReferenceCode,
			MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
	}

	protected MBCategory addCategory(
			String externalReferenceCode, long parentCategoryId)
		throws Exception {

		return _mbCategoryLocalService.addCategory(
			externalReferenceCode, TestPropsValues.getUserId(),
			parentCategoryId, RandomTestUtil.randomString(), StringPool.BLANK,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));
	}

	protected MBMessage addMessage(long categoryId) throws Exception {
		return addMessage(categoryId, MBThreadConstants.PRIORITY_NOT_GIVEN);
	}

	protected MBMessage addMessage(long categoryId, double priority)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		return _mbMessageLocalService.addMessage(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			_group.getGroupId(), categoryId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), MBMessageConstants.DEFAULT_FORMAT,
			inputStreamOVPs, false, priority, false, serviceContext);
	}

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private MBCategoryLocalService _mbCategoryLocalService;

	@Inject
	private MBMessageLocalService _mbMessageLocalService;

}