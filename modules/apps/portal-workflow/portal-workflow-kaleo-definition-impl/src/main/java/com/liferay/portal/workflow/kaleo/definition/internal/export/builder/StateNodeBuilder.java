/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.export.builder;

import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

/**
 * @author Michael C. Han
 */
public class StateNodeBuilder extends BaseNodeBuilder<State> {

	@Override
	public NodeType getNodeType() {
		return NodeType.STATE;
	}

	@Override
	protected State createNode(KaleoNode kaleoNode) {
		return new State(
			kaleoNode.getName(), kaleoNode.getDescription(),
			kaleoNode.isInitial());
	}

}