/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.internal.security.permission.resource;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import java.util.Map;
import java.util.Objects;

/**
 * @author Preston Crary
 */
public class DefaultPortletResourcePermission
	implements PortletResourcePermission {

	public DefaultPortletResourcePermission(
		String resourceName,
		PortletResourcePermissionLogic... portletResourcePermissionLogics) {

		_resourceName = Objects.requireNonNull(resourceName);
		_portletResourcePermissionLogics = Objects.requireNonNull(
			portletResourcePermissionLogics);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PrincipalException {

		if (group == null) {
			check(permissionChecker, 0, actionId);
		}
		else if (!contains(permissionChecker, group, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), _resourceName,
				group.getGroupId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), _resourceName, groupId,
				actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, Group group, String actionId) {

		Map<Object, Object> permissionChecksMap =
			permissionChecker.getPermissionChecksMap();

		long groupId = 0;

		if (group != null) {
			groupId = group.getGroupId();
		}

		PermissionCacheKey permissionCacheKey = new PermissionCacheKey(
			_resourceName, groupId, actionId);

		long finalGroupId = groupId;

		return (Boolean)permissionChecksMap.computeIfAbsent(
			permissionCacheKey,
			key -> _contains(permissionChecker, group, finalGroupId, actionId));
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		Map<Object, Object> permissionChecksMap =
			permissionChecker.getPermissionChecksMap();

		PermissionCacheKey permissionCacheKey = new PermissionCacheKey(
			_resourceName, groupId, actionId);

		return (Boolean)permissionChecksMap.computeIfAbsent(
			permissionCacheKey,
			key -> {
				Group group = null;

				if (groupId > 0) {
					group = GroupLocalServiceUtil.fetchGroup(groupId);
				}

				return _contains(permissionChecker, group, groupId, actionId);
			});
	}

	@Override
	public String getResourceName() {
		return _resourceName;
	}

	private boolean _contains(
		PermissionChecker permissionChecker, Group group, long groupId,
		String actionId) {

		for (PortletResourcePermissionLogic portletResourcePermissionLogic :
				_portletResourcePermissionLogics) {

			Boolean contains = portletResourcePermissionLogic.contains(
				permissionChecker, _resourceName, group, actionId);

			if (contains != null) {
				return contains;
			}
		}

		if ((group != null) && group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		return permissionChecker.hasPermission(
			group, _resourceName, groupId, actionId);
	}

	private final PortletResourcePermissionLogic[]
		_portletResourcePermissionLogics;
	private final String _resourceName;

}