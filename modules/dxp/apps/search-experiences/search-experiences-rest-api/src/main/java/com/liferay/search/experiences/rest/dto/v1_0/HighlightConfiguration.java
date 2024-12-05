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
@GraphQLName("HighlightConfiguration")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "HighlightConfiguration")
public class HighlightConfiguration implements Serializable {

	public static HighlightConfiguration toDTO(String json) {
		return ObjectMapperUtil.readValue(HighlightConfiguration.class, json);
	}

	public static HighlightConfiguration unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			HighlightConfiguration.class, json);
	}

	@Schema
	@Valid
	public Map<String, HighlightField> getFields() {
		if (_fieldsSupplier != null) {
			fields = _fieldsSupplier.get();

			_fieldsSupplier = null;
		}

		return fields;
	}

	public void setFields(Map<String, HighlightField> fields) {
		this.fields = fields;

		_fieldsSupplier = null;
	}

	@JsonIgnore
	public void setFields(
		UnsafeSupplier<Map<String, HighlightField>, Exception>
			fieldsUnsafeSupplier) {

		_fieldsSupplier = () -> {
			try {
				return fieldsUnsafeSupplier.get();
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
	protected Map<String, HighlightField> fields;

	@JsonIgnore
	private Supplier<Map<String, HighlightField>> _fieldsSupplier;

	@Schema
	public Integer getFragment_size() {
		if (_fragment_sizeSupplier != null) {
			fragment_size = _fragment_sizeSupplier.get();

			_fragment_sizeSupplier = null;
		}

		return fragment_size;
	}

	public void setFragment_size(Integer fragment_size) {
		this.fragment_size = fragment_size;

		_fragment_sizeSupplier = null;
	}

	@JsonIgnore
	public void setFragment_size(
		UnsafeSupplier<Integer, Exception> fragment_sizeUnsafeSupplier) {

		_fragment_sizeSupplier = () -> {
			try {
				return fragment_sizeUnsafeSupplier.get();
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
	protected Integer fragment_size;

	@JsonIgnore
	private Supplier<Integer> _fragment_sizeSupplier;

	@Schema
	public Integer getNumber_of_fragments() {
		if (_number_of_fragmentsSupplier != null) {
			number_of_fragments = _number_of_fragmentsSupplier.get();

			_number_of_fragmentsSupplier = null;
		}

		return number_of_fragments;
	}

	public void setNumber_of_fragments(Integer number_of_fragments) {
		this.number_of_fragments = number_of_fragments;

		_number_of_fragmentsSupplier = null;
	}

	@JsonIgnore
	public void setNumber_of_fragments(
		UnsafeSupplier<Integer, Exception> number_of_fragmentsUnsafeSupplier) {

		_number_of_fragmentsSupplier = () -> {
			try {
				return number_of_fragmentsUnsafeSupplier.get();
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
	protected Integer number_of_fragments;

	@JsonIgnore
	private Supplier<Integer> _number_of_fragmentsSupplier;

	@Schema
	public String[] getPost_tags() {
		if (_post_tagsSupplier != null) {
			post_tags = _post_tagsSupplier.get();

			_post_tagsSupplier = null;
		}

		return post_tags;
	}

	public void setPost_tags(String[] post_tags) {
		this.post_tags = post_tags;

		_post_tagsSupplier = null;
	}

	@JsonIgnore
	public void setPost_tags(
		UnsafeSupplier<String[], Exception> post_tagsUnsafeSupplier) {

		_post_tagsSupplier = () -> {
			try {
				return post_tagsUnsafeSupplier.get();
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
	protected String[] post_tags;

	@JsonIgnore
	private Supplier<String[]> _post_tagsSupplier;

	@Schema
	public String[] getPre_tags() {
		if (_pre_tagsSupplier != null) {
			pre_tags = _pre_tagsSupplier.get();

			_pre_tagsSupplier = null;
		}

		return pre_tags;
	}

	public void setPre_tags(String[] pre_tags) {
		this.pre_tags = pre_tags;

		_pre_tagsSupplier = null;
	}

	@JsonIgnore
	public void setPre_tags(
		UnsafeSupplier<String[], Exception> pre_tagsUnsafeSupplier) {

		_pre_tagsSupplier = () -> {
			try {
				return pre_tagsUnsafeSupplier.get();
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
	protected String[] pre_tags;

	@JsonIgnore
	private Supplier<String[]> _pre_tagsSupplier;

	@Schema
	public Boolean getRequire_field_match() {
		if (_require_field_matchSupplier != null) {
			require_field_match = _require_field_matchSupplier.get();

			_require_field_matchSupplier = null;
		}

		return require_field_match;
	}

	public void setRequire_field_match(Boolean require_field_match) {
		this.require_field_match = require_field_match;

		_require_field_matchSupplier = null;
	}

	@JsonIgnore
	public void setRequire_field_match(
		UnsafeSupplier<Boolean, Exception> require_field_matchUnsafeSupplier) {

		_require_field_matchSupplier = () -> {
			try {
				return require_field_matchUnsafeSupplier.get();
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
	protected Boolean require_field_match;

	@JsonIgnore
	private Supplier<Boolean> _require_field_matchSupplier;

	@Schema
	public String getType() {
		if (_typeSupplier != null) {
			type = _typeSupplier.get();

			_typeSupplier = null;
		}

		return type;
	}

	public void setType(String type) {
		this.type = type;

		_typeSupplier = null;
	}

	@JsonIgnore
	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		_typeSupplier = () -> {
			try {
				return typeUnsafeSupplier.get();
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
	protected String type;

	@JsonIgnore
	private Supplier<String> _typeSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HighlightConfiguration)) {
			return false;
		}

		HighlightConfiguration highlightConfiguration =
			(HighlightConfiguration)object;

		return Objects.equals(toString(), highlightConfiguration.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Map<String, HighlightField> fields = getFields();

		if (fields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fields\": ");

			sb.append(_toJSON(fields));
		}

		Integer fragment_size = getFragment_size();

		if (fragment_size != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragment_size\": ");

			sb.append(fragment_size);
		}

		Integer number_of_fragments = getNumber_of_fragments();

		if (number_of_fragments != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"number_of_fragments\": ");

			sb.append(number_of_fragments);
		}

		String[] post_tags = getPost_tags();

		if (post_tags != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"post_tags\": ");

			sb.append("[");

			for (int i = 0; i < post_tags.length; i++) {
				sb.append("\"");

				sb.append(_escape(post_tags[i]));

				sb.append("\"");

				if ((i + 1) < post_tags.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		String[] pre_tags = getPre_tags();

		if (pre_tags != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pre_tags\": ");

			sb.append("[");

			for (int i = 0; i < pre_tags.length; i++) {
				sb.append("\"");

				sb.append(_escape(pre_tags[i]));

				sb.append("\"");

				if ((i + 1) < pre_tags.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		Boolean require_field_match = getRequire_field_match();

		if (require_field_match != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"require_field_match\": ");

			sb.append(require_field_match);
		}

		String type = getType();

		if (type != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(type));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.search.experiences.rest.dto.v1_0.HighlightConfiguration",
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