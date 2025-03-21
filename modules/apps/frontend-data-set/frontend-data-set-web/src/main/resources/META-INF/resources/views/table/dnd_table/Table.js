/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';
import React, {useContext, useLayoutEffect, useRef} from 'react';

import TableContext from './TableContext';

function Table({children, className}) {
	const {draggingColumnName, isFixed, updateTableWidth} =
		useContext(TableContext);

	const dndTableRef = useRef(null);

	useLayoutEffect(() => {
		const tableWidth = dndTableRef.current.getBoundingClientRect().width;
		updateTableWidth(tableWidth);
	}, [updateTableWidth]);

	return (
		<div
			className={classNames(
				'dnd-table',
				{
					'fixed': isFixed,
					'is-dragging': draggingColumnName !== null,
				},
				className
			)}
			ref={dndTableRef}
		>
			{children}
		</div>
	);
}

export default Table;
