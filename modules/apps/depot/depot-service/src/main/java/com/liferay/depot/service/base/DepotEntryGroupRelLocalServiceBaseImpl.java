/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.depot.service.base;

import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.service.DepotEntryGroupRelLocalService;
import com.liferay.depot.service.persistence.DepotEntryGroupRelPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * Provides the base implementation for the depot entry group rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.depot.service.impl.DepotEntryGroupRelLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.depot.service.impl.DepotEntryGroupRelLocalServiceImpl
 * @generated
 */
public abstract class DepotEntryGroupRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DepotEntryGroupRelLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DepotEntryGroupRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.depot.service.DepotEntryGroupRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the depot entry group rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotEntryGroupRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotEntryGroupRel the depot entry group rel
	 * @return the depot entry group rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DepotEntryGroupRel addDepotEntryGroupRel(
		DepotEntryGroupRel depotEntryGroupRel) {

		depotEntryGroupRel.setNew(true);

		return depotEntryGroupRelPersistence.update(depotEntryGroupRel);
	}

	/**
	 * Creates a new depot entry group rel with the primary key. Does not add the depot entry group rel to the database.
	 *
	 * @param depotEntryGroupRelId the primary key for the new depot entry group rel
	 * @return the new depot entry group rel
	 */
	@Override
	@Transactional(enabled = false)
	public DepotEntryGroupRel createDepotEntryGroupRel(
		long depotEntryGroupRelId) {

		return depotEntryGroupRelPersistence.create(depotEntryGroupRelId);
	}

	/**
	 * Deletes the depot entry group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotEntryGroupRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotEntryGroupRelId the primary key of the depot entry group rel
	 * @return the depot entry group rel that was removed
	 * @throws PortalException if a depot entry group rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DepotEntryGroupRel deleteDepotEntryGroupRel(
			long depotEntryGroupRelId)
		throws PortalException {

		return depotEntryGroupRelPersistence.remove(depotEntryGroupRelId);
	}

	/**
	 * Deletes the depot entry group rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotEntryGroupRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotEntryGroupRel the depot entry group rel
	 * @return the depot entry group rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DepotEntryGroupRel deleteDepotEntryGroupRel(
		DepotEntryGroupRel depotEntryGroupRel) {

		return depotEntryGroupRelPersistence.remove(depotEntryGroupRel);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return depotEntryGroupRelPersistence.dslQuery(dslQuery);
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
			DepotEntryGroupRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return depotEntryGroupRelPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotEntryGroupRelModelImpl</code>.
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

		return depotEntryGroupRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotEntryGroupRelModelImpl</code>.
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

		return depotEntryGroupRelPersistence.findWithDynamicQuery(
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
		return depotEntryGroupRelPersistence.countWithDynamicQuery(
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

		return depotEntryGroupRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DepotEntryGroupRel fetchDepotEntryGroupRel(
		long depotEntryGroupRelId) {

		return depotEntryGroupRelPersistence.fetchByPrimaryKey(
			depotEntryGroupRelId);
	}

	/**
	 * Returns the depot entry group rel matching the UUID and group.
	 *
	 * @param uuid the depot entry group rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching depot entry group rel, or <code>null</code> if a matching depot entry group rel could not be found
	 */
	@Override
	public DepotEntryGroupRel fetchDepotEntryGroupRelByUuidAndGroupId(
		String uuid, long groupId) {

		return depotEntryGroupRelPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the depot entry group rel with the primary key.
	 *
	 * @param depotEntryGroupRelId the primary key of the depot entry group rel
	 * @return the depot entry group rel
	 * @throws PortalException if a depot entry group rel with the primary key could not be found
	 */
	@Override
	public DepotEntryGroupRel getDepotEntryGroupRel(long depotEntryGroupRelId)
		throws PortalException {

		return depotEntryGroupRelPersistence.findByPrimaryKey(
			depotEntryGroupRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			depotEntryGroupRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DepotEntryGroupRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"depotEntryGroupRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			depotEntryGroupRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DepotEntryGroupRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"depotEntryGroupRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			depotEntryGroupRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DepotEntryGroupRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"depotEntryGroupRelId");
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
			new ActionableDynamicQuery.PerformActionMethod
				<DepotEntryGroupRel>() {

				@Override
				public void performAction(DepotEntryGroupRel depotEntryGroupRel)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, depotEntryGroupRel);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(DepotEntryGroupRel.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return depotEntryGroupRelPersistence.create(
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
				"Implement DepotEntryGroupRelLocalServiceImpl#deleteDepotEntryGroupRel(DepotEntryGroupRel) to avoid orphaned data");
		}

		return depotEntryGroupRelLocalService.deleteDepotEntryGroupRel(
			(DepotEntryGroupRel)persistedModel);
	}

	@Override
	public BasePersistence<DepotEntryGroupRel> getBasePersistence() {
		return depotEntryGroupRelPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return depotEntryGroupRelPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the depot entry group rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the depot entry group rels
	 * @param companyId the primary key of the company
	 * @return the matching depot entry group rels, or an empty list if no matches were found
	 */
	@Override
	public List<DepotEntryGroupRel> getDepotEntryGroupRelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return depotEntryGroupRelPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of depot entry group rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the depot entry group rels
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of depot entry group rels
	 * @param end the upper bound of the range of depot entry group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching depot entry group rels, or an empty list if no matches were found
	 */
	@Override
	public List<DepotEntryGroupRel> getDepotEntryGroupRelsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DepotEntryGroupRel> orderByComparator) {

		return depotEntryGroupRelPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the depot entry group rel matching the UUID and group.
	 *
	 * @param uuid the depot entry group rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching depot entry group rel
	 * @throws PortalException if a matching depot entry group rel could not be found
	 */
	@Override
	public DepotEntryGroupRel getDepotEntryGroupRelByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return depotEntryGroupRelPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the depot entry group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotEntryGroupRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of depot entry group rels
	 * @param end the upper bound of the range of depot entry group rels (not inclusive)
	 * @return the range of depot entry group rels
	 */
	@Override
	public List<DepotEntryGroupRel> getDepotEntryGroupRels(int start, int end) {
		return depotEntryGroupRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of depot entry group rels.
	 *
	 * @return the number of depot entry group rels
	 */
	@Override
	public int getDepotEntryGroupRelsCount() {
		return depotEntryGroupRelPersistence.countAll();
	}

	/**
	 * Updates the depot entry group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotEntryGroupRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotEntryGroupRel the depot entry group rel
	 * @return the depot entry group rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DepotEntryGroupRel updateDepotEntryGroupRel(
		DepotEntryGroupRel depotEntryGroupRel) {

		return depotEntryGroupRelPersistence.update(depotEntryGroupRel);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DepotEntryGroupRelLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		depotEntryGroupRelLocalService =
			(DepotEntryGroupRelLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DepotEntryGroupRelLocalService.class.getName();
	}

	@Override
	public CTPersistence<DepotEntryGroupRel> getCTPersistence() {
		return depotEntryGroupRelPersistence;
	}

	@Override
	public Class<DepotEntryGroupRel> getModelClass() {
		return DepotEntryGroupRel.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DepotEntryGroupRel>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(depotEntryGroupRelPersistence);
	}

	protected String getModelClassName() {
		return DepotEntryGroupRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				depotEntryGroupRelPersistence.getDataSource();

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

	protected DepotEntryGroupRelLocalService depotEntryGroupRelLocalService;

	@Reference
	protected DepotEntryGroupRelPersistence depotEntryGroupRelPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		DepotEntryGroupRelLocalServiceBaseImpl.class);

}