/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.template.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.template.constants.TemplatePortletKeys;

import java.io.File;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	property = {
		"javax.portlet.name=" + TemplatePortletKeys.TEMPLATE,
		"mvc.command.name=/template/update_ddm_template"
	},
	service = MVCActionCommand.class
)
public class UpdateDDMTemplateMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long ddmTemplateId = ParamUtil.getLong(
			uploadPortletRequest, "ddmTemplateId");

		long classPK = ParamUtil.getLong(uploadPortletRequest, "classPK");
		Map<Locale, String> nameMap = _localization.getLocalizationMap(
			uploadPortletRequest, "name");
		Map<Locale, String> descriptionMap = _localization.getLocalizationMap(
			uploadPortletRequest, "description");
		String script = FileUtil.read(
			uploadPortletRequest.getFile("scriptContent"));
		boolean cacheable = ParamUtil.getBoolean(
			uploadPortletRequest, "cacheable");

		String smallImageSource = ParamUtil.getString(
			uploadPortletRequest, "smallImageSource", "none");

		boolean smallImage = !Objects.equals(smallImageSource, "none");

		String smallImageURL = StringPool.BLANK;
		File smallImageFile = null;

		if (Objects.equals(smallImageSource, "url")) {
			smallImageURL = ParamUtil.getString(
				uploadPortletRequest, "smallImageURL");
		}
		else if (Objects.equals(smallImageSource, "file")) {
			smallImageFile = uploadPortletRequest.getFile("smallImageFile");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), uploadPortletRequest);

		DDMTemplate ddmTemplate = null;

		if (ddmTemplateId <= 0) {
			long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId");
			long classNameId = ParamUtil.getLong(
				uploadPortletRequest, "classNameId");
			long resourceClassNameId = ParamUtil.getLong(
				uploadPortletRequest, "resourceClassNameId");
			String templateKey = ParamUtil.getString(
				uploadPortletRequest, "templateKey");

			ddmTemplate = _ddmTemplateService.addTemplate(
				null, groupId, classNameId, classPK, resourceClassNameId,
				templateKey, nameMap, descriptionMap,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, StringPool.BLANK,
				TemplateConstants.LANG_TYPE_FTL, script, cacheable, smallImage,
				smallImageURL, smallImageFile, serviceContext);
		}
		else {
			ddmTemplate = _ddmTemplateService.updateTemplate(
				ddmTemplateId, classPK, nameMap, descriptionMap,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, StringPool.BLANK,
				TemplateConstants.LANG_TYPE_FTL, script, cacheable, smallImage,
				smallImageURL, smallImageFile, serviceContext);
		}

		boolean saveAndContinue = ParamUtil.getBoolean(
			uploadPortletRequest, "saveAndContinue");

		if (saveAndContinue) {
			actionRequest.setAttribute(
				WebKeys.REDIRECT,
				PortletURLBuilder.createRenderURL(
					_portal.getLiferayPortletResponse(actionResponse)
				).setMVCRenderCommandName(
					"/template/edit_ddm_template"
				).setRedirect(
					ParamUtil.getString(uploadPortletRequest, "redirect")
				).setTabs1(
					ParamUtil.getString(
						uploadPortletRequest, "tabs1", "information-templates")
				).setParameter(
					"ddmTemplateId", ddmTemplate.getTemplateId()
				).buildString());
		}
	}

	@Reference
	private DDMTemplateService _ddmTemplateService;

	@Reference
	private Localization _localization;

	@Reference
	private Portal _portal;

}