/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.contacts.uad.display;

import com.liferay.user.associated.data.display.UADDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = UADDisplay.class)
public class EntryUADDisplay extends BaseEntryUADDisplay {

	@Override
	public boolean isSiteScoped() {
		return false;
	}

}