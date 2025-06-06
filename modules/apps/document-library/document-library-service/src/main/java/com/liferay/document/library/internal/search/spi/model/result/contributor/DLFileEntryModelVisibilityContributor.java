/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.search.spi.model.result.contributor;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

/**
 * @author Michael C. Han
 */
public class DLFileEntryModelVisibilityContributor
	implements ModelVisibilityContributor {

	public DLFileEntryModelVisibilityContributor(
		DLAppLocalService dlAppLocalService) {

		_dlAppLocalService = dlAppLocalService;
	}

	@Override
	public boolean isVisible(long classPK, int status) {
		FileVersion fileVersion = _getFileVersion(classPK);

		if (fileVersion == null) {
			return false;
		}

		return isVisible(fileVersion.getStatus(), status);
	}

	private FileVersion _getFileVersion(long classPK) {
		try {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(classPK);

			return fileEntry.getFileVersion();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryModelVisibilityContributor.class);

	private final DLAppLocalService _dlAppLocalService;

}