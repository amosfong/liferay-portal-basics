/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model.impl;

import com.liferay.account.model.AccountRole;
import com.liferay.account.model.AccountRoleModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
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
 * The base model implementation for the AccountRole service. Represents a row in the &quot;AccountRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AccountRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountRoleImpl
 * @generated
 */
@JSON(strict = true)
public class AccountRoleModelImpl
	extends BaseModelImpl<AccountRole> implements AccountRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account role model instance should use the <code>AccountRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "AccountRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"accountRoleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"accountEntryId", Types.BIGINT}, {"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AccountRole (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,accountRoleId LONG not null primary key,companyId LONG,accountEntryId LONG,roleId LONG)";

	public static final String TABLE_SQL_DROP = "drop table AccountRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY accountRole.accountRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AccountRole.accountRoleId ASC";

	public static final String ORDER_BY_SQL_INLINE_DISTINCT =
		" ORDER BY accountRole.accountRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACCOUNTENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROLEID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACCOUNTROLEID_COLUMN_BITMASK = 16L;

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

	public AccountRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AccountRole.class;
	}

	@Override
	public String getModelClassName() {
		return AccountRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AccountRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AccountRole)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AccountRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AccountRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AccountRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AccountRole, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AccountRole, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<AccountRole, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<AccountRole, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<AccountRole, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", AccountRole::getMvccVersion);
			attributeGetterFunctions.put(
				"externalReferenceCode", AccountRole::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"accountRoleId", AccountRole::getAccountRoleId);
			attributeGetterFunctions.put(
				"companyId", AccountRole::getCompanyId);
			attributeGetterFunctions.put(
				"accountEntryId", AccountRole::getAccountEntryId);
			attributeGetterFunctions.put("roleId", AccountRole::getRoleId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<AccountRole, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<AccountRole, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<AccountRole, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<AccountRole, Long>)AccountRole::setMvccVersion);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<AccountRole, String>)
					AccountRole::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"accountRoleId",
				(BiConsumer<AccountRole, Long>)AccountRole::setAccountRoleId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<AccountRole, Long>)AccountRole::setCompanyId);
			attributeSetterBiConsumers.put(
				"accountEntryId",
				(BiConsumer<AccountRole, Long>)AccountRole::setAccountEntryId);
			attributeSetterBiConsumers.put(
				"roleId",
				(BiConsumer<AccountRole, Long>)AccountRole::setRoleId);

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

	@JSON
	@Override
	public long getAccountRoleId() {
		return _accountRoleId;
	}

	@Override
	public void setAccountRoleId(long accountRoleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountRoleId = accountRoleId;
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
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryId = accountEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAccountEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("accountEntryId"));
	}

	@JSON
	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleId = roleId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRoleId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("roleId"));
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
			getCompanyId(), AccountRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AccountRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AccountRole>
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
		AccountRoleImpl accountRoleImpl = new AccountRoleImpl();

		accountRoleImpl.setMvccVersion(getMvccVersion());
		accountRoleImpl.setExternalReferenceCode(getExternalReferenceCode());
		accountRoleImpl.setAccountRoleId(getAccountRoleId());
		accountRoleImpl.setCompanyId(getCompanyId());
		accountRoleImpl.setAccountEntryId(getAccountEntryId());
		accountRoleImpl.setRoleId(getRoleId());

		accountRoleImpl.resetOriginalValues();

		return accountRoleImpl;
	}

	@Override
	public AccountRole cloneWithOriginalValues() {
		AccountRoleImpl accountRoleImpl = new AccountRoleImpl();

		accountRoleImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		accountRoleImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		accountRoleImpl.setAccountRoleId(
			this.<Long>getColumnOriginalValue("accountRoleId"));
		accountRoleImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		accountRoleImpl.setAccountEntryId(
			this.<Long>getColumnOriginalValue("accountEntryId"));
		accountRoleImpl.setRoleId(this.<Long>getColumnOriginalValue("roleId"));

		return accountRoleImpl;
	}

	@Override
	public int compareTo(AccountRole accountRole) {
		long primaryKey = accountRole.getPrimaryKey();

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

		if (!(object instanceof AccountRole)) {
			return false;
		}

		AccountRole accountRole = (AccountRole)object;

		long primaryKey = accountRole.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AccountRole> toCacheModel() {
		AccountRoleCacheModel accountRoleCacheModel =
			new AccountRoleCacheModel();

		accountRoleCacheModel.mvccVersion = getMvccVersion();

		accountRoleCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			accountRoleCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			accountRoleCacheModel.externalReferenceCode = null;
		}

		accountRoleCacheModel.accountRoleId = getAccountRoleId();

		accountRoleCacheModel.companyId = getCompanyId();

		accountRoleCacheModel.accountEntryId = getAccountEntryId();

		accountRoleCacheModel.roleId = getRoleId();

		return accountRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AccountRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((AccountRole)this);

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

		private static final Function<InvocationHandler, AccountRole>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					AccountRole.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private long _accountRoleId;
	private long _companyId;
	private long _accountEntryId;
	private long _roleId;

	public <T> T getColumnValue(String columnName) {
		Function<AccountRole, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AccountRole)this);
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
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("accountRoleId", _accountRoleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("accountEntryId", _accountEntryId);
		_columnOriginalValues.put("roleId", _roleId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("externalReferenceCode", 2L);

		columnBitmasks.put("accountRoleId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("accountEntryId", 16L);

		columnBitmasks.put("roleId", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AccountRole _escapedModel;

}