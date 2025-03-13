/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public class FaviconUtil {

	public static String getFaviconTitle(
		Layout layout, Locale locale) {

		if (layout.getFaviconFileEntryId() > 0) {
			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
					layout.getFaviconFileEntryId());

				return fileEntry.getTitle();
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}

		if (layout.getMasterLayoutPlid() > 0) {
			Layout masterLayout = LayoutLocalServiceUtil.fetchLayout(
				layout.getMasterLayoutPlid());

			if (masterLayout != null) {
				if (masterLayout.getFaviconFileEntryId() > 0) {
					return LanguageUtil.get(locale, "favicon-from-master");
				}
			}
		}

		return getFaviconTitle(layout.getLayoutSet(), locale);
	}

	public static String getFaviconTitle(LayoutSet layoutSet, Locale locale) {
		if (layoutSet.getFaviconFileEntryId() > 0) {
			try {
				Group group = layoutSet.getGroup();

				return LanguageUtil.format(
					locale, "favicon-from-x",
					group.getLayoutRootNodeName(
						layoutSet.isPrivateLayout(), locale));
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}

		return LanguageUtil.get(locale, "favicon-from-theme");
	}

	public static String getFaviconURL(Layout layout) {
		String faviconURL = layout.getFaviconURL();

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		if (layout.getMasterLayoutPlid() > 0) {
			Layout masterLayout = LayoutLocalServiceUtil.fetchLayout(
				layout.getMasterLayoutPlid());

			if (masterLayout != null) {
				if (Validator.isNotNull(faviconURL)) {
					return faviconURL;
				}

				faviconURL = masterLayout.getFaviconURL();

				if (Validator.isNotNull(faviconURL)) {
					return faviconURL;
				}
			}
		}

		return getFaviconURL(layout.getLayoutSet());
	}

	public static String getFaviconURL(LayoutSet layoutSet) {
		return  layoutSet.getFaviconURL();
	}

	private static final Log _log = LogFactoryUtil.getLog(FaviconUtil.class);

}