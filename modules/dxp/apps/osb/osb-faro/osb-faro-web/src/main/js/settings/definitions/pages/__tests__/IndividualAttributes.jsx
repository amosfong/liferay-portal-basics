import 'test/mock-modal';

import IndividualAttributes from '../IndividualAttributes';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {open} from 'shared/actions/modals';
import {Provider} from 'react-redux';
import {StaticRouter} from 'react-router';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({timeZoneId: 'UTC'})
}));

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<StaticRouter>
			<IndividualAttributes groupId='23' {...props} />
		</StaticRouter>
	</Provider>
);

describe('IndividualAttributes', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should open modal after click on fielName', async () => {
		const {container, getByText} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		fireEvent.click(getByText('testFildName0'));

		expect(open).toBeCalled();
	});
});
