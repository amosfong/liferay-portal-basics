/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CONJUNCTIONS} from '../../../src/main/resources/META-INF/resources/js/utils/constants';
import * as Utils from '../../../src/main/resources/META-INF/resources/js/utils/utils';
import {mockCriteria, mockCriteriaNested} from '../mockData';

const GROUP_ID = 'group_1';

describe('utils', () => {
	describe('createNewGroup', () => {
		it('returns a new group object with the passed in items', () => {
			expect(Utils.createNewGroup([])).toEqual({
				conjunctionName: CONJUNCTIONS.AND,
				groupId: GROUP_ID,
				items: [],
			});
		});
	});

	describe('getChildGroupIds', () => {
		it('returns an empty array if there are no child groups', () => {
			expect(Utils.getChildGroupIds(mockCriteria(1))).toEqual([]);
			expect(Utils.getChildGroupIds(mockCriteria(3))).toEqual([]);
		});

		it('returns the child group ids', () => {
			expect(Utils.getChildGroupIds(mockCriteriaNested())).toEqual([
				'group_02',
				'group_03',
				'group_04',
			]);
		});
	});

	describe('getSupportedOperatorsFromType', () => {
		it('returns an array of supported operators', () => {
			const supportedOperators =
				Utils.getSupportedOperatorsFromType('boolean');

			expect(supportedOperators).toEqual([
				{
					label: 'equals',
					name: 'eq',
				},
				{
					label: 'not-equals',
					name: 'not-eq',
				},
			]);
		});
	});

	describe('insertAtIndex', () => {
		it('inserts an item at the beginning', () => {
			expect(Utils.insertAtIndex('c', ['a', 'b'], 0)).toEqual([
				'c',
				'a',
				'b',
			]);
		});

		it('inserts an item at the middle', () => {
			expect(Utils.insertAtIndex('c', ['a', 'b'], 1)).toEqual([
				'a',
				'c',
				'b',
			]);
		});

		it('inserts an item at the end', () => {
			expect(Utils.insertAtIndex('c', ['a', 'b'], 2)).toEqual([
				'a',
				'b',
				'c',
			]);
		});
	});

	describe('objectToFormData', () => {
		it('takes an object of key value pairs and return a form data object with the same values', () => {
			const testData = {
				bar: 'bar',
				foo: 'foo',
			};

			const formData = Utils.objectToFormData(testData);

			expect(formData.get('bar')).toEqual('bar');
			expect(formData.get('foo')).toEqual('foo');
		});
	});

	describe('removeAtIndex', () => {
		it('removes the item at the beginning', () => {
			expect(Utils.removeAtIndex(['a', 'b', 'c'], 0)).toEqual(['b', 'c']);
		});

		it('removes the item at the middle', () => {
			expect(Utils.removeAtIndex(['a', 'b', 'c'], 1)).toEqual(['a', 'c']);
		});

		it('removes the item at the end', () => {
			expect(Utils.removeAtIndex(['a', 'b', 'c'], 2)).toEqual(['a', 'b']);
		});
	});

	describe('replaceAtIndex', () => {
		it('replaces the item at the beginning', () => {
			expect(Utils.replaceAtIndex('x', ['a', 'b', 'c'], 0)).toEqual([
				'x',
				'b',
				'c',
			]);
		});

		it('replaces the item at the middle', () => {
			expect(Utils.replaceAtIndex('x', ['a', 'b', 'c'], 1)).toEqual([
				'a',
				'x',
				'c',
			]);
		});

		it('replaces the item at the end', () => {
			expect(Utils.replaceAtIndex('x', ['a', 'b', 'c'], 2)).toEqual([
				'a',
				'b',
				'x',
			]);
		});
	});

	describe('sub', () => {
		it('returns an array', () => {
			const res = Utils.sub('hello world', [''], false);

			expect(res).toEqual(['hello world']);
		});

		it('returns a string', () => {
			const res = Utils.sub('hello world', ['']);

			expect(res).toEqual('hello world');
		});

		it('returns with a subbed value for {0}', () => {
			const res = Utils.sub('hello {0}', ['world']);

			expect(res).toEqual('hello world');
		});

		it('returns with multiple subbed values', () => {
			const res = Utils.sub('My name is {0} {1}', ['hello', 'world']);

			expect(res).toEqual('My name is hello world');
		});
	});
});
