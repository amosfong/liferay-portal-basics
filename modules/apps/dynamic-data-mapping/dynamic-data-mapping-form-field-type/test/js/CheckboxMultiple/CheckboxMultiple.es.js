/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom/extend-expect';
import {act, cleanup, render} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {PageProvider} from 'data-engine-js-components-web';
import React from 'react';

import CheckboxMultiple from '../../../src/main/resources/META-INF/resources/CheckboxMultiple/CheckboxMultiple.es';

const spritemap = 'icons.svg';

const CheckboxMultipleWithProvider = (props) => (
	<PageProvider value={{editingLanguageId: 'en_US'}}>
		<CheckboxMultiple {...props} />
	</PageProvider>
);

describe('Field Checkbox Multiple', () => {

	// eslint-disable-next-line no-console
	const originalWarn = console.warn;

	beforeAll(() => {

		// eslint-disable-next-line no-console
		console.warn = (...args) => {
			if (/DataProvider: Trying/.test(args[0])) {
				return;
			}
			originalWarn.call(console, ...args);
		};
	});

	afterAll(() => {

		// eslint-disable-next-line no-console
		console.warn = originalWarn;
	});

	afterEach(cleanup);

	beforeEach(() => {
		jest.useFakeTimers();
		fetch.mockResponseOnce(JSON.stringify({}));
	});

	it('is not editable', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				readOnly={true}
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a helptext', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				spritemap={spritemap}
				tip="Type something"
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has an id', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider id="ID" spritemap={spritemap} />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a label', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider label="label" spritemap={spritemap} />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a predefined Value', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				placeholder="Option 1"
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('is not required', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				required={false}
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('is shown as a switcher', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				showAsSwitcher
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('is shown as checkbox', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				showAsSwitcher={false}
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('renders Label if showLabel is true', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider
				label="text"
				showLabel
				spritemap={spritemap}
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a spritemap', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider spritemap={spritemap} />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a value', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider spritemap={spritemap} value={true} />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a key', () => {
		const {container} = render(
			<CheckboxMultipleWithProvider key="key" spritemap={spritemap} />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('call the onChange callback on the field change', () => {
		const handleFieldEdited = jest.fn();

		render(
			<CheckboxMultipleWithProvider
				onChange={handleFieldEdited}
				spritemap={spritemap}
			/>
		);

		userEvent.click(document.body.querySelector('input'));

		act(() => {
			jest.runAllTimers();
		});

		expect(handleFieldEdited).toHaveBeenCalled();
	});

	it('checks the value if there is a value', () => {
		const {getByLabelText} = render(
			<CheckboxMultipleWithProvider
				options={[
					{
						label: 'Option 1',
						value: 'option1',
					},
					{
						label: 'Option 2',
						value: 'option2',
					},
					{
						label: 'Option 3',
						value: 'option3',
					},
				]}
				predefinedValue={['option1', 'option2']}
				spritemap={spritemap}
				value={['option3']}
			/>
		);

		expect(getByLabelText('Option 1')).not.toBeChecked();
		expect(getByLabelText('Option 2')).not.toBeChecked();
		expect(getByLabelText('Option 3')).toBeChecked();
	});

	it('checks the predefinedValue if there is no value', () => {
		const {getByLabelText} = render(
			<CheckboxMultipleWithProvider
				options={[
					{
						label: 'Option 1',
						value: 'option1',
					},
					{
						label: 'Option 2',
						value: 'option2',
					},
					{
						label: 'Option 3',
						value: 'option3',
					},
				]}
				predefinedValue={['option1', 'option2']}
				spritemap={spritemap}
				value={[]}
			/>
		);

		expect(getByLabelText('Option 1')).toBeChecked();
		expect(getByLabelText('Option 2')).toBeChecked();
		expect(getByLabelText('Option 3')).not.toBeChecked();
	});

	it('renders data-option-reference attribute regardless of the element being a switcher', () => {
		const otherProps = {
			options: [
				{
					label: 'Option 1',
					reference: 'option1Reference',
					value: 'option1',
				},
				{
					label: 'Option 2',
					reference: 'option2Reference',
					value: 'option2',
				},
			],
			predefinedValue: ['option1', 'option2'],
			spritemap,
			value: [],
		};

		const allProps = [
			{showAsSwitcher: false, ...otherProps},
			{showAsSwitcher: true, ...otherProps},
		];

		allProps.forEach((props) => {
			const {container} = render(
				<CheckboxMultipleWithProvider {...props} />
			);

			const checkboxInputElement1 = container.querySelector(
				`input[value][type="checkbox"][data-option-reference="option1Reference"]`
			);

			const checkboxInputElement2 = container.querySelector(
				`input[value][type="checkbox"][data-option-reference="option2Reference"]`
			);

			expect(checkboxInputElement1).toBeTruthy();
			expect(checkboxInputElement2).toBeTruthy();
		});
	});

	it('uncheck all values if the user has edited the field to clear the predefinedValue', () => {
		const {getByLabelText} = render(
			<CheckboxMultipleWithProvider
				localizedValueEdited={{en_US: true}}
				options={[
					{
						label: 'Option 1',
						value: 'option1',
					},
					{
						label: 'Option 2',
						value: 'option2',
					},
					{
						label: 'Option 3',
						value: 'option3',
					},
				]}
				predefinedValue={['option1', 'option2']}
				spritemap={spritemap}
				value={[]}
			/>
		);

		expect(getByLabelText('Option 1')).not.toBeChecked();
		expect(getByLabelText('Option 2')).not.toBeChecked();
		expect(getByLabelText('Option 3')).not.toBeChecked();
	});
});
