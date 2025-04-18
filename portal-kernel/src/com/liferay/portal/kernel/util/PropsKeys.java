/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public interface PropsKeys {

	public static final String ACCESS_CONTROL_SANITIZE_SECURITY_EXCEPTION =
		"access.control.sanitize.security.exception";

	public static final String ADMIN_ANALYTICS_TYPES = "admin.analytics.types";

	public static final String ADMIN_DEFAULT_GROUP_NAMES =
		"admin.default.group.names";

	public static final String ADMIN_DEFAULT_ORGANIZATION_GROUP_NAMES =
		"admin.default.organization.group.names";

	public static final String ADMIN_DEFAULT_ROLE_NAMES =
		"admin.default.role.names";

	public static final String ADMIN_DEFAULT_USER_GROUP_NAMES =
		"admin.default.user.group.names";

	public static final String ADMIN_EMAIL_FROM_ADDRESS =
		"admin.email.from.address";

	public static final String ADMIN_EMAIL_FROM_NAME = "admin.email.from.name";

	public static final String ADMIN_EMAIL_PASSWORD_CHANGED_BODY =
		"admin.email.password.changed.body";

	public static final String ADMIN_EMAIL_PASSWORD_CHANGED_SUBJECT =
		"admin.email.password.changed.subject";

	public static final String ADMIN_EMAIL_PASSWORD_LOCKOUT_BODY =
		"admin.email.password.lockout.body";

	public static final String ADMIN_EMAIL_PASSWORD_LOCKOUT_SUBJECT =
		"admin.email.password.lockout.subject";

	public static final String ADMIN_EMAIL_PASSWORD_LOCKOUT_UNTIL_BODY =
		"admin.email.password.lockout.until.body";

	public static final String ADMIN_EMAIL_PASSWORD_RESET_BODY =
		"admin.email.password.reset.body";

	public static final String ADMIN_EMAIL_PASSWORD_RESET_SUBJECT =
		"admin.email.password.reset.subject";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String ADMIN_EMAIL_PASSWORD_SENT_BODY =
		"admin.email.password.sent.body";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String ADMIN_EMAIL_PASSWORD_SENT_SUBJECT =
		"admin.email.password.sent.subject";

	public static final String ADMIN_EMAIL_PASSWORD_UNCHANGEABLE_BODY =
		"admin.email.password.unchangeable.body";

	public static final String ADMIN_EMAIL_PASSWORD_UNCHANGEABLE_SUBJECT =
		"admin.email.password.unchangeable.subject";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String ADMIN_EMAIL_USER_ADDED_BODY =
		"admin.email.user.added.body";

	public static final String ADMIN_EMAIL_USER_ADDED_ENABLED =
		"admin.email.user.added.enabled";

	public static final String ADMIN_EMAIL_USER_ADDED_NO_PASSWORD_BODY =
		"admin.email.user.added.no.password.body";

	public static final String ADMIN_EMAIL_USER_ADDED_RESET_PASSWORD_BODY =
		"admin.email.user.added.reset.password.body";

	public static final String ADMIN_EMAIL_USER_ADDED_SUBJECT =
		"admin.email.user.added.subject";

	public static final String ADMIN_EMAIL_USER_CREATION_ATTEMPT_BODY =
		"admin.email.user.creation.attempt.body";

	public static final String ADMIN_EMAIL_USER_CREATION_ATTEMPT_SUBJECT =
		"admin.email.user.creation.attempt.subject";

	public static final String ADMIN_EMAIL_VERIFICATION_BODY =
		"admin.email.verification.body";

	public static final String ADMIN_EMAIL_VERIFICATION_SUBJECT =
		"admin.email.verification.subject";

	public static final String ADMIN_LIFERAY_ANALYTICS_KEY =
		"admin.liferay.analytics.key";

	public static final String ADMIN_MAIL_HOST_NAMES = "admin.mail.host.names";

	public static final String ADMIN_OBFUSCATED_PROPERTIES =
		"admin.obfuscated.properties";

	public static final String ADMIN_RESERVED_EMAIL_ADDRESSES =
		"admin.reserved.email.addresses";

	public static final String ADMIN_RESERVED_SCREEN_NAMES =
		"admin.reserved.screen.names";

	public static final String ADMIN_SYNC_DEFAULT_ASSOCIATIONS =
		"admin.sync.default.associations";

	public static final String ANALYTICS_CLOUD_CLIENT_JS_VERSION =
		"analytics.cloud.client.js.version";

	public static final String ANNOUNCEMENTS_EMAIL_BODY =
		"announcements.email.body";

	public static final String ANNOUNCEMENTS_EMAIL_FROM_ADDRESS =
		"announcements.email.from.address";

	public static final String ANNOUNCEMENTS_EMAIL_FROM_NAME =
		"announcements.email.from.name";

	public static final String ANNOUNCEMENTS_EMAIL_SUBJECT =
		"announcements.email.subject";

	public static final String ANNOUNCEMENTS_EMAIL_TO_ADDRESS =
		"announcements.email.to.address";

	public static final String ANNOUNCEMENTS_EMAIL_TO_NAME =
		"announcements.email.to.name";

	public static final String ANNOUNCEMENTS_ENTRY_CHECK_INTERVAL =
		"announcements.entry.check.interval";

	public static final String ANNOUNCEMENTS_ENTRY_PAGE_DELTA_VALUES =
		"announcements.entry.page.delta.values";

	public static final String ANNOUNCEMENTS_ENTRY_TYPES =
		"announcements.entry.types";

	public static final String APPLICATION_SHUTDOWN_EVENTS =
		"application.shutdown.events";

	public static final String APPLICATION_STARTUP_EVENTS =
		"application.startup.events";

	public static final String APPLICATIONS_MENU_DEFAULT_LIFERAY_LOGO =
		"application.menu.default.liferay.logo";

	public static final String APPLICATIONS_MENU_DEFAULT_LIFERAY_NAME =
		"application.menu.default.liferay.name";

	public static final String ASSET_BROWSER_SEARCH_WITH_DATABASE =
		"asset.browser.search.with.database";

	public static final String ASSET_CATEGORIES_SELECTOR_MAX_ENTRIES =
		"asset.categories.selector.max.entries";

	public static final String ASSET_ENTRY_INCREMENT_VIEW_COUNTER_ENABLED =
		"asset.entry.increment.view.counter.enabled";

	public static final String ASSET_FILTER_SEARCH_LIMIT =
		"asset.filter.search.limit";

	public static final String ASSET_RENDERER_ENABLED =
		"asset.renderer.enabled.";

	public static final String ASSET_VOCABULARY_DEFAULT =
		"asset.vocabulary.default";

	public static final String
		AUDIT_MESSAGE_COM_LIFERAY_PORTAL_MODEL_LAYOUT_VIEW =
			"audit.message.com.liferay.portal.kernel.model.Layout.VIEW";

	public static final String AUTH_FAILURE = "auth.failure";

	public static final String AUTH_FORWARD_BY_LAST_PATH =
		"auth.forward.by.last.path";

	public static final String AUTH_FORWARD_BY_REDIRECT =
		"auth.forward.by.redirect";

	public static final String AUTH_FORWARD_LAST_PATHS =
		"auth.forward.last.paths";

	public static final String AUTH_LOGIN_DISABLED = "auth.login.disabled";

	public static final String AUTH_LOGIN_DISABLED_PATH =
		"auth.login.disabled.path";

	public static final String AUTH_LOGIN_PORTLET_NAME =
		"auth.login.portlet.name";

	public static final String AUTH_LOGIN_SITE_URL = "auth.login.site.url";

	public static final String AUTH_LOGIN_URL = "auth.login.url";

	public static final String AUTH_MAC_ALGORITHM = "auth.mac.algorithm";

	public static final String AUTH_MAC_ALLOW = "auth.mac.allow";

	public static final String AUTH_MAC_SHARED_KEY = "auth.mac.shared.key";

	public static final String AUTH_MAX_FAILURES = "auth.max.failures";

	public static final String AUTH_PIPELINE_ENABLE_LIFERAY_CHECK =
		"auth.pipeline.enable.liferay.check";

	public static final String AUTH_PIPELINE_POST = "auth.pipeline.post";

	public static final String AUTH_PIPELINE_PRE = "auth.pipeline.pre";

	public static final String AUTH_PUBLIC_PATHS = "auth.public.paths";

	public static final String AUTH_SIMULTANEOUS_LOGINS =
		"auth.simultaneous.logins";

	public static final String AUTH_TOKEN_CHECK_ENABLED =
		"auth.token.check.enabled";

	public static final String AUTH_TOKEN_IGNORE_ACTIONS =
		"auth.token.ignore.actions";

	public static final String AUTH_TOKEN_IGNORE_ORIGINS =
		"auth.token.ignore.origins";

	public static final String AUTH_TOKEN_IGNORE_PORTLETS =
		"auth.token.ignore.portlets";

	public static final String AUTH_TOKEN_IMPL = "auth.token.impl";

	public static final String AUTH_TOKEN_LENGTH = "auth.token.length";

	public static final String AUTH_TOKEN_SHARED_SECRET =
		"auth.token.shared.secret";

	public static final String AUTH_VERIFIER = "auth.verifier.";

	public static final String AUTH_VERIFIER_PIPELINE =
		"auth.verifier.pipeline";

	public static final String AUTO_DEPLOY_CUSTOM_PORTLET_XML =
		"auto.deploy.custom.portlet.xml";

	public static final String AUTO_DEPLOY_DEPLOY_DIR =
		"auto.deploy.deploy.dir";

	public static final String AUTO_DEPLOY_ENABLED = "auto.deploy.enabled";

	public static final String AUTO_DEPLOY_INTERVAL = "auto.deploy.interval";

	public static final String AUTO_DEPLOY_LISTENERS = "auto.deploy.listeners";

	public static final String AUTO_DEPLOY_TOMCAT_CONF_DIR =
		"auto.deploy.tomcat.conf.dir";

	public static final String AUTO_LOGIN_HOOKS = "auto.login.hooks";

	public static final String AUTO_LOGIN_IGNORE_HOSTS =
		"auto.login.ignore.hosts";

	public static final String AUTO_LOGIN_IGNORE_PATHS =
		"auto.login.ignore.paths";

	public static final String BASIC_AUTH_PASSWORD_REQUIRED =
		"basic.auth.password.required";

	public static final String BLOGS_DISPLAY_TEMPLATES_CONFIG =
		"blogs.display.templates.config";

	public static final String BLOGS_EMAIL_ENTRY_ADDED_BODY =
		"blogs.email.entry.added.body";

	public static final String BLOGS_EMAIL_ENTRY_ADDED_ENABLED =
		"blogs.email.entry.added.enabled";

	public static final String BLOGS_EMAIL_ENTRY_ADDED_SUBJECT =
		"blogs.email.entry.added.subject";

	public static final String BLOGS_EMAIL_ENTRY_UPDATED_BODY =
		"blogs.email.entry.updated.body";

	public static final String BLOGS_EMAIL_ENTRY_UPDATED_ENABLED =
		"blogs.email.entry.updated.enabled";

	public static final String BLOGS_EMAIL_ENTRY_UPDATED_SUBJECT =
		"blogs.email.entry.updated.subject";

	public static final String BLOGS_EMAIL_FROM_ADDRESS =
		"blogs.email.from.address";

	public static final String BLOGS_EMAIL_FROM_NAME = "blogs.email.from.name";

	public static final String BLOGS_ENTRY_COMMENTS_ENABLED =
		"blogs.entry.comments.enabled";

	public static final String BLOGS_ENTRY_PAGE_DELTA_VALUES =
		"blogs.entry.page.delta.values";

	public static final String
		BLOGS_ENTRY_PREVIOUS_AND_NEXT_NAVIGATION_ENABLED =
			"blogs.entry.previous.and.next.navigation.enabled";

	public static final String BLOGS_LINKBACK_EXCERPT_LENGTH =
		"blogs.linkback.excerpt.length";

	public static final String BLOGS_PAGE_ABSTRACT_LENGTH =
		"blogs.page.abstract.length";

	public static final String BLOGS_PING_GOOGLE_ENABLED =
		"blogs.ping.google.enabled";

	public static final String BLOGS_PINGBACK_ENABLED =
		"blogs.pingback.enabled";

	public static final String BLOGS_PUBLISH_TO_LIVE_BY_DEFAULT =
		"blogs.publish.to.live.by.default";

	public static final String BLOGS_RSS_ABSTRACT_LENGTH =
		"blogs.rss.abstract.length";

	public static final String BLOGS_TRACKBACK_ENABLED =
		"blogs.trackback.enabled";

	public static final String BROWSER_CACHE_DISABLED =
		"browser.cache.disabled";

	public static final String BROWSER_CACHE_SIGNED_IN_DISABLED =
		"browser.cache.signed.in.disabled";

	public static final String BROWSER_LAUNCHER_URL = "browser.launcher.url";

	public static final String BUFFERED_INCREMENT_STANDBY_QUEUE_THRESHOLD =
		"buffered.increment.standby.queue.threshold";

	public static final String BUFFERED_INCREMENT_STANDBY_TIME_UPPER_LIMIT =
		"buffered.increment.standby.time.upper.limit";

	public static final String BUFFERED_INCREMENT_THREADPOOL_KEEP_ALIVE_TIME =
		"buffered.increment.threadpool.keep.alive.time";

	public static final String BUFFERED_INCREMENT_THREADPOOL_MAX_SIZE =
		"buffered.increment.threadpool.max.size";

	public static final String CACHE_CONTENT_THRESHOLD_SIZE =
		"cache.content.threshold.size";

	public static final String CACHE_FILTER_INCLUDE_USER_AGENT =
		"cache.filter.include.user.agent";

	public static final String CALENDAR_EMAIL_EVENT_REMINDER_BODY =
		"calendar.email.event.reminder.body";

	public static final String CALENDAR_EMAIL_EVENT_REMINDER_ENABLED =
		"calendar.email.event.reminder.enabled";

	public static final String CALENDAR_EMAIL_EVENT_REMINDER_SUBJECT =
		"calendar.email.event.reminder.subject";

	public static final String CALENDAR_EMAIL_FROM_ADDRESS =
		"calendar.email.from.address";

	public static final String CALENDAR_EMAIL_FROM_NAME =
		"calendar.email.from.name";

	public static final String CALENDAR_EVENT_CHECK_INTERVAL =
		"calendar.event.check.interval";

	public static final String CALENDAR_EVENT_COMMENTS_ENABLED =
		"calendar.event.comments.enabled";

	public static final String CALENDAR_EVENT_RATINGS_ENABLED =
		"calendar.event.ratings.enabled";

	public static final String CALENDAR_EVENT_TYPES = "calendar.event.types";

	public static final String CDN_DYNAMIC_RESOURCES_ENABLED =
		"cdn.dynamic.resources.enabled";

	public static final String CDN_HOST_HTTP = "cdn.host.http";

	public static final String CDN_HOST_HTTPS = "cdn.host.https";

	public static final String CHANGE_TRACKING_DELETION_PROTECTION_ENABLED =
		"change.tracking.deletion.protection.enabled";

	public static final String CLUSTER_LINK_AUTH_VALUE =
		"cluster.link.auth.value";

	public static final String CLUSTER_LINK_AUTODETECT_ADDRESS =
		"cluster.link.autodetect.address";

	public static final String CLUSTER_LINK_CHANNEL_LOGIC_NAME_CONTROL =
		"cluster.link.channel.logic.name.control";

	public static final String CLUSTER_LINK_CHANNEL_LOGIC_NAME_TRANSPORT =
		"cluster.link.channel.logic.name.transport";

	public static final String CLUSTER_LINK_CHANNEL_NAME_CONTROL =
		"cluster.link.channel.name.control";

	public static final String CLUSTER_LINK_CHANNEL_NAME_TRANSPORT =
		"cluster.link.channel.name.transport";

	public static final String CLUSTER_LINK_CHANNEL_PROPERTIES_CONTROL =
		"cluster.link.channel.properties.control";

	public static final String CLUSTER_LINK_CHANNEL_PROPERTIES_TRANSPORT =
		"cluster.link.channel.properties.transport";

	public static final String CLUSTER_LINK_CHANNEL_SYSTEM_PROPERTIES =
		"cluster.link.channel.system.properties";

	public static final String CLUSTER_LINK_ENABLED = "cluster.link.enabled";

	public static final String CLUSTERABLE_ADVICE_CALL_MASTER_TIMEOUT =
		"clusterable.advice.call.master.timeout";

	public static final String COMBO_ALLOWED_FILE_EXTENSIONS =
		"combo.allowed.file.extensions";

	public static final String COMBO_CHECK_TIMESTAMP = "combo.check.timestamp";

	public static final String COMBO_CHECK_TIMESTAMP_INTERVAL =
		"combo.check.timestamp.interval";

	public static final String COMBO_MAX_FILES = "combo.max.files";

	public static final String COMMUNITIES_CONTROL_PANEL_MEMBERS_VISIBLE =
		"communities.control.panel.members.visible";

	public static final String COMPANY_DEFAULT_ADD_DEFAULT_ADMIN_USER =
		"company.default.add.default.admin.user";

	public static final String COMPANY_DEFAULT_HOME_URL =
		"company.default.home.url";

	public static final String COMPANY_DEFAULT_LOCALE =
		"company.default.locale";

	public static final String COMPANY_DEFAULT_NAME = "company.default.name";

	public static final String COMPANY_DEFAULT_TIME_ZONE =
		"company.default.time.zone";

	public static final String COMPANY_DEFAULT_VIRTUAL_HOST_MAIL_DOMAIN =
		"company.default.virtual.host.mail.domain";

	public static final String COMPANY_DEFAULT_VIRTUAL_HOST_NAME =
		"company.default.virtual.host.name";

	public static final String COMPANY_DEFAULT_VIRTUAL_HOST_SYNC_ON_STARTUP =
		"company.default.virtual.host.sync.on.startup";

	public static final String COMPANY_DEFAULT_WEB_ID =
		"company.default.web.id";

	public static final String COMPANY_ENCRYPTION_ALGORITHM =
		"company.encryption.algorithm";

	public static final String COMPANY_ENCRYPTION_KEY_SIZE =
		"company.encryption.key.size";

	public static final String COMPANY_LOG_ENABLED = "company.log.enabled";

	public static final String COMPANY_LOGIN_PREPOPULATE_DOMAIN =
		"company.login.prepopulate.domain";

	public static final String COMPANY_PREDICTABLE_COMPANY_IDS_ENABLED =
		"company.predictable.company.ids.enabled";

	public static final String COMPANY_SECURITY_AUTH_TYPE =
		"company.security.auth.type";

	public static final String COMPANY_SECURITY_AUTO_LOGIN =
		"company.security.auto.login";

	public static final String COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE =
		"company.security.auto.login.max.age";

	public static final String COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE =
		"company.security.login.form.autocomplete";

	public static final String
		COMPANY_SECURITY_PASSWORD_REMINDER_QUERY_FORM_AUTOCOMPLETE =
			"company.security.password.reminder.query.form.autocomplete";

	public static final String COMPANY_SECURITY_SEND_PASSWORD_RESET_LINK =
		"company.security.send.password.reset.link";

	public static final String COMPANY_SECURITY_SITE_LOGO =
		"company.security.site.logo";

	public static final String COMPANY_SECURITY_STRANGERS =
		"company.security.strangers";

	public static final String COMPANY_SECURITY_STRANGERS_URL =
		"company.security.strangers.url";

	public static final String COMPANY_SECURITY_STRANGERS_VERIFY =
		"company.security.strangers.verify";

	public static final String COMPANY_SECURITY_STRANGERS_WITH_MX =
		"company.security.strangers.with.mx";

	public static final String COMPANY_SECURITY_UPDATE_PASSWORD_REQUIRED =
		"company.security.update.password.required";

	public static final String COMPANY_SETTINGS_FORM_AUTHENTICATION =
		"company.settings.form.authentication";

	public static final String COMPANY_SETTINGS_FORM_CONFIGURATION =
		"company.settings.form.configuration";

	public static final String COMPANY_SETTINGS_FORM_IDENTIFICATION =
		"company.settings.form.identification";

	public static final String COMPANY_SETTINGS_FORM_MISCELLANEOUS =
		"company.settings.form.miscellaneous";

	public static final String COMPANY_SETTINGS_FORM_SOCIAL =
		"company.settings.form.social";

	public static final String CONTROL_PANEL_DEFAULT_ENTRY_CLASS =
		"control.panel.default.entry.class";

	public static final String CONTROL_PANEL_LAYOUT_FRIENDLY_URL =
		"control.panel.layout.friendly.url";

	public static final String CONTROL_PANEL_LAYOUT_NAME =
		"control.panel.layout.name";

	public static final String CONTROL_PANEL_LAYOUT_REGULAR_THEME_ID =
		"control.panel.layout.regular.theme.id";

	public static final String CORS_DISABLE_AUTHORIZATION_CONTEXT_CHECK =
		"cors.disable.authorization.context.check";

	public static final String COUNTER_DATA_CENTER_COUNT =
		"counter.data.center.count";

	public static final String COUNTER_DATA_CENTER_DEPLOYMENT_ID =
		"counter.data.center.deployment.id";

	public static final String COUNTER_INCREMENT = "counter.increment";

	public static final String COUNTER_INCREMENT_PREFIX = "counter.increment.";

	public static final String CUSTOM_SQL_AUTO_ESCAPE_WILDCARDS_ENABLED =
		"custom.sql.auto.escape.wildcards.enabled";

	public static final String CUSTOM_SQL_FUNCTION_ISNOTNULL =
		"custom.sql.function.isnotnull";

	public static final String CUSTOM_SQL_FUNCTION_ISNULL =
		"custom.sql.function.isnull";

	public static final String DATA_LIMIT_DL_STORAGE_MAX_SIZE =
		"data.limit.dl.storage.max.size";

	public static final String DATA_LIMIT_MAIL_MESSAGE_MAX_COUNT =
		"data.limit.mail.message.max.count";

	public static final String DATA_LIMIT_MAIL_MESSAGE_MAX_PERIOD =
		"data.limit.mail.message.max.period";

	public static final String DATA_LIMIT_SITE_MAX_COUNT =
		"data.limit.site.max.count";

	public static final String DATABASE_IN_MAX_PARAMETERS =
		"database.in.max.parameters";

	public static final String DATABASE_INDEXES_UPDATE_ON_STARTUP =
		"database.indexes.update.on.startup";

	public static final String DATABASE_MAX_PARAMETERS =
		"database.max.parameters";

	public static final String DATABASE_MYSQL_ENGINE = "database.mysql.engine";

	public static final String DATABASE_ORDER_BY_MAX_COLUMNS =
		"database.order.by.max.columns";

	public static final String DATABASE_STRING_INDEX_MAX_LENGTH =
		"database.string.index.max.length";

	public static final String DEFAULT_ADMIN_EMAIL_ADDRESS_PREFIX =
		"default.admin.email.address.prefix";

	public static final String DEFAULT_ADMIN_FIRST_NAME =
		"default.admin.first.name";

	public static final String DEFAULT_ADMIN_LAST_NAME =
		"default.admin.last.name";

	public static final String DEFAULT_ADMIN_MIDDLE_NAME =
		"default.admin.middle.name";

	public static final String DEFAULT_ADMIN_PASSWORD =
		"default.admin.password";

	public static final String DEFAULT_ADMIN_SCREEN_NAME =
		"default.admin.screen.name";

	public static final String DEFAULT_GUEST_PUBLIC_LAYOUT_FRIENDLY_URL =
		"default.guest.public.layout.friendly.url";

	public static final String DEFAULT_GUEST_PUBLIC_LAYOUT_NAME =
		"default.guest.public.layout.name";

	public static final String
		DEFAULT_GUEST_PUBLIC_LAYOUT_REGULAR_COLOR_SCHEME_ID =
			"default.guest.public.layout.regular.color.scheme.id";

	public static final String DEFAULT_GUEST_PUBLIC_LAYOUT_REGULAR_THEME_ID =
		"default.guest.public.layout.regular.theme.id";

	public static final String DEFAULT_GUEST_PUBLIC_LAYOUT_TEMPLATE_ID =
		"default.guest.public.layout.template.id";

	public static final String DEFAULT_GUEST_PUBLIC_LAYOUTS_LAR =
		"default.guest.public.layouts.lar";

	public static final String DEFAULT_LANDING_PAGE_PATH =
		"default.landing.page.path";

	public static final String DEFAULT_LAYOUT_TEMPLATE_ID =
		"default.layout.template.id";

	public static final String DEFAULT_LIFERAY_HOME = "default.liferay.home";

	public static final String DEFAULT_LOGOUT_PAGE_PATH =
		"default.logout.page.path";

	public static final String DEFAULT_PORTLET_DECORATOR_CSS_CLASS =
		"default.portlet.decorator.css.class";

	public static final String DEFAULT_PORTLET_DECORATOR_ID =
		"default.portlet.decorator.id";

	public static final String DEFAULT_REGULAR_COLOR_SCHEME_ID =
		"default.regular.color.scheme.id";

	public static final String DEFAULT_REGULAR_THEME_ID =
		"default.regular.theme.id";

	public static final String DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL =
		"default.user.private.layout.friendly.url";

	public static final String DEFAULT_USER_PRIVATE_LAYOUT_NAME =
		"default.user.private.layout.name";

	public static final String DEFAULT_USER_PRIVATE_LAYOUT_PREFIX =
		"default.user.private.layout.";

	public static final String
		DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_COLOR_SCHEME_ID =
			"default.user.private.layout.regular.color.scheme.id";

	public static final String DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_THEME_ID =
		"default.user.private.layout.regular.theme.id";

	public static final String DEFAULT_USER_PRIVATE_LAYOUT_TEMPLATE_ID =
		"default.user.private.layout.template.id";

	public static final String DEFAULT_USER_PRIVATE_LAYOUTS_LAR =
		"default.user.private.layouts.lar";

	public static final String DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL =
		"default.user.public.layout.friendly.url";

	public static final String DEFAULT_USER_PUBLIC_LAYOUT_NAME =
		"default.user.public.layout.name";

	public static final String DEFAULT_USER_PUBLIC_LAYOUT_PREFIX =
		"default.user.public.layout.";

	public static final String
		DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_COLOR_SCHEME_ID =
			"default.user.public.layout.regular.color.scheme.id";

	public static final String DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_THEME_ID =
		"default.user.public.layout.regular.theme.id";

	public static final String DEFAULT_USER_PUBLIC_LAYOUT_TEMPLATE_ID =
		"default.user.public.layout.template.id";

	public static final String DEFAULT_USER_PUBLIC_LAYOUTS_LAR =
		"default.user.public.layouts.lar";

	public static final String DEPENDENCY_MANAGER_SYNC_TIMEOUT =
		"dependency.manager.sync.timeout";

	public static final String DEPENDENCY_MANAGER_THREAD_POOL_ENABLED =
		"dependency.manager.thread.pool.enabled";

	public static final String DIRECT_SERVLET_CONTEXT_ENABLED =
		"direct.servlet.context.enabled";

	public static final String DIRECT_SERVLET_CONTEXT_RELOAD =
		"direct.servlet.context.reload";

	public static final String DISCUSSION_COMMENTS_ALLOWED_CONTENT =
		"discussion.comments.allowed.content";

	public static final String DISCUSSION_COMMENTS_DELTA_VALUE =
		"discussion.comments.delta.value";

	public static final String DISCUSSION_COMMENTS_FORMAT =
		"discussion.comments.format";

	public static final String DISCUSSION_MAX_COMMENTS =
		"discussion.max.comments";

	public static final String DL_ACTIONS_VISIBLE = "dl.actions.visible";

	public static final String DL_CHAR_BLACKLIST = "dl.char.blacklist";

	public static final String DL_CHAR_LAST_BLACKLIST =
		"dl.char.last.blacklist";

	public static final String DL_COMMENT_RATINGS_ENABLED =
		"dl.comment.ratings.enabled";

	public static final String DL_COMPARABLE_FILE_EXTENSIONS =
		"dl.comparable.file.extensions";

	public static final String DL_DEFAULT_DISPLAY_VIEW =
		"dl.default.display.view";

	public static final String DL_DISPLAY_VIEWS = "dl.display.views";

	public static final String DL_EMAIL_FILE_ENTRY_ADDED_BODY =
		"dl.email.file.entry.added.body";

	public static final String DL_EMAIL_FILE_ENTRY_ADDED_ENABLED =
		"dl.email.file.entry.added.enabled";

	public static final String DL_EMAIL_FILE_ENTRY_ADDED_SUBJECT =
		"dl.email.file.entry.added.subject";

	public static final String DL_EMAIL_FILE_ENTRY_EXPIRED_BODY =
		"dl.email.file.entry.expired.body";

	public static final String DL_EMAIL_FILE_ENTRY_EXPIRED_ENABLED =
		"dl.email.file.entry.expired.enabled";

	public static final String DL_EMAIL_FILE_ENTRY_EXPIRED_SUBJECT =
		"dl.email.file.entry.expired.subject";

	public static final String DL_EMAIL_FILE_ENTRY_REVIEW_BODY =
		"dl.email.file.entry.review.body";

	public static final String DL_EMAIL_FILE_ENTRY_REVIEW_ENABLED =
		"dl.email.file.entry.review.enabled";

	public static final String DL_EMAIL_FILE_ENTRY_REVIEW_SUBJECT =
		"dl.email.file.entry.review.subject";

	public static final String DL_EMAIL_FILE_ENTRY_UPDATED_BODY =
		"dl.email.file.entry.updated.body";

	public static final String DL_EMAIL_FILE_ENTRY_UPDATED_ENABLED =
		"dl.email.file.entry.updated.enabled";

	public static final String DL_EMAIL_FILE_ENTRY_UPDATED_SUBJECT =
		"dl.email.file.entry.updated.subject";

	public static final String DL_EMAIL_FROM_ADDRESS = "dl.email.from.address";

	public static final String DL_EMAIL_FROM_NAME = "dl.email.from.name";

	public static final String DL_ENTRY_COLUMNS = "dl.entry.columns";

	public static final String DL_FILE_ENTRY_COLUMNS = "dl.file.entry.columns";

	public static final String DL_FILE_ENTRY_COMMENTS_ENABLED =
		"dl.file.entry.comments.enabled";

	public static final String DL_FILE_ENTRY_CONVERSIONS_ENABLED =
		"dl.file.entry.conversions.enabled";

	public static final String DL_FILE_ENTRY_DRAFTS_ENABLED =
		"dl.file.entry.drafts.enabled";

	public static final String DL_FILE_ENTRY_IG_THUMBNAIL_GENERATION =
		"dl.file.entry.ig.thumbnail.generation";

	public static final String DL_FILE_ENTRY_LOCK_POLICY =
		"dl.file.entry.lock.policy";

	public static final String DL_FILE_ENTRY_PREVIEW_AUDIO =
		"dl.file.entry.preview.audio.";

	public static final String DL_FILE_ENTRY_PREVIEW_AUDIO_BIT_RATE =
		"dl.file.entry.preview.audio.bit.rate";

	public static final String DL_FILE_ENTRY_PREVIEW_AUDIO_CONTAINERS =
		"dl.file.entry.preview.audio.containers";

	public static final String DL_FILE_ENTRY_PREVIEW_AUDIO_MIME_TYPES =
		"dl.file.entry.preview.audio.mime.types";

	public static final String DL_FILE_ENTRY_PREVIEW_AUDIO_SAMPLE_RATE =
		"dl.file.entry.preview.audio.sample.rate";

	public static final String DL_FILE_ENTRY_PREVIEW_DOCUMENT_DPI =
		"dl.file.entry.preview.document.dpi";

	public static final String DL_FILE_ENTRY_PREVIEW_DOCUMENT_MAX_HEIGHT =
		"dl.file.entry.preview.document.max.height";

	public static final String DL_FILE_ENTRY_PREVIEW_DOCUMENT_MAX_WIDTH =
		"dl.file.entry.preview.document.max.width";

	public static final String DL_FILE_ENTRY_PREVIEW_ENABLED =
		"dl.file.entry.preview.enabled";

	public static final String DL_FILE_ENTRY_PREVIEW_FORK_PROCESS_ENABLED =
		"dl.file.entry.preview.fork.process.enabled";

	public static final String DL_FILE_ENTRY_PREVIEW_FORK_PROCESS_JVM_OPTIONS =
		"dl.file.entry.preview.fork.process.jvm.options";

	public static final String
		DL_FILE_ENTRY_PREVIEW_GENERATION_DECRYPT_PASSWORDS_PDFBOX =
			"dl.file.entry.preview.generation.decrypt.passwords.pdfbox";

	public static final String
		DL_FILE_ENTRY_PREVIEW_GENERATION_TIMEOUT_GHOSTSCRIPT =
			"dl.file.entry.preview.generation.timeout.ghostscript";

	public static final String DL_FILE_ENTRY_PREVIEW_GENERATION_TIMEOUT_PDFBOX =
		"dl.file.entry.preview.generation.timeout.pdfbox";

	public static final String DL_FILE_ENTRY_PREVIEW_IMAGE_MIME_TYPES =
		"dl.file.entry.preview.image.mime.types";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO =
		"dl.file.entry.preview.video.";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO_BIT_RATE =
		"dl.file.entry.preview.video.bit.rate";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO_CONTAINERS =
		"dl.file.entry.preview.video.containers";

	public static final String
		DL_FILE_ENTRY_PREVIEW_VIDEO_FRAME_RATE_DENOMINATOR =
			"dl.file.entry.preview.video.frame.rate.denominator";

	public static final String
		DL_FILE_ENTRY_PREVIEW_VIDEO_FRAME_RATE_NUMERATOR =
			"dl.file.entry.preview.video.frame.rate.numerator";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO_HEIGHT =
		"dl.file.entry.preview.video.height";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO_MIME_TYPES =
		"dl.file.entry.preview.video.mime.types";

	public static final String DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH =
		"dl.file.entry.preview.video.width";

	public static final String
		DL_FILE_ENTRY_RAW_METADATA_PROCESSOR_EXCLUDED_MIME_TYPES =
			"dl.file.entry.raw.metadata.processor.excluded.mime.types";

	public static final String DL_FILE_ENTRY_THUMBNAIL_CUSTOM_1_MAX_HEIGHT =
		"dl.file.entry.thumbnail.custom1.max.height";

	public static final String DL_FILE_ENTRY_THUMBNAIL_CUSTOM_1_MAX_WIDTH =
		"dl.file.entry.thumbnail.custom1.max.width";

	public static final String DL_FILE_ENTRY_THUMBNAIL_CUSTOM_2_MAX_HEIGHT =
		"dl.file.entry.thumbnail.custom2.max.height";

	public static final String DL_FILE_ENTRY_THUMBNAIL_CUSTOM_2_MAX_WIDTH =
		"dl.file.entry.thumbnail.custom2.max.width";

	public static final String DL_FILE_ENTRY_THUMBNAIL_ENABLED =
		"dl.file.entry.thumbnail.enabled";

	public static final String DL_FILE_ENTRY_THUMBNAIL_MAX_HEIGHT =
		"dl.file.entry.thumbnail.max.height";

	public static final String DL_FILE_ENTRY_THUMBNAIL_MAX_WIDTH =
		"dl.file.entry.thumbnail.max.width";

	public static final String DL_FILE_EXTENSIONS_STRICT_CHECK =
		"dl.file.extensions.strict.check";

	public static final String DL_FILE_GENERIC_EXTENSIONS =
		"dl.file.generic.extensions";

	public static final String DL_FILE_GENERIC_NAMES = "dl.file.generic.names";

	public static final String DL_FILE_ICONS = "dl.file.icons";

	public static final String DL_FILE_INDEXING_IGNORE_EXTENSIONS =
		"dl.file.indexing.ignore.extensions";

	public static final String DL_FILE_INDEXING_MAX_SIZE =
		"dl.file.indexing.max.size";

	public static final String DL_FOLDER_COLUMNS = "dl.folder.columns";

	public static final String DL_FOLDERS_SEARCH_VISIBLE =
		"dl.folders.search.visible";

	public static final String DL_NAME_BLACKLIST = "dl.name.blacklist";

	public static final String DL_PUBLISH_TO_LIVE_BY_DEFAULT =
		"dl.publish.to.live.by.default";

	public static final String DL_RATINGS_ENABLED = "dl.ratings.enabled";

	public static final String DL_RELATED_ASSETS_ENABLED =
		"dl.related.assets.enabled";

	public static final String DL_REPOSITORY_GUEST_PASSWORD =
		"dl.repository.guest.password";

	public static final String DL_REPOSITORY_GUEST_USERNAME =
		"dl.repository.guest.username";

	public static final String DL_REPOSITORY_IMPL = "dl.repository.impl";

	public static final String DL_SHOW_HIDDEN_MOUNT_FOLDERS =
		"dl.show.hidden.mount.folders";

	public static final String DL_STORE_ANTIVIRUS_ENABLED =
		"dl.store.antivirus.enabled";

	public static final String DL_STORE_FILE_IMPL_SAFE_FILE_NAME_2_AMPERSAND =
		"dl.store.file.impl.safe.file.name.2.ampersand";

	public static final String
		DL_STORE_FILE_IMPL_SAFE_FILE_NAME_2_CLOSE_PARENTHESIS =
			"dl.store.file.impl.safe.file.name.2.close.parenthesis";

	public static final String
		DL_STORE_FILE_IMPL_SAFE_FILE_NAME_2_OPEN_PARENTHESIS =
			"dl.store.file.impl.safe.file.name.2.open.parenthesis";

	public static final String DL_STORE_FILE_IMPL_SAFE_FILE_NAME_2_SEMICOLON =
		"dl.store.file.impl.safe.file.name.2.semicolon";

	public static final String DL_STORE_IMPL = "dl.store.impl";

	public static final String DL_SUBFOLDERS_VISIBLE = "dl.subfolders.visible";

	public static final String DL_WEBDAV_SUBSTITUTION_CHAR =
		"dl.webdav.substitution.char";

	public static final String DNS_SECURITY_ADDRESS_TIMEOUT_SECONDS =
		"dns.security.address.timeout.seconds";

	public static final String DNS_SECURITY_THREAD_LIMIT =
		"dns.security.thread.limit";

	public static final String DNS_SECURITY_THREAD_QUEUE_LIMIT =
		"dns.security.thread.queue.limit";

	public static final String EDITOR_WYSIWYG_DEFAULT =
		"editor.wysiwyg.default";

	public static final String EHCACHE_MULTI_VM_CONFIG_LOCATION =
		"ehcache.multi.vm.config.location";

	public static final String EHCACHE_PORTAL_CACHE_MANAGER_JMX_ENABLED =
		"ehcache.portal.cache.manager.jmx.enabled";

	public static final String EHCACHE_REPLICATOR_PROPERTIES =
		"ehcache.replicator.properties";

	public static final String EHCACHE_REPLICATOR_PROPERTIES_DEFAULT =
		"ehcache.replicator.properties.default";

	public static final String EHCACHE_SINGLE_VM_CONFIG_LOCATION =
		"ehcache.single.vm.config.location";

	public static final String ENTERPRISE_PRODUCT_NOTIFICATION_ENABLED =
		"enterprise.product.notification.enabled";

	public static final String ETAG_RESPONSE_SIZE_MAX =
		"etag.response.size.max";

	public static final String FACEBOOK_CONNECT_APP_ID =
		"facebook.connect.app.id";

	public static final String FACEBOOK_CONNECT_APP_SECRET =
		"facebook.connect.app.secret";

	public static final String FACEBOOK_CONNECT_AUTH_ENABLED =
		"facebook.connect.auth.enabled";

	public static final String FACEBOOK_CONNECT_GRAPH_URL =
		"facebook.connect.graph.url";

	public static final String FACEBOOK_CONNECT_OAUTH_AUTH_URL =
		"facebook.connect.oauth.auth.url";

	public static final String FACEBOOK_CONNECT_OAUTH_REDIRECT_URL =
		"facebook.connect.oauth.redirect.url";

	public static final String FACEBOOK_CONNECT_OAUTH_TOKEN_URL =
		"facebook.connect.oauth.token.url";

	public static final String FACEBOOK_CONNECT_VERIFIED_ACCOUNT_REQUIRED =
		"facebook.connect.verified.account.required";

	public static final String FIELD_EDITABLE_DOMAINS =
		"field.editable.domains";

	public static final String FIELD_EDITABLE_ROLES = "field.editable.roles";

	public static final String FIELD_EDITABLE_USER_TYPES =
		"field.editable.user.types";

	public static final String
		FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_CONTACT_BIRTHDAY =
			"field.enable.com.liferay.portal.kernel.model.Contact.birthday";

	public static final String
		FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_CONTACT_MALE =
			"field.enable.com.liferay.portal.kernel.model.Contact.male";

	public static final String
		FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_LAYOUT_JAVASCRIPT =
			"field.enable.com.liferay.portal.kernel.model.Layout.javascript";

	public static final String
		FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_LAYOUTSET_JAVASCRIPT =
			"field.enable.com.liferay.portal.kernel.model.LayoutSet.javascript";

	public static final String
		FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_ORGANIZATION_STATUS =
			"field.enable.com.liferay.portal.kernel.model.Organization.status";

	public static final String FULL_PAGE_DISPLAYABLE = "full.page.displayable";

	public static final String GLOBAL_SHUTDOWN_EVENTS =
		"global.shutdown.events";

	public static final String GLOBAL_STARTUP_EVENTS = "global.startup.events";

	public static final String GOOGLE_GADGET_SERVLET_MAPPING =
		"google.gadget.servlet.mapping";

	public static final String GROUPS_COMPLEX_SQL_CLASS_NAMES =
		"groups.complex.sql.class.names";

	public static final String GZIP_COMPRESSION_LEVEL =
		"gzip.compression.level";

	public static final String HEALTH_CHECK_DATA_SOURCE_ENABLED =
		"health.check.data.source.enabled";

	public static final String HIBERNATE_CONFIGS = "hibernate.configs";

	public static final String HIBERNATE_DIALECT = "hibernate.dialect";

	public static final String HIBERNATE_GENERATE_STATISTICS =
		"hibernate.generate_statistics";

	public static final String HIBERNATE_HBM_JAXB_CACHE =
		"hibernate.hbm.jaxb.cache";

	public static final String HIBERNATE_JDBC_BATCH_SIZE =
		"hibernate.jdbc.batch_size";

	public static final String HIBERNATE_JDBC_USE_STREAMS_FOR_BINARY =
		"hibernate.jdbc.use_streams_for_binary";

	public static final String HOT_DEPLOY_DEPENDENCY_MANAGEMENT_ENABLED =
		"hot.deploy.dependency.management.enabled";

	public static final String HOT_DEPLOY_LISTENERS = "hot.deploy.listeners";

	public static final String HTTP_HEADER_VERSION_VERBOSITY =
		"http.header.version.verbosity";

	public static final String IFRAME_PASSWORD_PASSWORD_TOKEN_ROLE =
		"iframe.password.token.role";

	public static final String IMAGE_AUTO_SCALE = "image.auto.scale";

	public static final String IMAGE_DEFAULT_COMPANY_LOGO =
		"image.default.company.logo";

	public static final String IMAGE_DEFAULT_ORGANIZATION_LOGO =
		"image.default.organization.logo";

	public static final String IMAGE_DEFAULT_SPACER = "image.default.spacer";

	public static final String IMAGE_DEFAULT_USER_FEMALE_PORTRAIT =
		"image.default.user.female.portrait";

	public static final String IMAGE_DEFAULT_USER_MALE_PORTRAIT =
		"image.default.user.male.portrait";

	public static final String IMAGE_DEFAULT_USER_PORTRAIT =
		"image.default.user.portrait";

	public static final String IMAGE_IO_USE_DISK_CACHE =
		"image.io.use.disk.cache";

	public static final String IMAGE_TOOL_IMAGE_MAX_HEIGHT =
		"image.tool.image.max.height";

	public static final String IMAGE_TOOL_IMAGE_MAX_WIDTH =
		"image.tool.image.max.width";

	public static final String IMAGEMAGICK_ENABLED = "imagemagick.enabled";

	public static final String IMAGEMAGICK_GLOBAL_SEARCH_PATH =
		"imagemagick.global.search.path";

	public static final String IMAGEMAGICK_RESOURCE_LIMIT =
		"imagemagick.resource.limit.";

	public static final String INDEX_DATE_FORMAT_PATTERN =
		"index.date.format.pattern";

	public static final String INDEX_ON_STARTUP = "index.on.startup";

	public static final String INDEX_ON_STARTUP_DELAY =
		"index.on.startup.delay";

	public static final String
		INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_ENABLED =
			"index.search.collated.spell.check.result.enabled";

	public static final String
		INDEX_SEARCH_COLLATED_SPELL_CHECK_RESULT_SCORES_THRESHOLD =
			"index.search.collated.spell.check.result.scores.threshold";

	public static final String INDEX_SEARCH_HIGHLIGHT_FRAGMENT_SIZE =
		"index.search.highlight.fragment.size";

	public static final String INDEX_SEARCH_HIGHLIGHT_REQUIRE_FIELD_MATCH =
		"index.search.highlight.require.field.match";

	public static final String INDEX_SEARCH_HIGHLIGHT_SNIPPET_SIZE =
		"index.search.highlight.snippet.size";

	public static final String INDEX_SEARCH_LIMIT = "index.search.limit";

	public static final String INDEX_SEARCH_QUERY_INDEXING_ENABLED =
		"index.search.query.indexing.enabled";

	public static final String INDEX_SEARCH_QUERY_INDEXING_THRESHOLD =
		"index.search.query.indexing.threshold";

	public static final String INDEX_SEARCH_QUERY_SUGGESTION_DICTIONARY =
		"index.search.query.suggestion.dictionary";

	public static final String INDEX_SEARCH_QUERY_SUGGESTION_ENABLED =
		"index.search.query.suggestion.enabled";

	public static final String INDEX_SEARCH_QUERY_SUGGESTION_MAX =
		"index.search.query.suggestion.max";

	public static final String INDEX_SEARCH_QUERY_SUGGESTION_SCORES_THRESHOLD =
		"index.search.query.suggestion.scores.threshold";

	public static final String INDEX_SEARCH_SCORING_ENABLED =
		"index.search.scoring.enabled";

	public static final String INDEX_SEARCH_SPELL_CHECKER_DICTIONARY =
		"index.search.spell.checker.dictionary";

	public static final String INDEX_SEARCH_SPELL_CHECKER_SUPPORTED_LOCALES =
		"index.search.spell.checker.supported.locales";

	public static final String INDEX_SORTABLE_TEXT_FIELDS =
		"index.sortable.text.fields";

	public static final String INDEX_SORTABLE_TEXT_FIELDS_TRUNCATED_LENGTH =
		"index.sortable.text.fields.truncated.length";

	public static final String INDEXER_ENABLED = "indexer.enabled";

	public static final String INITIAL_SYSTEM_CHECK_ENABLED =
		"initial.system.check.enabled";

	public static final String
		INVOKER_FILTER_CHAIN_CACHE_QUERY_STRING_IGNORED_KEYS =
			"invoker.filter.chain.cache.query.string.ignored.keys";

	public static final String
		INVOKER_FILTER_CHAIN_CACHE_SKIP_QUERY_STRING_URIS =
			"invoker.filter.chain.cache.skip.query.string.uris";

	public static final String INVOKER_FILTER_CHAIN_ENABLED =
		"invoker.filter.chain.cache.enabled";

	public static final String INVOKER_FILTER_URI_MAX_LENGTH =
		"invoker.filter.uri.max.length";

	public static final String JAVASCRIPT_BAREBONE_ENABLED =
		"javascript.barebone.enabled";

	public static final String JAVASCRIPT_BAREBONE_FILES =
		"javascript.barebone.files";

	public static final String JAVASCRIPT_BUNDLE_DEPENDENCIES =
		"javascript.bundle.dependencies";

	public static final String JAVASCRIPT_BUNDLE_DIR = "javascript.bundle.dir";

	public static final String JAVASCRIPT_BUNDLE_IDS = "javascript.bundle.ids";

	public static final String JAVASCRIPT_EVERYTHING_FILES =
		"javascript.everything.files";

	public static final String JAVASCRIPT_FAST_LOAD = "javascript.fast.load";

	public static final String JAVASCRIPT_LOG_ENABLED =
		"javascript.log.enabled";

	public static final String JAVASCRIPT_SINGLE_PAGE_APPLICATION_ENABLED =
		"javascript.single.page.application.enabled";

	public static final String JAVASCRIPT_SINGLE_PAGE_APPLICATION_TIMEOUT =
		"javascript.single.page.application.timeout";

	public static final String JDBC_DEFAULT_DRIVER_CLASS_NAME =
		"jdbc.default.driverClassName";

	public static final String JDBC_DEFAULT_JNDI_NAME =
		"jdbc.default.jndi.name";

	public static final String JDBC_DEFAULT_PASSWORD = "jdbc.default.password";

	public static final String JDBC_DEFAULT_URL = "jdbc.default.url";

	public static final String JDBC_DEFAULT_USERNAME = "jdbc.default.username";

	public static final String JNDI_ENVIRONMENT = "jndi.environment.";

	public static final String JSON_DESERIALIZATION_WHITELIST_CLASS_NAMES =
		"json.deserialization.whitelist.class.names";

	public static final String JSON_SERVICE_AUTH_TOKEN_ENABLED =
		"json.service.auth.token.enabled";

	public static final String JSON_SERVICE_AUTH_TOKEN_HOSTS_ALLOWED =
		"json.service.auth.token.hosts.allowed";

	public static final String JSON_SERVICE_INVALID_CLASS_NAMES =
		"json.service.invalid.class.names";

	public static final String JSON_SERVICE_INVALID_METHOD_NAMES =
		"json.service.invalid.method.names";

	public static final String JSON_SERVICE_SERIALIZE_THROWABLE =
		"json.service.serialize.throwable";

	public static final String JSON_STRING_MAX_LENGTH =
		"json.string.max.length";

	public static final String JSON_WEB_SERVICE_ENABLED =
		"json.web.service.enabled";

	public static final String JSONWS_WEB_SERVICE_API_DISCOVERABLE =
		"jsonws.web.service.api.discoverable";

	public static final String JSONWS_WEB_SERVICE_INVALID_HTTP_METHODS =
		"jsonws.web.service.invalid.http.methods";

	public static final String
		JSONWS_WEB_SERVICE_PARAMETER_TYPE_WHITELIST_CLASS_NAMES =
			"jsonws.web.service.parameter.type.whitelist.class.names";

	public static final String JSONWS_WEB_SERVICE_PATHS_EXCLUDES =
		"jsonws.web.service.paths.excludes";

	public static final String JSONWS_WEB_SERVICE_PATHS_INCLUDES =
		"jsonws.web.service.paths.includes";

	public static final String JSONWS_WEB_SERVICE_STRICT_HTTP_METHOD =
		"jsonws.web.service.strict.http.method";

	public static final String JSP_PAGE_CONTEXT_FORCE_GET_ATTRIBUTE =
		"jsp.page.context.force.get.attribute";

	public static final String JSP_WRITER_BUFFER_SIZE =
		"jsp.writer.buffer.size";

	public static final String LAYOUT_AJAX_RENDER_ENABLE =
		"layout.ajax.render.enable";

	public static final String LAYOUT_BROWSABLE = "layout.browsable";

	public static final String LAYOUT_CLONE_IMPL = "layout.clone.impl";

	public static final String LAYOUT_CONFIGURATION_ACTION_DELETE =
		"layout.configuration.action.delete";

	public static final String LAYOUT_CONFIGURATION_ACTION_UPDATE =
		"layout.configuration.action.update";

	public static final String LAYOUT_DEFAULT_P_L_RESET =
		"layout.default.p_l_reset";

	public static final String LAYOUT_DEFAULT_TEMPLATE_ID =
		"layout.default.template.id";

	public static final String LAYOUT_EDIT_PAGE = "layout.edit.page";

	public static final String LAYOUT_FIRST_PAGEABLE = "layout.first.pageable";

	public static final String LAYOUT_FORM_ADD = "layout.form.add";

	public static final String LAYOUT_FORM_UPDATE = "layout.form.update";

	public static final String LAYOUT_FRIENDLY_URL_KEYWORDS =
		"layout.friendly.url.keywords";

	public static final String LAYOUT_FRIENDLY_URL_PAGE_NOT_FOUND =
		"layout.friendly.url.page.not.found";

	public static final String
		LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING =
			"layout.friendly.url.private.group.servlet.mapping";

	public static final String
		LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING =
			"layout.friendly.url.private.user.servlet.mapping";

	public static final String LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING =
		"layout.friendly.url.public.servlet.mapping";

	public static final String LAYOUT_GUEST_SHOW_MAX_ICON =
		"layout.guest.show.max.icon";

	public static final String LAYOUT_GUEST_SHOW_MIN_ICON =
		"layout.guest.show.min.icon";

	public static final String LAYOUT_MANAGE_PAGES_INITIAL_CHILDREN =
		"layout.manage.pages.initial.children";

	public static final String LAYOUT_PARENTABLE = "layout.parentable";

	public static final String LAYOUT_PROTOTYPE_LINK_ENABLED_DEFAULT =
		"layout.prototype.link.enabled.default";

	public static final String LAYOUT_PROTOTYPE_MERGE_FAIL_THRESHOLD =
		"layout.prototype.merge.fail.threshold";

	public static final String LAYOUT_PROTOTYPE_MERGE_LOCK_MAX_TIME =
		"layout.prototype.merge.lock.max.time";

	public static final String LAYOUT_REMEMBER_MAXIMIZED_WINDOW_STATE =
		"layout.remember.maximized.window.state";

	public static final String LAYOUT_SCOPE_GROUP_FINDER_ENABLED =
		"layout.scope.group.finder.enabled";

	public static final String LAYOUT_SCOPE_GROUP_FINDER_THRESHOLD =
		"layout.scope.group.finder.threshold";

	public static final String LAYOUT_SET_FORM_UPDATE =
		"layout.set.form.update";

	public static final String LAYOUT_SET_PROTOTYPE_MERGE_FAIL_THRESHOLD =
		"layout.set.prototype.merge.fail.threshold";

	public static final String LAYOUT_SET_PROTOTYPE_MERGE_LOCK_MAX_TIME =
		"layout.set.prototype.merge.lock.max.time";

	public static final String LAYOUT_SET_PROTOTYPE_PROPAGATE_LOGO =
		"layout.set.prototype.propagate.logo";

	public static final String LAYOUT_SHOW_HTTP_STATUS =
		"layout.show.http.status";

	public static final String LAYOUT_SHOW_PORTLET_ACCESS_DENIED =
		"layout.show.portlet.access.denied";

	public static final String LAYOUT_SHOW_PORTLET_INACTIVE =
		"layout.show.portlet.inactive";

	public static final String LAYOUT_SITEMAPABLE = "layout.sitemapable";

	public static final String LAYOUT_STATIC_PORTLETS =
		"layout.static.portlets.";

	public static final String LAYOUT_STATIC_PORTLETS_ALL =
		"layout.static.portlets.all";

	public static final String LAYOUT_STATIC_PORTLETS_END =
		"layout.static.portlets.end.";

	public static final String LAYOUT_STATIC_PORTLETS_START =
		"layout.static.portlets.start.";

	public static final String LAYOUT_TEMPLATE_CACHE_ENABLED =
		"layout.template.cache.enabled";

	public static final String LAYOUT_URL = "layout.url";

	public static final String LAYOUT_URL_FRIENDLIABLE =
		"layout.url.friendliable";

	public static final String LAYOUT_USER_ACCESS_VIA_PLID_ENABLED =
		"layout.user.access.via.plid.enabled";

	public static final String LAYOUT_USER_PRIVATE_LAYOUTS_AUTO_CREATE =
		"layout.user.private.layouts.auto.create";

	public static final String LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED =
		"layout.user.private.layouts.enabled";

	public static final String LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED =
		"layout.user.private.layouts.power.user.required";

	public static final String LAYOUT_USER_PUBLIC_LAYOUTS_AUTO_CREATE =
		"layout.user.public.layouts.auto.create";

	public static final String LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED =
		"layout.user.public.layouts.enabled";

	public static final String LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED =
		"layout.user.public.layouts.power.user.required";

	public static final String LAYOUT_VIEW_PAGE = "layout.view.page";

	public static final String LDAP_ATTRS_TRANSFORMER_IMPL =
		"ldap.attrs.transformer.impl";

	public static final String LIFERAY_HOME = "liferay.home";

	public static final String LIFERAY_SHIELDED_CONTAINER_LIB_PORTAL_DIR =
		"liferay.shielded.container.lib.portal.dir";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String LIVE_USERS_ENABLED = "live.users.enabled";

	public static final String LOCALE_DEFAULT_REQUEST =
		"locale.default.request";

	public static final String LOCALE_PREPEND_FRIENDLY_URL_STYLE =
		"locale.prepend.friendly.url.style";

	public static final String LOCALE_USE_DEFAULT_IF_NOT_AVAILABLE =
		"locale.use.default.if.not.available";

	public static final String LOCALES = "locales";

	public static final String LOCALES_BETA = "locales.beta";

	public static final String LOCALES_ENABLED = "locales.enabled";

	public static final String LOCK_LISTENERS = "lock.listeners";

	public static final String LOG_SANITIZER_ENABLED = "log.sanitizer.enabled";

	public static final String LOG_SANITIZER_ESCAPE_HTML_ENABLED =
		"log.sanitizer.escape.html.enabled";

	public static final String LOG_SANITIZER_REPLACEMENT_CHARACTER =
		"log.sanitizer.replacement.character";

	public static final String LOG_SANITIZER_WHITELIST_CHARACTERS =
		"log.sanitizer.whitelist.characters";

	public static final String LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD =
		"login.create.account.allow.custom.password";

	public static final String LOGIN_DIALOG_DISABLED = "login.dialog.disabled";

	public static final String LOGIN_EMAIL_FROM_ADDRESS =
		"login.email.from.address";

	public static final String LOGIN_EMAIL_FROM_NAME = "login.email.from.name";

	public static final String LOGIN_EVENTS_POST = "login.events.post";

	public static final String LOGIN_EVENTS_PRE = "login.events.pre";

	public static final String LOGIN_FORM_NAVIGATION_POST =
		"login.form.navigation.post";

	public static final String LOGIN_FORM_NAVIGATION_PRE =
		"login.form.navigation.pre";

	public static final String LOGIN_SECURE_FORGOT_PASSWORD =
		"login.secure.forgot.password";

	public static final String LOGOUT_EVENTS_POST = "logout.events.post";

	public static final String LOGOUT_EVENTS_PRE = "logout.events.pre";

	public static final String MAIL_AUDIT_TRAIL = "mail.audit.trail";

	public static final String MAIL_BATCH_SIZE = "mail.batch.size";

	public static final String MAIL_MX_UPDATE = "mail.mx.update";

	public static final String MAIL_SEND_BLACKLIST = "mail.send.blacklist";

	public static final String MAIL_SESSION_MAIL = "mail.session.mail";

	public static final String MAIL_SESSION_MAIL_ADVANCED_PROPERTIES =
		"mail.session.mail.advanced.properties";

	public static final String MAIL_SESSION_MAIL_POP3_HOST =
		"mail.session.mail.pop3.host";

	public static final String MAIL_SESSION_MAIL_POP3_PASSWORD =
		"mail.session.mail.pop3.password";

	public static final String MAIL_SESSION_MAIL_POP3_PORT =
		"mail.session.mail.pop3.port";

	public static final String MAIL_SESSION_MAIL_POP3_USER =
		"mail.session.mail.pop3.user";

	public static final String MAIL_SESSION_MAIL_SMTP_HOST =
		"mail.session.mail.smtp.host";

	public static final String MAIL_SESSION_MAIL_SMTP_PASSWORD =
		"mail.session.mail.smtp.password";

	public static final String MAIL_SESSION_MAIL_SMTP_PORT =
		"mail.session.mail.smtp.port";

	public static final String MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE =
		"mail.session.mail.smtp.starttls.enable";

	public static final String MAIL_SESSION_MAIL_SMTP_USER =
		"mail.session.mail.smtp.user";

	public static final String MAIL_SESSION_MAIL_STORE_PROTOCOL =
		"mail.session.mail.store.protocol";

	public static final String MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL =
		"mail.session.mail.transport.protocol";

	public static final String MAIL_THROWS_EXCEPTION_ON_FAILURE =
		"mail.throws.exception.on.failure";

	public static final String MEMBERSHIP_POLICY_AUTO_VERIFY =
		"membership.policy.auto.verify";

	public static final String MEMBERSHIP_POLICY_ORGANIZATIONS =
		"membership.policy.organizations";

	public static final String MEMBERSHIP_POLICY_ROLES =
		"membership.policy.roles";

	public static final String MEMBERSHIP_POLICY_SITES =
		"membership.policy.sites";

	public static final String MEMBERSHIP_POLICY_USER_GROUPS =
		"membership.policy.user.groups";

	public static final String MENU_MAX_DISPLAY_ITEMS =
		"menu.max.display.items";

	public static final String MESSAGE_BOARDS_ALLOW_ANONYMOUS_POSTING =
		"message.boards.anonymous.posting.enabled";

	public static final String MESSAGE_BOARDS_ANONYMOUS_POSTING_ENABLED =
		"message.boards.anonymous.posting.enabled";

	public static final String MESSAGE_BOARDS_CATEGORY_DISPLAY_STYLES =
		"message.boards.category.display.styles";

	public static final String MESSAGE_BOARDS_CATEGORY_DISPLAY_STYLES_DEFAULT =
		"message.boards.category.display.styles.default";

	public static final String MESSAGE_BOARDS_EMAIL_BULK =
		"message.boards.email.bulk";

	public static final String MESSAGE_BOARDS_EMAIL_FROM_ADDRESS =
		"message.boards.email.from.address";

	public static final String MESSAGE_BOARDS_EMAIL_FROM_NAME =
		"message.boards.email.from.name";

	public static final String MESSAGE_BOARDS_EMAIL_HTML_FORMAT =
		"message.boards.email.html.format";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_BODY =
		"message.boards.email.message.added.body";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_ENABLED =
		"message.boards.email.message.added.enabled";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_ADDED_SUBJECT =
		"message.boards.email.message.added.subject";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_BODY =
		"message.boards.email.message.updated.body";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_ENABLED =
		"message.boards.email.message.updated.enabled";

	public static final String MESSAGE_BOARDS_EMAIL_MESSAGE_UPDATED_SUBJECT =
		"message.boards.email.message.updated.subject";

	public static final String MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL =
		"message.boards.expire.ban.interval";

	public static final String MESSAGE_BOARDS_FLAGS_ENABLED =
		"message.boards.flags.enabled";

	public static final String MESSAGE_BOARDS_MESSAGE_FORMATS =
		"message.boards.message.formats";

	public static final String MESSAGE_BOARDS_MESSAGE_FORMATS_DEFAULT =
		"message.boards.message.formats.default";

	public static final String MESSAGE_BOARDS_PINGBACK_ENABLED =
		"message.boards.pingback.enabled";

	public static final String MESSAGE_BOARDS_PUBLISH_TO_LIVE_BY_DEFAULT =
		"message.boards.publish.to.live.by.default";

	public static final String MESSAGE_BOARDS_RATINGS_ENABLED =
		"message.boards.ratings.enabled";

	public static final String MESSAGE_BOARDS_RECENT_POSTS_DATE_OFFSET =
		"message.boards.recent.posts.date.offset";

	public static final String MESSAGE_BOARDS_RSS_ABSTRACT_LENGTH =
		"message.boards.rss.abstract.length";

	public static final String MESSAGE_BOARDS_RSS_ENABLED =
		"message.boards.rss.enabled";

	public static final String MESSAGE_BOARDS_SUBSCRIBE_BY_DEFAULT =
		"message.boards.subscribe.by.default";

	public static final String MESSAGE_BOARDS_THREAD_AS_QUESTION_BY_DEFAULT =
		"message.boards.thread.as.question.by.default";

	public static final String MESSAGE_BOARDS_THREAD_PRIORITIES =
		"message.boards.thread.priorities";

	public static final String MESSAGE_BOARDS_USER_RANKS =
		"message.boards.user.ranks";

	public static final String MIME_TYPES_CONTENT_DISPOSITION_INLINE =
		"mime.types.content.disposition.inline";

	public static final String MIME_TYPES_WEB_IMAGES = "mime.types.web.images";

	public static final String MINIFIER_ENABLED = "minifier.enabled";

	public static final String MINIFIER_INLINE_CONTENT_CACHE_ENABLED =
		"minifier.inline.content.cache.enabled";

	public static final String MINIFIER_INLINE_CONTENT_CACHE_SKIP_CSS =
		"minifier.inline.content.cache.skip.css";

	public static final String MINIFIER_INLINE_CONTENT_CACHE_SKIP_JAVASCRIPT =
		"minifier.inline.content.cache.skip.javascript";

	public static final String MODEL_HINTS_CONFIGS = "model.hints.configs";

	public static final String MODEL_TREE_REBUILD_QUERY_RESULTS_BATCH_SIZE =
		"model.tree.rebuild.query.results.batch.size";

	public static final String MODULE_FRAMEWORK_AUTO_DEPLOY_DIRS =
		"module.framework.auto.deploy.dirs";

	public static final String MODULE_FRAMEWORK_AUTO_DEPLOY_INTERVAL =
		"module.framework.auto.deploy.interval";

	public static final String MODULE_FRAMEWORK_BASE_DIR =
		"module.framework.base.dir";

	public static final String MODULE_FRAMEWORK_CONFIGS_DIR =
		"module.framework.configs.dir";

	public static final String MODULE_FRAMEWORK_EXPORT_PASSWORD_ATTRIBUTES =
		"module.framework.export.password.attributes";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_ACTIVE_LEVEL =
		"module.framework.file.install.active.level";

	public static final String
		MODULE_FRAMEWORK_FILE_INSTALL_BUNDLES_START_ACTIVATION_POLICY =
			"module.framework.file.install.bundles.start.activation.policy";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_BUNDLES_START_NEW =
		"module.framework.file.install.bundles.start.new";

	public static final String
		MODULE_FRAMEWORK_FILE_INSTALL_BUNDLES_START_TRANSIENT =
			"module.framework.file.install.bundles.start.transient";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_CFG_ENABLED =
		"module.framework.file.install.cfg.enabled";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_FILTER =
		"module.framework.file.install.filter";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_NO_INITIAL_DELAY =
		"module.framework.file.install.no.initial.delay";

	public static final String MODULE_FRAMEWORK_FILE_INSTALL_SUBDIR_MODE =
		"module.framework.file.install.subdir.mode";

	public static final String MODULE_FRAMEWORK_MARKETPLACE_DIR =
		"module.framework.marketplace.dir";

	public static final String MODULE_FRAMEWORK_MODULES_DIR =
		"module.framework.modules.dir";

	public static final String MODULE_FRAMEWORK_PORTAL_DIR =
		"module.framework.portal.dir";

	public static final String MODULE_FRAMEWORK_PROPERTIES =
		"module.framework.properties.";

	public static final String MODULE_FRAMEWORK_STATE_DIR =
		"module.framework.state.dir";

	public static final String MODULE_FRAMEWORK_STOP_WAIT_TIMEOUT =
		"module.framework.stop.wait.timeout";

	public static final String MODULE_FRAMEWORK_WAR_DIR =
		"module.framework.war.dir";

	public static final String
		MODULE_FRAMEWORK_WEB_GENERATOR_DEFAULT_SERVLET_PACKAGES =
			"module.framework.web.generator.default.servlet.packages";

	public static final String MODULE_FRAMEWORK_WEB_GENERATOR_EXCLUDED_PATHS =
		"module.framework.web.generator.excluded.paths";

	public static final String
		MODULE_FRAMEWORK_WEB_GENERATOR_GENERATED_WABS_STORE =
			"module.framework.web.generator.generated.wabs.store";

	public static final String
		MODULE_FRAMEWORK_WEB_GENERATOR_GENERATED_WABS_STORE_DIR =
			"module.framework.web.generator.generated.wabs.store.dir";

	public static final String MODULE_FRAMEWORK_WEB_GENERATOR_HEADERS =
		"module.framework.web.generator.headers.";

	public static final String
		MODULE_FRAMEWORK_WEB_GENERATOR_JSP_COMPILER_DEPENDENCIES =
			"module.framework.web.generator.jsp.compiler.dependencies";

	public static final String
		MODULE_FRAMEWORK_WEB_SERVLET_ANNOTATION_SCANNING_BLACKLIST =
			"module.framework.web.servlet.annotation.scanning.blacklist";

	public static final String
		MODULE_FRAMEWORK_WEB_SERVLET_ANNOTATION_SCANNING_WHITELIST =
			"module.framework.web.servlet.annotation.scanning.whitelist";

	public static final String MY_SITES_DIRECTORY_SITE_EXCLUDES =
		"my.sites.directory.site.excludes";

	public static final String MY_SITES_MAX_ELEMENTS = "my.sites.max.elements";

	public static final String MY_SITES_SHOW_PRIVATE_SITES_WITH_NO_LAYOUTS =
		"my.sites.show.private.sites.with.no.layouts";

	public static final String MY_SITES_SHOW_PUBLIC_SITES_WITH_NO_LAYOUTS =
		"my.sites.show.public.sites.with.no.layouts";

	public static final String
		MY_SITES_SHOW_USER_PRIVATE_SITES_WITH_NO_LAYOUTS =
			"my.sites.show.user.private.sites.with.no.layouts";

	public static final String MY_SITES_SHOW_USER_PUBLIC_SITES_WITH_NO_LAYOUTS =
		"my.sites.show.user.public.sites.with.no.layouts";

	public static final String NETVIBES_SERVLET_MAPPING =
		"netvibes.servlet.mapping";

	public static final String NOTIFICATION_EMAIL_TEMPLATE_ENABLED =
		"notification.email.template.enabled";

	public static final String NOTIFICATIONS_MAX_EVENTS =
		"notifications.max.events";

	public static final String OBJECT_ENCRYPTION_ALGORITHM =
		"object.encryption.algorithm";

	public static final String OBJECT_ENCRYPTION_ENABLED =
		"object.encryption.enabled";

	public static final String OBJECT_ENCRYPTION_KEY = "object.encryption.key";

	public static final String OBJECT_NESTED_FIELDS_MAX_QUERY_DEPTH =
		"object.nested.fields.max.query.depth";

	public static final String OMNIADMIN_USERS = "omniadmin.users";

	public static final String OPEN_ID_AUTH_ENABLED = "open.id.auth.enabled";

	public static final String OPEN_ID_AX_SCHEMA = "open.id.ax.schema";

	public static final String OPEN_ID_AX_TYPE_EMAIL = "open.id.ax.type.email";

	public static final String OPEN_ID_AX_TYPE_FIRST_NAME =
		"open.id.ax.type.firstname";

	public static final String OPEN_ID_AX_TYPE_FULL_NAME =
		"open.id.ax.type.fullname";

	public static final String OPEN_ID_AX_TYPE_LAST_NAME =
		"open.id.ax.type.lastname";

	public static final String OPEN_ID_PROVIDERS = "open.id.providers";

	public static final String OPEN_ID_URL = "open.id.url";

	public static final String OPEN_SSO_AUTH_ENABLED = "open.sso.auth.enabled";

	public static final String OPEN_SSO_EMAIL_ADDRESS_ATTR =
		"open.sso.email.address.attr";

	public static final String OPEN_SSO_FIRST_NAME_ATTR =
		"open.sso.first.name.attr";

	public static final String OPEN_SSO_LAST_NAME_ATTR =
		"open.sso.last.name.attr";

	public static final String OPEN_SSO_LDAP_IMPORT_ENABLED =
		"open.sso.ldap.import.enabled";

	public static final String OPEN_SSO_LOGIN_URL = "open.sso.login.url";

	public static final String OPEN_SSO_LOGOUT_ON_SESSION_EXPIRATION =
		"open.sso.logout.on.session.expiration";

	public static final String OPEN_SSO_LOGOUT_URL = "open.sso.logout.url";

	public static final String OPEN_SSO_SCREEN_NAME_ATTR =
		"open.sso.screen.name.attr";

	public static final String OPEN_SSO_SERVICE_URL = "open.sso.service.url";

	public static final String OPENOFFICE_CONVERSION_SOURCE_EXTENSIONS =
		"openoffice.conversion.source.extensions";

	public static final String OPENOFFICE_CONVERSION_TARGET_EXTENSIONS =
		"openoffice.conversion.target.extensions";

	public static final String ORGANIZATIONS_ASSIGNMENT_STRICT =
		"organizations.assignment.strict";

	public static final String ORGANIZATIONS_FORM_ADD_IDENTIFICATION =
		"organizations.form.add.identification";

	public static final String ORGANIZATIONS_FORM_ADD_MAIN =
		"organizations.form.add.main";

	public static final String ORGANIZATIONS_FORM_ADD_MISCELLANEOUS =
		"organizations.form.add.miscellaneous";

	public static final String ORGANIZATIONS_FORM_UPDATE_IDENTIFICATION =
		"organizations.form.update.identification";

	public static final String ORGANIZATIONS_FORM_UPDATE_MAIN =
		"organizations.form.update.main";

	public static final String ORGANIZATIONS_FORM_UPDATE_MISCELLANEOUS =
		"organizations.form.update.miscellaneous";

	public static final String ORGANIZATIONS_MEMBERSHIP_STRICT =
		"organizations.membership.strict";

	public static final String ORGANIZATIONS_SEARCH_WITH_INDEX =
		"organizations.search.with.index";

	public static final String PASSWORDS_DEFAULT_POLICY_ALLOW_DICTIONARY_WORDS =
		"passwords.default.policy.allow.dictionary.words";

	public static final String PASSWORDS_DEFAULT_POLICY_CHANGE_REQUIRED =
		"passwords.default.policy.change.required";

	public static final String PASSWORDS_DEFAULT_POLICY_CHANGEABLE =
		"passwords.default.policy.changeable";

	public static final String PASSWORDS_DEFAULT_POLICY_CHECK_SYNTAX =
		"passwords.default.policy.check.syntax";

	public static final String PASSWORDS_DEFAULT_POLICY_EXPIREABLE =
		"passwords.default.policy.expireable";

	public static final String PASSWORDS_DEFAULT_POLICY_GRACE_LIMIT =
		"passwords.default.policy.grace.limit";

	public static final String PASSWORDS_DEFAULT_POLICY_HISTORY =
		"passwords.default.policy.history";

	public static final String PASSWORDS_DEFAULT_POLICY_HISTORY_COUNT =
		"passwords.default.policy.history.count";

	public static final String PASSWORDS_DEFAULT_POLICY_LOCKOUT =
		"passwords.default.policy.lockout";

	public static final String PASSWORDS_DEFAULT_POLICY_LOCKOUT_DURATION =
		"passwords.default.policy.lockout.duration";

	public static final String PASSWORDS_DEFAULT_POLICY_MAX_AGE =
		"passwords.default.policy.max.age";

	public static final String PASSWORDS_DEFAULT_POLICY_MAX_FAILURE =
		"passwords.default.policy.max.failure";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_AGE =
		"passwords.default.policy.min.age";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_ALPHANUMERIC =
		"passwords.default.policy.alphanumeric";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_LENGTH =
		"passwords.default.policy.min.length";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_LOWERCASE =
		"passwords.default.policy.min.lowercase";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_NUMBERS =
		"passwords.default.policy.min.numbers";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_SYMBOLS =
		"passwords.default.policy.min.symbols";

	public static final String PASSWORDS_DEFAULT_POLICY_MIN_UPPERCASE =
		"passwords.default.policy.min.uppercase";

	public static final String PASSWORDS_DEFAULT_POLICY_NAME =
		"passwords.default.policy.name";

	public static final String PASSWORDS_DEFAULT_POLICY_REGEX =
		"passwords.default.policy.regex";

	public static final String PASSWORDS_DEFAULT_POLICY_RESET_FAILURE_COUNT =
		"passwords.default.policy.reset.failure.count";

	public static final String PASSWORDS_DEFAULT_POLICY_RESET_TICKET_MAX_AGE =
		"passwords.default.policy.reset.ticket.max.age";

	public static final String PASSWORDS_DEFAULT_POLICY_WARNING_TIME =
		"passwords.default.policy.warning.time";

	public static final String PASSWORDS_DIGEST_ENCODING =
		"passwords.digest.encoding";

	public static final String PASSWORDS_ENCRYPTION_ALGORITHM =
		"passwords.encryption.algorithm";

	public static final String PASSWORDS_ENCRYPTION_ALGORITHM_LEGACY =
		"passwords.encryption.algorithm.legacy";

	public static final String PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR =
		"passwords.passwordpolicytoolkit.generator";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_LOWERCASE =
			"passwords.passwordpolicytoolkit.generator.charset.lowercase";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_NUMBERS =
			"passwords.passwordpolicytoolkit.generator.charset.numbers";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_SYMBOLS =
			"passwords.passwordpolicytoolkit.generator.charset.symbols";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_GENERATOR_CHARSET_UPPERCASE =
			"passwords.passwordpolicytoolkit.generator.charset.uppercase";

	public static final String PASSWORDS_PASSWORDPOLICYTOOLKIT_STATIC =
		"passwords.passwordpolicytoolkit.static";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_VALIDATOR_CHARSET_LOWERCASE =
			"passwords.passwordpolicytoolkit.validator.charset.lowercase";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_VALIDATOR_CHARSET_NUMBERS =
			"passwords.passwordpolicytoolkit.validator.charset.numbers";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_VALIDATOR_CHARSET_SYMBOLS =
			"passwords.passwordpolicytoolkit.validator.charset.symbols";

	public static final String
		PASSWORDS_PASSWORDPOLICYTOOLKIT_VALIDATOR_CHARSET_UPPERCASE =
			"passwords.passwordpolicytoolkit.validator.charset.uppercase";

	public static final String PASSWORDS_REGEXPTOOLKIT_CHARSET =
		"passwords.regexptoolkit.charset";

	public static final String PASSWORDS_REGEXPTOOLKIT_LENGTH =
		"passwords.regexptoolkit.length";

	public static final String PASSWORDS_REGEXPTOOLKIT_PATTERN =
		"passwords.regexptoolkit.pattern";

	public static final String PASSWORDS_TOOLKIT = "passwords.toolkit";

	public static final String PERMISSIONS_CHECK_GUEST_ENABLED =
		"permissions.check.guest.enabled";

	public static final String PERMISSIONS_CHECKER = "permissions.checker";

	public static final String
		PERMISSIONS_CUSTOM_ATTRIBUTE_READ_CHECK_BY_DEFAULT =
			"permissions.custom.attribute.read.check.by.default";

	public static final String
		PERMISSIONS_CUSTOM_ATTRIBUTE_WRITE_CHECK_BY_DEFAULT =
			"permissions.custom.attribute.write.check.by.default";

	public static final String PERMISSIONS_PROPAGATION_ENABLED =
		"permissions.propagation.enabled";

	public static final String
		PERMISSIONS_ROLE_RESOURCE_PERMISSION_QUERY_THRESHOLD =
			"permissions.role.resource.permission.query.threshold";

	public static final String PERMISSIONS_VIEW_DYNAMIC_INHERITANCE =
		"permissions.view.dynamic.inheritance";

	public static final String PHONE_NUMBER_FORMAT_IMPL =
		"phone.number.format.impl";

	public static final String PHONE_NUMBER_FORMAT_INTERNATIONAL_REGEXP =
		"phone.number.format.international.regexp";

	public static final String PHONE_NUMBER_FORMAT_USA_REGEXP =
		"phone.number.format.usa.regexp";

	public static final String POP_SERVER_NOTIFICATIONS_ENABLED =
		"pop.server.notifications.enabled";

	public static final String POP_SERVER_SUBDOMAIN = "pop.server.subdomain";

	public static final String PORTAL_IMPERSONATION_DEFAULT_URL =
		"portal.impersonation.default.url";

	public static final String PORTAL_IMPERSONATION_ENABLE =
		"portal.impersonation.enable";

	public static final String PORTAL_INSTANCE_INET_SOCKET_ADDRESS =
		"portal.instance.inet.socket.address";

	public static final String PORTAL_INSTANCE_PROTOCOL =
		"portal.instance.protocol";

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	public static final String PORTAL_JAAS_AUTH_TYPE = "portal.jaas.auth.type";

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	public static final String PORTAL_JAAS_ENABLE = "portal.jaas.enable";

	/**
	 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
	 */
	@Deprecated
	public static final String PORTAL_JAAS_PLAIN_PASSWORD =
		"portal.jaas.plain.password";

	public static final String PORTAL_PROXY_PATH = "portal.proxy.path";

	public static final String
		PORTAL_SECURITY_MANAGER_FILE_CHECKER_DEFAULT_READ_PATHS =
			"portal.security.manager.file.checker.default.read.paths";

	public static final String
		PORTAL_SECURITY_MANAGER_PRELOAD_CLASSLOADER_CLASSES =
			"portal.security.manager.preload.classloader.classes";

	public static final String PORTAL_SECURITY_MANAGER_STRATEGY =
		"portal.security.manager.strategy";

	public static final String PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED =
		"portlet.add.default.resource.check.enabled";

	public static final String PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST =
		"portlet.add.default.resource.check.whitelist";

	public static final String
		PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST_ACTIONS =
			"portlet.add.default.resource.check.whitelist.actions";

	public static final String PORTLET_CONFIG_SHOW_PORTLET_ID =
		"portlet.config.show.portlet.id";

	public static final String PORTLET_CONFIGS = "portlet.configs";

	public static final String PORTLET_CONTAINER_RESTRICT =
		"portlet.container.restrict";

	public static final String PORTLET_CROSS_LAYOUT_INVOCATION_MODE =
		"portlet.cross.layout.invocation.mode";

	public static final String PORTLET_CSS_ENABLED = "portlet.css.enabled";

	public static final String PORTLET_DEPENDENCY_CSS_URN =
		"portlet.dependency.css.urn";

	public static final String PORTLET_DEPENDENCY_JAVASCRIPT_URN =
		"portlet.dependency.javascript.urn";

	public static final String PORTLET_DISPLAY_TEMPLATES_ERROR =
		"portlet.display.templates.error";

	public static final String PORTLET_DISPLAY_TEMPLATES_HELP =
		"portlet.display.templates.help";

	public static final String PORTLET_EVENT_DISTRIBUTION =
		"portlet.event.distribution";

	public static final String PORTLET_FILTERS_SYSTEM =
		"portlet.filters.system";

	public static final String PORTLET_INTERRUPTED_REQUEST_WHITELIST =
		"portlet.interrupted.request.whitelist";

	public static final String PORTLET_INTERRUPTED_REQUEST_WHITELIST_ACTIONS =
		"portlet.interrupted.request.whitelist.actions";

	public static final String PORTLET_PREFERENCES_CACHE_KEY_THRESHOLD_SIZE =
		"portlet.preferences.cache.key.threshold.size";

	public static final String PORTLET_PREFERENCES_STRICT_STORE =
		"portlet.preferences.strict.store";

	public static final String PORTLET_PUBLIC_RENDER_PARAMETER_DISTRIBUTION =
		"portlet.public.render.parameter.distribution";

	public static final String PORTLET_RESOURCE_ID_BANNED_PATHS_REGEXP =
		"portlet.resource.id.banned.paths.regexp";

	public static final String PORTLET_SESSION_REPLICATE_ENABLED =
		"portlet.session.replicate.enabled";

	public static final String PORTLET_URL_ANCHOR_ENABLE =
		"portlet.url.anchor.enable";

	public static final String PORTLET_URL_APPEND_PARAMETERS =
		"portlet.url.append.parameters";

	public static final String PORTLET_URL_ESCAPE_XML =
		"portlet.url.escape.xml";

	public static final String PORTLET_URL_REFRESH_URL_RESERVED_PARAMETERS =
		"portlet.url.refresh.url.reserved.parameters";

	public static final String PORTLET_VIRTUAL_PATH = "portlet.virtual.path";

	public static final String PORTLET_XML_VALIDATE = "portlet.xml.validate";

	public static final String PREFERENCE_VALIDATE_ON_STARTUP =
		"preference.validate.on.startup";

	public static final String RATINGS_DEFAULT_NUMBER_OF_STARS =
		"ratings.default.number.of.stars";

	public static final String RATINGS_UPGRADE_STARS_NORMALIZATION_FACTOR =
		"ratings.upgrade.stars.normalization.factor";

	public static final String RATINGS_UPGRADE_THUMBS_CLASS_NAMES =
		"ratings.upgrade.thumbs.class.names";

	public static final String RECENT_CONTENT_MAX_DISPLAY_ITEMS =
		"recent.content.max.display.items";

	public static final String RECENT_GROUPS_MAX_ELEMENTS =
		"recent.groups.max.elements";

	public static final String RELEASE_INFO_BUILD_NUMBER =
		"release.info.build.number";

	public static final String RELEASE_INFO_PREVIOUS_BUILD_NUMBER =
		"release.info.previous.build.number";

	public static final String REQUEST_HEADER_AUTH_IMPORT_FROM_LDAP =
		"request.header.auth.import.from.ldap";

	public static final String REQUEST_HEADER_IGNORE_INIT_PARAMS =
		"request.header.ignore.init.params";

	public static final String REQUEST_SHARED_ATTRIBUTES =
		"request.shared.attributes";

	public static final String REQUEST_UNWRAP_PACKAGES =
		"request.unwrap.packages";

	public static final String RESOURCE_ACTIONS_CONFIGS =
		"resource.actions.configs";

	public static final String RESOURCE_ACTIONS_READ_PORTLET_RESOURCES =
		"resource.actions.read.portlet.resources";

	public static final String RESOURCE_ACTIONS_STRICT_MODE_ENABLED =
		"resource.actions.strict.mode.enabled";

	public static final String RESOURCE_REPOSITORIES_ROOT =
		"resource.repositories.root";

	public static final String REST_PROXY_URL_PREFIXES_ALLOWED =
		"rest.proxy.url.prefixes.allowed";

	public static final String RETRY_ADVICE_MAX_RETRIES =
		"retry.advice.max.retries";

	public static final String RETRY_DATA_SOURCE_MAX_RETRIES =
		"retry.data.source.max.retries";

	public static final String RETRY_JDBC_ON_STARTUP_DELAY =
		"retry.jdbc.on.startup.delay";

	public static final String RETRY_JDBC_ON_STARTUP_MAX_RETRIES =
		"retry.jdbc.on.startup.max.retries";

	public static final String ROBOTS_TXT_WITH_SITEMAP =
		"robots.txt.with.sitemap";

	public static final String ROBOTS_TXT_WITHOUT_SITEMAP =
		"robots.txt.without.sitemap";

	public static final String ROLES_NAME_ALLOW_NUMERIC =
		"roles.name.allow.numeric";

	public static final String ROLES_ORGANIZATION_SUBTYPES =
		"roles.organization.subtypes";

	public static final String ROLES_REGULAR_SUBTYPES =
		"roles.regular.subtypes";

	public static final String ROLES_SITE_SUBTYPES = "roles.site.subtypes";

	public static final String RSS_CONNECTION_TIMEOUT =
		"rss.connection.timeout";

	public static final String RSS_FEED_DISPLAY_STYLE_DEFAULT =
		"rss.feed.display.style.default";

	public static final String RSS_FEED_TYPE_DEFAULT = "rss.feed.type.default";

	public static final String RSS_FEED_TYPES = "rss.feed.types";

	public static final String RSS_FEEDS_ENABLED = "rss.feeds.enabled";

	public static final String SANITIZER_IMPL = "sanitizer.impl";

	public static final String SCHEDULER_DESCRIPTION_MAX_LENGTH =
		"scheduler.description.max.length";

	public static final String SCHEDULER_ENABLED = "scheduler.enabled";

	public static final String SCHEDULER_GROUP_NAME_MAX_LENGTH =
		"scheduler.group.name.max.length";

	public static final String SCHEDULER_JOB_NAME_MAX_LENGTH =
		"scheduler.job.name.max.length";

	public static final String SCHEMA_MODULE_BUILD_AUTO_UPGRADE =
		"schema.module.build.auto.upgrade";

	public static final String SCHEMA_RUN_ENABLED = "schema.run.enabled";

	public static final String SCRIPT_MANAGEMENT_CONFIGURATION_ENABLED =
		"script.management.configuration.enabled";

	public static final String SEARCH_CONTAINER_PAGE_DEFAULT_DELTA =
		"search.container.page.default.delta";

	public static final String SEARCH_CONTAINER_PAGE_DELTA_VALUES =
		"search.container.page.delta.values";

	public static final String SEARCH_CONTAINER_PAGE_ITERATOR_MAX_PAGES =
		"search.container.page.iterator.max.pages";

	public static final String SEARCH_CONTAINER_PAGE_MAX_DELTA =
		"search.container.page.max.delta";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String SEARCH_CONTAINER_SHOW_PAGINATION_BOTTOM =
		"search.container.show.pagination.bottom";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String SEARCH_CONTAINER_SHOW_PAGINATION_TOP =
		"search.container.show.pagination.top";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String SEARCH_CONTAINER_SHOW_PAGINATION_TOP_DELTA =
		"search.container.show.pagination.top.delta";

	public static final String SERVLET_CONTEXT_CLASS_LOADER_POOL_FALLBACK =
		"servlet.context.class.loader.pool.fallback";

	public static final String SERVLET_SERVICE_EVENTS_POST =
		"servlet.service.events.post";

	public static final String SERVLET_SERVICE_EVENTS_PRE =
		"servlet.service.events.pre";

	public static final String SERVLET_SERVICE_EVENTS_PRE_ERROR_PAGE =
		"servlet.service.events.pre.error.page";

	public static final String SERVLET_SESSION_CREATE_EVENTS =
		"servlet.session.create.events";

	public static final String SERVLET_SESSION_DESTROY_EVENTS =
		"servlet.session.destroy.events";

	public static final String SESSION_CLICKS_MAX_ALLOWED_VALUES =
		"session.clicks.max.allowed.values";

	public static final String SESSION_CLICKS_MAX_SIZE_TERMS =
		"session.clicks.max.size.terms";

	public static final String SESSION_COOKIE_DOMAIN = "session.cookie.domain";

	public static final String SESSION_COOKIE_USE_FULL_HOSTNAME =
		"session.cookie.use.full.hostname";

	public static final String SESSION_ENABLE_PERSISTENT_COOKIES =
		"session.enable.persistent.cookies";

	public static final String SESSION_ENABLE_PHISHING_PROTECTION =
		"session.enable.phishing.protection";

	public static final String SESSION_ENABLE_URL_WITH_SESSION_ID =
		"session.enable.url.with.session.id";

	public static final String SESSION_ID_DELIMITER = "session.id.delimiter";

	public static final String SESSION_MAX_ALLOWED = "session.max.allowed";

	public static final String SESSION_PHISHING_PROTECTED_ATTRIBUTES =
		"session.phishing.protected.attributes";

	public static final String SESSION_SHARED_ATTRIBUTES =
		"session.shared.attributes";

	public static final String SESSION_SHARED_ATTRIBUTES_EXCLUDES =
		"session.shared.attributes.excludes";

	public static final String SESSION_STORE_PASSWORD =
		"session.store.password";

	public static final String SESSION_TEST_COOKIE_SUPPORT =
		"session.test.cookie.support";

	public static final String SESSION_TIMEOUT = "session.timeout";

	public static final String SESSION_TIMEOUT_REDIRECT_ON_EXPIRE =
		"session.timeout.redirect.on.expire";

	public static final String SESSION_TIMEOUT_WARNING =
		"session.timeout.warning";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String SESSION_TRACKER_FRIENDLY_PATHS_ENABLED =
		"session.tracker.friendly.paths.enabled";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String SESSION_TRACKER_IGNORE_PATHS =
		"session.tracker.ignore.paths";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String SESSION_TRACKER_MEMORY_ENABLED =
		"session.tracker.memory.enabled";

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public static final String SESSION_TRACKER_PERSISTENCE_ENABLED =
		"session.tracker.persistence.enabled";

	public static final String SESSION_VERIFY_SERIALIZABLE_ATTRIBUTE =
		"session.verify.serializable.attribute";

	public static final String SETUP_DATABASE_DRIVER_CLASS_NAME =
		"setup.database.driverClassName";

	public static final String SETUP_DATABASE_JAR_NAME =
		"setup.database.jar.name";

	public static final String SETUP_DATABASE_JAR_SHA1 =
		"setup.database.jar.sha1";

	public static final String SETUP_DATABASE_JAR_URL =
		"setup.database.jar.url";

	public static final String SETUP_DATABASE_URL = "setup.database.url";

	public static final String SETUP_LIFERAY_POOL_PROVIDER_JAR_NAME =
		"setup.liferay.pool.provider.jar.name";

	public static final String SETUP_LIFERAY_POOL_PROVIDER_JAR_URL =
		"setup.liferay.pool.provider.jar.url";

	public static final String SETUP_WIZARD_ADD_SAMPLE_DATA =
		"setup.wizard.add.sample.data";

	public static final String SETUP_WIZARD_ENABLED = "setup.wizard.enabled";

	public static final String SHAREPOINT_STORAGE_CLASS =
		"sharepoint.storage.class";

	public static final String SHAREPOINT_STORAGE_TOKENS =
		"sharepoint.storage.tokens";

	public static final String SHUTDOWN_PROGRAMMATICALLY_EXIT =
		"shutdown.programmatically.exit";

	public static final String SITEMAP_DISPLAY_TEMPLATES_CONFIG =
		"sitemap.display.templates.config";

	public static final String SITEMINDER_AUTH_ENABLED =
		"siteminder.auth.enabled";

	public static final String SITEMINDER_IMPORT_FROM_LDAP =
		"siteminder.import.from.ldap";

	public static final String SITEMINDER_USER_HEADER =
		"siteminder.user.header";

	public static final String
		SITES_CONTENT_SHARING_THROUGH_ADMINISTRATORS_ENABLED =
			"sites.content.sharing.through.administrators.enabled";

	public static final String SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED =
		"sites.content.sharing.with.children.enabled";

	public static final String SITES_CONTROL_PANEL_MEMBERS_VISIBLE =
		"sites.control.panel.members.visible";

	public static final String SITES_EMAIL_FROM_ADDRESS =
		"sites.email.from.address";

	public static final String SITES_EMAIL_FROM_NAME = "sites.email.from.name";

	public static final String SITES_EMAIL_MEMBERSHIP_REPLY_BODY =
		"sites.email.membership.reply.body";

	public static final String SITES_EMAIL_MEMBERSHIP_REPLY_SUBJECT =
		"sites.email.membership.reply.subject";

	public static final String SITES_EMAIL_MEMBERSHIP_REQUEST_BODY =
		"sites.email.membership.request.body";

	public static final String SITES_EMAIL_MEMBERSHIP_REQUEST_SUBJECT =
		"sites.email.membership.request.subject";

	public static final String SITES_FORM_ADD_ADVANCED =
		"sites.form.add.advanced";

	public static final String SITES_FORM_ADD_MAIN = "sites.form.add.main";

	public static final String SITES_FORM_ADD_MISCELLANEOUS =
		"sites.form.add.miscellaneous";

	public static final String SITES_FORM_ADD_SEO = "sites.form.add.seo";

	public static final String SITES_FORM_UPDATE_ADVANCED =
		"sites.form.update.advanced";

	public static final String SITES_FORM_UPDATE_MAIN =
		"sites.form.update.main";

	public static final String SITES_FORM_UPDATE_MISCELLANEOUS =
		"sites.form.update.miscellaneous";

	public static final String SITES_FORM_UPDATE_SEO = "sites.form.update.seo";

	public static final String SITES_FRIENDLY_URL_PAGE_NOT_FOUND =
		"sites.friendly.url.page.not.found";

	public static final String
		SITES_SHOW_INHERIT_CONTENT_SCOPE_FROM_PARENT_SITE =
			"sites.show.inherit.content.scope.from.parent.site";

	public static final String SITES_SITEMAP_DEFAULT_CHANGE_FREQUENCY =
		"sites.sitemap.default.change.frequency";

	public static final String SITES_SITEMAP_DEFAULT_PRIORITY =
		"sites.sitemap.default.priority";

	public static final String SOCIAL_ACTIVITY_COUNTER_PERIOD_LENGTH =
		"social.activity.counter.period.length";

	public static final String SOCIAL_ACTIVITY_FILTER_SEARCH_LIMIT =
		"social.activity.filter.search.limit";

	public static final String SOCIAL_ACTIVITY_LOCK_RETRY_DELAY =
		"social.activity.lock.retry.delay";

	public static final String SOCIAL_ACTIVITY_LOCK_TIMEOUT =
		"social.activity.lock.timeout";

	public static final String SOCIAL_ACTIVITY_SETS_BUNDLING_ENABLED =
		"social.activity.sets.bundling.enabled";

	public static final String SOCIAL_ACTIVITY_SETS_ENABLED =
		"social.activity.sets.enabled";

	public static final String SOCIAL_ACTIVITY_SETS_SELECTOR =
		"social.activity.sets.selector";

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static final String SOCIAL_BOOKMARK_ICON = "social.bookmark.icon";

	public static final String SOURCE_FORGE_MIRRORS = "source.forge.mirrors";

	public static final String SPRING_BEANFACTORY_STRICT_LIFECYCLE_ENABLED =
		"spring.beanfactory.strict.lifecycle.enabled";

	public static final String SPRING_CONFIGS = "spring.configs";

	public static final String
		SPRING_HIBERNATE_CONFIGURATION_PROXY_FACTORY_PRELOAD_CLASSLOADER_CLASSES =
			"spring.hibernate.configuration.proxy.factory.preload.classloader.classes";

	public static final String SPRING_HIBERNATE_SESSION_DELEGATED =
		"spring.hibernate.session.delegated";

	public static final String
		SPRING_HIBERNATE_SESSION_FACTORY_PRELOAD_CLASSLOADER_CLASSES =
			"spring.hibernate.session.factory.preload.classloader.classes";

	public static final String SPRING_HIBERNATE_SESSION_FACTORY_VERIFY =
		"spring.hibernate.session.factory.verify";

	public static final String
		STAGING_DRAFT_EXPORT_IMPORT_CONFIGURATION_CHECK_INTERVAL =
			"staging.draft.export.import.configuration.check.interval";

	public static final String
		STAGING_DRAFT_EXPORT_IMPORT_CONFIGURATION_CLEAN_UP_COUNT =
			"staging.draft.export.import.configuration.clean.up.count";

	public static final String STAGING_LIVE_GROUP_LOCKING_ENABLED =
		"staging.live.group.locking.enabled";

	public static final String STAGING_LIVE_GROUP_REMOTE_STAGING_ENABLED =
		"staging.live.group.remote.staging.enabled";

	public static final String STAGING_REMOTE_TRANSFER_BUFFER_SIZE =
		"staging.remote.transfer.buffer.size";

	public static final String STAGING_SYSTEM_EVENT_CHECK_INTERVAL =
		"staging.system.event.check.interval";

	public static final String STAGING_SYSTEM_EVENT_MAX_AGE =
		"staging.system.event.max.age";

	public static final String STRIP_CSS_SASS_ENABLED =
		"strip.css.sass.enabled";

	public static final String STRIP_IGNORE_PATHS = "strip.ignore.paths";

	public static final String STRIP_JS_LANGUAGE_ATTRIBUTE_SUPPORT_ENABLED =
		"strip.js.language.attribute.support.enabled";

	public static final String STRIP_MIME_TYPES = "strip.mime.types";

	public static final String SYSTEM_GROUPS = "system.groups";

	public static final String SYSTEM_ORGANIZATION_ROLES =
		"system.organization.roles";

	public static final String SYSTEM_ROLES = "system.roles";

	public static final String SYSTEM_SITE_ROLES = "system.site.roles";

	public static final String TABLE_MAPPER_CACHELESS_MAPPING_TABLE_NAMES =
		"table.mapper.cacheless.mapping.table.names";

	public static final String TEMPLATE_ENGINE_CACHE_ENABLED =
		"template.engine.cache.enabled";

	public static final String TEMPLATE_ENGINE_FREEMARKER_COMPANY_RESTRICT =
		"template.engine.freemarker.company.restrict";

	public static final String
		TEMPLATE_ENGINE_FREEMARKER_TRANSACTION_READ_ONLY =
			"template.engine.freemarker.transaction.read.only";

	public static final String TEMPLATE_ENGINE_SERVICE_LOCATOR_RESTRICT =
		"template.engine.service.locator.restrict";

	public static final String TERMS_OF_USE_REQUIRED = "terms.of.use.required";

	public static final String THEME_CSS_FAST_LOAD = "theme.css.fast.load";

	public static final String THEME_CSS_FAST_LOAD_CHECK_REQUEST_PARAMETER =
		"theme.css.fast.load.check.request.parameter";

	public static final String THEME_IMAGES_FAST_LOAD =
		"theme.images.fast.load";

	public static final String THEME_IMAGES_FAST_LOAD_CHECK_REQUEST_PARAMETER =
		"theme.images.fast.load.check.request.parameter";

	public static final String THEME_JSP_OVERRIDE_ENABLED =
		"theme.jsp.override.enabled";

	public static final String THEME_LOADER_NEW_THEME_ID_ON_IMPORT =
		"theme.loader.new.theme.id.on.import";

	public static final String THEME_LOADER_STORAGE_PATH =
		"theme.loader.storage.path";

	public static final String THEME_PORTLET_DECORATE_DEFAULT =
		"theme.portlet.decorate.default";

	public static final String THEME_PORTLET_SHARING_DEFAULT =
		"theme.portlet.sharing.default";

	public static final String THEME_SHORTCUT_ICON = "theme.shortcut.icon";

	public static final String THEME_SYNC_ON_GROUP = "theme.sync.on.group";

	public static final String THEME_VIRTUAL_PATH = "theme.virtual.path";

	public static final String THREAD_DUMP_SPEED_THRESHOLD =
		"thread.dump.speed.threshold";

	public static final String TIME_ZONES = "time.zones";

	public static final String TRANSACTION_ISOLATION_PORTAL =
		"transaction.isolation.portal";

	public static final String TRANSACTIONAL_CACHE_ENABLED =
		"transactional.cache.enable";

	public static final String TRANSACTIONAL_CACHE_NAMES =
		"transactional.cache.names";

	public static final String TRANSLATIONS_DISABLED = "translations.disabled";

	public static final String TRASH_ENABLED = "trash.enabled";

	public static final String TRASH_ENTRIES_MAX_AGE = "trash.entries.max.age";

	public static final String TRASH_ENTRY_CHECK_INTERVAL =
		"trash.entry.check.interval";

	public static final String TRASH_SEARCH_LIMIT = "trash.search.limit";

	public static final String TUNNEL_SERVLET_HIDE_EXCEPTION_DATA =
		"tunnel.servlet.hide.exception.data";

	public static final String TUNNELING_SERVLET_ENCRYPTION_ALGORITHM =
		"tunneling.servlet.encryption.algorithm";

	public static final String TUNNELING_SERVLET_SHARED_SECRET =
		"tunneling.servlet.shared.secret";

	public static final String TUNNELING_SERVLET_SHARED_SECRET_HEX =
		"tunneling.servlet.shared.secret.hex";

	public static final String TUNNELING_SERVLET_TIMEOUT =
		"tunneling.servlet.timeout";

	public static final String UNICODE_TEXT_NORMALIZER_FORM =
		"unicode.text.normalizer.form";

	public static final String UPGRADE_CONCURRENT_FETCH_SIZE =
		"upgrade.concurrent.fetch.size";

	public static final String UPGRADE_CONCURRENT_PROCESS_FUTURE_LIST_MAX_SIZE =
		"upgrade.concurrent.process.future.list.max.size";

	public static final String UPGRADE_DATABASE_AUTO_RUN =
		"upgrade.database.auto.run";

	public static final String UPGRADE_DATABASE_TRANSACTIONS_DISABLED =
		"upgrade.database.transactions.disabled";

	public static final String UPGRADE_LOG_CONTEXT_ENABLED =
		"upgrade.log.context.enabled";

	public static final String UPGRADE_REPORT_DIR = "upgrade.report.dir";

	public static final String UPGRADE_REPORT_DL_STORAGE_SIZE_TIMEOUT =
		"upgrade.report.dl.storage.size.timeout";

	public static final String UPGRADE_REPORT_ENABLED =
		"upgrade.report.enabled";

	public static final String UPGRADE_REPORT_SQL_STATEMENT_THRESHOLD =
		"upgrade.report.sql.statement.threshold";

	public static final String UPGRADE_REPORT_UPGRADE_PROCESS_THRESHOLD =
		"upgrade.report.upgrade.process.threshold";

	public static final String USER_GROUPS_NAME_ALLOW_NUMERIC =
		"user.groups.name.allow.numeric";

	public static final String USER_GROUPS_SEARCH_WITH_INDEX =
		"user.groups.search.with.index";

	public static final String USER_NOTIFICATION_EVENT_CONFIRMATION_ENABLED =
		"user.notification.event.confirmation.enabled";

	public static final String USERS_ADMIN_ORGANIZATION_COLUMN_LIMIT =
		"users.admin.organization.column.limit";

	public static final String USERS_ADMIN_ROLE_COLUMN_LIMIT =
		"users.admin.role.column.limit";

	public static final String USERS_ADMIN_USER_GROUP_COLUMN_LIMIT =
		"users.admin.user.group.column.limit";

	public static final String USERS_DELETE = "users.delete";

	public static final String USERS_EMAIL_ADDRESS_AUTO_SUFFIX =
		"users.email.address.auto.suffix";

	public static final String USERS_EMAIL_ADDRESS_GENERATOR =
		"users.email.address.generator";

	public static final String USERS_EMAIL_ADDRESS_REQUIRED =
		"users.email.address.required";

	public static final String USERS_EMAIL_ADDRESS_VALIDATOR =
		"users.email.address.validator";

	public static final String USERS_EXPORT_CSV_FIELDS =
		"users.export.csv.fields";

	public static final String USERS_FORM_ADD_IDENTIFICATION =
		"users.form.add.identification";

	public static final String USERS_FORM_ADD_MAIN = "users.form.add.main";

	public static final String USERS_FORM_ADD_MISCELLANEOUS =
		"users.form.add.miscellaneous";

	public static final String USERS_FORM_MY_ACCOUNT_IDENTIFICATION =
		"users.form.my.account.identification";

	public static final String USERS_FORM_MY_ACCOUNT_MAIN =
		"users.form.my.account.main";

	public static final String USERS_FORM_MY_ACCOUNT_MISCELLANEOUS =
		"users.form.my.account.miscellaneous";

	public static final String USERS_FORM_UPDATE_IDENTIFICATION =
		"users.form.update.identification";

	public static final String USERS_FORM_UPDATE_MAIN =
		"users.form.update.main";

	public static final String USERS_FORM_UPDATE_MISCELLANEOUS =
		"users.form.update.miscellaneous";

	public static final String USERS_FULL_NAME_GENERATOR =
		"users.full.name.generator";

	public static final String USERS_FULL_NAME_VALIDATOR =
		"users.full.name.validator";

	public static final String USERS_PROFILE_FRIENDLY_URL =
		"users.profile.friendly.url";

	public static final String USERS_REMINDER_QUERIES_CUSTOM_QUESTION_ENABLED =
		"users.reminder.queries.custom.question.enabled";

	public static final String USERS_REMINDER_QUERIES_DISPLAY_IN_PLAIN_TEXT =
		"users.reminder.queries.display.answer.in.plain.text";

	public static final String USERS_REMINDER_QUERIES_ENABLED =
		"users.reminder.queries.enabled";

	public static final String USERS_REMINDER_QUERIES_QUESTIONS =
		"users.reminder.queries.questions";

	public static final String USERS_REMINDER_QUERIES_REQUIRED =
		"users.reminder.queries.required";

	public static final String USERS_SCREEN_NAME_ALLOW_NUMERIC =
		"users.screen.name.allow.numeric";

	public static final String USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE =
		"users.screen.name.always.autogenerate";

	public static final String USERS_SCREEN_NAME_GENERATOR =
		"users.screen.name.generator";

	public static final String USERS_SCREEN_NAME_SPECIAL_CHARACTERS =
		"users.screen.name.special.characters";

	public static final String USERS_SCREEN_NAME_VALIDATOR =
		"users.screen.name.validator";

	public static final String USERS_SEARCH_WITH_INDEX =
		"users.search.with.index";

	public static final String USERS_UPDATE_LAST_LOGIN =
		"users.update.last.login";

	public static final String USERS_UPDATE_LAST_LOGIN_BATCH_INTERVAL =
		"users.update.last.login.batch.interval";

	public static final String USERS_UPDATE_LAST_LOGIN_BATCH_SIZE =
		"users.update.last.login.batch.size";

	public static final String USERS_UPDATE_USER_NAME =
		"users.update.user.name.";

	public static final String VALUE_OBJECT_ENTITY_CACHE_ENABLED =
		"value.object.entity.cache.enabled";

	public static final String VALUE_OBJECT_ENTITY_THREAD_LOCAL_CACHE_MAX_SIZE =
		"value.object.entity.thread.local.cache.max.size";

	public static final String VALUE_OBJECT_FINDER_CACHE_ENABLED =
		"value.object.finder.cache.enabled";

	public static final String VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD =
		"value.object.finder.cache.list.threshold";

	public static final String VALUE_OBJECT_FINDER_THREAD_LOCAL_CACHE_MAX_SIZE =
		"value.object.finder.thread.local.cache.max.size";

	public static final String VALUE_OBJECT_LISTENER = "value.object.listener.";

	public static final String VALUE_OBJECT_MVCC_ENTITY_CACHE_ENABLED =
		"value.object.mvcc.entity.cache.enabled";

	public static final String VERIFY_DATABASE_TRANSACTIONS_DISABLED =
		"verify.database.transactions.disabled";

	public static final String VIRTUAL_HOSTS_DEFAULT_SITE_NAME =
		"virtual.hosts.default.site.name";

	public static final String VIRTUAL_HOSTS_IGNORE_EXTENSIONS =
		"virtual.hosts.ignore.extensions";

	public static final String VIRTUAL_HOSTS_IGNORE_HOSTS =
		"virtual.hosts.ignore.hosts";

	public static final String VIRTUAL_HOSTS_IGNORE_PATHS =
		"virtual.hosts.ignore.paths";

	public static final String VIRTUAL_HOSTS_STRICT_ACCESS =
		"virtual.hosts.strict.access";

	public static final String VIRTUAL_HOSTS_VALID_HOSTS =
		"virtual.hosts.valid.hosts";

	public static final String WEB_SERVER_DISPLAY_NODE =
		"web.server.display.node";

	public static final String WEB_SERVER_FORWARDED_HOST_ENABLED =
		"web.server.forwarded.host.enabled";

	public static final String WEB_SERVER_FORWARDED_HOST_HEADER =
		"web.server.forwarded.host.header";

	public static final String WEB_SERVER_FORWARDED_PORT_ENABLED =
		"web.server.forwarded.port.enabled";

	public static final String WEB_SERVER_FORWARDED_PORT_HEADER =
		"web.server.forwarded.port.header";

	public static final String WEB_SERVER_FORWARDED_PROTOCOL_ENABLED =
		"web.server.forwarded.protocol.enabled";

	public static final String WEB_SERVER_FORWARDED_PROTOCOL_HEADER =
		"web.server.forwarded.protocol.header";

	public static final String WEB_SERVER_HOST = "web.server.host";

	public static final String WEB_SERVER_HTTP_PORT = "web.server.http.port";

	public static final String WEB_SERVER_HTTPS_PORT = "web.server.https.port";

	public static final String WEB_SERVER_PROTOCOL = "web.server.protocol";

	public static final String WEB_SERVER_PROXY_LEGACY_MODE =
		"web.server.proxy.legacy.mode";

	public static final String WEB_SERVER_SERVLET_ACCEPT_RANGES_MIME_TYPES =
		"web.server.servlet.accept.ranges.mime.types";

	public static final String WEB_SERVER_SERVLET_CHECK_IMAGE_GALLERY =
		"web.server.servlet.check.image.gallery";

	public static final String WEB_SERVER_SERVLET_DIRECTORY_INDEXING_ENABLED =
		"web.server.servlet.directory.indexing.enabled";

	public static final String WEB_SERVER_SERVLET_MAX_RANGE_FIELDS =
		"web.server.servlet.max.range.fields";

	public static final String WEB_SERVER_SERVLET_VERSION_VERBOSITY =
		"web.server.servlet.version.verbosity";

	public static final String WEBDAV_IGNORE = "webdav.ignore";

	public static final String WEBDAV_NONCE_CLUSTER_TIMEOUT =
		"webdav.nonce.cluster.timeout";

	public static final String WEBDAV_NONCE_EXPIRATION =
		"webdav.nonce.expiration";

	public static final String WEBDAV_SERVLET_HTTPS_REQUIRED =
		"webdav.servlet.https.required";

	public static final String WEBLOGIC_REQUEST_WRAP_NON_SERIALIZABLE =
		"weblogic.request.wrap.non.serializable";

	public static final String WIDGET_SERVLET_MAPPING =
		"widget.servlet.mapping";

	public static final String WORKFLOW_EMAIL_FROM_ADDRESS =
		"workflow.email.from.address";

	public static final String WORKFLOW_EMAIL_FROM_NAME =
		"workflow.email.from.name";

	public static final String XML_RPC_MAX_PARAMETERS =
		"xml.rpc.max.parameters";

	public static final String XML_SECURITY_ENABLED = "xml.security.enabled";

	public static final String XML_VALIDATION_ENABLED =
		"xml.validation.enabled";

	public static final String YUI_COMPRESSOR_CSS_LINE_BREAK =
		"yui.compressor.css.line.break";

	public static final String ZIP_FILE_WRITER_EXPORT_BUFFER_SIZE =
		"zip.file.writer.export.buffer.size";

}