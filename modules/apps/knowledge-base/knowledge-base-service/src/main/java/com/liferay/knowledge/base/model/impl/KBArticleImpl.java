/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model.impl;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.comparator.RepositoryModelTitleComparator;
import com.liferay.knowledge.base.constants.KBArticleConstants;
import com.liferay.knowledge.base.constants.KBConstants;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.exception.NoSuchArticleException;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBArticleModel;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalServiceUtil;
import com.liferay.knowledge.base.service.KBArticleServiceUtil;
import com.liferay.knowledge.base.service.KBFolderLocalServiceUtil;
import com.liferay.knowledge.base.service.KBFolderServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.view.count.ViewCountManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleImpl extends KBArticleBaseImpl {

	/**
	 * @see com.liferay.portal.model.impl.OrganizationBaseImpl#buildTreePath()
	 */
	@Override
	public String buildTreePath() throws PortalException {
		List<KBFolder> folders = new ArrayList<>();

		KBFolder folder = KBFolderLocalServiceUtil.fetchKBFolder(
			getParentResourcePrimKey());

		while (folder != null) {
			folders.add(folder);

			folder = folder.getParentKBFolder();
		}

		StringBundler sb = new StringBundler((folders.size() * 2) + 1);

		sb.append("/");

		for (int i = folders.size() - 1; i >= 0; i--) {
			folder = folders.get(i);

			sb.append(folder.getKbFolderId());

			sb.append("/");
		}

		return sb.toString();
	}

	@Override
	public List<KBArticle> getAncestorKBArticles() throws PortalException {
		return _getAncestors(Function.identity());
	}

	@Override
	public List<Long> getAncestorResourcePrimaryKeys() throws PortalException {
		List<Long> ancestorResourcePrimaryKeys = new ArrayList<>();

		ancestorResourcePrimaryKeys.add(getResourcePrimKey());
		ancestorResourcePrimaryKeys.addAll(
			_getAncestors(KBArticleModel::getResourcePrimKey));

		if (getKbFolderId() == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return ancestorResourcePrimaryKeys;
		}

		ancestorResourcePrimaryKeys.add(getKbFolderId());

		KBFolder kbFolder = KBFolderLocalServiceUtil.getKBFolder(
			getKbFolderId());

		ancestorResourcePrimaryKeys.addAll(kbFolder.getAncestorKBFolderIds());

		return ancestorResourcePrimaryKeys;
	}

	@Override
	public List<FileEntry> getAttachmentsFileEntries() throws PortalException {
		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), getAttachmentsFolderId(),
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new RepositoryModelTitleComparator<>(true));
	}

	@Override
	public FileEntry getAttachmentsFileEntryByExternalReferenceCode(
			String externalReferenceCode)
		throws PortalException {

		FileEntry fileEntry =
			PortletFileRepositoryUtil.
				getPortletFileEntryByExternalReferenceCode(
					externalReferenceCode, getGroupId());

		if (getAttachmentsFolderId() == fileEntry.getFolderId()) {
			return fileEntry;
		}

		throw new NoSuchFileEntryException(
			StringBundler.concat(
				"No FileEntry exists with the key {externalReferenceCode=",
				externalReferenceCode, ", groupId=", getGroupId(), "}"));
	}

	@Override
	public long getAttachmentsFolderId() throws PortalException {
		if (_attachmentsFolderId > 0) {
			return _attachmentsFolderId;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			getGroupId(), KBConstants.SERVICE_NAME, serviceContext);

		Folder folder = PortletFileRepositoryUtil.addPortletFolder(
			PortalUtil.getValidUserId(getCompanyId(), getUserId()),
			repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			String.valueOf(getResourcePrimKey()), serviceContext);

		_attachmentsFolderId = folder.getFolderId();

		return _attachmentsFolderId;
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(KBArticleConstants.getClassName());
	}

	@Override
	public long getClassPK() {
		if (isApproved() || isExpired()) {
			return getResourcePrimKey();
		}

		return getKbArticleId();
	}

	@Override
	public KBArticle getParentKBArticle() throws PortalException {
		if (!hasParentKBArticle()) {
			return null;
		}

		return KBArticleLocalServiceUtil.getLatestKBArticle(
			getParentResourcePrimKey(), WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public String getParentTitle(Locale locale, int status)
		throws PortalException {

		if (isRoot()) {
			return LanguageUtil.get(locale, "home");
		}

		if (getParentResourceClassNameId() == getClassNameId()) {
			KBArticle kbArticle = KBArticleServiceUtil.getLatestKBArticle(
				getParentResourcePrimKey(), status);

			return kbArticle.getTitle();
		}

		KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
			getParentResourcePrimKey());

		return kbFolder.getName();
	}

	@Override
	public long getTrashEntryClassPK() {
		return getResourcePrimKey();
	}

	@Override
	public long getViewCount() {
		return ViewCountManagerUtil.getViewCount(
			getCompanyId(),
			ClassNameLocalServiceUtil.getClassNameId(KBArticle.class),
			getPrimaryKey());
	}

	@Override
	public boolean hasParentKBArticle() {
		if ((getParentResourcePrimKey() <= 0) ||
			(getParentResourceClassNameId() != getClassNameId())) {

			return false;
		}

		return true;
	}

	@Override
	public boolean isFirstVersion() {
		if (getVersion() == KBArticleConstants.DEFAULT_VERSION) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isResourceMain() {
		return isMain();
	}

	@Override
	public boolean isRoot() {
		if (getParentResourcePrimKey() ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return true;
		}

		return false;
	}

	private <T> List<T> _getAncestors(Function<KBArticle, T> function)
		throws PortalException {

		List<T> ancestors = new ArrayList<>();

		KBArticle kbArticle = this;

		while (kbArticle.hasParentKBArticle()) {
			try {
				kbArticle = kbArticle.getParentKBArticle();

				ancestors.add(function.apply(kbArticle));
			}
			catch (NoSuchArticleException noSuchArticleException) {
				if (kbArticle.isInTrash()) {
					break;
				}

				throw noSuchArticleException;
			}
		}

		return ancestors;
	}

	private long _attachmentsFolderId;

}