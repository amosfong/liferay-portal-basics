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
	description = "Deprecated as of Athanasius (7.3.x), replaced by rowViewports",
	value = "RowViewportConfig"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "RowViewportConfig")
public class RowViewportConfig implements Serializable {

	public static RowViewportConfig toDTO(String json) {
		return ObjectMapperUtil.readValue(RowViewportConfig.class, json);
	}

	public static RowViewportConfig unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(RowViewportConfig.class, json);
	}

	@Schema
	@Valid
	public LandscapeMobile getLandscapeMobile() {
		if (_landscapeMobileSupplier != null) {
			landscapeMobile = _landscapeMobileSupplier.get();

			_landscapeMobileSupplier = null;
		}

		return landscapeMobile;
	}

	public void setLandscapeMobile(LandscapeMobile landscapeMobile) {
		this.landscapeMobile = landscapeMobile;

		_landscapeMobileSupplier = null;
	}

	@JsonIgnore
	public void setLandscapeMobile(
		UnsafeSupplier<LandscapeMobile, Exception>
			landscapeMobileUnsafeSupplier) {

		_landscapeMobileSupplier = () -> {
			try {
				return landscapeMobileUnsafeSupplier.get();
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
	protected LandscapeMobile landscapeMobile;

	@JsonIgnore
	private Supplier<LandscapeMobile> _landscapeMobileSupplier;

	@Schema
	@Valid
	public PortraitMobile getPortraitMobile() {
		if (_portraitMobileSupplier != null) {
			portraitMobile = _portraitMobileSupplier.get();

			_portraitMobileSupplier = null;
		}

		return portraitMobile;
	}

	public void setPortraitMobile(PortraitMobile portraitMobile) {
		this.portraitMobile = portraitMobile;

		_portraitMobileSupplier = null;
	}

	@JsonIgnore
	public void setPortraitMobile(
		UnsafeSupplier<PortraitMobile, Exception>
			portraitMobileUnsafeSupplier) {

		_portraitMobileSupplier = () -> {
			try {
				return portraitMobileUnsafeSupplier.get();
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
	protected PortraitMobile portraitMobile;

	@JsonIgnore
	private Supplier<PortraitMobile> _portraitMobileSupplier;

	@Schema
	@Valid
	public Tablet getTablet() {
		if (_tabletSupplier != null) {
			tablet = _tabletSupplier.get();

			_tabletSupplier = null;
		}

		return tablet;
	}

	public void setTablet(Tablet tablet) {
		this.tablet = tablet;

		_tabletSupplier = null;
	}

	@JsonIgnore
	public void setTablet(
		UnsafeSupplier<Tablet, Exception> tabletUnsafeSupplier) {

		_tabletSupplier = () -> {
			try {
				return tabletUnsafeSupplier.get();
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
	protected Tablet tablet;

	@JsonIgnore
	private Supplier<Tablet> _tabletSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RowViewportConfig)) {
			return false;
		}

		RowViewportConfig rowViewportConfig = (RowViewportConfig)object;

		return Objects.equals(toString(), rowViewportConfig.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		LandscapeMobile landscapeMobile = getLandscapeMobile();

		if (landscapeMobile != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"landscapeMobile\": ");

			sb.append(String.valueOf(landscapeMobile));
		}

		PortraitMobile portraitMobile = getPortraitMobile();

		if (portraitMobile != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"portraitMobile\": ");

			sb.append(String.valueOf(portraitMobile));
		}

		Tablet tablet = getTablet();

		if (tablet != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tablet\": ");

			sb.append(String.valueOf(tablet));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.delivery.dto.v1_0.RowViewportConfig",
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