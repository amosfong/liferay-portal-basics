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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@Generated("")
@GraphQLName("Source")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Source")
public class Source implements Serializable {

	public static Source toDTO(String json) {
		return ObjectMapperUtil.readValue(Source.class, json);
	}

	public static Source unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Source.class, json);
	}

	@Schema
	public String[] getExcludes() {
		if (_excludesSupplier != null) {
			excludes = _excludesSupplier.get();

			_excludesSupplier = null;
		}

		return excludes;
	}

	public void setExcludes(String[] excludes) {
		this.excludes = excludes;

		_excludesSupplier = null;
	}

	@JsonIgnore
	public void setExcludes(
		UnsafeSupplier<String[], Exception> excludesUnsafeSupplier) {

		_excludesSupplier = () -> {
			try {
				return excludesUnsafeSupplier.get();
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
	protected String[] excludes;

	@JsonIgnore
	private Supplier<String[]> _excludesSupplier;

	@Schema
	public Boolean getFetchSource() {
		if (_fetchSourceSupplier != null) {
			fetchSource = _fetchSourceSupplier.get();

			_fetchSourceSupplier = null;
		}

		return fetchSource;
	}

	public void setFetchSource(Boolean fetchSource) {
		this.fetchSource = fetchSource;

		_fetchSourceSupplier = null;
	}

	@JsonIgnore
	public void setFetchSource(
		UnsafeSupplier<Boolean, Exception> fetchSourceUnsafeSupplier) {

		_fetchSourceSupplier = () -> {
			try {
				return fetchSourceUnsafeSupplier.get();
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
	protected Boolean fetchSource;

	@JsonIgnore
	private Supplier<Boolean> _fetchSourceSupplier;

	@Schema
	public String[] getIncludes() {
		if (_includesSupplier != null) {
			includes = _includesSupplier.get();

			_includesSupplier = null;
		}

		return includes;
	}

	public void setIncludes(String[] includes) {
		this.includes = includes;

		_includesSupplier = null;
	}

	@JsonIgnore
	public void setIncludes(
		UnsafeSupplier<String[], Exception> includesUnsafeSupplier) {

		_includesSupplier = () -> {
			try {
				return includesUnsafeSupplier.get();
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
	protected String[] includes;

	@JsonIgnore
	private Supplier<String[]> _includesSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Source)) {
			return false;
		}

		Source source = (Source)object;

		return Objects.equals(toString(), source.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String[] excludes = getExcludes();

		if (excludes != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"excludes\": ");

			sb.append("[");

			for (int i = 0; i < excludes.length; i++) {
				sb.append("\"");

				sb.append(_escape(excludes[i]));

				sb.append("\"");

				if ((i + 1) < excludes.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		Boolean fetchSource = getFetchSource();

		if (fetchSource != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fetchSource\": ");

			sb.append(fetchSource);
		}

		String[] includes = getIncludes();

		if (includes != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"includes\": ");

			sb.append("[");

			for (int i = 0; i < includes.length; i++) {
				sb.append("\"");

				sb.append(_escape(includes[i]));

				sb.append("\"");

				if ((i + 1) < includes.length) {
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
		defaultValue = "com.liferay.search.experiences.rest.dto.v1_0.Source",
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