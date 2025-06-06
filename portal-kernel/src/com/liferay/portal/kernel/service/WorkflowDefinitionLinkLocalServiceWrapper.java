/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link WorkflowDefinitionLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkLocalService
 * @generated
 */
public class WorkflowDefinitionLinkLocalServiceWrapper
	implements ServiceWrapper<WorkflowDefinitionLinkLocalService>,
			   WorkflowDefinitionLinkLocalService {

	public WorkflowDefinitionLinkLocalServiceWrapper() {
		this(null);
	}

	public WorkflowDefinitionLinkLocalServiceWrapper(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			addWorkflowDefinitionLink(
				long userId, long companyId, long groupId, String className,
				long classPK, long typePK, String workflowDefinitionName,
				int workflowDefinitionVersion)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.addWorkflowDefinitionLink(
			userId, companyId, groupId, className, classPK, typePK,
			workflowDefinitionName, workflowDefinitionVersion);
	}

	/**
	 * Adds the workflow definition link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowDefinitionLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowDefinitionLink the workflow definition link
	 * @return the workflow definition link that was added
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		addWorkflowDefinitionLink(
			com.liferay.portal.kernel.model.WorkflowDefinitionLink
				workflowDefinitionLink) {

		return _workflowDefinitionLinkLocalService.addWorkflowDefinitionLink(
			workflowDefinitionLink);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new workflow definition link with the primary key. Does not add the workflow definition link to the database.
	 *
	 * @param workflowDefinitionLinkId the primary key for the new workflow definition link
	 * @return the new workflow definition link
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		createWorkflowDefinitionLink(long workflowDefinitionLinkId) {

		return _workflowDefinitionLinkLocalService.createWorkflowDefinitionLink(
			workflowDefinitionLinkId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowDefinitionLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link that was removed
	 * @throws PortalException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			deleteWorkflowDefinitionLink(long workflowDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			workflowDefinitionLinkId);
	}

	@Override
	public void deleteWorkflowDefinitionLink(
		long companyId, long groupId, String className, long classPK,
		long typePK) {

		_workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK);
	}

	/**
	 * Deletes the workflow definition link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowDefinitionLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowDefinitionLink the workflow definition link
	 * @return the workflow definition link that was removed
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		deleteWorkflowDefinitionLink(
			com.liferay.portal.kernel.model.WorkflowDefinitionLink
				workflowDefinitionLink) {

		return _workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			workflowDefinitionLink);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _workflowDefinitionLinkLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _workflowDefinitionLinkLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowDefinitionLinkLocalService.dynamicQuery();
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

		return _workflowDefinitionLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>.
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

		return _workflowDefinitionLinkLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>.
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

		return _workflowDefinitionLinkLocalService.dynamicQuery(
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

		return _workflowDefinitionLinkLocalService.dynamicQueryCount(
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

		return _workflowDefinitionLinkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		fetchDefaultWorkflowDefinitionLink(
			long companyId, String className, long classPK, long typePK) {

		return _workflowDefinitionLinkLocalService.
			fetchDefaultWorkflowDefinitionLink(
				companyId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		fetchWorkflowDefinitionLink(long workflowDefinitionLinkId) {

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
			workflowDefinitionLinkId);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		fetchWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK) {

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		fetchWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK, boolean strict) {

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK, strict);
	}

	@Override
	public java.util.List
		<com.liferay.portal.kernel.model.WorkflowDefinitionLink>
			fetchWorkflowDefinitionLinks(
				long companyId, long groupId, String className, long classPK) {

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLinks(
			companyId, groupId, className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _workflowDefinitionLinkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			getDefaultWorkflowDefinitionLink(
				long companyId, String className, long classPK, long typePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.
			getDefaultWorkflowDefinitionLink(
				companyId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _workflowDefinitionLinkLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _workflowDefinitionLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the workflow definition link with the primary key.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link
	 * @throws PortalException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			getWorkflowDefinitionLink(long workflowDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
			workflowDefinitionLinkId);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			getWorkflowDefinitionLink(
				long companyId, long groupId, String className, long classPK,
				long typePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			getWorkflowDefinitionLink(
				long companyId, long groupId, String className, long classPK,
				long typePK, boolean strict)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK, strict);
	}

	/**
	 * Returns a range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of workflow definition links
	 */
	@Override
	public java.util.List
		<com.liferay.portal.kernel.model.WorkflowDefinitionLink>
			getWorkflowDefinitionLinks(int start, int end) {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinks(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.portal.kernel.model.WorkflowDefinitionLink>
				getWorkflowDefinitionLinks(
					long companyId, long groupId, long classPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinks(
			companyId, groupId, classPK);
	}

	@Override
	public java.util.List
		<com.liferay.portal.kernel.model.WorkflowDefinitionLink>
				getWorkflowDefinitionLinks(
					long companyId, long groupId, String className,
					long classPK)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinks(
			companyId, groupId, className, classPK);
	}

	@Override
	public java.util.List
		<com.liferay.portal.kernel.model.WorkflowDefinitionLink>
				getWorkflowDefinitionLinks(
					long companyId, String workflowDefinitionName,
					int workflowDefinitionVersion)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinks(
			companyId, workflowDefinitionName, workflowDefinitionVersion);
	}

	/**
	 * Returns the number of workflow definition links.
	 *
	 * @return the number of workflow definition links
	 */
	@Override
	public int getWorkflowDefinitionLinksCount() {
		return _workflowDefinitionLinkLocalService.
			getWorkflowDefinitionLinksCount();
	}

	@Override
	public int getWorkflowDefinitionLinksCount(
		long companyId, long groupId, String className) {

		return _workflowDefinitionLinkLocalService.
			getWorkflowDefinitionLinksCount(companyId, groupId, className);
	}

	@Override
	public int getWorkflowDefinitionLinksCount(
		long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) {

		return _workflowDefinitionLinkLocalService.
			getWorkflowDefinitionLinksCount(
				companyId, workflowDefinitionName, workflowDefinitionVersion);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(
		long companyId, long groupId, String className) {

		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
			companyId, groupId, className);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(
		long companyId, long groupId, String className, long classPK) {

		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
			companyId, groupId, className, classPK);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(
		long companyId, long groupId, String className, long classPK,
		long typePK) {

		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK);
	}

	@Override
	public void updateWorkflowDefinitionLink(
			long userId, long companyId, long groupId, String className,
			long classPK, long typePK, String workflowDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			userId, companyId, groupId, className, classPK, typePK,
			workflowDefinition);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
			updateWorkflowDefinitionLink(
				long userId, long companyId, long groupId, String className,
				long classPK, long typePK, String workflowDefinitionName,
				int workflowDefinitionVersion)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			userId, companyId, groupId, className, classPK, typePK,
			workflowDefinitionName, workflowDefinitionVersion);
	}

	/**
	 * Updates the workflow definition link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowDefinitionLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowDefinitionLink the workflow definition link
	 * @return the workflow definition link that was updated
	 */
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink
		updateWorkflowDefinitionLink(
			com.liferay.portal.kernel.model.WorkflowDefinitionLink
				workflowDefinitionLink) {

		return _workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			workflowDefinitionLink);
	}

	@Override
	public void updateWorkflowDefinitionLinks(
			long userId, long companyId, long groupId, String className,
			long classPK,
			java.util.List
				<com.liferay.portal.kernel.util.ObjectValuePair<Long, String>>
					workflowDefinitionOVPs)
		throws com.liferay.portal.kernel.exception.PortalException {

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLinks(
			userId, companyId, groupId, className, classPK,
			workflowDefinitionOVPs);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _workflowDefinitionLinkLocalService.getBasePersistence();
	}

	@Override
	public WorkflowDefinitionLinkLocalService getWrappedService() {
		return _workflowDefinitionLinkLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}