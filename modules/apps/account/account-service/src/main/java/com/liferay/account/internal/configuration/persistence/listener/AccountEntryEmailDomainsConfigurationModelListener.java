/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.configuration.persistence.listener;

import com.liferay.account.configuration.AccountEntryEmailDomainsConfiguration;
import com.liferay.account.internal.validator.util.DomainValidatorFactoryUtil;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.validator.routines.DomainValidator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	property = "model.class.name=com.liferay.account.configuration.AccountEntryEmailDomainsConfiguration",
	service = ConfigurationModelListener.class
)
public class AccountEntryEmailDomainsConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onAfterSave(String pid, Dictionary<String, Object> properties) {
		long companyId = _getCompanyId(properties);

		if (companyId == CompanyConstants.SYSTEM) {
			return;
		}

		String[] blockedEmailAddressDomains = _getBlockedEmailAddressDomains(
			properties);

		if (ArrayUtil.isEmpty(blockedEmailAddressDomains)) {
			return;
		}

		BaseModelSearchResult<AccountEntry> baseModelSearchResult =
			_accountEntryLocalService.searchAccountEntries(
				companyId, null,
				new LinkedHashMap<>(
					Collections.singletonMap(
						"domains", blockedEmailAddressDomains)),
				-1, -1, null, false);

		for (AccountEntry accountEntry :
				baseModelSearchResult.getBaseModels()) {

			String[] domains = accountEntry.getDomainsArray();

			for (String blockedEmailAddressDomain :
					blockedEmailAddressDomains) {

				domains = ArrayUtil.remove(domains, blockedEmailAddressDomain);
			}

			accountEntry.setDomains(StringUtil.merge(domains));

			_accountEntryLocalService.updateAccountEntry(accountEntry);
		}
	}

	@Override
	public void onBeforeSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		if (_getCompanyId(properties) == CompanyConstants.SYSTEM) {
			return;
		}

		String[] blockedEmailAddressDomains = _getBlockedEmailAddressDomains(
			properties);

		if (ArrayUtil.isEmpty(blockedEmailAddressDomains)) {
			return;
		}

		Arrays.setAll(
			blockedEmailAddressDomains,
			i -> StringUtil.lowerCase(
				StringUtil.trim(blockedEmailAddressDomains[i])));

		DomainValidator domainValidator = DomainValidatorFactoryUtil.create(
			GetterUtil.getStringValues(
				properties.get("customTLDs"), _EMPTY_STRING_ARRAY));

		List<String> invalidDomains = new ArrayList<>();

		for (String blockedEmailAddressDomain : blockedEmailAddressDomains) {
			if (!domainValidator.isValid(blockedEmailAddressDomain)) {
				invalidDomains.add(blockedEmailAddressDomain);
			}
		}

		if (ListUtil.isNotEmpty(invalidDomains)) {
			throw new ConfigurationModelListenerException(
				_language.format(
					LocaleThreadLocal.getSiteDefaultLocale(),
					"these-domains-are-not-formatted-correctly-x",
					StringUtil.merge(
						invalidDomains, StringPool.COMMA_AND_SPACE)),
				AccountEntryEmailDomainsConfiguration.class,
				AccountEntryEmailDomainsConfigurationModelListener.class,
				HashMapDictionaryBuilder.<String, Object>put(
					"invalidDomains", ArrayUtil.toStringArray(invalidDomains)
				).build());
		}

		properties.put(
			"blockedEmailDomains",
			StringUtil.merge(blockedEmailAddressDomains, StringPool.NEW_LINE));
	}

	private String[] _getBlockedEmailAddressDomains(
		Dictionary<String, Object> properties) {

		return StringUtil.split(
			GetterUtil.getString(properties.get("blockedEmailDomains")),
			CharPool.NEW_LINE);
	}

	private long _getCompanyId(Dictionary<String, Object> properties) {
		return GetterUtil.getLong(
			properties.get("companyId"), CompanyConstants.SYSTEM);
	}

	private static final String[] _EMPTY_STRING_ARRAY = new String[0];

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private Language _language;

}