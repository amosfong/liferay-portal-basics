/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;FragmentCollection&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentCollection
 * @generated
 */
public class FragmentCollectionTable
	extends BaseTable<FragmentCollectionTable> {

	public static final FragmentCollectionTable INSTANCE =
		new FragmentCollectionTable();

	public final Column<FragmentCollectionTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<FragmentCollectionTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, String> externalReferenceCode =
		createColumn(
			"externalReferenceCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Long> fragmentCollectionId =
		createColumn(
			"fragmentCollectionId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<FragmentCollectionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, String> fragmentCollectionKey =
		createColumn(
			"fragmentCollectionKey", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, String> description =
		createColumn(
			"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FragmentCollectionTable, Date> lastPublishDate =
		createColumn(
			"lastPublishDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);

	private FragmentCollectionTable() {
		super("FragmentCollection", FragmentCollectionTable::new);
	}

}