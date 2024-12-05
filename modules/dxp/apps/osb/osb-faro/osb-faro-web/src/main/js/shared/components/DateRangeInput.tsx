import ClayButton from '@clayui/button';
import ClayDropDown, {Align} from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import DatePicker from './date-picker';
import getCN from 'classnames';
import Input from './Input';
import moment from 'moment';
import React, {useState} from 'react';
import {DatePickerRetentionPeriodHeader} from './DatePickerRetentionPeriodHeader';
import {DEFAULT_DATE_FORMAT} from 'shared/util/date';
import {formatDateWithTimezone} from './dropdown-range-key/utils';
import {isNil, noop} from 'lodash';
import {sub} from 'shared/util/lang';
import {useRetentionPeriod} from 'shared/hooks/useRetentionPeriod';
import {useTimeZone} from 'shared/hooks/useTimeZone';

const convertToMoment = (value: string, format): moment.Moment => {
	const date = moment(value, format);

	return date.isValid() ? date : null;
};

export type DateRange = {
	end: string;
	start: string;
};

export type MomentDateRange = {
	end: moment.Moment;
	start: moment.Moment;
};

interface IDateInputProps {
	className?: string;
	displayFormat?: string;
	format?: string;
	groupId?: string;
	id?: string;
	name?: string;
	onBlur?: (event?: FocusEvent) => void;
	onChange: (range: DateRange) => void;
	overlayAlignment?: string;
	showRetentionPeriod?: boolean;
	usePortal?: boolean;
	value: DateRange;
}

const DateInput: React.FC<IDateInputProps> = ({
	className,
	displayFormat,
	format = DEFAULT_DATE_FORMAT,
	groupId,
	onBlur = noop,
	onChange = noop,
	showRetentionPeriod = true,
	value
}) => {
	const [active, setActive] = useState(false);

	const {timeZoneId} = useTimeZone(groupId);
	const retentionPeriod = useRetentionPeriod();

	const convertMomentToDisplayFormat = (value: moment.Moment): string =>
		isNil(value) ? null : value.format(displayFormat || format);

	const handleDateSelect = ({end, start}: MomentDateRange) => {
		onChange({
			end: convertMomentToDisplayFormat(end),
			start: convertMomentToDisplayFormat(start)
		});
	};

	const getDateRangeDisplay = ({end, start}: MomentDateRange): string => {
		if (end || start) {
			return sub(Liferay.Language.get('x-to-x'), [
				convertMomentToDisplayFormat(start),
				convertMomentToDisplayFormat(end)
			]) as string;
		}

		return '';
	};

	const momentDateRange = {
		end: convertToMoment(value.end, format),
		start: convertToMoment(value.start, format)
	};

	const minDate = formatDateWithTimezone(timeZoneId).clone();

	return (
		<ClayDropDown
			active={active}
			alignmentPosition={Align.TopLeft}
			className={getCN(className, 'dropdown-range-key-root')}
			menuElementAttrs={{
				className: getCN('dropdown-range-key-menu-root', {
					'show-date-picker': active
				}),
				style: {
					zIndex: 1060
				}
			}}
			onActiveChange={active => {
				setActive(active);

				!active && onBlur();
			}}
			trigger={
				<div>
					<Input.Group>
						<Input.GroupItem>
							<Input
								autoComplete='off'
								data-testid='date-range-input'
								inset='after'
								onClick={() => setActive(true)}
								placeholder={sub(
									Liferay.Language.get('x-to-x'),
									[
										Liferay.Language.get('yyyy-mm-dd'),
										Liferay.Language.get('yyyy-mm-dd')
									]
								)}
								readOnly
								value={getDateRangeDisplay(momentDateRange)}
							/>

							<Input.Inset position='after'>
								<ClayButton
									aria-label={Liferay.Language.get(
										'choose-date-range'
									)}
									className='button-root'
									displayType='unstyled'
									onClick={() => setActive(true)}
								>
									<ClayIcon
										className='icon-root'
										symbol='calendar'
									/>
								</ClayButton>
							</Input.Inset>
						</Input.GroupItem>
					</Input.Group>
				</div>
			}
		>
			<DatePicker
				date={momentDateRange}
				header={
					showRetentionPeriod ? (
						<DatePickerRetentionPeriodHeader
							retentionPeriod={retentionPeriod}
						/>
					) : null
				}
				maxDate={formatDateWithTimezone(timeZoneId)
					.clone()
					.subtract(1, 'days')}
				maxRange={365}
				minDate={
					showRetentionPeriod
						? minDate.subtract(retentionPeriod, 'months')
						: minDate.subtract(100, 'years')
				}
				onSelect={handleDateSelect}
				timeZoneId={timeZoneId}
			/>
		</ClayDropDown>
	);
};

export default DateInput;
