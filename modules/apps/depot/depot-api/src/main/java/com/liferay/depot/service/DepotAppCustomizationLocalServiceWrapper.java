/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.depot.service;

import com.liferay.depot.model.DepotAppCustomization;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

/**
 * Provides a wrapper for {@link DepotAppCustomizationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DepotAppCustomizationLocalService
 * @generated
 */
public class DepotAppCustomizationLocalServiceWrapper
	implements DepotAppCustomizationLocalService,
			   ServiceWrapper<DepotAppCustomizationLocalService> {

	public DepotAppCustomizationLocalServiceWrapper() {
		this(null);
	}

	public DepotAppCustomizationLocalServiceWrapper(
		DepotAppCustomizationLocalService depotAppCustomizationLocalService) {

		_depotAppCustomizationLocalService = depotAppCustomizationLocalService;
	}

	/**
	 * Adds the depot app customization to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotAppCustomizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotAppCustomization the depot app customization
	 * @return the depot app customization that was added
	 */
	@Override
	public DepotAppCustomization addDepotAppCustomization(
		DepotAppCustomization depotAppCustomization) {

		return _depotAppCustomizationLocalService.addDepotAppCustomization(
			depotAppCustomization);
	}

	/**
	 * Creates a new depot app customization with the primary key. Does not add the depot app customization to the database.
	 *
	 * @param depotAppCustomizationId the primary key for the new depot app customization
	 * @return the new depot app customization
	 */
	@Override
	public DepotAppCustomization createDepotAppCustomization(
		long depotAppCustomizationId) {

		return _depotAppCustomizationLocalService.createDepotAppCustomization(
			depotAppCustomizationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the depot app customization from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotAppCustomizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotAppCustomization the depot app customization
	 * @return the depot app customization that was removed
	 */
	@Override
	public DepotAppCustomization deleteDepotAppCustomization(
		DepotAppCustomization depotAppCustomization) {

		return _depotAppCustomizationLocalService.deleteDepotAppCustomization(
			depotAppCustomization);
	}

	/**
	 * Deletes the depot app customization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotAppCustomizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotAppCustomizationId the primary key of the depot app customization
	 * @return the depot app customization that was removed
	 * @throws PortalException if a depot app customization with the primary key could not be found
	 */
	@Override
	public DepotAppCustomization deleteDepotAppCustomization(
			long depotAppCustomizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.deleteDepotAppCustomization(
			depotAppCustomizationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _depotAppCustomizationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _depotAppCustomizationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _depotAppCustomizationLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _depotAppCustomizationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotAppCustomizationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _depotAppCustomizationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotAppCustomizationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _depotAppCustomizationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _depotAppCustomizationLocalService.dynamicQueryCount(
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _depotAppCustomizationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public DepotAppCustomization fetchDepotAppCustomization(
		long depotAppCustomizationId) {

		return _depotAppCustomizationLocalService.fetchDepotAppCustomization(
			depotAppCustomizationId);
	}

	@Override
	public DepotAppCustomization fetchDepotAppCustomization(
		long depotEntryId, String portletId) {

		return _depotAppCustomizationLocalService.fetchDepotAppCustomization(
			depotEntryId, portletId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _depotAppCustomizationLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the depot app customization with the primary key.
	 *
	 * @param depotAppCustomizationId the primary key of the depot app customization
	 * @return the depot app customization
	 * @throws PortalException if a depot app customization with the primary key could not be found
	 */
	@Override
	public DepotAppCustomization getDepotAppCustomization(
			long depotAppCustomizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.getDepotAppCustomization(
			depotAppCustomizationId);
	}

	/**
	 * Returns a range of all the depot app customizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.depot.model.impl.DepotAppCustomizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of depot app customizations
	 * @param end the upper bound of the range of depot app customizations (not inclusive)
	 * @return the range of depot app customizations
	 */
	@Override
	public java.util.List<DepotAppCustomization> getDepotAppCustomizations(
		int start, int end) {

		return _depotAppCustomizationLocalService.getDepotAppCustomizations(
			start, end);
	}

	@Override
	public java.util.List<DepotAppCustomization> getDepotAppCustomizations(
		long depotEntryId) {

		return _depotAppCustomizationLocalService.getDepotAppCustomizations(
			depotEntryId);
	}

	/**
	 * Returns the number of depot app customizations.
	 *
	 * @return the number of depot app customizations
	 */
	@Override
	public int getDepotAppCustomizationsCount() {
		return _depotAppCustomizationLocalService.
			getDepotAppCustomizationsCount();
	}

	@Override
	public int getDepotAppCustomizationsCount(
		long depotEntryId, boolean enabled) {

		return _depotAppCustomizationLocalService.
			getDepotAppCustomizationsCount(depotEntryId, enabled);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _depotAppCustomizationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _depotAppCustomizationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the depot app customization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DepotAppCustomizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param depotAppCustomization the depot app customization
	 * @return the depot app customization that was updated
	 */
	@Override
	public DepotAppCustomization updateDepotAppCustomization(
		DepotAppCustomization depotAppCustomization) {

		return _depotAppCustomizationLocalService.updateDepotAppCustomization(
			depotAppCustomization);
	}

	@Override
	public DepotAppCustomization updateDepotAppCustomization(
			long depotEntryId, boolean enabled, String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _depotAppCustomizationLocalService.updateDepotAppCustomization(
			depotEntryId, enabled, portletId);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _depotAppCustomizationLocalService.getBasePersistence();
	}

	@Override
	public CTPersistence<DepotAppCustomization> getCTPersistence() {
		return _depotAppCustomizationLocalService.getCTPersistence();
	}

	@Override
	public Class<DepotAppCustomization> getModelClass() {
		return _depotAppCustomizationLocalService.getModelClass();
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DepotAppCustomization>, R, E>
				updateUnsafeFunction)
		throws E {

		return _depotAppCustomizationLocalService.updateWithUnsafeFunction(
			updateUnsafeFunction);
	}

	@Override
	public DepotAppCustomizationLocalService getWrappedService() {
		return _depotAppCustomizationLocalService;
	}

	@Override
	public void setWrappedService(
		DepotAppCustomizationLocalService depotAppCustomizationLocalService) {

		_depotAppCustomizationLocalService = depotAppCustomizationLocalService;
	}

	private DepotAppCustomizationLocalService
		_depotAppCustomizationLocalService;

}