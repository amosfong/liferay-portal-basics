/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.web.internal.portlet.action;

import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.spi.display.CTDisplayRendererRegistry;
import com.liferay.change.tracking.web.internal.constants.CTWebKeys;
import com.liferay.change.tracking.web.internal.display.context.ViewRelatedEntriesDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Noor Najjar
 */
@Component(
	property = {
		"javax.portlet.name=" + CTPortletKeys.PUBLICATIONS,
		"mvc.command.name=/change_tracking/view_move_changes"
	},
	service = MVCRenderCommand.class
)
public class ViewMoveChangesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		ViewRelatedEntriesDisplayContext viewRelatedEntriesDisplayContext =
			new ViewRelatedEntriesDisplayContext(
				_ctCollectionLocalService, _ctDisplayRendererRegistry,
				_portal.getHttpServletRequest(renderRequest), renderRequest,
				renderResponse, _userLocalService);

		renderRequest.setAttribute(
			CTWebKeys.VIEW_RELATED_ENTRIES_DISPLAY_CONTEXT,
			viewRelatedEntriesDisplayContext);

		return "/publications/view_move_changes.jsp";
	}

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTDisplayRendererRegistry _ctDisplayRendererRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}