/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.service.persistence;

import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the expando value service. This utility wraps <code>com.liferay.portlet.expando.service.persistence.impl.ExpandoValuePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoValuePersistence
 * @generated
 */
public class ExpandoValueUtil {

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
	public static void clearCache(ExpandoValue expandoValue) {
		getPersistence().clearCache(expandoValue);
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
	public static Map<Serializable, ExpandoValue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExpandoValue update(ExpandoValue expandoValue) {
		return getPersistence().update(expandoValue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExpandoValue update(
		ExpandoValue expandoValue, ServiceContext serviceContext) {

		return getPersistence().update(expandoValue, serviceContext);
	}

	/**
	 * Returns all the expando values where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByTableId(long tableId) {
		return getPersistence().findByTableId(tableId);
	}

	/**
	 * Returns a range of all the expando values where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByTableId(
		long tableId, int start, int end) {

		return getPersistence().findByTableId(tableId, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByTableId(
		long tableId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByTableId(
			tableId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByTableId(
		long tableId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTableId(
			tableId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByTableId_First(
			long tableId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByTableId_First(tableId, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByTableId_First(
		long tableId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByTableId_First(
			tableId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByTableId_Last(
			long tableId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByTableId_Last(tableId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByTableId_Last(
		long tableId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByTableId_Last(tableId, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where tableId = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByTableId_PrevAndNext(
			long valueId, long tableId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByTableId_PrevAndNext(
			valueId, tableId, orderByComparator);
	}

	/**
	 * Removes all the expando values where tableId = &#63; from the database.
	 *
	 * @param tableId the table ID
	 */
	public static void removeByTableId(long tableId) {
		getPersistence().removeByTableId(tableId);
	}

	/**
	 * Returns the number of expando values where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the number of matching expando values
	 */
	public static int countByTableId(long tableId) {
		return getPersistence().countByTableId(tableId);
	}

	/**
	 * Returns all the expando values where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByColumnId(long columnId) {
		return getPersistence().findByColumnId(columnId);
	}

	/**
	 * Returns a range of all the expando values where columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByColumnId(
		long columnId, int start, int end) {

		return getPersistence().findByColumnId(columnId, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByColumnId(
		long columnId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByColumnId(
			columnId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByColumnId(
		long columnId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByColumnId(
			columnId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByColumnId_First(
			long columnId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByColumnId_First(
			columnId, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByColumnId_First(
		long columnId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByColumnId_First(
			columnId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByColumnId_Last(
			long columnId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByColumnId_Last(
			columnId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByColumnId_Last(
		long columnId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByColumnId_Last(
			columnId, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where columnId = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByColumnId_PrevAndNext(
			long valueId, long columnId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByColumnId_PrevAndNext(
			valueId, columnId, orderByComparator);
	}

	/**
	 * Removes all the expando values where columnId = &#63; from the database.
	 *
	 * @param columnId the column ID
	 */
	public static void removeByColumnId(long columnId) {
		getPersistence().removeByColumnId(columnId);
	}

	/**
	 * Returns the number of expando values where columnId = &#63;.
	 *
	 * @param columnId the column ID
	 * @return the number of matching expando values
	 */
	public static int countByColumnId(long columnId) {
		return getPersistence().countByColumnId(columnId);
	}

	/**
	 * Returns all the expando values where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByRowId(long rowId) {
		return getPersistence().findByRowId(rowId);
	}

	/**
	 * Returns a range of all the expando values where rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByRowId(
		long rowId, int start, int end) {

		return getPersistence().findByRowId(rowId, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByRowId(
		long rowId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByRowId(
			rowId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByRowId(
		long rowId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByRowId(
			rowId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByRowId_First(
			long rowId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByRowId_First(rowId, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByRowId_First(
		long rowId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByRowId_First(rowId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByRowId_Last(
			long rowId, OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByRowId_Last(rowId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByRowId_Last(
		long rowId, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByRowId_Last(rowId, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where rowId = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByRowId_PrevAndNext(
			long valueId, long rowId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByRowId_PrevAndNext(
			valueId, rowId, orderByComparator);
	}

	/**
	 * Removes all the expando values where rowId = &#63; from the database.
	 *
	 * @param rowId the row ID
	 */
	public static void removeByRowId(long rowId) {
		getPersistence().removeByRowId(rowId);
	}

	/**
	 * Returns the number of expando values where rowId = &#63;.
	 *
	 * @param rowId the row ID
	 * @return the number of matching expando values
	 */
	public static int countByRowId(long rowId) {
		return getPersistence().countByRowId(rowId);
	}

	/**
	 * Returns all the expando values where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByT_C(long tableId, long columnId) {
		return getPersistence().findByT_C(tableId, columnId);
	}

	/**
	 * Returns a range of all the expando values where tableId = &#63; and columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C(
		long tableId, long columnId, int start, int end) {

		return getPersistence().findByT_C(tableId, columnId, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C(
		long tableId, long columnId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByT_C(
			tableId, columnId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and columnId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C(
		long tableId, long columnId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByT_C(
			tableId, columnId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_C_First(
			long tableId, long columnId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_First(
			tableId, columnId, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_First(
		long tableId, long columnId,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_C_First(
			tableId, columnId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_C_Last(
			long tableId, long columnId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_Last(
			tableId, columnId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_Last(
		long tableId, long columnId,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_C_Last(
			tableId, columnId, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where tableId = &#63; and columnId = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByT_C_PrevAndNext(
			long valueId, long tableId, long columnId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_PrevAndNext(
			valueId, tableId, columnId, orderByComparator);
	}

	/**
	 * Removes all the expando values where tableId = &#63; and columnId = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 */
	public static void removeByT_C(long tableId, long columnId) {
		getPersistence().removeByT_C(tableId, columnId);
	}

	/**
	 * Returns the number of expando values where tableId = &#63; and columnId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @return the number of matching expando values
	 */
	public static int countByT_C(long tableId, long columnId) {
		return getPersistence().countByT_C(tableId, columnId);
	}

	/**
	 * Returns all the expando values where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByT_R(long tableId, long rowId) {
		return getPersistence().findByT_R(tableId, rowId);
	}

	/**
	 * Returns a range of all the expando values where tableId = &#63; and rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByT_R(
		long tableId, long rowId, int start, int end) {

		return getPersistence().findByT_R(tableId, rowId, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_R(
		long tableId, long rowId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByT_R(
			tableId, rowId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and rowId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_R(
		long tableId, long rowId, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByT_R(
			tableId, rowId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_R_First(
			long tableId, long rowId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_R_First(
			tableId, rowId, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_R_First(
		long tableId, long rowId,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_R_First(
			tableId, rowId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_R_Last(
			long tableId, long rowId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_R_Last(
			tableId, rowId, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_R_Last(
		long tableId, long rowId,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_R_Last(
			tableId, rowId, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where tableId = &#63; and rowId = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByT_R_PrevAndNext(
			long valueId, long tableId, long rowId,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_R_PrevAndNext(
			valueId, tableId, rowId, orderByComparator);
	}

	/**
	 * Removes all the expando values where tableId = &#63; and rowId = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 */
	public static void removeByT_R(long tableId, long rowId) {
		getPersistence().removeByT_R(tableId, rowId);
	}

	/**
	 * Returns the number of expando values where tableId = &#63; and rowId = &#63;.
	 *
	 * @param tableId the table ID
	 * @param rowId the row ID
	 * @return the number of matching expando values
	 */
	public static int countByT_R(long tableId, long rowId) {
		return getPersistence().countByT_R(tableId, rowId);
	}

	/**
	 * Returns all the expando values where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByT_CPK(long tableId, long classPK) {
		return getPersistence().findByT_CPK(tableId, classPK);
	}

	/**
	 * Returns a range of all the expando values where tableId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByT_CPK(
		long tableId, long classPK, int start, int end) {

		return getPersistence().findByT_CPK(tableId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_CPK(
		long tableId, long classPK, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByT_CPK(
			tableId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_CPK(
		long tableId, long classPK, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByT_CPK(
			tableId, classPK, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_CPK_First(
			long tableId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_CPK_First(
			tableId, classPK, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_CPK_First(
		long tableId, long classPK,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_CPK_First(
			tableId, classPK, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_CPK_Last(
			long tableId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_CPK_Last(
			tableId, classPK, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_CPK_Last(
		long tableId, long classPK,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_CPK_Last(
			tableId, classPK, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where tableId = &#63; and classPK = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByT_CPK_PrevAndNext(
			long valueId, long tableId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_CPK_PrevAndNext(
			valueId, tableId, classPK, orderByComparator);
	}

	/**
	 * Removes all the expando values where tableId = &#63; and classPK = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 */
	public static void removeByT_CPK(long tableId, long classPK) {
		getPersistence().removeByT_CPK(tableId, classPK);
	}

	/**
	 * Returns the number of expando values where tableId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param classPK the class pk
	 * @return the number of matching expando values
	 */
	public static int countByT_CPK(long tableId, long classPK) {
		return getPersistence().countByT_CPK(tableId, classPK);
	}

	/**
	 * Returns the expando value where columnId = &#63; and rowId = &#63; or throws a <code>NoSuchValueException</code> if it could not be found.
	 *
	 * @param columnId the column ID
	 * @param rowId the row ID
	 * @return the matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByC_R(long columnId, long rowId)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByC_R(columnId, rowId);
	}

	/**
	 * Returns the expando value where columnId = &#63; and rowId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param columnId the column ID
	 * @param rowId the row ID
	 * @return the matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByC_R(long columnId, long rowId) {
		return getPersistence().fetchByC_R(columnId, rowId);
	}

	/**
	 * Returns the expando value where columnId = &#63; and rowId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param columnId the column ID
	 * @param rowId the row ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByC_R(
		long columnId, long rowId, boolean useFinderCache) {

		return getPersistence().fetchByC_R(columnId, rowId, useFinderCache);
	}

	/**
	 * Removes the expando value where columnId = &#63; and rowId = &#63; from the database.
	 *
	 * @param columnId the column ID
	 * @param rowId the row ID
	 * @return the expando value that was removed
	 */
	public static ExpandoValue removeByC_R(long columnId, long rowId)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().removeByC_R(columnId, rowId);
	}

	/**
	 * Returns the number of expando values where columnId = &#63; and rowId = &#63;.
	 *
	 * @param columnId the column ID
	 * @param rowId the row ID
	 * @return the number of matching expando values
	 */
	public static int countByC_R(long columnId, long rowId) {
		return getPersistence().countByC_R(columnId, rowId);
	}

	/**
	 * Returns all the expando values where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the expando values where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByC_C_PrevAndNext(
			long valueId, long classNameId, long classPK,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByC_C_PrevAndNext(
			valueId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the expando values where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of expando values where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching expando values
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns the expando value where tableId = &#63; and columnId = &#63; and classPK = &#63; or throws a <code>NoSuchValueException</code> if it could not be found.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param classPK the class pk
	 * @return the matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_C_C(
			long tableId, long columnId, long classPK)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_C(tableId, columnId, classPK);
	}

	/**
	 * Returns the expando value where tableId = &#63; and columnId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param classPK the class pk
	 * @return the matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_C(
		long tableId, long columnId, long classPK) {

		return getPersistence().fetchByT_C_C(tableId, columnId, classPK);
	}

	/**
	 * Returns the expando value where tableId = &#63; and columnId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_C(
		long tableId, long columnId, long classPK, boolean useFinderCache) {

		return getPersistence().fetchByT_C_C(
			tableId, columnId, classPK, useFinderCache);
	}

	/**
	 * Removes the expando value where tableId = &#63; and columnId = &#63; and classPK = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param classPK the class pk
	 * @return the expando value that was removed
	 */
	public static ExpandoValue removeByT_C_C(
			long tableId, long columnId, long classPK)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().removeByT_C_C(tableId, columnId, classPK);
	}

	/**
	 * Returns the number of expando values where tableId = &#63; and columnId = &#63; and classPK = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param classPK the class pk
	 * @return the number of matching expando values
	 */
	public static int countByT_C_C(long tableId, long columnId, long classPK) {
		return getPersistence().countByT_C_C(tableId, columnId, classPK);
	}

	/**
	 * Returns all the expando values where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @return the matching expando values
	 */
	public static List<ExpandoValue> findByT_C_D(
		long tableId, long columnId, String data) {

		return getPersistence().findByT_C_D(tableId, columnId, data);
	}

	/**
	 * Returns a range of all the expando values where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C_D(
		long tableId, long columnId, String data, int start, int end) {

		return getPersistence().findByT_C_D(
			tableId, columnId, data, start, end);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C_D(
		long tableId, long columnId, String data, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findByT_C_D(
			tableId, columnId, data, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching expando values
	 */
	public static List<ExpandoValue> findByT_C_D(
		long tableId, long columnId, String data, int start, int end,
		OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByT_C_D(
			tableId, columnId, data, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_C_D_First(
			long tableId, long columnId, String data,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_D_First(
			tableId, columnId, data, orderByComparator);
	}

	/**
	 * Returns the first expando value in the ordered set where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_D_First(
		long tableId, long columnId, String data,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_C_D_First(
			tableId, columnId, data, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value
	 * @throws NoSuchValueException if a matching expando value could not be found
	 */
	public static ExpandoValue findByT_C_D_Last(
			long tableId, long columnId, String data,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_D_Last(
			tableId, columnId, data, orderByComparator);
	}

	/**
	 * Returns the last expando value in the ordered set where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando value, or <code>null</code> if a matching expando value could not be found
	 */
	public static ExpandoValue fetchByT_C_D_Last(
		long tableId, long columnId, String data,
		OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().fetchByT_C_D_Last(
			tableId, columnId, data, orderByComparator);
	}

	/**
	 * Returns the expando values before and after the current expando value in the ordered set where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param valueId the primary key of the current expando value
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue[] findByT_C_D_PrevAndNext(
			long valueId, long tableId, long columnId, String data,
			OrderByComparator<ExpandoValue> orderByComparator)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByT_C_D_PrevAndNext(
			valueId, tableId, columnId, data, orderByComparator);
	}

	/**
	 * Removes all the expando values where tableId = &#63; and columnId = &#63; and data = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 */
	public static void removeByT_C_D(long tableId, long columnId, String data) {
		getPersistence().removeByT_C_D(tableId, columnId, data);
	}

	/**
	 * Returns the number of expando values where tableId = &#63; and columnId = &#63; and data = &#63;.
	 *
	 * @param tableId the table ID
	 * @param columnId the column ID
	 * @param data the data
	 * @return the number of matching expando values
	 */
	public static int countByT_C_D(long tableId, long columnId, String data) {
		return getPersistence().countByT_C_D(tableId, columnId, data);
	}

	/**
	 * Caches the expando value in the entity cache if it is enabled.
	 *
	 * @param expandoValue the expando value
	 */
	public static void cacheResult(ExpandoValue expandoValue) {
		getPersistence().cacheResult(expandoValue);
	}

	/**
	 * Caches the expando values in the entity cache if it is enabled.
	 *
	 * @param expandoValues the expando values
	 */
	public static void cacheResult(List<ExpandoValue> expandoValues) {
		getPersistence().cacheResult(expandoValues);
	}

	/**
	 * Creates a new expando value with the primary key. Does not add the expando value to the database.
	 *
	 * @param valueId the primary key for the new expando value
	 * @return the new expando value
	 */
	public static ExpandoValue create(long valueId) {
		return getPersistence().create(valueId);
	}

	/**
	 * Removes the expando value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param valueId the primary key of the expando value
	 * @return the expando value that was removed
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue remove(long valueId)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().remove(valueId);
	}

	public static ExpandoValue updateImpl(ExpandoValue expandoValue) {
		return getPersistence().updateImpl(expandoValue);
	}

	/**
	 * Returns the expando value with the primary key or throws a <code>NoSuchValueException</code> if it could not be found.
	 *
	 * @param valueId the primary key of the expando value
	 * @return the expando value
	 * @throws NoSuchValueException if a expando value with the primary key could not be found
	 */
	public static ExpandoValue findByPrimaryKey(long valueId)
		throws com.liferay.expando.kernel.exception.NoSuchValueException {

		return getPersistence().findByPrimaryKey(valueId);
	}

	/**
	 * Returns the expando value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param valueId the primary key of the expando value
	 * @return the expando value, or <code>null</code> if a expando value with the primary key could not be found
	 */
	public static ExpandoValue fetchByPrimaryKey(long valueId) {
		return getPersistence().fetchByPrimaryKey(valueId);
	}

	/**
	 * Returns all the expando values.
	 *
	 * @return the expando values
	 */
	public static List<ExpandoValue> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the expando values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @return the range of expando values
	 */
	public static List<ExpandoValue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the expando values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of expando values
	 */
	public static List<ExpandoValue> findAll(
		int start, int end, OrderByComparator<ExpandoValue> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the expando values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExpandoValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando values
	 * @param end the upper bound of the range of expando values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of expando values
	 */
	public static List<ExpandoValue> findAll(
		int start, int end, OrderByComparator<ExpandoValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the expando values from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of expando values.
	 *
	 * @return the number of expando values
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ExpandoValuePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ExpandoValuePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ExpandoValuePersistence _persistence;

}