/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {applicationsMenuPageTest} from '../../fixtures/applicationsMenuPageTest';
import {collectionsPagesTest} from '../../fixtures/collectionsPagesTest';
import {featureFlagsTest} from '../../fixtures/featureFlagsTest';
import {fragmentsPagesTest} from '../../fixtures/fragmentPagesTest';
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {pageEditorPagesTest} from '../../fixtures/pageEditorPagesTest';
import {pageManagementSiteTest} from '../../fixtures/pageManagementSiteTest';
import {pageViewModePagesTest} from '../../fixtures/pageViewModePagesTest';
import {checkAccessibility} from '../../utils/checkAccessibility';
import {clickAndExpectToBeHidden} from '../../utils/clickAndExpectToBeHidden';
import {clickAndExpectToBeVisible} from '../../utils/clickAndExpectToBeVisible';
import createUserWithPermissions from '../../utils/createUserWithPermissions';
import {expandSection} from '../../utils/expandSection';
import getRandomString from '../../utils/getRandomString';
import {hoverAndExpectToBeVisible} from '../../utils/hoverAndExpectToBeVisible';
import {performUserSwitch} from '../../utils/performLogin';
import {closeProductMenu, openProductMenu} from '../../utils/productMenu';
import getBasicWebContentStructureId from '../../utils/structured-content/getBasicWebContentStructureId';
import {waitForAlert} from '../../utils/waitForAlert';
import {journalPagesTest} from '../journal-web/fixtures/journalPagesTest';
import {ANIMALS_COLLECTION_NAME} from '../setup/page-management-site/constants/animals';
import getCollectionDefinition from './utils/getCollectionDefinition';
import getFragmentDefinition from './utils/getFragmentDefinition';
import getGridDefinition from './utils/getGridDefinition';
import getPageDefinition from './utils/getPageDefinition';

const test = mergeTests(
	apiHelpersTest,
	applicationsMenuPageTest,
	collectionsPagesTest,
	featureFlagsTest({
		'LPD-15596': true,
		'LPD-18221': true,
		'LPS-169837': true,
		'LPS-178052': true,
	}),
	fragmentsPagesTest,
	isolatedSiteTest,
	journalPagesTest,
	loginTest(),
	pageEditorPagesTest,
	pageManagementSiteTest,
	pageViewModePagesTest
);

const PANELS: SidebarTab[] = [
	'Fragments and Widgets',
	'Browser',
	'Page Design Options',
	'Page Rules',
	'Page Content',
	'Comments',
];

test('Renders all panel buttons in the vertical bar', async ({
	apiHelpers,
	page,
	pageEditorPage,
	site,
}) => {
	await page.goto('/');

	// Create page and go to edit mode

	const layout = await apiHelpers.headlessDelivery.createSitePage({
		siteId: site.id,
		title: getRandomString(),
	});

	await pageEditorPage.goto(layout, site.friendlyUrlPath);

	// Check all panel buttons are rendered

	for (const panel of PANELS) {
		const panelButton = page.getByLabel(panel, {exact: true});

		await expect(panelButton).toBeVisible();
		await expect(panelButton).toHaveAttribute(
			'aria-selected',
			panel === PANELS[0] ? 'true' : 'false'
		);
	}
});

test('Renders sidebars visible at desktop size and sidebars not visible at small resolutions', async ({
	apiHelpers,
	page,
	pageEditorPage,
	site,
}) => {
	await page.goto('/');

	// Create content page and go to edit mode

	const layout = await apiHelpers.headlessDelivery.createSitePage({
		siteId: site.id,
		title: getRandomString(),
	});

	await pageEditorPage.goto(layout, site.friendlyUrlPath);

	const panel = page.getByLabel('Fragments and Widgets Panel');
	const configurationPanel = page.getByLabel('Configuration Panel', {
		exact: true,
	});

	await expect(panel).toBeVisible();

	await expect(configurationPanel).toBeVisible();

	// Set small resolution and check panels are not visible

	await page.setViewportSize({height: 600, width: 600});

	await panel.waitFor({state: 'hidden'});

	await expect(panel).not.toBeVisible();

	await expect(configurationPanel).not.toBeVisible();
});

test('Checks if sidebars are open or closed depending on Product Menu', async ({
	apiHelpers,
	page,
	pageEditorPage,
	site,
}) => {
	await page.goto('/');

	// Create content page and go to edit mode

	const layout = await apiHelpers.headlessDelivery.createSitePage({
		siteId: site.id,
		title: getRandomString(),
	});

	await pageEditorPage.goto(layout, site.friendlyUrlPath);

	// Check panels are visible

	const panel = page.getByLabel('Fragments and Widgets Panel');
	const configurationPanel = page.getByLabel('Configuration Panel', {
		exact: true,
	});

	// Check if sidebars are not visible when Product Menu is open

	await panel.waitFor({state: 'visible'});

	await openProductMenu(page);

	await expect(panel).not.toBeVisible();

	await expect(configurationPanel).not.toBeVisible();

	// Check if sidebars are visible when Product Menu is closed

	await closeProductMenu(page);

	await expect(panel).toBeVisible();

	await expect(configurationPanel).toBeVisible();
});

test('Checks sidebar accessibility', async ({
	apiHelpers,
	page,
	pageEditorPage,
	site,
}) => {

	// Create page and go to edit mode

	const layout = await apiHelpers.headlessDelivery.createSitePage({
		siteId: site.id,
		title: getRandomString(),
	});

	await pageEditorPage.goto(layout, site.friendlyUrlPath);

	// Check where the focus goes when the sidebar is closed

	await page.getByRole('button', {name: 'Close'}).press('Enter');

	await expect(
		page.getByLabel('Fragments and Widgets', {exact: true})
	).toBeFocused();

	// Check with axe

	await checkAccessibility({
		page,
		selectors: ['.page-editor__sidebar'],
	});
});

test.describe('Browser Panel', () => {
	test('Deleting a fragment while its editable is selected', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create a page with a Dropdown and a Heading fragment

		const dropdownDefinition = getFragmentDefinition({
			id: getRandomString(),
			key: 'BASIC_COMPONENT-dropdown',
		});

		const headingId = getRandomString();
		const headingDefinition = getFragmentDefinition({
			id: headingId,
			key: 'BASIC_COMPONENT-heading',
		});

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([
				dropdownDefinition,
				headingDefinition,
			]),
			siteId: site.id,
			title: getRandomString(),
		});

		// Go to edit mode of page

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Select editable

		await pageEditorPage.selectEditable(headingId, 'element-text');

		// Go to Browser an delete the fragment

		await pageEditorPage.goToSidebarTab('Browser');

		await hoverAndExpectToBeVisible({
			autoClick: true,
			target: page
				.locator('.treeview-item', {hasText: 'Heading'})
				.getByLabel('Options'),
			trigger: page.getByLabel('Select Heading'),
		});

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'Delete'}),
			trigger: page
				.locator('.treeview-item', {hasText: 'Heading'})
				.getByLabel('Options'),
		});

		await pageEditorPage.waitForChangesSaved();
	});

	test(
		'Hiding a fragment in smaller viewports does not affect Desktop',
		{tag: ['@LPD-42984']},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page with a Grid and a Heading fragment inside it

			const headingId = getRandomString();
			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			const gridDefinition = getGridDefinition({
				columns: [{pageElements: [headingDefinition], size: 12}],
				id: getRandomString(),
			});

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([gridDefinition]),
				siteId: site.id,
				title: getRandomString(),
			});

			// Go to edit mode of page

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Select the Heading

			await pageEditorPage.selectFragment(headingId);

			// Go to Browser and hide the fragment

			await pageEditorPage.goToSidebarTab('Browser');

			const desktopHeading = pageEditorPage.getFragment(headingId);

			await clickAndExpectToBeHidden({
				target: desktopHeading,
				trigger: page
					.locator('.treeview-link')
					.getByLabel('Hide Heading'),
			});

			// Change to Landscape Mobile and show it again

			await pageEditorPage.switchViewport('Landscape Phone');

			const mobileHeading = pageEditorPage.getFragment(headingId, false);

			await clickAndExpectToBeVisible({
				target: mobileHeading,
				trigger: page
					.locator('.treeview-link')
					.getByLabel('Show Heading'),
			});

			// Change to Desktop again and check Heading is still hidden

			await pageEditorPage.switchViewport('Desktop');

			await expect(desktopHeading).toBeHidden();
		}
	);
});

test.describe('Fragments Panel', () => {
	test('Only published fragments are shown in the Fragments Sidebar', async ({
		apiHelpers,
		fragmentsPage,
		page,
		pageEditorPage,
		pageManagementSite,
	}) => {

		// Create unpublished fragment inside Page Management fragment set

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		const unpublishedFragmentName = getRandomString();

		await fragmentsPage.createFragment(
			'Page Management Fragments',
			unpublishedFragmentName
		);

		// Create content page and go to edit mode

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition(),
			siteId: pageManagementSite.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, pageManagementSite.friendlyUrlPath);

		// Check only published fragment is displayed

		await pageEditorPage.goToSidebarTab('Fragments and Widgets');

		await page
			.getByRole('menuitem', {
				exact: true,
				name: 'Page Management Fragments',
			})
			.click();

		await expect(page.getByText('Apple')).toBeVisible();

		await expect(page.getByText(unpublishedFragmentName)).not.toBeVisible();

		// Check that the new set appears in the last position

		const lastFragmentSet = page
			.getByLabel('Fragments', {exact: true})
			.locator('.panel-header')
			.last();

		await expect(lastFragmentSet).toContainText(
			'Page Management Fragments'
		);

		// Delete unpublished fragment

		await fragmentsPage.goto(pageManagementSite.friendlyUrlPath);

		await fragmentsPage.gotoFragmentSet('Page Management Fragments');

		await fragmentsPage.deleteFragment(unpublishedFragmentName);
	});

	test('Can remove search text when pressing backspace', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create content page and go to edit mode

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Open the "Fragments and Widgets" panel

		await pageEditorPage.goToSidebarTab('Fragments and Widgets');

		// Find the search input and type some text

		const searchInput = page.getByPlaceholder('Search...');

		await searchInput.fill('Heading');

		// Verify the search text is present

		await expect(searchInput).toHaveValue('Heading');

		// Press Backspace to remove the text

		await searchInput.press('Backspace');

		// Verify the search text has been removed

		await expect(searchInput).toHaveValue('Headin');
	});

	test('Favorite section is empty when there are no favorites', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create content page and go to edit mode

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Open the "Fragments and Widgets" panel

		await pageEditorPage.goToSidebarTab('Fragments and Widgets');

		// Assert favorite section is empty

		await expect(
			page.getByRole('menuitem', {name: 'Favorites'})
		).not.toBeVisible();
	});

	test(
		'A widget marked as favorite in a content page is also marked in a widget page',
		{tag: '@LPS-161732'},
		async ({apiHelpers, page, pageEditorPage, site, widgetPagePage}) => {

			// Create content page and go to edit mode

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Go to the Widget tab a select one widget as favorite

			await pageEditorPage.goToSidebarTab('Fragments and Widgets');

			await page.getByRole('tab', {exact: true, name: 'Widgets'}).click();

			let highlightedSet = page
				.locator('.page-editor__collapse')
				.filter({hasText: 'Highlighted'});

			await highlightedSet.waitFor();

			const favoriteButton = page.getByTitle(
				'Mark Reports Display as Favorite'
			);

			// If the widget is already marked as favorite, unmark it

			if ((await favoriteButton.count()) > 1) {
				await favoriteButton.first().click();

				await expect(favoriteButton).toHaveCount(1);
			}

			await favoriteButton.click();

			// Check that the widget is inside Highlighted set

			await expect(highlightedSet).toContainText('Reports Display');

			// Create a Widget page and check that the widget is also inside Highlighted set in a widget page

			const widgetLayout =
				await apiHelpers.jsonWebServicesLayout.addLayout({
					groupId: site.id,
					title: getRandomString(),
				});

			await widgetPagePage.goto(widgetLayout, site.friendlyUrlPath);

			await widgetPagePage.openAddPanel();

			highlightedSet = page.locator('.panel', {hasText: 'Highlighted'});

			await expect(highlightedSet).toContainText('Reports Display');

			// Check that a new user with update permissions cannot see the changes

			const company =
				await apiHelpers.jsonWebServicesCompany.getCompanyByWebId(
					'liferay.com'
				);

			const user = await createUserWithPermissions({
				apiHelpers,
				rolePermissions: [
					{
						actionIds: ['UPDATE'],
						primaryKey: company.companyId,
						resourceName: 'com.liferay.portal.kernel.model.Layout',
						scope: 1,
					},
				],
			});

			await performUserSwitch(page, user.alternateName);

			await widgetPagePage.goto(widgetLayout, site.friendlyUrlPath);

			await widgetPagePage.openAddPanel();

			await expect(highlightedSet).not.toContainText('Reports Display');
		}
	);

	test('Fragment and widget sets are reordered', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
		widgetPagePage,
	}) => {
		const moveSet = async (setTitle: string) => {

			// Move the set 2 positions down

			await page.getByLabel(setTitle, {exact: true}).press('Enter');

			await page.keyboard.press('ArrowDown');

			await page.keyboard.press('ArrowDown');

			await page.keyboard.press('ArrowDown');

			await page.keyboard.press('Enter');
		};

		// Create content page and go to edit mode

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Open the "Fragments and Widgets" and get the first set of fragments

		await pageEditorPage.goToSidebarTab('Fragments and Widgets');

		const tabpanel = page
			.locator('.page-editor__sidebar__fragments-widgets-panel')
			.getByRole('tabpanel');

		const fragmentSets = tabpanel.first().locator('.panel-header');

		const firstFragmentSet = await fragmentSets.first().textContent();

		// Got to the Widgets tab and get the first set of widgets

		await page.getByRole('tab', {exact: true, name: 'Widgets'}).click();

		await page.getByText('Highlighted').waitFor();

		let widgetSets = tabpanel
			.last()
			.locator('.panel-header', {hasNotText: 'Highlighted'});

		const firstWidgetSet = await widgetSets.first().textContent();

		// Open "Reorder Sets" modal and reorder the first set of fragments

		await page.getByTitle('Reorder Sets', {exact: true}).click();

		const modal = page.locator('.modal-body');

		await modal
			.getByText('Fragments and widgets sets can be ordered')
			.waitFor();

		await moveSet(firstFragmentSet);

		// Go to the Widget tab and reorder the first set

		await modal.getByRole('tab', {exact: true, name: 'Widgets'}).click();

		await modal.getByText(firstWidgetSet).waitFor();

		await moveSet(firstWidgetSet);

		// Save

		await page.getByText('Save', {exact: true}).click();

		await pageEditorPage.waitForChangesSaved();

		// Check that the position of the first widget set has changed

		await expect(widgetSets.nth(2)).toContainText(firstWidgetSet);

		// Go back to the Fragments tab and check that the position of the first fragment has changed

		await page.getByRole('tab', {exact: true, name: 'Fragments'}).click();

		await expect(fragmentSets.nth(2)).toContainText(firstFragmentSet);

		// Refresh the page and check that order is maintained

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		await expect(fragmentSets.nth(2)).toContainText(firstFragmentSet);

		await page.getByRole('tab', {exact: true, name: 'Widgets'}).click();

		await expect(widgetSets.nth(2)).toContainText(firstWidgetSet);

		// Create a Widget page and check that the order is maintained on the widget page

		const widgetLayout = await apiHelpers.jsonWebServicesLayout.addLayout({
			groupId: site.id,
			title: getRandomString(),
		});

		await widgetPagePage.goto(widgetLayout, site.friendlyUrlPath);

		await widgetPagePage.openAddPanel();

		widgetSets = page.locator('.sidebar-body__add-panel .panel-header', {
			hasNotText: 'Highlighted',
		});

		await expect(widgetSets.nth(2)).toContainText(firstWidgetSet);

		// Check that a new user with update permissions cannot see the changes

		const company =
			await apiHelpers.jsonWebServicesCompany.getCompanyByWebId(
				'liferay.com'
			);

		const user = await createUserWithPermissions({
			apiHelpers,
			rolePermissions: [
				{
					actionIds: ['UPDATE'],
					primaryKey: company.companyId,
					resourceName: 'com.liferay.portal.kernel.model.Layout',
					scope: 1,
				},
			],
		});

		await performUserSwitch(page, user.alternateName);

		await widgetPagePage.goto(widgetLayout, site.friendlyUrlPath);

		await widgetPagePage.openAddPanel();

		[
			'Accounts',
			'Business Intelligence & Reporting',
			'Collaboration',
			'Commerce',
			'Community',
			'Content Management',
			'News',
			'Object',
			'Sample',
			'Search',
		].forEach(async (set, index) => {
			await expect(widgetSets.nth(index)).toContainText(set);
		});
	});

	test(
		'Save interactions with the panel when the page is refresh',
		{tag: '@LPS-76741'},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create content page and go to edit mode

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Change the view of the fragments to Cards

			await pageEditorPage.goToSidebarTab('Fragments and Widgets');

			const firstSetList = page
				.locator('.page-editor__collapse ul')
				.first();

			await expect(firstSetList).toHaveClass(
				/page-editor__fragments-widgets__tab-collection-list/
			);

			await page.getByTitle('Switch to Card View').click();

			// Open Cookie Banner collapse

			const menuDisplayFragmentSet = page.getByRole('menuitem', {
				exact: true,
				name: 'Cookie Banner',
			});

			await expect(menuDisplayFragmentSet).toHaveClass(/collapsed/);

			await menuDisplayFragmentSet.click();

			// Refresh the page and check that everything remains the same

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			await pageEditorPage.goToSidebarTab('Fragments and Widgets');

			await expect(firstSetList).toHaveClass(
				/page-editor__fragments-widgets__tab-collection-cards/
			);

			await expect(menuDisplayFragmentSet).not.toHaveClass(/collapsed/);

			// Reset the panel

			await page.getByTitle('Switch to List View').click();

			await menuDisplayFragmentSet.click();
		}
	);

	test(
		'Move the list fragment from its boundary and check that it is disabled when dragging',
		{tag: '@LPS-130964'},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create content page and go to edit mode

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Go to the Fragments and Widget panel

			await pageEditorPage.goToSidebarTab('Fragments and Widgets');

			const fragment = page
				.locator('.page-editor__fragments-widgets__tab-list-item')
				.filter({hasText: 'External Video'});

			await fragment.hover();

			// Drag the list fragment from its coordinates 0,0 and check that it is disabled when dragging

			const fragmentBox = await fragment.boundingBox();

			await page.mouse.move(fragmentBox.x, fragmentBox.y);

			await page.mouse.down();

			await page
				.getByText('Drag and drop fragments or widgets here.')
				.hover();

			await expect(fragment).toHaveClass(/disabled/);
		}
	);

	test(
		'Correct message is displayed when adding a removed fragment',
		{tag: '@LPD-39508'},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a fragment set with a fragment

			const collectionName = getRandomString();

			const {fragmentCollectionId} =
				await apiHelpers.jsonWebServicesFragmentCollection.addFragmentCollection(
					{
						groupId: site.id,
						name: collectionName,
					}
				);

			const fragmentName = getRandomString();

			await apiHelpers.jsonWebServicesFragmentEntry.addFragmentEntry({
				fragmentCollectionId,
				groupId: site.id,
				html: '<div class="fragment-name">Fragment Example</div>',
				name: fragmentName,
				type: 'component',
			});

			// Create content page and go to edit mode

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Delete the fragment set

			await apiHelpers.jsonWebServicesFragmentCollection.deleteFragmentCollection(
				fragmentCollectionId
			);

			// Try to add the fragment

			await pageEditorPage.goToSidebarTab('Fragments and Widgets');

			const header = page.getByRole('menuitem', {
				exact: true,
				name: collectionName,
			});

			await expandSection(header);

			await page.getByLabel(`Add ${fragmentName}`).focus();

			await page.keyboard.press('Enter');
			await page.keyboard.press('Enter');

			await expect(
				page.getByText(
					'Error:The fragment can no longer be added because it has been deleted.',
					{exact: true}
				)
			).toBeVisible();
		}
	);
});

test.describe('Page Contents Panel', () => {
	const FRAGMENT_FIELDS = [
		{
			id: 'element-text',
			value: {
				text: {
					mapping: {
						fieldKey: 'JournalArticle_title',
						itemReference: {
							contextSource: 'CollectionItem',
						},
					},
				},
			},
		},
	];

	test('Allows editing inline text from Page Content Panel', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create a page with a Heading fragment

		const headingId = getRandomString();
		const headingDefinition = getFragmentDefinition({
			id: headingId,
			key: 'BASIC_COMPONENT-heading',
		});

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([headingDefinition]),
			siteId: site.id,
			title: getRandomString(),
		});

		// Go to edit mode of page

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Go to Page Contents panel

		await pageEditorPage.goToSidebarTab('Page Content');

		// Hover the content and check that the fragment is hovered

		const content = page.getByLabel('Edit Text Heading Example');

		await content.hover();

		const headingFragment = page.locator('.component-heading');

		await expect(headingFragment).toHaveClass(
			/page-editor__editable--content-hovered/
		);

		// Edit inline text

		await content.click();

		const editable = pageEditorPage.getEditable({
			editableId: 'element-text',
			fragmentId: headingId,
		});

		await editable.locator('.cke_editable_inline').waitFor();

		// Clear current content text and fill with new one

		await page.keyboard.press('Control+KeyA');
		await page.keyboard.press('Backspace');

		await page.keyboard.type('New Content');
		await page.locator('body').click();

		await pageEditorPage.waitForChangesSaved();

		await expect(
			page.locator('.page-editor__page-contents__page-content')
		).toContainText('New Content');
	});

	test(
		'Allows editing mapped asset from Page Content Panel',
		{tag: ['@LPS-122204', '@LPS-125985', '@LPS-122396']},
		async ({apiHelpers, journalPage, page, pageEditorPage, site}) => {

			// Create a page with a Heading fragment

			const headingId = getRandomString();
			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([headingDefinition]),
				siteId: site.id,
				title: getRandomString(),
			});

			// Create basic web content

			const basicWebContentTitle = getRandomString();

			await apiHelpers.jsonWebServicesJournal.addWebContent({
				ddmStructureId: await getBasicWebContentStructureId(apiHelpers),
				groupId: site.id,
				titleMap: {en_US: basicWebContentTitle},
			});

			// Go to edit mode of page and map the editable to te BWC

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			await page.getByText('Heading Example', {exact: true}).waitFor();

			await pageEditorPage.selectEditable(headingId, 'element-text');

			await pageEditorPage.setMappedItem({
				entity: 'Web Content',
				entry: basicWebContentTitle,
				field: 'Title',
			});

			// Check asset info appears in mapping panel

			const typeLabel = page
				.getByLabel('Configuration Panel')
				.locator('.page-editor__mapping-panel__type-label');

			await expect(typeLabel.nth(0)).toContainText(
				'Content Type:Web Content Article'
			);

			await expect(typeLabel.nth(1)).toContainText(
				'Subtype:Basic Web Content'
			);

			// Go to Page Contents panel and edit web content from there

			await pageEditorPage.goToSidebarTab('Page Content');

			const panel = page.getByLabel('Page Content Panel');

			const content = panel.locator(
				'.page-editor__page-contents__page-content'
			);

			await expect(async () => {
				await hoverAndExpectToBeVisible({
					autoClick: true,
					target: content.getByLabel(
						`Actions for ${basicWebContentTitle}`
					),
					trigger: content,
				});

				await page
					.getByRole('menuitem', {
						name: 'Edit',
					})
					.waitFor();

				await page
					.getByRole('menuitem', {
						name: 'Edit',
					})
					.click();

				await expect(
					page.locator('.article-content-content')
				).toBeVisible({
					timeout: 500,
				});
			}).toPass();

			await page.getByLabel('Select a language').waitFor();

			const newTitle = getRandomString();

			await journalPage.articleTitleInput.fill(newTitle);

			await expect(async () => {
				if (
					await page
						.locator('.journal-article-button-row')
						.isVisible()
				) {
					await page
						.locator('.journal-article-button-row')
						.getByRole('button', {name: 'Publish'})
						.click();

					await page
						.getByRole('menuitem', {
							name: 'Publish',
						})
						.click({timeout: 500});
				}

				await expect(page.locator('.page-editor')).toBeVisible({
					timeout: 2000,
				});
			}).toPass();

			// Check new title is displayed in page editor

			await expect(page.getByText(newTitle)).toBeVisible();
		}
	);

	test(
		'Page creator can perform actions on collection displayed in collection display via page content panel',
		{
			tag: ['@LPS-122204', '@LPS-125985'],
		},
		async ({
			apiHelpers,
			collectionsPage,
			journalEditArticlePage,
			page,
			pageEditorPage,
			pageManagementSite,
		}) => {

			// Create definition for a collection mapped to Animals collection

			const animalsClassPK = await collectionsPage.getCollectionClassPK(
				ANIMALS_COLLECTION_NAME,
				pageManagementSite.friendlyUrlPath
			);

			const collectionDefinition = getCollectionDefinition({
				classPK: animalsClassPK,
				id: getRandomString(),
				pageElements: [
					getFragmentDefinition({
						fragmentFields: FRAGMENT_FIELDS,
						id: getRandomString(),
						key: 'BASIC_COMPONENT-heading',
					}),
				],
			});

			// Create a content page and go to edit mode

			const layoutTitle = getRandomString();

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([collectionDefinition]),
				siteId: pageManagementSite.id,
				title: layoutTitle,
			});

			await pageEditorPage.goto(
				layout,
				pageManagementSite.friendlyUrlPath
			);

			// Go to page contents panel and click in edit

			await pageEditorPage.clickPageContentContentAction(
				'Edit',
				ANIMALS_COLLECTION_NAME
			);

			await expect(
				page.getByRole('heading', {name: ANIMALS_COLLECTION_NAME})
			).toBeVisible();

			await page.getByTitle(`Go to ${layoutTitle}`).click();

			// Go to page contents panel and click in view items

			await pageEditorPage.clickPageContentContentAction(
				'View Items',
				ANIMALS_COLLECTION_NAME
			);

			await expect(
				page.getByRole('heading', {name: 'View Items'})
			).toBeVisible();

			await expect(
				page.getByText('Animal 01 - Dogs and Cats categories')
			).toBeVisible();
			await expect(
				page.getByText('Animal 02 - Dogs category')
			).toBeVisible();

			// Assert edit content action

			const viewItemsFrame = page.frameLocator(
				'iframe[title="View Items"]'
			);

			const row = viewItemsFrame.getByRole('row', {
				name: 'Animal 01 - Dogs and Cats categories',
			});

			await clickAndExpectToBeVisible({
				autoClick: false,
				target: viewItemsFrame.getByRole('menuitem', {
					name: 'Edit Content',
				}),
				trigger: row.getByLabel('Show Actions', {exact: true}),
			});

			await page.getByRole('dialog').getByLabel('close').click();

			// Go to page contents panel, click in add items and add a new item

			await pageEditorPage.clickPageContentContentAction(
				'Add Items',
				ANIMALS_COLLECTION_NAME,
				'Animal'
			);

			const articleTitle = 'Animal 03 - Elephant';

			await journalEditArticlePage.fillTitle(articleTitle);
			await journalEditArticlePage.publishArticle();

			await expect(page.getByText(articleTitle)).toBeVisible();

			// Go to page contents panel and click in permissions

			await pageEditorPage.clickPageContentContentAction(
				'Permissions',
				ANIMALS_COLLECTION_NAME
			);

			const permissionsFrame = page.frameLocator(
				'iframe[title="Permissions"]'
			);

			const guestActionViewCheckBox =
				permissionsFrame.locator('#guest_ACTION_VIEW');

			await guestActionViewCheckBox.uncheck({trial: true});

			await guestActionViewCheckBox.uncheck();

			await permissionsFrame
				.getByRole('button', {exact: true, name: 'Save'})
				.click();

			await waitForAlert(permissionsFrame);

			await expect(guestActionViewCheckBox).not.toBeChecked();
		}
	);

	test(
		'Search localized inline text items',
		{
			tag: ['@LPS-106776', '@LPS-122148'],
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page with a Heading fragment

			const headingId = getRandomString();

			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			const paragraphId = getRandomString();

			const paragraphDefinition = getFragmentDefinition({
				id: paragraphId,
				key: 'BASIC_COMPONENT-paragraph',
			});

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([
					headingDefinition,
					paragraphDefinition,
				]),
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Localize fragments

			await pageEditorPage.editTextEditable(
				headingId,
				'element-text',
				'Edited Text'
			);

			await pageEditorPage.editTextEditable(
				paragraphId,
				'element-text',
				'Edited Title'
			);

			// Deselect the editable before changing the translation language

			await page.locator('#banner.page-editor__disabled-area').click();

			await pageEditorPage.switchLanguage('es-ES');

			await pageEditorPage.editTextEditable(
				headingId,
				'element-text',
				'Texto Editado'
			);

			await pageEditorPage.editTextEditable(
				paragraphId,
				'element-text',
				'Título Editado'
			);

			// Search localized values

			await pageEditorPage.goToSidebarTab('Page Content');

			await expect(page.getByTitle('Texto Editado')).toBeVisible();

			await expect(page.getByTitle('Título Editado')).toBeVisible();

			await page.getByPlaceholder('Search...').fill('Texto Editado');

			await expect(page.getByTitle('Texto Editado')).toBeVisible();

			await expect(page.getByTitle('Título Editado')).not.toBeVisible();

			await pageEditorPage.switchLanguage('en-US');

			await expect(page.getByText('No Results Found')).toBeVisible();
		}
	);

	test(
		'Search mapped assets',
		{
			tag: '@LPS-122148',
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page with a Heading fragment

			const headingId = getRandomString();

			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			const layoutTitle = getRandomString();

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([headingDefinition]),
				siteId: site.id,
				title: layoutTitle,
			});

			// Create basic web content

			const basicWebContentTitle = getRandomString();

			await apiHelpers.jsonWebServicesJournal.addWebContent({
				ddmStructureId: await getBasicWebContentStructureId(apiHelpers),
				groupId: site.id,
				titleMap: {en_US: basicWebContentTitle},
			});

			// Go to edit mode of page and map the editable to te BWC

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			await page.getByText('Heading Example', {exact: true}).waitFor();

			await pageEditorPage.selectEditable(headingId, 'element-text');

			await pageEditorPage.setMappedItem({
				entity: 'Web Content',
				entry: basicWebContentTitle,
				field: 'Title',
			});

			// Go to Page Contents panel and assert permissions

			await pageEditorPage.goToSidebarTab('Page Content');

			// Search by keywods

			await expect(page.getByTitle(basicWebContentTitle)).toBeVisible();

			await page.getByPlaceholder('Search...').fill(getRandomString());

			await expect(
				page.getByTitle(basicWebContentTitle)
			).not.toBeVisible();

			await page.getByPlaceholder('Search...').fill(basicWebContentTitle);

			await expect(page.getByTitle(basicWebContentTitle)).toBeVisible();
		}
	);

	test(
		'View collection, mapped content and mapped content via content display in page content panel',
		{
			tag: '@LPS-125985',
		},
		async ({
			apiHelpers,
			collectionsPage,
			page,
			pageEditorPage,
			pageManagementSite,
		}) => {

			// Create definition for a collection mapped to Animals collection

			const animalsClassPK = await collectionsPage.getCollectionClassPK(
				ANIMALS_COLLECTION_NAME,
				pageManagementSite.friendlyUrlPath
			);

			const collectionId = getRandomString();

			const collectionDefinition = getCollectionDefinition({
				classPK: animalsClassPK,
				id: collectionId,
				pageElements: [
					getFragmentDefinition({
						fragmentFields: FRAGMENT_FIELDS,
						id: getRandomString(),
						key: 'BASIC_COMPONENT-heading',
					}),
				],
			});

			const contentDisplayId = getRandomString();

			const contentDisplayDefinition = getFragmentDefinition({
				fragmentConfig: {
					itemSelector: {
						template: {
							infoItemRendererKey:
								'com.liferay.journal.web.internal.info.item.renderer.JournalArticleFullContentInfoItemRenderer',
						},
					},
				},
				id: contentDisplayId,
				key: 'com.liferay.fragment.internal.renderer.ContentObjectFragmentRenderer',
			});

			// Create definition for a heading fragment

			const headingId = getRandomString();

			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			// Create a content page and go to edit mode

			const layoutTitle = getRandomString();

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([
					collectionDefinition,
					contentDisplayDefinition,
					headingDefinition,
				]),
				siteId: pageManagementSite.id,
				title: layoutTitle,
			});

			await pageEditorPage.goto(
				layout,
				pageManagementSite.friendlyUrlPath
			);

			// Map the content display fragment to the created web content

			await pageEditorPage.selectFragment(contentDisplayId);

			await pageEditorPage.setMappedItem({
				entity: 'Web Content',
				entry: 'Animal 01 - Dogs and Cats categories',
				folder: 'Animals',
			});

			// Map the heading fragment to the created web content

			await pageEditorPage.selectEditable(headingId, 'element-text');

			await pageEditorPage.setMappingConfiguration({
				mapping: {
					entity: 'Web Content',
					entry: 'Animal 02 - Dogs category',
					field: 'Title',
					folder: 'Animals',
				},
			});

			// Check collection, mapped content and mapped content via content display are visible in page content panel

			await pageEditorPage.goToSidebarTab('Page Content');

			const panel = page.getByLabel('Page Content Panel', {
				exact: true,
			});

			await expect(
				panel.getByText(ANIMALS_COLLECTION_NAME, {exact: true})
			).toBeVisible();

			await expect(
				panel.getByText('Animal 01 - Dogs and Cats categories', {
					exact: true,
				})
			).toBeVisible();

			await expect(
				panel.getByText('Animal 02 - Dogs category', {exact: true})
			).toBeVisible();

			await expect(
				panel.getByText('There is no content on this page.')
			).not.toBeVisible();

			// Removes collection, content display and heading fragment

			await pageEditorPage.removeFragment(collectionId);

			await pageEditorPage.removeFragment(contentDisplayId);

			await pageEditorPage.removeFragment(headingId);

			// Check collection, mapped content and mapped content via content display are not visible in page content panel

			await pageEditorPage.goToSidebarTab('Page Content');

			await expect(
				panel.getByText(ANIMALS_COLLECTION_NAME, {exact: true})
			).not.toBeVisible();

			await expect(
				panel.getByText('Animal 01 - Dogs and Cats categories', {
					exact: true,
				})
			).not.toBeVisible();

			await expect(
				panel.getByText('Animal 02 - Dogs category', {exact: true})
			).not.toBeVisible();

			await expect(
				panel.getByText('There is no content on this page.')
			).toBeVisible();
		}
	);

	test(
		'View inline text in different experiences',
		{
			tag: ['@LPS-122148'],
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition(),
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Create new experience and check it's the last one and inactive

			await pageEditorPage.createExperience('E1');

			// Edit heading fragment editable value

			await pageEditorPage.addFragment('Basic Components', 'Heading');

			const headingId2 = await pageEditorPage.getFragmentId('Heading');

			await pageEditorPage.editTextEditable(
				headingId2,
				'element-text',
				'E1 Text'
			);

			// Go to content panel and assert that only second heading fragment appears

			await pageEditorPage.goToSidebarTab('Page Content');

			await expect(page.getByTitle('E1 Text')).toBeVisible();

			// Change to default experience

			await pageEditorPage.switchExperience('Default');

			// Edit heading fragment editable value

			await pageEditorPage.addFragment('Basic Components', 'Heading');

			const headingId1 = await pageEditorPage.getFragmentId('Heading');

			await pageEditorPage.editTextEditable(
				headingId1,
				'element-text',
				'Default Text'
			);

			// Go to content panel and assert that only first heading fragment appears

			await pageEditorPage.goToSidebarTab('Page Content');

			await expect(page.getByTitle('Default Text')).toBeVisible();

			await expect(page.getByTitle('E1 Text')).not.toBeVisible();
		}
	);

	test(
		'View permissions and usage of mapped web content in Contents panel',
		{
			tag: '@LPS-96794',
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page with a Heading fragment

			const headingId = getRandomString();

			const headingDefinition = getFragmentDefinition({
				id: headingId,
				key: 'BASIC_COMPONENT-heading',
			});

			const layoutTitle = getRandomString();

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([headingDefinition]),
				siteId: site.id,
				title: layoutTitle,
			});

			// Create basic web content

			const basicWebContentTitle = getRandomString();

			await apiHelpers.jsonWebServicesJournal.addWebContent({
				ddmStructureId: await getBasicWebContentStructureId(apiHelpers),
				groupId: site.id,
				titleMap: {en_US: basicWebContentTitle},
			});

			// Go to edit mode of page and map the editable to te BWC

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			await page.getByText('Heading Example', {exact: true}).waitFor();

			await pageEditorPage.selectEditable(headingId, 'element-text');

			await pageEditorPage.setMappedItem({
				entity: 'Web Content',
				entry: basicWebContentTitle,
				field: 'Title',
			});

			// Go to Page Contents panel and assert permissions

			await pageEditorPage.goToSidebarTab('Page Content');

			// Assert content page editor can edit/view permissions

			const panel = page.getByLabel('Page Content Panel');

			const content = panel.locator(
				'.page-editor__page-contents__page-content'
			);

			await expect(async () => {
				await hoverAndExpectToBeVisible({
					autoClick: true,
					target: content.getByLabel(
						`Actions for ${basicWebContentTitle}`
					),
					trigger: content,
				});

				await page
					.getByRole('menuitem', {
						name: 'Permissions',
					})
					.waitFor();

				await page
					.getByRole('menuitem', {
						name: 'Permissions',
					})
					.click({timeout: 1000});

				await expect(
					page
						.frameLocator('iframe[title="Permissions"]')
						.getByText('Guest')
				).toBeVisible({timeout: 1000});

				await page.getByLabel('close', {exact: true}).click();
			}).toPass();

			// Assert content page editor can view usages

			await expect(async () => {
				await hoverAndExpectToBeVisible({
					autoClick: true,
					target: content.getByLabel(
						`Actions for ${basicWebContentTitle}`
					),
					trigger: content,
				});

				await page
					.getByRole('menuitem', {
						name: 'View Usages',
					})
					.waitFor();

				await page
					.getByRole('menuitem', {
						name: 'View Usages',
					})
					.click();

				const iframe = page.frameLocator('iframe[title="View Usages"]');

				await expect(
					iframe.getByRole('heading', {name: 'All (1)'})
				).toBeVisible();

				await expect(iframe.getByText(layoutTitle)).toBeVisible();
			}).toPass();
		}
	);
});

test.describe('Page Design Options', () => {
	test(
		'Allows setting page design options',
		{
			tag: '@LPS-146373',
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create a page

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				siteId: site.id,
				title: getRandomString(),
			});

			// Go to edit mode of page

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Go to Page Design Options tab

			await pageEditorPage.goToSidebarTab('Page Design Options');

			// Go to look and feel

			await page
				.getByTitle('More Page Design Options', {exact: true})
				.click();

			// Assert sections

			await expect(
				page.getByRole('heading', {name: 'Theme'})
			).toBeAttached();

			await expect(
				page.getByRole('heading', {name: 'Basic Settings'})
			).toBeAttached();

			await expect(
				page.getByRole('heading', {name: 'Customization'})
			).toBeAttached();
		}
	);
});

test.describe('Rules Panel', () => {
	test(
		'Add, edit and delete page rule',
		{
			tag: ['@LPS-196461', '@LPS-196462', '@LPS-200349'],
		},
		async ({apiHelpers, page, pageEditorPage, site}) => {

			// Create content page with a button fragment and go to edit mode

			const buttonId = getRandomString();

			const buttonDefinition = getFragmentDefinition({
				id: buttonId,
				key: 'BASIC_COMPONENT-button',
			});

			const layout = await apiHelpers.headlessDelivery.createSitePage({
				pageDefinition: getPageDefinition([buttonDefinition]),
				siteId: site.id,
				title: getRandomString(),
			});

			await pageEditorPage.goto(layout, site.friendlyUrlPath);

			// Assert info message

			await pageEditorPage.goToSidebarTab('Page Rules');

			await expect(
				page.getByText('Fortunately, it is very easy to add new ones.')
			).toBeVisible();

			// Open new rule modal

			const modal = page.locator('.modal-dialog');

			await clickAndExpectToBeVisible({
				target: modal.getByRole('heading', {name: 'New Rule'}),
				trigger: page.getByRole('button', {name: 'New Rule'}),
			});

			// Create new rule

			const ruleName = getRandomString();

			await modal.getByLabel('Rule Name').fill(ruleName);

			// Condition

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'User'}),
				trigger: page.getByLabel('Select Item for the Condition'),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'Is the User'}),
				trigger: page.getByLabel('Select Condition'),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'test'}),
				trigger: page.getByLabel('Select User'),
			});

			// Action

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'Hide'}),
				trigger: page.getByLabel('Select Action'),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'Fragment'}),
				trigger: page.getByLabel('Select Item for the Action'),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'Button'}),
				trigger: page.getByLabel('Select Fragment'),
			});

			await modal
				.getByRole('button', {exact: true, name: 'Save'})
				.click();

			await waitForAlert(
				page,
				'Success:The rule was created successfully.'
			);

			// Assert rule is created

			await expect(
				page.getByText('IfUserIs the UsertestHideFragmentButton')
			).toBeVisible();

			// Edit rule

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('menuitem', {name: 'Edit'}),
				trigger: page.getByLabel(`View ${ruleName} Options`),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {name: 'Has the Role Of'}),
				trigger: page.getByLabel('Select Condition'),
			});

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('option', {exact: true, name: 'Guest'}),
				trigger: page.getByLabel('Select Role'),
			});

			await modal
				.getByRole('button', {exact: true, name: 'Save'})
				.click();

			await waitForAlert(
				page,
				'Success:The rule was updated successfully.'
			);

			// Assert rule was updated

			await expect(
				page.getByText('IfUserHas the Role OfGuestHideFragmentButton')
			).toBeVisible();

			// Delete rule

			await clickAndExpectToBeVisible({
				autoClick: true,
				target: page.getByRole('menuitem', {name: 'Delete'}),
				trigger: page.getByLabel(`View ${ruleName} Options`),
			});

			// Assert rule was deleted

			await expect(
				page.getByText('Fortunately, it is very easy to add new ones.')
			).toBeVisible();
		}
	);

	test('Checks the accessibility of the rule modal by filling out a condition and an action', async ({
		apiHelpers,
		page,
		pageEditorPage,
		site,
	}) => {

		// Create content page with a Heading fragment and go to edit mode

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([
				getFragmentDefinition({
					id: getRandomString(),
					key: 'BASIC_COMPONENT-heading',
				}),
			]),
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		// Add rule and check accessibility of modal

		await pageEditorPage.goToSidebarTab('Page Rules');

		await page.getByRole('button', {name: 'New Rule'}).click();

		await pageEditorPage.addRuleCondition();

		await pageEditorPage.addRuleAction();

		await checkAccessibility({
			page,
			selectors: ['.modal-body'],
		});
	});
});
