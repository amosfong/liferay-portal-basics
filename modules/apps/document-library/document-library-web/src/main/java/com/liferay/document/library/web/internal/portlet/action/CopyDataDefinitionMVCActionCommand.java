/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action;

import com.liferay.data.engine.field.type.util.LocalizedValueUtil;
import com.liferay.data.engine.rest.dto.v2_0.DataDefinition;
import com.liferay.data.engine.rest.resource.v2_0.DataDefinitionResource;
import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseTransactionalMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 * @author Alicia García
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/copy_data_definition"
	},
	service = MVCActionCommand.class
)
public class CopyDataDefinitionMVCActionCommand
	extends BaseTransactionalMVCActionCommand {

	@Override
	protected void doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ddmStructureId = ParamUtil.getLong(
			actionRequest, "ddmStructureId");

		Map<Locale, String> descriptionMap = _localization.getLocalizationMap(
			actionRequest, "description");
		Map<Locale, String> nameMap = _localization.getLocalizationMap(
			actionRequest, "name");

		DataDefinitionResource.Builder dataDefinitionResourceBuilder =
			_dataDefinitionResourceFactory.create();

		DataDefinitionResource dataDefinitionResource =
			dataDefinitionResourceBuilder.user(
				themeDisplay.getUser()
			).build();

		DataDefinition dataDefinition =
			dataDefinitionResource.postDataDefinitionCopy(ddmStructureId);

		dataDefinition.setDescription(
			() -> LocalizedValueUtil.toStringObjectMap(descriptionMap));
		dataDefinition.setName(
			() -> LocalizedValueUtil.toStringObjectMap(nameMap));

		dataDefinitionResource.putDataDefinition(
			dataDefinition.getId(), dataDefinition);

		boolean copyTemplates = ParamUtil.getBoolean(
			actionRequest, "copyTemplates");

		if (copyTemplates) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				DDMStructure.class.getName(), actionRequest);

			_ddmTemplateService.copyTemplates(
				_portal.getClassNameId(DDMStructure.class), ddmStructureId,
				_portal.getClassNameId(DLFileEntryMetadata.class.getName()),
				dataDefinition.getId(),
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, serviceContext);
		}
	}

	@Reference
	private DataDefinitionResource.Factory _dataDefinitionResourceFactory;

	@Reference
	private DDMTemplateService _ddmTemplateService;

	@Reference
	private Localization _localization;

	@Reference
	private Portal _portal;

}