/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
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
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationModel;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationVersion;

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
 * The base model implementation for the LVEntryLocalization service. Represents a row in the &quot;LVEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LVEntryLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LVEntryLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryLocalizationImpl
 * @generated
 */
public class LVEntryLocalizationModelImpl
	extends BaseModelImpl<LVEntryLocalization>
	implements LVEntryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lv entry localization model instance should use the <code>LVEntryLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "LVEntryLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"headId", Types.BIGINT},
		{"head", Types.BOOLEAN}, {"lvEntryLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"lvEntryId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"title", Types.VARCHAR},
		{"content", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lvEntryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lvEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LVEntryLocalization (mvccVersion LONG default 0 not null,headId LONG,head BOOLEAN,lvEntryLocalizationId LONG not null primary key,companyId LONG,lvEntryId LONG,languageId VARCHAR(75) null,title VARCHAR(75) null,content VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table LVEntryLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY lvEntryLocalization.lvEntryLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LVEntryLocalization.lvEntryLocalizationId ASC";

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
	public static final long HEAD_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long HEADID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LANGUAGEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LVENTRYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LVENTRYLOCALIZATIONID_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization"));

	public LVEntryLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lvEntryLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLvEntryLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lvEntryLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LVEntryLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return LVEntryLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LVEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LVEntryLocalization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntryLocalization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LVEntryLocalization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LVEntryLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LVEntryLocalization, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LVEntryLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LVEntryLocalization, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LVEntryLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<LVEntryLocalization, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<LVEntryLocalization, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<LVEntryLocalization, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", LVEntryLocalization::getMvccVersion);
			attributeGetterFunctions.put(
				"headId", LVEntryLocalization::getHeadId);
			attributeGetterFunctions.put(
				"lvEntryLocalizationId",
				LVEntryLocalization::getLvEntryLocalizationId);
			attributeGetterFunctions.put(
				"companyId", LVEntryLocalization::getCompanyId);
			attributeGetterFunctions.put(
				"lvEntryId", LVEntryLocalization::getLvEntryId);
			attributeGetterFunctions.put(
				"languageId", LVEntryLocalization::getLanguageId);
			attributeGetterFunctions.put(
				"title", LVEntryLocalization::getTitle);
			attributeGetterFunctions.put(
				"content", LVEntryLocalization::getContent);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<LVEntryLocalization, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<LVEntryLocalization, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<LVEntryLocalization, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<LVEntryLocalization, Long>)
					LVEntryLocalization::setMvccVersion);
			attributeSetterBiConsumers.put(
				"headId",
				(BiConsumer<LVEntryLocalization, Long>)
					LVEntryLocalization::setHeadId);
			attributeSetterBiConsumers.put(
				"lvEntryLocalizationId",
				(BiConsumer<LVEntryLocalization, Long>)
					LVEntryLocalization::setLvEntryLocalizationId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<LVEntryLocalization, Long>)
					LVEntryLocalization::setCompanyId);
			attributeSetterBiConsumers.put(
				"lvEntryId",
				(BiConsumer<LVEntryLocalization, Long>)
					LVEntryLocalization::setLvEntryId);
			attributeSetterBiConsumers.put(
				"languageId",
				(BiConsumer<LVEntryLocalization, String>)
					LVEntryLocalization::setLanguageId);
			attributeSetterBiConsumers.put(
				"title",
				(BiConsumer<LVEntryLocalization, String>)
					LVEntryLocalization::setTitle);
			attributeSetterBiConsumers.put(
				"content",
				(BiConsumer<LVEntryLocalization, String>)
					LVEntryLocalization::setContent);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public void populateVersionModel(
		LVEntryLocalizationVersion lvEntryLocalizationVersion) {

		lvEntryLocalizationVersion.setCompanyId(getCompanyId());
		lvEntryLocalizationVersion.setLvEntryId(getLvEntryId());
		lvEntryLocalizationVersion.setLanguageId(getLanguageId());
		lvEntryLocalizationVersion.setTitle(getTitle());
		lvEntryLocalizationVersion.setContent(getContent());
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
	public long getLvEntryLocalizationId() {
		return _lvEntryLocalizationId;
	}

	@Override
	public void setLvEntryLocalizationId(long lvEntryLocalizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryLocalizationId = lvEntryLocalizationId;
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
	public long getLvEntryId() {
		return _lvEntryId;
	}

	@Override
	public void setLvEntryId(long lvEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryId = lvEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLvEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("lvEntryId"));
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
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
			getCompanyId(), LVEntryLocalization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LVEntryLocalization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LVEntryLocalization>
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
		LVEntryLocalizationImpl lvEntryLocalizationImpl =
			new LVEntryLocalizationImpl();

		lvEntryLocalizationImpl.setMvccVersion(getMvccVersion());
		lvEntryLocalizationImpl.setHeadId(getHeadId());
		lvEntryLocalizationImpl.setLvEntryLocalizationId(
			getLvEntryLocalizationId());
		lvEntryLocalizationImpl.setCompanyId(getCompanyId());
		lvEntryLocalizationImpl.setLvEntryId(getLvEntryId());
		lvEntryLocalizationImpl.setLanguageId(getLanguageId());
		lvEntryLocalizationImpl.setTitle(getTitle());
		lvEntryLocalizationImpl.setContent(getContent());

		lvEntryLocalizationImpl.resetOriginalValues();

		return lvEntryLocalizationImpl;
	}

	@Override
	public LVEntryLocalization cloneWithOriginalValues() {
		LVEntryLocalizationImpl lvEntryLocalizationImpl =
			new LVEntryLocalizationImpl();

		lvEntryLocalizationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		lvEntryLocalizationImpl.setHeadId(
			this.<Long>getColumnOriginalValue("headId"));
		lvEntryLocalizationImpl.setLvEntryLocalizationId(
			this.<Long>getColumnOriginalValue("lvEntryLocalizationId"));
		lvEntryLocalizationImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		lvEntryLocalizationImpl.setLvEntryId(
			this.<Long>getColumnOriginalValue("lvEntryId"));
		lvEntryLocalizationImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		lvEntryLocalizationImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		lvEntryLocalizationImpl.setContent(
			this.<String>getColumnOriginalValue("content"));

		return lvEntryLocalizationImpl;
	}

	@Override
	public int compareTo(LVEntryLocalization lvEntryLocalization) {
		long primaryKey = lvEntryLocalization.getPrimaryKey();

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

		if (!(object instanceof LVEntryLocalization)) {
			return false;
		}

		LVEntryLocalization lvEntryLocalization = (LVEntryLocalization)object;

		long primaryKey = lvEntryLocalization.getPrimaryKey();

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
	public CacheModel<LVEntryLocalization> toCacheModel() {
		LVEntryLocalizationCacheModel lvEntryLocalizationCacheModel =
			new LVEntryLocalizationCacheModel();

		lvEntryLocalizationCacheModel.mvccVersion = getMvccVersion();

		lvEntryLocalizationCacheModel.headId = getHeadId();

		lvEntryLocalizationCacheModel.head = isHead();

		lvEntryLocalizationCacheModel.lvEntryLocalizationId =
			getLvEntryLocalizationId();

		lvEntryLocalizationCacheModel.companyId = getCompanyId();

		lvEntryLocalizationCacheModel.lvEntryId = getLvEntryId();

		lvEntryLocalizationCacheModel.languageId = getLanguageId();

		String languageId = lvEntryLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			lvEntryLocalizationCacheModel.languageId = null;
		}

		lvEntryLocalizationCacheModel.title = getTitle();

		String title = lvEntryLocalizationCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			lvEntryLocalizationCacheModel.title = null;
		}

		lvEntryLocalizationCacheModel.content = getContent();

		String content = lvEntryLocalizationCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			lvEntryLocalizationCacheModel.content = null;
		}

		return lvEntryLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LVEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LVEntryLocalization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntryLocalization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(LVEntryLocalization)this);

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

		private static final Function<InvocationHandler, LVEntryLocalization>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					LVEntryLocalization.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _headId;
	private boolean _head;
	private long _lvEntryLocalizationId;
	private long _companyId;
	private long _lvEntryId;
	private String _languageId;
	private String _title;
	private String _content;

	public <T> T getColumnValue(String columnName) {
		if (columnName.equals("head")) {
			return (T)(Object)getHead();
		}

		Function<LVEntryLocalization, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LVEntryLocalization)this);
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
		_columnOriginalValues.put("headId", _headId);
		_columnOriginalValues.put("head", _head);
		_columnOriginalValues.put(
			"lvEntryLocalizationId", _lvEntryLocalizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("lvEntryId", _lvEntryId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("headId", 2L);

		columnBitmasks.put("head", 4L);

		columnBitmasks.put("lvEntryLocalizationId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("lvEntryId", 32L);

		columnBitmasks.put("languageId", 64L);

		columnBitmasks.put("title", 128L);

		columnBitmasks.put("content", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LVEntryLocalization _escapedModel;

}