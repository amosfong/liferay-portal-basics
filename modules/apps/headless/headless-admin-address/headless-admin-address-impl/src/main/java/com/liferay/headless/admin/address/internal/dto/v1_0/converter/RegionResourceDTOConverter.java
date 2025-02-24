/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.address.internal.dto.v1_0.converter;

import com.liferay.headless.admin.address.dto.v1_0.Region;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "dto.class.name=com.liferay.portal.kernel.model.Region",
	service = DTOConverter.class
)
public class RegionResourceDTOConverter
	implements DTOConverter<com.liferay.portal.kernel.model.Region, Region> {

	@Override
	public String getContentType() {
		return Region.class.getSimpleName();
	}

	@Override
	public Region toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.portal.kernel.model.Region serviceBuilderRegion)
		throws Exception {

		return new Region() {
			{
				setActive(serviceBuilderRegion::isActive);
				setCountryId(serviceBuilderRegion::getCountryId);
				setId(serviceBuilderRegion::getRegionId);
				setName(serviceBuilderRegion::getName);
				setPosition(serviceBuilderRegion::getPosition);
				setRegionCode(serviceBuilderRegion::getRegionCode);
				setTitle_i18n(serviceBuilderRegion::getLanguageIdToTitleMap);
			}
		};
	}

}