/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.form.navigator.internal.configuration;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alejandro Tardín
 */
public class RetrieverWhenThereAreSeveralConfigurationsTest
	extends BaseFormNavigatorEntryConfigurationRetrieverTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		createConfiguration(
			"form1",
			new String[] {
				"add.general=formNavigatorEntryKey1,formNavigatorEntryKey2," +
					"formNavigatorEntryKey3"
			});
		createConfiguration(
			"form1",
			new String[] {
				"update.general=formNavigatorEntryKey1," +
					"formNavigatorEntryKey4,formNavigatorEntryKey5"
			});
	}

	@Test
	public void testContainsValuesForEntry1() {
		List<String> formNavigatorEntryKeys =
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "general", "add");

		Assert.assertEquals(
			formNavigatorEntryKeys.toString(), 3,
			formNavigatorEntryKeys.size());

		Iterator<String> iterator = formNavigatorEntryKeys.iterator();

		Assert.assertEquals("formNavigatorEntryKey1", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey2", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey3", iterator.next());
	}

	@Test
	public void testContainsValuesForEntry2() {
		List<String> formNavigatorEntryKeys =
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "general", "update");

		Assert.assertEquals(
			formNavigatorEntryKeys.toString(), 3,
			formNavigatorEntryKeys.size());

		Iterator<String> iterator = formNavigatorEntryKeys.iterator();

		Assert.assertEquals("formNavigatorEntryKey1", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey4", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey5", iterator.next());
	}

	@Test
	public void testReturnsEmptyOptionalForAnUnknownCategory() {
		Assert.assertNull(
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "unknownCategory", "add"));
	}

	@Test
	public void testReturnsEmptyOptionalForAnUnknownContext() {
		Assert.assertNull(
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "general", "unknownContext"));
	}

	@Test
	public void testReturnsEmptyOptionalForAnUnknownFormId() {
		Assert.assertNull(
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"unknownForm", "general", "add"));
	}

}