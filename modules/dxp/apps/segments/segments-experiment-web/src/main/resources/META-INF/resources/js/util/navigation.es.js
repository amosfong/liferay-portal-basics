/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {navigate} from 'frontend-js-web';

const EXPERIENCE_ID_URL_KEY = 'segmentsExperienceId';

const EXPERIENCE_KEY_URL_KEY = 'segmentsExperienceKey';
const EXPERIMENT_KEY_URL_KEY = 'segmentsExperimentKey';
const EXPERIMENT_ACTION_URL_KEY = 'segmentsExperimentAction';

/**
 * Generates standard navigation between Experiences
 * Cleans alternative queryParams externally used to navigate in Experiments
 *
 * @export
 * @param {string} experienceId
 * @param {string} [baseUrl=window.location.href]
 */
export function navigateToExperience({
	experienceId,
	baseUrl = window.location.href,
	params = [],
}) {
	const currentUrl = new URL(baseUrl);
	const urlQueryString = currentUrl.search;
	const urlSearchParams = new URLSearchParams(urlQueryString);

	urlSearchParams.delete(EXPERIENCE_KEY_URL_KEY);
	urlSearchParams.delete(EXPERIMENT_KEY_URL_KEY);

	urlSearchParams.set(EXPERIENCE_ID_URL_KEY, experienceId);

	if (params.length) {
		params.forEach((param) => {
			urlSearchParams.set(param.key, param.value);
		});
	}

	currentUrl.search = urlSearchParams.toString();

	const newUrl = currentUrl.toString();

	navigate(newUrl);
}

export function getSegmentsExperimentParameter(
	paramName = EXPERIMENT_ACTION_URL_KEY
) {
	const url = new URL(window.location.href);
	const action = url.searchParams.get(paramName);

	if (!action) {
		return null;
	}

	url.searchParams.delete(paramName);
	window.history.replaceState(null, null, decodeURIComponent(url.href));

	return action;
}
