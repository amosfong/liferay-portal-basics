/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.osgi.commands;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.serializer.LayoutStructureItemJSONSerializer;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.osgi.util.osgi.commands.OSGiCommands;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;

import org.apache.felix.service.command.Descriptor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	property = {
		"osgi.command.function=exportAsJSON", "osgi.command.scope=layout"
	},
	service = OSGiCommands.class
)
public class LayoutOSGiCommands implements OSGiCommands {

	@Descriptor("Get page definition JSON for a given layout by its PLID")
	public String exportAsJSON(long plid) throws PortalException {
		Layout layout = _layoutLocalService.fetchLayout(plid);

		if (layout == null) {
			return "Layout with PLID " + plid + " does not exist";
		}

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(layout.getGroupId(), plid);

		if (layoutPageTemplateStructure == null) {
			return "Layout with PLID " + plid +
				" does not have a layout page template structure";
		}

		long defaultSegmentsExperienceId = 0;

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructure.getData(defaultSegmentsExperienceId));

		return _layoutStructureItemJSONSerializer.toJSONString(
			layout, layoutStructure.getMainItemId(), false, false,
			defaultSegmentsExperienceId);
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private LayoutStructureItemJSONSerializer
		_layoutStructureItemJSONSerializer;

}