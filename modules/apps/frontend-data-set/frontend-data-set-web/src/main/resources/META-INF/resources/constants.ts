/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const DEFAULT_FETCH_HEADERS = {
	'Accept': 'application/json',
	'Accept-Language': Liferay.ThemeDisplay.getBCP47LanguageId(),
	'Content-Type': 'application/json',
};
export const FDS_ARRAY_FIELD_NAME_DELIMITER: string = '[]';
export const FDS_ARRAY_FIELD_NAME_PARENT_SUFFIX: string = `${FDS_ARRAY_FIELD_NAME_DELIMITER}*`;
export const FDS_NESTED_FIELD_NAME_DELIMITER: string = '.';
export const FDS_NESTED_FIELD_NAME_PARENT_SUFFIX: string = `${FDS_NESTED_FIELD_NAME_DELIMITER}*`;
