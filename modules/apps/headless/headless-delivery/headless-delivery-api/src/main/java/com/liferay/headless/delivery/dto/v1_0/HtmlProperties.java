/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.dto.v1_0;

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
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "The page section's html properties", value = "HtmlProperties"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "HtmlProperties")
public class HtmlProperties implements Serializable {

	public static HtmlProperties toDTO(String json) {
		return ObjectMapperUtil.readValue(HtmlProperties.class, json);
	}

	public static HtmlProperties unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(HtmlProperties.class, json);
	}

	@JsonGetter("htmlTag")
	@Schema
	@Valid
	public HtmlTag getHtmlTag() {
		if (_htmlTagSupplier != null) {
			htmlTag = _htmlTagSupplier.get();

			_htmlTagSupplier = null;
		}

		return htmlTag;
	}

	@JsonIgnore
	public String getHtmlTagAsString() {
		HtmlTag htmlTag = getHtmlTag();

		if (htmlTag == null) {
			return null;
		}

		return htmlTag.toString();
	}

	public void setHtmlTag(HtmlTag htmlTag) {
		this.htmlTag = htmlTag;

		_htmlTagSupplier = null;
	}

	@JsonIgnore
	public void setHtmlTag(
		UnsafeSupplier<HtmlTag, Exception> htmlTagUnsafeSupplier) {

		_htmlTagSupplier = () -> {
			try {
				return htmlTagUnsafeSupplier.get();
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
	protected HtmlTag htmlTag;

	@JsonIgnore
	private Supplier<HtmlTag> _htmlTagSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HtmlProperties)) {
			return false;
		}

		HtmlProperties htmlProperties = (HtmlProperties)object;

		return Objects.equals(toString(), htmlProperties.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		HtmlTag htmlTag = getHtmlTag();

		if (htmlTag != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"htmlTag\": ");

			sb.append("\"");

			sb.append(htmlTag);

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.HtmlProperties",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("HtmlTag")
	public static enum HtmlTag {

		ARTICLE("Article"), ASIDE("Aside"), DIV("Div"), FOOTER("Footer"),
		HEADER("Header"), MAIN("Main"), NAV("Nav"), SECTION("Section");

		@JsonCreator
		public static HtmlTag create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (HtmlTag htmlTag : values()) {
				if (Objects.equals(htmlTag.getValue(), value)) {
					return htmlTag;
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

		private HtmlTag(String value) {
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