/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingMethodImpl extends CommerceShippingMethodBaseImpl {

	@Override
	public String getImageURL(ThemeDisplay themeDisplay) {
		if (getImageId() <= 0) {
			return null;
		}

		return StringBundler.concat(
			themeDisplay.getPathImage(), "/shipping/method?img_id=",
			getImageId(), "&t=",
			WebServerServletTokenUtil.getToken(getImageId()));
	}

	@Override
	public UnicodeProperties getTypeSettingsUnicodeProperties() {
		if (_typeSettingsUnicodeProperties == null) {
			_typeSettingsUnicodeProperties = UnicodePropertiesBuilder.create(
				true
			).fastLoad(
				getTypeSettings()
			).build();
		}

		return _typeSettingsUnicodeProperties;
	}

	@Override
	public void setTypeSettingsUnicodeProperties(
		UnicodeProperties typeSettingsUnicodeProperties) {

		_typeSettingsUnicodeProperties = typeSettingsUnicodeProperties;

		if (_typeSettingsUnicodeProperties == null) {
			_typeSettingsUnicodeProperties = new UnicodeProperties();
		}

		super.setTypeSettings(_typeSettingsUnicodeProperties.toString());
	}

	private UnicodeProperties _typeSettingsUnicodeProperties;

}