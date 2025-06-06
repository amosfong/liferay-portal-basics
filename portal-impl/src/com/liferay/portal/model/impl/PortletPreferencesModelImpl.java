/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletPreferencesModel;
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
 * The base model implementation for the PortletPreferences service. Represents a row in the &quot;PortletPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PortletPreferencesModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PortletPreferencesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesImpl
 * @generated
 */
@JSON(strict = true)
public class PortletPreferencesModelImpl
	extends BaseModelImpl<PortletPreferences>
	implements PortletPreferencesModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a portlet preferences model instance should use the <code>PortletPreferences</code> interface instead.
	 */
	public static final String TABLE_NAME = "PortletPreferences";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"portletPreferencesId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"ownerId", Types.BIGINT},
		{"ownerType", Types.INTEGER}, {"plid", Types.BIGINT},
		{"portletId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletPreferencesId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ownerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ownerType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PortletPreferences (mvccVersion LONG default 0 not null,portletPreferencesId LONG not null primary key,companyId LONG,ownerId LONG,ownerType INTEGER,plid LONG,portletId VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table PortletPreferences";

	public static final String ORDER_BY_JPQL =
		" ORDER BY portletPreferences.portletPreferencesId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PortletPreferences.portletPreferencesId ASC";

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
	public static final long OWNERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OWNERTYPE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PLID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTLETID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTLETPREFERENCESID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.PortletPreferences"));

	public PortletPreferencesModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _portletPreferencesId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPortletPreferencesId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _portletPreferencesId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PortletPreferences.class;
	}

	@Override
	public String getModelClassName() {
		return PortletPreferences.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PortletPreferences, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PortletPreferences, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortletPreferences, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PortletPreferences)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PortletPreferences, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PortletPreferences, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PortletPreferences)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PortletPreferences, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PortletPreferences, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<PortletPreferences, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<PortletPreferences, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<PortletPreferences, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", PortletPreferences::getMvccVersion);
			attributeGetterFunctions.put(
				"portletPreferencesId",
				PortletPreferences::getPortletPreferencesId);
			attributeGetterFunctions.put(
				"companyId", PortletPreferences::getCompanyId);
			attributeGetterFunctions.put(
				"ownerId", PortletPreferences::getOwnerId);
			attributeGetterFunctions.put(
				"ownerType", PortletPreferences::getOwnerType);
			attributeGetterFunctions.put("plid", PortletPreferences::getPlid);
			attributeGetterFunctions.put(
				"portletId", PortletPreferences::getPortletId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<PortletPreferences, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<PortletPreferences, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<PortletPreferences, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<PortletPreferences, Long>)
					PortletPreferences::setMvccVersion);
			attributeSetterBiConsumers.put(
				"portletPreferencesId",
				(BiConsumer<PortletPreferences, Long>)
					PortletPreferences::setPortletPreferencesId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<PortletPreferences, Long>)
					PortletPreferences::setCompanyId);
			attributeSetterBiConsumers.put(
				"ownerId",
				(BiConsumer<PortletPreferences, Long>)
					PortletPreferences::setOwnerId);
			attributeSetterBiConsumers.put(
				"ownerType",
				(BiConsumer<PortletPreferences, Integer>)
					PortletPreferences::setOwnerType);
			attributeSetterBiConsumers.put(
				"plid",
				(BiConsumer<PortletPreferences, Long>)
					PortletPreferences::setPlid);
			attributeSetterBiConsumers.put(
				"portletId",
				(BiConsumer<PortletPreferences, String>)
					PortletPreferences::setPortletId);

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
	public long getPortletPreferencesId() {
		return _portletPreferencesId;
	}

	@Override
	public void setPortletPreferencesId(long portletPreferencesId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portletPreferencesId = portletPreferencesId;
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
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
			getCompanyId(), PortletPreferences.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PortletPreferences toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PortletPreferences>
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
		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setMvccVersion(getMvccVersion());
		portletPreferencesImpl.setPortletPreferencesId(
			getPortletPreferencesId());
		portletPreferencesImpl.setCompanyId(getCompanyId());
		portletPreferencesImpl.setOwnerId(getOwnerId());
		portletPreferencesImpl.setOwnerType(getOwnerType());
		portletPreferencesImpl.setPlid(getPlid());
		portletPreferencesImpl.setPortletId(getPortletId());

		portletPreferencesImpl.resetOriginalValues();

		return portletPreferencesImpl;
	}

	@Override
	public PortletPreferences cloneWithOriginalValues() {
		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		portletPreferencesImpl.setPortletPreferencesId(
			this.<Long>getColumnOriginalValue("portletPreferencesId"));
		portletPreferencesImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		portletPreferencesImpl.setOwnerId(
			this.<Long>getColumnOriginalValue("ownerId"));
		portletPreferencesImpl.setOwnerType(
			this.<Integer>getColumnOriginalValue("ownerType"));
		portletPreferencesImpl.setPlid(
			this.<Long>getColumnOriginalValue("plid"));
		portletPreferencesImpl.setPortletId(
			this.<String>getColumnOriginalValue("portletId"));

		return portletPreferencesImpl;
	}

	@Override
	public int compareTo(PortletPreferences portletPreferences) {
		long primaryKey = portletPreferences.getPrimaryKey();

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

		if (!(object instanceof PortletPreferences)) {
			return false;
		}

		PortletPreferences portletPreferences = (PortletPreferences)object;

		long primaryKey = portletPreferences.getPrimaryKey();

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
	public CacheModel<PortletPreferences> toCacheModel() {
		PortletPreferencesCacheModel portletPreferencesCacheModel =
			new PortletPreferencesCacheModel();

		portletPreferencesCacheModel.mvccVersion = getMvccVersion();

		portletPreferencesCacheModel.portletPreferencesId =
			getPortletPreferencesId();

		portletPreferencesCacheModel.companyId = getCompanyId();

		portletPreferencesCacheModel.ownerId = getOwnerId();

		portletPreferencesCacheModel.ownerType = getOwnerType();

		portletPreferencesCacheModel.plid = getPlid();

		portletPreferencesCacheModel.portletId = getPortletId();

		String portletId = portletPreferencesCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			portletPreferencesCacheModel.portletId = null;
		}

		return portletPreferencesCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PortletPreferences, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PortletPreferences, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortletPreferences, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(PortletPreferences)this);

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

		private static final Function<InvocationHandler, PortletPreferences>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					PortletPreferences.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _portletPreferencesId;
	private long _companyId;
	private long _ownerId;
	private int _ownerType;
	private long _plid;
	private String _portletId;

	public <T> T getColumnValue(String columnName) {
		Function<PortletPreferences, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((PortletPreferences)this);
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
			"portletPreferencesId", _portletPreferencesId);
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

		columnBitmasks.put("portletPreferencesId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("ownerId", 8L);

		columnBitmasks.put("ownerType", 16L);

		columnBitmasks.put("plid", 32L);

		columnBitmasks.put("portletId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private PortletPreferences _escapedModel;

}