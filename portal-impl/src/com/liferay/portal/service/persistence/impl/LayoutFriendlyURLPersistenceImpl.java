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
import com.liferay.portal.kernel.exception.NoSuchLayoutFriendlyURLException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.model.LayoutFriendlyURLTable;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.LayoutFriendlyURLPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutFriendlyURLUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.impl.LayoutFriendlyURLImpl;
import com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the layout friendly url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutFriendlyURLPersistenceImpl
	extends BasePersistenceImpl<LayoutFriendlyURL>
	implements LayoutFriendlyURLPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LayoutFriendlyURLUtil</code> to access the layout friendly url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LayoutFriendlyURLImpl.class.getName();

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
	 * Returns all the layout friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

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

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!uuid.equals(layoutFriendlyURL.getUuid())) {
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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_First(
			String uuid, OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_First(
			uuid, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_First(
		String uuid, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_Last(
			String uuid, OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_Last(
			uuid, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_Last(
		String uuid, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByUuid_PrevAndNext(
			long layoutFriendlyURLId, String uuid,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		uuid = Objects.toString(uuid, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, layoutFriendlyURL, uuid, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByUuid_PrevAndNext(
				session, layoutFriendlyURL, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFriendlyURL getByUuid_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, String uuid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"layoutFriendlyURL.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByUUID_G(uuid, groupId);

		if (layoutFriendlyURL == null) {
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

			throw new NoSuchLayoutFriendlyURLException(sb.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the layout friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if (!Objects.equals(uuid, layoutFriendlyURL.getUuid()) ||
				(groupId != layoutFriendlyURL.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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

				List<LayoutFriendlyURL> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByUUID_G(uuid, groupId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		LayoutFriendlyURL layoutFriendlyURL = fetchByUUID_G(uuid, groupId);

		if (layoutFriendlyURL == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"layoutFriendlyURL.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"layoutFriendlyURL.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

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

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!uuid.equals(layoutFriendlyURL.getUuid()) ||
						(companyId != layoutFriendlyURL.getCompanyId())) {

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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByUuid_C_PrevAndNext(
			long layoutFriendlyURLId, String uuid, long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		uuid = Objects.toString(uuid, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, layoutFriendlyURL, uuid, companyId, orderByComparator,
				true);

			array[1] = layoutFriendlyURL;

			array[2] = getByUuid_C_PrevAndNext(
				session, layoutFriendlyURL, uuid, companyId, orderByComparator,
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

	protected LayoutFriendlyURL getByUuid_C_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, String uuid,
		long companyId, OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"layoutFriendlyURL.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(layoutFriendlyURL.uuid IS NULL OR layoutFriendlyURL.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"layoutFriendlyURL.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the layout friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (groupId != layoutFriendlyURL.getGroupId()) {
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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByGroupId_First(
			long groupId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByGroupId_First(
			groupId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByGroupId_First(
		long groupId, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByGroupId_Last(
			long groupId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByGroupId_Last(
		long groupId, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByGroupId_PrevAndNext(
			long layoutFriendlyURLId, long groupId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, layoutFriendlyURL, groupId, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByGroupId_PrevAndNext(
				session, layoutFriendlyURL, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFriendlyURL getByGroupId_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long groupId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"layoutFriendlyURL.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the layout friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (companyId != layoutFriendlyURL.getCompanyId()) {
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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByCompanyId_First(
			long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByCompanyId_First(
		long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByCompanyId_Last(
			long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByCompanyId_PrevAndNext(
			long layoutFriendlyURLId, long companyId,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, layoutFriendlyURL, companyId, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByCompanyId_PrevAndNext(
				session, layoutFriendlyURL, companyId, orderByComparator,
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

	protected LayoutFriendlyURL getByCompanyId_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long companyId,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"layoutFriendlyURL.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByPlid;
	private FinderPath _finderPathWithoutPaginationFindByPlid;
	private FinderPath _finderPathCountByPlid;

	/**
	 * Returns all the layout friendly urls where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid) {
		return findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(long plid, int start, int end) {
		return findByPlid(plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(
		long plid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByPlid(plid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByPlid(
		long plid, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (plid != layoutFriendlyURL.getPlid()) {
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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_PLID_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPlid_First(
			long plid, OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByPlid_First(
			plid, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPlid_First(
		long plid, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByPlid(
			plid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPlid_Last(
			long plid, OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByPlid_Last(
			plid, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPlid_Last(
		long plid, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByPlid(plid);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByPlid(
			plid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where plid = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByPlid_PrevAndNext(
			long layoutFriendlyURLId, long plid,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByPlid_PrevAndNext(
				session, layoutFriendlyURL, plid, orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByPlid_PrevAndNext(
				session, layoutFriendlyURL, plid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFriendlyURL getByPlid_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long plid,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
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
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where plid = &#63; from the database.
	 *
	 * @param plid the plid
	 */
	@Override
	public void removeByPlid(long plid) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByPlid(long plid) {
		FinderPath finderPath = _finderPathCountByPlid;

		Object[] finderArgs = new Object[] {plid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

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
		"layoutFriendlyURL.plid = ?";

	private FinderPath _finderPathWithPaginationFindByC_F;
	private FinderPath _finderPathWithoutPaginationFindByC_F;
	private FinderPath _finderPathCountByC_F;

	/**
	 * Returns all the layout friendly urls where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByC_F(
		long companyId, String friendlyURL) {

		return findByC_F(
			companyId, friendlyURL, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByC_F(
		long companyId, String friendlyURL, int start, int end) {

		return findByC_F(companyId, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByC_F(
		long companyId, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByC_F(
			companyId, friendlyURL, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByC_F(
		long companyId, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_F;
				finderArgs = new Object[] {companyId, friendlyURL};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_F;
			finderArgs = new Object[] {
				companyId, friendlyURL, start, end, orderByComparator
			};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((companyId != layoutFriendlyURL.getCompanyId()) ||
						!friendlyURL.equals(
							layoutFriendlyURL.getFriendlyURL())) {

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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByC_F_First(
			long companyId, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByC_F_First(
			companyId, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByC_F_First(
		long companyId, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByC_F(
			companyId, friendlyURL, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByC_F_Last(
			long companyId, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByC_F_Last(
			companyId, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByC_F_Last(
		long companyId, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByC_F(companyId, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByC_F(
			companyId, friendlyURL, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByC_F_PrevAndNext(
			long layoutFriendlyURLId, long companyId, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		friendlyURL = Objects.toString(friendlyURL, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByC_F_PrevAndNext(
				session, layoutFriendlyURL, companyId, friendlyURL,
				orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByC_F_PrevAndNext(
				session, layoutFriendlyURL, companyId, friendlyURL,
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

	protected LayoutFriendlyURL getByC_F_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long companyId,
		String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_2);
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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindFriendlyURL) {
			queryPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where companyId = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByC_F(long companyId, String friendlyURL) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByC_F(
					companyId, friendlyURL, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where companyId = &#63; and friendlyURL = &#63;.
	 *
	 * @param companyId the company ID
	 * @param friendlyURL the friendly url
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByC_F(long companyId, String friendlyURL) {
		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = _finderPathCountByC_F;

		Object[] finderArgs = new Object[] {companyId, friendlyURL};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_C_F_FRIENDLYURL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
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

	private static final String _FINDER_COLUMN_C_F_COMPANYID_2 =
		"layoutFriendlyURL.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_F_FRIENDLYURL_2 =
		"layoutFriendlyURL.friendlyURL = ?";

	private static final String _FINDER_COLUMN_C_F_FRIENDLYURL_3 =
		"(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '')";

	private FinderPath _finderPathWithPaginationFindByP_F;
	private FinderPath _finderPathWithoutPaginationFindByP_F;
	private FinderPath _finderPathCountByP_F;

	/**
	 * Returns all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(long plid, String friendlyURL) {
		return findByP_F(
			plid, friendlyURL, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(
		long plid, String friendlyURL, int start, int end) {

		return findByP_F(plid, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(
		long plid, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByP_F(
			plid, friendlyURL, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_F(
		long plid, String friendlyURL, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByP_F;
				finderArgs = new Object[] {plid, friendlyURL};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByP_F;
			finderArgs = new Object[] {
				plid, friendlyURL, start, end, orderByComparator
			};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((plid != layoutFriendlyURL.getPlid()) ||
						!friendlyURL.equals(
							layoutFriendlyURL.getFriendlyURL())) {

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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_P_F_PLID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_F_First(
			long plid, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByP_F_First(
			plid, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_F_First(
		long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByP_F(
			plid, friendlyURL, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_F_Last(
			long plid, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByP_F_Last(
			plid, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("plid=");
		sb.append(plid);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_F_Last(
		long plid, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByP_F(plid, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByP_F(
			plid, friendlyURL, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByP_F_PrevAndNext(
			long layoutFriendlyURLId, long plid, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		friendlyURL = Objects.toString(friendlyURL, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByP_F_PrevAndNext(
				session, layoutFriendlyURL, plid, friendlyURL,
				orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByP_F_PrevAndNext(
				session, layoutFriendlyURL, plid, friendlyURL,
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

	protected LayoutFriendlyURL getByP_F_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long plid,
		String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_P_F_PLID_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(plid);

		if (bindFriendlyURL) {
			queryPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where plid = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByP_F(long plid, String friendlyURL) {
		for (LayoutFriendlyURL layoutFriendlyURL :
				findByP_F(
					plid, friendlyURL, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63; and friendlyURL = &#63;.
	 *
	 * @param plid the plid
	 * @param friendlyURL the friendly url
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_F(long plid, String friendlyURL) {
		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = _finderPathCountByP_F;

		Object[] finderArgs = new Object[] {plid, friendlyURL};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_P_F_PLID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_P_F_FRIENDLYURL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
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

	private static final String _FINDER_COLUMN_P_F_PLID_2 =
		"layoutFriendlyURL.plid = ? AND ";

	private static final String _FINDER_COLUMN_P_F_FRIENDLYURL_2 =
		"layoutFriendlyURL.friendlyURL = ?";

	private static final String _FINDER_COLUMN_P_F_FRIENDLYURL_3 =
		"(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '')";

	private FinderPath _finderPathWithPaginationFindByP_L;
	private FinderPath _finderPathWithoutPaginationFindByP_L;
	private FinderPath _finderPathFetchByP_L;
	private FinderPath _finderPathCountByP_L;
	private FinderPath _finderPathWithPaginationCountByP_L;

	/**
	 * Returns all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(long[] plids, String languageId) {
		return findByP_L(
			plids, languageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(
		long[] plids, String languageId, int start, int end) {

		return findByP_L(plids, languageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(
		long[] plids, String languageId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByP_L(
			plids, languageId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where plid = &#63; and languageId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByP_L(
		long[] plids, String languageId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		if (plids == null) {
			plids = new long[0];
		}
		else if (plids.length > 1) {
			plids = ArrayUtil.sortedUnique(plids);
		}

		languageId = Objects.toString(languageId, "");

		if (plids.length == 1) {
			LayoutFriendlyURL layoutFriendlyURL = fetchByP_L(
				plids[0], languageId);

			if (layoutFriendlyURL == null) {
				return Collections.emptyList();
			}
			else {
				return Collections.singletonList(layoutFriendlyURL);
			}
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {StringUtil.merge(plids), languageId};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				StringUtil.merge(plids), languageId, start, end,
				orderByComparator
			};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				_finderPathWithPaginationFindByP_L, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if (!ArrayUtil.contains(
							plids, layoutFriendlyURL.getPlid()) ||
						!languageId.equals(layoutFriendlyURL.getLanguageId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			try {
				if ((start == QueryUtil.ALL_POS) &&
					(end == QueryUtil.ALL_POS) &&
					(databaseInMaxParameters > 0) &&
					(plids.length > databaseInMaxParameters)) {

					list = new ArrayList<LayoutFriendlyURL>();

					long[][] plidsPages = (long[][])ArrayUtil.split(
						plids, databaseInMaxParameters);

					for (long[] plidsPage : plidsPages) {
						list.addAll(
							_findByP_L(
								plidsPage, languageId, start, end,
								orderByComparator));
					}

					Collections.sort(list, orderByComparator);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = _findByP_L(
						plids, languageId, start, end, orderByComparator);
				}

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationFindByP_L, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return list;
	}

	private List<LayoutFriendlyURL> _findByP_L(
		long[] plids, String languageId, int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		if (plids.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_P_L_PLID_7);

			sb.append(StringUtil.merge(plids));

			sb.append(")");

			sb.append(")");

			sb.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
		}

		sb.setStringAt(
			removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

		if (orderByComparator != null) {
			appendOrderByComparator(
				sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
		}
		else {
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			if (bindLanguageId) {
				queryPos.add(languageId);
			}

			list = (List<LayoutFriendlyURL>)QueryUtil.list(
				query, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return list;
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByP_L(long plid, String languageId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByP_L(plid, languageId);

		if (layoutFriendlyURL == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("plid=");
			sb.append(plid);

			sb.append(", languageId=");
			sb.append(languageId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLayoutFriendlyURLException(sb.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_L(long plid, String languageId) {
		return fetchByP_L(plid, languageId, true);
	}

	/**
	 * Returns the layout friendly url where plid = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByP_L(
		long plid, String languageId, boolean useFinderCache) {

		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {plid, languageId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByP_L, finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if ((plid != layoutFriendlyURL.getPlid()) ||
				!Objects.equals(
					languageId, layoutFriendlyURL.getLanguageId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_P_L_PLID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindLanguageId) {
					queryPos.add(languageId);
				}

				List<LayoutFriendlyURL> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByP_L, finderArgs, list);
					}
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where plid = &#63; and languageId = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByP_L(long plid, String languageId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByP_L(plid, languageId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where plid = &#63; and languageId = &#63;.
	 *
	 * @param plid the plid
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_L(long plid, String languageId) {
		languageId = Objects.toString(languageId, "");

		FinderPath finderPath = _finderPathCountByP_L;

		Object[] finderArgs = new Object[] {plid, languageId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_P_L_PLID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(plid);

				if (bindLanguageId) {
					queryPos.add(languageId);
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

	/**
	 * Returns the number of layout friendly urls where plid = any &#63; and languageId = &#63;.
	 *
	 * @param plids the plids
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByP_L(long[] plids, String languageId) {
		if (plids == null) {
			plids = new long[0];
		}
		else if (plids.length > 1) {
			plids = ArrayUtil.sortedUnique(plids);
		}

		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = new Object[] {
			StringUtil.merge(plids), languageId
		};

		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathWithPaginationCountByP_L, finderArgs, this);

		if (count == null) {
			try {
				if ((databaseInMaxParameters > 0) &&
					(plids.length > databaseInMaxParameters)) {

					count = Long.valueOf(0);

					long[][] plidsPages = (long[][])ArrayUtil.split(
						plids, databaseInMaxParameters);

					for (long[] plidsPage : plidsPages) {
						count += Long.valueOf(
							_countByP_L(plidsPage, languageId));
					}
				}
				else {
					count = Long.valueOf(_countByP_L(plids, languageId));
				}

				FinderCacheUtil.putResult(
					_finderPathWithPaginationCountByP_L, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return count.intValue();
	}

	private int _countByP_L(long[] plids, String languageId) {
		Long count = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

		if (plids.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_P_L_PLID_7);

			sb.append(StringUtil.merge(plids));

			sb.append(")");

			sb.append(")");

			sb.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			sb.append(_FINDER_COLUMN_P_L_LANGUAGEID_2);
		}

		sb.setStringAt(
			removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			if (bindLanguageId) {
				queryPos.add(languageId);
			}

			count = (Long)query.uniqueResult();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_L_PLID_2 =
		"layoutFriendlyURL.plid = ? AND ";

	private static final String _FINDER_COLUMN_P_L_PLID_7 =
		"layoutFriendlyURL.plid IN (";

	private static final String _FINDER_COLUMN_P_L_LANGUAGEID_2 =
		"layoutFriendlyURL.languageId = ?";

	private static final String _FINDER_COLUMN_P_L_LANGUAGEID_3 =
		"(layoutFriendlyURL.languageId IS NULL OR layoutFriendlyURL.languageId = '')";

	private FinderPath _finderPathWithPaginationFindByG_P_F;
	private FinderPath _finderPathWithoutPaginationFindByG_P_F;
	private FinderPath _finderPathCountByG_P_F;

	/**
	 * Returns all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @return the matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL) {

		return findByG_P_F(
			groupId, privateLayout, friendlyURL, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL, int start,
		int end) {

		return findByG_P_F(
			groupId, privateLayout, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findByG_P_F(
			groupId, privateLayout, friendlyURL, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL, int start,
		int end, OrderByComparator<LayoutFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_P_F;
				finderArgs = new Object[] {groupId, privateLayout, friendlyURL};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_P_F;
			finderArgs = new Object[] {
				groupId, privateLayout, friendlyURL, start, end,
				orderByComparator
			};
		}

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFriendlyURL layoutFriendlyURL : list) {
					if ((groupId != layoutFriendlyURL.getGroupId()) ||
						(privateLayout !=
							layoutFriendlyURL.isPrivateLayout()) ||
						!friendlyURL.equals(
							layoutFriendlyURL.getFriendlyURL())) {

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

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Returns the first layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_First(
			long groupId, boolean privateLayout, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_First(
			groupId, privateLayout, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", privateLayout=");
		sb.append(privateLayout);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_First(
		long groupId, boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		List<LayoutFriendlyURL> list = findByG_P_F(
			groupId, privateLayout, friendlyURL, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_Last(
			long groupId, boolean privateLayout, String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_Last(
			groupId, privateLayout, friendlyURL, orderByComparator);

		if (layoutFriendlyURL != null) {
			return layoutFriendlyURL;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", privateLayout=");
		sb.append(privateLayout);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchLayoutFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_Last(
		long groupId, boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		int count = countByG_P_F(groupId, privateLayout, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<LayoutFriendlyURL> list = findByG_P_F(
			groupId, privateLayout, friendlyURL, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout friendly urls before and after the current layout friendly url in the ordered set where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param layoutFriendlyURLId the primary key of the current layout friendly url
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL[] findByG_P_F_PrevAndNext(
			long layoutFriendlyURLId, long groupId, boolean privateLayout,
			String friendlyURL,
			OrderByComparator<LayoutFriendlyURL> orderByComparator)
		throws NoSuchLayoutFriendlyURLException {

		friendlyURL = Objects.toString(friendlyURL, "");

		LayoutFriendlyURL layoutFriendlyURL = findByPrimaryKey(
			layoutFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL[] array = new LayoutFriendlyURLImpl[3];

			array[0] = getByG_P_F_PrevAndNext(
				session, layoutFriendlyURL, groupId, privateLayout, friendlyURL,
				orderByComparator, true);

			array[1] = layoutFriendlyURL;

			array[2] = getByG_P_F_PrevAndNext(
				session, layoutFriendlyURL, groupId, privateLayout, friendlyURL,
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

	protected LayoutFriendlyURL getByG_P_F_PrevAndNext(
		Session session, LayoutFriendlyURL layoutFriendlyURL, long groupId,
		boolean privateLayout, String friendlyURL,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
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
			sb.append(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(privateLayout);

		if (bindFriendlyURL) {
			queryPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						layoutFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL) {

		for (LayoutFriendlyURL layoutFriendlyURL :
				findByG_P_F(
					groupId, privateLayout, friendlyURL, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByG_P_F(
		long groupId, boolean privateLayout, String friendlyURL) {

		friendlyURL = Objects.toString(friendlyURL, "");

		FinderPath finderPath = _finderPathCountByG_P_F;

		Object[] finderArgs = new Object[] {
			groupId, privateLayout, friendlyURL
		};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_G_P_F_FRIENDLYURL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
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

	private static final String _FINDER_COLUMN_G_P_F_GROUPID_2 =
		"layoutFriendlyURL.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_F_PRIVATELAYOUT_2 =
		"layoutFriendlyURL.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_G_P_F_FRIENDLYURL_2 =
		"layoutFriendlyURL.friendlyURL = ?";

	private static final String _FINDER_COLUMN_G_P_F_FRIENDLYURL_3 =
		"(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '')";

	private FinderPath _finderPathFetchByG_P_F_L;

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the matching layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL findByG_P_F_L(
			long groupId, boolean privateLayout, String friendlyURL,
			String languageId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_L(
			groupId, privateLayout, friendlyURL, languageId);

		if (layoutFriendlyURL == null) {
			StringBundler sb = new StringBundler(10);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append(", privateLayout=");
			sb.append(privateLayout);

			sb.append(", friendlyURL=");
			sb.append(friendlyURL);

			sb.append(", languageId=");
			sb.append(languageId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLayoutFriendlyURLException(sb.toString());
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_L(
		long groupId, boolean privateLayout, String friendlyURL,
		String languageId) {

		return fetchByG_P_F_L(
			groupId, privateLayout, friendlyURL, languageId, true);
	}

	/**
	 * Returns the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout friendly url, or <code>null</code> if a matching layout friendly url could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByG_P_F_L(
		long groupId, boolean privateLayout, String friendlyURL,
		String languageId, boolean useFinderCache) {

		friendlyURL = Objects.toString(friendlyURL, "");
		languageId = Objects.toString(languageId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {
				groupId, privateLayout, friendlyURL, languageId
			};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByG_P_F_L, finderArgs, this);
		}

		if (result instanceof LayoutFriendlyURL) {
			LayoutFriendlyURL layoutFriendlyURL = (LayoutFriendlyURL)result;

			if ((groupId != layoutFriendlyURL.getGroupId()) ||
				(privateLayout != layoutFriendlyURL.isPrivateLayout()) ||
				!Objects.equals(
					friendlyURL, layoutFriendlyURL.getFriendlyURL()) ||
				!Objects.equals(
					languageId, layoutFriendlyURL.getLanguageId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_G_P_F_L_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_F_L_PRIVATELAYOUT_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_G_P_F_L_FRIENDLYURL_2);
			}

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				sb.append(_FINDER_COLUMN_G_P_F_L_LANGUAGEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				if (bindLanguageId) {
					queryPos.add(languageId);
				}

				List<LayoutFriendlyURL> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByG_P_F_L, finderArgs, list);
					}
				}
				else {
					LayoutFriendlyURL layoutFriendlyURL = list.get(0);

					result = layoutFriendlyURL;

					cacheResult(layoutFriendlyURL);
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
			return (LayoutFriendlyURL)result;
		}
	}

	/**
	 * Removes the layout friendly url where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the layout friendly url that was removed
	 */
	@Override
	public LayoutFriendlyURL removeByG_P_F_L(
			long groupId, boolean privateLayout, String friendlyURL,
			String languageId)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = findByG_P_F_L(
			groupId, privateLayout, friendlyURL, languageId);

		return remove(layoutFriendlyURL);
	}

	/**
	 * Returns the number of layout friendly urls where groupId = &#63; and privateLayout = &#63; and friendlyURL = &#63; and languageId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param friendlyURL the friendly url
	 * @param languageId the language ID
	 * @return the number of matching layout friendly urls
	 */
	@Override
	public int countByG_P_F_L(
		long groupId, boolean privateLayout, String friendlyURL,
		String languageId) {

		LayoutFriendlyURL layoutFriendlyURL = fetchByG_P_F_L(
			groupId, privateLayout, friendlyURL, languageId);

		if (layoutFriendlyURL == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_G_P_F_L_GROUPID_2 =
		"layoutFriendlyURL.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_F_L_PRIVATELAYOUT_2 =
		"layoutFriendlyURL.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_G_P_F_L_FRIENDLYURL_2 =
		"layoutFriendlyURL.friendlyURL = ? AND ";

	private static final String _FINDER_COLUMN_G_P_F_L_FRIENDLYURL_3 =
		"(layoutFriendlyURL.friendlyURL IS NULL OR layoutFriendlyURL.friendlyURL = '') AND ";

	private static final String _FINDER_COLUMN_G_P_F_L_LANGUAGEID_2 =
		"layoutFriendlyURL.languageId = ?";

	private static final String _FINDER_COLUMN_G_P_F_L_LANGUAGEID_3 =
		"(layoutFriendlyURL.languageId IS NULL OR layoutFriendlyURL.languageId = '')";

	public LayoutFriendlyURLPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LayoutFriendlyURL.class);

		setModelImplClass(LayoutFriendlyURLImpl.class);
		setModelPKClass(long.class);

		setTable(LayoutFriendlyURLTable.INSTANCE);
	}

	/**
	 * Caches the layout friendly url in the entity cache if it is enabled.
	 *
	 * @param layoutFriendlyURL the layout friendly url
	 */
	@Override
	public void cacheResult(LayoutFriendlyURL layoutFriendlyURL) {
		EntityCacheUtil.putResult(
			LayoutFriendlyURLImpl.class, layoutFriendlyURL.getPrimaryKey(),
			layoutFriendlyURL);

		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				layoutFriendlyURL.getUuid(), layoutFriendlyURL.getGroupId()
			},
			layoutFriendlyURL);

		FinderCacheUtil.putResult(
			_finderPathFetchByP_L,
			new Object[] {
				layoutFriendlyURL.getPlid(), layoutFriendlyURL.getLanguageId()
			},
			layoutFriendlyURL);

		FinderCacheUtil.putResult(
			_finderPathFetchByG_P_F_L,
			new Object[] {
				layoutFriendlyURL.getGroupId(),
				layoutFriendlyURL.isPrivateLayout(),
				layoutFriendlyURL.getFriendlyURL(),
				layoutFriendlyURL.getLanguageId()
			},
			layoutFriendlyURL);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the layout friendly urls in the entity cache if it is enabled.
	 *
	 * @param layoutFriendlyURLs the layout friendly urls
	 */
	@Override
	public void cacheResult(List<LayoutFriendlyURL> layoutFriendlyURLs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (layoutFriendlyURLs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			if (EntityCacheUtil.getResult(
					LayoutFriendlyURLImpl.class,
					layoutFriendlyURL.getPrimaryKey()) == null) {

				cacheResult(layoutFriendlyURL);
			}
		}
	}

	/**
	 * Clears the cache for all layout friendly urls.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(LayoutFriendlyURLImpl.class);

		FinderCacheUtil.clearCache(LayoutFriendlyURLImpl.class);
	}

	/**
	 * Clears the cache for the layout friendly url.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutFriendlyURL layoutFriendlyURL) {
		EntityCacheUtil.removeResult(
			LayoutFriendlyURLImpl.class, layoutFriendlyURL);
	}

	@Override
	public void clearCache(List<LayoutFriendlyURL> layoutFriendlyURLs) {
		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			EntityCacheUtil.removeResult(
				LayoutFriendlyURLImpl.class, layoutFriendlyURL);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(LayoutFriendlyURLImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(
				LayoutFriendlyURLImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutFriendlyURLModelImpl layoutFriendlyURLModelImpl) {

		Object[] args = new Object[] {
			layoutFriendlyURLModelImpl.getUuid(),
			layoutFriendlyURLModelImpl.getGroupId()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G, args, layoutFriendlyURLModelImpl);

		args = new Object[] {
			layoutFriendlyURLModelImpl.getPlid(),
			layoutFriendlyURLModelImpl.getLanguageId()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByP_L, args, layoutFriendlyURLModelImpl);

		args = new Object[] {
			layoutFriendlyURLModelImpl.getGroupId(),
			layoutFriendlyURLModelImpl.isPrivateLayout(),
			layoutFriendlyURLModelImpl.getFriendlyURL(),
			layoutFriendlyURLModelImpl.getLanguageId()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByG_P_F_L, args, layoutFriendlyURLModelImpl);
	}

	/**
	 * Creates a new layout friendly url with the primary key. Does not add the layout friendly url to the database.
	 *
	 * @param layoutFriendlyURLId the primary key for the new layout friendly url
	 * @return the new layout friendly url
	 */
	@Override
	public LayoutFriendlyURL create(long layoutFriendlyURLId) {
		LayoutFriendlyURL layoutFriendlyURL = new LayoutFriendlyURLImpl();

		layoutFriendlyURL.setNew(true);
		layoutFriendlyURL.setPrimaryKey(layoutFriendlyURLId);

		String uuid = PortalUUIDUtil.generate();

		layoutFriendlyURL.setUuid(uuid);

		layoutFriendlyURL.setCompanyId(CompanyThreadLocal.getCompanyId());

		return layoutFriendlyURL;
	}

	/**
	 * Removes the layout friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url that was removed
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL remove(long layoutFriendlyURLId)
		throws NoSuchLayoutFriendlyURLException {

		return remove((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Removes the layout friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout friendly url
	 * @return the layout friendly url that was removed
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL remove(Serializable primaryKey)
		throws NoSuchLayoutFriendlyURLException {

		Session session = null;

		try {
			session = openSession();

			LayoutFriendlyURL layoutFriendlyURL =
				(LayoutFriendlyURL)session.get(
					LayoutFriendlyURLImpl.class, primaryKey);

			if (layoutFriendlyURL == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutFriendlyURLException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(layoutFriendlyURL);
		}
		catch (NoSuchLayoutFriendlyURLException noSuchEntityException) {
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
	protected LayoutFriendlyURL removeImpl(
		LayoutFriendlyURL layoutFriendlyURL) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutFriendlyURL)) {
				layoutFriendlyURL = (LayoutFriendlyURL)session.get(
					LayoutFriendlyURLImpl.class,
					layoutFriendlyURL.getPrimaryKeyObj());
			}

			if (layoutFriendlyURL != null) {
				session.delete(layoutFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (layoutFriendlyURL != null) {
			clearCache(layoutFriendlyURL);
		}

		return layoutFriendlyURL;
	}

	@Override
	public LayoutFriendlyURL updateImpl(LayoutFriendlyURL layoutFriendlyURL) {
		boolean isNew = layoutFriendlyURL.isNew();

		if (!(layoutFriendlyURL instanceof LayoutFriendlyURLModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(layoutFriendlyURL.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					layoutFriendlyURL);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutFriendlyURL proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutFriendlyURL implementation " +
					layoutFriendlyURL.getClass());
		}

		LayoutFriendlyURLModelImpl layoutFriendlyURLModelImpl =
			(LayoutFriendlyURLModelImpl)layoutFriendlyURL;

		if (Validator.isNull(layoutFriendlyURL.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			layoutFriendlyURL.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (layoutFriendlyURL.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutFriendlyURL.setCreateDate(date);
			}
			else {
				layoutFriendlyURL.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!layoutFriendlyURLModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutFriendlyURL.setModifiedDate(date);
			}
			else {
				layoutFriendlyURL.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(layoutFriendlyURL);
			}
			else {
				layoutFriendlyURL = (LayoutFriendlyURL)session.merge(
					layoutFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			LayoutFriendlyURLImpl.class, layoutFriendlyURLModelImpl, false,
			true);

		cacheUniqueFindersCache(layoutFriendlyURLModelImpl);

		if (isNew) {
			layoutFriendlyURL.setNew(false);
		}

		layoutFriendlyURL.resetOriginalValues();

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout friendly url
	 * @return the layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutFriendlyURLException {

		LayoutFriendlyURL layoutFriendlyURL = fetchByPrimaryKey(primaryKey);

		if (layoutFriendlyURL == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutFriendlyURLException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return layoutFriendlyURL;
	}

	/**
	 * Returns the layout friendly url with the primary key or throws a <code>NoSuchLayoutFriendlyURLException</code> if it could not be found.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url
	 * @throws NoSuchLayoutFriendlyURLException if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL findByPrimaryKey(long layoutFriendlyURLId)
		throws NoSuchLayoutFriendlyURLException {

		return findByPrimaryKey((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Returns the layout friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutFriendlyURLId the primary key of the layout friendly url
	 * @return the layout friendly url, or <code>null</code> if a layout friendly url with the primary key could not be found
	 */
	@Override
	public LayoutFriendlyURL fetchByPrimaryKey(long layoutFriendlyURLId) {
		return fetchByPrimaryKey((Serializable)layoutFriendlyURLId);
	}

	/**
	 * Returns all the layout friendly urls.
	 *
	 * @return the layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @return the range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout friendly urls
	 * @param end the upper bound of the range of layout friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of layout friendly urls
	 */
	@Override
	public List<LayoutFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<LayoutFriendlyURL> orderByComparator,
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

		List<LayoutFriendlyURL> list = null;

		if (useFinderCache) {
			list = (List<LayoutFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LAYOUTFRIENDLYURL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTFRIENDLYURL;

				sql = sql.concat(LayoutFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LayoutFriendlyURL>)QueryUtil.list(
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
	 * Removes all the layout friendly urls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutFriendlyURL layoutFriendlyURL : findAll()) {
			remove(layoutFriendlyURL);
		}
	}

	/**
	 * Returns the number of layout friendly urls.
	 *
	 * @return the number of layout friendly urls
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LAYOUTFRIENDLYURL);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "layoutFriendlyURLId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LAYOUTFRIENDLYURL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutFriendlyURLModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout friendly url persistence.
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

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
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

		_finderPathWithPaginationFindByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "friendlyURL"}, true);

		_finderPathWithoutPaginationFindByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "friendlyURL"}, true);

		_finderPathCountByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "friendlyURL"}, false);

		_finderPathWithPaginationFindByP_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"plid", "friendlyURL"}, true);

		_finderPathWithoutPaginationFindByP_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "friendlyURL"}, true);

		_finderPathCountByP_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "friendlyURL"}, false);

		_finderPathWithPaginationFindByP_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"plid", "languageId"}, true);

		_finderPathWithoutPaginationFindByP_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "languageId"}, true);

		_finderPathFetchByP_L = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByP_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "languageId"}, true);

		_finderPathCountByP_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "languageId"}, false);

		_finderPathWithPaginationCountByP_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"plid", "languageId"}, false);

		_finderPathWithPaginationFindByG_P_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_F",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "privateLayout", "friendlyURL"}, true);

		_finderPathWithoutPaginationFindByG_P_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_F",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "privateLayout", "friendlyURL"}, true);

		_finderPathCountByG_P_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_F",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "privateLayout", "friendlyURL"}, false);

		_finderPathFetchByG_P_F_L = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P_F_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				String.class.getName(), String.class.getName()
			},
			new String[] {
				"groupId", "privateLayout", "friendlyURL", "languageId"
			},
			true);

		LayoutFriendlyURLUtil.setPersistence(this);
	}

	public void destroy() {
		LayoutFriendlyURLUtil.setPersistence(null);

		EntityCacheUtil.removeCache(LayoutFriendlyURLImpl.class.getName());
	}

	private static final String _SQL_SELECT_LAYOUTFRIENDLYURL =
		"SELECT layoutFriendlyURL FROM LayoutFriendlyURL layoutFriendlyURL";

	private static final String _SQL_SELECT_LAYOUTFRIENDLYURL_WHERE =
		"SELECT layoutFriendlyURL FROM LayoutFriendlyURL layoutFriendlyURL WHERE ";

	private static final String _SQL_COUNT_LAYOUTFRIENDLYURL =
		"SELECT COUNT(layoutFriendlyURL) FROM LayoutFriendlyURL layoutFriendlyURL";

	private static final String _SQL_COUNT_LAYOUTFRIENDLYURL_WHERE =
		"SELECT COUNT(layoutFriendlyURL) FROM LayoutFriendlyURL layoutFriendlyURL WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutFriendlyURL.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LayoutFriendlyURL exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LayoutFriendlyURL exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutFriendlyURLPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}