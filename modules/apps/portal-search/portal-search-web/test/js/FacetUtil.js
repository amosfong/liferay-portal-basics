/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FacetUtil} from '../../src/main/resources/META-INF/resources/js/FacetUtil';

describe('FacetUtil', () => {
	describe('removeURLParameters()', () => {
		it('removes the parameter whose name is the given key', () => {
			const parameters = ['modified=last-24-hours', 'q=test'];

			const newParameters = FacetUtil.removeURLParameters(
				'modified',
				parameters
			);

			expect(newParameters).toEqual(['q=test']);
		});

		it('does not remove parameters not matching the given key', () => {
			const parameters = [
				'modifiedFrom=2018-01-01',
				'modifiedTo=2018-01-31',
				'q=test',
			];

			const newParameters = FacetUtil.removeURLParameters(
				'modified',
				parameters
			);

			expect(newParameters).toEqual([
				'modifiedFrom=2018-01-01',
				'modifiedTo=2018-01-31',
				'q=test',
			]);
		});

		it('removes given parameter', () => {
			const parameters = FacetUtil.removeURLParameters('key', [
				'key=sel1',
				'key=sel2',
			]);

			expect(parameters).toEqual([]);
		});

		it('preserves other parameters', () => {
			const parameters = FacetUtil.removeURLParameters('key1', [
				'key1=sel1',
				'key2=sel2',
			]);

			expect(parameters).toEqual(['key2=sel2']);
		});

		it('preserves key-only parameters', () => {
			const parameters = FacetUtil.removeURLParameters('key', [
				'checked',
				'key=value',
			]);

			expect(parameters).toEqual(['checked']);
		});

		it('removes key-only parameters', () => {
			const parameters = FacetUtil.removeURLParameters('checked', [
				'checked',
				'key=value',
			]);

			expect(parameters).toEqual(['key=value']);
		});
	});

	describe('setURLParameter()', () => {
		it('adds a missing parameter', () => {
			const url = FacetUtil.setURLParameter(
				'http://example.com/',
				'q',
				'test'
			);

			expect(url).toBe('http://example.com/?q=test');
		});

		it('updates an existing parameter', () => {
			const url = FacetUtil.setURLParameter(
				'http://example.com/?q=example',
				'q',
				'test'
			);

			expect(url).toBe('http://example.com/?q=test');
		});

		it('adds a missing parameter with path', () => {
			const url = FacetUtil.setURLParameter(
				'http://example.com/path',
				'q',
				'test'
			);

			expect(url).toBe('http://example.com/path?q=test');
		});
	});

	describe('setURLParameters()', () => {
		it('adds new selections', () => {
			const parameters = FacetUtil.setURLParameters(
				'key',
				['sel1', 'sel2'],
				[]
			);

			expect(parameters).toEqual(['key=sel1', 'key=sel2']);
		});

		it('removes old selections', () => {
			const parameters = FacetUtil.setURLParameters(
				'key',
				['sel2', 'sel3'],
				['key=sel1']
			);

			expect(parameters).toEqual(['key=sel2', 'key=sel3']);
		});

		it('preserves other selections', () => {
			const parameters = FacetUtil.setURLParameters(
				'key1',
				['sel1'],
				['key2=sel2']
			);

			expect(parameters).toEqual(['key2=sel2', 'key1=sel1']);
		});
	});

	describe('.updateQueryString()', () => {
		it('removes old selections', () => {
			const queryString = FacetUtil.updateQueryString(
				'key',
				['sel2', 'sel3'],
				'?key=sel1'
			);

			expect(queryString).toEqual('?key=sel2&key=sel3');
		});

		it('adds new selections', () => {
			const queryString = FacetUtil.updateQueryString(
				'key1',
				['sel1'],
				'?key2=sel2'
			);

			expect(queryString).toEqual('?key2=sel2&key1=sel1');
		});

		it('accepts query string without question mark', () => {
			const queryString = FacetUtil.updateQueryString(
				'key1',
				['sel1'],
				'key2=sel2'
			);

			expect(queryString).toEqual('key2=sel2&key1=sel1');
		});

		it('does not prefix with ampersand', () => {
			const queryString = FacetUtil.updateQueryString(
				'key',
				['sel1', 'sel2'],
				'?'
			);

			expect(queryString).toEqual('?key=sel1&key=sel2');
		});
	});
});
