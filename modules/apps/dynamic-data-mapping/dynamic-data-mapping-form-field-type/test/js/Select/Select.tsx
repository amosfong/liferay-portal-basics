/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {FormProvider, PageProvider} from 'data-engine-js-components-web';
import React from 'react';

import Select from '../../../src/main/resources/META-INF/resources/Select/Select';

interface Props {
	multiple: boolean;
	name: string;
	options: Option[];
}

const SelectWithProvider = (props: Props) => (
	<FormProvider initialState={{viewMode: true}}>
		<PageProvider value={{editingLanguageId: 'en_US'}}>
			<Select
				label=""
				onChange={null}
				readOnly={false}
				selectedKey=""
				showEmptyOption={false}
				{...props}
			/>
		</PageProvider>
	</FormProvider>
);

describe('Select', () => {
	afterEach(cleanup);

	beforeEach(() => {
		window.ResizeObserver = jest.fn().mockImplementation(() => ({
			disconnect: jest.fn(),
			observe: jest.fn(),
			unobserve: jest.fn(),
		}));
	});

	const options = [
		{
			label: 'Option 1',
			reference: 'Option1Reference',
			value: 'value1',
		},
		{
			label: 'Option 2',
			reference: 'Option2Reference',
			value: 'value2',
		},
	];

	it('renders data-option-reference in option elements', () => {
		const props = {
			multiple: false,
			name: 'selectName',
			options,
		};

		const {getAllByRole, getByRole} = render(
			<SelectWithProvider {...props} />
		);

		userEvent.click(getByRole('combobox'));

		const optionElements = getAllByRole('option');

		optionElements.forEach((optionElement, index) => {
			expect(optionElement.getAttribute('data-option-reference')).toEqual(
				options[index].reference
			);
		});
	});

	it('renders data-option-reference in option elements when allowing multiple selections', () => {
		const props = {
			multiple: true,
			name: 'selectName',
			options,
		};

		const {getByLabelText, getByRole} = render(
			<SelectWithProvider {...props} />
		);

		userEvent.click(getByRole('combobox'));

		props.options.forEach((option) => {
			const checkboxElement = getByLabelText(option.label);

			expect(
				checkboxElement.getAttribute('data-option-reference')
			).toEqual(option.reference);
		});
	});
});
