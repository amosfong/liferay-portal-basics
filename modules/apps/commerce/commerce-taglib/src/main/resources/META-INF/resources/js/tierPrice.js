/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {TierPrice} from 'commerce-frontend-js';

export default function ({
	accountId,
	channelId,
	cpInstanceId,
	namespace,
	productId,
	unitOfMeasureTierPriceId,
}) {
	const props = {
		accountId,
		channelId,
		cpInstanceId,
		namespace,
		productId,
	};

	TierPrice(unitOfMeasureTierPriceId, unitOfMeasureTierPriceId, props);
}
