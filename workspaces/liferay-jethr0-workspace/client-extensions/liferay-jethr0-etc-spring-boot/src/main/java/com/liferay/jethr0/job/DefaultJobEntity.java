/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.job;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class DefaultJobEntity extends BaseJobEntity {

	@Override
	public String getJenkinsJobName() {
		return getName();
	}

	protected DefaultJobEntity(JSONObject jsonObject) {
		super(jsonObject);
	}

}