/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.importer.structure.util;

import com.liferay.headless.delivery.dto.v1_0.PageElement;
import com.liferay.layout.internal.importer.LayoutStructureItemImporterContext;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;

import java.util.Set;

/**
 * @author Víctor Galán
 */
public class FormStepItemLayoutStructureItemImporter
	implements LayoutStructureItemImporter {

	@Override
	public LayoutStructureItem addLayoutStructureItem(
			LayoutStructure layoutStructure,
			LayoutStructureItemImporterContext
				layoutStructureItemImporterContext,
			PageElement pageElement, Set<String> warningMessages)
		throws Exception {

		return layoutStructure.addFormStepLayoutStructureItem(
			layoutStructureItemImporterContext.getItemId(pageElement),
			layoutStructureItemImporterContext.getParentItemId(),
			layoutStructureItemImporterContext.getPosition());
	}

	@Override
	public PageElement.Type getPageElementType() {
		return PageElement.Type.FORM_STEP;
	}

}