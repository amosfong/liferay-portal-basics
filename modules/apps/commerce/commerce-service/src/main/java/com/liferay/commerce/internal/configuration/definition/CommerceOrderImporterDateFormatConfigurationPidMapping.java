/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.configuration.definition;

import com.liferay.commerce.configuration.CommerceOrderImporterDateFormatConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;

import org.osgi.service.component.annotations.Component;

/**
 * @author Christian Chiappa
 */
@Component(service = ConfigurationPidMapping.class)
public class CommerceOrderImporterDateFormatConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceOrderImporterDateFormatConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return CommerceConstants.
			SERVICE_NAME_COMMERCE_ORDER_IMPORTER_DATE_FORMAT;
	}

}