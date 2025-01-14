/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.headless.delivery.dto.v1_0.util;

import com.liferay.headless.delivery.dto.v1_0.MasterPage;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;

/**
 * @author Rubén Pulido
 */
public class MasterPageUtil {

	public static MasterPage toMasterPage(
		LayoutPageTemplateEntry layoutPageTemplateEntry) {

		return new MasterPage() {
			{
				setName(layoutPageTemplateEntry::getName);
			}
		};
	}

}