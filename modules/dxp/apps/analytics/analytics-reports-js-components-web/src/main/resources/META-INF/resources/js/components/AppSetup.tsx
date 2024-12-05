/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Provider as ClayIconProvider} from '@clayui/core';
import ClayLink from '@clayui/link';
import {ClayTooltipProvider} from '@clayui/tooltip';
import {fetch} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import {AnalyticsReportsProvider} from '../AnalyticsReportsContext';
import {AssetTypes, Version} from '../types/global';
import EmptyState from './EmptyState';
import StateRenderer from './StateRenderer';

interface IAppSetupStateRendererProps
	extends React.HTMLAttributes<HTMLElement> {
	contentPerformanceDataFetchURL: string;
	getItemVersionsURL: string;
}

type Data = {
	analyticsSettingsPortletURL: string;
	assetId: string;
	assetLibrary: boolean;
	assetType: AssetTypes | null;
	connectedToAnalyticsCloud: boolean;
	connectedToAssetLibrary: boolean;
	groupId: string;
	isAdmin: boolean;
	siteEditDepotEntryDepotAdminPortletURL: string;
	siteSyncedToAnalyticsCloud: boolean;
	versions: Version[] | null;
};

interface IAppSetupProps {
	data: Data;
}

const AppSetup: React.FC<IAppSetupProps> = ({children, data}) => {
	if (!data.connectedToAnalyticsCloud) {
		if (data.isAdmin) {
			return (
				<EmptyState
					description={Liferay.Language.get(
						'in-order-to-view-asset-performance,-your-liferay-dxp-instance-has-to-be-connected-with-liferay-analytics-cloud'
					)}
					title={Liferay.Language.get(
						'connect-to-liferay-analytics-cloud'
					)}
				>
					<ClayLink
						button
						displayType="secondary"
						href={data.analyticsSettingsPortletURL}
					>
						{Liferay.Language.get('connect')}
					</ClayLink>
				</EmptyState>
			);
		}

		return (
			<EmptyState
				description={Liferay.Language.get(
					'please-contact-a-dxp-instance-administrator-to-connect-your-dxp-instance-to-analytics-cloud'
				)}
				title={Liferay.Language.get(
					'connect-to-liferay-analytics-cloud'
				)}
			/>
		);
	}

	if (data.assetLibrary && !data.connectedToAssetLibrary) {
		if (data.isAdmin) {
			return (
				<EmptyState
					description={Liferay.Language.get(
						'in-order-to-view-asset-performance,-connect-sites-that-are-synced-to-analytics-cloud-to-your-asset-library'
					)}
					imgSrc={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/search_state.svg`}
					title={Liferay.Language.get(
						'there-are-no-sites-connected-to-this-asset-library'
					)}
				>
					<ClayLink
						button
						displayType="secondary"
						href={data.siteEditDepotEntryDepotAdminPortletURL}
					>
						{Liferay.Language.get('connect')}
					</ClayLink>
				</EmptyState>
			);
		}

		return (
			<EmptyState
				description={Liferay.Language.get(
					'please-contact-a-dxp-instance-administrator-to-connect-your-sites-to-an-asset-library'
				)}
				imgSrc={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/search_state.svg`}
				title={Liferay.Language.get(
					'there-are-no-sites-connected-to-this-asset-library'
				)}
			/>
		);
	}

	if (!data.siteSyncedToAnalyticsCloud) {
		if (data.isAdmin) {
			return (
				<EmptyState
					description={Liferay.Language.get(
						'in-order-to-view-asset-performance,-your-sites-have-to-be-synced-to-liferay-analytics-cloud'
					)}
					title={Liferay.Language.get('sync-to-analytics-cloud')}
				>
					<ClayLink
						button
						displayType="secondary"
						href={`${data.analyticsSettingsPortletURL}&currentPage=PROPERTIES`}
					>
						{Liferay.Language.get('sync')}
					</ClayLink>
				</EmptyState>
			);
		}

		return (
			<EmptyState
				description={Liferay.Language.get(
					'please-contact-a-dxp-instance-administrator-to-sync-your-sites-to-analytics-cloud'
				)}
				title={Liferay.Language.get('sync-to-analytics-cloud')}
			/>
		);
	}

	return (
		<ClayIconProvider
			spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/clay/icons.svg`}
		>
			<ClayTooltipProvider>
				<div>
					<AnalyticsReportsProvider
						assetId={data?.assetId ?? '0'}
						assetType={data?.assetType ?? null}
						groupId={data?.groupId ?? '0'}
						versions={data?.versions ?? null}
					>
						{children}
					</AnalyticsReportsProvider>
				</div>
			</ClayTooltipProvider>
		</ClayIconProvider>
	);
};

const AppSetupStateRenderer: React.FC<IAppSetupStateRendererProps> = ({
	children,
	contentPerformanceDataFetchURL,
	getItemVersionsURL,
}) => {
	const [data, setData] = useState<Data | null>(null);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState('');

	useEffect(() => {
		async function fetchData() {
			try {
				const response = await fetch(contentPerformanceDataFetchURL, {
					method: 'GET',
				});

				if (!response.ok) {
					throw new Error();
				}

				const data = await response.json();

				if (data.error) {
					throw new Error(data.error);
				}

				let versionsData:
					| ({versions: Version[]} & {error: string})
					| null = null;

				if (getItemVersionsURL) {
					const responseVersions = await fetch(getItemVersionsURL, {
						method: 'GET',
					});

					if (!responseVersions.ok) {
						throw new Error();
					}

					versionsData = await responseVersions.json();

					if (versionsData?.error) {
						throw new Error(versionsData.error);
					}
				}

				setData({
					...data,
					versions: versionsData?.versions.map(
						({createDate, version}) => ({
							createDate,
							version,
						})
					),
				});
				setLoading(false);
				setError('');
			}
			catch (error: any) {
				if (process.env.NODE_ENV === 'development') {
					console.error(error);
				}

				setData(null);
				setLoading(false);
				setError(error.toString());
			}
		}

		fetchData();
	}, [contentPerformanceDataFetchURL, getItemVersionsURL]);

	return (
		<StateRenderer data={data} error={error} loading={loading}>
			{({data}) => <AppSetup data={data}>{children}</AppSetup>}
		</StateRenderer>
	);
};

export default AppSetupStateRenderer;
