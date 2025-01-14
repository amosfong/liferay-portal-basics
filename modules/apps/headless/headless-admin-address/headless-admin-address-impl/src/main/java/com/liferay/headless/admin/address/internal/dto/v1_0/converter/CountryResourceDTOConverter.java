/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.address.internal.dto.v1_0.converter;

import com.liferay.headless.admin.address.dto.v1_0.Country;
import com.liferay.headless.admin.address.dto.v1_0.Region;
import com.liferay.headless.admin.address.internal.dto.v1_0.converter.constants.DTOConverterConstants;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	property = "dto.class.name=com.liferay.portal.kernel.model.Country",
	service = DTOConverter.class
)
public class CountryResourceDTOConverter
	implements DTOConverter<com.liferay.portal.kernel.model.Country, Country> {

	@Override
	public String getContentType() {
		return Country.class.getSimpleName();
	}

	@Override
	public com.liferay.portal.kernel.model.Country getObject(
			String externalReferenceCode)
		throws Exception {

		return _countryService.getCountry(
			GetterUtil.getLong(externalReferenceCode));
	}

	@Override
	public Country toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.portal.kernel.model.Country serviceBuilderCountry)
		throws Exception {

		return new Country() {
			{
				setA2(serviceBuilderCountry::getA2);
				setA3(serviceBuilderCountry::getA3);
				setActive(serviceBuilderCountry::isActive);
				setBillingAllowed(serviceBuilderCountry::isBillingAllowed);
				setGroupFilterEnabled(
					serviceBuilderCountry::isGroupFilterEnabled);
				setId(serviceBuilderCountry::getCountryId);
				setIdd(
					() -> {
						if (Validator.isNull(serviceBuilderCountry.getIdd())) {
							return null;
						}

						return Integer.valueOf(serviceBuilderCountry.getIdd());
					});
				setName(serviceBuilderCountry::getName);
				setNumber(
					() -> Integer.valueOf(serviceBuilderCountry.getNumber()));
				setPosition(serviceBuilderCountry::getPosition);
				setRegions(
					() -> TransformUtil.transformToArray(
						_regionService.getRegions(
							serviceBuilderCountry.getCountryId()),
						serviceBuilderRegion ->
							_regionResourceDTOConverter.toDTO(
								serviceBuilderRegion),
						Region.class));
				setShippingAllowed(serviceBuilderCountry::isShippingAllowed);
				setSubjectToVAT(serviceBuilderCountry::isSubjectToVAT);
				setTitle_i18n(serviceBuilderCountry::getLanguageIdToTitleMap);
				setZipRequired(serviceBuilderCountry::isZipRequired);
			}
		};
	}

	@Reference
	private CountryService _countryService;

	@Reference(target = DTOConverterConstants.REGION_RESOURCE_DTO_CONVERTER)
	private DTOConverter<com.liferay.portal.kernel.model.Region, Region>
		_regionResourceDTOConverter;

	@Reference
	private RegionService _regionService;

}