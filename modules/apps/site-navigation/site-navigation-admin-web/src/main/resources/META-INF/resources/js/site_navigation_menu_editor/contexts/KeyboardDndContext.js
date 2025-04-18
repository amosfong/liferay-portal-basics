/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useCallback, useContext, useState} from 'react';

import {NESTING_MARGIN} from '../constants/nestingMargin';
import getFlatItems from '../utils/getFlatItems';

const KeyboardDndContext = React.createContext();

const ITEM_MARGIN = 16;

const ROOT_ITEM_OFFSET_WIDTH = NESTING_MARGIN * 0.325;

export function KeyboardDndProvider({children}) {
	const [dragLayer, setDragLayer] = useState(null);

	return (
		<KeyboardDndContext.Provider value={{dragLayer, setDragLayer}}>
			{children}
		</KeyboardDndContext.Provider>
	);
}

export function useDragLayer() {
	return useContext(KeyboardDndContext).dragLayer;
}

export function useSetDragLayer() {
	const {setDragLayer} = useContext(KeyboardDndContext);

	return useCallback(
		(items, nextDragLayer) => {
			if (!nextDragLayer) {
				setDragLayer(null);

				return;
			}

			// Try to put the placeholder next to the corresponding sibling

			const siblingsItems = getFlatItems(items).filter(
				(item) =>
					item.parentSiteNavigationMenuItemId ===
					nextDragLayer.parentSiteNavigationMenuItemId
			);

			const siblingItem = siblingsItems[nextDragLayer.order];

			if (siblingItem) {
				const siblingElement = document.querySelector(
					`[data-item-id="${siblingItem.siteNavigationMenuItemId}"]`
				);

				const siblingElementRect =
					siblingElement.firstChild.getBoundingClientRect();

				setDragLayer({
					...nextDragLayer,
					currentOffset: {
						x: siblingElementRect.x,
						y: siblingElementRect.y,
					},
				});

				return;
			}

			// Otherwise place it at the very end of it's parent

			const parentElement = document.querySelector(
				`[data-item-id="${nextDragLayer.parentSiteNavigationMenuItemId}"]`
			);

			if (!parentElement) {
				setDragLayer(nextDragLayer);

				return;
			}

			const parentElementAriaLevel = parseInt(
				parentElement.getAttribute('aria-level'),
				10
			);

			// Traverse the DOM until we find and element with same or less
			// aria-level than our parent.

			let nextChildNode = parentElement.nextElementSibling;

			while (nextChildNode) {
				const childNodeAriaLevel = parseInt(
					nextChildNode.getAttribute('aria-level'),
					10
				);

				if (childNodeAriaLevel <= parentElementAriaLevel) {
					break;
				}

				nextChildNode = nextChildNode.nextElementSibling;
			}

			const parentElementRect =
				parentElement.firstChild.getBoundingClientRect();

			// If we reach this point without nextChildNode, we have reached
			// the end of the tree.

			const wrapperElementRect = document
				.querySelector('[data-item-id="0"]')
				.getBoundingClientRect();

			setDragLayer({
				...nextDragLayer,
				currentOffset: {
					x:
						nextDragLayer.parentSiteNavigationMenuItemId === '0'
							? parentElementRect.x + ROOT_ITEM_OFFSET_WIDTH
							: parentElementRect.x + NESTING_MARGIN,
					y: nextChildNode
						? nextChildNode.getBoundingClientRect().y
						: wrapperElementRect.bottom + ITEM_MARGIN,
				},
			});
		},
		[setDragLayer]
	);
}
