/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.BooleanEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public class CurrencyEntityModel implements EntityModel {

	public CurrencyEntityModel() {
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
			new BooleanEntityField("active", locale -> "active"),
			new BooleanEntityField("primary", locale -> "primary"),
			new StringEntityField("code", locale -> "code"),
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName("name")));
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}