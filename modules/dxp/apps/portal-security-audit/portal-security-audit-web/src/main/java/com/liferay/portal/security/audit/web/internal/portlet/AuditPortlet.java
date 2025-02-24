/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.audit.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.audit.AuditEvent;
import com.liferay.portal.security.audit.web.internal.AuditEventManagerUtil;
import com.liferay.portal.security.audit.web.internal.constants.AuditPortletKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Greenwald
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.icon=/icons/audit.png",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Audit", "javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AuditPortletKeys.AUDIT,
		"javax.portlet.portlet-info.short-title=Audit",
		"javax.portlet.portlet-info.title=Audit",
		"javax.portlet.portlet-mode=text/html;view",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class AuditPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		_checkCompanyAdmin(actionRequest);

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		_checkCompanyAdmin(renderRequest);

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		_checkCompanyAdmin(resourceRequest);

		super.serveResource(resourceRequest, resourceResponse);
	}

	private void _checkCompanyAdmin(PortletRequest portletRequest)
		throws PortletException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		long auditEventId = ParamUtil.getLong(portletRequest, "auditEventId");

		if (auditEventId > 0) {
			AuditEvent auditEvent = AuditEventManagerUtil.fetchAuditEvent(
				auditEventId);

			if ((auditEvent != null) &&
				(permissionChecker.getCompanyId() !=
					auditEvent.getCompanyId())) {

				PrincipalException principalException =
					new PrincipalException.MustBeCompanyAdmin(
						permissionChecker.getUserId());

				throw new PortletException(principalException);
			}
		}

		if (!permissionChecker.isCompanyAdmin()) {
			PrincipalException principalException =
				new PrincipalException.MustBeCompanyAdmin(
					permissionChecker.getUserId());

			throw new PortletException(principalException);
		}
	}

}