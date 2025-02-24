/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.internal.search.spi.model.result.contributor;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

/**
 * @author Alessio Antonio Rendina
 */
public class COREntryModelVisibilityContributor
	implements ModelVisibilityContributor {

	public COREntryModelVisibilityContributor(
		COREntryLocalService corEntryLocalService) {

		_corEntryLocalService = corEntryLocalService;
	}

	@Override
	public boolean isVisible(long classPK, int status) {
		try {
			COREntry corEntry = _corEntryLocalService.getCOREntry(classPK);

			return isVisible(corEntry.getStatus(), status);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to check visibility for commerce order rule entry",
					portalException);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		COREntryModelVisibilityContributor.class);

	private final COREntryLocalService _corEntryLocalService;

}