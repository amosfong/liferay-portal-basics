/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTCollectionTemplate;
import com.liferay.change.tracking.service.CTCollectionTemplateLocalServiceUtil;

/**
 * The extended model base implementation for the CTCollectionTemplate service. Represents a row in the &quot;CTCollectionTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTCollectionTemplateImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTCollectionTemplateImpl
 * @see CTCollectionTemplate
 * @generated
 */
public abstract class CTCollectionTemplateBaseImpl
	extends CTCollectionTemplateModelImpl implements CTCollectionTemplate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct collection template model instance should use the <code>CTCollectionTemplate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CTCollectionTemplateLocalServiceUtil.addCTCollectionTemplate(this);
		}
		else {
			CTCollectionTemplateLocalServiceUtil.updateCTCollectionTemplate(
				this);
		}
	}

}