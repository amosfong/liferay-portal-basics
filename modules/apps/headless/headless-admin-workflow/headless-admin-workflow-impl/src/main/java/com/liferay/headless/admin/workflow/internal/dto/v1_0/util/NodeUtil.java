/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.workflow.internal.dto.v1_0.util;

import com.liferay.headless.admin.workflow.dto.v1_0.Node;
import com.liferay.portal.kernel.workflow.WorkflowNode;

import java.util.Locale;

/**
 * @author Feliphe Marinho
 */
public class NodeUtil {

	public static Node toNode(Locale locale, WorkflowNode workflowNode) {
		return new Node() {
			{
				setLabel(() -> workflowNode.getLabel(locale));
				setName(workflowNode::getName);
				setType(
					() -> {
						WorkflowNode.Type workflowNodeType =
							workflowNode.getType();

						return Node.Type.create(workflowNodeType.name());
					});
			}
		};
	}

}