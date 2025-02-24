/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.workflow.dto.v1_0;

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
@GraphQLName("WorkflowTaskTransitions")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "WorkflowTaskTransitions")
public class WorkflowTaskTransitions implements Serializable {

	public static WorkflowTaskTransitions toDTO(String json) {
		return ObjectMapperUtil.readValue(WorkflowTaskTransitions.class, json);
	}

	public static WorkflowTaskTransitions unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			WorkflowTaskTransitions.class, json);
	}

	@Schema
	@Valid
	public WorkflowTaskTransition[] getWorkflowTaskTransitions() {
		if (_workflowTaskTransitionsSupplier != null) {
			workflowTaskTransitions = _workflowTaskTransitionsSupplier.get();

			_workflowTaskTransitionsSupplier = null;
		}

		return workflowTaskTransitions;
	}

	public void setWorkflowTaskTransitions(
		WorkflowTaskTransition[] workflowTaskTransitions) {

		this.workflowTaskTransitions = workflowTaskTransitions;

		_workflowTaskTransitionsSupplier = null;
	}

	@JsonIgnore
	public void setWorkflowTaskTransitions(
		UnsafeSupplier<WorkflowTaskTransition[], Exception>
			workflowTaskTransitionsUnsafeSupplier) {

		_workflowTaskTransitionsSupplier = () -> {
			try {
				return workflowTaskTransitionsUnsafeSupplier.get();
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
	protected WorkflowTaskTransition[] workflowTaskTransitions;

	@JsonIgnore
	private Supplier<WorkflowTaskTransition[]> _workflowTaskTransitionsSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WorkflowTaskTransitions)) {
			return false;
		}

		WorkflowTaskTransitions workflowTaskTransitions =
			(WorkflowTaskTransitions)object;

		return Objects.equals(toString(), workflowTaskTransitions.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		WorkflowTaskTransition[] workflowTaskTransitions =
			getWorkflowTaskTransitions();

		if (workflowTaskTransitions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowTaskTransitions\": ");

			sb.append("[");

			for (int i = 0; i < workflowTaskTransitions.length; i++) {
				sb.append(String.valueOf(workflowTaskTransitions[i]));

				if ((i + 1) < workflowTaskTransitions.length) {
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
		defaultValue = "com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskTransitions",
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