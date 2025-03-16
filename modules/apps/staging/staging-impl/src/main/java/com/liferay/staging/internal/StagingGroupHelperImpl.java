/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.staging.StagingGroupHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(service = StagingGroupHelper.class)
public class StagingGroupHelperImpl implements StagingGroupHelper {

	@Override
	public Group fetchLiveGroup(Group group) {
		if (isLocalStagingGroup(group)) {
			return fetchLocalLiveGroup(group);
		}

		if (isRemoteStagingGroup(group)) {
			return fetchRemoteLiveGroup(group);
		}

		return null;
	}

	@Override
	public Group fetchLiveGroup(long groupId) {
		return fetchLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public Group fetchLocalLiveGroup(Group group) {
		if (!isLocalStagingGroup(group)) {
			return null;
		}

		group = _getParentGroupForScopeGroup(group);

		return _groupLocalService.fetchGroup(group.getLiveGroupId());
	}

	@Override
	public Group fetchLocalLiveGroup(long groupId) {
		return fetchLocalLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public Group fetchLocalStagingGroup(Group group) {
		if (!isLocalLiveGroup(group)) {
			return null;
		}

		group = _getParentGroupForScopeGroup(group);

		return _groupLocalService.fetchStagingGroup(group.getGroupId());
	}

	@Override
	public Group fetchLocalStagingGroup(long groupId) {
		return fetchLocalStagingGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public Group fetchRemoteLiveGroup(Group group) {
		return null;
	}

	@Override
	public Group fetchRemoteLiveGroup(long groupId) {
		return fetchRemoteLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public Group getStagedPortletGroup(Group group, String portletId) {
		if (isLocalStagingGroup(group.getGroupId()) &&
			!isStagedPortlet(group.getGroupId(), portletId)) {

			return group.getLiveGroup();
		}

		return group;
	}

	@Override
	public long getStagedPortletGroupId(long groupId, String portletId) {
		if (!isStagedPortlet(groupId, portletId) &&
			!isRemoteStagingGroup(groupId)) {

			Group liveGroup = fetchLiveGroup(groupId);

			if (liveGroup != null) {
				return liveGroup.getGroupId();
			}
		}

		return groupId;
	}

	@Override
	public boolean isLiveGroup(Group group) {
		if (isLocalLiveGroup(group) || isRemoteLiveGroup(group)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isLiveGroup(long groupId) {
		return isLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isLocalLiveGroup(Group group) {
		if (group == null) {
			return false;
		}

		group = _getParentGroupForScopeGroup(group);

		Group stagingGroup = _groupLocalService.fetchStagingGroup(
			group.getGroupId());

		if (stagingGroup == null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isLocalLiveGroup(long groupId) {
		return isLocalLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isLocalStagingGroup(Group group) {
		if (group == null) {
			return false;
		}

		group = _getParentGroupForScopeGroup(group);

		if (group.getLiveGroupId() == GroupConstants.DEFAULT_LIVE_GROUP_ID) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isLocalStagingGroup(long groupId) {
		return isLocalStagingGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isLocalStagingOrLocalLiveGroup(Group group) {
		if (isLocalStagingGroup(group) || isLocalLiveGroup(group)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isLocalStagingOrLocalLiveGroup(long groupId) {
		return isLocalStagingOrLocalLiveGroup(
			_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isRemoteLiveGroup(Group group) {
		if (group == null) {
			return false;
		}

		group = _getParentGroupForScopeGroup(group);

		if (group.getRemoteStagingGroupCount() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRemoteLiveGroup(long groupId) {
		return isRemoteLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isRemoteStagingGroup(Group group) {
		if (group == null) {
			return false;
		}

		group = _getParentGroupForScopeGroup(group);

		return GetterUtil.getBoolean(
			_getTypeSettingsProperty(group, "stagedRemotely"));
	}

	@Override
	public boolean isRemoteStagingGroup(long groupId) {
		return isRemoteStagingGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isRemoteStagingOrRemoteLiveGroup(Group group) {
		if (isRemoteStagingGroup(group) || isRemoteLiveGroup(group)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRemoteStagingOrRemoteLiveGroup(long groupId) {
		return isRemoteStagingOrRemoteLiveGroup(
			_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isStagedPortlet(Group group, String portletId) {
		if (!isStagingGroup(group) && !isLiveGroup(group)) {
			return false;
		}

		return group.isStagedPortlet(portletId);
	}

	@Override
	public boolean isStagedPortlet(long groupId, String portletId) {
		return isStagedPortlet(
			_groupLocalService.fetchGroup(groupId), portletId);
	}

	@Override
	public boolean isStagedPortletData(long groupId, String className) {
		return true;
	}

	@Override
	public boolean isStagingGroup(Group group) {
		if (isLocalStagingGroup(group) || isRemoteStagingGroup(group)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isStagingGroup(long groupId) {
		return isStagingGroup(_groupLocalService.fetchGroup(groupId));
	}

	@Override
	public boolean isStagingOrLiveGroup(Group group) {
		if (isStagingGroup(group) || isLiveGroup(group)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isStagingOrLiveGroup(long groupId) {
		return isStagingOrLiveGroup(_groupLocalService.fetchGroup(groupId));
	}

	private Group _getParentGroupForScopeGroup(Group group) {
		if (group.isLayout()) {
			return group.getParentGroup();
		}

		return group;
	}

	private String _getTypeSettingsProperty(Group group, String key) {
		UnicodeProperties typeSettingsUnicodeProperties =
			group.getTypeSettingsProperties();

		return typeSettingsUnicodeProperties.getProperty(key);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		StagingGroupHelperImpl.class);

	@Reference
	private GroupLocalService _groupLocalService;

}