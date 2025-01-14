/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments;

import com.liferay.segments.context.Context;

/**
 * Provides methods to to obtain the {@link com.liferay.segments.model.SegmentsEntry SegmentsEntry} from a userId.
 *
 * @author Cristina González
 */
public interface SegmentsEntryRetriever {

	public long[] getSegmentsEntryIds(
		long groupId, long userId, Context context, long[] segmentEntryIds);

}