/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.model.impl;

import com.liferay.document.library.model.DLFileVersionPreview;
import com.liferay.document.library.service.DLFileVersionPreviewLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileVersionPreview service. Represents a row in the &quot;DLFileVersionPreview&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileVersionPreviewImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersionPreviewImpl
 * @see DLFileVersionPreview
 * @generated
 */
public abstract class DLFileVersionPreviewBaseImpl
	extends DLFileVersionPreviewModelImpl implements DLFileVersionPreview {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dl file version preview model instance should use the <code>DLFileVersionPreview</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DLFileVersionPreviewLocalServiceUtil.addDLFileVersionPreview(this);
		}
		else {
			DLFileVersionPreviewLocalServiceUtil.updateDLFileVersionPreview(
				this);
		}
	}

}