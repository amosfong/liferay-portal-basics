/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.layout.constants.LayoutTypeSettingsConstants;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.content.page.editor.web.internal.util.StyleBookEntryUtil;
import com.liferay.layout.content.page.editor.web.internal.util.layout.structure.LayoutStructureUtil;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.util.DefaultStyleBookEntryUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/change_master_layout"
	},
	service = MVCActionCommand.class
)
public class ChangeMasterLayoutMVCActionCommand
	extends BaseContentPageEditorTransactionalMVCActionCommand {

	@Override
	protected JSONObject doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long masterLayoutPlid = ParamUtil.getLong(
			actionRequest, "masterLayoutPlid");

		Layout layout = _layoutLocalService.fetchLayout(themeDisplay.getPlid());

		LayoutPermissionUtil.checkLayoutRestrictedUpdatePermission(
			themeDisplay.getPermissionChecker(), layout);

		Layout updatedLayout = _layoutLocalService.updateMasterLayoutPlid(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			masterLayoutPlid);

		if (layout.isDraftLayout()) {
			UnicodeProperties layoutTypeSettingsUnicodeProperties =
				layout.getTypeSettingsProperties();

			layoutTypeSettingsUnicodeProperties.put(
				LayoutTypeSettingsConstants.KEY_DESIGN_CONFIGURATION_MODIFIED,
				Boolean.TRUE.toString());

			updatedLayout = _layoutLocalService.updateLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(),
				layoutTypeSettingsUnicodeProperties.toString());
		}

		actionRequest.setAttribute(WebKeys.LAYOUT, updatedLayout);

		if (masterLayoutPlid == 0) {
			return JSONUtil.put(
				"styleBook",
				_getStyleBookJSONObject(updatedLayout, themeDisplay));
		}

		LayoutStructure layoutStructure =
			LayoutStructureUtil.getLayoutStructure(
				themeDisplay.getScopeGroupId(), masterLayoutPlid, "DEFAULT");

		List<FragmentEntryLink> fragmentEntryLinks =
			_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
				themeDisplay.getScopeGroupId(), masterLayoutPlid);

		JSONObject fragmentEntryLinksJSONObject =
			_jsonFactory.createJSONObject();

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			JSONObject fragmentEntryLinkJSONObject =
				_fragmentEntryLinkManager.getFragmentEntryLinkJSONObject(
					fragmentEntryLink,
					_portal.getHttpServletRequest(actionRequest),
					_portal.getHttpServletResponse(actionResponse),
					layoutStructure);

			fragmentEntryLinkJSONObject.put("masterLayout", Boolean.TRUE);

			fragmentEntryLinksJSONObject.put(
				String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
				fragmentEntryLinkJSONObject);
		}

		return JSONUtil.put(
			"fragmentEntryLinks", fragmentEntryLinksJSONObject
		).put(
			"masterLayoutData", layoutStructure.toJSONObject()
		).put(
			"styleBook", _getStyleBookJSONObject(updatedLayout, themeDisplay)
		);
	}

	@Override
	protected boolean isLayoutLockRequired() {
		return false;
	}

	private JSONObject _getStyleBookJSONObject(
			Layout layout, ThemeDisplay themeDisplay)
		throws Exception {

		StyleBookEntry defaultMasterStyleBookEntry =
			DefaultStyleBookEntryUtil.getDefaultMasterStyleBookEntry(layout);

		String defaultStyleBookEntryName = StringPool.BLANK;
		String defaultStyleBookEntryImagePreviewURL = StringPool.BLANK;

		if (defaultMasterStyleBookEntry != null) {
			defaultStyleBookEntryName = defaultMasterStyleBookEntry.getName();
			defaultStyleBookEntryImagePreviewURL =
				defaultMasterStyleBookEntry.getImagePreviewURL(themeDisplay);
		}

		JSONObject jsonObject = JSONUtil.put(
			"defaultStyleBookEntryImagePreviewURL",
			defaultStyleBookEntryImagePreviewURL
		).put(
			"defaultStyleBookEntryName", defaultStyleBookEntryName
		);

		StyleBookEntry styleBookEntry =
			DefaultStyleBookEntryUtil.getDefaultStyleBookEntry(layout);

		return jsonObject.put(
			"styleBookEntryId", layout.getStyleBookEntryId()
		).put(
			"tokenValues",
			StyleBookEntryUtil.getFrontendTokensValues(
				_frontendTokenDefinitionRegistry.getFrontendTokenDefinition(
					layout.getLayoutSet()),
				themeDisplay.getLocale(), styleBookEntry)
		);
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLinkManager _fragmentEntryLinkManager;

	@Reference
	private FrontendTokenDefinitionRegistry _frontendTokenDefinitionRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}