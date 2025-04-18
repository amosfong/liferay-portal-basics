/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for WorkflowInstanceLink. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLinkLocalServiceUtil
 * @generated
 */
@OSGiBeanProperties(
	property = {
		"model.class.name=com.liferay.portal.kernel.model.WorkflowInstanceLink"
	}
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface WorkflowInstanceLinkLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.portal.service.impl.WorkflowInstanceLinkLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the workflow instance link local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link WorkflowInstanceLinkLocalServiceUtil} if injection and service tracking are not available.
	 */
	public WorkflowInstanceLink addWorkflowInstanceLink(
			long userId, long companyId, long groupId, String className,
			long classPK, long workflowInstanceId)
		throws PortalException;

	/**
	 * Adds the workflow instance link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowInstanceLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowInstanceLink the workflow instance link
	 * @return the workflow instance link that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WorkflowInstanceLink addWorkflowInstanceLink(
		WorkflowInstanceLink workflowInstanceLink);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Creates a new workflow instance link with the primary key. Does not add the workflow instance link to the database.
	 *
	 * @param workflowInstanceLinkId the primary key for the new workflow instance link
	 * @return the new workflow instance link
	 */
	@Transactional(enabled = false)
	public WorkflowInstanceLink createWorkflowInstanceLink(
		long workflowInstanceLinkId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the workflow instance link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowInstanceLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowInstanceLinkId the primary key of the workflow instance link
	 * @return the workflow instance link that was removed
	 * @throws PortalException if a workflow instance link with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public WorkflowInstanceLink deleteWorkflowInstanceLink(
			long workflowInstanceLinkId)
		throws PortalException;

	public WorkflowInstanceLink deleteWorkflowInstanceLink(
			long companyId, long groupId, String className, long classPK)
		throws PortalException;

	/**
	 * Deletes the workflow instance link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowInstanceLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowInstanceLink the workflow instance link
	 * @return the workflow instance link that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public WorkflowInstanceLink deleteWorkflowInstanceLink(
			WorkflowInstanceLink workflowInstanceLink)
		throws PortalException;

	public void deleteWorkflowInstanceLinks(
			long companyId, long groupId, String className, long classPK)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl</code>.
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
	public WorkflowInstanceLink fetchWorkflowInstanceLink(
		long workflowInstanceLinkId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowInstanceLink fetchWorkflowInstanceLink(
		long companyId, long groupId, String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getState(
			long companyId, long groupId, String className, long classPK)
		throws PortalException;

	/**
	 * Returns the workflow instance link with the primary key.
	 *
	 * @param workflowInstanceLinkId the primary key of the workflow instance link
	 * @return the workflow instance link
	 * @throws PortalException if a workflow instance link with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowInstanceLink getWorkflowInstanceLink(
			long workflowInstanceLinkId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WorkflowInstanceLink getWorkflowInstanceLink(
			long companyId, long groupId, String className, long classPK)
		throws PortalException;

	/**
	 * Returns a range of all the workflow instance links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WorkflowInstanceLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow instance links
	 * @param end the upper bound of the range of workflow instance links (not inclusive)
	 * @return the range of workflow instance links
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowInstanceLink> getWorkflowInstanceLinks(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WorkflowInstanceLink> getWorkflowInstanceLinks(
		long companyId, long groupId, String className, long classPK);

	/**
	 * Returns the number of workflow instance links.
	 *
	 * @return the number of workflow instance links
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkflowInstanceLinksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWorkflowInstanceLink(
		long companyId, long groupId, String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isEnded(
			long companyId, long groupId, String className, long classPK)
		throws PortalException;

	public void startWorkflowInstance(
			long companyId, long groupId, long userId, String className,
			long classPK, Map<String, Serializable> workflowContext)
		throws PortalException;

	public void startWorkflowInstance(
			long companyId, long groupId, long userId, String className,
			long classPK, Map<String, Serializable> workflowContext,
			boolean waitForCompletion)
		throws PortalException;

	public void updateClassPK(
			long companyId, long groupId, String className, long oldClassPK,
			long newClassPK)
		throws PortalException;

	/**
	 * Updates the workflow instance link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowInstanceLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowInstanceLink the workflow instance link
	 * @return the workflow instance link that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WorkflowInstanceLink updateWorkflowInstanceLink(
		WorkflowInstanceLink workflowInstanceLink);

}