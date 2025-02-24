/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function reducer(state, action) {
	switch (action.type) {
		case 'ADD_EXPERIMENT':
			return {
				...state,
				experiment: action.payload,
				viewExperimentDetailsURL: action.payload.detailsURL,
			};

		case 'ADD_VARIANT':
			return {
				...state,
				variants: [...state.variants, action.payload],
			};

		case 'ARCHIVE_EXPERIMENT':
			return {
				...state,
				errors: {},
				experiment: null,
				variants: [],
				viewExperimentDetailsURL: undefined,
			};

		case 'CREATE_EXPERIMENT_FINISH':
			return {
				...state,
				createExperimentModal: {
					active: false,
				},
			};

		case 'CREATE_EXPERIMENT_START':
			return _createExperimentStart(state, action.payload);

		case 'DELETE_EXPERIMENT':
			return {
				...state,
				deleteExperimentModal: {
					active: action.payload.active,
				},
			};

		case 'PUBLISH_EXPERIMENT':
			return {
				...state,
				publishExperimentModal: action.payload,
			};

		case 'TERMINATE_EXPERIMENT':
			return {
				...state,
				terminateExperimentModal: {
					active: action.payload.active,
				},
			};

		case 'EDIT_EXPERIMENT':
			return _editExperiment(state, action.payload);

		case 'EDIT_EXPERIMENT_FINISH':
			return {
				...state,
				editExperimentModal: {
					active: false,
				},
			};

		case 'EDIT_EXPERIMENT_START':
			return _editExperienceStart(state, action.payload);

		case 'REVIEW_AND_RUN_EXPERIMENT':
			return _reviewAndRunExperiment(state);

		case 'REVIEW_AND_RUN_EXPERIMENT_FINISH':
			return {
				...state,
				reviewExperimentModal: {
					active: false,
				},
			};

		case 'REVIEW_CLICK_TARGET_ELEMENT':
			return _reviewClickTargetElement(state);

		case 'REVIEW_VARIANTS':
			return _reviewVariants(state);

		case 'RUN_EXPERIMENT':
			return _runExperiment(state, action.payload);

		case 'UPDATE_SEGMENTS_EXPERIMENT_TARGET':
			return _editExperiment(state, action.payload);

		case 'UPDATE_EXPERIMENT_STATUS':
			return _updateExperimentStatus(state, action.payload);

		case 'UPDATE_VARIANT':
			return {
				...state,
				variants: state.variants.map((variant) => {
					if (
						action.payload.variantId ===
						variant.segmentsExperimentRelId
					) {
						return {
							...variant,
							...action.payload.changes,
						};
					}

					return variant;
				}),
			};

		case 'UPDATE_VARIANTS':
			return {
				...state,
				variants: action.payload,
			};

		default:
			return state;
	}
}

function _createExperimentStart(state, experimentModalState = {}) {
	const {selectedExperienceId} = state;
	const {description, error, name, segmentsExperienceId} =
		experimentModalState;

	return {
		...state,
		createExperimentModal: {
			active: true,
			description,
			error,
			name,
			segmentsExperienceId: segmentsExperienceId
				? segmentsExperienceId
				: selectedExperienceId,
		},
	};
}

function _editExperiment(state, updatedValues) {
	return {
		...state,
		experiment: {...state.experiment, ...updatedValues},
	};
}

function _editExperienceStart(state, experiementModalState = {}) {
	const {experiment} = state;

	const {
		description,
		editable,
		error,
		goal,
		name,
		segmentsEntryName,
		segmentsExperienceId,
		segmentsExperimentId,
		status,
	} = experiementModalState;

	return {
		...state,
		editExperimentModal: {
			active: true,
			description: description ? description : experiment.description,
			editable: editable ? editable : experiment.editable,
			error: error ? error : experiment.error,
			goal: goal ? goal : experiment.goal,
			name: name ? name : experiment.name,
			segmentsEntryName: segmentsEntryName
				? segmentsEntryName
				: experiment.segmentsEntryName,
			segmentsExperienceId: segmentsExperienceId
				? segmentsExperienceId
				: experiment.segmentsExperienceId,
			segmentsExperimentId: segmentsExperimentId
				? segmentsExperimentId
				: experiment.segmentsExperimentId,
			status: status ? status : experiment.status,
		},
	};
}

function _reviewAndRunExperiment(state) {
	const newState = {...state};

	newState.errors = {
		...newState.errors,
		clickTargetError:
			state.experiment.goal.value === 'click' &&
			!state.experiment.goal.target,
		variantsError: newState.variants.length <= 1,
	};

	const errors = Object.entries(newState.errors);

	for (let i = 0; i < errors.length; i++) {
		const [, hasError] = errors[i];

		if (hasError) {
			return {
				...newState,
				reviewExperimentModal: {
					active: false,
				},
			};
		}
	}

	return {
		...newState,
		reviewExperimentModal: {
			active: true,
		},
	};
}

function _reviewClickTargetElement(state) {
	const newState = {...state};

	newState.errors = {
		...newState.errors,
		clickTargetError:
			state.experiment.goal.value === 'click' &&
			!state.experiment.goal.target,
	};

	return newState;
}

function _reviewVariants(state) {
	const newState = {...state};

	newState.errors = {
		...newState.errors,
		variantsError: state.variants.length <= 1,
	};

	return newState;
}

function _runExperiment(state, {experiment, splitVariantsMap}) {
	const variants = state.variants.map((variant) => ({
		...variant,
		split: splitVariantsMap[variant.segmentsExperimentRelId],
	}));

	return {
		...state,
		error: {},
		experiment: {
			...state.experiment,
			...experiment,
		},
		variants,
	};
}

function _updateExperimentStatus(state, updatedValues) {
	return {
		...state,
		errors: {},
		experiment: {...state.experiment, ...updatedValues},
	};
}
