/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import BreadcrumbsItem from './BreadcrumbsItem';

const Breadcrumbs = ({entries}) => (
	<ol className="breadcrumb">
		{entries.map((entry, index) => (
			<BreadcrumbsItem
				active={index === entries.length - 1}
				key={`${entry.title}-${entry.url || ''}`}
				title={entry.title}
				url={entry.url}
			/>
		))}
	</ol>
);

export default Breadcrumbs;
