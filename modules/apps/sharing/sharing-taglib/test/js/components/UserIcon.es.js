/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import UserIcon from '../../../src/main/resources/META-INF/resources/js/collaborators/components/UserIcon';

describe('UserIcon', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = render(
			<UserIcon fullName="Jane Smith" userId="1000" />
		);

		expect(asFragment()).toMatchSnapshot();
	});
});
