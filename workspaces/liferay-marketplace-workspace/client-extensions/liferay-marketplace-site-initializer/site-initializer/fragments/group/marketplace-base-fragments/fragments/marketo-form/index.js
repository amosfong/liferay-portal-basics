/* eslint-disable no-undef */

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const marketoCallback = function (form) {
	const formEl = form.getFormElem()[0];
	const arrayify = getSelection.call.bind([].slice);

	const styledElements = arrayify(formEl.querySelectorAll('[style]')).concat(
		formEl
	);

	formEl.querySelectorAll('style').forEach((element) => element.remove());
	styledElements.forEach((element) => element.removeAttribute('style'));

	const styleSheets = arrayify(document.styleSheets);

	styleSheets.forEach((stylesheet) => {
		if (
			[mktoForms2BaseStyle, mktoForms2ThemeStyle].indexOf(
				stylesheet.ownerNode
			) !== -1 ||
			formEl.contains(stylesheet.ownerNode)
		) {
			stylesheet.disabled = true;
		}
	});

	const buttonElem = form.getFormElem().find('button.mktoButton');

	if (buttonElem) {
		buttonElem.html(Liferay.Language.get(configuration.submitButtonText));
	}

	Liferay.on(`submit-marketo-form/${configuration.formId}`, (event) => {
		const formData = event.details[0];

		form.vals(formData);
		form.submit();
	});
};

MktoForms2.loadForm(
	configuration.podId,
	configuration.munchkinId,
	configuration.formId,
	marketoCallback
);