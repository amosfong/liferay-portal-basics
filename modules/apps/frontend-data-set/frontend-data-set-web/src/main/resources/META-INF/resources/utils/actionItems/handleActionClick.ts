/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {navigate, openConfirmModal} from 'frontend-js-web';

import {IItemsActions} from '../../index';
import {openPermissionsModal} from '../modals/openPermissionsModal';
import {resolveModalSize} from '../modals/resolveModalSize';
import {ACTION_ITEM_TARGETS} from './constants';
import formatActionURL from './formatActionURL';

const {MODAL_PERMISSIONS} = ACTION_ITEM_TARGETS;

const handleActionClick = ({
	action,
	closeMenu,
	event,
	executeAsyncItemAction,
	highlightItems,
	itemData,
	itemId,
	loadData,
	onActionDropdownItemClick,
	openModal,
	openSidePanel,
	setLoading,
	toggleItemInlineEdit,
}: {
	action: IItemsActions;
	closeMenu?: any;
	event: Event;
	executeAsyncItemAction: Function;
	highlightItems: Function;
	itemData: any;
	itemId: string | number;
	loadData: Function;
	onActionDropdownItemClick: Function;
	openModal: Function;
	openSidePanel: Function;
	setLoading?: Function;
	toggleItemInlineEdit: Function;
}) => {
	const {data, href, method, onClick, target} = action;

	const {
		confirmationMessage,
		disableHeader,
		errorMessage,
		requestBody,
		size,
		status,
		successMessage,
		title,
	} = data ?? {};

	const url = formatActionURL(href, itemData, target);

	const doAction = ({defaultPrevented}: {defaultPrevented: boolean}) => {
		if (target?.includes('modal')) {
			event.preventDefault();

			if (target === MODAL_PERMISSIONS) {
				openPermissionsModal(url);
			}
			else {
				openModal({
					disableHeader,
					size: size || resolveModalSize(target),
					title,
					url,
				});
			}
		}
		else if (target === 'sidePanel') {
			event.preventDefault();

			highlightItems([itemId]);

			openSidePanel({
				disableHeader,
				size: 'lg',
				title,
				url,
			});
		}
		else if (target === 'async' || target === 'headless') {
			event.preventDefault();

			setLoading && setLoading(true);

			executeAsyncItemAction({
				errorMessage,
				method: method ?? data?.method,
				requestBody,
				setActionItemLoading: setLoading,
				successMessage,
				url,
			});
		}
		else if (target === 'inlineEdit') {
			event.preventDefault();

			toggleItemInlineEdit(itemId);
		}
		else if (target === 'blank') {
			event.preventDefault();

			window.open(url);
		}

		const exposedProps = {
			action,
			event,
			itemData,
			loadData,
			openSidePanel,
		};

		if (onClick) {
			onClick(exposedProps);
		}

		if (onActionDropdownItemClick) {
			onActionDropdownItemClick(exposedProps);
		}

		if (target === 'link' && defaultPrevented) {
			navigate(url);
		}
	};

	if (confirmationMessage) {
		let defaultPrevented = false;

		if (target === 'link') {
			event.preventDefault();

			defaultPrevented = true;
		}

		openConfirmModal({
			message: confirmationMessage,
			onConfirm: (isConfirmed) => {
				if (isConfirmed) {
					doAction({defaultPrevented});
				}
			},
			status,
			title,
		});
	}
	else {
		doAction({defaultPrevented: false});
	}

	if (closeMenu) {
		closeMenu();
	}
};

export default handleActionClick;
