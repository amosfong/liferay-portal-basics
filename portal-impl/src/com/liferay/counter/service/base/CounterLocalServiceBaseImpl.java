/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.counter.service.base;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.counter.kernel.service.persistence.CounterFinder;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;

/**
 * Provides the base implementation for the counter local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.counter.service.impl.CounterLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.counter.service.impl.CounterLocalServiceImpl
 * @generated
 */
public abstract class CounterLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CounterLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CounterLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CounterLocalServiceUtil</code>.
	 */

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		CounterLocalService counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the counter finder.
	 *
	 * @return the counter finder
	 */
	public CounterFinder getCounterFinder() {
		return counterFinder;
	}

	/**
	 * Sets the counter finder.
	 *
	 * @param counterFinder the counter finder
	 */
	public void setCounterFinder(CounterFinder counterFinder) {
		this.counterFinder = counterFinder;
	}

	public void afterPropertiesSet() {
		CounterLocalServiceUtil.setService(counterLocalService);
	}

	public void destroy() {
		CounterLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CounterLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Counter.class;
	}

	protected String getModelClassName() {
		return Counter.class.getName();
	}

	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;

	@BeanReference(type = CounterFinder.class)
	protected CounterFinder counterFinder;

	private static final Log _log = LogFactoryUtil.getLog(
		CounterLocalServiceBaseImpl.class);

}