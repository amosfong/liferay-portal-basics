/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ObjectDefinitionApi} from '@liferay/object-admin-rest-client-js';
import {Page, expect, mergeTests} from '@playwright/test';
import path from 'path';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../fixtures/featureFlagsTest';
import {fragmentsPagesTest} from '../../fixtures/fragmentPagesTest';
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {pageEditorPagesTest} from '../../fixtures/pageEditorPagesTest';
import {pageManagementSiteTest} from '../../fixtures/pageManagementSiteTest';
import {clickAndExpectToBeHidden} from '../../utils/clickAndExpectToBeHidden';
import {clickAndExpectToBeVisible} from '../../utils/clickAndExpectToBeVisible';
import getGlobalSiteId from '../../utils/getGlobalSiteId';
import getRandomString from '../../utils/getRandomString';
import {waitForAlert} from '../../utils/waitForAlert';
import getFormContainerDefinition from '../layout-content-page-editor-web/utils/getFormContainerDefinition';
import getFragmentDefinition from '../layout-content-page-editor-web/utils/getFragmentDefinition';
import getPageDefinition from '../layout-content-page-editor-web/utils/getPageDefinition';
import {getObjectERC} from '../setup/page-management-site/utils/getObjectERC';

const test = mergeTests(
	apiHelpersTest,
	isolatedSiteTest,
	featureFlagsTest({
		'LPS-178052': true,
	}),
	loginTest(),
	fragmentsPagesTest,
	pageEditorPagesTest,
	pageManagementSiteTest
);

async function checkBackButtonTitle(page: Page, title: string) {
	await expect(
		page.locator('.control-menu-nav-item').getByTitle(title)
	).toBeVisible();
}

test(
	'Autocomplete',
	{
		tag: ['@LPS-80503', '@LPS-108566'],
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const setName = getRandomString();

		await fragmentsPage.createFragmentSet(setName);

		// Create fragment

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(setName, fragmentName);

		// Assert fragment editor autocomplete for lfr-widget tags

		await page.locator('.html.source-editor .CodeMirror').click();

		await page.keyboard.type('<lfr-');

		await expect(page.getByText('lfr-widget-asset-list')).toBeVisible();

		await page.keyboard.press('Enter');

		// Assert fragment editor autocomplete for variables

		await page.keyboard.type('${');

		await expect(page.getByText('getterUtil')).toBeVisible();

		await page.keyboard.press('Enter');
		await page.keyboard.press('Enter');

		await page.keyboard.type('[@');

		await expect(page.getByText('liferay_aui')).toBeVisible();
	}
);

test(
	'Autocomplete resources with folders',
	{
		tag: ['@LPS-90063', '@LPS-108566', '@LPS-159235'],
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const setName = getRandomString();

		await fragmentsPage.createFragmentSet(setName);

		// Go to resources tab

		await page.getByRole('link', {name: 'Resources'}).click();

		// Creates a new folder

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'Folder'}),
			trigger: page.getByRole('button', {exact: true, name: 'New'}),
		});

		const folderName = getRandomString();

		await page.getByLabel('Name').fill(folderName);

		await page.getByRole('button', {name: 'Save'}).click();

		await waitForAlert(page);

		await page.getByRole('link', {name: folderName}).click();

		// Adds new file

		const fileChooserPromise = page.waitForEvent('filechooser');

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'File Upload'}),
			trigger: page.getByRole('button', {exact: true, name: 'New'}),
		});

		const fileChooser = await fileChooserPromise;

		await fileChooser.setFiles(
			path.join(__dirname, '/dependencies/image.jpg')
		);

		await expect(page.getByText('Image')).toBeVisible();

		// Create fragment

		await page.getByRole('link', {name: 'Fragments'}).click();

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(setName, fragmentName);

		// Assert fragment editor autocomplete for lfr-widget tags

		await page.locator('.html.source-editor .CodeMirror').click();

		await page.keyboard.type('[resources:');

		await expect(page.getByText(`${folderName}/image.jpg`)).toBeVisible();
	}
);

test(
	'Autosave',
	{
		tag: '@LPS-114145',
	},
	async ({fragmentEditorPage, fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const setName = getRandomString();

		await fragmentsPage.createFragmentSet(setName);

		// Create fragment

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(setName, fragmentName);

		await fragmentEditorPage.addHTML('<p>Custom Text</p>');

		await expect(page.getByText('Changes Saved')).toBeVisible();

		// Edit fragment and assert changes were saved

		await fragmentsPage.goto(site.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(setName);

		await fragmentsPage.clickAction('Edit', fragmentName);

		await expect(page.getByText('<p>Custom Text</p>')).toBeVisible();
	}
);

test(
	'Back button have correct title in edit fragment',
	{
		tag: '@LPS-177682',
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const setName = getRandomString();

		await fragmentsPage.createFragmentSet(setName);

		// Create fragment

		await fragmentsPage.createFragment(setName, getRandomString());

		await checkBackButtonTitle(page, 'Go to Fragments');
	}
);

test(
	'Can add fragment set during copy OOTB fragment',
	{
		tag: ['@LPS-166203', '@LPS-101354', '@LPS-89115'],
	},
	async ({fragmentsPage, page, site}) => {

		// Go to Basic Components fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);
		await fragmentsPage.gotoFragmentSet('Basic Components');

		// Copy to new set when there's no sets and check the copy was done

		const set1 = getRandomString();

		await fragmentsPage.copyFragmentToSet('Button', set1);
		await fragmentsPage.gotoFragmentSet(set1);

		await expect(
			page.getByRole('link').filter({hasText: 'Button (Copy)'})
		).toBeVisible();

		// Copy to new set when we already have sets and check the copy was done

		const set2 = getRandomString();

		await fragmentsPage.gotoFragmentSet('Basic Components');
		await fragmentsPage.copyFragmentToSet('Button', set2);
		await fragmentsPage.gotoFragmentSet(set2);

		await expect(
			page.getByRole('link').filter({hasText: 'Button (Copy)'})
		).toBeVisible();

		// Copy to existing set and check the copy was done

		await fragmentsPage.gotoFragmentSet('Basic Components');
		await fragmentsPage.copyFragmentToSet('Heading', set1);
		await fragmentsPage.gotoFragmentSet(set1);

		await expect(
			page.getByRole('link').filter({hasText: 'Heading (Copy)'})
		).toBeVisible();
	}
);

test(
	'Copy global fragment collection',
	{
		tag: ['@LPS-98501', '@LPS-101230', '@LPS-100540'],
	},
	async ({apiHelpers, fragmentsPage, page, site}) => {

		// Create global fragment set

		const globalSiteId = await getGlobalSiteId(apiHelpers);

		const globalFragmentCollectionName = getRandomString();

		const globalFragmentCollection =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: globalSiteId,
					name: globalFragmentCollectionName,
				}
			);

		// Create global fragment

		const fragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId: globalFragmentCollection.fragmentCollectionId,
			groupId: globalSiteId,
			html: '<div class="fragment-name"><img src="[resources:image]" /></div>',
			name: fragmentEntryName,
		});

		// Create fragment set in current site

		const fragmentCollectionName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
			{
				groupId: site.id,
				name: fragmentCollectionName,
			}
		);

		// Assert global fragment view site usages is disabled

		await fragmentsPage.goto('/global');

		await fragmentsPage.gotoFragmentSet(globalFragmentCollectionName);

		await clickAndExpectToBeVisible({
			autoClick: false,
			target: page.getByRole('menuitem', {name: 'View Site Usages'}),
			trigger: page
				.locator('.card-row')
				.filter({hasText: fragmentEntryName})
				.getByLabel('More actions'),
		});

		await expect(
			page.getByRole('menuitem', {name: 'View Site Usages'})
		).toHaveClass(/disabled/);

		// Add resource

		await fragmentsPage.goto('/global');

		await fragmentsPage.gotoFragmentSet(globalFragmentCollectionName);

		const resources = page.getByRole('link', {name: 'Resources'});

		await resources.click();

		const fileChooserPromise = page.waitForEvent('filechooser');

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'File Upload'}),
			trigger: page.getByRole('button', {exact: true, name: 'New'}),
		});

		const fileChooser = await fileChooserPromise;

		await fileChooser.setFiles(
			path.join(__dirname, '/dependencies/image.jpg')
		);

		await expect(page.getByText('Image')).toBeVisible();

		// Assert global fragment in current site

		await fragmentsPage.goto(site.friendlyUrlPath);

		await page
			.getByRole('menuitem', {
				exact: true,
				name: globalFragmentCollectionName,
			})
			.click();

		await expect(
			page.getByTitle(fragmentEntryName, {exact: true})
		).toBeVisible();

		// Copy fragment

		await fragmentsPage.copyFragmentToSet(
			fragmentEntryName,
			fragmentCollectionName
		);

		// Go to fragment collection

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		// Assert copied fragment

		await expect(
			page.getByTitle(`${fragmentEntryName} (Copy)`, {exact: true})
		).toBeVisible();

		// Assert copied fragment resource

		await resources.click();

		await expect(page.getByText('Image')).toBeVisible();

		// Delete global fragment collection

		await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
			globalFragmentCollection.fragmentCollectionId
		);
	}
);

test(
	'Can add, delete, copy and rename a fragment via UI',
	{
		tag: '@LPS-97184',
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const fragmentSetName = getRandomString();

		await fragmentsPage.createFragmentSet(fragmentSetName);

		// Create fragment

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(fragmentSetName, fragmentName);

		await fragmentsPage.goto(site.friendlyUrlPath);

		await expect(
			page.getByTitle(fragmentName, {exact: true})
		).toBeVisible();

		// Copy fragment

		await fragmentsPage.copyFragment(fragmentName);

		await expect(
			page.getByTitle(`${fragmentName} (Copy)`, {exact: true})
		).toBeVisible();

		// Delete fragment

		await fragmentsPage.deleteFragment(`${fragmentName} (Copy)`);

		await expect(
			page.getByTitle(`${fragmentName} (Copy)`, {exact: true})
		).not.toBeVisible();

		// Rename fragment

		const newFragmentName = getRandomString();

		await fragmentsPage.renameFragment(newFragmentName, fragmentName);

		await expect(
			page.getByTitle(fragmentName, {exact: true})
		).not.toBeVisible();

		await expect(
			page.getByTitle(newFragmentName, {exact: true})
		).toBeVisible();
	}
);

test(
	'Can check cacheable for fragments when create them in portal and they are non-cacheable by default',
	{
		tag: '@LPS-108376',
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration and create fragment set

		await fragmentsPage.goto(site.friendlyUrlPath);

		const fragmentSetName = getRandomString();

		await fragmentsPage.createFragmentSet(fragmentSetName);

		// Create fragment

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(fragmentSetName, fragmentName);

		await fragmentsPage.goto(site.friendlyUrlPath);

		await expect(
			page.locator('span').filter({hasText: 'Cached'}).first()
		).not.toBeVisible();

		await fragmentsPage.markAsCacheable(fragmentName);

		await expect(
			page.locator('span').filter({hasText: 'Cached'}).first()
		).toBeVisible();
	}
);

test('Can delete fragment set', async ({fragmentsPage, page, site}) => {

	// Go to fragment administration

	await fragmentsPage.goto(site.friendlyUrlPath);

	// Create fragment set

	const fragmentSetName = getRandomString();

	await fragmentsPage.createFragmentSet(fragmentSetName);

	await expect(
		page.getByRole('menuitem', {exact: true, name: fragmentSetName})
	).toBeVisible();

	// Go to Basic Components fragment set

	await fragmentsPage.deleteFragmentSet(fragmentSetName);

	await expect(
		page.getByRole('menuitem', {exact: true, name: fragmentSetName})
	).not.toBeVisible();
});

test(
	'Can see contributed fragment set in fragment administration',
	{
		tag: '@LPS-89115',
	},
	async ({fragmentsPage, page, site}) => {

		// Go to fragment administration

		await fragmentsPage.goto(site.friendlyUrlPath);

		// Go to Basic Components fragment set

		await fragmentsPage.gotoFragmentSet('Basic Components');

		await expect(
			page.getByRole('link').filter({hasText: 'Button'})
		).toBeVisible();

		// Go to Basic Components fragment set

		await fragmentsPage.gotoFragmentSet('Featured Content');

		await expect(
			page.getByRole('link').filter({hasText: 'Banner Slider'})
		).toBeVisible();
	}
);

test(
	'No alert popup when add a fragment set with XSS name',
	{
		tag: '@LPS-121200',
	},
	async ({apiHelpers, fragmentsPage, page}) => {

		// Add listener with expect so it fails when a browser dialog is shown

		page.on('dialog', async (dialog) => {
			dialog.accept();

			expect(
				dialog.message(),
				'This alert should not be shown'
			).toBeNull();
		});

		// Create go to fragment administration to check dialog is not shown

		const site = await apiHelpers.headlessSite.createSite({
			name: '<script>alert(123);</script>',
		});

		await fragmentsPage.goto(site.friendlyUrlPath);

		expect(await apiHelpers.headlessSite.deleteSite(site.id)).toBeOK();
	}
);

test(
	'Can select default fragment for form button type',
	{
		tag: '@LPD-10727',
	},
	async ({
		apiHelpers,
		fragmentEditorPage,
		fragmentsPage,
		page,
		pageEditorPage,
		pageManagementSite,
	}) => {

		// Go to fragment administration

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		// Create a form button fragment

		const fragmentSetName = getRandomString();

		await fragmentsPage.createFragmentSet(fragmentSetName);

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(
			fragmentSetName,
			fragmentName,
			'form',
			['Form Button']
		);

		await fragmentEditorPage.addHTML(` 
			<button class="btn btn-sm" data-lfr-editable-id="submit-button-text" data-lfr-editable-type="text" id="fragment-submit-button" type="submit">
				Custom Submit
			</button>	
		`);

		await fragmentEditorPage.publish();

		// Go to configuration

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'Configuration'}),
			trigger: page.getByLabel('Options', {exact: true}),
		});

		// Change default form button fragment

		await page
			.locator('tr')
			.filter({hasText: 'Form Button'})
			.getByRole('button', {name: 'Select'})
			.click();

		const frameLocator = page.frameLocator(
			'iframe[title="Select Fragment"]'
		);

		const siteLink = frameLocator
			.locator('.nav-link')
			.filter({hasText: pageManagementSite.name});

		const fragmentSetCard = frameLocator
			.locator('.card-horizontal')
			.filter({hasText: fragmentSetName});

		const fragmentCard = frameLocator
			.locator('.card-page-item-asset')
			.filter({hasText: fragmentName});

		await clickAndExpectToBeVisible({
			target: fragmentSetCard,
			trigger: siteLink,
		});

		await clickAndExpectToBeVisible({
			target: fragmentCard,
			trigger: fragmentSetCard,
		});

		await clickAndExpectToBeHidden({
			target: page.locator('.modal-dialog'),
			trigger: fragmentCard,
		});

		// Save and check that the fragment is selected

		await page.getByRole('button', {name: 'Save'}).click();

		await waitForAlert(page);

		const fragmentInput = page
			.locator('tr')
			.filter({hasText: 'Form Button'})
			.locator('input');

		await expect(fragmentInput).toHaveValue(fragmentName);

		// Create a page

		const formId = getRandomString();

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([
				getFormContainerDefinition({
					id: formId,
				}),
			]),
			siteId: pageManagementSite.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, pageManagementSite.friendlyUrlPath);

		// Check that the custom button is used

		await pageEditorPage.mapFormFragment(formId, 'Lemon', [
			'Lemon Size',
			'Lemon Basket to Lemons',
		]);

		await expect(page.getByText('Custom Submit')).toBeVisible();
	}
);

test(
	'Can view usages',
	{
		tag: ['@LPS-168163', '@LPS-177682'],
	},
	async ({apiHelpers, fragmentsPage, page, pageManagementSite}) => {

		// Create new fragment collection

		const fragmentCollectionName = getRandomString();

		const {fragmentCollectionId} =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: pageManagementSite.id,
					name: fragmentCollectionName,
				}
			);

		// Create custom basic fragment

		const basicFragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId,
			groupId: pageManagementSite.id,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: basicFragmentEntryName,
		});

		// Create custom input fragment

		const inputFragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId,
			groupId: pageManagementSite.id,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: inputFragmentEntryName,
			type: 'input',
			typeOptions: {fieldTypes: ['long-text', 'text']},
		});

		// Assert view usages is disabled

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		const viewUsagesAction = page.getByRole('menuitem', {
			name: 'View Usages',
		});

		await clickAndExpectToBeVisible({
			autoClick: false,
			target: viewUsagesAction,
			trigger: page
				.locator('.card-row')
				.filter({hasText: basicFragmentEntryName})
				.getByLabel('More actions'),
		});

		await expect(viewUsagesAction).toHaveAttribute('disabled');

		// Create a content page with custom basic fragment and custom input fragment

		const basicFragmentDefinition = getFragmentDefinition({
			id: getRandomString(),
			key: basicFragmentEntryName,
		});

		const objectDefinitionApiClient =
			await apiHelpers.buildRestClient(ObjectDefinitionApi);

		const {className: objectDefinitionClassName} = (
			await objectDefinitionApiClient.getObjectDefinitionByExternalReferenceCode(
				getObjectERC('Lemon')
			)
		).body;

		const inputFragmentDefinition = getFragmentDefinition({
			id: getRandomString(),
			key: inputFragmentEntryName,
		});

		const formDefinition = getFormContainerDefinition({
			id: getRandomString(),
			objectDefinitionClassName,
			pageElements: [inputFragmentDefinition],
		});

		const layoutTitle = getRandomString();

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([
				basicFragmentDefinition,
				formDefinition,
			]),
			siteId: pageManagementSite.id,
			title: layoutTitle,
		});

		// Assert basic fragment view usages

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		await fragmentsPage.clickAction('View Usages', basicFragmentEntryName);

		await expect(
			page.getByRole('menuitem', {name: 'Pages (2)'})
		).toBeAttached();

		// Assert input fragment view usages

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		await fragmentsPage.clickAction('View Usages', inputFragmentEntryName);

		await expect(
			page.getByRole('menuitem', {name: 'Pages (2)'})
		).toBeAttached();

		// Assert tooltip of back button

		await checkBackButtonTitle(page, 'Go to Fragments');

		// Delete data

		await apiHelpers.jsonWebServicesLayout.deleteLayout(layout.id);

		expect(
			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				fragmentCollectionId
			)
		).toHaveProperty('fragmentCollectionId', fragmentCollectionId);
	}
);

test(
	'Draft form fragments are not present in configuration and do not have form config link',
	{
		tag: '@LPS-180331',
	},
	async ({apiHelpers, fragmentsPage, page, pageManagementSite}) => {

		// Create new fragment collection

		const fragmentCollectionName = getRandomString();

		const {fragmentCollectionId} =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: pageManagementSite.id,
					name: fragmentCollectionName,
				}
			);

		// Create custom input fragment

		const publishedInputFragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId,
			groupId: pageManagementSite.id,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: publishedInputFragmentEntryName,
			type: 'input',
			typeOptions: {fieldTypes: ['long-text', 'text']},
		});

		// Go to fragment set

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		// Create custom input fragment

		const draftInputFragmentEntryName = getRandomString();

		await fragmentsPage.createFragment(
			fragmentCollectionName,
			draftInputFragmentEntryName,
			'form',
			['Long Text']
		);

		// Go to configuration tab

		await page.getByRole('tab', {name: 'Configuration'}).click();

		// Assert form configuration link is not present in fragment configuration for draft fragments

		await expect(
			page.getByRole('link', {
				name: 'Define the default form fragments for this site.',
			})
		).not.toBeAttached();

		// Assert draft fragments are not present under form configuration

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await expect(async () => {
			await fragmentsPage.selectDefaultFormFragment({
				fieldType: 'Long Text',
				fragmentCollectionName,
				fragmentName: draftInputFragmentEntryName,
				siteName: pageManagementSite.name,
			});
		}).not.toPass();

		// Delete data

		expect(
			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				fragmentCollectionId
			)
		).toHaveProperty('fragmentCollectionId', fragmentCollectionId);
	}
);

test(
	'Form fragment configuration should show input fragment of same type',
	{
		tag: '@LPS-180331',
	},
	async ({apiHelpers, fragmentsPage, page, pageManagementSite}) => {

		// Create new fragment collection

		const fragmentCollectionName = getRandomString();

		const {fragmentCollectionId} =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: pageManagementSite.id,
					name: fragmentCollectionName,
				}
			);

		// Create custom input fragment

		const inputFragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId,
			groupId: pageManagementSite.id,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: inputFragmentEntryName,
			type: 'input',
			typeOptions: {fieldTypes: ['long-text', 'text']},
		});

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId,
			groupId: pageManagementSite.id,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: getRandomString(),
			type: 'input',
			typeOptions: {fieldTypes: ['boolean']},
		});

		// Create custom input fragment in global site

		const globalSiteId = await getGlobalSiteId(apiHelpers);

		const globalFragmentCollectionName = getRandomString();

		const globalFragmentCollection =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: globalSiteId,
					name: globalFragmentCollectionName,
				}
			);

		const globalInputFragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId: globalFragmentCollection.fragmentCollectionId,
			groupId: globalSiteId,
			html: '<div class="fragment-name">Fragment Example</div>',
			name: globalInputFragmentEntryName,
			type: 'input',
			typeOptions: {fieldTypes: ['boolean']},
		});

		// Go to fragment set

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet(fragmentCollectionName);

		// Assert form configuration link is present in fragment configuration for published fragments

		await fragmentsPage.clickAction('Edit', inputFragmentEntryName);

		await page.getByRole('tab', {name: 'Configuration'}).click();

		await expect(
			page.getByRole('link', {
				name: 'Define the default form fragments for this site.',
			})
		).toBeVisible();

		// Assert new input fragment is not present under checkbox type

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await expect(async () => {
			await fragmentsPage.selectDefaultFormFragment({
				fieldType: 'Boolean',
				fragmentCollectionName,
				fragmentName: inputFragmentEntryName,
				siteName: pageManagementSite.name,
			});
		}).not.toPass();

		// Assert global input fragment is present under checkbox type

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await expect(async () => {
			await fragmentsPage.selectDefaultFormFragment({
				fieldType: 'Boolean',
				fragmentCollectionName: globalFragmentCollectionName,
				fragmentName: globalInputFragmentEntryName,
				siteName: 'Global',
			});
		}).toPass();

		await expect(
			fragmentsPage.selectFragmentIFrame.getByText(
				globalInputFragmentEntryName
			)
		).toBeAttached();

		// Assert new input fragment is present under long text type

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await expect(async () => {
			await fragmentsPage.selectDefaultFormFragment({
				fieldType: 'Long Text',
				fragmentCollectionName,
				fragmentName: inputFragmentEntryName,
				siteName: pageManagementSite.name,
			});
		}).toPass();

		// Delete data

		expect(
			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				globalFragmentCollection.fragmentCollectionId
			)
		).toHaveProperty(
			'fragmentCollectionId',
			globalFragmentCollection.fragmentCollectionId
		);

		expect(
			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				fragmentCollectionId
			)
		).toHaveProperty('fragmentCollectionId', fragmentCollectionId);
	}
);

test(
	'Can change resource image and propagate',
	{tag: '@LPS-152633'},
	async ({
		apiHelpers,
		fragmentEditorPage,
		fragmentsPage,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create a new fragment set and fragment

		await fragmentsPage.goto(site.friendlyUrlPath);

		const setName = getRandomString();

		await fragmentsPage.createFragmentSet(setName);

		const fragmentName = getRandomString();

		await fragmentsPage.createFragment(setName, fragmentName);

		await fragmentEditorPage.addHTML(` 
		<div class="fragment-name">
			Test Fragment
		</div>
	`);

		await fragmentEditorPage.publish();

		// Add the fragment to a page

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([]),
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		await pageEditorPage.addFragment(setName, fragmentName);

		await expect(page.getByText('Test Fragment')).toBeVisible();

		// Edit the fragment but don't publish it

		await fragmentsPage.goto(site.friendlyUrlPath);

		await fragmentsPage.clickAction('Edit', fragmentName);

		await fragmentEditorPage.addHTML(` 
		<div class="fragment-name">
			Test Fragment New
		</div>
	`);

		await fragmentEditorPage.publish();

		// Check that the fragment is not updated on the page

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		await expect(
			page.getByText('Test Fragment', {exact: true})
		).toBeVisible();

		await fragmentsPage.goto(site.friendlyUrlPath);

		// Propagate the changes

		await fragmentsPage.clickAction('View Usages', fragmentName);

		await page
			.getByLabel('Select All Items on the Page')
			.check({trial: true});

		await page.getByLabel('Select All Items on the Page').check();

		await page.getByRole('button', {name: 'Propagate'}).click();

		await waitForAlert(page);

		// Check that the fragment is updated on the page

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		await expect(page.getByText('Test Fragment New')).toBeVisible();
	}
);

test(
	'View site usages and propagate changes of global fragments',
	{
		tag: '@LPS-100540',
	},
	async ({apiHelpers, fragmentEditorPage, fragmentsPage, page, site}) => {

		// Create global fragment set

		const globalSiteId = await getGlobalSiteId(apiHelpers);

		const globalFragmentCollectionName = getRandomString();

		const globalFragmentCollection =
			await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
				{
					groupId: globalSiteId,
					name: globalFragmentCollectionName,
				}
			);

		// Create global fragment

		const fragmentEntryName = getRandomString();

		await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
			fragmentCollectionId: globalFragmentCollection.fragmentCollectionId,
			groupId: globalSiteId,
			html: '<div class="fragment-name">Custom Fragment</div>',
			name: fragmentEntryName,
		});

		// Add layout with global fragment

		const globalFragmentDefinition = getFragmentDefinition({
			id: getRandomString(),
			key: fragmentEntryName,
		});

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([globalFragmentDefinition]),
			siteId: site.id,
			title: getRandomString(),
		});

		// Assert custom fragment in view mode

		await page.goto(`/web${site.friendlyUrlPath}${layout.friendlyUrlPath}`);

		await expect(page.getByText('Custom Fragment')).toBeVisible();

		// Create new site

		const siteName = getRandomString();

		const newSite = await apiHelpers.headlessSite.createSite({
			name: siteName,
		});

		// Add layout with global fragment to new site

		const newSiteLayout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([globalFragmentDefinition]),
			siteId: newSite.id,
			title: getRandomString(),
		});

		// Assert custom fragment in view mode for new site

		await page.goto(
			`/web${newSite.friendlyUrlPath}${newSiteLayout.friendlyUrlPath}`
		);

		await expect(page.getByText('Custom Fragment')).toBeVisible();

		// Go to global site

		await fragmentsPage.goto('/global');

		await fragmentsPage.gotoFragmentSet(globalFragmentCollectionName);

		// Edit custom fragment

		await fragmentsPage.clickAction('Edit', fragmentEntryName);

		await fragmentEditorPage.addHTML(` 
			<div class="fragment-name">
				Edited Custom Fragment
			</div>
		`);

		await fragmentEditorPage.publish();

		// Assert usages

		await fragmentsPage.clickAction('View Site Usages', fragmentEntryName);

		await expect(page.getByRole('row', {name: site.name})).toContainText(
			'2'
		);
		await expect(page.getByRole('row', {name: newSite.name})).toContainText(
			'2'
		);

		// Propagate changes

		await page.getByLabel('Select All Items on the Page').check();

		await page.getByRole('button', {name: 'Propagate'}).click();

		await waitForAlert(page);

		// Assert custom fragment in view mode

		await page.goto(`/web${site.friendlyUrlPath}${layout.friendlyUrlPath}`);

		await expect(page.getByText('Edited Custom Fragment')).toBeVisible();

		// Assert custom fragment in view mode for new site

		await page.goto(
			`/web${newSite.friendlyUrlPath}${newSiteLayout.friendlyUrlPath}`
		);

		await expect(page.getByText('Edited Custom Fragment')).toBeVisible();

		// Clean up

		await expect(
			await apiHelpers.headlessSite.deleteSite(newSite.id)
		).toBeOK();

		await apiHelpers.jsonWebServicesLayout.deleteLayout(layout.id);

		expect(
			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				globalFragmentCollection.fragmentCollectionId
			)
		).toHaveProperty(
			'fragmentCollectionId',
			globalFragmentCollection.fragmentCollectionId
		);
	}
);
