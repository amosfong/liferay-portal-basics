/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.filter.groupid;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.internal.spi.model.query.contributor.GroupIdQueryPreFilterContributor;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Tibor Lipusz
 * @author André de Oliveira
 */
public abstract class BaseGroupIdQueryPreFilterContributorTestCase
	extends BaseIndexingTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		Mockito.doReturn(
			Arrays.asList(_INACTIVE_GROUP_ID1, _INACTIVE_GROUP_ID2)
		).when(
			_groupLocalService
		).getGroupIds(
			Mockito.anyLong(), Mockito.eq(false)
		);
	}

	@Test
	public void testNoEmptyClauses() throws Exception {
		Group group = Mockito.mock(Group.class);

		long groupId = 11111;

		Mockito.doReturn(
			group
		).when(
			_groupLocalService
		).getGroup(
			groupId
		);

		Mockito.doReturn(
			false
		).when(
			_groupLocalService
		).isLiveGroupActive(
			group
		);

		assertSearch(
			indexingTestHelper -> indexingTestHelper.define(
				searchContext -> {
					searchContext.setGroupIds(new long[] {groupId});

					BooleanFilter booleanFilter = (BooleanFilter)_createFilter(
						searchContext);

					_assertEmptyClauses(booleanFilter.getMustBooleanClauses());
					_assertEmptyClauses(
						booleanFilter.getMustNotBooleanClauses());
					_assertEmptyClauses(
						booleanFilter.getShouldBooleanClauses());
				}));
	}

	@Test
	public void testScopeEverythingWithInactiveGroups() {
		_addDocuments(1, 2, 3, _INACTIVE_GROUP_ID1, _INACTIVE_GROUP_ID2);

		_assertSearch(0, "[1, 2, 3]");

		Mockito.verify(
			_groupLocalService, Mockito.never()
		).getActiveGroups(
			Mockito.anyLong(), Mockito.anyBoolean()
		);
	}

	@Test
	public void testScopeSingleGroup() throws Exception {
		Group group = Mockito.mock(Group.class);

		Mockito.doReturn(
			group
		).when(
			_groupLocalService
		).getGroup(
			2
		);

		Mockito.doReturn(
			true
		).when(
			_groupLocalService
		).isLiveGroupActive(
			group
		);

		_addDocuments(1, 2, 3, _INACTIVE_GROUP_ID1, _INACTIVE_GROUP_ID2);

		_assertSearch(2, "[2]");
	}

	private void _addDocuments(long... groupIds) {
		for (long groupId : groupIds) {
			addDocument(
				document -> {
					document.addKeyword(Field.GROUP_ID, groupId);
					document.addKeyword(Field.SCOPE_GROUP_ID, groupId);
				});
		}
	}

	private void _assertEmptyClauses(List<BooleanClause<Filter>> clauses) {
		Assert.assertEquals(clauses.toString(), 0, clauses.size());
	}

	private void _assertSearch(long scopeGroupId, String expected) {
		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.define(
					searchContext -> {
						searchContext.setGroupIds(new long[] {scopeGroupId});

						indexingTestHelper.setFilter(
							_createFilter(searchContext));
					});

				indexingTestHelper.search();

				indexingTestHelper.verifyResponse(
					searchResponse ->
						DocumentsAssert.assertValuesIgnoreRelevance(
							searchResponse.getRequestString(),
							searchResponse.getDocuments(), Field.GROUP_ID,
							expected));
			});
	}

	private Filter _createFilter(SearchContext searchContext) {
		GroupIdQueryPreFilterContributor contributor =
			new GroupIdQueryPreFilterContributor();

		ReflectionTestUtil.setFieldValue(
			contributor, "_groupLocalService", _groupLocalService);

		BooleanFilter booleanFilter = new BooleanFilter();

		contributor.contribute(booleanFilter, searchContext);

		return booleanFilter;
	}

	private static final long _INACTIVE_GROUP_ID1 = 4L;

	private static final long _INACTIVE_GROUP_ID2 = 5L;

	private final GroupLocalService _groupLocalService = Mockito.mock(
		GroupLocalService.class);

}