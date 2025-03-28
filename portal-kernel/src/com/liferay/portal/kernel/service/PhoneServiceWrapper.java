/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link PhoneService}.
 *
 * @author Brian Wing Shun Chan
 * @see PhoneService
 * @generated
 */
public class PhoneServiceWrapper
	implements PhoneService, ServiceWrapper<PhoneService> {

	public PhoneServiceWrapper() {
		this(null);
	}

	public PhoneServiceWrapper(PhoneService phoneService) {
		_phoneService = phoneService;
	}

	@Override
	public com.liferay.portal.kernel.model.Phone addPhone(
			java.lang.String externalReferenceCode, java.lang.String className,
			long classPK, java.lang.String number, java.lang.String extension,
			long typeId, boolean primary, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.addPhone(
			externalReferenceCode, className, classPK, number, extension,
			typeId, primary, serviceContext);
	}

	@Override
	public void deletePhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_phoneService.deletePhone(phoneId);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone
			fetchPhoneByExternalReferenceCode(
				java.lang.String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.fetchPhoneByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _phoneService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.Phone getPhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.getPhone(phoneId);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone
			getPhoneByExternalReferenceCode(
				java.lang.String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.getPhoneByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Phone> getPhones(
			java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.getPhones(className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone updatePhone(
			java.lang.String externalReferenceCode, long phoneId,
			java.lang.String number, java.lang.String extension, long typeId,
			boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _phoneService.updatePhone(
			externalReferenceCode, phoneId, number, extension, typeId, primary);
	}

	@Override
	public PhoneService getWrappedService() {
		return _phoneService;
	}

	@Override
	public void setWrappedService(PhoneService phoneService) {
		_phoneService = phoneService;
	}

	private PhoneService _phoneService;

}