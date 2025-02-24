/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.repository.cmis.internal;

import com.liferay.document.library.repository.cmis.CMISRepositoryHandler;
import com.liferay.document.library.repository.cmis.Session;
import com.liferay.document.library.repository.cmis.internal.constants.CMISRepositoryConstants;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.lang.ThreadContextClassLoaderUtil;
import com.liferay.portal.kernel.exception.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

/**
 * @author Alexander Chow
 */
public class CMISAtomPubRepository extends CMISRepositoryHandler {

	@Override
	public Session getSession() throws PortalException {
		Locale locale = LocaleUtil.getSiteDefault();

		String login = getLogin();
		String password = null;

		if (Validator.isNotNull(login)) {
			password = PrincipalThreadLocal.getPassword();
		}
		else {
			login = _DL_REPOSITORY_GUEST_USERNAME;
			password = _DL_REPOSITORY_GUEST_PASSWORD;
		}

		Map<String, String> parameters = HashMapBuilder.put(
			SessionParameter.ATOMPUB_URL,
			getTypeSettingsValue(
				CMISRepositoryConstants.CMIS_ATOMPUB_URL_PARAMETER)
		).put(
			SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value()
		).put(
			SessionParameter.COMPRESSION, Boolean.TRUE.toString()
		).put(
			SessionParameter.LOCALE_ISO639_LANGUAGE, locale.getLanguage()
		).put(
			SessionParameter.LOCALE_ISO3166_COUNTRY, locale.getCountry()
		).put(
			SessionParameter.PASSWORD, password
		).put(
			SessionParameter.USER, login
		).build();

		try (SafeCloseable safeCloseable = ThreadContextClassLoaderUtil.swap(
				CMISAtomPubRepository.class.getClassLoader())) {

			CMISRepositoryUtil.checkRepository(
				getRepositoryId(), parameters, getTypeSettingsProperties(),
				CMISRepositoryConstants.CMIS_ATOMPUB_REPOSITORY_ID_PARAMETER);

			return CMISRepositoryUtil.createSession(parameters);
		}
	}

	protected String getTypeSettingsValue(String typeSettingsKey)
		throws InvalidRepositoryException {

		return CMISRepositoryUtil.getTypeSettingsValue(
			getTypeSettingsProperties(), typeSettingsKey);
	}

	private static final String _DL_REPOSITORY_GUEST_PASSWORD =
		GetterUtil.getString(
			PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_PASSWORD));

	private static final String _DL_REPOSITORY_GUEST_USERNAME =
		GetterUtil.getString(
			PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_USERNAME));

}