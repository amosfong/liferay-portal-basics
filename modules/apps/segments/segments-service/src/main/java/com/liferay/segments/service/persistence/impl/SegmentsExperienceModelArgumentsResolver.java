/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.segments.model.SegmentsExperienceTable;
import com.liferay.segments.model.impl.SegmentsExperienceImpl;
import com.liferay.segments.model.impl.SegmentsExperienceModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from SegmentsExperience.
 *
 * @author Eduardo Garcia
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.segments.model.impl.SegmentsExperienceImpl",
		"table.name=SegmentsExperience"
	},
	service = ArgumentsResolver.class
)
public class SegmentsExperienceModelArgumentsResolver
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

		SegmentsExperienceModelImpl segmentsExperienceModelImpl =
			(SegmentsExperienceModelImpl)baseModel;

		long columnBitmask = segmentsExperienceModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				segmentsExperienceModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					segmentsExperienceModelImpl.getColumnBitmask(columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(SegmentsExperiencePersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				segmentsExperienceModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return SegmentsExperienceImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return SegmentsExperienceTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		SegmentsExperienceModelImpl segmentsExperienceModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					segmentsExperienceModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = segmentsExperienceModelImpl.getColumnValue(
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

		orderByColumnsBitmask |= SegmentsExperienceModelImpl.getColumnBitmask(
			"priority");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}