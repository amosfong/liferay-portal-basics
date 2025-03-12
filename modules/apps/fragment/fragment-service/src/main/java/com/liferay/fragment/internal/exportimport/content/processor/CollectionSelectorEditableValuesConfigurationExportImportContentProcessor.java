/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(
	property = "content.processor.type=FragmentEntryLinkEditableValues",
	service = ExportImportContentProcessor.class
)
public class
	CollectionSelectorEditableValuesConfigurationExportImportContentProcessor
		extends BaseEditableValuesConfigurationExportImportContentProcessor {

	@Override
	protected String getConfigurationType() {
		return "collectionSelector";
	}

	@Override
	protected FragmentEntryConfigurationParser
		getFragmentEntryConfigurationParser() {

		return _fragmentEntryConfigurationParser;
	}

	@Override
	protected void replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			JSONObject configurationValueJSONObject,
			boolean exportReferencedContent)
		throws Exception {

		if (!configurationValueJSONObject.has("classPK")) {
			return;
		}

		configurationValueJSONObject.put(
			"className",
			_portal.getClassName(
				configurationValueJSONObject.getLong("classNameId")));
	}

	@Override
	protected void replaceImportContentReferences(
		PortletDataContext portletDataContext,
		JSONObject configurationValueJSONObject) {
	}

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private Portal _portal;

}