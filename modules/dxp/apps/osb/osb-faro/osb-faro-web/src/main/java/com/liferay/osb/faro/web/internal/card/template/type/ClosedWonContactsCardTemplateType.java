/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.card.template.type;

import com.liferay.osb.faro.contacts.model.constants.ContactsCardTemplateConstants;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.ClosedWonContactsCardTemplateDisplay;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.ContactsCardTemplateDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class ClosedWonContactsCardTemplateType
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
		return ClosedWonContactsCardTemplateDisplay.class;
	}

	@Override
	public int getType() {
		return ContactsCardTemplateConstants.TYPE_CLOSED_WON;
	}

	private static final String _DEFAULT_NAME = "Closed Won";

	private static final Map<String, Object> _defaultSettings =
		HashMapBuilder.<String, Object>put(
			"endDateTime", 0
		).put(
			"startDateTime", 0
		).build();

}