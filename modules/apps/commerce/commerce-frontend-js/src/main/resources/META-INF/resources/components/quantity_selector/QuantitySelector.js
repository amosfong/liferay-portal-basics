/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classnames from 'classnames';
import PropTypes from 'prop-types';
import React, {forwardRef, useRef} from 'react';

import InputQuantitySelector from './InputQuantitySelector';
import ListQuantitySelector from './ListQuantitySelector';

import './quantity_selector.scss';

const QuantitySelector = forwardRef(
	(
		{
			alignment,
			allowEmptyValue,
			allowedQuantities,
			disabled,
			max,
			min,
			name,
			namespace,
			onUpdate,
			quantity,
			size,
			step,
			unitOfMeasure,
			...props
		},
		providedRef
	) => {
		const inputRef = useRef();

		const Selector =
			allowedQuantities?.length > 0
				? ListQuantitySelector
				: InputQuantitySelector;

		return (
			<Selector
				{...props}
				alignment={alignment}
				allowEmptyValue={allowEmptyValue}
				allowedQuantities={allowedQuantities}
				className={classnames({
					[`form-control-${size}`]: size,
					'quantity-selector': true,
				})}
				disabled={disabled}
				max={max}
				min={min}
				name={name}
				namespace={namespace}
				onUpdate={onUpdate}
				quantity={quantity}
				ref={providedRef || inputRef}
				step={step}
				unitOfMeasure={unitOfMeasure}
			/>
		);
	}
);

QuantitySelector.defaultProps = {
	allowEmptyValue: false,
	disabled: false,
};

QuantitySelector.propTypes = {
	alignment: PropTypes.oneOf(['top', 'bottom']),
	allowEmptyValue: PropTypes.bool,
	disabled: PropTypes.bool,
	name: PropTypes.string,
	namespace: PropTypes.string,
	onUpdate: PropTypes.func.isRequired,
	quantity: PropTypes.number,
	size: PropTypes.oneOf(['lg', 'md', 'sm']),
};

export default QuantitySelector;
