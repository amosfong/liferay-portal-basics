/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.uad.display;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.uad.constants.DLUADConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.user.associated.data.display.BaseModelUADDisplay;

import java.io.Serializable;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the DLFolder UAD display.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link DLFolderUADDisplay}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseDLFolderUADDisplay
	extends BaseModelUADDisplay<DLFolder> {

	@Override
	public DLFolder get(Serializable primaryKey) throws PortalException {
		return dlFolderLocalService.getDLFolder(
			Long.valueOf(primaryKey.toString()));
	}

	@Override
	public String[] getDisplayFieldNames() {
		return new String[] {"name", "description"};
	}

	@Override
	public Class<DLFolder> getTypeClass() {
		return DLFolder.class;
	}

	@Override
	protected long doCount(DynamicQuery dynamicQuery) {
		return dlFolderLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	protected DynamicQuery doGetDynamicQuery() {
		return dlFolderLocalService.dynamicQuery();
	}

	@Override
	protected List<DLFolder> doGetRange(
		DynamicQuery dynamicQuery, int start, int end) {

		return dlFolderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return DLUADConstants.USER_ID_FIELD_NAMES_DL_FOLDER;
	}

	@Reference
	protected DLFolderLocalService dlFolderLocalService;

}