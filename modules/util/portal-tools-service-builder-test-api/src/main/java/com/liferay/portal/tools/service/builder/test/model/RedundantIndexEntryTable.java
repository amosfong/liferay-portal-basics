/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;RedundantIndexEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see RedundantIndexEntry
 * @generated
 */
public class RedundantIndexEntryTable
	extends BaseTable<RedundantIndexEntryTable> {

	public static final RedundantIndexEntryTable INSTANCE =
		new RedundantIndexEntryTable();

	public final Column<RedundantIndexEntryTable, Long> redundantIndexEntryId =
		createColumn(
			"redundantIndexEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<RedundantIndexEntryTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<RedundantIndexEntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private RedundantIndexEntryTable() {
		super("RedundantIndexEntry", RedundantIndexEntryTable::new);
	}

}