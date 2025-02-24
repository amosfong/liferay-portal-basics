/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.InvalidParameterException;

/**
 * @author Tina Tian
 */
public class PortletIdCodec {

	public static final int PORTLET_INSTANCE_KEY_MAX_LENGTH =
		255 -
			(PortletIdCodec._INSTANCE_SEPARATOR.length() +
				PortletIdCodec._USER_SEPARATOR.length() + 31);

	public static String decodeInstanceId(String portletId) {
		int index = portletId.indexOf(_INSTANCE_SEPARATOR);

		if (index == -1) {
			return null;
		}

		return portletId.substring(index + _INSTANCE_SEPARATOR.length());
	}

	public static String decodePortletName(String portletId) {
		int x = portletId.indexOf(_USER_SEPARATOR);

		if (x != -1) {
			return portletId.substring(0, x);
		}

		int y = portletId.indexOf(_INSTANCE_SEPARATOR);

		if (y != -1) {
			return portletId.substring(0, y);
		}

		return portletId;
	}

	public static long decodeUserId(String portletId) {
		int x = portletId.indexOf(_USER_SEPARATOR);

		if (x == -1) {
			return 0;
		}

		int y = portletId.indexOf(_INSTANCE_SEPARATOR);

		if (y != -1) {
			return GetterUtil.getLong(
				portletId.substring(x + _USER_SEPARATOR.length(), y));
		}

		return GetterUtil.getLong(
			portletId.substring(x + _USER_SEPARATOR.length()));
	}

	public static ObjectValuePair<Long, String> decodeUserIdAndInstanceId(
		String userIdAndInstanceId) {

		if (userIdAndInstanceId == null) {
			throw new InvalidParameterException(
				"User ID and instance ID are null");
		}

		if (userIdAndInstanceId.isEmpty()) {
			return new ObjectValuePair<>(0L, null);
		}

		if (userIdAndInstanceId.indexOf(CharPool.SLASH) != -1) {
			throw new InvalidParameterException(
				"User ID and instance ID contain slashes");
		}

		int underlineCount = StringUtil.count(
			userIdAndInstanceId, CharPool.UNDERLINE);

		if (underlineCount > 1) {
			throw new InvalidParameterException(
				"User ID and instance ID has more than one underscore");
		}

		if (underlineCount == 1) {
			int index = userIdAndInstanceId.indexOf(CharPool.UNDERLINE);

			long userId = GetterUtil.getLong(
				userIdAndInstanceId.substring(0, index), -1);

			if (userId == -1) {
				throw new InvalidParameterException("User ID is not a number");
			}

			String instanceId = null;

			if (index < (userIdAndInstanceId.length() - 1)) {
				instanceId = userIdAndInstanceId.substring(index + 1);
			}

			return new ObjectValuePair<>(userId, instanceId);
		}

		return new ObjectValuePair<>(0L, userIdAndInstanceId);
	}

	public static String encode(String portletName) {
		return encode(portletName, 0, StringUtil.randomString(12));
	}

	public static String encode(String portletName, long userId) {
		return encode(portletName, userId, null);
	}

	public static String encode(
		String portletName, long userId, String instanceId) {

		StringBundler sb = new StringBundler(5);

		sb.append(portletName);

		if (userId > 0) {
			sb.append(_USER_SEPARATOR);
			sb.append(userId);
		}

		if (Validator.isNotNull(instanceId)) {
			sb.append(_INSTANCE_SEPARATOR);
			sb.append(instanceId);
		}

		return sb.toString();
	}

	public static String encode(String portletName, String instanceId) {
		return encode(portletName, 0, instanceId);
	}

	public static String encodeUserIdAndInstanceId(
		long userId, String instanceId) {

		if ((userId <= 0) && Validator.isBlank(instanceId)) {
			return null;
		}

		StringBundler sb = new StringBundler(3);

		if (userId > 0) {
			sb.append(userId);
			sb.append(StringPool.UNDERLINE);
		}

		if (instanceId != null) {
			sb.append(instanceId);
		}

		return sb.toString();
	}

	public static String generateInstanceId() {
		return StringUtil.randomString(12);
	}

	public static boolean hasInstanceId(String portletId) {
		return portletId.contains(_INSTANCE_SEPARATOR);
	}

	public static boolean hasUserId(String portletId) {
		return portletId.contains(_USER_SEPARATOR);
	}

	public static void validatePortletName(String portletName) {
		String keyword = null;

		if (portletName.contains(_INSTANCE_SEPARATOR)) {
			keyword = _INSTANCE_SEPARATOR;
		}
		else if (portletName.contains(_USER_SEPARATOR)) {
			keyword = _USER_SEPARATOR;
		}

		if (keyword != null) {
			throw new InvalidParameterException(
				StringBundler.concat(
					"The portlet name \"", portletName,
					"\" must not contain the keyword ", keyword));
		}
	}

	private static final String _INSTANCE_SEPARATOR = "_INSTANCE_";

	private static final String _USER_SEPARATOR = "_USER_";

}