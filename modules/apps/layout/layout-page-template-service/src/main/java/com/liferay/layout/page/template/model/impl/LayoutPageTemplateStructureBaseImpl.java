/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.model.impl;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutPageTemplateStructure service. Represents a row in the &quot;LayoutPageTemplateStructure&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPageTemplateStructureImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateStructureImpl
 * @see LayoutPageTemplateStructure
 * @generated
 */
public abstract class LayoutPageTemplateStructureBaseImpl
	extends LayoutPageTemplateStructureModelImpl
	implements LayoutPageTemplateStructure {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout page template structure model instance should use the <code>LayoutPageTemplateStructure</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			LayoutPageTemplateStructureLocalServiceUtil.
				addLayoutPageTemplateStructure(this);
		}
		else {
			LayoutPageTemplateStructureLocalServiceUtil.
				updateLayoutPageTemplateStructure(this);
		}
	}

}