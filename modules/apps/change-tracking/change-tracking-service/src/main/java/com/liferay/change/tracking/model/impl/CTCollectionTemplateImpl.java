/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.service.CTCollectionTemplateLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class CTCollectionTemplateImpl extends CTCollectionTemplateBaseImpl {

	@Override
	public JSONObject getJSONObject() {
		return null;
	}

	@Override
	public String getParsedPublicationDescription() {
		return CTCollectionTemplateLocalServiceUtil.parseTokens(
			getCtCollectionTemplateId(), getPublicationDescription());
	}

	@Override
	public String getParsedPublicationName() {
		return CTCollectionTemplateLocalServiceUtil.parseTokens(
			getCtCollectionTemplateId(), getPublicationName());
	}

	@Override
	public String getPublicationDescription() {
		JSONObject jsonObject = getJSONObject();

		return String.valueOf(jsonObject.get("description"));
	}

	@Override
	public String getPublicationName() {
		JSONObject jsonObject = getJSONObject();

		return String.valueOf(jsonObject.get("name"));
	}

	@Override
	public String getUserName() {
		User user = UserLocalServiceUtil.fetchUser(getUserId());

		if (user == null) {
			return StringPool.BLANK;
		}

		return user.getFullName();
	}

}