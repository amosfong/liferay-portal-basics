/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.web.internal.portlet.action;

import com.liferay.exportimport.constants.ExportImportPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(
	property = {
		"javax.portlet.name=" + ExportImportPortletKeys.EXPORT,
		"mvc.command.name=/export_import/export_layouts"
	},
	service = MVCRenderCommand.class
)
public class ExportLayoutsMVCRenderCommand extends BaseGroupMVCRenderCommand {

	@Override
	protected String getPath() {
		return "/export/new_export/export_layouts.jsp";
	}

}