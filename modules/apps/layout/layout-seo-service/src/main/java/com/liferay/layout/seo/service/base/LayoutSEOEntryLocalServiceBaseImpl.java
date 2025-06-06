/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.layout.seo.service.persistence.LayoutSEOEntryPersistence;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout seo entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.layout.seo.service.impl.LayoutSEOEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.seo.service.impl.LayoutSEOEntryLocalServiceImpl
 * @generated
 */
public abstract class LayoutSEOEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, LayoutSEOEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutSEOEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.layout.seo.service.LayoutSEOEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the layout seo entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntry the layout seo entry
	 * @return the layout seo entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutSEOEntry addLayoutSEOEntry(LayoutSEOEntry layoutSEOEntry) {
		layoutSEOEntry.setNew(true);

		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	/**
	 * Creates a new layout seo entry with the primary key. Does not add the layout seo entry to the database.
	 *
	 * @param layoutSEOEntryId the primary key for the new layout seo entry
	 * @return the new layout seo entry
	 */
	@Override
	@Transactional(enabled = false)
	public LayoutSEOEntry createLayoutSEOEntry(long layoutSEOEntryId) {
		return layoutSEOEntryPersistence.create(layoutSEOEntryId);
	}

	/**
	 * Deletes the layout seo entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntryId the primary key of the layout seo entry
	 * @return the layout seo entry that was removed
	 * @throws PortalException if a layout seo entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutSEOEntry deleteLayoutSEOEntry(long layoutSEOEntryId)
		throws PortalException {

		return layoutSEOEntryPersistence.remove(layoutSEOEntryId);
	}

	/**
	 * Deletes the layout seo entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntry the layout seo entry
	 * @return the layout seo entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutSEOEntry deleteLayoutSEOEntry(LayoutSEOEntry layoutSEOEntry) {
		return layoutSEOEntryPersistence.remove(layoutSEOEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return layoutSEOEntryPersistence.dslQuery(dslQuery);
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
			LayoutSEOEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return layoutSEOEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryModelImpl</code>.
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

		return layoutSEOEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryModelImpl</code>.
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

		return layoutSEOEntryPersistence.findWithDynamicQuery(
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
		return layoutSEOEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return layoutSEOEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LayoutSEOEntry fetchLayoutSEOEntry(long layoutSEOEntryId) {
		return layoutSEOEntryPersistence.fetchByPrimaryKey(layoutSEOEntryId);
	}

	/**
	 * Returns the layout seo entry matching the UUID and group.
	 *
	 * @param uuid the layout seo entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout seo entry, or <code>null</code> if a matching layout seo entry could not be found
	 */
	@Override
	public LayoutSEOEntry fetchLayoutSEOEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return layoutSEOEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the layout seo entry with the primary key.
	 *
	 * @param layoutSEOEntryId the primary key of the layout seo entry
	 * @return the layout seo entry
	 * @throws PortalException if a layout seo entry with the primary key could not be found
	 */
	@Override
	public LayoutSEOEntry getLayoutSEOEntry(long layoutSEOEntryId)
		throws PortalException {

		return layoutSEOEntryPersistence.findByPrimaryKey(layoutSEOEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(layoutSEOEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutSEOEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutSEOEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			layoutSEOEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LayoutSEOEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutSEOEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(layoutSEOEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutSEOEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutSEOEntryId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<LayoutSEOEntry>() {

				@Override
				public void performAction(LayoutSEOEntry layoutSEOEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, layoutSEOEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(LayoutSEOEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutSEOEntryPersistence.create(
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
				"Implement LayoutSEOEntryLocalServiceImpl#deleteLayoutSEOEntry(LayoutSEOEntry) to avoid orphaned data");
		}

		return layoutSEOEntryLocalService.deleteLayoutSEOEntry(
			(LayoutSEOEntry)persistedModel);
	}

	@Override
	public BasePersistence<LayoutSEOEntry> getBasePersistence() {
		return layoutSEOEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutSEOEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the layout seo entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout seo entries
	 * @param companyId the primary key of the company
	 * @return the matching layout seo entries, or an empty list if no matches were found
	 */
	@Override
	public List<LayoutSEOEntry> getLayoutSEOEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return layoutSEOEntryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of layout seo entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout seo entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of layout seo entries
	 * @param end the upper bound of the range of layout seo entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching layout seo entries, or an empty list if no matches were found
	 */
	@Override
	public List<LayoutSEOEntry> getLayoutSEOEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutSEOEntry> orderByComparator) {

		return layoutSEOEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the layout seo entry matching the UUID and group.
	 *
	 * @param uuid the layout seo entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout seo entry
	 * @throws PortalException if a matching layout seo entry could not be found
	 */
	@Override
	public LayoutSEOEntry getLayoutSEOEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return layoutSEOEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the layout seo entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.seo.model.impl.LayoutSEOEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout seo entries
	 * @param end the upper bound of the range of layout seo entries (not inclusive)
	 * @return the range of layout seo entries
	 */
	@Override
	public List<LayoutSEOEntry> getLayoutSEOEntries(int start, int end) {
		return layoutSEOEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layout seo entries.
	 *
	 * @return the number of layout seo entries
	 */
	@Override
	public int getLayoutSEOEntriesCount() {
		return layoutSEOEntryPersistence.countAll();
	}

	/**
	 * Updates the layout seo entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutSEOEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutSEOEntry the layout seo entry
	 * @return the layout seo entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutSEOEntry updateLayoutSEOEntry(LayoutSEOEntry layoutSEOEntry) {
		return layoutSEOEntryPersistence.update(layoutSEOEntry);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			LayoutSEOEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		layoutSEOEntryLocalService = (LayoutSEOEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutSEOEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LayoutSEOEntry.class;
	}

	protected String getModelClassName() {
		return LayoutSEOEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutSEOEntryPersistence.getDataSource();

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

	protected LayoutSEOEntryLocalService layoutSEOEntryLocalService;

	@Reference
	protected LayoutSEOEntryPersistence layoutSEOEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutSEOEntryLocalServiceBaseImpl.class);

}