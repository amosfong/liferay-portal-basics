/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.controller;

import com.liferay.exportimport.kernel.controller.ImportController;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.xml.Element;

/**
 * @author Matthew Tambara
 */
public interface PortletImportController extends ImportController {

	public void deletePortletData(PortletDataContext portletDataContext)
		throws Exception;

	public void importPortletData(
			PortletDataContext portletDataContext, Element portletDataElement)
		throws Exception;

	public void importPortletPreferences(
			PortletDataContext portletDataContext, long companyId, long groupId,
			Layout layout, Element parentElement, boolean preserveScopeLayoutId,
			boolean importPortletArchivedSetups, boolean importPortletData,
			boolean importPortletSetup, boolean importPortletUserPreferences)
		throws Exception;

	public void importServicePortletPreferences(
			PortletDataContext portletDataContext, Element serviceElement)
		throws PortalException;

	public void readExpandoTables(PortletDataContext portletDataContext)
		throws Exception;

	public void readLocks(PortletDataContext portletDataContext)
		throws Exception;

	public void resetPortletScope(
		PortletDataContext portletDataContext, long groupId);

}