/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link RoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see RoleService
 * @generated
 */
public class RoleServiceWrapper
	implements RoleService, ServiceWrapper<RoleService> {

	public RoleServiceWrapper() {
		this(null);
	}

	public RoleServiceWrapper(RoleService roleService) {
		_roleService = roleService;
	}

	@Override
	public com.liferay.portal.kernel.model.Role addRole(
			java.lang.String externalReferenceCode, java.lang.String className,
			long classPK, java.lang.String name,
			java.util.Map<java.util.Locale, java.lang.String> titleMap,
			java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
			int type, java.lang.String subtype, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.addRole(
			externalReferenceCode, className, classPK, name, titleMap,
			descriptionMap, type, subtype, serviceContext);
	}

	/**
	 * Adds the roles to the user. The user is reindexed after the roles are
	 * added.
	 *
	 * @param userId the primary key of the user
	 * @param roleIds the primary keys of the roles
	 */
	@Override
	public void addUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_roleService.addUserRoles(userId, roleIds);
	}

	/**
	 * Deletes the role with the primary key and its associated permissions.
	 *
	 * @param roleId the primary key of the role
	 */
	@Override
	public void deleteRole(long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_roleService.deleteRole(roleId);
	}

	@Override
	public com.liferay.portal.kernel.model.Role fetchRole(long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.fetchRole(roleId);
	}

	@Override
	public com.liferay.portal.kernel.model.Role fetchRole(
			long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.fetchRole(companyId, name);
	}

	@Override
	public com.liferay.portal.kernel.model.Role
			fetchRoleByExternalReferenceCode(
				java.lang.String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.fetchRoleByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns all the roles associated with the group.
	 *
	 * @param groupId the primary key of the group
	 * @return the roles associated with the group
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> getGroupRoles(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getGroupRoles(groupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
		getGroupRolesAndTeamRoles(
			long companyId, java.lang.String name,
			java.util.List<java.lang.String> excludedNames,
			java.lang.String title, java.lang.String description, int[] types,
			long excludedTeamRoleId, long teamGroupId, int start, int end) {

		return _roleService.getGroupRolesAndTeamRoles(
			companyId, name, excludedNames, title, description, types,
			excludedTeamRoleId, teamGroupId, start, end);
	}

	@Override
	public int getGroupRolesAndTeamRolesCount(
		long companyId, java.lang.String name,
		java.util.List<java.lang.String> excludedNames, java.lang.String title,
		java.lang.String description, int[] types, long excludedTeamRoleId,
		long teamGroupId) {

		return _roleService.getGroupRolesAndTeamRolesCount(
			companyId, name, excludedNames, title, description, types,
			excludedTeamRoleId, teamGroupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _roleService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the role with the primary key.
	 *
	 * @param roleId the primary key of the role
	 * @return the role with the primary key
	 */
	@Override
	public com.liferay.portal.kernel.model.Role getRole(long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getRole(roleId);
	}

	/**
	 * Returns the role with the name in the company.
	 *
	 * <p>
	 * The method searches the system roles map first for default roles. If a
	 * role with the name is not found, then the method will query the database.
	 * </p>
	 *
	 * @param companyId the primary key of the company
	 * @param name the role's name
	 * @return the role with the name
	 */
	@Override
	public com.liferay.portal.kernel.model.Role getRole(
			long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getRole(companyId, name);
	}

	@Override
	public com.liferay.portal.kernel.model.Role getRoleByExternalReferenceCode(
			java.lang.String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getRoleByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
			int type, java.lang.String subtype)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getRoles(type, subtype);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
			long companyId, int[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getRoles(companyId, types);
	}

	/**
	 * Returns all the user's roles within the user group.
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @return the user's roles within the user group
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
			getUserGroupGroupRoles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getUserGroupGroupRoles(userId, groupId);
	}

	/**
	 * Returns all the user's roles within the user group.
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @return the user's roles within the user group
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
			getUserGroupRoles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getUserGroupRoles(userId, groupId);
	}

	/**
	 * Returns the union of all the user's roles within the groups. If no
	 * groups are provided, only the user's directly assigned roles are
	 * returned.
	 *
	 * @param userId the primary key of the user
	 * @param groups the groups (optionally <code>null</code>)
	 * @return the union of all the user's roles within the groups
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role>
			getUserRelatedRoles(
				long userId,
				java.util.List<com.liferay.portal.kernel.model.Group> groups)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getUserRelatedRoles(userId, groups);
	}

	/**
	 * Returns all the roles associated with the user.
	 *
	 * @param userId the primary key of the user
	 * @return the roles associated with the user
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> getUserRoles(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.getUserRoles(userId);
	}

	/**
	 * Returns <code>true</code> if the user is associated with the named
	 * regular role.
	 *
	 * @param userId the primary key of the user
	 * @param companyId the primary key of the company
	 * @param name the name of the role
	 * @param inherited whether to include the user's inherited roles in the
	 search
	 * @return <code>true</code> if the user is associated with the regular
	 role; <code>false</code> otherwise
	 */
	@Override
	public boolean hasUserRole(
			long userId, long companyId, java.lang.String name,
			boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.hasUserRole(userId, companyId, name, inherited);
	}

	/**
	 * Returns <code>true</code> if the user has any one of the named regular
	 * roles.
	 *
	 * @param userId the primary key of the user
	 * @param companyId the primary key of the company
	 * @param names the names of the roles
	 * @param inherited whether to include the user's inherited roles in the
	 search
	 * @return <code>true</code> if the user has any one of the regular roles;
	 <code>false</code> otherwise
	 */
	@Override
	public boolean hasUserRoles(
			long userId, long companyId, java.lang.String[] names,
			boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.hasUserRoles(userId, companyId, names, inherited);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Role> search(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Role> orderByComparator) {

		return _roleService.search(
			companyId, keywords, types, params, start, end, orderByComparator);
	}

	@Override
	public int searchCount(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {

		return _roleService.searchCount(companyId, keywords, types, params);
	}

	/**
	 * Removes the matching roles associated with the user. The user is
	 * reindexed after the roles are removed.
	 *
	 * @param userId the primary key of the user
	 * @param roleIds the primary keys of the roles
	 */
	@Override
	public void unsetUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_roleService.unsetUserRoles(userId, roleIds);
	}

	@Override
	public com.liferay.portal.kernel.model.Role updateExternalReferenceCode(
			long roleId, java.lang.String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.updateExternalReferenceCode(
			roleId, externalReferenceCode);
	}

	@Override
	public com.liferay.portal.kernel.model.Role updateExternalReferenceCode(
			com.liferay.portal.kernel.model.Role role,
			java.lang.String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.updateExternalReferenceCode(
			role, externalReferenceCode);
	}

	/**
	 * Updates the role with the primary key.
	 *
	 * @param roleId the primary key of the role
	 * @param name the role's new name
	 * @param titleMap the new localized titles (optionally <code>null</code>)
	 to replace those existing for the role
	 * @param descriptionMap the new localized descriptions (optionally
	 <code>null</code>) to replace those existing for the role
	 * @param subtype the role's new subtype (optionally <code>null</code>)
	 * @param serviceContext the service context to be applied (optionally
	 <code>null</code>). Can set the expando bridge attributes for the
	 role.
	 * @return the role with the primary key
	 */
	@Override
	public com.liferay.portal.kernel.model.Role updateRole(
			long roleId, java.lang.String name,
			java.util.Map<java.util.Locale, java.lang.String> titleMap,
			java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
			java.lang.String subtype, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _roleService.updateRole(
			roleId, name, titleMap, descriptionMap, subtype, serviceContext);
	}

	@Override
	public RoleService getWrappedService() {
		return _roleService;
	}

	@Override
	public void setWrappedService(RoleService roleService) {
		_roleService = roleService;
	}

	private RoleService _roleService;

}