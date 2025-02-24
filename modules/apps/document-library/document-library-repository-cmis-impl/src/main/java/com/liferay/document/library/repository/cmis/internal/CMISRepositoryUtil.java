/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.repository.cmis.internal;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.lang.ThreadContextClassLoaderUtil;
import com.liferay.portal.kernel.exception.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.RepositoryLocalServiceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;
import org.apache.chemistry.opencmis.commons.exceptions.CmisPermissionDeniedException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;

/**
 * @author Alexander Chow
 */
public class CMISRepositoryUtil {

	public static void checkRepository(
			long repositoryId, Map<String, String> parameters,
			UnicodeProperties typeSettingsUnicodeProperties,
			String typeSettingsKey)
		throws PortalException, RepositoryException {

		if (!typeSettingsUnicodeProperties.containsKey(typeSettingsKey) ||
			Validator.isNull(
				typeSettingsUnicodeProperties.getProperty(typeSettingsKey))) {

			Repository cmisRepository = _getCMISRepository(parameters);

			typeSettingsUnicodeProperties.setProperty(
				typeSettingsKey, cmisRepository.getId());

			try {
				RepositoryLocalServiceUtil.updateRepository(
					repositoryId, typeSettingsUnicodeProperties);
			}
			catch (PortalException | SystemException exception) {
				throw new RepositoryException(exception);
			}
		}

		parameters.put(
			SessionParameter.REPOSITORY_ID,
			getTypeSettingsValue(
				typeSettingsUnicodeProperties, typeSettingsKey));
	}

	public static com.liferay.document.library.repository.cmis.Session
			createSession(Map<String, String> parameters)
		throws PrincipalException, RepositoryException {

		try (SafeCloseable safeCloseable = ThreadContextClassLoaderUtil.swap(
				CMISRepositoryUtil.class.getClassLoader())) {

			Session session = _sessionFactory.createSession(parameters);

			session.setDefaultContext(_operationContext);

			return new SessionImpl(session);
		}
		catch (CmisPermissionDeniedException cmisPermissionDeniedException) {
			throw new PrincipalException.MustBeAuthenticated(
				parameters.get(SessionParameter.USER),
				cmisPermissionDeniedException);
		}
		catch (CmisUnauthorizedException cmisUnauthorizedException) {
			throw new PrincipalException.MustBeAuthenticated(
				parameters.get(SessionParameter.USER),
				cmisUnauthorizedException);
		}
		catch (Exception exception) {
			throw new RepositoryException(exception);
		}
	}

	public static String getTypeSettingsValue(
			UnicodeProperties typeSettingsUnicodeProperties,
			String typeSettingsKey)
		throws InvalidRepositoryException {

		String value = typeSettingsUnicodeProperties.getProperty(
			typeSettingsKey);

		if (Validator.isNull(value)) {
			throw new InvalidRepositoryException(
				"Properties value cannot be null for key " + typeSettingsKey);
		}

		return value;
	}

	private static Repository _getCMISRepository(
		Map<String, String> parameters) {

		try (SafeCloseable safeCloseable = ThreadContextClassLoaderUtil.swap(
				CMISRepositoryUtil.class.getClassLoader())) {

			List<Repository> repositories = _sessionFactory.getRepositories(
				parameters);

			return repositories.get(0);
		}
	}

	private static final OperationContext _operationContext;
	private static final SessionFactory _sessionFactory =
		SessionFactoryImpl.newInstance();

	static {
		Set<String> defaultFilterSet = new HashSet<>();

		// Base

		defaultFilterSet.add(PropertyIds.BASE_TYPE_ID);
		defaultFilterSet.add(PropertyIds.CREATED_BY);
		defaultFilterSet.add(PropertyIds.CREATION_DATE);
		defaultFilterSet.add(PropertyIds.LAST_MODIFICATION_DATE);
		defaultFilterSet.add(PropertyIds.LAST_MODIFIED_BY);
		defaultFilterSet.add(PropertyIds.NAME);
		defaultFilterSet.add(PropertyIds.OBJECT_ID);
		defaultFilterSet.add(PropertyIds.OBJECT_TYPE_ID);

		// Document

		defaultFilterSet.add(PropertyIds.CONTENT_STREAM_LENGTH);
		defaultFilterSet.add(PropertyIds.CONTENT_STREAM_MIME_TYPE);
		defaultFilterSet.add(PropertyIds.IS_VERSION_SERIES_CHECKED_OUT);
		defaultFilterSet.add(PropertyIds.VERSION_LABEL);
		defaultFilterSet.add(PropertyIds.VERSION_SERIES_CHECKED_OUT_BY);
		defaultFilterSet.add(PropertyIds.VERSION_SERIES_CHECKED_OUT_ID);
		defaultFilterSet.add(PropertyIds.VERSION_SERIES_ID);

		// Folder

		defaultFilterSet.add(PropertyIds.PARENT_ID);
		defaultFilterSet.add(PropertyIds.PATH);

		// Operation context

		_operationContext = new OperationContextImpl(
			defaultFilterSet, false, true, false, IncludeRelationships.NONE,
			null, false, "cmis:name ASC", true, 1000);
	}

}