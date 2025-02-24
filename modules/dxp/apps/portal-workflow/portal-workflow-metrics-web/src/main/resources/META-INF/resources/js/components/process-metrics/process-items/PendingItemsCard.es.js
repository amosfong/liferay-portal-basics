/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import ProcessItemsCard from './ProcessItemsCard.es';

const PendingItemsCard = ({processId}) => {
	return (
		<ProcessItemsCard
			description={Liferay.Language.get('pending-items-description')}
			processId={processId}
			title={Liferay.Language.get('pending-items')}
		/>
	);
};

export default PendingItemsCard;
