/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.card.template.type;

import com.liferay.osb.faro.contacts.model.constants.ContactsCardTemplateConstants;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.ContactsCardTemplateDisplay;
import com.liferay.osb.faro.web.internal.model.display.contacts.card.template.CoworkersContactsCardTemplateDisplay;

/**
 * @author Shinn Lok
 */
public class CoworkersContactsCardTemplateType
	extends BaseContactsCardTemplateType {

	@Override
	public String getDefaultName() {
		return _DEFAULT_NAME;
	}

	@Override
	public Class<? extends ContactsCardTemplateDisplay> getDisplayClass() {
		return CoworkersContactsCardTemplateDisplay.class;
	}

	@Override
	public int getType() {
		return ContactsCardTemplateConstants.TYPE_COWORKERS;
	}

	private static final String _DEFAULT_NAME = "Coworkers";

}