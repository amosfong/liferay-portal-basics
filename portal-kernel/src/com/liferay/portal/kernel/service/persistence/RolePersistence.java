/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.model.Role;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RoleUtil
 * @generated
 */
@ProviderType
public interface RolePersistence extends BasePersistence<Role> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RoleUtil} to access the role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching roles
	 */
	public java.util.List<Role> findByUuid(String uuid);

	/**
	 * Returns a range of all the roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where uuid = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByUuid_PrevAndNext(
			long roleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid(String uuid);

	/**
	 * Returns a range of all the roles that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByUuid_PrevAndNext(
			long roleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching roles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching roles
	 */
	public java.util.List<Role> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByUuid_C_PrevAndNext(
			long roleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByUuid_C_PrevAndNext(
			long roleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching roles
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the roles where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching roles
	 */
	public java.util.List<Role> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the roles where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where companyId = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByCompanyId_PrevAndNext(
			long roleId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByCompanyId(long companyId);

	/**
	 * Returns a range of all the roles that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where companyId = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByCompanyId_PrevAndNext(
			long roleId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of roles where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching roles
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByCompanyId(long companyId);

	/**
	 * Returns all the roles where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching roles
	 */
	public java.util.List<Role> findByName(String name);

	/**
	 * Returns a range of all the roles where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByName(String name, int start, int end);

	/**
	 * Returns an ordered range of all the roles where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByName_First(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByName_First(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByName_Last(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByName_Last(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where name = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByName_PrevAndNext(
			long roleId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByName(String name);

	/**
	 * Returns a range of all the roles that the user has permission to view where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByName(
		String name, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where name = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByName_PrevAndNext(
			long roleId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	public void removeByName(String name);

	/**
	 * Returns the number of roles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching roles
	 */
	public int countByName(String name);

	/**
	 * Returns the number of roles that the user has permission to view where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByName(String name);

	/**
	 * Returns all the roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching roles
	 */
	public java.util.List<Role> findByType(int type);

	/**
	 * Returns a range of all the roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByType(int type, int start, int end);

	/**
	 * Returns an ordered range of all the roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByType_First(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByType_Last(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where type = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByType_PrevAndNext(
			long roleId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByType(int type);

	/**
	 * Returns a range of all the roles that the user has permission to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByType(int type, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where type = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByType_PrevAndNext(
			long roleId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeByType(int type);

	/**
	 * Returns the number of roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching roles
	 */
	public int countByType(int type);

	/**
	 * Returns the number of roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByType(int type);

	/**
	 * Returns all the roles where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @return the matching roles
	 */
	public java.util.List<Role> findBySubtype(String subtype);

	/**
	 * Returns a range of all the roles where subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findBySubtype(
		String subtype, int start, int end);

	/**
	 * Returns an ordered range of all the roles where subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findBySubtype(
		String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findBySubtype(
		String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findBySubtype_First(
			String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchBySubtype_First(
		String subtype,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findBySubtype_Last(
			String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchBySubtype_Last(
		String subtype,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where subtype = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findBySubtype_PrevAndNext(
			long roleId, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindBySubtype(String subtype);

	/**
	 * Returns a range of all the roles that the user has permission to view where subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindBySubtype(
		String subtype, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindBySubtype(
		String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where subtype = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindBySubtype_PrevAndNext(
			long roleId, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where subtype = &#63; from the database.
	 *
	 * @param subtype the subtype
	 */
	public void removeBySubtype(String subtype);

	/**
	 * Returns the number of roles where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @return the number of matching roles
	 */
	public int countBySubtype(String subtype);

	/**
	 * Returns the number of roles that the user has permission to view where subtype = &#63;.
	 *
	 * @param subtype the subtype
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountBySubtype(String subtype);

	/**
	 * Returns the role where companyId = &#63; and name = &#63; or throws a <code>NoSuchRoleException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByC_N(long companyId, String name)
		throws NoSuchRoleException;

	/**
	 * Returns the role where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_N(long companyId, String name);

	/**
	 * Returns the role where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_N(long companyId, String name, boolean useFinderCache);

	/**
	 * Removes the role where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the role that was removed
	 */
	public Role removeByC_N(long companyId, String name)
		throws NoSuchRoleException;

	/**
	 * Returns the number of roles where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching roles
	 */
	public int countByC_N(long companyId, String name);

	/**
	 * Returns all the roles where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching roles
	 */
	public java.util.List<Role> findByC_T(long companyId, int type);

	/**
	 * Returns a range of all the roles where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int type, int start, int end);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByC_T_First(
			long companyId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_T_First(
		long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByC_T_Last(
			long companyId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_T_Last(
		long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByC_T_PrevAndNext(
			long roleId, long companyId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(long companyId, int type);

	/**
	 * Returns a range of all the roles that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(
		long companyId, int type, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(
		long companyId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByC_T_PrevAndNext(
			long roleId, long companyId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where companyId = &#63; and type = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(long companyId, int[] types);

	/**
	 * Returns a range of all the roles that the user has permission to view where companyId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(
		long companyId, int[] types, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permission to view where companyId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByC_T(
		long companyId, int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns all the roles where companyId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @return the matching roles
	 */
	public java.util.List<Role> findByC_T(long companyId, int[] types);

	/**
	 * Returns a range of all the roles where companyId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int[] types, int start, int end);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_T(
		long companyId, int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the roles where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 */
	public void removeByC_T(long companyId, int type);

	/**
	 * Returns the number of roles where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching roles
	 */
	public int countByC_T(long companyId, int type);

	/**
	 * Returns the number of roles where companyId = &#63; and type = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @return the number of matching roles
	 */
	public int countByC_T(long companyId, int[] types);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_T(long companyId, int type);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and type = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param types the types
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_T(long companyId, int[] types);

	/**
	 * Returns all the roles where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @return the matching roles
	 */
	public java.util.List<Role> findByT_S(int type, String subtype);

	/**
	 * Returns a range of all the roles where type = &#63; and subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByT_S(
		int type, String subtype, int start, int end);

	/**
	 * Returns an ordered range of all the roles where type = &#63; and subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByT_S(
		int type, String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where type = &#63; and subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByT_S(
		int type, String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first role in the ordered set where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByT_S_First(
			int type, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the first role in the ordered set where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByT_S_First(
		int type, String subtype,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the last role in the ordered set where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByT_S_Last(
			int type, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns the last role in the ordered set where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByT_S_Last(
		int type, String subtype,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set where type = &#63; and subtype = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] findByT_S_PrevAndNext(
			long roleId, int type, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Returns all the roles that the user has permission to view where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @return the matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByT_S(int type, String subtype);

	/**
	 * Returns a range of all the roles that the user has permission to view where type = &#63; and subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByT_S(
		int type, String subtype, int start, int end);

	/**
	 * Returns an ordered range of all the roles that the user has permissions to view where type = &#63; and subtype = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles that the user has permission to view
	 */
	public java.util.List<Role> filterFindByT_S(
		int type, String subtype, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns the roles before and after the current role in the ordered set of roles that the user has permission to view where type = &#63; and subtype = &#63;.
	 *
	 * @param roleId the primary key of the current role
	 * @param type the type
	 * @param subtype the subtype
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role[] filterFindByT_S_PrevAndNext(
			long roleId, int type, String subtype,
			com.liferay.portal.kernel.util.OrderByComparator<Role>
				orderByComparator)
		throws NoSuchRoleException;

	/**
	 * Removes all the roles where type = &#63; and subtype = &#63; from the database.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 */
	public void removeByT_S(int type, String subtype);

	/**
	 * Returns the number of roles where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @return the number of matching roles
	 */
	public int countByT_S(int type, String subtype);

	/**
	 * Returns the number of roles that the user has permission to view where type = &#63; and subtype = &#63;.
	 *
	 * @param type the type
	 * @param subtype the subtype
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByT_S(int type, String subtype);

	/**
	 * Returns all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the matching roles
	 */
	public java.util.List<Role> findByC_C_C(
		long companyId, long classNameId, long[] classPKs);

	/**
	 * Returns a range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByC_C_C(
		long companyId, long classNameId, long[] classPKs, int start, int end);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_C_C(
		long companyId, long classNameId, long[] classPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_C_C(
		long companyId, long classNameId, long[] classPKs, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchRoleException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByC_C_C(long companyId, long classNameId, long classPK)
		throws NoSuchRoleException;

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_C_C(
		long companyId, long classNameId, long classPK, boolean useFinderCache);

	/**
	 * Removes the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the role that was removed
	 */
	public Role removeByC_C_C(long companyId, long classNameId, long classPK)
		throws NoSuchRoleException;

	/**
	 * Returns the number of roles where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching roles
	 */
	public int countByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns the number of roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the number of matching roles
	 */
	public int countByC_C_C(long companyId, long classNameId, long[] classPKs);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_C_C(
		long companyId, long classNameId, long classPK);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_C_C(
		long companyId, long classNameId, long[] classPKs);

	/**
	 * Returns all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @return the matching roles
	 */
	public java.util.List<Role> findByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type);

	/**
	 * Returns a range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of matching roles
	 */
	public java.util.List<Role> findByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type, int start,
		int end);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching roles
	 */
	public java.util.List<Role> findByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or throws a <code>NoSuchRoleException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByC_C_C_T(
			long companyId, long classNameId, long classPK, int type)
		throws NoSuchRoleException;

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_C_C_T(
		long companyId, long classNameId, long classPK, int type);

	/**
	 * Returns the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByC_C_C_T(
		long companyId, long classNameId, long classPK, int type,
		boolean useFinderCache);

	/**
	 * Removes the role where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the role that was removed
	 */
	public Role removeByC_C_C_T(
			long companyId, long classNameId, long classPK, int type)
		throws NoSuchRoleException;

	/**
	 * Returns the number of roles where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching roles
	 */
	public int countByC_C_C_T(
		long companyId, long classNameId, long classPK, int type);

	/**
	 * Returns the number of roles where companyId = &#63; and classNameId = &#63; and classPK = any &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @return the number of matching roles
	 */
	public int countByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_C_C_T(
		long companyId, long classNameId, long classPK, int type);

	/**
	 * Returns the number of roles that the user has permission to view where companyId = &#63; and classNameId = &#63; and classPK = any &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param type the type
	 * @return the number of matching roles that the user has permission to view
	 */
	public int filterCountByC_C_C_T(
		long companyId, long classNameId, long[] classPKs, int type);

	/**
	 * Returns the role where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchRoleException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching role
	 * @throws NoSuchRoleException if a matching role could not be found
	 */
	public Role findByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchRoleException;

	/**
	 * Returns the role where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Returns the role where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching role, or <code>null</code> if a matching role could not be found
	 */
	public Role fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache);

	/**
	 * Removes the role where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the role that was removed
	 */
	public Role removeByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchRoleException;

	/**
	 * Returns the number of roles where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching roles
	 */
	public int countByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Caches the role in the entity cache if it is enabled.
	 *
	 * @param role the role
	 */
	public void cacheResult(Role role);

	/**
	 * Caches the roles in the entity cache if it is enabled.
	 *
	 * @param roles the roles
	 */
	public void cacheResult(java.util.List<Role> roles);

	/**
	 * Creates a new role with the primary key. Does not add the role to the database.
	 *
	 * @param roleId the primary key for the new role
	 * @return the new role
	 */
	public Role create(long roleId);

	/**
	 * Removes the role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param roleId the primary key of the role
	 * @return the role that was removed
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role remove(long roleId) throws NoSuchRoleException;

	public Role updateImpl(Role role);

	/**
	 * Returns the role with the primary key or throws a <code>NoSuchRoleException</code> if it could not be found.
	 *
	 * @param roleId the primary key of the role
	 * @return the role
	 * @throws NoSuchRoleException if a role with the primary key could not be found
	 */
	public Role findByPrimaryKey(long roleId) throws NoSuchRoleException;

	/**
	 * Returns the role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param roleId the primary key of the role
	 * @return the role, or <code>null</code> if a role with the primary key could not be found
	 */
	public Role fetchByPrimaryKey(long roleId);

	/**
	 * Returns all the roles.
	 *
	 * @return the roles
	 */
	public java.util.List<Role> findAll();

	/**
	 * Returns a range of all the roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of roles
	 */
	public java.util.List<Role> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of roles
	 */
	public java.util.List<Role> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator);

	/**
	 * Returns an ordered range of all the roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of roles
	 */
	public java.util.List<Role> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Role>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of roles.
	 *
	 * @return the number of roles
	 */
	public int countAll();

	/**
	 * Returns the primaryKeys of groups associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return long[] of the primaryKeys of groups associated with the role
	 */
	public long[] getGroupPrimaryKeys(long pk);

	/**
	 * Returns all the groups associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return the groups associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk);

	/**
	 * Returns a range of all the groups associated with the role.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the role
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of groups associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk, int start, int end);

	/**
	 * Returns an ordered range of all the groups associated with the role.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the role
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of groups associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Group> orderByComparator);

	/**
	 * Returns the number of groups associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return the number of groups associated with the role
	 */
	public int getGroupsSize(long pk);

	/**
	 * Returns <code>true</code> if the group is associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @param groupPK the primary key of the group
	 * @return <code>true</code> if the group is associated with the role; <code>false</code> otherwise
	 */
	public boolean containsGroup(long pk, long groupPK);

	/**
	 * Returns <code>true</code> if the role has any groups associated with it.
	 *
	 * @param pk the primary key of the role to check for associations with groups
	 * @return <code>true</code> if the role has any groups associated with it; <code>false</code> otherwise
	 */
	public boolean containsGroups(long pk);

	/**
	 * Adds an association between the role and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groupPK the primary key of the group
	 * @return <code>true</code> if an association between the role and the group was added; <code>false</code> if they were already associated
	 */
	public boolean addGroup(long pk, long groupPK);

	/**
	 * Adds an association between the role and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param group the group
	 * @return <code>true</code> if an association between the role and the group was added; <code>false</code> if they were already associated
	 */
	public boolean addGroup(
		long pk, com.liferay.portal.kernel.model.Group group);

	/**
	 * Adds an association between the role and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groupPKs the primary keys of the groups
	 * @return <code>true</code> if at least one association between the role and the groups was added; <code>false</code> if they were all already associated
	 */
	public boolean addGroups(long pk, long[] groupPKs);

	/**
	 * Adds an association between the role and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groups the groups
	 * @return <code>true</code> if at least one association between the role and the groups was added; <code>false</code> if they were all already associated
	 */
	public boolean addGroups(
		long pk, java.util.List<com.liferay.portal.kernel.model.Group> groups);

	/**
	 * Clears all associations between the role and its groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role to clear the associated groups from
	 */
	public void clearGroups(long pk);

	/**
	 * Removes the association between the role and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groupPK the primary key of the group
	 */
	public void removeGroup(long pk, long groupPK);

	/**
	 * Removes the association between the role and the group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param group the group
	 */
	public void removeGroup(
		long pk, com.liferay.portal.kernel.model.Group group);

	/**
	 * Removes the association between the role and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groupPKs the primary keys of the groups
	 */
	public void removeGroups(long pk, long[] groupPKs);

	/**
	 * Removes the association between the role and the groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groups the groups
	 */
	public void removeGroups(
		long pk, java.util.List<com.liferay.portal.kernel.model.Group> groups);

	/**
	 * Sets the groups associated with the role, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groupPKs the primary keys of the groups to be associated with the role
	 */
	public void setGroups(long pk, long[] groupPKs);

	/**
	 * Sets the groups associated with the role, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param groups the groups to be associated with the role
	 */
	public void setGroups(
		long pk, java.util.List<com.liferay.portal.kernel.model.Group> groups);

	/**
	 * Returns the primaryKeys of users associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return long[] of the primaryKeys of users associated with the role
	 */
	public long[] getUserPrimaryKeys(long pk);

	/**
	 * Returns all the users associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return the users associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.User> getUsers(
		long pk);

	/**
	 * Returns a range of all the users associated with the role.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the role
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @return the range of users associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.User> getUsers(
		long pk, int start, int end);

	/**
	 * Returns an ordered range of all the users associated with the role.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoleModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the role
	 * @param start the lower bound of the range of roles
	 * @param end the upper bound of the range of roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users associated with the role
	 */
	public java.util.List<com.liferay.portal.kernel.model.User> getUsers(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.User> orderByComparator);

	/**
	 * Returns the number of users associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @return the number of users associated with the role
	 */
	public int getUsersSize(long pk);

	/**
	 * Returns <code>true</code> if the user is associated with the role.
	 *
	 * @param pk the primary key of the role
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if the user is associated with the role; <code>false</code> otherwise
	 */
	public boolean containsUser(long pk, long userPK);

	/**
	 * Returns <code>true</code> if the role has any users associated with it.
	 *
	 * @param pk the primary key of the role to check for associations with users
	 * @return <code>true</code> if the role has any users associated with it; <code>false</code> otherwise
	 */
	public boolean containsUsers(long pk);

	/**
	 * Adds an association between the role and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if an association between the role and the user was added; <code>false</code> if they were already associated
	 */
	public boolean addUser(long pk, long userPK);

	/**
	 * Adds an association between the role and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param user the user
	 * @return <code>true</code> if an association between the role and the user was added; <code>false</code> if they were already associated
	 */
	public boolean addUser(long pk, com.liferay.portal.kernel.model.User user);

	/**
	 * Adds an association between the role and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param userPKs the primary keys of the users
	 * @return <code>true</code> if at least one association between the role and the users was added; <code>false</code> if they were all already associated
	 */
	public boolean addUsers(long pk, long[] userPKs);

	/**
	 * Adds an association between the role and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param users the users
	 * @return <code>true</code> if at least one association between the role and the users was added; <code>false</code> if they were all already associated
	 */
	public boolean addUsers(
		long pk, java.util.List<com.liferay.portal.kernel.model.User> users);

	/**
	 * Clears all associations between the role and its users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role to clear the associated users from
	 */
	public void clearUsers(long pk);

	/**
	 * Removes the association between the role and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param userPK the primary key of the user
	 */
	public void removeUser(long pk, long userPK);

	/**
	 * Removes the association between the role and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param user the user
	 */
	public void removeUser(long pk, com.liferay.portal.kernel.model.User user);

	/**
	 * Removes the association between the role and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param userPKs the primary keys of the users
	 */
	public void removeUsers(long pk, long[] userPKs);

	/**
	 * Removes the association between the role and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param users the users
	 */
	public void removeUsers(
		long pk, java.util.List<com.liferay.portal.kernel.model.User> users);

	/**
	 * Sets the users associated with the role, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param userPKs the primary keys of the users to be associated with the role
	 */
	public void setUsers(long pk, long[] userPKs);

	/**
	 * Sets the users associated with the role, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the role
	 * @param users the users to be associated with the role
	 */
	public void setUsers(
		long pk, java.util.List<com.liferay.portal.kernel.model.User> users);

}