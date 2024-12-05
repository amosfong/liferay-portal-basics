/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.scim.rest.dto.v1_0;

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
 * @author Olivér Kecskeméty
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Each SCIM resource (Users, Groups, etc.) includes the following common attributes.",
	value = "BaseScim"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "BaseScim")
public class BaseScim implements Serializable {

	public static BaseScim toDTO(String json) {
		return ObjectMapperUtil.readValue(BaseScim.class, json);
	}

	public static BaseScim unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(BaseScim.class, json);
	}

	@Schema(
		description = "A String that is an identifier for the resource as defined by the provisioning client."
	)
	public String getExternalId() {
		if (_externalIdSupplier != null) {
			externalId = _externalIdSupplier.get();

			_externalIdSupplier = null;
		}

		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;

		_externalIdSupplier = null;
	}

	@JsonIgnore
	public void setExternalId(
		UnsafeSupplier<String, Exception> externalIdUnsafeSupplier) {

		_externalIdSupplier = () -> {
			try {
				return externalIdUnsafeSupplier.get();
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
		description = "A String that is an identifier for the resource as defined by the provisioning client."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String externalId;

	@JsonIgnore
	private Supplier<String> _externalIdSupplier;

	@Schema(
		description = "A unique identifier for a SCIM resource as defined by the service provider."
	)
	public String getId() {
		if (_idSupplier != null) {
			id = _idSupplier.get();

			_idSupplier = null;
		}

		return id;
	}

	public void setId(String id) {
		this.id = id;

		_idSupplier = null;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<String, Exception> idUnsafeSupplier) {
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

	@GraphQLField(
		description = "A unique identifier for a SCIM resource as defined by the service provider."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String id;

	@JsonIgnore
	private Supplier<String> _idSupplier;

	@Schema
	@Valid
	public Meta getMeta() {
		if (_metaSupplier != null) {
			meta = _metaSupplier.get();

			_metaSupplier = null;
		}

		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;

		_metaSupplier = null;
	}

	@JsonIgnore
	public void setMeta(UnsafeSupplier<Meta, Exception> metaUnsafeSupplier) {
		_metaSupplier = () -> {
			try {
				return metaUnsafeSupplier.get();
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
	protected Meta meta;

	@JsonIgnore
	private Supplier<Meta> _metaSupplier;

	@Schema(
		description = "A multi-valued list of strings indicating the namespaces of the SCIM schemas that define the attributes present in the current JSON structure."
	)
	public String[] getSchemas() {
		if (_schemasSupplier != null) {
			schemas = _schemasSupplier.get();

			_schemasSupplier = null;
		}

		return schemas;
	}

	public void setSchemas(String[] schemas) {
		this.schemas = schemas;

		_schemasSupplier = null;
	}

	@JsonIgnore
	public void setSchemas(
		UnsafeSupplier<String[], Exception> schemasUnsafeSupplier) {

		_schemasSupplier = () -> {
			try {
				return schemasUnsafeSupplier.get();
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
		description = "A multi-valued list of strings indicating the namespaces of the SCIM schemas that define the attributes present in the current JSON structure."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String[] schemas;

	@JsonIgnore
	private Supplier<String[]> _schemasSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BaseScim)) {
			return false;
		}

		BaseScim baseScim = (BaseScim)object;

		return Objects.equals(toString(), baseScim.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String externalId = getExternalId();

		if (externalId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalId\": ");

			sb.append("\"");

			sb.append(_escape(externalId));

			sb.append("\"");
		}

		String id = getId();

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append("\"");

			sb.append(_escape(id));

			sb.append("\"");
		}

		Meta meta = getMeta();

		if (meta != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"meta\": ");

			sb.append(String.valueOf(meta));
		}

		String[] schemas = getSchemas();

		if (schemas != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"schemas\": ");

			sb.append("[");

			for (int i = 0; i < schemas.length; i++) {
				sb.append("\"");

				sb.append(_escape(schemas[i]));

				sb.append("\"");

				if ((i + 1) < schemas.length) {
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
		defaultValue = "com.liferay.scim.rest.dto.v1_0.BaseScim",
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