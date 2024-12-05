/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.internal.background.task;

import com.liferay.change.tracking.conflict.ConflictInfo;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.constants.PublicationRoleConstants;
import com.liferay.change.tracking.exception.CTPublishConflictException;
import com.liferay.change.tracking.internal.CTServiceRegistry;
import com.liferay.change.tracking.internal.background.task.display.CTPublishBackgroundTaskDisplay;
import com.liferay.change.tracking.internal.helper.CTTableMapperHelper;
import com.liferay.change.tracking.internal.helper.CTUserNotificationHelper;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.service.CTSchemaVersionLocalService;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltan Csaszi
 * @author Daniel Kocsis
 */
@Component(
	property = "background.task.executor.class.name=com.liferay.change.tracking.internal.background.task.CTPublishBackgroundTaskExecutor",
	service = AopService.class
)
public class CTPublishBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor implements AopService {

	public CTPublishBackgroundTaskExecutor() {
		setIsolationLevel(BackgroundTaskConstants.ISOLATION_LEVEL_COMPANY);
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return _backgroundTaskExecutor;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class
	)
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long ctCollectionId = GetterUtil.getLong(
			taskContextMap.get("ctCollectionId"));

		CTCollection ctCollection = _ctCollectionLocalService.getCTCollection(
			ctCollectionId);

		if (!_ctSchemaVersionLocalService.isLatestCTSchemaVersion(
				ctCollection.getSchemaVersionId())) {

			throw new IllegalArgumentException(
				StringBundler.concat(
					"Unable to publish ", ctCollection.getName(),
					" because it is out of date with the current release"));
		}

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					ctCollectionId)) {

			_ctServiceRegistry.onBeforePublish(ctCollectionId);
		}

		Map<Long, List<ConflictInfo>> conflictInfosMap =
			_ctCollectionLocalService.checkConflicts(ctCollection);

		if (!conflictInfosMap.isEmpty()) {
			List<ConflictInfo> unresolvedConflictInfos = new ArrayList<>();

			for (Map.Entry<Long, List<ConflictInfo>> entry :
					conflictInfosMap.entrySet()) {

				for (ConflictInfo conflictInfo : entry.getValue()) {
					if (!conflictInfo.isResolved()) {
						unresolvedConflictInfos.add(conflictInfo);
					}
				}
			}

			if (!unresolvedConflictInfos.isEmpty()) {
				throw new CTPublishConflictException(
					StringBundler.concat(
						"Unable to publish ", ctCollection.getName(),
						" because of unresolved conflicts: ",
						unresolvedConflictInfos));
			}
		}

		Map<Long, CTServicePublisher<?>> ctServicePublishers = new HashMap<>();

		List<CTEntry> ctEntries = _ctEntryLocalService.getCTCollectionCTEntries(
			ctCollectionId);

		for (CTEntry ctEntry : ctEntries) {
			CTServicePublisher<?> ctServicePublisher =
				ctServicePublishers.computeIfAbsent(
					ctEntry.getModelClassNameId(),
					modelClassNameId -> {
						CTService<?> ctService =
							_ctServiceRegistry.getCTService(modelClassNameId);

						if (ctService != null) {
							return new CTServicePublisher<>(
								_ctEntryLocalService, ctService,
								modelClassNameId, ctCollectionId,
								CTConstants.CT_COLLECTION_ID_PRODUCTION);
						}

						throw new SystemException(
							StringBundler.concat(
								"Unable to publish ", ctCollection.getName(),
								" because service for ", modelClassNameId,
								" is missing"));
					});

			ctServicePublisher.addCTEntry(ctEntry);
		}

		for (CTServicePublisher<?> ctServicePublisher :
				ctServicePublishers.values()) {

			ctServicePublisher.publish();
		}

		for (CTTableMapperHelper ctTableMapperHelper :
				_ctServiceRegistry.getCTTableMapperHelpers()) {

			ctTableMapperHelper.publish(
				ctCollectionId, _multiVMPool.getPortalCacheManager());
		}

		Date modifiedDate = new Date();

		ctCollection.setModifiedDate(modifiedDate);

		ctCollection.setStatus(WorkflowConstants.STATUS_APPROVED);
		ctCollection.setStatusByUserId(backgroundTask.getUserId());
		ctCollection.setStatusDate(modifiedDate);

		_ctCollectionLocalService.updateCTCollection(ctCollection);

		_ctServiceRegistry.onAfterPublish(ctCollectionId);

		return BackgroundTaskResult.SUCCESS;
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {BackgroundTaskExecutor.class};
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return new CTPublishBackgroundTaskDisplay(backgroundTask);
	}

	@Override
	public String handleException(
		BackgroundTask backgroundTask, Exception exception) {

		boolean showConflicts = false;

		if (exception instanceof CTPublishConflictException) {
			showConflicts = true;
		}

		long ctCollectionId = MapUtil.getLong(
			backgroundTask.getTaskContextMap(), "ctCollectionId");

		try {
			CTCollection ctCollection =
				_ctCollectionLocalService.getCTCollection(ctCollectionId);

			_ctUserNotificationHelper.sendUserNotificationEvents(
				ctCollection,
				JSONUtil.put(
					"backgroundTaskId", backgroundTask.getBackgroundTaskId()
				).put(
					"ctCollectionId", ctCollectionId
				).put(
					"ctCollectionName", HtmlUtil.escape(ctCollection.getName())
				).put(
					"notificationType",
					UserNotificationDefinition.NOTIFICATION_TYPE_REVIEW_ENTRY
				).put(
					"showConflicts", showConflicts
				),
				_getPublicationRolesUserIds(ctCollection, showConflicts));
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return super.handleException(backgroundTask, exception);
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		_backgroundTaskExecutor = (BackgroundTaskExecutor)aopProxy;
	}

	private long[] _getPublicationRolesUserIds(
		CTCollection ctCollection, boolean showConflicts) {

		Set<Long> userIds = SetUtil.fromArray(
			_ctUserNotificationHelper.getPublicationRoleUserIds(
				ctCollection, true, PublicationRoleConstants.NAME_ADMIN,
				PublicationRoleConstants.NAME_EDITOR,
				PublicationRoleConstants.NAME_PUBLISHER));

		if (!showConflicts) {
			Role role = _roleLocalService.fetchRole(
				ctCollection.getCompanyId(), RoleConstants.ADMINISTRATOR);

			for (long userId :
					_userLocalService.getRoleUserIds(role.getRoleId())) {

				userIds.add(userId);
			}
		}

		return ArrayUtil.toLongArray(userIds);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CTPublishBackgroundTaskExecutor.class);

	private BackgroundTaskExecutor _backgroundTaskExecutor;

	@Reference
	private CTCollectionLocalService _ctCollectionLocalService;

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	@Reference
	private CTSchemaVersionLocalService _ctSchemaVersionLocalService;

	@Reference
	private CTServiceRegistry _ctServiceRegistry;

	@Reference
	private CTUserNotificationHelper _ctUserNotificationHelper;

	@Reference
	private MultiVMPool _multiVMPool;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}