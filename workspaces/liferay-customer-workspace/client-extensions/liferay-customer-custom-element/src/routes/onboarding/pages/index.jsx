/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useEffect, useState} from 'react';
import useUserAccountsByAccountExternalReferenceCode from '~/routes/customer-portal/pages/Project/TeamMembers/components/TeamMembersTable/hooks/useUserAccountsByAccountExternalReferenceCode';
import i18n from '../../../common/I18n';
import InviteTeamMembersForm from '../../../common/containers/setup-forms/InviteTeamMembersForm';
import SetupAnalyticsCloudForm from '../../../common/containers/setup-forms/SetupAnalyticsCloudForm';
import SetupDXPCloudForm from '../../../common/containers/setup-forms/SetupDXPCloudForm';
import {useAppPropertiesContext} from '../../../common/contexts/AppPropertiesContext';
import {getOrRequestToken} from '../../../common/services/liferay/security/auth/getOrRequestToken';
import {PAGE_ROUTER_TYPES} from '../../../common/utils/constants';
import ConfirmationMessageModal from '../../customer-portal/components/ActivationStatus/LiferayExperienceCloud/components/ConfirmationMessageModal';
import SetupLiferayExperienceCloudForm from '../../customer-portal/components/ActivationStatus/LiferayExperienceCloud/components/SetupLXCModal/components/SetupLXCForm';
import {LIST_TYPES, PRODUCT_TYPES} from '../../customer-portal/utils/constants';
import {useOnboarding} from '../context';
import {actionTypes} from '../context/reducer';
import {ONBOARDING_STEP_TYPES} from '../utils/constants';
import SuccessCloud from './SuccessCloud';
import Welcome from './Welcome';

const Pages = () => {
	const [
		{
			analyticsCloudActivationSubmittedStatus,
			dxpCloudActivationSubmittedStatus,
			liferayExperienceCloudActivationSubmittedStatus,
			project,
			step,
			subscriptionGroups,
		},
		dispatch,
	] = useOnboarding();

	const [oAuthToken, setOAuthToken] = useState();

	useEffect(() => {
		const fetchToken = async () => {
			const token = await getOrRequestToken();

			setOAuthToken(token);
		};

		fetchToken();
	}, []);

	const [supportSeatsCount] = useUserAccountsByAccountExternalReferenceCode(
		project?.accountKey
	);

	const {client, featureFlags} = useAppPropertiesContext();

	const subscriptionDXPCloud = subscriptionGroups?.find(
		(subscriptionGroup) => subscriptionGroup.name === PRODUCT_TYPES.dxpCloud
	);

	const subscriptionAnalyticsCloud = subscriptionGroups?.find(
		(subscriptionGroup) =>
			subscriptionGroup.name === PRODUCT_TYPES.analyticsCloud
	);

	const subscriptionLiferayExperienceCloud = subscriptionGroups?.find(
		(subscriptionGroup) =>
			subscriptionGroup.name === PRODUCT_TYPES.liferayExperienceCloud
	);

	const pageHandle = () => {
		window.location.href = PAGE_ROUTER_TYPES.project(project.accountKey);
	};

	const invitesPageHandle = () => {
		if (
			featureFlags.includes('LPS-153478') &&
			subscriptionLiferayExperienceCloud &&
			!liferayExperienceCloudActivationSubmittedStatus
		) {
			return dispatch({
				payload: ONBOARDING_STEP_TYPES.liferayExperienceCloud,
				type: actionTypes.CHANGE_STEP,
			});
		} else {
			if (subscriptionDXPCloud && !dxpCloudActivationSubmittedStatus) {
				return dispatch({
					payload: ONBOARDING_STEP_TYPES.dxpCloud,
					type: actionTypes.CHANGE_STEP,
				});
			}

			if (
				subscriptionAnalyticsCloud &&
				!analyticsCloudActivationSubmittedStatus
			) {
				return dispatch({
					payload: ONBOARDING_STEP_TYPES.analyticsCloud,
					type: actionTypes.CHANGE_STEP,
				});
			}
		}

		pageHandle();
	};

	const dxpCloudPageHandle = () => {
		if (
			subscriptionAnalyticsCloud &&
			!analyticsCloudActivationSubmittedStatus
		) {
			return dispatch({
				payload: ONBOARDING_STEP_TYPES.analyticsCloud,
				type: actionTypes.CHANGE_STEP,
			});
		}

		pageHandle();
	};

	let availableSupportSeatsCount =
		project && project.maxRequestors - supportSeatsCount;
	availableSupportSeatsCount =
		availableSupportSeatsCount < 0 ? 0 : availableSupportSeatsCount;

	const StepsLayout = {
		[ONBOARDING_STEP_TYPES.invites]: {
			Component: (
				<InviteTeamMembersForm
					availableSupportSeatsCount={availableSupportSeatsCount}
					handlePage={invitesPageHandle}
					leftButton={i18n.translate('skip-for-now')}
					oAuthToken={oAuthToken}
					project={project}
				/>
			),
		},

		[ONBOARDING_STEP_TYPES.liferayExperienceCloud]: {
			Component: (
				<SetupLiferayExperienceCloudForm
					client={client}
					handleChangeForm={() => pageHandle()}
					handleOnLeftButtonClick={() => pageHandle()}
					leftButton={i18n.translate('skip-for-now')}
					project={project}
					subscriptionGroupLxcId={
						subscriptionLiferayExperienceCloud?.accountSubscriptionGroupId
					}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.successliferayExperienceCloud]: {
			Component: (
				<ConfirmationMessageModal
					handleChangeForm={() => pageHandle()}
					productType={PRODUCT_TYPES.liferayExperienceCloud}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.dxpCloud]: {
			Component: (
				<SetupDXPCloudForm
					client={client}
					dxpVersion={project?.dxpVersion}
					handlePage={(isSuccess) => {
						if (isSuccess) {
							return dispatch({
								payload: ONBOARDING_STEP_TYPES.successDxpCloud,
								type: actionTypes.CHANGE_STEP,
							});
						}
						dxpCloudPageHandle();
					}}
					leftButton={i18n.translate('skip-for-now')}
					listType={LIST_TYPES.dxpVersion}
					project={project}
					subscriptionGroupId={
						subscriptionDXPCloud?.accountSubscriptionGroupId
					}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.successDxpCloud]: {
			Component: (
				<SuccessCloud
					handlePage={dxpCloudPageHandle}
					productType={PRODUCT_TYPES.dxpCloud}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.welcome]: {
			Component: <Welcome />,
			Skeleton: <Welcome.Skeleton />,
		},
		[ONBOARDING_STEP_TYPES.analyticsCloud]: {
			Component: (
				<SetupAnalyticsCloudForm
					client={client}
					handlePage={(isSuccess) => {
						if (isSuccess) {
							return dispatch({
								payload:
									ONBOARDING_STEP_TYPES.successAnalyticsCloud,
								type: actionTypes.CHANGE_STEP,
							});
						}
						pageHandle();
					}}
					leftButton={i18n.translate('skip-for-now')}
					project={project}
					subscriptionGroupId={
						subscriptionAnalyticsCloud?.accountSubscriptionGroupId
					}
				/>
			),
		},
		[ONBOARDING_STEP_TYPES.successAnalyticsCloud]: {
			Component: (
				<SuccessCloud
					handlePage={pageHandle}
					productType={PRODUCT_TYPES.analyticsCloud}
				/>
			),
		},
	};

	if (project && subscriptionGroups) {
		return StepsLayout[step].Component;
	}

	return StepsLayout[ONBOARDING_STEP_TYPES.welcome].Skeleton;
};

export default Pages;