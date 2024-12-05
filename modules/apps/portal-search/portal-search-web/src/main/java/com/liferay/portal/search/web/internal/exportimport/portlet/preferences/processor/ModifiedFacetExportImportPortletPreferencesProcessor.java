/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.search.web.internal.modified.facet.constants.ModifiedFacetPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Felipe Lorenz
 */
@Component(
	property = "javax.portlet.name=" + ModifiedFacetPortletKeys.MODIFIED_FACET,
	service = ExportImportPortletPreferencesProcessor.class
)
public class ModifiedFacetExportImportPortletPreferencesProcessor
	extends BaseExportImportPortletPreferencesProcessor {
}