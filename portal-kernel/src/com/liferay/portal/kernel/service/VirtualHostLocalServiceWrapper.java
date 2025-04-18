/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link VirtualHostLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHostLocalService
 * @generated
 */
public class VirtualHostLocalServiceWrapper
	implements ServiceWrapper<VirtualHostLocalService>,
			   VirtualHostLocalService {

	public VirtualHostLocalServiceWrapper() {
		this(null);
	}

	public VirtualHostLocalServiceWrapper(
		VirtualHostLocalService virtualHostLocalService) {

		_virtualHostLocalService = virtualHostLocalService;
	}

	/**
	 * Adds the virtual host to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VirtualHostLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param virtualHost the virtual host
	 * @return the virtual host that was added
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost addVirtualHost(
		com.liferay.portal.kernel.model.VirtualHost virtualHost) {

		return _virtualHostLocalService.addVirtualHost(virtualHost);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new virtual host with the primary key. Does not add the virtual host to the database.
	 *
	 * @param virtualHostId the primary key for the new virtual host
	 * @return the new virtual host
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost createVirtualHost(
		long virtualHostId) {

		return _virtualHostLocalService.createVirtualHost(virtualHostId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the virtual host with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VirtualHostLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param virtualHostId the primary key of the virtual host
	 * @return the virtual host that was removed
	 * @throws PortalException if a virtual host with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost deleteVirtualHost(
			long virtualHostId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.deleteVirtualHost(virtualHostId);
	}

	/**
	 * Deletes the virtual host from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VirtualHostLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param virtualHost the virtual host
	 * @return the virtual host that was removed
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost deleteVirtualHost(
		com.liferay.portal.kernel.model.VirtualHost virtualHost) {

		return _virtualHostLocalService.deleteVirtualHost(virtualHost);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _virtualHostLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _virtualHostLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _virtualHostLocalService.dynamicQuery();
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

		return _virtualHostLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.VirtualHostModelImpl</code>.
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

		return _virtualHostLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.VirtualHostModelImpl</code>.
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

		return _virtualHostLocalService.dynamicQuery(
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

		return _virtualHostLocalService.dynamicQueryCount(dynamicQuery);
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

		return _virtualHostLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.kernel.model.VirtualHost fetchVirtualHost(
		long virtualHostId) {

		return _virtualHostLocalService.fetchVirtualHost(virtualHostId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #getVirtualHosts(long, long)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.VirtualHost fetchVirtualHost(
		long companyId, long layoutSetId) {

		return _virtualHostLocalService.fetchVirtualHost(
			companyId, layoutSetId);
	}

	@Override
	public com.liferay.portal.kernel.model.VirtualHost fetchVirtualHost(
		String hostname) {

		return _virtualHostLocalService.fetchVirtualHost(hostname);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _virtualHostLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _virtualHostLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _virtualHostLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the virtual host with the primary key.
	 *
	 * @param virtualHostId the primary key of the virtual host
	 * @return the virtual host
	 * @throws PortalException if a virtual host with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost getVirtualHost(
			long virtualHostId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.getVirtualHost(virtualHostId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #getVirtualHosts(long, long)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.VirtualHost getVirtualHost(
			long companyId, long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.getVirtualHost(companyId, layoutSetId);
	}

	@Override
	public com.liferay.portal.kernel.model.VirtualHost getVirtualHost(
			String hostname)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _virtualHostLocalService.getVirtualHost(hostname);
	}

	/**
	 * Returns a range of all the virtual hosts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of virtual hosts
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.VirtualHost>
		getVirtualHosts(int start, int end) {

		return _virtualHostLocalService.getVirtualHosts(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.VirtualHost>
		getVirtualHosts(long companyId) {

		return _virtualHostLocalService.getVirtualHosts(companyId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.VirtualHost>
		getVirtualHosts(long companyId, long layoutSetId) {

		return _virtualHostLocalService.getVirtualHosts(companyId, layoutSetId);
	}

	/**
	 * Returns the number of virtual hosts.
	 *
	 * @return the number of virtual hosts
	 */
	@Override
	public int getVirtualHostsCount() {
		return _virtualHostLocalService.getVirtualHostsCount();
	}

	@Override
	public long getVirtualHostsCount(
		long excludedLayoutSetId, String[] virtualHostNames) {

		return _virtualHostLocalService.getVirtualHostsCount(
			excludedLayoutSetId, virtualHostNames);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #updateVirtualHosts(long, long, TreeMap)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.VirtualHost updateVirtualHost(
		long companyId, long layoutSetId, String hostname) {

		return _virtualHostLocalService.updateVirtualHost(
			companyId, layoutSetId, hostname);
	}

	/**
	 * Updates the virtual host in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VirtualHostLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param virtualHost the virtual host
	 * @return the virtual host that was updated
	 */
	@Override
	public com.liferay.portal.kernel.model.VirtualHost updateVirtualHost(
		com.liferay.portal.kernel.model.VirtualHost virtualHost) {

		return _virtualHostLocalService.updateVirtualHost(virtualHost);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.VirtualHost>
		updateVirtualHosts(
			long companyId, long layoutSetId,
			java.util.TreeMap<String, String> hostnames) {

		return _virtualHostLocalService.updateVirtualHosts(
			companyId, layoutSetId, hostnames);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _virtualHostLocalService.getBasePersistence();
	}

	@Override
	public VirtualHostLocalService getWrappedService() {
		return _virtualHostLocalService;
	}

	@Override
	public void setWrappedService(
		VirtualHostLocalService virtualHostLocalService) {

		_virtualHostLocalService = virtualHostLocalService;
	}

	private VirtualHostLocalService _virtualHostLocalService;

}