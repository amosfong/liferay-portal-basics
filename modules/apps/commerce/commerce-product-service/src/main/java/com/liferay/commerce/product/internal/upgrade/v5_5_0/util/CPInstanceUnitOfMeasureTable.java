/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v5_5_0.util;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 * @generated
 * @see com.liferay.portal.tools.upgrade.table.builder.UpgradeTableBuilder
 */
public class CPInstanceUnitOfMeasureTable {

	public static UpgradeProcess create() {
		return new UpgradeProcess() {

			@Override
			protected void doUpgrade() throws Exception {
				if (!hasTable(_TABLE_NAME)) {
					runSQL(_TABLE_SQL_CREATE);
				}
			}

		};
	}

	private static final String _TABLE_NAME = "CPInstanceUOM";

	private static final String _TABLE_SQL_CREATE =
		"create table CPInstanceUOM (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CPInstanceUOMId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPInstanceId LONG,active_ BOOLEAN,incrementalOrderQuantity BIGDECIMAL null,key_ VARCHAR(75) null,name STRING null,precision_ INTEGER,primary_ BOOLEAN,priority DOUBLE,rate BIGDECIMAL null,sku VARCHAR(75) null,primary key (CPInstanceUOMId, ctCollectionId))";

}