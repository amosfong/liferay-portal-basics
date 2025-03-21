/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayForm from '@clayui/form';
import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import React, {ReactNode} from 'react';

interface Props {
	children: ReactNode;
	error?: string;
	id: string;
	name: string;
	required?: boolean;
}

const FormField = ({children, error, id, name, required = false}: Props) => {
	const hasError = Boolean(error);

	return (
		<ClayForm.Group className={classNames({'has-error': hasError})}>
			<label htmlFor={id}>
				{name}

				{required ? (
					<ClayIcon className="reference-mark" symbol="asterisk" />
				) : null}
			</label>

			{children}

			{hasError && (
				<ClayForm.FeedbackGroup role="alert">
					<ClayForm.FeedbackItem>{error}</ClayForm.FeedbackItem>
				</ClayForm.FeedbackGroup>
			)}
		</ClayForm.Group>
	);
};

export default FormField;
