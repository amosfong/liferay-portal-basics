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
@GraphQLName("WorkflowTaskTransition")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "WorkflowTaskTransition")
public class WorkflowTaskTransition implements Serializable {

	public static WorkflowTaskTransition toDTO(String json) {
		return ObjectMapperUtil.readValue(WorkflowTaskTransition.class, json);
	}

	public static WorkflowTaskTransition unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			WorkflowTaskTransition.class, json);
	}

	@Schema
	@Valid
	public Transition[] getTransitions() {
		if (_transitionsSupplier != null) {
			transitions = _transitionsSupplier.get();

			_transitionsSupplier = null;
		}

		return transitions;
	}

	public void setTransitions(Transition[] transitions) {
		this.transitions = transitions;

		_transitionsSupplier = null;
	}

	@JsonIgnore
	public void setTransitions(
		UnsafeSupplier<Transition[], Exception> transitionsUnsafeSupplier) {

		_transitionsSupplier = () -> {
			try {
				return transitionsUnsafeSupplier.get();
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
	protected Transition[] transitions;

	@JsonIgnore
	private Supplier<Transition[]> _transitionsSupplier;

	@Schema
	public String getWorkflowDefinitionVersion() {
		if (_workflowDefinitionVersionSupplier != null) {
			workflowDefinitionVersion =
				_workflowDefinitionVersionSupplier.get();

			_workflowDefinitionVersionSupplier = null;
		}

		return workflowDefinitionVersion;
	}

	public void setWorkflowDefinitionVersion(String workflowDefinitionVersion) {
		this.workflowDefinitionVersion = workflowDefinitionVersion;

		_workflowDefinitionVersionSupplier = null;
	}

	@JsonIgnore
	public void setWorkflowDefinitionVersion(
		UnsafeSupplier<String, Exception>
			workflowDefinitionVersionUnsafeSupplier) {

		_workflowDefinitionVersionSupplier = () -> {
			try {
				return workflowDefinitionVersionUnsafeSupplier.get();
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
	protected String workflowDefinitionVersion;

	@JsonIgnore
	private Supplier<String> _workflowDefinitionVersionSupplier;

	@Schema
	public String getWorkflowTaskLabel() {
		if (_workflowTaskLabelSupplier != null) {
			workflowTaskLabel = _workflowTaskLabelSupplier.get();

			_workflowTaskLabelSupplier = null;
		}

		return workflowTaskLabel;
	}

	public void setWorkflowTaskLabel(String workflowTaskLabel) {
		this.workflowTaskLabel = workflowTaskLabel;

		_workflowTaskLabelSupplier = null;
	}

	@JsonIgnore
	public void setWorkflowTaskLabel(
		UnsafeSupplier<String, Exception> workflowTaskLabelUnsafeSupplier) {

		_workflowTaskLabelSupplier = () -> {
			try {
				return workflowTaskLabelUnsafeSupplier.get();
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
	protected String workflowTaskLabel;

	@JsonIgnore
	private Supplier<String> _workflowTaskLabelSupplier;

	@Schema
	public String getWorkflowTaskName() {
		if (_workflowTaskNameSupplier != null) {
			workflowTaskName = _workflowTaskNameSupplier.get();

			_workflowTaskNameSupplier = null;
		}

		return workflowTaskName;
	}

	public void setWorkflowTaskName(String workflowTaskName) {
		this.workflowTaskName = workflowTaskName;

		_workflowTaskNameSupplier = null;
	}

	@JsonIgnore
	public void setWorkflowTaskName(
		UnsafeSupplier<String, Exception> workflowTaskNameUnsafeSupplier) {

		_workflowTaskNameSupplier = () -> {
			try {
				return workflowTaskNameUnsafeSupplier.get();
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
	protected String workflowTaskName;

	@JsonIgnore
	private Supplier<String> _workflowTaskNameSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WorkflowTaskTransition)) {
			return false;
		}

		WorkflowTaskTransition workflowTaskTransition =
			(WorkflowTaskTransition)object;

		return Objects.equals(toString(), workflowTaskTransition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Transition[] transitions = getTransitions();

		if (transitions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transitions\": ");

			sb.append("[");

			for (int i = 0; i < transitions.length; i++) {
				sb.append(String.valueOf(transitions[i]));

				if ((i + 1) < transitions.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		String workflowDefinitionVersion = getWorkflowDefinitionVersion();

		if (workflowDefinitionVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinitionVersion\": ");

			sb.append("\"");

			sb.append(_escape(workflowDefinitionVersion));

			sb.append("\"");
		}

		String workflowTaskLabel = getWorkflowTaskLabel();

		if (workflowTaskLabel != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowTaskLabel\": ");

			sb.append("\"");

			sb.append(_escape(workflowTaskLabel));

			sb.append("\"");
		}

		String workflowTaskName = getWorkflowTaskName();

		if (workflowTaskName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowTaskName\": ");

			sb.append("\"");

			sb.append(_escape(workflowTaskName));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.admin.workflow.dto.v1_0.WorkflowTaskTransition",
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