/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.search.engine.adapter.ccr;

import com.liferay.portal.search.engine.adapter.ccr.UnfollowCCRRequest;
import com.liferay.portal.search.engine.adapter.ccr.UnfollowCCRResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(service = UnfollowCCRRequestExecutor.class)
public class UnfollowCCRRequestExecutorImpl
	implements UnfollowCCRRequestExecutor {

	@Override
	public UnfollowCCRResponse execute(UnfollowCCRRequest unfollowCCRRequest) {
		throw new UnsupportedOperationException();
	}

}