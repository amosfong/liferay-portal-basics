/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectStateFlow;
import com.liferay.object.service.ObjectStateFlowLocalServiceUtil;

/**
 * The extended model base implementation for the ObjectStateFlow service. Represents a row in the &quot;ObjectStateFlow&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ObjectStateFlowImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectStateFlowImpl
 * @see ObjectStateFlow
 * @generated
 */
public abstract class ObjectStateFlowBaseImpl
	extends ObjectStateFlowModelImpl implements ObjectStateFlow {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a object state flow model instance should use the <code>ObjectStateFlow</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ObjectStateFlowLocalServiceUtil.addObjectStateFlow(this);
		}
		else {
			ObjectStateFlowLocalServiceUtil.updateObjectStateFlow(this);
		}
	}

}