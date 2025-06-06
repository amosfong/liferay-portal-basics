/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import java.util.List;

import javax.portlet.Event;
import javax.portlet.StateAwareResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Neil Griffin
 */
@ProviderType
public interface LiferayStateAwareResponse
	extends LiferayPortletResponse, StateAwareResponse {

	public List<Event> getEvents();

	public String getRedirectLocation();

	public boolean isCalledSetRenderParameter();

}