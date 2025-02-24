/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.internal.dto.v1_0.converter;

import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.headless.admin.user.dto.v1_0.EmailAddress;
import com.liferay.headless.admin.user.dto.v1_0.HoursAvailable;
import com.liferay.headless.admin.user.dto.v1_0.Location;
import com.liferay.headless.admin.user.dto.v1_0.Organization;
import com.liferay.headless.admin.user.dto.v1_0.OrganizationContactInformation;
import com.liferay.headless.admin.user.dto.v1_0.Phone;
import com.liferay.headless.admin.user.dto.v1_0.PostalAddress;
import com.liferay.headless.admin.user.dto.v1_0.Service;
import com.liferay.headless.admin.user.dto.v1_0.WebUrl;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.EmailAddressUtil;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.PhoneUtil;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.PostalAddressUtil;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.WebUrlUtil;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.EmailAddressService;
import com.liferay.portal.kernel.service.OrgLaborService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.PhoneService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.WebsiteService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Javier Gamarra
 */
@Component(
	property = {
		"application.name=Liferay.Headless.Admin.User",
		"dto.class.name=com.liferay.portal.kernel.model.Organization",
		"version=v1.0"
	},
	service = DTOConverter.class
)
public class OrganizationResourceDTOConverter
	implements DTOConverter
		<com.liferay.portal.kernel.model.Organization, Organization> {

	@Override
	public String getContentType() {
		return Organization.class.getSimpleName();
	}

	@Override
	public com.liferay.portal.kernel.model.Organization getObject(
			String externalReferenceCode)
		throws Exception {

		com.liferay.portal.kernel.model.Organization organization =
			_organizationLocalService.fetchOrganizationByExternalReferenceCode(
				externalReferenceCode, CompanyThreadLocal.getCompanyId());

		if (organization == null) {
			organization = _organizationService.getOrganization(
				GetterUtil.getLong(externalReferenceCode));
		}

		return organization;
	}

	@Override
	public Organization toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.portal.kernel.model.Organization organization)
		throws Exception {

		if (organization == null) {
			return null;
		}

		Country country = _countryService.fetchCountry(
			organization.getCountryId());
		OrganizationResourceDTOConverter organizationResourceDTOConverter =
			this;
		Region region = _regionService.fetchRegion(organization.getRegionId());

		return new Organization() {
			{
				setActions(dtoConverterContext::getActions);
				setComment(organization::getComments);
				setCustomFields(
					() -> CustomFieldsUtil.toCustomFields(
						dtoConverterContext.isAcceptAllLanguages(),
						com.liferay.portal.kernel.model.Organization.class.
							getName(),
						organization.getOrganizationId(),
						organization.getCompanyId(),
						dtoConverterContext.getLocale()));
				setDateCreated(organization::getCreateDate);
				setDateModified(organization::getModifiedDate);
				setExternalReferenceCode(
					organization::getExternalReferenceCode);
				setId(() -> String.valueOf(organization.getOrganizationId()));
				setImage(
					() -> {
						if (organization.getLogoId() <= 0) {
							return null;
						}

						return organization.getLogoURL();
					});
				setImageId(organization::getLogoId);
				setKeywords(
					() -> ListUtil.toArray(
						_assetTagLocalService.getTags(
							organization.getModelClassName(),
							organization.getOrganizationId()),
						AssetTag.NAME_ACCESSOR));
				setLocation(
					() -> new Location() {
						{
							setAddressCountry(
								() -> {
									if (country == null) {
										return null;
									}

									return country.getName(
										dtoConverterContext.getLocale());
								});
							setAddressCountry_i18n(
								() -> {
									if (!dtoConverterContext.
											isAcceptAllLanguages() ||
										(country == null)) {

										return null;
									}

									Map<String, String> countryNames =
										new HashMap<>();

									for (Locale locale :
											_language.
												getCompanyAvailableLocales(
													organization.
														getCompanyId())) {

										countryNames.put(
											LocaleUtil.toBCP47LanguageId(
												locale),
											country.getName());
									}

									return countryNames;
								});
							setAddressCountryCode(
								() -> {
									if (country == null) {
										return null;
									}

									return country.getA2();
								});
							setAddressRegion(
								() -> {
									if (region == null) {
										return null;
									}

									return region.getName();
								});
							setAddressRegionCode(
								() -> {
									if (region == null) {
										return null;
									}

									return region.getRegionCode();
								});
						}
					});
				setName(organization::getName);
				setNumberOfAccounts(
					() ->
						_accountEntryOrganizationRelLocalService.
							getAccountEntryOrganizationRelsCountByOrganizationId(
								organization.getOrganizationId()));
				setNumberOfOrganizations(
					() -> _organizationService.getOrganizationsCount(
						organization.getCompanyId(),
						organization.getOrganizationId()));
				setNumberOfUsers(
					() -> _userService.getOrganizationUsersCount(
						organization.getOrganizationId(),
						WorkflowConstants.STATUS_ANY));
				setOrganizationContactInformation(
					() -> new OrganizationContactInformation() {
						{
							setEmailAddresses(
								() -> TransformUtil.transformToArray(
									_emailAddressService.getEmailAddresses(
										organization.getModelClassName(),
										organization.getOrganizationId()),
									EmailAddressUtil::toEmailAddress,
									EmailAddress.class));
							setPostalAddresses(
								() -> TransformUtil.transformToArray(
									organization.getAddresses(),
									address ->
										PostalAddressUtil.toPostalAddress(
											dtoConverterContext.
												isAcceptAllLanguages(),
											address,
											organization.getCompanyId(),
											dtoConverterContext.getLocale()),
									PostalAddress.class));
							setTelephones(
								() -> TransformUtil.transformToArray(
									_phoneService.getPhones(
										organization.getModelClassName(),
										organization.getOrganizationId()),
									PhoneUtil::toPhone, Phone.class));
							setWebUrls(
								() -> TransformUtil.transformToArray(
									_websiteService.getWebsites(
										organization.getModelClassName(),
										organization.getOrganizationId()),
									WebUrlUtil::toWebUrl, WebUrl.class));
						}
					});
				setParentOrganization(
					() -> organizationResourceDTOConverter.toDTO(
						dtoConverterContext,
						organization.getParentOrganization()));
				setServices(
					() -> TransformUtil.transformToArray(
						_orgLaborService.getOrgLabors(
							organization.getOrganizationId()),
						OrganizationResourceDTOConverter.this::_toService,
						Service.class));
				setTreePath(organization::getTreePath);
			}
		};
	}

	private HoursAvailable _createHoursAvailable(
		int closeHour, String day, int openHour) {

		return new HoursAvailable() {
			{
				setCloses(() -> _formatHour(closeHour));
				setDayOfWeek(() -> day);
				setOpens(() -> _formatHour(openHour));
			}
		};
	}

	private String _formatHour(int hour) {
		if (hour == -1) {
			return null;
		}

		DecimalFormat decimalFormat = new DecimalFormat("00,00") {
			{
				setDecimalFormatSymbols(
					new DecimalFormatSymbols() {
						{
							setGroupingSeparator(':');
						}
					});
				setGroupingSize(2);
			}
		};

		return decimalFormat.format(hour);
	}

	private Service _toService(OrgLabor orgLabor) throws Exception {
		ListType listType = orgLabor.getListType();

		return new Service() {
			{
				setHoursAvailable(
					() -> new HoursAvailable[] {
						_createHoursAvailable(
							orgLabor.getSunClose(), "Sunday",
							orgLabor.getSunOpen()),
						_createHoursAvailable(
							orgLabor.getMonClose(), "Monday",
							orgLabor.getMonOpen()),
						_createHoursAvailable(
							orgLabor.getTueClose(), "Tuesday",
							orgLabor.getTueOpen()),
						_createHoursAvailable(
							orgLabor.getWedClose(), "Wednesday",
							orgLabor.getWedOpen()),
						_createHoursAvailable(
							orgLabor.getThuClose(), "Thursday",
							orgLabor.getThuOpen()),
						_createHoursAvailable(
							orgLabor.getFriClose(), "Friday",
							orgLabor.getFriOpen()),
						_createHoursAvailable(
							orgLabor.getSatClose(), "Saturday",
							orgLabor.getSatOpen())
					});
				setServiceType(listType::getName);
			}
		};
	}

	@Reference
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private CountryService _countryService;

	@Reference
	private EmailAddressService _emailAddressService;

	@Reference
	private Language _language;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private OrganizationService _organizationService;

	@Reference
	private OrgLaborService _orgLaborService;

	@Reference
	private PhoneService _phoneService;

	@Reference
	private RegionService _regionService;

	@Reference
	private UserService _userService;

	@Reference
	private WebsiteService _websiteService;

}