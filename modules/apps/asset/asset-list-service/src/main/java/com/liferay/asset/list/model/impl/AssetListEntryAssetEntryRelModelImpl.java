/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.model.impl;

import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.asset.list.model.AssetListEntryAssetEntryRelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the AssetListEntryAssetEntryRel service. Represents a row in the &quot;AssetListEntryAssetEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AssetListEntryAssetEntryRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetListEntryAssetEntryRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryAssetEntryRelImpl
 * @generated
 */
public class AssetListEntryAssetEntryRelModelImpl
	extends BaseModelImpl<AssetListEntryAssetEntryRel>
	implements AssetListEntryAssetEntryRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset list entry asset entry rel model instance should use the <code>AssetListEntryAssetEntryRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetListEntryAssetEntryRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR},
		{"assetListEntryAssetEntryRelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"assetListEntryId", Types.BIGINT}, {"assetEntryId", Types.BIGINT},
		{"segmentsEntryId", Types.BIGINT}, {"position", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetListEntryAssetEntryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assetListEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("position", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetListEntryAssetEntryRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,assetListEntryAssetEntryRelId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetListEntryId LONG,assetEntryId LONG,segmentsEntryId LONG,position INTEGER,lastPublishDate DATE null,primary key (assetListEntryAssetEntryRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table AssetListEntryAssetEntryRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetListEntryAssetEntryRel.position ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetListEntryAssetEntryRel.position ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long POSITION_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEGMENTSENTRYID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public AssetListEntryAssetEntryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetListEntryAssetEntryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetListEntryAssetEntryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetListEntryAssetEntryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetListEntryAssetEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetListEntryAssetEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetListEntryAssetEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetListEntryAssetEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntryAssetEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(AssetListEntryAssetEntryRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetListEntryAssetEntryRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetListEntryAssetEntryRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetListEntryAssetEntryRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetListEntryAssetEntryRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetListEntryAssetEntryRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<AssetListEntryAssetEntryRel, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<AssetListEntryAssetEntryRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<AssetListEntryAssetEntryRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", AssetListEntryAssetEntryRel::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId",
				AssetListEntryAssetEntryRel::getCtCollectionId);
			attributeGetterFunctions.put(
				"uuid", AssetListEntryAssetEntryRel::getUuid);
			attributeGetterFunctions.put(
				"assetListEntryAssetEntryRelId",
				AssetListEntryAssetEntryRel::getAssetListEntryAssetEntryRelId);
			attributeGetterFunctions.put(
				"groupId", AssetListEntryAssetEntryRel::getGroupId);
			attributeGetterFunctions.put(
				"companyId", AssetListEntryAssetEntryRel::getCompanyId);
			attributeGetterFunctions.put(
				"userId", AssetListEntryAssetEntryRel::getUserId);
			attributeGetterFunctions.put(
				"userName", AssetListEntryAssetEntryRel::getUserName);
			attributeGetterFunctions.put(
				"createDate", AssetListEntryAssetEntryRel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", AssetListEntryAssetEntryRel::getModifiedDate);
			attributeGetterFunctions.put(
				"assetListEntryId",
				AssetListEntryAssetEntryRel::getAssetListEntryId);
			attributeGetterFunctions.put(
				"assetEntryId", AssetListEntryAssetEntryRel::getAssetEntryId);
			attributeGetterFunctions.put(
				"segmentsEntryId",
				AssetListEntryAssetEntryRel::getSegmentsEntryId);
			attributeGetterFunctions.put(
				"position", AssetListEntryAssetEntryRel::getPosition);
			attributeGetterFunctions.put(
				"lastPublishDate",
				AssetListEntryAssetEntryRel::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<AssetListEntryAssetEntryRel, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<AssetListEntryAssetEntryRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<AssetListEntryAssetEntryRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<AssetListEntryAssetEntryRel, String>)
					AssetListEntryAssetEntryRel::setUuid);
			attributeSetterBiConsumers.put(
				"assetListEntryAssetEntryRelId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::
						setAssetListEntryAssetEntryRelId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<AssetListEntryAssetEntryRel, String>)
					AssetListEntryAssetEntryRel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<AssetListEntryAssetEntryRel, Date>)
					AssetListEntryAssetEntryRel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<AssetListEntryAssetEntryRel, Date>)
					AssetListEntryAssetEntryRel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"assetListEntryId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setAssetListEntryId);
			attributeSetterBiConsumers.put(
				"assetEntryId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setAssetEntryId);
			attributeSetterBiConsumers.put(
				"segmentsEntryId",
				(BiConsumer<AssetListEntryAssetEntryRel, Long>)
					AssetListEntryAssetEntryRel::setSegmentsEntryId);
			attributeSetterBiConsumers.put(
				"position",
				(BiConsumer<AssetListEntryAssetEntryRel, Integer>)
					AssetListEntryAssetEntryRel::setPosition);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<AssetListEntryAssetEntryRel, Date>)
					AssetListEntryAssetEntryRel::setLastPublishDate);

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
	public long getAssetListEntryAssetEntryRelId() {
		return _assetListEntryAssetEntryRelId;
	}

	@Override
	public void setAssetListEntryAssetEntryRelId(
		long assetListEntryAssetEntryRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryAssetEntryRelId = assetListEntryAssetEntryRelId;
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

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getAssetListEntryId() {
		return _assetListEntryId;
	}

	@Override
	public void setAssetListEntryId(long assetListEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryId = assetListEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAssetListEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("assetListEntryId"));
	}

	@Override
	public long getAssetEntryId() {
		return _assetEntryId;
	}

	@Override
	public void setAssetEntryId(long assetEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetEntryId = assetEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAssetEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("assetEntryId"));
	}

	@Override
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_segmentsEntryId = segmentsEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSegmentsEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
	}

	@Override
	public int getPosition() {
		return _position;
	}

	@Override
	public void setPosition(int position) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_position = position;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalPosition() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("position"));
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				AssetListEntryAssetEntryRel.class.getName()));
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
			getCompanyId(), AssetListEntryAssetEntryRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetListEntryAssetEntryRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AssetListEntryAssetEntryRel>
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
		AssetListEntryAssetEntryRelImpl assetListEntryAssetEntryRelImpl =
			new AssetListEntryAssetEntryRelImpl();

		assetListEntryAssetEntryRelImpl.setMvccVersion(getMvccVersion());
		assetListEntryAssetEntryRelImpl.setCtCollectionId(getCtCollectionId());
		assetListEntryAssetEntryRelImpl.setUuid(getUuid());
		assetListEntryAssetEntryRelImpl.setAssetListEntryAssetEntryRelId(
			getAssetListEntryAssetEntryRelId());
		assetListEntryAssetEntryRelImpl.setGroupId(getGroupId());
		assetListEntryAssetEntryRelImpl.setCompanyId(getCompanyId());
		assetListEntryAssetEntryRelImpl.setUserId(getUserId());
		assetListEntryAssetEntryRelImpl.setUserName(getUserName());
		assetListEntryAssetEntryRelImpl.setCreateDate(getCreateDate());
		assetListEntryAssetEntryRelImpl.setModifiedDate(getModifiedDate());
		assetListEntryAssetEntryRelImpl.setAssetListEntryId(
			getAssetListEntryId());
		assetListEntryAssetEntryRelImpl.setAssetEntryId(getAssetEntryId());
		assetListEntryAssetEntryRelImpl.setSegmentsEntryId(
			getSegmentsEntryId());
		assetListEntryAssetEntryRelImpl.setPosition(getPosition());
		assetListEntryAssetEntryRelImpl.setLastPublishDate(
			getLastPublishDate());

		assetListEntryAssetEntryRelImpl.resetOriginalValues();

		return assetListEntryAssetEntryRelImpl;
	}

	@Override
	public AssetListEntryAssetEntryRel cloneWithOriginalValues() {
		AssetListEntryAssetEntryRelImpl assetListEntryAssetEntryRelImpl =
			new AssetListEntryAssetEntryRelImpl();

		assetListEntryAssetEntryRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		assetListEntryAssetEntryRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		assetListEntryAssetEntryRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		assetListEntryAssetEntryRelImpl.setAssetListEntryAssetEntryRelId(
			this.<Long>getColumnOriginalValue("assetListEntryAssetEntryRelId"));
		assetListEntryAssetEntryRelImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		assetListEntryAssetEntryRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		assetListEntryAssetEntryRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		assetListEntryAssetEntryRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		assetListEntryAssetEntryRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		assetListEntryAssetEntryRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		assetListEntryAssetEntryRelImpl.setAssetListEntryId(
			this.<Long>getColumnOriginalValue("assetListEntryId"));
		assetListEntryAssetEntryRelImpl.setAssetEntryId(
			this.<Long>getColumnOriginalValue("assetEntryId"));
		assetListEntryAssetEntryRelImpl.setSegmentsEntryId(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
		assetListEntryAssetEntryRelImpl.setPosition(
			this.<Integer>getColumnOriginalValue("position"));
		assetListEntryAssetEntryRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return assetListEntryAssetEntryRelImpl;
	}

	@Override
	public int compareTo(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {

		int value = 0;

		if (getPosition() < assetListEntryAssetEntryRel.getPosition()) {
			value = -1;
		}
		else if (getPosition() > assetListEntryAssetEntryRel.getPosition()) {
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

		if (!(object instanceof AssetListEntryAssetEntryRel)) {
			return false;
		}

		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel =
			(AssetListEntryAssetEntryRel)object;

		long primaryKey = assetListEntryAssetEntryRel.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AssetListEntryAssetEntryRel> toCacheModel() {
		AssetListEntryAssetEntryRelCacheModel
			assetListEntryAssetEntryRelCacheModel =
				new AssetListEntryAssetEntryRelCacheModel();

		assetListEntryAssetEntryRelCacheModel.mvccVersion = getMvccVersion();

		assetListEntryAssetEntryRelCacheModel.ctCollectionId =
			getCtCollectionId();

		assetListEntryAssetEntryRelCacheModel.uuid = getUuid();

		String uuid = assetListEntryAssetEntryRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetListEntryAssetEntryRelCacheModel.uuid = null;
		}

		assetListEntryAssetEntryRelCacheModel.assetListEntryAssetEntryRelId =
			getAssetListEntryAssetEntryRelId();

		assetListEntryAssetEntryRelCacheModel.groupId = getGroupId();

		assetListEntryAssetEntryRelCacheModel.companyId = getCompanyId();

		assetListEntryAssetEntryRelCacheModel.userId = getUserId();

		assetListEntryAssetEntryRelCacheModel.userName = getUserName();

		String userName = assetListEntryAssetEntryRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetListEntryAssetEntryRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetListEntryAssetEntryRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			assetListEntryAssetEntryRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetListEntryAssetEntryRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			assetListEntryAssetEntryRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetListEntryAssetEntryRelCacheModel.assetListEntryId =
			getAssetListEntryId();

		assetListEntryAssetEntryRelCacheModel.assetEntryId = getAssetEntryId();

		assetListEntryAssetEntryRelCacheModel.segmentsEntryId =
			getSegmentsEntryId();

		assetListEntryAssetEntryRelCacheModel.position = getPosition();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			assetListEntryAssetEntryRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			assetListEntryAssetEntryRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return assetListEntryAssetEntryRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetListEntryAssetEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetListEntryAssetEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntryAssetEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AssetListEntryAssetEntryRel)this);

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
			<InvocationHandler, AssetListEntryAssetEntryRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						AssetListEntryAssetEntryRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _assetListEntryAssetEntryRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _assetListEntryId;
	private long _assetEntryId;
	private long _segmentsEntryId;
	private int _position;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AssetListEntryAssetEntryRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AssetListEntryAssetEntryRel)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"assetListEntryAssetEntryRelId", _assetListEntryAssetEntryRelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("assetListEntryId", _assetListEntryId);
		_columnOriginalValues.put("assetEntryId", _assetEntryId);
		_columnOriginalValues.put("segmentsEntryId", _segmentsEntryId);
		_columnOriginalValues.put("position", _position);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("assetListEntryAssetEntryRelId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("assetListEntryId", 1024L);

		columnBitmasks.put("assetEntryId", 2048L);

		columnBitmasks.put("segmentsEntryId", 4096L);

		columnBitmasks.put("position", 8192L);

		columnBitmasks.put("lastPublishDate", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AssetListEntryAssetEntryRel _escapedModel;

}