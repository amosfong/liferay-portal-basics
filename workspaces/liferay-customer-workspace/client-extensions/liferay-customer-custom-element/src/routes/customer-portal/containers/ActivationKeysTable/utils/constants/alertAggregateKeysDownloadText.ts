/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import i18n from '../../../../../../common/I18n';
import {ALERT_DOWNLOAD_TYPE} from '../../../../utils/constants/alertDownloadType';

export const ALERT_ACTIVATION_AGGREGATED_KEYS_DOWNLOAD_TEXT = {
	[ALERT_DOWNLOAD_TYPE.danger]: i18n.translate(
		'unable-to-export-keys-please-try-again'
	),
	[ALERT_DOWNLOAD_TYPE.success]: i18n.translate(
		'activation-key-was-downloaded-successfully'
	),
};