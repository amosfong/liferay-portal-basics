/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessTable;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessImpl;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from KaleoProcess.
 *
 * @author Marcellus Tavares
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessImpl",
		"table.name=KaleoProcess"
	},
	service = ArgumentsResolver.class
)
public class KaleoProcessModelArgumentsResolver implements ArgumentsResolver {

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

		KaleoProcessModelImpl kaleoProcessModelImpl =
			(KaleoProcessModelImpl)baseModel;

		long columnBitmask = kaleoProcessModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(kaleoProcessModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					kaleoProcessModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(kaleoProcessModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return KaleoProcessImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return KaleoProcessTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		KaleoProcessModelImpl kaleoProcessModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = kaleoProcessModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = kaleoProcessModelImpl.getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}