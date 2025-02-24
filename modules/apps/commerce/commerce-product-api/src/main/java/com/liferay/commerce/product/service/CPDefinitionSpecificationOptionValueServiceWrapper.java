/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDefinitionSpecificationOptionValueService}.
 *
 * @author Marco Leo
 * @see CPDefinitionSpecificationOptionValueService
 * @generated
 */
public class CPDefinitionSpecificationOptionValueServiceWrapper
	implements CPDefinitionSpecificationOptionValueService,
			   ServiceWrapper<CPDefinitionSpecificationOptionValueService> {

	public CPDefinitionSpecificationOptionValueServiceWrapper() {
		this(null);
	}

	public CPDefinitionSpecificationOptionValueServiceWrapper(
		CPDefinitionSpecificationOptionValueService
			cpDefinitionSpecificationOptionValueService) {

		_cpDefinitionSpecificationOptionValueService =
			cpDefinitionSpecificationOptionValueService;
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			addCPDefinitionSpecificationOptionValue(
				String externalReferenceCode, long cpDefinitionId,
				long cpSpecificationOptionId, long cpOptionCategoryId,
				double priority,
				java.util.Map<java.util.Locale, String> valueMap,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			addCPDefinitionSpecificationOptionValue(
				externalReferenceCode, cpDefinitionId, cpSpecificationOptionId,
				cpOptionCategoryId, priority, valueMap, serviceContext);
	}

	@Override
	public void deleteCPDefinitionSpecificationOptionValue(
			long cpDefinitionSpecificationOptionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionSpecificationOptionValueService.
			deleteCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValueId);
	}

	@Override
	public void deleteCPDefinitionSpecificationOptionValues(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDefinitionSpecificationOptionValueService.
			deleteCPDefinitionSpecificationOptionValues(cpDefinitionId);
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			fetchCPDefinitionSpecificationOptionValue(
				long cpDefinitionSpecificationOptionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			fetchCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValueId);
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			fetchCPDefinitionSpecificationOptionValueByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			fetchCPDefinitionSpecificationOptionValueByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			getCPDefinitionSpecificationOptionValue(
				long cpDefinitionSpecificationOptionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValueId);
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			getCPDefinitionSpecificationOptionValueByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValueByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public java.util.List<CPDefinitionSpecificationOptionValue>
			getCPDefinitionSpecificationOptionValues(
				long cpDefinitionId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<CPDefinitionSpecificationOptionValue> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValues(
				cpDefinitionId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<CPDefinitionSpecificationOptionValue>
			getCPDefinitionSpecificationOptionValues(
				long cpDefinitionId, long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValues(
				cpDefinitionId, cpOptionCategoryId);
	}

	@Override
	public int getCPDefinitionSpecificationOptionValuesCount(
			long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValuesCount(cpDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDefinitionSpecificationOptionValueService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CPDefinitionSpecificationOptionValue
			updateCPDefinitionSpecificationOptionValue(
				String externalReferenceCode,
				long cpDefinitionSpecificationOptionValueId,
				long cpOptionCategoryId, String key, double priority,
				java.util.Map<java.util.Locale, String> valueMap,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				externalReferenceCode, cpDefinitionSpecificationOptionValueId,
				cpOptionCategoryId, key, priority, valueMap, serviceContext);
	}

	@Override
	public CPDefinitionSpecificationOptionValueService getWrappedService() {
		return _cpDefinitionSpecificationOptionValueService;
	}

	@Override
	public void setWrappedService(
		CPDefinitionSpecificationOptionValueService
			cpDefinitionSpecificationOptionValueService) {

		_cpDefinitionSpecificationOptionValueService =
			cpDefinitionSpecificationOptionValueService;
	}

	private CPDefinitionSpecificationOptionValueService
		_cpDefinitionSpecificationOptionValueService;

}