/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bruno Farache
 * @author Macerllus Tavares
 * @author Juan Fernández
 * @author Julio Camarero
 */
public interface WorkflowHandler<T> {

	public default void contributeServiceContext(
		ServiceContext serviceContext) {
	}

	public String getClassName();

	public default long getDiscussionClassPK(
		Map<String, Serializable> workflowContext) {

		return GetterUtil.getLong(
			workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	}

	public String getIconCssClass();

	public default String getNotificationLink(
			long workflowTaskId, ServiceContext serviceContext)
		throws PortalException {

		return StringPool.BLANK;
	}

	public String getSummary(
		long classPK, PortletRequest portletRequest,
		PortletResponse portletResponse);

	public String getTitle(long classPK, Locale locale);

	public String getType(Locale locale);

	public PortletURL getURLEdit(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse);

	public PortletURL getURLViewDiffs(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse);

	public String getURLViewInContext(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect);

	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException;

	public boolean include(
		long classPK, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String template);

	public default boolean isCommentable() {
		return true;
	}

	public boolean isScopeable();

	public boolean isVisible();

	public default boolean isVisible(Group group) {
		return isVisible();
	}

	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK, T model,
			Map<String, Serializable> workflowContext)
		throws PortalException;

	public T updateStatus(int status, Map<String, Serializable> workflowContext)
		throws PortalException;

	public default T updateStatus(
			T model, int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		return updateStatus(status, workflowContext);
	}

}