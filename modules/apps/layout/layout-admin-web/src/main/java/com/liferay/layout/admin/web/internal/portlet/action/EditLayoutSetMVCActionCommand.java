/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.portlet.action;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.layout.admin.constants.LayoutScreenNavigationEntryConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.LayoutSetService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"javax.portlet.name=" + LayoutAdminPortletKeys.GROUP_PAGES,
		"mvc.command.name=/layout_admin/edit_layout_set"
	},
	service = MVCActionCommand.class
)
public class EditLayoutSetMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long layoutSetId = ParamUtil.getLong(actionRequest, "layoutSetId");

		long liveGroupId = ParamUtil.getLong(actionRequest, "liveGroupId");
		long stagingGroupId = ParamUtil.getLong(
			actionRequest, "stagingGroupId");
		boolean privateLayout = ParamUtil.getBoolean(
			actionRequest, "privateLayout");
		String screenNavigationEntryKey = ParamUtil.getString(
			actionRequest, "screenNavigationEntryKey");

		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(layoutSetId);

		if (screenNavigationEntryKey.equals(
				LayoutScreenNavigationEntryConstants.ENTRY_KEY_DESIGN)) {

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_updateLogo(
				actionRequest, liveGroupId, stagingGroupId, privateLayout);

			updateLookAndFeel(
				actionRequest, themeDisplay.getCompanyId(), liveGroupId,
				stagingGroupId, privateLayout,
				layoutSet.getSettingsProperties());
		}

		_updateMergePages(actionRequest, liveGroupId);

		_updateSettings(
			actionRequest, liveGroupId, stagingGroupId, privateLayout,
			layoutSet.getSettingsProperties());
	}

	protected void updateLookAndFeel(
			ActionRequest actionRequest, long companyId, long liveGroupId,
			long stagingGroupId, boolean privateLayout,
			UnicodeProperties typeSettingsUnicodeProperties)
		throws Exception {

		long groupId = liveGroupId;

		if (stagingGroupId > 0) {
			groupId = stagingGroupId;
		}

		_updateLookAndFeel(
			actionRequest, companyId, groupId, privateLayout,
			typeSettingsUnicodeProperties);

		if (privateLayout) {
			return;
		}

		Group group = _groupLocalService.getGroup(groupId);

		if (!group.hasPrivateLayouts()) {
			_updateLookAndFeel(
				actionRequest, companyId, groupId, true,
				typeSettingsUnicodeProperties);
		}
	}

	private void _updateLogo(
			ActionRequest actionRequest, long liveGroupId, long stagingGroupId,
			boolean privateLayout)
		throws Exception {

		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		byte[] logoBytes = null;

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			logoBytes = FileUtil.getBytes(fileEntry.getContentStream());
		}

		long groupId = liveGroupId;

		if (stagingGroupId > 0) {
			groupId = stagingGroupId;
		}

		_layoutSetService.updateLogo(
			groupId, privateLayout, !deleteLogo, logoBytes);
	}

	private void _updateLookAndFeel(
			ActionRequest actionRequest, long companyId, long groupId,
			boolean privateLayout,
			UnicodeProperties typeSettingsUnicodeProperties)
		throws Exception {

		String deviceThemeId = ParamUtil.getString(
			actionRequest, "regularThemeId");
		String deviceColorSchemeId = ParamUtil.getString(
			actionRequest, "regularColorSchemeId");
		String deviceCss = ParamUtil.getString(actionRequest, "regularCss");

		if (Validator.isNotNull(deviceThemeId)) {
			long layoutId = ParamUtil.getLong(actionRequest, "layoutId");

			deviceColorSchemeId = ActionUtil.getColorSchemeId(
				companyId, deviceThemeId, deviceColorSchemeId);

			ActionUtil.updateThemeSettingsProperties(
				actionRequest, companyId, groupId, layoutId, privateLayout,
				typeSettingsUnicodeProperties, deviceThemeId, false);
		}

		_layoutSetService.updateLookAndFeel(
			groupId, privateLayout, deviceThemeId, deviceColorSchemeId,
			deviceCss);

		long faviconFileEntryId = ParamUtil.getLong(
			actionRequest, "faviconFileEntryId");

		_layoutSetService.updateFaviconFileEntryId(
			groupId, privateLayout, faviconFileEntryId);
	}

	private void _updateMergePages(
			ActionRequest actionRequest, long liveGroupId)
		throws Exception {

		boolean mergeGuestPublicPages = ParamUtil.getBoolean(
			actionRequest, "mergeGuestPublicPages");

		Group liveGroup = _groupLocalService.getGroup(liveGroupId);

		UnicodeProperties typeSettingsUnicodeProperties =
			liveGroup.getTypeSettingsProperties();

		typeSettingsUnicodeProperties.setProperty(
			"mergeGuestPublicPages", String.valueOf(mergeGuestPublicPages));

		_groupService.updateGroup(liveGroupId, liveGroup.getTypeSettings());
	}

	private void _updateSettings(
			ActionRequest actionRequest, long liveGroupId, long stagingGroupId,
			boolean privateLayout, UnicodeProperties settingsUnicodeProperties)
		throws Exception {

		UnicodeProperties typeSettingsUnicodeProperties =
			PropertiesParamUtil.getProperties(
				actionRequest, "TypeSettingsProperties--");

		settingsUnicodeProperties.putAll(typeSettingsUnicodeProperties);

		long groupId = liveGroupId;

		if (stagingGroupId > 0) {
			groupId = stagingGroupId;
		}

		_layoutSetService.updateSettings(
			groupId, privateLayout, settingsUnicodeProperties.toString());
	}

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private GroupService _groupService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private LayoutSetService _layoutSetService;

	@Reference
	private Portal _portal;

}