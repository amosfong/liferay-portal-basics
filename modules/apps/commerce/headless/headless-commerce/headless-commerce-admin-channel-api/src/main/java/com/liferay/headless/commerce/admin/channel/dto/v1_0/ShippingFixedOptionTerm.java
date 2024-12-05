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
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
@GraphQLName("ShippingFixedOptionTerm")
@JsonFilter("Liferay.Vulcan")
@Schema(requiredProperties = {"shippingFixedOptionId", "termId"})
@XmlRootElement(name = "ShippingFixedOptionTerm")
public class ShippingFixedOptionTerm implements Serializable {

	public static ShippingFixedOptionTerm toDTO(String json) {
		return ObjectMapperUtil.readValue(ShippingFixedOptionTerm.class, json);
	}

	public static ShippingFixedOptionTerm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			ShippingFixedOptionTerm.class, json);
	}

	@Schema
	@Valid
	public Map<String, Map<String, String>> getActions() {
		if (_actionsSupplier != null) {
			actions = _actionsSupplier.get();

			_actionsSupplier = null;
		}

		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;

		_actionsSupplier = null;
	}

	@JsonIgnore
	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		_actionsSupplier = () -> {
			try {
				return actionsUnsafeSupplier.get();
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
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, Map<String, String>> actions;

	@JsonIgnore
	private Supplier<Map<String, Map<String, String>>> _actionsSupplier;

	@DecimalMin("0")
	@Schema(example = "30324")
	public Long getShippingFixedOptionId() {
		if (_shippingFixedOptionIdSupplier != null) {
			shippingFixedOptionId = _shippingFixedOptionIdSupplier.get();

			_shippingFixedOptionIdSupplier = null;
		}

		return shippingFixedOptionId;
	}

	public void setShippingFixedOptionId(Long shippingFixedOptionId) {
		this.shippingFixedOptionId = shippingFixedOptionId;

		_shippingFixedOptionIdSupplier = null;
	}

	@JsonIgnore
	public void setShippingFixedOptionId(
		UnsafeSupplier<Long, Exception> shippingFixedOptionIdUnsafeSupplier) {

		_shippingFixedOptionIdSupplier = () -> {
			try {
				return shippingFixedOptionIdUnsafeSupplier.get();
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
	@NotNull
	protected Long shippingFixedOptionId;

	@JsonIgnore
	private Supplier<Long> _shippingFixedOptionIdSupplier;

	@DecimalMin("0")
	@Schema(example = "30643")
	public Long getShippingFixedOptionTermId() {
		if (_shippingFixedOptionTermIdSupplier != null) {
			shippingFixedOptionTermId =
				_shippingFixedOptionTermIdSupplier.get();

			_shippingFixedOptionTermIdSupplier = null;
		}

		return shippingFixedOptionTermId;
	}

	public void setShippingFixedOptionTermId(Long shippingFixedOptionTermId) {
		this.shippingFixedOptionTermId = shippingFixedOptionTermId;

		_shippingFixedOptionTermIdSupplier = null;
	}

	@JsonIgnore
	public void setShippingFixedOptionTermId(
		UnsafeSupplier<Long, Exception>
			shippingFixedOptionTermIdUnsafeSupplier) {

		_shippingFixedOptionTermIdSupplier = () -> {
			try {
				return shippingFixedOptionTermIdUnsafeSupplier.get();
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
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long shippingFixedOptionTermId;

	@JsonIgnore
	private Supplier<Long> _shippingFixedOptionTermIdSupplier;

	@Schema
	@Valid
	public Term getTerm() {
		if (_termSupplier != null) {
			term = _termSupplier.get();

			_termSupplier = null;
		}

		return term;
	}

	public void setTerm(Term term) {
		this.term = term;

		_termSupplier = null;
	}

	@JsonIgnore
	public void setTerm(UnsafeSupplier<Term, Exception> termUnsafeSupplier) {
		_termSupplier = () -> {
			try {
				return termUnsafeSupplier.get();
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
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Term term;

	@JsonIgnore
	private Supplier<Term> _termSupplier;

	@Schema(example = "PAB-34098-789-N")
	public String getTermExternalReferenceCode() {
		if (_termExternalReferenceCodeSupplier != null) {
			termExternalReferenceCode =
				_termExternalReferenceCodeSupplier.get();

			_termExternalReferenceCodeSupplier = null;
		}

		return termExternalReferenceCode;
	}

	public void setTermExternalReferenceCode(String termExternalReferenceCode) {
		this.termExternalReferenceCode = termExternalReferenceCode;

		_termExternalReferenceCodeSupplier = null;
	}

	@JsonIgnore
	public void setTermExternalReferenceCode(
		UnsafeSupplier<String, Exception>
			termExternalReferenceCodeUnsafeSupplier) {

		_termExternalReferenceCodeSupplier = () -> {
			try {
				return termExternalReferenceCodeUnsafeSupplier.get();
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
	protected String termExternalReferenceCode;

	@JsonIgnore
	private Supplier<String> _termExternalReferenceCodeSupplier;

	@DecimalMin("0")
	@Schema(example = "30130")
	public Long getTermId() {
		if (_termIdSupplier != null) {
			termId = _termIdSupplier.get();

			_termIdSupplier = null;
		}

		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;

		_termIdSupplier = null;
	}

	@JsonIgnore
	public void setTermId(
		UnsafeSupplier<Long, Exception> termIdUnsafeSupplier) {

		_termIdSupplier = () -> {
			try {
				return termIdUnsafeSupplier.get();
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
	@NotNull
	protected Long termId;

	@JsonIgnore
	private Supplier<Long> _termIdSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ShippingFixedOptionTerm)) {
			return false;
		}

		ShippingFixedOptionTerm shippingFixedOptionTerm =
			(ShippingFixedOptionTerm)object;

		return Objects.equals(toString(), shippingFixedOptionTerm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Map<String, Map<String, String>> actions = getActions();

		if (actions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(actions));
		}

		Long shippingFixedOptionId = getShippingFixedOptionId();

		if (shippingFixedOptionId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingFixedOptionId\": ");

			sb.append(shippingFixedOptionId);
		}

		Long shippingFixedOptionTermId = getShippingFixedOptionTermId();

		if (shippingFixedOptionTermId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"shippingFixedOptionTermId\": ");

			sb.append(shippingFixedOptionTermId);
		}

		Term term = getTerm();

		if (term != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"term\": ");

			sb.append(String.valueOf(term));
		}

		String termExternalReferenceCode = getTermExternalReferenceCode();

		if (termExternalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termExternalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(termExternalReferenceCode));

			sb.append("\"");
		}

		Long termId = getTermId();

		if (termId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"termId\": ");

			sb.append(termId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.commerce.admin.channel.dto.v1_0.ShippingFixedOptionTerm",
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