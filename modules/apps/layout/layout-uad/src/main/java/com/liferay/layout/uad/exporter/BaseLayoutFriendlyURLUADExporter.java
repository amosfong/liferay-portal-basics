/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.uad.exporter;

import com.liferay.layout.uad.constants.LayoutUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout friendly url UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link LayoutFriendlyURLUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseLayoutFriendlyURLUADExporter
	extends DynamicQueryUADExporter<LayoutFriendlyURL> {

	@Override
	public Class<LayoutFriendlyURL> getTypeClass() {
		return LayoutFriendlyURL.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return layoutFriendlyURLLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return LayoutUADConstants.USER_ID_FIELD_NAMES_LAYOUT_FRIENDLY_URL;
	}

	@Override
	protected String toXmlString(LayoutFriendlyURL layoutFriendlyURL) {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.LayoutFriendlyURL");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(layoutFriendlyURL.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected LayoutFriendlyURLLocalService layoutFriendlyURLLocalService;

}