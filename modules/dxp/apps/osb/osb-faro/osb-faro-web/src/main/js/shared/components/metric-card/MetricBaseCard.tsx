import BaseCard from 'shared/components/base-card';
import Card from 'shared/components/Card';
import MetricChart from './MetricChart';
import MetricTabs from './MetricTabs';
import React, {createContext, useContext, useReducer} from 'react';
import {DocumentNode} from 'apollo-boost';
import {getMetricsChartData} from './util';
import {Interval, RangeSelectors, Router} from 'shared/types';
import {Metric} from './metrics';
import {RawFilters} from 'shared/util/filter';
import {ReportContainer} from '../download-report/DownloadPDFReport';

const initialState = {
	activeItemIndex: 0,
	chartDataMapFn: null,
	compareToPrevious: false,
	metrics: [],
	queries: {
		MetricQuery: null,
		name: '',
		TabsQuery: null
	},
	variables: () => {}
};

const MetricContext = createContext(initialState as any);

const MetricContextActions = createContext({
	changeActiveItemIndex: () => {},
	changeCompareToPrevious: () => {}
} as any);

export interface ICommonMetricProps {
	emptyDescription?: React.ReactNode;
	emptyTitle?: string;
	filters: RawFilters;
	interval: Interval;
	rangeSelectors: RangeSelectors;
}

export interface IGenericMetricBaseCardProps {
	emptyDescription?: React.ReactNode;
	emptyTitle?: string;
	label: string;
	legacyDropdownRangeKey?: boolean;
	reportContainer?: ReportContainer;
	showIntervals?: boolean;
}

interface IMetricBaseCardProps<TChartData>
	extends IGenericMetricBaseCardProps,
		React.HTMLAttributes<HTMLElement> {
	chartDataMapFn?: TChartData | typeof getMetricsChartData;
	metrics: Metric[];
	queries: {
		MetricQuery: (metricName: string) => DocumentNode;
		name: string;
		TabsQuery: DocumentNode;
	};
	variables: (commonVariables: {
		filters: Object;
		interval: Interval;
		rangeSelectors: RangeSelectors;
		router: Router;
	}) => void;
}

function MetricBaseCard<TChartData>({
	chartDataMapFn = getMetricsChartData,
	emptyDescription,
	emptyTitle,
	label,
	legacyDropdownRangeKey = false,
	id,
	metrics,
	queries,
	reportContainer,
	showIntervals = false,
	variables
}: IMetricBaseCardProps<TChartData>): React.ReactElement {
	const [state, dispatch] = useReducer(reducer, initialState);

	const actions = {
		changeActiveItemIndex: (activeItemIndex: number) => {
			dispatch({
				payload: activeItemIndex,
				type: Actions.UpdateActiveItemIndex
			});
		},
		changeCompareToPrevious: (compareToPrevious: boolean) => {
			dispatch({
				payload: compareToPrevious,
				type: Actions.UpdateCompareToPrevious
			});
		}
	};

	return (
		<MetricContext.Provider
			value={{
				...state,
				chartDataMapFn,
				metrics,
				queries,
				variables
			}}
		>
			<MetricContextActions.Provider value={actions}>
				<BaseCard
					className='analytics-metrics-card'
					id={id}
					label={label}
					legacyDropdownRangeKey={legacyDropdownRangeKey}
					minHeight={605}
					reportContainer={reportContainer}
					showInterval={showIntervals}
				>
					{({filters, interval, rangeSelectors}) => {
						const sharedProps: ICommonMetricProps = {
							emptyDescription,
							emptyTitle,
							filters,
							interval,
							rangeSelectors
						};

						return (
							<Card.Body className='analytics-metrics'>
								<MetricTabs {...sharedProps} />

								<MetricChart {...sharedProps} />
							</Card.Body>
						);
					}}
				</BaseCard>
			</MetricContextActions.Provider>
		</MetricContext.Provider>
	);
}

export const reducer = (state, action) => {
	const handlerFn = actionHandlers[action.type];

	if (handlerFn) {
		return handlerFn(state, action);
	}

	throw new Error('Unhandled action type: ${type}');
};

enum Actions {
	UpdateActiveItemIndex = 'UPDATE_ACTIVE_ITEM_INDEX',
	UpdateCompareToPrevious = 'UPDATE_COMPARE_TO_PREVIOUS'
}

const actionHandlers = {
	[Actions.UpdateActiveItemIndex]: (state, {payload}) => ({
		...state,
		activeItemIndex: payload
	}),
	[Actions.UpdateCompareToPrevious]: (state, {payload}) => ({
		...state,
		compareToPrevious: payload
	})
};

export const useData = () => useContext(MetricContext);
export const useActions = () => useContext(MetricContextActions);

export default MetricBaseCard;
