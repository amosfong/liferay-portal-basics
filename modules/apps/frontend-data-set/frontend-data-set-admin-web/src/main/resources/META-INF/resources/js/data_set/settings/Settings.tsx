/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {Option, Picker, Text} from '@clayui/core';
import DropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLayout from '@clayui/layout';
import ClayLink from '@clayui/link';
import {ClayTooltipProvider} from '@clayui/tooltip';
import {fetch, navigate} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import {
	API_URL,
	DEFAULT_FETCH_HEADERS,
	DEFAULT_VISUALIZATION_MODES,
	OBJECT_RELATIONSHIP,
} from '../../utils/constants';
import openDefaultFailureToast from '../../utils/openDefaultFailureToast';
import openDefaultSuccessToast from '../../utils/openDefaultSuccessToast';
import {TVisualizationMode} from '../../utils/types';
import {IDataSetSectionProps} from '../DataSet';

const NOT_CONFIGURED_VISUALIZATION_MODE = {
	label: Liferay.Language.get('go-to-visualization-modes'),
	thumbnail: 'plus',
	type: Liferay.Language.get('not-configured'),
};

const Settings = ({
	backURL,
	dataSet,
	onActiveSectionChange,
	onDataSetUpdate,
	spritemap,
}: IDataSetSectionProps) => {
	const [defaultVisualizationMode, setDefaultVisualizationMode] = useState<
		string | undefined
	>(NOT_CONFIGURED_VISUALIZATION_MODE.type);
	const [loading, setLoading] = useState(true);
	const [visualizationModes, setVisualizationModes] = useState<
		Array<TVisualizationMode>
	>([]);

	const getActiveVisualizationModes = async () => {
		const fields = [
			OBJECT_RELATIONSHIP.DATA_SET_CARDS_SECTIONS,
			OBJECT_RELATIONSHIP.DATA_SET_LIST_SECTIONS,
			OBJECT_RELATIONSHIP.DATA_SET_TABLE_SECTIONS,
		].join(',');

		const response = await fetch(
			`${API_URL.DATA_SETS}/by-external-reference-code/${dataSet.externalReferenceCode}?fields=${fields}&nestedFields=${fields}`,
			{
				headers: DEFAULT_FETCH_HEADERS,
			}
		);

		if (!response.ok) {
			openDefaultFailureToast();

			setVisualizationModes([]);

			setLoading(false);

			return;
		}

		const responseJSON = await response.json();

		const {
			[OBJECT_RELATIONSHIP.DATA_SET_CARDS_SECTIONS]: cards,
			[OBJECT_RELATIONSHIP.DATA_SET_LIST_SECTIONS]: list,
			[OBJECT_RELATIONSHIP.DATA_SET_TABLE_SECTIONS]: table,
		} = responseJSON;

		const activeViews: Array<TVisualizationMode> = [];

		(DEFAULT_VISUALIZATION_MODES as Array<TVisualizationMode>).forEach(
			(view) => {
				if (view.mode === 'cards' && cards && cards.length) {
					activeViews.push(view);
				}
				if (view.mode === 'list' && list && list.length) {
					activeViews.push(view);
				}
				if (view.mode === 'table' && table && table.length) {
					activeViews.push(view);
				}
			}
		);

		setVisualizationModes(activeViews);

		setDefaultVisualizationMode(() => {
			if (
				activeViews.find(
					(view: TVisualizationMode) =>
						view.mode === dataSet.defaultVisualizationMode
				)
			) {
				return dataSet.defaultVisualizationMode;
			}
			else {
				return activeViews.length
					? activeViews[0].mode
					: NOT_CONFIGURED_VISUALIZATION_MODE.type;
			}
		});

		setLoading(false);
	};

	const updateFDSViewSettings = async () => {
		const body = {
			defaultVisualizationMode,
		};

		const response = await fetch(
			`${API_URL.DATA_SETS}/by-external-reference-code/${dataSet.externalReferenceCode}`,
			{
				body: JSON.stringify(body),
				headers: DEFAULT_FETCH_HEADERS,
				method: 'PATCH',
			}
		);

		if (!response.ok) {
			openDefaultFailureToast();

			return;
		}

		const responseJSON = await response.json();

		if (responseJSON?.id) {
			openDefaultSuccessToast();

			onDataSetUpdate(responseJSON);
		}
		else {
			openDefaultFailureToast();
		}
	};

	useEffect(() => {
		getActiveVisualizationModes();

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	return (
		<ClayLayout.Sheet className="mt-3" size="lg">
			<ClayLayout.SheetHeader className="mb-4">
				<h2 className="sheet-title">
					{Liferay.Language.get('settings')}
				</h2>
			</ClayLayout.SheetHeader>

			<ClayLayout.SheetSection>
				<h3 className="sheet-subtitle">
					{Liferay.Language.get('fragment-defaults')}
				</h3>

				<ClayLayout.Row className="align-items-center justify-content-between">
					<ClayLayout.Col size={8}>
						<div>
							<label htmlFor="view-mode-picker" id="view-mode">
								{Liferay.Language.get(
									'default-visualization-mode'
								)}
							</label>

							<ClayTooltipProvider>
								<span
									className="ml-1 text-secondary"
									data-tooltip-align="top"
									title={Liferay.Language.get(
										'default-visualization-mode-tooltip'
									)}
								>
									<ClayIcon
										spritemap={spritemap}
										symbol="question-circle-full"
									/>
								</span>
							</ClayTooltipProvider>
						</div>

						<div>
							{Liferay.Language.get(
								'default-visualization-mode-help'
							)}
						</div>
					</ClayLayout.Col>

					<ClayLayout.Col size={4}>
						{!loading && (
							<Picker
								aria-labelledby="view-mode"
								className="mb-2"
								disabled={!visualizationModes.length}
								id="view-mode-picker"
								items={visualizationModes}
								onSelectionChange={(option: React.Key) => {
									if (
										option !==
										NOT_CONFIGURED_VISUALIZATION_MODE.type
									) {
										setDefaultVisualizationMode(
											option as string
										);
									}
								}}
								placeholder={
									NOT_CONFIGURED_VISUALIZATION_MODE.type
								}
								selectedKey={defaultVisualizationMode}
							>
								{visualizationModes.length ? (
									({label, mode, thumbnail}) => (
										<Option key={mode} textValue={label}>
											<ClayIcon
												className="mr-3"
												symbol={thumbnail}
											/>

											{label}
										</Option>
									)
								) : (
									<DropDown.Group
										header={Liferay.Language.get(
											'not-configured'
										)}
									>
										<Option
											key={
												NOT_CONFIGURED_VISUALIZATION_MODE.type
											}
											textValue={
												NOT_CONFIGURED_VISUALIZATION_MODE.type
											}
										>
											<ClayLayout.Row>
												<ClayLayout.Col>
													<Text size={3}>
														{
															NOT_CONFIGURED_VISUALIZATION_MODE.label
														}
													</Text>
												</ClayLayout.Col>
											</ClayLayout.Row>
										</Option>
									</DropDown.Group>
								)}
							</Picker>
						)}

						{!loading && !visualizationModes.length && (
							<ClayLink
								borderless
								onClick={() => onActiveSectionChange(1)}
								onKeyPress={() => onActiveSectionChange(1)}
								tabIndex={0}
								weight="semi-bold"
							>
								<span className="inline-item inline-item-before">
									<ClayIcon
										spritemap={spritemap}
										symbol="shortcut"
									/>
								</span>

								{Liferay.Language.get(
									'go-to-visualization-modes'
								)}
							</ClayLink>
						)}
					</ClayLayout.Col>
				</ClayLayout.Row>
			</ClayLayout.SheetSection>

			<ClayLayout.SheetFooter>
				<ClayButton.Group spaced>
					<ClayButton onClick={updateFDSViewSettings}>
						{Liferay.Language.get('save')}
					</ClayButton>

					<ClayButton
						displayType="secondary"
						onClick={() => navigate(backURL)}
					>
						{Liferay.Language.get('cancel')}
					</ClayButton>
				</ClayButton.Group>
			</ClayLayout.SheetFooter>
		</ClayLayout.Sheet>
	);
};

export default Settings;
