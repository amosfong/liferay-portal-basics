/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useRef, useState} from 'react';

import SaveTemplateModal from './SaveTemplateModal';
import {
	CSV_FORMAT,
	DISALLOWED_CSV_ENTITY_TYPES,
	EXPORT_FILE_FORMAT_SELECTED_EVENT,
	FILE_EXTENSION_EVENT,
	SCHEMA_SELECTED_EVENT,
	TEMPLATE_SELECTED_EVENT,
	TEMPLATE_SOILED_EVENT,
} from './constants';

function SaveTemplate({
	evaluateForm,
	formIsValid,
	formSaveAsTemplateDataQuerySelector,
	formSaveAsTemplateURL,
	portletNamespace,
	type,
}) {
	const [disable, setDisable] = useState(true);
	const [modalVisible, setModalVisible] = useState(false);

	const {observer, onClose} = useModal({
		onClose: () => setModalVisible(false),
	});

	const useTemplateMappingRef = useRef();

	const onButtonClick = useCallback(() => {
		if (type === 'import') {
			evaluateForm();

			if (formIsValid) {
				setModalVisible(true);
			}
		}
		else {
			setModalVisible(true);
		}
	}, [evaluateForm, formIsValid, type]);

	useEffect(() => {
		const handleExportFileFormatUpdated = ({
			selectedExportFileFormat,
			selectedSchema,
		}) => {
			if (
				selectedExportFileFormat === CSV_FORMAT.toUpperCase() &&
				DISALLOWED_CSV_ENTITY_TYPES.includes(selectedSchema)
			) {
				setDisable(true);
			}
		};

		function handleFileExtensionUpdate({entityType, fileExtension}) {
			if (
				fileExtension === CSV_FORMAT &&
				DISALLOWED_CSV_ENTITY_TYPES.includes(entityType)
			) {
				setDisable(true);
			}
		}

		function handleSchemaChange({schema}) {
			if (schema && !useTemplateMappingRef.current) {
				setDisable(false);
			}
			else if (!schema) {
				setDisable(true);
			}
		}

		function handleTemplateSelection({template}) {
			setDisable(!!template);

			useTemplateMappingRef.current = !!template;
		}

		function handleTemplateSoiled() {
			useTemplateMappingRef.current = false;

			setDisable(false);
		}

		Liferay.on(
			EXPORT_FILE_FORMAT_SELECTED_EVENT,
			handleExportFileFormatUpdated
		);
		Liferay.on(SCHEMA_SELECTED_EVENT, handleSchemaChange);
		Liferay.on(FILE_EXTENSION_EVENT, handleFileExtensionUpdate);
		Liferay.on(TEMPLATE_SELECTED_EVENT, handleTemplateSelection);
		Liferay.on(TEMPLATE_SOILED_EVENT, handleTemplateSoiled);

		return () => {
			Liferay.detach(
				EXPORT_FILE_FORMAT_SELECTED_EVENT,
				handleExportFileFormatUpdated
			);
			Liferay.detach(FILE_EXTENSION_EVENT, handleFileExtensionUpdate);
			Liferay.detach(SCHEMA_SELECTED_EVENT, handleSchemaChange);
			Liferay.detach(TEMPLATE_SELECTED_EVENT, handleTemplateSelection);
			Liferay.detach(TEMPLATE_SOILED_EVENT, handleTemplateSoiled);
		};
	}, [portletNamespace]);

	return (
		<span className="mr-3">
			<ClayButton
				disabled={disable}
				displayType="secondary"
				id={`${portletNamespace}saveTemplate`}
				onClick={onButtonClick}
				type="button"
			>
				{Liferay.Language.get('save-as-template')}
			</ClayButton>

			{modalVisible && (
				<SaveTemplateModal
					closeModal={onClose}
					formDataQuerySelector={formSaveAsTemplateDataQuerySelector}
					formSubmitURL={formSaveAsTemplateURL}
					namespace={portletNamespace}
					observer={observer}
				/>
			)}
		</span>
	);
}

SaveTemplate.propTypes = {
	forceDisable: PropTypes.bool,
	formSaveAsTemplateDataQuerySelector: PropTypes.string.isRequired,
	formSaveAsTemplateURL: PropTypes.string.isRequired,
	portletNamespace: PropTypes.string.isRequired,
	type: PropTypes.oneOf(['import', 'export']),
};

export default SaveTemplate;
