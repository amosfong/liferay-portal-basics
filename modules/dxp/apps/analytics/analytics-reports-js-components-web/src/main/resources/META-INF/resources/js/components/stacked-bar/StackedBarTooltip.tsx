/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';
import {utcFormat} from 'd3';
import moment from 'moment';
import React, {useContext} from 'react';

import {AnalyticsReportsContext} from '../../AnalyticsReportsContext';
import {Alignments, RangeSelectors, Weights} from '../../types/global';
import {getDateRange} from '../../utils/date';
import {toThousands} from '../../utils/math';
import ChartTooltip from '../ChartTooltip';
import {ChartData, chartBgColors, chartColors} from './StackedBarChart';

const TOOLTIP_WIDTH = 450;

function formatStackedBarTooltipDate(rangeSelector: RangeSelectors) {
	const {endDate, startDate} = getDateRange(rangeSelector);

	const dayFormat = utcFormat('%-d');
	const dayMonthFormat = utcFormat('%b %-d');
	const yearMonthDayFormat = utcFormat('%Y %b %-d');

	const sameMonth =
		moment(startDate).get('month') === moment(endDate).get('month');
	const sameYear =
		moment(startDate).get('year') === moment(endDate).get('year');

	if (sameMonth && sameYear) {
		return `${yearMonthDayFormat(startDate)} - ${dayFormat(endDate)}`;
	}

	if (sameYear) {
		return `${yearMonthDayFormat(startDate)} - ${dayMonthFormat(endDate)}`;
	}

	return `${yearMonthDayFormat(startDate)} - ${yearMonthDayFormat(endDate)}`;
}

interface IStackedBarTooltipProps {
	activeIndex: number;
	data: ChartData;
	title: string;
}

const StackedBarTooltip: React.FC<IStackedBarTooltipProps> = ({
	activeIndex,
	data,
	title,
}) => {
	const selectedData = data.data[activeIndex];

	const {filters} = useContext(AnalyticsReportsContext);

	const header = [
		{
			columns: [
				{
					label: title,
					weight: Weights.Bold,
					width: 200,
				},
				{
					align: Alignments.Right,
					label: formatStackedBarTooltipDate(filters.rangeSelector),
					width: 140,
				},
				{
					align: Alignments.Right,
					label: '%',
					width: 80,
				},
			],
		},
	];

	const rows = [
		{
			columns: [
				{
					label: () => (
						<>
							<div
								className={classNames(
									'stacked-bar-tooltip__icon'
								)}
								style={{
									backgroundColor: chartColors[activeIndex],
									backgroundImage: chartBgColors[activeIndex]
										? `url('data:image/svg+xml;utf8,\
			<svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" patternUnits="userSpaceOnUse">\
			  <rect fill="none" width="10" height="10" />\
			  <path d="${chartBgColors[activeIndex]}" fill="none" stroke="white" stroke-width="1" stroke-opacity="0.5"/>\
			</svg>')`
										: '',
								}}
							/>

							{selectedData.label}
						</>
					),
					weight: Weights.Bold,
				},
				{
					align: Alignments.Right,
					label: toThousands(selectedData.value),
				},
				{
					align: Alignments.Right,
					label: `${selectedData.percentage}%`,
				},
			],
		},
	];

	return (
		<div
			className="bb-tooltip-container stacked-bar-tooltip"
			style={{
				width: TOOLTIP_WIDTH,
			}}
		>
			<ChartTooltip header={header} rows={rows} />
		</div>
	);
};

export default StackedBarTooltip;
