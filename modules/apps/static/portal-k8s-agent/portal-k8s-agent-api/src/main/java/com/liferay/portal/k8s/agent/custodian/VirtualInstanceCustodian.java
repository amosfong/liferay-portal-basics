/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.k8s.agent.custodian;

import java.util.List;

/**
 * @author Raymond Augé
 */
@FunctionalInterface
public interface VirtualInstanceCustodian {

	public void clean(List<String> virtualInstanceIds);

}