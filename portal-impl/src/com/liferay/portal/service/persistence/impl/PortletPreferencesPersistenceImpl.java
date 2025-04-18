/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchPortletPreferencesException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletPreferencesTable;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.PortletPreferencesImpl;
import com.liferay.portal.model.impl.PortletPreferencesModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the portlet preferences service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PortletPreferencesPersistenceImpl
	extends BasePersistenceImpl<PortletPreferences>
	implements PortletPreferencesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PortletPreferencesUtil</code> to access the portlet preferences persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PortletPreferencesImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByOwnerId;
	private FinderPath _finderPathWithoutPaginationFindByOwnerId;
	private FinderPath _finderPathCountByOwnerId;

	/**
	 * Returns all the portlet preferenceses where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByOwnerId(long ownerId) {
		return findByOwnerId(
			ownerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByOwnerId(
		long ownerId, int start, int end) {

		return findByOwnerId(ownerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByOwnerId(
		long ownerId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByOwnerId(ownerId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByOwnerId(
		long ownerId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOwnerId;
				finderArgs = new Object[] {ownerId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOwnerId;
			finderArgs = new Object[] {ownerId, start, end, orderByComparator};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if (ownerId != portletPreferences.getOwnerId()) {
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

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByOwnerId_First(
			long ownerId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByOwnerId_First(
			ownerId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByOwnerId_First(
		long ownerId, OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByOwnerId(
			ownerId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByOwnerId_Last(
			long ownerId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByOwnerId_Last(
			ownerId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByOwnerId_Last(
		long ownerId, OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByOwnerId(ownerId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByOwnerId(
			ownerId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerId the owner ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByOwnerId_PrevAndNext(
			long portletPreferencesId, long ownerId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByOwnerId_PrevAndNext(
				session, portletPreferences, ownerId, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByOwnerId_PrevAndNext(
				session, portletPreferences, ownerId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByOwnerId_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long ownerId,
		OrderByComparator<PortletPreferences> orderByComparator,
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

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ownerId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerId = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 */
	@Override
	public void removeByOwnerId(long ownerId) {
		for (PortletPreferences portletPreferences :
				findByOwnerId(
					ownerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByOwnerId(long ownerId) {
		FinderPath finderPath = _finderPathCountByOwnerId;

		Object[] finderArgs = new Object[] {ownerId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_OWNERID_OWNERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_OWNERID_OWNERID_2 =
		"portletPreferences.ownerId = ?";

	private FinderPath _finderPathWithPaginationFindByPlid;
	private FinderPath _finderPathWithoutPaginationFindByPlid;
	private FinderPath _finderPathCountByPlid;

	/**
	 * Returns all the portlet preferenceses where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPlid(long plid) {
		return findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPlid(long plid, int start, int end) {
		return findByPlid(plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPlid(
		long plid, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByPlid(plid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPlid(
		long plid, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPlid;
				finderArgs = new Object[] {plid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPlid;
			finderArgs = new Object[] {plid, start, end, orderByComparator};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if (plid != portletPreferences.getPlid()) {
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

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_PLID_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByPlid_First(
			long plid, OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByPlid_First(
			plid, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByPlid_First(
		long plid, OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByPlid(
			plid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByPlid_Last(
			long plid, OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByPlid_Last(
			plid, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByPlid_Last(
		long plid, OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByPlid(plid);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByPlid(
			plid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where plid = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByPlid_PrevAndNext(
			long portletPreferencesId, long plid,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByPlid_PrevAndNext(
				session, portletPreferences, plid, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByPlid_PrevAndNext(
				session, portletPreferences, plid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByPlid_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long plid,
		OrderByComparator<PortletPreferences> orderByComparator,
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

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_PLID_PLID_2);

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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(plid);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where plid = &#63; from the database.
	 *
	 * @param plid the plid
	 */
	@Override
	public void removeByPlid(long plid) {
		for (PortletPreferences portletPreferences :
				findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByPlid(long plid) {
		FinderPath finderPath = _finderPathCountByPlid;

		Object[] finderArgs = new Object[] {plid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_PLID_PLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_PLID_PLID_2 =
		"portletPreferences.plid = ?";

	private FinderPath _finderPathWithPaginationFindByPortletId;
	private FinderPath _finderPathWithoutPaginationFindByPortletId;
	private FinderPath _finderPathCountByPortletId;

	/**
	 * Returns all the portlet preferenceses where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPortletId(String portletId) {
		return findByPortletId(
			portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPortletId(
		String portletId, int start, int end) {

		return findByPortletId(portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPortletId(
		String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByPortletId(portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByPortletId(
		String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPortletId;
				finderArgs = new Object[] {portletId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPortletId;
			finderArgs = new Object[] {
				portletId, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if (!portletId.equals(portletPreferences.getPortletId())) {
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

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByPortletId_First(
			String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByPortletId_First(
			portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByPortletId_First(
		String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByPortletId(
			portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByPortletId_Last(
			String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByPortletId_Last(
			portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByPortletId_Last(
		String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByPortletId(portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByPortletId(
			portletId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where portletId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByPortletId_PrevAndNext(
			long portletPreferencesId, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByPortletId_PrevAndNext(
				session, portletPreferences, portletId, orderByComparator,
				true);

			array[1] = portletPreferences;

			array[2] = getByPortletId_PrevAndNext(
				session, portletPreferences, portletId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByPortletId_PrevAndNext(
		Session session, PortletPreferences portletPreferences,
		String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
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

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where portletId = &#63; from the database.
	 *
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByPortletId(String portletId) {
		for (PortletPreferences portletPreferences :
				findByPortletId(
					portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByPortletId(String portletId) {
		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathCountByPortletId;

		Object[] finderArgs = new Object[] {portletId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	private FinderPath _finderPathWithPaginationFindByO_P;
	private FinderPath _finderPathWithoutPaginationFindByO_P;
	private FinderPath _finderPathCountByO_P;

	/**
	 * Returns all the portlet preferenceses where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P(int ownerType, String portletId) {
		return findByO_P(
			ownerType, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P(
		int ownerType, String portletId, int start, int end) {

		return findByO_P(ownerType, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P(
		int ownerType, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByO_P(
			ownerType, portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P(
		int ownerType, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByO_P;
				finderArgs = new Object[] {ownerType, portletId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByO_P;
			finderArgs = new Object[] {
				ownerType, portletId, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((ownerType != portletPreferences.getOwnerType()) ||
						!portletId.equals(portletPreferences.getPortletId())) {

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

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_P_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_P_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_P_First(
			int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_P_First(
			ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerType=");
		sb.append(ownerType);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_P_First(
		int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByO_P(
			ownerType, portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_P_Last(
			int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_P_Last(
			ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerType=");
		sb.append(ownerType);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_P_Last(
		int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByO_P(ownerType, portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByO_P(
			ownerType, portletId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByO_P_PrevAndNext(
			long portletPreferencesId, int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_P_PrevAndNext(
				session, portletPreferences, ownerType, portletId,
				orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_P_PrevAndNext(
				session, portletPreferences, ownerType, portletId,
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

	protected PortletPreferences getByO_P_PrevAndNext(
		Session session, PortletPreferences portletPreferences, int ownerType,
		String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
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

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_O_P_OWNERTYPE_2);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_O_P_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_O_P_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ownerType);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerType = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByO_P(int ownerType, String portletId) {
		for (PortletPreferences portletPreferences :
				findByO_P(
					ownerType, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByO_P(int ownerType, String portletId) {
		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathCountByO_P;

		Object[] finderArgs = new Object[] {ownerType, portletId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_P_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_P_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_O_P_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_O_P_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_O_P_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	private FinderPath _finderPathWithPaginationFindByP_P;
	private FinderPath _finderPathWithoutPaginationFindByP_P;
	private FinderPath _finderPathCountByP_P;

	/**
	 * Returns all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByP_P(long plid, String portletId) {
		return findByP_P(
			plid, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByP_P(
		long plid, String portletId, int start, int end) {

		return findByP_P(plid, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByP_P(
		long plid, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByP_P(plid, portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByP_P(
		long plid, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByP_P;
				finderArgs = new Object[] {plid, portletId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByP_P;
			finderArgs = new Object[] {
				plid, portletId, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((plid != portletPreferences.getPlid()) ||
						!portletId.equals(portletPreferences.getPortletId())) {

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

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_P_P_PLID_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_P_P_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByP_P_First(
			long plid, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByP_P_First(
			plid, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByP_P_First(
		long plid, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByP_P(
			plid, portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByP_P_Last(
			long plid, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByP_P_Last(
			plid, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByP_P_Last(
		long plid, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByP_P(plid, portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByP_P(
			plid, portletId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByP_P_PrevAndNext(
			long portletPreferencesId, long plid, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByP_P_PrevAndNext(
				session, portletPreferences, plid, portletId, orderByComparator,
				true);

			array[1] = portletPreferences;

			array[2] = getByP_P_PrevAndNext(
				session, portletPreferences, plid, portletId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByP_P_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long plid,
		String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
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

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_P_P_PLID_2);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_P_P_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_P_P_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(plid);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByP_P(long plid, String portletId) {
		for (PortletPreferences portletPreferences :
				findByP_P(
					plid, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByP_P(long plid, String portletId) {
		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathCountByP_P;

		Object[] finderArgs = new Object[] {plid, portletId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_P_P_PLID_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_P_P_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_P_P_PLID_2 =
		"portletPreferences.plid = ? AND ";

	private static final String _FINDER_COLUMN_P_P_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_P_P_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	private FinderPath _finderPathWithPaginationFindByO_O_P;
	private FinderPath _finderPathWithoutPaginationFindByO_O_P;
	private FinderPath _finderPathCountByO_O_P;

	/**
	 * Returns all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_P(
		long ownerId, int ownerType, long plid) {

		return findByO_O_P(
			ownerId, ownerType, plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_P(
		long ownerId, int ownerType, long plid, int start, int end) {

		return findByO_O_P(ownerId, ownerType, plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_P(
		long ownerId, int ownerType, long plid, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByO_O_P(
			ownerId, ownerType, plid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_P(
		long ownerId, int ownerType, long plid, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByO_O_P;
				finderArgs = new Object[] {ownerId, ownerType, plid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByO_O_P;
			finderArgs = new Object[] {
				ownerId, ownerType, plid, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((ownerId != portletPreferences.getOwnerId()) ||
						(ownerType != portletPreferences.getOwnerType()) ||
						(plid != portletPreferences.getPlid())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

			sb.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

			sb.append(_FINDER_COLUMN_O_O_P_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				queryPos.add(plid);

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_O_P_First(
			long ownerId, int ownerType, long plid,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_O_P_First(
			ownerId, ownerType, plid, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_P_First(
		long ownerId, int ownerType, long plid,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByO_O_P(
			ownerId, ownerType, plid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_O_P_Last(
			long ownerId, int ownerType, long plid,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_O_P_Last(
			ownerId, ownerType, plid, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_P_Last(
		long ownerId, int ownerType, long plid,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByO_O_P(ownerId, ownerType, plid);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByO_O_P(
			ownerId, ownerType, plid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByO_O_P_PrevAndNext(
			long portletPreferencesId, long ownerId, int ownerType, long plid,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_O_P_PrevAndNext(
				session, portletPreferences, ownerId, ownerType, plid,
				orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_O_P_PrevAndNext(
				session, portletPreferences, ownerId, ownerType, plid,
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

	protected PortletPreferences getByO_O_P_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long ownerId,
		int ownerType, long plid,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

		sb.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

		sb.append(_FINDER_COLUMN_O_O_P_PLID_2);

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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ownerId);

		queryPos.add(ownerType);

		queryPos.add(plid);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 */
	@Override
	public void removeByO_O_P(long ownerId, int ownerType, long plid) {
		for (PortletPreferences portletPreferences :
				findByO_O_P(
					ownerId, ownerType, plid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByO_O_P(long ownerId, int ownerType, long plid) {
		FinderPath finderPath = _finderPathCountByO_O_P;

		Object[] finderArgs = new Object[] {ownerId, ownerType, plid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

			sb.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

			sb.append(_FINDER_COLUMN_O_O_P_PLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				queryPos.add(plid);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_O_O_P_OWNERID_2 =
		"portletPreferences.ownerId = ? AND ";

	private static final String _FINDER_COLUMN_O_O_P_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_O_O_P_PLID_2 =
		"portletPreferences.plid = ?";

	private FinderPath _finderPathWithPaginationFindByO_O_PI;
	private FinderPath _finderPathWithoutPaginationFindByO_O_PI;
	private FinderPath _finderPathCountByO_O_PI;

	/**
	 * Returns all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_PI(
		long ownerId, int ownerType, String portletId) {

		return findByO_O_PI(
			ownerId, ownerType, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_PI(
		long ownerId, int ownerType, String portletId, int start, int end) {

		return findByO_O_PI(ownerId, ownerType, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_PI(
		long ownerId, int ownerType, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByO_O_PI(
			ownerId, ownerType, portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_O_PI(
		long ownerId, int ownerType, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByO_O_PI;
				finderArgs = new Object[] {ownerId, ownerType, portletId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByO_O_PI;
			finderArgs = new Object[] {
				ownerId, ownerType, portletId, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((ownerId != portletPreferences.getOwnerId()) ||
						(ownerType != portletPreferences.getOwnerType()) ||
						!portletId.equals(portletPreferences.getPortletId())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_O_PI_OWNERID_2);

			sb.append(_FINDER_COLUMN_O_O_PI_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_O_PI_First(
			long ownerId, int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_O_PI_First(
			ownerId, ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_PI_First(
		long ownerId, int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByO_O_PI(
			ownerId, ownerType, portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_O_PI_Last(
			long ownerId, int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_O_PI_Last(
			ownerId, ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_PI_Last(
		long ownerId, int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByO_O_PI(ownerId, ownerType, portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByO_O_PI(
			ownerId, ownerType, portletId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByO_O_PI_PrevAndNext(
			long portletPreferencesId, long ownerId, int ownerType,
			String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_O_PI_PrevAndNext(
				session, portletPreferences, ownerId, ownerType, portletId,
				orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_O_PI_PrevAndNext(
				session, portletPreferences, ownerId, ownerType, portletId,
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

	protected PortletPreferences getByO_O_PI_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long ownerId,
		int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_O_O_PI_OWNERID_2);

		sb.append(_FINDER_COLUMN_O_O_PI_OWNERTYPE_2);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ownerId);

		queryPos.add(ownerType);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByO_O_PI(long ownerId, int ownerType, String portletId) {
		for (PortletPreferences portletPreferences :
				findByO_O_PI(
					ownerId, ownerType, portletId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63; and ownerType = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByO_O_PI(long ownerId, int ownerType, String portletId) {
		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathCountByO_O_PI;

		Object[] finderArgs = new Object[] {ownerId, ownerType, portletId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_O_PI_OWNERID_2);

			sb.append(_FINDER_COLUMN_O_O_PI_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_O_PI_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_O_O_PI_OWNERID_2 =
		"portletPreferences.ownerId = ? AND ";

	private static final String _FINDER_COLUMN_O_O_PI_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_O_O_PI_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_O_O_PI_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	private FinderPath _finderPathWithPaginationFindByO_P_P;
	private FinderPath _finderPathWithoutPaginationFindByO_P_P;
	private FinderPath _finderPathCountByO_P_P;

	/**
	 * Returns all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P_P(
		int ownerType, long plid, String portletId) {

		return findByO_P_P(
			ownerType, plid, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P_P(
		int ownerType, long plid, String portletId, int start, int end) {

		return findByO_P_P(ownerType, plid, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P_P(
		int ownerType, long plid, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByO_P_P(
			ownerType, plid, portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByO_P_P(
		int ownerType, long plid, String portletId, int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByO_P_P;
				finderArgs = new Object[] {ownerType, plid, portletId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByO_P_P;
			finderArgs = new Object[] {
				ownerType, plid, portletId, start, end, orderByComparator
			};
		}

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((ownerType != portletPreferences.getOwnerType()) ||
						(plid != portletPreferences.getPlid()) ||
						!portletId.equals(portletPreferences.getPortletId())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

			sb.append(_FINDER_COLUMN_O_P_P_PLID_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerType);

				queryPos.add(plid);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_P_P_First(
			int ownerType, long plid, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_P_P_First(
			ownerType, plid, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerType=");
		sb.append(ownerType);

		sb.append(", plid=");
		sb.append(plid);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_P_P_First(
		int ownerType, long plid, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByO_P_P(
			ownerType, plid, portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_P_P_Last(
			int ownerType, long plid, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_P_P_Last(
			ownerType, plid, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ownerType=");
		sb.append(ownerType);

		sb.append(", plid=");
		sb.append(plid);

		sb.append(", portletId=");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_P_P_Last(
		int ownerType, long plid, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByO_P_P(ownerType, plid, portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByO_P_P(
			ownerType, plid, portletId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByO_P_P_PrevAndNext(
			long portletPreferencesId, int ownerType, long plid,
			String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_P_P_PrevAndNext(
				session, portletPreferences, ownerType, plid, portletId,
				orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_P_P_PrevAndNext(
				session, portletPreferences, ownerType, plid, portletId,
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

	protected PortletPreferences getByO_P_P_PrevAndNext(
		Session session, PortletPreferences portletPreferences, int ownerType,
		long plid, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

		sb.append(_FINDER_COLUMN_O_P_P_PLID_2);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ownerType);

		queryPos.add(plid);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByO_P_P(int ownerType, long plid, String portletId) {
		for (PortletPreferences portletPreferences :
				findByO_P_P(
					ownerType, plid, portletId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByO_P_P(int ownerType, long plid, String portletId) {
		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathCountByO_P_P;

		Object[] finderArgs = new Object[] {ownerType, plid, portletId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

			sb.append(_FINDER_COLUMN_O_P_P_PLID_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerType);

				queryPos.add(plid);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_O_P_P_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_O_P_P_PLID_2 =
		"portletPreferences.plid = ? AND ";

	private static final String _FINDER_COLUMN_O_P_P_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_O_P_P_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	private FinderPath _finderPathWithPaginationFindByC_O_O_LikeP;
	private FinderPath _finderPathWithPaginationCountByC_O_O_LikeP;

	/**
	 * Returns all the portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId) {

		return findByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId,
		int start, int end) {

		return findByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId,
		int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId,
		int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_O_O_LikeP;
		finderArgs = new Object[] {
			companyId, ownerId, ownerType, portletId, start, end,
			orderByComparator
		};

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PortletPreferences portletPreferences : list) {
					if ((companyId != portletPreferences.getCompanyId()) ||
						(ownerId != portletPreferences.getOwnerId()) ||
						(ownerType != portletPreferences.getOwnerType()) ||
						!StringUtil.wildcardMatches(
							portletPreferences.getPortletId(), portletId, '_',
							'%', '\\', true)) {

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
					6 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(6);
			}

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERID_2);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Returns the first portlet preferences in the ordered set where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByC_O_O_LikeP_First(
			long companyId, long ownerId, int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByC_O_O_LikeP_First(
			companyId, ownerId, ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", portletIdLIKE");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the first portlet preferences in the ordered set where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByC_O_O_LikeP_First(
		long companyId, long ownerId, int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		List<PortletPreferences> list = findByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portlet preferences in the ordered set where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByC_O_O_LikeP_Last(
			long companyId, long ownerId, int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByC_O_O_LikeP_Last(
			companyId, ownerId, ownerType, portletId, orderByComparator);

		if (portletPreferences != null) {
			return portletPreferences;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", ownerId=");
		sb.append(ownerId);

		sb.append(", ownerType=");
		sb.append(ownerType);

		sb.append(", portletIdLIKE");
		sb.append(portletId);

		sb.append("}");

		throw new NoSuchPortletPreferencesException(sb.toString());
	}

	/**
	 * Returns the last portlet preferences in the ordered set where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByC_O_O_LikeP_Last(
		long companyId, long ownerId, int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator) {

		int count = countByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId);

		if (count == 0) {
			return null;
		}

		List<PortletPreferences> list = findByC_O_O_LikeP(
			companyId, ownerId, ownerType, portletId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences[] findByC_O_O_LikeP_PrevAndNext(
			long portletPreferencesId, long companyId, long ownerId,
			int ownerType, String portletId,
			OrderByComparator<PortletPreferences> orderByComparator)
		throws NoSuchPortletPreferencesException {

		portletId = Objects.toString(portletId, "");

		PortletPreferences portletPreferences = findByPrimaryKey(
			portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByC_O_O_LikeP_PrevAndNext(
				session, portletPreferences, companyId, ownerId, ownerType,
				portletId, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByC_O_O_LikeP_PrevAndNext(
				session, portletPreferences, companyId, ownerId, ownerType,
				portletId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByC_O_O_LikeP_PrevAndNext(
		Session session, PortletPreferences portletPreferences, long companyId,
		long ownerId, int ownerType, String portletId,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		sb.append(_FINDER_COLUMN_C_O_O_LIKEP_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERID_2);

		sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERTYPE_2);

		boolean bindPortletId = false;

		if (portletId.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_2);
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
			sb.append(PortletPreferencesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		queryPos.add(ownerId);

		queryPos.add(ownerType);

		if (bindPortletId) {
			queryPos.add(portletId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						portletPreferences)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PortletPreferences> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId) {

		for (PortletPreferences portletPreferences :
				findByC_O_O_LikeP(
					companyId, ownerId, ownerType, portletId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where companyId = &#63; and ownerId = &#63; and ownerType = &#63; and portletId LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByC_O_O_LikeP(
		long companyId, long ownerId, int ownerType, String portletId) {

		portletId = Objects.toString(portletId, "");

		FinderPath finderPath = _finderPathWithPaginationCountByC_O_O_LikeP;

		Object[] finderArgs = new Object[] {
			companyId, ownerId, ownerType, portletId
		};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERID_2);

			sb.append(_FINDER_COLUMN_C_O_O_LIKEP_OWNERTYPE_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_C_O_O_LIKEP_COMPANYID_2 =
		"portletPreferences.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_O_O_LIKEP_OWNERID_2 =
		"portletPreferences.ownerId = ? AND ";

	private static final String _FINDER_COLUMN_C_O_O_LIKEP_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_2 =
		"portletPreferences.portletId LIKE ?";

	private static final String _FINDER_COLUMN_C_O_O_LIKEP_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId LIKE '')";

	private FinderPath _finderPathFetchByO_O_P_P;

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or throws a <code>NoSuchPortletPreferencesException</code> if it could not be found.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferences
	 * @throws NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences findByO_O_P_P(
			long ownerId, int ownerType, long plid, String portletId)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByO_O_P_P(
			ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			StringBundler sb = new StringBundler(10);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("ownerId=");
			sb.append(ownerId);

			sb.append(", ownerType=");
			sb.append(ownerType);

			sb.append(", plid=");
			sb.append(plid);

			sb.append(", portletId=");
			sb.append(portletId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPortletPreferencesException(sb.toString());
		}

		return portletPreferences;
	}

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_P_P(
		long ownerId, int ownerType, long plid, String portletId) {

		return fetchByO_O_P_P(ownerId, ownerType, plid, portletId, true);
	}

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 */
	@Override
	public PortletPreferences fetchByO_O_P_P(
		long ownerId, int ownerType, long plid, String portletId,
		boolean useFinderCache) {

		portletId = Objects.toString(portletId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {ownerId, ownerType, plid, portletId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByO_O_P_P, finderArgs, this);
		}

		if (result instanceof PortletPreferences) {
			PortletPreferences portletPreferences = (PortletPreferences)result;

			if ((ownerId != portletPreferences.getOwnerId()) ||
				(ownerType != portletPreferences.getOwnerType()) ||
				(plid != portletPreferences.getPlid()) ||
				!Objects.equals(portletId, portletPreferences.getPortletId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			sb.append(_FINDER_COLUMN_O_O_P_P_OWNERID_2);

			sb.append(_FINDER_COLUMN_O_O_P_P_OWNERTYPE_2);

			sb.append(_FINDER_COLUMN_O_O_P_P_PLID_2);

			boolean bindPortletId = false;

			if (portletId.isEmpty()) {
				sb.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				sb.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ownerId);

				queryPos.add(ownerType);

				queryPos.add(plid);

				if (bindPortletId) {
					queryPos.add(portletId);
				}

				List<PortletPreferences> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByO_O_P_P, finderArgs, list);
					}
				}
				else {
					PortletPreferences portletPreferences = list.get(0);

					result = portletPreferences;

					cacheResult(portletPreferences);
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
			return (PortletPreferences)result;
		}
	}

	/**
	 * Removes the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the portlet preferences that was removed
	 */
	@Override
	public PortletPreferences removeByO_O_P_P(
			long ownerId, int ownerType, long plid, String portletId)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = findByO_O_P_P(
			ownerId, ownerType, plid, portletId);

		return remove(portletPreferences);
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 */
	@Override
	public int countByO_O_P_P(
		long ownerId, int ownerType, long plid, String portletId) {

		PortletPreferences portletPreferences = fetchByO_O_P_P(
			ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_O_O_P_P_OWNERID_2 =
		"portletPreferences.ownerId = ? AND ";

	private static final String _FINDER_COLUMN_O_O_P_P_OWNERTYPE_2 =
		"portletPreferences.ownerType = ? AND ";

	private static final String _FINDER_COLUMN_O_O_P_P_PLID_2 =
		"portletPreferences.plid = ? AND ";

	private static final String _FINDER_COLUMN_O_O_P_P_PORTLETID_2 =
		"portletPreferences.portletId = ?";

	private static final String _FINDER_COLUMN_O_O_P_P_PORTLETID_3 =
		"(portletPreferences.portletId IS NULL OR portletPreferences.portletId = '')";

	public PortletPreferencesPersistenceImpl() {
		setModelClass(PortletPreferences.class);

		setModelImplClass(PortletPreferencesImpl.class);
		setModelPKClass(long.class);

		setTable(PortletPreferencesTable.INSTANCE);
	}

	/**
	 * Caches the portlet preferences in the entity cache if it is enabled.
	 *
	 * @param portletPreferences the portlet preferences
	 */
	@Override
	public void cacheResult(PortletPreferences portletPreferences) {
		EntityCacheUtil.putResult(
			PortletPreferencesImpl.class, portletPreferences.getPrimaryKey(),
			portletPreferences);

		FinderCacheUtil.putResult(
			_finderPathFetchByO_O_P_P,
			new Object[] {
				portletPreferences.getOwnerId(),
				portletPreferences.getOwnerType(), portletPreferences.getPlid(),
				portletPreferences.getPortletId()
			},
			portletPreferences);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the portlet preferenceses in the entity cache if it is enabled.
	 *
	 * @param portletPreferenceses the portlet preferenceses
	 */
	@Override
	public void cacheResult(List<PortletPreferences> portletPreferenceses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (portletPreferenceses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PortletPreferences portletPreferences : portletPreferenceses) {
			if (EntityCacheUtil.getResult(
					PortletPreferencesImpl.class,
					portletPreferences.getPrimaryKey()) == null) {

				cacheResult(portletPreferences);
			}
		}
	}

	/**
	 * Clears the cache for all portlet preferenceses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(PortletPreferencesImpl.class);

		FinderCacheUtil.clearCache(PortletPreferencesImpl.class);
	}

	/**
	 * Clears the cache for the portlet preferences.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PortletPreferences portletPreferences) {
		EntityCacheUtil.removeResult(
			PortletPreferencesImpl.class, portletPreferences);
	}

	@Override
	public void clearCache(List<PortletPreferences> portletPreferenceses) {
		for (PortletPreferences portletPreferences : portletPreferenceses) {
			EntityCacheUtil.removeResult(
				PortletPreferencesImpl.class, portletPreferences);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(PortletPreferencesImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(
				PortletPreferencesImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		PortletPreferencesModelImpl portletPreferencesModelImpl) {

		Object[] args = new Object[] {
			portletPreferencesModelImpl.getOwnerId(),
			portletPreferencesModelImpl.getOwnerType(),
			portletPreferencesModelImpl.getPlid(),
			portletPreferencesModelImpl.getPortletId()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByO_O_P_P, args, portletPreferencesModelImpl);
	}

	/**
	 * Creates a new portlet preferences with the primary key. Does not add the portlet preferences to the database.
	 *
	 * @param portletPreferencesId the primary key for the new portlet preferences
	 * @return the new portlet preferences
	 */
	@Override
	public PortletPreferences create(long portletPreferencesId) {
		PortletPreferences portletPreferences = new PortletPreferencesImpl();

		portletPreferences.setNew(true);
		portletPreferences.setPrimaryKey(portletPreferencesId);

		portletPreferences.setCompanyId(CompanyThreadLocal.getCompanyId());

		return portletPreferences;
	}

	/**
	 * Removes the portlet preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences that was removed
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences remove(long portletPreferencesId)
		throws NoSuchPortletPreferencesException {

		return remove((Serializable)portletPreferencesId);
	}

	/**
	 * Removes the portlet preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the portlet preferences
	 * @return the portlet preferences that was removed
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences remove(Serializable primaryKey)
		throws NoSuchPortletPreferencesException {

		Session session = null;

		try {
			session = openSession();

			PortletPreferences portletPreferences =
				(PortletPreferences)session.get(
					PortletPreferencesImpl.class, primaryKey);

			if (portletPreferences == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPortletPreferencesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(portletPreferences);
		}
		catch (NoSuchPortletPreferencesException noSuchEntityException) {
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
	protected PortletPreferences removeImpl(
		PortletPreferences portletPreferences) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(portletPreferences)) {
				portletPreferences = (PortletPreferences)session.get(
					PortletPreferencesImpl.class,
					portletPreferences.getPrimaryKeyObj());
			}

			if (portletPreferences != null) {
				session.delete(portletPreferences);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (portletPreferences != null) {
			clearCache(portletPreferences);
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences updateImpl(
		PortletPreferences portletPreferences) {

		boolean isNew = portletPreferences.isNew();

		if (!(portletPreferences instanceof PortletPreferencesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(portletPreferences.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					portletPreferences);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in portletPreferences proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PortletPreferences implementation " +
					portletPreferences.getClass());
		}

		PortletPreferencesModelImpl portletPreferencesModelImpl =
			(PortletPreferencesModelImpl)portletPreferences;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(portletPreferences);
			}
			else {
				portletPreferences = (PortletPreferences)session.merge(
					portletPreferences);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			PortletPreferencesImpl.class, portletPreferencesModelImpl, false,
			true);

		cacheUniqueFindersCache(portletPreferencesModelImpl);

		if (isNew) {
			portletPreferences.setNew(false);
		}

		portletPreferences.resetOriginalValues();

		return portletPreferences;
	}

	/**
	 * Returns the portlet preferences with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the portlet preferences
	 * @return the portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPortletPreferencesException {

		PortletPreferences portletPreferences = fetchByPrimaryKey(primaryKey);

		if (portletPreferences == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPortletPreferencesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return portletPreferences;
	}

	/**
	 * Returns the portlet preferences with the primary key or throws a <code>NoSuchPortletPreferencesException</code> if it could not be found.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences
	 * @throws NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences findByPrimaryKey(long portletPreferencesId)
		throws NoSuchPortletPreferencesException {

		return findByPrimaryKey((Serializable)portletPreferencesId);
	}

	/**
	 * Returns the portlet preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences, or <code>null</code> if a portlet preferences with the primary key could not be found
	 */
	@Override
	public PortletPreferences fetchByPrimaryKey(long portletPreferencesId) {
		return fetchByPrimaryKey((Serializable)portletPreferencesId);
	}

	/**
	 * Returns all the portlet preferenceses.
	 *
	 * @return the portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findAll(
		int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PortletPreferencesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of portlet preferenceses
	 */
	@Override
	public List<PortletPreferences> findAll(
		int start, int end,
		OrderByComparator<PortletPreferences> orderByComparator,
		boolean useFinderCache) {

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

		List<PortletPreferences> list = null;

		if (useFinderCache) {
			list = (List<PortletPreferences>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PORTLETPREFERENCES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PORTLETPREFERENCES;

				sql = sql.concat(PortletPreferencesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PortletPreferences>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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

	/**
	 * Removes all the portlet preferenceses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PortletPreferences portletPreferences : findAll()) {
			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses.
	 *
	 * @return the number of portlet preferenceses
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_PORTLETPREFERENCES);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
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

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "portletPreferencesId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PORTLETPREFERENCES;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PortletPreferencesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the portlet preferences persistence.
	 */
	public void afterPropertiesSet() {
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

		_finderPathWithPaginationFindByOwnerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOwnerId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"ownerId"}, true);

		_finderPathWithoutPaginationFindByOwnerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOwnerId",
			new String[] {Long.class.getName()}, new String[] {"ownerId"},
			true);

		_finderPathCountByOwnerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOwnerId",
			new String[] {Long.class.getName()}, new String[] {"ownerId"},
			false);

		_finderPathWithPaginationFindByPlid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlid",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"plid"}, true);

		_finderPathWithoutPaginationFindByPlid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlid",
			new String[] {Long.class.getName()}, new String[] {"plid"}, true);

		_finderPathCountByPlid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlid",
			new String[] {Long.class.getName()}, new String[] {"plid"}, false);

		_finderPathWithPaginationFindByPortletId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPortletId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"portletId"}, true);

		_finderPathWithoutPaginationFindByPortletId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPortletId",
			new String[] {String.class.getName()}, new String[] {"portletId"},
			true);

		_finderPathCountByPortletId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPortletId",
			new String[] {String.class.getName()}, new String[] {"portletId"},
			false);

		_finderPathWithPaginationFindByO_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_P",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"ownerType", "portletId"}, true);

		_finderPathWithoutPaginationFindByO_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_P",
			new String[] {Integer.class.getName(), String.class.getName()},
			new String[] {"ownerType", "portletId"}, true);

		_finderPathCountByO_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_P",
			new String[] {Integer.class.getName(), String.class.getName()},
			new String[] {"ownerType", "portletId"}, false);

		_finderPathWithPaginationFindByP_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"plid", "portletId"}, true);

		_finderPathWithoutPaginationFindByP_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_P",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "portletId"}, true);

		_finderPathCountByP_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_P",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "portletId"}, false);

		_finderPathWithPaginationFindByO_O_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"ownerId", "ownerType", "plid"}, true);

		_finderPathWithoutPaginationFindByO_O_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			new String[] {"ownerId", "ownerType", "plid"}, true);

		_finderPathCountByO_O_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			new String[] {"ownerId", "ownerType", "plid"}, false);

		_finderPathWithPaginationFindByO_O_PI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_O_PI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"ownerId", "ownerType", "portletId"}, true);

		_finderPathWithoutPaginationFindByO_O_PI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_O_PI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			new String[] {"ownerId", "ownerType", "portletId"}, true);

		_finderPathCountByO_O_PI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_O_PI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			new String[] {"ownerId", "ownerType", "portletId"}, false);

		_finderPathWithPaginationFindByO_P_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"ownerType", "plid", "portletId"}, true);

		_finderPathWithoutPaginationFindByO_P_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"ownerType", "plid", "portletId"}, true);

		_finderPathCountByO_P_P = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"ownerType", "plid", "portletId"}, false);

		_finderPathWithPaginationFindByC_O_O_LikeP = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_O_O_LikeP",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "ownerId", "ownerType", "portletId"},
			true);

		_finderPathWithPaginationCountByC_O_O_LikeP = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_O_O_LikeP",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), String.class.getName()
			},
			new String[] {"companyId", "ownerId", "ownerType", "portletId"},
			false);

		_finderPathFetchByO_O_P_P = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByO_O_P_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(), String.class.getName()
			},
			new String[] {"ownerId", "ownerType", "plid", "portletId"}, true);

		PortletPreferencesUtil.setPersistence(this);
	}

	public void destroy() {
		PortletPreferencesUtil.setPersistence(null);

		EntityCacheUtil.removeCache(PortletPreferencesImpl.class.getName());
	}

	private static final String _SQL_SELECT_PORTLETPREFERENCES =
		"SELECT portletPreferences FROM PortletPreferences portletPreferences";

	private static final String _SQL_SELECT_PORTLETPREFERENCES_WHERE =
		"SELECT portletPreferences FROM PortletPreferences portletPreferences WHERE ";

	private static final String _SQL_COUNT_PORTLETPREFERENCES =
		"SELECT COUNT(portletPreferences) FROM PortletPreferences portletPreferences";

	private static final String _SQL_COUNT_PORTLETPREFERENCES_WHERE =
		"SELECT COUNT(portletPreferences) FROM PortletPreferences portletPreferences WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "portletPreferences.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PortletPreferences exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PortletPreferences exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PortletPreferencesPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}