/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.web.internal.portlet.action.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.multi.factor.authentication.spi.checker.headless.HeadlessMFAChecker;
import com.liferay.portal.configuration.test.util.CompanyConfigurationTemporarySwapper;
import com.liferay.portal.kernel.cookies.constants.CookiesConstants;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayActionRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.ActionRequestFactory;

import java.net.URLDecoder;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Alvaro Saugar
 */
@RunWith(Arquillian.class)
public class LoginMVCActionCommandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testResetPasswordValueDoesNotChangeWhenItIsTrue()
		throws Exception {

		_group = GroupTestUtil.addGroup();

		_company = _companyLocalService.getCompany(_group.getCompanyId());

		User user1 = UserTestUtil.addUser(_company);

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(
				user1.getGroupId(), user1.getUserId()));

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper =
					new CompanyConfigurationTemporarySwapper(
						_company.getCompanyId(),
						"com.liferay.multi.factor.authentication.email.otp." +
							"configuration.MFAEmailOTPConfiguration",
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", true
						).build())) {

			String password = StringUtil.toLowerCase(
				RandomTestUtil.randomString());

			user1 = _userLocalService.updatePassword(
				user1.getUserId(), password, password, true, false);

			Bundle bundle = FrameworkUtil.getBundle(
				LoginMVCActionCommandTest.class);

			BundleContext bundleContext = bundle.getBundleContext();

			bundleContext.registerService(
				HeadlessMFAChecker.class, new FalseHeadlessMFAChecker(),
				MapUtil.singletonDictionary(
					"companyId", _company.getCompanyId()));

			MockLiferayPortletActionRequest mockLiferayPortletActionRequest1 =
				_getMockLiferayPortletActionRequest(user1, password);

			_mvcActionCommand.processAction(
				mockLiferayPortletActionRequest1,
				new MockLiferayPortletActionResponse());

			User user2 = _userLocalService.getUser(user1.getUserId());

			Assert.assertEquals(
				user1.isPasswordReset(), user2.isPasswordReset());
			Assert.assertTrue(user2.isPasswordReset());

			bundleContext.registerService(
				HeadlessMFAChecker.class, new TrueHeadlessMFAChecker(),
				MapUtil.singletonDictionary(
					"companyId", _company.getCompanyId()));

			HttpServletRequest httpServletRequest1 =
				_portal.getOriginalServletRequest(
					_portal.getHttpServletRequest(
						mockLiferayPortletActionRequest1));

			httpServletRequest1 = _portal.getOriginalServletRequest(
				httpServletRequest1);

			HttpSession httpSession1 = httpServletRequest1.getSession();

			String digest = (String)httpSession1.getAttribute("MFA_WEB_DIGEST");
			String key = (String)httpSession1.getAttribute("MFA_WEB_KEY");

			MockLiferayPortletActionRequest mockLiferayPortletActionRequest2 =
				_getMockLiferayPortletActionRequest(
					_getState(
						(String)mockLiferayPortletActionRequest1.getAttribute(
							"REDIRECT")));

			HttpServletRequest httpServletRequest2 =
				_portal.getOriginalServletRequest(
					_portal.getHttpServletRequest(
						mockLiferayPortletActionRequest2));

			HttpSession httpSession2 = httpServletRequest2.getSession();

			httpSession2.setAttribute("MFA_WEB_DIGEST", digest);
			httpSession2.setAttribute("MFA_WEB_KEY", key);

			LiferayActionRequest liferayActionRequest =
				ActionRequestFactory.create(
					httpServletRequest2,
					_portletLocalService.getPortletById(LoginPortletKeys.LOGIN),
					null, null, null, null, null, TestPropsValues.getPlid());

			liferayActionRequest.setPortletRequestDispatcherRequest(
				httpServletRequest2);

			_mvcActionCommand.processAction(
				liferayActionRequest, new MockLiferayPortletActionResponse());

			user2 = _userLocalService.getUser(user1.getUserId());

			Assert.assertEquals(
				user1.isPasswordReset(), user2.isPasswordReset());
			Assert.assertTrue(user2.isPasswordReset());
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	private void _addCookieSupportCookie(
		MockHttpServletRequest mockHttpServletRequest) {

		mockHttpServletRequest.setCookies(
			new Cookie(CookiesConstants.NAME_COOKIE_SUPPORT, "true"));
	}

	private LiferayPortletConfig _getLiferayPortletConfig() {
		Portlet portlet = _portletLocalService.getPortletById(
			LoginPortletKeys.LOGIN);

		return (LiferayPortletConfig)PortletConfigFactoryUtil.create(
			portlet, null);
	}

	private MockLiferayPortletActionRequest _getMockLiferayPortletActionRequest(
			String state)
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			new MockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, _getLiferayPortletConfig());
		mockLiferayPortletActionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE,
			new MockLiferayPortletActionResponse());
		mockLiferayPortletActionRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay());
		mockLiferayPortletActionRequest.setParameter("state", state);

		_addCookieSupportCookie(
			(MockHttpServletRequest)
				mockLiferayPortletActionRequest.getHttpServletRequest());

		return mockLiferayPortletActionRequest;
	}

	private MockLiferayPortletActionRequest _getMockLiferayPortletActionRequest(
			User user, String password)
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			new MockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, _getLiferayPortletConfig());
		mockLiferayPortletActionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE,
			new MockLiferayPortletActionResponse());
		mockLiferayPortletActionRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay());
		mockLiferayPortletActionRequest.setParameter(
			"login", user.getEmailAddress());
		mockLiferayPortletActionRequest.setParameter("password", password);

		_addCookieSupportCookie(
			(MockHttpServletRequest)
				mockLiferayPortletActionRequest.getHttpServletRequest());

		HttpServletRequest httpServletRequest =
			_portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(mockLiferayPortletActionRequest));

		httpServletRequest = _portal.getOriginalServletRequest(
			httpServletRequest);

		HttpSession httpSession = httpServletRequest.getSession();

		httpSession.setAttribute("MFA_WEB_KEY", RandomTestUtil.randomString());

		return mockLiferayPortletActionRequest;
	}

	private String _getState(String redirect) throws Exception {
		redirect = URLDecoder.decode(redirect, StandardCharsets.UTF_8.name());

		int index = StringUtil.lastIndexOfAny(
			redirect, new String[] {"state="});

		return redirect.substring(index + 6);
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(_company);
		themeDisplay.setLayout(LayoutTestUtil.addTypeContentLayout(_group));

		LayoutSet layoutSet = _group.getPublicLayoutSet();

		themeDisplay.setLookAndFeel(layoutSet.getTheme(), null);

		themeDisplay.setPermissionChecker(
			PermissionThreadLocal.getPermissionChecker());
		themeDisplay.setRealUser(TestPropsValues.getUser());
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject(filter = "mvc.command.name=/login/login")
	private MVCActionCommand _mvcActionCommand;

	@Inject
	private Portal _portal;

	@Inject
	private PortletLocalService _portletLocalService;

	@Inject
	private UserLocalService _userLocalService;

	private class FalseHeadlessMFAChecker implements HeadlessMFAChecker {

		@Override
		public boolean verifyHeadlessRequest(
			HttpServletRequest httpServletRequest, long userId) {

			return false;
		}

	}

	private class TrueHeadlessMFAChecker implements HeadlessMFAChecker {

		@Override
		public boolean verifyHeadlessRequest(
			HttpServletRequest httpServletRequest, long userId) {

			return true;
		}

	}

}