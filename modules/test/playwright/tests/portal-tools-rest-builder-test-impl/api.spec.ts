/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	TestEntity,
	TestEntityApi,
} from '@liferay/portal-tools-rest-builder-test-client-js';
import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {loginTest} from '../../fixtures/loginTest';

const test = mergeTests(loginTest(), apiHelpersTest);

test('can use the autogenerated js client', async ({apiHelpers}) => {
	const testEntityApiClient = await apiHelpers.buildRestClient(TestEntityApi);

	const {body: testEntity} = await testEntityApiClient.postTestEntity({
		name: 'test',
		type: TestEntity.TypeEnum.ChildTestEntity1,
	});

	expect(testEntity).toMatchObject({name: 'test'});

	expect(
		await testEntityApiClient.getTestEntity(testEntity.id)
	).toMatchObject({body: testEntity});

	const {body: entitiesPage} =
		await testEntityApiClient.getTestEntitiesPage();

	expect(entitiesPage.items).toContainEqual(testEntity);
	expect(entitiesPage.page).toBeGreaterThanOrEqual(1);
	expect(entitiesPage.totalCount).toBeGreaterThanOrEqual(1);
});