/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class BaseSettingsTest {

	public BaseSettingsTest() {
		_baseSettings = new MemorySettings(_parentSettings);
	}

	@Test
	public void testGetModifiableSettingsForModifiableBaseSettings() {
		BaseSettings baseSettings = new MemorySettings();

		Assert.assertTrue(baseSettings instanceof ModifiableSettings);
		Assert.assertSame(baseSettings, baseSettings.getModifiableSettings());
	}

	@Test
	public void testGetModifiableSettingsForUnmodifiableBaseSettings() {
		ModifiableSettings modifiableSettings = new MemorySettings();

		BaseSettings baseSettings = new ParameterMapSettings(
			Collections.<String, String[]>emptyMap(), modifiableSettings);

		Assert.assertFalse(baseSettings instanceof ModifiableSettings);
		Assert.assertSame(
			modifiableSettings, baseSettings.getModifiableSettings());
	}

	@Test
	public void testGetParentSettings() {
		Assert.assertSame(_parentSettings, _baseSettings.getParentSettings());
	}

	@Test
	public void testGetValueReturnsDefaultWhenValueAndParentNotSet() {
		Assert.assertEquals(
			_DEFAULT_VALUE, _baseSettings.getValue(_KEY, _DEFAULT_VALUE));
		Assert.assertArrayEquals(
			_DEFAULT_VALUES, _baseSettings.getValues(_KEY, _DEFAULT_VALUES));

		_parentSettings.setValue(_KEY, _VALUE);

		Assert.assertEquals(
			_VALUE, _baseSettings.getValue(_KEY, _DEFAULT_VALUE));

		_parentSettings.setValues(_KEY, _VALUES);

		Assert.assertArrayEquals(
			_VALUES, _baseSettings.getValues(_KEY, _DEFAULT_VALUES));
	}

	private static final String _DEFAULT_VALUE = "defaultValue";

	private static final String[] _DEFAULT_VALUES = {
		"defaultValue0", "defaultValue1"
	};

	private static final String _KEY = "key";

	private static final String _VALUE = "value";

	private static final String[] _VALUES = {"value0", "value1"};

	private final BaseSettings _baseSettings;
	private final MemorySettings _parentSettings = new MemorySettings();

}