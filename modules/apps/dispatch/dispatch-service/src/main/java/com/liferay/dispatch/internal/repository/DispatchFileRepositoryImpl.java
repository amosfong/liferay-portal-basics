/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dispatch.internal.repository;

import com.liferay.dispatch.constants.DispatchConstants;
import com.liferay.dispatch.constants.DispatchPortletKeys;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.repository.DispatchFileRepository;
import com.liferay.dispatch.repository.DispatchFileValidator;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.InputStream;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@Component(service = DispatchFileRepository.class)
public class DispatchFileRepositoryImpl implements DispatchFileRepository {

	@Override
	public FileEntry addFileEntry(
			long userId, long dispatchTriggerId, String fileName, long size,
			String contentType, InputStream inputStream)
		throws PortalException {

		DispatchTrigger dispatchTrigger =
			_dispatchTriggerLocalService.getDispatchTrigger(dispatchTriggerId);

		DispatchFileValidator dispatchFileValidator = _getDispatchFileValidator(
			dispatchTrigger.getDispatchTaskExecutorType());

		dispatchFileValidator.validateExtension(fileName);
		dispatchFileValidator.validateSize(size);

		Company company = _companyLocalService.getCompany(
			dispatchTrigger.getCompanyId());

		return _addFileEntry(
			company.getGroupId(), userId, dispatchTriggerId, contentType,
			inputStream);
	}

	@Override
	public FileEntry fetchFileEntry(long dispatchTriggerId) {
		try {
			DispatchTrigger dispatchTrigger =
				_dispatchTriggerLocalService.getDispatchTrigger(
					dispatchTriggerId);

			Company company = _companyLocalService.getCompany(
				dispatchTrigger.getCompanyId());

			Folder folder = _getFolder(
				company.getGroupId(), dispatchTrigger.getUserId());

			return _portletFileRepository.fetchPortletFileEntry(
				company.getGroupId(), folder.getFolderId(),
				String.valueOf(dispatchTriggerId));
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to fetch file entry", portalException);
			}
		}

		return null;
	}

	@Override
	public String fetchFileEntryName(long dispatchTriggerId) {
		FileEntry fileEntry = fetchFileEntry(dispatchTriggerId);

		if (fileEntry != null) {
			return fileEntry.getFileName();
		}

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, DispatchFileValidator.class,
			"dispatch.file.validator.type");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private FileEntry _addFileEntry(
			long groupId, long userId, long dispatchTriggerId,
			String contentType, InputStream inputStream)
		throws PortalException {

		Folder folder = _getFolder(groupId, userId);

		FileEntry fileEntry = _portletFileRepository.fetchPortletFileEntry(
			groupId, folder.getFolderId(), String.valueOf(dispatchTriggerId));

		if (fileEntry != null) {
			_portletFileRepository.deletePortletFileEntry(
				fileEntry.getFileEntryId());
		}

		return _portletFileRepository.addPortletFileEntry(
			null, groupId, userId, DispatchTrigger.class.getName(),
			dispatchTriggerId, DispatchPortletKeys.DISPATCH,
			folder.getFolderId(), inputStream,
			String.valueOf(dispatchTriggerId), contentType, false);
	}

	private DispatchFileValidator _getDispatchFileValidator(
		String dispatchTaskExecutorType) {

		if (_serviceTrackerMap.containsKey(dispatchTaskExecutorType)) {
			return _serviceTrackerMap.getService(dispatchTaskExecutorType);
		}

		return _serviceTrackerMap.getService("default");
	}

	private Folder _getFolder(long groupId, long userId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = _portletFileRepository.addPortletRepository(
			groupId, DispatchPortletKeys.DISPATCH, serviceContext);

		return _portletFileRepository.addPortletFolder(
			userId, repository.getRepositoryId(),
			DispatchConstants.REPOSITORY_DEFAULT_PARENT_FOLDER_ID,
			DispatchConstants.REPOSITORY_FOLDER_NAME, serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DispatchFileRepositoryImpl.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private DispatchTriggerLocalService _dispatchTriggerLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTLETS_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private PortletFileRepository _portletFileRepository;

	private ServiceTrackerMap<String, DispatchFileValidator> _serviceTrackerMap;

}