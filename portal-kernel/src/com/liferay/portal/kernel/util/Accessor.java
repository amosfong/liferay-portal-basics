/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author Shuyang Zhou
 */
public interface Accessor<T, A> {

	public A get(T t);

	public Class<A> getAttributeClass();

	public Class<T> getTypeClass();

}