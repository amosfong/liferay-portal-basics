/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.forecast.alert.service;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CommerceMLForecastAlertEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceMLForecastAlertEntryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce ml forecast alert entry remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceMLForecastAlertEntryServiceUtil} if injection and service tracking are not available.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry>
			getAboveThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry>
			getBelowThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, double relativeChange,
				int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status, double relativeChange)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
			long companyId, long userId, int status, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public CommerceMLForecastAlertEntry updateStatus(
			long commerceMLForecastAlertEntryId, int status)
		throws PortalException;

}