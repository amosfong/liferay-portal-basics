import client from 'shared/apollo/client';
import DateInput from '../DateInput';
import React from 'react';
import {ApolloProvider} from '@apollo/react-hooks';
import {cleanup, render} from '@testing-library/react';
import {MockedProvider} from '@apollo/react-testing';
import {mockPreferenceReq} from 'test/graphql-data';
import {Property} from 'shared/util/records';

jest.unmock('react-dom');

const WrapperComponent = ({children}) => (
	<ApolloProvider client={client}>
		<MockedProvider mocks={[mockPreferenceReq()]}>
			{children}
		</MockedProvider>
	</ApolloProvider>
);

describe('DateInput', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WrapperComponent>
				<DateInput
					operatorRenderer={() => <div>{'operator'}</div>}
					property={new Property()}
				/>
			</WrapperComponent>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render with data', () => {
		const {container} = render(
			<WrapperComponent>
				<DateInput
					displayValue='Start Date'
					operatorRenderer={() => <div>{'operator'}</div>}
					property={new Property()}
					value='12/12/12'
				/>
			</WrapperComponent>
		);

		expect(container).toMatchSnapshot();
	});
});
