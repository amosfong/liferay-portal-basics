/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.item.selector.ItemSelector;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration;
import com.liferay.layout.content.page.editor.web.internal.manager.ContentManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentCollectionManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.style.book.service.StyleBookEntryLocalService;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class ContentPageEditorLayoutUtilityPageEntryDisplayContext
	extends ContentPageEditorDisplayContext {

	public ContentPageEditorLayoutUtilityPageEntryDisplayContext(
		List<ContentPageEditorSidebarPanel> contentPageEditorSidebarPanels,
		ContentManager contentManager,
		FragmentCollectionManager fragmentCollectionManager,
		FragmentEntryLinkManager fragmentEntryLinkManager,
		FragmentEntryLinkLocalService fragmentEntryLinkLocalService,
		FragmentEntryLocalService fragmentEntryLocalService,
		FrontendTokenDefinitionRegistry frontendTokenDefinitionRegistry,
		HttpServletRequest httpServletRequest,
		ItemSelector itemSelector, JSONFactory jsonFactory, Language language,
		LayoutLocalService layoutLocalService,
		LayoutLockManager layoutLockManager,
		LayoutSetLocalService layoutSetLocalService,
		LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService,
		LayoutPageTemplateEntryService layoutPageTemplateEntryService,
		LayoutPermission layoutPermission,
		PageEditorConfiguration pageEditorConfiguration, Portal portal,
		PortletRequest portletRequest, PortletURLFactory portletURLFactory,
		RenderResponse renderResponse, Staging staging,
		StagingGroupHelper stagingGroupHelper,
		StyleBookEntryLocalService styleBookEntryLocalService,
		UserLocalService userLocalService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		super(
			contentPageEditorSidebarPanels, contentManager,
			fragmentCollectionManager, fragmentEntryLinkManager,
			fragmentEntryLinkLocalService, fragmentEntryLocalService,
			frontendTokenDefinitionRegistry, httpServletRequest,
			itemSelector, jsonFactory, language, layoutLocalService,
			layoutLockManager, layoutPageTemplateEntryLocalService,
			layoutPageTemplateEntryService, layoutPermission,
			layoutSetLocalService, pageEditorConfiguration, portal,
			portletRequest, portletURLFactory, renderResponse, staging,
			stagingGroupHelper, styleBookEntryLocalService, userLocalService,
			workflowDefinitionLinkLocalService);
	}

	@Override
	public boolean isWorkflowEnabled() {
		return false;
	}

	@Override
	protected String getLookAndFeelURL() {
		return null;
	}

}