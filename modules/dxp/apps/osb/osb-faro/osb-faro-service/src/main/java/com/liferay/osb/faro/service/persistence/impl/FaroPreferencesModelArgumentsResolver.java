/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.service.persistence.impl;

import com.liferay.osb.faro.model.FaroPreferencesTable;
import com.liferay.osb.faro.model.impl.FaroPreferencesImpl;
import com.liferay.osb.faro.model.impl.FaroPreferencesModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from FaroPreferences.
 *
 * @author Matthew Kong
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.osb.faro.model.impl.FaroPreferencesImpl",
		"table.name=OSBFaro_FaroPreferences"
	},
	service = ArgumentsResolver.class
)
public class FaroPreferencesModelArgumentsResolver
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

		FaroPreferencesModelImpl faroPreferencesModelImpl =
			(FaroPreferencesModelImpl)baseModel;

		long columnBitmask = faroPreferencesModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(faroPreferencesModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					faroPreferencesModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(faroPreferencesModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return FaroPreferencesImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return FaroPreferencesTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		FaroPreferencesModelImpl faroPreferencesModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = faroPreferencesModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = faroPreferencesModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}