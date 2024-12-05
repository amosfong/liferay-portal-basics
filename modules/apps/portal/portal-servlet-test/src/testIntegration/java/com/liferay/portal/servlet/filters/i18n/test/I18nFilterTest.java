/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.i18n.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TreeMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PropsValues;

import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Manuel de la Peña
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class I18nFilterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testEnglishUserEnglishSessionEnglishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.US, LocaleUtil.US, null, null));
	}

	@Test
	public void testEnglishUserEnglishSessionSpanishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.US, LocaleUtil.SPAIN, null, null));
	}

	@Test
	public void testEnglishUserEnglishSessionWithoutCookieVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.US, null, null, null));
	}

	@Test
	public void testEnglishUserSpanishCookieSpanishVirtualHostWithoutSessionPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, LocaleUtil.ENGLISH, null, LocaleUtil.SPAIN, LocaleUtil.SPAIN,
				null));
	}

	@Test
	public void testEnglishUserSpanishSessionEnglishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.SPAIN, LocaleUtil.US, null, null));
	}

	@Test
	public void testEnglishUserSpanishSessionSpanishCookieSpanishVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, LocaleUtil.ENGLISH, LocaleUtil.SPAIN, LocaleUtil.SPAIN,
				LocaleUtil.SPAIN, null));
	}

	@Test
	public void testEnglishUserSpanishSessionSpanishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.SPAIN, LocaleUtil.SPAIN, null,
				null));
	}

	@Test
	public void testEnglishUserSpanishSessionWithoutCookieVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, LocaleUtil.US, LocaleUtil.SPAIN, null, null, null));
	}

	@Test
	public void testGuestEnglishPreferredWithoutSessionCookieVirtualHostAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, null, null, null, null, LocaleUtil.ENGLISH));
		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, null, null, null, null, LocaleUtil.US));
	}

	@Test
	public void testGuestEnglishSessionEnglishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.US, LocaleUtil.US, null, null));
	}

	@Test
	public void testGuestEnglishSessionSpanishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.US, LocaleUtil.SPAIN, null, null));
	}

	@Test
	public void testGuestEnglishSessionWithoutCookieVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertNull(
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.US, null, null, null));
	}

	@Test
	public void testGuestSpanishCookieSpanishVirtualHostWithoutSessionPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, null, LocaleUtil.SPAIN, LocaleUtil.SPAIN, null));
	}

	@Test
	public void testGuestSpanishPreferredWithoutSessionCookieVirtualHostAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, null, null, null, LocaleUtil.SPAIN));

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, null, null, null, new Locale("es")));
	}

	@Test
	public void testGuestSpanishSessionEnglishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.SPAIN, LocaleUtil.US, null, null));
	}

	@Test
	public void testGuestSpanishSessionSpanishCookieSpanishVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.SPAIN, LocaleUtil.SPAIN, LocaleUtil.SPAIN,
				null));
	}

	@Test
	public void testGuestSpanishSessionSpanishCookieWithoutVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.SPAIN, LocaleUtil.SPAIN, null, null));
	}

	@Test
	public void testGuestSpanishSessionWithoutCookieVirtualHostPreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, LocaleUtil.SPAIN, null, null, null));
	}

	@Test
	public void testGuestSpanishVirtualHostWithoutSessionCookiePreferredAlgorithm3()
		throws Exception {

		Assert.assertEquals(
			LocaleUtil.toLanguageId(LocaleUtil.SPAIN),
			_getPrependI18nLanguageId(
				3, null, null, null, LocaleUtil.SPAIN, null));
	}

	private String _getPrependI18nLanguageId(
			int localePrependFriendlyURLStyle, Locale userLocale,
			Locale sessionLocale, Locale cookieLocale, Locale virtualHostLocale,
			Locale preferredLocale)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();
		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		if (virtualHostLocale != null) {
			String layoutHostname =
				RandomTestUtil.randomString(6) + "." +
					RandomTestUtil.randomString(3);

			LayoutSet layoutSet = _group.getPublicLayoutSet();

			_virtualHostLocalService.updateVirtualHosts(
				_group.getCompanyId(), layoutSet.getLayoutSetId(),
				TreeMapBuilder.put(
					StringUtil.toLowerCase(layoutHostname),
					LocaleUtil.toLanguageId(virtualHostLocale)
				).build());

			mockHttpServletRequest.addHeader("Host", layoutHostname);
			mockHttpServletRequest.setServerName(layoutHostname);

			PortalInstances.getCompanyId(mockHttpServletRequest);
		}

		if (sessionLocale != null) {
			HttpSession httpSession = mockHttpServletRequest.getSession();

			httpSession.setAttribute(WebKeys.LOCALE, sessionLocale);
		}

		if (userLocale != null) {
			_user = UserTestUtil.addUser(
				null, userLocale, RandomTestUtil.randomString(),
				RandomTestUtil.randomString(),
				new long[] {_group.getGroupId()});

			mockHttpServletRequest.setAttribute(WebKeys.USER, _user);
		}

		if (cookieLocale != null) {
			_language.updateCookie(
				mockHttpServletRequest, mockHttpServletResponse, cookieLocale);

			// Passing cookies from mock HTTP servlet response to mock HTTP
			// servlet request

			mockHttpServletRequest.setCookies(
				mockHttpServletResponse.getCookies());
		}

		boolean localeDefaultRequest = false;

		if (preferredLocale != null) {
			localeDefaultRequest = true;

			mockHttpServletRequest.setPreferredLocales(
				ListUtil.fromArray(preferredLocale));
		}

		try (AutoCloseable autoCloseable =
				ReflectionTestUtil.setFieldValueWithAutoCloseable(
					PropsValues.class, "LOCALE_DEFAULT_REQUEST",
					localeDefaultRequest)) {

			Assert.assertTrue(
				ReflectionTestUtil.invoke(
					_i18nFilter, "isFilterEnabled",
					new Class<?>[] {
						HttpServletRequest.class, HttpServletResponse.class
					},
					mockHttpServletRequest, mockHttpServletResponse));

			return ReflectionTestUtil.invoke(
				_i18nFilter, "prependI18nLanguageId",
				new Class<?>[] {HttpServletRequest.class, int.class},
				mockHttpServletRequest, localePrependFriendlyURLStyle);
		}
	}

	@DeleteAfterTestRun
	private Group _group;

	@Inject(filter = "servlet-filter-name=I18n Filter")
	private Filter _i18nFilter;

	@Inject
	private Language _language;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private VirtualHostLocalService _virtualHostLocalService;

}