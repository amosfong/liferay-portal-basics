import * as API from 'shared/api';
import CheckSegmentLink from '../CheckSegmentLink';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

const RenderText = () => 'Wrapped component text';

describe('CheckSegmentLink', () => {
	afterEach(cleanup);

	it('should render', () => {
		const WrappedComponent = CheckSegmentLink(RenderText);

		const {getByText} = render(
			<WrappedComponent
				location={{
					pathname:
						'/workspace/faro-dev-liferay/123/contacts/segments/456'
				}}
			/>
		);

		expect(getByText('Wrapped component text')).toBeTruthy();
	});

	it('should request and replace url if channelId is not in location', async () => {
		const spy = jest.fn();
		const WrappedComponent = CheckSegmentLink(RenderText);

		API.individualSegment.fetch.mockReturnValueOnce(
			Promise.resolve({channelId: 123, id: 456})
		);

		const {container} = render(
			<WrappedComponent
				groupId='faro-dev-liferay'
				history={{replace: spy}}
				location={{
					pathname:
						'/workspace/faro-dev-liferay/contacts/segments/456'
				}}
			/>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(spy).toBeCalledWith(
			'/workspace/faro-dev-liferay/123/contacts/segments/456'
		);
	});
});