/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_0;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Sergio González
 */
public class KBAttachmentsUpgradeProcess extends UpgradeProcess {

	public KBAttachmentsUpgradeProcess(
		CompanyLocalService companyLocalService,
		PortletFileRepository portletFileRepository, Store store) {

		_companyLocalService = companyLocalService;
		_portletFileRepository = portletFileRepository;
		_store = store;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_updateAttachments();

		_deleteEmptyDirectories();
	}

	private void _deleteEmptyDirectories() throws Exception {
		_companyLocalService.forEachCompanyId(
			companyId -> _store.deleteDirectory(
				companyId, CompanyConstants.SYSTEM,
				"knowledgebase/kbarticles"));
	}

	private String[] _getAttachments(long companyId, long resourcePrimKey)
		throws Exception {

		String dirName = "knowledgebase/kbarticles/" + resourcePrimKey;

		return _store.getFileNames(companyId, CompanyConstants.SYSTEM, dirName);
	}

	/**
	 * @see KBArticleAttachmentsUtil#_getFolderId(long, long, long)
	 */
	private long _getFolderId(long groupId, long userId, long resourcePrimKey)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = _portletFileRepository.addPortletRepository(
			groupId, _PORTLET_ID, serviceContext);

		Folder folder = _portletFileRepository.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			String.valueOf(resourcePrimKey), serviceContext);

		return folder.getFolderId();
	}

	private void _updateAttachments() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select kbArticleId, resourcePrimKey, groupId, companyId, " +
					"userId, status from KBArticle");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long groupId = resultSet.getLong("groupId");
				long companyId = resultSet.getLong("companyId");

				long classPK = resultSet.getLong("resourcePrimKey");

				int status = resultSet.getInt("status");

				if (status != WorkflowConstants.STATUS_APPROVED) {
					classPK = resultSet.getLong("kbArticleId");
				}

				long userId = resultSet.getLong("userId");

				_updateAttachments(companyId, groupId, classPK, userId);
			}
		}
	}

	private void _updateAttachments(
			long companyId, long groupId, long resourcePrimKey, long userId)
		throws Exception {

		for (String attachment : _getAttachments(companyId, resourcePrimKey)) {
			try {
				if (!_store.hasFile(
						companyId, CompanyConstants.SYSTEM, attachment,
						Store.VERSION_DEFAULT)) {

					continue;
				}

				long folderId = _getFolderId(groupId, userId, resourcePrimKey);

				byte[] bytes = StreamUtil.toByteArray(
					_store.getFileAsStream(
						companyId, CompanyConstants.SYSTEM, attachment,
						StringPool.BLANK));

				String title = FileUtil.getShortFileName(attachment);

				String mimeType = MimeTypesUtil.getExtensionContentType(
					FileUtil.getExtension(title));

				_portletFileRepository.addPortletFileEntry(
					groupId, userId, _CLASS_NAME_KB_ARTICLE, resourcePrimKey,
					_PORTLET_ID, folderId, bytes, title, mimeType, false);

				for (String versionLabel :
						_store.getFileVersions(
							companyId, CompanyConstants.SYSTEM, attachment)) {

					_store.deleteFile(
						companyId, CompanyConstants.SYSTEM, attachment,
						versionLabel);
				}
			}
			catch (PortalException portalException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to upgrade attachment " + attachment,
						portalException);
				}
			}
		}
	}

	private static final String _CLASS_NAME_KB_ARTICLE =
		"com.liferay.knowledgebase.model.KBArticle";

	private static final String _PORTLET_ID = "3_WAR_knowledgebaseportlet";

	private static final Log _log = LogFactoryUtil.getLog(
		KBAttachmentsUpgradeProcess.class);

	private final CompanyLocalService _companyLocalService;
	private final PortletFileRepository _portletFileRepository;
	private final Store _store;

}