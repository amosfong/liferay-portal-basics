/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alexander Chow
 */
public class DLFileEntryMetadataImpl extends DLFileEntryMetadataBaseImpl {

	@Override
	public DLFileVersion getFileVersion() throws PortalException {
		return DLFileVersionLocalServiceUtil.getFileVersion(getFileVersionId());
	}

}