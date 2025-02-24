import DateRangeInput from '../DateRangeInput';
import getCN from 'classnames';
import HelpBlock from './HelpBlock';
import Label from './Label';
import React from 'react';
import {FieldProps} from 'formik';
import {isNumber} from 'lodash';

interface IFormDateRangeInputProps
	extends FieldProps,
		React.HTMLAttributes<HTMLElement> {
	inline: boolean;
	label: string;
	overlayAlignment?: string;
	popover: {
		content: React.ReactNode;
		title: React.ReactNode;
	};
	required?: boolean;
	usePortal?: boolean;
	width: number;
}

const FormDateRangeInput: React.FC<IFormDateRangeInputProps> = ({
	className,
	field,
	form,
	inline = false,
	label,
	overlayAlignment = 'bottomLeft',
	popover,
	required = false,
	usePortal = true,
	width
}) => {
	const {name, value} = field;

	const handleChange = (value): void => {
		const {setFieldValue} = form;

		setFieldValue(name, value);
	};

	const error = form.errors[name];
	const touched = form.touched[name];

	const classes = getCN('form-date-input-root', className, {
		'form-inline-group': inline,
		'has-error': error && touched,
		'has-success': !error && touched
	});

	const style = isNumber(width)
		? {flexBasis: `${width}%`, flexGrow: 0}
		: undefined;

	return (
		<div className={classes} style={style}>
			{label && (
				<Label htmlFor={name} popover={popover} required={required}>
					{label}
				</Label>
			)}

			<DateRangeInput
				id={name}
				name={name}
				onChange={handleChange}
				overlayAlignment={overlayAlignment}
				showRetentionPeriod={false}
				usePortal={usePortal}
				value={value}
			/>

			<HelpBlock name={name} />
		</div>
	);
};

export default FormDateRangeInput;
