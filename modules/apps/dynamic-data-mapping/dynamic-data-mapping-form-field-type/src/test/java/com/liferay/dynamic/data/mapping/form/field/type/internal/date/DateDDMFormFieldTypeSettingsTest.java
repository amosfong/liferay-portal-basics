/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.date;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.test.util.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.test.util.DDMFormLayoutTestUtil;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.dynamic.data.mapping.util.DDMFormLayoutFactory;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Bruno Basto
 */
public class DateDDMFormFieldTypeSettingsTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	@Override
	public void setUp() {
		setUpJSONFactoryUtil();
		setUpLanguageUtil();
		setUpPortalUtil();
		setUpResourceBundleUtil();
	}

	@Test
	public void testCreateDateDDMFormFieldTypeSettingsDDMForm() {
		DDMForm ddmForm = DDMFormFactory.create(
			DateDDMFormFieldTypeSettings.class);

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(false);

		DDMFormField htmlAutocompleteAttribute = ddmFormFieldsMap.get(
			"htmlAutocompleteAttribute");

		Assert.assertNotNull(htmlAutocompleteAttribute);
		Assert.assertNotNull(htmlAutocompleteAttribute.getLabel());
		Assert.assertNotNull(
			htmlAutocompleteAttribute.getProperty("invalidCharacters"));
		Assert.assertEquals("text", htmlAutocompleteAttribute.getType());
		Assert.assertTrue(htmlAutocompleteAttribute.isVisualProperty());

		DDMFormField predefinedValueDDMFormField = ddmFormFieldsMap.get(
			"predefinedValue");

		DDMFormField requiredErrorMessage = ddmFormFieldsMap.get(
			"requiredErrorMessage");

		Assert.assertNotNull(requiredErrorMessage);

		Assert.assertNotNull(predefinedValueDDMFormField);
		Assert.assertEquals(
			"string", predefinedValueDDMFormField.getDataType());
		Assert.assertEquals("date", predefinedValueDDMFormField.getType());
		Assert.assertTrue(predefinedValueDDMFormField.isLocalizable());

		DDMFormField validationDDMFormField = ddmFormFieldsMap.get(
			"validation");

		Assert.assertNotNull(validationDDMFormField);
		Assert.assertEquals("date", validationDDMFormField.getDataType());
		Assert.assertEquals("validation", validationDDMFormField.getType());

		DDMFormField indexTypeDDMFormField = ddmFormFieldsMap.get("indexType");

		Assert.assertNotNull(indexTypeDDMFormField);
		Assert.assertNotNull(indexTypeDDMFormField.getLabel());
		Assert.assertEquals("radio", indexTypeDDMFormField.getType());

		List<DDMFormRule> ddmFormRules = ddmForm.getDDMFormRules();

		Assert.assertEquals(ddmFormRules.toString(), 2, ddmFormRules.size());

		DDMFormRule ddmFormRule0 = ddmFormRules.get(0);

		Assert.assertEquals(
			"hasObjectField(getValue('objectFieldName'))",
			ddmFormRule0.getCondition());

		List<String> actions = ddmFormRule0.getActions();

		Assert.assertEquals(actions.toString(), 1, actions.size());
		Assert.assertEquals(
			"setValue('required', isRequiredObjectField(getValue(" +
				"'objectFieldName')))",
			actions.get(0));

		DDMFormRule ddmFormRule1 = ddmFormRules.get(1);

		Assert.assertEquals("TRUE", ddmFormRule1.getCondition());

		actions = ddmFormRule1.getActions();

		Assert.assertEquals(actions.toString(), 3, actions.size());

		Assert.assertEquals(
			"setEnabled('required', not(hasObjectField(" +
				"getValue('objectFieldName'))))",
			actions.get(0));
		Assert.assertEquals("setVisible('dataType', false)", actions.get(1));
		Assert.assertEquals(
			"setVisible('requiredErrorMessage', getValue('required'))",
			actions.get(2));
	}

	@Test
	public void testCreateDateDDMFormFieldTypeSettingsDDMFormLayout() {
		assertDDMFormLayout(
			DDMFormLayoutFactory.create(DateDDMFormFieldTypeSettings.class),
			DDMFormLayoutTestUtil.createDDMFormLayout(
				DDMFormLayout.TABBED_MODE,
				DDMFormLayoutTestUtil.createDDMFormLayoutPage(
					"label", "tip", "required", "requiredErrorMessage"),
				DDMFormLayoutTestUtil.createDDMFormLayoutPage(
					"fieldReference", "name", "htmlAutocompleteAttribute",
					"predefinedValue", "objectFieldName",
					"visibilityExpression", "fieldNamespace", "indexType",
					"labelAtStructureLevel", "localizable", "nativeField",
					"readOnly", "dataType", "type", "showLabel", "repeatable",
					"validation")));
	}

	@Override
	protected void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Override
	protected void setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(Mockito.mock(Language.class));
	}

}