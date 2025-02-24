/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;

import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class EmailUtil {

	public static String getCheckIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/icon_check.png");
	}

	public static String getEmailBannerURL(String frequency) {
		if (Objects.equals(frequency, "daily")) {
			return FaroPropsValues.FARO_URL.concat(
				"/o/osb-faro-web/images/email/ac_email_banner_daily.png");
		}
		else if (Objects.equals(frequency, "monthly")) {
			return FaroPropsValues.FARO_URL.concat(
				"/o/osb-faro-web/images/email/ac_email_banner_monthly.png");
		}

		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/ac_email_banner_weekly.png");
	}

	public static String getEmailHeaderURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/email_header.png");
	}

	public static String getLiferayIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/liferay_logo_1.png");
	}

	public static String getLiferayLogoIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/liferay_logo.png");
	}

	public static String getLogoIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/ac_chart.png");
	}

	public static String getShareIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/icon_share.png");
	}

	public static String getTitleIconURL() {
		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/ac_title.png");
	}

	public static String getTrendIconURL(String trend) {
		if (Objects.equals(trend, "NEGATIVE")) {
			return FaroPropsValues.FARO_URL.concat(
				"/o/osb-faro-web/images/email/icon_order_arrow_down.png");
		}
		else if (Objects.equals(trend, "POSITIVE")) {
			return FaroPropsValues.FARO_URL.concat(
				"/o/osb-faro-web/images/email/icon_order_arrow_up.png");
		}

		return FaroPropsValues.FARO_URL.concat(
			"/o/osb-faro-web/images/email/icon_empty.png");
	}

	public static String getWorkspaceURL(Group group) {
		StringBuilder sb = new StringBuilder(4);

		sb.append(FaroPropsValues.FARO_URL);
		sb.append("/workspace");

		String friendlyURL = group.getFriendlyURL();

		if ((friendlyURL != null) && !friendlyURL.isEmpty()) {
			sb.append(friendlyURL);
		}
		else {
			sb.append(StringPool.SLASH);
			sb.append(group.getGroupId());
		}

		return sb.toString();
	}

	public static String getWorkspaceURL(String channelId, Group group) {
		StringBuilder sb = new StringBuilder(4);

		sb.append(getWorkspaceURL(group));
		sb.append(StringPool.SLASH);
		sb.append(channelId);
		sb.append("/sites");

		return sb.toString();
	}

}