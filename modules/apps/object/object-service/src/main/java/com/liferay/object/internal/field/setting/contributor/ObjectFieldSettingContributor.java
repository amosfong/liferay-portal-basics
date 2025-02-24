/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.field.setting.contributor;

import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Feliphe Marinho
 */
public interface ObjectFieldSettingContributor {

	public default void addObjectFieldSetting(
			long userId, long objectFieldId,
			ObjectFieldSetting objectFieldSetting)
		throws PortalException {
	}

	public default void updateObjectFieldSetting(
			long objectFieldSettingId, ObjectFieldSetting objectFieldSetting)
		throws PortalException {
	}

}