/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CodeMirror} from '@liferay/frontend-js-codemirror-web';
import React, {ReactNode, RefObject} from 'react';

import {Collapsible} from './Collapsible';
import {Element} from './Element';

import './Sidebar.scss';

interface SidebarElement {
	content: string;
	helpText?: string;
	label: string;
	tooltip?: string;
}

export interface SidebarCategory {
	items: SidebarElement[];
	label: string;
}
interface SidebarProps {
	CustomSidebarContent?: ReactNode;
	disabled?: boolean;
	editorRef: RefObject<CodeMirror.Editor>;
	elements: SidebarCategory[];
	elementsDisabled?: boolean;
	otherProps?: unknown;
}

export function Sidebar({
	CustomSidebarContent,
	disabled,
	editorRef,
	elements,
	elementsDisabled,
}: SidebarProps) {
	const handleClick = (item: SidebarElement) =>
		editorRef.current?.replaceSelection(item.content);

	return (
		<div className="lfr-objects__code-editor-sidebar">
			<div className="px-3">
				<div className="h5 my-3">
					{Liferay.Language.get('elements')}
				</div>

				{CustomSidebarContent}

				{elements?.map(({items, label}) => (
					<Collapsible key={label} label={label}>
						{items.map((item) => (
							<Element
								disabled={elementsDisabled || disabled}
								helpText={
									(item.helpText ?? item.tooltip) as string
								}
								key={item.label}
								label={item.label}
								onClick={() => handleClick(item)}
							/>
						))}
					</Collapsible>
				))}
			</div>
		</div>
	);
}
