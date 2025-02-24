/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import {DEFAULT_LANGUAGE} from '../../../../../source-builder/constants';
import {DiagramBuilderContext} from '../../../../DiagramBuilderContext';
import BaseSourceCode from '../shared-components/BaseSourceCode';

const TimerSourceCode = () => {
	const {scriptedReassignmentTimerIndex, selectedItem, setSelectedItem} =
		useContext(DiagramBuilderContext);

	const scriptSourceCode =
		selectedItem.data.taskTimers?.reassignments?.[
			scriptedReassignmentTimerIndex
		]?.script;

	const updateTimer = (editor) => {
		if (editor.getData().trim() !== '') {
			setSelectedItem((previousValue) => ({
				...previousValue,
				data: {
					...previousValue.data,
					taskTimers: {
						...previousValue.data.taskTimers,
						reassignments:
							previousValue.data.taskTimers.reassignments.map(
								(reassignment, index) => {
									if (
										index === scriptedReassignmentTimerIndex
									) {
										return {
											assignmentType: [
												'scriptedAssignment',
											],
											script: [editor.getData()],
											scriptLanguage: [DEFAULT_LANGUAGE],
										};
									}
									else {
										return reassignment;
									}
								}
							),
					},
				},
			}));
		}
	};

	return (
		<BaseSourceCode
			scriptSourceCode={scriptSourceCode}
			updateSelectedItem={updateTimer}
		/>
	);
};

export default TimerSourceCode;
