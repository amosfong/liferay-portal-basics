/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.journal.model.JournalContentSearch;
import com.liferay.journal.model.JournalContentSearchModel;
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
 * The base model implementation for the JournalContentSearch service. Represents a row in the &quot;JournalContentSearch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>JournalContentSearchModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalContentSearchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearchImpl
 * @generated
 */
public class JournalContentSearchModelImpl
	extends BaseModelImpl<JournalContentSearch>
	implements JournalContentSearchModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal content search model instance should use the <code>JournalContentSearch</code> interface instead.
	 */
	public static final String TABLE_NAME = "JournalContentSearch";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"contentSearchId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"privateLayout", Types.BOOLEAN},
		{"layoutId", Types.BIGINT}, {"portletId", Types.VARCHAR},
		{"articleId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contentSearchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("privateLayout", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("layoutId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("articleId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table JournalContentSearch (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,contentSearchId LONG not null,groupId LONG,companyId LONG,privateLayout BOOLEAN,layoutId LONG,portletId VARCHAR(200) null,articleId VARCHAR(75) null,primary key (contentSearchId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table JournalContentSearch";

	public static final String ORDER_BY_JPQL =
		" ORDER BY journalContentSearch.contentSearchId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY JournalContentSearch.contentSearchId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ARTICLEID_COLUMN_BITMASK = 1L;

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
	public static final long LAYOUTID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTLETID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIVATELAYOUT_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONTENTSEARCHID_COLUMN_BITMASK = 64L;

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

	public JournalContentSearchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contentSearchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContentSearchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentSearchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return JournalContentSearch.class;
	}

	@Override
	public String getModelClassName() {
		return JournalContentSearch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<JournalContentSearch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<JournalContentSearch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<JournalContentSearch, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((JournalContentSearch)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<JournalContentSearch, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<JournalContentSearch, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(JournalContentSearch)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<JournalContentSearch, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<JournalContentSearch, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<JournalContentSearch, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<JournalContentSearch, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<JournalContentSearch, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", JournalContentSearch::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", JournalContentSearch::getCtCollectionId);
			attributeGetterFunctions.put(
				"contentSearchId", JournalContentSearch::getContentSearchId);
			attributeGetterFunctions.put(
				"groupId", JournalContentSearch::getGroupId);
			attributeGetterFunctions.put(
				"companyId", JournalContentSearch::getCompanyId);
			attributeGetterFunctions.put(
				"privateLayout", JournalContentSearch::getPrivateLayout);
			attributeGetterFunctions.put(
				"layoutId", JournalContentSearch::getLayoutId);
			attributeGetterFunctions.put(
				"portletId", JournalContentSearch::getPortletId);
			attributeGetterFunctions.put(
				"articleId", JournalContentSearch::getArticleId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<JournalContentSearch, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<JournalContentSearch, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<JournalContentSearch, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"contentSearchId",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setContentSearchId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setCompanyId);
			attributeSetterBiConsumers.put(
				"privateLayout",
				(BiConsumer<JournalContentSearch, Boolean>)
					JournalContentSearch::setPrivateLayout);
			attributeSetterBiConsumers.put(
				"layoutId",
				(BiConsumer<JournalContentSearch, Long>)
					JournalContentSearch::setLayoutId);
			attributeSetterBiConsumers.put(
				"portletId",
				(BiConsumer<JournalContentSearch, String>)
					JournalContentSearch::setPortletId);
			attributeSetterBiConsumers.put(
				"articleId",
				(BiConsumer<JournalContentSearch, String>)
					JournalContentSearch::setArticleId);

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
	public long getContentSearchId() {
		return _contentSearchId;
	}

	@Override
	public void setContentSearchId(long contentSearchId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_contentSearchId = contentSearchId;
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
	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_privateLayout = privateLayout;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalPrivateLayout() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("privateLayout"));
	}

	@Override
	public long getLayoutId() {
		return _layoutId;
	}

	@Override
	public void setLayoutId(long layoutId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutId = layoutId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLayoutId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("layoutId"));
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

	@Override
	public String getArticleId() {
		if (_articleId == null) {
			return "";
		}
		else {
			return _articleId;
		}
	}

	@Override
	public void setArticleId(String articleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_articleId = articleId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalArticleId() {
		return getColumnOriginalValue("articleId");
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
			getCompanyId(), JournalContentSearch.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public JournalContentSearch toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, JournalContentSearch>
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
		JournalContentSearchImpl journalContentSearchImpl =
			new JournalContentSearchImpl();

		journalContentSearchImpl.setMvccVersion(getMvccVersion());
		journalContentSearchImpl.setCtCollectionId(getCtCollectionId());
		journalContentSearchImpl.setContentSearchId(getContentSearchId());
		journalContentSearchImpl.setGroupId(getGroupId());
		journalContentSearchImpl.setCompanyId(getCompanyId());
		journalContentSearchImpl.setPrivateLayout(isPrivateLayout());
		journalContentSearchImpl.setLayoutId(getLayoutId());
		journalContentSearchImpl.setPortletId(getPortletId());
		journalContentSearchImpl.setArticleId(getArticleId());

		journalContentSearchImpl.resetOriginalValues();

		return journalContentSearchImpl;
	}

	@Override
	public JournalContentSearch cloneWithOriginalValues() {
		JournalContentSearchImpl journalContentSearchImpl =
			new JournalContentSearchImpl();

		journalContentSearchImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		journalContentSearchImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		journalContentSearchImpl.setContentSearchId(
			this.<Long>getColumnOriginalValue("contentSearchId"));
		journalContentSearchImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		journalContentSearchImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		journalContentSearchImpl.setPrivateLayout(
			this.<Boolean>getColumnOriginalValue("privateLayout"));
		journalContentSearchImpl.setLayoutId(
			this.<Long>getColumnOriginalValue("layoutId"));
		journalContentSearchImpl.setPortletId(
			this.<String>getColumnOriginalValue("portletId"));
		journalContentSearchImpl.setArticleId(
			this.<String>getColumnOriginalValue("articleId"));

		return journalContentSearchImpl;
	}

	@Override
	public int compareTo(JournalContentSearch journalContentSearch) {
		long primaryKey = journalContentSearch.getPrimaryKey();

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

		if (!(object instanceof JournalContentSearch)) {
			return false;
		}

		JournalContentSearch journalContentSearch =
			(JournalContentSearch)object;

		long primaryKey = journalContentSearch.getPrimaryKey();

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
	public CacheModel<JournalContentSearch> toCacheModel() {
		JournalContentSearchCacheModel journalContentSearchCacheModel =
			new JournalContentSearchCacheModel();

		journalContentSearchCacheModel.mvccVersion = getMvccVersion();

		journalContentSearchCacheModel.ctCollectionId = getCtCollectionId();

		journalContentSearchCacheModel.contentSearchId = getContentSearchId();

		journalContentSearchCacheModel.groupId = getGroupId();

		journalContentSearchCacheModel.companyId = getCompanyId();

		journalContentSearchCacheModel.privateLayout = isPrivateLayout();

		journalContentSearchCacheModel.layoutId = getLayoutId();

		journalContentSearchCacheModel.portletId = getPortletId();

		String portletId = journalContentSearchCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			journalContentSearchCacheModel.portletId = null;
		}

		journalContentSearchCacheModel.articleId = getArticleId();

		String articleId = journalContentSearchCacheModel.articleId;

		if ((articleId != null) && (articleId.length() == 0)) {
			journalContentSearchCacheModel.articleId = null;
		}

		return journalContentSearchCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<JournalContentSearch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<JournalContentSearch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<JournalContentSearch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(JournalContentSearch)this);

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

		private static final Function<InvocationHandler, JournalContentSearch>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					JournalContentSearch.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _contentSearchId;
	private long _groupId;
	private long _companyId;
	private boolean _privateLayout;
	private long _layoutId;
	private String _portletId;
	private String _articleId;

	public <T> T getColumnValue(String columnName) {
		Function<JournalContentSearch, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((JournalContentSearch)this);
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
		_columnOriginalValues.put("contentSearchId", _contentSearchId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("privateLayout", _privateLayout);
		_columnOriginalValues.put("layoutId", _layoutId);
		_columnOriginalValues.put("portletId", _portletId);
		_columnOriginalValues.put("articleId", _articleId);
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

		columnBitmasks.put("contentSearchId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("privateLayout", 32L);

		columnBitmasks.put("layoutId", 64L);

		columnBitmasks.put("portletId", 128L);

		columnBitmasks.put("articleId", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private JournalContentSearch _escapedModel;

}