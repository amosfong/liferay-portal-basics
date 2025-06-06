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
import com.liferay.portal.kernel.exception.NoSuchRecentLayoutBranchException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RecentLayoutBranch;
import com.liferay.portal.kernel.model.RecentLayoutBranchTable;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.RecentLayoutBranchPersistence;
import com.liferay.portal.kernel.service.persistence.RecentLayoutBranchUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.model.impl.RecentLayoutBranchImpl;
import com.liferay.portal.model.impl.RecentLayoutBranchModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the recent layout branch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RecentLayoutBranchPersistenceImpl
	extends BasePersistenceImpl<RecentLayoutBranch>
	implements RecentLayoutBranchPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RecentLayoutBranchUtil</code> to access the recent layout branch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RecentLayoutBranchImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the recent layout branches where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout branches where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @return the range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
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

		List<RecentLayoutBranch> list = null;

		if (useFinderCache) {
			list = (List<RecentLayoutBranch>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutBranch recentLayoutBranch : list) {
					if (groupId != recentLayoutBranch.getGroupId()) {
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

			sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<RecentLayoutBranch>)QueryUtil.list(
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
	 * Returns the first recent layout branch in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByGroupId_First(
			long groupId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByGroupId_First(
			groupId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the first recent layout branch in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByGroupId_First(
		long groupId, OrderByComparator<RecentLayoutBranch> orderByComparator) {

		List<RecentLayoutBranch> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout branch in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByGroupId_Last(
			long groupId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the last recent layout branch in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByGroupId_Last(
		long groupId, OrderByComparator<RecentLayoutBranch> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutBranch> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout branches before and after the current recent layout branch in the ordered set where groupId = &#63;.
	 *
	 * @param recentLayoutBranchId the primary key of the current recent layout branch
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch[] findByGroupId_PrevAndNext(
			long recentLayoutBranchId, long groupId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = findByPrimaryKey(
			recentLayoutBranchId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutBranch[] array = new RecentLayoutBranchImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, recentLayoutBranch, groupId, orderByComparator, true);

			array[1] = recentLayoutBranch;

			array[2] = getByGroupId_PrevAndNext(
				session, recentLayoutBranch, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayoutBranch getByGroupId_PrevAndNext(
		Session session, RecentLayoutBranch recentLayoutBranch, long groupId,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
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

		sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

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
			sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
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
						recentLayoutBranch)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RecentLayoutBranch> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout branches where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (RecentLayoutBranch recentLayoutBranch :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(recentLayoutBranch);
		}
	}

	/**
	 * Returns the number of recent layout branches where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching recent layout branches
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECENTLAYOUTBRANCH_WHERE);

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
		"recentLayoutBranch.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the recent layout branches where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout branches where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @return the range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByUserId(
		long userId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByUserId(
		long userId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<RecentLayoutBranch> list = null;

		if (useFinderCache) {
			list = (List<RecentLayoutBranch>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutBranch recentLayoutBranch : list) {
					if (userId != recentLayoutBranch.getUserId()) {
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

			sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<RecentLayoutBranch>)QueryUtil.list(
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
	 * Returns the first recent layout branch in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByUserId_First(
			long userId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByUserId_First(
			userId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the first recent layout branch in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByUserId_First(
		long userId, OrderByComparator<RecentLayoutBranch> orderByComparator) {

		List<RecentLayoutBranch> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout branch in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByUserId_Last(
			long userId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByUserId_Last(
			userId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the last recent layout branch in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByUserId_Last(
		long userId, OrderByComparator<RecentLayoutBranch> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutBranch> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout branches before and after the current recent layout branch in the ordered set where userId = &#63;.
	 *
	 * @param recentLayoutBranchId the primary key of the current recent layout branch
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch[] findByUserId_PrevAndNext(
			long recentLayoutBranchId, long userId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = findByPrimaryKey(
			recentLayoutBranchId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutBranch[] array = new RecentLayoutBranchImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, recentLayoutBranch, userId, orderByComparator, true);

			array[1] = recentLayoutBranch;

			array[2] = getByUserId_PrevAndNext(
				session, recentLayoutBranch, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayoutBranch getByUserId_PrevAndNext(
		Session session, RecentLayoutBranch recentLayoutBranch, long userId,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
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

		sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						recentLayoutBranch)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RecentLayoutBranch> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout branches where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (RecentLayoutBranch recentLayoutBranch :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(recentLayoutBranch);
		}
	}

	/**
	 * Returns the number of recent layout branches where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching recent layout branches
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"recentLayoutBranch.userId = ?";

	private FinderPath _finderPathWithPaginationFindByLayoutBranchId;
	private FinderPath _finderPathWithoutPaginationFindByLayoutBranchId;
	private FinderPath _finderPathCountByLayoutBranchId;

	/**
	 * Returns all the recent layout branches where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @return the matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByLayoutBranchId(long layoutBranchId) {
		return findByLayoutBranchId(
			layoutBranchId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout branches where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @return the range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByLayoutBranchId(
		long layoutBranchId, int start, int end) {

		return findByLayoutBranchId(layoutBranchId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByLayoutBranchId(
		long layoutBranchId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		return findByLayoutBranchId(
			layoutBranchId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout branches where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findByLayoutBranchId(
		long layoutBranchId, int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByLayoutBranchId;
				finderArgs = new Object[] {layoutBranchId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLayoutBranchId;
			finderArgs = new Object[] {
				layoutBranchId, start, end, orderByComparator
			};
		}

		List<RecentLayoutBranch> list = null;

		if (useFinderCache) {
			list = (List<RecentLayoutBranch>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutBranch recentLayoutBranch : list) {
					if (layoutBranchId !=
							recentLayoutBranch.getLayoutBranchId()) {

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

			sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(layoutBranchId);

				list = (List<RecentLayoutBranch>)QueryUtil.list(
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
	 * Returns the first recent layout branch in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByLayoutBranchId_First(
			long layoutBranchId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByLayoutBranchId_First(
			layoutBranchId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutBranchId=");
		sb.append(layoutBranchId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the first recent layout branch in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByLayoutBranchId_First(
		long layoutBranchId,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		List<RecentLayoutBranch> list = findByLayoutBranchId(
			layoutBranchId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout branch in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByLayoutBranchId_Last(
			long layoutBranchId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByLayoutBranchId_Last(
			layoutBranchId, orderByComparator);

		if (recentLayoutBranch != null) {
			return recentLayoutBranch;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutBranchId=");
		sb.append(layoutBranchId);

		sb.append("}");

		throw new NoSuchRecentLayoutBranchException(sb.toString());
	}

	/**
	 * Returns the last recent layout branch in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByLayoutBranchId_Last(
		long layoutBranchId,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		int count = countByLayoutBranchId(layoutBranchId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutBranch> list = findByLayoutBranchId(
			layoutBranchId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout branches before and after the current recent layout branch in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param recentLayoutBranchId the primary key of the current recent layout branch
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch[] findByLayoutBranchId_PrevAndNext(
			long recentLayoutBranchId, long layoutBranchId,
			OrderByComparator<RecentLayoutBranch> orderByComparator)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = findByPrimaryKey(
			recentLayoutBranchId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutBranch[] array = new RecentLayoutBranchImpl[3];

			array[0] = getByLayoutBranchId_PrevAndNext(
				session, recentLayoutBranch, layoutBranchId, orderByComparator,
				true);

			array[1] = recentLayoutBranch;

			array[2] = getByLayoutBranchId_PrevAndNext(
				session, recentLayoutBranch, layoutBranchId, orderByComparator,
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

	protected RecentLayoutBranch getByLayoutBranchId_PrevAndNext(
		Session session, RecentLayoutBranch recentLayoutBranch,
		long layoutBranchId,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
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

		sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

		sb.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

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
			sb.append(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(layoutBranchId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						recentLayoutBranch)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RecentLayoutBranch> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout branches where layoutBranchId = &#63; from the database.
	 *
	 * @param layoutBranchId the layout branch ID
	 */
	@Override
	public void removeByLayoutBranchId(long layoutBranchId) {
		for (RecentLayoutBranch recentLayoutBranch :
				findByLayoutBranchId(
					layoutBranchId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(recentLayoutBranch);
		}
	}

	/**
	 * Returns the number of recent layout branches where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @return the number of matching recent layout branches
	 */
	@Override
	public int countByLayoutBranchId(long layoutBranchId) {
		FinderPath finderPath = _finderPathCountByLayoutBranchId;

		Object[] finderArgs = new Object[] {layoutBranchId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(layoutBranchId);

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

	private static final String _FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2 =
		"recentLayoutBranch.layoutBranchId = ?";

	private FinderPath _finderPathFetchByU_L_P;

	/**
	 * Returns the recent layout branch where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or throws a <code>NoSuchRecentLayoutBranchException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the matching recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch findByU_L_P(
			long userId, long layoutSetBranchId, long plid)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByU_L_P(
			userId, layoutSetBranchId, plid);

		if (recentLayoutBranch == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append(", layoutSetBranchId=");
			sb.append(layoutSetBranchId);

			sb.append(", plid=");
			sb.append(plid);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRecentLayoutBranchException(sb.toString());
		}

		return recentLayoutBranch;
	}

	/**
	 * Returns the recent layout branch where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByU_L_P(
		long userId, long layoutSetBranchId, long plid) {

		return fetchByU_L_P(userId, layoutSetBranchId, plid, true);
	}

	/**
	 * Returns the recent layout branch where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching recent layout branch, or <code>null</code> if a matching recent layout branch could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByU_L_P(
		long userId, long layoutSetBranchId, long plid,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId, layoutSetBranchId, plid};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByU_L_P, finderArgs, this);
		}

		if (result instanceof RecentLayoutBranch) {
			RecentLayoutBranch recentLayoutBranch = (RecentLayoutBranch)result;

			if ((userId != recentLayoutBranch.getUserId()) ||
				(layoutSetBranchId !=
					recentLayoutBranch.getLayoutSetBranchId()) ||
				(plid != recentLayoutBranch.getPlid())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH_WHERE);

			sb.append(_FINDER_COLUMN_U_L_P_USERID_2);

			sb.append(_FINDER_COLUMN_U_L_P_LAYOUTSETBRANCHID_2);

			sb.append(_FINDER_COLUMN_U_L_P_PLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(layoutSetBranchId);

				queryPos.add(plid);

				List<RecentLayoutBranch> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByU_L_P, finderArgs, list);
					}
				}
				else {
					RecentLayoutBranch recentLayoutBranch = list.get(0);

					result = recentLayoutBranch;

					cacheResult(recentLayoutBranch);
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
			return (RecentLayoutBranch)result;
		}
	}

	/**
	 * Removes the recent layout branch where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the recent layout branch that was removed
	 */
	@Override
	public RecentLayoutBranch removeByU_L_P(
			long userId, long layoutSetBranchId, long plid)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = findByU_L_P(
			userId, layoutSetBranchId, plid);

		return remove(recentLayoutBranch);
	}

	/**
	 * Returns the number of recent layout branches where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63;.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the number of matching recent layout branches
	 */
	@Override
	public int countByU_L_P(long userId, long layoutSetBranchId, long plid) {
		RecentLayoutBranch recentLayoutBranch = fetchByU_L_P(
			userId, layoutSetBranchId, plid);

		if (recentLayoutBranch == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_U_L_P_USERID_2 =
		"recentLayoutBranch.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_L_P_LAYOUTSETBRANCHID_2 =
		"recentLayoutBranch.layoutSetBranchId = ? AND ";

	private static final String _FINDER_COLUMN_U_L_P_PLID_2 =
		"recentLayoutBranch.plid = ?";

	public RecentLayoutBranchPersistenceImpl() {
		setModelClass(RecentLayoutBranch.class);

		setModelImplClass(RecentLayoutBranchImpl.class);
		setModelPKClass(long.class);

		setTable(RecentLayoutBranchTable.INSTANCE);
	}

	/**
	 * Caches the recent layout branch in the entity cache if it is enabled.
	 *
	 * @param recentLayoutBranch the recent layout branch
	 */
	@Override
	public void cacheResult(RecentLayoutBranch recentLayoutBranch) {
		EntityCacheUtil.putResult(
			RecentLayoutBranchImpl.class, recentLayoutBranch.getPrimaryKey(),
			recentLayoutBranch);

		FinderCacheUtil.putResult(
			_finderPathFetchByU_L_P,
			new Object[] {
				recentLayoutBranch.getUserId(),
				recentLayoutBranch.getLayoutSetBranchId(),
				recentLayoutBranch.getPlid()
			},
			recentLayoutBranch);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the recent layout branches in the entity cache if it is enabled.
	 *
	 * @param recentLayoutBranchs the recent layout branches
	 */
	@Override
	public void cacheResult(List<RecentLayoutBranch> recentLayoutBranchs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (recentLayoutBranchs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (RecentLayoutBranch recentLayoutBranch : recentLayoutBranchs) {
			if (EntityCacheUtil.getResult(
					RecentLayoutBranchImpl.class,
					recentLayoutBranch.getPrimaryKey()) == null) {

				cacheResult(recentLayoutBranch);
			}
		}
	}

	/**
	 * Clears the cache for all recent layout branches.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(RecentLayoutBranchImpl.class);

		FinderCacheUtil.clearCache(RecentLayoutBranchImpl.class);
	}

	/**
	 * Clears the cache for the recent layout branch.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RecentLayoutBranch recentLayoutBranch) {
		EntityCacheUtil.removeResult(
			RecentLayoutBranchImpl.class, recentLayoutBranch);
	}

	@Override
	public void clearCache(List<RecentLayoutBranch> recentLayoutBranchs) {
		for (RecentLayoutBranch recentLayoutBranch : recentLayoutBranchs) {
			EntityCacheUtil.removeResult(
				RecentLayoutBranchImpl.class, recentLayoutBranch);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(RecentLayoutBranchImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(
				RecentLayoutBranchImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		RecentLayoutBranchModelImpl recentLayoutBranchModelImpl) {

		Object[] args = new Object[] {
			recentLayoutBranchModelImpl.getUserId(),
			recentLayoutBranchModelImpl.getLayoutSetBranchId(),
			recentLayoutBranchModelImpl.getPlid()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByU_L_P, args, recentLayoutBranchModelImpl);
	}

	/**
	 * Creates a new recent layout branch with the primary key. Does not add the recent layout branch to the database.
	 *
	 * @param recentLayoutBranchId the primary key for the new recent layout branch
	 * @return the new recent layout branch
	 */
	@Override
	public RecentLayoutBranch create(long recentLayoutBranchId) {
		RecentLayoutBranch recentLayoutBranch = new RecentLayoutBranchImpl();

		recentLayoutBranch.setNew(true);
		recentLayoutBranch.setPrimaryKey(recentLayoutBranchId);

		recentLayoutBranch.setCompanyId(CompanyThreadLocal.getCompanyId());

		return recentLayoutBranch;
	}

	/**
	 * Removes the recent layout branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutBranchId the primary key of the recent layout branch
	 * @return the recent layout branch that was removed
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch remove(long recentLayoutBranchId)
		throws NoSuchRecentLayoutBranchException {

		return remove((Serializable)recentLayoutBranchId);
	}

	/**
	 * Removes the recent layout branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the recent layout branch
	 * @return the recent layout branch that was removed
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch remove(Serializable primaryKey)
		throws NoSuchRecentLayoutBranchException {

		Session session = null;

		try {
			session = openSession();

			RecentLayoutBranch recentLayoutBranch =
				(RecentLayoutBranch)session.get(
					RecentLayoutBranchImpl.class, primaryKey);

			if (recentLayoutBranch == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecentLayoutBranchException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(recentLayoutBranch);
		}
		catch (NoSuchRecentLayoutBranchException noSuchEntityException) {
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
	protected RecentLayoutBranch removeImpl(
		RecentLayoutBranch recentLayoutBranch) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recentLayoutBranch)) {
				recentLayoutBranch = (RecentLayoutBranch)session.get(
					RecentLayoutBranchImpl.class,
					recentLayoutBranch.getPrimaryKeyObj());
			}

			if (recentLayoutBranch != null) {
				session.delete(recentLayoutBranch);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (recentLayoutBranch != null) {
			clearCache(recentLayoutBranch);
		}

		return recentLayoutBranch;
	}

	@Override
	public RecentLayoutBranch updateImpl(
		RecentLayoutBranch recentLayoutBranch) {

		boolean isNew = recentLayoutBranch.isNew();

		if (!(recentLayoutBranch instanceof RecentLayoutBranchModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(recentLayoutBranch.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					recentLayoutBranch);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in recentLayoutBranch proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RecentLayoutBranch implementation " +
					recentLayoutBranch.getClass());
		}

		RecentLayoutBranchModelImpl recentLayoutBranchModelImpl =
			(RecentLayoutBranchModelImpl)recentLayoutBranch;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(recentLayoutBranch);
			}
			else {
				recentLayoutBranch = (RecentLayoutBranch)session.merge(
					recentLayoutBranch);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			RecentLayoutBranchImpl.class, recentLayoutBranchModelImpl, false,
			true);

		cacheUniqueFindersCache(recentLayoutBranchModelImpl);

		if (isNew) {
			recentLayoutBranch.setNew(false);
		}

		recentLayoutBranch.resetOriginalValues();

		return recentLayoutBranch;
	}

	/**
	 * Returns the recent layout branch with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the recent layout branch
	 * @return the recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecentLayoutBranchException {

		RecentLayoutBranch recentLayoutBranch = fetchByPrimaryKey(primaryKey);

		if (recentLayoutBranch == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecentLayoutBranchException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return recentLayoutBranch;
	}

	/**
	 * Returns the recent layout branch with the primary key or throws a <code>NoSuchRecentLayoutBranchException</code> if it could not be found.
	 *
	 * @param recentLayoutBranchId the primary key of the recent layout branch
	 * @return the recent layout branch
	 * @throws NoSuchRecentLayoutBranchException if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch findByPrimaryKey(long recentLayoutBranchId)
		throws NoSuchRecentLayoutBranchException {

		return findByPrimaryKey((Serializable)recentLayoutBranchId);
	}

	/**
	 * Returns the recent layout branch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recentLayoutBranchId the primary key of the recent layout branch
	 * @return the recent layout branch, or <code>null</code> if a recent layout branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutBranch fetchByPrimaryKey(long recentLayoutBranchId) {
		return fetchByPrimaryKey((Serializable)recentLayoutBranchId);
	}

	/**
	 * Returns all the recent layout branches.
	 *
	 * @return the recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout branches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @return the range of recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout branches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findAll(
		int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout branches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecentLayoutBranchModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout branches
	 * @param end the upper bound of the range of recent layout branches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of recent layout branches
	 */
	@Override
	public List<RecentLayoutBranch> findAll(
		int start, int end,
		OrderByComparator<RecentLayoutBranch> orderByComparator,
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

		List<RecentLayoutBranch> list = null;

		if (useFinderCache) {
			list = (List<RecentLayoutBranch>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RECENTLAYOUTBRANCH);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RECENTLAYOUTBRANCH;

				sql = sql.concat(RecentLayoutBranchModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RecentLayoutBranch>)QueryUtil.list(
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
	 * Removes all the recent layout branches from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RecentLayoutBranch recentLayoutBranch : findAll()) {
			remove(recentLayoutBranch);
		}
	}

	/**
	 * Returns the number of recent layout branches.
	 *
	 * @return the number of recent layout branches
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
					_SQL_COUNT_RECENTLAYOUTBRANCH);

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
		return "recentLayoutBranchId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RECENTLAYOUTBRANCH;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RecentLayoutBranchModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the recent layout branch persistence.
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

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByLayoutBranchId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLayoutBranchId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"layoutBranchId"}, true);

		_finderPathWithoutPaginationFindByLayoutBranchId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLayoutBranchId",
			new String[] {Long.class.getName()},
			new String[] {"layoutBranchId"}, true);

		_finderPathCountByLayoutBranchId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLayoutBranchId",
			new String[] {Long.class.getName()},
			new String[] {"layoutBranchId"}, false);

		_finderPathFetchByU_L_P = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByU_L_P",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"userId", "layoutSetBranchId", "plid"}, true);

		RecentLayoutBranchUtil.setPersistence(this);
	}

	public void destroy() {
		RecentLayoutBranchUtil.setPersistence(null);

		EntityCacheUtil.removeCache(RecentLayoutBranchImpl.class.getName());
	}

	private static final String _SQL_SELECT_RECENTLAYOUTBRANCH =
		"SELECT recentLayoutBranch FROM RecentLayoutBranch recentLayoutBranch";

	private static final String _SQL_SELECT_RECENTLAYOUTBRANCH_WHERE =
		"SELECT recentLayoutBranch FROM RecentLayoutBranch recentLayoutBranch WHERE ";

	private static final String _SQL_COUNT_RECENTLAYOUTBRANCH =
		"SELECT COUNT(recentLayoutBranch) FROM RecentLayoutBranch recentLayoutBranch";

	private static final String _SQL_COUNT_RECENTLAYOUTBRANCH_WHERE =
		"SELECT COUNT(recentLayoutBranch) FROM RecentLayoutBranch recentLayoutBranch WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "recentLayoutBranch.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RecentLayoutBranch exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RecentLayoutBranch exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RecentLayoutBranchPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}