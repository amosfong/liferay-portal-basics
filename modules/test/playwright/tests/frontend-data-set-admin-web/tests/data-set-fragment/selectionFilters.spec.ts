/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	ObjectDefinitionApi,
	ObjectField,
} from '@liferay/object-admin-rest-client-js';
import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../../../fixtures/featureFlagsTest';
import {isolatedLayoutTest} from '../../../../fixtures/isolatedLayoutTest';
import {loginTest} from '../../../../fixtures/loginTest';
import getRandomString from '../../../../utils/getRandomString';
import {dataSetManagerApiHelpersTest} from '../../fixtures/dataSetManagerApiHelpersTest';
import {picklistApiHelpersTest} from '../../fixtures/picklistApiHelpersTest';
import {API_ENDPOINT_PATH} from '../../utils/constants';
import {dataSetFragmentPageTest} from './fixtures/dataSetFragmentPageTest';

const picklistBooleanOptionLabel = 'Boolean';
const picklistDefaultOptionLabel = 'Default';

const apiHeadlessName = 'FieldType';
const apiHeadlessURL = `c/${apiHeadlessName.toLocaleLowerCase()}s`;
const dataSetERCs: string[] = [];
let dataSetERC: string;
let dataSetLabel: string;
let objectDefinition: any;
let picklistBooleanOption: any;
let picklistDefaultOption: any;
let picklistName: string;

export const test = mergeTests(
	apiHelpersTest,
	dataSetManagerApiHelpersTest,
	featureFlagsTest({
		'LPS-178052': true,
	}),
	isolatedLayoutTest({publish: false}),
	loginTest(),
	dataSetFragmentPageTest,
	picklistApiHelpersTest
);

test.beforeEach(
	async ({apiHelpers, dataSetManagerApiHelpers, picklistApiHelpers}) => {
		dataSetERC = getRandomString();
		dataSetLabel = getRandomString();
		picklistName = getRandomString();

		dataSetERCs.push(dataSetERC);

		await dataSetManagerApiHelpers.createDataSet({
			erc: dataSetERC,
			label: dataSetLabel,
		});

		await test.step('Create and populate a picklist', async () => {
			await picklistApiHelpers.createPicklist({
				name: picklistName,
			});

			picklistDefaultOption = await picklistApiHelpers.editPicklist({
				key: picklistDefaultOptionLabel.toLocaleLowerCase(),
				name: picklistName,
				value: picklistDefaultOptionLabel,
			});

			picklistBooleanOption = await picklistApiHelpers.editPicklist({
				key: picklistBooleanOptionLabel.toLocaleLowerCase(),
				name: picklistName,
				value: picklistBooleanOptionLabel,
			});
		});

		const objectDefinitionAPIClient =
			await apiHelpers.buildRestClient(ObjectDefinitionApi);

		await test.step('Create a Headless application and populate with filter values', async () => {
			objectDefinition = (
				await objectDefinitionAPIClient.postObjectDefinition({
					enableLocalization: true,
					label: {
						en_US: 'Field Type',
					},
					modifiable: true,
					name: apiHeadlessName,
					objectFields: [
						{
							DBType: ObjectField.DBTypeEnum.String,
							businessType: ObjectField.BusinessTypeEnum.Text,
							indexed: true,
							indexedAsKeyword: true,
							label: {
								en_US: 'type',
							},
							localized: true,
							name: 'type',
							required: false,
							state: false,
						},
					],
					pluralLabel: {en_US: `${apiHeadlessName}s`},
					scope: 'company',
				})
			).body;

			await objectDefinitionAPIClient.postObjectDefinitionPublish(
				objectDefinition.id
			);

			await apiHelpers.objectEntry.postObjectEntry(
				{type: 'array'},
				apiHeadlessURL
			);
			await apiHelpers.objectEntry.postObjectEntry(
				{type: 'boolean'},
				apiHeadlessURL
			);
			await apiHelpers.objectEntry.postObjectEntry(
				{type: 'integer'},
				apiHeadlessURL
			);
			await apiHelpers.objectEntry.postObjectEntry(
				{type: 'object'},
				apiHeadlessURL
			);
			await apiHelpers.objectEntry.postObjectEntry(
				{type: 'string'},
				apiHeadlessURL
			);
		});
	}
);

test.afterEach(
	async ({apiHelpers, dataSetManagerApiHelpers, picklistApiHelpers}) => {
		for (const DATA_SET_ERC of dataSetERCs) {
			await dataSetManagerApiHelpers.deleteDataSet({
				erc: DATA_SET_ERC,
			});
		}

		dataSetERCs.length = 0;

		await picklistApiHelpers.deletePicklist(picklistName);

		const objectDefinitionAPIClient =
			await apiHelpers.buildRestClient(ObjectDefinitionApi);

		await objectDefinitionAPIClient.deleteObjectDefinition(
			objectDefinition.id
		);
	}
);

test('Selection filter of type "Object Picklist" is displayed in fragment @LPD-10754', async ({
	dataSetFragmentPage,
	dataSetManagerApiHelpers,
	layout,
	page,
	picklistApiHelpers,
}) => {
	const filterLabel = getRandomString();

	await test.step('Add a field, so FDS has something to show', async () => {
		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'renderer',
			label_i18n: {en_US: 'Renderer'},
		});

		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'sortable',
			label_i18n: {en_US: 'Sortable'},
			renderer: 'boolean',
		});
	});

	await test.step('Configure Data Set fragment', async () => {
		await dataSetFragmentPage.configureDataSetFragment({
			dataSetLabel,
			layout,
		});
	});

	await test.step('There are no filters in the Frontend Data Set', async () => {
		await expect(dataSetFragmentPage.filterButton).not.toBeVisible();
	});

	await test.step('Create a new selection filter', async () => {
		const picklist = await picklistApiHelpers.getPicklist(picklistName);

		await dataSetManagerApiHelpers.createDataSetSelectionFilter({
			dataSetERC,
			fieldName: 'renderer',
			label_i18n: {en_US: filterLabel},
			source: picklist.externalReferenceCode,
			sourceType: 'PICKLIST',
		});
	});

	await test.step('Check current items in the Frontend Data Set', async () => {
		await page.reload();
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 2 of 2 entries.'
			)
		).toBeVisible();
	});

	await test.step('Select filter', async () => {
		await dataSetFragmentPage.selectFilter(filterLabel);
	});

	await test.step('Configure and apply filter', async () => {
		await expect(
			dataSetFragmentPage.filterItem.getByRole('radio', {
				name: picklistDefaultOptionLabel,
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('radio', {
				name: picklistBooleanOptionLabel,
			})
		).toBeVisible();

		await dataSetFragmentPage.filterItem
			.getByRole('radio', {name: picklistBooleanOptionLabel})
			.check();

		await dataSetFragmentPage.addFilterButton.click();

		// Close filter

		await dataSetFragmentPage.page.keyboard.press('Escape');
	});

	await test.step('Check that the filter works', async () => {
		await dataSetFragmentPage.filterResumeButton.waitFor({
			state: 'visible',
		});

		await expect(
			dataSetFragmentPage.page.getByRole('button', {
				name: `${filterLabel}: ${picklistBooleanOptionLabel}`,
			})
		).toBeVisible();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tbody > div')
				.first()
				.locator('.dnd-td')
		).toHaveText(['boolean', 'No', '']);

		await expect(
			dataSetFragmentPage.page.getByText('Showing 1 to 1 of 1 entries.')
		).toBeVisible();
	});
});

test('Selection filter of type "Object Picklist" can be configured to use single or multiple selection', async ({
	dataSetFragmentPage,
	dataSetManagerApiHelpers,
	layout,
	page,
	picklistApiHelpers,
}) => {
	const filterLabel = getRandomString();
	let selectionFilter;

	await test.step('Add fields, so FDS has something to show', async () => {
		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'renderer',
			label_i18n: {en_US: 'Renderer'},
		});

		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'sortable',
			label_i18n: {en_US: 'Sortable'},
			renderer: 'boolean',
		});
	});

	await test.step('Create a new single selection filter', async () => {
		const picklist = await picklistApiHelpers.getPicklist(picklistName);

		selectionFilter =
			await dataSetManagerApiHelpers.createDataSetSelectionFilter({
				dataSetERC,
				fieldName: 'renderer',
				label_i18n: {en_US: filterLabel},
				multiple: false,
				source: picklist.externalReferenceCode,
				sourceType: 'PICKLIST',
			});
	});

	await test.step('Configure Data Set fragment', async () => {
		await dataSetFragmentPage.configureDataSetFragment({
			dataSetLabel,
			layout,
		});
	});

	await test.step('Check current items in the Frontend Data Set', async () => {
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 2 of 2 entries.'
			)
		).toBeVisible();
	});

	await test.step('Select filter', async () => {
		await dataSetFragmentPage.selectFilter(filterLabel);
	});

	await test.step('Configure and apply filter', async () => {
		await expect(
			dataSetFragmentPage.filterItem.getByRole('radio', {
				name: picklistDefaultOptionLabel,
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('radio', {
				name: picklistBooleanOptionLabel,
			})
		).toBeVisible();

		await dataSetFragmentPage.filterItem
			.getByRole('radio', {name: picklistBooleanOptionLabel})
			.check();

		await dataSetFragmentPage.addFilterButton.click();

		// Close filter

		await dataSetFragmentPage.page.keyboard.press('Escape');
	});

	await test.step('Check that the filter works', async () => {
		await dataSetFragmentPage.filterResumeButton.waitFor({
			state: 'visible',
		});

		await expect(
			dataSetFragmentPage.page.getByRole('button', {
				name: `${filterLabel}: ${picklistBooleanOptionLabel}`,
			})
		).toBeVisible();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tbody > div')
				.first()
				.locator('.dnd-td')
		).toHaveText(['boolean', 'No', '']);

		await expect(
			dataSetFragmentPage.page.getByText('Showing 1 to 1 of 1 entries.')
		).toBeVisible();
	});

	await test.step('Update filter to allow multiple selection', async () => {
		await dataSetManagerApiHelpers.updateDataSetSelectionFilter({
			erc: selectionFilter.externalReferenceCode,
			multiple: true,
		});
	});

	await test.step('Check current items in the Frontend Data Set', async () => {
		await page.reload();
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 2 of 2 entries.'
			)
		).toBeVisible();
	});

	await test.step('Select filter', async () => {
		await dataSetFragmentPage.selectFilter(filterLabel);
	});

	await test.step('Configure and apply filter', async () => {
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: picklistDefaultOptionLabel,
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: picklistBooleanOptionLabel,
			})
		).toBeVisible();

		await dataSetFragmentPage.filterItem
			.getByRole('checkbox', {name: picklistDefaultOptionLabel})
			.check();
		await dataSetFragmentPage.filterItem
			.getByRole('checkbox', {name: picklistBooleanOptionLabel})
			.check();

		await dataSetFragmentPage.addFilterButton.click();

		// Close filter

		await dataSetFragmentPage.page.keyboard.press('Escape');
	});

	await test.step('Check that the filter works', async () => {
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 2 of 2 entries.'
			)
		).toBeVisible();
	});
});

test('Selection filter of type "Object Picklist" can be configured to include or exclude selected values', async ({
	dataSetFragmentPage,
	dataSetManagerApiHelpers,
	layout,
	page,
	picklistApiHelpers,
}) => {
	const filterLabel = getRandomString();
	let selectionFilter;

	await test.step('Add fields, so FDS has something to show', async () => {
		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'renderer',
			label_i18n: {en_US: 'Renderer'},
		});

		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'sortable',
			label_i18n: {en_US: 'Sortable'},
			renderer: 'boolean',
		});
	});

	await test.step('Create a new selection filter with preselected values and include mode', async () => {
		const picklist = await picklistApiHelpers.getPicklist(picklistName);

		selectionFilter =
			await dataSetManagerApiHelpers.createDataSetSelectionFilter({
				dataSetERC,
				fieldName: 'renderer',
				include: true,
				label_i18n: {en_US: filterLabel},
				multiple: false,
				preselectedValues: JSON.stringify([
					{
						label: getRandomString(),
						value: picklistDefaultOption.externalReferenceCode,
					},
				]),
				source: picklist.externalReferenceCode,
				sourceType: 'OBJECT_PICKLIST',
			});
	});

	await test.step('Configure Data Set fragment', async () => {
		await dataSetFragmentPage.configureDataSetFragment({
			dataSetLabel,
			layout,
		});
	});

	await test.step('Check current filter is applied in the Frontend Data Set', async () => {
		await expect(dataSetFragmentPage.filterResumeButton).toBeVisible();
		await expect(dataSetFragmentPage.filterResumeButton).toContainText(
			`${filterLabel}: ${picklistDefaultOptionLabel}`
		);
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tbody > div')
				.first()
				.locator('.dnd-td')
		).toHaveText(['default', 'No', '']);

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 1 of 1 entries.'
			)
		).toBeVisible();
	});

	await test.step('Update filter to use preselected values exclude mode', async () => {
		await dataSetManagerApiHelpers.updateDataSetSelectionFilter({
			erc: selectionFilter.externalReferenceCode,
			preselectedValues: JSON.stringify([
				{
					label: getRandomString(),
					value: picklistBooleanOption.externalReferenceCode,
				},
			]),
		});
	});

	await test.step('Check current items in the Frontend Data Set', async () => {
		await page.reload();
		await expect(dataSetFragmentPage.filterResumeButton).toBeVisible();
		await expect(dataSetFragmentPage.filterResumeButton).toContainText(
			`${filterLabel}: ${picklistBooleanOptionLabel}`
		);
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tbody > div')
				.first()
				.locator('.dnd-td')
		).toHaveText(['boolean', 'No', '']);

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 1 of 1 entries.'
			)
		).toBeVisible();
	});

	await test.step('Can remove the current filter', async () => {
		await dataSetFragmentPage.page
			.getByRole('button', {exact: true, name: 'Remove Filter'})
			.click();
		await expect(dataSetFragmentPage.filterResumeButton).not.toBeVisible();

		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 2 of 2 entries.'
			)
		).toBeVisible();
	});
});

test('Selection filter of type "API REST Application" is displayed in fragment @LPD-10754', async ({
	dataSetFragmentPage,
	dataSetManagerApiHelpers,
	layout,
}) => {
	const filterLabel = getRandomString();

	await test.step('Add fields, so FDS has something to show', async () => {
		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'id',
			label_i18n: {en_US: 'Id'},
			type: 'integer',
		});

		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'type',
			label_i18n: {en_US: 'Type'},
			type: 'string',
		});

		await dataSetManagerApiHelpers.createDataSetTableSection({
			dataSetERC,
			fieldName: 'sortable',
			label_i18n: {en_US: 'Sortable'},
			type: 'boolean',
		});
	});

	await test.step('Create a new "API Rest Application" selection filter', async () => {
		await dataSetManagerApiHelpers.createDataSetSelectionFilter({
			dataSetERC,
			fieldName: 'type',
			itemKey: 'type',
			itemLabel: 'type',
			label_i18n: {en_US: filterLabel},
			multiple: true,
			source: `/o/${apiHeadlessURL}`,
			sourceType: 'API_REST_APPLICATION',
		});
	});

	await test.step('Configure Data Set fragment', async () => {
		await dataSetFragmentPage.configureDataSetFragment({
			dataSetLabel,
			layout,
		});
	});

	await test.step('Check current items in the Frontend Data Set', async () => {
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 3 of 3 entries.'
			)
		).toBeVisible();
	});

	await test.step('Select filter', async () => {
		await dataSetFragmentPage.selectFilter(filterLabel);
	});

	await test.step('Configure and apply filter', async () => {
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'array',
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'boolean',
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'integer',
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'object',
			})
		).toBeVisible();
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'string',
			})
		).toBeVisible();

		await dataSetFragmentPage.filterItem
			.getByRole('checkbox', {name: 'integer'})
			.check();
		await dataSetFragmentPage.filterItem
			.getByRole('button', {name: 'Add filter'})
			.click();

		// Close filter

		await dataSetFragmentPage.page.keyboard.press('Escape');
	});

	await test.step('Check that the filter works', async () => {
		await dataSetFragmentPage.filterResumeButton.waitFor({
			state: 'visible',
		});

		await expect(
			dataSetFragmentPage.page.getByRole('button', {
				name: `${filterLabel}: integer`,
			})
		).toBeVisible();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tr')
				.filter({
					has: dataSetFragmentPage.page
						.getByText('integer', {exact: true})
						.first(),
				})
				.locator('.dnd-td')
				.nth(1)
		).toHaveText(['integer']);

		await expect(
			dataSetFragmentPage.page.getByText('Showing 1 to 1 of 1 entries.')
		).toBeVisible();
	});

	await test.step('Open filters component', async () => {
		await dataSetFragmentPage.filterButton.click();
	});

	await test.step('Select filter', async () => {
		await expect(
			dataSetFragmentPage.filterItem.getByRole('checkbox', {
				name: 'boolean',
			})
		).toBeVisible();

		await dataSetFragmentPage.filterItem
			.getByRole('checkbox', {name: 'boolean'})
			.check();
		await dataSetFragmentPage.addFilterButton.click();

		// Close filter

		await dataSetFragmentPage.page.keyboard.press('Escape');
	});

	await test.step('Check that the filter works', async () => {
		await dataSetFragmentPage.filterResumeButton.waitFor({
			state: 'visible',
		});

		await expect(
			dataSetFragmentPage.page.getByRole('button', {
				name: `${filterLabel}: integer, boolean`,
			})
		).toBeVisible();

		await expect(
			dataSetFragmentPage.page
				.locator('.dnd-tr')
				.filter({
					has: dataSetFragmentPage.page
						.getByText('boolean', {exact: true})
						.first(),
				})
				.locator('.dnd-td')
				.nth(1)
		).toHaveText(['boolean']);

		await expect(
			dataSetFragmentPage.page.getByText('Showing 1 to 2 of 2 entries.')
		).toBeVisible();
	});

	await test.step('Can reset applied filters', async () => {
		await dataSetFragmentPage.resetFilterButton.click();
	});

	await test.step('Check initial items in the Frontend Data Set', async () => {
		await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

		await expect(
			dataSetFragmentPage.paginationResults.getByText(
				'Showing 1 to 3 of 3 entries.'
			)
		).toBeVisible();
	});
});

test(
	'Selection filter of type "API REST Application" with a composed field name is displayed in the fragment',
	{tag: '@25905'},
	async ({dataSetFragmentPage, dataSetManagerApiHelpers, layout}) => {
		const filterLabel = getRandomString();
		const customDataSetLabel = getRandomString();
		const customDataSetERC = getRandomString();
		dataSetERCs.push(customDataSetERC);

		await test.step('Create custom data set of Data Sets', async () => {
			await dataSetManagerApiHelpers.createDataSet({
				erc: customDataSetERC,
				label: customDataSetLabel,
				restApplication: `${API_ENDPOINT_PATH}`,
				restSchema: 'DataSet',
			});
		});

		await test.step('Add some card sections', async () => {
			await dataSetManagerApiHelpers.createDataSetCardsSection({
				dataSetERC: customDataSetERC,
				fieldName: 'label',
				name: 'title',
			});
		});

		await test.step('Create a new "API Rest Application" selection filter for card fields', async () => {
			await dataSetManagerApiHelpers.createDataSetSelectionFilter({
				dataSetERC: customDataSetERC,
				fieldName: 'dataSetToDataSetCardsSections[]fieldName',
				itemKey: 'fieldName',
				itemLabel: 'fieldName',
				label_i18n: {en_US: filterLabel},
				multiple: true,
				source: `/o${API_ENDPOINT_PATH}/cards-sections/`,
				sourceType: 'API_REST_APPLICATION',
			});
		});

		await test.step('Configure Data Set fragment', async () => {
			await dataSetFragmentPage.configureDataSetFragment({
				dataSetLabel: customDataSetLabel,
				layout,
			});
		});

		await test.step('Check current items in the Frontend Data Set', async () => {
			await dataSetFragmentPage.paginationResults.scrollIntoViewIfNeeded();

			await expect(
				dataSetFragmentPage.paginationResults.getByText(
					'Showing 1 to 2 of 2 entries.'
				)
			).toBeVisible();
		});

		await test.step('Select filter', async () => {
			await dataSetFragmentPage.selectFilter(filterLabel);
		});

		await test.step('Configure and apply filter', async () => {
			await expect(
				dataSetFragmentPage.filterItem.getByRole('checkbox', {
					name: 'label',
				})
			).toBeVisible();

			await dataSetFragmentPage.filterItem
				.getByRole('checkbox', {name: 'label'})
				.check();
			await dataSetFragmentPage.filterItem
				.getByRole('button', {name: 'Add filter'})
				.click();

			// Close filter

			await dataSetFragmentPage.page.keyboard.press('Escape');
		});

		await test.step('Check that the filter works', async () => {
			await dataSetFragmentPage.filterResumeButton.waitFor({
				state: 'visible',
			});

			await expect(
				dataSetFragmentPage.page.getByRole('button', {
					name: `${filterLabel}: label`,
				})
			).toBeVisible();

			await dataSetFragmentPage.page.locator('.card').first().waitFor();

			const firstCard = dataSetFragmentPage.page.locator('.card').first();

			await expect(firstCard.locator('.card-title')).toContainText(
				customDataSetLabel
			);

			await expect(
				dataSetFragmentPage.page.getByText(
					'Showing 1 to 1 of 1 entries.'
				)
			).toBeVisible();
		});
	}
);
