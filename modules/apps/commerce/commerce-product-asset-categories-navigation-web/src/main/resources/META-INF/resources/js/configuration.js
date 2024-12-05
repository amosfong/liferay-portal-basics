/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

export default function ({namespace}) {
	async function getTaxonomyCategoryExternalReferenceCode(
		taxonomyCategoryId
	) {
		const url = new URL(
			`${themeDisplay.getPathContext()}/o/headless-admin-taxonomy/v1.0/taxonomy-categories/${taxonomyCategoryId}`,
			themeDisplay.getPortalURL()
		);

		return await fetch(url, {
			headers: {
				'Accept': 'application/json',
				'Accept-Language': Liferay.ThemeDisplay.getBCP47LanguageId(),
				'Content-Type': 'application/json',
			},
			method: 'GET',
		})
			.then((response) => {
				if (response.ok) {
					return response.json();
				}

				return '';
			})
			.then((data) => {
				return data ? data.externalReferenceCode : '';
			});
	}

	const assetVocabularyContainer = document.getElementById(
		`${namespace}assetVocabularyContainer`
	);
	const form = document.getElementById(`${namespace}fm`);
	const preferencesRootAssetCategoryExternalReferenceCode =
		document.getElementById(
			`${namespace}preferencesRootAssetCategoryExternalReferenceCode`
		);
	const preferencesUseCategoryFromRequest = document.getElementById(
		`${namespace}preferencesUseCategoryFromRequest`
	);
	const preferencesUseRootCategory = document.getElementById(
		`${namespace}preferencesUseRootCategory`
	);
	const rootAssetCategoryContainer = document.getElementById(
		`${namespace}rootAssetCategoryContainer`
	);
	const rootAssetCategoryExternalReferenceCodeInputContainer =
		document.getElementById(
			`${namespace}rootAssetCategoryExternalReferenceCodeInputContainer`
		);
	const submitButton = document.getElementById(`${namespace}submitButton`);

	submitButton.addEventListener('click', async () => {
		if (preferencesUseRootCategory.checked) {
			const assetCategoryIdsKey = Object.keys(form.elements).filter(
				(input) => input.includes('assetCategoriesSelectorCategoryId')
			);

			for (let i = 0; i < assetCategoryIdsKey.length; i++) {
				const assetCategoryId = assetCategoryIdsKey[i];

				if (form.elements[assetCategoryId].value) {
					preferencesRootAssetCategoryExternalReferenceCode.value =
						(await getTaxonomyCategoryExternalReferenceCode(
							Number(form.elements[assetCategoryId].value)
						)) || '';
					break;
				}
			}
		}
		submitForm(form);
	});

	preferencesUseRootCategory.addEventListener('change', (event) => {
		if (event.target.checked) {
			assetVocabularyContainer.classList.add('hide');

			rootAssetCategoryContainer.classList.remove('hide');
		}
		else {
			rootAssetCategoryContainer.classList.add('hide');

			assetVocabularyContainer.classList.remove('hide');
		}
	});

	preferencesUseCategoryFromRequest.addEventListener('change', (event) => {
		if (event.target.checked) {
			rootAssetCategoryExternalReferenceCodeInputContainer.classList.add(
				'hide'
			);
		}
		else {
			rootAssetCategoryExternalReferenceCodeInputContainer.classList.remove(
				'hide'
			);
		}
	});
}
