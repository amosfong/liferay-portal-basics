/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.base;

import com.liferay.commerce.product.model.CPInstanceOptionValueRel;
import com.liferay.commerce.product.service.CPInstanceOptionValueRelLocalService;
import com.liferay.commerce.product.service.persistence.CPInstanceOptionValueRelFinder;
import com.liferay.commerce.product.service.persistence.CPInstanceOptionValueRelPersistence;
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
 * Provides the base implementation for the cp instance option value rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.service.impl.CPInstanceOptionValueRelLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.impl.CPInstanceOptionValueRelLocalServiceImpl
 * @generated
 */
public abstract class CPInstanceOptionValueRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CPInstanceOptionValueRelLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPInstanceOptionValueRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.service.CPInstanceOptionValueRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cp instance option value rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPInstanceOptionValueRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpInstanceOptionValueRel the cp instance option value rel
	 * @return the cp instance option value rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstanceOptionValueRel addCPInstanceOptionValueRel(
		CPInstanceOptionValueRel cpInstanceOptionValueRel) {

		cpInstanceOptionValueRel.setNew(true);

		return cpInstanceOptionValueRelPersistence.update(
			cpInstanceOptionValueRel);
	}

	/**
	 * Creates a new cp instance option value rel with the primary key. Does not add the cp instance option value rel to the database.
	 *
	 * @param CPInstanceOptionValueRelId the primary key for the new cp instance option value rel
	 * @return the new cp instance option value rel
	 */
	@Override
	@Transactional(enabled = false)
	public CPInstanceOptionValueRel createCPInstanceOptionValueRel(
		long CPInstanceOptionValueRelId) {

		return cpInstanceOptionValueRelPersistence.create(
			CPInstanceOptionValueRelId);
	}

	/**
	 * Deletes the cp instance option value rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPInstanceOptionValueRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CPInstanceOptionValueRelId the primary key of the cp instance option value rel
	 * @return the cp instance option value rel that was removed
	 * @throws PortalException if a cp instance option value rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPInstanceOptionValueRel deleteCPInstanceOptionValueRel(
			long CPInstanceOptionValueRelId)
		throws PortalException {

		return cpInstanceOptionValueRelPersistence.remove(
			CPInstanceOptionValueRelId);
	}

	/**
	 * Deletes the cp instance option value rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPInstanceOptionValueRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpInstanceOptionValueRel the cp instance option value rel
	 * @return the cp instance option value rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPInstanceOptionValueRel deleteCPInstanceOptionValueRel(
		CPInstanceOptionValueRel cpInstanceOptionValueRel) {

		return cpInstanceOptionValueRelPersistence.remove(
			cpInstanceOptionValueRel);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return cpInstanceOptionValueRelPersistence.dslQuery(dslQuery);
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
			CPInstanceOptionValueRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cpInstanceOptionValueRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelModelImpl</code>.
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

		return cpInstanceOptionValueRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelModelImpl</code>.
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

		return cpInstanceOptionValueRelPersistence.findWithDynamicQuery(
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
		return cpInstanceOptionValueRelPersistence.countWithDynamicQuery(
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

		return cpInstanceOptionValueRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CPInstanceOptionValueRel fetchCPInstanceOptionValueRel(
		long CPInstanceOptionValueRelId) {

		return cpInstanceOptionValueRelPersistence.fetchByPrimaryKey(
			CPInstanceOptionValueRelId);
	}

	/**
	 * Returns the cp instance option value rel matching the UUID and group.
	 *
	 * @param uuid the cp instance option value rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp instance option value rel, or <code>null</code> if a matching cp instance option value rel could not be found
	 */
	@Override
	public CPInstanceOptionValueRel
		fetchCPInstanceOptionValueRelByUuidAndGroupId(
			String uuid, long groupId) {

		return cpInstanceOptionValueRelPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the cp instance option value rel with the primary key.
	 *
	 * @param CPInstanceOptionValueRelId the primary key of the cp instance option value rel
	 * @return the cp instance option value rel
	 * @throws PortalException if a cp instance option value rel with the primary key could not be found
	 */
	@Override
	public CPInstanceOptionValueRel getCPInstanceOptionValueRel(
			long CPInstanceOptionValueRelId)
		throws PortalException {

		return cpInstanceOptionValueRelPersistence.findByPrimaryKey(
			CPInstanceOptionValueRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			cpInstanceOptionValueRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPInstanceOptionValueRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPInstanceOptionValueRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cpInstanceOptionValueRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CPInstanceOptionValueRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPInstanceOptionValueRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			cpInstanceOptionValueRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPInstanceOptionValueRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPInstanceOptionValueRelId");
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

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CPInstanceOptionValueRel>() {

				@Override
				public void performAction(
						CPInstanceOptionValueRel cpInstanceOptionValueRel)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, cpInstanceOptionValueRel);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CPInstanceOptionValueRel.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpInstanceOptionValueRelPersistence.create(
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
				"Implement CPInstanceOptionValueRelLocalServiceImpl#deleteCPInstanceOptionValueRel(CPInstanceOptionValueRel) to avoid orphaned data");
		}

		return cpInstanceOptionValueRelLocalService.
			deleteCPInstanceOptionValueRel(
				(CPInstanceOptionValueRel)persistedModel);
	}

	@Override
	public BasePersistence<CPInstanceOptionValueRel> getBasePersistence() {
		return cpInstanceOptionValueRelPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpInstanceOptionValueRelPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the cp instance option value rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp instance option value rels
	 * @param companyId the primary key of the company
	 * @return the matching cp instance option value rels, or an empty list if no matches were found
	 */
	@Override
	public List<CPInstanceOptionValueRel>
		getCPInstanceOptionValueRelsByUuidAndCompanyId(
			String uuid, long companyId) {

		return cpInstanceOptionValueRelPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of cp instance option value rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp instance option value rels
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cp instance option value rels
	 * @param end the upper bound of the range of cp instance option value rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cp instance option value rels, or an empty list if no matches were found
	 */
	@Override
	public List<CPInstanceOptionValueRel>
		getCPInstanceOptionValueRelsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CPInstanceOptionValueRel> orderByComparator) {

		return cpInstanceOptionValueRelPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the cp instance option value rel matching the UUID and group.
	 *
	 * @param uuid the cp instance option value rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp instance option value rel
	 * @throws PortalException if a matching cp instance option value rel could not be found
	 */
	@Override
	public CPInstanceOptionValueRel getCPInstanceOptionValueRelByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return cpInstanceOptionValueRelPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the cp instance option value rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instance option value rels
	 * @param end the upper bound of the range of cp instance option value rels (not inclusive)
	 * @return the range of cp instance option value rels
	 */
	@Override
	public List<CPInstanceOptionValueRel> getCPInstanceOptionValueRels(
		int start, int end) {

		return cpInstanceOptionValueRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cp instance option value rels.
	 *
	 * @return the number of cp instance option value rels
	 */
	@Override
	public int getCPInstanceOptionValueRelsCount() {
		return cpInstanceOptionValueRelPersistence.countAll();
	}

	/**
	 * Updates the cp instance option value rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPInstanceOptionValueRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpInstanceOptionValueRel the cp instance option value rel
	 * @return the cp instance option value rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPInstanceOptionValueRel updateCPInstanceOptionValueRel(
		CPInstanceOptionValueRel cpInstanceOptionValueRel) {

		return cpInstanceOptionValueRelPersistence.update(
			cpInstanceOptionValueRel);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CPInstanceOptionValueRelLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		cpInstanceOptionValueRelLocalService =
			(CPInstanceOptionValueRelLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPInstanceOptionValueRelLocalService.class.getName();
	}

	@Override
	public CTPersistence<CPInstanceOptionValueRel> getCTPersistence() {
		return cpInstanceOptionValueRelPersistence;
	}

	@Override
	public Class<CPInstanceOptionValueRel> getModelClass() {
		return CPInstanceOptionValueRel.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CPInstanceOptionValueRel>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(cpInstanceOptionValueRelPersistence);
	}

	protected String getModelClassName() {
		return CPInstanceOptionValueRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpInstanceOptionValueRelPersistence.getDataSource();

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

	protected CPInstanceOptionValueRelLocalService
		cpInstanceOptionValueRelLocalService;

	@Reference
	protected CPInstanceOptionValueRelPersistence
		cpInstanceOptionValueRelPersistence;

	@Reference
	protected CPInstanceOptionValueRelFinder cpInstanceOptionValueRelFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceOptionValueRelLocalServiceBaseImpl.class);

}