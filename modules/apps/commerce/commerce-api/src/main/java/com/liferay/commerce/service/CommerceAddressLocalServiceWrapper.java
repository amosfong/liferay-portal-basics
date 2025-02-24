/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAddressLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddressLocalService
 * @deprecated As of Cavanaugh (7.4.x)
 * @generated
 */
@Deprecated
public class CommerceAddressLocalServiceWrapper
	implements CommerceAddressLocalService,
			   ServiceWrapper<CommerceAddressLocalService> {

	public CommerceAddressLocalServiceWrapper() {
		this(null);
	}

	public CommerceAddressLocalServiceWrapper(
		CommerceAddressLocalService commerceAddressLocalService) {

		_commerceAddressLocalService = commerceAddressLocalService;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), defaultBilling/Shipping exist on Account Entity. Pass type.
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceAddress addCommerceAddress(
			String className, long classPK, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, String phoneNumber,
			boolean defaultBilling, boolean defaultShipping,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.addCommerceAddress(
			className, classPK, name, description, street1, street2, street3,
			city, zip, regionId, countryId, phoneNumber, defaultBilling,
			defaultShipping, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress addCommerceAddress(
			String className, long classPK, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, String phoneNumber,
			int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.addCommerceAddress(
			className, classPK, name, description, street1, street2, street3,
			city, zip, regionId, countryId, phoneNumber, type, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress addCommerceAddress(
			String externalReferenceCode, String className, long classPK,
			String name, String description, String street1, String street2,
			String street3, String city, String zip, long regionId,
			long countryId, String phoneNumber, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.addCommerceAddress(
			externalReferenceCode, className, classPK, name, description,
			street1, street2, street3, city, zip, regionId, countryId,
			phoneNumber, type, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress copyCommerceAddress(
			long sourceCommerceAddressId, String className, long classPK,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.copyCommerceAddress(
			sourceCommerceAddressId, className, classPK, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress createCommerceAddress(
		long commerceAddressId) {

		return _commerceAddressLocalService.createCommerceAddress(
			commerceAddressId);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress deleteCommerceAddress(
			com.liferay.commerce.model.CommerceAddress commerceAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.deleteCommerceAddress(
			commerceAddress);
	}

	@Override
	public void deleteCommerceAddresses(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAddressLocalService.deleteCommerceAddresses(
			className, classPK);
	}

	@Override
	public void deleteCountryCommerceAddresses(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAddressLocalService.deleteCountryCommerceAddresses(countryId);
	}

	@Override
	public void deleteRegionCommerceAddresses(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAddressLocalService.deleteRegionCommerceAddresses(regionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress fetchCommerceAddress(
		long commerceAddressId) {

		return _commerceAddressLocalService.fetchCommerceAddress(
			commerceAddressId);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress
		fetchCommerceAddressByExternalReferenceCode(
			String externalReferenceCode, long companyId) {

		return _commerceAddressLocalService.
			fetchCommerceAddressByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress geolocateCommerceAddress(
			long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.geolocateCommerceAddress(
			commerceAddressId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getBillingAndShippingCommerceAddresses(
			long companyId, String className, long classPK) {

		return _commerceAddressLocalService.
			getBillingAndShippingCommerceAddresses(
				companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getBillingCommerceAddresses(
				long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getBillingCommerceAddresses(
			companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getBillingCommerceAddresses(
			long channelId, String className, long classPK, int start,
			int end) {

		return _commerceAddressLocalService.getBillingCommerceAddresses(
			channelId, className, classPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getBillingCommerceAddresses(
				long companyId, String className, long classPK,
				long commerceChannelId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getBillingCommerceAddresses(
			companyId, className, classPK, commerceChannelId, keywords, start,
			end, sort);
	}

	@Override
	public int getBillingCommerceAddressesCount(
		long channelId, String className, long classPK, int start, int end) {

		return _commerceAddressLocalService.getBillingCommerceAddressesCount(
			channelId, className, classPK, start, end);
	}

	@Override
	public int getBillingCommerceAddressesCount(
			long companyId, String className, long classPK,
			long commerceChannelId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getBillingCommerceAddressesCount(
			companyId, className, classPK, commerceChannelId, keywords);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress getCommerceAddress(
			long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getCommerceAddress(
			commerceAddressId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getCommerceAddresses(long groupId, String className, long classPK) {

		return _commerceAddressLocalService.getCommerceAddresses(
			groupId, className, classPK);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getCommerceAddresses(
			long groupId, String className, long classPK, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceAddress>
					orderByComparator) {

		return _commerceAddressLocalService.getCommerceAddresses(
			groupId, className, classPK, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getCommerceAddresses(
			String className, long classPK, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceAddress>
					orderByComparator) {

		return _commerceAddressLocalService.getCommerceAddresses(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getCommerceAddressesByCompanyId(
			long companyId, String className, long classPK) {

		return _commerceAddressLocalService.getCommerceAddressesByCompanyId(
			companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getCommerceAddressesByCompanyId(
			long companyId, String className, long classPK, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceAddress>
					orderByComparator) {

		return _commerceAddressLocalService.getCommerceAddressesByCompanyId(
			companyId, className, classPK, start, end, orderByComparator);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public int getCommerceAddressesCount(
		long groupId, String className, long classPK) {

		return _commerceAddressLocalService.getCommerceAddressesCount(
			groupId, className, classPK);
	}

	@Override
	public int getCommerceAddressesCount(String className, long classPK) {
		return _commerceAddressLocalService.getCommerceAddressesCount(
			className, classPK);
	}

	@Override
	public int getCommerceAddressesCountByCompanyId(
		long companyId, String className, long classPK) {

		return _commerceAddressLocalService.
			getCommerceAddressesCountByCompanyId(companyId, className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAddressLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getShippingCommerceAddresses(
				long companyId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getShippingCommerceAddresses(
			companyId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
		getShippingCommerceAddresses(
			long channelId, String className, long classPK, int start,
			int end) {

		return _commerceAddressLocalService.getShippingCommerceAddresses(
			channelId, className, classPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceAddress>
			getShippingCommerceAddresses(
				long companyId, String className, long classPK,
				long commerceChannelId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getShippingCommerceAddresses(
			companyId, className, classPK, commerceChannelId, keywords, start,
			end, sort);
	}

	@Override
	public int getShippingCommerceAddressesCount(
			long companyId, String className, long classPK,
			long commerceChannelId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.getShippingCommerceAddressesCount(
			companyId, className, classPK, commerceChannelId, keywords);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company. Don't need to pass groupId
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceAddress> searchCommerceAddresses(
				long companyId, long groupId, String className, long classPK,
				String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.searchCommerceAddresses(
			companyId, groupId, className, classPK, keywords, start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceAddress> searchCommerceAddresses(
				long companyId, String className, long classPK, String keywords,
				int start, int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.searchCommerceAddresses(
			companyId, className, classPK, keywords, start, end, sort);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), defaultBilling/Shipping exist on Account Entity. Pass type.
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.model.CommerceAddress updateCommerceAddress(
			long commerceAddressId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, String phoneNumber,
			boolean defaultBilling, boolean defaultShipping,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.updateCommerceAddress(
			commerceAddressId, name, description, street1, street2, street3,
			city, zip, regionId, countryId, phoneNumber, defaultBilling,
			defaultShipping, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceAddress updateCommerceAddress(
			long commerceAddressId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, String phoneNumber,
			int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAddressLocalService.updateCommerceAddress(
			commerceAddressId, name, description, street1, street2, street3,
			city, zip, regionId, countryId, phoneNumber, type, serviceContext);
	}

	@Override
	public CommerceAddressLocalService getWrappedService() {
		return _commerceAddressLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceAddressLocalService commerceAddressLocalService) {

		_commerceAddressLocalService = commerceAddressLocalService;
	}

	private CommerceAddressLocalService _commerceAddressLocalService;

}