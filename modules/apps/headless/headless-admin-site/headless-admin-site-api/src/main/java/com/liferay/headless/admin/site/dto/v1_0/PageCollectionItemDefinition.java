/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "The definition of a Page Collection Item.",
	value = "PageCollectionItemDefinition"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PageCollectionItemDefinition")
public class PageCollectionItemDefinition implements Serializable {

	public static PageCollectionItemDefinition toDTO(String json) {
		return ObjectMapperUtil.readValue(
			PageCollectionItemDefinition.class, json);
	}

	public static PageCollectionItemDefinition unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			PageCollectionItemDefinition.class, json);
	}

	@Schema(description = "The page collection item's configuration.")
	@Valid
	public Object getCollectionItemConfig() {
		if (_collectionItemConfigSupplier != null) {
			collectionItemConfig = _collectionItemConfigSupplier.get();

			_collectionItemConfigSupplier = null;
		}

		return collectionItemConfig;
	}

	public void setCollectionItemConfig(Object collectionItemConfig) {
		this.collectionItemConfig = collectionItemConfig;

		_collectionItemConfigSupplier = null;
	}

	@JsonIgnore
	public void setCollectionItemConfig(
		UnsafeSupplier<Object, Exception> collectionItemConfigUnsafeSupplier) {

		_collectionItemConfigSupplier = () -> {
			try {
				return collectionItemConfigUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The page collection item's configuration.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Object collectionItemConfig;

	@JsonIgnore
	private Supplier<Object> _collectionItemConfigSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PageCollectionItemDefinition)) {
			return false;
		}

		PageCollectionItemDefinition pageCollectionItemDefinition =
			(PageCollectionItemDefinition)object;

		return Objects.equals(
			toString(), pageCollectionItemDefinition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Object collectionItemConfig = getCollectionItemConfig();

		if (collectionItemConfig != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"collectionItemConfig\": ");

			if (collectionItemConfig instanceof Map) {
				sb.append(
					JSONFactoryUtil.createJSONObject(
						(Map<?, ?>)collectionItemConfig));
			}
			else if (collectionItemConfig instanceof String) {
				sb.append("\"");
				sb.append(_escape((String)collectionItemConfig));
				sb.append("\"");
			}
			else {
				sb.append(collectionItemConfig);
			}
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.admin.site.dto.v1_0.PageCollectionItemDefinition",
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