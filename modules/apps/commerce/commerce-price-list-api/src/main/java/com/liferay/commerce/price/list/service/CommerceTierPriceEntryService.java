/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service;

import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
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
import com.liferay.portal.kernel.util.OrderByComparator;

import java.math.BigDecimal;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CommerceTierPriceEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTierPriceEntryServiceUtil
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
public interface CommerceTierPriceEntryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce tier price entry remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceTierPriceEntryServiceUtil} if injection and service tracking are not available.
	 */
	public CommerceTierPriceEntry addCommerceTierPriceEntry(
			long commercePriceEntryId, BigDecimal price, BigDecimal promoPrice,
			BigDecimal minQuantity, ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry addCommerceTierPriceEntry(
			String externalReferenceCode, long commercePriceEntryId,
			BigDecimal price, BigDecimal promoPrice, BigDecimal minQuantity,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry addCommerceTierPriceEntry(
			String externalReferenceCode, long commercePriceEntryId,
			BigDecimal price, BigDecimal minQuantity, boolean bulkPricing,
			boolean discountDiscovery, BigDecimal discountLevel1,
			BigDecimal discountLevel2, BigDecimal discountLevel3,
			BigDecimal discountLevel4, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry addOrUpdateCommerceTierPriceEntry(
			String externalReferenceCode, long commerceTierPriceEntryId,
			long commercePriceEntryId, BigDecimal price, BigDecimal promoPrice,
			BigDecimal minQuantity, String priceEntryExternalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry addOrUpdateCommerceTierPriceEntry(
			String externalReferenceCode, long commerceTierPriceEntryId,
			long commercePriceEntryId, BigDecimal price, BigDecimal minQuantity,
			boolean bulkPricing, boolean discountDiscovery,
			BigDecimal discountLevel1, BigDecimal discountLevel2,
			BigDecimal discountLevel3, BigDecimal discountLevel4,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String priceEntryExternalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteCommerceTierPriceEntry(long commerceTierPriceEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceTierPriceEntry fetchCommerceTierPriceEntry(
			long commerceTierPriceEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceTierPriceEntry
			fetchCommerceTierPriceEntryByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceTierPriceEntry> getCommerceTierPriceEntries(
			long commercePriceEntryId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceTierPriceEntry> getCommerceTierPriceEntries(
			long commercePriceEntryId, int start, int end,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceTierPriceEntriesCount(long commercePriceEntryId)
		throws PortalException;

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceTierPriceEntriesCountByCompanyId(long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceTierPriceEntry getCommerceTierPriceEntry(
			long commerceTierPriceEntryId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceTierPriceEntry>
			searchCommerceTierPriceEntries(
				long companyId, long commercePriceEntryId, String keywords,
				int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCommerceTierPriceEntriesCount(
			long companyId, long commercePriceEntryId, String keywords)
		throws PortalException;

	public CommerceTierPriceEntry updateCommerceTierPriceEntry(
			long commerceTierPriceEntryId, BigDecimal price,
			BigDecimal promoPrice, BigDecimal minQuantity,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry updateCommerceTierPriceEntry(
			long commerceTierPriceEntryId, BigDecimal price,
			BigDecimal minQuantity, boolean bulkPricing,
			boolean discountDiscovery, BigDecimal discountLevel1,
			BigDecimal discountLevel2, BigDecimal discountLevel3,
			BigDecimal discountLevel4, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceTierPriceEntry updateExternalReferenceCode(
			CommerceTierPriceEntry commerceTierPriceEntry,
			String externalReferenceCode)
		throws PortalException;

}