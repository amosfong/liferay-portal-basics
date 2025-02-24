/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.resource.v1_0;

import com.liferay.headless.delivery.dto.v1_0.Document;
import com.liferay.headless.delivery.dto.v1_0.Rating;
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
import com.liferay.portal.vulcan.multipart.MultipartBody;
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
public interface DocumentResource {

	public Page<Document> getAssetLibraryDocumentsPage(
			Long assetLibraryId, Boolean flatten, String search,
			com.liferay.portal.vulcan.aggregation.Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Response postAssetLibraryDocumentsPageExportBatch(
			Long assetLibraryId, String search, Filter filter, Sort[] sorts,
			String callbackURL, String contentType, String fieldNames)
		throws Exception;

	public Document postAssetLibraryDocument(
			Long assetLibraryId, MultipartBody multipartBody)
		throws Exception;

	public Response postAssetLibraryDocumentBatch(
			Long assetLibraryId, MultipartBody multipartBody,
			String callbackURL, Object object)
		throws Exception;

	public void deleteAssetLibraryDocumentByExternalReferenceCode(
			Long assetLibraryId, String externalReferenceCode)
		throws Exception;

	public Document getAssetLibraryDocumentByExternalReferenceCode(
			Long assetLibraryId, String externalReferenceCode)
		throws Exception;

	public Document putAssetLibraryDocumentByExternalReferenceCode(
			Long assetLibraryId, String externalReferenceCode,
			MultipartBody multipartBody)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			getAssetLibraryDocumentPermissionsPage(
				Long assetLibraryId, String roleNames)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			putAssetLibraryDocumentPermissionsPage(
				Long assetLibraryId,
				com.liferay.portal.vulcan.permission.Permission[] permissions)
		throws Exception;

	public Page<Document> getAssetLibraryDocumentsRatedByMePage(
			Long assetLibraryId, Pagination pagination)
		throws Exception;

	public Page<Document> getDocumentFolderDocumentsPage(
			Long documentFolderId, Boolean flatten, String search,
			com.liferay.portal.vulcan.aggregation.Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Response postDocumentFolderDocumentsPageExportBatch(
			Long documentFolderId, String search, Filter filter, Sort[] sorts,
			String callbackURL, String contentType, String fieldNames)
		throws Exception;

	public Document postDocumentFolderDocument(
			Long documentFolderId, MultipartBody multipartBody)
		throws Exception;

	public Response postDocumentFolderDocumentBatch(
			Long documentFolderId, MultipartBody multipartBody,
			String callbackURL, Object object)
		throws Exception;

	public void deleteDocument(Long documentId) throws Exception;

	public Response deleteDocumentBatch(String callbackURL, Object object)
		throws Exception;

	public Document getDocument(Long documentId) throws Exception;

	public Document patchDocument(Long documentId, MultipartBody multipartBody)
		throws Exception;

	public Document putDocument(Long documentId, MultipartBody multipartBody)
		throws Exception;

	public Response putDocumentBatch(
			MultipartBody multipartBody, String callbackURL, Object object)
		throws Exception;

	public void deleteDocumentMyRating(Long documentId) throws Exception;

	public Rating getDocumentMyRating(Long documentId) throws Exception;

	public Rating postDocumentMyRating(Long documentId, Rating rating)
		throws Exception;

	public Rating putDocumentMyRating(Long documentId, Rating rating)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			getDocumentPermissionsPage(Long documentId, String roleNames)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			putDocumentPermissionsPage(
				Long documentId,
				com.liferay.portal.vulcan.permission.Permission[] permissions)
		throws Exception;

	public String getDocumentRenderedContentByDisplayPageDisplayPageKey(
			Long documentId, String displayPageKey)
		throws Exception;

	public Page<Document> getSiteDocumentsPage(
			Long siteId, Boolean flatten, String search,
			com.liferay.portal.vulcan.aggregation.Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Response postSiteDocumentsPageExportBatch(
			Long siteId, String search, Filter filter, Sort[] sorts,
			String callbackURL, String contentType, String fieldNames)
		throws Exception;

	public Document postSiteDocument(Long siteId, MultipartBody multipartBody)
		throws Exception;

	public Response postSiteDocumentBatch(
			Long siteId, MultipartBody multipartBody, String callbackURL,
			Object object)
		throws Exception;

	public void deleteSiteDocumentByExternalReferenceCode(
			Long siteId, String externalReferenceCode)
		throws Exception;

	public Document getSiteDocumentByExternalReferenceCode(
			Long siteId, String externalReferenceCode)
		throws Exception;

	public Document putSiteDocumentByExternalReferenceCode(
			Long siteId, String externalReferenceCode,
			MultipartBody multipartBody)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			getSiteDocumentPermissionsPage(Long siteId, String roleNames)
		throws Exception;

	public Page<com.liferay.portal.vulcan.permission.Permission>
			putSiteDocumentPermissionsPage(
				Long siteId,
				com.liferay.portal.vulcan.permission.Permission[] permissions)
		throws Exception;

	public Page<Document> getSiteDocumentsRatedByMePage(
			Long siteId, Pagination pagination)
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

		public DocumentResource build();

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