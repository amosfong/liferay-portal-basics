/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceShippingOptionAccountEntryRelTable;
import com.liferay.commerce.model.impl.CommerceShippingOptionAccountEntryRelImpl;
import com.liferay.commerce.model.impl.CommerceShippingOptionAccountEntryRelModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CommerceShippingOptionAccountEntryRel.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.commerce.model.impl.CommerceShippingOptionAccountEntryRelImpl",
		"table.name=CSOptionAccountEntryRel"
	},
	service = ArgumentsResolver.class
)
public class CommerceShippingOptionAccountEntryRelModelArgumentsResolver
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

		CommerceShippingOptionAccountEntryRelModelImpl
			commerceShippingOptionAccountEntryRelModelImpl =
				(CommerceShippingOptionAccountEntryRelModelImpl)baseModel;

		long columnBitmask =
			commerceShippingOptionAccountEntryRelModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				commerceShippingOptionAccountEntryRelModelImpl, columnNames,
				original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					commerceShippingOptionAccountEntryRelModelImpl.
						getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				commerceShippingOptionAccountEntryRelModelImpl, columnNames,
				original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CommerceShippingOptionAccountEntryRelImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CommerceShippingOptionAccountEntryRelTable.INSTANCE.
			getTableName();
	}

	private static Object[] _getValue(
		CommerceShippingOptionAccountEntryRelModelImpl
			commerceShippingOptionAccountEntryRelModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					commerceShippingOptionAccountEntryRelModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					commerceShippingOptionAccountEntryRelModelImpl.
						getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}