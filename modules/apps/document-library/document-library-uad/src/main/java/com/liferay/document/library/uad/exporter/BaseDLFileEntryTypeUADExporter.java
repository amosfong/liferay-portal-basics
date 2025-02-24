/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.uad.exporter;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.uad.constants.DLUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the document library file entry type UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link DLFileEntryTypeUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseDLFileEntryTypeUADExporter
	extends DynamicQueryUADExporter<DLFileEntryType> {

	@Override
	public Class<DLFileEntryType> getTypeClass() {
		return DLFileEntryType.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return dlFileEntryTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return DLUADConstants.USER_ID_FIELD_NAMES_DL_FILE_ENTRY_TYPE;
	}

	@Override
	protected String toXmlString(DLFileEntryType dlFileEntryType) {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.document.library.kernel.model.DLFileEntryType");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(dlFileEntryType.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected DLFileEntryTypeLocalService dlFileEntryTypeLocalService;

}