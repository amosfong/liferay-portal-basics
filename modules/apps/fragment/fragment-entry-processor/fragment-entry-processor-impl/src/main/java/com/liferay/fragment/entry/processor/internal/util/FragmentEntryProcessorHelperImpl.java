/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.internal.util;

import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = FragmentEntryProcessorHelper.class)
public class FragmentEntryProcessorHelperImpl
	implements FragmentEntryProcessorHelper {

	@Override
	public String getEditableValue(JSONObject jsonObject, Locale locale) {
		String value = jsonObject.getString(
			_language.getLanguageId(locale), null);

		if (value != null) {
			return value;
		}

		return jsonObject.getString(
			_language.getLanguageId(LocaleUtil.getSiteDefault()),
			jsonObject.getString("defaultValue", null));
	}

	@Override
	public long getFileEntryId(String className, long classPK) {
		if (!Objects.equals(className, FileEntry.class.getName())) {
			return 0;
		}

		return classPK;
	}

	@Override
	public boolean hasViewPermission(
		JSONObject editableValueJSONObject,
		FragmentEntryProcessorContext fragmentEntryProcessorContext) {

		if (!FeatureFlagManagerUtil.isEnabled("LPD-19955")) {
			return true;
		}

		return true;
	}

	@Override
	public boolean isMapped(JSONObject jsonObject) {
		long classNameId = jsonObject.getLong("classNameId");
		long classPK = jsonObject.getLong("classPK");
		String externalReferenceCode = jsonObject.getString(
			"externalReferenceCode");
		String fieldId = jsonObject.getString("fieldId");

		if ((classNameId > 0) &&
			((classPK > 0) || Validator.isNotNull(externalReferenceCode)) &&
			Validator.isNotNull(fieldId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isMappedCollection(JSONObject jsonObject) {
		if (jsonObject.has("collectionFieldId")) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isMappedDisplayPage(JSONObject jsonObject) {
		if (Validator.isNotNull(jsonObject.get("mappedField"))) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryProcessorHelperImpl.class);

	@Reference
	private Language _language;

}