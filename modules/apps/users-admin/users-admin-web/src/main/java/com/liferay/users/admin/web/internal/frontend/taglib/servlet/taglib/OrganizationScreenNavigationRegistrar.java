/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(service = {})
public class OrganizationScreenNavigationRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_registerService(
			ScreenNavigationCategory.class, 10,
			new OrganizationScreenNavigationCategory(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL));

		_registerService(
			ScreenNavigationCategory.class, 20,
			new OrganizationScreenNavigationCategory(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_CONTACT));

		_registerService(
			ScreenNavigationEntry.class, 10,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL
			).entryKey(
				"information"
			).jspPath(
				"/organization/information.jsp"
			).mvcActionCommandName(
				"/users_admin/edit_organization"
			).visibleBiFunction(
				(user, organization) -> true
			).build());

		_registerService(
			ScreenNavigationEntry.class, 20,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL
			).entryKey(
				"organization-site"
			).jspPath(
				"/organization/organization_site.jsp"
			).mvcActionCommandName(
				"/users_admin/update_organization_organization_site"
			).showControls(
				false
			).visibleBiFunction(
				(user, organization) -> {
					if (organization == null) {
						return false;
					}

					try {
						if (!GroupPermissionUtil.contains(
								PermissionThreadLocal.getPermissionChecker(),
								organization.getGroup(), ActionKeys.UPDATE)) {

							return false;
						}
					}
					catch (Exception exception) {
						if (_log.isDebugEnabled()) {
							_log.debug(exception);
						}

						return false;
					}

					return true;
				}
			).build());

		_registerService(
			ScreenNavigationEntry.class, 30,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL
			).entryKey(
				"security-questions"
			).jspPath(
				"/organization/reminder_queries.jsp"
			).mvcActionCommandName(
				"/users_admin/update_organization_reminder_queries"
			).build());

		_registerService(
			ScreenNavigationEntry.class, 10,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_CONTACT
			).entryKey(
				"addresses"
			).jspPath(
				"/organization/addresses.jsp"
			).mvcActionCommandName(
				"/users_admin/update_contact_information"
			).showControls(
				false
			).showTitle(
				false
			).build());

		_registerService(
			ScreenNavigationEntry.class, 20,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_CONTACT
			).entryKey(
				"contact-information"
			).jspPath(
				"/organization/contact_information.jsp"
			).mvcActionCommandName(
				"/users_admin/update_contact_information"
			).showControls(
				false
			).build());

		_registerService(
			ScreenNavigationEntry.class, 30,
			_getBuilder(
			).categoryKey(
				UserScreenNavigationEntryConstants.CATEGORY_KEY_CONTACT
			).entryKey(
				"opening-hours"
			).jspPath(
				"/organization/opening_hours.jsp"
			).mvcActionCommandName(
				"/users_admin/update_contact_information"
			).showControls(
				false
			).showTitle(
				false
			).build());
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistrations.forEach(ServiceRegistration::unregister);

		_serviceRegistrations.clear();
	}

	private OrganizationScreenNavigationEntry.Builder _getBuilder() {
		return OrganizationScreenNavigationEntry.builder(
		).jspRenderer(
			_jspRenderer
		).organizationService(
			_organizationService
		);
	}

	private <T> void _registerService(
		Class<T> clazz, int order, T serviceObject) {

		_serviceRegistrations.add(
			_bundleContext.registerService(
				clazz, serviceObject,
				HashMapDictionaryBuilder.<String, Object>put(
					"screen.navigation.category.order", order
				).put(
					"screen.navigation.entry.order", order
				).build()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationScreenNavigationRegistrar.class);

	private BundleContext _bundleContext;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private OrganizationService _organizationService;

	private final List<ServiceRegistration<?>> _serviceRegistrations =
		new ArrayList<>();

}