/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import React from 'react';

const FieldLabel = ({htmlFor, required, text, ...otherProps}) => (
	<label htmlFor={htmlFor} {...otherProps}>
		{text + ' '}

		{required && (
			<span className="reference-mark">
				<ClayIcon symbol="asterisk" />
			</span>
		)}
	</label>
);

export default FieldLabel;