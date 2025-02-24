/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.formatter;

import java.util.Collection;
import java.util.Locale;

/**
 * @author Jorge Ferrer
 *
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Deprecated
public interface InfoCollectionTextFormatter<T> {

	public String format(Collection<T> collection, Locale locale);

}