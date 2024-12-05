/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.login.web.internal.portlet;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Istvan Sajtos
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-login",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.icon=/icons/login.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.single-page-application=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Forgot Password",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.init-param.config-template=/forgot_password/configuration.jsp",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/forgot_password.jsp",
		"javax.portlet.name=" + LoginPortletKeys.FORGOT_PASSWORD,
		"javax.portlet.portlet-mode=text/html;config",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class ForgotPasswordPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!_isAllowedToRenderView(renderRequest)) {
			renderRequest.setAttribute(
				getMVCPathAttributeName(renderResponse.getNamespace()),
				"/login.jsp");
		}

		super.render(renderRequest, renderResponse);
	}

	private boolean _isAllowedToRenderView(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return true;
		}

		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		String mvcRenderCommandName = ParamUtil.getString(
			renderRequest, "mvcRenderCommandName");

		if ((Validator.isNull(mvcPath) &&
			 Validator.isNull(mvcRenderCommandName)) ||
			mvcPath.equals("/forgot_password.jsp") ||
			mvcRenderCommandName.equals("/login/forgot_password")) {

			return false;
		}

		return true;
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.login.web)(&(release.schema.version>=1.0.0)(!(release.schema.version>=2.0.0))))"
	)
	private Release _release;

}