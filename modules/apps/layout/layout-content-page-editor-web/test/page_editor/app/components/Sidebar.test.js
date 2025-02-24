/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {act, fireEvent, render, screen} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {sessionStorage} from 'frontend-js-web';
import React from 'react';
import {DndProvider} from 'react-dnd';
import {HTML5Backend} from 'react-dnd-html5-backend';

import Sidebar, {
	MAX_SIDEBAR_WIDTH,
	MIN_SIDEBAR_WIDTH,
	SIDEBAR_WIDTH_RESIZE_STEP,
} from '../../../../src/main/resources/META-INF/resources/page_editor/app/components/Sidebar';
import {useSetOpenShortcutModal} from '../../../../src/main/resources/META-INF/resources/page_editor/app/contexts/ShortcutContext';
import {DragAndDropContextProvider} from '../../../../src/main/resources/META-INF/resources/page_editor/app/utils/drag_and_drop/useDragAndDrop';
import StoreMother from '../../../../src/main/resources/META-INF/resources/page_editor/test_utils/StoreMother';

jest.mock(
	'../../../../src/main/resources/META-INF/resources/page_editor/app/contexts/ShortcutContext',
	() => {
		const setOpenShortcutModal = jest.fn();

		return {
			...jest.requireActual(
				'../../../../src/main/resources/META-INF/resources/page_editor/app/contexts/ShortcutContext'
			),
			useSetOpenShortcutModal: () => setOpenShortcutModal,
		};
	}
);

const renderSidebar = () =>
	render(
		<StoreMother.Component>
			<DndProvider backend={HTML5Backend}>
				<DragAndDropContextProvider>
					<Sidebar />
				</DragAndDropContextProvider>
			</DndProvider>
		</StoreMother.Component>
	);

describe('Sidebar', () => {
	beforeAll(() => {
		Liferay.Language.direction = {en_US: 'ltr'};
	});

	describe('resize', () => {
		afterEach(() => {
			sessionStorage.clear();
		});

		it('can be resized with mouse', async () => {
			await renderSidebar();

			const handler = screen.getByRole('separator', {
				label: 'resize-sidebar',
			});

			act(() => {
				fireEvent(
					handler,
					new MouseEvent('mousedown', {
						bubbles: true,
						cancelable: true,
						clientX: 0,
					})
				);

				fireEvent(
					document.body,
					new MouseEvent('mousemove', {
						bubbles: true,
						cancelable: true,
						clientX: 100,
					})
				);

				fireEvent(
					document.body,
					new MouseEvent('mouseup', {
						bubbles: true,
						cancelable: true,
						clientX: 100,
					})
				);
			});

			expect(handler.getAttribute('aria-valuenow')).toBe(
				(MIN_SIDEBAR_WIDTH + 100).toString()
			);
		});

		it('can be resized with arrow keys', async () => {
			await renderSidebar();

			const handler = screen.getByRole('separator', {
				label: 'resize-sidebar',
			});

			act(() => {
				fireEvent(
					handler,
					new KeyboardEvent('keydown', {
						bubbles: true,
						cancelable: true,
						key: 'ArrowRight',
					})
				);
			});

			expect(handler.getAttribute('aria-valuenow')).toBe(
				(MIN_SIDEBAR_WIDTH + SIDEBAR_WIDTH_RESIZE_STEP).toString()
			);

			act(() => {
				fireEvent(
					handler,
					new KeyboardEvent('keydown', {
						bubbles: true,
						cancelable: true,
						key: 'End',
					})
				);
			});

			expect(handler.getAttribute('aria-valuenow')).toBe(
				MAX_SIDEBAR_WIDTH.toString()
			);

			act(() => {
				fireEvent(
					handler,
					new KeyboardEvent('keydown', {
						bubbles: true,
						cancelable: true,
						key: 'Home',
					})
				);
			});

			expect(handler.getAttribute('aria-valuenow')).toBe(
				MIN_SIDEBAR_WIDTH.toString()
			);
		});

		it('opens the shortcut modal when the button is pressed', () => {
			const setOpenShortcutModal = useSetOpenShortcutModal();

			renderSidebar();

			userEvent.click(
				screen.getByLabelText('open-keyboard-shortcuts⇧+?')
			);

			expect(setOpenShortcutModal).toBeCalledWith(true);
		});
	});
});
