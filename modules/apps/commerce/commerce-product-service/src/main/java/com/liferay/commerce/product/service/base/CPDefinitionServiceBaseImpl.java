/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.base;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.persistence.CPDefinitionFinder;
import com.liferay.commerce.product.service.persistence.CPDefinitionLocalizationPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the cp definition remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl
 * @generated
 */
public abstract class CPDefinitionServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, CPDefinitionService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDefinitionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.service.CPDefinitionServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CPDefinitionService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		cpDefinitionService = (CPDefinitionService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPDefinitionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDefinition.class;
	}

	protected String getModelClassName() {
		return CPDefinition.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = cpDefinitionPersistence.getDataSource();

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

	@Reference
	protected com.liferay.commerce.product.service.CPDefinitionLocalService
		cpDefinitionLocalService;

	protected CPDefinitionService cpDefinitionService;

	@Reference
	protected CPDefinitionPersistence cpDefinitionPersistence;

	@Reference
	protected CPDefinitionFinder cpDefinitionFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected CPDefinitionLocalizationPersistence
		cpDefinitionLocalizationPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionServiceBaseImpl.class);

}