/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.PasswordPolicyRel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the password policy rel service. This utility wraps <code>com.liferay.portal.service.persistence.impl.PasswordPolicyRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRelPersistence
 * @generated
 */
public class PasswordPolicyRelUtil {

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
	public static void clearCache(PasswordPolicyRel passwordPolicyRel) {
		getPersistence().clearCache(passwordPolicyRel);
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
	public static Map<Serializable, PasswordPolicyRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PasswordPolicyRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PasswordPolicyRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PasswordPolicyRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PasswordPolicyRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PasswordPolicyRel update(
		PasswordPolicyRel passwordPolicyRel) {

		return getPersistence().update(passwordPolicyRel);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PasswordPolicyRel update(
		PasswordPolicyRel passwordPolicyRel, ServiceContext serviceContext) {

		return getPersistence().update(passwordPolicyRel, serviceContext);
	}

	/**
	 * Returns all the password policy rels where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @return the matching password policy rels
	 */
	public static List<PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId) {

		return getPersistence().findByPasswordPolicyId(passwordPolicyId);
	}

	/**
	 * Returns a range of all the password policy rels where passwordPolicyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @return the range of matching password policy rels
	 */
	public static List<PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId, int start, int end) {

		return getPersistence().findByPasswordPolicyId(
			passwordPolicyId, start, end);
	}

	/**
	 * Returns an ordered range of all the password policy rels where passwordPolicyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching password policy rels
	 */
	public static List<PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId, int start, int end,
		OrderByComparator<PasswordPolicyRel> orderByComparator) {

		return getPersistence().findByPasswordPolicyId(
			passwordPolicyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the password policy rels where passwordPolicyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching password policy rels
	 */
	public static List<PasswordPolicyRel> findByPasswordPolicyId(
		long passwordPolicyId, int start, int end,
		OrderByComparator<PasswordPolicyRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPasswordPolicyId(
			passwordPolicyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first password policy rel in the ordered set where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching password policy rel
	 * @throws NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel findByPasswordPolicyId_First(
			long passwordPolicyId,
			OrderByComparator<PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().findByPasswordPolicyId_First(
			passwordPolicyId, orderByComparator);
	}

	/**
	 * Returns the first password policy rel in the ordered set where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel fetchByPasswordPolicyId_First(
		long passwordPolicyId,
		OrderByComparator<PasswordPolicyRel> orderByComparator) {

		return getPersistence().fetchByPasswordPolicyId_First(
			passwordPolicyId, orderByComparator);
	}

	/**
	 * Returns the last password policy rel in the ordered set where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching password policy rel
	 * @throws NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel findByPasswordPolicyId_Last(
			long passwordPolicyId,
			OrderByComparator<PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().findByPasswordPolicyId_Last(
			passwordPolicyId, orderByComparator);
	}

	/**
	 * Returns the last password policy rel in the ordered set where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel fetchByPasswordPolicyId_Last(
		long passwordPolicyId,
		OrderByComparator<PasswordPolicyRel> orderByComparator) {

		return getPersistence().fetchByPasswordPolicyId_Last(
			passwordPolicyId, orderByComparator);
	}

	/**
	 * Returns the password policy rels before and after the current password policy rel in the ordered set where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyRelId the primary key of the current password policy rel
	 * @param passwordPolicyId the password policy ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next password policy rel
	 * @throws NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	 */
	public static PasswordPolicyRel[] findByPasswordPolicyId_PrevAndNext(
			long passwordPolicyRelId, long passwordPolicyId,
			OrderByComparator<PasswordPolicyRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().findByPasswordPolicyId_PrevAndNext(
			passwordPolicyRelId, passwordPolicyId, orderByComparator);
	}

	/**
	 * Removes all the password policy rels where passwordPolicyId = &#63; from the database.
	 *
	 * @param passwordPolicyId the password policy ID
	 */
	public static void removeByPasswordPolicyId(long passwordPolicyId) {
		getPersistence().removeByPasswordPolicyId(passwordPolicyId);
	}

	/**
	 * Returns the number of password policy rels where passwordPolicyId = &#63;.
	 *
	 * @param passwordPolicyId the password policy ID
	 * @return the number of matching password policy rels
	 */
	public static int countByPasswordPolicyId(long passwordPolicyId) {
		return getPersistence().countByPasswordPolicyId(passwordPolicyId);
	}

	/**
	 * Returns the password policy rel where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchPasswordPolicyRelException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching password policy rel
	 * @throws NoSuchPasswordPolicyRelException if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel findByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns the password policy rel where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel fetchByC_C(long classNameId, long classPK) {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	 * Returns the password policy rel where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching password policy rel, or <code>null</code> if a matching password policy rel could not be found
	 */
	public static PasswordPolicyRel fetchByC_C(
		long classNameId, long classPK, boolean useFinderCache) {

		return getPersistence().fetchByC_C(
			classNameId, classPK, useFinderCache);
	}

	/**
	 * Removes the password policy rel where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the password policy rel that was removed
	 */
	public static PasswordPolicyRel removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of password policy rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching password policy rels
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Caches the password policy rel in the entity cache if it is enabled.
	 *
	 * @param passwordPolicyRel the password policy rel
	 */
	public static void cacheResult(PasswordPolicyRel passwordPolicyRel) {
		getPersistence().cacheResult(passwordPolicyRel);
	}

	/**
	 * Caches the password policy rels in the entity cache if it is enabled.
	 *
	 * @param passwordPolicyRels the password policy rels
	 */
	public static void cacheResult(List<PasswordPolicyRel> passwordPolicyRels) {
		getPersistence().cacheResult(passwordPolicyRels);
	}

	/**
	 * Creates a new password policy rel with the primary key. Does not add the password policy rel to the database.
	 *
	 * @param passwordPolicyRelId the primary key for the new password policy rel
	 * @return the new password policy rel
	 */
	public static PasswordPolicyRel create(long passwordPolicyRelId) {
		return getPersistence().create(passwordPolicyRelId);
	}

	/**
	 * Removes the password policy rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param passwordPolicyRelId the primary key of the password policy rel
	 * @return the password policy rel that was removed
	 * @throws NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	 */
	public static PasswordPolicyRel remove(long passwordPolicyRelId)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().remove(passwordPolicyRelId);
	}

	public static PasswordPolicyRel updateImpl(
		PasswordPolicyRel passwordPolicyRel) {

		return getPersistence().updateImpl(passwordPolicyRel);
	}

	/**
	 * Returns the password policy rel with the primary key or throws a <code>NoSuchPasswordPolicyRelException</code> if it could not be found.
	 *
	 * @param passwordPolicyRelId the primary key of the password policy rel
	 * @return the password policy rel
	 * @throws NoSuchPasswordPolicyRelException if a password policy rel with the primary key could not be found
	 */
	public static PasswordPolicyRel findByPrimaryKey(long passwordPolicyRelId)
		throws com.liferay.portal.kernel.exception.
			NoSuchPasswordPolicyRelException {

		return getPersistence().findByPrimaryKey(passwordPolicyRelId);
	}

	/**
	 * Returns the password policy rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param passwordPolicyRelId the primary key of the password policy rel
	 * @return the password policy rel, or <code>null</code> if a password policy rel with the primary key could not be found
	 */
	public static PasswordPolicyRel fetchByPrimaryKey(
		long passwordPolicyRelId) {

		return getPersistence().fetchByPrimaryKey(passwordPolicyRelId);
	}

	/**
	 * Returns all the password policy rels.
	 *
	 * @return the password policy rels
	 */
	public static List<PasswordPolicyRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the password policy rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @return the range of password policy rels
	 */
	public static List<PasswordPolicyRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the password policy rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of password policy rels
	 */
	public static List<PasswordPolicyRel> findAll(
		int start, int end,
		OrderByComparator<PasswordPolicyRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the password policy rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PasswordPolicyRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of password policy rels
	 */
	public static List<PasswordPolicyRel> findAll(
		int start, int end,
		OrderByComparator<PasswordPolicyRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the password policy rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of password policy rels.
	 *
	 * @return the number of password policy rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PasswordPolicyRelPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		PasswordPolicyRelPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile PasswordPolicyRelPersistence _persistence;

}