/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.ccr;

import com.liferay.portal.search.engine.adapter.ccr.FollowInfoCCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.FollowInfoCCRResponse;

/**
 * @author Bryan Engler
 */
public interface FollowInfoCCRRequestExecutor {

	public FollowInfoCCRResponse execute(
		FollowInfoCCRRequest followInfoCCRRequest);

}