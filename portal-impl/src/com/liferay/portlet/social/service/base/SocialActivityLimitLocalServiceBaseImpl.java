/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.service.base;

import com.liferay.petra.function.UnsafeFunction;
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
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.model.SocialActivityLimit;
import com.liferay.social.kernel.service.SocialActivityLimitLocalService;
import com.liferay.social.kernel.service.SocialActivityLimitLocalServiceUtil;
import com.liferay.social.kernel.service.persistence.SocialActivityLimitPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the social activity limit local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.social.service.impl.SocialActivityLimitLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.impl.SocialActivityLimitLocalServiceImpl
 * @generated
 */
public abstract class SocialActivityLimitLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, SocialActivityLimitLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SocialActivityLimitLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SocialActivityLimitLocalServiceUtil</code>.
	 */

	/**
	 * Adds the social activity limit to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialActivityLimitLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialActivityLimit the social activity limit
	 * @return the social activity limit that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SocialActivityLimit addSocialActivityLimit(
		SocialActivityLimit socialActivityLimit) {

		socialActivityLimit.setNew(true);

		return socialActivityLimitPersistence.update(socialActivityLimit);
	}

	/**
	 * Creates a new social activity limit with the primary key. Does not add the social activity limit to the database.
	 *
	 * @param activityLimitId the primary key for the new social activity limit
	 * @return the new social activity limit
	 */
	@Override
	@Transactional(enabled = false)
	public SocialActivityLimit createSocialActivityLimit(long activityLimitId) {
		return socialActivityLimitPersistence.create(activityLimitId);
	}

	/**
	 * Deletes the social activity limit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialActivityLimitLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activityLimitId the primary key of the social activity limit
	 * @return the social activity limit that was removed
	 * @throws PortalException if a social activity limit with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SocialActivityLimit deleteSocialActivityLimit(long activityLimitId)
		throws PortalException {

		return socialActivityLimitPersistence.remove(activityLimitId);
	}

	/**
	 * Deletes the social activity limit from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialActivityLimitLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialActivityLimit the social activity limit
	 * @return the social activity limit that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SocialActivityLimit deleteSocialActivityLimit(
		SocialActivityLimit socialActivityLimit) {

		return socialActivityLimitPersistence.remove(socialActivityLimit);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return socialActivityLimitPersistence.dslQuery(dslQuery);
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
			SocialActivityLimit.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return socialActivityLimitPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialActivityLimitModelImpl</code>.
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

		return socialActivityLimitPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialActivityLimitModelImpl</code>.
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

		return socialActivityLimitPersistence.findWithDynamicQuery(
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
		return socialActivityLimitPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return socialActivityLimitPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SocialActivityLimit fetchSocialActivityLimit(long activityLimitId) {
		return socialActivityLimitPersistence.fetchByPrimaryKey(
			activityLimitId);
	}

	/**
	 * Returns the social activity limit with the primary key.
	 *
	 * @param activityLimitId the primary key of the social activity limit
	 * @return the social activity limit
	 * @throws PortalException if a social activity limit with the primary key could not be found
	 */
	@Override
	public SocialActivityLimit getSocialActivityLimit(long activityLimitId)
		throws PortalException {

		return socialActivityLimitPersistence.findByPrimaryKey(activityLimitId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			socialActivityLimitLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SocialActivityLimit.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("activityLimitId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			socialActivityLimitLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			SocialActivityLimit.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"activityLimitId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			socialActivityLimitLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SocialActivityLimit.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("activityLimitId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return socialActivityLimitPersistence.create(
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
				"Implement SocialActivityLimitLocalServiceImpl#deleteSocialActivityLimit(SocialActivityLimit) to avoid orphaned data");
		}

		return socialActivityLimitLocalService.deleteSocialActivityLimit(
			(SocialActivityLimit)persistedModel);
	}

	@Override
	public BasePersistence<SocialActivityLimit> getBasePersistence() {
		return socialActivityLimitPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return socialActivityLimitPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the social activity limits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialActivityLimitModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity limits
	 * @param end the upper bound of the range of social activity limits (not inclusive)
	 * @return the range of social activity limits
	 */
	@Override
	public List<SocialActivityLimit> getSocialActivityLimits(
		int start, int end) {

		return socialActivityLimitPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of social activity limits.
	 *
	 * @return the number of social activity limits
	 */
	@Override
	public int getSocialActivityLimitsCount() {
		return socialActivityLimitPersistence.countAll();
	}

	/**
	 * Updates the social activity limit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialActivityLimitLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialActivityLimit the social activity limit
	 * @return the social activity limit that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SocialActivityLimit updateSocialActivityLimit(
		SocialActivityLimit socialActivityLimit) {

		return socialActivityLimitPersistence.update(socialActivityLimit);
	}

	/**
	 * Returns the social activity limit local service.
	 *
	 * @return the social activity limit local service
	 */
	public SocialActivityLimitLocalService
		getSocialActivityLimitLocalService() {

		return socialActivityLimitLocalService;
	}

	/**
	 * Sets the social activity limit local service.
	 *
	 * @param socialActivityLimitLocalService the social activity limit local service
	 */
	public void setSocialActivityLimitLocalService(
		SocialActivityLimitLocalService socialActivityLimitLocalService) {

		this.socialActivityLimitLocalService = socialActivityLimitLocalService;
	}

	/**
	 * Returns the social activity limit persistence.
	 *
	 * @return the social activity limit persistence
	 */
	public SocialActivityLimitPersistence getSocialActivityLimitPersistence() {
		return socialActivityLimitPersistence;
	}

	/**
	 * Sets the social activity limit persistence.
	 *
	 * @param socialActivityLimitPersistence the social activity limit persistence
	 */
	public void setSocialActivityLimitPersistence(
		SocialActivityLimitPersistence socialActivityLimitPersistence) {

		this.socialActivityLimitPersistence = socialActivityLimitPersistence;
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
		SocialActivityLimitLocalServiceUtil.setService(
			socialActivityLimitLocalService);
	}

	public void destroy() {
		SocialActivityLimitLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SocialActivityLimitLocalService.class.getName();
	}

	@Override
	public CTPersistence<SocialActivityLimit> getCTPersistence() {
		return socialActivityLimitPersistence;
	}

	@Override
	public Class<SocialActivityLimit> getModelClass() {
		return SocialActivityLimit.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<SocialActivityLimit>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(socialActivityLimitPersistence);
	}

	protected String getModelClassName() {
		return SocialActivityLimit.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				socialActivityLimitPersistence.getDataSource();

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

	@BeanReference(type = SocialActivityLimitLocalService.class)
	protected SocialActivityLimitLocalService socialActivityLimitLocalService;

	@BeanReference(type = SocialActivityLimitPersistence.class)
	protected SocialActivityLimitPersistence socialActivityLimitPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		SocialActivityLimitLocalServiceBaseImpl.class);

}