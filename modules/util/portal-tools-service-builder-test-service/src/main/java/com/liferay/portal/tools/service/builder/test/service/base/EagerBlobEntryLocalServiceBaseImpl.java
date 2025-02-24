/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.base;

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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.EagerBlobEntry;
import com.liferay.portal.tools.service.builder.test.service.EagerBlobEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.EagerBlobEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.EagerBlobEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the eager blob entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.EagerBlobEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.EagerBlobEntryLocalServiceImpl
 * @generated
 */
public abstract class EagerBlobEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements EagerBlobEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>EagerBlobEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>EagerBlobEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the eager blob entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EagerBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eagerBlobEntry the eager blob entry
	 * @return the eager blob entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EagerBlobEntry addEagerBlobEntry(EagerBlobEntry eagerBlobEntry) {
		eagerBlobEntry.setNew(true);

		return eagerBlobEntryPersistence.update(eagerBlobEntry);
	}

	/**
	 * Creates a new eager blob entry with the primary key. Does not add the eager blob entry to the database.
	 *
	 * @param eagerBlobEntryId the primary key for the new eager blob entry
	 * @return the new eager blob entry
	 */
	@Override
	@Transactional(enabled = false)
	public EagerBlobEntry createEagerBlobEntry(long eagerBlobEntryId) {
		return eagerBlobEntryPersistence.create(eagerBlobEntryId);
	}

	/**
	 * Deletes the eager blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EagerBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eagerBlobEntryId the primary key of the eager blob entry
	 * @return the eager blob entry that was removed
	 * @throws PortalException if a eager blob entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public EagerBlobEntry deleteEagerBlobEntry(long eagerBlobEntryId)
		throws PortalException {

		return eagerBlobEntryPersistence.remove(eagerBlobEntryId);
	}

	/**
	 * Deletes the eager blob entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EagerBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eagerBlobEntry the eager blob entry
	 * @return the eager blob entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public EagerBlobEntry deleteEagerBlobEntry(EagerBlobEntry eagerBlobEntry) {
		return eagerBlobEntryPersistence.remove(eagerBlobEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return eagerBlobEntryPersistence.dslQuery(dslQuery);
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
			EagerBlobEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return eagerBlobEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.EagerBlobEntryModelImpl</code>.
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

		return eagerBlobEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.EagerBlobEntryModelImpl</code>.
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

		return eagerBlobEntryPersistence.findWithDynamicQuery(
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
		return eagerBlobEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return eagerBlobEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public EagerBlobEntry fetchEagerBlobEntry(long eagerBlobEntryId) {
		return eagerBlobEntryPersistence.fetchByPrimaryKey(eagerBlobEntryId);
	}

	/**
	 * Returns the eager blob entry matching the UUID and group.
	 *
	 * @param uuid the eager blob entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching eager blob entry, or <code>null</code> if a matching eager blob entry could not be found
	 */
	@Override
	public EagerBlobEntry fetchEagerBlobEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return eagerBlobEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the eager blob entry with the primary key.
	 *
	 * @param eagerBlobEntryId the primary key of the eager blob entry
	 * @return the eager blob entry
	 * @throws PortalException if a eager blob entry with the primary key could not be found
	 */
	@Override
	public EagerBlobEntry getEagerBlobEntry(long eagerBlobEntryId)
		throws PortalException {

		return eagerBlobEntryPersistence.findByPrimaryKey(eagerBlobEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(eagerBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(EagerBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("eagerBlobEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			eagerBlobEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(EagerBlobEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"eagerBlobEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(eagerBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(EagerBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("eagerBlobEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return eagerBlobEntryPersistence.create(
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
				"Implement EagerBlobEntryLocalServiceImpl#deleteEagerBlobEntry(EagerBlobEntry) to avoid orphaned data");
		}

		return eagerBlobEntryLocalService.deleteEagerBlobEntry(
			(EagerBlobEntry)persistedModel);
	}

	@Override
	public BasePersistence<EagerBlobEntry> getBasePersistence() {
		return eagerBlobEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return eagerBlobEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the eager blob entry matching the UUID and group.
	 *
	 * @param uuid the eager blob entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching eager blob entry
	 * @throws PortalException if a matching eager blob entry could not be found
	 */
	@Override
	public EagerBlobEntry getEagerBlobEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return eagerBlobEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the eager blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.EagerBlobEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of eager blob entries
	 * @param end the upper bound of the range of eager blob entries (not inclusive)
	 * @return the range of eager blob entries
	 */
	@Override
	public List<EagerBlobEntry> getEagerBlobEntries(int start, int end) {
		return eagerBlobEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of eager blob entries.
	 *
	 * @return the number of eager blob entries
	 */
	@Override
	public int getEagerBlobEntriesCount() {
		return eagerBlobEntryPersistence.countAll();
	}

	/**
	 * Updates the eager blob entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EagerBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eagerBlobEntry the eager blob entry
	 * @return the eager blob entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EagerBlobEntry updateEagerBlobEntry(EagerBlobEntry eagerBlobEntry) {
		return eagerBlobEntryPersistence.update(eagerBlobEntry);
	}

	/**
	 * Returns the eager blob entry local service.
	 *
	 * @return the eager blob entry local service
	 */
	public EagerBlobEntryLocalService getEagerBlobEntryLocalService() {
		return eagerBlobEntryLocalService;
	}

	/**
	 * Sets the eager blob entry local service.
	 *
	 * @param eagerBlobEntryLocalService the eager blob entry local service
	 */
	public void setEagerBlobEntryLocalService(
		EagerBlobEntryLocalService eagerBlobEntryLocalService) {

		this.eagerBlobEntryLocalService = eagerBlobEntryLocalService;
	}

	/**
	 * Returns the eager blob entry persistence.
	 *
	 * @return the eager blob entry persistence
	 */
	public EagerBlobEntryPersistence getEagerBlobEntryPersistence() {
		return eagerBlobEntryPersistence;
	}

	/**
	 * Sets the eager blob entry persistence.
	 *
	 * @param eagerBlobEntryPersistence the eager blob entry persistence
	 */
	public void setEagerBlobEntryPersistence(
		EagerBlobEntryPersistence eagerBlobEntryPersistence) {

		this.eagerBlobEntryPersistence = eagerBlobEntryPersistence;
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
		EagerBlobEntryLocalServiceUtil.setService(eagerBlobEntryLocalService);
	}

	public void destroy() {
		EagerBlobEntryLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return EagerBlobEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return EagerBlobEntry.class;
	}

	protected String getModelClassName() {
		return EagerBlobEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = eagerBlobEntryPersistence.getDataSource();

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

	@BeanReference(type = EagerBlobEntryLocalService.class)
	protected EagerBlobEntryLocalService eagerBlobEntryLocalService;

	@BeanReference(type = EagerBlobEntryPersistence.class)
	protected EagerBlobEntryPersistence eagerBlobEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		EagerBlobEntryLocalServiceBaseImpl.class);

}