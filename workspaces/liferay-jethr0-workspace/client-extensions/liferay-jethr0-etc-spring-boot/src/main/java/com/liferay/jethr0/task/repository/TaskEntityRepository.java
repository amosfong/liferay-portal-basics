/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.task.repository;

import com.liferay.jethr0.entity.repository.BaseEntityRepository;
import com.liferay.jethr0.task.TaskEntity;
import com.liferay.jethr0.task.dalo.TaskEntityDALO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class TaskEntityRepository extends BaseEntityRepository<TaskEntity> {

	@Override
	public TaskEntityDALO getEntityDALO() {
		return _taskEntityDALO;
	}

	@Autowired
	private TaskEntityDALO _taskEntityDALO;

}