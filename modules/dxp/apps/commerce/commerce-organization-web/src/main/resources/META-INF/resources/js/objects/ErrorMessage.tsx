/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

const ErrorMessage = ({error}: TErrorMessageProps) => {
	return (
		<>
			{!!error && (
				<div className="form-feedback-group">
					<div className="form-feedback-item">{error}</div>
				</div>
			)}
		</>
	);
};

type TErrorMessageProps = {
	error: string | null;
};

export default ErrorMessage;
