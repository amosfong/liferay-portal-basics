/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.service.persistence.impl;

import com.liferay.friendly.url.exception.NoSuchFriendlyURLEntryException;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryTable;
import com.liferay.friendly.url.model.impl.FriendlyURLEntryImpl;
import com.liferay.friendly.url.model.impl.FriendlyURLEntryModelImpl;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryLocalizationPersistence;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryPersistence;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryUtil;
import com.liferay.friendly.url.service.persistence.impl.constants.FURLPersistenceConstants;
import com.liferay.petra.string.StringBundler;
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

import java.util.Date;
import java.util.HashMap;
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
 * The persistence implementation for the friendly url entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FriendlyURLEntryPersistence.class)
public class FriendlyURLEntryPersistenceImpl
	extends BasePersistenceImpl<FriendlyURLEntry>
	implements FriendlyURLEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FriendlyURLEntryUtil</code> to access the friendly url entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FriendlyURLEntryImpl.class.getName();

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
	 * Returns all the friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @return the range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		List<FriendlyURLEntry> list = null;

		if (useFinderCache) {
			list = (List<FriendlyURLEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FriendlyURLEntry friendlyURLEntry : list) {
					if (!uuid.equals(friendlyURLEntry.getUuid())) {
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

			sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

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
				sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
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

				list = (List<FriendlyURLEntry>)QueryUtil.list(
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

	/**
	 * Returns the first friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByUuid_First(
			String uuid, OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByUuid_First(
			uuid, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the first friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUuid_First(
		String uuid, OrderByComparator<FriendlyURLEntry> orderByComparator) {

		List<FriendlyURLEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByUuid_Last(
			String uuid, OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the last friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUuid_Last(
		String uuid, OrderByComparator<FriendlyURLEntry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FriendlyURLEntry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the friendly url entries before and after the current friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param friendlyURLEntryId the primary key of the current friendly url entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry[] findByUuid_PrevAndNext(
			long friendlyURLEntryId, String uuid,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		uuid = Objects.toString(uuid, "");

		FriendlyURLEntry friendlyURLEntry = findByPrimaryKey(
			friendlyURLEntryId);

		Session session = null;

		try {
			session = openSession();

			FriendlyURLEntry[] array = new FriendlyURLEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, friendlyURLEntry, uuid, orderByComparator, true);

			array[1] = friendlyURLEntry;

			array[2] = getByUuid_PrevAndNext(
				session, friendlyURLEntry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FriendlyURLEntry getByUuid_PrevAndNext(
		Session session, FriendlyURLEntry friendlyURLEntry, String uuid,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

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
			sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
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
						friendlyURLEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FriendlyURLEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the friendly url entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FriendlyURLEntry friendlyURLEntry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(friendlyURLEntry);
		}
	}

	/**
	 * Returns the number of friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching friendly url entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FRIENDLYURLENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"friendlyURLEntry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(friendlyURLEntry.uuid IS NULL OR friendlyURLEntry.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the friendly url entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByUUID_G(uuid, groupId);

		if (friendlyURLEntry == null) {
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

			throw new NoSuchFriendlyURLEntryException(sb.toString());
		}

		return friendlyURLEntry;
	}

	/**
	 * Returns the friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUUID_G(
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

		if (result instanceof FriendlyURLEntry) {
			FriendlyURLEntry friendlyURLEntry = (FriendlyURLEntry)result;

			if (!Objects.equals(uuid, friendlyURLEntry.getUuid()) ||
				(groupId != friendlyURLEntry.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

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

				List<FriendlyURLEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					FriendlyURLEntry friendlyURLEntry = list.get(0);

					result = friendlyURLEntry;

					cacheResult(friendlyURLEntry);
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
			return (FriendlyURLEntry)result;
		}
	}

	/**
	 * Removes the friendly url entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the friendly url entry that was removed
	 */
	@Override
	public FriendlyURLEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = findByUUID_G(uuid, groupId);

		return remove(friendlyURLEntry);
	}

	/**
	 * Returns the number of friendly url entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching friendly url entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FriendlyURLEntry friendlyURLEntry = fetchByUUID_G(uuid, groupId);

		if (friendlyURLEntry == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"friendlyURLEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(friendlyURLEntry.uuid IS NULL OR friendlyURLEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"friendlyURLEntry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @return the range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		List<FriendlyURLEntry> list = null;

		if (useFinderCache) {
			list = (List<FriendlyURLEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FriendlyURLEntry friendlyURLEntry : list) {
					if (!uuid.equals(friendlyURLEntry.getUuid()) ||
						(companyId != friendlyURLEntry.getCompanyId())) {

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

			sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

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
				sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
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

				list = (List<FriendlyURLEntry>)QueryUtil.list(
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

	/**
	 * Returns the first friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the first friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		List<FriendlyURLEntry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the last friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FriendlyURLEntry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the friendly url entries before and after the current friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param friendlyURLEntryId the primary key of the current friendly url entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry[] findByUuid_C_PrevAndNext(
			long friendlyURLEntryId, String uuid, long companyId,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		uuid = Objects.toString(uuid, "");

		FriendlyURLEntry friendlyURLEntry = findByPrimaryKey(
			friendlyURLEntryId);

		Session session = null;

		try {
			session = openSession();

			FriendlyURLEntry[] array = new FriendlyURLEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, friendlyURLEntry, uuid, companyId, orderByComparator,
				true);

			array[1] = friendlyURLEntry;

			array[2] = getByUuid_C_PrevAndNext(
				session, friendlyURLEntry, uuid, companyId, orderByComparator,
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

	protected FriendlyURLEntry getByUuid_C_PrevAndNext(
		Session session, FriendlyURLEntry friendlyURLEntry, String uuid,
		long companyId, OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

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
			sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
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
						friendlyURLEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FriendlyURLEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the friendly url entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FriendlyURLEntry friendlyURLEntry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(friendlyURLEntry);
		}
	}

	/**
	 * Returns the number of friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching friendly url entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FRIENDLYURLENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"friendlyURLEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(friendlyURLEntry.uuid IS NULL OR friendlyURLEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"friendlyURLEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByG_C_C;
	private FinderPath _finderPathWithoutPaginationFindByG_C_C;
	private FinderPath _finderPathCountByG_C_C;

	/**
	 * Returns all the friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK) {

		return findByG_C_C(
			groupId, classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @return the range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end) {

		return findByG_C_C(groupId, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		return findByG_C_C(
			groupId, classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_C_C;
				finderArgs = new Object[] {groupId, classNameId, classPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_C_C;
			finderArgs = new Object[] {
				groupId, classNameId, classPK, start, end, orderByComparator
			};
		}

		List<FriendlyURLEntry> list = null;

		if (useFinderCache) {
			list = (List<FriendlyURLEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FriendlyURLEntry friendlyURLEntry : list) {
					if ((groupId != friendlyURLEntry.getGroupId()) ||
						(classNameId != friendlyURLEntry.getClassNameId()) ||
						(classPK != friendlyURLEntry.getClassPK())) {

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

			sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

			sb.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<FriendlyURLEntry>)QueryUtil.list(
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

	/**
	 * Returns the first friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByG_C_C_First(
			long groupId, long classNameId, long classPK,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByG_C_C_First(
			groupId, classNameId, classPK, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the first friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByG_C_C_First(
		long groupId, long classNameId, long classPK,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		List<FriendlyURLEntry> list = findByG_C_C(
			groupId, classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry findByG_C_C_Last(
			long groupId, long classNameId, long classPK,
			OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByG_C_C_Last(
			groupId, classNameId, classPK, orderByComparator);

		if (friendlyURLEntry != null) {
			return friendlyURLEntry;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchFriendlyURLEntryException(sb.toString());
	}

	/**
	 * Returns the last friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByG_C_C_Last(
		long groupId, long classNameId, long classPK,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		int count = countByG_C_C(groupId, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<FriendlyURLEntry> list = findByG_C_C(
			groupId, classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the friendly url entries before and after the current friendly url entry in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param friendlyURLEntryId the primary key of the current friendly url entry
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry[] findByG_C_C_PrevAndNext(
			long friendlyURLEntryId, long groupId, long classNameId,
			long classPK, OrderByComparator<FriendlyURLEntry> orderByComparator)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = findByPrimaryKey(
			friendlyURLEntryId);

		Session session = null;

		try {
			session = openSession();

			FriendlyURLEntry[] array = new FriendlyURLEntryImpl[3];

			array[0] = getByG_C_C_PrevAndNext(
				session, friendlyURLEntry, groupId, classNameId, classPK,
				orderByComparator, true);

			array[1] = friendlyURLEntry;

			array[2] = getByG_C_C_PrevAndNext(
				session, friendlyURLEntry, groupId, classNameId, classPK,
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

	protected FriendlyURLEntry getByG_C_C_PrevAndNext(
		Session session, FriendlyURLEntry friendlyURLEntry, long groupId,
		long classNameId, long classPK,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_FRIENDLYURLENTRY_WHERE);

		sb.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

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
			sb.append(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						friendlyURLEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FriendlyURLEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByG_C_C(long groupId, long classNameId, long classPK) {
		for (FriendlyURLEntry friendlyURLEntry :
				findByG_C_C(
					groupId, classNameId, classPK, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(friendlyURLEntry);
		}
	}

	/**
	 * Returns the number of friendly url entries where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching friendly url entries
	 */
	@Override
	public int countByG_C_C(long groupId, long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByG_C_C;

		Object[] finderArgs = new Object[] {groupId, classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_FRIENDLYURLENTRY_WHERE);

			sb.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(classNameId);

				queryPos.add(classPK);

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

	private static final String _FINDER_COLUMN_G_C_C_GROUPID_2 =
		"friendlyURLEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_C_CLASSNAMEID_2 =
		"friendlyURLEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_C_CLASSPK_2 =
		"friendlyURLEntry.classPK = ?";

	public FriendlyURLEntryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FriendlyURLEntry.class);

		setModelImplClass(FriendlyURLEntryImpl.class);
		setModelPKClass(long.class);

		setTable(FriendlyURLEntryTable.INSTANCE);
	}

	/**
	 * Caches the friendly url entry in the entity cache if it is enabled.
	 *
	 * @param friendlyURLEntry the friendly url entry
	 */
	@Override
	public void cacheResult(FriendlyURLEntry friendlyURLEntry) {
		entityCache.putResult(
			FriendlyURLEntryImpl.class, friendlyURLEntry.getPrimaryKey(),
			friendlyURLEntry);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				friendlyURLEntry.getUuid(), friendlyURLEntry.getGroupId()
			},
			friendlyURLEntry);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the friendly url entries in the entity cache if it is enabled.
	 *
	 * @param friendlyURLEntries the friendly url entries
	 */
	@Override
	public void cacheResult(List<FriendlyURLEntry> friendlyURLEntries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (friendlyURLEntries.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FriendlyURLEntry friendlyURLEntry : friendlyURLEntries) {
			if (entityCache.getResult(
					FriendlyURLEntryImpl.class,
					friendlyURLEntry.getPrimaryKey()) == null) {

				cacheResult(friendlyURLEntry);
			}
		}
	}

	/**
	 * Clears the cache for all friendly url entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FriendlyURLEntryImpl.class);

		finderCache.clearCache(FriendlyURLEntryImpl.class);
	}

	/**
	 * Clears the cache for the friendly url entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FriendlyURLEntry friendlyURLEntry) {
		entityCache.removeResult(FriendlyURLEntryImpl.class, friendlyURLEntry);
	}

	@Override
	public void clearCache(List<FriendlyURLEntry> friendlyURLEntries) {
		for (FriendlyURLEntry friendlyURLEntry : friendlyURLEntries) {
			entityCache.removeResult(
				FriendlyURLEntryImpl.class, friendlyURLEntry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FriendlyURLEntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FriendlyURLEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FriendlyURLEntryModelImpl friendlyURLEntryModelImpl) {

		Object[] args = new Object[] {
			friendlyURLEntryModelImpl.getUuid(),
			friendlyURLEntryModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args, friendlyURLEntryModelImpl);
	}

	/**
	 * Creates a new friendly url entry with the primary key. Does not add the friendly url entry to the database.
	 *
	 * @param friendlyURLEntryId the primary key for the new friendly url entry
	 * @return the new friendly url entry
	 */
	@Override
	public FriendlyURLEntry create(long friendlyURLEntryId) {
		FriendlyURLEntry friendlyURLEntry = new FriendlyURLEntryImpl();

		friendlyURLEntry.setNew(true);
		friendlyURLEntry.setPrimaryKey(friendlyURLEntryId);

		String uuid = PortalUUIDUtil.generate();

		friendlyURLEntry.setUuid(uuid);

		friendlyURLEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return friendlyURLEntry;
	}

	/**
	 * Removes the friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntryId the primary key of the friendly url entry
	 * @return the friendly url entry that was removed
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry remove(long friendlyURLEntryId)
		throws NoSuchFriendlyURLEntryException {

		return remove((Serializable)friendlyURLEntryId);
	}

	/**
	 * Removes the friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the friendly url entry
	 * @return the friendly url entry that was removed
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry remove(Serializable primaryKey)
		throws NoSuchFriendlyURLEntryException {

		Session session = null;

		try {
			session = openSession();

			FriendlyURLEntry friendlyURLEntry = (FriendlyURLEntry)session.get(
				FriendlyURLEntryImpl.class, primaryKey);

			if (friendlyURLEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFriendlyURLEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(friendlyURLEntry);
		}
		catch (NoSuchFriendlyURLEntryException noSuchEntityException) {
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
	protected FriendlyURLEntry removeImpl(FriendlyURLEntry friendlyURLEntry) {
		friendlyURLEntryLocalizationPersistence.removeByFriendlyURLEntryId(
			friendlyURLEntry.getFriendlyURLEntryId());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(friendlyURLEntry)) {
				friendlyURLEntry = (FriendlyURLEntry)session.get(
					FriendlyURLEntryImpl.class,
					friendlyURLEntry.getPrimaryKeyObj());
			}

			if (friendlyURLEntry != null) {
				session.delete(friendlyURLEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (friendlyURLEntry != null) {
			clearCache(friendlyURLEntry);
		}

		return friendlyURLEntry;
	}

	@Override
	public FriendlyURLEntry updateImpl(FriendlyURLEntry friendlyURLEntry) {
		boolean isNew = friendlyURLEntry.isNew();

		if (!(friendlyURLEntry instanceof FriendlyURLEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(friendlyURLEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					friendlyURLEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in friendlyURLEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FriendlyURLEntry implementation " +
					friendlyURLEntry.getClass());
		}

		FriendlyURLEntryModelImpl friendlyURLEntryModelImpl =
			(FriendlyURLEntryModelImpl)friendlyURLEntry;

		if (Validator.isNull(friendlyURLEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			friendlyURLEntry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (friendlyURLEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				friendlyURLEntry.setCreateDate(date);
			}
			else {
				friendlyURLEntry.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!friendlyURLEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				friendlyURLEntry.setModifiedDate(date);
			}
			else {
				friendlyURLEntry.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(friendlyURLEntry);
			}
			else {
				friendlyURLEntry = (FriendlyURLEntry)session.merge(
					friendlyURLEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FriendlyURLEntryImpl.class, friendlyURLEntryModelImpl, false, true);

		cacheUniqueFindersCache(friendlyURLEntryModelImpl);

		if (isNew) {
			friendlyURLEntry.setNew(false);
		}

		friendlyURLEntry.resetOriginalValues();

		return friendlyURLEntry;
	}

	/**
	 * Returns the friendly url entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the friendly url entry
	 * @return the friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFriendlyURLEntryException {

		FriendlyURLEntry friendlyURLEntry = fetchByPrimaryKey(primaryKey);

		if (friendlyURLEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFriendlyURLEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return friendlyURLEntry;
	}

	/**
	 * Returns the friendly url entry with the primary key or throws a <code>NoSuchFriendlyURLEntryException</code> if it could not be found.
	 *
	 * @param friendlyURLEntryId the primary key of the friendly url entry
	 * @return the friendly url entry
	 * @throws NoSuchFriendlyURLEntryException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry findByPrimaryKey(long friendlyURLEntryId)
		throws NoSuchFriendlyURLEntryException {

		return findByPrimaryKey((Serializable)friendlyURLEntryId);
	}

	/**
	 * Returns the friendly url entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param friendlyURLEntryId the primary key of the friendly url entry
	 * @return the friendly url entry, or <code>null</code> if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry fetchByPrimaryKey(long friendlyURLEntryId) {
		return fetchByPrimaryKey((Serializable)friendlyURLEntryId);
	}

	/**
	 * Returns all the friendly url entries.
	 *
	 * @return the friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @return the range of friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findAll(
		int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> findAll(
		int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator,
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

		List<FriendlyURLEntry> list = null;

		if (useFinderCache) {
			list = (List<FriendlyURLEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FRIENDLYURLENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FRIENDLYURLENTRY;

				sql = sql.concat(FriendlyURLEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FriendlyURLEntry>)QueryUtil.list(
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

	/**
	 * Removes all the friendly url entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FriendlyURLEntry friendlyURLEntry : findAll()) {
			remove(friendlyURLEntry);
		}
	}

	/**
	 * Returns the number of friendly url entries.
	 *
	 * @return the number of friendly url entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FRIENDLYURLENTRY);

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
		return "friendlyURLEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FRIENDLYURLENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FriendlyURLEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the friendly url entry persistence.
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

		_finderPathWithPaginationFindByG_C_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "classNameId", "classPK"}, true);

		_finderPathWithoutPaginationFindByG_C_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"groupId", "classNameId", "classPK"}, true);

		_finderPathCountByG_C_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"groupId", "classNameId", "classPK"}, false);

		FriendlyURLEntryUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FriendlyURLEntryUtil.setPersistence(null);

		entityCache.removeCache(FriendlyURLEntryImpl.class.getName());
	}

	@Override
	@Reference(
		target = FURLPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = FURLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = FURLPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	@Reference
	protected FriendlyURLEntryLocalizationPersistence
		friendlyURLEntryLocalizationPersistence;

	private static final String _SQL_SELECT_FRIENDLYURLENTRY =
		"SELECT friendlyURLEntry FROM FriendlyURLEntry friendlyURLEntry";

	private static final String _SQL_SELECT_FRIENDLYURLENTRY_WHERE =
		"SELECT friendlyURLEntry FROM FriendlyURLEntry friendlyURLEntry WHERE ";

	private static final String _SQL_COUNT_FRIENDLYURLENTRY =
		"SELECT COUNT(friendlyURLEntry) FROM FriendlyURLEntry friendlyURLEntry";

	private static final String _SQL_COUNT_FRIENDLYURLENTRY_WHERE =
		"SELECT COUNT(friendlyURLEntry) FROM FriendlyURLEntry friendlyURLEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "friendlyURLEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FriendlyURLEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FriendlyURLEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FriendlyURLEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}