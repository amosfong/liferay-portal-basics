/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.resource.v1_0;

import com.liferay.headless.delivery.dto.v1_0.DocumentShortcut;
import com.liferay.portal.kernel.change.tracking.CTAware;
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
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-delivery/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@CTAware
@Generated("")
@ProviderType
public interface DocumentShortcutResource {

	public Page<DocumentShortcut> getAssetLibraryDocumentShortcutsPage(
			Long assetLibraryId, Pagination pagination)
		throws Exception;

	public Response postAssetLibraryDocumentShortcutsPageExportBatch(
			Long assetLibraryId, String callbackURL, String contentType,
			String fieldNames)
		throws Exception;

	public DocumentShortcut postAssetLibraryDocumentShortcut(
			Long assetLibraryId, DocumentShortcut documentShortcut)
		throws Exception;

	public Response postAssetLibraryDocumentShortcutBatch(
			Long assetLibraryId, String callbackURL, Object object)
		throws Exception;

	public void deleteDocumentShortcut(Long documentShortcutId)
		throws Exception;

	public Response deleteDocumentShortcutBatch(
			String callbackURL, Object object)
		throws Exception;

	public DocumentShortcut getDocumentShortcut(Long documentShortcutId)
		throws Exception;

	public DocumentShortcut patchDocumentShortcut(
			Long documentShortcutId, DocumentShortcut documentShortcut)
		throws Exception;

	public DocumentShortcut putDocumentShortcut(
			Long documentShortcutId, DocumentShortcut documentShortcut)
		throws Exception;

	public Response putDocumentShortcutBatch(String callbackURL, Object object)
		throws Exception;

	public Page<DocumentShortcut> getSiteDocumentShortcutsPage(
			Long siteId, Pagination pagination)
		throws Exception;

	public Response postSiteDocumentShortcutsPageExportBatch(
			Long siteId, String callbackURL, String contentType,
			String fieldNames)
		throws Exception;

	public DocumentShortcut postSiteDocumentShortcut(
			Long siteId, DocumentShortcut documentShortcut)
		throws Exception;

	public Response postSiteDocumentShortcutBatch(
			Long siteId, String callbackURL, Object object)
		throws Exception;

	public void deleteSiteDocumentShortcutByExternalReferenceCode(
			Long siteId, String externalReferenceCode)
		throws Exception;

	public DocumentShortcut getSiteDocumentShortcutByExternalReferenceCode(
			Long siteId, String externalReferenceCode)
		throws Exception;

	public DocumentShortcut putSiteDocumentShortcutByExternalReferenceCode(
			Long siteId, String externalReferenceCode,
			DocumentShortcut documentShortcut)
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

		public DocumentShortcutResource build();

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