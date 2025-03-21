/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.collapse;

import com.liferay.portal.search.collapse.CollapseBuilder;
import com.liferay.portal.search.collapse.CollapseBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(service = CollapseBuilderFactory.class)
public class CollapseBuilderFactoryImpl implements CollapseBuilderFactory {

	@Override
	public CollapseBuilder builder() {
		return new CollapseBuilderImpl();
	}

}