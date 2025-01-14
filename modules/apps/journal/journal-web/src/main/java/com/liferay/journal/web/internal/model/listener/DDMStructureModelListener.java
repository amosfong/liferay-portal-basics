/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.model.listener;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.web.internal.exception.DDMStructureValidationModelListenerException;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.util.Portal;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = ModelListener.class)
public class DDMStructureModelListener extends BaseModelListener<DDMStructure> {

	@Override
	public void onBeforeUpdate(
			DDMStructure originalDDMStructure, DDMStructure ddmStructure)
		throws ModelListenerException {

		if ((ddmStructure.getClassNameId() != _portal.getClassNameId(
				JournalArticle.class)) ||
			Objects.equals(
				originalDDMStructure.getStructureKey(),
				ddmStructure.getStructureKey())) {

			return;
		}

		JournalServiceConfiguration journalServiceConfiguration =
			_getJournalServiceConfiguration(ddmStructure.getCompanyId());

		if (((journalServiceConfiguration != null) &&
			 journalServiceConfiguration.autogenerateDDMStructureKey()) ||
			Objects.equals(
				originalDDMStructure.getStructureKey(), "BASIC-WEB-CONTENT")) {

			throw new DDMStructureValidationModelListenerException();
		}
	}

	@Reference
	protected ConfigurationProvider configurationProvider;

	private JournalServiceConfiguration _getJournalServiceConfiguration(
		long companyId) {

		try {
			return configurationProvider.getCompanyConfiguration(
				JournalServiceConfiguration.class, companyId);
		}
		catch (ConfigurationException configurationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(configurationException);
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStructureModelListener.class);

	@Reference
	private Portal _portal;

}