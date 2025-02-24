/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.search.spi.model.result.contributor;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderTypeModelVisibilityContributor
	implements ModelVisibilityContributor {

	public CommerceOrderTypeModelVisibilityContributor(
		CommerceOrderTypeLocalService commerceOrderTypeLocalService) {

		_commerceOrderTypeLocalService = commerceOrderTypeLocalService;
	}

	@Override
	public boolean isVisible(long classPK, int status) {
		try {
			CommerceOrderType commerceOrderType =
				_commerceOrderTypeLocalService.getCommerceOrderType(classPK);

			return isVisible(commerceOrderType.getStatus(), status);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to check visibility for commerce order type",
					portalException);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderTypeModelVisibilityContributor.class);

	private final CommerceOrderTypeLocalService _commerceOrderTypeLocalService;

}