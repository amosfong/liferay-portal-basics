/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.search.experiences.model.SXPElement;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for SXPElement. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SXPElementServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface SXPElementService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.search.experiences.service.impl.SXPElementServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the sxp element remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link SXPElementServiceUtil} if injection and service tracking are not available.
	 */
	public SXPElement addSXPElement(
			String externalReferenceCode, Map<Locale, String> descriptionMap,
			String elementDefinitionJSON, String fallbackDescription,
			String fallbackTitle, boolean readOnly, String schemaVersion,
			Map<Locale, String> titleMap, int type,
			ServiceContext serviceContext)
		throws PortalException;

	public SXPElement deleteSXPElement(long sxpElementId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPElement fetchSXPElement(long sxpElementId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPElement fetchSXPElementByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPElement getSXPElement(long sxpElementId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPElement getSXPElementByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	public SXPElement updateSXPElement(
			String externalReferenceCode, long sxpElementId,
			Map<Locale, String> descriptionMap, String elementDefinitionJSON,
			String schemaVersion, boolean hidden, Map<Locale, String> titleMap,
			ServiceContext serviceContext)
		throws PortalException;

}