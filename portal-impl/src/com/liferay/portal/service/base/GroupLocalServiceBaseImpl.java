/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the group local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.GroupLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.GroupLocalServiceImpl
 * @generated
 */
public abstract class GroupLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements GroupLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>GroupLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>GroupLocalServiceUtil</code>.
	 */

	/**
	 * Adds the group to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param group the group
	 * @return the group that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Group addGroup(Group group) {
		group.setNew(true);

		return groupPersistence.update(group);
	}

	/**
	 * Creates a new group with the primary key. Does not add the group to the database.
	 *
	 * @param groupId the primary key for the new group
	 * @return the new group
	 */
	@Override
	@Transactional(enabled = false)
	public Group createGroup(long groupId) {
		return groupPersistence.create(groupId);
	}

	/**
	 * Deletes the group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @return the group that was removed
	 * @throws PortalException if a group with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Group deleteGroup(long groupId) throws PortalException {
		return groupPersistence.remove(groupId);
	}

	/**
	 * Deletes the group from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param group the group
	 * @return the group that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Group deleteGroup(Group group) throws PortalException {
		return groupPersistence.remove(group);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return groupPersistence.dslQuery(dslQuery);
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
			Group.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return groupPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.GroupModelImpl</code>.
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

		return groupPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.GroupModelImpl</code>.
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

		return groupPersistence.findWithDynamicQuery(
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
		return groupPersistence.countWithDynamicQuery(dynamicQuery);
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

		return groupPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Group fetchGroup(long groupId) {
		return groupPersistence.fetchByPrimaryKey(groupId);
	}

	/**
	 * Returns the group with the matching UUID and company.
	 *
	 * @param uuid the group's UUID
	 * @param companyId the primary key of the company
	 * @return the matching group, or <code>null</code> if a matching group could not be found
	 */
	@Override
	public Group fetchGroupByUuidAndCompanyId(String uuid, long companyId) {
		return groupPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	@Override
	public Group fetchGroupByExternalReferenceCode(
		String externalReferenceCode, long companyId) {

		return groupPersistence.fetchByERC_C(externalReferenceCode, companyId);
	}

	@Override
	public Group getGroupByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return groupPersistence.findByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the group with the primary key.
	 *
	 * @param groupId the primary key of the group
	 * @return the group
	 * @throws PortalException if a group with the primary key could not be found
	 */
	@Override
	public Group getGroup(long groupId) throws PortalException {
		return groupPersistence.findByPrimaryKey(groupId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(groupLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Group.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("groupId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(groupLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Group.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("groupId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(groupLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Group.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("groupId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return groupPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement GroupLocalServiceImpl#deleteGroup(Group) to avoid orphaned data");
		}

		return groupLocalService.deleteGroup((Group)persistedModel);
	}

	@Override
	public BasePersistence<Group> getBasePersistence() {
		return groupPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return groupPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the group with the matching UUID and company.
	 *
	 * @param uuid the group's UUID
	 * @param companyId the primary key of the company
	 * @return the matching group
	 * @throws PortalException if a matching group could not be found
	 */
	@Override
	public Group getGroupByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException {

		return groupPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.GroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of groups
	 * @param end the upper bound of the range of groups (not inclusive)
	 * @return the range of groups
	 */
	@Override
	public List<Group> getGroups(int start, int end) {
		return groupPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of groups.
	 *
	 * @return the number of groups
	 */
	@Override
	public int getGroupsCount() {
		return groupPersistence.countAll();
	}

	/**
	 * Updates the group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect GroupLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param group the group
	 * @return the group that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Group updateGroup(Group group) {
		return groupPersistence.update(group);
	}

	/**
	 */
	@Override
	public boolean addOrganizationGroup(long organizationId, long groupId) {
		return organizationPersistence.addGroup(organizationId, groupId);
	}

	/**
	 */
	@Override
	public boolean addOrganizationGroup(long organizationId, Group group) {
		return organizationPersistence.addGroup(organizationId, group);
	}

	/**
	 */
	@Override
	public boolean addOrganizationGroups(long organizationId, long[] groupIds) {
		return organizationPersistence.addGroups(organizationId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addOrganizationGroups(
		long organizationId, List<Group> groups) {

		return organizationPersistence.addGroups(organizationId, groups);
	}

	/**
	 */
	@Override
	public void clearOrganizationGroups(long organizationId) {
		organizationPersistence.clearGroups(organizationId);
	}

	/**
	 */
	@Override
	public void deleteOrganizationGroup(long organizationId, long groupId) {
		organizationPersistence.removeGroup(organizationId, groupId);
	}

	/**
	 */
	@Override
	public void deleteOrganizationGroup(long organizationId, Group group) {
		organizationPersistence.removeGroup(organizationId, group);
	}

	/**
	 */
	@Override
	public void deleteOrganizationGroups(long organizationId, long[] groupIds) {
		organizationPersistence.removeGroups(organizationId, groupIds);
	}

	/**
	 */
	@Override
	public void deleteOrganizationGroups(
		long organizationId, List<Group> groups) {

		organizationPersistence.removeGroups(organizationId, groups);
	}

	/**
	 * Returns the organizationIds of the organizations associated with the group.
	 *
	 * @param groupId the groupId of the group
	 * @return long[] the organizationIds of organizations associated with the group
	 */
	@Override
	public long[] getOrganizationPrimaryKeys(long groupId) {
		return groupPersistence.getOrganizationPrimaryKeys(groupId);
	}

	/**
	 */
	@Override
	public List<Group> getOrganizationGroups(long organizationId) {
		return organizationPersistence.getGroups(organizationId);
	}

	/**
	 */
	@Override
	public List<Group> getOrganizationGroups(
		long organizationId, int start, int end) {

		return organizationPersistence.getGroups(organizationId, start, end);
	}

	/**
	 */
	@Override
	public List<Group> getOrganizationGroups(
		long organizationId, int start, int end,
		OrderByComparator<Group> orderByComparator) {

		return organizationPersistence.getGroups(
			organizationId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getOrganizationGroupsCount(long organizationId) {
		return organizationPersistence.getGroupsSize(organizationId);
	}

	/**
	 */
	@Override
	public boolean hasOrganizationGroup(long organizationId, long groupId) {
		return organizationPersistence.containsGroup(organizationId, groupId);
	}

	/**
	 */
	@Override
	public boolean hasOrganizationGroups(long organizationId) {
		return organizationPersistence.containsGroups(organizationId);
	}

	/**
	 */
	@Override
	public void setOrganizationGroups(long organizationId, long[] groupIds) {
		organizationPersistence.setGroups(organizationId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addRoleGroup(long roleId, long groupId) {
		return rolePersistence.addGroup(roleId, groupId);
	}

	/**
	 */
	@Override
	public boolean addRoleGroup(long roleId, Group group) {
		return rolePersistence.addGroup(roleId, group);
	}

	/**
	 */
	@Override
	public boolean addRoleGroups(long roleId, long[] groupIds) {
		return rolePersistence.addGroups(roleId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addRoleGroups(long roleId, List<Group> groups) {
		return rolePersistence.addGroups(roleId, groups);
	}

	/**
	 */
	@Override
	public void clearRoleGroups(long roleId) {
		rolePersistence.clearGroups(roleId);
	}

	/**
	 */
	@Override
	public void deleteRoleGroup(long roleId, long groupId) {
		rolePersistence.removeGroup(roleId, groupId);
	}

	/**
	 */
	@Override
	public void deleteRoleGroup(long roleId, Group group) {
		rolePersistence.removeGroup(roleId, group);
	}

	/**
	 */
	@Override
	public void deleteRoleGroups(long roleId, long[] groupIds) {
		rolePersistence.removeGroups(roleId, groupIds);
	}

	/**
	 */
	@Override
	public void deleteRoleGroups(long roleId, List<Group> groups) {
		rolePersistence.removeGroups(roleId, groups);
	}

	/**
	 * Returns the roleIds of the roles associated with the group.
	 *
	 * @param groupId the groupId of the group
	 * @return long[] the roleIds of roles associated with the group
	 */
	@Override
	public long[] getRolePrimaryKeys(long groupId) {
		return groupPersistence.getRolePrimaryKeys(groupId);
	}

	/**
	 */
	@Override
	public List<Group> getRoleGroups(long roleId) {
		return rolePersistence.getGroups(roleId);
	}

	/**
	 */
	@Override
	public List<Group> getRoleGroups(long roleId, int start, int end) {
		return rolePersistence.getGroups(roleId, start, end);
	}

	/**
	 */
	@Override
	public List<Group> getRoleGroups(
		long roleId, int start, int end,
		OrderByComparator<Group> orderByComparator) {

		return rolePersistence.getGroups(roleId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getRoleGroupsCount(long roleId) {
		return rolePersistence.getGroupsSize(roleId);
	}

	/**
	 */
	@Override
	public boolean hasRoleGroup(long roleId, long groupId) {
		return rolePersistence.containsGroup(roleId, groupId);
	}

	/**
	 */
	@Override
	public boolean hasRoleGroups(long roleId) {
		return rolePersistence.containsGroups(roleId);
	}

	/**
	 */
	@Override
	public void setRoleGroups(long roleId, long[] groupIds) {
		rolePersistence.setGroups(roleId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addUserGroupGroup(long userGroupId, long groupId) {
		return userGroupPersistence.addGroup(userGroupId, groupId);
	}

	/**
	 */
	@Override
	public boolean addUserGroupGroup(long userGroupId, Group group) {
		return userGroupPersistence.addGroup(userGroupId, group);
	}

	/**
	 */
	@Override
	public boolean addUserGroupGroups(long userGroupId, long[] groupIds) {
		return userGroupPersistence.addGroups(userGroupId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addUserGroupGroups(long userGroupId, List<Group> groups) {
		return userGroupPersistence.addGroups(userGroupId, groups);
	}

	/**
	 */
	@Override
	public void clearUserGroupGroups(long userGroupId) {
		userGroupPersistence.clearGroups(userGroupId);
	}

	/**
	 */
	@Override
	public void deleteUserGroupGroup(long userGroupId, long groupId) {
		userGroupPersistence.removeGroup(userGroupId, groupId);
	}

	/**
	 */
	@Override
	public void deleteUserGroupGroup(long userGroupId, Group group) {
		userGroupPersistence.removeGroup(userGroupId, group);
	}

	/**
	 */
	@Override
	public void deleteUserGroupGroups(long userGroupId, long[] groupIds) {
		userGroupPersistence.removeGroups(userGroupId, groupIds);
	}

	/**
	 */
	@Override
	public void deleteUserGroupGroups(long userGroupId, List<Group> groups) {
		userGroupPersistence.removeGroups(userGroupId, groups);
	}

	/**
	 * Returns the userGroupIds of the user groups associated with the group.
	 *
	 * @param groupId the groupId of the group
	 * @return long[] the userGroupIds of user groups associated with the group
	 */
	@Override
	public long[] getUserGroupPrimaryKeys(long groupId) {
		return groupPersistence.getUserGroupPrimaryKeys(groupId);
	}

	/**
	 */
	@Override
	public List<Group> getUserGroupGroups(long userGroupId) {
		return userGroupPersistence.getGroups(userGroupId);
	}

	/**
	 */
	@Override
	public List<Group> getUserGroupGroups(
		long userGroupId, int start, int end) {

		return userGroupPersistence.getGroups(userGroupId, start, end);
	}

	/**
	 */
	@Override
	public List<Group> getUserGroupGroups(
		long userGroupId, int start, int end,
		OrderByComparator<Group> orderByComparator) {

		return userGroupPersistence.getGroups(
			userGroupId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserGroupGroupsCount(long userGroupId) {
		return userGroupPersistence.getGroupsSize(userGroupId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupGroup(long userGroupId, long groupId) {
		return userGroupPersistence.containsGroup(userGroupId, groupId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupGroups(long userGroupId) {
		return userGroupPersistence.containsGroups(userGroupId);
	}

	/**
	 */
	@Override
	public void setUserGroupGroups(long userGroupId, long[] groupIds) {
		userGroupPersistence.setGroups(userGroupId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addUserGroup(long userId, long groupId) {
		return userPersistence.addGroup(userId, groupId);
	}

	/**
	 */
	@Override
	public boolean addUserGroup(long userId, Group group) {
		return userPersistence.addGroup(userId, group);
	}

	/**
	 */
	@Override
	public boolean addUserGroups(long userId, long[] groupIds) {
		return userPersistence.addGroups(userId, groupIds);
	}

	/**
	 */
	@Override
	public boolean addUserGroups(long userId, List<Group> groups) {
		return userPersistence.addGroups(userId, groups);
	}

	/**
	 */
	@Override
	public void clearUserGroups(long userId) {
		userPersistence.clearGroups(userId);
	}

	/**
	 */
	@Override
	public void deleteUserGroup(long userId, long groupId) {
		userPersistence.removeGroup(userId, groupId);
	}

	/**
	 */
	@Override
	public void deleteUserGroup(long userId, Group group) {
		userPersistence.removeGroup(userId, group);
	}

	/**
	 */
	@Override
	public void deleteUserGroups(long userId, long[] groupIds) {
		userPersistence.removeGroups(userId, groupIds);
	}

	/**
	 */
	@Override
	public void deleteUserGroups(long userId, List<Group> groups) {
		userPersistence.removeGroups(userId, groups);
	}

	/**
	 * Returns the userIds of the users associated with the group.
	 *
	 * @param groupId the groupId of the group
	 * @return long[] the userIds of users associated with the group
	 */
	@Override
	public long[] getUserPrimaryKeys(long groupId) {
		return groupPersistence.getUserPrimaryKeys(groupId);
	}

	/**
	 */
	@Override
	public List<Group> getUserGroups(long userId) {
		return userPersistence.getGroups(userId);
	}

	/**
	 */
	@Override
	public List<Group> getUserGroups(long userId, int start, int end) {
		return userPersistence.getGroups(userId, start, end);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public List<Group> getUserGroups(
			long userId, int start, int end,
			OrderByComparator<Group> orderByComparator)
		throws PortalException {

		return userPersistence.getGroups(userId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserGroupsCount(long userId) {
		return userPersistence.getGroupsSize(userId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroup(long userId, long groupId) {
		return userPersistence.containsGroup(userId, groupId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroups(long userId) {
		return userPersistence.containsGroups(userId);
	}

	/**
	 */
	@Override
	public void setUserGroups(long userId, long[] groupIds) {
		userPersistence.setGroups(userId, groupIds);
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		GroupLocalServiceUtil.setService(groupLocalService);
	}

	public void destroy() {
		GroupLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return GroupLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Group.class;
	}

	protected String getModelClassName() {
		return Group.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = groupPersistence.getDataSource();

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

	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;

	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;

	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		GroupLocalServiceBaseImpl.class);

}