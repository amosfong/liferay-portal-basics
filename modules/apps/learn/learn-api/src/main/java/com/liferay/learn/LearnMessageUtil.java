/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learn;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.Objects;

/**
 * @author Brian Wing Shun Chan
 */
public class LearnMessageUtil {

	public static JSONObject getJSONObject(String resource) {
		return (JSONObject)WebCachePoolUtil.get(
			JSONObjectWebCacheItem.class.getName() + StringPool.POUND +
				resource,
			new JSONObjectWebCacheItem(resource));
	}

	public static LearnMessage getLearnMessage(
		String key, String languageId, String resource) {

		JSONObject jsonObject = getJSONObject(resource);

		return new LearnMessage(jsonObject, key, languageId);
	}

	public static JSONObject getReactDataJSONObject(String resource) {
		JSONObject learnMessageJSONObject = getJSONObject(resource);

		return JSONUtil.put(resource, learnMessageJSONObject);
	}

	public static JSONObject getReactDataJSONObject(String[] resources) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String resource : resources) {
			JSONObject learnMessageJSONObject = getJSONObject(resource);

			jsonObject.put(resource, learnMessageJSONObject);
		}

		return jsonObject;
	}

	private static final boolean _LEARN_RESOURCES_MODE_DEV = Objects.equals(
		PropsUtil.get("learn.resources.mode"), "dev");

	private static final boolean _LEARN_RESOURCES_MODE_OFF = Objects.equals(
		PropsUtil.get("learn.resources.mode"), "off");

	private static final Log _log = LogFactoryUtil.getLog(
		LearnMessageUtil.class);

	private static class JSONObjectWebCacheItem implements WebCacheItem {

		public JSONObjectWebCacheItem(String resource) {
			_resource = resource;
		}

		@Override
		public JSONObject convert(String key) {
			try {
				if (_LEARN_RESOURCES_MODE_OFF) {
					return JSONFactoryUtil.createJSONObject();
				}

				StringBundler sb = new StringBundler(4);

				if (_LEARN_RESOURCES_MODE_DEV) {
					sb.append("http://localhost:3062/");
				}
				else {
					sb.append("https://s3.amazonaws.com");
					sb.append("/learn-resources.liferay.com/");
				}

				sb.append(_resource);
				sb.append(".json");

				String url = sb.toString();

				if (_log.isDebugEnabled()) {
					_log.debug("Reading " + url);
				}

				return JSONFactoryUtil.createJSONObject(
					HttpUtil.URLtoString(url));
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception);
				}

				return JSONFactoryUtil.createJSONObject();
			}
		}

		@Override
		public long getRefreshTime() {
			if (_LEARN_RESOURCES_MODE_DEV) {
				return 0;
			}

			// 4 hours

			return 14400000;
		}

		private final String _resource;

	}

}