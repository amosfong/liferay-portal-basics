/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.util;

import com.liferay.configuration.admin.web.internal.model.ConfigurationModel;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.definitions.ExtendedAttributeDefinition;
import com.liferay.portal.configuration.metatype.definitions.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.language.LanguageImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Vector;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.Configuration;

/**
 * @author Marcellus Tavares
 */
public class ConfigurationModelToDDMFormValuesConverterTest extends Mockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() {
		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		Mockito.when(
			FrameworkUtil.getBundle(Mockito.any())
		).thenReturn(
			bundleContext.getBundle()
		);
	}

	@AfterClass
	public static void tearDownClass() {
		_frameworkUtilMockedStatic.close();
	}

	@Before
	public void setUp() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(new LanguageImpl());
	}

	@Test
	public void testGetValuesByConfigurationAndNegativeCardinalityWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, -2);
		_whenGetDefaultValue(extendedAttributeDefinition, null);
		whenGetID(extendedAttributeDefinition, "Text");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		Vector<String> vector = new Vector<>();

		vector.add("Joe Bloggs");
		vector.add("Ella Fitzgerald");

		properties.put("Text", vector);

		_whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, configuration, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", _getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", _getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void testGetValuesByConfigurationAndPositiveCardinalityWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 2);
		_whenGetDefaultValue(extendedAttributeDefinition, null);
		whenGetID(extendedAttributeDefinition, "Text");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("Text", new String[] {"Joe Bloggs", "Ella Fitzgerald"});

		_whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, configuration, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", _getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", _getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void testGetValuesByConfigurationWithCheckboxField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		whenGetID(extendedAttributeDefinition, "Boolean");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("Boolean", Boolean.TRUE);

		_whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, configuration, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals("true", _getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByConfigurationWithPresentKey() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		_whenGetDefaultValue(
			extendedAttributeDefinition, new String[] {"9999"});
		whenGetID(extendedAttributeDefinition, "Long");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		_whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, configuration, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals(
			"It should return the default value when no key is set", "9999",
			_getValueString(ddmFormFieldValues.get(0)));

		properties.put("Long", 0L);

		ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals(
			"It should return the configuration value if they key is set", "0",
			_getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithCheckboxField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		whenGetID(extendedAttributeDefinition, "Boolean");

		_whenGetDefaultValue(
			extendedAttributeDefinition, new String[] {"false"});

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, null, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals(
			"false", _getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithSelectField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		_whenGetDefaultValue(
			extendedAttributeDefinition, new String[] {"REQUEST_HEADER"});
		whenGetID(extendedAttributeDefinition, "Select");
		whenGetOptionLabels(
			extendedAttributeDefinition,
			new String[] {"COOKIE", "REQUEST_HEADER"});
		whenGetOptionValues(
			extendedAttributeDefinition,
			new String[] {"COOKIE", "REQUEST_HEADER"});

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, null, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals(
			"[\"REQUEST_HEADER\"]", _getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 2);
		_whenGetDefaultValue(
			extendedAttributeDefinition,
			new String[] {"Joe Bloggs|Ella Fitzgerald"});
		whenGetID(extendedAttributeDefinition, "Text");

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, null, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", _getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", _getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void testGetValuesByEmptyDefaultValueWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		_whenGetDefaultValue(extendedAttributeDefinition, null);
		whenGetID(extendedAttributeDefinition, "Text");

		ConfigurationModel configurationModel = new ConfigurationModel(
			null, null, null, extendedObjectClassDefinition, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(
			ddmFormFieldValues.toString(), 1, ddmFormFieldValues.size());
		Assert.assertEquals(
			StringPool.BLANK, _getValueString(ddmFormFieldValues.get(0)));
	}

	protected DDMForm getDDMForm(ConfigurationModel configurationModel) {
		ConfigurationModelToDDMFormConverter
			configurationModelToDDMFormConverter = spy(
				new ConfigurationModelToDDMFormConverter(
					configurationModel, _enLocale, new EmptyResourceBundle()));

		Mockito.doReturn(
			null
		).when(
			configurationModelToDDMFormConverter
		).getConfigurationDDMForm();

		return configurationModelToDDMFormConverter.getDDMForm();
	}

	protected DDMFormValues getDDMFormValues(
		ConfigurationModel configurationModel, DDMForm ddmForm) {

		ConfigurationModelToDDMFormValuesConverter
			configurationModelToDDMFormValuesConverter =
				new ConfigurationModelToDDMFormValuesConverter(
					configurationModel, ddmForm, _enLocale);

		return configurationModelToDDMFormValuesConverter.getDDMFormValues();
	}

	protected void whenGetAttributeDefinitions(
		ExtendedObjectClassDefinition objectClassDefinition,
		ExtendedAttributeDefinition[] extendedAttributeDefinitions) {

		when(
			objectClassDefinition.getAttributeDefinitions(Mockito.anyInt())
		).thenReturn(
			extendedAttributeDefinitions
		);
	}

	protected void whenGetCardinality(
		ExtendedAttributeDefinition extendedAttributeDefinition,
		int cardinality) {

		when(
			extendedAttributeDefinition.getCardinality()
		).thenReturn(
			cardinality
		);
	}

	protected void whenGetID(
		ExtendedAttributeDefinition extendedAttributeDefinition, String id) {

		when(
			extendedAttributeDefinition.getID()
		).thenReturn(
			id
		);
	}

	protected void whenGetOptionLabels(
		ExtendedAttributeDefinition extendedAttributeDefinition,
		String[] optionLabels) {

		when(
			extendedAttributeDefinition.getOptionLabels()
		).thenReturn(
			optionLabels
		);
	}

	protected void whenGetOptionValues(
		ExtendedAttributeDefinition extendedAttributeDefinition,
		String[] optionValues) {

		when(
			extendedAttributeDefinition.getOptionValues()
		).thenReturn(
			optionValues
		);
	}

	private String _getValueString(DDMFormFieldValue ddmFormFieldValue) {
		Value value = ddmFormFieldValue.getValue();

		return value.getString(_enLocale);
	}

	private void _whenGetDefaultValue(
		ExtendedAttributeDefinition extendedAttributeDefinition,
		String[] defaultValue) {

		when(
			extendedAttributeDefinition.getDefaultValue()
		).thenReturn(
			defaultValue
		);
	}

	private void _whenGetProperties(
		Configuration configuration, Dictionary<String, Object> properties) {

		when(
			configuration.getProperties()
		).thenReturn(
			properties
		);
	}

	private static final MockedStatic<FrameworkUtil>
		_frameworkUtilMockedStatic = Mockito.mockStatic(FrameworkUtil.class);

	private final Locale _enLocale = LocaleUtil.US;

	private static class EmptyResourceBundle extends ListResourceBundle {

		@Override
		protected Object[][] getContents() {
			return new Object[0][];
		}

	}

}