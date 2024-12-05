/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.channel.dto.v1_0;

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
import javax.validation.constraints.DecimalMin;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
@GraphQLName("ShippingMethod")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ShippingMethod")
public class ShippingMethod implements Serializable {

	public static ShippingMethod toDTO(String json) {
		return ObjectMapperUtil.readValue(ShippingMethod.class, json);
	}

	public static ShippingMethod unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(ShippingMethod.class, json);
	}

	@Schema(example = "true")
	public Boolean getActive() {
		if (_activeSupplier != null) {
			active = _activeSupplier.get();

			_activeSupplier = null;
		}

		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;

		_activeSupplier = null;
	}

	@JsonIgnore
	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		_activeSupplier = () -> {
			try {
				return activeUnsafeSupplier.get();
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
	protected Boolean active;

	@JsonIgnore
	private Supplier<Boolean> _activeSupplier;

	@Schema(
		example = "{hu_HU=Product Description HU, hr_HR=Product Description HR, en_US=Professional hand stainless steel saw for wood. Made to last and saw forever. Made of best steel}"
	)
	@Valid
	public Map<String, String> getDescription() {
		if (_descriptionSupplier != null) {
			description = _descriptionSupplier.get();

			_descriptionSupplier = null;
		}

		return description;
	}

	public void setDescription(Map<String, String> description) {
		this.description = description;

		_descriptionSupplier = null;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<Map<String, String>, Exception>
			descriptionUnsafeSupplier) {

		_descriptionSupplier = () -> {
			try {
				return descriptionUnsafeSupplier.get();
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
	protected Map<String, String> description;

	@JsonIgnore
	private Supplier<Map<String, String>> _descriptionSupplier;

	@Schema(example = "DAB-34098-789-N")
	public String getEngineKey() {
		if (_engineKeySupplier != null) {
			engineKey = _engineKeySupplier.get();

			_engineKeySupplier = null;
		}

		return engineKey;
	}

	public void setEngineKey(String engineKey) {
		this.engineKey = engineKey;

		_engineKeySupplier = null;
	}

	@JsonIgnore
	public void setEngineKey(
		UnsafeSupplier<String, Exception> engineKeyUnsafeSupplier) {

		_engineKeySupplier = () -> {
			try {
				return engineKeyUnsafeSupplier.get();
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
	protected String engineKey;

	@JsonIgnore
	private Supplier<String> _engineKeySupplier;

	@DecimalMin("0")
	@Schema(example = "30324")
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

	@Schema(
		example = "{en_US=Professional hand stainless steel saw for wood. Made to last and saw forever. Made of best steel, hr_HR=Product Description HR, hu_HU=Product Description HU}"
	)
	@Valid
	public Map<String, String> getName() {
		if (_nameSupplier != null) {
			name = _nameSupplier.get();

			_nameSupplier = null;
		}

		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;

		_nameSupplier = null;
	}

	@JsonIgnore
	public void setName(
		UnsafeSupplier<Map<String, String>, Exception> nameUnsafeSupplier) {

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
	protected Map<String, String> name;

	@JsonIgnore
	private Supplier<Map<String, String>> _nameSupplier;

	@Schema(example = "1.2")
	public Double getPriority() {
		if (_prioritySupplier != null) {
			priority = _prioritySupplier.get();

			_prioritySupplier = null;
		}

		return priority;
	}

	public void setPriority(Double priority) {
		this.priority = priority;

		_prioritySupplier = null;
	}

	@JsonIgnore
	public void setPriority(
		UnsafeSupplier<Double, Exception> priorityUnsafeSupplier) {

		_prioritySupplier = () -> {
			try {
				return priorityUnsafeSupplier.get();
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
	protected Double priority;

	@JsonIgnore
	private Supplier<Double> _prioritySupplier;

	@Schema
	@Valid
	public ShippingOption[] getShippingOptions() {
		if (_shippingOptionsSupplier != null) {
			shippingOptions = _shippingOptionsSupplier.get();

			_shippingOptionsSupplier = null;
		}

		return shippingOptions;
	}

	public void setShippingOptions(ShippingOption[] shippingOptions) {
		this.shippingOptions = shippingOptions;

		_shippingOptionsSupplier = null;
	}

	@JsonIgnore
	public void setShippingOptions(
		UnsafeSupplier<ShippingOption[], Exception>
			shippingOptionsUnsafeSupplier) {

		_shippingOptionsSupplier = () -> {
			try {
				return shippingOptionsUnsafeSupplier.get();
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
	protected ShippingOption[] shippingOptions;

	@JsonIgnore
	private Supplier<ShippingOption[]> _shippingOptionsSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ShippingMethod)) {
			return false;
		}

		ShippingMethod shippingMethod = (ShippingMethod)object;

		return Objects.equals(toString(), shippingMethod.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Boolean active = getActive();

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
		}

		Map<String, String> description = getDescription();

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append(_toJSON(description));
		}

		String engineKey = getEngineKey();

		if (engineKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"engineKey\": ");

			sb.append("\"");

			sb.append(_escape(engineKey));

			sb.append("\"");
		}

		Long id = getId();

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		Map<String, String> name = getName();

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append(_toJSON(name));
		}

		Double priority = getPriority();

		if (priority != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(priority);
		}

		ShippingOption[] shippingOptions = getShippingOptions();

		if (shippingOptions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingOptions\": ");

			sb.append("[");

			for (int i = 0; i < shippingOptions.length; i++) {
				sb.append(String.valueOf(shippingOptions[i]));

				if ((i + 1) < shippingOptions.length) {
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
		defaultValue = "com.liferay.headless.commerce.admin.channel.dto.v1_0.ShippingMethod",
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