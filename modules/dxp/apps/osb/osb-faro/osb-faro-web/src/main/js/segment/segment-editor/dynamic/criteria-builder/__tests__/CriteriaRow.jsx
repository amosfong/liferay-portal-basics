import client from 'shared/apollo/client';
import CriteriaRow from '../CriteriaRow';
import mockStore from 'test/mock-store';
import React from 'react';
import {ApolloProvider} from '@apollo/react-hooks';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {DndProvider} from 'react-dnd';
import {fromJS, Map} from 'immutable';
import {HTML5Backend} from 'react-dnd-html5-backend';
import {MockedProvider} from '@apollo/react-testing';
import {mockPreferenceReq} from 'test/graphql-data';
import {Provider} from 'react-redux';

jest.unmock('react-dom');

const WrapperComponent = ({children}) => (
	<ApolloProvider client={client}>
		<Provider store={mockStore()}>
			<MockedProvider mocks={[mockPreferenceReq()]}>
				{children}
			</MockedProvider>
		</Provider>
	</ApolloProvider>
);

describe('CriteriaRow', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container, getAllByText, getByText} = render(
			<WrapperComponent>
				<DndProvider backend={HTML5Backend}>
					<CriteriaRow
						criterion={{
							operatorName: 'sessions-filter',
							propertyName: 'context/referrer',
							rowId: 'row_0',
							touched: false,
							valid: true,
							value: fromJS({
								criterionGroup: {
									conjunctionName: 'and',
									criteriaGroupId: 'group_0',
									items: [
										{
											operatorName: 'ne',
											propertyName: 'context/referrer',
											rowId: 'row_1',
											touched: false,
											valid: true
										},
										{
											operatorName: 'gt',
											propertyName: 'completeDate',
											rowId: 'row_2',
											touched: false,
											valid: true
										}
									]
								}
							})
						}}
						referencedAssetsIMap={new Map()}
						referencedPropertiesIMap={new Map()}
					/>
				</DndProvider>
			</WrapperComponent>
		);

		fireEvent.click(getByText('is not'));

		expect(getByText('is')).toBeTruthy();
		expect(getAllByText('is not')[1]).toBeTruthy();
		expect(getByText('contains')).toBeTruthy();
		expect(getByText('does not contain')).toBeTruthy();
		expect(getByText('is known')).toBeTruthy();
		expect(getByText('is unknown')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('should render w/ Non-Existent Property message', () => {
		const {queryByText} = render(
			<WrapperComponent>
				<DndProvider backend={HTML5Backend}>
					<CriteriaRow
						referencedAssetsIMap={new Map()}
						referencedPropertiesIMap={new Map()}
					/>
				</DndProvider>
			</WrapperComponent>
		);

		expect(queryByText('Attribute no longer exists.')).toBeTruthy();
	});
});