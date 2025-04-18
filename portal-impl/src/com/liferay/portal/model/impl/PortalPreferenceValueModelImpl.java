/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.PortalPreferenceValue;
import com.liferay.portal.kernel.model.PortalPreferenceValueModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The base model implementation for the PortalPreferenceValue service. Represents a row in the &quot;PortalPreferenceValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PortalPreferenceValueModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PortalPreferenceValueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferenceValueImpl
 * @generated
 */
public class PortalPreferenceValueModelImpl
	extends BaseModelImpl<PortalPreferenceValue>
	implements PortalPreferenceValueModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a portal preference value model instance should use the <code>PortalPreferenceValue</code> interface instead.
	 */
	public static final String TABLE_NAME = "PortalPreferenceValue";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"portalPreferenceValueId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"portalPreferencesId", Types.BIGINT}, {"index_", Types.INTEGER},
		{"key_", Types.VARCHAR}, {"largeValue", Types.CLOB},
		{"namespace", Types.VARCHAR}, {"smallValue", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portalPreferenceValueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portalPreferencesId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("index_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("largeValue", Types.CLOB);
		TABLE_COLUMNS_MAP.put("namespace", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("smallValue", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PortalPreferenceValue (mvccVersion LONG default 0 not null,portalPreferenceValueId LONG not null primary key,companyId LONG,portalPreferencesId LONG,index_ INTEGER,key_ VARCHAR(1024) null,largeValue TEXT null,namespace VARCHAR(255) null,smallValue VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP =
		"drop table PortalPreferenceValue";

	public static final String ORDER_BY_JPQL =
		" ORDER BY portalPreferenceValue.index ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PortalPreferenceValue.index_ ASC";

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
	public static final long INDEX_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KEY_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAMESPACE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTALPREFERENCESID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SMALLVALUE_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.PortalPreferenceValue"));

	public PortalPreferenceValueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _portalPreferenceValueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPortalPreferenceValueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _portalPreferenceValueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PortalPreferenceValue.class;
	}

	@Override
	public String getModelClassName() {
		return PortalPreferenceValue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PortalPreferenceValue, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PortalPreferenceValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortalPreferenceValue, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PortalPreferenceValue)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PortalPreferenceValue, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PortalPreferenceValue, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PortalPreferenceValue)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PortalPreferenceValue, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PortalPreferenceValue, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<PortalPreferenceValue, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<PortalPreferenceValue, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<PortalPreferenceValue, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", PortalPreferenceValue::getMvccVersion);
			attributeGetterFunctions.put(
				"portalPreferenceValueId",
				PortalPreferenceValue::getPortalPreferenceValueId);
			attributeGetterFunctions.put(
				"companyId", PortalPreferenceValue::getCompanyId);
			attributeGetterFunctions.put(
				"portalPreferencesId",
				PortalPreferenceValue::getPortalPreferencesId);
			attributeGetterFunctions.put(
				"index", PortalPreferenceValue::getIndex);
			attributeGetterFunctions.put("key", PortalPreferenceValue::getKey);
			attributeGetterFunctions.put(
				"largeValue", PortalPreferenceValue::getLargeValue);
			attributeGetterFunctions.put(
				"namespace", PortalPreferenceValue::getNamespace);
			attributeGetterFunctions.put(
				"smallValue", PortalPreferenceValue::getSmallValue);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<PortalPreferenceValue, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<PortalPreferenceValue, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<PortalPreferenceValue, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<PortalPreferenceValue, Long>)
					PortalPreferenceValue::setMvccVersion);
			attributeSetterBiConsumers.put(
				"portalPreferenceValueId",
				(BiConsumer<PortalPreferenceValue, Long>)
					PortalPreferenceValue::setPortalPreferenceValueId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<PortalPreferenceValue, Long>)
					PortalPreferenceValue::setCompanyId);
			attributeSetterBiConsumers.put(
				"portalPreferencesId",
				(BiConsumer<PortalPreferenceValue, Long>)
					PortalPreferenceValue::setPortalPreferencesId);
			attributeSetterBiConsumers.put(
				"index",
				(BiConsumer<PortalPreferenceValue, Integer>)
					PortalPreferenceValue::setIndex);
			attributeSetterBiConsumers.put(
				"key",
				(BiConsumer<PortalPreferenceValue, String>)
					PortalPreferenceValue::setKey);
			attributeSetterBiConsumers.put(
				"largeValue",
				(BiConsumer<PortalPreferenceValue, String>)
					PortalPreferenceValue::setLargeValue);
			attributeSetterBiConsumers.put(
				"namespace",
				(BiConsumer<PortalPreferenceValue, String>)
					PortalPreferenceValue::setNamespace);
			attributeSetterBiConsumers.put(
				"smallValue",
				(BiConsumer<PortalPreferenceValue, String>)
					PortalPreferenceValue::setSmallValue);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

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

	@Override
	public long getPortalPreferenceValueId() {
		return _portalPreferenceValueId;
	}

	@Override
	public void setPortalPreferenceValueId(long portalPreferenceValueId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portalPreferenceValueId = portalPreferenceValueId;
	}

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

	@Override
	public long getPortalPreferencesId() {
		return _portalPreferencesId;
	}

	@Override
	public void setPortalPreferencesId(long portalPreferencesId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portalPreferencesId = portalPreferencesId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalPortalPreferencesId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("portalPreferencesId"));
	}

	@Override
	public int getIndex() {
		return _index;
	}

	@Override
	public void setIndex(int index) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_index = index;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalIndex() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("index_"));
	}

	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_key = key;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKey() {
		return getColumnOriginalValue("key_");
	}

	@Override
	public String getLargeValue() {
		if (_largeValue == null) {
			return "";
		}
		else {
			return _largeValue;
		}
	}

	@Override
	public void setLargeValue(String largeValue) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_largeValue = largeValue;
	}

	@Override
	public String getNamespace() {
		if (_namespace == null) {
			return "";
		}
		else {
			return _namespace;
		}
	}

	@Override
	public void setNamespace(String namespace) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_namespace = namespace;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalNamespace() {
		return getColumnOriginalValue("namespace");
	}

	@Override
	public String getSmallValue() {
		if (_smallValue == null) {
			return "";
		}
		else {
			return _smallValue;
		}
	}

	@Override
	public void setSmallValue(String smallValue) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_smallValue = smallValue;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalSmallValue() {
		return getColumnOriginalValue("smallValue");
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
			getCompanyId(), PortalPreferenceValue.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PortalPreferenceValue toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PortalPreferenceValue>
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
		PortalPreferenceValueImpl portalPreferenceValueImpl =
			new PortalPreferenceValueImpl();

		portalPreferenceValueImpl.setMvccVersion(getMvccVersion());
		portalPreferenceValueImpl.setPortalPreferenceValueId(
			getPortalPreferenceValueId());
		portalPreferenceValueImpl.setCompanyId(getCompanyId());
		portalPreferenceValueImpl.setPortalPreferencesId(
			getPortalPreferencesId());
		portalPreferenceValueImpl.setIndex(getIndex());
		portalPreferenceValueImpl.setKey(getKey());
		portalPreferenceValueImpl.setLargeValue(getLargeValue());
		portalPreferenceValueImpl.setNamespace(getNamespace());
		portalPreferenceValueImpl.setSmallValue(getSmallValue());

		portalPreferenceValueImpl.resetOriginalValues();

		return portalPreferenceValueImpl;
	}

	@Override
	public PortalPreferenceValue cloneWithOriginalValues() {
		PortalPreferenceValueImpl portalPreferenceValueImpl =
			new PortalPreferenceValueImpl();

		portalPreferenceValueImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		portalPreferenceValueImpl.setPortalPreferenceValueId(
			this.<Long>getColumnOriginalValue("portalPreferenceValueId"));
		portalPreferenceValueImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		portalPreferenceValueImpl.setPortalPreferencesId(
			this.<Long>getColumnOriginalValue("portalPreferencesId"));
		portalPreferenceValueImpl.setIndex(
			this.<Integer>getColumnOriginalValue("index_"));
		portalPreferenceValueImpl.setKey(
			this.<String>getColumnOriginalValue("key_"));
		portalPreferenceValueImpl.setLargeValue(
			this.<String>getColumnOriginalValue("largeValue"));
		portalPreferenceValueImpl.setNamespace(
			this.<String>getColumnOriginalValue("namespace"));
		portalPreferenceValueImpl.setSmallValue(
			this.<String>getColumnOriginalValue("smallValue"));

		return portalPreferenceValueImpl;
	}

	@Override
	public int compareTo(PortalPreferenceValue portalPreferenceValue) {
		int value = 0;

		if (getIndex() < portalPreferenceValue.getIndex()) {
			value = -1;
		}
		else if (getIndex() > portalPreferenceValue.getIndex()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PortalPreferenceValue)) {
			return false;
		}

		PortalPreferenceValue portalPreferenceValue =
			(PortalPreferenceValue)object;

		long primaryKey = portalPreferenceValue.getPrimaryKey();

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
	public CacheModel<PortalPreferenceValue> toCacheModel() {
		PortalPreferenceValueCacheModel portalPreferenceValueCacheModel =
			new PortalPreferenceValueCacheModel();

		portalPreferenceValueCacheModel.mvccVersion = getMvccVersion();

		portalPreferenceValueCacheModel.portalPreferenceValueId =
			getPortalPreferenceValueId();

		portalPreferenceValueCacheModel.companyId = getCompanyId();

		portalPreferenceValueCacheModel.portalPreferencesId =
			getPortalPreferencesId();

		portalPreferenceValueCacheModel.index = getIndex();

		portalPreferenceValueCacheModel.key = getKey();

		String key = portalPreferenceValueCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			portalPreferenceValueCacheModel.key = null;
		}

		portalPreferenceValueCacheModel.largeValue = getLargeValue();

		String largeValue = portalPreferenceValueCacheModel.largeValue;

		if ((largeValue != null) && (largeValue.length() == 0)) {
			portalPreferenceValueCacheModel.largeValue = null;
		}

		portalPreferenceValueCacheModel.namespace = getNamespace();

		String namespace = portalPreferenceValueCacheModel.namespace;

		if ((namespace != null) && (namespace.length() == 0)) {
			portalPreferenceValueCacheModel.namespace = null;
		}

		portalPreferenceValueCacheModel.smallValue = getSmallValue();

		String smallValue = portalPreferenceValueCacheModel.smallValue;

		if ((smallValue != null) && (smallValue.length() == 0)) {
			portalPreferenceValueCacheModel.smallValue = null;
		}

		return portalPreferenceValueCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PortalPreferenceValue, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PortalPreferenceValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortalPreferenceValue, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(PortalPreferenceValue)this);

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

		private static final Function<InvocationHandler, PortalPreferenceValue>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					PortalPreferenceValue.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _portalPreferenceValueId;
	private long _companyId;
	private long _portalPreferencesId;
	private int _index;
	private String _key;
	private String _largeValue;
	private String _namespace;
	private String _smallValue;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<PortalPreferenceValue, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((PortalPreferenceValue)this);
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
		_columnOriginalValues.put(
			"portalPreferenceValueId", _portalPreferenceValueId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("portalPreferencesId", _portalPreferencesId);
		_columnOriginalValues.put("index_", _index);
		_columnOriginalValues.put("key_", _key);
		_columnOriginalValues.put("largeValue", _largeValue);
		_columnOriginalValues.put("namespace", _namespace);
		_columnOriginalValues.put("smallValue", _smallValue);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("index_", "index");
		attributeNames.put("key_", "key");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("portalPreferenceValueId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("portalPreferencesId", 8L);

		columnBitmasks.put("index_", 16L);

		columnBitmasks.put("key_", 32L);

		columnBitmasks.put("largeValue", 64L);

		columnBitmasks.put("namespace", 128L);

		columnBitmasks.put("smallValue", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private PortalPreferenceValue _escapedModel;

}