/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.exception.NoSuchCPDefinitionInventoryException;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CPDefinitionInventoryTable;
import com.liferay.commerce.model.impl.CPDefinitionInventoryImpl;
import com.liferay.commerce.model.impl.CPDefinitionInventoryModelImpl;
import com.liferay.commerce.service.persistence.CPDefinitionInventoryPersistence;
import com.liferay.commerce.service.persistence.CPDefinitionInventoryUtil;
import com.liferay.commerce.service.persistence.impl.constants.CommercePersistenceConstants;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.change.tracking.CTColumnResolutionType;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.change.tracking.helper.CTPersistenceHelper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the cp definition inventory service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Component(service = CPDefinitionInventoryPersistence.class)
public class CPDefinitionInventoryPersistenceImpl
	extends BasePersistenceImpl<CPDefinitionInventory>
	implements CPDefinitionInventoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPDefinitionInventoryUtil</code> to access the cp definition inventory persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPDefinitionInventoryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the cp definition inventories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition inventories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @return the range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByUuid;
					finderArgs = new Object[] {uuid};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByUuid;
				finderArgs = new Object[] {uuid, start, end, orderByComparator};
			}

			List<CPDefinitionInventory> list = null;

			if (useFinderCache) {
				list = (List<CPDefinitionInventory>)finderCache.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (CPDefinitionInventory cpDefinitionInventory : list) {
						if (!uuid.equals(cpDefinitionInventory.getUuid())) {
							list = null;

							break;
						}
					}
				}
			}

			if (list == null) {
				StringBundler sb = null;

				if (orderByComparator != null) {
					sb = new StringBundler(
						3 + (orderByComparator.getOrderByFields().length * 2));
				}
				else {
					sb = new StringBundler(3);
				}

				sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

				boolean bindUuid = false;

				if (uuid.isEmpty()) {
					sb.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					bindUuid = true;

					sb.append(_FINDER_COLUMN_UUID_UUID_2);
				}

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(CPDefinitionInventoryModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					if (bindUuid) {
						queryPos.add(uuid);
					}

					list = (List<CPDefinitionInventory>)QueryUtil.list(
						query, getDialect(), start, end);

					cacheResult(list);

					if (useFinderCache) {
						finderCache.putResult(finderPath, finderArgs, list);
					}
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return list;
		}
	}

	/**
	 * Returns the first cp definition inventory in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByUuid_First(
			String uuid,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpDefinitionInventory != null) {
			return cpDefinitionInventory;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCPDefinitionInventoryException(sb.toString());
	}

	/**
	 * Returns the first cp definition inventory in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUuid_First(
		String uuid,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		List<CPDefinitionInventory> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition inventory in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByUuid_Last(
			String uuid,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpDefinitionInventory != null) {
			return cpDefinitionInventory;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCPDefinitionInventoryException(sb.toString());
	}

	/**
	 * Returns the last cp definition inventory in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUuid_Last(
		String uuid,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionInventory> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition inventories before and after the current cp definition inventory in the ordered set where uuid = &#63;.
	 *
	 * @param CPDefinitionInventoryId the primary key of the current cp definition inventory
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory[] findByUuid_PrevAndNext(
			long CPDefinitionInventoryId, String uuid,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionInventory cpDefinitionInventory = findByPrimaryKey(
			CPDefinitionInventoryId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionInventory[] array = new CPDefinitionInventoryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpDefinitionInventory, uuid, orderByComparator, true);

			array[1] = cpDefinitionInventory;

			array[2] = getByUuid_PrevAndNext(
				session, cpDefinitionInventory, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionInventory getByUuid_PrevAndNext(
		Session session, CPDefinitionInventory cpDefinitionInventory,
		String uuid, OrderByComparator<CPDefinitionInventory> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CPDefinitionInventoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionInventory)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionInventory> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition inventories where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPDefinitionInventory cpDefinitionInventory :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionInventory);
		}
	}

	/**
	 * Returns the number of cp definition inventories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp definition inventories
	 */
	@Override
	public int countByUuid(String uuid) {
		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = _finderPathCountByUuid;

			Object[] finderArgs = new Object[] {uuid};

			Long count = (Long)finderCache.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(2);

				sb.append(_SQL_COUNT_CPDEFINITIONINVENTORY_WHERE);

				boolean bindUuid = false;

				if (uuid.isEmpty()) {
					sb.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					bindUuid = true;

					sb.append(_FINDER_COLUMN_UUID_UUID_2);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					if (bindUuid) {
						queryPos.add(uuid);
					}

					count = (Long)query.uniqueResult();

					finderCache.putResult(finderPath, finderArgs, count);
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return count.intValue();
		}
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cpDefinitionInventory.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpDefinitionInventory.uuid IS NULL OR cpDefinitionInventory.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the cp definition inventory where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDefinitionInventoryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByUUID_G(
			uuid, groupId);

		if (cpDefinitionInventory == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCPDefinitionInventoryException(sb.toString());
		}

		return cpDefinitionInventory;
	}

	/**
	 * Returns the cp definition inventory where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp definition inventory where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			uuid = Objects.toString(uuid, "");

			Object[] finderArgs = null;

			if (useFinderCache) {
				finderArgs = new Object[] {uuid, groupId};
			}

			Object result = null;

			if (useFinderCache) {
				result = finderCache.getResult(
					_finderPathFetchByUUID_G, finderArgs, this);
			}

			if (result instanceof CPDefinitionInventory) {
				CPDefinitionInventory cpDefinitionInventory =
					(CPDefinitionInventory)result;

				if (!Objects.equals(uuid, cpDefinitionInventory.getUuid()) ||
					(groupId != cpDefinitionInventory.getGroupId())) {

					result = null;
				}
			}

			if (result == null) {
				StringBundler sb = new StringBundler(4);

				sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

				boolean bindUuid = false;

				if (uuid.isEmpty()) {
					sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					bindUuid = true;

					sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}

				sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					if (bindUuid) {
						queryPos.add(uuid);
					}

					queryPos.add(groupId);

					List<CPDefinitionInventory> list = query.list();

					if (list.isEmpty()) {
						if (useFinderCache) {
							finderCache.putResult(
								_finderPathFetchByUUID_G, finderArgs, list);
						}
					}
					else {
						CPDefinitionInventory cpDefinitionInventory = list.get(
							0);

						result = cpDefinitionInventory;

						cacheResult(cpDefinitionInventory);
					}
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (CPDefinitionInventory)result;
			}
		}
	}

	/**
	 * Removes the cp definition inventory where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp definition inventory that was removed
	 */
	@Override
	public CPDefinitionInventory removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = findByUUID_G(
			uuid, groupId);

		return remove(cpDefinitionInventory);
	}

	/**
	 * Returns the number of cp definition inventories where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp definition inventories
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		CPDefinitionInventory cpDefinitionInventory = fetchByUUID_G(
			uuid, groupId);

		if (cpDefinitionInventory == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"cpDefinitionInventory.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpDefinitionInventory.uuid IS NULL OR cpDefinitionInventory.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpDefinitionInventory.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp definition inventories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition inventories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @return the range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByUuid_C;
					finderArgs = new Object[] {uuid, companyId};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByUuid_C;
				finderArgs = new Object[] {
					uuid, companyId, start, end, orderByComparator
				};
			}

			List<CPDefinitionInventory> list = null;

			if (useFinderCache) {
				list = (List<CPDefinitionInventory>)finderCache.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (CPDefinitionInventory cpDefinitionInventory : list) {
						if (!uuid.equals(cpDefinitionInventory.getUuid()) ||
							(companyId !=
								cpDefinitionInventory.getCompanyId())) {

							list = null;

							break;
						}
					}
				}
			}

			if (list == null) {
				StringBundler sb = null;

				if (orderByComparator != null) {
					sb = new StringBundler(
						4 + (orderByComparator.getOrderByFields().length * 2));
				}
				else {
					sb = new StringBundler(4);
				}

				sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

				boolean bindUuid = false;

				if (uuid.isEmpty()) {
					sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					bindUuid = true;

					sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}

				sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(CPDefinitionInventoryModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					if (bindUuid) {
						queryPos.add(uuid);
					}

					queryPos.add(companyId);

					list = (List<CPDefinitionInventory>)QueryUtil.list(
						query, getDialect(), start, end);

					cacheResult(list);

					if (useFinderCache) {
						finderCache.putResult(finderPath, finderArgs, list);
					}
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return list;
		}
	}

	/**
	 * Returns the first cp definition inventory in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpDefinitionInventory != null) {
			return cpDefinitionInventory;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCPDefinitionInventoryException(sb.toString());
	}

	/**
	 * Returns the first cp definition inventory in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		List<CPDefinitionInventory> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition inventory in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpDefinitionInventory != null) {
			return cpDefinitionInventory;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCPDefinitionInventoryException(sb.toString());
	}

	/**
	 * Returns the last cp definition inventory in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionInventory> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition inventories before and after the current cp definition inventory in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDefinitionInventoryId the primary key of the current cp definition inventory
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory[] findByUuid_C_PrevAndNext(
			long CPDefinitionInventoryId, String uuid, long companyId,
			OrderByComparator<CPDefinitionInventory> orderByComparator)
		throws NoSuchCPDefinitionInventoryException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionInventory cpDefinitionInventory = findByPrimaryKey(
			CPDefinitionInventoryId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionInventory[] array = new CPDefinitionInventoryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpDefinitionInventory, uuid, companyId,
				orderByComparator, true);

			array[1] = cpDefinitionInventory;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpDefinitionInventory, uuid, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionInventory getByUuid_C_PrevAndNext(
		Session session, CPDefinitionInventory cpDefinitionInventory,
		String uuid, long companyId,
		OrderByComparator<CPDefinitionInventory> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CPDefinitionInventoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionInventory)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionInventory> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition inventories where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPDefinitionInventory cpDefinitionInventory :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionInventory);
		}
	}

	/**
	 * Returns the number of cp definition inventories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp definition inventories
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = _finderPathCountByUuid_C;

			Object[] finderArgs = new Object[] {uuid, companyId};

			Long count = (Long)finderCache.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_COUNT_CPDEFINITIONINVENTORY_WHERE);

				boolean bindUuid = false;

				if (uuid.isEmpty()) {
					sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					bindUuid = true;

					sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}

				sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					if (bindUuid) {
						queryPos.add(uuid);
					}

					queryPos.add(companyId);

					count = (Long)query.uniqueResult();

					finderCache.putResult(finderPath, finderArgs, count);
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return count.intValue();
		}
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"cpDefinitionInventory.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpDefinitionInventory.uuid IS NULL OR cpDefinitionInventory.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpDefinitionInventory.companyId = ?";

	private FinderPath _finderPathFetchByCPDefinitionId;

	/**
	 * Returns the cp definition inventory where CPDefinitionId = &#63; or throws a <code>NoSuchCPDefinitionInventoryException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory findByCPDefinitionId(long CPDefinitionId)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByCPDefinitionId(
			CPDefinitionId);

		if (cpDefinitionInventory == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("CPDefinitionId=");
			sb.append(CPDefinitionId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCPDefinitionInventoryException(sb.toString());
		}

		return cpDefinitionInventory;
	}

	/**
	 * Returns the cp definition inventory where CPDefinitionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByCPDefinitionId(long CPDefinitionId) {
		return fetchByCPDefinitionId(CPDefinitionId, true);
	}

	/**
	 * Returns the cp definition inventory where CPDefinitionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition inventory, or <code>null</code> if a matching cp definition inventory could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByCPDefinitionId(
		long CPDefinitionId, boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			Object[] finderArgs = null;

			if (useFinderCache) {
				finderArgs = new Object[] {CPDefinitionId};
			}

			Object result = null;

			if (useFinderCache) {
				result = finderCache.getResult(
					_finderPathFetchByCPDefinitionId, finderArgs, this);
			}

			if (result instanceof CPDefinitionInventory) {
				CPDefinitionInventory cpDefinitionInventory =
					(CPDefinitionInventory)result;

				if (CPDefinitionId !=
						cpDefinitionInventory.getCPDefinitionId()) {

					result = null;
				}
			}

			if (result == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY_WHERE);

				sb.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(CPDefinitionId);

					List<CPDefinitionInventory> list = query.list();

					if (list.isEmpty()) {
						if (useFinderCache) {
							finderCache.putResult(
								_finderPathFetchByCPDefinitionId, finderArgs,
								list);
						}
					}
					else {
						CPDefinitionInventory cpDefinitionInventory = list.get(
							0);

						result = cpDefinitionInventory;

						cacheResult(cpDefinitionInventory);
					}
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (CPDefinitionInventory)result;
			}
		}
	}

	/**
	 * Removes the cp definition inventory where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the cp definition inventory that was removed
	 */
	@Override
	public CPDefinitionInventory removeByCPDefinitionId(long CPDefinitionId)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = findByCPDefinitionId(
			CPDefinitionId);

		return remove(cpDefinitionInventory);
	}

	/**
	 * Returns the number of cp definition inventories where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp definition inventories
	 */
	@Override
	public int countByCPDefinitionId(long CPDefinitionId) {
		CPDefinitionInventory cpDefinitionInventory = fetchByCPDefinitionId(
			CPDefinitionId);

		if (cpDefinitionInventory == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2 =
		"cpDefinitionInventory.CPDefinitionId = ?";

	public CPDefinitionInventoryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CPDefinitionInventory.class);

		setModelImplClass(CPDefinitionInventoryImpl.class);
		setModelPKClass(long.class);

		setTable(CPDefinitionInventoryTable.INSTANCE);
	}

	/**
	 * Caches the cp definition inventory in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionInventory the cp definition inventory
	 */
	@Override
	public void cacheResult(CPDefinitionInventory cpDefinitionInventory) {
		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					cpDefinitionInventory.getCtCollectionId())) {

			entityCache.putResult(
				CPDefinitionInventoryImpl.class,
				cpDefinitionInventory.getPrimaryKey(), cpDefinitionInventory);

			finderCache.putResult(
				_finderPathFetchByUUID_G,
				new Object[] {
					cpDefinitionInventory.getUuid(),
					cpDefinitionInventory.getGroupId()
				},
				cpDefinitionInventory);

			finderCache.putResult(
				_finderPathFetchByCPDefinitionId,
				new Object[] {cpDefinitionInventory.getCPDefinitionId()},
				cpDefinitionInventory);
		}
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the cp definition inventories in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionInventories the cp definition inventories
	 */
	@Override
	public void cacheResult(
		List<CPDefinitionInventory> cpDefinitionInventories) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (cpDefinitionInventories.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CPDefinitionInventory cpDefinitionInventory :
				cpDefinitionInventories) {

			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
						cpDefinitionInventory.getCtCollectionId())) {

				if (entityCache.getResult(
						CPDefinitionInventoryImpl.class,
						cpDefinitionInventory.getPrimaryKey()) == null) {

					cacheResult(cpDefinitionInventory);
				}
			}
		}
	}

	/**
	 * Clears the cache for all cp definition inventories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPDefinitionInventoryImpl.class);

		finderCache.clearCache(CPDefinitionInventoryImpl.class);
	}

	/**
	 * Clears the cache for the cp definition inventory.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPDefinitionInventory cpDefinitionInventory) {
		entityCache.removeResult(
			CPDefinitionInventoryImpl.class, cpDefinitionInventory);
	}

	@Override
	public void clearCache(
		List<CPDefinitionInventory> cpDefinitionInventories) {

		for (CPDefinitionInventory cpDefinitionInventory :
				cpDefinitionInventories) {

			entityCache.removeResult(
				CPDefinitionInventoryImpl.class, cpDefinitionInventory);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CPDefinitionInventoryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CPDefinitionInventoryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CPDefinitionInventoryModelImpl cpDefinitionInventoryModelImpl) {

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					cpDefinitionInventoryModelImpl.getCtCollectionId())) {

			Object[] args = new Object[] {
				cpDefinitionInventoryModelImpl.getUuid(),
				cpDefinitionInventoryModelImpl.getGroupId()
			};

			finderCache.putResult(
				_finderPathFetchByUUID_G, args, cpDefinitionInventoryModelImpl);

			args = new Object[] {
				cpDefinitionInventoryModelImpl.getCPDefinitionId()
			};

			finderCache.putResult(
				_finderPathFetchByCPDefinitionId, args,
				cpDefinitionInventoryModelImpl);
		}
	}

	/**
	 * Creates a new cp definition inventory with the primary key. Does not add the cp definition inventory to the database.
	 *
	 * @param CPDefinitionInventoryId the primary key for the new cp definition inventory
	 * @return the new cp definition inventory
	 */
	@Override
	public CPDefinitionInventory create(long CPDefinitionInventoryId) {
		CPDefinitionInventory cpDefinitionInventory =
			new CPDefinitionInventoryImpl();

		cpDefinitionInventory.setNew(true);
		cpDefinitionInventory.setPrimaryKey(CPDefinitionInventoryId);

		String uuid = PortalUUIDUtil.generate();

		cpDefinitionInventory.setUuid(uuid);

		cpDefinitionInventory.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpDefinitionInventory;
	}

	/**
	 * Removes the cp definition inventory with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionInventoryId the primary key of the cp definition inventory
	 * @return the cp definition inventory that was removed
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory remove(long CPDefinitionInventoryId)
		throws NoSuchCPDefinitionInventoryException {

		return remove((Serializable)CPDefinitionInventoryId);
	}

	/**
	 * Removes the cp definition inventory with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp definition inventory
	 * @return the cp definition inventory that was removed
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory remove(Serializable primaryKey)
		throws NoSuchCPDefinitionInventoryException {

		Session session = null;

		try {
			session = openSession();

			CPDefinitionInventory cpDefinitionInventory =
				(CPDefinitionInventory)session.get(
					CPDefinitionInventoryImpl.class, primaryKey);

			if (cpDefinitionInventory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPDefinitionInventoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpDefinitionInventory);
		}
		catch (NoSuchCPDefinitionInventoryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CPDefinitionInventory removeImpl(
		CPDefinitionInventory cpDefinitionInventory) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpDefinitionInventory)) {
				cpDefinitionInventory = (CPDefinitionInventory)session.get(
					CPDefinitionInventoryImpl.class,
					cpDefinitionInventory.getPrimaryKeyObj());
			}

			if ((cpDefinitionInventory != null) &&
				ctPersistenceHelper.isRemove(cpDefinitionInventory)) {

				session.delete(cpDefinitionInventory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cpDefinitionInventory != null) {
			clearCache(cpDefinitionInventory);
		}

		return cpDefinitionInventory;
	}

	@Override
	public CPDefinitionInventory updateImpl(
		CPDefinitionInventory cpDefinitionInventory) {

		boolean isNew = cpDefinitionInventory.isNew();

		if (!(cpDefinitionInventory instanceof
				CPDefinitionInventoryModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpDefinitionInventory.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpDefinitionInventory);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpDefinitionInventory proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPDefinitionInventory implementation " +
					cpDefinitionInventory.getClass());
		}

		CPDefinitionInventoryModelImpl cpDefinitionInventoryModelImpl =
			(CPDefinitionInventoryModelImpl)cpDefinitionInventory;

		if (Validator.isNull(cpDefinitionInventory.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpDefinitionInventory.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (cpDefinitionInventory.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpDefinitionInventory.setCreateDate(date);
			}
			else {
				cpDefinitionInventory.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!cpDefinitionInventoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpDefinitionInventory.setModifiedDate(date);
			}
			else {
				cpDefinitionInventory.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ctPersistenceHelper.isInsert(cpDefinitionInventory)) {
				if (!isNew) {
					session.evict(
						CPDefinitionInventoryImpl.class,
						cpDefinitionInventory.getPrimaryKeyObj());
				}

				session.save(cpDefinitionInventory);
			}
			else {
				cpDefinitionInventory = (CPDefinitionInventory)session.merge(
					cpDefinitionInventory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CPDefinitionInventoryImpl.class, cpDefinitionInventoryModelImpl,
			false, true);

		cacheUniqueFindersCache(cpDefinitionInventoryModelImpl);

		if (isNew) {
			cpDefinitionInventory.setNew(false);
		}

		cpDefinitionInventory.resetOriginalValues();

		return cpDefinitionInventory;
	}

	/**
	 * Returns the cp definition inventory with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition inventory
	 * @return the cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPDefinitionInventoryException {

		CPDefinitionInventory cpDefinitionInventory = fetchByPrimaryKey(
			primaryKey);

		if (cpDefinitionInventory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPDefinitionInventoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpDefinitionInventory;
	}

	/**
	 * Returns the cp definition inventory with the primary key or throws a <code>NoSuchCPDefinitionInventoryException</code> if it could not be found.
	 *
	 * @param CPDefinitionInventoryId the primary key of the cp definition inventory
	 * @return the cp definition inventory
	 * @throws NoSuchCPDefinitionInventoryException if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory findByPrimaryKey(long CPDefinitionInventoryId)
		throws NoSuchCPDefinitionInventoryException {

		return findByPrimaryKey((Serializable)CPDefinitionInventoryId);
	}

	/**
	 * Returns the cp definition inventory with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition inventory
	 * @return the cp definition inventory, or <code>null</code> if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByPrimaryKey(Serializable primaryKey) {
		if (ctPersistenceHelper.isProductionMode(
				CPDefinitionInventory.class, primaryKey)) {

			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.
						setProductionModeWithSafeCloseable()) {

				return super.fetchByPrimaryKey(primaryKey);
			}
		}

		CPDefinitionInventory cpDefinitionInventory =
			(CPDefinitionInventory)entityCache.getResult(
				CPDefinitionInventoryImpl.class, primaryKey);

		if (cpDefinitionInventory != null) {
			return cpDefinitionInventory;
		}

		Session session = null;

		try {
			session = openSession();

			cpDefinitionInventory = (CPDefinitionInventory)session.get(
				CPDefinitionInventoryImpl.class, primaryKey);

			if (cpDefinitionInventory != null) {
				cacheResult(cpDefinitionInventory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return cpDefinitionInventory;
	}

	/**
	 * Returns the cp definition inventory with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDefinitionInventoryId the primary key of the cp definition inventory
	 * @return the cp definition inventory, or <code>null</code> if a cp definition inventory with the primary key could not be found
	 */
	@Override
	public CPDefinitionInventory fetchByPrimaryKey(
		long CPDefinitionInventoryId) {

		return fetchByPrimaryKey((Serializable)CPDefinitionInventoryId);
	}

	@Override
	public Map<Serializable, CPDefinitionInventory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (ctPersistenceHelper.isProductionMode(CPDefinitionInventory.class)) {
			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.
						setProductionModeWithSafeCloseable()) {

				return super.fetchByPrimaryKeys(primaryKeys);
			}
		}

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPDefinitionInventory> map =
			new HashMap<Serializable, CPDefinitionInventory>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPDefinitionInventory cpDefinitionInventory = fetchByPrimaryKey(
				primaryKey);

			if (cpDefinitionInventory != null) {
				map.put(primaryKey, cpDefinitionInventory);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			try (SafeCloseable safeCloseable =
					ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
						CPDefinitionInventory.class, primaryKey)) {

				CPDefinitionInventory cpDefinitionInventory =
					(CPDefinitionInventory)entityCache.getResult(
						CPDefinitionInventoryImpl.class, primaryKey);

				if (cpDefinitionInventory == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, cpDefinitionInventory);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		if ((databaseInMaxParameters > 0) &&
			(primaryKeys.size() > databaseInMaxParameters)) {

			Iterator<Serializable> iterator = primaryKeys.iterator();

			while (iterator.hasNext()) {
				Set<Serializable> page = new HashSet<>();

				for (int i = 0;
					 (i < databaseInMaxParameters) && iterator.hasNext(); i++) {

					page.add(iterator.next());
				}

				map.putAll(fetchByPrimaryKeys(page));
			}

			return map;
		}

		StringBundler sb = new StringBundler((primaryKeys.size() * 2) + 1);

		sb.append(getSelectSQL());
		sb.append(" WHERE ");
		sb.append(getPKDBName());
		sb.append(" IN (");

		for (Serializable primaryKey : primaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (CPDefinitionInventory cpDefinitionInventory :
					(List<CPDefinitionInventory>)query.list()) {

				map.put(
					cpDefinitionInventory.getPrimaryKeyObj(),
					cpDefinitionInventory);

				cacheResult(cpDefinitionInventory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the cp definition inventories.
	 *
	 * @return the cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition inventories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @return the range of cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition inventories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPDefinitionInventoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition inventories
	 * @param end the upper bound of the range of cp definition inventories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp definition inventories
	 */
	@Override
	public List<CPDefinitionInventory> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionInventory> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindAll;
					finderArgs = FINDER_ARGS_EMPTY;
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindAll;
				finderArgs = new Object[] {start, end, orderByComparator};
			}

			List<CPDefinitionInventory> list = null;

			if (useFinderCache) {
				list = (List<CPDefinitionInventory>)finderCache.getResult(
					finderPath, finderArgs, this);
			}

			if (list == null) {
				StringBundler sb = null;
				String sql = null;

				if (orderByComparator != null) {
					sb = new StringBundler(
						2 + (orderByComparator.getOrderByFields().length * 2));

					sb.append(_SQL_SELECT_CPDEFINITIONINVENTORY);

					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

					sql = sb.toString();
				}
				else {
					sql = _SQL_SELECT_CPDEFINITIONINVENTORY;

					sql = sql.concat(
						CPDefinitionInventoryModelImpl.ORDER_BY_JPQL);
				}

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					list = (List<CPDefinitionInventory>)QueryUtil.list(
						query, getDialect(), start, end);

					cacheResult(list);

					if (useFinderCache) {
						finderCache.putResult(finderPath, finderArgs, list);
					}
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return list;
		}
	}

	/**
	 * Removes all the cp definition inventories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPDefinitionInventory cpDefinitionInventory : findAll()) {
			remove(cpDefinitionInventory);
		}
	}

	/**
	 * Returns the number of cp definition inventories.
	 *
	 * @return the number of cp definition inventories
	 */
	@Override
	public int countAll() {
		try (SafeCloseable safeCloseable =
				ctPersistenceHelper.setCTCollectionIdWithSafeCloseable(
					CPDefinitionInventory.class)) {

			Long count = (Long)finderCache.getResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY, this);

			if (count == null) {
				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(
						_SQL_COUNT_CPDEFINITIONINVENTORY);

					count = (Long)query.uniqueResult();

					finderCache.putResult(
						_finderPathCountAll, FINDER_ARGS_EMPTY, count);
				}
				catch (Exception exception) {
					throw processException(exception);
				}
				finally {
					closeSession(session);
				}
			}

			return count.intValue();
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "CPDefinitionInventoryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CPDEFINITIONINVENTORY;
	}

	@Override
	public Set<String> getCTColumnNames(
		CTColumnResolutionType ctColumnResolutionType) {

		return _ctColumnNamesMap.getOrDefault(
			ctColumnResolutionType, Collections.emptySet());
	}

	@Override
	public List<String> getMappingTableNames() {
		return _mappingTableNames;
	}

	@Override
	public Map<String, Integer> getTableColumnsMap() {
		return CPDefinitionInventoryModelImpl.TABLE_COLUMNS_MAP;
	}

	@Override
	public String getTableName() {
		return "CPDefinitionInventory";
	}

	@Override
	public List<String[]> getUniqueIndexColumnNames() {
		return _uniqueIndexColumnNames;
	}

	private static final Map<CTColumnResolutionType, Set<String>>
		_ctColumnNamesMap = new EnumMap<CTColumnResolutionType, Set<String>>(
			CTColumnResolutionType.class);
	private static final List<String> _mappingTableNames =
		new ArrayList<String>();
	private static final List<String[]> _uniqueIndexColumnNames =
		new ArrayList<String[]>();

	static {
		Set<String> ctControlColumnNames = new HashSet<String>();
		Set<String> ctIgnoreColumnNames = new HashSet<String>();
		Set<String> ctMergeColumnNames = new HashSet<String>();
		Set<String> ctStrictColumnNames = new HashSet<String>();

		ctControlColumnNames.add("mvccVersion");
		ctControlColumnNames.add("ctCollectionId");
		ctStrictColumnNames.add("uuid_");
		ctStrictColumnNames.add("groupId");
		ctStrictColumnNames.add("companyId");
		ctStrictColumnNames.add("userId");
		ctStrictColumnNames.add("userName");
		ctStrictColumnNames.add("createDate");
		ctIgnoreColumnNames.add("modifiedDate");
		ctMergeColumnNames.add("CPDefinitionId");
		ctMergeColumnNames.add("CPDefinitionInventoryEngine");
		ctMergeColumnNames.add("lowStockActivity");
		ctMergeColumnNames.add("displayAvailability");
		ctMergeColumnNames.add("displayStockQuantity");
		ctMergeColumnNames.add("minStockQuantity");
		ctMergeColumnNames.add("backOrders");
		ctMergeColumnNames.add("minOrderQuantity");
		ctMergeColumnNames.add("maxOrderQuantity");
		ctMergeColumnNames.add("allowedOrderQuantities");
		ctMergeColumnNames.add("multipleOrderQuantity");

		_ctColumnNamesMap.put(
			CTColumnResolutionType.CONTROL, ctControlColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.IGNORE, ctIgnoreColumnNames);
		_ctColumnNamesMap.put(CTColumnResolutionType.MERGE, ctMergeColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.PK,
			Collections.singleton("CPDefinitionInventoryId"));
		_ctColumnNamesMap.put(
			CTColumnResolutionType.STRICT, ctStrictColumnNames);

		_uniqueIndexColumnNames.add(new String[] {"uuid_", "groupId"});

		_uniqueIndexColumnNames.add(new String[] {"CPDefinitionId"});
	}

	/**
	 * Initializes the cp definition inventory persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathFetchByCPDefinitionId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCPDefinitionId",
			new String[] {Long.class.getName()},
			new String[] {"CPDefinitionId"}, true);

		CPDefinitionInventoryUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CPDefinitionInventoryUtil.setPersistence(null);

		entityCache.removeCache(CPDefinitionInventoryImpl.class.getName());
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected CTPersistenceHelper ctPersistenceHelper;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPDEFINITIONINVENTORY =
		"SELECT cpDefinitionInventory FROM CPDefinitionInventory cpDefinitionInventory";

	private static final String _SQL_SELECT_CPDEFINITIONINVENTORY_WHERE =
		"SELECT cpDefinitionInventory FROM CPDefinitionInventory cpDefinitionInventory WHERE ";

	private static final String _SQL_COUNT_CPDEFINITIONINVENTORY =
		"SELECT COUNT(cpDefinitionInventory) FROM CPDefinitionInventory cpDefinitionInventory";

	private static final String _SQL_COUNT_CPDEFINITIONINVENTORY_WHERE =
		"SELECT COUNT(cpDefinitionInventory) FROM CPDefinitionInventory cpDefinitionInventory WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"cpDefinitionInventory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPDefinitionInventory exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPDefinitionInventory exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionInventoryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}