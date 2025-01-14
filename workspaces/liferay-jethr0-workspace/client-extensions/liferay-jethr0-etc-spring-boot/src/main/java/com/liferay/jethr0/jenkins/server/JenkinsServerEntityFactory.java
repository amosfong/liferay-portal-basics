/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.jenkins.server;

import com.liferay.jethr0.entity.factory.BaseEntityFactory;
import com.liferay.jethr0.event.jenkins.client.JenkinsClient;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class JenkinsServerEntityFactory
	extends BaseEntityFactory<JenkinsServerEntity> {

	@Override
	public JenkinsServerEntity newEntity(JSONObject jsonObject) {
		return new DefaultJenkinsServerEntity(_jenkinsClient, jsonObject);
	}

	protected JenkinsServerEntityFactory() {
		super(JenkinsServerEntity.class);
	}

	@Autowired
	private JenkinsClient _jenkinsClient;

}