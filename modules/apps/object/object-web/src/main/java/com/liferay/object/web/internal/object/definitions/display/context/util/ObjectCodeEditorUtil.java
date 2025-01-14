/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.web.internal.object.definitions.display.context.util;

import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Carolina Barbosa
 */
public class ObjectCodeEditorUtil {

	public static List<Map<String, Object>> getCodeEditorElements(
		boolean includeDDMExpressionBuilderElements,
		boolean includeGeneralVariables, Locale locale, long objectDefinitionId,
		Predicate<ObjectField> objectFieldPredicate) {

		if (includeDDMExpressionBuilderElements) {
			return getCodeEditorElements(
				ddmExpressionFunctionPredicate -> true,
				ddmExpressionOperatorPredicate -> true, includeGeneralVariables,
				locale, objectDefinitionId, objectFieldPredicate);
		}

		return getCodeEditorElements(
			ddmExpressionFunctionPredicate -> false,
			ddmExpressionOperatorPredicate -> false, includeGeneralVariables,
			locale, objectDefinitionId, objectFieldPredicate);
	}

	public static List<Map<String, Object>> getCodeEditorElements(
		Predicate<DDMExpressionFunction> ddmExpressionFunctionPredicate,
		Predicate<DDMExpressionOperator> ddmExpressionOperatorPredicate,
		boolean includeGeneralVariables, Locale locale, long objectDefinitionId,
		Predicate<ObjectField> objectFieldPredicate) {

		List<Map<String, Object>> codeEditorElements = new ArrayList<>();

		ObjectFieldLocalService objectFieldLocalService =
			_objectFieldLocalServiceSnapshot.get();

		codeEditorElements.add(
			_createCodeEditorElement(
				TransformUtil.transform(
					ListUtil.filter(
						objectFieldLocalService.getObjectFields(
							objectDefinitionId),
						objectFieldPredicate),
					objectField -> HashMapBuilder.put(
						"content", objectField.getName()
					).put(
						"helpText", StringPool.BLANK
					).put(
						"label", objectField.getLabel(locale)
					).build()),
				"fields", locale));

		if (includeGeneralVariables) {
			codeEditorElements.add(
				_createCodeEditorElement(
					Arrays.asList(
						HashMapBuilder.put(
							"content", "currentDate"
						).put(
							"helpText", StringPool.BLANK
						).put(
							"label", LanguageUtil.get(locale, "current-date")
						).build(),
						HashMapBuilder.put(
							"content", "currentUserId"
						).put(
							"helpText", StringPool.BLANK
						).put(
							"label", LanguageUtil.get(locale, "current-user")
						).build()),
					"general-variables", locale));
		}

		List<Map<String, String>> ddmExpressionOperators =
			DDMExpressionOperator.getItems(
				ddmExpressionOperatorPredicate, locale);

		if (ListUtil.isNotEmpty(ddmExpressionOperators)) {
			codeEditorElements.add(
				_createCodeEditorElement(
					ddmExpressionOperators, "operators", locale));
		}

		List<Map<String, String>> ddmExpressionFunctions =
			DDMExpressionFunction.getItems(
				ddmExpressionFunctionPredicate, locale);

		if (ListUtil.isNotEmpty(ddmExpressionFunctions)) {
			codeEditorElements.add(
				_createCodeEditorElement(
					ddmExpressionFunctions, "functions", locale));
		}

		return codeEditorElements;
	}

	public enum DDMExpressionFunction {

		ADD_DAYS(
			"addDays(field_name, parameter)",
			"calculates-the-result-of-adding-or-subtracting-a-specified-" +
				"number-of-days-from-a-given-date.-to-subtract,-prefix-the-" +
					"number-of-days-with-a-minus-sign",
			"add-days"),
		ADD_MONTHS(
			"addMonths(field_name, parameter)",
			"calculates-the-result-of-adding-or-subtracting-a-specified-" +
				"number-of-months-from-a-given-date.-to-subtract,-prefix-the-" +
					"number-of-months-with-a-minus-sign",
			"add-months"),
		ADD_YEARS(
			"addYears(field_name, parameter)",
			"calculates-the-result-of-adding-or-subtracting-a-specified-" +
				"number-of-years-from-a-given-date.-to-subtract,-prefix-the-" +
					"number-of-years-with-a-minus-sign",
			"add-years"),
		COMPARE_DATES(
			"compareDates(field_name, parameter)",
			"check-if-a-field-has-the-same-date-of-the-value", "compare-dates"),
		CONCAT(
			"concat(parameter1, parameter2, parameterN)",
			"combine-multiple-strings-or-text-fields-and-return-a-single-" +
				"string-that-can-be-used-with-other-validation-functions",
			"concat"),
		CONDITION(
			"condition(condition, parameter1, parameter2)",
			"provide-for-the-customer-the-possibility-of-condition-for-" +
				"values-or-fields-and-determines-if-expressions-are-true-or-" +
					"false",
			"condition"),
		CONTAINS(
			"contains(field_name, parameter)",
			"check-if-a-field-contains-a-specific-value-and-return-a-boolean",
			"contains"),
		DOES_NOT_CONTAIN(
			"NOT(contains(field_name, parameter))",
			"check-if-a-field-contains-a-specific-value-and-return-a-boolean-" +
				"if-the-field-does-contain-the-value-it-is-invalid",
			"does-not-contain"),
		FUTURE_DATES(
			"futureDates(field_name, parameter)",
			"check-if-a-date-fields-value-is-in-the-future-and-return-a-" +
				"boolean",
			"future-dates"),
		IS_A_URL(
			"isURL(field_name)",
			"check-if-a-text-field-is-a-URL-and-return-a-boolean", "is-a-url"),
		IS_AN_EMAIL(
			"isEmailAddress(field_name)",
			"check-if-a-text-field-is-an-email-and-return-a-boolean",
			"is-an-email"),
		IS_DECIMAL(
			"isDecimal(parameter)",
			"check-if-a-numeric-field-is-a-decimal-and-return-a-boolean",
			"is-decimal"),
		IS_EMPTY(
			"isEmpty(parameter)",
			"check-if-a-text-field-is-empty-and-return-a-boolean", "is-empty"),
		IS_EQUAL_TO(
			"field_name == parameter",
			"check-if-a-field-is-equal-to-a-specific-value-and-return-a-" +
				"boolean",
			"is-equal-to"),
		IS_GREATER_THAN(
			"field_name > parameter",
			"check-if-a-numeric-field-is-greater-than-a-specific-numeric-" +
				"value-and-return-a-boolean",
			"is-greater-than"),
		IS_GREATER_THAN_OR_EQUAL_TO(
			"field_name >= parameter",
			"check-if-a-numeric-field-is-greater-than-or-equal-to-a-specific-" +
				"numeric-value-and-return-a-boolean",
			"is-greater-than-or-equal-to"),
		IS_INTEGER(
			"isInteger(parameter)",
			"check-if-a-numeric-field-is-an-integer-and-return-a-boolean",
			"is-integer"),
		IS_LESS_THAN(
			"field_name < parameter",
			"check-if-a-numeric-field-is-less-than-a-specific-numeric-value-" +
				"and-return-a-boolean",
			"is-less-than"),
		IS_LESS_THAN_OR_EQUAL_TO(
			"field_name <= parameter",
			"check-if-a-numeric-field-is-less-than-or-equal-to-a-specific-" +
				"numeric-value-and-return-a-boolean",
			"is-less-than-or-equal-to"),
		IS_NOT_EQUAL_TO(
			"field_name != parameter",
			"check-if-a-field-is-not-equal-to-a-specific-value-and-return-a-" +
				"boolean",
			"is-not-equal-to"),
		MATCH(
			"match(field_name, parameter)",
			"check-if-a-text-field-matches-a-specific-string-value-or-regex-" +
				"expression-and-return-a-boolean",
			"match"),
		OLD_VALUE(
			"oldValue(\"field_name\")",
			"use-the-previous-value-of-a-field-before-its-update-to-create-" +
				"more-accurate-conditions",
			"old-value"),
		PAST_DATES(
			"pastDates(field_name, parameter)",
			"check-if-a-date-fields-value-is-in-the-past-and-return-a-boolean",
			"past-dates"),
		POW(
			"pow(field_name, parameter)",
			"raise-a-number-to-a-power-of-a-specified-number", "power"),
		RANGE(
			"futureDates(field_name, parameter) AND pastDates(" +
				"field_name, parameter)",
			"check-if-a-date-range-begins-with-a-past-date-and-ends-with-a-" +
				"future-date",
			"range"),
		SUM(
			"sum(parameter1, parameter2, parameterN)",
			"add-multiple-numeric-fields-together-and-return-a-single-number-" +
				"that-can-be-used-with-other-validation-functions",
			"sum");

		public static List<Map<String, String>> getItems(
			Predicate<DDMExpressionFunction> ddmExpressionFunctionPredicate,
			Locale locale) {

			List<Map<String, String>> values = new ArrayList<>();

			for (DDMExpressionFunction ddmExpressionFunction : values()) {
				if (StringUtil.equals(ddmExpressionFunction._key, "power")) {
					continue;
				}

				if ((ddmExpressionFunctionPredicate == null) ||
					ddmExpressionFunctionPredicate.test(
						ddmExpressionFunction)) {

					values.add(
						HashMapBuilder.put(
							"content", ddmExpressionFunction._content
						).put(
							"helpText",
							LanguageUtil.get(
								locale, ddmExpressionFunction._helpTextKey)
						).put(
							"label",
							LanguageUtil.get(locale, ddmExpressionFunction._key)
						).build());
				}
			}

			return values;
		}

		private DDMExpressionFunction(
			String content, String helpTextKey, String key) {

			_content = content;
			_helpTextKey = helpTextKey;
			_key = key;
		}

		private String _content;
		private String _helpTextKey;
		private String _key;

	}

	public enum DDMExpressionOperator {

		AND(
			"AND",
			"this-is-a-type-of-coordinating-conjunction-that-is-commonly-" +
				"used-to-indicate-a-dependent-relationship",
			"and"),
		DIVIDED_BY(
			"field_name1 / field_name2",
			"divide-one-numeric-field-by-another-to-create-an-expression",
			"divided-by"),
		MINUS(
			"field_name1 - field_name2",
			"subtract-numeric-fields-from-one-another-to-create-an-expression",
			"minus"),
		OR(
			"OR",
			"this-is-a-type-of-coordinating-conjunction-that-indicates-an-" +
				"independent-relationship",
			"or"),
		PLUS(
			"field_name1 + field_name2",
			"add-numeric-fields-to-create-an-expression", "plus"),
		TIMES(
			"field_name1 * field_name2",
			"multiply-numeric-fields-to-create-an-expression", "times");

		public static List<Map<String, String>> getItems(
			Predicate<DDMExpressionOperator> ddmExpressionOperatorPredicate,
			Locale locale) {

			List<Map<String, String>> values = new ArrayList<>();

			for (DDMExpressionOperator ddmExpressionOperator : values()) {
				if ((ddmExpressionOperatorPredicate == null) ||
					ddmExpressionOperatorPredicate.test(
						ddmExpressionOperator)) {

					values.add(
						HashMapBuilder.put(
							"content", ddmExpressionOperator._content
						).put(
							"helpText",
							LanguageUtil.get(
								locale, ddmExpressionOperator._helpTextKey)
						).put(
							"label",
							LanguageUtil.get(locale, ddmExpressionOperator._key)
						).build());
				}
			}

			return values;
		}

		private DDMExpressionOperator(
			String content, String helpTextKey, String key) {

			_content = content;
			_helpTextKey = helpTextKey;
			_key = key;
		}

		private String _content;
		private String _helpTextKey;
		private String _key;

	}

	private static Map<String, Object> _createCodeEditorElement(
		List<Map<String, String>> items, String key, Locale locale) {

		return HashMapBuilder.<String, Object>put(
			"items", items
		).put(
			"label", LanguageUtil.get(locale, key)
		).build();
	}

	private static final Snapshot<ObjectFieldLocalService>
		_objectFieldLocalServiceSnapshot = new Snapshot<>(
			ObjectCodeEditorUtil.class, ObjectFieldLocalService.class);

}