/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.service.test;

import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PasswordExpiredException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredRoleException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.Authenticator;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PasswordPolicyLocalService;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.randomizerbumpers.UniqueStringRandomizerBumper;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.PropsValuesTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.UserLastLoginDateComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.test.rule.FeatureFlags;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.util.DigesterImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Michael C. Han
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class UserLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		PrincipalThreadLocal.setName(_originalName);
	}

	@Before
	public void setUp() throws Exception {
		_auditMessageProcessor = new TestAuditMessageProcessor();

		_bundleActivator = new UserLocalServiceTestBundleActivator();

		Bundle bundle = FrameworkUtil.getBundle(UserLocalServiceTest.class);

		_bundleContext = bundle.getBundleContext();

		_bundleActivator.start(_bundleContext);
	}

	@After
	public void tearDown() throws Exception {
		_bundleActivator.stop(_bundleContext);
	}

	@Test
	public void testAddUserWithEmptyPassword() throws Exception {
		User user = _userLocalService.addUser(
			0, TestPropsValues.getCompanyId(), true, StringPool.BLANK,
			StringPool.BLANK, false, RandomTestUtil.randomString(),
			RandomTestUtil.randomString() + "@liferay.com", LocaleUtil.US,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), 0, 0, true, 1, 1, 1970,
			StringPool.BLANK, UserConstants.TYPE_REGULAR, new long[0],
			new long[0], new long[0], new long[0], false,
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
				TestPropsValues.getUserId()));

		Assert.assertTrue(Validator.isNull(user.getPassword()));

		String password = RandomTestUtil.randomString(
			UniqueStringRandomizerBumper.INSTANCE);

		user = _userLocalService.addUser(
			0, TestPropsValues.getCompanyId(), false, password, password, false,
			RandomTestUtil.randomString(),
			RandomTestUtil.randomString() + "@liferay.com", LocaleUtil.US,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), 0, 0, true, 1, 1, 1970,
			StringPool.BLANK, UserConstants.TYPE_REGULAR, new long[0],
			new long[0], new long[0], new long[0], false,
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
				TestPropsValues.getUserId()));

		Assert.assertTrue(Validator.isNotNull(user.getPassword()));
	}

	@Test
	public void testAuthenticateByEmailAddress() throws Exception {
		User user = UserTestUtil.addUser();

		String password = "password";

		user = _userLocalService.updatePassword(
			user.getUserId(), password, password, false, true);

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setExpireable(true);
		passwordPolicy.setMaxAge(0);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		int failedLoginAttempts = user.getFailedLoginAttempts();

		Assert.assertEquals(
			Authenticator.FAILURE,
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(),
				RandomTestUtil.randomString(), null, null, null));

		try {
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(), password, null,
				null, null);
		}
		catch (PortalException portalException) {
			Assert.assertEquals(
				PasswordExpiredException.class, portalException.getClass());
		}

		user = _userLocalService.fetchUser(user.getUserId());

		Assert.assertEquals(
			failedLoginAttempts + 2, user.getFailedLoginAttempts());
		passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setExpireable(false);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		Assert.assertEquals(
			Authenticator.SUCCESS,
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(), password, null,
				null, null));
	}

	@Test
	public void testAuthenticationWhenUserDoesNotExist() throws Exception {
		Assert.assertEquals(
			Authenticator.DNE,
			_userLocalService.authenticateByEmailAddress(
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), null, null, null));
	}

	@Test
	public void testDeleteUserDeletesNotificationEvents() throws Exception {
		User user = UserTestUtil.addUser();

		_userNotificationEventLocalService.sendUserNotificationEvents(
			user.getUserId(), null, 0, false, false,
			JSONFactoryUtil.createJSONObject());

		_userLocalService.deleteUser(user);

		int count =
			_userNotificationEventLocalService.getUserNotificationEventsCount(
				user.getUserId());

		Assert.assertEquals(0, count);
	}

	@Test
	public void testDeleteUserDeletesPreferences() throws Exception {
		User user = UserTestUtil.addUser();

		_portalPreferencesLocalService.addPortalPreferences(
			user.getUserId(), PortletKeys.PREFS_OWNER_TYPE_USER, null);
		_portletPreferencesLocalService.addPortletPreferences(
			user.getCompanyId(), user.getUserId(),
			PortletKeys.PREFS_OWNER_TYPE_USER, 0, null, null, null);

		_userLocalService.deleteUser(user);

		Assert.assertNull(
			_portalPreferencesLocalService.fetchPortalPreferences(
				user.getUserId(), PortletKeys.PREFS_OWNER_TYPE_USER));
		Assert.assertNull(
			_portletPreferencesLocalService.fetchPortletPreferences(
				user.getUserId(), PortletKeys.PREFS_OWNER_TYPE_USER, 0, null));
	}

	@Test
	public void testDeleteUserDeletesTickets() throws Exception {
		User user = UserTestUtil.addUser();

		_userLocalService.deleteUser(user);

		List<Ticket> tickets = _ticketLocalService.getTickets(
			user.getCompanyId(), User.class.getName(), user.getUserId());

		Assert.assertEquals(tickets.toString(), 0, tickets.size());
	}

	@Test
	public void testGetCompanyUsers() throws Exception {
		_company = CompanyTestUtil.addCompany();

		List<User> companyUsers = _userLocalService.getCompanyUsers(
			_company.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(companyUsers.toString(), 1, companyUsers.size());

		User user = companyUsers.get(0);

		Assert.assertFalse(user.isGuestUser());
	}

	@Test
	public void testGetGroupUsers() throws Exception {
		Group group = GroupTestUtil.addGroup();

		long[] userIds = _addUsers(20);

		_userLocalService.addGroupUsers(group.getGroupId(), userIds);

		long[] allGroupUserIds = _userLocalService.getGroupUserIds(
			group.getGroupId());

		Assert.assertEquals(
			allGroupUserIds.toString(), userIds.length + 1,
			allGroupUserIds.length);
		Assert.assertTrue(ArrayUtil.containsAll(allGroupUserIds, userIds));

		int start = 5;
		int delta = 5;

		List<User> partialGroupUsers = _userLocalService.getGroupUsers(
			group.getGroupId(), WorkflowConstants.STATUS_APPROVED, start,
			start + delta, null);

		Assert.assertEquals(
			partialGroupUsers.toString(), delta, partialGroupUsers.size());
		Assert.assertTrue(
			ArrayUtil.containsAll(
				allGroupUserIds,
				ListUtil.toLongArray(
					partialGroupUsers, User.USER_ID_ACCESSOR)));
	}

	@Test
	public void testGetNoAnnouncementsDeliveries() throws Exception {
		User user1 = UserTestUtil.addUser();
		User user2 = UserTestUtil.addUser();

		_announcementsDeliveryLocalService.addUserDelivery(
			user1.getUserId(), "general");

		List<User> users = _userLocalService.getNoAnnouncementsDeliveries(
			"general");

		Assert.assertFalse(users.toString(), users.contains(user1));
		Assert.assertTrue(users.toString(), users.contains(user2));
	}

	@Test
	public void testGetNoGroups() throws Exception {
		User user = UserTestUtil.addUser();

		_groupLocalService.deleteGroup(user.getGroupId());

		List<User> users = _userLocalService.getNoGroups();

		Assert.assertTrue(users.toString(), users.contains(user));
	}

	@Test
	public void testGetOrganizationsAndUserGroupsUsersCount() throws Exception {
		long[] commonUserIds = _addUsers(5);

		int organizationIterations = 4;
		int uniqueOrganizationUsersCount = 0;

		long[] organizationIds = new long[organizationIterations];

		for (int i = 0; i < organizationIterations; i++) {
			long[] uniqueUserIds = _addUsers(organizationIterations);

			Organization organization = OrganizationTestUtil.addOrganization();

			_userLocalService.addOrganizationUsers(
				organization.getOrganizationId(), commonUserIds);
			_userLocalService.addOrganizationUsers(
				organization.getOrganizationId(), uniqueUserIds);

			organizationIds[i] = organization.getOrganizationId();

			uniqueOrganizationUsersCount += uniqueUserIds.length;
		}

		int uniqueUserGroupUsersCount = 0;

		int userGroupIterations = 3;

		long[] userGroupIds = new long[userGroupIterations];

		for (int i = 0; i < userGroupIterations; i++) {
			long[] uniqueUserIds = _addUsers(userGroupIterations);

			UserGroup userGroup = UserGroupTestUtil.addUserGroup();

			_userLocalService.addUserGroupUsers(
				userGroup.getUserGroupId(), commonUserIds);
			_userLocalService.addUserGroupUsers(
				userGroup.getUserGroupId(), uniqueUserIds);

			userGroupIds[i] = userGroup.getUserGroupId();

			uniqueUserGroupUsersCount += uniqueUserIds.length;
		}

		long[] emptyLongArray = new long[0];

		Assert.assertEquals(
			0,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				null, null));
		Assert.assertEquals(
			0,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				emptyLongArray.clone(), null));
		Assert.assertEquals(
			0,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				null, emptyLongArray.clone()));
		Assert.assertEquals(
			0,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				emptyLongArray.clone(), emptyLongArray.clone()));

		int commonUsersCount = commonUserIds.length;

		Assert.assertEquals(
			commonUsersCount + uniqueOrganizationUsersCount +
				uniqueUserGroupUsersCount,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				organizationIds, userGroupIds));

		Assert.assertEquals(
			commonUsersCount + uniqueOrganizationUsersCount,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				organizationIds, null));
		Assert.assertEquals(
			commonUsersCount + uniqueOrganizationUsersCount,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				organizationIds, emptyLongArray.clone()));
		Assert.assertEquals(
			commonUsersCount + uniqueUserGroupUsersCount,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				null, userGroupIds));
		Assert.assertEquals(
			commonUsersCount + uniqueUserGroupUsersCount,
			_userLocalService.getOrganizationsAndUserGroupsUsersCount(
				emptyLongArray.clone(), userGroupIds));
	}

	@Test
	public void testGetOrganizationUsers() throws Exception {
		Organization organization = OrganizationTestUtil.addOrganization();

		long[] userIds = _addUsers(20);

		_userLocalService.addOrganizationUsers(
			organization.getOrganizationId(), userIds);

		long[] organizationUserIds = _userLocalService.getOrganizationUserIds(
			organization.getOrganizationId());

		Assert.assertEquals(
			organizationUserIds.toString(), userIds.length,
			organizationUserIds.length);
		Assert.assertTrue(ArrayUtil.containsAll(organizationUserIds, userIds));

		int start = 5;
		int delta = 5;

		List<User> organizationUsers = _userLocalService.getOrganizationUsers(
			organization.getOrganizationId(), WorkflowConstants.STATUS_APPROVED,
			start, start + delta, null);

		Assert.assertEquals(
			organizationUsers.toString(), delta, organizationUsers.size());
		Assert.assertTrue(
			ArrayUtil.containsAll(
				userIds,
				ListUtil.toLongArray(
					organizationUsers, User.USER_ID_ACCESSOR)));
	}

	@Test
	public void testGetUserGroupUsers() throws Exception {
		UserGroup userGroup = UserGroupTestUtil.addUserGroup();

		long[] userIds = _addUsers(20);

		_userLocalService.addUserGroupUsers(
			userGroup.getUserGroupId(), userIds);

		List<User> userGroupUsers = _userLocalService.getUserGroupUsers(
			userGroup.getUserGroupId());

		Assert.assertEquals(
			userGroupUsers.toString(), userIds.length, userGroupUsers.size());
		Assert.assertTrue(
			ArrayUtil.containsAll(
				ListUtil.toLongArray(userGroupUsers, User.USER_ID_ACCESSOR),
				userIds));

		int start = 5;
		int delta = 5;

		userGroupUsers = _userLocalService.getUserGroupUsers(
			userGroup.getUserGroupId(), start, start + delta);

		Assert.assertEquals(
			userGroupUsers.toString(), delta, userGroupUsers.size());
		Assert.assertTrue(
			ArrayUtil.containsAll(
				userIds,
				ListUtil.toLongArray(userGroupUsers, User.USER_ID_ACCESSOR)));
	}

	@Test
	public void testLockoutUser() throws Exception {
		User user = UserTestUtil.addUser();

		String password = "password";

		user = _userLocalService.updatePassword(
			user.getUserId(), password, password, false, true);

		Assert.assertEquals(
			Authenticator.SUCCESS,
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(), password, null,
				null, null));

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setLockout(true);
		passwordPolicy.setMaxFailure(1);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		int failedLoginAttempts = user.getFailedLoginAttempts();

		Assert.assertEquals(
			Authenticator.FAILURE,
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(),
				RandomTestUtil.randomString(), null, null, null));

		try {
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(), password, null,
				null, null);
		}
		catch (PortalException portalException) {
			Assert.assertEquals(
				UserLockoutException.PasswordPolicyLockout.class,
				portalException.getClass());
		}

		try {
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(),
				RandomTestUtil.randomString(), null, null, null);
		}
		catch (PortalException portalException) {
			Assert.assertEquals(
				AuthException.class, portalException.getClass());
		}

		user = _userLocalService.fetchUser(user.getUserId());

		Assert.assertEquals(
			failedLoginAttempts + 3, user.getFailedLoginAttempts());

		passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setLockout(false);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		Assert.assertEquals(
			Authenticator.SUCCESS,
			_userLocalService.authenticateByEmailAddress(
				user.getCompanyId(), user.getEmailAddress(), password, null,
				null, null));
	}

	@Test
	public void testPasswordHistory() throws Exception {
		User user = UserTestUtil.addUser();

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setHistory(true);
		passwordPolicy.setHistoryCount(2);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		String password1 = "password1";
		String password2 = "password2";

		try {
			ServiceContextThreadLocal.pushServiceContext(
				ServiceContextTestUtil.getServiceContext(
					user.getGroupId(), user.getUserId()));

			user = _userLocalService.updatePassword(
				user.getUserId(), password1, password1, false, false);

			user = _userLocalService.updatePassword(
				user.getUserId(), password2, password2, false, false);

			Assert.assertEquals(
				Authenticator.SUCCESS,
				_userLocalService.authenticateByEmailAddress(
					user.getCompanyId(), user.getEmailAddress(), password2,
					null, null, null));

			_userLocalService.updatePassword(
				user.getUserId(), password1, password1, false, false);

			Assert.fail();
		}
		catch (PortalException portalException) {
			Assert.assertEquals(
				UserPasswordException.MustNotBeRecentlyUsed.class,
				portalException.getClass());

			Assert.assertEquals(
				Authenticator.SUCCESS,
				_userLocalService.authenticateByEmailAddress(
					user.getCompanyId(), user.getEmailAddress(), password2,
					null, null, null));
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	@Test
	public void testPasswordHistoryWithModifiedEncryption() throws Exception {
		try (AutoCloseable autoCloseable1 =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					DigesterImpl.class, "_BASE_64", false);
			AutoCloseable autoCloseable2 =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					PasswordEncryptorUtil.class,
					"_PASSWORDS_ENCRYPTION_ALGORITHM", "SHA-384")) {

			User user = UserTestUtil.addUser();

			PasswordPolicy passwordPolicy = user.getPasswordPolicy();

			passwordPolicy.setHistory(true);
			passwordPolicy.setHistoryCount(2);

			_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

			String password1 = "password1";
			String password2 = "password2";

			try {
				ServiceContextThreadLocal.pushServiceContext(
					ServiceContextTestUtil.getServiceContext(
						user.getGroupId(), user.getUserId()));

				user = _userLocalService.updatePassword(
					user.getUserId(), password1, password1, false, false);

				Assert.assertEquals(
					"{SHA-384}f5e2dd85fe11cec4c913f0f1fcecddb4a654dd92852f9" +
						"78d6345638a0779a5e77ea39d33d6254bde0e1afa7a6c8ef0b9",
					user.getPassword());

				user = _userLocalService.updatePassword(
					user.getUserId(), password2, password2, false, false);

				Assert.assertEquals(
					"{SHA-384}66b6aa56af08dc8caf7e001683058338244f436de61d40" +
						"e342d0c69bda9f73cd6d167fdb29925db579923bdcef1fe5ae",
					user.getPassword());

				Assert.assertEquals(
					Authenticator.SUCCESS,
					_userLocalService.authenticateByEmailAddress(
						user.getCompanyId(), user.getEmailAddress(), password2,
						null, null, null));

				_userLocalService.updatePassword(
					user.getUserId(), password1, password1, false, false);

				Assert.fail();
			}
			catch (PortalException portalException) {
				Assert.assertEquals(
					UserPasswordException.MustNotBeRecentlyUsed.class,
					portalException.getClass());

				Assert.assertEquals(
					"{SHA-384}66b6aa56af08dc8caf7e001683058338244f436de61d40" +
						"e342d0c69bda9f73cd6d167fdb29925db579923bdcef1fe5ae",
					user.getPassword());
			}
			finally {
				ServiceContextThreadLocal.popServiceContext();
			}
		}
	}

	@Test
	public void testSearch() throws Exception {
		List<User> users = _userLocalService.search(
			TestPropsValues.getCompanyId(), null,
			WorkflowConstants.STATUS_APPROVED, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, (OrderByComparator<User>)null);

		users = ListUtil.filter(
			users, user -> user.getType() != UserConstants.TYPE_REGULAR);

		Assert.assertTrue(users.isEmpty());

		PermissionChecker oldPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

			users = _userLocalService.search(
				TestPropsValues.getCompanyId(), null,
				WorkflowConstants.STATUS_APPROVED,
				LinkedHashMapBuilder.<String, Object>put(
					"types",
					new long[] {UserConstants.TYPE_DEFAULT_SERVICE_ACCOUNT}
				).build(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				(OrderByComparator<User>)null);

			Assert.assertEquals(users.toString(), 1, users.size());

			User user = users.get(0);

			Assert.assertEquals(
				UserConstants.TYPE_DEFAULT_SERVICE_ACCOUNT, user.getType());
			Assert.assertTrue(user.isServiceAccountUser());

			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(UserTestUtil.addUser()));

			users = _userLocalService.search(
				TestPropsValues.getCompanyId(), null,
				WorkflowConstants.STATUS_APPROVED,
				LinkedHashMapBuilder.<String, Object>put(
					"types",
					new long[] {UserConstants.TYPE_DEFAULT_SERVICE_ACCOUNT}
				).build(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				(OrderByComparator<User>)null);

			Assert.assertTrue(users.isEmpty());
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(oldPermissionChecker);
		}
	}

	@Test
	public void testSearchCounts() throws Exception {

		// LPS-119805

		long[] values = new long[2001];

		int index = 0;

		for (long i = 1000; i <= 3000; i++) {
			values[index++] = i;
		}

		_userLocalService.searchCounts(
			TestPropsValues.getCompanyId(), WorkflowConstants.STATUS_APPROVED,
			values);
	}

	@Test
	public void testSearchCountsUserRole() throws Exception {
		Group group = GroupTestUtil.addGroup();

		PermissionChecker oldPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(UserTestUtil.addUser()));

		try {
			Map<Long, Integer> counts = _userLocalService.searchCounts(
				TestPropsValues.getCompanyId(),
				WorkflowConstants.STATUS_APPROVED,
				new long[] {group.getGroupId()});

			Integer count = counts.get(group.getGroupId());

			Assert.assertNotNull(count);

			Assert.assertEquals(1, count.intValue());
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(oldPermissionChecker);
		}
	}

	@Test
	public void testSearchUserGroupUserInOrganizationSite() throws Exception {
		Organization organization = OrganizationTestUtil.addOrganization(true);

		Group organizationSite = _groupLocalService.getOrganizationGroup(
			TestPropsValues.getCompanyId(), organization.getOrganizationId());

		organizationSite.setManualMembership(true);

		User user = UserTestUtil.addUser();

		UserGroup userGroup = UserGroupTestUtil.addUserGroup();

		_userGroupLocalService.addUserUserGroup(user.getUserId(), userGroup);

		_groupLocalService.addUserGroupGroup(
			userGroup.getUserGroupId(), organizationSite);

		List<User> users = _userLocalService.search(
			TestPropsValues.getCompanyId(), user.getFirstName(),
			WorkflowConstants.STATUS_APPROVED,
			LinkedHashMapBuilder.<String, Object>put(
				"inherit", Boolean.TRUE
			).put(
				"usersGroups", organizationSite.getGroupId()
			).build(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			(OrderByComparator<User>)null);

		Assert.assertEquals(users.toString(), 1, users.size());
		Assert.assertTrue(users.contains(user));
	}

	@Test
	public void testSearchUsersFromDatabase() throws Exception {
		try (SafeCloseable safeCloseable =
				PropsValuesTestUtil.swapWithSafeCloseable(
					"USERS_SEARCH_WITH_INDEX", false)) {

			_userLocalService.searchCount(
				TestPropsValues.getCompanyId(), null,
				WorkflowConstants.STATUS_APPROVED,
				LinkedHashMapBuilder.<String, Object>put(
					Field.GROUP_ID, TestPropsValues.getGroupId()
				).build());
		}
	}

	@Test
	public void testSetRoleUsers() throws Exception {
		User user = UserTestUtil.addUser();

		long roleId = RoleTestUtil.addGroupRole(user.getGroupId());

		_userLocalService.addRoleUser(roleId, user);

		user = _userLocalService.getUser(user.getUserId());

		Assert.assertTrue(ArrayUtil.contains(user.getRoleIds(), roleId));
	}

	@FeatureFlags(enable = false, value = "LPD-36010")
	@Test
	public void testSortUsersByLastLoginDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		String middleName = RandomTestUtil.randomString();

		User user1 = UserTestUtil.addUser();

		user1.setMiddleName(middleName);
		user1.setLastLoginDate(calendar.getTime());

		user1 = _userLocalService.updateUser(user1);

		calendar.add(Calendar.MINUTE, -5);

		User user2 = UserTestUtil.addUser();

		user2.setMiddleName(middleName);
		user2.setLastLoginDate(calendar.getTime());

		user2 = _userLocalService.updateUser(user2);

		calendar.add(Calendar.MINUTE, 2);

		User user3 = UserTestUtil.addUser();

		user3.setMiddleName(middleName);
		user3.setLastLoginDate(calendar.getTime());

		user3 = _userLocalService.updateUser(user3);

		List<User> users = _userLocalService.search(
			user1.getCompanyId(), middleName, 0, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, UserLastLoginDateComparator.getInstance(false));

		Assert.assertEquals(user1, users.get(0));
		Assert.assertEquals(user2, users.get(2));
		Assert.assertEquals(user3, users.get(1));

		users = _userLocalService.search(
			user1.getCompanyId(), middleName, 0, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, UserLastLoginDateComparator.getInstance(true));

		Assert.assertEquals(user1, users.get(2));
		Assert.assertEquals(user2, users.get(0));
		Assert.assertEquals(user3, users.get(1));
	}

	@Test
	public void testUnlockoutUserWithStaleLastFailedLoginDate()
		throws Exception {

		User user = UserTestUtil.addUser();

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setLockout(false);
		passwordPolicy.setResetFailureCount(3L);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		long companyId = user.getCompanyId();
		String emailAddress = user.getEmailAddress();
		String screenName = user.getScreenName();
		long userId = user.getUserId();

		user = _assertFailedLoginAttempts(
			() -> _userLocalService.authenticateByEmailAddress(
				companyId, emailAddress, RandomTestUtil.randomString(), null,
				null, null),
			user);
		user = _assertFailedLoginAttempts(
			() -> _userLocalService.authenticateByScreenName(
				companyId, screenName, RandomTestUtil.randomString(), null,
				null, null),
			user);

		_assertFailedLoginAttempts(
			() -> _userLocalService.authenticateByUserId(
				companyId, userId, RandomTestUtil.randomString(), null, null,
				null),
			user);
	}

	@Test
	public void testUnlockoutUserWithStaleLockoutDate() throws Exception {
		User user = UserTestUtil.addUser();

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		passwordPolicy.setLockout(true);
		passwordPolicy.setMaxFailure(0);
		passwordPolicy.setLockoutDuration(3L);

		_passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);

		long companyId = user.getCompanyId();
		String emailAddress = user.getEmailAddress();
		String screenName = user.getScreenName();
		long userId = user.getUserId();

		user = _assertLockout(
			() -> _userLocalService.authenticateByEmailAddress(
				companyId, emailAddress, RandomTestUtil.randomString(), null,
				null, null),
			user);
		user = _assertLockout(
			() -> _userLocalService.authenticateByScreenName(
				companyId, screenName, RandomTestUtil.randomString(), null,
				null, null),
			user);

		_assertLockout(
			() -> _userLocalService.authenticateByUserId(
				companyId, userId, RandomTestUtil.randomString(), null, null,
				null),
			user);
	}

	@Test
	public void testUnsetRoleUsers() throws Exception {
		User user = UserTestUtil.addUser();

		long roleId = RoleTestUtil.addGroupRole(user.getGroupId());

		_userLocalService.addRoleUser(roleId, user);

		_userLocalService.unsetRoleUsers(roleId, new long[] {user.getUserId()});

		Assert.assertFalse(ArrayUtil.contains(user.getRoleIds(), roleId));
	}

	@Test(expected = RequiredRoleException.MustNotRemoveLastAdministator.class)
	public void testUnsetRoleUsersLastAdministratorRole() throws Exception {
		Group group = GroupTestUtil.addGroup();

		UserTestUtil.addUser(group.getGroupId());

		List<User> groupUsers = _userLocalService.getGroupUsers(
			group.getGroupId());

		Role role = _roleLocalService.getRole(
			group.getCompanyId(), RoleConstants.ADMINISTRATOR);

		_userLocalService.unsetRoleUsers(role.getRoleId(), groupUsers);
	}

	@Test(expected = RequiredRoleException.MustNotRemoveUserRole.class)
	public void testUnsetRoleUsersUserRole() throws Exception {
		Group group = GroupTestUtil.addGroup();

		User user = UserTestUtil.addUser(group.getGroupId());

		Role role = _roleLocalService.getRole(
			group.getCompanyId(), RoleConstants.USER);

		_userLocalService.unsetRoleUsers(
			role.getRoleId(), new long[] {user.getUserId()});
	}

	@Test
	public void testUpdatePassword() throws Exception {
		User user = UserTestUtil.addUser();
		String password = RandomTestUtil.randomString(
			UniqueStringRandomizerBumper.INSTANCE);

		Date oldPasswordModifiedDate = user.getPasswordModifiedDate();

		try {
			ServiceContextThreadLocal.pushServiceContext(
				ServiceContextTestUtil.getServiceContext(
					user.getGroupId(), user.getUserId()));

			_userLocalService.updatePassword(
				user.getUserId(), password, password, false, true);

			user = _userLocalService.getUser(user.getUserId());

			Date passwordModifiedDate = user.getPasswordModifiedDate();

			Assert.assertTrue(
				passwordModifiedDate.after(oldPasswordModifiedDate));
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	@Test
	public void testUpdatePasswordWithModifiedAlgorithm() throws Exception {
		try (AutoCloseable autoCloseable =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					PasswordEncryptorUtil.class,
					"_PASSWORDS_ENCRYPTION_ALGORITHM",
					"PBKDF2WithHmacSHA1/160/720000")) {

			String password = RandomTestUtil.randomString(
				UniqueStringRandomizerBumper.INSTANCE);

			User user = _userLocalService.addUser(
				0, TestPropsValues.getCompanyId(), false, password, password,
				false, RandomTestUtil.randomString(),
				RandomTestUtil.randomString() + "@liferay.com", LocaleUtil.US,
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), 0, 0, true, 1, 1, 1970,
				StringPool.BLANK, UserConstants.TYPE_REGULAR, new long[0],
				new long[0], new long[0], new long[0], false,
				ServiceContextTestUtil.getServiceContext(
					TestPropsValues.getCompanyId(),
					TestPropsValues.getGroupId(), TestPropsValues.getUserId()));

			String encryptedPassword = user.getPassword();

			Assert.assertTrue(
				encryptedPassword.startsWith("{PBKDF2WithHmacSHA1}"));

			ReflectionTestUtil.setFieldValue(
				PasswordEncryptorUtil.class, "_PASSWORDS_ENCRYPTION_ALGORITHM",
				"MD5");

			password = RandomTestUtil.randomString(
				UniqueStringRandomizerBumper.INSTANCE);

			user = _userLocalService.updatePassword(
				user.getUserId(), password, password, false, true);

			encryptedPassword = user.getPassword();

			Assert.assertTrue(encryptedPassword.startsWith("{MD5}"));
		}
	}

	@Test
	public void testUpdateTicketWithModifiedEncryption() throws Exception {
		try (AutoCloseable autoCloseable1 =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					DigesterImpl.class, "_BASE_64", false);
			AutoCloseable autoCloseable2 =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					PasswordEncryptorUtil.class,
					"_PASSWORDS_ENCRYPTION_ALGORITHM", "SHA-384")) {

			Ticket ticket = _ticketLocalService.addDistinctTicket(
				RandomTestUtil.randomLong(), null, RandomTestUtil.randomLong(),
				TicketConstants.TYPE_PASSWORD, null, RandomTestUtil.nextDate(),
				null);

			ticket.setKey(PasswordEncryptorUtil.encrypt("password"));

			ticket = _ticketLocalService.updateTicket(ticket);

			Assert.assertEquals(
				"{SHA-384}a8b64babd0aca91a59bdbb7761b421d4f2bb38280" +
					"d3a75ba0f21f2bebc45583d446c598660c94ce680c47d19c30783a7",
				ticket.getKey());
		}
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = UserTestUtil.addUser();

		TransactionConfig transactionConfig = TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

		// Update user twice in same transaction (with email address change)

		try {
			TransactionInvokerUtil.invoke(
				transactionConfig,
				() -> {
					_userLocalService.updateUser(user);

					return _userLocalService.updateUser(
						user.getUserId(), StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, false, StringPool.BLANK,
						StringPool.BLANK,
						"TestUser" + RandomTestUtil.nextLong(),
						"UserServiceTest." + RandomTestUtil.nextLong() +
							"@liferay.com",
						false, null, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK, "UserServiceTest",
						StringPool.BLANK, "UserServiceTest", 0, 0, true,
						Calendar.JANUARY, 1, 1970, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK, null, null, null,
						null, null,
						ServiceContextTestUtil.getServiceContext(
							user.getGroupId(), user.getUserId()));
				});
		}
		catch (Throwable throwable) {
			throw new Exception(throwable);
		}
	}

	private long[] _addUsers(int numberOfUsers) throws Exception {
		long[] userIds = new long[numberOfUsers];

		for (int i = 0; i < numberOfUsers; i++) {
			User user = UserTestUtil.addUser();

			userIds[i] = user.getUserId();
		}

		return userIds;
	}

	private User _assertFailedLoginAttempts(
			UnsafeRunnable<PortalException> unsafeRunnable, User user)
		throws Exception {

		user.setLastFailedLoginDate(
			DateUtil.newDate(System.currentTimeMillis() - 5000L));
		user.setFailedLoginAttempts(3);

		user = _userLocalService.updateUser(user);

		unsafeRunnable.run();

		user = _userLocalService.fetchUser(user.getUserId());

		Assert.assertEquals(1, user.getFailedLoginAttempts());

		return user;
	}

	private User _assertLockout(
			UnsafeRunnable<PortalException> unsafeRunnable, User user)
		throws Exception {

		user.setLockout(true);
		user.setLockoutDate(
			DateUtil.newDate(System.currentTimeMillis() - 5000L));

		user = _userLocalService.updateUser(user);

		unsafeRunnable.run();

		user = _userLocalService.fetchUser(user.getUserId());

		Assert.assertFalse(user.isLockout());

		return user;
	}

	private static Company _company;

	@Inject
	private static CompanyLocalService _companyLocalService;

	private static String _originalName;

	@Inject
	private AnnouncementsDeliveryLocalService
		_announcementsDeliveryLocalService;

	private AuditMessageProcessor _auditMessageProcessor;
	private BundleActivator _bundleActivator;
	private BundleContext _bundleContext;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private PasswordPolicyLocalService _passwordPolicyLocalService;

	@Inject
	private PortalPreferencesLocalService _portalPreferencesLocalService;

	@Inject
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Inject
	private RoleLocalService _roleLocalService;

	private ServiceRegistration<AuditMessageProcessor> _serviceRegistration;

	@Inject
	private TicketLocalService _ticketLocalService;

	@Inject
	private UserGroupLocalService _userGroupLocalService;

	@Inject
	private UserLocalService _userLocalService;

	@Inject
	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

	private class TestAuditMessageProcessor implements AuditMessageProcessor {

		@Override
		public void process(AuditMessage auditMessage) {
			Assert.assertNotNull(auditMessage);

			JSONObject additionalInfoJSONObject =
				auditMessage.getAdditionalInfo();

			String authType = String.valueOf(
				additionalInfoJSONObject.get("authType"));

			Assert.assertEquals("emailAddress", authType);

			String reason = String.valueOf(
				additionalInfoJSONObject.get("reason"));

			Assert.assertEquals("User does not exist", reason);

			Assert.assertEquals(
				EventTypes.LOGIN_DNE, auditMessage.getEventType());
		}

	}

	private class UserLocalServiceTestBundleActivator
		implements BundleActivator {

		@Override
		public void start(BundleContext bundleContext) {
			_serviceRegistration = _bundleContext.registerService(
				AuditMessageProcessor.class, _auditMessageProcessor,
				HashMapDictionaryBuilder.<String, Object>put(
					"eventTypes", EventTypes.LOGIN_DNE
				).build());
		}

		@Override
		public void stop(BundleContext bundleContext) {
			_serviceRegistration.unregister();
		}

	}

}