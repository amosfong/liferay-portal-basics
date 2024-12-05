/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.k8s.agent.internal.model.listener;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.k8s.agent.PortalK8sConfigMapModifier;
import com.liferay.portal.k8s.agent.internal.thread.local.AgentPortalK8sThreadLocal;
import com.liferay.portal.k8s.agent.internal.util.CompanyConfigMapUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.service.Snapshot;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 */
@Component(service = ModelListener.class)
public class CompanyModelListener extends BaseModelListener<Company> {

	@Override
	public void onAfterRemove(Company company) throws ModelListenerException {
		if (_log.isDebugEnabled()) {
			_log.debug("Removing company " + company.getWebId());
		}

		PortalK8sConfigMapModifier portalK8sConfigMapModifier =
			_portalK8sConfigMapModifierSnapshot.get();

		try (SafeCloseable safeCloseable =
				AgentPortalK8sThreadLocal.
					executeOnCurrentNodeWithSafeCloseable()) {

			CompanyConfigMapUtil.clearConfigMap(
				company, portalK8sConfigMapModifier);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CompanyModelListener.class);

	private static final Snapshot<PortalK8sConfigMapModifier>
		_portalK8sConfigMapModifierSnapshot = new Snapshot<>(
			CompanyModelListener.class, PortalK8sConfigMapModifier.class, null,
			true);

}