/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auto.login.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.action.UpdatePasswordAction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.Action;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Alvaro Saugar
 */
@RunWith(Arquillian.class)
public class UpdatePasswordActionTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testExecute() throws Exception {
		Action action = new UpdatePasswordAction();

		User user = UserTestUtil.addUser();

		user.setLastLoginDate(null);

		user = UserLocalServiceUtil.updateUser(user);

		Company company = CompanyLocalServiceUtil.getCompany(
			user.getCompanyId());

		MockHttpServletRequest mockHttpServletRequest = _mockHttpServletRequest(
			company, user);

		action.execute(
			null, mockHttpServletRequest, new MockHttpServletResponse());

		user = UserLocalServiceUtil.getUserByEmailAddress(
			company.getCompanyId(), user.getEmailAddress());

		Assert.assertNotNull(user.getLastLoginDate());
	}

	private MockHttpServletRequest _mockHttpServletRequest(
			Company company, User user)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(
				ServletContextPool.get(StringPool.BLANK), HttpMethods.POST,
				StringPool.SLASH);

		mockHttpServletRequest.addParameter(Constants.CMD, "update");

		String password = RandomTestUtil.randomString();

		mockHttpServletRequest.addParameter("password1", password);
		mockHttpServletRequest.addParameter("password2", password);

		mockHttpServletRequest.addParameter("p_auth", "test");

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(company);

		Layout layout = LayoutLocalServiceUtil.getLayout(1);

		themeDisplay.setLayout(layout);
		themeDisplay.setLayoutSet(layout.getLayoutSet());

		Group group = layout.getGroup();

		themeDisplay.setSiteGroupId(group.getGroupId());

		themeDisplay.setUser(user);

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		mockHttpServletRequest.setMethod("POST");

		Date expirationDate = new Date(System.currentTimeMillis() + 3600000);

		Ticket ticket = _ticketLocalService.addDistinctTicket(
			user.getCompanyId(), User.class.getName(), user.getUserId(),
			TicketConstants.TYPE_PASSWORD, null, expirationDate,
			new ServiceContext());

		mockHttpServletRequest.setParameter(
			"ticketId", String.valueOf(ticket.getTicketId()));
		mockHttpServletRequest.setParameter("ticketKey", ticket.getKey());

		ticket.setKey(PasswordEncryptorUtil.encrypt(ticket.getKey()));

		_ticketLocalService.updateTicket(ticket);

		HttpSession httpSession = mockHttpServletRequest.getSession();

		httpSession.setAttribute(
			"LIFERAY_SHARED_AUTHENTICATION_TOKEN#CSRF", "test");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			mockHttpServletRequest);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		return mockHttpServletRequest;
	}

	@Inject
	private TicketLocalService _ticketLocalService;

}