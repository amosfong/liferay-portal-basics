/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Summary} from 'commerce-frontend-js';

export default function main({commerceOrderId, placedOrderItems, portletId}) {
	Summary('summary', 'summary-root', {
		apiUrl: `/o/headless-commerce-admin-order/v1.0/orders/${commerceOrderId}`,
		dataSetDisplayId: placedOrderItems,
		portletId,
	});
}