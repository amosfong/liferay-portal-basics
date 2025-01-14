/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.search.spi.model.result.contributor;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

/**
 * @author Luan Maoski
 */
public class BlogsEntryModelVisibilityContributor
	implements ModelVisibilityContributor {

	public BlogsEntryModelVisibilityContributor(
		BlogsEntryLocalService blogsEntryLocalService) {

		_blogsEntryLocalService = blogsEntryLocalService;
	}

	@Override
	public boolean isVisible(long classPK, int status) {
		try {
			BlogsEntry entry = _blogsEntryLocalService.getEntry(classPK);

			return isVisible(entry.getStatus(), status);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to check visibility for blogs entry ",
					portalException);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlogsEntryModelVisibilityContributor.class);

	private final BlogsEntryLocalService _blogsEntryLocalService;

}