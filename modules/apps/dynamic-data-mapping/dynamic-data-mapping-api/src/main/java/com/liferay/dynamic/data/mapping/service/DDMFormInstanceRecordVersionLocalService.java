/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for DDMFormInstanceRecordVersion. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecordVersionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DDMFormInstanceRecordVersionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceRecordVersionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the ddm form instance record version local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link DDMFormInstanceRecordVersionLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the ddm form instance record version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceRecordVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDMFormInstanceRecordVersion addDDMFormInstanceRecordVersion(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion);

	/**
	 * Creates a new ddm form instance record version with the primary key. Does not add the ddm form instance record version to the database.
	 *
	 * @param formInstanceRecordVersionId the primary key for the new ddm form instance record version
	 * @return the new ddm form instance record version
	 */
	@Transactional(enabled = false)
	public DDMFormInstanceRecordVersion createDDMFormInstanceRecordVersion(
		long formInstanceRecordVersionId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the ddm form instance record version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceRecordVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDMFormInstanceRecordVersion deleteDDMFormInstanceRecordVersion(
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion)
		throws PortalException;

	/**
	 * Deletes the ddm form instance record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceRecordVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 * @throws PortalException if a ddm form instance record version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDMFormInstanceRecordVersion deleteDDMFormInstanceRecordVersion(
			long formInstanceRecordVersionId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion fetchDDMFormInstanceRecordVersion(
		long formInstanceRecordVersionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion fetchLatestFormInstanceRecordVersion(
		long userId, long formInstanceId, String formInstanceVersion,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the ddm form instance record version with the primary key.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version
	 * @throws PortalException if a ddm form instance record version with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion getDDMFormInstanceRecordVersion(
			long formInstanceRecordVersionId)
		throws PortalException;

	/**
	 * Returns a range of all the ddm form instance record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of ddm form instance record versions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMFormInstanceRecordVersion> getDDMFormInstanceRecordVersions(
		int start, int end);

	/**
	 * Returns the number of ddm form instance record versions.
	 *
	 * @return the number of ddm form instance record versions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDMFormInstanceRecordVersionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion getFormInstanceRecordVersion(
			long ddmFormInstanceRecordVersionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion getFormInstanceRecordVersion(
			long ddmFormInstanceId, String version)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMFormInstanceRecordVersion> getFormInstanceRecordVersions(
		long ddmFormInstanceRecordId, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMFormInstanceRecordVersion> getFormInstanceRecordVersions(
		long userId, long formInstanceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFormInstanceRecordVersionsCount(long ddmFormInstanceRecordId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion getLatestFormInstanceRecordVersion(
			long ddmFormInstanceId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceRecordVersion getLatestFormInstanceRecordVersion(
			long ddmFormInstanceRecordId, int status)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the ddm form instance record version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMFormInstanceRecordVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDMFormInstanceRecordVersion updateDDMFormInstanceRecordVersion(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion);

}