/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CPDefinitionInventoryModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
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

import java.math.BigDecimal;

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
 * The base model implementation for the CPDefinitionInventory service. Represents a row in the &quot;CPDefinitionInventory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPDefinitionInventoryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDefinitionInventoryImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CPDefinitionInventoryImpl
 * @generated
 */
@JSON(strict = true)
public class CPDefinitionInventoryModelImpl
	extends BaseModelImpl<CPDefinitionInventory>
	implements CPDefinitionInventoryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition inventory model instance should use the <code>CPDefinitionInventory</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPDefinitionInventory";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"CPDefinitionInventoryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CPDefinitionId", Types.BIGINT},
		{"CPDefinitionInventoryEngine", Types.VARCHAR},
		{"lowStockActivity", Types.VARCHAR},
		{"displayAvailability", Types.BOOLEAN},
		{"displayStockQuantity", Types.BOOLEAN},
		{"minStockQuantity", Types.DECIMAL}, {"backOrders", Types.BOOLEAN},
		{"minOrderQuantity", Types.DECIMAL},
		{"maxOrderQuantity", Types.DECIMAL},
		{"allowedOrderQuantities", Types.VARCHAR},
		{"multipleOrderQuantity", Types.DECIMAL}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPDefinitionInventoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionInventoryEngine", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lowStockActivity", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayAvailability", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("displayStockQuantity", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("minStockQuantity", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("backOrders", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("minOrderQuantity", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("maxOrderQuantity", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("allowedOrderQuantities", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("multipleOrderQuantity", Types.DECIMAL);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPDefinitionInventory (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CPDefinitionInventoryId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPDefinitionId LONG,CPDefinitionInventoryEngine VARCHAR(75) null,lowStockActivity VARCHAR(75) null,displayAvailability BOOLEAN,displayStockQuantity BOOLEAN,minStockQuantity BIGDECIMAL null,backOrders BOOLEAN,minOrderQuantity BIGDECIMAL null,maxOrderQuantity BIGDECIMAL null,allowedOrderQuantities VARCHAR(75) null,multipleOrderQuantity BIGDECIMAL null,primary key (CPDefinitionInventoryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CPDefinitionInventory";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpDefinitionInventory.CPDefinitionInventoryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPDefinitionInventory.CPDefinitionInventoryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

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
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONINVENTORYID_COLUMN_BITMASK = 16L;

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

	public CPDefinitionInventoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPDefinitionInventoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionInventory.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionInventory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPDefinitionInventory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionInventory, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPDefinitionInventory)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPDefinitionInventory, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPDefinitionInventory, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPDefinitionInventory)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPDefinitionInventory, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPDefinitionInventory, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CPDefinitionInventory, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CPDefinitionInventory, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CPDefinitionInventory, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CPDefinitionInventory::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", CPDefinitionInventory::getCtCollectionId);
			attributeGetterFunctions.put(
				"uuid", CPDefinitionInventory::getUuid);
			attributeGetterFunctions.put(
				"CPDefinitionInventoryId",
				CPDefinitionInventory::getCPDefinitionInventoryId);
			attributeGetterFunctions.put(
				"groupId", CPDefinitionInventory::getGroupId);
			attributeGetterFunctions.put(
				"companyId", CPDefinitionInventory::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CPDefinitionInventory::getUserId);
			attributeGetterFunctions.put(
				"userName", CPDefinitionInventory::getUserName);
			attributeGetterFunctions.put(
				"createDate", CPDefinitionInventory::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CPDefinitionInventory::getModifiedDate);
			attributeGetterFunctions.put(
				"CPDefinitionId", CPDefinitionInventory::getCPDefinitionId);
			attributeGetterFunctions.put(
				"CPDefinitionInventoryEngine",
				CPDefinitionInventory::getCPDefinitionInventoryEngine);
			attributeGetterFunctions.put(
				"lowStockActivity", CPDefinitionInventory::getLowStockActivity);
			attributeGetterFunctions.put(
				"displayAvailability",
				CPDefinitionInventory::getDisplayAvailability);
			attributeGetterFunctions.put(
				"displayStockQuantity",
				CPDefinitionInventory::getDisplayStockQuantity);
			attributeGetterFunctions.put(
				"minStockQuantity", CPDefinitionInventory::getMinStockQuantity);
			attributeGetterFunctions.put(
				"backOrders", CPDefinitionInventory::getBackOrders);
			attributeGetterFunctions.put(
				"minOrderQuantity", CPDefinitionInventory::getMinOrderQuantity);
			attributeGetterFunctions.put(
				"maxOrderQuantity", CPDefinitionInventory::getMaxOrderQuantity);
			attributeGetterFunctions.put(
				"allowedOrderQuantities",
				CPDefinitionInventory::getAllowedOrderQuantities);
			attributeGetterFunctions.put(
				"multipleOrderQuantity",
				CPDefinitionInventory::getMultipleOrderQuantity);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CPDefinitionInventory, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CPDefinitionInventory, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CPDefinitionInventory, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CPDefinitionInventory, String>)
					CPDefinitionInventory::setUuid);
			attributeSetterBiConsumers.put(
				"CPDefinitionInventoryId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setCPDefinitionInventoryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CPDefinitionInventory, String>)
					CPDefinitionInventory::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CPDefinitionInventory, Date>)
					CPDefinitionInventory::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CPDefinitionInventory, Date>)
					CPDefinitionInventory::setModifiedDate);
			attributeSetterBiConsumers.put(
				"CPDefinitionId",
				(BiConsumer<CPDefinitionInventory, Long>)
					CPDefinitionInventory::setCPDefinitionId);
			attributeSetterBiConsumers.put(
				"CPDefinitionInventoryEngine",
				(BiConsumer<CPDefinitionInventory, String>)
					CPDefinitionInventory::setCPDefinitionInventoryEngine);
			attributeSetterBiConsumers.put(
				"lowStockActivity",
				(BiConsumer<CPDefinitionInventory, String>)
					CPDefinitionInventory::setLowStockActivity);
			attributeSetterBiConsumers.put(
				"displayAvailability",
				(BiConsumer<CPDefinitionInventory, Boolean>)
					CPDefinitionInventory::setDisplayAvailability);
			attributeSetterBiConsumers.put(
				"displayStockQuantity",
				(BiConsumer<CPDefinitionInventory, Boolean>)
					CPDefinitionInventory::setDisplayStockQuantity);
			attributeSetterBiConsumers.put(
				"minStockQuantity",
				(BiConsumer<CPDefinitionInventory, BigDecimal>)
					CPDefinitionInventory::setMinStockQuantity);
			attributeSetterBiConsumers.put(
				"backOrders",
				(BiConsumer<CPDefinitionInventory, Boolean>)
					CPDefinitionInventory::setBackOrders);
			attributeSetterBiConsumers.put(
				"minOrderQuantity",
				(BiConsumer<CPDefinitionInventory, BigDecimal>)
					CPDefinitionInventory::setMinOrderQuantity);
			attributeSetterBiConsumers.put(
				"maxOrderQuantity",
				(BiConsumer<CPDefinitionInventory, BigDecimal>)
					CPDefinitionInventory::setMaxOrderQuantity);
			attributeSetterBiConsumers.put(
				"allowedOrderQuantities",
				(BiConsumer<CPDefinitionInventory, String>)
					CPDefinitionInventory::setAllowedOrderQuantities);
			attributeSetterBiConsumers.put(
				"multipleOrderQuantity",
				(BiConsumer<CPDefinitionInventory, BigDecimal>)
					CPDefinitionInventory::setMultipleOrderQuantity);

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

	@JSON
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

	@JSON
	@Override
	public long getCPDefinitionInventoryId() {
		return _CPDefinitionInventoryId;
	}

	@Override
	public void setCPDefinitionInventoryId(long CPDefinitionInventoryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionInventoryId = CPDefinitionInventoryId;
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionId = CPDefinitionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPDefinitionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
	}

	@JSON
	@Override
	public String getCPDefinitionInventoryEngine() {
		if (_CPDefinitionInventoryEngine == null) {
			return "";
		}
		else {
			return _CPDefinitionInventoryEngine;
		}
	}

	@Override
	public void setCPDefinitionInventoryEngine(
		String CPDefinitionInventoryEngine) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionInventoryEngine = CPDefinitionInventoryEngine;
	}

	@JSON
	@Override
	public String getLowStockActivity() {
		if (_lowStockActivity == null) {
			return "";
		}
		else {
			return _lowStockActivity;
		}
	}

	@Override
	public void setLowStockActivity(String lowStockActivity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lowStockActivity = lowStockActivity;
	}

	@JSON
	@Override
	public boolean getDisplayAvailability() {
		return _displayAvailability;
	}

	@JSON
	@Override
	public boolean isDisplayAvailability() {
		return _displayAvailability;
	}

	@Override
	public void setDisplayAvailability(boolean displayAvailability) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayAvailability = displayAvailability;
	}

	@JSON
	@Override
	public boolean getDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	@JSON
	@Override
	public boolean isDisplayStockQuantity() {
		return _displayStockQuantity;
	}

	@Override
	public void setDisplayStockQuantity(boolean displayStockQuantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayStockQuantity = displayStockQuantity;
	}

	@JSON
	@Override
	public BigDecimal getMinStockQuantity() {
		return _minStockQuantity;
	}

	@Override
	public void setMinStockQuantity(BigDecimal minStockQuantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_minStockQuantity = minStockQuantity;
	}

	@JSON
	@Override
	public boolean getBackOrders() {
		return _backOrders;
	}

	@JSON
	@Override
	public boolean isBackOrders() {
		return _backOrders;
	}

	@Override
	public void setBackOrders(boolean backOrders) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_backOrders = backOrders;
	}

	@JSON
	@Override
	public BigDecimal getMinOrderQuantity() {
		return _minOrderQuantity;
	}

	@Override
	public void setMinOrderQuantity(BigDecimal minOrderQuantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_minOrderQuantity = minOrderQuantity;
	}

	@JSON
	@Override
	public BigDecimal getMaxOrderQuantity() {
		return _maxOrderQuantity;
	}

	@Override
	public void setMaxOrderQuantity(BigDecimal maxOrderQuantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_maxOrderQuantity = maxOrderQuantity;
	}

	@JSON
	@Override
	public String getAllowedOrderQuantities() {
		if (_allowedOrderQuantities == null) {
			return "";
		}
		else {
			return _allowedOrderQuantities;
		}
	}

	@Override
	public void setAllowedOrderQuantities(String allowedOrderQuantities) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_allowedOrderQuantities = allowedOrderQuantities;
	}

	@JSON
	@Override
	public BigDecimal getMultipleOrderQuantity() {
		return _multipleOrderQuantity;
	}

	@Override
	public void setMultipleOrderQuantity(BigDecimal multipleOrderQuantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_multipleOrderQuantity = multipleOrderQuantity;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CPDefinitionInventory.class.getName()));
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
			getCompanyId(), CPDefinitionInventory.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CPDefinitionInventory toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPDefinitionInventory>
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
		CPDefinitionInventoryImpl cpDefinitionInventoryImpl =
			new CPDefinitionInventoryImpl();

		cpDefinitionInventoryImpl.setMvccVersion(getMvccVersion());
		cpDefinitionInventoryImpl.setCtCollectionId(getCtCollectionId());
		cpDefinitionInventoryImpl.setUuid(getUuid());
		cpDefinitionInventoryImpl.setCPDefinitionInventoryId(
			getCPDefinitionInventoryId());
		cpDefinitionInventoryImpl.setGroupId(getGroupId());
		cpDefinitionInventoryImpl.setCompanyId(getCompanyId());
		cpDefinitionInventoryImpl.setUserId(getUserId());
		cpDefinitionInventoryImpl.setUserName(getUserName());
		cpDefinitionInventoryImpl.setCreateDate(getCreateDate());
		cpDefinitionInventoryImpl.setModifiedDate(getModifiedDate());
		cpDefinitionInventoryImpl.setCPDefinitionId(getCPDefinitionId());
		cpDefinitionInventoryImpl.setCPDefinitionInventoryEngine(
			getCPDefinitionInventoryEngine());
		cpDefinitionInventoryImpl.setLowStockActivity(getLowStockActivity());
		cpDefinitionInventoryImpl.setDisplayAvailability(
			isDisplayAvailability());
		cpDefinitionInventoryImpl.setDisplayStockQuantity(
			isDisplayStockQuantity());
		cpDefinitionInventoryImpl.setMinStockQuantity(getMinStockQuantity());
		cpDefinitionInventoryImpl.setBackOrders(isBackOrders());
		cpDefinitionInventoryImpl.setMinOrderQuantity(getMinOrderQuantity());
		cpDefinitionInventoryImpl.setMaxOrderQuantity(getMaxOrderQuantity());
		cpDefinitionInventoryImpl.setAllowedOrderQuantities(
			getAllowedOrderQuantities());
		cpDefinitionInventoryImpl.setMultipleOrderQuantity(
			getMultipleOrderQuantity());

		cpDefinitionInventoryImpl.resetOriginalValues();

		return cpDefinitionInventoryImpl;
	}

	@Override
	public CPDefinitionInventory cloneWithOriginalValues() {
		CPDefinitionInventoryImpl cpDefinitionInventoryImpl =
			new CPDefinitionInventoryImpl();

		cpDefinitionInventoryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		cpDefinitionInventoryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		cpDefinitionInventoryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		cpDefinitionInventoryImpl.setCPDefinitionInventoryId(
			this.<Long>getColumnOriginalValue("CPDefinitionInventoryId"));
		cpDefinitionInventoryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		cpDefinitionInventoryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		cpDefinitionInventoryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		cpDefinitionInventoryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		cpDefinitionInventoryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		cpDefinitionInventoryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		cpDefinitionInventoryImpl.setCPDefinitionId(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
		cpDefinitionInventoryImpl.setCPDefinitionInventoryEngine(
			this.<String>getColumnOriginalValue("CPDefinitionInventoryEngine"));
		cpDefinitionInventoryImpl.setLowStockActivity(
			this.<String>getColumnOriginalValue("lowStockActivity"));
		cpDefinitionInventoryImpl.setDisplayAvailability(
			this.<Boolean>getColumnOriginalValue("displayAvailability"));
		cpDefinitionInventoryImpl.setDisplayStockQuantity(
			this.<Boolean>getColumnOriginalValue("displayStockQuantity"));
		cpDefinitionInventoryImpl.setMinStockQuantity(
			this.<BigDecimal>getColumnOriginalValue("minStockQuantity"));
		cpDefinitionInventoryImpl.setBackOrders(
			this.<Boolean>getColumnOriginalValue("backOrders"));
		cpDefinitionInventoryImpl.setMinOrderQuantity(
			this.<BigDecimal>getColumnOriginalValue("minOrderQuantity"));
		cpDefinitionInventoryImpl.setMaxOrderQuantity(
			this.<BigDecimal>getColumnOriginalValue("maxOrderQuantity"));
		cpDefinitionInventoryImpl.setAllowedOrderQuantities(
			this.<String>getColumnOriginalValue("allowedOrderQuantities"));
		cpDefinitionInventoryImpl.setMultipleOrderQuantity(
			this.<BigDecimal>getColumnOriginalValue("multipleOrderQuantity"));

		return cpDefinitionInventoryImpl;
	}

	@Override
	public int compareTo(CPDefinitionInventory cpDefinitionInventory) {
		long primaryKey = cpDefinitionInventory.getPrimaryKey();

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

		if (!(object instanceof CPDefinitionInventory)) {
			return false;
		}

		CPDefinitionInventory cpDefinitionInventory =
			(CPDefinitionInventory)object;

		long primaryKey = cpDefinitionInventory.getPrimaryKey();

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
	public CacheModel<CPDefinitionInventory> toCacheModel() {
		CPDefinitionInventoryCacheModel cpDefinitionInventoryCacheModel =
			new CPDefinitionInventoryCacheModel();

		cpDefinitionInventoryCacheModel.mvccVersion = getMvccVersion();

		cpDefinitionInventoryCacheModel.ctCollectionId = getCtCollectionId();

		cpDefinitionInventoryCacheModel.uuid = getUuid();

		String uuid = cpDefinitionInventoryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpDefinitionInventoryCacheModel.uuid = null;
		}

		cpDefinitionInventoryCacheModel.CPDefinitionInventoryId =
			getCPDefinitionInventoryId();

		cpDefinitionInventoryCacheModel.groupId = getGroupId();

		cpDefinitionInventoryCacheModel.companyId = getCompanyId();

		cpDefinitionInventoryCacheModel.userId = getUserId();

		cpDefinitionInventoryCacheModel.userName = getUserName();

		String userName = cpDefinitionInventoryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpDefinitionInventoryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpDefinitionInventoryCacheModel.createDate = createDate.getTime();
		}
		else {
			cpDefinitionInventoryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpDefinitionInventoryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			cpDefinitionInventoryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpDefinitionInventoryCacheModel.CPDefinitionId = getCPDefinitionId();

		cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine =
			getCPDefinitionInventoryEngine();

		String CPDefinitionInventoryEngine =
			cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine;

		if ((CPDefinitionInventoryEngine != null) &&
			(CPDefinitionInventoryEngine.length() == 0)) {

			cpDefinitionInventoryCacheModel.CPDefinitionInventoryEngine = null;
		}

		cpDefinitionInventoryCacheModel.lowStockActivity =
			getLowStockActivity();

		String lowStockActivity =
			cpDefinitionInventoryCacheModel.lowStockActivity;

		if ((lowStockActivity != null) && (lowStockActivity.length() == 0)) {
			cpDefinitionInventoryCacheModel.lowStockActivity = null;
		}

		cpDefinitionInventoryCacheModel.displayAvailability =
			isDisplayAvailability();

		cpDefinitionInventoryCacheModel.displayStockQuantity =
			isDisplayStockQuantity();

		cpDefinitionInventoryCacheModel.minStockQuantity =
			getMinStockQuantity();

		cpDefinitionInventoryCacheModel.backOrders = isBackOrders();

		cpDefinitionInventoryCacheModel.minOrderQuantity =
			getMinOrderQuantity();

		cpDefinitionInventoryCacheModel.maxOrderQuantity =
			getMaxOrderQuantity();

		cpDefinitionInventoryCacheModel.allowedOrderQuantities =
			getAllowedOrderQuantities();

		String allowedOrderQuantities =
			cpDefinitionInventoryCacheModel.allowedOrderQuantities;

		if ((allowedOrderQuantities != null) &&
			(allowedOrderQuantities.length() == 0)) {

			cpDefinitionInventoryCacheModel.allowedOrderQuantities = null;
		}

		cpDefinitionInventoryCacheModel.multipleOrderQuantity =
			getMultipleOrderQuantity();

		return cpDefinitionInventoryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPDefinitionInventory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPDefinitionInventory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionInventory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CPDefinitionInventory)this);

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

		private static final Function<InvocationHandler, CPDefinitionInventory>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CPDefinitionInventory.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _CPDefinitionInventoryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPDefinitionId;
	private String _CPDefinitionInventoryEngine;
	private String _lowStockActivity;
	private boolean _displayAvailability;
	private boolean _displayStockQuantity;
	private BigDecimal _minStockQuantity;
	private boolean _backOrders;
	private BigDecimal _minOrderQuantity;
	private BigDecimal _maxOrderQuantity;
	private String _allowedOrderQuantities;
	private BigDecimal _multipleOrderQuantity;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CPDefinitionInventory, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CPDefinitionInventory)this);
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
			"CPDefinitionInventoryId", _CPDefinitionInventoryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("CPDefinitionId", _CPDefinitionId);
		_columnOriginalValues.put(
			"CPDefinitionInventoryEngine", _CPDefinitionInventoryEngine);
		_columnOriginalValues.put("lowStockActivity", _lowStockActivity);
		_columnOriginalValues.put("displayAvailability", _displayAvailability);
		_columnOriginalValues.put(
			"displayStockQuantity", _displayStockQuantity);
		_columnOriginalValues.put("minStockQuantity", _minStockQuantity);
		_columnOriginalValues.put("backOrders", _backOrders);
		_columnOriginalValues.put("minOrderQuantity", _minOrderQuantity);
		_columnOriginalValues.put("maxOrderQuantity", _maxOrderQuantity);
		_columnOriginalValues.put(
			"allowedOrderQuantities", _allowedOrderQuantities);
		_columnOriginalValues.put(
			"multipleOrderQuantity", _multipleOrderQuantity);
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

		columnBitmasks.put("CPDefinitionInventoryId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("CPDefinitionId", 1024L);

		columnBitmasks.put("CPDefinitionInventoryEngine", 2048L);

		columnBitmasks.put("lowStockActivity", 4096L);

		columnBitmasks.put("displayAvailability", 8192L);

		columnBitmasks.put("displayStockQuantity", 16384L);

		columnBitmasks.put("minStockQuantity", 32768L);

		columnBitmasks.put("backOrders", 65536L);

		columnBitmasks.put("minOrderQuantity", 131072L);

		columnBitmasks.put("maxOrderQuantity", 262144L);

		columnBitmasks.put("allowedOrderQuantities", 524288L);

		columnBitmasks.put("multipleOrderQuantity", 1048576L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CPDefinitionInventory _escapedModel;

}