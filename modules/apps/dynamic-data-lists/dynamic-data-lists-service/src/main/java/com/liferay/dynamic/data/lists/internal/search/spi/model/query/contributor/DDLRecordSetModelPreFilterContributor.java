/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcela Cunha
 */
@Component(
	property = "indexer.class.name=com.liferay.dynamic.data.lists.model.DDLRecordSet",
	service = ModelPreFilterContributor.class
)
public class DDLRecordSetModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		long ddmStructureId = GetterUtil.getLong(
			searchContext.getAttribute("DDMStructureId"));

		if (ddmStructureId > 0) {
			booleanFilter.addRequiredTerm("DDMStructureId", ddmStructureId);
		}

		booleanFilter.addRequiredTerm(
			"scope", GetterUtil.getLong(searchContext.getAttribute("scope")));
	}

}