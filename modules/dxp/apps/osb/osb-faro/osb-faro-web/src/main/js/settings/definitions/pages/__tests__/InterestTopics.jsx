import * as API from 'shared/api';
import * as data from 'test/data';
import InterestTopics from '../InterestTopics';
import mockStore, {mockStoreData, toRD} from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {MemoryRouter, Route} from 'react-router-dom';
import {Provider} from 'react-redux';
import {Routes} from 'shared/util/router';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

const defaultProps = {
	groupId: '23'
};

const DefaultComponent = ({queryString = '', ...otherProps}) => (
	<Provider store={mockStore()}>
		<MemoryRouter
			initialEntries={[
				`/workspace/23/settings/definitions/interest-topics${queryString}`
			]}
		>
			<Route path={Routes.SETTINGS_DEFINITIONS_INTEREST_TOPICS}>
				<InterestTopics {...defaultProps} {...otherProps} />
			</Route>
		</MemoryRouter>
	</Provider>
);

describe('InterestTopics', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});

	it('should render page not found puts a invalid page', async () => {
		API.blockedKeywords.search.mockReturnValueOnce(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(<DefaultComponent queryString='?page=33' />);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render without an "add keyword" button if the user role is member', () => {
		API.user.fetchCurrentUser.mockReturnValueOnce(
			Promise.resolve(data.mockMemberUser('24'))
		);

		const {queryByText} = render(
			<Provider
				store={mockStore(
					mockStoreData.setIn(['currentUser'], toRD('24'))
				)}
			>
				<MemoryRouter
					initialEntries={[
						'/workspace/23/settings/definitions/interest-topics'
					]}
				>
					<Route path={Routes.SETTINGS_DEFINITIONS_INTEREST_TOPICS}>
						<InterestTopics {...defaultProps} />
					</Route>
				</MemoryRouter>
			</Provider>
		);

		expect(queryByText('Add Keyword')).toBeNull();
	});

	it('should render with an empty state', async () => {
		API.blockedKeywords.search.mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(
			<DefaultComponent queryString='?query=foo' />
		);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render with a message to add keywords if there are none', async () => {
		API.blockedKeywords.search.mockReturnValueOnce(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});

	it('should render with a member-specific message to add keywords if there are none', async () => {
		API.user.fetchCurrentUser.mockReturnValueOnce(
			Promise.resolve(data.mockMemberUser('24'))
		);

		API.blockedKeywords.search.mockReturnValueOnce(
			Promise.resolve({items: [], total: 0})
		);

		const {container} = render(
			<Provider
				store={mockStore(
					mockStoreData.setIn(['currentUser'], toRD('24'))
				)}
			>
				<MemoryRouter
					initialEntries={[
						'/workspace/23/settings/definitions/interest-topics'
					]}
				>
					<Route path={Routes.SETTINGS_DEFINITIONS_INTEREST_TOPICS}>
						<InterestTopics {...defaultProps} />
					</Route>
				</MemoryRouter>
			</Provider>
		);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(container.querySelector('.no-results-root')).toMatchSnapshot();
	});
});