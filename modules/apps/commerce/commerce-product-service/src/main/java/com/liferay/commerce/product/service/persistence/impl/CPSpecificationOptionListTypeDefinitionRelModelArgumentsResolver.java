/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.model.CPSpecificationOptionListTypeDefinitionRelTable;
import com.liferay.commerce.product.model.impl.CPSpecificationOptionListTypeDefinitionRelImpl;
import com.liferay.commerce.product.model.impl.CPSpecificationOptionListTypeDefinitionRelModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CPSpecificationOptionListTypeDefinitionRel.
 *
 * @author Marco Leo
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.commerce.product.model.impl.CPSpecificationOptionListTypeDefinitionRelImpl",
		"table.name=CPSOListTypeDefinitionRel"
	},
	service = ArgumentsResolver.class
)
public class CPSpecificationOptionListTypeDefinitionRelModelArgumentsResolver
	implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		CPSpecificationOptionListTypeDefinitionRelModelImpl
			cpSpecificationOptionListTypeDefinitionRelModelImpl =
				(CPSpecificationOptionListTypeDefinitionRelModelImpl)baseModel;

		long columnBitmask =
			cpSpecificationOptionListTypeDefinitionRelModelImpl.
				getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				cpSpecificationOptionListTypeDefinitionRelModelImpl,
				columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					cpSpecificationOptionListTypeDefinitionRelModelImpl.
						getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				cpSpecificationOptionListTypeDefinitionRelModelImpl,
				columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CPSpecificationOptionListTypeDefinitionRelImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CPSpecificationOptionListTypeDefinitionRelTable.INSTANCE.
			getTableName();
	}

	private static Object[] _getValue(
		CPSpecificationOptionListTypeDefinitionRelModelImpl
			cpSpecificationOptionListTypeDefinitionRelModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					cpSpecificationOptionListTypeDefinitionRelModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					cpSpecificationOptionListTypeDefinitionRelModelImpl.
						getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}