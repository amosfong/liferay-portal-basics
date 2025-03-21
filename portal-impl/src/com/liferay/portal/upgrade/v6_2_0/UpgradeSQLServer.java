/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Douglas Wong
 * @author Samuel Ziemer
 */
public class UpgradeSQLServer extends UpgradeProcess {

	protected void convertColumnsToUnicode() {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			dropNonunicodeTableIndexes();

			StringBundler sb = new StringBundler(12);

			sb.append("select sysobjects.name as table_name, syscolumns.name ");
			sb.append("AS column_name, systypes.name as data_type, ");
			sb.append("syscolumns.length, syscolumns.isnullable as ");
			sb.append("is_nullable FROM sysobjects inner join syscolumns on ");
			sb.append("sysobjects.id = syscolumns.id inner join systypes on ");
			sb.append("syscolumns.xtype = systypes.xtype where ");
			sb.append("(sysobjects.xtype = 'U') and (sysobjects.category != ");
			sb.append("2) and ");
			sb.append(_FILTER_NONUNICODE_DATA_TYPES);
			sb.append(" and ");
			sb.append(_FILTER_EXCLUDED_TABLES);
			sb.append(" order by sysobjects.name, syscolumns.colid");

			String sql = sb.toString();

			try (PreparedStatement preparedStatement =
					connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					String tableName = resultSet.getString("table_name");

					if (!isPortal62TableName(tableName)) {
						continue;
					}

					String columnName = resultSet.getString("column_name");
					String dataType = resultSet.getString("data_type");
					boolean nullable = resultSet.getBoolean("is_nullable");

					if (dataType.equals("varchar")) {
						int length = resultSet.getInt("length");

						convertVarcharColumn(
							tableName, columnName, length, nullable);
					}
					else if (dataType.equals("ntext") ||
							 dataType.equals("text")) {

						convertTextColumn(tableName, columnName, nullable);
					}
				}

				for (String addPrimaryKeySQL : _addPrimaryKeySQLs) {
					runSQL(addPrimaryKeySQL);
				}
			}
			catch (Exception exception) {
				_log.error(exception);
			}
		}
	}

	protected void convertTextColumn(
			String tableName, String columnName, boolean nullable)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Updating ", tableName, ".", columnName, " to use ",
					"nvarchar(max)"));
		}

		StringBundler sb = new StringBundler(4);

		sb.append("alter table ");
		sb.append(tableName);
		sb.append(" add temp nvarchar(max)");

		if (!nullable) {
			sb.append(" not null");
		}

		runSQL(sb.toString());

		runSQL(
			StringBundler.concat(
				"update ", tableName, " set temp = ", columnName));

		runSQL(
			StringBundler.concat(
				"alter table ", tableName, " drop column ", columnName));

		runSQL(
			StringBundler.concat(
				"exec sp_rename \'", tableName, ".temp\', \'", columnName,
				"\', \'column\'"));
	}

	protected void convertVarcharColumn(
			String tableName, String columnName, int length, boolean nullable)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Updating ", tableName, ".", columnName,
					" to use nvarchar"));
		}

		StringBundler sb = new StringBundler(8);

		sb.append("alter table ");
		sb.append(tableName);
		sb.append(" alter column ");
		sb.append(columnName);
		sb.append(" nvarchar(");

		if (length == -1) {
			sb.append("max");
		}
		else {
			sb.append(length);
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		if (!nullable) {
			sb.append(" not null");
		}

		runSQL(sb.toString());
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (DBManagerUtil.getDBType() != DBType.SQLSERVER) {
			return;
		}

		convertColumnsToUnicode();
	}

	protected void dropNonunicodeTableIndexes() {
		StringBundler sb = new StringBundler(14);

		sb.append("select distinct sysobjects.name as table_name, ");
		sb.append("sysindexes.name as index_name FROM sysobjects inner join ");
		sb.append("sysindexes on sysobjects.id = sysindexes.id inner join ");
		sb.append("syscolumns on sysobjects.id = syscolumns.id inner join ");
		sb.append("sysindexkeys on ((sysobjects.id = sysindexkeys.id) and ");
		sb.append("(syscolumns.colid = sysindexkeys.colid) and ");
		sb.append("(sysindexes.indid = sysindexkeys.indid)) inner join ");
		sb.append("systypes on syscolumns.xtype = systypes.xtype where ");
		sb.append("(sysobjects.type = 'U') and (sysobjects.category != 2) ");
		sb.append("and ");
		sb.append(_FILTER_NONUNICODE_DATA_TYPES);
		sb.append(" and ");
		sb.append(_FILTER_EXCLUDED_TABLES);
		sb.append(" order by sysobjects.name, sysindexes.name");

		String sql = sb.toString();

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String tableName = resultSet.getString("table_name");

				if (!isPortal62TableName(tableName)) {
					continue;
				}

				String indexName = resultSet.getString("index_name");

				if (_log.isInfoEnabled()) {
					_log.info(
						StringBundler.concat(
							"Dropping index ", tableName, ".", indexName));
				}

				String indexNameUpperCase = StringUtil.toUpperCase(indexName);

				if (indexNameUpperCase.startsWith("PK")) {
					String primaryKeyColumnNames = StringUtil.merge(
						getPrimaryKeyColumnNames(indexName));

					runSQL(
						StringBundler.concat(
							"alter table ", tableName, " drop constraint ",
							indexName));

					_addPrimaryKeySQLs.add(
						StringBundler.concat(
							"alter table ", tableName, " add primary key (",
							primaryKeyColumnNames, ")"));
				}
				else {
					runSQL(
						StringBundler.concat(
							"drop index ", indexName, " on ", tableName));
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}
	}

	protected List<String> getPrimaryKeyColumnNames(String indexName) {
		List<String> columnNames = new ArrayList<>();

		StringBundler sb = new StringBundler(9);

		sb.append("select distinct syscolumns.name as column_name from ");
		sb.append("sysobjects inner join syscolumns on sysobjects.id = ");
		sb.append("syscolumns.id inner join sysindexes on sysobjects.id = ");
		sb.append("sysindexes.id inner join sysindexkeys on ((sysobjects.id ");
		sb.append("= sysindexkeys.id) and (syscolumns.colid = ");
		sb.append("sysindexkeys.colid) and (sysindexes.indid = ");
		sb.append("sysindexkeys.indid)) where sysindexes.name = '");
		sb.append(indexName);
		sb.append("'");

		String sql = sb.toString();

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String columnName = resultSet.getString("column_name");

				columnNames.add(columnName);
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return columnNames;
	}

	private static final String _FILTER_EXCLUDED_TABLES =
		"(sysobjects.name not like 'Counter') and (sysobjects.name not like " +
			"'QUARTZ%')";

	private static final String _FILTER_NONUNICODE_DATA_TYPES =
		"((systypes.name = 'ntext') OR (systypes.name = 'text') OR " +
			"(systypes.name = 'varchar'))";

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeSQLServer.class);

	private final List<String> _addPrimaryKeySQLs = new ArrayList<>();

}