/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const FULL = 'full';
const LARGE = 'lg';
const SMALL = 'sm';

export const CLAY_MODAL_SIZES_MAP = {
	DEFAULT: null,
	[FULL]: 'full-screen',
	[LARGE]: 'lg',
	[SMALL]: 'sm',
};

export const MODAL_HEIGHT_MAP = {
	INITIAL: '450px',
	[LARGE]: '450px',
	[SMALL]: '350px',
};

export const INITIAL_MODAL_SIZE = LARGE;
