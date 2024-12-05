/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.content.targeting.upgrade.internal.upgrade.v1_0_0.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;

/**
 * @author Eduardo García
 */
public class PreviousVisitedSiteRuleConverter implements RuleConverter {

	public static final String RULE_CONVERTER_KEY = "PreviousVisitedSiteRule";

	public PreviousVisitedSiteRuleConverter(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	public void convert(
		long companyId, Criteria criteria, String typeSettings) {

		try {
			JSONArray jsonArray = _jsonFactory.createJSONArray(typeSettings);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String value = jsonObject.getString("value");

				SegmentsCriteriaContributor.contribute(
					criteria, "contains(referrerURL, '" + value + "')",
					Criteria.Conjunction.AND, "context", Criteria.Type.CONTEXT);
			}
		}
		catch (JSONException jsonException) {
			_log.error(
				"Unable to convert previous visited site rule with type " +
					"settings" + typeSettings,
				jsonException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PreviousVisitedSiteRuleConverter.class);

	private final JSONFactory _jsonFactory;

}