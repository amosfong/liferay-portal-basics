/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saved.content.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.saved.content.model.SavedContentEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the saved content entry service. This utility wraps <code>com.liferay.saved.content.service.persistence.impl.SavedContentEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SavedContentEntryPersistence
 * @generated
 */
public class SavedContentEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SavedContentEntry savedContentEntry) {
		getPersistence().clearCache(savedContentEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, SavedContentEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SavedContentEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SavedContentEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SavedContentEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SavedContentEntry update(
		SavedContentEntry savedContentEntry) {

		return getPersistence().update(savedContentEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SavedContentEntry update(
		SavedContentEntry savedContentEntry, ServiceContext serviceContext) {

		return getPersistence().update(savedContentEntry, serviceContext);
	}

	/**
	 * Returns all the saved content entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the saved content entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUuid_First(
			String uuid, OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUuid_First(
		String uuid, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUuid_Last(
			String uuid, OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUuid_Last(
		String uuid, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where uuid = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByUuid_PrevAndNext(
			long savedContentEntryId, String uuid,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_PrevAndNext(
			savedContentEntryId, uuid, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of saved content entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching saved content entries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the saved content entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSavedContentEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUUID_G(String uuid, long groupId)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the saved content entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the saved content entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the saved content entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the saved content entry that was removed
	 */
	public static SavedContentEntry removeByUUID_G(String uuid, long groupId)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of saved content entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching saved content entries
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the saved content entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the saved content entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByUuid_C_PrevAndNext(
			long savedContentEntryId, String uuid, long companyId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUuid_C_PrevAndNext(
			savedContentEntryId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of saved content entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching saved content entries
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the saved content entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the saved content entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByGroupId_First(
			long groupId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByGroupId_First(
		long groupId, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByGroupId_Last(
			long groupId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByGroupId_Last(
		long groupId, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where groupId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByGroupId_PrevAndNext(
			long savedContentEntryId, long groupId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByGroupId_PrevAndNext(
			savedContentEntryId, groupId, orderByComparator);
	}

	/**
	 * Returns all the saved content entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the saved content entries that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set of saved content entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] filterFindByGroupId_PrevAndNext(
			long savedContentEntryId, long groupId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			savedContentEntryId, groupId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of saved content entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching saved content entries
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of saved content entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching saved content entries that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the saved content entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the saved content entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUserId_First(
			long userId, OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUserId_First(
		long userId, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByUserId_Last(
			long userId, OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByUserId_Last(
		long userId, OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where userId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByUserId_PrevAndNext(
			long savedContentEntryId, long userId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByUserId_PrevAndNext(
			savedContentEntryId, userId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of saved content entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching saved content entries
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the saved content entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_U(long groupId, long userId) {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	 * Returns a range of all the saved content entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_U(
		long groupId, long userId, int start, int end) {

		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_U(
			groupId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_U_First(
			long groupId, long userId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_U_First(
		long groupId, long userId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_U_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_U_Last(
			long groupId, long userId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_U_Last(
		long groupId, long userId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_U_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByG_U_PrevAndNext(
			long savedContentEntryId, long groupId, long userId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_U_PrevAndNext(
			savedContentEntryId, groupId, userId, orderByComparator);
	}

	/**
	 * Returns all the saved content entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_U(
		long groupId, long userId) {

		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	 * Returns a range of all the saved content entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_U(
		long groupId, long userId, int start, int end) {

		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().filterFindByG_U(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set of saved content entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] filterFindByG_U_PrevAndNext(
			long savedContentEntryId, long groupId, long userId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().filterFindByG_U_PrevAndNext(
			savedContentEntryId, groupId, userId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public static void removeByG_U(long groupId, long userId) {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	 * Returns the number of saved content entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching saved content entries
	 */
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	 * Returns the number of saved content entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching saved content entries that the user has permission to view
	 */
	public static int filterCountByG_U(long groupId, long userId) {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	 * Returns all the saved content entries where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_CN(
		long groupId, long classNameId) {

		return getPersistence().findByG_CN(groupId, classNameId);
	}

	/**
	 * Returns a range of all the saved content entries where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_CN(
		long groupId, long classNameId, int start, int end) {

		return getPersistence().findByG_CN(groupId, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_CN(
		long groupId, long classNameId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByG_CN(
			groupId, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_CN(
		long groupId, long classNameId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_CN(
			groupId, classNameId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_CN_First(
			long groupId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_CN_First(
			groupId, classNameId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_CN_First(
		long groupId, long classNameId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_CN_First(
			groupId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_CN_Last(
			long groupId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_CN_Last(
			groupId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_CN_Last(
		long groupId, long classNameId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_CN_Last(
			groupId, classNameId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByG_CN_PrevAndNext(
			long savedContentEntryId, long groupId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_CN_PrevAndNext(
			savedContentEntryId, groupId, classNameId, orderByComparator);
	}

	/**
	 * Returns all the saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_CN(
		long groupId, long classNameId) {

		return getPersistence().filterFindByG_CN(groupId, classNameId);
	}

	/**
	 * Returns a range of all the saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_CN(
		long groupId, long classNameId, int start, int end) {

		return getPersistence().filterFindByG_CN(
			groupId, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries that the user has permissions to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_CN(
		long groupId, long classNameId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().filterFindByG_CN(
			groupId, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set of saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] filterFindByG_CN_PrevAndNext(
			long savedContentEntryId, long groupId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().filterFindByG_CN_PrevAndNext(
			savedContentEntryId, groupId, classNameId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where groupId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 */
	public static void removeByG_CN(long groupId, long classNameId) {
		getPersistence().removeByG_CN(groupId, classNameId);
	}

	/**
	 * Returns the number of saved content entries where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the number of matching saved content entries
	 */
	public static int countByG_CN(long groupId, long classNameId) {
		return getPersistence().countByG_CN(groupId, classNameId);
	}

	/**
	 * Returns the number of saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the number of matching saved content entries that the user has permission to view
	 */
	public static int filterCountByG_CN(long groupId, long classNameId) {
		return getPersistence().filterCountByG_CN(groupId, classNameId);
	}

	/**
	 * Returns all the saved content entries where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByU_C(
		long userId, long classNameId) {

		return getPersistence().findByU_C(userId, classNameId);
	}

	/**
	 * Returns a range of all the saved content entries where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByU_C(
		long userId, long classNameId, int start, int end) {

		return getPersistence().findByU_C(userId, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByU_C(
		long userId, long classNameId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByU_C(
			userId, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByU_C(
		long userId, long classNameId, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_C(
			userId, classNameId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByU_C_First(
			long userId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByU_C_First(
			userId, classNameId, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByU_C_First(
		long userId, long classNameId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByU_C_First(
			userId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByU_C_Last(
			long userId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByU_C_Last(
			userId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByU_C_Last(
		long userId, long classNameId,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByU_C_Last(
			userId, classNameId, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByU_C_PrevAndNext(
			long savedContentEntryId, long userId, long classNameId,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByU_C_PrevAndNext(
			savedContentEntryId, userId, classNameId, orderByComparator);
	}

	/**
	 * Removes all the saved content entries where userId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 */
	public static void removeByU_C(long userId, long classNameId) {
		getPersistence().removeByU_C(userId, classNameId);
	}

	/**
	 * Returns the number of saved content entries where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the number of matching saved content entries
	 */
	public static int countByU_C(long userId, long classNameId) {
		return getPersistence().countByU_C(userId, classNameId);
	}

	/**
	 * Returns all the saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_C_C(
		long groupId, long classNameId, long classPK) {

		return getPersistence().findByG_C_C(groupId, classNameId, classPK);
	}

	/**
	 * Returns a range of all the saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end) {

		return getPersistence().findByG_C_C(
			groupId, classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByG_C_C(
			groupId, classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_C(
			groupId, classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_C_C_First(
			long groupId, long classNameId, long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_C_C_First(
			groupId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_C_C_First(
		long groupId, long classNameId, long classPK,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_C_C_First(
			groupId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_C_C_Last(
			long groupId, long classNameId, long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_C_C_Last(
			groupId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_C_C_Last(
		long groupId, long classNameId, long classPK,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByG_C_C_Last(
			groupId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByG_C_C_PrevAndNext(
			long savedContentEntryId, long groupId, long classNameId,
			long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_C_C_PrevAndNext(
			savedContentEntryId, groupId, classNameId, classPK,
			orderByComparator);
	}

	/**
	 * Returns all the saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_C_C(
		long groupId, long classNameId, long classPK) {

		return getPersistence().filterFindByG_C_C(
			groupId, classNameId, classPK);
	}

	/**
	 * Returns a range of all the saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end) {

		return getPersistence().filterFindByG_C_C(
			groupId, classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries that the user has permissions to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries that the user has permission to view
	 */
	public static List<SavedContentEntry> filterFindByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().filterFindByG_C_C(
			groupId, classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set of saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] filterFindByG_C_C_PrevAndNext(
			long savedContentEntryId, long groupId, long classNameId,
			long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().filterFindByG_C_C_PrevAndNext(
			savedContentEntryId, groupId, classNameId, classPK,
			orderByComparator);
	}

	/**
	 * Removes all the saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByG_C_C(
		long groupId, long classNameId, long classPK) {

		getPersistence().removeByG_C_C(groupId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching saved content entries
	 */
	public static int countByG_C_C(
		long groupId, long classNameId, long classPK) {

		return getPersistence().countByG_C_C(groupId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching saved content entries that the user has permission to view
	 */
	public static int filterCountByG_C_C(
		long groupId, long classNameId, long classPK) {

		return getPersistence().filterCountByG_C_C(
			groupId, classNameId, classPK);
	}

	/**
	 * Returns all the saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_C_C(
		long companyId, long classNameId, long classPK) {

		return getPersistence().findByC_C_C(companyId, classNameId, classPK);
	}

	/**
	 * Returns a range of all the saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first saved content entry in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByC_C_C_First(
			long companyId, long classNameId, long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByC_C_C_First(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first saved content entry in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByC_C_C_First(
		long companyId, long classNameId, long classPK,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByC_C_C_First(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByC_C_C_Last(
			long companyId, long classNameId, long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByC_C_C_Last(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last saved content entry in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByC_C_C_Last(
		long companyId, long classNameId, long classPK,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().fetchByC_C_C_Last(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the saved content entries before and after the current saved content entry in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param savedContentEntryId the primary key of the current saved content entry
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry[] findByC_C_C_PrevAndNext(
			long savedContentEntryId, long companyId, long classNameId,
			long classPK,
			OrderByComparator<SavedContentEntry> orderByComparator)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByC_C_C_PrevAndNext(
			savedContentEntryId, companyId, classNameId, classPK,
			orderByComparator);
	}

	/**
	 * Removes all the saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C_C(
		long companyId, long classNameId, long classPK) {

		getPersistence().removeByC_C_C(companyId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching saved content entries
	 */
	public static int countByC_C_C(
		long companyId, long classNameId, long classPK) {

		return getPersistence().countByC_C_C(companyId, classNameId, classPK);
	}

	/**
	 * Returns the saved content entry where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchSavedContentEntryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByG_U_C_C(
			long groupId, long userId, long classNameId, long classPK)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByG_U_C_C(
			groupId, userId, classNameId, classPK);
	}

	/**
	 * Returns the saved content entry where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_U_C_C(
		long groupId, long userId, long classNameId, long classPK) {

		return getPersistence().fetchByG_U_C_C(
			groupId, userId, classNameId, classPK);
	}

	/**
	 * Returns the saved content entry where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByG_U_C_C(
		long groupId, long userId, long classNameId, long classPK,
		boolean useFinderCache) {

		return getPersistence().fetchByG_U_C_C(
			groupId, userId, classNameId, classPK, useFinderCache);
	}

	/**
	 * Removes the saved content entry where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the saved content entry that was removed
	 */
	public static SavedContentEntry removeByG_U_C_C(
			long groupId, long userId, long classNameId, long classPK)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().removeByG_U_C_C(
			groupId, userId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching saved content entries
	 */
	public static int countByG_U_C_C(
		long groupId, long userId, long classNameId, long classPK) {

		return getPersistence().countByG_U_C_C(
			groupId, userId, classNameId, classPK);
	}

	/**
	 * Returns all the saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_U_C_C(
		long companyId, long userId, long classNameId, long[] classPKs) {

		return getPersistence().findByC_U_C_C(
			companyId, userId, classNameId, classPKs);
	}

	/**
	 * Returns a range of all the saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_U_C_C(
		long companyId, long userId, long classNameId, long[] classPKs,
		int start, int end) {

		return getPersistence().findByC_U_C_C(
			companyId, userId, classNameId, classPKs, start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_U_C_C(
		long companyId, long userId, long classNameId, long[] classPKs,
		int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findByC_U_C_C(
			companyId, userId, classNameId, classPKs, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saved content entries
	 */
	public static List<SavedContentEntry> findByC_U_C_C(
		long companyId, long userId, long classNameId, long[] classPKs,
		int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_U_C_C(
			companyId, userId, classNameId, classPKs, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the saved content entry where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchSavedContentEntryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entry
	 * @throws NoSuchSavedContentEntryException if a matching saved content entry could not be found
	 */
	public static SavedContentEntry findByC_U_C_C(
			long companyId, long userId, long classNameId, long classPK)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByC_U_C_C(
			companyId, userId, classNameId, classPK);
	}

	/**
	 * Returns the saved content entry where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByC_U_C_C(
		long companyId, long userId, long classNameId, long classPK) {

		return getPersistence().fetchByC_U_C_C(
			companyId, userId, classNameId, classPK);
	}

	/**
	 * Returns the saved content entry where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching saved content entry, or <code>null</code> if a matching saved content entry could not be found
	 */
	public static SavedContentEntry fetchByC_U_C_C(
		long companyId, long userId, long classNameId, long classPK,
		boolean useFinderCache) {

		return getPersistence().fetchByC_U_C_C(
			companyId, userId, classNameId, classPK, useFinderCache);
	}

	/**
	 * Removes the saved content entry where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the saved content entry that was removed
	 */
	public static SavedContentEntry removeByC_U_C_C(
			long companyId, long userId, long classNameId, long classPK)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().removeByC_U_C_C(
			companyId, userId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching saved content entries
	 */
	public static int countByC_U_C_C(
		long companyId, long userId, long classNameId, long classPK) {

		return getPersistence().countByC_U_C_C(
			companyId, userId, classNameId, classPK);
	}

	/**
	 * Returns the number of saved content entries where companyId = &#63; and userId = &#63; and classNameId = &#63; and classPK = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPKs the class pks
	 * @return the number of matching saved content entries
	 */
	public static int countByC_U_C_C(
		long companyId, long userId, long classNameId, long[] classPKs) {

		return getPersistence().countByC_U_C_C(
			companyId, userId, classNameId, classPKs);
	}

	/**
	 * Caches the saved content entry in the entity cache if it is enabled.
	 *
	 * @param savedContentEntry the saved content entry
	 */
	public static void cacheResult(SavedContentEntry savedContentEntry) {
		getPersistence().cacheResult(savedContentEntry);
	}

	/**
	 * Caches the saved content entries in the entity cache if it is enabled.
	 *
	 * @param savedContentEntries the saved content entries
	 */
	public static void cacheResult(
		List<SavedContentEntry> savedContentEntries) {

		getPersistence().cacheResult(savedContentEntries);
	}

	/**
	 * Creates a new saved content entry with the primary key. Does not add the saved content entry to the database.
	 *
	 * @param savedContentEntryId the primary key for the new saved content entry
	 * @return the new saved content entry
	 */
	public static SavedContentEntry create(long savedContentEntryId) {
		return getPersistence().create(savedContentEntryId);
	}

	/**
	 * Removes the saved content entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param savedContentEntryId the primary key of the saved content entry
	 * @return the saved content entry that was removed
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry remove(long savedContentEntryId)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().remove(savedContentEntryId);
	}

	public static SavedContentEntry updateImpl(
		SavedContentEntry savedContentEntry) {

		return getPersistence().updateImpl(savedContentEntry);
	}

	/**
	 * Returns the saved content entry with the primary key or throws a <code>NoSuchSavedContentEntryException</code> if it could not be found.
	 *
	 * @param savedContentEntryId the primary key of the saved content entry
	 * @return the saved content entry
	 * @throws NoSuchSavedContentEntryException if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry findByPrimaryKey(long savedContentEntryId)
		throws com.liferay.saved.content.exception.
			NoSuchSavedContentEntryException {

		return getPersistence().findByPrimaryKey(savedContentEntryId);
	}

	/**
	 * Returns the saved content entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param savedContentEntryId the primary key of the saved content entry
	 * @return the saved content entry, or <code>null</code> if a saved content entry with the primary key could not be found
	 */
	public static SavedContentEntry fetchByPrimaryKey(
		long savedContentEntryId) {

		return getPersistence().fetchByPrimaryKey(savedContentEntryId);
	}

	/**
	 * Returns all the saved content entries.
	 *
	 * @return the saved content entries
	 */
	public static List<SavedContentEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the saved content entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @return the range of saved content entries
	 */
	public static List<SavedContentEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the saved content entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saved content entries
	 */
	public static List<SavedContentEntry> findAll(
		int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the saved content entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SavedContentEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saved content entries
	 * @param end the upper bound of the range of saved content entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of saved content entries
	 */
	public static List<SavedContentEntry> findAll(
		int start, int end,
		OrderByComparator<SavedContentEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the saved content entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of saved content entries.
	 *
	 * @return the number of saved content entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SavedContentEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		SavedContentEntryPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile SavedContentEntryPersistence _persistence;

}