/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.expando.model.impl;

import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ExpandoTable service. Represents a row in the &quot;ExpandoTable&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ExpandoTableModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExpandoTableImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoTableImpl
 * @generated
 */
@JSON(strict = true)
public class ExpandoTableModelImpl
	extends BaseModelImpl<ExpandoTable> implements ExpandoTableModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a expando table model instance should use the <code>ExpandoTable</code> interface instead.
	 */
	public static final String TABLE_NAME = "ExpandoTable";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"tableId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("tableId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ExpandoTable (mvccVersion LONG default 0 not null,tableId LONG not null primary key,companyId LONG,classNameId LONG,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table ExpandoTable";

	public static final String ORDER_BY_JPQL =
		" ORDER BY expandoTable.tableId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ExpandoTable.tableId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TABLEID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.expando.kernel.model.ExpandoTable"));

	public ExpandoTableModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _tableId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTableId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _tableId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ExpandoTable.class;
	}

	@Override
	public String getModelClassName() {
		return ExpandoTable.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ExpandoTable, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ExpandoTable, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoTable, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ExpandoTable)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ExpandoTable, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ExpandoTable, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ExpandoTable)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ExpandoTable, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ExpandoTable, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<ExpandoTable, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<ExpandoTable, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<ExpandoTable, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", ExpandoTable::getMvccVersion);
			attributeGetterFunctions.put("tableId", ExpandoTable::getTableId);
			attributeGetterFunctions.put(
				"companyId", ExpandoTable::getCompanyId);
			attributeGetterFunctions.put(
				"classNameId", ExpandoTable::getClassNameId);
			attributeGetterFunctions.put("name", ExpandoTable::getName);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<ExpandoTable, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<ExpandoTable, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<ExpandoTable, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<ExpandoTable, Long>)ExpandoTable::setMvccVersion);
			attributeSetterBiConsumers.put(
				"tableId",
				(BiConsumer<ExpandoTable, Long>)ExpandoTable::setTableId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<ExpandoTable, Long>)ExpandoTable::setCompanyId);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<ExpandoTable, Long>)ExpandoTable::setClassNameId);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<ExpandoTable, String>)ExpandoTable::setName);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getTableId() {
		return _tableId;
	}

	@Override
	public void setTableId(long tableId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tableId = tableId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoTable toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ExpandoTable>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ExpandoTableImpl expandoTableImpl = new ExpandoTableImpl();

		expandoTableImpl.setMvccVersion(getMvccVersion());
		expandoTableImpl.setTableId(getTableId());
		expandoTableImpl.setCompanyId(getCompanyId());
		expandoTableImpl.setClassNameId(getClassNameId());
		expandoTableImpl.setName(getName());

		expandoTableImpl.resetOriginalValues();

		return expandoTableImpl;
	}

	@Override
	public ExpandoTable cloneWithOriginalValues() {
		ExpandoTableImpl expandoTableImpl = new ExpandoTableImpl();

		expandoTableImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		expandoTableImpl.setTableId(
			this.<Long>getColumnOriginalValue("tableId"));
		expandoTableImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		expandoTableImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		expandoTableImpl.setName(this.<String>getColumnOriginalValue("name"));

		return expandoTableImpl;
	}

	@Override
	public int compareTo(ExpandoTable expandoTable) {
		long primaryKey = expandoTable.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ExpandoTable)) {
			return false;
		}

		ExpandoTable expandoTable = (ExpandoTable)object;

		long primaryKey = expandoTable.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ExpandoTable> toCacheModel() {
		ExpandoTableCacheModel expandoTableCacheModel =
			new ExpandoTableCacheModel();

		expandoTableCacheModel.mvccVersion = getMvccVersion();

		expandoTableCacheModel.tableId = getTableId();

		expandoTableCacheModel.companyId = getCompanyId();

		expandoTableCacheModel.classNameId = getClassNameId();

		expandoTableCacheModel.name = getName();

		String name = expandoTableCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			expandoTableCacheModel.name = null;
		}

		return expandoTableCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ExpandoTable, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ExpandoTable, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoTable, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ExpandoTable)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ExpandoTable>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ExpandoTable.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _tableId;
	private long _companyId;
	private long _classNameId;
	private String _name;

	public <T> T getColumnValue(String columnName) {
		Function<ExpandoTable, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ExpandoTable)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("tableId", _tableId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("name", _name);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("tableId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("classNameId", 8L);

		columnBitmasks.put("name", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ExpandoTable _escapedModel;

}