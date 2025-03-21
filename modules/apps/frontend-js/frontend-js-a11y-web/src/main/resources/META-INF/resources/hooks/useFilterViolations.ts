/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useMemo, useReducer} from 'react';

import type {NodeViolations, RuleRaw, Violations} from './useA11y';

export const TYPES = {
	ADD_FILTER: 'ADD_FILTER',
	REMOVE_FILTER: 'REMOVE_FILTER',
} as const;

type TAction = {
	payload: {key: keyof RuleRaw; value: string};
	type: keyof typeof TYPES;
};

type TState = {
	filters: Record<keyof RuleRaw, Array<string>>;
};

const initialState = {
	filters: {},
} as TState;

function reducer(state: TState, action: TAction) {
	const {key, value} = action.payload;

	switch (action.type) {
		case TYPES.ADD_FILTER:
			return {
				...state,
				filters: {
					...state.filters,
					[key]: [...(state.filters[key] ?? []), value],
				},
			};
		case TYPES.REMOVE_FILTER: {
			const values = state.filters[key].filter((item) => item !== value);

			const filters = {...state.filters};

			if (values.length) {
				filters[key] = values;
			}
			else {
				delete filters[key];
			}

			return {
				...state,
				filters,
			};
		}
		default:
			return state;
	}
}

export function useFilterViolations(value: Violations) {
	const [{filters}, dispatch] = useReducer(reducer, initialState);

	const rules = useMemo(() => {
		if (!Object.keys(filters).length) {
			return value.rules;
		}

		return Object.values(value.rules).reduce(
			(prev, props) => {
				const isVisible = (
					Object.keys(filters) as Array<keyof RuleRaw>
				).some((key) => {
					switch (typeof props[key]) {
						case 'string':
							return filters[key].includes(props[key] as string);
						case 'object':
							return (props[key] as Array<string>).some((value) =>
								filters[key].includes(value)
							);
						default:
							return false;
					}
				});

				if (isVisible) {
					const nodes = filters.nodes
						? props.nodes.filter((node) =>
								filters.nodes?.includes(node)
							)
						: props.nodes;

					prev[props.id] = {
						...props,
						nodes,
					};
				}

				return prev;
			},
			{} as Violations['rules']
		);
	}, [filters, value]);

	const nodes = useMemo(() => {
		if (!Object.keys(filters).length) {
			return value.nodes;
		}

		const newNodes: Record<string, NodeViolations> = {};

		for (const property in value.nodes) {
			const rulesResults = value.nodes[property];
			const rulesIds = Object.keys(rulesResults);

			const results = Object.values(rulesResults).reduce(
				(prevResults, results, index) => {
					const ruleId = rulesIds[index];

					if (
						rules[ruleId] &&
						rules[ruleId].nodes.includes(property)
					) {
						prevResults[ruleId] = results;
					}

					return prevResults;
				},
				{} as NodeViolations
			);

			if (Object.keys(results).length) {
				newNodes[property] = results;
			}
		}

		return newNodes;
	}, [filters, rules, value.nodes]);

	return [{filters, violations: {nodes, rules}}, dispatch] as const;
}
