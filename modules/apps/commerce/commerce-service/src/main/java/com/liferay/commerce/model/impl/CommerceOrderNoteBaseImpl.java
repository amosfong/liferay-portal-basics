/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceOrderNote service. Represents a row in the &quot;CommerceOrderNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderNoteImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteImpl
 * @see CommerceOrderNote
 * @generated
 */
public abstract class CommerceOrderNoteBaseImpl
	extends CommerceOrderNoteModelImpl implements CommerceOrderNote {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order note model instance should use the <code>CommerceOrderNote</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceOrderNoteLocalServiceUtil.addCommerceOrderNote(this);
		}
		else {
			CommerceOrderNoteLocalServiceUtil.updateCommerceOrderNote(this);
		}
	}

}