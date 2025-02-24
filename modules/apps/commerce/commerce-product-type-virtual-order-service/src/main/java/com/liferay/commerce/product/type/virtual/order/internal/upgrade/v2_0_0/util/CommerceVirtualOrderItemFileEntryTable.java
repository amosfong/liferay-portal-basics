/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.internal.upgrade.v2_0_0.util;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 * @generated
 * @see com.liferay.portal.tools.upgrade.table.builder.UpgradeTableBuilder
 */
public class CommerceVirtualOrderItemFileEntryTable {

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

	private static final String _TABLE_NAME = "CVirtualOrderItemFileEntry";

	private static final String _TABLE_SQL_CREATE =
		"create table CVirtualOrderItemFileEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,cVirtualOrderItemFileEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceVirtualOrderItemId LONG,fileEntryId LONG,url VARCHAR(255) null,usages INTEGER,version VARCHAR(75) null)";

}