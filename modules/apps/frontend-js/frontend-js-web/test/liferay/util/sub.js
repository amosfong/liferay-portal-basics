/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {render} from '@testing-library/react';
import React from 'react';

import sub from '../../../src/main/resources/META-INF/resources/liferay/util/sub';

describe('sub', () => {
	it('replaces the matching regex with just one argument', () => {
		expect(sub('foo {0}', 'bar')).toBe('foo bar');
	});

	it('replaces the matching regex with string arguments', () => {
		expect(sub('foo {0} {1} {2} {3}', 'bar', 'baz', 'lorem', 'ipsum')).toBe(
			'foo bar baz lorem ipsum'
		);
	});

	it('replaces the matching regex with an array of arguments', () => {
		expect(
			sub('foo {0} {1} {2} {3}', ['bar', 'baz', 'lorem', 'ipsum'])
		).toBe('foo bar baz lorem ipsum');
	});

	it('replaces the matching regex with html elements in form of string', () => {
		expect(
			sub(
				'{0}Learn how{1} to tailor categories to your needs',
				'<a href="" target="_blank">',
				'</a>'
			)
		).toBe(
			'<a href="" target="_blank">Learn how</a> to tailor categories to your needs'
		);
	});

	it('returns a JSX element if some ReactNode is passed as data', () => {

		// We need to render this instead of comparing the React nodes directly
		// because this version of jest has a bug when passing JSX to
		// assertions: https://github.com/jestjs/jest/issues/9745

		const {container} = render(
			sub('foo {0} bar {1}', <h1>Hey</h1>, 'there')
		);

		expect(container.innerHTML).toBe('foo <h1>Hey</h1> bar there');
	});
});
