/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v6_1_4;

import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Dictionary;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Mikel Lorza
 */
public class JournalArticleAutogenerateDDMKeyConfigurationUpgradeProcess
	extends UpgradeProcess {

	public JournalArticleAutogenerateDDMKeyConfigurationUpgradeProcess(
		CompanyLocalService companyLocalService,
		ConfigurationAdmin configurationAdmin) {

		_companyLocalService = companyLocalService;
		_configurationAdmin = configurationAdmin;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			"com.liferay.journal.web.internal.configuration." +
				"JournalWebConfiguration",
			StringPool.QUESTION);

		if (configuration == null) {
			return;
		}

		Dictionary<String, Object> dictionary = configuration.getProperties();

		if (dictionary == null) {
			return;
		}

		boolean autogenerateDDMStructureKey = GetterUtil.getBoolean(
			dictionary.get("autogenerateDDMStructureKey"), true);
		boolean autogenerateDDMTemplateKey = GetterUtil.getBoolean(
			dictionary.get("autogenerateDDMTemplateKey"), true);

		if (autogenerateDDMStructureKey && autogenerateDDMTemplateKey) {
			return;
		}

		_companyLocalService.forEachCompany(
			company -> _updateCompanyConfiguration(
				autogenerateDDMStructureKey, autogenerateDDMTemplateKey,
				company.getCompanyId()));
	}

	private void _updateCompanyConfiguration(
			boolean autogenerateDDMStructureKey,
			boolean autogenerateDDMTemplateKey, long companyId)
		throws Exception {

		Configuration configuration = null;
		Dictionary<String, Object> properties = null;

		Configuration[] configurations = _configurationAdmin.listConfigurations(
			String.format(
				"(&(service.factoryPid=%s)(%s=%d))",
				JournalServiceConfiguration.class.getName() + ".scoped",
				ExtendedObjectClassDefinition.Scope.COMPANY.getPropertyKey(),
				companyId));

		if (configurations == null) {
			configuration = _configurationAdmin.createFactoryConfiguration(
				JournalServiceConfiguration.class.getName() + ".scoped",
				StringPool.QUESTION);
			properties = HashMapDictionaryBuilder.<String, Object>put(
				ExtendedObjectClassDefinition.Scope.COMPANY.getPropertyKey(),
				companyId
			).build();
		}
		else {
			configuration = configurations[0];

			properties = configuration.getProperties();
		}

		properties.put(
			"autogenerateDDMStructureKey", autogenerateDDMStructureKey);
		properties.put(
			"autogenerateDDMTemplateKey", autogenerateDDMTemplateKey);

		configuration.update(properties);
	}

	private final CompanyLocalService _companyLocalService;
	private final ConfigurationAdmin _configurationAdmin;

}