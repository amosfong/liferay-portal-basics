/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.card.template.type;

import com.liferay.osb.faro.contacts.model.constants.ContactsCardTemplateConstants;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.ContactsCardTemplateDisplay;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.ConversionHealthContactsCardTemplateDisplay;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class ConversionHealthContactsCardTemplateType
	extends BaseContactsCardTemplateType {

	@Override
	public String getDefaultName() {
		return _DEFAULT_NAME;
	}

	@Override
	public Map<String, Object> getDefaultSettings() {
		return _defaultSettings;
	}

	@Override
	public Class<? extends ContactsCardTemplateDisplay> getDisplayClass() {
		return ConversionHealthContactsCardTemplateDisplay.class;
	}

	@Override
	public int getType() {
		return ContactsCardTemplateConstants.TYPE_CONVERSION_HEALTH;
	}

	private static final String _DEFAULT_NAME = "Conversion Health";

	private static final Map<String, Object> _defaultSettings =
		HashMapBuilder.<String, Object>put(
			"endDateTime", 0
		).put(
			"interval", 30
		).put(
			"minCurrentStageId", StringPool.BLANK
		).put(
			"stageId", StringPool.BLANK
		).put(
			"startDateTime", 0
		).put(
			"unit", ContactsCardTemplateConstants.SETTINGS_UNIT_PERCENTAGE
		).build();

}