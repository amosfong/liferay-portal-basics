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
import com.liferay.search.experiences.model.SXPBlueprint;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for SXPBlueprint. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SXPBlueprintServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface SXPBlueprintService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.search.experiences.service.impl.SXPBlueprintServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the sxp blueprint remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link SXPBlueprintServiceUtil} if injection and service tracking are not available.
	 */
	public SXPBlueprint addSXPBlueprint(
			String externalReferenceCode, String configurationJSON,
			Map<Locale, String> descriptionMap, String elementInstancesJSON,
			String schemaVersion, Map<Locale, String> titleMap,
			ServiceContext serviceContext)
		throws PortalException;

	public SXPBlueprint deleteSXPBlueprint(long sxpBlueprintId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPBlueprint fetchSXPBlueprint(long sxpBlueprintId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPBlueprint fetchSXPBlueprintByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPBlueprint getSXPBlueprint(long sxpBlueprintId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SXPBlueprint getSXPBlueprintByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException;

	public SXPBlueprint updateSXPBlueprint(
			String externalReferenceCode, long sxpBlueprintId,
			String configurationJSON, Map<Locale, String> descriptionMap,
			String elementInstancesJSON, String schemaVersion,
			Map<Locale, String> titleMap, ServiceContext serviceContext)
		throws PortalException;

}