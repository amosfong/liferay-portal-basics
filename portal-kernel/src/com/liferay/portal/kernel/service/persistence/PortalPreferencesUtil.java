/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.PortalPreferences;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the portal preferences service. This utility wraps <code>com.liferay.portal.service.persistence.impl.PortalPreferencesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferencesPersistence
 * @generated
 */
public class PortalPreferencesUtil {

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
	public static void clearCache(PortalPreferences portalPreferences) {
		getPersistence().clearCache(portalPreferences);
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
	public static Map<Serializable, PortalPreferences> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PortalPreferences> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PortalPreferences> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PortalPreferences update(
		PortalPreferences portalPreferences) {

		return getPersistence().update(portalPreferences);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PortalPreferences update(
		PortalPreferences portalPreferences, ServiceContext serviceContext) {

		return getPersistence().update(portalPreferences, serviceContext);
	}

	/**
	 * Returns all the portal preferenceses where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @return the matching portal preferenceses
	 */
	public static List<PortalPreferences> findByOwnerType(int ownerType) {
		return getPersistence().findByOwnerType(ownerType);
	}

	/**
	 * Returns a range of all the portal preferenceses where ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @return the range of matching portal preferenceses
	 */
	public static List<PortalPreferences> findByOwnerType(
		int ownerType, int start, int end) {

		return getPersistence().findByOwnerType(ownerType, start, end);
	}

	/**
	 * Returns an ordered range of all the portal preferenceses where ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portal preferenceses
	 */
	public static List<PortalPreferences> findByOwnerType(
		int ownerType, int start, int end,
		OrderByComparator<PortalPreferences> orderByComparator) {

		return getPersistence().findByOwnerType(
			ownerType, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the portal preferenceses where ownerType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portal preferenceses
	 */
	public static List<PortalPreferences> findByOwnerType(
		int ownerType, int start, int end,
		OrderByComparator<PortalPreferences> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOwnerType(
			ownerType, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first portal preferences in the ordered set where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal preferences
	 * @throws NoSuchPreferencesException if a matching portal preferences could not be found
	 */
	public static PortalPreferences findByOwnerType_First(
			int ownerType,
			OrderByComparator<PortalPreferences> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().findByOwnerType_First(
			ownerType, orderByComparator);
	}

	/**
	 * Returns the first portal preferences in the ordered set where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	 */
	public static PortalPreferences fetchByOwnerType_First(
		int ownerType, OrderByComparator<PortalPreferences> orderByComparator) {

		return getPersistence().fetchByOwnerType_First(
			ownerType, orderByComparator);
	}

	/**
	 * Returns the last portal preferences in the ordered set where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal preferences
	 * @throws NoSuchPreferencesException if a matching portal preferences could not be found
	 */
	public static PortalPreferences findByOwnerType_Last(
			int ownerType,
			OrderByComparator<PortalPreferences> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().findByOwnerType_Last(
			ownerType, orderByComparator);
	}

	/**
	 * Returns the last portal preferences in the ordered set where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	 */
	public static PortalPreferences fetchByOwnerType_Last(
		int ownerType, OrderByComparator<PortalPreferences> orderByComparator) {

		return getPersistence().fetchByOwnerType_Last(
			ownerType, orderByComparator);
	}

	/**
	 * Returns the portal preferenceses before and after the current portal preferences in the ordered set where ownerType = &#63;.
	 *
	 * @param portalPreferencesId the primary key of the current portal preferences
	 * @param ownerType the owner type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portal preferences
	 * @throws NoSuchPreferencesException if a portal preferences with the primary key could not be found
	 */
	public static PortalPreferences[] findByOwnerType_PrevAndNext(
			long portalPreferencesId, int ownerType,
			OrderByComparator<PortalPreferences> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().findByOwnerType_PrevAndNext(
			portalPreferencesId, ownerType, orderByComparator);
	}

	/**
	 * Removes all the portal preferenceses where ownerType = &#63; from the database.
	 *
	 * @param ownerType the owner type
	 */
	public static void removeByOwnerType(int ownerType) {
		getPersistence().removeByOwnerType(ownerType);
	}

	/**
	 * Returns the number of portal preferenceses where ownerType = &#63;.
	 *
	 * @param ownerType the owner type
	 * @return the number of matching portal preferenceses
	 */
	public static int countByOwnerType(int ownerType) {
		return getPersistence().countByOwnerType(ownerType);
	}

	/**
	 * Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or throws a <code>NoSuchPreferencesException</code> if it could not be found.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @return the matching portal preferences
	 * @throws NoSuchPreferencesException if a matching portal preferences could not be found
	 */
	public static PortalPreferences findByO_O(long ownerId, int ownerType)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().findByO_O(ownerId, ownerType);
	}

	/**
	 * Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @return the matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	 */
	public static PortalPreferences fetchByO_O(long ownerId, int ownerType) {
		return getPersistence().fetchByO_O(ownerId, ownerType);
	}

	/**
	 * Returns the portal preferences where ownerId = &#63; and ownerType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching portal preferences, or <code>null</code> if a matching portal preferences could not be found
	 */
	public static PortalPreferences fetchByO_O(
		long ownerId, int ownerType, boolean useFinderCache) {

		return getPersistence().fetchByO_O(ownerId, ownerType, useFinderCache);
	}

	/**
	 * Removes the portal preferences where ownerId = &#63; and ownerType = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @return the portal preferences that was removed
	 */
	public static PortalPreferences removeByO_O(long ownerId, int ownerType)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().removeByO_O(ownerId, ownerType);
	}

	/**
	 * Returns the number of portal preferenceses where ownerId = &#63; and ownerType = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @return the number of matching portal preferenceses
	 */
	public static int countByO_O(long ownerId, int ownerType) {
		return getPersistence().countByO_O(ownerId, ownerType);
	}

	/**
	 * Caches the portal preferences in the entity cache if it is enabled.
	 *
	 * @param portalPreferences the portal preferences
	 */
	public static void cacheResult(PortalPreferences portalPreferences) {
		getPersistence().cacheResult(portalPreferences);
	}

	/**
	 * Caches the portal preferenceses in the entity cache if it is enabled.
	 *
	 * @param portalPreferenceses the portal preferenceses
	 */
	public static void cacheResult(
		List<PortalPreferences> portalPreferenceses) {

		getPersistence().cacheResult(portalPreferenceses);
	}

	/**
	 * Creates a new portal preferences with the primary key. Does not add the portal preferences to the database.
	 *
	 * @param portalPreferencesId the primary key for the new portal preferences
	 * @return the new portal preferences
	 */
	public static PortalPreferences create(long portalPreferencesId) {
		return getPersistence().create(portalPreferencesId);
	}

	/**
	 * Removes the portal preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param portalPreferencesId the primary key of the portal preferences
	 * @return the portal preferences that was removed
	 * @throws NoSuchPreferencesException if a portal preferences with the primary key could not be found
	 */
	public static PortalPreferences remove(long portalPreferencesId)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().remove(portalPreferencesId);
	}

	public static PortalPreferences updateImpl(
		PortalPreferences portalPreferences) {

		return getPersistence().updateImpl(portalPreferences);
	}

	/**
	 * Returns the portal preferences with the primary key or throws a <code>NoSuchPreferencesException</code> if it could not be found.
	 *
	 * @param portalPreferencesId the primary key of the portal preferences
	 * @return the portal preferences
	 * @throws NoSuchPreferencesException if a portal preferences with the primary key could not be found
	 */
	public static PortalPreferences findByPrimaryKey(long portalPreferencesId)
		throws com.liferay.portal.kernel.exception.NoSuchPreferencesException {

		return getPersistence().findByPrimaryKey(portalPreferencesId);
	}

	/**
	 * Returns the portal preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param portalPreferencesId the primary key of the portal preferences
	 * @return the portal preferences, or <code>null</code> if a portal preferences with the primary key could not be found
	 */
	public static PortalPreferences fetchByPrimaryKey(
		long portalPreferencesId) {

		return getPersistence().fetchByPrimaryKey(portalPreferencesId);
	}

	/**
	 * Returns all the portal preferenceses.
	 *
	 * @return the portal preferenceses
	 */
	public static List<PortalPreferences> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the portal preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @return the range of portal preferenceses
	 */
	public static List<PortalPreferences> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the portal preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of portal preferenceses
	 */
	public static List<PortalPreferences> findAll(
		int start, int end,
		OrderByComparator<PortalPreferences> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the portal preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortalPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portal preferenceses
	 * @param end the upper bound of the range of portal preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of portal preferenceses
	 */
	public static List<PortalPreferences> findAll(
		int start, int end,
		OrderByComparator<PortalPreferences> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the portal preferenceses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of portal preferenceses.
	 *
	 * @return the number of portal preferenceses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PortalPreferencesPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		PortalPreferencesPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile PortalPreferencesPersistence _persistence;

}