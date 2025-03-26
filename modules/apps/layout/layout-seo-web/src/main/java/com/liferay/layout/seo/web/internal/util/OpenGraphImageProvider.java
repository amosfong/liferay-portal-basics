/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.web.internal.util;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.service.DDMFieldLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.model.LayoutSEOSite;
import com.liferay.layout.seo.service.LayoutSEOSiteLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Locale;

/**
 * @author Alejandro Tardín
 */
public class OpenGraphImageProvider {

	public OpenGraphImageProvider(
		DDMFieldLocalService ddmFieldLocalService,
		DDMStructureLocalService ddmStructureLocalService,
		DLAppLocalService dlAppLocalService,
		DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService,
		DLURLHelper dlurlHelper,
		LayoutSEOSiteLocalService layoutSEOSiteLocalService,
		Portal portal) {

		_dlAppLocalService = dlAppLocalService;
		_dlurlHelper = dlurlHelper;
		_layoutSEOSiteLocalService = layoutSEOSiteLocalService;

		_fileEntryMetadataOpenGraphTagsProvider =
			new FileEntryMetadataOpenGraphTagsProvider(
				ddmFieldLocalService, ddmStructureLocalService,
				dlFileEntryMetadataLocalService, portal);
	}

	public OpenGraphImage getOpenGraphImage(
		Layout layout,
		LayoutSEOEntry layoutSEOEntry, ThemeDisplay themeDisplay) {

		return _getFileEntryOpenGraphImage(
			layout, layoutSEOEntry, themeDisplay);
	}

	public interface OpenGraphImage {

		public String getAlt();

		public Iterable<KeyValuePair> getMetadataTagKeyValuePairs();

		public String getMimeType();

		public String getURL();

	}

	private String _getAbsoluteURL(ThemeDisplay themeDisplay, String url) {
		if (url.startsWith("http")) {
			return url;
		}

		return themeDisplay.getPortalURL() + url;
	}

	private OpenGraphImage _getFileEntryOpenGraphImage(
		Layout layout,
		LayoutSEOEntry layoutSEOEntry, ThemeDisplay themeDisplay) {

		try {
			long openGraphImageFileEntryId = _getOpenGraphImageFileEntryId(
				layout, layoutSEOEntry);

			if (openGraphImageFileEntryId == 0) {
				return null;
			}

			FileEntry fileEntry = _dlAppLocalService.getFileEntry(
				openGraphImageFileEntryId);

			if ((fileEntry == null) || fileEntry.isInTrash()) {
				return null;
			}

			Iterable<KeyValuePair> fileEntryMetadataOpenGraphTagKeyValuePairs =
				_fileEntryMetadataOpenGraphTagsProvider.
					getFileEntryMetadataOpenGraphTagKeyValuePairs(fileEntry);

			String imagePreviewURL = _dlurlHelper.getImagePreviewURL(
				fileEntry, themeDisplay);

			return new OpenGraphImage() {

				@Override
				public String getAlt() {
					return _getImageAltTagValue(
						layout, layoutSEOEntry,
						themeDisplay.getLocale());
				}

				@Override
				public Iterable<KeyValuePair> getMetadataTagKeyValuePairs() {
					return fileEntryMetadataOpenGraphTagKeyValuePairs;
				}

				@Override
				public String getMimeType() {
					return fileEntry.getMimeType();
				}

				@Override
				public String getURL() {
					return imagePreviewURL;
				}

			};
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return null;
	}

	private String _getImageAltTagValue(
		Layout layout,
		LayoutSEOEntry layoutSEOEntry, Locale locale) {

		if ((layoutSEOEntry != null) &&
			(layoutSEOEntry.getOpenGraphImageFileEntryId() > 0)) {

			return layoutSEOEntry.getOpenGraphImageAlt(locale);
		}

		LayoutSEOSite layoutSEOSite =
			_layoutSEOSiteLocalService.fetchLayoutSEOSiteByGroupId(
				layout.getGroupId());

		if ((layoutSEOSite != null) &&
			(layoutSEOSite.getOpenGraphImageFileEntryId() > 0)) {

			return layoutSEOSite.getOpenGraphImageAlt(locale);
		}

		return null;
	}

	private OpenGraphImage _getMappedOpenGraphImage(
		Layout layout,
		LayoutSEOEntry layoutSEOEntry, ThemeDisplay themeDisplay) {

		return null;
	}

	private long _getOpenGraphImageFileEntryId(
		Layout layout, LayoutSEOEntry layoutSEOEntry) {

		if ((layoutSEOEntry != null) &&
			(layoutSEOEntry.getOpenGraphImageFileEntryId() > 0)) {

			return layoutSEOEntry.getOpenGraphImageFileEntryId();
		}

		LayoutSEOSite layoutSEOSite =
			_layoutSEOSiteLocalService.fetchLayoutSEOSiteByGroupId(
				layout.getGroupId());

		if ((layoutSEOSite == null) ||
			(layoutSEOSite.getOpenGraphImageFileEntryId() == 0)) {

			return 0;
		}

		return layoutSEOSite.getOpenGraphImageFileEntryId();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenGraphImageProvider.class);

	private final DLAppLocalService _dlAppLocalService;
	private final DLURLHelper _dlurlHelper;
	private final FileEntryMetadataOpenGraphTagsProvider
		_fileEntryMetadataOpenGraphTagsProvider;
	private final LayoutSEOSiteLocalService _layoutSEOSiteLocalService;

}