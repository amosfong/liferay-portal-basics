/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.model.CPDefinitionLinkTable;
import com.liferay.commerce.product.model.impl.CPDefinitionLinkImpl;
import com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CPDefinitionLink.
 *
 * @author Marco Leo
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.commerce.product.model.impl.CPDefinitionLinkImpl",
		"table.name=CPDefinitionLink"
	},
	service = ArgumentsResolver.class
)
public class CPDefinitionLinkModelArgumentsResolver
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

		CPDefinitionLinkModelImpl cpDefinitionLinkModelImpl =
			(CPDefinitionLinkModelImpl)baseModel;

		long columnBitmask = cpDefinitionLinkModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(cpDefinitionLinkModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					cpDefinitionLinkModelImpl.getColumnBitmask(columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(CPDefinitionLinkPersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(cpDefinitionLinkModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CPDefinitionLinkImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CPDefinitionLinkTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CPDefinitionLinkModelImpl cpDefinitionLinkModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = cpDefinitionLinkModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = cpDefinitionLinkModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

	private static final long _ORDER_BY_COLUMNS_BITMASK;

	static {
		long orderByColumnsBitmask = 0;

		orderByColumnsBitmask |= CPDefinitionLinkModelImpl.getColumnBitmask(
			"priority");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}