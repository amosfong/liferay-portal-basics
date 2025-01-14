/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayModal from '@clayui/modal';
import ClayPanel from '@clayui/panel';
import React, {useMemo} from 'react';

import ContentView from '../../../../../../shared/components/content-view/ContentView.es';
import RetryButton from '../../../../../../shared/components/list/RetryButton.es';
import {capitalize} from '../../../../../../shared/util/util.es';
import Card from './SelectTransitionStepCard.es';

function Body({data, setRetry, tasks}) {
	const {workflowTaskTransitions = []} = data;

	const taskSteps = useMemo(() => {
		const versions = {};

		tasks.forEach((task) => {
			if (
				versions[task.processVersion] &&
				versions[task.processVersion][task.name]
			) {
				versions[task.processVersion][task.name].push(task);
			}
			else if (versions[task.processVersion]) {
				versions[task.processVersion][task.name] = [task];
			}
			else {
				versions[task.processVersion] = {
					[task.name]: [task],
				};
			}
		});

		return versions;

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [tasks]);

	const versionedCards = useMemo(
		() =>
			Object.entries(taskSteps).map((array) => Object.entries(array[1])),
		[taskSteps]
	);

	const nextTransitions = useMemo(() => {
		const taskTransitions = {};

		workflowTaskTransitions.forEach(
			({transitions, workflowDefinitionVersion, workflowTaskName}) => {
				if (!taskTransitions[workflowDefinitionVersion]) {
					taskTransitions[workflowDefinitionVersion] = {
						[workflowTaskName]: transitions,
					};
				}
				else if (taskTransitions[workflowDefinitionVersion]) {
					taskTransitions[workflowDefinitionVersion][
						workflowTaskName
					] = transitions;
				}
			}
		);

		return Object.entries(taskTransitions).map((array) => array[1]);
	}, [workflowTaskTransitions]);

	const statesProps = {
		errorProps: {
			actionButton: (
				<RetryButton onClick={() => setRetry((retry) => retry + 1)} />
			),
			className: 'pb-7 pt-8',
			hideAnimation: true,
			message: Liferay.Language.get('failed-to-retrieve-tasks'),
			messageClassName: 'small',
		},
		loadingProps: {
			className: 'mb-4 mt-6 py-8',
			message: Liferay.Language.get('retrieving-all-transitions'),
			messageClassName: 'small',
		},
	};

	return (
		<ClayModal.Body>
			<ContentView {...statesProps}>
				{versionedCards.map((versionedCard, versionIndex) =>
					versionedCard.map(([taskLabel, tasks], cardIndex) => (
						<ClayPanel key={`${versionIndex}_${cardIndex}`}>
							<ClayPanel.Header>
								<div className="h4 mt-2">
									{capitalize(taskLabel)}
								</div>
							</ClayPanel.Header>

							<Body.Card
								cardIndex={`${versionIndex}_${cardIndex}`}
								nextTransitions={
									nextTransitions[versionIndex]
										? nextTransitions[versionIndex][
												taskLabel
											]
										: []
								}
								tasks={tasks}
							/>
						</ClayPanel>
					))
				)}
			</ContentView>
		</ClayModal.Body>
	);
}

Body.Card = Card;

export default Body;
