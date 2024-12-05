/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../fixtures/featureFlagsTest';
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {pageEditorPagesTest} from '../../fixtures/pageEditorPagesTest';
import getRandomString from '../../utils/getRandomString';
import {blogsPagesTest} from './fixtures/blogsPagesTest';
import {blogsCategorizedFriendlyUrlSetup} from './utils/blogsCategorizedFriendlyUrlSetup';

const PREFIX_FRIENDLY_URL = '/-/blogs/';

const test = mergeTests(
	apiHelpersTest,
	isolatedSiteTest,
	blogsPagesTest,
	pageEditorPagesTest,
	loginTest(),
	featureFlagsTest({
		'LPD-11147': true,
		'LPD-39304': true,
		'LPS-178052': true,
	})
);

test(
	'Permission sets are differing depending on autosaving of a blog entry',
	{
		tag: '@LPD-22497',
	},
	async ({blogsEditBlogEntryPage, blogsPage, page, site}) => {
		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			publish: false,
			title,
		});

		await expect(
			page.locator(
				'#_com_liferay_blogs_web_portlet_BlogsAdminPortlet_saveStatus'
			)
		).toContainText('Draft Saved at', {
			timeout: 40000,
		});

		await blogsPage.goto(site.friendlyUrlPath);

		await blogsPage.assertBlogEntryPermissions(
			[
				{enabled: true, locator: '#guest_ACTION_ADD_DISCUSSION'},
				{enabled: true, locator: '#guest_ACTION_VIEW'},
				{enabled: true, locator: '#site-member_ACTION_ADD_DISCUSSION'},
				{enabled: true, locator: '#site-member_ACTION_VIEW'},
			],
			title
		);
	}
);

test(
	'Select categories for the custom friendly URL',
	{
		tag: '@LPD-26752',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'category-1'},
			{name: 'category-2'},
			{name: 'category-3'},
		];

		await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: false,
			title,
		});

		await test.step('Check input addon categories preview', async () => {
			await expect(
				page.getByText(
					`${PREFIX_FRIENDLY_URL}${friendlyUrlCategories
						.map(({name}) => name)
						.join('/')}/`
				)
			).toBeVisible();
		});

		await test.step('Check categories in friendly URL', async () => {
			await blogsEditBlogEntryPage.publishBlogEntry();

			const response = await page.goto(
				`/web${site.friendlyUrlPath}/b/${title}`
			);

			await expect(response.url()).toContain(
				`/web${site.friendlyUrlPath}/b/${friendlyUrlCategories
					.map(({name}) => name)
					.join('/')}/${title}`
			);
		});
	}
);

test(
	'Categories with blank spaces in friendly URL',
	{
		tag: '@LPD-24858',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'category 1'},
			{name: 'category 2'},
			{name: 'category 3'},
		];

		await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: true,
			title,
		});

		const response = await page.goto(
			`/web${site.friendlyUrlPath}/b/${title}`
		);

		await expect(response.url()).toContain(
			`/web${site.friendlyUrlPath}/b/${friendlyUrlCategories
				.map(({name}) => encodeURIComponent(name))
				.join('/')}/${title}`
		);
	}
);

test(
	'The URL changes when a category is modified',
	{
		tag: '@LPD-26753',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'category-1'},
			{name: 'category-2'},
			{name: 'category-3'},
		];

		const {categories} = await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: true,
			title,
		});

		const initialResponse = await page.goto(
			`/web${site.friendlyUrlPath}/b/${title}`
		);

		await test.step('Check categories in friendly URL', async () => {
			await expect(initialResponse.url()).toContain(
				`/web${site.friendlyUrlPath}/b/${friendlyUrlCategories
					.map(({name}) => name)
					.join('/')}/${title}`
			);
		});

		await test.step('Check redirection and edited category in the initial friendly URL', async () => {
			friendlyUrlCategories[0].name = `${friendlyUrlCategories[0].name}-edited`;
			await apiHelpers.headlessAdminTaxonomy.patchTaxonomyCategory({
				id: categories[0].id,
				name: friendlyUrlCategories[0].name,
			});

			const editedResponse = await page.goto(initialResponse.url());
			await expect(editedResponse.url()).toContain(
				`/web${site.friendlyUrlPath}/b/${friendlyUrlCategories
					.map(({name}) => name)
					.join('/')}/${title}`
			);
		});
	}
);

test(
	'The URL redirects to the correct language when a category has a translation',
	{
		tag: '@LPS-26755',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'lifestyle', name_i18n: {['ES-es']: 'estilo-de-vida'}},
			{name: 'fashion', name_i18n: {['ES-es']: 'moda'}},
			{name: 'places', name_i18n: {['ES-es']: 'lugares'}},
		];

		await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: true,
			title,
		});

		const response = await page.goto(
			`/es/web${site.friendlyUrlPath}/b/${title}`
		);
		await expect(response.url()).toContain(
			`/es/web${site.friendlyUrlPath}/b/${friendlyUrlCategories
				.map((category) => category.name_i18n['ES-es'])
				.join('/')}/${title}`
		);

		// change back to english language

		await page.goto('/en');
	}
);

test(
	'Reorder categories in the friendly URL',
	{
		tag: '@LPD-26659',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'category-1'},
			{name: 'category-2'},
			{name: 'category-3'},
		];

		await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: false,
			title,
		});

		await page.getByLabel('Current').selectOption('category-1');
		await page.getByLabel('Reorder Down').click();
		await page.getByLabel('Reorder Down').click();

		const expectedCategoriesPartialURL = 'category-2/category-3/category-1';

		await test.step('Check categories order in the input addon categories preview', async () => {
			await expect(
				page.getByText(
					`${PREFIX_FRIENDLY_URL}${expectedCategoriesPartialURL}`
				)
			).toBeVisible();
		});

		await test.step('Check categories order in friendly URL', async () => {
			await blogsEditBlogEntryPage.publishBlogEntry();

			const response = await page.goto(
				`/web${site.friendlyUrlPath}/b/${title}`
			);

			await expect(response.url()).toContain(
				`/web${site.friendlyUrlPath}/b/${expectedCategoriesPartialURL}/${title}`
			);
		});
	}
);

test(
	'Only blogs entry categories in the friendly URL',
	{
		tag: '@LPD-26659',
	},
	async ({
		apiHelpers,
		blogsEditBlogEntryPage,
		displayPageTemplatesPage,
		page,
		pageEditorPage,
		site,
	}) => {
		const vocabularyName = getRandomString();
		const friendlyUrlCategories = [
			{name: 'category-1'},
			{name: 'category-2'},
			{name: 'category-3'},
		];

		await blogsCategorizedFriendlyUrlSetup({
			apiHelpers,
			displayPageTemplatesPage,
			friendlyUrlCategories,
			page,
			pageEditorPage,
			site,
			vocabularyName,
		});

		await blogsEditBlogEntryPage.goto(site.friendlyUrlPath);

		const title = getRandomString();

		await blogsEditBlogEntryPage.editBlogEntry({
			content: getRandomString(),
			friendlyUrl: {
				categories: friendlyUrlCategories,
				vocabularyName,
			},
			publish: false,
			title,
		});

		await page.getByLabel('Remove category-1').click();
		await page.getByLabel('Current').selectOption('category-2');
		await page.getByLabel('Reorder Down').click();

		const expectedCategoriesPartialURL = 'category-3/category-2';

		await test.step('Check categories in the input addon categories preview', async () => {
			await expect(
				page.getByText(
					`${PREFIX_FRIENDLY_URL}${expectedCategoriesPartialURL}`
				)
			).toBeVisible();
		});

		await test.step('Check categories in friendly URL', async () => {
			await blogsEditBlogEntryPage.publishBlogEntry();

			const response = await page.goto(
				`/web${site.friendlyUrlPath}/b/${title}`
			);

			await expect(response.url()).toContain(
				`/web${site.friendlyUrlPath}/b/${expectedCategoriesPartialURL}/${title}`
			);
		});
	}
);
