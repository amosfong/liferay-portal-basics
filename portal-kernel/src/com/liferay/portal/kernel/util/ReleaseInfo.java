/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringPool;

import java.text.DateFormat;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ReleaseInfo {

	public static final int RELEASE_6_0_12_BUILD_NUMBER = 6012;

	public static final int RELEASE_6_1_0_BUILD_NUMBER = 6100;

	public static final int RELEASE_6_1_1_BUILD_NUMBER = 6101;

	public static final int RELEASE_6_2_0_BUILD_NUMBER = 6200;

	public static final int RELEASE_7_0_0_BUILD_NUMBER = 7000;

	public static final int RELEASE_7_0_1_BUILD_NUMBER = 7001;

	public static final int RELEASE_7_0_2_BUILD_NUMBER = 7002;

	public static final int RELEASE_7_0_3_BUILD_NUMBER = 7003;

	public static final int RELEASE_7_0_4_BUILD_NUMBER = 7004;

	public static final int RELEASE_7_0_5_BUILD_NUMBER = 7005;

	public static final int RELEASE_7_0_6_BUILD_NUMBER = 7006;

	public static final int RELEASE_7_0_10_BUILD_NUMBER = 7010;

	public static final int RELEASE_7_1_0_BUILD_NUMBER = 7100;

	public static final int RELEASE_7_1_1_BUILD_NUMBER = 7101;

	public static final int RELEASE_7_1_2_BUILD_NUMBER = 7102;

	public static final int RELEASE_7_1_10_BUILD_NUMBER = 7110;

	public static final int RELEASE_7_2_0_BUILD_NUMBER = 7200;

	public static final int RELEASE_7_2_1_BUILD_NUMBER = 7201;

	public static final int RELEASE_7_2_10_BUILD_NUMBER = 7210;

	public static final int RELEASE_7_3_0_BUILD_NUMBER = 7300;

	public static final int RELEASE_7_3_1_BUILD_NUMBER = 7301;

	public static final int RELEASE_7_3_2_BUILD_NUMBER = 7302;

	public static final int RELEASE_7_3_3_BUILD_NUMBER = 7303;

	public static final int RELEASE_7_3_4_BUILD_NUMBER = 7304;

	public static final int RELEASE_7_3_5_BUILD_NUMBER = 7305;

	public static final int RELEASE_7_3_10_BUILD_NUMBER = 7310;

	public static final int RELEASE_7_4_0_BUILD_NUMBER = 7400;

	public static final int RELEASE_7_4_1_BUILD_NUMBER = 7401;

	public static final int RELEASE_7_4_2_BUILD_NUMBER = 7402;

	public static final int RELEASE_7_4_3_BUILD_NUMBER = 7403;

	public static final int RELEASE_7_4_10_BUILD_NUMBER = 7410;

	public static final int RELEASE_7_4_11_BUILD_NUMBER = 7411;

	public static final int RELEASE_7_4_12_BUILD_NUMBER = 7412;

	public static final int RELEASE_7_4_13_BUILD_NUMBER = 7413;

	public static Date getBuildDate() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		return GetterUtil.getDate(_DATE, df);
	}

	public static int getBuildNumber() {
		return _BUILD_NUMBER;
	}

	public static String getCodeName() {
		return _CODE_NAME;
	}

	public static String getName() {
		return _NAME;
	}

	public static int getParentBuildNumber() {
		return _PARENT_BUILD_NUMBER;
	}

	public static String getReleaseInfo() {
		if (_releaseInfo == null) {
			StringBuilder sb = new StringBuilder(_RELEASE_INFO_PREFIX);

			sb.append(_NAME);
			sb.append(" ");
			sb.append(_VERSION_DISPLAY_NAME);
			sb.append(" (");
			sb.append(_DATE);
			sb.append(")");
			sb.append(_RELEASE_INFO_SUFFIX);

			_releaseInfo = sb.toString();
		}

		return _releaseInfo;
	}

	public static String getServerInfo() {
		if (_serverInfo == null) {
			_serverInfo = _NAME + " / " + _VERSION;
		}

		return _serverInfo;
	}

	public static boolean isDXP() {
		if (_NAME.contains("Community")) {
			return false;
		}

		return true;
	}

	public static String getVendor() {
		return _VENDOR;
	}

	public static String getVersion() {
		return _VERSION;
	}

	public static String getVersionDisplayName() {
		return _VERSION_DISPLAY_NAME;
	}

	private static final String _BUILD = "7403";

	private static final int _BUILD_NUMBER = GetterUtil.getInteger(_BUILD);

	private static final String _CODE_NAME = "Cavanaugh";

	private static final String _DATE = "December 20, 2024";

	private static final String _NAME = "Liferay Community Edition Portal";

	private static final int _PARENT_BUILD_NUMBER = _BUILD_NUMBER;

	private static final String _RELEASE_INFO_PREFIX = System.getProperty(
		"liferay.release.info.prefix", StringPool.BLANK);

	private static final String _RELEASE_INFO_SUFFIX = System.getProperty(
		"liferay.release.info.suffix", StringPool.BLANK);

	private static final String _VENDOR = "Liferay, Inc.";

	private static final String _VERSION = "7.4.3.131";

	private static final String _VERSION_DISPLAY_NAME =
		"7.4.3.131 CE GA131";

	private static String _releaseInfo;
	private static String _serverInfo;

}