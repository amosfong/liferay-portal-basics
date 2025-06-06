/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.style.book.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.model.StyleBookEntryVersion;
import com.liferay.style.book.model.StyleBookEntryVersionModel;

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
 * The base model implementation for the StyleBookEntryVersion service. Represents a row in the &quot;StyleBookEntryVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>StyleBookEntryVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StyleBookEntryVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StyleBookEntryVersionImpl
 * @generated
 */
public class StyleBookEntryVersionModelImpl
	extends BaseModelImpl<StyleBookEntryVersion>
	implements StyleBookEntryVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a style book entry version model instance should use the <code>StyleBookEntryVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "StyleBookEntryVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"styleBookEntryVersionId", Types.BIGINT}, {"version", Types.INTEGER},
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"styleBookEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"defaultStyleBookEntry", Types.BOOLEAN},
		{"frontendTokensValues", Types.CLOB}, {"name", Types.VARCHAR},
		{"previewFileEntryId", Types.BIGINT},
		{"styleBookEntryKey", Types.VARCHAR}, {"themeId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("styleBookEntryVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("version", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("styleBookEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("defaultStyleBookEntry", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("frontendTokensValues", Types.CLOB);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("previewFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("styleBookEntryKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("themeId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table StyleBookEntryVersion (styleBookEntryVersionId LONG not null primary key,version INTEGER,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,styleBookEntryId LONG,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,defaultStyleBookEntry BOOLEAN,frontendTokensValues TEXT null,name VARCHAR(75) null,previewFileEntryId LONG,styleBookEntryKey VARCHAR(75) null,themeId VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP =
		"drop table StyleBookEntryVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY styleBookEntryVersion.version DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY StyleBookEntryVersion.version DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEFAULTSTYLEBOOKENTRY_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STYLEBOOKENTRYID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STYLEBOOKENTRYKEY_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VERSION_COLUMN_BITMASK = 128L;

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

	public StyleBookEntryVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _styleBookEntryVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStyleBookEntryVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _styleBookEntryVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return StyleBookEntryVersion.class;
	}

	@Override
	public String getModelClassName() {
		return StyleBookEntryVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<StyleBookEntryVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<StyleBookEntryVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<StyleBookEntryVersion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((StyleBookEntryVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<StyleBookEntryVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<StyleBookEntryVersion, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(StyleBookEntryVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<StyleBookEntryVersion, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<StyleBookEntryVersion, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<StyleBookEntryVersion, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<StyleBookEntryVersion, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<StyleBookEntryVersion, Object>>();

			attributeGetterFunctions.put(
				"styleBookEntryVersionId",
				StyleBookEntryVersion::getStyleBookEntryVersionId);
			attributeGetterFunctions.put(
				"version", StyleBookEntryVersion::getVersion);
			attributeGetterFunctions.put(
				"uuid", StyleBookEntryVersion::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				StyleBookEntryVersion::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"styleBookEntryId", StyleBookEntryVersion::getStyleBookEntryId);
			attributeGetterFunctions.put(
				"groupId", StyleBookEntryVersion::getGroupId);
			attributeGetterFunctions.put(
				"companyId", StyleBookEntryVersion::getCompanyId);
			attributeGetterFunctions.put(
				"userId", StyleBookEntryVersion::getUserId);
			attributeGetterFunctions.put(
				"userName", StyleBookEntryVersion::getUserName);
			attributeGetterFunctions.put(
				"createDate", StyleBookEntryVersion::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", StyleBookEntryVersion::getModifiedDate);
			attributeGetterFunctions.put(
				"defaultStyleBookEntry",
				StyleBookEntryVersion::getDefaultStyleBookEntry);
			attributeGetterFunctions.put(
				"frontendTokensValues",
				StyleBookEntryVersion::getFrontendTokensValues);
			attributeGetterFunctions.put(
				"name", StyleBookEntryVersion::getName);
			attributeGetterFunctions.put(
				"previewFileEntryId",
				StyleBookEntryVersion::getPreviewFileEntryId);
			attributeGetterFunctions.put(
				"styleBookEntryKey",
				StyleBookEntryVersion::getStyleBookEntryKey);
			attributeGetterFunctions.put(
				"themeId", StyleBookEntryVersion::getThemeId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<StyleBookEntryVersion, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<StyleBookEntryVersion, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<StyleBookEntryVersion, ?>>();

			attributeSetterBiConsumers.put(
				"styleBookEntryVersionId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setStyleBookEntryVersionId);
			attributeSetterBiConsumers.put(
				"version",
				(BiConsumer<StyleBookEntryVersion, Integer>)
					StyleBookEntryVersion::setVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"styleBookEntryId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setStyleBookEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<StyleBookEntryVersion, Date>)
					StyleBookEntryVersion::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<StyleBookEntryVersion, Date>)
					StyleBookEntryVersion::setModifiedDate);
			attributeSetterBiConsumers.put(
				"defaultStyleBookEntry",
				(BiConsumer<StyleBookEntryVersion, Boolean>)
					StyleBookEntryVersion::setDefaultStyleBookEntry);
			attributeSetterBiConsumers.put(
				"frontendTokensValues",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setFrontendTokensValues);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setName);
			attributeSetterBiConsumers.put(
				"previewFileEntryId",
				(BiConsumer<StyleBookEntryVersion, Long>)
					StyleBookEntryVersion::setPreviewFileEntryId);
			attributeSetterBiConsumers.put(
				"styleBookEntryKey",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setStyleBookEntryKey);
			attributeSetterBiConsumers.put(
				"themeId",
				(BiConsumer<StyleBookEntryVersion, String>)
					StyleBookEntryVersion::setThemeId);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getVersionedModelId() {
		return getStyleBookEntryId();
	}

	@Override
	public void populateVersionedModel(StyleBookEntry styleBookEntry) {
		styleBookEntry.setUuid(getUuid());
		styleBookEntry.setExternalReferenceCode(getExternalReferenceCode());
		styleBookEntry.setGroupId(getGroupId());
		styleBookEntry.setCompanyId(getCompanyId());
		styleBookEntry.setUserId(getUserId());
		styleBookEntry.setUserName(getUserName());
		styleBookEntry.setCreateDate(getCreateDate());
		styleBookEntry.setModifiedDate(getModifiedDate());
		styleBookEntry.setDefaultStyleBookEntry(getDefaultStyleBookEntry());
		styleBookEntry.setFrontendTokensValues(getFrontendTokensValues());
		styleBookEntry.setName(getName());
		styleBookEntry.setPreviewFileEntryId(getPreviewFileEntryId());
		styleBookEntry.setStyleBookEntryKey(getStyleBookEntryKey());
		styleBookEntry.setThemeId(getThemeId());
	}

	@Override
	public void setVersionedModelId(long styleBookEntryId) {
		setStyleBookEntryId(styleBookEntryId);
	}

	@Override
	public StyleBookEntry toVersionedModel() {
		StyleBookEntry styleBookEntry = new StyleBookEntryImpl();

		styleBookEntry.setPrimaryKey(getVersionedModelId());
		styleBookEntry.setHeadId(-getVersionedModelId());

		populateVersionedModel(styleBookEntry);

		return styleBookEntry;
	}

	@Override
	public long getStyleBookEntryVersionId() {
		return _styleBookEntryVersionId;
	}

	@Override
	public void setStyleBookEntryVersionId(long styleBookEntryVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_styleBookEntryVersionId = styleBookEntryVersionId;
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
	public long getStyleBookEntryId() {
		return _styleBookEntryId;
	}

	@Override
	public void setStyleBookEntryId(long styleBookEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_styleBookEntryId = styleBookEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalStyleBookEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("styleBookEntryId"));
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
	public boolean getDefaultStyleBookEntry() {
		return _defaultStyleBookEntry;
	}

	@Override
	public boolean isDefaultStyleBookEntry() {
		return _defaultStyleBookEntry;
	}

	@Override
	public void setDefaultStyleBookEntry(boolean defaultStyleBookEntry) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultStyleBookEntry = defaultStyleBookEntry;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDefaultStyleBookEntry() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("defaultStyleBookEntry"));
	}

	@Override
	public String getFrontendTokensValues() {
		if (_frontendTokensValues == null) {
			return "";
		}
		else {
			return _frontendTokensValues;
		}
	}

	@Override
	public void setFrontendTokensValues(String frontendTokensValues) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_frontendTokensValues = frontendTokensValues;
	}

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

	@Override
	public long getPreviewFileEntryId() {
		return _previewFileEntryId;
	}

	@Override
	public void setPreviewFileEntryId(long previewFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_previewFileEntryId = previewFileEntryId;
	}

	@Override
	public String getStyleBookEntryKey() {
		if (_styleBookEntryKey == null) {
			return "";
		}
		else {
			return _styleBookEntryKey;
		}
	}

	@Override
	public void setStyleBookEntryKey(String styleBookEntryKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_styleBookEntryKey = styleBookEntryKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalStyleBookEntryKey() {
		return getColumnOriginalValue("styleBookEntryKey");
	}

	@Override
	public String getThemeId() {
		if (_themeId == null) {
			return "";
		}
		else {
			return _themeId;
		}
	}

	@Override
	public void setThemeId(String themeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_themeId = themeId;
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
			getCompanyId(), StyleBookEntryVersion.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public StyleBookEntryVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, StyleBookEntryVersion>
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
		StyleBookEntryVersionImpl styleBookEntryVersionImpl =
			new StyleBookEntryVersionImpl();

		styleBookEntryVersionImpl.setStyleBookEntryVersionId(
			getStyleBookEntryVersionId());
		styleBookEntryVersionImpl.setVersion(getVersion());
		styleBookEntryVersionImpl.setUuid(getUuid());
		styleBookEntryVersionImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		styleBookEntryVersionImpl.setStyleBookEntryId(getStyleBookEntryId());
		styleBookEntryVersionImpl.setGroupId(getGroupId());
		styleBookEntryVersionImpl.setCompanyId(getCompanyId());
		styleBookEntryVersionImpl.setUserId(getUserId());
		styleBookEntryVersionImpl.setUserName(getUserName());
		styleBookEntryVersionImpl.setCreateDate(getCreateDate());
		styleBookEntryVersionImpl.setModifiedDate(getModifiedDate());
		styleBookEntryVersionImpl.setDefaultStyleBookEntry(
			isDefaultStyleBookEntry());
		styleBookEntryVersionImpl.setFrontendTokensValues(
			getFrontendTokensValues());
		styleBookEntryVersionImpl.setName(getName());
		styleBookEntryVersionImpl.setPreviewFileEntryId(
			getPreviewFileEntryId());
		styleBookEntryVersionImpl.setStyleBookEntryKey(getStyleBookEntryKey());
		styleBookEntryVersionImpl.setThemeId(getThemeId());

		styleBookEntryVersionImpl.resetOriginalValues();

		return styleBookEntryVersionImpl;
	}

	@Override
	public StyleBookEntryVersion cloneWithOriginalValues() {
		StyleBookEntryVersionImpl styleBookEntryVersionImpl =
			new StyleBookEntryVersionImpl();

		styleBookEntryVersionImpl.setStyleBookEntryVersionId(
			this.<Long>getColumnOriginalValue("styleBookEntryVersionId"));
		styleBookEntryVersionImpl.setVersion(
			this.<Integer>getColumnOriginalValue("version"));
		styleBookEntryVersionImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		styleBookEntryVersionImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		styleBookEntryVersionImpl.setStyleBookEntryId(
			this.<Long>getColumnOriginalValue("styleBookEntryId"));
		styleBookEntryVersionImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		styleBookEntryVersionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		styleBookEntryVersionImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		styleBookEntryVersionImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		styleBookEntryVersionImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		styleBookEntryVersionImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		styleBookEntryVersionImpl.setDefaultStyleBookEntry(
			this.<Boolean>getColumnOriginalValue("defaultStyleBookEntry"));
		styleBookEntryVersionImpl.setFrontendTokensValues(
			this.<String>getColumnOriginalValue("frontendTokensValues"));
		styleBookEntryVersionImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		styleBookEntryVersionImpl.setPreviewFileEntryId(
			this.<Long>getColumnOriginalValue("previewFileEntryId"));
		styleBookEntryVersionImpl.setStyleBookEntryKey(
			this.<String>getColumnOriginalValue("styleBookEntryKey"));
		styleBookEntryVersionImpl.setThemeId(
			this.<String>getColumnOriginalValue("themeId"));

		return styleBookEntryVersionImpl;
	}

	@Override
	public int compareTo(StyleBookEntryVersion styleBookEntryVersion) {
		int value = 0;

		if (getVersion() < styleBookEntryVersion.getVersion()) {
			value = -1;
		}
		else if (getVersion() > styleBookEntryVersion.getVersion()) {
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

		if (!(object instanceof StyleBookEntryVersion)) {
			return false;
		}

		StyleBookEntryVersion styleBookEntryVersion =
			(StyleBookEntryVersion)object;

		long primaryKey = styleBookEntryVersion.getPrimaryKey();

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
	public CacheModel<StyleBookEntryVersion> toCacheModel() {
		StyleBookEntryVersionCacheModel styleBookEntryVersionCacheModel =
			new StyleBookEntryVersionCacheModel();

		styleBookEntryVersionCacheModel.styleBookEntryVersionId =
			getStyleBookEntryVersionId();

		styleBookEntryVersionCacheModel.version = getVersion();

		styleBookEntryVersionCacheModel.uuid = getUuid();

		String uuid = styleBookEntryVersionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			styleBookEntryVersionCacheModel.uuid = null;
		}

		styleBookEntryVersionCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			styleBookEntryVersionCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			styleBookEntryVersionCacheModel.externalReferenceCode = null;
		}

		styleBookEntryVersionCacheModel.styleBookEntryId =
			getStyleBookEntryId();

		styleBookEntryVersionCacheModel.groupId = getGroupId();

		styleBookEntryVersionCacheModel.companyId = getCompanyId();

		styleBookEntryVersionCacheModel.userId = getUserId();

		styleBookEntryVersionCacheModel.userName = getUserName();

		String userName = styleBookEntryVersionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			styleBookEntryVersionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			styleBookEntryVersionCacheModel.createDate = createDate.getTime();
		}
		else {
			styleBookEntryVersionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			styleBookEntryVersionCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			styleBookEntryVersionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		styleBookEntryVersionCacheModel.defaultStyleBookEntry =
			isDefaultStyleBookEntry();

		styleBookEntryVersionCacheModel.frontendTokensValues =
			getFrontendTokensValues();

		String frontendTokensValues =
			styleBookEntryVersionCacheModel.frontendTokensValues;

		if ((frontendTokensValues != null) &&
			(frontendTokensValues.length() == 0)) {

			styleBookEntryVersionCacheModel.frontendTokensValues = null;
		}

		styleBookEntryVersionCacheModel.name = getName();

		String name = styleBookEntryVersionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			styleBookEntryVersionCacheModel.name = null;
		}

		styleBookEntryVersionCacheModel.previewFileEntryId =
			getPreviewFileEntryId();

		styleBookEntryVersionCacheModel.styleBookEntryKey =
			getStyleBookEntryKey();

		String styleBookEntryKey =
			styleBookEntryVersionCacheModel.styleBookEntryKey;

		if ((styleBookEntryKey != null) && (styleBookEntryKey.length() == 0)) {
			styleBookEntryVersionCacheModel.styleBookEntryKey = null;
		}

		styleBookEntryVersionCacheModel.themeId = getThemeId();

		String themeId = styleBookEntryVersionCacheModel.themeId;

		if ((themeId != null) && (themeId.length() == 0)) {
			styleBookEntryVersionCacheModel.themeId = null;
		}

		return styleBookEntryVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<StyleBookEntryVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<StyleBookEntryVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<StyleBookEntryVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(StyleBookEntryVersion)this);

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

		private static final Function<InvocationHandler, StyleBookEntryVersion>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					StyleBookEntryVersion.class, ModelWrapper.class);

	}

	private long _styleBookEntryVersionId;
	private int _version;
	private String _uuid;
	private String _externalReferenceCode;
	private long _styleBookEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _defaultStyleBookEntry;
	private String _frontendTokensValues;
	private String _name;
	private long _previewFileEntryId;
	private String _styleBookEntryKey;
	private String _themeId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<StyleBookEntryVersion, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((StyleBookEntryVersion)this);
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
			"styleBookEntryVersionId", _styleBookEntryVersionId);
		_columnOriginalValues.put("version", _version);
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("styleBookEntryId", _styleBookEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"defaultStyleBookEntry", _defaultStyleBookEntry);
		_columnOriginalValues.put(
			"frontendTokensValues", _frontendTokensValues);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("previewFileEntryId", _previewFileEntryId);
		_columnOriginalValues.put("styleBookEntryKey", _styleBookEntryKey);
		_columnOriginalValues.put("themeId", _themeId);
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

		columnBitmasks.put("styleBookEntryVersionId", 1L);

		columnBitmasks.put("version", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("externalReferenceCode", 8L);

		columnBitmasks.put("styleBookEntryId", 16L);

		columnBitmasks.put("groupId", 32L);

		columnBitmasks.put("companyId", 64L);

		columnBitmasks.put("userId", 128L);

		columnBitmasks.put("userName", 256L);

		columnBitmasks.put("createDate", 512L);

		columnBitmasks.put("modifiedDate", 1024L);

		columnBitmasks.put("defaultStyleBookEntry", 2048L);

		columnBitmasks.put("frontendTokensValues", 4096L);

		columnBitmasks.put("name", 8192L);

		columnBitmasks.put("previewFileEntryId", 16384L);

		columnBitmasks.put("styleBookEntryKey", 32768L);

		columnBitmasks.put("themeId", 65536L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private StyleBookEntryVersion _escapedModel;

}