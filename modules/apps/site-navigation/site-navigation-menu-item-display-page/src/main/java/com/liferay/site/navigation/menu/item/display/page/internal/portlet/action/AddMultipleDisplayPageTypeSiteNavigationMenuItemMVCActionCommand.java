/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.item.display.page.internal.portlet.action;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.admin.constants.SiteNavigationAdminPortletKeys;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemService;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	property = {
		"javax.portlet.name=" + SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN,
		"mvc.command.name=/navigation_menu/add_multiple_display_page_type_site_navigation_menu_item"
	},
	service = MVCActionCommand.class
)
public class AddMultipleDisplayPageTypeSiteNavigationMenuItemMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		String siteNavigationMenuItemTypeString = ParamUtil.getString(
			actionRequest, "siteNavigationMenuItemType");
		long siteNavigationMenuId = ParamUtil.getLong(
			actionRequest, "siteNavigationMenuId");

		if (Validator.isNotNull(siteNavigationMenuItemTypeString) &&
			(siteNavigationMenuId > 0)) {

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			try {
				Map<Long, JSONObject> jsonObjects = new HashMap<>();

				JSONArray jsonArray = _jsonFactory.createJSONArray(
					ParamUtil.getString(actionRequest, "items"));

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject itemJSONObject = jsonArray.getJSONObject(i);

					if ((itemJSONObject != null) &&
						Objects.equals(
							itemJSONObject.getString("className"),
							siteNavigationMenuItemTypeString)) {

						jsonObjects.put(
							itemJSONObject.getLong("classPK"), itemJSONObject);
					}
				}

				int order = ParamUtil.getInteger(actionRequest, "order", -1);
				long parentSiteNavigationMenuItemId = ParamUtil.getLong(
					actionRequest, "parentSiteNavigationMenuItemId");

				SiteNavigationMenuItemType siteNavigationMenuItemType =
					_siteNavigationMenuItemTypeRegistry.
						getSiteNavigationMenuItemType(
							siteNavigationMenuItemTypeString);

				String message = _language.format(
					themeDisplay.getLocale(), "x-x-was-added-to-this-menu",
					Arrays.asList(
						jsonArray.length(),
						siteNavigationMenuItemType.getLabel(
							themeDisplay.getLocale())));

				SessionMessages.add(
					actionRequest, "siteNavigationMenuItemsAdded", message);
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}

				jsonObject.put(
					"errorMessage",
					_language.get(
						_portal.getHttpServletRequest(actionRequest),
						"an-unexpected-error-occurred"));
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to add multiple site navigation menu items ",
						"for site navigation menu ID ", siteNavigationMenuId,
						" and type ", siteNavigationMenuItemTypeString));
			}

			jsonObject.put(
				"errorMessage",
				_language.get(
					_portal.getHttpServletRequest(actionRequest),
					"an-unexpected-error-occurred"));
		}

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private void _addSiteNavigationMenuItem(
			long groupId, Map<Long, JSONObject> jsonObjects, int order,
			long parentSiteNavigationMenuItemId, ServiceContext serviceContext,
			long siteNavigationMenuId, String siteNavigationMenuItemType)
		throws PortalException {

		JSONObject jsonObject = jsonObjects.get(0);

		if (jsonObject == null) {
			return;
		}

		SiteNavigationMenuItem siteNavigationMenuItem =
			_siteNavigationMenuItemService.addSiteNavigationMenuItem(
				null, groupId, siteNavigationMenuId,
				parentSiteNavigationMenuItemId, siteNavigationMenuItemType,
				UnicodePropertiesBuilder.create(
					true
				).put(
					"className", jsonObject.getString("className")
				).put(
					"classNameId", jsonObject.getString("classNameId")
				).put(
					"classPK", jsonObject.getString("classPK")
				).put(
					"classTypeId", jsonObject.getString("classTypeId")
				).put(
					"title", jsonObject.getString("title")
				).put(
					"type", jsonObject.getString("type")
				).buildString(),
				serviceContext);

		if (order >= 0) {
			_siteNavigationMenuItemService.updateSiteNavigationMenuItem(
				siteNavigationMenuItem.getSiteNavigationMenuItemId(),
				parentSiteNavigationMenuItemId, order);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddMultipleDisplayPageTypeSiteNavigationMenuItemMVCActionCommand.class);

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private SiteNavigationMenuItemService _siteNavigationMenuItemService;

	@Reference
	private SiteNavigationMenuItemTypeRegistry
		_siteNavigationMenuItemTypeRegistry;

}