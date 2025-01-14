/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.type;

import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 * @deprecated As of Cavanaugh (7.4.x)
 */
@Deprecated
public interface CommerceNotificationType {

	public String getClassName(Object object);

	public long getClassPK(Object object);

	public String getKey();

	public String getLabel(Locale locale);

}