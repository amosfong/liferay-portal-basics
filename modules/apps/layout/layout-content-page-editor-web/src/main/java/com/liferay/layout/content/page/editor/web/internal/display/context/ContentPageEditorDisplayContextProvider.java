/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorWebKeys;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration;
import com.liferay.layout.content.page.editor.web.internal.manager.ContentManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentCollectionManager;
import com.liferay.layout.content.page.editor.web.internal.manager.FragmentEntryLinkManager;
import com.liferay.layout.manager.LayoutLockManager;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalService;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.style.book.service.StyleBookEntryLocalService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	configurationPid = "com.liferay.layout.content.page.editor.web.internal.configuration.PageEditorConfiguration",
	service = ContentPageEditorDisplayContextProvider.class
)
public class ContentPageEditorDisplayContextProvider {

	public ContentPageEditorDisplayContext getContentPageEditorDisplayContext(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse,
		PortletRequest portletRequest) {

		String className = (String)httpServletRequest.getAttribute(
			ContentPageEditorWebKeys.CLASS_NAME);

		if (Objects.equals(className, Layout.class.getName())) {
			return new ContentPageLayoutEditorDisplayContext(
				_getContentPageEditorSidebarPanels(), _contentManager,
				_fragmentCollectionManager, _fragmentEntryLinkManager,
				_fragmentEntryLinkLocalService, _fragmentEntryLocalService,
				_frontendTokenDefinitionRegistry, _groupLocalService,
				httpServletRequest, _jsonFactory, _language,
				_layoutLocalService, _layoutLockManager, _layoutSetLocalService,
				_layoutPageTemplateEntryLocalService,
				_layoutPageTemplateEntryService,
				_layoutPageTemplateStructureLocalService,
				_layoutPageTemplateStructureRelLocalService, _layoutPermission,
				_pageEditorConfiguration, _portal, portletRequest,
				_portletURLFactory, renderResponse, _staging,
				_stagingGroupHelper, _styleBookEntryLocalService,
				_userLocalService, _workflowDefinitionLinkLocalService);
		}

		if (Objects.equals(className, LayoutUtilityPageEntry.class.getName())) {
			return new ContentPageEditorLayoutUtilityPageEntryDisplayContext(
				_getContentPageEditorSidebarPanels(), _contentManager,
				_fragmentCollectionManager, _fragmentEntryLinkManager,
				_fragmentEntryLinkLocalService, _fragmentEntryLocalService,
				_frontendTokenDefinitionRegistry, httpServletRequest,
				_jsonFactory, _language, _layoutLocalService,
				_layoutLockManager, _layoutSetLocalService,
				_layoutPageTemplateEntryLocalService,
				_layoutPageTemplateEntryService, _layoutPermission,
				_pageEditorConfiguration, _portal, portletRequest,
				_portletURLFactory, renderResponse, _staging,
				_stagingGroupHelper, _styleBookEntryLocalService,
				_userLocalService, _workflowDefinitionLinkLocalService);
		}

		long classPK = GetterUtil.getLong(
			httpServletRequest.getAttribute(ContentPageEditorWebKeys.CLASS_PK));

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.fetchLayoutPageTemplateEntry(
				classPK);

		boolean pageIsDisplayPage = false;

		if ((layoutPageTemplateEntry != null) &&
			(layoutPageTemplateEntry.getType() ==
				LayoutPageTemplateEntryTypeConstants.DISPLAY_PAGE)) {

			pageIsDisplayPage = true;
		}

		return new ContentPageEditorLayoutPageTemplateDisplayContext(
			_getContentPageEditorSidebarPanels(), _contentManager,
			_fragmentCollectionManager, _fragmentEntryLinkManager,
			_fragmentEntryLinkLocalService, _fragmentEntryLocalService,
			_frontendTokenDefinitionRegistry, httpServletRequest, _jsonFactory,
			_language, _layoutLocalService, _layoutLockManager,
			_layoutSetLocalService, _layoutPageTemplateEntryLocalService,
			_layoutPageTemplateEntryService, _layoutPermission,
			_pageEditorConfiguration, pageIsDisplayPage, _portal,
			portletRequest, _portletURLFactory, renderResponse, _staging,
			_stagingGroupHelper, _styleBookEntryLocalService, _userLocalService,
			_workflowDefinitionLinkLocalService);
	}

	@Activate
	@Modified
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_pageEditorConfiguration = ConfigurableUtil.createConfigurable(
			PageEditorConfiguration.class, properties);
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, ContentPageEditorSidebarPanel.class);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private List<ContentPageEditorSidebarPanel>
		_getContentPageEditorSidebarPanels() {

		return _serviceTrackerList.toList();
	}

	@Reference
	private ContentManager _contentManager;

	@Reference
	private FragmentCollectionManager _fragmentCollectionManager;

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLinkManager _fragmentEntryLinkManager;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private FrontendTokenDefinitionRegistry _frontendTokenDefinitionRegistry;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutLockManager _layoutLockManager;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private LayoutPageTemplateStructureRelLocalService
		_layoutPageTemplateStructureRelLocalService;

	@Reference
	private LayoutPermission _layoutPermission;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	private volatile PageEditorConfiguration _pageEditorConfiguration;

	@Reference
	private Portal _portal;

	@Reference
	private PortletURLFactory _portletURLFactory;

	private volatile ServiceTrackerList<ContentPageEditorSidebarPanel>
		_serviceTrackerList;

	@Reference
	private Staging _staging;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

	@Reference
	private StyleBookEntryLocalService _styleBookEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}