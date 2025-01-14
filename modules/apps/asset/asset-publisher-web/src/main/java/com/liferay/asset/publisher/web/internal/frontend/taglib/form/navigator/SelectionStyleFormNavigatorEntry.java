/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.internal.frontend.taglib.form.navigator;

import com.liferay.asset.publisher.constants.AssetPublisherConstants;
import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.asset.publisher.web.internal.configuration.AssetPublisherWebConfiguration;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherCustomizer;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherCustomizerRegistry;
import com.liferay.frontend.taglib.form.navigator.FormNavigatorEntry;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	configurationPid = "com.liferay.asset.publisher.web.internal.configuration.AssetPublisherWebConfiguration",
	property = "form.navigator.entry.order:Integer=600",
	service = FormNavigatorEntry.class
)
public class SelectionStyleFormNavigatorEntry
	extends BaseConfigurationFormNavigatorEntry {

	@Override
	public String getCategoryKey() {
		return AssetPublisherConstants.CATEGORY_KEY_ASSET_SELECTION;
	}

	@Override
	public String getKey() {
		return "asset-selection";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public boolean isVisible(User user, Object object) {
		if (!FeatureFlagManagerUtil.isEnabled("LPD-39304")) {
			return false;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		HttpServletRequest httpServletRequest = serviceContext.getRequest();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		Portlet portlet = _portletLocalService.getPortletById(
			themeDisplay.getCompanyId(), portletDisplay.getPortletResource());

		AssetPublisherCustomizer assetPublisherCustomizer =
			_assetPublisherCustomizerRegistry.getAssetPublisherCustomizer(
				portlet.getRootPortletId());

		if (assetPublisherCustomizer.isSelectionStyleEnabled(
				httpServletRequest)) {

			return true;
		}

		return false;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_assetPublisherCustomizerRegistry =
			new AssetPublisherCustomizerRegistry(
				_assetPublisherHelper,
				ConfigurableUtil.createConfigurable(
					AssetPublisherWebConfiguration.class, properties));
	}

	@Override
	protected String getJspPath() {
		return "/configuration/selection_style.jsp";
	}

	private volatile AssetPublisherCustomizerRegistry
		_assetPublisherCustomizerRegistry;

	@Reference
	private AssetPublisherHelper _assetPublisherHelper;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.publisher.web)"
	)
	private ServletContext _servletContext;

}