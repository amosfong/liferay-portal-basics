/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.dto.v2_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
@GraphQLName("DataListView")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "DataListView")
public class DataListView implements Serializable {

	public static DataListView toDTO(String json) {
		return ObjectMapperUtil.readValue(DataListView.class, json);
	}

	public static DataListView unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(DataListView.class, json);
	}

	@Schema
	@Valid
	public Map<String, Object> getAppliedFilters() {
		if (_appliedFiltersSupplier != null) {
			appliedFilters = _appliedFiltersSupplier.get();

			_appliedFiltersSupplier = null;
		}

		return appliedFilters;
	}

	public void setAppliedFilters(Map<String, Object> appliedFilters) {
		this.appliedFilters = appliedFilters;

		_appliedFiltersSupplier = null;
	}

	@JsonIgnore
	public void setAppliedFilters(
		UnsafeSupplier<Map<String, Object>, Exception>
			appliedFiltersUnsafeSupplier) {

		_appliedFiltersSupplier = () -> {
			try {
				return appliedFiltersUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> appliedFilters;

	@JsonIgnore
	private Supplier<Map<String, Object>> _appliedFiltersSupplier;

	@Schema
	public Long getDataDefinitionId() {
		if (_dataDefinitionIdSupplier != null) {
			dataDefinitionId = _dataDefinitionIdSupplier.get();

			_dataDefinitionIdSupplier = null;
		}

		return dataDefinitionId;
	}

	public void setDataDefinitionId(Long dataDefinitionId) {
		this.dataDefinitionId = dataDefinitionId;

		_dataDefinitionIdSupplier = null;
	}

	@JsonIgnore
	public void setDataDefinitionId(
		UnsafeSupplier<Long, Exception> dataDefinitionIdUnsafeSupplier) {

		_dataDefinitionIdSupplier = () -> {
			try {
				return dataDefinitionIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long dataDefinitionId;

	@JsonIgnore
	private Supplier<Long> _dataDefinitionIdSupplier;

	@Schema
	public Date getDateCreated() {
		if (_dateCreatedSupplier != null) {
			dateCreated = _dateCreatedSupplier.get();

			_dateCreatedSupplier = null;
		}

		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;

		_dateCreatedSupplier = null;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		_dateCreatedSupplier = () -> {
			try {
				return dateCreatedUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date dateCreated;

	@JsonIgnore
	private Supplier<Date> _dateCreatedSupplier;

	@Schema
	public Date getDateModified() {
		if (_dateModifiedSupplier != null) {
			dateModified = _dateModifiedSupplier.get();

			_dateModifiedSupplier = null;
		}

		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;

		_dateModifiedSupplier = null;
	}

	@JsonIgnore
	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		_dateModifiedSupplier = () -> {
			try {
				return dateModifiedUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date dateModified;

	@JsonIgnore
	private Supplier<Date> _dateModifiedSupplier;

	@Schema
	public String[] getFieldNames() {
		if (_fieldNamesSupplier != null) {
			fieldNames = _fieldNamesSupplier.get();

			_fieldNamesSupplier = null;
		}

		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;

		_fieldNamesSupplier = null;
	}

	@JsonIgnore
	public void setFieldNames(
		UnsafeSupplier<String[], Exception> fieldNamesUnsafeSupplier) {

		_fieldNamesSupplier = () -> {
			try {
				return fieldNamesUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String[] fieldNames;

	@JsonIgnore
	private Supplier<String[]> _fieldNamesSupplier;

	@Schema
	public Long getId() {
		if (_idSupplier != null) {
			id = _idSupplier.get();

			_idSupplier = null;
		}

		return id;
	}

	public void setId(Long id) {
		this.id = id;

		_idSupplier = null;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		_idSupplier = () -> {
			try {
				return idUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@JsonIgnore
	private Supplier<Long> _idSupplier;

	@Schema
	@Valid
	public Map<String, Object> getName() {
		if (_nameSupplier != null) {
			name = _nameSupplier.get();

			_nameSupplier = null;
		}

		return name;
	}

	public void setName(Map<String, Object> name) {
		this.name = name;

		_nameSupplier = null;
	}

	@JsonIgnore
	public void setName(
		UnsafeSupplier<Map<String, Object>, Exception> nameUnsafeSupplier) {

		_nameSupplier = () -> {
			try {
				return nameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> name;

	@JsonIgnore
	private Supplier<Map<String, Object>> _nameSupplier;

	@Schema
	public Long getSiteId() {
		if (_siteIdSupplier != null) {
			siteId = _siteIdSupplier.get();

			_siteIdSupplier = null;
		}

		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;

		_siteIdSupplier = null;
	}

	@JsonIgnore
	public void setSiteId(
		UnsafeSupplier<Long, Exception> siteIdUnsafeSupplier) {

		_siteIdSupplier = () -> {
			try {
				return siteIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long siteId;

	@JsonIgnore
	private Supplier<Long> _siteIdSupplier;

	@Schema
	public String getSortField() {
		if (_sortFieldSupplier != null) {
			sortField = _sortFieldSupplier.get();

			_sortFieldSupplier = null;
		}

		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;

		_sortFieldSupplier = null;
	}

	@JsonIgnore
	public void setSortField(
		UnsafeSupplier<String, Exception> sortFieldUnsafeSupplier) {

		_sortFieldSupplier = () -> {
			try {
				return sortFieldUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String sortField;

	@JsonIgnore
	private Supplier<String> _sortFieldSupplier;

	@Schema
	public Long getUserId() {
		if (_userIdSupplier != null) {
			userId = _userIdSupplier.get();

			_userIdSupplier = null;
		}

		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;

		_userIdSupplier = null;
	}

	@JsonIgnore
	public void setUserId(
		UnsafeSupplier<Long, Exception> userIdUnsafeSupplier) {

		_userIdSupplier = () -> {
			try {
				return userIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long userId;

	@JsonIgnore
	private Supplier<Long> _userIdSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataListView)) {
			return false;
		}

		DataListView dataListView = (DataListView)object;

		return Objects.equals(toString(), dataListView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		Map<String, Object> appliedFilters = getAppliedFilters();

		if (appliedFilters != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"appliedFilters\": ");

			sb.append(_toJSON(appliedFilters));
		}

		Long dataDefinitionId = getDataDefinitionId();

		if (dataDefinitionId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dataDefinitionId\": ");

			sb.append(dataDefinitionId);
		}

		Date dateCreated = getDateCreated();

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		Date dateModified = getDateModified();

		if (dateModified != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateModified));

			sb.append("\"");
		}

		String[] fieldNames = getFieldNames();

		if (fieldNames != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldNames\": ");

			sb.append("[");

			for (int i = 0; i < fieldNames.length; i++) {
				sb.append("\"");

				sb.append(_escape(fieldNames[i]));

				sb.append("\"");

				if ((i + 1) < fieldNames.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		Long id = getId();

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		Map<String, Object> name = getName();

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append(_toJSON(name));
		}

		Long siteId = getSiteId();

		if (siteId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(siteId);
		}

		String sortField = getSortField();

		if (sortField != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sortField\": ");

			sb.append("\"");

			sb.append(_escape(sortField));

			sb.append("\"");
		}

		Long userId = getUserId();

		if (userId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(userId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.data.engine.rest.dto.v2_0.DataListView",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof Map) {
						sb.append(_toJSON((Map<String, ?>)valueArray[i]));
					}
					else if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}