/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.validation;

import com.liferay.portal.kernel.model.ClassedModel;

/**
 * @author Máté Thurzó
 */
public interface ModelValidator<T extends ClassedModel> {

	public ModelValidationResults validateModel(T model);

}