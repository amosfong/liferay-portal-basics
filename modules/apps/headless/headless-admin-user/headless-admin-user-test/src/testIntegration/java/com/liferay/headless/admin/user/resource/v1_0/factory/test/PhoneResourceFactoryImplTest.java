/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.resource.v1_0.factory.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.admin.user.dto.v1_0.Phone;
import com.liferay.headless.admin.user.resource.v1_0.PhoneResource;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(Arquillian.class)
public class PhoneResourceFactoryImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_organization = OrganizationTestUtil.addOrganization();

		_companyAdminUser = UserTestUtil.addCompanyAdminUser(
			_companyLocalService.getCompany(_organization.getCompanyId()));
	}

	@Test
	public void testCheckPermissionsWithCompanyAdminUser() throws Exception {
		PhoneResource.Builder phoneResourceBuilder =
			_phoneResourceFactory.create();

		_testCheckPermissions(
			phoneResourceBuilder.user(
				_companyAdminUser
			).build());
	}

	@Test
	public void testCheckPermissionsWithUser1() throws Exception {
		User user = UserTestUtil.addUser();

		try {
			PhoneResource.Builder phoneResourceBuilder =
				_phoneResourceFactory.create();

			_testCheckPermissions(
				phoneResourceBuilder.checkPermissions(
					false
				).user(
					user
				).build());
		}
		finally {
			_userLocalService.deleteUser(user);
		}
	}

	@Test(expected = PrincipalException.MustHavePermission.class)
	public void testCheckPermissionsWithUser2() throws Exception {
		User user = UserTestUtil.addUser();

		try {
			PhoneResource.Builder phoneResourceBuilder =
				_phoneResourceFactory.create();

			_testCheckPermissions(
				phoneResourceBuilder.checkPermissions(
					true
				).user(
					user
				).build());
		}
		finally {
			_userLocalService.deleteUser(user);
		}
	}

	@Test
	public void testTransaction() throws Throwable {
		PhoneResource.Builder phoneResourceBuilder =
			_phoneResourceFactory.create();

		PhoneResource phoneResource = phoneResourceBuilder.user(
			_companyAdminUser
		).build();

		Phone expectedPhone = _randomPhone();

		Exception exception1 = new Exception();

		try {
			TransactionInvokerUtil.invoke(
				_REQUIRES_NEW_TRANSACTION_CONFIG,
				() -> {
					List<ListType> listTypes =
						_listTypeLocalService.getListTypes(
							_organization.getCompanyId(),
							ListTypeConstants.ORGANIZATION_PHONE);

					ListType listType = listTypes.get(0);

					_phoneLocalService.addPhone(
						RandomTestUtil.randomString(),
						_companyAdminUser.getUserId(),
						_organization.getModelClassName(),
						_organization.getOrganizationId(),
						expectedPhone.getPhoneNumber(),
						expectedPhone.getExtension(), listType.getListTypeId(),
						expectedPhone.getPrimary(), new ServiceContext());

					Page<Phone> page = phoneResource.getOrganizationPhonesPage(
						String.valueOf(_organization.getOrganizationId()));

					Collection<Phone> phones = page.getItems();

					Iterator<Phone> iterator = phones.iterator();

					Phone phone = iterator.next();

					Assert.assertEquals(
						expectedPhone.getPhoneNumber(), phone.getPhoneNumber());

					throw exception1;
				});
		}
		catch (Exception exception2) {
			Assert.assertSame(exception1, exception2);
		}

		TransactionInvokerUtil.invoke(
			_SUPPORTS_TRANSACTION_CONFIG,
			() -> {
				Page<Phone> page = phoneResource.getOrganizationPhonesPage(
					String.valueOf(_organization.getOrganizationId()));

				Collection<Phone> phones = page.getItems();

				Assert.assertTrue(phones.toString(), phones.isEmpty());

				return null;
			});
	}

	private Phone _randomPhone() {
		return new Phone() {
			{
				extension = String.valueOf(RandomTestUtil.randomInt());
				phoneNumber = RandomTestUtil.randomString();
				primary = false;
			}
		};
	}

	private void _testCheckPermissions(PhoneResource phoneResource)
		throws Exception {

		com.liferay.portal.kernel.model.Phone serviceBuiderPhone =
			OrganizationTestUtil.addPhone(_organization);

		Page<Phone> page = phoneResource.getOrganizationPhonesPage(
			String.valueOf(_organization.getOrganizationId()));

		Assert.assertEquals(1, page.getTotalCount());

		Collection<Phone> phones = page.getItems();

		Iterator<Phone> iterator = phones.iterator();

		Phone phone = iterator.next();

		Assert.assertEquals(
			Long.valueOf(serviceBuiderPhone.getPhoneId()), phone.getId());
	}

	private static final TransactionConfig _REQUIRES_NEW_TRANSACTION_CONFIG =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

	private static final TransactionConfig _SUPPORTS_TRANSACTION_CONFIG =
		TransactionConfig.Factory.create(
			Propagation.SUPPORTS, new Class<?>[] {Exception.class});

	@DeleteAfterTestRun
	private User _companyAdminUser;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private ListTypeLocalService _listTypeLocalService;

	@DeleteAfterTestRun
	private Organization _organization;

	@Inject
	private PhoneLocalService _phoneLocalService;

	@Inject
	private PhoneResource.Factory _phoneResourceFactory;

	@Inject
	private UserLocalService _userLocalService;

}