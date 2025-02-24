/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.uad.exporter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalService;
import com.liferay.portal.workflow.kaleo.uad.constants.KaleoUADConstants;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kaleo task assignment instance UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link KaleoTaskAssignmentInstanceUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseKaleoTaskAssignmentInstanceUADExporter
	extends DynamicQueryUADExporter<KaleoTaskAssignmentInstance> {

	@Override
	public Class<KaleoTaskAssignmentInstance> getTypeClass() {
		return KaleoTaskAssignmentInstance.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return kaleoTaskAssignmentInstanceLocalService.
			getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return KaleoUADConstants.
			USER_ID_FIELD_NAMES_KALEO_TASK_ASSIGNMENT_INSTANCE;
	}

	@Override
	protected String toXmlString(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {

		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(kaleoTaskAssignmentInstance.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected KaleoTaskAssignmentInstanceLocalService
		kaleoTaskAssignmentInstanceLocalService;

}