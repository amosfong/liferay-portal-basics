/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

function addExperience(state, experience) {
	if (process.env.NODE_ENV !== 'production') {
		if (experience.priority === undefined) {
			console.warn(
				'An Experience without priority has been added to the state'
			);
		}
		if (experience.hasLockedSegmentsExperiment === undefined) {
			console.warn(
				'An Experience without a `hasLockedSegmentsExperiment` key has been added to the state'
			);
		}
		if (
			state.availableSegmentsExperiences[
				experience.segmentsExperienceId
			] !== undefined
		) {
			console.warn(
				'An existing Experience is trying to be added to the state. Updated instead'
			);
		}
	}

	return {
		...state,
		availableSegmentsExperiences: {
			...state.availableSegmentsExperiences,
			[experience.segmentsExperienceId]: experience,
		},
	};
}

/**
 * Sets `lockedSegmentsExperience` in the state, depending on the Experience
 * @param {object} state
 * @param {string | null} state.selectedSidebarPanelId
 * @param {object} experience
 * @param {boolean} experience.hasLockedSegmentsExperiment
 * @return {object} nextState
 */
function setExperienceLock(state, experience) {
	const lockedSegmentsExperience = experience.hasLockedSegmentsExperiment;

	// TODO selectedSidebarPanelId

	return {
		...state,
		lockedSegmentsExperience,
	};
}

/**
 * Stores a the layout data of a new experience in layoutDataList
 * @param {object} state
 * @param {Array<{segmentsExperienceId: string}>} state.layoutDataList
 * @param {object} state.layoutData
 * @param {string} state.defaultSegmentsExperienceId
 * @param {string} segmentsExperienceId The segmentsExperience id that owns this LayoutData
 * @param {string} layoutData The new LayoutData to store
 * @returns {object}
 */
function storeNewLayoutData(state, segmentsExperienceId, layoutData) {
	const nextState = state;

	nextState.layoutDataList.push({
		layoutData,
		segmentsExperienceId,
	});

	return nextState;
}

function selectExperience(state, experienceId) {
	let nextState = state;

	if (!state.loadedSegmentsExperiences?.includes(experienceId)) {
		nextState = {
			...nextState,
			loadedSegmentsExperiences: [
				...(nextState.loadedSegmentsExperiences || []),
				experienceId,
			],
		};
	}

	return {
		...nextState,
		segmentsExperienceId: experienceId,
	};
}

function switchLayoutData(state, {currentExperienceId, targetExperienceId}) {
	let nextState = state;
	const {layoutData: prevLayoutData, layoutDataList} = nextState;

	const layoutDataItem = state.layoutDataList.find(
		(layoutDataItem) =>
			layoutDataItem.segmentsExperienceId === targetExperienceId
	);

	nextState = {
		...nextState,
		layoutData: layoutDataItem.layoutData,
		layoutDataList: layoutDataList.map((layoutDataItem) => {
			if (currentExperienceId === layoutDataItem.segmentsExperienceId) {
				return {
					...layoutDataItem,
					layoutData: prevLayoutData,
				};
			}

			return layoutDataItem;
		}),
	};

	return nextState;
}

function deleteExperienceById(state, segmentsExperienceId) {
	const experiences = {...state.availableSegmentsExperiences};

	delete experiences[segmentsExperienceId];

	return {
		...state,
		availableSegmentsExperiences: experiences,
	};
}

function removeLayoutDataItemById(state, segmentsExperienceId) {
	const layoutDataList = state.layoutDataList;

	const updatedLayoutDataList = layoutDataList.filter(
		(layoutDataItem) =>
			layoutDataItem.segmentsExperienceId !== segmentsExperienceId
	);

	return {
		...state,
		layoutDataList: updatedLayoutDataList,
	};
}

/**
 * Attaches to the state a the experiment status of a experience
 */
function setExperimentStatus(state, experienceId) {
	const selectedExperience = state.availableSegmentsExperiences[experienceId];

	return {
		...state,
		segmentsExperimentStatus: selectedExperience.segmentsExperimentStatus,
	};
}

export {
	addExperience,
	deleteExperienceById,
	removeLayoutDataItemById,
	selectExperience,
	setExperienceLock,
	setExperimentStatus,
	storeNewLayoutData,
	switchLayoutData,
};
