/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default async function getGlobalSiteId(apiHelpers) {
	const company =
		await apiHelpers.jsonWebServicesCompany.getCompanyByWebId(
			'liferay.com'
		);

	const globalGroup = await apiHelpers.jsonWebServicesGroup.getCompanyGroup(
		company.companyId
	);

	return globalGroup.groupId;
}
