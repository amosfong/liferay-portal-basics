/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service.persistence.impl;

import com.liferay.fragment.model.FragmentEntryLinkTable;
import com.liferay.fragment.model.impl.FragmentEntryLinkImpl;
import com.liferay.fragment.model.impl.FragmentEntryLinkModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from FragmentEntryLink.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.fragment.model.impl.FragmentEntryLinkImpl",
		"table.name=FragmentEntryLink"
	},
	service = ArgumentsResolver.class
)
public class FragmentEntryLinkModelArgumentsResolver
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

		FragmentEntryLinkModelImpl fragmentEntryLinkModelImpl =
			(FragmentEntryLinkModelImpl)baseModel;

		long columnBitmask = fragmentEntryLinkModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(fragmentEntryLinkModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					fragmentEntryLinkModelImpl.getColumnBitmask(columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(FragmentEntryLinkPersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(fragmentEntryLinkModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return FragmentEntryLinkImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return FragmentEntryLinkTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		FragmentEntryLinkModelImpl fragmentEntryLinkModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					fragmentEntryLinkModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = fragmentEntryLinkModelImpl.getColumnValue(
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

		orderByColumnsBitmask |= FragmentEntryLinkModelImpl.getColumnBitmask(
			"classNameId");
		orderByColumnsBitmask |= FragmentEntryLinkModelImpl.getColumnBitmask(
			"classPK");
		orderByColumnsBitmask |= FragmentEntryLinkModelImpl.getColumnBitmask(
			"position");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}