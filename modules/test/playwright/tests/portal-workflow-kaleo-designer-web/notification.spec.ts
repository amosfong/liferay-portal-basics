/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {loginTest} from '../../fixtures/loginTest';
import {workflowPagesTest} from '../../fixtures/workflowPagesTest';
import {getRandomInt} from '../../utils/getRandomInt';
import getRandomString from '../../utils/getRandomString';
import countSubstringOccurrences from './utils/countSubstringOccurrences';
import {getWorkflowDefinition} from './utils/getWorkflowDefinition';
import postSingleApproverCopy from './utils/postSingleApproverCopy';

export const test = mergeTests(apiHelpersTest, loginTest(), workflowPagesTest);

const roleTypeNotification = {
	notificationDescription: 'notificationDescription0' + getRandomInt(),
	notificationName: 'Role Type Notification',
	notificationTypeEmail: true,
	notificationTypeUser: true,
	recipientType: 'roleType',
	recipientTypeData: {
		autocreate: false,
		roleName: 'Account Manager',
		roleType: 'Organization',
	},
	template: 'template0' + getRandomInt(),
	templateLanguage: 'freemarker',
} as Notification;

const scriptedRecipientNotification = {
	notificationDescription: 'notificationDescription1' + getRandomInt(),
	notificationName: 'notificationName1' + getRandomInt(),
	notificationTypeEmail: true,
	notificationTypeUser: true,
	recipientType: 'scriptedRecipient',
	recipientTypeData: {
		script: 'script' + getRandomInt(),
		scriptLanguage: 'groovy',
	},
	template: 'template1' + getRandomInt(),
	templateLanguage: 'text',
} as Notification;

let workflowDefinitionIds: number[] = [];

let workflowDefinitionName: string;

let scriptManagementDisabled: boolean = false;

test.beforeEach(async ({apiHelpers}) => {
	const workFlowDefinition = await postSingleApproverCopy(apiHelpers);

	workflowDefinitionIds.push(workFlowDefinition.id);
	workflowDefinitionName = workFlowDefinition.name;
});

test.afterEach(async ({apiHelpers, scriptManagementPage}) => {
	for (const workflowDefinitionId of workflowDefinitionIds) {
		await apiHelpers.headlessAdminWorkflow.deleteWorkflowDefinition(
			workflowDefinitionId
		);
	}

	workflowDefinitionIds = [];

	if (scriptManagementDisabled) {
		await scriptManagementPage.enableScriptManagementConfiguration();
		scriptManagementDisabled = false;
	}
});

test('cannot see scripted recipient option when script management configuration is disabled', async ({
	nodePropertiesSidebarPage,
	notificationSectionPage,
	processBuilderPage,
	scriptManagementPage,
}) => {
	await scriptManagementPage.disableScriptManagementConfiguration();

	scriptManagementDisabled = true;

	await processBuilderPage.goto();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await nodePropertiesSidebarPage.dragNodeToDiagram('Task', 200, 200);

	await nodePropertiesSidebarPage.addNotificationButton.click();

	expect(
		await notificationSectionPage.getRecipientTypeTypeOption(
			'scriptedRecipient'
		)
	).toBeNull();
});

test('cannot save a workflow definition with a scripted recipient notification when script management configuration is disabled', async ({
	diagramViewPage,
	nodePropertiesSidebarPage,
	notificationSectionPage,
	page,
	processBuilderPage,
	scriptManagementPage,
}) => {
	await processBuilderPage.goto();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await nodePropertiesSidebarPage.dragNodeToDiagram('Task', 200, 200);

	await nodePropertiesSidebarPage.nodeLabelInput.fill('Notification Node');

	await nodePropertiesSidebarPage.addNotificationButton.click();

	await notificationSectionPage.fillNotificationSectionFields(
		false,
		scriptedRecipientNotification
	);

	await diagramViewPage.saveWorkflowDefinition();

	await scriptManagementPage.disableScriptManagementConfiguration();

	scriptManagementDisabled = true;

	await processBuilderPage.goto();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await diagramViewPage.saveWorkflowDefinition();

	await expect(page.getByText('Error Updating Definition')).toBeVisible();
});

test('create a notification using role type', async ({
	diagramViewPage,
	nodePropertiesSidebarPage,
	notificationSectionPage,
	processBuilderPage,
}) => {
	await processBuilderPage.goto();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await diagramViewPage.clickNode('review');

	await nodePropertiesSidebarPage.deleteNotifications();

	await nodePropertiesSidebarPage.createNotification(roleTypeNotification);

	await processBuilderPage.switchToSourceViewAndBackToDiagram();

	await diagramViewPage.clickNode('review');

	const notificationEntry = processBuilderPage.page.getByRole('link', {
		name: 'Role Type Notification',
	});

	await expect(notificationEntry).toBeVisible();

	await notificationEntry.click();

	await notificationSectionPage.assertNotificationSectionFields(
		0,
		roleTypeNotification
	);

	await diagramViewPage.saveWorkflowDefinition();

	await diagramViewPage.goBack();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await diagramViewPage.clickNode('review');

	await expect(notificationEntry).toBeVisible();

	await notificationEntry.click();

	await notificationSectionPage.assertNotificationSectionFields(
		0,
		roleTypeNotification
	);
});

test('notification receptionType on source remains the same after clicking on notification link on diagram', async ({
	apiHelpers,
	diagramViewPage,
	page,
	processBuilderPage,
	sourceViewPage,
}) => {
	const workflowDefinitionName = 'Workflow Definition' + getRandomString();

	const workflowDefinition =
		await apiHelpers.headlessAdminWorkflow.postWorkflowDefinitionSave(
			workflowDefinitionName,
			getWorkflowDefinition('bcc-reception-type')
		);

	workflowDefinitionIds.push(workflowDefinition.id);

	await processBuilderPage.goto();

	await processBuilderPage.clickWorkflowDefinitionName(
		workflowDefinitionName
	);

	await diagramViewPage.clickNode('Task');

	const notificationEntry = processBuilderPage.page.getByRole('link', {
		name: 'notification 1',
	});

	await notificationEntry.click();

	await expect(
		page.getByRole('tablist').filter({hasText: 'Recipient Type'})
	).toHaveCount(6);

	await diagramViewPage.clickSourceViewButton();

	const requestPromise = page.waitForRequest('**/workflow-definitions/save');

	await sourceViewPage.saveWorkflowDefinition();

	const request = await requestPromise;

	const xmlContent = request.postDataJSON().content;

	expect(
		await countSubstringOccurrences(
			xmlContent,
			`<recipients receptionType="bcc">`
		)
	).toBe(6);
});
