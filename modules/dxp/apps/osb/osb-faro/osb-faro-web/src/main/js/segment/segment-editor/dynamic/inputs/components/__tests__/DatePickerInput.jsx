import client from 'shared/apollo/client';
import DatePickerInput from '../DatePickerInput';
import mockStore from 'test/mock-store';
import React from 'react';
import {ApolloProvider} from '@apollo/react-hooks';
import {cleanup, render} from '@testing-library/react';
import {MockedProvider} from '@apollo/react-testing';
import {mockPreferenceReq} from 'test/graphql-data';
import {Provider} from 'react-redux';

jest.unmock('react-dom');

jest.mock('react-router-dom', () => ({
	...jest.requireActual('react-router-dom'),
	useParams: () => ({
		channelId: '456',
		groupId: '2000',
		query: {
			rangeKey: '30'
		}
	})
}));

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({
		timeZoneId: 'UTC'
	})
}));

const WrapperComponent = ({children}) => (
	<ApolloProvider client={client}>
		<Provider store={mockStore()}>
			<MockedProvider mocks={[mockPreferenceReq()]}>
				{children}
			</MockedProvider>
		</Provider>
	</ApolloProvider>
);

describe('DatePickerInput', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WrapperComponent>
				<DatePickerInput onChange={jest.fn()} value='2020-12-12' />
			</WrapperComponent>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render w/ DateRangeInput', () => {
		const {getByTestId} = render(
			<WrapperComponent>
				<DatePickerInput
					isRange
					onChange={jest.fn()}
					value={{end: '2020-12-12', start: '2020-12-20'}}
				/>
			</WrapperComponent>
		);

		expect(getByTestId('date-range-input')).toBeTruthy();
	});
});
