/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function getTruncateText(text: string, maxLength = 500) {
	const truncateText = text.substring(0, maxLength);

	return text.length >= maxLength ? truncateText + '...' : text;
}
