/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

/**
 * @author Brian Wing Shun Chan
 */
public interface AttachedModel extends TypedModel {

	public long getClassPK();

	public void setClassPK(long classPK);

}