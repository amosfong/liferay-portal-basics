/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.base;

import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.price.list.service.persistence.CommerceTierPriceEntryPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
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
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the commerce tier price entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryLocalServiceImpl
 * @generated
 */
public abstract class CommerceTierPriceEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CommerceTierPriceEntryLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceTierPriceEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce tier price entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTierPriceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTierPriceEntry the commerce tier price entry
	 * @return the commerce tier price entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceTierPriceEntry addCommerceTierPriceEntry(
		CommerceTierPriceEntry commerceTierPriceEntry) {

		commerceTierPriceEntry.setNew(true);

		return commerceTierPriceEntryPersistence.update(commerceTierPriceEntry);
	}

	/**
	 * Creates a new commerce tier price entry with the primary key. Does not add the commerce tier price entry to the database.
	 *
	 * @param commerceTierPriceEntryId the primary key for the new commerce tier price entry
	 * @return the new commerce tier price entry
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceTierPriceEntry createCommerceTierPriceEntry(
		long commerceTierPriceEntryId) {

		return commerceTierPriceEntryPersistence.create(
			commerceTierPriceEntryId);
	}

	/**
	 * Deletes the commerce tier price entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTierPriceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTierPriceEntryId the primary key of the commerce tier price entry
	 * @return the commerce tier price entry that was removed
	 * @throws PortalException if a commerce tier price entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceTierPriceEntry deleteCommerceTierPriceEntry(
			long commerceTierPriceEntryId)
		throws PortalException {

		return commerceTierPriceEntryPersistence.remove(
			commerceTierPriceEntryId);
	}

	/**
	 * Deletes the commerce tier price entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTierPriceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTierPriceEntry the commerce tier price entry
	 * @return the commerce tier price entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceTierPriceEntry deleteCommerceTierPriceEntry(
			CommerceTierPriceEntry commerceTierPriceEntry)
		throws PortalException {

		return commerceTierPriceEntryPersistence.remove(commerceTierPriceEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceTierPriceEntryPersistence.dslQuery(dslQuery);
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
			CommerceTierPriceEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceTierPriceEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryModelImpl</code>.
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

		return commerceTierPriceEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryModelImpl</code>.
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

		return commerceTierPriceEntryPersistence.findWithDynamicQuery(
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
		return commerceTierPriceEntryPersistence.countWithDynamicQuery(
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

		return commerceTierPriceEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceTierPriceEntry fetchCommerceTierPriceEntry(
		long commerceTierPriceEntryId) {

		return commerceTierPriceEntryPersistence.fetchByPrimaryKey(
			commerceTierPriceEntryId);
	}

	/**
	 * Returns the commerce tier price entry with the matching UUID and company.
	 *
	 * @param uuid the commerce tier price entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchCommerceTierPriceEntryByUuidAndCompanyId(
		String uuid, long companyId) {

		return commerceTierPriceEntryPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	@Override
	public CommerceTierPriceEntry
		fetchCommerceTierPriceEntryByExternalReferenceCode(
			String externalReferenceCode, long companyId) {

		return commerceTierPriceEntryPersistence.fetchByERC_C(
			externalReferenceCode, companyId);
	}

	@Override
	public CommerceTierPriceEntry
			getCommerceTierPriceEntryByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws PortalException {

		return commerceTierPriceEntryPersistence.findByERC_C(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the commerce tier price entry with the primary key.
	 *
	 * @param commerceTierPriceEntryId the primary key of the commerce tier price entry
	 * @return the commerce tier price entry
	 * @throws PortalException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry getCommerceTierPriceEntry(
			long commerceTierPriceEntryId)
		throws PortalException {

		return commerceTierPriceEntryPersistence.findByPrimaryKey(
			commerceTierPriceEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceTierPriceEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceTierPriceEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTierPriceEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceTierPriceEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceTierPriceEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTierPriceEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceTierPriceEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceTierPriceEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceTierPriceEntryId");
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
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria("modifiedDate");

					Criterion statusDateCriterion =
						portletDataContext.getDateRangeCriteria("statusDate");

					if ((modifiedDateCriterion != null) &&
						(statusDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty =
						PropertyFactoryUtil.forName("status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(
							workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						StagedModelDataHandler<?> stagedModelDataHandler =
							StagedModelDataHandlerRegistryUtil.
								getStagedModelDataHandler(
									CommerceTierPriceEntry.class.getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceTierPriceEntry>() {

				@Override
				public void performAction(
						CommerceTierPriceEntry commerceTierPriceEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commerceTierPriceEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CommerceTierPriceEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceTierPriceEntryPersistence.create(
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
				"Implement CommerceTierPriceEntryLocalServiceImpl#deleteCommerceTierPriceEntry(CommerceTierPriceEntry) to avoid orphaned data");
		}

		return commerceTierPriceEntryLocalService.deleteCommerceTierPriceEntry(
			(CommerceTierPriceEntry)persistedModel);
	}

	@Override
	public BasePersistence<CommerceTierPriceEntry> getBasePersistence() {
		return commerceTierPriceEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceTierPriceEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns the commerce tier price entry with the matching UUID and company.
	 *
	 * @param uuid the commerce tier price entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce tier price entry
	 * @throws PortalException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry getCommerceTierPriceEntryByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return commerceTierPriceEntryPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> getCommerceTierPriceEntries(
		int start, int end) {

		return commerceTierPriceEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce tier price entries.
	 *
	 * @return the number of commerce tier price entries
	 */
	@Override
	public int getCommerceTierPriceEntriesCount() {
		return commerceTierPriceEntryPersistence.countAll();
	}

	/**
	 * Updates the commerce tier price entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceTierPriceEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceTierPriceEntry the commerce tier price entry
	 * @return the commerce tier price entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceTierPriceEntry updateCommerceTierPriceEntry(
		CommerceTierPriceEntry commerceTierPriceEntry) {

		return commerceTierPriceEntryPersistence.update(commerceTierPriceEntry);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommerceTierPriceEntryLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commerceTierPriceEntryLocalService =
			(CommerceTierPriceEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceTierPriceEntryLocalService.class.getName();
	}

	@Override
	public CTPersistence<CommerceTierPriceEntry> getCTPersistence() {
		return commerceTierPriceEntryPersistence;
	}

	@Override
	public Class<CommerceTierPriceEntry> getModelClass() {
		return CommerceTierPriceEntry.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CommerceTierPriceEntry>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(commerceTierPriceEntryPersistence);
	}

	protected String getModelClassName() {
		return CommerceTierPriceEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceTierPriceEntryPersistence.getDataSource();

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

	protected CommerceTierPriceEntryLocalService
		commerceTierPriceEntryLocalService;

	@Reference
	protected CommerceTierPriceEntryPersistence
		commerceTierPriceEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTierPriceEntryLocalServiceBaseImpl.class);

}