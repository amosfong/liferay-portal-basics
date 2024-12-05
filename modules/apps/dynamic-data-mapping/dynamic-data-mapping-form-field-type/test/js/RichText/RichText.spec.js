/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom/extend-expect';
import {render} from '@testing-library/react';
import React from 'react';

import RichText from '../../../src/main/resources/META-INF/resources/RichText/RichText.es';

describe('RichText component', () => {
	it('has aria-label attribute when it is rendered', () => {
		const {getByRole} = render(
			<RichText label="RichText Label" name="RichTextName" />
		);

		const richTextElement = getByRole('textbox');

		expect(richTextElement).toHaveAttribute('aria-label', 'RichText Label');
	});
});
