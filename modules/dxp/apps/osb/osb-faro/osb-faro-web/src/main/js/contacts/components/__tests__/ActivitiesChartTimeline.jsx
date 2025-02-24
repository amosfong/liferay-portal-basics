import * as data from 'test/data';
import ActivitiesChartTimeline from '../ActivitiesChartTimeline';
import React from 'react';
import {EntityTypes} from 'shared/util/constants';
import {render} from '@testing-library/react';
import {StaticRouter} from 'react-router';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

const {activityAggregations} = data.mockActivityHistory();

describe('ActivitiesChartTimeline', () => {
	it('should render', async () => {
		const {container} = render(
			<StaticRouter>
				<ActivitiesChartTimeline
					activitiesLabel={Liferay.Language.get(
						'accounts-activities-x'
					)}
					channelId='123123'
					entityType={EntityTypes.Account}
					groupId='23'
					history={activityAggregations}
					id='123'
					rangeSelectors={{
						rangeKey: '30'
					}}
				/>
			</StaticRouter>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
