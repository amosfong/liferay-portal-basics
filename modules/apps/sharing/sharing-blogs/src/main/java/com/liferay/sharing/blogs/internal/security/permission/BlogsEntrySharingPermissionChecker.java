/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.blogs.internal.security.permission;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.sharing.security.permission.SharingEntryAction;
import com.liferay.sharing.security.permission.SharingPermissionChecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "model.class.name=com.liferay.blogs.model.BlogsEntry",
	service = SharingPermissionChecker.class
)
public class BlogsEntrySharingPermissionChecker
	implements SharingPermissionChecker {

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, long groupId,
			Collection<SharingEntryAction> sharingEntryActions)
		throws PortalException {

		for (SharingEntryAction sharingEntryAction : sharingEntryActions) {
			if (!_actionKeysSet.contains(sharingEntryAction) ||
				!_blogsEntryModelResourcePermission.contains(
					permissionChecker, classPK,
					sharingEntryAction.getActionId())) {

				return false;
			}
		}

		return true;
	}

	private static final Set<SharingEntryAction> _actionKeysSet = new HashSet<>(
		Arrays.asList(
			SharingEntryAction.ADD_DISCUSSION, SharingEntryAction.UPDATE,
			SharingEntryAction.VIEW));

	@Reference(target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)")
	private ModelResourcePermission<BlogsEntry>
		_blogsEntryModelResourcePermission;

}