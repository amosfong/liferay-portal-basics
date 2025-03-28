/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.background.image;

import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.DocumentFragmentEntryProcessor;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "fragment.entry.processor.priority:Integer=5",
	service = DocumentFragmentEntryProcessor.class
)
public class BackgroundImageDocumentFragmentEntryProcessor
	implements DocumentFragmentEntryProcessor {

	@Override
	public void processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, Document document,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			fragmentEntryLink.getEditableValues());

		JSONObject editableValuesJSONObject = jsonObject.getJSONObject(
			FragmentEntryProcessorConstants.
				KEY_BACKGROUND_IMAGE_FRAGMENT_ENTRY_PROCESSOR);

		if (editableValuesJSONObject == null) {
			return;
		}

		for (Element element :
				document.getElementsByAttribute(
					"data-lfr-background-image-id")) {

			String id = element.attr("data-lfr-background-image-id");

			if (!editableValuesJSONObject.has(id)) {
				continue;
			}

			JSONObject editableValueJSONObject =
				editableValuesJSONObject.getJSONObject(id);

			String value = StringPool.BLANK;

			if (Validator.isNull(value)) {
				value = _fragmentEntryProcessorHelper.getEditableValue(
					editableValueJSONObject,
					fragmentEntryProcessorContext.getLocale());
			}

			if (Validator.isNotNull(value)) {
				long fileEntryId = 0;

				if (JSONUtil.isJSONObject(value)) {
					JSONObject valueJSONObject = _jsonFactory.createJSONObject(
						value);

					fileEntryId = valueJSONObject.getLong("fileEntryId");

					if (fileEntryId == 0) {
						fileEntryId =
							_fragmentEntryProcessorHelper.getFileEntryId(
								valueJSONObject.getString("className"),
								valueJSONObject.getLong("classPK"));
					}

					value = valueJSONObject.getString("url", value);
				}

				StringBundler sb = new StringBundler(6);

				sb.append("background-image: url(");
				sb.append(value);
				sb.append("); background-size: cover;");

				if (fileEntryId > 0) {
					sb.append(" --background-image-file-entry-id: ");
					sb.append(fileEntryId);
					sb.append(StringPool.SEMICOLON);
				}

				element.attr("style", sb.toString());
			}

			if (fragmentEntryProcessorContext.isPreviewMode() ||
				fragmentEntryProcessorContext.isViewMode()) {

				element.removeAttr("data-lfr-background-image-id");
			}
		}
	}

	private String _getImageURL(Object fieldValue) {
		if (fieldValue instanceof JSONObject) {
			JSONObject fieldValueJSONObject = (JSONObject)fieldValue;

			return fieldValueJSONObject.getString("url");
		}

		return String.valueOf(fieldValue);
	}

	@Reference
	private FragmentEntryProcessorHelper _fragmentEntryProcessorHelper;

	@Reference
	private JSONFactory _jsonFactory;

}