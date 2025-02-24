/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.model.impl;

import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil;

/**
 * The extended model base implementation for the KaleoProcessLink service. Represents a row in the &quot;KaleoProcessLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoProcessLinkImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkImpl
 * @see KaleoProcessLink
 * @generated
 */
public abstract class KaleoProcessLinkBaseImpl
	extends KaleoProcessLinkModelImpl implements KaleoProcessLink {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo process link model instance should use the <code>KaleoProcessLink</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoProcessLinkLocalServiceUtil.addKaleoProcessLink(this);
		}
		else {
			KaleoProcessLinkLocalServiceUtil.updateKaleoProcessLink(this);
		}
	}

}