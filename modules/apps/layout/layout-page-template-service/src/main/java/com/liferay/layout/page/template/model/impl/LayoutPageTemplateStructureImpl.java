/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.model.impl;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalServiceUtil;
import com.liferay.petra.string.StringPool;

import java.util.List;

/**
 * @author Eduardo García
 */
public class LayoutPageTemplateStructureImpl
	extends LayoutPageTemplateStructureBaseImpl {

	@Override
	public String getData(long segmentsExperienceId) {
		// TODO move data field to LayoutPageTemplateStructure and delete
		// LayoutPageTemplateStructureRel

		List<LayoutPageTemplateStructureRel> layoutPageTemplateStructureRels =
			LayoutPageTemplateStructureRelLocalServiceUtil.
				getLayoutPageTemplateStructureRels(
					getLayoutPageTemplateStructureId());

		if (!layoutPageTemplateStructureRels.isEmpty()) {
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel =
				layoutPageTemplateStructureRels.get(0);

			return layoutPageTemplateStructureRel.getData();
		}

		return StringPool.BLANK;
	}

	@Override
	public String getData(String segmentsExperienceKey) {
		return StringPool.BLANK;
	}

	@Override
	public String getDefaultSegmentsExperienceData() {
		List<LayoutPageTemplateStructureRel> layoutPageTemplateStructureRels =
			LayoutPageTemplateStructureRelLocalServiceUtil.
				getLayoutPageTemplateStructureRels(
					getLayoutPageTemplateStructureId());

		if (!layoutPageTemplateStructureRels.isEmpty()) {
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel =
				layoutPageTemplateStructureRels.get(0);

			return layoutPageTemplateStructureRel.getData();
		}

		return StringPool.BLANK;
	}

}