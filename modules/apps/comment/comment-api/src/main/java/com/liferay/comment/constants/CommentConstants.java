/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.comment.constants;

import com.liferay.message.boards.model.MBDiscussion;

/**
 * @author Adolfo Pérez
 */
public class CommentConstants {

	public static final String SERVICE_NAME = "com.liferay.comment";

	public static Class<?> getDiscussionClass() {
		return MBDiscussion.class;
	}

	public static String getDiscussionClassName() {
		return MBDiscussion.class.getName();
	}

}