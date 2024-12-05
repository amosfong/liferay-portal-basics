/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayIconSpriteContext} from '@clayui/icon';
import React from 'react';
import ReactDOM from 'react-dom';

import ContentTypeModal from '../components/ContentTypeModal';
import {MODAL_TYPES} from '../constants/modalTypes';

let container;

/**
 * Opens a modal that will let the user choose the title
 * and the mapping types of a displaoy page.
 *
 * @param {string} param.displayPageName
 * @param {string} param.formSubmitURL
 * @param {array} param.mappingTypes
 * @param {string} param.namespace
 * @param {string} param.spritemap
 * @param {string} param.title
 */
export default function openContentTypeModal({
	description,
	disableWarning,
	displayPageName,
	formSubmitURL,
	mappingTypes,
	namespace,
	selectedSubtype,
	selectedType,
	spritemap,
	title,
	type = MODAL_TYPES.create,
	warningMessage,
}) {
	if (container) {
		cleanUp();
	}

	container = document.createElement('div');

	document.body.appendChild(container);

	// eslint-disable-next-line @liferay/portal/no-react-dom-render
	ReactDOM.render(
		<ClayIconSpriteContext.Provider value={spritemap}>
			<ContentTypeModal
				description={description}
				disableWarning={disableWarning}
				displayPageName={displayPageName}
				formSubmitURL={formSubmitURL}
				mappingTypes={mappingTypes}
				namespace={namespace}
				onClose={cleanUp}
				selectedSubtype={selectedSubtype}
				selectedType={selectedType}
				title={title}
				type={type}
				warningMessage={warningMessage}
			/>
		</ClayIconSpriteContext.Provider>,
		container
	);

	Liferay.once('destroyPortlet', cleanUp);
}

function cleanUp() {
	if (container) {
		ReactDOM.unmountComponentAtNode(container);
		document.body.removeChild(container);

		container = null;
	}
}
