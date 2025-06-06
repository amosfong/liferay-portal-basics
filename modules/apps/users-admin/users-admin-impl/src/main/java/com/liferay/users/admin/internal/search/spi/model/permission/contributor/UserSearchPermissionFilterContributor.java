/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.search.spi.model.permission.contributor;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ContactTable;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.service.ContactLocalService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.spi.model.permission.contributor.SearchPermissionFilterContributor;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jesse Yeh
 * @author Drew Brokke
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.User",
	service = SearchPermissionFilterContributor.class
)
public class UserSearchPermissionFilterContributor
	implements SearchPermissionFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, long companyId, long[] groupIds,
		long userId, PermissionChecker permissionChecker, String className) {

		if (!className.equals(User.class.getName())) {
			return;
		}

		_addManagedOrganizationUsersFilter(booleanFilter, permissionChecker);
		_addOwnedUsersFilter(booleanFilter, userId);
	}

	private void _addManagedOrganizationUsersFilter(
		BooleanFilter booleanFilter, PermissionChecker permissionChecker) {

		try {
			TermsFilter termsFilter = new TermsFilter("organizationIds");

			UserBag userBag = permissionChecker.getUserBag();

			long[] userOrgIds = userBag.getUserOrgIds();

			for (long userOrgId : userOrgIds) {
				if (OrganizationPermissionUtil.contains(
						permissionChecker, userOrgId,
						ActionKeys.MANAGE_USERS)) {

					termsFilter.addValue(String.valueOf(userOrgId));
				}
			}

			if (!termsFilter.isEmpty()) {
				booleanFilter.add(termsFilter);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}
		}
	}

	private void _addOwnedUsersFilter(
		BooleanFilter booleanFilter, long userId) {

		TermsFilter termsFilter = new TermsFilter(Field.ENTRY_CLASS_PK);

		List<Contact> contacts = _contactLocalService.dslQuery(
			DSLQueryFactoryUtil.selectDistinct(
				ContactTable.INSTANCE
			).from(
				ContactTable.INSTANCE
			).where(
				ContactTable.INSTANCE.classNameId.eq(
					_portal.getClassNameId(User.class)
				).and(
					ContactTable.INSTANCE.userId.eq(userId)
				)
			));

		for (Contact contact : contacts) {
			termsFilter.addValue(String.valueOf(contact.getClassPK()));
		}

		if (!termsFilter.isEmpty()) {
			booleanFilter.add(termsFilter);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserSearchPermissionFilterContributor.class);

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private Portal _portal;

}