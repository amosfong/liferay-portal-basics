/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.notification.model.NotificationQueueEntryAttachment;
import com.liferay.notification.model.NotificationQueueEntryAttachmentModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
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
 * The base model implementation for the NotificationQueueEntryAttachment service. Represents a row in the &quot;NQueueEntryAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NotificationQueueEntryAttachmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NotificationQueueEntryAttachmentImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationQueueEntryAttachmentImpl
 * @generated
 */
public class NotificationQueueEntryAttachmentModelImpl
	extends BaseModelImpl<NotificationQueueEntryAttachment>
	implements NotificationQueueEntryAttachmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notification queue entry attachment model instance should use the <code>NotificationQueueEntryAttachment</code> interface instead.
	 */
	public static final String TABLE_NAME = "NQueueEntryAttachment";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"NQueueEntryAttachmentId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT},
		{"notificationQueueEntryId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("NQueueEntryAttachmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notificationQueueEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table NQueueEntryAttachment (mvccVersion LONG default 0 not null,NQueueEntryAttachmentId LONG not null primary key,companyId LONG,fileEntryId LONG,notificationQueueEntryId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table NQueueEntryAttachment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY notificationQueueEntryAttachment.notificationQueueEntryAttachmentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY NQueueEntryAttachment.NQueueEntryAttachmentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NOTIFICATIONQUEUEENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NOTIFICATIONQUEUEENTRYATTACHMENTID_COLUMN_BITMASK =
		2L;

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

	public NotificationQueueEntryAttachmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _notificationQueueEntryAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNotificationQueueEntryAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notificationQueueEntryAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NotificationQueueEntryAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return NotificationQueueEntryAttachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NotificationQueueEntryAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<NotificationQueueEntryAttachment, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotificationQueueEntryAttachment, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(NotificationQueueEntryAttachment)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NotificationQueueEntryAttachment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NotificationQueueEntryAttachment, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NotificationQueueEntryAttachment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NotificationQueueEntryAttachment, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NotificationQueueEntryAttachment, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<NotificationQueueEntryAttachment, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<NotificationQueueEntryAttachment, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<NotificationQueueEntryAttachment, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion",
				NotificationQueueEntryAttachment::getMvccVersion);
			attributeGetterFunctions.put(
				"notificationQueueEntryAttachmentId",
				NotificationQueueEntryAttachment::
					getNotificationQueueEntryAttachmentId);
			attributeGetterFunctions.put(
				"companyId", NotificationQueueEntryAttachment::getCompanyId);
			attributeGetterFunctions.put(
				"fileEntryId",
				NotificationQueueEntryAttachment::getFileEntryId);
			attributeGetterFunctions.put(
				"notificationQueueEntryId",
				NotificationQueueEntryAttachment::getNotificationQueueEntryId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<NotificationQueueEntryAttachment, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<NotificationQueueEntryAttachment, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String,
						 BiConsumer<NotificationQueueEntryAttachment, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<NotificationQueueEntryAttachment, Long>)
					NotificationQueueEntryAttachment::setMvccVersion);
			attributeSetterBiConsumers.put(
				"notificationQueueEntryAttachmentId",
				(BiConsumer<NotificationQueueEntryAttachment, Long>)
					NotificationQueueEntryAttachment::
						setNotificationQueueEntryAttachmentId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<NotificationQueueEntryAttachment, Long>)
					NotificationQueueEntryAttachment::setCompanyId);
			attributeSetterBiConsumers.put(
				"fileEntryId",
				(BiConsumer<NotificationQueueEntryAttachment, Long>)
					NotificationQueueEntryAttachment::setFileEntryId);
			attributeSetterBiConsumers.put(
				"notificationQueueEntryId",
				(BiConsumer<NotificationQueueEntryAttachment, Long>)
					NotificationQueueEntryAttachment::
						setNotificationQueueEntryId);

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
	public long getNotificationQueueEntryAttachmentId() {
		return _notificationQueueEntryAttachmentId;
	}

	@Override
	public void setNotificationQueueEntryAttachmentId(
		long notificationQueueEntryAttachmentId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationQueueEntryAttachmentId =
			notificationQueueEntryAttachmentId;
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
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileEntryId = fileEntryId;
	}

	@Override
	public long getNotificationQueueEntryId() {
		return _notificationQueueEntryId;
	}

	@Override
	public void setNotificationQueueEntryId(long notificationQueueEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationQueueEntryId = notificationQueueEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalNotificationQueueEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("notificationQueueEntryId"));
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
			getCompanyId(), NotificationQueueEntryAttachment.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NotificationQueueEntryAttachment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NotificationQueueEntryAttachment>
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
		NotificationQueueEntryAttachmentImpl
			notificationQueueEntryAttachmentImpl =
				new NotificationQueueEntryAttachmentImpl();

		notificationQueueEntryAttachmentImpl.setMvccVersion(getMvccVersion());
		notificationQueueEntryAttachmentImpl.
			setNotificationQueueEntryAttachmentId(
				getNotificationQueueEntryAttachmentId());
		notificationQueueEntryAttachmentImpl.setCompanyId(getCompanyId());
		notificationQueueEntryAttachmentImpl.setFileEntryId(getFileEntryId());
		notificationQueueEntryAttachmentImpl.setNotificationQueueEntryId(
			getNotificationQueueEntryId());

		notificationQueueEntryAttachmentImpl.resetOriginalValues();

		return notificationQueueEntryAttachmentImpl;
	}

	@Override
	public NotificationQueueEntryAttachment cloneWithOriginalValues() {
		NotificationQueueEntryAttachmentImpl
			notificationQueueEntryAttachmentImpl =
				new NotificationQueueEntryAttachmentImpl();

		notificationQueueEntryAttachmentImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		notificationQueueEntryAttachmentImpl.
			setNotificationQueueEntryAttachmentId(
				this.<Long>getColumnOriginalValue("NQueueEntryAttachmentId"));
		notificationQueueEntryAttachmentImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		notificationQueueEntryAttachmentImpl.setFileEntryId(
			this.<Long>getColumnOriginalValue("fileEntryId"));
		notificationQueueEntryAttachmentImpl.setNotificationQueueEntryId(
			this.<Long>getColumnOriginalValue("notificationQueueEntryId"));

		return notificationQueueEntryAttachmentImpl;
	}

	@Override
	public int compareTo(
		NotificationQueueEntryAttachment notificationQueueEntryAttachment) {

		long primaryKey = notificationQueueEntryAttachment.getPrimaryKey();

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

		if (!(object instanceof NotificationQueueEntryAttachment)) {
			return false;
		}

		NotificationQueueEntryAttachment notificationQueueEntryAttachment =
			(NotificationQueueEntryAttachment)object;

		long primaryKey = notificationQueueEntryAttachment.getPrimaryKey();

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
	public CacheModel<NotificationQueueEntryAttachment> toCacheModel() {
		NotificationQueueEntryAttachmentCacheModel
			notificationQueueEntryAttachmentCacheModel =
				new NotificationQueueEntryAttachmentCacheModel();

		notificationQueueEntryAttachmentCacheModel.mvccVersion =
			getMvccVersion();

		notificationQueueEntryAttachmentCacheModel.
			notificationQueueEntryAttachmentId =
				getNotificationQueueEntryAttachmentId();

		notificationQueueEntryAttachmentCacheModel.companyId = getCompanyId();

		notificationQueueEntryAttachmentCacheModel.fileEntryId =
			getFileEntryId();

		notificationQueueEntryAttachmentCacheModel.notificationQueueEntryId =
			getNotificationQueueEntryId();

		return notificationQueueEntryAttachmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NotificationQueueEntryAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<NotificationQueueEntryAttachment, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NotificationQueueEntryAttachment, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(NotificationQueueEntryAttachment)this);

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
			<InvocationHandler, NotificationQueueEntryAttachment>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						NotificationQueueEntryAttachment.class,
						ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _notificationQueueEntryAttachmentId;
	private long _companyId;
	private long _fileEntryId;
	private long _notificationQueueEntryId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<NotificationQueueEntryAttachment, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NotificationQueueEntryAttachment)this);
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
			"NQueueEntryAttachmentId", _notificationQueueEntryAttachmentId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put(
			"notificationQueueEntryId", _notificationQueueEntryId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"NQueueEntryAttachmentId", "notificationQueueEntryAttachmentId");

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

		columnBitmasks.put("NQueueEntryAttachmentId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("fileEntryId", 8L);

		columnBitmasks.put("notificationQueueEntryId", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NotificationQueueEntryAttachment _escapedModel;

}