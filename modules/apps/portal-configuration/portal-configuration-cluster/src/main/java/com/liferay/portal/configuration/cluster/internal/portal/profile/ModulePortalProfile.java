/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.cluster.internal.portal.profile;

import com.liferay.portal.configuration.cluster.internal.ConfigurationSynchronousConfigurationListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	protected void activate(ComponentContext componentContext) {
		List<String> supportedPortalProfileNames = null;

		if (GetterUtil.getBoolean(_props.get(PropsKeys.CLUSTER_LINK_ENABLED))) {
			supportedPortalProfileNames = new ArrayList<>();

			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_CE);
			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_DXP);
		}
		else {
			supportedPortalProfileNames = Collections.emptyList();
		}

		init(
			componentContext, supportedPortalProfileNames,
			ConfigurationSynchronousConfigurationListener.class.getName());
	}

	@Reference
	private Props _props;

}