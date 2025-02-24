/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.manager;

import javax.portlet.ActionRequest;

/**
 * @author Danny Situ
 */
public interface ContactInfoManager<T> {

	public void delete(long primaryKey) throws Exception;

	public void edit(ActionRequest actionRequest) throws Exception;

	public void makePrimary(long primaryKey) throws Exception;

}