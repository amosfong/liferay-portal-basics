/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayInput} from '@clayui/form';
import React from 'react';

const InputComponent = ({
	dir,
	displayStyle,
	fieldName,
	inputValue,
	label,
	name,
	onFieldBlurred,
	onFieldChanged,
	onFieldFocused,
	placeholder,
	readOnly,
}) => {
	if (displayStyle === 'multiline') {
		return (
			<ClayInput.GroupItem>
				<textarea
					aria-label={label}
					className="ddm-field-text form-control"
					dir={dir}
					disabled={readOnly}
					id={`${name}inputValue`}
					onBlur={onFieldBlurred}
					onChange={onFieldChanged}
					onFocus={onFieldFocused}
					placeholder={placeholder}
					type="text"
					value={inputValue}
				>
					{inputValue}
				</textarea>
			</ClayInput.GroupItem>
		);
	}

	return (
		<ClayInput.GroupItem append>
			<input
				{...(fieldName === 'submitLabel' && {maxLength: 25})}
				className="ddm-field-text form-control"
				data-testid="visibleChangeInput"
				dir={dir}
				disabled={readOnly}
				id={`${name}inputValue`}
				onBlur={onFieldBlurred}
				onChange={onFieldChanged}
				onFocus={onFieldFocused}
				placeholder={placeholder}
				type="text"
				value={inputValue}
			/>
		</ClayInput.GroupItem>
	);
};

export default InputComponent;
