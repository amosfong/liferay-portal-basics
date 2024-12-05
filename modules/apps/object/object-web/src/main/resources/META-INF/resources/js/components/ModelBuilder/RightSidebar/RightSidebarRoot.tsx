/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CustomVerticalBar} from '@liferay/object-js-components-web';
import React, {ReactNode, useEffect, useState} from 'react';

import './RightSidebarRoot.scss';
import {useObjectFolderContext} from '../ModelBuilderContext/objectFolderContext';
import {getRightSidebarWidth} from './rightSidebarUtil';

interface IRightSidebarRoot {
	children: ReactNode;
}

export function RightSideBarRoot({children}: IRightSidebarRoot) {
	const [
		{
			selectedObjectDefinitionNode,
			selectedObjectField,
			selectedObjectRelationship,
			showSidebars,
		},
	] = useObjectFolderContext();
	const [loading, setLoading] = useState(false);
	const [verticalBarWidth, setVerticalBarWidth] = useState(320);

	const setNewVerticalBarWidthValue = (width: number) => {
		setLoading(true);

		setVerticalBarWidth(width);

		setTimeout(() => setLoading(false), 50);
	};

	useEffect(() => {
		const newRightSidebarWidth = getRightSidebarWidth(selectedObjectField);

		setNewVerticalBarWidthValue(newRightSidebarWidth);
	}, [
		selectedObjectDefinitionNode,
		selectedObjectField,
		selectedObjectRelationship,
	]);

	return (
		<>
			{!loading && (
				<CustomVerticalBar
					className="lfr-objects__model-builder-custom-vertical-bar"
					defaultActive="objectsModelBuilderRightSidebar"
					panelWidth={verticalBarWidth}
					position="right"
					resize={false}
					triggerSideBarAnimation={showSidebars}
					verticalBarItems={[
						{
							title: 'objectsModelBuilderRightSidebar',
						},
					]}
				>
					{children}
				</CustomVerticalBar>
			)}
		</>
	);
}
