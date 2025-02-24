/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import i18n from '~/common/I18n';

const SwitchSLACardButton = ({handleClick}) => (
	<ClayButtonWithIcon
		aria-label={i18n.translate('switch-sla-card')}
		className="bg-white cp-switch-sla-card-button d-none p-1 position-absolute rounded-circle shadow-lg"
		displayType="primary"
		onClick={handleClick}
		outline
		symbol="angle-right"
	/>
);
export default SwitchSLACardButton;
