/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shop.by.diagram.service.base;

import com.liferay.commerce.shop.by.diagram.model.CSDiagramPin;
import com.liferay.commerce.shop.by.diagram.service.CSDiagramPinLocalService;
import com.liferay.commerce.shop.by.diagram.service.persistence.CSDiagramPinPersistence;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the cs diagram pin local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramPinLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramPinLocalServiceImpl
 * @generated
 */
public abstract class CSDiagramPinLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CSDiagramPinLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CSDiagramPinLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.shop.by.diagram.service.CSDiagramPinLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cs diagram pin to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CSDiagramPin addCSDiagramPin(CSDiagramPin csDiagramPin) {
		csDiagramPin.setNew(true);

		return csDiagramPinPersistence.update(csDiagramPin);
	}

	/**
	 * Creates a new cs diagram pin with the primary key. Does not add the cs diagram pin to the database.
	 *
	 * @param CSDiagramPinId the primary key for the new cs diagram pin
	 * @return the new cs diagram pin
	 */
	@Override
	@Transactional(enabled = false)
	public CSDiagramPin createCSDiagramPin(long CSDiagramPinId) {
		return csDiagramPinPersistence.create(CSDiagramPinId);
	}

	/**
	 * Deletes the cs diagram pin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CSDiagramPinId the primary key of the cs diagram pin
	 * @return the cs diagram pin that was removed
	 * @throws PortalException if a cs diagram pin with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CSDiagramPin deleteCSDiagramPin(long CSDiagramPinId)
		throws PortalException {

		return csDiagramPinPersistence.remove(CSDiagramPinId);
	}

	/**
	 * Deletes the cs diagram pin from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CSDiagramPin deleteCSDiagramPin(CSDiagramPin csDiagramPin) {
		return csDiagramPinPersistence.remove(csDiagramPin);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return csDiagramPinPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CSDiagramPin.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return csDiagramPinPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return csDiagramPinPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return csDiagramPinPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return csDiagramPinPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return csDiagramPinPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CSDiagramPin fetchCSDiagramPin(long CSDiagramPinId) {
		return csDiagramPinPersistence.fetchByPrimaryKey(CSDiagramPinId);
	}

	/**
	 * Returns the cs diagram pin with the primary key.
	 *
	 * @param CSDiagramPinId the primary key of the cs diagram pin
	 * @return the cs diagram pin
	 * @throws PortalException if a cs diagram pin with the primary key could not be found
	 */
	@Override
	public CSDiagramPin getCSDiagramPin(long CSDiagramPinId)
		throws PortalException {

		return csDiagramPinPersistence.findByPrimaryKey(CSDiagramPinId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(csDiagramPinLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CSDiagramPin.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CSDiagramPinId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			csDiagramPinLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CSDiagramPin.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CSDiagramPinId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(csDiagramPinLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CSDiagramPin.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CSDiagramPinId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return csDiagramPinPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement CSDiagramPinLocalServiceImpl#deleteCSDiagramPin(CSDiagramPin) to avoid orphaned data");
		}

		return csDiagramPinLocalService.deleteCSDiagramPin(
			(CSDiagramPin)persistedModel);
	}

	@Override
	public BasePersistence<CSDiagramPin> getBasePersistence() {
		return csDiagramPinPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return csDiagramPinPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the cs diagram pins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cs diagram pins
	 * @param end the upper bound of the range of cs diagram pins (not inclusive)
	 * @return the range of cs diagram pins
	 */
	@Override
	public List<CSDiagramPin> getCSDiagramPins(int start, int end) {
		return csDiagramPinPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cs diagram pins.
	 *
	 * @return the number of cs diagram pins
	 */
	@Override
	public int getCSDiagramPinsCount() {
		return csDiagramPinPersistence.countAll();
	}

	/**
	 * Updates the cs diagram pin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CSDiagramPin updateCSDiagramPin(CSDiagramPin csDiagramPin) {
		return csDiagramPinPersistence.update(csDiagramPin);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CSDiagramPinLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		csDiagramPinLocalService = (CSDiagramPinLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CSDiagramPinLocalService.class.getName();
	}

	@Override
	public CTPersistence<CSDiagramPin> getCTPersistence() {
		return csDiagramPinPersistence;
	}

	@Override
	public Class<CSDiagramPin> getModelClass() {
		return CSDiagramPin.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CSDiagramPin>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(csDiagramPinPersistence);
	}

	protected String getModelClassName() {
		return CSDiagramPin.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = csDiagramPinPersistence.getDataSource();

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

	protected CSDiagramPinLocalService csDiagramPinLocalService;

	@Reference
	protected CSDiagramPinPersistence csDiagramPinPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CSDiagramPinLocalServiceBaseImpl.class);

}