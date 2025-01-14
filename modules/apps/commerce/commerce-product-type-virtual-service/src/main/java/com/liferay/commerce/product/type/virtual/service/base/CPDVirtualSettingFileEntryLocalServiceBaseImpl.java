/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.service.base;

import com.liferay.commerce.product.type.virtual.model.CPDVirtualSettingFileEntry;
import com.liferay.commerce.product.type.virtual.service.CPDVirtualSettingFileEntryLocalService;
import com.liferay.commerce.product.type.virtual.service.persistence.CPDVirtualSettingFileEntryPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * Provides the base implementation for the cpd virtual setting file entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.service.impl.CPDVirtualSettingFileEntryLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.type.virtual.service.impl.CPDVirtualSettingFileEntryLocalServiceImpl
 * @generated
 */
public abstract class CPDVirtualSettingFileEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CPDVirtualSettingFileEntryLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDVirtualSettingFileEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.type.virtual.service.CPDVirtualSettingFileEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cpd virtual setting file entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDVirtualSettingFileEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdVirtualSettingFileEntry the cpd virtual setting file entry
	 * @return the cpd virtual setting file entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDVirtualSettingFileEntry addCPDVirtualSettingFileEntry(
		CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry) {

		cpdVirtualSettingFileEntry.setNew(true);

		return cpdVirtualSettingFileEntryPersistence.update(
			cpdVirtualSettingFileEntry);
	}

	/**
	 * Creates a new cpd virtual setting file entry with the primary key. Does not add the cpd virtual setting file entry to the database.
	 *
	 * @param CPDefinitionVirtualSettingFileEntryId the primary key for the new cpd virtual setting file entry
	 * @return the new cpd virtual setting file entry
	 */
	@Override
	@Transactional(enabled = false)
	public CPDVirtualSettingFileEntry createCPDVirtualSettingFileEntry(
		long CPDefinitionVirtualSettingFileEntryId) {

		return cpdVirtualSettingFileEntryPersistence.create(
			CPDefinitionVirtualSettingFileEntryId);
	}

	/**
	 * Deletes the cpd virtual setting file entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDVirtualSettingFileEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CPDefinitionVirtualSettingFileEntryId the primary key of the cpd virtual setting file entry
	 * @return the cpd virtual setting file entry that was removed
	 * @throws PortalException if a cpd virtual setting file entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDVirtualSettingFileEntry deleteCPDVirtualSettingFileEntry(
			long CPDefinitionVirtualSettingFileEntryId)
		throws PortalException {

		return cpdVirtualSettingFileEntryPersistence.remove(
			CPDefinitionVirtualSettingFileEntryId);
	}

	/**
	 * Deletes the cpd virtual setting file entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDVirtualSettingFileEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdVirtualSettingFileEntry the cpd virtual setting file entry
	 * @return the cpd virtual setting file entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDVirtualSettingFileEntry deleteCPDVirtualSettingFileEntry(
		CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry) {

		return cpdVirtualSettingFileEntryPersistence.remove(
			cpdVirtualSettingFileEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return cpdVirtualSettingFileEntryPersistence.dslQuery(dslQuery);
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
			CPDVirtualSettingFileEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cpdVirtualSettingFileEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDVirtualSettingFileEntryModelImpl</code>.
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

		return cpdVirtualSettingFileEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDVirtualSettingFileEntryModelImpl</code>.
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

		return cpdVirtualSettingFileEntryPersistence.findWithDynamicQuery(
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
		return cpdVirtualSettingFileEntryPersistence.countWithDynamicQuery(
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

		return cpdVirtualSettingFileEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CPDVirtualSettingFileEntry fetchCPDVirtualSettingFileEntry(
		long CPDefinitionVirtualSettingFileEntryId) {

		return cpdVirtualSettingFileEntryPersistence.fetchByPrimaryKey(
			CPDefinitionVirtualSettingFileEntryId);
	}

	/**
	 * Returns the cpd virtual setting file entry matching the UUID and group.
	 *
	 * @param uuid the cpd virtual setting file entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cpd virtual setting file entry, or <code>null</code> if a matching cpd virtual setting file entry could not be found
	 */
	@Override
	public CPDVirtualSettingFileEntry
		fetchCPDVirtualSettingFileEntryByUuidAndGroupId(
			String uuid, long groupId) {

		return cpdVirtualSettingFileEntryPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the cpd virtual setting file entry with the primary key.
	 *
	 * @param CPDefinitionVirtualSettingFileEntryId the primary key of the cpd virtual setting file entry
	 * @return the cpd virtual setting file entry
	 * @throws PortalException if a cpd virtual setting file entry with the primary key could not be found
	 */
	@Override
	public CPDVirtualSettingFileEntry getCPDVirtualSettingFileEntry(
			long CPDefinitionVirtualSettingFileEntryId)
		throws PortalException {

		return cpdVirtualSettingFileEntryPersistence.findByPrimaryKey(
			CPDefinitionVirtualSettingFileEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			cpdVirtualSettingFileEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDVirtualSettingFileEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingFileEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cpdVirtualSettingFileEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CPDVirtualSettingFileEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingFileEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			cpdVirtualSettingFileEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDVirtualSettingFileEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingFileEntryId");
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
				<CPDVirtualSettingFileEntry>() {

				@Override
				public void performAction(
						CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, cpdVirtualSettingFileEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CPDVirtualSettingFileEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpdVirtualSettingFileEntryPersistence.create(
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
				"Implement CPDVirtualSettingFileEntryLocalServiceImpl#deleteCPDVirtualSettingFileEntry(CPDVirtualSettingFileEntry) to avoid orphaned data");
		}

		return cpdVirtualSettingFileEntryLocalService.
			deleteCPDVirtualSettingFileEntry(
				(CPDVirtualSettingFileEntry)persistedModel);
	}

	@Override
	public BasePersistence<CPDVirtualSettingFileEntry> getBasePersistence() {
		return cpdVirtualSettingFileEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpdVirtualSettingFileEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the cpd virtual setting file entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cpd virtual setting file entries
	 * @param companyId the primary key of the company
	 * @return the matching cpd virtual setting file entries, or an empty list if no matches were found
	 */
	@Override
	public List<CPDVirtualSettingFileEntry>
		getCPDVirtualSettingFileEntriesByUuidAndCompanyId(
			String uuid, long companyId) {

		return cpdVirtualSettingFileEntryPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of cpd virtual setting file entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cpd virtual setting file entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cpd virtual setting file entries
	 * @param end the upper bound of the range of cpd virtual setting file entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cpd virtual setting file entries, or an empty list if no matches were found
	 */
	@Override
	public List<CPDVirtualSettingFileEntry>
		getCPDVirtualSettingFileEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CPDVirtualSettingFileEntry> orderByComparator) {

		return cpdVirtualSettingFileEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the cpd virtual setting file entry matching the UUID and group.
	 *
	 * @param uuid the cpd virtual setting file entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cpd virtual setting file entry
	 * @throws PortalException if a matching cpd virtual setting file entry could not be found
	 */
	@Override
	public CPDVirtualSettingFileEntry
			getCPDVirtualSettingFileEntryByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return cpdVirtualSettingFileEntryPersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the cpd virtual setting file entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDVirtualSettingFileEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd virtual setting file entries
	 * @param end the upper bound of the range of cpd virtual setting file entries (not inclusive)
	 * @return the range of cpd virtual setting file entries
	 */
	@Override
	public List<CPDVirtualSettingFileEntry> getCPDVirtualSettingFileEntries(
		int start, int end) {

		return cpdVirtualSettingFileEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cpd virtual setting file entries.
	 *
	 * @return the number of cpd virtual setting file entries
	 */
	@Override
	public int getCPDVirtualSettingFileEntriesCount() {
		return cpdVirtualSettingFileEntryPersistence.countAll();
	}

	/**
	 * Updates the cpd virtual setting file entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDVirtualSettingFileEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdVirtualSettingFileEntry the cpd virtual setting file entry
	 * @return the cpd virtual setting file entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDVirtualSettingFileEntry updateCPDVirtualSettingFileEntry(
		CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry) {

		return cpdVirtualSettingFileEntryPersistence.update(
			cpdVirtualSettingFileEntry);
	}

	@Deactivate
	protected void deactivate() {
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CPDVirtualSettingFileEntryLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		cpdVirtualSettingFileEntryLocalService =
			(CPDVirtualSettingFileEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPDVirtualSettingFileEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDVirtualSettingFileEntry.class;
	}

	protected String getModelClassName() {
		return CPDVirtualSettingFileEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpdVirtualSettingFileEntryPersistence.getDataSource();

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

	protected CPDVirtualSettingFileEntryLocalService
		cpdVirtualSettingFileEntryLocalService;

	@Reference
	protected CPDVirtualSettingFileEntryPersistence
		cpdVirtualSettingFileEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CPDVirtualSettingFileEntryLocalServiceBaseImpl.class);

}