/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPConfigurationEntry;
import com.liferay.commerce.product.service.CPConfigurationEntryLocalServiceUtil;

/**
 * The extended model base implementation for the CPConfigurationEntry service. Represents a row in the &quot;CPConfigurationEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPConfigurationEntryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPConfigurationEntryImpl
 * @see CPConfigurationEntry
 * @generated
 */
public abstract class CPConfigurationEntryBaseImpl
	extends CPConfigurationEntryModelImpl implements CPConfigurationEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp configuration entry model instance should use the <code>CPConfigurationEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CPConfigurationEntryLocalServiceUtil.addCPConfigurationEntry(this);
		}
		else {
			CPConfigurationEntryLocalServiceUtil.updateCPConfigurationEntry(
				this);
		}
	}

}