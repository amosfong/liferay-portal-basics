/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileEntry service. Represents a row in the &quot;DLFileEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryImpl
 * @see DLFileEntry
 * @generated
 */
public abstract class DLFileEntryBaseImpl
	extends DLFileEntryModelImpl implements DLFileEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file entry model instance should use the <code>DLFileEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DLFileEntryLocalServiceUtil.addDLFileEntry(this);
		}
		else {
			DLFileEntryLocalServiceUtil.updateDLFileEntry(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) {
		DLFileEntry dlFileEntry = this;

		dlFileEntry.setTreePath(treePath);

		DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
	}

}