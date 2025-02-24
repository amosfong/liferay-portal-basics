/* eslint-disable @liferay/portal/no-global-fetch */

/* eslint-disable no-undef */

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const containerId = configuration.containerId;
const fragmentName = 'raylife-tip-container';
const elementName = `.dynamic-web-content-${containerId}`;
const eventName = `${fragmentName}-${containerId}`;
const eventNameDismiss = `${fragmentName}-dismiss-${containerId}`;
const baseURL = window.location.origin + Liferay.ThemeDisplay.getPathContext();
const headlessBaseURL = `${baseURL}/o/headless-delivery/v1.0`;
const siteGroupId = Liferay.ThemeDisplay.getSiteGroupId();

/**
 *
 * @param {*} htmlBody HTML Content from Web Content rendered and put on DOM
 */

function setDynamicWebContent(htmlBody, customData = {}) {
	const keys = Object.keys(customData);
	const sanitizeHTMLRegex = /<[^>]*>?/gm;
	const sanitizeEmptyKeysRegex = /{{[^\s]*}}/g;

	let html = htmlBody;

	keys.forEach((key) => {
		html = html.replace(
			`{{${key}}}`,
			customData[key].replace(sanitizeHTMLRegex, '')
		);
	});

	html = html.replace(sanitizeEmptyKeysRegex, '');

	fragmentElement.querySelector(elementName).innerHTML = html;

	dismissButtonListener();
}

async function fetchHeadless(url, resolveAsJson = true) {
	const response = await fetch(`${headlessBaseURL}${url}`, {
		headers: {
			'Cache-Control': 'max-age=30, stale-while-revalidate=30',
			'x-csrf-token': Liferay.authToken,
		},
	});

	if (resolveAsJson) {
		return response.json();
	}

	return response;
}

function fetchWebContent(structuredContentId, contentTemplateId, customData) {
	fetchHeadless(
		`/structured-contents/${structuredContentId}/rendered-content/${contentTemplateId}`,
		false
	)
		.then((response) => response.text())
		.then((response) => setDynamicWebContent(response, customData));
}

/**
 * @description The heart of the application, the idea here's listen events
 * and then call and render the template into Fragment
 * @param {Number} templateId TIP Container Template
 * @param {Array<Object>} structuredContents Structures created for TIP Container
 */

function raylifeFragmentInteractiveListener(templateId, structuredContents) {
	window.addEventListener(eventName, (event) => {
		const data = event.detail.data;

		function getStructuredContentIdByName(templateName) {
			return structuredContents.find(
				({friendlyUrlPath, key}) =>
					friendlyUrlPath === templateName ||
					key === templateName.toUpperCase()
			)?.id;
		}

		if (data.hide) {
			fragmentElement.querySelector(elementName).innerHTML = '';
		}
		else if (
			typeof data === 'object' &&
			getStructuredContentIdByName(data.templateName)
		) {
			fetchWebContent(
				getStructuredContentIdByName(data.templateName),
				templateId,
				data.templateData
			);
		}
		else {
			console.warn(`Structure ${data.templateName} not found`);
		}
	});
}

function dismissButtonListener() {
	document.getElementById('dismiss').addEventListener('click', () =>
		window.dispatchEvent(
			new CustomEvent(eventNameDismiss, {
				bubbles: true,
				composed: true,
			})
		)
	);
}

async function workflow() {

	/**
	 * Get all Folders from Web Content and get his ID;
	 */

	const structuredContentFolders = await fetchHeadless(
		`/sites/${siteGroupId}/structured-content-folders`
	);

	const {id: raylifeFolderId} =
		structuredContentFolders.items.find(({name}) => name === 'Tip') || {};

	if (!raylifeFolderId) {
		return console.warn('Raylife TIP Folder not found');
	}

	/**
	 * Get all Web Contents Structure from Raylife folder
	 */
	const structuredContents = await fetchHeadless(
		`/structured-content-folders/${raylifeFolderId}/structured-contents`
	);

	/**
	 * Get all Web Content Templates in this Site
	 */

	const contentTemplates = await fetchHeadless(
		`/sites/${siteGroupId}/content-templates`
	);

	/**
	 * Filter the template by the name, to get Raylife Template ID
	 */

	const contentTemplate = contentTemplates.items.find(
		(template) => template.name === 'Tip Card'
	);

	raylifeFragmentInteractiveListener(
		contentTemplate?.id,
		structuredContents.items
	);
}

workflow();
