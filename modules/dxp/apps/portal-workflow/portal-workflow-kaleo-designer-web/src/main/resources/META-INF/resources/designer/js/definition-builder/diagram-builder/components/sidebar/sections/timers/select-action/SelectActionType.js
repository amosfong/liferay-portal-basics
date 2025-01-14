/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayForm, {ClaySelect} from '@clayui/form';
import React, {useContext} from 'react';

import {DefinitionBuilderContext} from '../../../../../../DefinitionBuilderContext';
import {DiagramBuilderContext} from '../../../../../DiagramBuilderContext';

const options = [
	{
		actionType: 'timerActions',
		disabled: false,
		label: Liferay.Language.get('action'),
	},
	{
		actionType: 'timerNotifications',
		disabled: false,
		label: Liferay.Language.get('notification'),
	},
	{
		actionType: 'reassignments',
		disabled: false,
		label: Liferay.Language.get('reassignment'),
	},
];

const SelectActionType = ({
	actionSectionsIndex,
	actionType,
	reassignments,
	setActionSections,
}) => {
	const {
		allowScriptContentToBeExecutedOrIncluded,
		hadGroovyOrJavaScriptBefore,
	} = useContext(DefinitionBuilderContext);
	const {functionActionExecutors} = useContext(DiagramBuilderContext);

	options[2].disabled = reassignments;
	function handleChange(value) {
		setActionSections((previousSections) => {
			const updatedSections = [...previousSections];

			updatedSections.splice(actionSectionsIndex, 1, {
				actionType: value,
				identifier: `${Date.now()}-${actionSectionsIndex}`,
			});

			return updatedSections;
		});
	}

	const getActionTypeOptions = () => {
		if (
			!allowScriptContentToBeExecutedOrIncluded &&
			!hadGroovyOrJavaScriptBefore &&
			!functionActionExecutors.length
		) {
			return options.filter(
				(option) => option.actionType !== 'timerActions'
			);
		}

		return options;
	};

	return (
		<ClayForm.Group>
			<label htmlFor="action-type">{Liferay.Language.get('type')}</label>

			<ClaySelect
				aria-label="Select"
				defaultValue={actionType}
				id="action-type"
				onChange={(event) => {
					handleChange(event.target.value);
				}}
			>
				{getActionTypeOptions().map((item) => (
					<ClaySelect.Option
						disabled={item.disabled}
						key={item.actionType}
						label={item.label}
						value={item.actionType}
					/>
				))}
			</ClaySelect>
		</ClayForm.Group>
	);
};

export default SelectActionType;
