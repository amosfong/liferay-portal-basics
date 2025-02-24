/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.model.adapter;

import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.portal.kernel.model.StagedModel;

/**
 * @author Akos Thurzo
 */
public interface StagedExpandoTable extends ExpandoTable, StagedModel {
}