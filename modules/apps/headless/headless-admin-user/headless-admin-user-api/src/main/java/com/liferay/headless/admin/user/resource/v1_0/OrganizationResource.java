/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.resource.v1_0;

import com.liferay.headless.admin.user.dto.v1_0.Organization;
import com.liferay.headless.admin.user.dto.v1_0.UserAccount;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineExportTaskResource;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-admin-user/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@ProviderType
public interface OrganizationResource {

	public Page<Organization>
			getAccountByExternalReferenceCodeOrganizationsPage(
				String externalReferenceCode, String search, Filter filter,
				Pagination pagination, Sort[] sorts)
		throws Exception;

	public void deleteAccountByExternalReferenceCodeOrganization(
			String externalReferenceCode, String organizationId)
		throws Exception;

	public Organization getAccountByExternalReferenceCodeOrganization(
			String externalReferenceCode, String organizationId)
		throws Exception;

	public void postAccountByExternalReferenceCodeOrganization(
			String externalReferenceCode, String organizationId)
		throws Exception;

	public Page<Organization> getAccountOrganizationsPage(
			Long accountId, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception;

	public Response postAccountOrganizationsPageExportBatch(
			Long accountId, String search, Filter filter, Sort[] sorts,
			String callbackURL, String contentType, String fieldNames)
		throws Exception;

	public void deleteAccountOrganization(Long accountId, String organizationId)
		throws Exception;

	public Organization getAccountOrganization(
			Long accountId, String organizationId)
		throws Exception;

	public void postAccountOrganization(Long accountId, String organizationId)
		throws Exception;

	public Page<Organization> getOrganizationsPage(
			Boolean flatten, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception;

	public Response postOrganizationsPageExportBatch(
			String search, Filter filter, Sort[] sorts, String callbackURL,
			String contentType, String fieldNames)
		throws Exception;

	public Organization postOrganization(Organization organization)
		throws Exception;

	public Response postOrganizationBatch(String callbackURL, Object object)
		throws Exception;

	public void deleteOrganizationByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Organization getOrganizationByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Organization patchOrganizationByExternalReferenceCode(
			String externalReferenceCode, Organization organization)
		throws Exception;

	public Organization putOrganizationByExternalReferenceCode(
			String externalReferenceCode, Organization organization)
		throws Exception;

	public Page<Organization>
			getOrganizationByExternalReferenceCodeChildOrganizationsPage(
				String externalReferenceCode, Boolean flatten, String search,
				Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public void
			deleteOrganizationByExternalReferenceCodeUserAccountsByEmailAddress(
				String externalReferenceCode, String[] strings)
		throws Exception;

	public Page<UserAccount>
			postOrganizationByExternalReferenceCodeUserAccountsByEmailAddress(
				String externalReferenceCode, String organizationRoleIds,
				String[] strings)
		throws Exception;

	public void
			deleteOrganizationByExternalReferenceCodeUserAccountByEmailAddress(
				String externalReferenceCode, String emailAddress)
		throws Exception;

	public UserAccount
			postOrganizationByExternalReferenceCodeUserAccountByEmailAddress(
				String externalReferenceCode, String emailAddress)
		throws Exception;

	public void deleteOrganization(String organizationId) throws Exception;

	public Response deleteOrganizationBatch(String callbackURL, Object object)
		throws Exception;

	public Organization getOrganization(String organizationId) throws Exception;

	public Organization patchOrganization(
			String organizationId, Organization organization)
		throws Exception;

	public Organization putOrganization(
			String organizationId, Organization organization)
		throws Exception;

	public Response putOrganizationBatch(String callbackURL, Object object)
		throws Exception;

	public Page<Organization> getOrganizationChildOrganizationsPage(
			String organizationId, Boolean flatten, String search,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public void deleteUserAccountsByEmailAddress(
			String organizationId, String[] strings)
		throws Exception;

	public Page<UserAccount> postUserAccountsByEmailAddress(
			String organizationId, String organizationRoleIds, String[] strings)
		throws Exception;

	public void deleteUserAccountByEmailAddress(
			String organizationId, String emailAddress)
		throws Exception;

	public UserAccount postUserAccountByEmailAddress(
			String organizationId, String emailAddress)
		throws Exception;

	public Page<Organization> getOrganizationOrganizationsPage(
			String parentOrganizationId, Boolean flatten, String search,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public void setExpressionConvert(
		ExpressionConvert<Filter> expressionConvert);

	public void setFilterParserProvider(
		FilterParserProvider filterParserProvider);

	public void setGroupLocalService(GroupLocalService groupLocalService);

	public void setResourceActionLocalService(
		ResourceActionLocalService resourceActionLocalService);

	public void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService);

	public void setRoleLocalService(RoleLocalService roleLocalService);

	public void setSortParserProvider(SortParserProvider sortParserProvider);

	public void setVulcanBatchEngineExportTaskResource(
		VulcanBatchEngineExportTaskResource
			vulcanBatchEngineExportTaskResource);

	public void setVulcanBatchEngineImportTaskResource(
		VulcanBatchEngineImportTaskResource
			vulcanBatchEngineImportTaskResource);

	public default Filter toFilter(String filterString) {
		return toFilter(
			filterString, Collections.<String, List<String>>emptyMap());
	}

	public default Filter toFilter(
		String filterString, Map<String, List<String>> multivaluedMap) {

		return null;
	}

	public default Sort[] toSorts(String sortsString) {
		return new Sort[0];
	}

	@ProviderType
	public interface Builder {

		public OrganizationResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder httpServletResponse(
			HttpServletResponse httpServletResponse);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder uriInfo(UriInfo uriInfo);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}