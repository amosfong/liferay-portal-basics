/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user group service. This utility wraps <code>com.liferay.portal.service.persistence.impl.UserGroupPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupPersistence
 * @generated
 */
public class UserGroupUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserGroup userGroup) {
		getPersistence().clearCache(userGroup);
	}

	/**
	 * @see BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, UserGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserGroup update(UserGroup userGroup) {
		return getPersistence().update(userGroup);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserGroup update(
		UserGroup userGroup, ServiceContext serviceContext) {

		return getPersistence().update(userGroup, serviceContext);
	}

	/**
	 * Returns all the user groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the user groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByUuid_First(
			String uuid, OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByUuid_First(
		String uuid, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByUuid_Last(
			String uuid, OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByUuid_Last(
		String uuid, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set where uuid = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] findByUuid_PrevAndNext(
			long userGroupId, String uuid,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_PrevAndNext(
			userGroupId, uuid, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set of user groups that the user has permission to view where uuid = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] filterFindByUuid_PrevAndNext(
			long userGroupId, String uuid,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			userGroupId, uuid, orderByComparator);
	}

	/**
	 * Removes all the user groups where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of user groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user groups
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the user groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the user groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] findByUuid_C_PrevAndNext(
			long userGroupId, String uuid, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByUuid_C_PrevAndNext(
			userGroupId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set of user groups that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] filterFindByUuid_C_PrevAndNext(
			long userGroupId, String uuid, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			userGroupId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the user groups where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of user groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching user groups
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the user groups where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the user groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByCompanyId_First(
			long companyId, OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByCompanyId_First(
		long companyId, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByCompanyId_Last(
			long companyId, OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByCompanyId_Last(
		long companyId, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set where companyId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] findByCompanyId_PrevAndNext(
			long userGroupId, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByCompanyId_PrevAndNext(
			userGroupId, companyId, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set of user groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] filterFindByCompanyId_PrevAndNext(
			long userGroupId, long companyId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().filterFindByCompanyId_PrevAndNext(
			userGroupId, companyId, orderByComparator);
	}

	/**
	 * Removes all the user groups where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of user groups where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching user groups
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	 * Returns all the user groups where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByC_P(
		long companyId, long parentUserGroupId) {

		return getPersistence().findByC_P(companyId, parentUserGroupId);
	}

	/**
	 * Returns a range of all the user groups where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByC_P(
		long companyId, long parentUserGroupId, int start, int end) {

		return getPersistence().findByC_P(
			companyId, parentUserGroupId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByC_P(
		long companyId, long parentUserGroupId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByC_P(
			companyId, parentUserGroupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByC_P(
		long companyId, long parentUserGroupId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_P(
			companyId, parentUserGroupId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByC_P_First(
			long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_P_First(
			companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_P_First(
		long companyId, long parentUserGroupId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByC_P_First(
			companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByC_P_Last(
			long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_P_Last(
			companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_P_Last(
		long companyId, long parentUserGroupId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByC_P_Last(
			companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] findByC_P_PrevAndNext(
			long userGroupId, long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_P_PrevAndNext(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_P(
		long companyId, long parentUserGroupId) {

		return getPersistence().filterFindByC_P(companyId, parentUserGroupId);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_P(
		long companyId, long parentUserGroupId, int start, int end) {

		return getPersistence().filterFindByC_P(
			companyId, parentUserGroupId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_P(
		long companyId, long parentUserGroupId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByC_P(
			companyId, parentUserGroupId, start, end, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set of user groups that the user has permission to view where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] filterFindByC_P_PrevAndNext(
			long userGroupId, long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().filterFindByC_P_PrevAndNext(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Removes all the user groups where companyId = &#63; and parentUserGroupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 */
	public static void removeByC_P(long companyId, long parentUserGroupId) {
		getPersistence().removeByC_P(companyId, parentUserGroupId);
	}

	/**
	 * Returns the number of user groups where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the number of matching user groups
	 */
	public static int countByC_P(long companyId, long parentUserGroupId) {
		return getPersistence().countByC_P(companyId, parentUserGroupId);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByC_P(long companyId, long parentUserGroupId) {
		return getPersistence().filterCountByC_P(companyId, parentUserGroupId);
	}

	/**
	 * Returns the user group where companyId = &#63; and name = &#63; or throws a <code>NoSuchUserGroupException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByC_N(long companyId, String name)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_N(companyId, name);
	}

	/**
	 * Returns the user group where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_N(long companyId, String name) {
		return getPersistence().fetchByC_N(companyId, name);
	}

	/**
	 * Returns the user group where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_N(
		long companyId, String name, boolean useFinderCache) {

		return getPersistence().fetchByC_N(companyId, name, useFinderCache);
	}

	/**
	 * Removes the user group where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the user group that was removed
	 */
	public static UserGroup removeByC_N(long companyId, String name)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().removeByC_N(companyId, name);
	}

	/**
	 * Returns the number of user groups where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching user groups
	 */
	public static int countByC_N(long companyId, String name) {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	 * Returns all the user groups where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByC_LikeN(long companyId, String name) {
		return getPersistence().findByC_LikeN(companyId, name);
	}

	/**
	 * Returns a range of all the user groups where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByC_LikeN(
		long companyId, String name, int start, int end) {

		return getPersistence().findByC_LikeN(companyId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByC_LikeN(
		long companyId, String name, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByC_LikeN(
			companyId, name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByC_LikeN(
		long companyId, String name, int start, int end,
		OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_LikeN(
			companyId, name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByC_LikeN_First(
			long companyId, String name,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_LikeN_First(
			companyId, name, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_LikeN_First(
		long companyId, String name,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByC_LikeN_First(
			companyId, name, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByC_LikeN_Last(
			long companyId, String name,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_LikeN_Last(
			companyId, name, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByC_LikeN_Last(
		long companyId, String name,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByC_LikeN_Last(
			companyId, name, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] findByC_LikeN_PrevAndNext(
			long userGroupId, long companyId, String name,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByC_LikeN_PrevAndNext(
			userGroupId, companyId, name, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_LikeN(
		long companyId, String name) {

		return getPersistence().filterFindByC_LikeN(companyId, name);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_LikeN(
		long companyId, String name, int start, int end) {

		return getPersistence().filterFindByC_LikeN(
			companyId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByC_LikeN(
		long companyId, String name, int start, int end,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByC_LikeN(
			companyId, name, start, end, orderByComparator);
	}

	/**
	 * Returns the user groups before and after the current user group in the ordered set of user groups that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param userGroupId the primary key of the current user group
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup[] filterFindByC_LikeN_PrevAndNext(
			long userGroupId, long companyId, String name,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().filterFindByC_LikeN_PrevAndNext(
			userGroupId, companyId, name, orderByComparator);
	}

	/**
	 * Removes all the user groups where companyId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 */
	public static void removeByC_LikeN(long companyId, String name) {
		getPersistence().removeByC_LikeN(companyId, name);
	}

	/**
	 * Returns the number of user groups where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching user groups
	 */
	public static int countByC_LikeN(long companyId, String name) {
		return getPersistence().countByC_LikeN(companyId, name);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByC_LikeN(long companyId, String name) {
		return getPersistence().filterCountByC_LikeN(companyId, name);
	}

	/**
	 * Returns all the user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the matching user groups
	 */
	public static List<UserGroup> findByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId) {

		return getPersistence().findByGtU_C_P(
			userGroupId, companyId, parentUserGroupId);
	}

	/**
	 * Returns a range of all the user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups
	 */
	public static List<UserGroup> findByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId, int start,
		int end) {

		return getPersistence().findByGtU_C_P(
			userGroupId, companyId, parentUserGroupId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId, int start,
		int end, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findByGtU_C_P(
			userGroupId, companyId, parentUserGroupId, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user groups
	 */
	public static List<UserGroup> findByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId, int start,
		int end, OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGtU_C_P(
			userGroupId, companyId, parentUserGroupId, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user group in the ordered set where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByGtU_C_P_First(
			long userGroupId, long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByGtU_C_P_First(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the first user group in the ordered set where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByGtU_C_P_First(
		long userGroupId, long companyId, long parentUserGroupId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByGtU_C_P_First(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByGtU_C_P_Last(
			long userGroupId, long companyId, long parentUserGroupId,
			OrderByComparator<UserGroup> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByGtU_C_P_Last(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns the last user group in the ordered set where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByGtU_C_P_Last(
		long userGroupId, long companyId, long parentUserGroupId,
		OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().fetchByGtU_C_P_Last(
			userGroupId, companyId, parentUserGroupId, orderByComparator);
	}

	/**
	 * Returns all the user groups that the user has permission to view where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId) {

		return getPersistence().filterFindByGtU_C_P(
			userGroupId, companyId, parentUserGroupId);
	}

	/**
	 * Returns a range of all the user groups that the user has permission to view where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId, int start,
		int end) {

		return getPersistence().filterFindByGtU_C_P(
			userGroupId, companyId, parentUserGroupId, start, end);
	}

	/**
	 * Returns an ordered range of all the user groups that the user has permissions to view where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user groups that the user has permission to view
	 */
	public static List<UserGroup> filterFindByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId, int start,
		int end, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().filterFindByGtU_C_P(
			userGroupId, companyId, parentUserGroupId, start, end,
			orderByComparator);
	}

	/**
	 * Removes all the user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63; from the database.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 */
	public static void removeByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId) {

		getPersistence().removeByGtU_C_P(
			userGroupId, companyId, parentUserGroupId);
	}

	/**
	 * Returns the number of user groups where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the number of matching user groups
	 */
	public static int countByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId) {

		return getPersistence().countByGtU_C_P(
			userGroupId, companyId, parentUserGroupId);
	}

	/**
	 * Returns the number of user groups that the user has permission to view where userGroupId &gt; &#63; and companyId = &#63; and parentUserGroupId = &#63;.
	 *
	 * @param userGroupId the user group ID
	 * @param companyId the company ID
	 * @param parentUserGroupId the parent user group ID
	 * @return the number of matching user groups that the user has permission to view
	 */
	public static int filterCountByGtU_C_P(
		long userGroupId, long companyId, long parentUserGroupId) {

		return getPersistence().filterCountByGtU_C_P(
			userGroupId, companyId, parentUserGroupId);
	}

	/**
	 * Returns the user group where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchUserGroupException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching user group
	 * @throws NoSuchUserGroupException if a matching user group could not be found
	 */
	public static UserGroup findByERC_C(
			String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the user group where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().fetchByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the user group where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user group, or <code>null</code> if a matching user group could not be found
	 */
	public static UserGroup fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache) {

		return getPersistence().fetchByERC_C(
			externalReferenceCode, companyId, useFinderCache);
	}

	/**
	 * Removes the user group where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the user group that was removed
	 */
	public static UserGroup removeByERC_C(
			String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().removeByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the number of user groups where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching user groups
	 */
	public static int countByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().countByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Caches the user group in the entity cache if it is enabled.
	 *
	 * @param userGroup the user group
	 */
	public static void cacheResult(UserGroup userGroup) {
		getPersistence().cacheResult(userGroup);
	}

	/**
	 * Caches the user groups in the entity cache if it is enabled.
	 *
	 * @param userGroups the user groups
	 */
	public static void cacheResult(List<UserGroup> userGroups) {
		getPersistence().cacheResult(userGroups);
	}

	/**
	 * Creates a new user group with the primary key. Does not add the user group to the database.
	 *
	 * @param userGroupId the primary key for the new user group
	 * @return the new user group
	 */
	public static UserGroup create(long userGroupId) {
		return getPersistence().create(userGroupId);
	}

	/**
	 * Removes the user group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userGroupId the primary key of the user group
	 * @return the user group that was removed
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup remove(long userGroupId)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().remove(userGroupId);
	}

	public static UserGroup updateImpl(UserGroup userGroup) {
		return getPersistence().updateImpl(userGroup);
	}

	/**
	 * Returns the user group with the primary key or throws a <code>NoSuchUserGroupException</code> if it could not be found.
	 *
	 * @param userGroupId the primary key of the user group
	 * @return the user group
	 * @throws NoSuchUserGroupException if a user group with the primary key could not be found
	 */
	public static UserGroup findByPrimaryKey(long userGroupId)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupException {

		return getPersistence().findByPrimaryKey(userGroupId);
	}

	/**
	 * Returns the user group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userGroupId the primary key of the user group
	 * @return the user group, or <code>null</code> if a user group with the primary key could not be found
	 */
	public static UserGroup fetchByPrimaryKey(long userGroupId) {
		return getPersistence().fetchByPrimaryKey(userGroupId);
	}

	/**
	 * Returns all the user groups.
	 *
	 * @return the user groups
	 */
	public static List<UserGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of user groups
	 */
	public static List<UserGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user groups
	 */
	public static List<UserGroup> findAll(
		int start, int end, OrderByComparator<UserGroup> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user groups
	 */
	public static List<UserGroup> findAll(
		int start, int end, OrderByComparator<UserGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user groups from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user groups.
	 *
	 * @return the number of user groups
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of groups associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return long[] of the primaryKeys of groups associated with the user group
	 */
	public static long[] getGroupPrimaryKeys(long pk) {
		return getPersistence().getGroupPrimaryKeys(pk);
	}

	/**
	 * Returns all the groups associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the groups associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk) {

		return getPersistence().getGroups(pk);
	}

	/**
	 * Returns a range of all the groups associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of groups associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk, int start, int end) {

		return getPersistence().getGroups(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the groups associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of groups associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.model.Group>
			orderByComparator) {

		return getPersistence().getGroups(pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of groups associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the number of groups associated with the user group
	 */
	public static int getGroupsSize(long pk) {
		return getPersistence().getGroupsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the group is associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPK the primary key of the group
	 * @return <code>true</code> if the group is associated with the user group; <code>false</code> otherwise
	 */
	public static boolean containsGroup(long pk, long groupPK) {
		return getPersistence().containsGroup(pk, groupPK);
	}

	/**
	 * Returns <code>true</code> if the user group has any groups associated with it.
	 *
	 * @param pk the primary key of the user group to check for associations with groups
	 * @return <code>true</code> if the user group has any groups associated with it; <code>false</code> otherwise
	 */
	public static boolean containsGroups(long pk) {
		return getPersistence().containsGroups(pk);
	}

	/**
	 * Adds an association between the user group and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPK the primary key of the group
	 * @return <code>true</code> if an association between the user group and the group was added; <code>false</code> if they were already associated
	 */
	public static boolean addGroup(long pk, long groupPK) {
		return getPersistence().addGroup(pk, groupPK);
	}

	/**
	 * Adds an association between the user group and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param group the group
	 * @return <code>true</code> if an association between the user group and the group was added; <code>false</code> if they were already associated
	 */
	public static boolean addGroup(
		long pk, com.liferay.portal.kernel.model.Group group) {

		return getPersistence().addGroup(pk, group);
	}

	/**
	 * Adds an association between the user group and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPKs the primary keys of the groups
	 * @return <code>true</code> if at least one association between the user group and the groups was added; <code>false</code> if they were all already associated
	 */
	public static boolean addGroups(long pk, long[] groupPKs) {
		return getPersistence().addGroups(pk, groupPKs);
	}

	/**
	 * Adds an association between the user group and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groups the groups
	 * @return <code>true</code> if at least one association between the user group and the groups was added; <code>false</code> if they were all already associated
	 */
	public static boolean addGroups(
		long pk, List<com.liferay.portal.kernel.model.Group> groups) {

		return getPersistence().addGroups(pk, groups);
	}

	/**
	 * Clears all associations between the user group and its groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group to clear the associated groups from
	 */
	public static void clearGroups(long pk) {
		getPersistence().clearGroups(pk);
	}

	/**
	 * Removes the association between the user group and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPK the primary key of the group
	 */
	public static void removeGroup(long pk, long groupPK) {
		getPersistence().removeGroup(pk, groupPK);
	}

	/**
	 * Removes the association between the user group and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param group the group
	 */
	public static void removeGroup(
		long pk, com.liferay.portal.kernel.model.Group group) {

		getPersistence().removeGroup(pk, group);
	}

	/**
	 * Removes the association between the user group and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPKs the primary keys of the groups
	 */
	public static void removeGroups(long pk, long[] groupPKs) {
		getPersistence().removeGroups(pk, groupPKs);
	}

	/**
	 * Removes the association between the user group and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groups the groups
	 */
	public static void removeGroups(
		long pk, List<com.liferay.portal.kernel.model.Group> groups) {

		getPersistence().removeGroups(pk, groups);
	}

	/**
	 * Sets the groups associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groupPKs the primary keys of the groups to be associated with the user group
	 */
	public static void setGroups(long pk, long[] groupPKs) {
		getPersistence().setGroups(pk, groupPKs);
	}

	/**
	 * Sets the groups associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param groups the groups to be associated with the user group
	 */
	public static void setGroups(
		long pk, List<com.liferay.portal.kernel.model.Group> groups) {

		getPersistence().setGroups(pk, groups);
	}

	/**
	 * Returns the primaryKeys of teams associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return long[] of the primaryKeys of teams associated with the user group
	 */
	public static long[] getTeamPrimaryKeys(long pk) {
		return getPersistence().getTeamPrimaryKeys(pk);
	}

	/**
	 * Returns all the teams associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the teams associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Team> getTeams(long pk) {
		return getPersistence().getTeams(pk);
	}

	/**
	 * Returns a range of all the teams associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of teams associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Team> getTeams(
		long pk, int start, int end) {

		return getPersistence().getTeams(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the teams associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teams associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.Team> getTeams(
		long pk, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.model.Team>
			orderByComparator) {

		return getPersistence().getTeams(pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of teams associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the number of teams associated with the user group
	 */
	public static int getTeamsSize(long pk) {
		return getPersistence().getTeamsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the team is associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPK the primary key of the team
	 * @return <code>true</code> if the team is associated with the user group; <code>false</code> otherwise
	 */
	public static boolean containsTeam(long pk, long teamPK) {
		return getPersistence().containsTeam(pk, teamPK);
	}

	/**
	 * Returns <code>true</code> if the user group has any teams associated with it.
	 *
	 * @param pk the primary key of the user group to check for associations with teams
	 * @return <code>true</code> if the user group has any teams associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTeams(long pk) {
		return getPersistence().containsTeams(pk);
	}

	/**
	 * Adds an association between the user group and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPK the primary key of the team
	 * @return <code>true</code> if an association between the user group and the team was added; <code>false</code> if they were already associated
	 */
	public static boolean addTeam(long pk, long teamPK) {
		return getPersistence().addTeam(pk, teamPK);
	}

	/**
	 * Adds an association between the user group and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param team the team
	 * @return <code>true</code> if an association between the user group and the team was added; <code>false</code> if they were already associated
	 */
	public static boolean addTeam(
		long pk, com.liferay.portal.kernel.model.Team team) {

		return getPersistence().addTeam(pk, team);
	}

	/**
	 * Adds an association between the user group and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPKs the primary keys of the teams
	 * @return <code>true</code> if at least one association between the user group and the teams was added; <code>false</code> if they were all already associated
	 */
	public static boolean addTeams(long pk, long[] teamPKs) {
		return getPersistence().addTeams(pk, teamPKs);
	}

	/**
	 * Adds an association between the user group and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teams the teams
	 * @return <code>true</code> if at least one association between the user group and the teams was added; <code>false</code> if they were all already associated
	 */
	public static boolean addTeams(
		long pk, List<com.liferay.portal.kernel.model.Team> teams) {

		return getPersistence().addTeams(pk, teams);
	}

	/**
	 * Clears all associations between the user group and its teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group to clear the associated teams from
	 */
	public static void clearTeams(long pk) {
		getPersistence().clearTeams(pk);
	}

	/**
	 * Removes the association between the user group and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPK the primary key of the team
	 */
	public static void removeTeam(long pk, long teamPK) {
		getPersistence().removeTeam(pk, teamPK);
	}

	/**
	 * Removes the association between the user group and the team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param team the team
	 */
	public static void removeTeam(
		long pk, com.liferay.portal.kernel.model.Team team) {

		getPersistence().removeTeam(pk, team);
	}

	/**
	 * Removes the association between the user group and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPKs the primary keys of the teams
	 */
	public static void removeTeams(long pk, long[] teamPKs) {
		getPersistence().removeTeams(pk, teamPKs);
	}

	/**
	 * Removes the association between the user group and the teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teams the teams
	 */
	public static void removeTeams(
		long pk, List<com.liferay.portal.kernel.model.Team> teams) {

		getPersistence().removeTeams(pk, teams);
	}

	/**
	 * Sets the teams associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teamPKs the primary keys of the teams to be associated with the user group
	 */
	public static void setTeams(long pk, long[] teamPKs) {
		getPersistence().setTeams(pk, teamPKs);
	}

	/**
	 * Sets the teams associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param teams the teams to be associated with the user group
	 */
	public static void setTeams(
		long pk, List<com.liferay.portal.kernel.model.Team> teams) {

		getPersistence().setTeams(pk, teams);
	}

	/**
	 * Returns the primaryKeys of users associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return long[] of the primaryKeys of users associated with the user group
	 */
	public static long[] getUserPrimaryKeys(long pk) {
		return getPersistence().getUserPrimaryKeys(pk);
	}

	/**
	 * Returns all the users associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the users associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.User> getUsers(long pk) {
		return getPersistence().getUsers(pk);
	}

	/**
	 * Returns a range of all the users associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @return the range of users associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.User> getUsers(
		long pk, int start, int end) {

		return getPersistence().getUsers(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the users associated with the user group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserGroupModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the user group
	 * @param start the lower bound of the range of user groups
	 * @param end the upper bound of the range of user groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users associated with the user group
	 */
	public static List<com.liferay.portal.kernel.model.User> getUsers(
		long pk, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.model.User>
			orderByComparator) {

		return getPersistence().getUsers(pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of users associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @return the number of users associated with the user group
	 */
	public static int getUsersSize(long pk) {
		return getPersistence().getUsersSize(pk);
	}

	/**
	 * Returns <code>true</code> if the user is associated with the user group.
	 *
	 * @param pk the primary key of the user group
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if the user is associated with the user group; <code>false</code> otherwise
	 */
	public static boolean containsUser(long pk, long userPK) {
		return getPersistence().containsUser(pk, userPK);
	}

	/**
	 * Returns <code>true</code> if the user group has any users associated with it.
	 *
	 * @param pk the primary key of the user group to check for associations with users
	 * @return <code>true</code> if the user group has any users associated with it; <code>false</code> otherwise
	 */
	public static boolean containsUsers(long pk) {
		return getPersistence().containsUsers(pk);
	}

	/**
	 * Adds an association between the user group and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if an association between the user group and the user was added; <code>false</code> if they were already associated
	 */
	public static boolean addUser(long pk, long userPK) {
		return getPersistence().addUser(pk, userPK);
	}

	/**
	 * Adds an association between the user group and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param user the user
	 * @return <code>true</code> if an association between the user group and the user was added; <code>false</code> if they were already associated
	 */
	public static boolean addUser(
		long pk, com.liferay.portal.kernel.model.User user) {

		return getPersistence().addUser(pk, user);
	}

	/**
	 * Adds an association between the user group and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param userPKs the primary keys of the users
	 * @return <code>true</code> if at least one association between the user group and the users was added; <code>false</code> if they were all already associated
	 */
	public static boolean addUsers(long pk, long[] userPKs) {
		return getPersistence().addUsers(pk, userPKs);
	}

	/**
	 * Adds an association between the user group and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param users the users
	 * @return <code>true</code> if at least one association between the user group and the users was added; <code>false</code> if they were all already associated
	 */
	public static boolean addUsers(
		long pk, List<com.liferay.portal.kernel.model.User> users) {

		return getPersistence().addUsers(pk, users);
	}

	/**
	 * Clears all associations between the user group and its users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group to clear the associated users from
	 */
	public static void clearUsers(long pk) {
		getPersistence().clearUsers(pk);
	}

	/**
	 * Removes the association between the user group and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param userPK the primary key of the user
	 */
	public static void removeUser(long pk, long userPK) {
		getPersistence().removeUser(pk, userPK);
	}

	/**
	 * Removes the association between the user group and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param user the user
	 */
	public static void removeUser(
		long pk, com.liferay.portal.kernel.model.User user) {

		getPersistence().removeUser(pk, user);
	}

	/**
	 * Removes the association between the user group and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param userPKs the primary keys of the users
	 */
	public static void removeUsers(long pk, long[] userPKs) {
		getPersistence().removeUsers(pk, userPKs);
	}

	/**
	 * Removes the association between the user group and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param users the users
	 */
	public static void removeUsers(
		long pk, List<com.liferay.portal.kernel.model.User> users) {

		getPersistence().removeUsers(pk, users);
	}

	/**
	 * Sets the users associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param userPKs the primary keys of the users to be associated with the user group
	 */
	public static void setUsers(long pk, long[] userPKs) {
		getPersistence().setUsers(pk, userPKs);
	}

	/**
	 * Sets the users associated with the user group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the user group
	 * @param users the users to be associated with the user group
	 */
	public static void setUsers(
		long pk, List<com.liferay.portal.kernel.model.User> users) {

		getPersistence().setUsers(pk, users);
	}

	public static UserGroupPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(UserGroupPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile UserGroupPersistence _persistence;

}