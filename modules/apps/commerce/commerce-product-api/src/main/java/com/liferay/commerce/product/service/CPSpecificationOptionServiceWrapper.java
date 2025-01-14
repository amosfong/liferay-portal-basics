/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSpecificationOptionService}.
 *
 * @author Marco Leo
 * @see CPSpecificationOptionService
 * @generated
 */
public class CPSpecificationOptionServiceWrapper
	implements CPSpecificationOptionService,
			   ServiceWrapper<CPSpecificationOptionService> {

	public CPSpecificationOptionServiceWrapper() {
		this(null);
	}

	public CPSpecificationOptionServiceWrapper(
		CPSpecificationOptionService cpSpecificationOptionService) {

		_cpSpecificationOptionService = cpSpecificationOptionService;
	}

	@Override
	public CPSpecificationOption addCPSpecificationOption(
			String externalReferenceCode, long cpOptionCategoryId,
			long[] listTypeDefinitionIds,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean facetable, String key, double priority,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.addCPSpecificationOption(
			externalReferenceCode, cpOptionCategoryId, listTypeDefinitionIds,
			titleMap, descriptionMap, facetable, key, priority, serviceContext);
	}

	@Override
	public void deleteCPSpecificationOption(long cpSpecificationOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpSpecificationOptionService.deleteCPSpecificationOption(
			cpSpecificationOptionId);
	}

	@Override
	public CPSpecificationOption fetchCPSpecificationOption(
			long companyId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.fetchCPSpecificationOption(
			companyId, key);
	}

	@Override
	public CPSpecificationOption
			fetchCPSpecificationOptionByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.
			fetchCPSpecificationOptionByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public CPSpecificationOption getCPSpecificationOption(
			long cpSpecificationOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.getCPSpecificationOption(
			cpSpecificationOptionId);
	}

	@Override
	public CPSpecificationOption getCPSpecificationOption(
			long companyId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.getCPSpecificationOption(
			companyId, key);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpSpecificationOptionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPSpecificationOption> searchCPSpecificationOptions(
				long companyId, Boolean facetable, String keywords, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.searchCPSpecificationOptions(
			companyId, facetable, keywords, start, end, sort);
	}

	@Override
	public CPSpecificationOption updateCPSpecificationOption(
			String externalReferenceCode, long cpSpecificationOptionId,
			long cpOptionCategoryId, long[] listTypeDefinitionIds,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean facetable, String key, double priority,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.updateCPSpecificationOption(
			externalReferenceCode, cpSpecificationOptionId, cpOptionCategoryId,
			listTypeDefinitionIds, titleMap, descriptionMap, facetable, key,
			priority, serviceContext);
	}

	@Override
	public CPSpecificationOptionService getWrappedService() {
		return _cpSpecificationOptionService;
	}

	@Override
	public void setWrappedService(
		CPSpecificationOptionService cpSpecificationOptionService) {

		_cpSpecificationOptionService = cpSpecificationOptionService;
	}

	private CPSpecificationOptionService _cpSpecificationOptionService;

}