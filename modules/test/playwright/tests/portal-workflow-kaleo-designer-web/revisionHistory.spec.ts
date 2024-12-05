/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {loginTest} from '../../fixtures/loginTest';
import {workflowPagesTest} from '../../fixtures/workflowPagesTest';
import performLogin, {performLogout} from '../../utils/performLogin';
import postSingleApproverCopy from './utils/postSingleApproverCopy';
import {toLocalDateTimeFormatted} from './utils/toLocalDateTimeFormatted';

export const test = mergeTests(apiHelpersTest, loginTest(), workflowPagesTest);

let workflowDefinitionId: number;
let workflowDefinitionName: string;

test.beforeEach(async ({apiHelpers}) => {
	const workFlowDefinition = await postSingleApproverCopy(apiHelpers);

	workflowDefinitionId = workFlowDefinition.id;
	workflowDefinitionName = workFlowDefinition.name;
});

test.afterEach(async ({apiHelpers}) => {
	await apiHelpers.headlessAdminWorkflow.deleteWorkflowDefinition(
		workflowDefinitionId
	);
});

test.describe('Revision History tab', () => {
	test('can create a new version by saving and publishing the workflow definition', async ({
		apiHelpers,
		definitionInfoPage,
		diagramViewPage,
		nodePropertiesSidebarPage,
		page,
		processBuilderPage,
	}) => {
		const demoUser =
			await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
				'demo.company.admin@liferay.com'
			);

		await performLogout(page);

		await performLogin(page, demoUser.alternateName);

		await processBuilderPage.goto();

		await processBuilderPage.clickWorkflowDefinitionName(
			workflowDefinitionName
		);

		await nodePropertiesSidebarPage.dragNodeToDiagram('Task', 200, 200);

		await nodePropertiesSidebarPage.nodeLabelInput.fill('Task 1');

		await diagramViewPage.saveWorkflowDefinition();

		await diagramViewPage.definitionInfoButton.click();

		await definitionInfoPage.revisionHistoryTabButton.click();

		const newWorkflowDefinition =
			await apiHelpers.headlessAdminWorkflow.getWorkflowDefinitionByName(
				workflowDefinitionName
			);

		const dateCreated = toLocalDateTimeFormatted(
			newWorkflowDefinition.dateCreated,
			'en-us',
			'UTC'
		);
		const dateModified = toLocalDateTimeFormatted(
			newWorkflowDefinition.dateModified,
			'en-us',
			'UTC'
		);

		await expect(definitionInfoPage.getVersionLabel('2')).toBeVisible();

		const testUser =
			await apiHelpers.headlessAdminUser.getUserAccountByEmailAddress(
				'test@liferay.com'
			);

		await expect(
			definitionInfoPage.getDateAndUserFromVersion(
				dateCreated,
				testUser.name
			)
		).toBeVisible();

		await expect(
			definitionInfoPage.getDateAndUserFromVersion(
				dateModified,
				demoUser.name
			)
		).toBeVisible();

		await performLogout(page);

		await performLogin(page, testUser.alternateName);

		await processBuilderPage.goto();

		await processBuilderPage.clickWorkflowDefinitionName(
			workflowDefinitionName
		);

		await diagramViewPage.deleteNode('Task 1');

		await diagramViewPage.publishWorkflowDefinitionButton.click();

		await diagramViewPage.definitionInfoButton.click();

		await definitionInfoPage.revisionHistoryTabButton.click();

		const newWorkflowDefinition2 =
			await apiHelpers.headlessAdminWorkflow.getWorkflowDefinitionByName(
				workflowDefinitionName
			);

		const currentDateModified = toLocalDateTimeFormatted(
			newWorkflowDefinition2.dateModified,
			'en-us',
			'UTC'
		);

		await expect(definitionInfoPage.getVersionLabel('3')).toBeVisible();

		await expect(
			definitionInfoPage
				.getDateAndUserFromVersion(currentDateModified, testUser.name)
				.last()
		).toBeVisible();
	});
});
