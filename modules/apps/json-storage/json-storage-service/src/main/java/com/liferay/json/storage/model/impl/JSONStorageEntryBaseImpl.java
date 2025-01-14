/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.json.storage.model.impl;

import com.liferay.json.storage.model.JSONStorageEntry;
import com.liferay.json.storage.service.JSONStorageEntryLocalServiceUtil;

/**
 * The extended model base implementation for the JSONStorageEntry service. Represents a row in the &quot;JSONStorageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JSONStorageEntryImpl}.
 * </p>
 *
 * @author Preston Crary
 * @see JSONStorageEntryImpl
 * @see JSONStorageEntry
 * @generated
 */
public abstract class JSONStorageEntryBaseImpl
	extends JSONStorageEntryModelImpl implements JSONStorageEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a json storage entry model instance should use the <code>JSONStorageEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			JSONStorageEntryLocalServiceUtil.addJSONStorageEntry(this);
		}
		else {
			JSONStorageEntryLocalServiceUtil.updateJSONStorageEntry(this);
		}
	}

}