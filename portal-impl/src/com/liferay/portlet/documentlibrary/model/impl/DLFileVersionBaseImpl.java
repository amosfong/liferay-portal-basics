/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileVersion service. Represents a row in the &quot;DLFileVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersionImpl
 * @see DLFileVersion
 * @generated
 */
public abstract class DLFileVersionBaseImpl
	extends DLFileVersionModelImpl implements DLFileVersion {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file version model instance should use the <code>DLFileVersion</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DLFileVersionLocalServiceUtil.addDLFileVersion(this);
		}
		else {
			DLFileVersionLocalServiceUtil.updateDLFileVersion(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) {
		DLFileVersion dlFileVersion = this;

		dlFileVersion.setTreePath(treePath);

		DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);
	}

}