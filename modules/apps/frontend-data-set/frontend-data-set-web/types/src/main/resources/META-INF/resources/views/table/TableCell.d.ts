/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/// <reference types="react" />

export declare function InlineEditInputRenderer({
	itemId,
	options,
	rootPropertyName,
	type,
	value,
	valuePath,
	...otherProps
}: any): JSX.Element;
declare function TableCell({
	actions,
	field,
	itemData,
	itemId,
	itemInlineChanges,
	rootPropertyName,
	value,
	valuePath,
}: any): JSX.Element | null;
export default TableCell;
