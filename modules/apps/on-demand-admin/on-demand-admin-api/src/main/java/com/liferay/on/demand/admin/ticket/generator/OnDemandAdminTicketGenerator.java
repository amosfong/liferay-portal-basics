/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.on.demand.admin.ticket.generator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Pei-Jung Lan
 */
@ProviderType
public interface OnDemandAdminTicketGenerator {

	public Ticket generate(
			Company company, String justification, long requestorUserId)
		throws PortalException;

	public Ticket generate(
			Company company, String justification, User requestorUser)
		throws PortalException;

}