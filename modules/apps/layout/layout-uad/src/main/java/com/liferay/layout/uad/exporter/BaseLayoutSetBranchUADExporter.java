/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.uad.exporter;

import com.liferay.layout.uad.constants.LayoutUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalService;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout set branch UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link LayoutSetBranchUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseLayoutSetBranchUADExporter
	extends DynamicQueryUADExporter<LayoutSetBranch> {

	@Override
	public Class<LayoutSetBranch> getTypeClass() {
		return LayoutSetBranch.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return layoutSetBranchLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return LayoutUADConstants.USER_ID_FIELD_NAMES_LAYOUT_SET_BRANCH;
	}

	@Override
	protected String toXmlString(LayoutSetBranch layoutSetBranch) {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.LayoutSetBranch");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(layoutSetBranch.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected LayoutSetBranchLocalService layoutSetBranchLocalService;

}