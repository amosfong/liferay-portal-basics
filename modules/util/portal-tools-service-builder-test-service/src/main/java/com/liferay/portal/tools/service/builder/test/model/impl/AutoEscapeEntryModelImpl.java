/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.service.builder.test.model.AutoEscapeEntry;
import com.liferay.portal.tools.service.builder.test.model.AutoEscapeEntryModel;

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
 * The base model implementation for the AutoEscapeEntry service. Represents a row in the &quot;AutoEscapeEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AutoEscapeEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AutoEscapeEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AutoEscapeEntryImpl
 * @generated
 */
public class AutoEscapeEntryModelImpl
	extends BaseModelImpl<AutoEscapeEntry> implements AutoEscapeEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a auto escape entry model instance should use the <code>AutoEscapeEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "AutoEscapeEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"autoEscapeEntryId", Types.BIGINT},
		{"autoEscapeDisabledColumn", Types.VARCHAR},
		{"autoEscapeEnabledColumn", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("autoEscapeEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("autoEscapeDisabledColumn", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("autoEscapeEnabledColumn", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AutoEscapeEntry (autoEscapeEntryId LONG not null primary key,autoEscapeDisabledColumn VARCHAR(75) null,autoEscapeEnabledColumn VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table AutoEscapeEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY autoEscapeEntry.autoEscapeEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AutoEscapeEntry.autoEscapeEntryId ASC";

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
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long AUTOESCAPEENTRYID_COLUMN_BITMASK = 1L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.AutoEscapeEntry"));

	public AutoEscapeEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _autoEscapeEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAutoEscapeEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _autoEscapeEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AutoEscapeEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AutoEscapeEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AutoEscapeEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AutoEscapeEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AutoEscapeEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AutoEscapeEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AutoEscapeEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AutoEscapeEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AutoEscapeEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AutoEscapeEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AutoEscapeEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<AutoEscapeEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<AutoEscapeEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<AutoEscapeEntry, Object>>();

			attributeGetterFunctions.put(
				"autoEscapeEntryId", AutoEscapeEntry::getAutoEscapeEntryId);
			attributeGetterFunctions.put(
				"autoEscapeDisabledColumn",
				AutoEscapeEntry::getAutoEscapeDisabledColumn);
			attributeGetterFunctions.put(
				"autoEscapeEnabledColumn",
				AutoEscapeEntry::getAutoEscapeEnabledColumn);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<AutoEscapeEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<AutoEscapeEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<AutoEscapeEntry, ?>>();

			attributeSetterBiConsumers.put(
				"autoEscapeEntryId",
				(BiConsumer<AutoEscapeEntry, Long>)
					AutoEscapeEntry::setAutoEscapeEntryId);
			attributeSetterBiConsumers.put(
				"autoEscapeDisabledColumn",
				(BiConsumer<AutoEscapeEntry, String>)
					AutoEscapeEntry::setAutoEscapeDisabledColumn);
			attributeSetterBiConsumers.put(
				"autoEscapeEnabledColumn",
				(BiConsumer<AutoEscapeEntry, String>)
					AutoEscapeEntry::setAutoEscapeEnabledColumn);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getAutoEscapeEntryId() {
		return _autoEscapeEntryId;
	}

	@Override
	public void setAutoEscapeEntryId(long autoEscapeEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_autoEscapeEntryId = autoEscapeEntryId;
	}

	@Override
	public String getAutoEscapeDisabledColumn() {
		if (_autoEscapeDisabledColumn == null) {
			return "";
		}
		else {
			return _autoEscapeDisabledColumn;
		}
	}

	@Override
	public void setAutoEscapeDisabledColumn(String autoEscapeDisabledColumn) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_autoEscapeDisabledColumn = autoEscapeDisabledColumn;
	}

	@Override
	public String getAutoEscapeEnabledColumn() {
		if (_autoEscapeEnabledColumn == null) {
			return "";
		}
		else {
			return _autoEscapeEnabledColumn;
		}
	}

	@Override
	public void setAutoEscapeEnabledColumn(String autoEscapeEnabledColumn) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_autoEscapeEnabledColumn = autoEscapeEnabledColumn;
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
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, AutoEscapeEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AutoEscapeEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AutoEscapeEntry>
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
		AutoEscapeEntryImpl autoEscapeEntryImpl = new AutoEscapeEntryImpl();

		autoEscapeEntryImpl.setAutoEscapeEntryId(getAutoEscapeEntryId());
		autoEscapeEntryImpl.setAutoEscapeDisabledColumn(
			getAutoEscapeDisabledColumn());
		autoEscapeEntryImpl.setAutoEscapeEnabledColumn(
			getAutoEscapeEnabledColumn());

		autoEscapeEntryImpl.resetOriginalValues();

		return autoEscapeEntryImpl;
	}

	@Override
	public AutoEscapeEntry cloneWithOriginalValues() {
		AutoEscapeEntryImpl autoEscapeEntryImpl = new AutoEscapeEntryImpl();

		autoEscapeEntryImpl.setAutoEscapeEntryId(
			this.<Long>getColumnOriginalValue("autoEscapeEntryId"));
		autoEscapeEntryImpl.setAutoEscapeDisabledColumn(
			this.<String>getColumnOriginalValue("autoEscapeDisabledColumn"));
		autoEscapeEntryImpl.setAutoEscapeEnabledColumn(
			this.<String>getColumnOriginalValue("autoEscapeEnabledColumn"));

		return autoEscapeEntryImpl;
	}

	@Override
	public int compareTo(AutoEscapeEntry autoEscapeEntry) {
		long primaryKey = autoEscapeEntry.getPrimaryKey();

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

		if (!(object instanceof AutoEscapeEntry)) {
			return false;
		}

		AutoEscapeEntry autoEscapeEntry = (AutoEscapeEntry)object;

		long primaryKey = autoEscapeEntry.getPrimaryKey();

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
	public CacheModel<AutoEscapeEntry> toCacheModel() {
		AutoEscapeEntryCacheModel autoEscapeEntryCacheModel =
			new AutoEscapeEntryCacheModel();

		autoEscapeEntryCacheModel.autoEscapeEntryId = getAutoEscapeEntryId();

		autoEscapeEntryCacheModel.autoEscapeDisabledColumn =
			getAutoEscapeDisabledColumn();

		String autoEscapeDisabledColumn =
			autoEscapeEntryCacheModel.autoEscapeDisabledColumn;

		if ((autoEscapeDisabledColumn != null) &&
			(autoEscapeDisabledColumn.length() == 0)) {

			autoEscapeEntryCacheModel.autoEscapeDisabledColumn = null;
		}

		autoEscapeEntryCacheModel.autoEscapeEnabledColumn =
			getAutoEscapeEnabledColumn();

		String autoEscapeEnabledColumn =
			autoEscapeEntryCacheModel.autoEscapeEnabledColumn;

		if ((autoEscapeEnabledColumn != null) &&
			(autoEscapeEnabledColumn.length() == 0)) {

			autoEscapeEntryCacheModel.autoEscapeEnabledColumn = null;
		}

		return autoEscapeEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AutoEscapeEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AutoEscapeEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AutoEscapeEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((AutoEscapeEntry)this);

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

		private static final Function<InvocationHandler, AutoEscapeEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					AutoEscapeEntry.class, ModelWrapper.class);

	}

	private long _autoEscapeEntryId;
	private String _autoEscapeDisabledColumn;
	private String _autoEscapeEnabledColumn;

	public <T> T getColumnValue(String columnName) {
		Function<AutoEscapeEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AutoEscapeEntry)this);
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

		_columnOriginalValues.put("autoEscapeEntryId", _autoEscapeEntryId);
		_columnOriginalValues.put(
			"autoEscapeDisabledColumn", _autoEscapeDisabledColumn);
		_columnOriginalValues.put(
			"autoEscapeEnabledColumn", _autoEscapeEnabledColumn);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("autoEscapeEntryId", 1L);

		columnBitmasks.put("autoEscapeDisabledColumn", 2L);

		columnBitmasks.put("autoEscapeEnabledColumn", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AutoEscapeEntry _escapedModel;

}