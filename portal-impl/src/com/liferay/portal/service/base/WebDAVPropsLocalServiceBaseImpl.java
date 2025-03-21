/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.model.WebDAVProps;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.WebDAVPropsLocalService;
import com.liferay.portal.kernel.service.WebDAVPropsLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.WebDAVPropsPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the web dav props local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.WebDAVPropsLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.WebDAVPropsLocalServiceImpl
 * @generated
 */
public abstract class WebDAVPropsLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, WebDAVPropsLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WebDAVPropsLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>WebDAVPropsLocalServiceUtil</code>.
	 */

	/**
	 * Adds the web dav props to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebDAVPropsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param webDAVProps the web dav props
	 * @return the web dav props that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WebDAVProps addWebDAVProps(WebDAVProps webDAVProps) {
		webDAVProps.setNew(true);

		return webDAVPropsPersistence.update(webDAVProps);
	}

	/**
	 * Creates a new web dav props with the primary key. Does not add the web dav props to the database.
	 *
	 * @param webDavPropsId the primary key for the new web dav props
	 * @return the new web dav props
	 */
	@Override
	@Transactional(enabled = false)
	public WebDAVProps createWebDAVProps(long webDavPropsId) {
		return webDAVPropsPersistence.create(webDavPropsId);
	}

	/**
	 * Deletes the web dav props with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebDAVPropsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param webDavPropsId the primary key of the web dav props
	 * @return the web dav props that was removed
	 * @throws PortalException if a web dav props with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WebDAVProps deleteWebDAVProps(long webDavPropsId)
		throws PortalException {

		return webDAVPropsPersistence.remove(webDavPropsId);
	}

	/**
	 * Deletes the web dav props from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebDAVPropsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param webDAVProps the web dav props
	 * @return the web dav props that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WebDAVProps deleteWebDAVProps(WebDAVProps webDAVProps) {
		return webDAVPropsPersistence.remove(webDAVProps);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return webDAVPropsPersistence.dslQuery(dslQuery);
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
			WebDAVProps.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return webDAVPropsPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebDAVPropsModelImpl</code>.
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

		return webDAVPropsPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebDAVPropsModelImpl</code>.
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

		return webDAVPropsPersistence.findWithDynamicQuery(
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
		return webDAVPropsPersistence.countWithDynamicQuery(dynamicQuery);
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

		return webDAVPropsPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public WebDAVProps fetchWebDAVProps(long webDavPropsId) {
		return webDAVPropsPersistence.fetchByPrimaryKey(webDavPropsId);
	}

	/**
	 * Returns the web dav props with the primary key.
	 *
	 * @param webDavPropsId the primary key of the web dav props
	 * @return the web dav props
	 * @throws PortalException if a web dav props with the primary key could not be found
	 */
	@Override
	public WebDAVProps getWebDAVProps(long webDavPropsId)
		throws PortalException {

		return webDAVPropsPersistence.findByPrimaryKey(webDavPropsId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(webDAVPropsLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WebDAVProps.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("webDavPropsId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			webDAVPropsLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(WebDAVProps.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"webDavPropsId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(webDAVPropsLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WebDAVProps.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("webDavPropsId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return webDAVPropsPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement WebDAVPropsLocalServiceImpl#deleteWebDAVProps(WebDAVProps) to avoid orphaned data");
		}

		return webDAVPropsLocalService.deleteWebDAVProps(
			(WebDAVProps)persistedModel);
	}

	@Override
	public BasePersistence<WebDAVProps> getBasePersistence() {
		return webDAVPropsPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return webDAVPropsPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the web dav propses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebDAVPropsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of web dav propses
	 * @param end the upper bound of the range of web dav propses (not inclusive)
	 * @return the range of web dav propses
	 */
	@Override
	public List<WebDAVProps> getWebDAVPropses(int start, int end) {
		return webDAVPropsPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of web dav propses.
	 *
	 * @return the number of web dav propses
	 */
	@Override
	public int getWebDAVPropsesCount() {
		return webDAVPropsPersistence.countAll();
	}

	/**
	 * Updates the web dav props in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebDAVPropsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param webDAVProps the web dav props
	 * @return the web dav props that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WebDAVProps updateWebDAVProps(WebDAVProps webDAVProps) {
		return webDAVPropsPersistence.update(webDAVProps);
	}

	/**
	 * Returns the web dav props local service.
	 *
	 * @return the web dav props local service
	 */
	public WebDAVPropsLocalService getWebDAVPropsLocalService() {
		return webDAVPropsLocalService;
	}

	/**
	 * Sets the web dav props local service.
	 *
	 * @param webDAVPropsLocalService the web dav props local service
	 */
	public void setWebDAVPropsLocalService(
		WebDAVPropsLocalService webDAVPropsLocalService) {

		this.webDAVPropsLocalService = webDAVPropsLocalService;
	}

	/**
	 * Returns the web dav props persistence.
	 *
	 * @return the web dav props persistence
	 */
	public WebDAVPropsPersistence getWebDAVPropsPersistence() {
		return webDAVPropsPersistence;
	}

	/**
	 * Sets the web dav props persistence.
	 *
	 * @param webDAVPropsPersistence the web dav props persistence
	 */
	public void setWebDAVPropsPersistence(
		WebDAVPropsPersistence webDAVPropsPersistence) {

		this.webDAVPropsPersistence = webDAVPropsPersistence;
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
		WebDAVPropsLocalServiceUtil.setService(webDAVPropsLocalService);
	}

	public void destroy() {
		WebDAVPropsLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WebDAVPropsLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WebDAVProps.class;
	}

	protected String getModelClassName() {
		return WebDAVProps.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = webDAVPropsPersistence.getDataSource();

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

	@BeanReference(type = WebDAVPropsLocalService.class)
	protected WebDAVPropsLocalService webDAVPropsLocalService;

	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		WebDAVPropsLocalServiceBaseImpl.class);

}