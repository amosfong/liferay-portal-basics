/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import React, {MouseEventHandler} from 'react';

interface Props {
	analyticsURL: string;
	hideAnalyticsReportsPanelURL: string;
	isAnalyticsConnected: boolean;
	onHideAnalyticsReportsPanelClick: MouseEventHandler;
	pathToAssets: string;
}

export default function ConnectToAC({
	analyticsURL,
	isAnalyticsConnected,
	hideAnalyticsReportsPanelURL,
	pathToAssets,

	onHideAnalyticsReportsPanelClick = (event) => {
		event.preventDefault();

		// @ts-ignore

		if (document.hrefFm && typeof submitForm !== 'undefined') {

			// @ts-ignore

			submitForm(document.hrefFm, hideAnalyticsReportsPanelURL);
		}
	},
}: Props) {
	return (
		<div className="p-3 pt-4 text-center">
			<img
				alt={Liferay.Language.get('connect-to-liferay-analytics-cloud')}
				src={`${pathToAssets}/assets/ac-icon.svg`}
			></img>

			{isAnalyticsConnected ? (
				<>
					<div className="font-weight-semi-bold h5 mt-3">
						{Liferay.Language.get('sync-to-analytics-cloud')}
					</div>

					<p className="text-secondary">
						{Liferay.Language.get(
							'sync-your-liferay-dxp-instance-with-analytics-cloud-to-view-content-performance-metrics-and-build-a-successful-content-strategy'
						)}
					</p>

					<ClayLink
						button={true}
						displayType="primary"
						href={analyticsURL}
						target="_blank"
					>
						{Liferay.Language.get('open-analytics-cloud')}

						<span className="inline-item inline-item-after">
							<ClayIcon symbol="shortcut" />
						</span>
					</ClayLink>
				</>
			) : (
				<>
					<div className="font-weight-semi-bold h5 mt-3">
						{Liferay.Language.get(
							'connect-to-liferay-analytics-cloud'
						)}
					</div>

					<p className="text-secondary">
						{Liferay.Language.get(
							'liferay-dxp-instance-has-to-be-connected-with-analytics-cloud-to-view-content-performance-metrics-and-build-a-successful-content-strategy'
						)}
					</p>

					<ClayLink
						className="d-block font-weight-bold mb-2 mt-5"
						onClick={onHideAnalyticsReportsPanelClick}
						tabIndex={0}
					>
						{Liferay.Language.get('do-not-show-me-this-again')}
					</ClayLink>

					<p className="text-secondary">
						{Liferay.Language.get('do-not-show-me-this-again-help')}
					</p>
				</>
			)}
		</div>
	);
}
