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
import com.liferay.portal.tools.service.builder.test.model.IndexEntry;
import com.liferay.portal.tools.service.builder.test.model.IndexEntryModel;

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
 * The base model implementation for the IndexEntry service. Represents a row in the &quot;IndexEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>IndexEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IndexEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IndexEntryImpl
 * @generated
 */
public class IndexEntryModelImpl
	extends BaseModelImpl<IndexEntry> implements IndexEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a index entry model instance should use the <code>IndexEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "IndexEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"externalReferenceCode", Types.VARCHAR},
		{"indexEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"ownerId", Types.BIGINT}, {"ownerType", Types.INTEGER},
		{"plid", Types.BIGINT}, {"portletId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("indexEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ownerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ownerType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table IndexEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,externalReferenceCode VARCHAR(75) null,indexEntryId LONG not null,companyId LONG,ownerId LONG,ownerType INTEGER,plid LONG,portletId VARCHAR(75) null,primary key (indexEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table IndexEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY indexEntry.indexEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY IndexEntry.indexEntryId ASC";

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
	public static final long OWNERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OWNERTYPE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PLID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTLETID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long INDEXENTRYID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.IndexEntry"));

	public IndexEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _indexEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setIndexEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _indexEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return IndexEntry.class;
	}

	@Override
	public String getModelClassName() {
		return IndexEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<IndexEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<IndexEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<IndexEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((IndexEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<IndexEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<IndexEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(IndexEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<IndexEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<IndexEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<IndexEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<IndexEntry, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<IndexEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", IndexEntry::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", IndexEntry::getCtCollectionId);
			attributeGetterFunctions.put(
				"externalReferenceCode", IndexEntry::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"indexEntryId", IndexEntry::getIndexEntryId);
			attributeGetterFunctions.put("companyId", IndexEntry::getCompanyId);
			attributeGetterFunctions.put("ownerId", IndexEntry::getOwnerId);
			attributeGetterFunctions.put("ownerType", IndexEntry::getOwnerType);
			attributeGetterFunctions.put("plid", IndexEntry::getPlid);
			attributeGetterFunctions.put("portletId", IndexEntry::getPortletId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<IndexEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<IndexEntry, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<IndexEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<IndexEntry, Long>)IndexEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<IndexEntry, Long>)IndexEntry::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<IndexEntry, String>)
					IndexEntry::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"indexEntryId",
				(BiConsumer<IndexEntry, Long>)IndexEntry::setIndexEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<IndexEntry, Long>)IndexEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"ownerId",
				(BiConsumer<IndexEntry, Long>)IndexEntry::setOwnerId);
			attributeSetterBiConsumers.put(
				"ownerType",
				(BiConsumer<IndexEntry, Integer>)IndexEntry::setOwnerType);
			attributeSetterBiConsumers.put(
				"plid", (BiConsumer<IndexEntry, Long>)IndexEntry::setPlid);
			attributeSetterBiConsumers.put(
				"portletId",
				(BiConsumer<IndexEntry, String>)IndexEntry::setPortletId);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
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
	public long getIndexEntryId() {
		return _indexEntryId;
	}

	@Override
	public void setIndexEntryId(long indexEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_indexEntryId = indexEntryId;
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

	@Override
	public long getOwnerId() {
		return _ownerId;
	}

	@Override
	public void setOwnerId(long ownerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ownerId = ownerId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOwnerId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("ownerId"));
	}

	@Override
	public int getOwnerType() {
		return _ownerType;
	}

	@Override
	public void setOwnerType(int ownerType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ownerType = ownerType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalOwnerType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("ownerType"));
	}

	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_plid = plid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalPlid() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("plid"));
	}

	@Override
	public String getPortletId() {
		if (_portletId == null) {
			return "";
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portletId = portletId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalPortletId() {
		return getColumnOriginalValue("portletId");
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
			getCompanyId(), IndexEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public IndexEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, IndexEntry>
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
		IndexEntryImpl indexEntryImpl = new IndexEntryImpl();

		indexEntryImpl.setMvccVersion(getMvccVersion());
		indexEntryImpl.setCtCollectionId(getCtCollectionId());
		indexEntryImpl.setExternalReferenceCode(getExternalReferenceCode());
		indexEntryImpl.setIndexEntryId(getIndexEntryId());
		indexEntryImpl.setCompanyId(getCompanyId());
		indexEntryImpl.setOwnerId(getOwnerId());
		indexEntryImpl.setOwnerType(getOwnerType());
		indexEntryImpl.setPlid(getPlid());
		indexEntryImpl.setPortletId(getPortletId());

		indexEntryImpl.resetOriginalValues();

		return indexEntryImpl;
	}

	@Override
	public IndexEntry cloneWithOriginalValues() {
		IndexEntryImpl indexEntryImpl = new IndexEntryImpl();

		indexEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		indexEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		indexEntryImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		indexEntryImpl.setIndexEntryId(
			this.<Long>getColumnOriginalValue("indexEntryId"));
		indexEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		indexEntryImpl.setOwnerId(this.<Long>getColumnOriginalValue("ownerId"));
		indexEntryImpl.setOwnerType(
			this.<Integer>getColumnOriginalValue("ownerType"));
		indexEntryImpl.setPlid(this.<Long>getColumnOriginalValue("plid"));
		indexEntryImpl.setPortletId(
			this.<String>getColumnOriginalValue("portletId"));

		return indexEntryImpl;
	}

	@Override
	public int compareTo(IndexEntry indexEntry) {
		long primaryKey = indexEntry.getPrimaryKey();

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

		if (!(object instanceof IndexEntry)) {
			return false;
		}

		IndexEntry indexEntry = (IndexEntry)object;

		long primaryKey = indexEntry.getPrimaryKey();

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
	public CacheModel<IndexEntry> toCacheModel() {
		IndexEntryCacheModel indexEntryCacheModel = new IndexEntryCacheModel();

		indexEntryCacheModel.mvccVersion = getMvccVersion();

		indexEntryCacheModel.ctCollectionId = getCtCollectionId();

		indexEntryCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode =
			indexEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			indexEntryCacheModel.externalReferenceCode = null;
		}

		indexEntryCacheModel.indexEntryId = getIndexEntryId();

		indexEntryCacheModel.companyId = getCompanyId();

		indexEntryCacheModel.ownerId = getOwnerId();

		indexEntryCacheModel.ownerType = getOwnerType();

		indexEntryCacheModel.plid = getPlid();

		indexEntryCacheModel.portletId = getPortletId();

		String portletId = indexEntryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			indexEntryCacheModel.portletId = null;
		}

		return indexEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<IndexEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<IndexEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<IndexEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((IndexEntry)this);

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

		private static final Function<InvocationHandler, IndexEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					IndexEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _externalReferenceCode;
	private long _indexEntryId;
	private long _companyId;
	private long _ownerId;
	private int _ownerType;
	private long _plid;
	private String _portletId;

	public <T> T getColumnValue(String columnName) {
		Function<IndexEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((IndexEntry)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("indexEntryId", _indexEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("ownerId", _ownerId);
		_columnOriginalValues.put("ownerType", _ownerType);
		_columnOriginalValues.put("plid", _plid);
		_columnOriginalValues.put("portletId", _portletId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("externalReferenceCode", 4L);

		columnBitmasks.put("indexEntryId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("ownerId", 32L);

		columnBitmasks.put("ownerType", 64L);

		columnBitmasks.put("plid", 128L);

		columnBitmasks.put("portletId", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private IndexEntry _escapedModel;

}