import PercentOfCell from '../PercentOfCell';
import React from 'react';
import {DataTypes} from 'event-analysis/utils/types';
import {mockBreakdownData} from 'test/data';
import {parseBreakdownData} from 'event-analysis/utils/utils';
import {render} from '@testing-library/react';

jest.unmock('react-dom');

describe('PercentOfCell', () => {
	const orderedBreakdowns = [
		{dataType: DataTypes.String},
		{dataType: DataTypes.String},
		{dataType: DataTypes.String}
	];

	it('render', () => {
		const data = mockBreakdownData();
		const items = parseBreakdownData(data, orderedBreakdowns);
		const {events} = items[0];

		const {container} = render(
			<PercentOfCell
				compareToPrevious={false}
				events={events}
				totalEvents={data.totalEvents}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('render comparing previous', () => {
		const data = mockBreakdownData(true);
		const items = parseBreakdownData(data, orderedBreakdowns);
		const {events} = items[0];

		const {container} = render(
			<PercentOfCell
				compareToPrevious
				events={events}
				totalEvents={data.totalEvents}
			/>
		);

		expect(container.querySelectorAll('ul > li').length).toBe(2);
	});

	it('render comparing previous and segment', () => {
		const data = mockBreakdownData(true, true);
		const items = parseBreakdownData(data, orderedBreakdowns);
		const {events} = items[0];

		const {container} = render(
			<PercentOfCell
				compareToPrevious
				events={events}
				totalEvents={data.totalEvents}
			/>
		);

		expect(container.querySelectorAll('ul > li').length).toBe(4);
	});

	it('render comparing previous and event', () => {
		const data = mockBreakdownData(true, false, true);
		const items = parseBreakdownData(data, orderedBreakdowns);
		const {events} = items[0];

		const {container} = render(
			<PercentOfCell
				compareToPrevious
				events={events}
				totalEvents={data.totalEvents}
			/>
		);

		expect(container.querySelectorAll('ul > li').length).toBe(4);
	});

	it('render two list when comparing segment and event', () => {
		const data = mockBreakdownData(false, true, true);
		const items = parseBreakdownData(data, orderedBreakdowns);
		const {events} = items[0];

		const {container} = render(
			<PercentOfCell
				compareToPrevious
				events={events}
				totalEvents={data.totalEvents}
			/>
		);

		expect(container.querySelectorAll('ul').length).toBe(2);
	});

	it('display "undefined" value is attribute "name" is empty', () => {
		const data = {
			breakdownItems: [
				{
					breakdownItems: [
						{
							breakdownItems: [
								{
									breakdownItems: [],
									leafNode: true,
									name: 'All Individuals',
									value: 1717
								}
							],
							leafNode: false,
							value: 3367
						}
					],
					leafNode: false,
					value: 3367
				}
			],
			count: 1,
			page: 1,
			value: 5033
		};
		const items = parseBreakdownData(data, orderedBreakdowns);

		expect(items).toEqual([
			{
				breakdown0: {name: 'undefined', rowSpan: 1, value: 3367},
				breakdown1: {name: 'undefined', rowSpan: 1, value: 3367},
				events: [
					{
						breakdownItems: [],
						leafNode: true,
						name: 'All Individuals',
						value: 1717
					}
				],
				index: 0
			}
		]);
	});
});