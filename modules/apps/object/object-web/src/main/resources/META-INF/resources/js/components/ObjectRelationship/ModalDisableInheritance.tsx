/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {Text} from '@clayui/core';
import ClayModal, {ClayModalProvider, useModal} from '@clayui/modal';
import React, {useEffect, useState} from 'react';

function ModalDisableInheritance() {
	const [handleDisable, setHandleDisable] = useState<() => Promise<void>>();
	const [visible, setVisible] = useState(false);

	const {observer, onClose} = useModal({
		onClose: () => {
			setVisible(false);
		},
	});

	const warningMessages = [
		Liferay.Language.get('permissions-are-copied-from-the-previous-parent'),
		Liferay.Language.get(
			'rest-endpoints-are-created-for-the-new-parent-with-child-endpoints-grouped-under-them'
		),
		Liferay.Language.get(
			'an-action-trigger-is-created-for-updating-child-objects'
		),
	];

	useEffect(() => {
		const openModal = ({
			handleDisable,
		}: {
			handleDisable: () => Promise<void>;
		}) => {
			setHandleDisable(() => handleDisable);
			setVisible(true);
		};

		Liferay.on('openModalDisableInheritance', openModal);

		return () =>
			Liferay.detach(
				'openModalDisableInheritance',
				openModal as () => void
			);
	}, []);

	return (
		<>
			{visible && (
				<ClayModalProvider>
					<ClayModal center observer={observer} status="warning">
						<ClayModal.Header>
							{Liferay.Language.get(
								'disable-inheritance-confirmation'
							)}
						</ClayModal.Header>

						<ClayModal.Body className="c-gap-4 d-flex flex-column">
							<Text>
								{Liferay.Language.get(
									'when-you-disable-inheritance,-the-first-child-object-is-updated-with-options-for-showing-the-widget-in-the-page-builder-and-for-configuring-the-panel-link'
								)}
							</Text>

							<ol>
								{warningMessages.map((warningMessage) => (
									<li key={warningMessage}>
										<Text>{warningMessage}</Text>
									</li>
								))}
							</ol>
						</ClayModal.Body>

						<ClayModal.Footer
							last={
								<ClayButton.Group spaced>
									<ClayButton
										displayType="secondary"
										onClick={onClose}
									>
										{Liferay.Language.get('cancel')}
									</ClayButton>

									<ClayButton
										displayType="warning"
										onClick={async () => {
											if (handleDisable) {
												await handleDisable();
											}

											onClose();
										}}
									>
										{Liferay.Language.get('disable')}
									</ClayButton>
								</ClayButton.Group>
							}
						></ClayModal.Footer>
					</ClayModal>
				</ClayModalProvider>
			)}
		</>
	);
}

export default ModalDisableInheritance;
