/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.WebsiteService;
import com.liferay.portal.kernel.service.WebsiteServiceUtil;
import com.liferay.portal.kernel.service.persistence.WebsitePersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the website remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.WebsiteServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.WebsiteServiceImpl
 * @generated
 */
public abstract class WebsiteServiceBaseImpl
	extends BaseServiceImpl implements IdentifiableOSGiService, WebsiteService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WebsiteService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>WebsiteServiceUtil</code>.
	 */

	/**
	 * Returns the website local service.
	 *
	 * @return the website local service
	 */
	public com.liferay.portal.kernel.service.WebsiteLocalService
		getWebsiteLocalService() {

		return websiteLocalService;
	}

	/**
	 * Sets the website local service.
	 *
	 * @param websiteLocalService the website local service
	 */
	public void setWebsiteLocalService(
		com.liferay.portal.kernel.service.WebsiteLocalService
			websiteLocalService) {

		this.websiteLocalService = websiteLocalService;
	}

	/**
	 * Returns the website remote service.
	 *
	 * @return the website remote service
	 */
	public WebsiteService getWebsiteService() {
		return websiteService;
	}

	/**
	 * Sets the website remote service.
	 *
	 * @param websiteService the website remote service
	 */
	public void setWebsiteService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	/**
	 * Returns the website persistence.
	 *
	 * @return the website persistence
	 */
	public WebsitePersistence getWebsitePersistence() {
		return websitePersistence;
	}

	/**
	 * Sets the website persistence.
	 *
	 * @param websitePersistence the website persistence
	 */
	public void setWebsitePersistence(WebsitePersistence websitePersistence) {
		this.websitePersistence = websitePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		WebsiteServiceUtil.setService(websiteService);
	}

	public void destroy() {
		WebsiteServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WebsiteService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Website.class;
	}

	protected String getModelClassName() {
		return Website.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = websitePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(
		type = com.liferay.portal.kernel.service.WebsiteLocalService.class
	)
	protected com.liferay.portal.kernel.service.WebsiteLocalService
		websiteLocalService;

	@BeanReference(type = WebsiteService.class)
	protected WebsiteService websiteService;

	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		WebsiteServiceBaseImpl.class);

}