/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.parser;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapperFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.security.script.management.configuration.helper.ScriptManagementConfigurationHelper;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.definition.parser.NodeValidator;
import com.liferay.portal.workflow.kaleo.definition.parser.WorkflowValidator;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
@Component(service = WorkflowValidator.class)
public class XMLWorkflowValidator implements WorkflowValidator {

	@Override
	public void validate(Definition definition) throws WorkflowException {
		State initialState = definition.getInitialState();

		if (initialState == null) {
			throw new KaleoDefinitionValidationException.
				MustSetInitialStateNode();
		}

		List<State> terminalStates = definition.getTerminalStates();

		if (terminalStates.isEmpty()) {
			throw new KaleoDefinitionValidationException.
				MustSetTerminalStateNode();
		}

		String content = definition.getContent();

		if (!_scriptManagementConfigurationHelper.
				isAllowScriptContentToBeExecutedOrIncluded()) {

			if (content.contains("<script-language>groovy</script-language>")) {
				throw new KaleoDefinitionValidationException.
					NotAllowedScriptLanguage("Groovy is not allowed");
			}
			else if (content.contains(
						"<script-language>java</script-language>") &&
					 !Objects.equals(
						 definition.getName(),
						 "Message Board Threads and Comments Reputation " +
							 "Approver")) {

				throw new KaleoDefinitionValidationException.
					NotAllowedScriptLanguage("Java is not allowed");
			}
		}

		if (definition.getForksCount() != definition.getJoinsCount()) {
			throw new KaleoDefinitionValidationException.
				UnbalancedForkAndJoinNodes();
		}

		Collection<Node> nodes = definition.getNodes();

		for (Node node : nodes) {
			NodeValidator<Node> nodeValidator = _serviceTrackerMap.getService(
				node.getNodeType());

			nodeValidator.validate(definition, node);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<NodeValidator<Node>>)(Class<?>)NodeValidator.class, null,
			ServiceReferenceMapperFactory.create(
				bundleContext,
				(nodeValidator, emitter) -> emitter.emit(
					nodeValidator.getNodeType())));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference
	private ScriptManagementConfigurationHelper
		_scriptManagementConfigurationHelper;

	private ServiceTrackerMap<NodeType, NodeValidator<Node>> _serviceTrackerMap;

}