/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.search.AssetSearcherFactory;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
public class AssetSearcherStagingTest {

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

	@Test
	public void testSiteRolePermissions() throws Exception {
		Role role = addRole(RoleConstants.TYPE_SITE);

		String className = "com.liferay.journal.model.JournalArticle";

		RoleTestUtil.addResourcePermission(
			role, className, ResourceConstants.SCOPE_GROUP_TEMPLATE, "0",
			ActionKeys.VIEW);

		User user = addUser();

		UserTestUtil.setUser(user);

		addUserGroupRole(user, role);

		JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		UserTestUtil.setUser(TestPropsValues.getUser());

		GroupTestUtil.enableLocalStaging(_group);

		UserTestUtil.setUser(user);

		SearchContext searchContext = getSearchContext();

		Group stagingGroup = _group.getStagingGroup();

		searchContext.setGroupIds(new long[] {stagingGroup.getGroupId()});

		searchContext.setUserId(user.getUserId());

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.addSelectedFieldNames(Field.GROUP_ID, Field.STAGING_GROUP);

		Hits hits = search(getAssetEntryQuery(className), searchContext);

		Document[] documents = hits.getDocs();

		DocumentsAssert.assertCount(
			hits.toString(), documents, Field.COMPANY_ID, 1);

		Document document = documents[0];

		assertField(
			document, Field.GROUP_ID,
			String.valueOf(stagingGroup.getGroupId()));
		assertField(document, Field.STAGING_GROUP, StringPool.TRUE);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected Role addRole(int roleType) throws Exception {
		Role role = RoleTestUtil.addRole(roleType);

		_roles.add(role);

		return role;
	}

	protected User addUser() throws Exception {
		User user = UserTestUtil.addUser(_group.getGroupId());

		_users.add(user);

		return user;
	}

	protected UserGroupRole addUserGroupRole(User user, Role role) {
		UserGroupRole userGroupRole =
			_userGroupRoleLocalService.addUserGroupRole(
				user.getUserId(), _group.getGroupId(), role.getRoleId());

		_userGroupRoles.add(userGroupRole);

		return userGroupRole;
	}

	protected void assertField(Document document, String field, String value) {
		Assert.assertEquals(document.toString(), document.get(field), value);
	}

	protected AssetEntryQuery getAssetEntryQuery(String... classNames) {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setClassNameIds(getClassNameIds(classNames));
		assetEntryQuery.setGroupIds(new long[] {_group.getGroupId()});

		return assetEntryQuery;
	}

	protected long[] getClassNameIds(String... classNames) {
		return TransformUtil.transformToLongArray(
			Arrays.asList(classNames), PortalUtil::getClassNameId);
	}

	protected SearchContext getSearchContext() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(_group.getCompanyId());

		return searchContext;
	}

	protected Hits search(
			AssetEntryQuery assetEntryQuery, SearchContext searchContext)
		throws Exception {

		BaseSearcher baseSearcher = _assetSearcherFactory.createBaseSearcher(
			assetEntryQuery);

		return baseSearcher.search(searchContext);
	}

	@Inject
	private static AssetSearcherFactory _assetSearcherFactory;

	@Inject
	private static UserGroupRoleLocalService _userGroupRoleLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private final List<Role> _roles = new ArrayList<>();

	@DeleteAfterTestRun
	private final List<UserGroupRole> _userGroupRoles = new ArrayList<>();

	private final List<User> _users = new ArrayList<>();

}