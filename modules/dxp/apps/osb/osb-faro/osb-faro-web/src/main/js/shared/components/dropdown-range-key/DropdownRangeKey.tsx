import ErrorDisplay from '../ErrorDisplay';
import React from 'react';
import StatesRenderer from '../states-renderer/StatesRenderer';
import TimeRangeQuery from 'shared/queries/TimeRangeQuery';
import {DropdownRangeKeyContent} from './DropdownRangeKeyContent';
import {RangeKeyTimeRanges} from 'shared/util/constants';
import {RangeSelectors} from 'shared/types';
import {useQuery} from '@apollo/react-hooks';

export interface DropdownRangeKeyIProps
	extends React.HTMLAttributes<HTMLElement> {
	alignmentPosition?: number;
	legacy: boolean;
	onRangeSelectorChange: (rangeSelectors: RangeSelectors) => void;
	rangeKeys?: Array<RangeKeyTimeRanges>;
	rangeSelectors?: RangeSelectors;
}

export type Data = {
	preference: {
		value: number;
	};
	timeRange: {
		endDate: string;
		rangeKey: string;
		startDate: string;
	}[];
};

export const DropdownRangeKey: React.FC<DropdownRangeKeyIProps> = ({
	alignmentPosition,
	legacy,
	onRangeSelectorChange,
	rangeKeys,
	rangeSelectors
}) => {
	const {data, error, loading} = useQuery<Data>(TimeRangeQuery);

	return (
		<StatesRenderer error={!!error} loading={loading}>
			<StatesRenderer.Error apolloError={error}>
				<ErrorDisplay />
			</StatesRenderer.Error>

			<StatesRenderer.Loading center={false} />

			<StatesRenderer.Success>
				<DropdownRangeKeyContent
					alignmentPosition={alignmentPosition}
					data={data}
					legacy={legacy}
					onRangeSelectorChange={onRangeSelectorChange}
					rangeKeys={rangeKeys}
					rangeSelectors={rangeSelectors}
				/>
			</StatesRenderer.Success>
		</StatesRenderer>
	);
};
