/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.dto.v1_0.util;

import com.liferay.headless.delivery.dto.v1_0.CreatorStatistics;
import com.liferay.message.boards.service.MBStatsUserLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * @author Luis Miguel Barcos
 */
public class CreatorStatisticsUtil {

	public static CreatorStatistics toCreatorStatistics(
			long groupId, String languageId,
			MBStatsUserLocalService mbStatsUserLocalService, UriInfo uriInfo,
			User user)
		throws PortalException {

		return new CreatorStatistics() {
			{
				setJoinDate(user::getCreateDate);
				setLastPostDate(
					() -> {
						if (uriInfo != null) {
							MultivaluedMap<String, String> parameters =
								uriInfo.getQueryParameters();

							String nestedFields = parameters.getFirst(
								"nestedFields");

							if ((nestedFields != null) &&
								nestedFields.contains("lastPostDate")) {

								return mbStatsUserLocalService.
									getLastPostDateByUserId(
										user.getGroupId(), user.getUserId());
							}
						}

						return null;
					});
				setPostsNumber(
					() -> Math.toIntExact(
						mbStatsUserLocalService.getMessageCountByUserId(
							user.getUserId())));
				setRank(
					() -> {
						String[] ranks = mbStatsUserLocalService.getUserRank(
							groupId, languageId, user.getUserId());

						if (ranks[1].equals(StringPool.BLANK)) {
							return ranks[0];
						}

						return ranks[1];
					});
			}
		};
	}

}