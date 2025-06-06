/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link TeamLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeamLocalService
 * @generated
 */
public class TeamLocalServiceWrapper
	implements ServiceWrapper<TeamLocalService>, TeamLocalService {

	public TeamLocalServiceWrapper() {
		this(null);
	}

	public TeamLocalServiceWrapper(TeamLocalService teamLocalService) {
		_teamLocalService = teamLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.Team addTeam(
			long userId, long groupId, String name, String description,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.addTeam(
			userId, groupId, name, description, serviceContext);
	}

	/**
	 * Adds the team to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was added
	 */
	@Override
	public com.liferay.portal.kernel.model.Team addTeam(
		com.liferay.portal.kernel.model.Team team) {

		return _teamLocalService.addTeam(team);
	}

	@Override
	public boolean addUserGroupTeam(long userGroupId, long teamId) {
		return _teamLocalService.addUserGroupTeam(userGroupId, teamId);
	}

	@Override
	public boolean addUserGroupTeam(
		long userGroupId, com.liferay.portal.kernel.model.Team team) {

		return _teamLocalService.addUserGroupTeam(userGroupId, team);
	}

	@Override
	public boolean addUserGroupTeams(
		long userGroupId,
		java.util.List<com.liferay.portal.kernel.model.Team> teams) {

		return _teamLocalService.addUserGroupTeams(userGroupId, teams);
	}

	@Override
	public boolean addUserGroupTeams(long userGroupId, long[] teamIds) {
		return _teamLocalService.addUserGroupTeams(userGroupId, teamIds);
	}

	@Override
	public boolean addUserTeam(long userId, long teamId) {
		return _teamLocalService.addUserTeam(userId, teamId);
	}

	@Override
	public boolean addUserTeam(
		long userId, com.liferay.portal.kernel.model.Team team) {

		return _teamLocalService.addUserTeam(userId, team);
	}

	@Override
	public boolean addUserTeams(
		long userId,
		java.util.List<com.liferay.portal.kernel.model.Team> teams) {

		return _teamLocalService.addUserTeams(userId, teams);
	}

	@Override
	public boolean addUserTeams(long userId, long[] teamIds) {
		return _teamLocalService.addUserTeams(userId, teamIds);
	}

	@Override
	public void clearUserGroupTeams(long userGroupId) {
		_teamLocalService.clearUserGroupTeams(userGroupId);
	}

	@Override
	public void clearUserTeams(long userId) {
		_teamLocalService.clearUserTeams(userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new team with the primary key. Does not add the team to the database.
	 *
	 * @param teamId the primary key for the new team
	 * @return the new team
	 */
	@Override
	public com.liferay.portal.kernel.model.Team createTeam(long teamId) {
		return _teamLocalService.createTeam(teamId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamId the primary key of the team
	 * @return the team that was removed
	 * @throws PortalException if a team with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Team deleteTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.deleteTeam(teamId);
	}

	/**
	 * Deletes the team from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.Team deleteTeam(
			com.liferay.portal.kernel.model.Team team)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.deleteTeam(team);
	}

	@Override
	public void deleteTeams(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_teamLocalService.deleteTeams(groupId);
	}

	@Override
	public void deleteUserGroupTeam(long userGroupId, long teamId) {
		_teamLocalService.deleteUserGroupTeam(userGroupId, teamId);
	}

	@Override
	public void deleteUserGroupTeam(
		long userGroupId, com.liferay.portal.kernel.model.Team team) {

		_teamLocalService.deleteUserGroupTeam(userGroupId, team);
	}

	@Override
	public void deleteUserGroupTeams(
		long userGroupId,
		java.util.List<com.liferay.portal.kernel.model.Team> teams) {

		_teamLocalService.deleteUserGroupTeams(userGroupId, teams);
	}

	@Override
	public void deleteUserGroupTeams(long userGroupId, long[] teamIds) {
		_teamLocalService.deleteUserGroupTeams(userGroupId, teamIds);
	}

	@Override
	public void deleteUserTeam(long userId, long teamId) {
		_teamLocalService.deleteUserTeam(userId, teamId);
	}

	@Override
	public void deleteUserTeam(
		long userId, com.liferay.portal.kernel.model.Team team) {

		_teamLocalService.deleteUserTeam(userId, team);
	}

	@Override
	public void deleteUserTeams(
		long userId,
		java.util.List<com.liferay.portal.kernel.model.Team> teams) {

		_teamLocalService.deleteUserTeams(userId, teams);
	}

	@Override
	public void deleteUserTeams(long userId, long[] teamIds) {
		_teamLocalService.deleteUserTeams(userId, teamIds);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _teamLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _teamLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _teamLocalService.dynamicQuery();
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

		return _teamLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
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

		return _teamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
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

		return _teamLocalService.dynamicQuery(
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

		return _teamLocalService.dynamicQueryCount(dynamicQuery);
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

		return _teamLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.kernel.model.Team fetchTeam(long teamId) {
		return _teamLocalService.fetchTeam(teamId);
	}

	@Override
	public com.liferay.portal.kernel.model.Team fetchTeam(
		long groupId, String name) {

		return _teamLocalService.fetchTeam(groupId, name);
	}

	/**
	 * Returns the team matching the UUID and group.
	 *
	 * @param uuid the team's UUID
	 * @param groupId the primary key of the group
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Team fetchTeamByUuidAndGroupId(
		String uuid, long groupId) {

		return _teamLocalService.fetchTeamByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _teamLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _teamLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getGroupTeams(
		long groupId) {

		return _teamLocalService.getGroupTeams(groupId);
	}

	@Override
	public int getGroupTeamsCount(long groupId) {
		return _teamLocalService.getGroupTeamsCount(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _teamLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teamLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the team with the primary key.
	 *
	 * @param teamId the primary key of the team
	 * @return the team
	 * @throws PortalException if a team with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Team getTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.getTeam(teamId);
	}

	@Override
	public com.liferay.portal.kernel.model.Team getTeam(
			long groupId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.getTeam(groupId, name);
	}

	/**
	 * Returns the team matching the UUID and group.
	 *
	 * @param uuid the team's UUID
	 * @param groupId the primary key of the group
	 * @return the matching team
	 * @throws PortalException if a matching team could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Team getTeamByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.getTeamByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of teams
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getTeams(
		int start, int end) {

		return _teamLocalService.getTeams(start, end);
	}

	/**
	 * Returns all the teams matching the UUID and company.
	 *
	 * @param uuid the UUID of the teams
	 * @param companyId the primary key of the company
	 * @return the matching teams, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getTeamsByUuidAndCompanyId(String uuid, long companyId) {

		return _teamLocalService.getTeamsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of teams matching the UUID and company.
	 *
	 * @param uuid the UUID of the teams
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching teams, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getTeamsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return _teamLocalService.getTeamsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of teams.
	 *
	 * @return the number of teams
	 */
	@Override
	public int getTeamsCount() {
		return _teamLocalService.getTeamsCount();
	}

	/**
	 * Returns the userGroupIds of the user groups associated with the team.
	 *
	 * @param teamId the teamId of the team
	 * @return long[] the userGroupIds of user groups associated with the team
	 */
	@Override
	public long[] getUserGroupPrimaryKeys(long teamId) {
		return _teamLocalService.getUserGroupPrimaryKeys(teamId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getUserGroupTeams(long userGroupId) {

		return _teamLocalService.getUserGroupTeams(userGroupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getUserGroupTeams(long userGroupId, int start, int end) {

		return _teamLocalService.getUserGroupTeams(userGroupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getUserGroupTeams(
			long userGroupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return _teamLocalService.getUserGroupTeams(
			userGroupId, start, end, orderByComparator);
	}

	@Override
	public int getUserGroupTeamsCount(long userGroupId) {
		return _teamLocalService.getUserGroupTeamsCount(userGroupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team>
		getUserOrUserGroupTeams(long groupId, long userId) {

		return _teamLocalService.getUserOrUserGroupTeams(groupId, userId);
	}

	/**
	 * Returns the userIds of the users associated with the team.
	 *
	 * @param teamId the teamId of the team
	 * @return long[] the userIds of users associated with the team
	 */
	@Override
	public long[] getUserPrimaryKeys(long teamId) {
		return _teamLocalService.getUserPrimaryKeys(teamId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
		long userId) {

		return _teamLocalService.getUserTeams(userId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
		long userId, int start, int end) {

		return _teamLocalService.getUserTeams(userId, start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return _teamLocalService.getUserTeams(
			userId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
		long userId, long groupId) {

		return _teamLocalService.getUserTeams(userId, groupId);
	}

	@Override
	public int getUserTeamsCount(long userId) {
		return _teamLocalService.getUserTeamsCount(userId);
	}

	@Override
	public boolean hasUserGroupTeam(long userGroupId, long teamId) {
		return _teamLocalService.hasUserGroupTeam(userGroupId, teamId);
	}

	@Override
	public boolean hasUserGroupTeams(long userGroupId) {
		return _teamLocalService.hasUserGroupTeams(userGroupId);
	}

	@Override
	public boolean hasUserTeam(long userId, long teamId) {
		return _teamLocalService.hasUserTeam(userId, teamId);
	}

	@Override
	public boolean hasUserTeams(long userId) {
		return _teamLocalService.hasUserTeams(userId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> search(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Team> orderByComparator) {

		return _teamLocalService.search(
			groupId, name, description, params, start, end, orderByComparator);
	}

	@Override
	public int searchCount(
		long groupId, String name, String description,
		java.util.LinkedHashMap<String, Object> params) {

		return _teamLocalService.searchCount(
			groupId, name, description, params);
	}

	@Override
	public void setUserGroupTeams(long userGroupId, long[] teamIds) {
		_teamLocalService.setUserGroupTeams(userGroupId, teamIds);
	}

	@Override
	public void setUserTeams(long userId, long[] teamIds) {
		_teamLocalService.setUserTeams(userId, teamIds);
	}

	@Override
	public com.liferay.portal.kernel.model.Team updateTeam(
			long teamId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamLocalService.updateTeam(teamId, name, description);
	}

	/**
	 * Updates the team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was updated
	 */
	@Override
	public com.liferay.portal.kernel.model.Team updateTeam(
		com.liferay.portal.kernel.model.Team team) {

		return _teamLocalService.updateTeam(team);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _teamLocalService.getBasePersistence();
	}

	@Override
	public TeamLocalService getWrappedService() {
		return _teamLocalService;
	}

	@Override
	public void setWrappedService(TeamLocalService teamLocalService) {
		_teamLocalService = teamLocalService;
	}

	private TeamLocalService _teamLocalService;

}