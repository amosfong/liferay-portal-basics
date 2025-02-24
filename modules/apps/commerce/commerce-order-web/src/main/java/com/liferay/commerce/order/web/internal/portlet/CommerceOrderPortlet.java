/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.portlet;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.order.web.internal.display.context.CommerceOrderListDisplayContext;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-order",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Orders", "javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_ORDER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class CommerceOrderPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String mvcRenderCommandName = ParamUtil.getString(
				renderRequest, "mvcRenderCommandName");

			if (Validator.isNull(mvcRenderCommandName)) {
				CommerceOrderListDisplayContext
					commerceOrderListDisplayContext =
						new CommerceOrderListDisplayContext(
							_commerceOrderNoteService, renderRequest);

				renderRequest.setAttribute(
					WebKeys.PORTLET_DISPLAY_CONTEXT,
					commerceOrderListDisplayContext);
			}

			super.render(renderRequest, renderResponse);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

}