/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shop.by.diagram.model.impl;

import com.liferay.commerce.shop.by.diagram.model.CSDiagramSetting;
import com.liferay.commerce.shop.by.diagram.model.CSDiagramSettingModel;
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
 * The base model implementation for the CSDiagramSetting service. Represents a row in the &quot;CSDiagramSetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CSDiagramSettingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CSDiagramSettingImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CSDiagramSettingImpl
 * @generated
 */
@JSON(strict = true)
public class CSDiagramSettingModelImpl
	extends BaseModelImpl<CSDiagramSetting> implements CSDiagramSettingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cs diagram setting model instance should use the <code>CSDiagramSetting</code> interface instead.
	 */
	public static final String TABLE_NAME = "CSDiagramSetting";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"CSDiagramSettingId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"CPAttachmentFileEntryId", Types.BIGINT},
		{"CPDefinitionId", Types.BIGINT}, {"color", Types.VARCHAR},
		{"radius", Types.DOUBLE}, {"type_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CSDiagramSettingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPAttachmentFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("color", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("radius", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CSDiagramSetting (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CSDiagramSettingId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPAttachmentFileEntryId LONG,CPDefinitionId LONG,color VARCHAR(75) null,radius DOUBLE,type_ VARCHAR(75) null,primary key (CSDiagramSettingId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table CSDiagramSetting";

	public static final String ORDER_BY_JPQL =
		" ORDER BY csDiagramSetting.CSDiagramSettingId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CSDiagramSetting.CSDiagramSettingId ASC";

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
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CSDIAGRAMSETTINGID_COLUMN_BITMASK = 8L;

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

	public CSDiagramSettingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CSDiagramSettingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCSDiagramSettingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CSDiagramSettingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CSDiagramSetting.class;
	}

	@Override
	public String getModelClassName() {
		return CSDiagramSetting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CSDiagramSetting, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CSDiagramSetting, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CSDiagramSetting, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CSDiagramSetting)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CSDiagramSetting, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CSDiagramSetting, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CSDiagramSetting)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CSDiagramSetting, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CSDiagramSetting, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CSDiagramSetting, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CSDiagramSetting, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CSDiagramSetting, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CSDiagramSetting::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", CSDiagramSetting::getCtCollectionId);
			attributeGetterFunctions.put("uuid", CSDiagramSetting::getUuid);
			attributeGetterFunctions.put(
				"CSDiagramSettingId", CSDiagramSetting::getCSDiagramSettingId);
			attributeGetterFunctions.put(
				"companyId", CSDiagramSetting::getCompanyId);
			attributeGetterFunctions.put("userId", CSDiagramSetting::getUserId);
			attributeGetterFunctions.put(
				"userName", CSDiagramSetting::getUserName);
			attributeGetterFunctions.put(
				"createDate", CSDiagramSetting::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CSDiagramSetting::getModifiedDate);
			attributeGetterFunctions.put(
				"CPAttachmentFileEntryId",
				CSDiagramSetting::getCPAttachmentFileEntryId);
			attributeGetterFunctions.put(
				"CPDefinitionId", CSDiagramSetting::getCPDefinitionId);
			attributeGetterFunctions.put("color", CSDiagramSetting::getColor);
			attributeGetterFunctions.put("radius", CSDiagramSetting::getRadius);
			attributeGetterFunctions.put("type", CSDiagramSetting::getType);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<CSDiagramSetting, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CSDiagramSetting, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CSDiagramSetting, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CSDiagramSetting, String>)
					CSDiagramSetting::setUuid);
			attributeSetterBiConsumers.put(
				"CSDiagramSettingId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setCSDiagramSettingId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CSDiagramSetting, String>)
					CSDiagramSetting::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CSDiagramSetting, Date>)
					CSDiagramSetting::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CSDiagramSetting, Date>)
					CSDiagramSetting::setModifiedDate);
			attributeSetterBiConsumers.put(
				"CPAttachmentFileEntryId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setCPAttachmentFileEntryId);
			attributeSetterBiConsumers.put(
				"CPDefinitionId",
				(BiConsumer<CSDiagramSetting, Long>)
					CSDiagramSetting::setCPDefinitionId);
			attributeSetterBiConsumers.put(
				"color",
				(BiConsumer<CSDiagramSetting, String>)
					CSDiagramSetting::setColor);
			attributeSetterBiConsumers.put(
				"radius",
				(BiConsumer<CSDiagramSetting, Double>)
					CSDiagramSetting::setRadius);
			attributeSetterBiConsumers.put(
				"type",
				(BiConsumer<CSDiagramSetting, String>)
					CSDiagramSetting::setType);

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
	public long getCSDiagramSettingId() {
		return _CSDiagramSettingId;
	}

	@Override
	public void setCSDiagramSettingId(long CSDiagramSettingId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CSDiagramSettingId = CSDiagramSettingId;
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
	public long getCPAttachmentFileEntryId() {
		return _CPAttachmentFileEntryId;
	}

	@Override
	public void setCPAttachmentFileEntryId(long CPAttachmentFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPAttachmentFileEntryId = CPAttachmentFileEntryId;
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
	public String getColor() {
		if (_color == null) {
			return "";
		}
		else {
			return _color;
		}
	}

	@Override
	public void setColor(String color) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_color = color;
	}

	@JSON
	@Override
	public double getRadius() {
		return _radius;
	}

	@Override
	public void setRadius(double radius) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_radius = radius;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CSDiagramSetting.class.getName()));
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
			getCompanyId(), CSDiagramSetting.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CSDiagramSetting toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CSDiagramSetting>
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
		CSDiagramSettingImpl csDiagramSettingImpl = new CSDiagramSettingImpl();

		csDiagramSettingImpl.setMvccVersion(getMvccVersion());
		csDiagramSettingImpl.setCtCollectionId(getCtCollectionId());
		csDiagramSettingImpl.setUuid(getUuid());
		csDiagramSettingImpl.setCSDiagramSettingId(getCSDiagramSettingId());
		csDiagramSettingImpl.setCompanyId(getCompanyId());
		csDiagramSettingImpl.setUserId(getUserId());
		csDiagramSettingImpl.setUserName(getUserName());
		csDiagramSettingImpl.setCreateDate(getCreateDate());
		csDiagramSettingImpl.setModifiedDate(getModifiedDate());
		csDiagramSettingImpl.setCPAttachmentFileEntryId(
			getCPAttachmentFileEntryId());
		csDiagramSettingImpl.setCPDefinitionId(getCPDefinitionId());
		csDiagramSettingImpl.setColor(getColor());
		csDiagramSettingImpl.setRadius(getRadius());
		csDiagramSettingImpl.setType(getType());

		csDiagramSettingImpl.resetOriginalValues();

		return csDiagramSettingImpl;
	}

	@Override
	public CSDiagramSetting cloneWithOriginalValues() {
		CSDiagramSettingImpl csDiagramSettingImpl = new CSDiagramSettingImpl();

		csDiagramSettingImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		csDiagramSettingImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		csDiagramSettingImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		csDiagramSettingImpl.setCSDiagramSettingId(
			this.<Long>getColumnOriginalValue("CSDiagramSettingId"));
		csDiagramSettingImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		csDiagramSettingImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		csDiagramSettingImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		csDiagramSettingImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		csDiagramSettingImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		csDiagramSettingImpl.setCPAttachmentFileEntryId(
			this.<Long>getColumnOriginalValue("CPAttachmentFileEntryId"));
		csDiagramSettingImpl.setCPDefinitionId(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
		csDiagramSettingImpl.setColor(
			this.<String>getColumnOriginalValue("color"));
		csDiagramSettingImpl.setRadius(
			this.<Double>getColumnOriginalValue("radius"));
		csDiagramSettingImpl.setType(
			this.<String>getColumnOriginalValue("type_"));

		return csDiagramSettingImpl;
	}

	@Override
	public int compareTo(CSDiagramSetting csDiagramSetting) {
		long primaryKey = csDiagramSetting.getPrimaryKey();

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

		if (!(object instanceof CSDiagramSetting)) {
			return false;
		}

		CSDiagramSetting csDiagramSetting = (CSDiagramSetting)object;

		long primaryKey = csDiagramSetting.getPrimaryKey();

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
	public CacheModel<CSDiagramSetting> toCacheModel() {
		CSDiagramSettingCacheModel csDiagramSettingCacheModel =
			new CSDiagramSettingCacheModel();

		csDiagramSettingCacheModel.mvccVersion = getMvccVersion();

		csDiagramSettingCacheModel.ctCollectionId = getCtCollectionId();

		csDiagramSettingCacheModel.uuid = getUuid();

		String uuid = csDiagramSettingCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			csDiagramSettingCacheModel.uuid = null;
		}

		csDiagramSettingCacheModel.CSDiagramSettingId = getCSDiagramSettingId();

		csDiagramSettingCacheModel.companyId = getCompanyId();

		csDiagramSettingCacheModel.userId = getUserId();

		csDiagramSettingCacheModel.userName = getUserName();

		String userName = csDiagramSettingCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			csDiagramSettingCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			csDiagramSettingCacheModel.createDate = createDate.getTime();
		}
		else {
			csDiagramSettingCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			csDiagramSettingCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			csDiagramSettingCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		csDiagramSettingCacheModel.CPAttachmentFileEntryId =
			getCPAttachmentFileEntryId();

		csDiagramSettingCacheModel.CPDefinitionId = getCPDefinitionId();

		csDiagramSettingCacheModel.color = getColor();

		String color = csDiagramSettingCacheModel.color;

		if ((color != null) && (color.length() == 0)) {
			csDiagramSettingCacheModel.color = null;
		}

		csDiagramSettingCacheModel.radius = getRadius();

		csDiagramSettingCacheModel.type = getType();

		String type = csDiagramSettingCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			csDiagramSettingCacheModel.type = null;
		}

		return csDiagramSettingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CSDiagramSetting, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CSDiagramSetting, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CSDiagramSetting, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CSDiagramSetting)this);

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

		private static final Function<InvocationHandler, CSDiagramSetting>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CSDiagramSetting.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _CSDiagramSettingId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPAttachmentFileEntryId;
	private long _CPDefinitionId;
	private String _color;
	private double _radius;
	private String _type;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CSDiagramSetting, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CSDiagramSetting)this);
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
		_columnOriginalValues.put("CSDiagramSettingId", _CSDiagramSettingId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"CPAttachmentFileEntryId", _CPAttachmentFileEntryId);
		_columnOriginalValues.put("CPDefinitionId", _CPDefinitionId);
		_columnOriginalValues.put("color", _color);
		_columnOriginalValues.put("radius", _radius);
		_columnOriginalValues.put("type_", _type);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("CSDiagramSettingId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("CPAttachmentFileEntryId", 512L);

		columnBitmasks.put("CPDefinitionId", 1024L);

		columnBitmasks.put("color", 2048L);

		columnBitmasks.put("radius", 4096L);

		columnBitmasks.put("type_", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CSDiagramSetting _escapedModel;

}