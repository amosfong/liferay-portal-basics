/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.helper;

import com.liferay.fragment.configuration.DefaultInputFragmentEntryConfiguration;
import com.liferay.fragment.helper.DefaultInputFragmentEntryConfigurationProvider;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(service = DefaultInputFragmentEntryConfigurationProvider.class)
public class DefaultInputFragmentEntryConfigurationProviderImpl
	implements DefaultInputFragmentEntryConfigurationProvider {

	@Override
	public JSONObject getDefaultInputFragmentEntryKeysJSONObject(long groupId) {
		Group group = _groupLocalService.fetchGroup(groupId);

		JSONObject defaultInputFragmentEntryKeysJSONObject =
			_getDefaultInputFragmentEntryKeysJSONObject(group);

		if (defaultInputFragmentEntryKeysJSONObject != null) {
			return defaultInputFragmentEntryKeysJSONObject;
		}

		Group companyGroup = _groupLocalService.fetchCompanyGroup(
			group.getCompanyId());

		if ((companyGroup != null) &&
			!Objects.equals(companyGroup.getGroupId(), groupId)) {

			defaultInputFragmentEntryKeysJSONObject =
				_getDefaultInputFragmentEntryKeysJSONObject(companyGroup);
		}

		if (defaultInputFragmentEntryKeysJSONObject != null) {
			return defaultInputFragmentEntryKeysJSONObject;
		}

		return _jsonFactory.createJSONObject(
			_defaultInputFragmentEntryKeysJSONObject.toMap());
	}

	@Override
	public void updateDefaultInputFragmentEntryKeysJSONObject(
			JSONObject defaultInputFragmentEntryKeysJSONObject, long groupId)
		throws Exception {

		_configurationProvider.saveGroupConfiguration(
			DefaultInputFragmentEntryConfiguration.class, groupId,
			HashMapDictionaryBuilder.<String, Object>put(
				"defaultInputFragmentEntryKeys",
				defaultInputFragmentEntryKeysJSONObject.toString()
			).build());
	}

	private JSONObject _getDefaultInputFragmentEntryKeysJSONObject(
		Group group) {

		if (group == null) {
			return null;
		}

		try {
			DefaultInputFragmentEntryConfiguration
				defaultInputFragmentEntryConfiguration =
					_configurationProvider.getGroupConfiguration(
						DefaultInputFragmentEntryConfiguration.class,
						group.getGroupId());

			String defaultInputFragmentEntryKeys =
				defaultInputFragmentEntryConfiguration.
					defaultInputFragmentEntryKeys();

			if (Validator.isNull(defaultInputFragmentEntryKeys)) {
				return null;
			}

			return _jsonFactory.createJSONObject(defaultInputFragmentEntryKeys);
		}
		catch (ConfigurationException | JSONException exception) {
			_log.error(exception);

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultInputFragmentEntryConfigurationProviderImpl.class);

	private static final JSONObject _defaultInputFragmentEntryKeysJSONObject =
		JSONUtil.put(
			FORM_INPUT_SUBMIT_BUTTON,
			JSONUtil.put("key", "INPUTS-submit-button"));

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}