/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayRadio, ClayRadioGroup} from '@clayui/form';
import {
	ExpressionBuilder,
	SidebarCategory,
} from '@liferay/object-js-components-web';
import classNames from 'classnames';
import React from 'react';

interface ReadOnlyContainerProps {
	disabled?: boolean;
	modelBuilder?: boolean;
	onSubmit?: (values?: Partial<ObjectField>) => void;
	readOnlySidebarElements: SidebarCategory[];
	requiredField: boolean;
	setValues: (value: Partial<ObjectField>) => void;
	values: Partial<ObjectField>;
}

export function ReadOnlyContainer({
	disabled,
	modelBuilder = false,
	onSubmit,
	readOnlySidebarElements,
	requiredField,
	setValues,
	values,
}: ReadOnlyContainerProps) {
	const setReadOnly = (value: ReadOnlyFieldValue) => {
		setValues({
			readOnly: value,
			required:
				value === 'true' || value === 'conditional'
					? false
					: requiredField,
		});
	};

	return (
		<div
			className={classNames({
				'lfr-objects__edit-object-field-card-content': !modelBuilder,
				'lfr-objects__edit-object-field-model-builder-panel':
					modelBuilder,
			})}
		>
			{values.readOnly && (
				<>
					<ClayRadioGroup
						defaultValue={values?.readOnly}
						onBlur={(event) => {
							event.stopPropagation();

							if (onSubmit) {
								onSubmit();
							}
						}}
					>
						<ClayRadio
							disabled={disabled}
							label={Liferay.Language.get('true')}
							onClick={() => setReadOnly('true')}
							value="true"
						/>

						<ClayRadio
							disabled={disabled}
							label={Liferay.Language.get('false')}
							onClick={() => setReadOnly('false')}
							value="false"
						/>

						<ClayRadio
							disabled={disabled}
							label={Liferay.Language.get('conditional')}
							onClick={() => setReadOnly('conditional')}
							value="conditional"
						/>
					</ClayRadioGroup>

					{values.readOnly === 'conditional' && (
						<ExpressionBuilder
							feedbackMessage={Liferay.Language.get(
								'use-expressions-to-create-a-condition'
							)}
							label={Liferay.Language.get('expression-builder')}
							onBlur={(event) => {
								event.stopPropagation();

								if (onSubmit) {
									onSubmit();
								}
							}}
							onChange={({target: {value}}) => {
								setValues({
									readOnlyConditionExpression: value,
								});
							}}
							onOpenModal={() => {
								const parentWindow = Liferay.Util.getOpener();

								parentWindow.Liferay.fire(
									'openExpressionBuilderModal',
									{
										eventSidebarElements:
											readOnlySidebarElements,
										header: Liferay.Language.get(
											'expression-builder'
										),
										onSave: (script: string) => {
											setValues({
												readOnlyConditionExpression:
													script,
											});

											if (onSubmit) {
												onSubmit({
													...values,
													readOnlyConditionExpression:
														script,
												});
											}
										},
										placeholder: `<#-- ${Liferay.Language.get(
											'create-the-condition-of-the-read-only-state-using-expression-builder'
										)} -->`,
										required: false,
										source:
											values.readOnlyConditionExpression ??
											'',
										validateExpressionURL: '',
									}
								);
							}}
							placeholder={Liferay.Language.get(
								'create-an-expression'
							)}
							value={values.readOnlyConditionExpression ?? ''}
						/>
					)}
				</>
			)}
		</div>
	);
}
