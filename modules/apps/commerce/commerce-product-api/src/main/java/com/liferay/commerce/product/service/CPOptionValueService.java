/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CPOptionValue. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see CPOptionValueServiceUtil
 * @generated
 */
@AccessControlled
@CTAware
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CPOptionValueService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPOptionValueServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the cp option value remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CPOptionValueServiceUtil} if injection and service tracking are not available.
	 */
	public CPOptionValue addCPOptionValue(
			long cpOptionId, Map<Locale, String> nameMap, double priority,
			String key, ServiceContext serviceContext)
		throws PortalException;

	public CPOptionValue addOrUpdateCPOptionValue(
			String externalReferenceCode, long cpOptionId,
			Map<Locale, String> nameMap, double priority, String key,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteCPOptionValue(long cpOptionValueId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPOptionValue fetchCPOptionValue(long cpOptionValueId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPOptionValue fetchCPOptionValueByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPOptionValue getCPOptionValue(long cpOptionValueId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPOptionValue> getCPOptionValues(
			long cpOptionId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCPOptionValuesCount(long cpOptionId) throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CPOptionValue> searchCPOptionValues(
			long companyId, long cpOptionId, String keywords, int start,
			int end, Sort[] sorts)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCPOptionValuesCount(
			long companyId, long cpOptionId, String keywords)
		throws PortalException;

	public CPOptionValue updateCPOptionValue(
			long cpOptionValueId, Map<Locale, String> nameMap, double priority,
			String key, ServiceContext serviceContext)
		throws PortalException;

}