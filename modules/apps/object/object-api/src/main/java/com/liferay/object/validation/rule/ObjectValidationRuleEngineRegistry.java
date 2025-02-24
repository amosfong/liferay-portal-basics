/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.validation.rule;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Marco Leo
 */
public interface ObjectValidationRuleEngineRegistry {

	public ObjectValidationRuleEngine getObjectValidationRuleEngine(
			long companyId, String key)
		throws PortalException;

	public List<ObjectValidationRuleEngine> getObjectValidationRuleEngines(
		long companyId, String objectDefinitionName);

	public boolean hasObjectValidationRuleEngine(
		long companyId, String objectActionExecutorKey);

}