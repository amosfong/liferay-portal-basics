/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.security.permission.resource;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "model.class.name=com.liferay.account.model.AccountEntry",
	service = ModelResourcePermission.class
)
public class AccountEntryModelResourcePermission
	implements ModelResourcePermission<AccountEntry> {

	@Override
	public void check(
			PermissionChecker permissionChecker, AccountEntry accountEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AccountEntry.class.getName(),
				accountEntry.getAccountEntryId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AccountEntry.class.getName(), accountEntryId,
				actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, AccountEntry accountEntry,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker, accountEntry.getAccountEntryId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		AccountEntry accountEntry = _accountEntryLocalService.fetchAccountEntry(
			accountEntryId);

		if ((accountEntry != null) &&
			permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(), AccountEntry.class.getName(),
				accountEntryId, accountEntry.getUserId(), actionId)) {

			return true;
		}

		List<AccountEntryOrganizationRel> accountEntryOrganizationRels =
			_accountEntryOrganizationRelLocalService.
				getAccountEntryOrganizationRels(accountEntryId);

		long[] userOrganizationIds =
			_organizationLocalService.getUserOrganizationIds(
				permissionChecker.getUserId(), true);

		for (AccountEntryOrganizationRel accountEntryOrganizationRel :
				accountEntryOrganizationRels) {

			Organization organization =
				_organizationLocalService.fetchOrganization(
					accountEntryOrganizationRel.getOrganizationId());

			Organization originalOrganization = organization;

			while (organization != null) {
				if (Objects.equals(
						actionId, AccountActionKeys.UPDATE_ORGANIZATIONS) &&
					permissionChecker.hasPermission(
						organization.getGroupId(), AccountEntry.class.getName(),
						accountEntryId,
						AccountActionKeys.MANAGE_ORGANIZATIONS)) {

					return true;
				}

				boolean organizationMember = ArrayUtil.contains(
					userOrganizationIds, organization.getOrganizationId());

				if (!Objects.equals(
						actionId, AccountActionKeys.MANAGE_ORGANIZATIONS) &&
					!Objects.equals(
						actionId, AccountActionKeys.UPDATE_ORGANIZATIONS) &&
					organizationMember &&
					OrganizationPermissionUtil.contains(
						permissionChecker, organization.getOrganizationId(),
						AccountActionKeys.MANAGE_AVAILABLE_ACCOUNTS)) {

					return true;
				}

				if (Objects.equals(organization, originalOrganization) &&
					permissionChecker.hasPermission(
						organization.getGroupId(), AccountEntry.class.getName(),
						accountEntryId, actionId)) {

					return true;
				}

				if (!Objects.equals(organization, originalOrganization) &&
					(OrganizationPermissionUtil.contains(
						permissionChecker, organization,
						AccountActionKeys.MANAGE_SUBORGANIZATIONS_ACCOUNTS) ||
					 OrganizationPermissionUtil.contains(
						 permissionChecker, organization,
						 AccountActionKeys.UPDATE_SUBORGANIZATIONS_ACCOUNTS)) &&
					((organizationMember &&
					  Objects.equals(actionId, ActionKeys.VIEW)) ||
					 permissionChecker.hasPermission(
						 organization.getGroupId(),
						 AccountEntry.class.getName(), accountEntryId,
						 actionId))) {

					return true;
				}

				organization = organization.getParentOrganization();
			}
		}

		long accountEntryGroupId = 0;

		if (accountEntry != null) {
			accountEntryGroupId = accountEntry.getAccountEntryGroupId();
		}

		if (Objects.equals(actionId, AccountActionKeys.UPDATE_ORGANIZATIONS) &&
			permissionChecker.hasPermission(
				accountEntryGroupId, AccountEntry.class.getName(),
				accountEntryId, AccountActionKeys.MANAGE_ORGANIZATIONS)) {

			return true;
		}

		return permissionChecker.hasPermission(
			accountEntryGroupId, AccountEntry.class.getName(), accountEntryId,
			actionId);
	}

	@Override
	public String getModelName() {
		return AccountEntry.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference(
		target = "(resource.name=" + AccountConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}