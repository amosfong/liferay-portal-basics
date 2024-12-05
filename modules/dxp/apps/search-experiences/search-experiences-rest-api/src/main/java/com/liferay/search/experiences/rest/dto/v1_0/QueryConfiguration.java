/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.rest.dto.v1_0;

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

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
@GraphQLName("QueryConfiguration")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "QueryConfiguration")
public class QueryConfiguration implements Serializable {

	public static QueryConfiguration toDTO(String json) {
		return ObjectMapperUtil.readValue(QueryConfiguration.class, json);
	}

	public static QueryConfiguration unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(QueryConfiguration.class, json);
	}

	@Schema
	public Boolean getApplyIndexerClauses() {
		if (_applyIndexerClausesSupplier != null) {
			applyIndexerClauses = _applyIndexerClausesSupplier.get();

			_applyIndexerClausesSupplier = null;
		}

		return applyIndexerClauses;
	}

	public void setApplyIndexerClauses(Boolean applyIndexerClauses) {
		this.applyIndexerClauses = applyIndexerClauses;

		_applyIndexerClausesSupplier = null;
	}

	@JsonIgnore
	public void setApplyIndexerClauses(
		UnsafeSupplier<Boolean, Exception> applyIndexerClausesUnsafeSupplier) {

		_applyIndexerClausesSupplier = () -> {
			try {
				return applyIndexerClausesUnsafeSupplier.get();
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
	protected Boolean applyIndexerClauses;

	@JsonIgnore
	private Supplier<Boolean> _applyIndexerClausesSupplier;

	@Schema
	@Valid
	public QueryEntry[] getQueryEntries() {
		if (_queryEntriesSupplier != null) {
			queryEntries = _queryEntriesSupplier.get();

			_queryEntriesSupplier = null;
		}

		return queryEntries;
	}

	public void setQueryEntries(QueryEntry[] queryEntries) {
		this.queryEntries = queryEntries;

		_queryEntriesSupplier = null;
	}

	@JsonIgnore
	public void setQueryEntries(
		UnsafeSupplier<QueryEntry[], Exception> queryEntriesUnsafeSupplier) {

		_queryEntriesSupplier = () -> {
			try {
				return queryEntriesUnsafeSupplier.get();
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
	protected QueryEntry[] queryEntries;

	@JsonIgnore
	private Supplier<QueryEntry[]> _queryEntriesSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof QueryConfiguration)) {
			return false;
		}

		QueryConfiguration queryConfiguration = (QueryConfiguration)object;

		return Objects.equals(toString(), queryConfiguration.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Boolean applyIndexerClauses = getApplyIndexerClauses();

		if (applyIndexerClauses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"applyIndexerClauses\": ");

			sb.append(applyIndexerClauses);
		}

		QueryEntry[] queryEntries = getQueryEntries();

		if (queryEntries != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"queryEntries\": ");

			sb.append("[");

			for (int i = 0; i < queryEntries.length; i++) {
				sb.append(String.valueOf(queryEntries[i]));

				if ((i + 1) < queryEntries.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.search.experiences.rest.dto.v1_0.QueryConfiguration",
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