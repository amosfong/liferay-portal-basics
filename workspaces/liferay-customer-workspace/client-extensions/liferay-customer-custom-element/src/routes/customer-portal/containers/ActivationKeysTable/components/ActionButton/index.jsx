/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useCallback} from 'react';
import {useNavigate} from 'react-router-dom';
import i18n from '../../../../../../common/I18n';
import {Button, ButtonDropDown} from '../../../../../../common/components';
import {useAppPropertiesContext} from '../../../../../../common/contexts/AppPropertiesContext';
import {ALERT_DOWNLOAD_TYPE} from '../../../../utils/constants';
import {getFilteredKeysActionsItems} from '../../utils/constants/columns-definitions/getFilteredKeysActionsItems';
import {getActivationKeyDownload} from '../../utils/getActivationKeyDownload';
import {getActivationKeysActionsItems} from '../../utils/getActivationKeysActionsItems';
import {getActivationKeysDownloadItems} from '../../utils/getActivationKeysDownloadItems';

const ActionButton = ({
	activationKeysByStatusPaginatedChecked,
	filterCheckedActivationKeys,
	hasRenewalSubscription,
	identifier,
	isAbleToDownloadAggregateKeys,
	isAdminOrPartnerManager,
	isAdminUserAccount,
	oAuthToken,
	productName,
	project,
	setStatus,
}) => {
	const {featureFlags, provisioningServerAPI} = useAppPropertiesContext();
	const navigate = useNavigate();

	const allowSelfProvisioning = project.allowSelfProvisioning;

	const handleAlertStatus = useCallback(
		(hasSuccessfullyDownloadedKeys) =>
			setStatus((previousStatus) => ({
				...previousStatus,
				downloadAggregated: hasSuccessfullyDownloadedKeys
					? ALERT_DOWNLOAD_TYPE.success
					: ALERT_DOWNLOAD_TYPE.danger,
			})),
		[setStatus]
	);

	const handleMultipleAlertStatus = useCallback(
		(hasSuccessfullyDownloadedKeys) =>
			setStatus((previousStatus) => ({
				...previousStatus,
				downloadMultiple: hasSuccessfullyDownloadedKeys
					? ALERT_DOWNLOAD_TYPE.success
					: ALERT_DOWNLOAD_TYPE.danger,
			})),
		[setStatus]
	);

	if (activationKeysByStatusPaginatedChecked.length > 1) {
		const activationKeysDownloadItems = getActivationKeysDownloadItems(
			isAbleToDownloadAggregateKeys,
			filterCheckedActivationKeys,
			oAuthToken,
			provisioningServerAPI,
			handleMultipleAlertStatus,
			handleAlertStatus,
			activationKeysByStatusPaginatedChecked,
			project.name,
			featureFlags
		);

		return (
			<ButtonDropDown
				items={activationKeysDownloadItems}
				label={i18n.translate('download')}
				menuElementAttrs={{
					className: 'p-0 cp-drop-down-action-button',
				}}
			/>
		);
	}

	if (activationKeysByStatusPaginatedChecked.length === 1) {
		return (
			<Button
				className="btn btn-primary"
				onClick={() =>
					getActivationKeyDownload(
						oAuthToken,
						provisioningServerAPI,
						handleAlertStatus,
						activationKeysByStatusPaginatedChecked[0],
						project.name
					)
				}
			>
				{i18n.translate('download')}
			</Button>
		);
	}

	const handleRedirectPage = () => {
		navigate('new', {
			state: {
				activationKeys: [],
				id: identifier,
			},
		});
	};
	const handleDeactivatePage = () => navigate('deactivate');

	const handleRedirectRenewPage = () => {
		navigate(`${productName.toLowerCase()}-renew`, {
			state: {
				activationKeys: [],
				id: identifier,
			},
		});
	};

	const activationKeysActionsItems = getActivationKeysActionsItems(
		project?.accountKey,
		oAuthToken,
		provisioningServerAPI,
		handleAlertStatus,
		handleRedirectPage,
		handleDeactivatePage,
		productName,
		allowSelfProvisioning,
		hasRenewalSubscription,
		handleRedirectRenewPage
	);

	const filteredKeysActionsItems = getFilteredKeysActionsItems(
		project?.accountKey,
		oAuthToken,
		provisioningServerAPI,
		handleAlertStatus,
		productName
	);

	if (isAdminUserAccount || isAdminOrPartnerManager) {
		return (
			<ButtonDropDown
				items={activationKeysActionsItems}
				label={i18n.translate('actions')}
				menuElementAttrs={{
					className: 'p-0',
				}}
			/>
		);
	}

	return (
		<ButtonDropDown
			items={filteredKeysActionsItems}
			label={i18n.translate('actions')}
			menuElementAttrs={{
				className: 'p-0',
			}}
		/>
	);
};

export default ActionButton;
