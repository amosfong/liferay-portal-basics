/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import React from 'react';

export function WarningMessage({
	fontWeight = 'bold',
	message,
	title,
}: {
	fontWeight?: 'bold' | 'normal';
	message: string;
	title?: string;
}) {
	return (
		<div
			className={`font-weight-${fontWeight} mt-1 small text-warning`}
			role="status"
		>
			<span className="mr-2">
				<ClayIcon symbol="warning-full" />
			</span>

			{title ? (
				<>
					<p className="text-weight-semi-bold">{title}</p>
					<p>{message}</p>
				</>
			) : (
				<span>{message}</span>
			)}
		</div>
	);
}
