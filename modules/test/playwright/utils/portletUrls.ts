/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
export const PORTLET_URLS = {
	announcements:
		'/~/control_panel/manage?p_p_id=com_liferay_announcements_web_portlet_AnnouncementsAdminPortlet',
	blogs: '/~/control_panel/manage?p_p_id=com_liferay_blogs_web_portlet_BlogsAdminPortlet',
	categoriesAdmin: '/~/control_panel/manage/-/categories_admin/vocabularies',
	collections:
		'/~/control_panel/manage?p_p_id=com_liferay_asset_list_web_portlet_AssetListPortlet',
	contentDashboard:
		'/~/control_panel/manage?p_p_id=com_liferay_content_dashboard_web_portlet_ContentDashboardAdminPortlet',
	depotAdmin:
		'/~/control_panel/manage?p_p_id=com_liferay_depot_web_portlet_DepotAdminPortlet',
	displayPageTemplates:
		'/~/control_panel/manage?p_p_id=com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet&_com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet_tabs1=display-page-templates',
	documentLibrary:
		'/~/control_panel/manage?p_p_id=com_liferay_document_library_web_portlet_DLAdminPortlet',
	forms: '/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet',
	fragments: '/~/control_panel/manage/-/fragments/fragment_collections',
	journal:
		'/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet',
	journalStructures:
		'/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fview_ddm_structures.jsp',
	journalTemplates:
		'/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fview_ddm_templates.jsp',
	knowledgeBase:
		'/~/control_panel/manage?p_p_id=com_liferay_knowledge_base_web_portlet_AdminPortlet',
	languageOverride:
		'/~/control_panel/manage?p_p_id=com_liferay_portal_language_override_web_internal_portlet_PLOPortlet',
	lockedItems:
		'/~/control_panel/manage?p_p_id=com_liferay_locked_items_web_internal_portlet_LockedItemsPortlet',
	lockedPages:
		'/~/control_panel/manage?p_p_id=com_liferay_layout_locked_layouts_web_internal_portlet_LockedLayoutsPortlet',
	masterPages:
		'/~/control_panel/manage?p_p_id=com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet&_com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet_tabs1=master-layouts',
	messageBoardsAdmin:
		'/~/control_panel/manage?p_p_id=com_liferay_message_boards_web_portlet_MBAdminPortlet',
	modelBuilder:
		'/~/control_panel/manage?p_p_id=com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet&_com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet_mvcRenderCommandName=/object_definitions/view_model_builder',
	myWorkflowTasks:
		'/~/control_panel/manage?p_p_id=com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet',
	navigationMenus:
		'/~/control_panel/manage?p_p_id=com_liferay_site_navigation_admin_web_portlet_SiteNavigationAdminPortlet',
	notificationTemplates:
		'/~/control_panel/manage?p_p_id=com_liferay_notification_web_internal_portlet_NotificationTemplatesPortlet',
	notifications:
		'/manage?p_p_id=com_liferay_notifications_web_portlet_NotificationsPortlet',
	objects:
		'/~/control_panel/manage?p_p_id=com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet',
	pageTemplates:
		'/~/control_panel/manage?p_p_id=com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet&_com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet_tabs1=page-templates',
	pages: '/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet',
	processBuilder:
		'/~/control_panel/manage?p_p_id=com_liferay_portal_workflow_web_portlet_ControlPanelWorkflowPortlet',
	publications:
		'/~/control_panel/manage?p_p_id=com_liferay_change_tracking_web_portlet_PublicationsPortlet',
	scriptManagement:
		'/~/control_panel/manage?p_p_id=com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_mvcRenderCommandName=%2Fconfiguration_admin%2Fview_configuration_screen&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_configurationScreenKey=script-management',
	selectSites: '/~/control_panel/manage/-/sites/sites/select_site',
	serviceAccessPolicy:
		'/~/control_panel/manage?p_p_id=com_liferay_portal_security_service_access_policy_web_portlet_SAPPortlet&p_p_lifecycle=0&p_p_state=maximized',
	siteSettings: '/~/control_panel/manage/-/site/settings',
	staging:
		'/~/control_panel/manage?p_p_id=com_liferay_staging_processes_web_portlet_StagingProcessesPortlet',
	styleBooks: '/~/control_panel/manage/-/style_books/style_books',
	systemFeatureFlagDeveloper:
		'/group/control_panel/manage?p_p_id=com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_mvcRenderCommandName=%2Fconfiguration_admin%2Fview_configuration_screen&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_configurationScreenKey=feature.flag.3.dev.system',
	tagsAdmin: '/~/control_panel/manage/-/tags_admin/tags',
	teams: '/~/control_panel/manage/-/site_teams/teams',
	templates:
		'/~/control_panel/manage?p_p_id=com_liferay_template_web_internal_portlet_TemplatePortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view',
	utilityPages:
		'/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_tabs1=utility-pages',
	widgetPageTemplates:
		'/group/global/~/control_panel/manage?p_p_id=com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet&_com_liferay_layout_page_template_admin_web_portlet_LayoutPageTemplatesPortlet_tabs1=page-templates',
	widgetTemplates:
		'/~/control_panel/manage?p_p_id=com_liferay_template_web_internal_portlet_TemplatePortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_template_web_internal_portlet_TemplatePortlet_tabs1=widget-templates',
	wikiAdmin:
		'/~/control_panel/manage?p_p_id=com_liferay_wiki_web_portlet_WikiAdminPortlet',
	workflow:
		'/~/control_panel/manage?p_p_id=com_liferay_portal_workflow_web_internal_portlet_SiteAdministrationWorkflowPortlet',
};
