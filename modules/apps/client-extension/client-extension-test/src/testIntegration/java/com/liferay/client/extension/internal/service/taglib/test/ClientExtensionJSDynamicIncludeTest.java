/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.client.extension.internal.service.taglib.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.model.ClientExtensionEntry;
import com.liferay.client.extension.service.ClientExtensionEntryLocalService;
import com.liferay.client.extension.service.ClientExtensionEntryRelLocalService;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Víctor Galán
 */
@Ignore
@RunWith(Arquillian.class)
public class ClientExtensionJSDynamicIncludeTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testGlobalBottomJSClientExtensionEntriesAreAdded()
		throws Exception {

		_testGlobalJSClientExtensionEntriesAreAdded(
			_clientExtensionBottomDynamicInclude, "bottom");
	}

	@Test
	public void testGlobalTopJSClientExtensionEntriesAreAdded()
		throws Exception {

		_testGlobalJSClientExtensionEntriesAreAdded(
			_clientExtensionTopDynamicInclude, "head");
	}

	private ClientExtensionEntry _addGlobalJSClientExtension(String url)
		throws Exception {

		ClientExtensionEntry clientExtensionEntry =
			_clientExtensionEntryLocalService.addClientExtensionEntry(
				RandomTestUtil.randomString(), TestPropsValues.getUserId(),
				StringPool.BLANK,
				Collections.singletonMap(
					LocaleUtil.getDefault(), RandomTestUtil.randomString()),
				StringPool.BLANK, StringPool.BLANK,
				ClientExtensionEntryConstants.TYPE_GLOBAL_JS,
				UnicodePropertiesBuilder.create(
					true
				).put(
					"url", url
				).buildString());

		_clientExtensionEntries.add(clientExtensionEntry);

		return clientExtensionEntry;
	}

	private String _getExpected(
			String layoutGlobalJSURL, String layoutSetGlobalJSURL,
			String masterLayoutGlobalJSURL)
		throws Exception {

		Class<?> clazz = getClass();

		return StringUtil.replace(
			StringUtil.read(
				clazz.getClassLoader(),
				"com/liferay/client/extension/internal/service/taglib/test" +
					"/dependencies/global_js_client_extensions_expected.html"),
			"[$", "$]",
			HashMapBuilder.put(
				"LAYOUT_GLOBAL_JS_URL", layoutGlobalJSURL
			).put(
				"LAYOUT_SET_GLOBAL_JS_URL", layoutSetGlobalJSURL
			).put(
				"MASTER_LAYOUT_GLOBAL_JS_URL", masterLayoutGlobalJSURL
			).build());
	}

	private MockHttpServletRequest _getMockHttpServletRequest(Layout layout) {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setLayout(layout);

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		return mockHttpServletRequest;
	}

	private String _getRandomURL() {
		return "http://" + RandomTestUtil.randomString() + ".com";
	}

	private String _normalize(String value) {
		return value.replaceAll("[\n\t]", StringPool.BLANK);
	}

	private void _testGlobalJSClientExtensionEntriesAreAdded(
			DynamicInclude dynamicInclude, String scriptLocation)
		throws Exception {

		String layoutSetGlobalJSURL = _getRandomURL();

		ClientExtensionEntry layoutSetGlobalJSClientExtensionEntry =
			_addGlobalJSClientExtension(layoutSetGlobalJSURL);

		LayoutSet publicLayoutSet = _group.getPublicLayoutSet();

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.create(
				true
			).put(
				"scriptLocation", scriptLocation
			).build();

		_clientExtensionEntryRelLocalService.addClientExtensionEntryRel(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_portal.getClassNameId(LayoutSet.class),
			publicLayoutSet.getLayoutSetId(),
			layoutSetGlobalJSClientExtensionEntry.getExternalReferenceCode(),
			ClientExtensionEntryConstants.TYPE_GLOBAL_JS,
			typeSettingsUnicodeProperties.toString(),
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		String masterLayoutGlobalJSURL = _getRandomURL();

		ClientExtensionEntry masterLayoutGlobalJSClientExtensionEntry =
			_addGlobalJSClientExtension(masterLayoutGlobalJSURL);

		LayoutPageTemplateEntry masterLayoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				null, TestPropsValues.getUserId(), _group.getGroupId(), 0,
				RandomTestUtil.randomString(),
				LayoutPageTemplateEntryTypeConstants.MASTER_LAYOUT, 0,
				WorkflowConstants.STATUS_APPROVED,
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		_clientExtensionEntryRelLocalService.addClientExtensionEntryRel(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_portal.getClassNameId(Layout.class),
			masterLayoutPageTemplateEntry.getPlid(),
			masterLayoutGlobalJSClientExtensionEntry.getExternalReferenceCode(),
			ClientExtensionEntryConstants.TYPE_GLOBAL_JS,
			typeSettingsUnicodeProperties.toString(),
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		String layoutGlobalJSURL = _getRandomURL();

		ClientExtensionEntry layoutGlobalJSClientExtensionEntry =
			_addGlobalJSClientExtension(layoutGlobalJSURL);

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		layout.setMasterLayoutPlid(masterLayoutPageTemplateEntry.getPlid());

		layout = _layoutLocalService.updateLayout(layout);

		_clientExtensionEntryRelLocalService.addClientExtensionEntryRel(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_portal.getClassNameId(Layout.class), layout.getPlid(),
			layoutGlobalJSClientExtensionEntry.getExternalReferenceCode(),
			ClientExtensionEntryConstants.TYPE_GLOBAL_JS,
			typeSettingsUnicodeProperties.toString(),
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		dynamicInclude.include(
			_getMockHttpServletRequest(layout), mockHttpServletResponse,
			StringPool.BLANK);

		Assert.assertEquals(
			_normalize(
				_getExpected(
					layoutGlobalJSURL, layoutSetGlobalJSURL,
					masterLayoutGlobalJSURL)),
			_normalize(mockHttpServletResponse.getContentAsString()));
	}

	@Inject(
		filter = "component.name=com.liferay.client.extension.internal.service.taglib.ClientExtensionBottomDynamicInclude"
	)
	private DynamicInclude _clientExtensionBottomDynamicInclude;

	@DeleteAfterTestRun
	private final List<ClientExtensionEntry> _clientExtensionEntries =
		new ArrayList<>();

	@Inject
	private ClientExtensionEntryLocalService _clientExtensionEntryLocalService;

	@Inject
	private ClientExtensionEntryRelLocalService
		_clientExtensionEntryRelLocalService;

	@Inject(
		filter = "component.name=com.liferay.client.extension.internal.service.taglib.ClientExtensionTopDynamicInclude"
	)
	private DynamicInclude _clientExtensionTopDynamicInclude;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Inject
	private Portal _portal;

}