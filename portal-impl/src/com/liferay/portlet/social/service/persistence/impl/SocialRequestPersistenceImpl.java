/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.service.persistence.impl;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.change.tracking.CTColumnResolutionType;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.change.tracking.helper.CTPersistenceHelperUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portlet.social.model.impl.SocialRequestImpl;
import com.liferay.portlet.social.model.impl.SocialRequestModelImpl;
import com.liferay.social.kernel.exception.NoSuchRequestException;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.model.SocialRequestTable;
import com.liferay.social.kernel.service.persistence.SocialRequestPersistence;
import com.liferay.social.kernel.service.persistence.SocialRequestUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the social request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SocialRequestPersistenceImpl
	extends BasePersistenceImpl<SocialRequest>
	implements SocialRequestPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SocialRequestUtil</code> to access the social request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SocialRequestImpl.class.getName();

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
	 * Returns all the social requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if (!uuid.equals(socialRequest.getUuid())) {
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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUuid_First(
			String uuid, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUuid_First(
			uuid, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUuid_First(
		String uuid, OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUuid_Last(
			String uuid, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUuid_Last(uuid, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUuid_Last(
		String uuid, OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where uuid = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByUuid_PrevAndNext(
			long requestId, String uuid,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		uuid = Objects.toString(uuid, "");

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, socialRequest, uuid, orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByUuid_PrevAndNext(
				session, socialRequest, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialRequest getByUuid_PrevAndNext(
		Session session, SocialRequest socialRequest, String uuid,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SocialRequest socialRequest :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching social requests
	 */
	@Override
	public int countByUuid(String uuid) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = _finderPathCountByUuid;

			Object[] finderArgs = new Object[] {uuid};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(2);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

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
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"socialRequest.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(socialRequest.uuid IS NULL OR socialRequest.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the social request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUUID_G(String uuid, long groupId)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUUID_G(uuid, groupId);

		if (socialRequest == null) {
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

			throw new NoSuchRequestException(sb.toString());
		}

		return socialRequest;
	}

	/**
	 * Returns the social request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the social request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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

			if (result instanceof SocialRequest) {
				SocialRequest socialRequest = (SocialRequest)result;

				if (!Objects.equals(uuid, socialRequest.getUuid()) ||
					(groupId != socialRequest.getGroupId())) {

					result = null;
				}
			}

			if (result == null) {
				StringBundler sb = new StringBundler(4);

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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

					List<SocialRequest> list = query.list();

					if (list.isEmpty()) {
						if (useFinderCache) {
							FinderCacheUtil.putResult(
								_finderPathFetchByUUID_G, finderArgs, list);
						}
					}
					else {
						SocialRequest socialRequest = list.get(0);

						result = socialRequest;

						cacheResult(socialRequest);
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
				return (SocialRequest)result;
			}
		}
	}

	/**
	 * Removes the social request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the social request that was removed
	 */
	@Override
	public SocialRequest removeByUUID_G(String uuid, long groupId)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByUUID_G(uuid, groupId);

		return remove(socialRequest);
	}

	/**
	 * Returns the number of social requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		SocialRequest socialRequest = fetchByUUID_G(uuid, groupId);

		if (socialRequest == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"socialRequest.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(socialRequest.uuid IS NULL OR socialRequest.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"socialRequest.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the social requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if (!uuid.equals(socialRequest.getUuid()) ||
							(companyId != socialRequest.getCompanyId())) {

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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByUuid_C_PrevAndNext(
			long requestId, String uuid, long companyId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		uuid = Objects.toString(uuid, "");

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, socialRequest, uuid, companyId, orderByComparator,
				true);

			array[1] = socialRequest;

			array[2] = getByUuid_C_PrevAndNext(
				session, socialRequest, uuid, companyId, orderByComparator,
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

	protected SocialRequest getByUuid_C_PrevAndNext(
		Session session, SocialRequest socialRequest, String uuid,
		long companyId, OrderByComparator<SocialRequest> orderByComparator,
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

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SocialRequest socialRequest :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			uuid = Objects.toString(uuid, "");

			FinderPath finderPath = _finderPathCountByUuid_C;

			Object[] finderArgs = new Object[] {uuid, companyId};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

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
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"socialRequest.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(socialRequest.uuid IS NULL OR socialRequest.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"socialRequest.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the social requests where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if (companyId != socialRequest.getCompanyId()) {
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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(companyId);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByCompanyId_First(
			long companyId, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByCompanyId_First(
		long companyId, OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByCompanyId_Last(
			long companyId, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByCompanyId_Last(
		long companyId, OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where companyId = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByCompanyId_PrevAndNext(
			long requestId, long companyId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, socialRequest, companyId, orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByCompanyId_PrevAndNext(
				session, socialRequest, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialRequest getByCompanyId_PrevAndNext(
		Session session, SocialRequest socialRequest, long companyId,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (SocialRequest socialRequest :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByCompanyId(long companyId) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByCompanyId;

			Object[] finderArgs = new Object[] {companyId};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(2);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

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
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"socialRequest.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the social requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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
				finderArgs = new Object[] {
					userId, start, end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if (userId != socialRequest.getUserId()) {
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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_USERID_USERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUserId_First(
			long userId, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUserId_First(
			userId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUserId_First(
		long userId, OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByUserId_Last(
			long userId, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByUserId_Last(
			userId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByUserId_Last(
		long userId, OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where userId = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByUserId_PrevAndNext(
			long requestId, long userId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, socialRequest, userId, orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByUserId_PrevAndNext(
				session, socialRequest, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialRequest getByUserId_PrevAndNext(
		Session session, SocialRequest socialRequest, long userId,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
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
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SocialRequest socialRequest :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByUserId(long userId) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByUserId;

			Object[] finderArgs = new Object[] {userId};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(2);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

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
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"socialRequest.userId = ?";

	private FinderPath _finderPathWithPaginationFindByReceiverUserId;
	private FinderPath _finderPathWithoutPaginationFindByReceiverUserId;
	private FinderPath _finderPathCountByReceiverUserId;

	/**
	 * Returns all the social requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByReceiverUserId(long receiverUserId) {
		return findByReceiverUserId(
			receiverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByReceiverUserId(
		long receiverUserId, int start, int end) {

		return findByReceiverUserId(receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByReceiverUserId(
			receiverUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath =
						_finderPathWithoutPaginationFindByReceiverUserId;
					finderArgs = new Object[] {receiverUserId};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByReceiverUserId;
				finderArgs = new Object[] {
					receiverUserId, start, end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if (receiverUserId !=
								socialRequest.getReceiverUserId()) {

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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(receiverUserId);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByReceiverUserId_First(
			long receiverUserId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByReceiverUserId_First(
			receiverUserId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("receiverUserId=");
		sb.append(receiverUserId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByReceiverUserId_First(
		long receiverUserId,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByReceiverUserId(
			receiverUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByReceiverUserId_Last(
			long receiverUserId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByReceiverUserId_Last(
			receiverUserId, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("receiverUserId=");
		sb.append(receiverUserId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByReceiverUserId_Last(
		long receiverUserId,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByReceiverUserId(receiverUserId);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByReceiverUserId(
			receiverUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByReceiverUserId_PrevAndNext(
			long requestId, long receiverUserId,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByReceiverUserId_PrevAndNext(
				session, socialRequest, receiverUserId, orderByComparator,
				true);

			array[1] = socialRequest;

			array[2] = getByReceiverUserId_PrevAndNext(
				session, socialRequest, receiverUserId, orderByComparator,
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

	protected SocialRequest getByReceiverUserId_PrevAndNext(
		Session session, SocialRequest socialRequest, long receiverUserId,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(receiverUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where receiverUserId = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 */
	@Override
	public void removeByReceiverUserId(long receiverUserId) {
		for (SocialRequest socialRequest :
				findByReceiverUserId(
					receiverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByReceiverUserId(long receiverUserId) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByReceiverUserId;

			Object[] finderArgs = new Object[] {receiverUserId};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(2);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(receiverUserId);

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
	}

	private static final String _FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2 =
		"socialRequest.receiverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByU_S;
	private FinderPath _finderPathWithoutPaginationFindByU_S;
	private FinderPath _finderPathCountByU_S;

	/**
	 * Returns all the social requests where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_S(long userId, int status) {
		return findByU_S(
			userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_S(
		long userId, int status, int start, int end) {

		return findByU_S(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByU_S(userId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_S(
		long userId, int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByU_S;
					finderArgs = new Object[] {userId, status};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByU_S;
				finderArgs = new Object[] {
					userId, status, start, end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if ((userId != socialRequest.getUserId()) ||
							(status != socialRequest.getStatus())) {

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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_U_S_USERID_2);

				sb.append(_FINDER_COLUMN_U_S_STATUS_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					queryPos.add(status);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByU_S_First(
			long userId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByU_S_First(
			userId, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_S_First(
		long userId, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByU_S(
			userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByU_S_Last(
			long userId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByU_S_Last(
			userId, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_S_Last(
		long userId, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByU_S(userId, status);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByU_S(
			userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByU_S_PrevAndNext(
			long requestId, long userId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByU_S_PrevAndNext(
				session, socialRequest, userId, status, orderByComparator,
				true);

			array[1] = socialRequest;

			array[2] = getByU_S_PrevAndNext(
				session, socialRequest, userId, status, orderByComparator,
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

	protected SocialRequest getByU_S_PrevAndNext(
		Session session, SocialRequest socialRequest, long userId, int status,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_U_S_USERID_2);

		sb.append(_FINDER_COLUMN_U_S_STATUS_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByU_S(long userId, int status) {
		for (SocialRequest socialRequest :
				findByU_S(
					userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching social requests
	 */
	@Override
	public int countByU_S(long userId, int status) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByU_S;

			Object[] finderArgs = new Object[] {userId, status};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_U_S_USERID_2);

				sb.append(_FINDER_COLUMN_U_S_STATUS_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					queryPos.add(status);

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
	}

	private static final String _FINDER_COLUMN_U_S_USERID_2 =
		"socialRequest.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_S_STATUS_2 =
		"socialRequest.status = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the social requests where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByC_C;
					finderArgs = new Object[] {classNameId, classPK};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByC_C;
				finderArgs = new Object[] {
					classNameId, classPK, start, end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if ((classNameId != socialRequest.getClassNameId()) ||
							(classPK != socialRequest.getClassPK())) {

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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByC_C_PrevAndNext(
			long requestId, long classNameId, long classPK,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, socialRequest, classNameId, classPK, orderByComparator,
				true);

			array[1] = socialRequest;

			array[2] = getByC_C_PrevAndNext(
				session, socialRequest, classNameId, classPK, orderByComparator,
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

	protected SocialRequest getByC_C_PrevAndNext(
		Session session, SocialRequest socialRequest, long classNameId,
		long classPK, OrderByComparator<SocialRequest> orderByComparator,
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

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (SocialRequest socialRequest :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching social requests
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByC_C;

			Object[] finderArgs = new Object[] {classNameId, classPK};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(classNameId);

					queryPos.add(classPK);

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
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"socialRequest.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"socialRequest.classPK = ?";

	private FinderPath _finderPathWithPaginationFindByR_S;
	private FinderPath _finderPathWithoutPaginationFindByR_S;
	private FinderPath _finderPathCountByR_S;

	/**
	 * Returns all the social requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByR_S(long receiverUserId, int status) {
		return findByR_S(
			receiverUserId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByR_S(
		long receiverUserId, int status, int start, int end) {

		return findByR_S(receiverUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByR_S(
			receiverUserId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByR_S;
					finderArgs = new Object[] {receiverUserId, status};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByR_S;
				finderArgs = new Object[] {
					receiverUserId, status, start, end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if ((receiverUserId !=
								socialRequest.getReceiverUserId()) ||
							(status != socialRequest.getStatus())) {

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

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

				sb.append(_FINDER_COLUMN_R_S_STATUS_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(receiverUserId);

					queryPos.add(status);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByR_S_First(
			long receiverUserId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByR_S_First(
			receiverUserId, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("receiverUserId=");
		sb.append(receiverUserId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByR_S_First(
		long receiverUserId, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByR_S(
			receiverUserId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByR_S_Last(
			long receiverUserId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByR_S_Last(
			receiverUserId, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("receiverUserId=");
		sb.append(receiverUserId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByR_S_Last(
		long receiverUserId, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByR_S(receiverUserId, status);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByR_S(
			receiverUserId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByR_S_PrevAndNext(
			long requestId, long receiverUserId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByR_S_PrevAndNext(
				session, socialRequest, receiverUserId, status,
				orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByR_S_PrevAndNext(
				session, socialRequest, receiverUserId, status,
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

	protected SocialRequest getByR_S_PrevAndNext(
		Session session, SocialRequest socialRequest, long receiverUserId,
		int status, OrderByComparator<SocialRequest> orderByComparator,
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

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

		sb.append(_FINDER_COLUMN_R_S_STATUS_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(receiverUserId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 */
	@Override
	public void removeByR_S(long receiverUserId, int status) {
		for (SocialRequest socialRequest :
				findByR_S(
					receiverUserId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching social requests
	 */
	@Override
	public int countByR_S(long receiverUserId, int status) {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByR_S;

			Object[] finderArgs = new Object[] {receiverUserId, status};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(3);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

				sb.append(_FINDER_COLUMN_R_S_STATUS_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(receiverUserId);

					queryPos.add(status);

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
	}

	private static final String _FINDER_COLUMN_R_S_RECEIVERUSERID_2 =
		"socialRequest.receiverUserId = ? AND ";

	private static final String _FINDER_COLUMN_R_S_STATUS_2 =
		"socialRequest.status = ?";

	private FinderPath _finderPathFetchByU_C_C_T_R;

	/**
	 * Returns the social request where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByU_C_C_T_R(
			long userId, long classNameId, long classPK, int type,
			long receiverUserId)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByU_C_C_T_R(
			userId, classNameId, classPK, type, receiverUserId);

		if (socialRequest == null) {
			StringBundler sb = new StringBundler(12);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append(", classNameId=");
			sb.append(classNameId);

			sb.append(", classPK=");
			sb.append(classPK);

			sb.append(", type=");
			sb.append(type);

			sb.append(", receiverUserId=");
			sb.append(receiverUserId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRequestException(sb.toString());
		}

		return socialRequest;
	}

	/**
	 * Returns the social request where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_C_C_T_R(
		long userId, long classNameId, long classPK, int type,
		long receiverUserId) {

		return fetchByU_C_C_T_R(
			userId, classNameId, classPK, type, receiverUserId, true);
	}

	/**
	 * Returns the social request where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_C_C_T_R(
		long userId, long classNameId, long classPK, int type,
		long receiverUserId, boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			Object[] finderArgs = null;

			if (useFinderCache) {
				finderArgs = new Object[] {
					userId, classNameId, classPK, type, receiverUserId
				};
			}

			Object result = null;

			if (useFinderCache) {
				result = FinderCacheUtil.getResult(
					_finderPathFetchByU_C_C_T_R, finderArgs, this);
			}

			if (result instanceof SocialRequest) {
				SocialRequest socialRequest = (SocialRequest)result;

				if ((userId != socialRequest.getUserId()) ||
					(classNameId != socialRequest.getClassNameId()) ||
					(classPK != socialRequest.getClassPK()) ||
					(type != socialRequest.getType()) ||
					(receiverUserId != socialRequest.getReceiverUserId())) {

					result = null;
				}
			}

			if (result == null) {
				StringBundler sb = new StringBundler(7);

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_U_C_C_T_R_USERID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_R_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_R_CLASSPK_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_R_TYPE_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_R_RECEIVERUSERID_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					queryPos.add(type);

					queryPos.add(receiverUserId);

					List<SocialRequest> list = query.list();

					if (list.isEmpty()) {
						if (useFinderCache) {
							FinderCacheUtil.putResult(
								_finderPathFetchByU_C_C_T_R, finderArgs, list);
						}
					}
					else {
						SocialRequest socialRequest = list.get(0);

						result = socialRequest;

						cacheResult(socialRequest);
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
				return (SocialRequest)result;
			}
		}
	}

	/**
	 * Removes the social request where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the social request that was removed
	 */
	@Override
	public SocialRequest removeByU_C_C_T_R(
			long userId, long classNameId, long classPK, int type,
			long receiverUserId)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByU_C_C_T_R(
			userId, classNameId, classPK, type, receiverUserId);

		return remove(socialRequest);
	}

	/**
	 * Returns the number of social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching social requests
	 */
	@Override
	public int countByU_C_C_T_R(
		long userId, long classNameId, long classPK, int type,
		long receiverUserId) {

		SocialRequest socialRequest = fetchByU_C_C_T_R(
			userId, classNameId, classPK, type, receiverUserId);

		if (socialRequest == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_U_C_C_T_R_USERID_2 =
		"socialRequest.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_R_CLASSNAMEID_2 =
		"socialRequest.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_R_CLASSPK_2 =
		"socialRequest.classPK = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_R_TYPE_2 =
		"socialRequest.type = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_R_RECEIVERUSERID_2 =
		"socialRequest.receiverUserId = ?";

	private FinderPath _finderPathWithPaginationFindByU_C_C_T_S;
	private FinderPath _finderPathWithoutPaginationFindByU_C_C_T_S;
	private FinderPath _finderPathCountByU_C_C_T_S;

	/**
	 * Returns all the social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status) {

		return findByU_C_C_T_S(
			userId, classNameId, classPK, type, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status,
		int start, int end) {

		return findByU_C_C_T_S(
			userId, classNameId, classPK, type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status,
		int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByU_C_C_T_S(
			userId, classNameId, classPK, type, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status,
		int start, int end, OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByU_C_C_T_S;
					finderArgs = new Object[] {
						userId, classNameId, classPK, type, status
					};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByU_C_C_T_S;
				finderArgs = new Object[] {
					userId, classNameId, classPK, type, status, start, end,
					orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if ((userId != socialRequest.getUserId()) ||
							(classNameId != socialRequest.getClassNameId()) ||
							(classPK != socialRequest.getClassPK()) ||
							(type != socialRequest.getType()) ||
							(status != socialRequest.getStatus())) {

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
						7 + (orderByComparator.getOrderByFields().length * 2));
				}
				else {
					sb = new StringBundler(7);
				}

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_USERID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSPK_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_TYPE_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_STATUS_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					queryPos.add(type);

					queryPos.add(status);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByU_C_C_T_S_First(
			long userId, long classNameId, long classPK, int type, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByU_C_C_T_S_First(
			userId, classNameId, classPK, type, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", type=");
		sb.append(type);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_C_C_T_S_First(
		long userId, long classNameId, long classPK, int type, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByU_C_C_T_S(
			userId, classNameId, classPK, type, status, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByU_C_C_T_S_Last(
			long userId, long classNameId, long classPK, int type, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByU_C_C_T_S_Last(
			userId, classNameId, classPK, type, status, orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", type=");
		sb.append(type);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByU_C_C_T_S_Last(
		long userId, long classNameId, long classPK, int type, int status,
		OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByU_C_C_T_S(
			userId, classNameId, classPK, type, status);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByU_C_C_T_S(
			userId, classNameId, classPK, type, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByU_C_C_T_S_PrevAndNext(
			long requestId, long userId, long classNameId, long classPK,
			int type, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByU_C_C_T_S_PrevAndNext(
				session, socialRequest, userId, classNameId, classPK, type,
				status, orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByU_C_C_T_S_PrevAndNext(
				session, socialRequest, userId, classNameId, classPK, type,
				status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialRequest getByU_C_C_T_S_PrevAndNext(
		Session session, SocialRequest socialRequest, long userId,
		long classNameId, long classPK, int type, int status,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				8 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(7);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_U_C_C_T_S_USERID_2);

		sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSPK_2);

		sb.append(_FINDER_COLUMN_U_C_C_T_S_TYPE_2);

		sb.append(_FINDER_COLUMN_U_C_C_T_S_STATUS_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		queryPos.add(type);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 */
	@Override
	public void removeByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status) {

		for (SocialRequest socialRequest :
				findByU_C_C_T_S(
					userId, classNameId, classPK, type, status,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param status the status
	 * @return the number of matching social requests
	 */
	@Override
	public int countByU_C_C_T_S(
		long userId, long classNameId, long classPK, int type, int status) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByU_C_C_T_S;

			Object[] finderArgs = new Object[] {
				userId, classNameId, classPK, type, status
			};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(6);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_USERID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_CLASSPK_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_TYPE_2);

				sb.append(_FINDER_COLUMN_U_C_C_T_S_STATUS_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(userId);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					queryPos.add(type);

					queryPos.add(status);

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
	}

	private static final String _FINDER_COLUMN_U_C_C_T_S_USERID_2 =
		"socialRequest.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_S_CLASSNAMEID_2 =
		"socialRequest.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_S_CLASSPK_2 =
		"socialRequest.classPK = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_S_TYPE_2 =
		"socialRequest.type = ? AND ";

	private static final String _FINDER_COLUMN_U_C_C_T_S_STATUS_2 =
		"socialRequest.status = ?";

	private FinderPath _finderPathWithPaginationFindByC_C_T_R_S;
	private FinderPath _finderPathWithoutPaginationFindByC_C_T_R_S;
	private FinderPath _finderPathCountByC_C_T_R_S;

	/**
	 * Returns all the social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status) {

		return findByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status, int start, int end) {

		return findByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching social requests
	 */
	@Override
	public List<SocialRequest> findByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = null;
			Object[] finderArgs = null;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {

				if (useFinderCache) {
					finderPath = _finderPathWithoutPaginationFindByC_C_T_R_S;
					finderArgs = new Object[] {
						classNameId, classPK, type, receiverUserId, status
					};
				}
			}
			else if (useFinderCache) {
				finderPath = _finderPathWithPaginationFindByC_C_T_R_S;
				finderArgs = new Object[] {
					classNameId, classPK, type, receiverUserId, status, start,
					end, orderByComparator
				};
			}

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);

				if ((list != null) && !list.isEmpty()) {
					for (SocialRequest socialRequest : list) {
						if ((classNameId != socialRequest.getClassNameId()) ||
							(classPK != socialRequest.getClassPK()) ||
							(type != socialRequest.getType()) ||
							(receiverUserId !=
								socialRequest.getReceiverUserId()) ||
							(status != socialRequest.getStatus())) {

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
						7 + (orderByComparator.getOrderByFields().length * 2));
				}
				else {
					sb = new StringBundler(7);
				}

				sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSPK_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_TYPE_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_RECEIVERUSERID_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_STATUS_2);

				if (orderByComparator != null) {
					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
				}
				else {
					sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					queryPos.add(type);

					queryPos.add(receiverUserId);

					queryPos.add(status);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Returns the first social request in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByC_C_T_R_S_First(
			long classNameId, long classPK, int type, long receiverUserId,
			int status, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByC_C_T_R_S_First(
			classNameId, classPK, type, receiverUserId, status,
			orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", type=");
		sb.append(type);

		sb.append(", receiverUserId=");
		sb.append(receiverUserId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first social request in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByC_C_T_R_S_First(
		long classNameId, long classPK, int type, long receiverUserId,
		int status, OrderByComparator<SocialRequest> orderByComparator) {

		List<SocialRequest> list = findByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social request in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request
	 * @throws NoSuchRequestException if a matching social request could not be found
	 */
	@Override
	public SocialRequest findByC_C_T_R_S_Last(
			long classNameId, long classPK, int type, long receiverUserId,
			int status, OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByC_C_T_R_S_Last(
			classNameId, classPK, type, receiverUserId, status,
			orderByComparator);

		if (socialRequest != null) {
			return socialRequest;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", type=");
		sb.append(type);

		sb.append(", receiverUserId=");
		sb.append(receiverUserId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last social request in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchByC_C_T_R_S_Last(
		long classNameId, long classPK, int type, long receiverUserId,
		int status, OrderByComparator<SocialRequest> orderByComparator) {

		int count = countByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status);

		if (count == 0) {
			return null;
		}

		List<SocialRequest> list = findByC_C_T_R_S(
			classNameId, classPK, type, receiverUserId, status, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social requests before and after the current social request in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param requestId the primary key of the current social request
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest[] findByC_C_T_R_S_PrevAndNext(
			long requestId, long classNameId, long classPK, int type,
			long receiverUserId, int status,
			OrderByComparator<SocialRequest> orderByComparator)
		throws NoSuchRequestException {

		SocialRequest socialRequest = findByPrimaryKey(requestId);

		Session session = null;

		try {
			session = openSession();

			SocialRequest[] array = new SocialRequestImpl[3];

			array[0] = getByC_C_T_R_S_PrevAndNext(
				session, socialRequest, classNameId, classPK, type,
				receiverUserId, status, orderByComparator, true);

			array[1] = socialRequest;

			array[2] = getByC_C_T_R_S_PrevAndNext(
				session, socialRequest, classNameId, classPK, type,
				receiverUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialRequest getByC_C_T_R_S_PrevAndNext(
		Session session, SocialRequest socialRequest, long classNameId,
		long classPK, int type, long receiverUserId, int status,
		OrderByComparator<SocialRequest> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				8 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(7);
		}

		sb.append(_SQL_SELECT_SOCIALREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSPK_2);

		sb.append(_FINDER_COLUMN_C_C_T_R_S_TYPE_2);

		sb.append(_FINDER_COLUMN_C_C_T_R_S_RECEIVERUSERID_2);

		sb.append(_FINDER_COLUMN_C_C_T_R_S_STATUS_2);

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
			sb.append(SocialRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		queryPos.add(type);

		queryPos.add(receiverUserId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						socialRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SocialRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 */
	@Override
	public void removeByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status) {

		for (SocialRequest socialRequest :
				findByC_C_T_R_S(
					classNameId, classPK, type, receiverUserId, status,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching social requests
	 */
	@Override
	public int countByC_C_T_R_S(
		long classNameId, long classPK, int type, long receiverUserId,
		int status) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			FinderPath finderPath = _finderPathCountByC_C_T_R_S;

			Object[] finderArgs = new Object[] {
				classNameId, classPK, type, receiverUserId, status
			};

			Long count = (Long)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if (count == null) {
				StringBundler sb = new StringBundler(6);

				sb.append(_SQL_COUNT_SOCIALREQUEST_WHERE);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSNAMEID_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_CLASSPK_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_TYPE_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_RECEIVERUSERID_2);

				sb.append(_FINDER_COLUMN_C_C_T_R_S_STATUS_2);

				String sql = sb.toString();

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					QueryPos queryPos = QueryPos.getInstance(query);

					queryPos.add(classNameId);

					queryPos.add(classPK);

					queryPos.add(type);

					queryPos.add(receiverUserId);

					queryPos.add(status);

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
	}

	private static final String _FINDER_COLUMN_C_C_T_R_S_CLASSNAMEID_2 =
		"socialRequest.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_R_S_CLASSPK_2 =
		"socialRequest.classPK = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_R_S_TYPE_2 =
		"socialRequest.type = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_R_S_RECEIVERUSERID_2 =
		"socialRequest.receiverUserId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_R_S_STATUS_2 =
		"socialRequest.status = ?";

	public SocialRequestPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SocialRequest.class);

		setModelImplClass(SocialRequestImpl.class);
		setModelPKClass(long.class);

		setTable(SocialRequestTable.INSTANCE);
	}

	/**
	 * Caches the social request in the entity cache if it is enabled.
	 *
	 * @param socialRequest the social request
	 */
	@Override
	public void cacheResult(SocialRequest socialRequest) {
		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					socialRequest.getCtCollectionId())) {

			EntityCacheUtil.putResult(
				SocialRequestImpl.class, socialRequest.getPrimaryKey(),
				socialRequest);

			FinderCacheUtil.putResult(
				_finderPathFetchByUUID_G,
				new Object[] {
					socialRequest.getUuid(), socialRequest.getGroupId()
				},
				socialRequest);

			FinderCacheUtil.putResult(
				_finderPathFetchByU_C_C_T_R,
				new Object[] {
					socialRequest.getUserId(), socialRequest.getClassNameId(),
					socialRequest.getClassPK(), socialRequest.getType(),
					socialRequest.getReceiverUserId()
				},
				socialRequest);
		}
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the social requests in the entity cache if it is enabled.
	 *
	 * @param socialRequests the social requests
	 */
	@Override
	public void cacheResult(List<SocialRequest> socialRequests) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (socialRequests.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SocialRequest socialRequest : socialRequests) {
			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
						socialRequest.getCtCollectionId())) {

				if (EntityCacheUtil.getResult(
						SocialRequestImpl.class,
						socialRequest.getPrimaryKey()) == null) {

					cacheResult(socialRequest);
				}
			}
		}
	}

	/**
	 * Clears the cache for all social requests.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(SocialRequestImpl.class);

		FinderCacheUtil.clearCache(SocialRequestImpl.class);
	}

	/**
	 * Clears the cache for the social request.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SocialRequest socialRequest) {
		EntityCacheUtil.removeResult(SocialRequestImpl.class, socialRequest);
	}

	@Override
	public void clearCache(List<SocialRequest> socialRequests) {
		for (SocialRequest socialRequest : socialRequests) {
			EntityCacheUtil.removeResult(
				SocialRequestImpl.class, socialRequest);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(SocialRequestImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(SocialRequestImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SocialRequestModelImpl socialRequestModelImpl) {

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					socialRequestModelImpl.getCtCollectionId())) {

			Object[] args = new Object[] {
				socialRequestModelImpl.getUuid(),
				socialRequestModelImpl.getGroupId()
			};

			FinderCacheUtil.putResult(
				_finderPathFetchByUUID_G, args, socialRequestModelImpl);

			args = new Object[] {
				socialRequestModelImpl.getUserId(),
				socialRequestModelImpl.getClassNameId(),
				socialRequestModelImpl.getClassPK(),
				socialRequestModelImpl.getType(),
				socialRequestModelImpl.getReceiverUserId()
			};

			FinderCacheUtil.putResult(
				_finderPathFetchByU_C_C_T_R, args, socialRequestModelImpl);
		}
	}

	/**
	 * Creates a new social request with the primary key. Does not add the social request to the database.
	 *
	 * @param requestId the primary key for the new social request
	 * @return the new social request
	 */
	@Override
	public SocialRequest create(long requestId) {
		SocialRequest socialRequest = new SocialRequestImpl();

		socialRequest.setNew(true);
		socialRequest.setPrimaryKey(requestId);

		String uuid = PortalUUIDUtil.generate();

		socialRequest.setUuid(uuid);

		socialRequest.setCompanyId(CompanyThreadLocal.getCompanyId());

		return socialRequest;
	}

	/**
	 * Removes the social request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param requestId the primary key of the social request
	 * @return the social request that was removed
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest remove(long requestId) throws NoSuchRequestException {
		return remove((Serializable)requestId);
	}

	/**
	 * Removes the social request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the social request
	 * @return the social request that was removed
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest remove(Serializable primaryKey)
		throws NoSuchRequestException {

		Session session = null;

		try {
			session = openSession();

			SocialRequest socialRequest = (SocialRequest)session.get(
				SocialRequestImpl.class, primaryKey);

			if (socialRequest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRequestException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(socialRequest);
		}
		catch (NoSuchRequestException noSuchEntityException) {
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
	protected SocialRequest removeImpl(SocialRequest socialRequest) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(socialRequest)) {
				socialRequest = (SocialRequest)session.get(
					SocialRequestImpl.class, socialRequest.getPrimaryKeyObj());
			}

			if ((socialRequest != null) &&
				CTPersistenceHelperUtil.isRemove(socialRequest)) {

				session.delete(socialRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (socialRequest != null) {
			clearCache(socialRequest);
		}

		return socialRequest;
	}

	@Override
	public SocialRequest updateImpl(SocialRequest socialRequest) {
		boolean isNew = socialRequest.isNew();

		if (!(socialRequest instanceof SocialRequestModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(socialRequest.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					socialRequest);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in socialRequest proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SocialRequest implementation " +
					socialRequest.getClass());
		}

		SocialRequestModelImpl socialRequestModelImpl =
			(SocialRequestModelImpl)socialRequest;

		if (Validator.isNull(socialRequest.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			socialRequest.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (CTPersistenceHelperUtil.isInsert(socialRequest)) {
				if (!isNew) {
					session.evict(
						SocialRequestImpl.class,
						socialRequest.getPrimaryKeyObj());
				}

				session.save(socialRequest);
			}
			else {
				socialRequest = (SocialRequest)session.merge(socialRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			SocialRequestImpl.class, socialRequestModelImpl, false, true);

		cacheUniqueFindersCache(socialRequestModelImpl);

		if (isNew) {
			socialRequest.setNew(false);
		}

		socialRequest.resetOriginalValues();

		return socialRequest;
	}

	/**
	 * Returns the social request with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the social request
	 * @return the social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRequestException {

		SocialRequest socialRequest = fetchByPrimaryKey(primaryKey);

		if (socialRequest == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRequestException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return socialRequest;
	}

	/**
	 * Returns the social request with the primary key or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param requestId the primary key of the social request
	 * @return the social request
	 * @throws NoSuchRequestException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest findByPrimaryKey(long requestId)
		throws NoSuchRequestException {

		return findByPrimaryKey((Serializable)requestId);
	}

	/**
	 * Returns the social request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the social request
	 * @return the social request, or <code>null</code> if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest fetchByPrimaryKey(Serializable primaryKey) {
		if (CTPersistenceHelperUtil.isProductionMode(
				SocialRequest.class, primaryKey)) {

			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.
						setProductionModeWithSafeCloseable()) {

				return super.fetchByPrimaryKey(primaryKey);
			}
		}

		SocialRequest socialRequest = (SocialRequest)EntityCacheUtil.getResult(
			SocialRequestImpl.class, primaryKey);

		if (socialRequest != null) {
			return socialRequest;
		}

		Session session = null;

		try {
			session = openSession();

			socialRequest = (SocialRequest)session.get(
				SocialRequestImpl.class, primaryKey);

			if (socialRequest != null) {
				cacheResult(socialRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return socialRequest;
	}

	/**
	 * Returns the social request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param requestId the primary key of the social request
	 * @return the social request, or <code>null</code> if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest fetchByPrimaryKey(long requestId) {
		return fetchByPrimaryKey((Serializable)requestId);
	}

	@Override
	public Map<Serializable, SocialRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (CTPersistenceHelperUtil.isProductionMode(SocialRequest.class)) {
			try (SafeCloseable safeCloseable =
					CTCollectionThreadLocal.
						setProductionModeWithSafeCloseable()) {

				return super.fetchByPrimaryKeys(primaryKeys);
			}
		}

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SocialRequest> map =
			new HashMap<Serializable, SocialRequest>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SocialRequest socialRequest = fetchByPrimaryKey(primaryKey);

			if (socialRequest != null) {
				map.put(primaryKey, socialRequest);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			try (SafeCloseable safeCloseable =
					CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
						SocialRequest.class, primaryKey)) {

				SocialRequest socialRequest =
					(SocialRequest)EntityCacheUtil.getResult(
						SocialRequestImpl.class, primaryKey);

				if (socialRequest == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, socialRequest);
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

			for (SocialRequest socialRequest :
					(List<SocialRequest>)query.list()) {

				map.put(socialRequest.getPrimaryKeyObj(), socialRequest);

				cacheResult(socialRequest);
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
	 * Returns all the social requests.
	 *
	 * @return the social requests
	 */
	@Override
	public List<SocialRequest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of social requests
	 */
	@Override
	public List<SocialRequest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the social requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of social requests
	 */
	@Override
	public List<SocialRequest> findAll(
		int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the social requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of social requests
	 */
	@Override
	public List<SocialRequest> findAll(
		int start, int end, OrderByComparator<SocialRequest> orderByComparator,
		boolean useFinderCache) {

		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

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

			List<SocialRequest> list = null;

			if (useFinderCache) {
				list = (List<SocialRequest>)FinderCacheUtil.getResult(
					finderPath, finderArgs, this);
			}

			if (list == null) {
				StringBundler sb = null;
				String sql = null;

				if (orderByComparator != null) {
					sb = new StringBundler(
						2 + (orderByComparator.getOrderByFields().length * 2));

					sb.append(_SQL_SELECT_SOCIALREQUEST);

					appendOrderByComparator(
						sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

					sql = sb.toString();
				}
				else {
					sql = _SQL_SELECT_SOCIALREQUEST;

					sql = sql.concat(SocialRequestModelImpl.ORDER_BY_JPQL);
				}

				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(sql);

					list = (List<SocialRequest>)QueryUtil.list(
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
	}

	/**
	 * Removes all the social requests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SocialRequest socialRequest : findAll()) {
			remove(socialRequest);
		}
	}

	/**
	 * Returns the number of social requests.
	 *
	 * @return the number of social requests
	 */
	@Override
	public int countAll() {
		try (SafeCloseable safeCloseable =
				CTPersistenceHelperUtil.setCTCollectionIdWithSafeCloseable(
					SocialRequest.class)) {

			Long count = (Long)FinderCacheUtil.getResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY, this);

			if (count == null) {
				Session session = null;

				try {
					session = openSession();

					Query query = session.createQuery(_SQL_COUNT_SOCIALREQUEST);

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
		return "requestId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SOCIALREQUEST;
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
		return SocialRequestModelImpl.TABLE_COLUMNS_MAP;
	}

	@Override
	public String getTableName() {
		return "SocialRequest";
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
		Set<String> ctMergeColumnNames = new HashSet<String>();
		Set<String> ctStrictColumnNames = new HashSet<String>();

		ctControlColumnNames.add("mvccVersion");
		ctControlColumnNames.add("ctCollectionId");
		ctStrictColumnNames.add("uuid_");
		ctStrictColumnNames.add("groupId");
		ctStrictColumnNames.add("companyId");
		ctStrictColumnNames.add("userId");
		ctStrictColumnNames.add("createDate");
		ctMergeColumnNames.add("modifiedDate");
		ctStrictColumnNames.add("classNameId");
		ctStrictColumnNames.add("classPK");
		ctMergeColumnNames.add("type_");
		ctMergeColumnNames.add("extraData");
		ctMergeColumnNames.add("receiverUserId");
		ctMergeColumnNames.add("status");

		_ctColumnNamesMap.put(
			CTColumnResolutionType.CONTROL, ctControlColumnNames);
		_ctColumnNamesMap.put(CTColumnResolutionType.MERGE, ctMergeColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.PK, Collections.singleton("requestId"));
		_ctColumnNamesMap.put(
			CTColumnResolutionType.STRICT, ctStrictColumnNames);

		_uniqueIndexColumnNames.add(new String[] {"uuid_", "groupId"});

		_uniqueIndexColumnNames.add(
			new String[] {
				"userId", "classNameId", "classPK", "type_", "receiverUserId"
			});
	}

	/**
	 * Initializes the social request persistence.
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

		_finderPathWithPaginationFindByReceiverUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByReceiverUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"receiverUserId"}, true);

		_finderPathWithoutPaginationFindByReceiverUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByReceiverUserId",
			new String[] {Long.class.getName()},
			new String[] {"receiverUserId"}, true);

		_finderPathCountByReceiverUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReceiverUserId",
			new String[] {Long.class.getName()},
			new String[] {"receiverUserId"}, false);

		_finderPathWithPaginationFindByU_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "status"}, true);

		_finderPathWithoutPaginationFindByU_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"userId", "status"}, true);

		_finderPathCountByU_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"userId", "status"}, false);

		_finderPathWithPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathCountByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, false);

		_finderPathWithPaginationFindByR_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"receiverUserId", "status"}, true);

		_finderPathWithoutPaginationFindByR_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"receiverUserId", "status"}, true);

		_finderPathCountByR_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"receiverUserId", "status"}, false);

		_finderPathFetchByU_C_C_T_R = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByU_C_C_T_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			new String[] {
				"userId", "classNameId", "classPK", "type_", "receiverUserId"
			},
			true);

		_finderPathWithPaginationFindByU_C_C_T_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_C_C_T_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {
				"userId", "classNameId", "classPK", "type_", "status"
			},
			true);

		_finderPathWithoutPaginationFindByU_C_C_T_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C_C_T_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			new String[] {
				"userId", "classNameId", "classPK", "type_", "status"
			},
			true);

		_finderPathCountByU_C_C_T_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C_T_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			new String[] {
				"userId", "classNameId", "classPK", "type_", "status"
			},
			false);

		_finderPathWithPaginationFindByC_C_T_R_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_T_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {
				"classNameId", "classPK", "type_", "receiverUserId", "status"
			},
			true);

		_finderPathWithoutPaginationFindByC_C_T_R_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {
				"classNameId", "classPK", "type_", "receiverUserId", "status"
			},
			true);

		_finderPathCountByC_C_T_R_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {
				"classNameId", "classPK", "type_", "receiverUserId", "status"
			},
			false);

		SocialRequestUtil.setPersistence(this);
	}

	public void destroy() {
		SocialRequestUtil.setPersistence(null);

		EntityCacheUtil.removeCache(SocialRequestImpl.class.getName());
	}

	private static final String _SQL_SELECT_SOCIALREQUEST =
		"SELECT socialRequest FROM SocialRequest socialRequest";

	private static final String _SQL_SELECT_SOCIALREQUEST_WHERE =
		"SELECT socialRequest FROM SocialRequest socialRequest WHERE ";

	private static final String _SQL_COUNT_SOCIALREQUEST =
		"SELECT COUNT(socialRequest) FROM SocialRequest socialRequest";

	private static final String _SQL_COUNT_SOCIALREQUEST_WHERE =
		"SELECT COUNT(socialRequest) FROM SocialRequest socialRequest WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "socialRequest.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SocialRequest exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SocialRequest exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SocialRequestPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}