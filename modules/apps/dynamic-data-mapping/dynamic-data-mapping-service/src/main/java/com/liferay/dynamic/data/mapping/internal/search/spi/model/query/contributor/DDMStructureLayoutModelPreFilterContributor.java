/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcelo Mello
 */
@Component(
	property = "indexer.class.name=com.liferay.dynamic.data.mapping.model.DDMStructureLayout",
	service = ModelPreFilterContributor.class
)
public class DDMStructureLayoutModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		long structureVersionId = GetterUtil.getLong(
			searchContext.getAttribute("structureVersionId"));

		if (structureVersionId > 0) {
			booleanFilter.addRequiredTerm(
				"structureVersionId", structureVersionId);
		}

		addWorkflowStatusFilter(
			booleanFilter, modelSearchSettings, searchContext);
	}

	protected void addRequiredTerm(
		BooleanFilter booleanFilter, SearchContext searchContext,
		String fieldName) {

		Serializable fieldValue = searchContext.getAttribute(fieldName);

		if (Validator.isNotNull(fieldValue)) {
			booleanFilter.addRequiredTerm(
				fieldName, String.valueOf(fieldValue));
		}
	}

	protected void addRequiredTerms(
		BooleanFilter booleanFilter, SearchContext searchContext,
		String fieldName, String contextFieldName) {

		long[] longValues = GetterUtil.getLongValues(
			searchContext.getAttribute(contextFieldName), null);

		if (ArrayUtil.isNotEmpty(longValues)) {
			TermsFilter termsFilter = new TermsFilter(fieldName);

			termsFilter.addValues(ArrayUtil.toStringArray(longValues));

			booleanFilter.add(termsFilter, BooleanClauseOccur.MUST);
		}
	}

	protected void addWorkflowStatusFilter(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		Serializable fieldValue = searchContext.getAttribute(Field.STATUS);

		if (Validator.isNull(fieldValue)) {
			return;
		}

		workflowStatusModelPreFilterContributor.contribute(
			booleanFilter, modelSearchSettings, searchContext);
	}

	@Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
	protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}