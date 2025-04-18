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
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.service.ReleaseLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ReleasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the release local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.ReleaseLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.ReleaseLocalServiceImpl
 * @generated
 */
public abstract class ReleaseLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, ReleaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ReleaseLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ReleaseLocalServiceUtil</code>.
	 */

	/**
	 * Adds the release to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Release addRelease(Release release) {
		release.setNew(true);

		return releasePersistence.update(release);
	}

	/**
	 * Creates a new release with the primary key. Does not add the release to the database.
	 *
	 * @param releaseId the primary key for the new release
	 * @return the new release
	 */
	@Override
	@Transactional(enabled = false)
	public Release createRelease(long releaseId) {
		return releasePersistence.create(releaseId);
	}

	/**
	 * Deletes the release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param releaseId the primary key of the release
	 * @return the release that was removed
	 * @throws PortalException if a release with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Release deleteRelease(long releaseId) throws PortalException {
		return releasePersistence.remove(releaseId);
	}

	/**
	 * Deletes the release from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Release deleteRelease(Release release) {
		return releasePersistence.remove(release);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return releasePersistence.dslQuery(dslQuery);
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
			Release.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return releasePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
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

		return releasePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
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

		return releasePersistence.findWithDynamicQuery(
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
		return releasePersistence.countWithDynamicQuery(dynamicQuery);
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

		return releasePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Release fetchRelease(long releaseId) {
		return releasePersistence.fetchByPrimaryKey(releaseId);
	}

	/**
	 * Returns the release with the primary key.
	 *
	 * @param releaseId the primary key of the release
	 * @return the release
	 * @throws PortalException if a release with the primary key could not be found
	 */
	@Override
	public Release getRelease(long releaseId) throws PortalException {
		return releasePersistence.findByPrimaryKey(releaseId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(releaseLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Release.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("releaseId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			releaseLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Release.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("releaseId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(releaseLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Release.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("releaseId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return releasePersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement ReleaseLocalServiceImpl#deleteRelease(Release) to avoid orphaned data");
		}

		return releaseLocalService.deleteRelease((Release)persistedModel);
	}

	@Override
	public BasePersistence<Release> getBasePersistence() {
		return releasePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return releasePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of releases
	 * @param end the upper bound of the range of releases (not inclusive)
	 * @return the range of releases
	 */
	@Override
	public List<Release> getReleases(int start, int end) {
		return releasePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of releases.
	 *
	 * @return the number of releases
	 */
	@Override
	public int getReleasesCount() {
		return releasePersistence.countAll();
	}

	/**
	 * Updates the release in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Release updateRelease(Release release) {
		return releasePersistence.update(release);
	}

	/**
	 * Returns the release local service.
	 *
	 * @return the release local service
	 */
	public ReleaseLocalService getReleaseLocalService() {
		return releaseLocalService;
	}

	/**
	 * Sets the release local service.
	 *
	 * @param releaseLocalService the release local service
	 */
	public void setReleaseLocalService(
		ReleaseLocalService releaseLocalService) {

		this.releaseLocalService = releaseLocalService;
	}

	/**
	 * Returns the release persistence.
	 *
	 * @return the release persistence
	 */
	public ReleasePersistence getReleasePersistence() {
		return releasePersistence;
	}

	/**
	 * Sets the release persistence.
	 *
	 * @param releasePersistence the release persistence
	 */
	public void setReleasePersistence(ReleasePersistence releasePersistence) {
		this.releasePersistence = releasePersistence;
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
		ReleaseLocalServiceUtil.setService(releaseLocalService);
	}

	public void destroy() {
		ReleaseLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ReleaseLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Release.class;
	}

	protected String getModelClassName() {
		return Release.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = releasePersistence.getDataSource();

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

	@BeanReference(type = ReleaseLocalService.class)
	protected ReleaseLocalService releaseLocalService;

	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ReleaseLocalServiceBaseImpl.class);

}