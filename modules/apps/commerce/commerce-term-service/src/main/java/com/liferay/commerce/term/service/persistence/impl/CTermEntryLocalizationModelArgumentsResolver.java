/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.term.service.persistence.impl;

import com.liferay.commerce.term.model.CTermEntryLocalizationTable;
import com.liferay.commerce.term.model.impl.CTermEntryLocalizationImpl;
import com.liferay.commerce.term.model.impl.CTermEntryLocalizationModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CTermEntryLocalization.
 *
 * @author Luca Pellizzon
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.commerce.term.model.impl.CTermEntryLocalizationImpl",
		"table.name=CTermEntryLocalization"
	},
	service = ArgumentsResolver.class
)
public class CTermEntryLocalizationModelArgumentsResolver
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

		CTermEntryLocalizationModelImpl cTermEntryLocalizationModelImpl =
			(CTermEntryLocalizationModelImpl)baseModel;

		long columnBitmask = cTermEntryLocalizationModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				cTermEntryLocalizationModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					cTermEntryLocalizationModelImpl.getColumnBitmask(
						columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				cTermEntryLocalizationModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CTermEntryLocalizationImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CTermEntryLocalizationTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CTermEntryLocalizationModelImpl cTermEntryLocalizationModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					cTermEntryLocalizationModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = cTermEntryLocalizationModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}