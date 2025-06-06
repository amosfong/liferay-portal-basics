/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.workflow;

import com.liferay.layout.constants.LayoutTypeSettingsConstants;
import com.liferay.layout.internal.configuration.LayoutWorkflowHandlerConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	configurationPid = "com.liferay.layout.internal.configuration.LayoutWorkflowHandlerConfiguration",
	property = "model.class.name=com.liferay.portal.kernel.model.Layout",
	service = WorkflowHandler.class
)
public class LayoutWorkflowHandler extends BaseWorkflowHandler<Layout> {

	@Override
	public String getClassName() {
		return Layout.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return _language.get(locale, "content-page");
	}

	@Override
	public String getURLViewInContext(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		return StringPool.BLANK;
	}

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException {

		Layout layout = _layoutLocalService.getLayout(classPK);

		if (layout.isSystem() || !layout.isTypeContent()) {
			return null;
		}

		return super.getWorkflowDefinitionLink(companyId, groupId, classPK);
	}

	@Override
	public boolean isVisible() {
		return _layoutWorkflowHandlerConfiguration.enabled();
	}

	@Override
	public Layout updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		Layout layout = _layoutLocalService.getLayout(classPK);

		if (layout.isDenied() && (status == WorkflowConstants.STATUS_PENDING)) {
			return layout;
		}

		ServiceContext serviceContext1 = (ServiceContext)workflowContext.get(
			"serviceContext");

		if (status != WorkflowConstants.STATUS_APPROVED) {
			return _layoutLocalService.updateStatus(
				userId, classPK, status, serviceContext1);
		}

		Layout draftLayout = layout.fetchDraftLayout();

		UnicodeProperties typeSettingsUnicodeProperties =
			draftLayout.getTypeSettingsProperties();

		typeSettingsUnicodeProperties.remove(
			LayoutTypeSettingsConstants.KEY_DESIGN_CONFIGURATION_MODIFIED);

		draftLayout = _layoutLocalService.updateLayout(
			draftLayout.getGroupId(), draftLayout.isPrivateLayout(),
			draftLayout.getLayoutId(),
			typeSettingsUnicodeProperties.toString());

		long originalUserId = PrincipalThreadLocal.getUserId();

		try {
			PrincipalThreadLocal.setName(userId);

			_layoutLocalService.copyLayoutContent(draftLayout, layout);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
		finally {
			PrincipalThreadLocal.setName(originalUserId);
		}

		_layoutLocalService.updateStatus(
			userId, draftLayout.getPlid(), WorkflowConstants.STATUS_APPROVED,
			serviceContext1);

		return _layoutLocalService.updateStatus(
			userId, classPK, status, serviceContext1);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_layoutWorkflowHandlerConfiguration =
			ConfigurableUtil.createConfigurable(
				LayoutWorkflowHandlerConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutWorkflowHandler.class);

	@Reference
	private Language _language;

	@Reference
	private LayoutLocalService _layoutLocalService;

	private volatile LayoutWorkflowHandlerConfiguration
		_layoutWorkflowHandlerConfiguration;

	@Reference
	private Portal _portal;

}