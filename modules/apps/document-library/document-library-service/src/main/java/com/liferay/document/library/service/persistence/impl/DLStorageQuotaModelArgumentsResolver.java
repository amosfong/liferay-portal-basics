/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.service.persistence.impl;

import com.liferay.document.library.model.DLStorageQuotaTable;
import com.liferay.document.library.model.impl.DLStorageQuotaImpl;
import com.liferay.document.library.model.impl.DLStorageQuotaModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from DLStorageQuota.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.document.library.model.impl.DLStorageQuotaImpl",
		"table.name=DLStorageQuota"
	},
	service = ArgumentsResolver.class
)
public class DLStorageQuotaModelArgumentsResolver implements ArgumentsResolver {

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

		DLStorageQuotaModelImpl dlStorageQuotaModelImpl =
			(DLStorageQuotaModelImpl)baseModel;

		long columnBitmask = dlStorageQuotaModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(dlStorageQuotaModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					dlStorageQuotaModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(dlStorageQuotaModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return DLStorageQuotaImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return DLStorageQuotaTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		DLStorageQuotaModelImpl dlStorageQuotaModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = dlStorageQuotaModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = dlStorageQuotaModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}