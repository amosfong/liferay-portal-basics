/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Liferay} from '../..';
import {LiferayAPIs} from '../../common/enums/apis';
import liferayFetcher from '../../common/utils/fetcher';
import {ResourceName} from '../enum/resourceName';

export default async function patchObjectEntry<T>(
	apiOption: ResourceName,
	id: number | string,
	body: Partial<T>
) {
	return await liferayFetcher.patch(
		`/o/${LiferayAPIs.OBJECT}/${apiOption}/${id}`,
		Liferay.authToken,
		body
	);
}
