/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.service.impl;

import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.service.BatchEngineImportTaskErrorLocalService;
import com.liferay.batch.engine.service.base.BatchEngineImportTaskServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(
	property = {
		"json.web.service.context.name=batchengine",
		"json.web.service.context.path=BatchEngineImportTask"
	},
	service = AopService.class
)
public class BatchEngineImportTaskServiceImpl
	extends BatchEngineImportTaskServiceBaseImpl {

	@Override
	public BatchEngineImportTask addBatchEngineImportTask(
			String externalReferenceCode, long companyId, long userId,
			long batchSize, String callbackURL, String className,
			byte[] content, String contentType, String executeStatus,
			Map<String, String> fieldNameMappingMap, int importStrategy,
			String operation, Map<String, Serializable> parameters,
			String taskItemDelegateName)
		throws PortalException {

		_checkPermission(companyId);

		return batchEngineImportTaskLocalService.addBatchEngineImportTask(
			externalReferenceCode, companyId, userId, batchSize, callbackURL,
			className, content, contentType, executeStatus, fieldNameMappingMap,
			importStrategy, operation, parameters, taskItemDelegateName);
	}

	@Override
	public BatchEngineImportTask addBatchEngineImportTask(
			String externalReferenceCode, long companyId, long userId,
			long batchSize, String callbackURL, String className,
			byte[] content, String contentType, String executeStatus,
			Map<String, String> fieldNameMappingMap, int importStrategy,
			String operation, Map<String, Serializable> parameters,
			String taskItemDelegateName,
			BatchEngineTaskItemDelegate<?> batchEngineTaskItemDelegate)
		throws PortalException {

		_checkPermission(companyId);

		return batchEngineImportTaskLocalService.addBatchEngineImportTask(
			externalReferenceCode, companyId, userId, batchSize, callbackURL,
			className, content, contentType, executeStatus, fieldNameMappingMap,
			importStrategy, operation, parameters, taskItemDelegateName,
			batchEngineTaskItemDelegate);
	}

	@Override
	public BatchEngineImportTask getBatchEngineImportTask(
			long batchEngineImportTaskId)
		throws PortalException {

		BatchEngineImportTask batchEngineImportTask =
			batchEngineImportTaskLocalService.getBatchEngineImportTask(
				batchEngineImportTaskId);

		_checkPermission(batchEngineImportTask);

		return batchEngineImportTask;
	}

	@Override
	public BatchEngineImportTask
			getBatchEngineImportTaskByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws PortalException {

		_checkPermission(companyId);

		BatchEngineImportTask batchEngineImportTask =
			batchEngineImportTaskLocalService.
				getBatchEngineImportTaskByExternalReferenceCode(
					externalReferenceCode, companyId);

		_checkPermission(batchEngineImportTask);

		return batchEngineImportTask;
	}

	@Override
	public List<BatchEngineImportTask> getBatchEngineImportTasks(
			long companyId, int start, int end)
		throws PortalException {

		_checkPermission(companyId);

		return _filterBatchEngineImportTasks(
			batchEngineImportTaskLocalService.getBatchEngineImportTasks(
				companyId, start, end));
	}

	@Override
	public List<BatchEngineImportTask> getBatchEngineImportTasks(
			long companyId, int start, int end,
			OrderByComparator<BatchEngineImportTask> orderByComparator)
		throws PortalException {

		_checkPermission(companyId);

		return _filterBatchEngineImportTasks(
			batchEngineImportTaskLocalService.getBatchEngineImportTasks(
				companyId, start, end, orderByComparator));
	}

	@Override
	public int getBatchEngineImportTasksCount(long companyId)
		throws PortalException {

		_checkPermission(companyId);

		List<BatchEngineImportTask> filteredBatchEngineImportTasks =
			_filterBatchEngineImportTasks(
				batchEngineImportTaskLocalService.getBatchEngineImportTasks(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		return filteredBatchEngineImportTasks.size();
	}

	@Override
	public InputStream openContentInputStream(long batchEngineImportTaskId)
		throws PortalException {

		_checkPermission(
			batchEngineImportTaskLocalService.getBatchEngineImportTask(
				batchEngineImportTaskId));

		return batchEngineImportTaskLocalService.openContentInputStream(
			batchEngineImportTaskId);
	}

	private void _checkPermission(BatchEngineImportTask batchEngineImportTask)
		throws PrincipalException {

		if (!_hasPermission(batchEngineImportTask, getPermissionChecker())) {
			throw new PrincipalException();
		}
	}

	private void _checkPermission(long companyId) throws PrincipalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if ((companyId != permissionChecker.getCompanyId()) &&
			!permissionChecker.isOmniadmin()) {

			throw new PrincipalException();
		}
	}

	private List<BatchEngineImportTask> _filterBatchEngineImportTasks(
			List<BatchEngineImportTask> batchEngineImportTasks)
		throws PrincipalException {

		List<BatchEngineImportTask> filteredBatchEngineImportTasks =
			new ArrayList<>();

		PermissionChecker permissionChecker = getPermissionChecker();

		for (BatchEngineImportTask batchEngineImportTask :
				batchEngineImportTasks) {

			if (_hasPermission(batchEngineImportTask, permissionChecker)) {
				filteredBatchEngineImportTasks.add(batchEngineImportTask);
			}
		}

		return filteredBatchEngineImportTasks;
	}

	private boolean _hasPermission(
		BatchEngineImportTask batchEngineImportTask,
		PermissionChecker permissionChecker) {

		if (permissionChecker.isCompanyAdmin(
				batchEngineImportTask.getCompanyId()) ||
			(batchEngineImportTask.getUserId() ==
				permissionChecker.getUserId())) {

			return true;
		}

		return false;
	}

	@Reference
	private BatchEngineImportTaskErrorLocalService
		_batchEngineImportTaskErrorLocalService;

}