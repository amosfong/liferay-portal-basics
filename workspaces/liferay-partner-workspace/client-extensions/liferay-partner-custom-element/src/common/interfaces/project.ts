/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import LiferayObject from './liferayObject';

export default interface Project extends Partial<LiferayObject> {
	additionalInformationAboutTheOpportunity?: string;
	leadExternalReferenceCode?: string;
	projectCategories?: string;
	projectNeed?: string;
	projectTimeline?: string;
}
