/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	getItem,
	getStorageSizeInKb,
	removeItem,
	setItem,
	verifyStorageLimitForKey,
} from '../../src/utils/storage';

const STORAGE_KEY = 'some-key';

window.Liferay = {
	FeatureFlags: {
		'LPD-10588': false,
	},
	Util: {
		LocalStorage: {
			TYPES: {
				PERFORMANCE: 'performance',
			},
			getItem: jest.fn(),
			removeItem: jest.fn(),
			setItem: jest.fn(),
		},
	},
};

describe('Storage Utils', () => {
	beforeEach(() => {
		window.Liferay.FeatureFlags['LPD-10588'] = false;

		localStorage.removeItem(STORAGE_KEY);
	});

	describe('removeItem', () => {
		it('Removes an item from localStorage', () => {
			const expected = undefined;

			localStorage.removeItem(STORAGE_KEY);

			expect(removeItem(STORAGE_KEY)).toEqual(expected);
		});

		it('Removes an item from localStorage by using Liferay Instance', () => {
			window.Liferay.FeatureFlags['LPD-10588'] = true;

			removeItem(STORAGE_KEY);

			expect(window.Liferay.Util.LocalStorage.removeItem).toBeCalled();
		});
	});

	describe('getItem', () => {
		it('Retrieves an item from localStorage', () => {
			const expected = {name: 'foo'};

			localStorage.setItem(STORAGE_KEY, JSON.stringify(expected));

			expect(getItem(STORAGE_KEY)).toEqual(expected);
		});

		it('Retrieves an item from localStorage by using Liferay Instance', () => {
			window.Liferay.FeatureFlags['LPD-10588'] = true;

			getItem(STORAGE_KEY);

			expect(window.Liferay.Util.LocalStorage.getItem).toBeCalled();
		});
	});

	describe('getStorageSizeInKb', () => {
		it('Calculates the kilobyte size of a string', () => {
			const expected = 0.0234375;

			expect(getStorageSizeInKb('0123456789')).toEqual(expected);
		});
	});

	describe('setItem', () => {
		it('Sets an item in localStorage', () => {
			const expected = {name: 'foo'};

			setItem(STORAGE_KEY, expected);

			expect(JSON.parse(localStorage.getItem(STORAGE_KEY))).toEqual(
				expected
			);
		});

		it('Sets an item in localStorage by using Liferay Instance', () => {
			window.Liferay.FeatureFlags['LPD-10588'] = true;

			setItem(STORAGE_KEY);

			expect(window.Liferay.Util.LocalStorage.setItem).toBeCalled();
		});
	});

	describe('verifyStorageLimitForKey', () => {
		it('Removes items in a localStorage queue if the storage limit is exceeded', async () => {
			const queue = [{name: 'foo'}, {name: 'bar'}, {name: 'baz'}];
			const mockStorageLimit = 0.05;

			setItem(STORAGE_KEY, queue);

			await verifyStorageLimitForKey(STORAGE_KEY, mockStorageLimit);

			expect(getItem(STORAGE_KEY)).toEqual(
				expect.arrayContaining(queue.slice(1))
			);
		});

		it('Does not change items in a localStorage queue if the storage limit is not exceeded', async () => {
			const queue = [{name: 'foo'}, {name: 'bar'}, {name: 'baz'}];
			const mockStorageLimit = 100;

			setItem(STORAGE_KEY, queue);

			await verifyStorageLimitForKey(STORAGE_KEY, mockStorageLimit);

			expect(getItem(STORAGE_KEY)).toEqual(expect.arrayContaining(queue));
		});
	});
});
