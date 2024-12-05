/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {SiteConfigurationDetailsPage} from '../pages/site-admin-web/SiteConfigurationDetailsPage';
import {SiteSettingsPage} from '../pages/site-admin-web/SiteSettingsPage';
import {ExportUserDataPage} from '../pages/user-associated-data-web/ExportUserDataPage';
import {PersonalDataErasurePage} from '../pages/user-associated-data-web/PersonalDataErasurePage';
import {AssignUsersPage} from '../pages/users-admin-web/AssignUsersPage';
import {EditOrganizationPage} from '../pages/users-admin-web/EditOrganizationPage';
import {EditUserPage} from '../pages/users-admin-web/EditUserPage';
import {OrganizationUsersPage} from '../pages/users-admin-web/OrganizationUsersPage';
import {ServiceAccountsPage} from '../pages/users-admin-web/ServiceAccountsPage';
import {TeamsPage} from '../pages/users-admin-web/TeamsPage';
import {UserPersonalSitePage} from '../pages/users-admin-web/UserPersonalSitePage';
import {UsersAndOrganizationsPage} from '../pages/users-admin-web/UsersAndOrganizationsPage';

const usersAndOrganizationsPagesTest = test.extend<{
	assignUsersPage: AssignUsersPage;
	editOrganizationPage: EditOrganizationPage;
	editUserPage: EditUserPage;
	exportUserDataPage: ExportUserDataPage;
	organizationUsersPage: OrganizationUsersPage;
	personalDataErasurePage: PersonalDataErasurePage;
	serviceAccountsPage: ServiceAccountsPage;
	siteConfigurationDetailsPage: SiteConfigurationDetailsPage;
	siteSettingsPage: SiteSettingsPage;
	teamsPage: TeamsPage;
	userPersonalSitePage: UserPersonalSitePage;
	usersAndOrganizationsPage: UsersAndOrganizationsPage;
}>({
	assignUsersPage: async ({page}, use) => {
		await use(new AssignUsersPage(page));
	},
	editOrganizationPage: async ({page}, use) => {
		await use(new EditOrganizationPage(page));
	},
	editUserPage: async ({page}, use) => {
		await use(new EditUserPage(page));
	},
	exportUserDataPage: async ({page}, use) => {
		await use(new ExportUserDataPage(page));
	},
	organizationUsersPage: async ({page}, use) => {
		await use(new OrganizationUsersPage(page));
	},
	personalDataErasurePage: async ({page}, use) => {
		await use(new PersonalDataErasurePage(page));
	},
	serviceAccountsPage: async ({page}, use) => {
		await use(new ServiceAccountsPage(page));
	},
	siteConfigurationDetailsPage: async ({page}, use) => {
		await use(new SiteConfigurationDetailsPage(page));
	},
	siteSettingsPage: async ({page}, use) => {
		await use(new SiteSettingsPage(page));
	},
	teamsPage: async ({page}, use) => {
		await use(new TeamsPage(page));
	},
	userPersonalSitePage: async ({page}, use) => {
		await use(new UserPersonalSitePage(page));
	},
	usersAndOrganizationsPage: async ({page}, use) => {
		await use(new UsersAndOrganizationsPage(page));
	},
});

export {usersAndOrganizationsPagesTest};
