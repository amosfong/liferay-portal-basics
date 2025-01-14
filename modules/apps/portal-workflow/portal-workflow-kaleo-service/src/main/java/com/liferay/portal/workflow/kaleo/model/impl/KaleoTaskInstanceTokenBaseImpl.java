/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

/**
 * The extended model base implementation for the KaleoTaskInstanceToken service. Represents a row in the &quot;KaleoTaskInstanceToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoTaskInstanceTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenImpl
 * @see KaleoTaskInstanceToken
 * @generated
 */
public abstract class KaleoTaskInstanceTokenBaseImpl
	extends KaleoTaskInstanceTokenModelImpl implements KaleoTaskInstanceToken {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo task instance token model instance should use the <code>KaleoTaskInstanceToken</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoTaskInstanceTokenLocalServiceUtil.addKaleoTaskInstanceToken(
				this);
		}
		else {
			KaleoTaskInstanceTokenLocalServiceUtil.updateKaleoTaskInstanceToken(
				this);
		}
	}

}