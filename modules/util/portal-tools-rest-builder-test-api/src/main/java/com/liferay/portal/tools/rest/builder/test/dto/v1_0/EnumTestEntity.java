/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.rest.builder.test.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
 * @author Alejandro Tardín
 * @generated
 */
@Generated("")
@GraphQLName("EnumTestEntity")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "EnumTestEntity")
public class EnumTestEntity implements Serializable {

	public static EnumTestEntity toDTO(String json) {
		return ObjectMapperUtil.readValue(EnumTestEntity.class, json);
	}

	public static EnumTestEntity unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(EnumTestEntity.class, json);
	}

	@JsonGetter("testEnum")
	@Schema
	@Valid
	public TestEnum getTestEnum() {
		if (_testEnumSupplier != null) {
			testEnum = _testEnumSupplier.get();

			_testEnumSupplier = null;
		}

		return testEnum;
	}

	@JsonIgnore
	public String getTestEnumAsString() {
		TestEnum testEnum = getTestEnum();

		if (testEnum == null) {
			return null;
		}

		return testEnum.toString();
	}

	public void setTestEnum(TestEnum testEnum) {
		this.testEnum = testEnum;

		_testEnumSupplier = null;
	}

	@JsonIgnore
	public void setTestEnum(
		UnsafeSupplier<TestEnum, Exception> testEnumUnsafeSupplier) {

		_testEnumSupplier = () -> {
			try {
				return testEnumUnsafeSupplier.get();
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
	protected TestEnum testEnum;

	@JsonIgnore
	private Supplier<TestEnum> _testEnumSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EnumTestEntity)) {
			return false;
		}

		EnumTestEntity enumTestEntity = (EnumTestEntity)object;

		return Objects.equals(toString(), enumTestEntity.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		TestEnum testEnum = getTestEnum();

		if (testEnum != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"testEnum\": ");

			sb.append("\"");

			sb.append(testEnum);

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.portal.tools.rest.builder.test.dto.v1_0.EnumTestEntity",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("TestEnum")
	public static enum TestEnum {

		NEGATIVE_1EM("-1em"), NEGATIVE_0_POINT_95EM("-0.95em"),
		POSITIVE_1EM("1em"), POSITIVE_0_POINT_95EM("0.95em");

		@JsonCreator
		public static TestEnum create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (TestEnum testEnum : values()) {
				if (Objects.equals(testEnum.getValue(), value)) {
					return testEnum;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private TestEnum(String value) {
			_value = value;
		}

		private final String _value;

	}

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