/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;

/**
 * The extended model base implementation for the ObjectDefinition service. Represents a row in the &quot;ObjectDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ObjectDefinitionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectDefinitionImpl
 * @see ObjectDefinition
 * @generated
 */
public abstract class ObjectDefinitionBaseImpl
	extends ObjectDefinitionModelImpl implements ObjectDefinition {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a object definition model instance should use the <code>ObjectDefinition</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ObjectDefinitionLocalServiceUtil.addObjectDefinition(this);
		}
		else {
			ObjectDefinitionLocalServiceUtil.updateObjectDefinition(this);
		}
	}

}