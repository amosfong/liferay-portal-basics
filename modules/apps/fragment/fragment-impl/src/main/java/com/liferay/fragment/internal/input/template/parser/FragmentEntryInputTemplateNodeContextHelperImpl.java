/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.input.template.parser;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.fragment.constants.FragmentConfigurationFieldDataType;
import com.liferay.fragment.input.template.parser.FragmentEntryInputTemplateNodeContextHelper;
import com.liferay.fragment.input.template.parser.InputTemplateNode;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = FragmentEntryInputTemplateNodeContextHelper.class)
public class FragmentEntryInputTemplateNodeContextHelperImpl
	implements FragmentEntryInputTemplateNodeContextHelper {

	@Override
	public InputTemplateNode toInputTemplateNode(
		String defaultInputLabel, FragmentEntryLink fragmentEntryLink,
		HttpServletRequest httpServletRequest, Locale locale) {

		String errorMessage = StringPool.BLANK;

		String inputHelpText = GetterUtil.getString(
			_fragmentEntryConfigurationParser.getFieldValue(
				fragmentEntryLink.getEditableValues(),
				new FragmentConfigurationField(
					"inputHelpText", "string",
					_language.get(locale, "add-your-help-text-here"), true,
					"text"),
				locale));

		String inputLabel = _getInputLabel(
			defaultInputLabel, fragmentEntryLink.getEditableValues(), locale);

		boolean localizable = false;
		String name = "name";
		boolean readOnly = false;
		boolean required = false;

		boolean inputShowHelpText = GetterUtil.getBoolean(
			_fragmentEntryConfigurationParser.getFieldValue(
				fragmentEntryLink.getEditableValues(),
				new FragmentConfigurationField(
					"inputShowHelpText", "boolean", "false", false, "checkbox"),
				locale));

		boolean inputShowLabel = GetterUtil.getBoolean(
			_fragmentEntryConfigurationParser.getFieldValue(
				fragmentEntryLink.getEditableValues(),
				new FragmentConfigurationField(
					"inputShowLabel", "boolean", "true", false, "checkbox"),
				locale));

		return new InputTemplateNode(
			errorMessage, inputHelpText, inputLabel, localizable, name,
			readOnly, required, inputShowHelpText, inputShowLabel, "type",
			StringPool.BLANK);
	}

	private String _getInputLabel(
		String defaultInputLabel, String editableValues, Locale locale) {

		String inputLabel = null;

		JSONObject inputLabelJSONObject =
			(JSONObject)
				_fragmentEntryConfigurationParser.getConfigurationFieldValue(
					editableValues, "inputLabel",
					FragmentConfigurationFieldDataType.OBJECT);

		if (inputLabelJSONObject != null) {
			inputLabel = inputLabelJSONObject.getString(
				_language.getLanguageId(locale));
		}

		if (Validator.isNotNull(inputLabel)) {
			return inputLabel;
		}

		return defaultInputLabel;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryInputTemplateNodeContextHelperImpl.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private Language _language;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

}