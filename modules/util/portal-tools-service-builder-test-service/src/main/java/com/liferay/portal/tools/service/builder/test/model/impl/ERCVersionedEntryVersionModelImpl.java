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
import com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntry;
import com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntryVersion;
import com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntryVersionModel;

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
 * The base model implementation for the ERCVersionedEntryVersion service. Represents a row in the &quot;ERCVersionedEntryVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ERCVersionedEntryVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ERCVersionedEntryVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ERCVersionedEntryVersionImpl
 * @generated
 */
public class ERCVersionedEntryVersionModelImpl
	extends BaseModelImpl<ERCVersionedEntryVersion>
	implements ERCVersionedEntryVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a erc versioned entry version model instance should use the <code>ERCVersionedEntryVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "ERCVersionedEntryVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"ercVersionedEntryVersionId", Types.BIGINT},
		{"version", Types.INTEGER}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"ercVersionedEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("ercVersionedEntryVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("version", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ercVersionedEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ERCVersionedEntryVersion (ercVersionedEntryVersionId LONG not null primary key,version INTEGER,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,ercVersionedEntryId LONG,groupId LONG,companyId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table ERCVersionedEntryVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ercVersionedEntryVersion.version DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ERCVersionedEntryVersion.version DESC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ERCVERSIONEDENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VERSION_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntryVersion"));

	public ERCVersionedEntryVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ercVersionedEntryVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setErcVersionedEntryVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ercVersionedEntryVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ERCVersionedEntryVersion.class;
	}

	@Override
	public String getModelClassName() {
		return ERCVersionedEntryVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ERCVersionedEntryVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ERCVersionedEntryVersion, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ERCVersionedEntryVersion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ERCVersionedEntryVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ERCVersionedEntryVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ERCVersionedEntryVersion, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ERCVersionedEntryVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ERCVersionedEntryVersion, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ERCVersionedEntryVersion, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<ERCVersionedEntryVersion, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<ERCVersionedEntryVersion, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<ERCVersionedEntryVersion, Object>>();

			attributeGetterFunctions.put(
				"ercVersionedEntryVersionId",
				ERCVersionedEntryVersion::getErcVersionedEntryVersionId);
			attributeGetterFunctions.put(
				"version", ERCVersionedEntryVersion::getVersion);
			attributeGetterFunctions.put(
				"uuid", ERCVersionedEntryVersion::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				ERCVersionedEntryVersion::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"ercVersionedEntryId",
				ERCVersionedEntryVersion::getErcVersionedEntryId);
			attributeGetterFunctions.put(
				"groupId", ERCVersionedEntryVersion::getGroupId);
			attributeGetterFunctions.put(
				"companyId", ERCVersionedEntryVersion::getCompanyId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<ERCVersionedEntryVersion, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<ERCVersionedEntryVersion, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<ERCVersionedEntryVersion, ?>>();

			attributeSetterBiConsumers.put(
				"ercVersionedEntryVersionId",
				(BiConsumer<ERCVersionedEntryVersion, Long>)
					ERCVersionedEntryVersion::setErcVersionedEntryVersionId);
			attributeSetterBiConsumers.put(
				"version",
				(BiConsumer<ERCVersionedEntryVersion, Integer>)
					ERCVersionedEntryVersion::setVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<ERCVersionedEntryVersion, String>)
					ERCVersionedEntryVersion::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<ERCVersionedEntryVersion, String>)
					ERCVersionedEntryVersion::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"ercVersionedEntryId",
				(BiConsumer<ERCVersionedEntryVersion, Long>)
					ERCVersionedEntryVersion::setErcVersionedEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<ERCVersionedEntryVersion, Long>)
					ERCVersionedEntryVersion::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<ERCVersionedEntryVersion, Long>)
					ERCVersionedEntryVersion::setCompanyId);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getVersionedModelId() {
		return getErcVersionedEntryId();
	}

	@Override
	public void populateVersionedModel(ERCVersionedEntry ercVersionedEntry) {
		ercVersionedEntry.setUuid(getUuid());
		ercVersionedEntry.setExternalReferenceCode(getExternalReferenceCode());
		ercVersionedEntry.setGroupId(getGroupId());
		ercVersionedEntry.setCompanyId(getCompanyId());
	}

	@Override
	public void setVersionedModelId(long ercVersionedEntryId) {
		setErcVersionedEntryId(ercVersionedEntryId);
	}

	@Override
	public ERCVersionedEntry toVersionedModel() {
		ERCVersionedEntry ercVersionedEntry = new ERCVersionedEntryImpl();

		ercVersionedEntry.setPrimaryKey(getVersionedModelId());
		ercVersionedEntry.setHeadId(-getVersionedModelId());

		populateVersionedModel(ercVersionedEntry);

		return ercVersionedEntry;
	}

	@Override
	public long getErcVersionedEntryVersionId() {
		return _ercVersionedEntryVersionId;
	}

	@Override
	public void setErcVersionedEntryVersionId(long ercVersionedEntryVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ercVersionedEntryVersionId = ercVersionedEntryVersionId;
	}

	@Override
	public int getVersion() {
		return _version;
	}

	@Override
	public void setVersion(int version) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_version = version;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalVersion() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("version"));
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	@Override
	public long getErcVersionedEntryId() {
		return _ercVersionedEntryId;
	}

	@Override
	public void setErcVersionedEntryId(long ercVersionedEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ercVersionedEntryId = ercVersionedEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalErcVersionedEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ercVersionedEntryId"));
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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
			getCompanyId(), ERCVersionedEntryVersion.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ERCVersionedEntryVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ERCVersionedEntryVersion>
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
		ERCVersionedEntryVersionImpl ercVersionedEntryVersionImpl =
			new ERCVersionedEntryVersionImpl();

		ercVersionedEntryVersionImpl.setErcVersionedEntryVersionId(
			getErcVersionedEntryVersionId());
		ercVersionedEntryVersionImpl.setVersion(getVersion());
		ercVersionedEntryVersionImpl.setUuid(getUuid());
		ercVersionedEntryVersionImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		ercVersionedEntryVersionImpl.setErcVersionedEntryId(
			getErcVersionedEntryId());
		ercVersionedEntryVersionImpl.setGroupId(getGroupId());
		ercVersionedEntryVersionImpl.setCompanyId(getCompanyId());

		ercVersionedEntryVersionImpl.resetOriginalValues();

		return ercVersionedEntryVersionImpl;
	}

	@Override
	public ERCVersionedEntryVersion cloneWithOriginalValues() {
		ERCVersionedEntryVersionImpl ercVersionedEntryVersionImpl =
			new ERCVersionedEntryVersionImpl();

		ercVersionedEntryVersionImpl.setErcVersionedEntryVersionId(
			this.<Long>getColumnOriginalValue("ercVersionedEntryVersionId"));
		ercVersionedEntryVersionImpl.setVersion(
			this.<Integer>getColumnOriginalValue("version"));
		ercVersionedEntryVersionImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		ercVersionedEntryVersionImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		ercVersionedEntryVersionImpl.setErcVersionedEntryId(
			this.<Long>getColumnOriginalValue("ercVersionedEntryId"));
		ercVersionedEntryVersionImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		ercVersionedEntryVersionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));

		return ercVersionedEntryVersionImpl;
	}

	@Override
	public int compareTo(ERCVersionedEntryVersion ercVersionedEntryVersion) {
		int value = 0;

		if (getVersion() < ercVersionedEntryVersion.getVersion()) {
			value = -1;
		}
		else if (getVersion() > ercVersionedEntryVersion.getVersion()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

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

		if (!(object instanceof ERCVersionedEntryVersion)) {
			return false;
		}

		ERCVersionedEntryVersion ercVersionedEntryVersion =
			(ERCVersionedEntryVersion)object;

		long primaryKey = ercVersionedEntryVersion.getPrimaryKey();

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
	public CacheModel<ERCVersionedEntryVersion> toCacheModel() {
		ERCVersionedEntryVersionCacheModel ercVersionedEntryVersionCacheModel =
			new ERCVersionedEntryVersionCacheModel();

		ercVersionedEntryVersionCacheModel.ercVersionedEntryVersionId =
			getErcVersionedEntryVersionId();

		ercVersionedEntryVersionCacheModel.version = getVersion();

		ercVersionedEntryVersionCacheModel.uuid = getUuid();

		String uuid = ercVersionedEntryVersionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ercVersionedEntryVersionCacheModel.uuid = null;
		}

		ercVersionedEntryVersionCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			ercVersionedEntryVersionCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			ercVersionedEntryVersionCacheModel.externalReferenceCode = null;
		}

		ercVersionedEntryVersionCacheModel.ercVersionedEntryId =
			getErcVersionedEntryId();

		ercVersionedEntryVersionCacheModel.groupId = getGroupId();

		ercVersionedEntryVersionCacheModel.companyId = getCompanyId();

		return ercVersionedEntryVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ERCVersionedEntryVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ERCVersionedEntryVersion, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ERCVersionedEntryVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(ERCVersionedEntryVersion)this);

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

		private static final Function
			<InvocationHandler, ERCVersionedEntryVersion>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						ERCVersionedEntryVersion.class, ModelWrapper.class);

	}

	private long _ercVersionedEntryVersionId;
	private int _version;
	private String _uuid;
	private String _externalReferenceCode;
	private long _ercVersionedEntryId;
	private long _groupId;
	private long _companyId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ERCVersionedEntryVersion, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ERCVersionedEntryVersion)this);
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

		_columnOriginalValues.put(
			"ercVersionedEntryVersionId", _ercVersionedEntryVersionId);
		_columnOriginalValues.put("version", _version);
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("ercVersionedEntryId", _ercVersionedEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("ercVersionedEntryVersionId", 1L);

		columnBitmasks.put("version", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("externalReferenceCode", 8L);

		columnBitmasks.put("ercVersionedEntryId", 16L);

		columnBitmasks.put("groupId", 32L);

		columnBitmasks.put("companyId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ERCVersionedEntryVersion _escapedModel;

}