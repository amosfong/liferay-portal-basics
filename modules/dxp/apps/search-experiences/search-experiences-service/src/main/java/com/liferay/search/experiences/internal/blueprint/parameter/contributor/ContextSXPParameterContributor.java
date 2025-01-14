/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.blueprint.parameter.contributor;

import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.search.experiences.blueprint.parameter.SXPParameter;
import com.liferay.search.experiences.blueprint.parameter.contributor.SXPParameterContributor;
import com.liferay.search.experiences.blueprint.parameter.contributor.SXPParameterContributorDefinition;
import com.liferay.search.experiences.internal.blueprint.parameter.BooleanSXPParameter;
import com.liferay.search.experiences.internal.blueprint.parameter.LongArraySXPParameter;
import com.liferay.search.experiences.internal.blueprint.parameter.LongSXPParameter;
import com.liferay.search.experiences.internal.blueprint.parameter.StringSXPParameter;

import java.beans.ExceptionListener;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author Petteri Karttunen
 */
public class ContextSXPParameterContributor implements SXPParameterContributor {

	public ContextSXPParameterContributor(
		GroupLocalService groupLocalService, Language language) {

		_groupLocalService = groupLocalService;
		_language = language;
	}

	@Override
	public void contribute(
		ExceptionListener exceptionListener, SearchContext searchContext,
		Set<SXPParameter> sxpParameters) {

		long[] commerceAccountGroupIds = (long[])searchContext.getAttribute(
			"commerceAccountGroupIds");

		if (commerceAccountGroupIds != null) {
			sxpParameters.add(
				new LongArraySXPParameter(
					"commerceAccountGroupIds", true,
					ArrayUtil.toArray(commerceAccountGroupIds)));
		}

		Long commerceChannelGroupId = (Long)searchContext.getAttribute(
			"commerceChannelGroupId");

		if (commerceChannelGroupId != null) {
			sxpParameters.add(
				new LongSXPParameter(
					"commerceChannelGroupId", true, commerceChannelGroupId));
		}

		sxpParameters.add(
			new LongSXPParameter(
				"context.company_id", true, searchContext.getCompanyId()));
		sxpParameters.add(
			new LongSXPParameter(
				"context.publication_id", true,
				CTCollectionThreadLocal.getCTCollectionId()));

		Locale locale = searchContext.getLocale();

		sxpParameters.add(
			new StringSXPParameter(
				"context.language", true, locale.getLanguage()));
		sxpParameters.add(
			new StringSXPParameter(
				"context.language_id", true, _language.getLanguageId(locale)));

		Layout layout = searchContext.getLayout();

		if (layout != null) {
			sxpParameters.add(
				new StringSXPParameter(
					"context.layout-name-localized", true,
					layout.getName(locale, true)));
			sxpParameters.add(
				new LongSXPParameter("plid", true, layout.getPlid()));
		}

		long scopeGroupId = GetterUtil.getLong(
			searchContext.getAttribute("search.experiences.scope.group.id"));

		if (scopeGroupId != 0) {
			sxpParameters.add(
				new LongSXPParameter(
					"context.scope_group_id", true, scopeGroupId));

			try {
				Group group = _groupLocalService.getGroup(scopeGroupId);

				sxpParameters.add(
					new BooleanSXPParameter(
						"context.is_staging_group", true,
						group.isStagingGroup()));
				sxpParameters.add(
					new StringSXPParameter(
						"context.scope_group_external_reference_code", true,
						group.getExternalReferenceCode()));
			}
			catch (PortalException portalException) {
				exceptionListener.exceptionThrown(portalException);
			}
		}
	}

	@Override
	public String getSXPParameterCategoryNameKey() {
		return "context";
	}

	@Override
	public List<SXPParameterContributorDefinition>
		getSXPParameterContributorDefinitions(long companyId, Locale locale) {

		return Arrays.asList(
			new SXPParameterContributorDefinition(
				LongSXPParameter.class, "company-id", "context.company_id"),
			new SXPParameterContributorDefinition(
				BooleanSXPParameter.class, "is-staging-group",
				"context.is_staging_group"),
			new SXPParameterContributorDefinition(
				StringSXPParameter.class, "language", "context.language"),
			new SXPParameterContributorDefinition(
				StringSXPParameter.class, "language-id", "context.language_id"),
			new SXPParameterContributorDefinition(
				StringSXPParameter.class, "layout-name-localized",
				"context.layout-name-localized"),
			new SXPParameterContributorDefinition(
				LongSXPParameter.class, "page-layout-id", "context.plid"),
			new SXPParameterContributorDefinition(
				LongSXPParameter.class, "publication-id",
				"context.publication_id"),
			new SXPParameterContributorDefinition(
				LongSXPParameter.class, "scope-group-id",
				"context.scope_group_id"),
			new SXPParameterContributorDefinition(
				StringSXPParameter.class, "scope-group-external-reference-code",
				"context.scope_group_external_reference_code"));
	}

	private final GroupLocalService _groupLocalService;
	private final Language _language;

}