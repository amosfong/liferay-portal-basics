/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.odata.entity.v1_0.provider;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.portal.odata.entity.EntityModel;

/**
 * @author Carlos Correa
 */
public interface EntityModelProvider {

	public EntityModel getEntityModel(ObjectDefinition objectDefinition);

	public default EntityModel getLegacyEntityModel(
		ObjectDefinition objectDefinition) {

		return null;
	}

}