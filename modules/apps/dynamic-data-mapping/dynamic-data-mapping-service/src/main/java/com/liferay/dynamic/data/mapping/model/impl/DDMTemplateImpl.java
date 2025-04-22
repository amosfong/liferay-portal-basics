/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.model.cache.CacheField;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMTemplateImpl extends DDMTemplateBaseImpl {

	@Override
	public String getDefaultLanguageId() {
		Document document = null;

		try {
			document = SAXReaderUtil.read(getName());

			if (document != null) {
				Element rootElement = document.getRootElement();

				return rootElement.attributeValue("default-locale");
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		Locale locale = LocaleUtil.getSiteDefault();

		return locale.toString();
	}

	@Override
	public DDMTemplateVersion getLatestTemplateVersion()
		throws PortalException {

		return DDMTemplateVersionLocalServiceUtil.getLatestTemplateVersion(
			getTemplateId());
	}

	@Override
	public String getResourceClassName() {
		if (_resourceClassName == null) {
			_resourceClassName = PortalUtil.getClassName(
				getResourceClassNameId());
		}

		return _resourceClassName;
	}

	@Override
	public String getSmallImageType() throws PortalException {
		if ((_smallImageType == null) && isSmallImage()) {
			Image smallImage = ImageLocalServiceUtil.getImage(
				getSmallImageId());

			_smallImageType = smallImage.getType();
		}

		return _smallImageType;
	}

	@Override
	public String getTemplateImageURL(ThemeDisplay themeDisplay) {
		if (!isSmallImage()) {
			return null;
		}

		if (Validator.isNotNull(getSmallImageURL())) {
			return getSmallImageURL();
		}

		return StringBundler.concat(
			themeDisplay.getPathImage(), "/template?img_id=", getSmallImageId(),
			"&t=", WebServerServletTokenUtil.getToken(getSmallImageId()));
	}

	@Override
	public DDMTemplateVersion getTemplateVersion() throws PortalException {
		return DDMTemplateVersionLocalServiceUtil.getTemplateVersion(
			getTemplateId(), getVersion());
	}

	@Override
	public void setResourceClassName(String resourceClassName) {
		_resourceClassName = resourceClassName;
	}

	@Override
	public void setResourceClassNameId(long resourceClassNameId) {
		super.setResourceClassNameId(resourceClassNameId);

		_resourceClassName = null;
	}

	@Override
	public void setSmallImageType(String smallImageType) {
		_smallImageType = smallImageType;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMTemplateImpl.class);

	@CacheField(propagateToInterface = true)
	private String _resourceClassName;

	private String _smallImageType;

}