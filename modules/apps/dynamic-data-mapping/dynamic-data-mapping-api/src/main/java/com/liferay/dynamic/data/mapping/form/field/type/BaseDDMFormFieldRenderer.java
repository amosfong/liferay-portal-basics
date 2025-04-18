/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.constants.LanguageConstants;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.Validator;

import java.io.Writer;

import java.net.URL;

import java.util.Locale;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDDMFormFieldRenderer implements DDMFormFieldRenderer {

	public abstract String getTemplateLanguage();

	public abstract String getTemplateNamespace();

	public abstract TemplateResource getTemplateResource();

	@Override
	public String render(
			DDMFormField ddmFormField,
			DDMFormFieldRenderingContext ddmFormFieldRenderingContext)
		throws PortalException {

		Template template = TemplateManagerUtil.getTemplate(
			getTemplateLanguage(), getTemplateResource(), false);

		template.put(TemplateConstants.NAMESPACE, getTemplateNamespace());
		template.put(TemplateConstants.RENDER_STRICT, Boolean.FALSE);

		populateRequiredContext(
			template, ddmFormField, ddmFormFieldRenderingContext);

		populateOptionalContext(
			template, ddmFormField, ddmFormFieldRenderingContext);

		return render(template);
	}

	protected TemplateResource getTemplateResource(String templatePath) {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		URL templateURL = classLoader.getResource(templatePath);

		return new URLTemplateResource(templateURL.getPath(), templateURL);
	}

	protected String getValueString(Value value, Locale locale) {
		if (value != null) {
			return value.getString(locale);
		}

		return StringPool.BLANK;
	}

	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {
	}

	protected void populateRequiredContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Locale locale = ddmFormFieldRenderingContext.getLocale();

		String childElementsHTML =
			ddmFormFieldRenderingContext.getChildElementsHTML();

		if (Validator.isNotNull(childElementsHTML)) {
			template.put("childElementsHTML", childElementsHTML);
		}

		template.put(
			"dir", LanguageUtil.get(locale, LanguageConstants.KEY_DIR));
		template.put("label", ddmFormFieldRenderingContext.getLabel());
		template.put("name", ddmFormFieldRenderingContext.getName());
		template.put(
			"readOnly",
			_isReadOnly(ddmFormField, ddmFormFieldRenderingContext));
		template.put("required", ddmFormFieldRenderingContext.isRequired());
		template.put("showLabel", ddmFormField.isShowLabel());
		template.put("tip", ddmFormFieldRenderingContext.getTip());
		template.put("value", ddmFormFieldRenderingContext.getValue());
		template.put("visible", ddmFormFieldRenderingContext.isVisible());
	}

	protected String render(Template template) throws PortalException {
		Writer writer = new UnsyncStringWriter();

		template.processTemplate(writer);

		return writer.toString();
	}

	private boolean _isReadOnly(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		if (ddmFormFieldRenderingContext.isReadOnly() ||
			ddmFormField.isReadOnly()) {

			return true;
		}

		return false;
	}

}