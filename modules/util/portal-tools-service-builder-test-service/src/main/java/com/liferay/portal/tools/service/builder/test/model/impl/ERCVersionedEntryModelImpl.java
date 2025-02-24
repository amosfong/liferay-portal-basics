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
import com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntryModel;
import com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntryVersion;

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
 * The base model implementation for the ERCVersionedEntry service. Represents a row in the &quot;ERCVersionedEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ERCVersionedEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ERCVersionedEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ERCVersionedEntryImpl
 * @generated
 */
public class ERCVersionedEntryModelImpl
	extends BaseModelImpl<ERCVersionedEntry> implements ERCVersionedEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a erc versioned entry model instance should use the <code>ERCVersionedEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ERCVersionedEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR}, {"headId", Types.BIGINT},
		{"head", Types.BOOLEAN}, {"ercVersionedEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("ercVersionedEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ERCVersionedEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,headId LONG,head BOOLEAN,ercVersionedEntryId LONG not null primary key,groupId LONG,companyId LONG)";

	public static final String TABLE_SQL_DROP = "drop table ERCVersionedEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ercVersionedEntry.ercVersionedEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ERCVersionedEntry.ercVersionedEntryId ASC";

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
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long HEAD_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long HEADID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ERCVERSIONEDENTRYID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.ERCVersionedEntry"));

	public ERCVersionedEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ercVersionedEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setErcVersionedEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ercVersionedEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ERCVersionedEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ERCVersionedEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ERCVersionedEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ERCVersionedEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ERCVersionedEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ERCVersionedEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ERCVersionedEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ERCVersionedEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ERCVersionedEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ERCVersionedEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ERCVersionedEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<ERCVersionedEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<ERCVersionedEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<ERCVersionedEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", ERCVersionedEntry::getMvccVersion);
			attributeGetterFunctions.put("uuid", ERCVersionedEntry::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				ERCVersionedEntry::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"headId", ERCVersionedEntry::getHeadId);
			attributeGetterFunctions.put(
				"ercVersionedEntryId",
				ERCVersionedEntry::getErcVersionedEntryId);
			attributeGetterFunctions.put(
				"groupId", ERCVersionedEntry::getGroupId);
			attributeGetterFunctions.put(
				"companyId", ERCVersionedEntry::getCompanyId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<ERCVersionedEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<ERCVersionedEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<ERCVersionedEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<ERCVersionedEntry, Long>)
					ERCVersionedEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<ERCVersionedEntry, String>)
					ERCVersionedEntry::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<ERCVersionedEntry, String>)
					ERCVersionedEntry::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"headId",
				(BiConsumer<ERCVersionedEntry, Long>)
					ERCVersionedEntry::setHeadId);
			attributeSetterBiConsumers.put(
				"ercVersionedEntryId",
				(BiConsumer<ERCVersionedEntry, Long>)
					ERCVersionedEntry::setErcVersionedEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<ERCVersionedEntry, Long>)
					ERCVersionedEntry::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<ERCVersionedEntry, Long>)
					ERCVersionedEntry::setCompanyId);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public void populateVersionModel(
		ERCVersionedEntryVersion ercVersionedEntryVersion) {

		ercVersionedEntryVersion.setUuid(getUuid());
		ercVersionedEntryVersion.setExternalReferenceCode(
			getExternalReferenceCode());
		ercVersionedEntryVersion.setGroupId(getGroupId());
		ercVersionedEntryVersion.setCompanyId(getCompanyId());
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@Override
	public long getHeadId() {
		return _headId;
	}

	@Override
	public void setHeadId(long headId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		if (headId >= 0) {
			setHead(false);
		}
		else {
			setHead(true);
		}

		_headId = headId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalHeadId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("headId"));
	}

	public boolean getHead() {
		return _head;
	}

	@Override
	public boolean isHead() {
		return _head;
	}

	public void setHead(boolean head) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_head = head;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalHead() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("head"));
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
			getCompanyId(), ERCVersionedEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ERCVersionedEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ERCVersionedEntry>
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
		ERCVersionedEntryImpl ercVersionedEntryImpl =
			new ERCVersionedEntryImpl();

		ercVersionedEntryImpl.setMvccVersion(getMvccVersion());
		ercVersionedEntryImpl.setUuid(getUuid());
		ercVersionedEntryImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		ercVersionedEntryImpl.setHeadId(getHeadId());
		ercVersionedEntryImpl.setErcVersionedEntryId(getErcVersionedEntryId());
		ercVersionedEntryImpl.setGroupId(getGroupId());
		ercVersionedEntryImpl.setCompanyId(getCompanyId());

		ercVersionedEntryImpl.resetOriginalValues();

		return ercVersionedEntryImpl;
	}

	@Override
	public ERCVersionedEntry cloneWithOriginalValues() {
		ERCVersionedEntryImpl ercVersionedEntryImpl =
			new ERCVersionedEntryImpl();

		ercVersionedEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		ercVersionedEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		ercVersionedEntryImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		ercVersionedEntryImpl.setHeadId(
			this.<Long>getColumnOriginalValue("headId"));
		ercVersionedEntryImpl.setErcVersionedEntryId(
			this.<Long>getColumnOriginalValue("ercVersionedEntryId"));
		ercVersionedEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		ercVersionedEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));

		return ercVersionedEntryImpl;
	}

	@Override
	public int compareTo(ERCVersionedEntry ercVersionedEntry) {
		long primaryKey = ercVersionedEntry.getPrimaryKey();

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

		if (!(object instanceof ERCVersionedEntry)) {
			return false;
		}

		ERCVersionedEntry ercVersionedEntry = (ERCVersionedEntry)object;

		long primaryKey = ercVersionedEntry.getPrimaryKey();

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
	public CacheModel<ERCVersionedEntry> toCacheModel() {
		ERCVersionedEntryCacheModel ercVersionedEntryCacheModel =
			new ERCVersionedEntryCacheModel();

		ercVersionedEntryCacheModel.mvccVersion = getMvccVersion();

		ercVersionedEntryCacheModel.uuid = getUuid();

		String uuid = ercVersionedEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ercVersionedEntryCacheModel.uuid = null;
		}

		ercVersionedEntryCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			ercVersionedEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			ercVersionedEntryCacheModel.externalReferenceCode = null;
		}

		ercVersionedEntryCacheModel.headId = getHeadId();

		ercVersionedEntryCacheModel.head = isHead();

		ercVersionedEntryCacheModel.ercVersionedEntryId =
			getErcVersionedEntryId();

		ercVersionedEntryCacheModel.groupId = getGroupId();

		ercVersionedEntryCacheModel.companyId = getCompanyId();

		return ercVersionedEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ERCVersionedEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ERCVersionedEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ERCVersionedEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(ERCVersionedEntry)this);

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

		private static final Function<InvocationHandler, ERCVersionedEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ERCVersionedEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private String _externalReferenceCode;
	private long _headId;
	private boolean _head;
	private long _ercVersionedEntryId;
	private long _groupId;
	private long _companyId;

	public <T> T getColumnValue(String columnName) {
		if (columnName.equals("head")) {
			return (T)(Object)getHead();
		}

		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ERCVersionedEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ERCVersionedEntry)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("headId", _headId);
		_columnOriginalValues.put("head", _head);
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

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("externalReferenceCode", 4L);

		columnBitmasks.put("headId", 8L);

		columnBitmasks.put("head", 16L);

		columnBitmasks.put("ercVersionedEntryId", 32L);

		columnBitmasks.put("groupId", 64L);

		columnBitmasks.put("companyId", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ERCVersionedEntry _escapedModel;

}