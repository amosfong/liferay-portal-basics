/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import React from 'react';

import {DropDownItems} from '../types';

import './ObjectDefinitionNodeHeader.scss';

interface ObjectDefinitionNodeHeaderProps {
	dbTableName: string | undefined;
	dropDownItems: DropDownItems[];
	handleSelectObjectDefinitionNode: () => void;
	isLinkedObjectDefinition: boolean;
	objectDefinitionLabel: string;
	status: {
		code: number;
		label: string;
		label_i18n: string;
	};
	system: boolean;
}

export default function ObjectDefinitionNodeHeader({
	dbTableName,
	dropDownItems,
	handleSelectObjectDefinitionNode,
	isLinkedObjectDefinition,
	objectDefinitionLabel,
	status,
	system,
}: ObjectDefinitionNodeHeaderProps) {
	return (
		<>
			<div
				className="lfr-objects__model-builder-node-header-container"
				onClick={(event) => {
					event.stopPropagation();

					handleSelectObjectDefinitionNode();
				}}
			>
				<div className="lfr-objects__model-builder-node-header-label-container">
					<div
						className={classNames(
							'lfr-objects__model-builder-node-header-label-title',
							!dbTableName?.length &&
								'lfr-objects__model-builder-node-header-label-title--danger'
						)}
					>
						{(!dbTableName?.length || isLinkedObjectDefinition) && (
							<ClayIcon
								className="c-pt-1 text-4"
								symbol={
									!dbTableName?.length
										? 'exclamation-circle'
										: 'link'
								}
							/>
						)}

						<span>{objectDefinitionLabel}</span>
					</div>

					<ClayDropDownWithItems
						items={dropDownItems}
						trigger={
							<ClayButtonWithIcon
								aria-label={Liferay.Language.get(
									'show-actions'
								)}
								displayType="secondary"
								onClick={(event) => {
									event?.stopPropagation();
								}}
								size="xs"
								symbol="ellipsis-v"
							/>
						}
					/>
				</div>

				<div>
					<ClayLabel displayType={system ? 'info' : 'warning'}>
						{system
							? Liferay.Language.get('system')
							: Liferay.Language.get('custom')}
					</ClayLabel>

					<ClayLabel
						displayType={
							status?.label === 'approved'
								? 'success'
								: status?.label === 'pending'
									? 'info'
									: 'secondary'
						}
					>
						{status?.label === 'approved'
							? Liferay.Language.get('approved')
							: status?.label === 'pending'
								? Liferay.Language.get('pending')
								: Liferay.Language.get('draft')}
					</ClayLabel>
				</div>
			</div>
		</>
	);
}
