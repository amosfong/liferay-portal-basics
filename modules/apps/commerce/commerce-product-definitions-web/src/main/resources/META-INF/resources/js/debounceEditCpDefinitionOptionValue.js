/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {slugify} from 'commerce-frontend-js';
import {debounce} from 'frontend-js-web';

export default function main({namespace}) {
	const form = document.getElementById(
		`${namespace}cpDefinitionOptionValueRelfm`
	);

	const keyInput = form.querySelector(`#${namespace}key`);
	const nameInput = form.querySelector(`#${namespace}optionValueName`);

	const handleOnNameInput = function () {
		keyInput.value = slugify(nameInput.value);
	};

	nameInput.addEventListener('input', debounce(handleOnNameInput, 200));
}