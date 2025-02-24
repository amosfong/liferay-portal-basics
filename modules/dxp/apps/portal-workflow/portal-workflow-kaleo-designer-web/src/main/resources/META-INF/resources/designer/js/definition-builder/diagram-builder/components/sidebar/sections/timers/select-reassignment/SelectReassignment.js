/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayForm, {ClaySelect} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import React, {useContext, useEffect} from 'react';

import {DefinitionBuilderContext} from '../../../../../../DefinitionBuilderContext';

const options = [
	{
		assignmentType: 'assetCreator',
		label: Liferay.Language.get('asset-creator'),
	},
	{
		assignmentType: 'resourceActions',
		label: Liferay.Language.get('resource-actions'),
	},
	{
		assignmentType: 'roleId',
		label: Liferay.Language.get('role'),
	},
	{
		assignmentType: 'user',
		label: Liferay.Language.get('user'),
	},
	{
		assignmentType: 'roleType',
		label: Liferay.Language.get('role-type'),
	},
	{
		assignmentType: 'scriptedReassignment',
		label: Liferay.Language.get('scripted-reassignment'),
	},
];

const SelectReassignment = ({
	currentAssignmentType,
	setSection,
	setSubSections,
}) => {
	const {
		allowScriptContentToBeExecutedOrIncluded,
		hadGroovyOrJavaScriptBefore,
	} = useContext(DefinitionBuilderContext);

	useEffect(() => {
		if (!currentAssignmentType) {
			setSection('assetCreator');
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const getReassignmentTypeOptions = () => {
		if (
			!allowScriptContentToBeExecutedOrIncluded &&
			!hadGroovyOrJavaScriptBefore
		) {
			return options.filter(
				(option) => option.assignmentType !== 'scriptedReassignment'
			);
		}

		return options;
	};

	return (
		<ClayForm.Group>
			<label htmlFor="reassignment-type">
				{Liferay.Language.get('reassignment-type')}

				<span
					className="ml-2"
					title={Liferay.Language.get('select-the-reassignment-type')}
				>
					<ClayIcon
						className="text-muted"
						symbol="question-circle-full"
					/>
				</span>
			</label>

			<ClaySelect
				aria-label="Select"
				defaultValue={currentAssignmentType}
				id="reassignment-type"
				onChange={(event) => {
					setSection(event.target.value);
					setSubSections([{identifier: `${Date.now()}-0`}]);
				}}
			>
				{getReassignmentTypeOptions().map(
					({assignmentType, disabled, label}) => (
						<ClaySelect.Option
							disabled={disabled}
							key={assignmentType}
							label={label}
							value={assignmentType}
						/>
					)
				)}
			</ClaySelect>
		</ClayForm.Group>
	);
};

export {SelectReassignment};
