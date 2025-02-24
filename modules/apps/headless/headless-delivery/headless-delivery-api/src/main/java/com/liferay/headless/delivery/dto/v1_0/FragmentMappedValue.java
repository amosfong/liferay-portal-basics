/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.dto.v1_0;

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
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Represents a fragment mapped value.",
	value = "FragmentMappedValue"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "FragmentMappedValue")
public class FragmentMappedValue implements Serializable {

	public static FragmentMappedValue toDTO(String json) {
		return ObjectMapperUtil.readValue(FragmentMappedValue.class, json);
	}

	public static FragmentMappedValue unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			FragmentMappedValue.class, json);
	}

	@Schema(description = "The default value of the fragment mapped value.")
	@Valid
	public FragmentInlineValue getDefaultFragmentInlineValue() {
		if (_defaultFragmentInlineValueSupplier != null) {
			defaultFragmentInlineValue =
				_defaultFragmentInlineValueSupplier.get();

			_defaultFragmentInlineValueSupplier = null;
		}

		return defaultFragmentInlineValue;
	}

	public void setDefaultFragmentInlineValue(
		FragmentInlineValue defaultFragmentInlineValue) {

		this.defaultFragmentInlineValue = defaultFragmentInlineValue;

		_defaultFragmentInlineValueSupplier = null;
	}

	@JsonIgnore
	public void setDefaultFragmentInlineValue(
		UnsafeSupplier<FragmentInlineValue, Exception>
			defaultFragmentInlineValueUnsafeSupplier) {

		_defaultFragmentInlineValueSupplier = () -> {
			try {
				return defaultFragmentInlineValueUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(
		description = "The default value of the fragment mapped value."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected FragmentInlineValue defaultFragmentInlineValue;

	@JsonIgnore
	private Supplier<FragmentInlineValue> _defaultFragmentInlineValueSupplier;

	@Schema(
		deprecated = true,
		description = "Deprecated as of Athanasius (7.3.x), replaced by defaultFragmentInlineValue"
	)
	@Valid
	public DefaultValue getDefaultValue() {
		if (_defaultValueSupplier != null) {
			defaultValue = _defaultValueSupplier.get();

			_defaultValueSupplier = null;
		}

		return defaultValue;
	}

	public void setDefaultValue(DefaultValue defaultValue) {
		this.defaultValue = defaultValue;

		_defaultValueSupplier = null;
	}

	@JsonIgnore
	public void setDefaultValue(
		UnsafeSupplier<DefaultValue, Exception> defaultValueUnsafeSupplier) {

		_defaultValueSupplier = () -> {
			try {
				return defaultValueUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@Deprecated
	@GraphQLField(
		description = "Deprecated as of Athanasius (7.3.x), replaced by defaultFragmentInlineValue"
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DefaultValue defaultValue;

	@JsonIgnore
	private Supplier<DefaultValue> _defaultValueSupplier;

	@Schema(description = "The mapping of the fragment mapped value.")
	@Valid
	public Mapping getMapping() {
		if (_mappingSupplier != null) {
			mapping = _mappingSupplier.get();

			_mappingSupplier = null;
		}

		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;

		_mappingSupplier = null;
	}

	@JsonIgnore
	public void setMapping(
		UnsafeSupplier<Mapping, Exception> mappingUnsafeSupplier) {

		_mappingSupplier = () -> {
			try {
				return mappingUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The mapping of the fragment mapped value.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Mapping mapping;

	@JsonIgnore
	private Supplier<Mapping> _mappingSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FragmentMappedValue)) {
			return false;
		}

		FragmentMappedValue fragmentMappedValue = (FragmentMappedValue)object;

		return Objects.equals(toString(), fragmentMappedValue.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		FragmentInlineValue defaultFragmentInlineValue =
			getDefaultFragmentInlineValue();

		if (defaultFragmentInlineValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultFragmentInlineValue\": ");

			sb.append(String.valueOf(defaultFragmentInlineValue));
		}

		DefaultValue defaultValue = getDefaultValue();

		if (defaultValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultValue\": ");

			sb.append(String.valueOf(defaultValue));
		}

		Mapping mapping = getMapping();

		if (mapping != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mapping\": ");

			sb.append(String.valueOf(mapping));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.FragmentMappedValue",
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