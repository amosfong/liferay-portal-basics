/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.example.sample.service.persistence.impl;

import com.example.sample.exception.NoSuchFooException;
import com.example.sample.model.Foo;
import com.example.sample.model.impl.FooImpl;
import com.example.sample.model.impl.FooModelImpl;
import com.example.sample.service.persistence.FooPersistence;
import com.example.sample.service.persistence.FooUtil;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the foo service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FooPersistenceImpl
	extends BasePersistenceImpl<Foo> implements FooPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FooUtil</code> to access the foo persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FooImpl.class.getName();

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
	 * Returns all the foos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching foos
	 */
	@Override
	public List<Foo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of matching foos
	 */
	@Override
	public List<Foo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Foo> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the foos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Foo> orderByComparator, boolean useFinderCache) {

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

		List<Foo> list = null;

		if (useFinderCache) {
			list = (List<Foo>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Foo foo : list) {
					if (!uuid.equals(foo.getUuid())) {
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

			sb.append(_SQL_SELECT_FOO_WHERE);

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
				sb.append(FooModelImpl.ORDER_BY_JPQL);
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

				list = (List<Foo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first foo in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByUuid_First(
			String uuid, OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByUuid_First(uuid, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the first foo in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUuid_First(
		String uuid, OrderByComparator<Foo> orderByComparator) {

		List<Foo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last foo in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByUuid_Last(
			String uuid, OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByUuid_Last(uuid, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the last foo in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUuid_Last(
		String uuid, OrderByComparator<Foo> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Foo> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the foos before and after the current foo in the ordered set where uuid = &#63;.
	 *
	 * @param fooId the primary key of the current foo
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next foo
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo[] findByUuid_PrevAndNext(
			long fooId, String uuid, OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		uuid = Objects.toString(uuid, "");

		Foo foo = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			Foo[] array = new FooImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, foo, uuid, orderByComparator, true);

			array[1] = foo;

			array[2] = getByUuid_PrevAndNext(
				session, foo, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Foo getByUuid_PrevAndNext(
		Session session, Foo foo, String uuid,
		OrderByComparator<Foo> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FOO_WHERE);

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
			sb.append(FooModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(foo)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Foo> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the foos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Foo foo :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(foo);
		}
	}

	/**
	 * Returns the number of foos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching foos
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FOO_WHERE);

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
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "foo.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(foo.uuid IS NULL OR foo.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFooException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByUUID_G(String uuid, long groupId)
		throws NoSuchFooException {

		Foo foo = fetchByUUID_G(uuid, groupId);

		if (foo == null) {
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

			throw new NoSuchFooException(sb.toString());
		}

		return foo;
	}

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

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

		if (result instanceof Foo) {
			Foo foo = (Foo)result;

			if (!Objects.equals(uuid, foo.getUuid()) ||
				(groupId != foo.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FOO_WHERE);

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

				List<Foo> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Foo foo = list.get(0);

					result = foo;

					cacheResult(foo);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
				}

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
			return (Foo)result;
		}
	}

	/**
	 * Removes the foo where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the foo that was removed
	 */
	@Override
	public Foo removeByUUID_G(String uuid, long groupId)
		throws NoSuchFooException {

		Foo foo = findByUUID_G(uuid, groupId);

		return remove(foo);
	}

	/**
	 * Returns the number of foos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching foos
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FOO_WHERE);

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

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"foo.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(foo.uuid IS NULL OR foo.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"foo.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the foos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching foos
	 */
	@Override
	public List<Foo> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of matching foos
	 */
	@Override
	public List<Foo> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Foo> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the foos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Foo> orderByComparator, boolean useFinderCache) {

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

		List<Foo> list = null;

		if (useFinderCache) {
			list = (List<Foo>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Foo foo : list) {
					if (!uuid.equals(foo.getUuid()) ||
						(companyId != foo.getCompanyId())) {

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

			sb.append(_SQL_SELECT_FOO_WHERE);

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
				sb.append(FooModelImpl.ORDER_BY_JPQL);
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

				list = (List<Foo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first foo in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the first foo in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUuid_C_First(
		String uuid, long companyId, OrderByComparator<Foo> orderByComparator) {

		List<Foo> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last foo in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the last foo in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByUuid_C_Last(
		String uuid, long companyId, OrderByComparator<Foo> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Foo> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the foos before and after the current foo in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fooId the primary key of the current foo
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next foo
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo[] findByUuid_C_PrevAndNext(
			long fooId, String uuid, long companyId,
			OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		uuid = Objects.toString(uuid, "");

		Foo foo = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			Foo[] array = new FooImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, foo, uuid, companyId, orderByComparator, true);

			array[1] = foo;

			array[2] = getByUuid_C_PrevAndNext(
				session, foo, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Foo getByUuid_C_PrevAndNext(
		Session session, Foo foo, String uuid, long companyId,
		OrderByComparator<Foo> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FOO_WHERE);

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
			sb.append(FooModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(foo)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Foo> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the foos where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Foo foo :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(foo);
		}
	}

	/**
	 * Returns the number of foos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching foos
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FOO_WHERE);

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
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"foo.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(foo.uuid IS NULL OR foo.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"foo.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByField2;
	private FinderPath _finderPathWithoutPaginationFindByField2;
	private FinderPath _finderPathCountByField2;

	/**
	 * Returns all the foos where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the matching foos
	 */
	@Override
	public List<Foo> findByField2(boolean field2) {
		return findByField2(field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of matching foos
	 */
	@Override
	public List<Foo> findByField2(boolean field2, int start, int end) {
		return findByField2(field2, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByField2(
		boolean field2, int start, int end,
		OrderByComparator<Foo> orderByComparator) {

		return findByField2(field2, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the foos where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching foos
	 */
	@Override
	public List<Foo> findByField2(
		boolean field2, int start, int end,
		OrderByComparator<Foo> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByField2;
				finderArgs = new Object[] {field2};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByField2;
			finderArgs = new Object[] {field2, start, end, orderByComparator};
		}

		List<Foo> list = null;

		if (useFinderCache) {
			list = (List<Foo>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Foo foo : list) {
					if (field2 != foo.isField2()) {
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

			sb.append(_SQL_SELECT_FOO_WHERE);

			sb.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FooModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(field2);

				list = (List<Foo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first foo in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByField2_First(
			boolean field2, OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByField2_First(field2, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("field2=");
		sb.append(field2);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the first foo in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByField2_First(
		boolean field2, OrderByComparator<Foo> orderByComparator) {

		List<Foo> list = findByField2(field2, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last foo in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo
	 * @throws NoSuchFooException if a matching foo could not be found
	 */
	@Override
	public Foo findByField2_Last(
			boolean field2, OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = fetchByField2_Last(field2, orderByComparator);

		if (foo != null) {
			return foo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("field2=");
		sb.append(field2);

		sb.append("}");

		throw new NoSuchFooException(sb.toString());
	}

	/**
	 * Returns the last foo in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchByField2_Last(
		boolean field2, OrderByComparator<Foo> orderByComparator) {

		int count = countByField2(field2);

		if (count == 0) {
			return null;
		}

		List<Foo> list = findByField2(
			field2, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the foos before and after the current foo in the ordered set where field2 = &#63;.
	 *
	 * @param fooId the primary key of the current foo
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next foo
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo[] findByField2_PrevAndNext(
			long fooId, boolean field2,
			OrderByComparator<Foo> orderByComparator)
		throws NoSuchFooException {

		Foo foo = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			Foo[] array = new FooImpl[3];

			array[0] = getByField2_PrevAndNext(
				session, foo, field2, orderByComparator, true);

			array[1] = foo;

			array[2] = getByField2_PrevAndNext(
				session, foo, field2, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Foo getByField2_PrevAndNext(
		Session session, Foo foo, boolean field2,
		OrderByComparator<Foo> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FOO_WHERE);

		sb.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

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
			sb.append(FooModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(field2);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(foo)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Foo> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the foos where field2 = &#63; from the database.
	 *
	 * @param field2 the field2
	 */
	@Override
	public void removeByField2(boolean field2) {
		for (Foo foo :
				findByField2(
					field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(foo);
		}
	}

	/**
	 * Returns the number of foos where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the number of matching foos
	 */
	@Override
	public int countByField2(boolean field2) {
		FinderPath finderPath = _finderPathCountByField2;

		Object[] finderArgs = new Object[] {field2};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FOO_WHERE);

			sb.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(field2);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FIELD2_FIELD2_2 =
		"foo.field2 = ?";

	public FooPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(Foo.class);
	}

	/**
	 * Caches the foo in the entity cache if it is enabled.
	 *
	 * @param foo the foo
	 */
	@Override
	public void cacheResult(Foo foo) {
		entityCache.putResult(
			FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
			foo.getPrimaryKey(), foo);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {foo.getUuid(), foo.getGroupId()}, foo);

		foo.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the foos in the entity cache if it is enabled.
	 *
	 * @param foos the foos
	 */
	@Override
	public void cacheResult(List<Foo> foos) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (foos.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Foo foo : foos) {
			if (entityCache.getResult(
					FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
					foo.getPrimaryKey()) == null) {

				cacheResult(foo);
			}
			else {
				foo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all foos.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FooImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the foo.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Foo foo) {
		entityCache.removeResult(
			FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
			foo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FooModelImpl)foo, true);
	}

	@Override
	public void clearCache(List<Foo> foos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Foo foo : foos) {
			entityCache.removeResult(
				FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
				foo.getPrimaryKey());

			clearUniqueFindersCache((FooModelImpl)foo, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(FooModelImpl fooModelImpl) {
		Object[] args = new Object[] {
			fooModelImpl.getUuid(), fooModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, fooModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FooModelImpl fooModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				fooModelImpl.getUuid(), fooModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((fooModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				fooModelImpl.getOriginalUuid(),
				fooModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new foo with the primary key. Does not add the foo to the database.
	 *
	 * @param fooId the primary key for the new foo
	 * @return the new foo
	 */
	@Override
	public Foo create(long fooId) {
		Foo foo = new FooImpl();

		foo.setNew(true);
		foo.setPrimaryKey(fooId);

		String uuid = PortalUUIDUtil.generate();

		foo.setUuid(uuid);

		foo.setCompanyId(CompanyThreadLocal.getCompanyId());

		return foo;
	}

	/**
	 * Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo that was removed
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo remove(long fooId) throws NoSuchFooException {
		return remove((Serializable)fooId);
	}

	/**
	 * Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo that was removed
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo remove(Serializable primaryKey) throws NoSuchFooException {
		Session session = null;

		try {
			session = openSession();

			Foo foo = (Foo)session.get(FooImpl.class, primaryKey);

			if (foo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFooException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(foo);
		}
		catch (NoSuchFooException noSuchEntityException) {
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
	protected Foo removeImpl(Foo foo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(foo)) {
				foo = (Foo)session.get(FooImpl.class, foo.getPrimaryKeyObj());
			}

			if (foo != null) {
				session.delete(foo);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (foo != null) {
			clearCache(foo);
		}

		return foo;
	}

	@Override
	public Foo updateImpl(Foo foo) {
		boolean isNew = foo.isNew();

		if (!(foo instanceof FooModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(foo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(foo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in foo proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Foo implementation " +
					foo.getClass());
		}

		FooModelImpl fooModelImpl = (FooModelImpl)foo;

		if (Validator.isNull(foo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			foo.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (foo.getCreateDate() == null)) {
			if (serviceContext == null) {
				foo.setCreateDate(date);
			}
			else {
				foo.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!fooModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				foo.setModifiedDate(date);
			}
			else {
				foo.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(foo);

				foo.setNew(false);
			}
			else {
				foo = (Foo)session.merge(foo);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FooModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {fooModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				fooModelImpl.getUuid(), fooModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {fooModelImpl.isField2()};

			finderCache.removeResult(_finderPathCountByField2, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByField2, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((fooModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {fooModelImpl.getOriginalUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {fooModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((fooModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					fooModelImpl.getOriginalUuid(),
					fooModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					fooModelImpl.getUuid(), fooModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((fooModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByField2.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {fooModelImpl.getOriginalField2()};

				finderCache.removeResult(_finderPathCountByField2, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByField2, args);

				args = new Object[] {fooModelImpl.isField2()};

				finderCache.removeResult(_finderPathCountByField2, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByField2, args);
			}
		}

		entityCache.putResult(
			FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
			foo.getPrimaryKey(), foo, false);

		clearUniqueFindersCache(fooModelImpl, false);
		cacheUniqueFindersCache(fooModelImpl);

		foo.resetOriginalValues();

		return foo;
	}

	/**
	 * Returns the foo with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFooException {

		Foo foo = fetchByPrimaryKey(primaryKey);

		if (foo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFooException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return foo;
	}

	/**
	 * Returns the foo with the primary key or throws a <code>NoSuchFooException</code> if it could not be found.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo
	 * @throws NoSuchFooException if a foo with the primary key could not be found
	 */
	@Override
	public Foo findByPrimaryKey(long fooId) throws NoSuchFooException {
		return findByPrimaryKey((Serializable)fooId);
	}

	/**
	 * Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo, or <code>null</code> if a foo with the primary key could not be found
	 */
	@Override
	public Foo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Foo foo = (Foo)serializable;

		if (foo == null) {
			Session session = null;

			try {
				session = openSession();

				foo = (Foo)session.get(FooImpl.class, primaryKey);

				if (foo != null) {
					cacheResult(foo);
				}
				else {
					entityCache.putResult(
						FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
					primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return foo;
	}

	/**
	 * Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo, or <code>null</code> if a foo with the primary key could not be found
	 */
	@Override
	public Foo fetchByPrimaryKey(long fooId) {
		return fetchByPrimaryKey((Serializable)fooId);
	}

	@Override
	public Map<Serializable, Foo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Foo> map = new HashMap<Serializable, Foo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Foo foo = fetchByPrimaryKey(primaryKey);

			if (foo != null) {
				map.put(primaryKey, foo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Foo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_FOO_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
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

			for (Foo foo : (List<Foo>)query.list()) {
				map.put(foo.getPrimaryKeyObj(), foo);

				cacheResult(foo);

				uncachedPrimaryKeys.remove(foo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					FooModelImpl.ENTITY_CACHE_ENABLED, FooImpl.class,
					primaryKey, nullModel);
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
	 * Returns all the foos.
	 *
	 * @return the foos
	 */
	@Override
	public List<Foo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of foos
	 */
	@Override
	public List<Foo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of foos
	 */
	@Override
	public List<Foo> findAll(
		int start, int end, OrderByComparator<Foo> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FooModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of foos
	 */
	@Override
	public List<Foo> findAll(
		int start, int end, OrderByComparator<Foo> orderByComparator,
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

		List<Foo> list = null;

		if (useFinderCache) {
			list = (List<Foo>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FOO);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FOO;

				sql = sql.concat(FooModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Foo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the foos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Foo foo : findAll()) {
			remove(foo);
		}
	}

	/**
	 * Returns the number of foos.
	 *
	 * @return the number of foos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FOO);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return FooModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the foo persistence.
	 */
	public void afterPropertiesSet() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get("value.object.finder.cache.list.threshold"));

		_finderPathWithPaginationFindAll = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			FooModelImpl.UUID_COLUMN_BITMASK |
			FooModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			FooModelImpl.UUID_COLUMN_BITMASK |
			FooModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			FooModelImpl.UUID_COLUMN_BITMASK |
			FooModelImpl.COMPANYID_COLUMN_BITMASK |
			FooModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByField2 = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByField2",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByField2 = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByField2",
			new String[] {Boolean.class.getName()},
			FooModelImpl.FIELD2_COLUMN_BITMASK |
			FooModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByField2 = new FinderPath(
			FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByField2",
			new String[] {Boolean.class.getName()});

		FooUtil.setPersistence(this);
	}

	public void destroy() {
		FooUtil.setPersistence(null);

		entityCache.removeCache(FooImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FOO = "SELECT foo FROM Foo foo";

	private static final String _SQL_SELECT_FOO_WHERE_PKS_IN =
		"SELECT foo FROM Foo foo WHERE fooId IN (";

	private static final String _SQL_SELECT_FOO_WHERE =
		"SELECT foo FROM Foo foo WHERE ";

	private static final String _SQL_COUNT_FOO =
		"SELECT COUNT(foo) FROM Foo foo";

	private static final String _SQL_COUNT_FOO_WHERE =
		"SELECT COUNT(foo) FROM Foo foo WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "foo.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Foo exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Foo exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FooPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}