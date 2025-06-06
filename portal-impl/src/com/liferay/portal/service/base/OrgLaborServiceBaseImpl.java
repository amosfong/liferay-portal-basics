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
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.OrgLaborService;
import com.liferay.portal.kernel.service.OrgLaborServiceUtil;
import com.liferay.portal.kernel.service.persistence.OrgLaborPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the org labor remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.OrgLaborServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.OrgLaborServiceImpl
 * @generated
 */
public abstract class OrgLaborServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, OrgLaborService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>OrgLaborService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>OrgLaborServiceUtil</code>.
	 */

	/**
	 * Returns the org labor local service.
	 *
	 * @return the org labor local service
	 */
	public com.liferay.portal.kernel.service.OrgLaborLocalService
		getOrgLaborLocalService() {

		return orgLaborLocalService;
	}

	/**
	 * Sets the org labor local service.
	 *
	 * @param orgLaborLocalService the org labor local service
	 */
	public void setOrgLaborLocalService(
		com.liferay.portal.kernel.service.OrgLaborLocalService
			orgLaborLocalService) {

		this.orgLaborLocalService = orgLaborLocalService;
	}

	/**
	 * Returns the org labor remote service.
	 *
	 * @return the org labor remote service
	 */
	public OrgLaborService getOrgLaborService() {
		return orgLaborService;
	}

	/**
	 * Sets the org labor remote service.
	 *
	 * @param orgLaborService the org labor remote service
	 */
	public void setOrgLaborService(OrgLaborService orgLaborService) {
		this.orgLaborService = orgLaborService;
	}

	/**
	 * Returns the org labor persistence.
	 *
	 * @return the org labor persistence
	 */
	public OrgLaborPersistence getOrgLaborPersistence() {
		return orgLaborPersistence;
	}

	/**
	 * Sets the org labor persistence.
	 *
	 * @param orgLaborPersistence the org labor persistence
	 */
	public void setOrgLaborPersistence(
		OrgLaborPersistence orgLaborPersistence) {

		this.orgLaborPersistence = orgLaborPersistence;
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
		OrgLaborServiceUtil.setService(orgLaborService);
	}

	public void destroy() {
		OrgLaborServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OrgLaborService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OrgLabor.class;
	}

	protected String getModelClassName() {
		return OrgLabor.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = orgLaborPersistence.getDataSource();

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
		type = com.liferay.portal.kernel.service.OrgLaborLocalService.class
	)
	protected com.liferay.portal.kernel.service.OrgLaborLocalService
		orgLaborLocalService;

	@BeanReference(type = OrgLaborService.class)
	protected OrgLaborService orgLaborService;

	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		OrgLaborServiceBaseImpl.class);

}