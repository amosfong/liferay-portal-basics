import * as data from 'test/data';
import Form from 'shared/components/form';
import Items from '../Items';
import mockStore from 'test/mock-store';
import React from 'react';
import {MockedProvider} from '@apollo/react-testing';
import {mockRecommendationPageAssetsReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {range} from 'lodash';
import {render} from '@testing-library/react';
import {waitForLoading} from 'test/helpers';

jest.unmock('react-dom');

describe('Items', () => {
	it('should render', async () => {
		const {container} = render(
			<MockedProvider
				mocks={[
					mockRecommendationPageAssetsReq(
						range(10).map(i => data.mockRecommendationPageAsset(i)),
						{size: 0}
					)
				]}
			>
				<Provider store={mockStore()}>
					<Form
						initialValues={{
							itemFilters: [
								{
									id: 'includeFilter - url ~ .*custom-assets',
									name: 'includeFilter',
									value: 'url ~ .*custom-assets'
								}
							]
						}}
					>
						{({values: {itemFilters}}) => (
							<Form.Form>
								<Items itemFilters={itemFilters} />
							</Form.Form>
						)}
					</Form>
				</Provider>
			</MockedProvider>
		);

		await waitForLoading(container);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});
});