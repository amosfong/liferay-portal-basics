/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.search.spi.model.query.contributor;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.constants.AccountConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.ExistsFilter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.Organization",
	service = ModelPreFilterContributor.class
)
public class OrganizationModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		long[] accountEntryIds = (long[])searchContext.getAttribute(
			"accountEntryIds");

		if (ArrayUtil.isNotEmpty(accountEntryIds)) {
			if ((accountEntryIds.length == 1) &&
				(accountEntryIds[0] == AccountConstants.ACCOUNT_ENTRY_ID_ANY)) {

				ExistsFilter existsFilter = new ExistsFilter("accountEntryIds");

				booleanFilter.add(existsFilter, BooleanClauseOccur.MUST);
			}
			else {
				TermsFilter termsFilter = new TermsFilter("accountEntryIds");

				termsFilter.addValues(ArrayUtil.toStringArray(accountEntryIds));

				booleanFilter.add(termsFilter, BooleanClauseOccur.MUST);
			}
		}

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params == null) {
			return;
		}

		List<Organization> accountsOrgsTree = (List<Organization>)params.get(
			"accountsOrgsTree");

		if (accountsOrgsTree != null) {
			BooleanFilter treePathBooleanFilter = new BooleanFilter();

			if (accountsOrgsTree.isEmpty()) {
				TermQuery termQuery = new TermQueryImpl(
					Field.TREE_PATH, StringPool.BLANK);

				treePathBooleanFilter.add(new QueryFilter(termQuery));
			}

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			for (Organization organization : accountsOrgsTree) {
				String treePath;

				try {
					treePath = organization.buildTreePath();

					if ((permissionChecker != null) &&
						OrganizationPermissionUtil.contains(
							permissionChecker, organization,
							AccountActionKeys.
								MANAGE_SUBORGANIZATIONS_ACCOUNTS)) {

						treePath = treePath + "*";
					}
				}
				catch (PortalException portalException) {
					throw new RuntimeException(portalException);
				}

				WildcardQuery wildcardQuery = new WildcardQueryImpl(
					Field.TREE_PATH, treePath);

				treePathBooleanFilter.add(new QueryFilter(wildcardQuery));
			}

			booleanFilter.add(treePathBooleanFilter, BooleanClauseOccur.MUST);
		}
	}

}