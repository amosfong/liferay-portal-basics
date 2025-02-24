/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.qualifier.model.impl;

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.service.CommerceQualifierEntryLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceQualifierEntry service. Represents a row in the &quot;CommerceQualifierEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceQualifierEntryImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceQualifierEntryImpl
 * @see CommerceQualifierEntry
 * @generated
 */
public abstract class CommerceQualifierEntryBaseImpl
	extends CommerceQualifierEntryModelImpl implements CommerceQualifierEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce qualifier entry model instance should use the <code>CommerceQualifierEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceQualifierEntryLocalServiceUtil.addCommerceQualifierEntry(
				this);
		}
		else {
			CommerceQualifierEntryLocalServiceUtil.updateCommerceQualifierEntry(
				this);
		}
	}

}