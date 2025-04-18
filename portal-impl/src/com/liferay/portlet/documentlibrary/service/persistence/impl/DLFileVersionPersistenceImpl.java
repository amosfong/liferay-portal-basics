/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.persistence.impl;

import com.liferay.document.library.kernel.exception.NoSuchFileVersionException;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFileVersionTable;
import com.liferay.document.library.kernel.service.persistence.DLFileVersionPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFileVersionUtil;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
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
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the document library file version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileVersionPersistenceImpl
	extends BasePersistenceImpl<DLFileVersion>
	implements DLFileVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DLFileVersionUtil</code> to access the document library file version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DLFileVersionImpl.class.getName();

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
	 * Returns all the document library file versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
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

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if (!uuid.equals(dlFileVersion.getUuid())) {
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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
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

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByUuid_First(
			String uuid, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByUuid_First(
			uuid, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUuid_First(
		String uuid, OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByUuid_Last(
			String uuid, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByUuid_Last(uuid, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUuid_Last(
		String uuid, OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where uuid = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByUuid_PrevAndNext(
			long fileVersionId, String uuid,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		uuid = Objects.toString(uuid, "");

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, dlFileVersion, uuid, orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByUuid_PrevAndNext(
				session, dlFileVersion, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileVersion getByUuid_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, String uuid,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
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
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DLFileVersion dlFileVersion :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching document library file versions
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

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

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
		"dlFileVersion.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(dlFileVersion.uuid IS NULL OR dlFileVersion.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the document library file version where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFileVersionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByUUID_G(String uuid, long groupId)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByUUID_G(uuid, groupId);

		if (dlFileVersion == null) {
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

			throw new NoSuchFileVersionException(sb.toString());
		}

		return dlFileVersion;
	}

	/**
	 * Returns the document library file version where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the document library file version where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUUID_G(
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

		if (result instanceof DLFileVersion) {
			DLFileVersion dlFileVersion = (DLFileVersion)result;

			if (!Objects.equals(uuid, dlFileVersion.getUuid()) ||
				(groupId != dlFileVersion.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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

				List<DLFileVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					DLFileVersion dlFileVersion = list.get(0);

					result = dlFileVersion;

					cacheResult(dlFileVersion);
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
			return (DLFileVersion)result;
		}
	}

	/**
	 * Removes the document library file version where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the document library file version that was removed
	 */
	@Override
	public DLFileVersion removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByUUID_G(uuid, groupId);

		return remove(dlFileVersion);
	}

	/**
	 * Returns the number of document library file versions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		DLFileVersion dlFileVersion = fetchByUUID_G(uuid, groupId);

		if (dlFileVersion == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"dlFileVersion.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(dlFileVersion.uuid IS NULL OR dlFileVersion.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"dlFileVersion.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the document library file versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
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

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if (!uuid.equals(dlFileVersion.getUuid()) ||
						(companyId != dlFileVersion.getCompanyId())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
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

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByUuid_C_PrevAndNext(
			long fileVersionId, String uuid, long companyId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		uuid = Objects.toString(uuid, "");

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, dlFileVersion, uuid, companyId, orderByComparator,
				true);

			array[1] = dlFileVersion;

			array[2] = getByUuid_C_PrevAndNext(
				session, dlFileVersion, uuid, companyId, orderByComparator,
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

	protected DLFileVersion getByUuid_C_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, String uuid,
		long companyId, OrderByComparator<DLFileVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
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
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DLFileVersion dlFileVersion :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching document library file versions
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

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

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
		"dlFileVersion.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(dlFileVersion.uuid IS NULL OR dlFileVersion.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"dlFileVersion.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the document library file versions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
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

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if (companyId != dlFileVersion.getCompanyId()) {
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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByCompanyId_First(
			long companyId, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByCompanyId_First(
		long companyId, OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByCompanyId_Last(
			long companyId, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByCompanyId_Last(
		long companyId, OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where companyId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByCompanyId_PrevAndNext(
			long fileVersionId, long companyId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, dlFileVersion, companyId, orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByCompanyId_PrevAndNext(
				session, dlFileVersion, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileVersion getByCompanyId_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long companyId,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
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
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (DLFileVersion dlFileVersion :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

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
		"dlFileVersion.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByFileEntryId;
	private FinderPath _finderPathWithoutPaginationFindByFileEntryId;
	private FinderPath _finderPathCountByFileEntryId;

	/**
	 * Returns all the document library file versions where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByFileEntryId(long fileEntryId) {
		return findByFileEntryId(
			fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByFileEntryId(
		long fileEntryId, int start, int end) {

		return findByFileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByFileEntryId(
			fileEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFileEntryId;
				finderArgs = new Object[] {fileEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFileEntryId;
			finderArgs = new Object[] {
				fileEntryId, start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if (fileEntryId != dlFileVersion.getFileEntryId()) {
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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByFileEntryId_First(
			long fileEntryId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByFileEntryId_First(
			fileEntryId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByFileEntryId_First(
		long fileEntryId, OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByFileEntryId(
			fileEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByFileEntryId_Last(
			long fileEntryId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByFileEntryId_Last(
			fileEntryId, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByFileEntryId_Last(
		long fileEntryId, OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByFileEntryId(fileEntryId);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByFileEntryId(
			fileEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByFileEntryId_PrevAndNext(
			long fileVersionId, long fileEntryId,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByFileEntryId_PrevAndNext(
				session, dlFileVersion, fileEntryId, orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByFileEntryId_PrevAndNext(
				session, dlFileVersion, fileEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileVersion getByFileEntryId_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long fileEntryId,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fileEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	@Override
	public void removeByFileEntryId(long fileEntryId) {
		for (DLFileVersion dlFileVersion :
				findByFileEntryId(
					fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByFileEntryId(long fileEntryId) {
		FinderPath finderPath = _finderPathCountByFileEntryId;

		Object[] finderArgs = new Object[] {fileEntryId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

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

	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 =
		"dlFileVersion.fileEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByMimeType;
	private FinderPath _finderPathWithoutPaginationFindByMimeType;
	private FinderPath _finderPathCountByMimeType;

	/**
	 * Returns all the document library file versions where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByMimeType(String mimeType) {
		return findByMimeType(
			mimeType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where mimeType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param mimeType the mime type
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByMimeType(
		String mimeType, int start, int end) {

		return findByMimeType(mimeType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where mimeType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param mimeType the mime type
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByMimeType(
		String mimeType, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByMimeType(mimeType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where mimeType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param mimeType the mime type
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByMimeType(
		String mimeType, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		mimeType = Objects.toString(mimeType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByMimeType;
				finderArgs = new Object[] {mimeType};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByMimeType;
			finderArgs = new Object[] {mimeType, start, end, orderByComparator};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if (!mimeType.equals(dlFileVersion.getMimeType())) {
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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			boolean bindMimeType = false;

			if (mimeType.isEmpty()) {
				sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_3);
			}
			else {
				bindMimeType = true;

				sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMimeType) {
					queryPos.add(mimeType);
				}

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByMimeType_First(
			String mimeType, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByMimeType_First(
			mimeType, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("mimeType=");
		sb.append(mimeType);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByMimeType_First(
		String mimeType, OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByMimeType(
			mimeType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByMimeType_Last(
			String mimeType, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByMimeType_Last(
			mimeType, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("mimeType=");
		sb.append(mimeType);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByMimeType_Last(
		String mimeType, OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByMimeType(mimeType);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByMimeType(
			mimeType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where mimeType = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param mimeType the mime type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByMimeType_PrevAndNext(
			long fileVersionId, String mimeType,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		mimeType = Objects.toString(mimeType, "");

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByMimeType_PrevAndNext(
				session, dlFileVersion, mimeType, orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByMimeType_PrevAndNext(
				session, dlFileVersion, mimeType, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileVersion getByMimeType_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, String mimeType,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		boolean bindMimeType = false;

		if (mimeType.isEmpty()) {
			sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_3);
		}
		else {
			bindMimeType = true;

			sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_2);
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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindMimeType) {
			queryPos.add(mimeType);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where mimeType = &#63; from the database.
	 *
	 * @param mimeType the mime type
	 */
	@Override
	public void removeByMimeType(String mimeType) {
		for (DLFileVersion dlFileVersion :
				findByMimeType(
					mimeType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where mimeType = &#63;.
	 *
	 * @param mimeType the mime type
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByMimeType(String mimeType) {
		mimeType = Objects.toString(mimeType, "");

		FinderPath finderPath = _finderPathCountByMimeType;

		Object[] finderArgs = new Object[] {mimeType};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			boolean bindMimeType = false;

			if (mimeType.isEmpty()) {
				sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_3);
			}
			else {
				bindMimeType = true;

				sb.append(_FINDER_COLUMN_MIMETYPE_MIMETYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMimeType) {
					queryPos.add(mimeType);
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

	private static final String _FINDER_COLUMN_MIMETYPE_MIMETYPE_2 =
		"dlFileVersion.mimeType = ?";

	private static final String _FINDER_COLUMN_MIMETYPE_MIMETYPE_3 =
		"(dlFileVersion.mimeType IS NULL OR dlFileVersion.mimeType = '')";

	private FinderPath _finderPathWithPaginationFindByC_SU;
	private FinderPath _finderPathWithoutPaginationFindByC_SU;
	private FinderPath _finderPathCountByC_SU;

	/**
	 * Returns all the document library file versions where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_SU(long companyId, String storeUUID) {
		return findByC_SU(
			companyId, storeUUID, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where companyId = &#63; and storeUUID = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_SU(
		long companyId, String storeUUID, int start, int end) {

		return findByC_SU(companyId, storeUUID, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and storeUUID = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_SU(
		long companyId, String storeUUID, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByC_SU(
			companyId, storeUUID, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and storeUUID = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_SU(
		long companyId, String storeUUID, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		storeUUID = Objects.toString(storeUUID, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_SU;
				finderArgs = new Object[] {companyId, storeUUID};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_SU;
			finderArgs = new Object[] {
				companyId, storeUUID, start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((companyId != dlFileVersion.getCompanyId()) ||
						!storeUUID.equals(dlFileVersion.getStoreUUID())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_SU_COMPANYID_2);

			boolean bindStoreUUID = false;

			if (storeUUID.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_SU_STOREUUID_3);
			}
			else {
				bindStoreUUID = true;

				sb.append(_FINDER_COLUMN_C_SU_STOREUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindStoreUUID) {
					queryPos.add(storeUUID);
				}

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_SU_First(
			long companyId, String storeUUID,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_SU_First(
			companyId, storeUUID, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", storeUUID=");
		sb.append(storeUUID);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_SU_First(
		long companyId, String storeUUID,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByC_SU(
			companyId, storeUUID, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_SU_Last(
			long companyId, String storeUUID,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_SU_Last(
			companyId, storeUUID, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", storeUUID=");
		sb.append(storeUUID);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_SU_Last(
		long companyId, String storeUUID,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByC_SU(companyId, storeUUID);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByC_SU(
			companyId, storeUUID, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByC_SU_PrevAndNext(
			long fileVersionId, long companyId, String storeUUID,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		storeUUID = Objects.toString(storeUUID, "");

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByC_SU_PrevAndNext(
				session, dlFileVersion, companyId, storeUUID, orderByComparator,
				true);

			array[1] = dlFileVersion;

			array[2] = getByC_SU_PrevAndNext(
				session, dlFileVersion, companyId, storeUUID, orderByComparator,
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

	protected DLFileVersion getByC_SU_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long companyId,
		String storeUUID, OrderByComparator<DLFileVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_C_SU_COMPANYID_2);

		boolean bindStoreUUID = false;

		if (storeUUID.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_SU_STOREUUID_3);
		}
		else {
			bindStoreUUID = true;

			sb.append(_FINDER_COLUMN_C_SU_STOREUUID_2);
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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindStoreUUID) {
			queryPos.add(storeUUID);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where companyId = &#63; and storeUUID = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 */
	@Override
	public void removeByC_SU(long companyId, String storeUUID) {
		for (DLFileVersion dlFileVersion :
				findByC_SU(
					companyId, storeUUID, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where companyId = &#63; and storeUUID = &#63;.
	 *
	 * @param companyId the company ID
	 * @param storeUUID the store uuid
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByC_SU(long companyId, String storeUUID) {
		storeUUID = Objects.toString(storeUUID, "");

		FinderPath finderPath = _finderPathCountByC_SU;

		Object[] finderArgs = new Object[] {companyId, storeUUID};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_SU_COMPANYID_2);

			boolean bindStoreUUID = false;

			if (storeUUID.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_SU_STOREUUID_3);
			}
			else {
				bindStoreUUID = true;

				sb.append(_FINDER_COLUMN_C_SU_STOREUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindStoreUUID) {
					queryPos.add(storeUUID);
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

	private static final String _FINDER_COLUMN_C_SU_COMPANYID_2 =
		"dlFileVersion.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_SU_STOREUUID_2 =
		"dlFileVersion.storeUUID = ?";

	private static final String _FINDER_COLUMN_C_SU_STOREUUID_3 =
		"(dlFileVersion.storeUUID IS NULL OR dlFileVersion.storeUUID = '')";

	private FinderPath _finderPathWithPaginationFindByC_NotS;
	private FinderPath _finderPathWithPaginationCountByC_NotS;

	/**
	 * Returns all the document library file versions where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_NotS(long companyId, int status) {
		return findByC_NotS(
			companyId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_NotS(
		long companyId, int status, int start, int end) {

		return findByC_NotS(companyId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_NotS(
		long companyId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByC_NotS(
			companyId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_NotS(
		long companyId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_NotS;
		finderArgs = new Object[] {
			companyId, status, start, end, orderByComparator
		};

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((companyId != dlFileVersion.getCompanyId()) ||
						(status == dlFileVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(status);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_NotS_First(
			long companyId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_NotS_First(
			companyId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", status!=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_NotS_First(
		long companyId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByC_NotS(
			companyId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_NotS_Last(
			long companyId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_NotS_Last(
			companyId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", status!=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_NotS_Last(
		long companyId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByC_NotS(companyId, status);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByC_NotS(
			companyId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByC_NotS_PrevAndNext(
			long fileVersionId, long companyId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByC_NotS_PrevAndNext(
				session, dlFileVersion, companyId, status, orderByComparator,
				true);

			array[1] = dlFileVersion;

			array[2] = getByC_NotS_PrevAndNext(
				session, dlFileVersion, companyId, status, orderByComparator,
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

	protected DLFileVersion getByC_NotS_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long companyId,
		int status, OrderByComparator<DLFileVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where companyId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 */
	@Override
	public void removeByC_NotS(long companyId, int status) {
		for (DLFileVersion dlFileVersion :
				findByC_NotS(
					companyId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByC_NotS(long companyId, int status) {
		FinderPath finderPath = _finderPathWithPaginationCountByC_NotS;

		Object[] finderArgs = new Object[] {companyId, status};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_NOTS_COMPANYID_2 =
		"dlFileVersion.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_NOTS_STATUS_2 =
		"dlFileVersion.status != ?";

	private FinderPath _finderPathFetchByF_V;

	/**
	 * Returns the document library file version where fileEntryId = &#63; and version = &#63; or throws a <code>NoSuchFileVersionException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param version the version
	 * @return the matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByF_V(long fileEntryId, String version)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByF_V(fileEntryId, version);

		if (dlFileVersion == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("fileEntryId=");
			sb.append(fileEntryId);

			sb.append(", version=");
			sb.append(version);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFileVersionException(sb.toString());
		}

		return dlFileVersion;
	}

	/**
	 * Returns the document library file version where fileEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param version the version
	 * @return the matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByF_V(long fileEntryId, String version) {
		return fetchByF_V(fileEntryId, version, true);
	}

	/**
	 * Returns the document library file version where fileEntryId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByF_V(
		long fileEntryId, String version, boolean useFinderCache) {

		version = Objects.toString(version, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {fileEntryId, version};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByF_V, finderArgs, this);
		}

		if (result instanceof DLFileVersion) {
			DLFileVersion dlFileVersion = (DLFileVersion)result;

			if ((fileEntryId != dlFileVersion.getFileEntryId()) ||
				!Objects.equals(version, dlFileVersion.getVersion())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_V_FILEENTRYID_2);

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_F_V_VERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				if (bindVersion) {
					queryPos.add(version);
				}

				List<DLFileVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByF_V, finderArgs, list);
					}
				}
				else {
					DLFileVersion dlFileVersion = list.get(0);

					result = dlFileVersion;

					cacheResult(dlFileVersion);
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
			return (DLFileVersion)result;
		}
	}

	/**
	 * Removes the document library file version where fileEntryId = &#63; and version = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param version the version
	 * @return the document library file version that was removed
	 */
	@Override
	public DLFileVersion removeByF_V(long fileEntryId, String version)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByF_V(fileEntryId, version);

		return remove(dlFileVersion);
	}

	/**
	 * Returns the number of document library file versions where fileEntryId = &#63; and version = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param version the version
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByF_V(long fileEntryId, String version) {
		DLFileVersion dlFileVersion = fetchByF_V(fileEntryId, version);

		if (dlFileVersion == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_F_V_FILEENTRYID_2 =
		"dlFileVersion.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_V_VERSION_2 =
		"dlFileVersion.version = ?";

	private static final String _FINDER_COLUMN_F_V_VERSION_3 =
		"(dlFileVersion.version IS NULL OR dlFileVersion.version = '')";

	private FinderPath _finderPathWithPaginationFindByF_S;
	private FinderPath _finderPathWithoutPaginationFindByF_S;
	private FinderPath _finderPathCountByF_S;
	private FinderPath _finderPathWithPaginationCountByF_S;

	/**
	 * Returns all the document library file versions where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(long fileEntryId, int status) {
		return findByF_S(
			fileEntryId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where fileEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int status, int start, int end) {

		return findByF_S(fileEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByF_S(
			fileEntryId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByF_S;
				finderArgs = new Object[] {fileEntryId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByF_S;
			finderArgs = new Object[] {
				fileEntryId, status, start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((fileEntryId != dlFileVersion.getFileEntryId()) ||
						(status != dlFileVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FILEENTRYID_2);

			sb.append(_FINDER_COLUMN_F_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				queryPos.add(status);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByF_S_First(
			long fileEntryId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByF_S_First(
			fileEntryId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByF_S_First(
		long fileEntryId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByF_S(
			fileEntryId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByF_S_Last(
			long fileEntryId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByF_S_Last(
			fileEntryId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByF_S_Last(
		long fileEntryId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByF_S(fileEntryId, status);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByF_S(
			fileEntryId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByF_S_PrevAndNext(
			long fileVersionId, long fileEntryId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByF_S_PrevAndNext(
				session, dlFileVersion, fileEntryId, status, orderByComparator,
				true);

			array[1] = dlFileVersion;

			array[2] = getByF_S_PrevAndNext(
				session, dlFileVersion, fileEntryId, status, orderByComparator,
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

	protected DLFileVersion getByF_S_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long fileEntryId,
		int status, OrderByComparator<DLFileVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_F_S_FILEENTRYID_2);

		sb.append(_FINDER_COLUMN_F_S_STATUS_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fileEntryId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file versions where fileEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param statuses the statuses
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(long fileEntryId, int[] statuses) {
		return findByF_S(
			fileEntryId, statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where fileEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int[] statuses, int start, int end) {

		return findByF_S(fileEntryId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int[] statuses, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByF_S(
			fileEntryId, statuses, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where fileEntryId = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByF_S(
		long fileEntryId, int[] statuses, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByF_S(
				fileEntryId, statuses[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					fileEntryId, StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				fileEntryId, StringUtil.merge(statuses), start, end,
				orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				_finderPathWithPaginationFindByF_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((fileEntryId != dlFileVersion.getFileEntryId()) ||
						!ArrayUtil.contains(
							statuses, dlFileVersion.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FILEENTRYID_2);

			if (statuses.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_F_S_STATUS_7);

				sb.append(StringUtil.merge(statuses));

				sb.append(")");

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				list = (List<DLFileVersion>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationFindByF_S, finderArgs, list);
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
	 * Removes all the document library file versions where fileEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 */
	@Override
	public void removeByF_S(long fileEntryId, int status) {
		for (DLFileVersion dlFileVersion :
				findByF_S(
					fileEntryId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where fileEntryId = &#63; and status = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param status the status
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByF_S(long fileEntryId, int status) {
		FinderPath finderPath = _finderPathCountByF_S;

		Object[] finderArgs = new Object[] {fileEntryId, status};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FILEENTRYID_2);

			sb.append(_FINDER_COLUMN_F_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

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

	/**
	 * Returns the number of document library file versions where fileEntryId = &#63; and status = any &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param statuses the statuses
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByF_S(long fileEntryId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			fileEntryId, StringUtil.merge(statuses)
		};

		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathWithPaginationCountByF_S, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FILEENTRYID_2);

			if (statuses.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_F_S_STATUS_7);

				sb.append(StringUtil.merge(statuses));

				sb.append(")");

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathWithPaginationCountByF_S, finderArgs, count);
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

	private static final String _FINDER_COLUMN_F_S_FILEENTRYID_2 =
		"dlFileVersion.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_S_STATUS_2 =
		"dlFileVersion.status = ?";

	private static final String _FINDER_COLUMN_F_S_STATUS_7 =
		"dlFileVersion.status IN (";

	private FinderPath _finderPathWithPaginationFindByLtD_S;
	private FinderPath _finderPathWithPaginationCountByLtD_S;

	/**
	 * Returns all the document library file versions where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByLtD_S(Date displayDate, int status) {
		return findByLtD_S(
			displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByLtD_S(
		Date displayDate, int status, int start, int end) {

		return findByLtD_S(displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByLtD_S(
			displayDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtD_S;
		finderArgs = new Object[] {
			_getTime(displayDate), status, start, end, orderByComparator
		};

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((displayDate.getTime() <= dlFileVersion.getDisplayDate(
						).getTime()) || (status != dlFileVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			sb.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDisplayDate) {
					queryPos.add(new Timestamp(displayDate.getTime()));
				}

				queryPos.add(status);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByLtD_S_First(
			Date displayDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByLtD_S_First(
			displayDate, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("displayDate<");
		sb.append(displayDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByLtD_S_First(
		Date displayDate, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByLtD_S(
			displayDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByLtD_S_Last(
			Date displayDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByLtD_S_Last(
			displayDate, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("displayDate<");
		sb.append(displayDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByLtD_S_Last(
		Date displayDate, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByLtD_S(displayDate, status);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByLtD_S(
			displayDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByLtD_S_PrevAndNext(
			long fileVersionId, Date displayDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByLtD_S_PrevAndNext(
				session, dlFileVersion, displayDate, status, orderByComparator,
				true);

			array[1] = dlFileVersion;

			array[2] = getByLtD_S_PrevAndNext(
				session, dlFileVersion, displayDate, status, orderByComparator,
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

	protected DLFileVersion getByLtD_S_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, Date displayDate,
		int status, OrderByComparator<DLFileVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		sb.append(_FINDER_COLUMN_LTD_S_STATUS_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDisplayDate) {
			queryPos.add(new Timestamp(displayDate.getTime()));
		}

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 */
	@Override
	public void removeByLtD_S(Date displayDate, int status) {
		for (DLFileVersion dlFileVersion :
				findByLtD_S(
					displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByLtD_S(Date displayDate, int status) {
		FinderPath finderPath = _finderPathWithPaginationCountByLtD_S;

		Object[] finderArgs = new Object[] {_getTime(displayDate), status};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				sb.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			sb.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDisplayDate) {
					queryPos.add(new Timestamp(displayDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_1 =
		"dlFileVersion.displayDate IS NULL AND ";

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_2 =
		"dlFileVersion.displayDate < ? AND ";

	private static final String _FINDER_COLUMN_LTD_S_STATUS_2 =
		"dlFileVersion.status = ?";

	private FinderPath _finderPathWithPaginationFindByG_F_S;
	private FinderPath _finderPathWithoutPaginationFindByG_F_S;
	private FinderPath _finderPathCountByG_F_S;

	/**
	 * Returns all the document library file versions where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_S(
		long groupId, long folderId, int status) {

		return findByG_F_S(
			groupId, folderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the document library file versions where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_S(
		long groupId, long folderId, int status, int start, int end) {

		return findByG_F_S(groupId, folderId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_S(
		long groupId, long folderId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByG_F_S(
			groupId, folderId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_S(
		long groupId, long folderId, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_F_S;
				finderArgs = new Object[] {groupId, folderId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_F_S;
			finderArgs = new Object[] {
				groupId, folderId, status, start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((groupId != dlFileVersion.getGroupId()) ||
						(folderId != dlFileVersion.getFolderId()) ||
						(status != dlFileVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_G_F_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_F_S_FOLDERID_2);

			sb.append(_FINDER_COLUMN_G_F_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(folderId);

				queryPos.add(status);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByG_F_S_First(
			long groupId, long folderId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByG_F_S_First(
			groupId, folderId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", folderId=");
		sb.append(folderId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByG_F_S_First(
		long groupId, long folderId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByG_F_S(
			groupId, folderId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByG_F_S_Last(
			long groupId, long folderId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByG_F_S_Last(
			groupId, folderId, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", folderId=");
		sb.append(folderId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByG_F_S_Last(
		long groupId, long folderId, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByG_F_S(groupId, folderId, status);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByG_F_S(
			groupId, folderId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByG_F_S_PrevAndNext(
			long fileVersionId, long groupId, long folderId, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByG_F_S_PrevAndNext(
				session, dlFileVersion, groupId, folderId, status,
				orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByG_F_S_PrevAndNext(
				session, dlFileVersion, groupId, folderId, status,
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

	protected DLFileVersion getByG_F_S_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long groupId,
		long folderId, int status,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_G_F_S_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_F_S_FOLDERID_2);

		sb.append(_FINDER_COLUMN_G_F_S_STATUS_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(folderId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where groupId = &#63; and folderId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 */
	@Override
	public void removeByG_F_S(long groupId, long folderId, int status) {
		for (DLFileVersion dlFileVersion :
				findByG_F_S(
					groupId, folderId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where groupId = &#63; and folderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param status the status
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByG_F_S(long groupId, long folderId, int status) {
		FinderPath finderPath = _finderPathCountByG_F_S;

		Object[] finderArgs = new Object[] {groupId, folderId, status};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_G_F_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_F_S_FOLDERID_2);

			sb.append(_FINDER_COLUMN_G_F_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(folderId);

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

	private static final String _FINDER_COLUMN_G_F_S_GROUPID_2 =
		"dlFileVersion.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_F_S_FOLDERID_2 =
		"dlFileVersion.folderId = ? AND ";

	private static final String _FINDER_COLUMN_G_F_S_STATUS_2 =
		"dlFileVersion.status = ?";

	private FinderPath _finderPathWithPaginationFindByC_E_S;
	private FinderPath _finderPathWithoutPaginationFindByC_E_S;
	private FinderPath _finderPathCountByC_E_S;
	private FinderPath _finderPathWithPaginationCountByC_E_S;

	/**
	 * Returns all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int status) {

		return findByC_E_S(
			companyId, expirationDate, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int status, int start, int end) {

		return findByC_E_S(companyId, expirationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByC_E_S(
			companyId, expirationDate, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int status, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_E_S;
				finderArgs = new Object[] {
					companyId, _getTime(expirationDate), status
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_E_S;
			finderArgs = new Object[] {
				companyId, _getTime(expirationDate), status, start, end,
				orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((companyId != dlFileVersion.getCompanyId()) ||
						!Objects.equals(
							expirationDate,
							dlFileVersion.getExpirationDate()) ||
						(status != dlFileVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_E_S_COMPANYID_2);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2);
			}

			sb.append(_FINDER_COLUMN_C_E_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

				queryPos.add(status);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_E_S_First(
			long companyId, Date expirationDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_E_S_First(
			companyId, expirationDate, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", expirationDate=");
		sb.append(expirationDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_E_S_First(
		long companyId, Date expirationDate, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByC_E_S(
			companyId, expirationDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByC_E_S_Last(
			long companyId, Date expirationDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByC_E_S_Last(
			companyId, expirationDate, status, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", expirationDate=");
		sb.append(expirationDate);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByC_E_S_Last(
		long companyId, Date expirationDate, int status,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByC_E_S(companyId, expirationDate, status);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByC_E_S(
			companyId, expirationDate, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByC_E_S_PrevAndNext(
			long fileVersionId, long companyId, Date expirationDate, int status,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByC_E_S_PrevAndNext(
				session, dlFileVersion, companyId, expirationDate, status,
				orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByC_E_S_PrevAndNext(
				session, dlFileVersion, companyId, expirationDate, status,
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

	protected DLFileVersion getByC_E_S_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long companyId,
		Date expirationDate, int status,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_C_E_S_COMPANYID_2);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2);
		}

		sb.append(_FINDER_COLUMN_C_E_S_STATUS_2);

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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindExpirationDate) {
			queryPos.add(new Timestamp(expirationDate.getTime()));
		}

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param statuses the statuses
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int[] statuses) {

		return findByC_E_S(
			companyId, expirationDate, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int[] statuses, int start,
		int end) {

		return findByC_E_S(
			companyId, expirationDate, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int[] statuses, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findByC_E_S(
			companyId, expirationDate, statuses, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param statuses the statuses
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByC_E_S(
		long companyId, Date expirationDate, int[] statuses, int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if (statuses.length == 1) {
			return findByC_E_S(
				companyId, expirationDate, statuses[0], start, end,
				orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					companyId, _getTime(expirationDate),
					StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				companyId, _getTime(expirationDate), StringUtil.merge(statuses),
				start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				_finderPathWithPaginationFindByC_E_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((companyId != dlFileVersion.getCompanyId()) ||
						!Objects.equals(
							expirationDate,
							dlFileVersion.getExpirationDate()) ||
						!ArrayUtil.contains(
							statuses, dlFileVersion.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_E_S_COMPANYID_2);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2);
			}

			if (statuses.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_C_E_S_STATUS_7);

				sb.append(StringUtil.merge(statuses));

				sb.append(")");

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

				list = (List<DLFileVersion>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationFindByC_E_S, finderArgs, list);
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
	 * Removes all the document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 */
	@Override
	public void removeByC_E_S(long companyId, Date expirationDate, int status) {
		for (DLFileVersion dlFileVersion :
				findByC_E_S(
					companyId, expirationDate, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where companyId = &#63; and expirationDate = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByC_E_S(long companyId, Date expirationDate, int status) {
		FinderPath finderPath = _finderPathCountByC_E_S;

		Object[] finderArgs = new Object[] {
			companyId, _getTime(expirationDate), status
		};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_E_S_COMPANYID_2);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2);
			}

			sb.append(_FINDER_COLUMN_C_E_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

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

	/**
	 * Returns the number of document library file versions where companyId = &#63; and expirationDate = &#63; and status = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param expirationDate the expiration date
	 * @param statuses the statuses
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByC_E_S(
		long companyId, Date expirationDate, int[] statuses) {

		if (statuses == null) {
			statuses = new int[0];
		}
		else if (statuses.length > 1) {
			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			companyId, _getTime(expirationDate), StringUtil.merge(statuses)
		};

		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathWithPaginationCountByC_E_S, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_E_S_COMPANYID_2);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				sb.append(_FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2);
			}

			if (statuses.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_C_E_S_STATUS_7);

				sb.append(StringUtil.merge(statuses));

				sb.append(")");

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExpirationDate) {
					queryPos.add(new Timestamp(expirationDate.getTime()));
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathWithPaginationCountByC_E_S, finderArgs, count);
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

	private static final String _FINDER_COLUMN_C_E_S_COMPANYID_2 =
		"dlFileVersion.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_E_S_EXPIRATIONDATE_1 =
		"dlFileVersion.expirationDate IS NULL AND ";

	private static final String _FINDER_COLUMN_C_E_S_EXPIRATIONDATE_2 =
		"dlFileVersion.expirationDate = ? AND ";

	private static final String _FINDER_COLUMN_C_E_S_STATUS_2 =
		"dlFileVersion.status = ?";

	private static final String _FINDER_COLUMN_C_E_S_STATUS_7 =
		"dlFileVersion.status IN (";

	private FinderPath _finderPathWithPaginationFindByG_F_T_V;
	private FinderPath _finderPathWithoutPaginationFindByG_F_T_V;
	private FinderPath _finderPathCountByG_F_T_V;

	/**
	 * Returns all the document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @return the matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_T_V(
		long groupId, long folderId, String title, String version) {

		return findByG_F_T_V(
			groupId, folderId, title, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_T_V(
		long groupId, long folderId, String title, String version, int start,
		int end) {

		return findByG_F_T_V(
			groupId, folderId, title, version, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_T_V(
		long groupId, long folderId, String title, String version, int start,
		int end, OrderByComparator<DLFileVersion> orderByComparator) {

		return findByG_F_T_V(
			groupId, folderId, title, version, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching document library file versions
	 */
	@Override
	public List<DLFileVersion> findByG_F_T_V(
		long groupId, long folderId, String title, String version, int start,
		int end, OrderByComparator<DLFileVersion> orderByComparator,
		boolean useFinderCache) {

		title = Objects.toString(title, "");
		version = Objects.toString(version, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_F_T_V;
				finderArgs = new Object[] {groupId, folderId, title, version};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_F_T_V;
			finderArgs = new Object[] {
				groupId, folderId, title, version, start, end, orderByComparator
			};
		}

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileVersion dlFileVersion : list) {
					if ((groupId != dlFileVersion.getGroupId()) ||
						(folderId != dlFileVersion.getFolderId()) ||
						!title.equals(dlFileVersion.getTitle()) ||
						!version.equals(dlFileVersion.getVersion())) {

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

			sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_G_F_T_V_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_F_T_V_FOLDERID_2);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_2);
			}

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(folderId);

				if (bindTitle) {
					queryPos.add(title);
				}

				if (bindVersion) {
					queryPos.add(version);
				}

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Returns the first document library file version in the ordered set where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByG_F_T_V_First(
			long groupId, long folderId, String title, String version,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByG_F_T_V_First(
			groupId, folderId, title, version, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", folderId=");
		sb.append(folderId);

		sb.append(", title=");
		sb.append(title);

		sb.append(", version=");
		sb.append(version);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the first document library file version in the ordered set where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByG_F_T_V_First(
		long groupId, long folderId, String title, String version,
		OrderByComparator<DLFileVersion> orderByComparator) {

		List<DLFileVersion> list = findByG_F_T_V(
			groupId, folderId, title, version, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file version in the ordered set where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version
	 * @throws NoSuchFileVersionException if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion findByG_F_T_V_Last(
			long groupId, long folderId, String title, String version,
			OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByG_F_T_V_Last(
			groupId, folderId, title, version, orderByComparator);

		if (dlFileVersion != null) {
			return dlFileVersion;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", folderId=");
		sb.append(folderId);

		sb.append(", title=");
		sb.append(title);

		sb.append(", version=");
		sb.append(version);

		sb.append("}");

		throw new NoSuchFileVersionException(sb.toString());
	}

	/**
	 * Returns the last document library file version in the ordered set where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file version, or <code>null</code> if a matching document library file version could not be found
	 */
	@Override
	public DLFileVersion fetchByG_F_T_V_Last(
		long groupId, long folderId, String title, String version,
		OrderByComparator<DLFileVersion> orderByComparator) {

		int count = countByG_F_T_V(groupId, folderId, title, version);

		if (count == 0) {
			return null;
		}

		List<DLFileVersion> list = findByG_F_T_V(
			groupId, folderId, title, version, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file versions before and after the current document library file version in the ordered set where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param fileVersionId the primary key of the current document library file version
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion[] findByG_F_T_V_PrevAndNext(
			long fileVersionId, long groupId, long folderId, String title,
			String version, OrderByComparator<DLFileVersion> orderByComparator)
		throws NoSuchFileVersionException {

		title = Objects.toString(title, "");
		version = Objects.toString(version, "");

		DLFileVersion dlFileVersion = findByPrimaryKey(fileVersionId);

		Session session = null;

		try {
			session = openSession();

			DLFileVersion[] array = new DLFileVersionImpl[3];

			array[0] = getByG_F_T_V_PrevAndNext(
				session, dlFileVersion, groupId, folderId, title, version,
				orderByComparator, true);

			array[1] = dlFileVersion;

			array[2] = getByG_F_T_V_PrevAndNext(
				session, dlFileVersion, groupId, folderId, title, version,
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

	protected DLFileVersion getByG_F_T_V_PrevAndNext(
		Session session, DLFileVersion dlFileVersion, long groupId,
		long folderId, String title, String version,
		OrderByComparator<DLFileVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_DLFILEVERSION_WHERE);

		sb.append(_FINDER_COLUMN_G_F_T_V_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_F_T_V_FOLDERID_2);

		boolean bindTitle = false;

		if (title.isEmpty()) {
			sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_3);
		}
		else {
			bindTitle = true;

			sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_2);
		}

		boolean bindVersion = false;

		if (version.isEmpty()) {
			sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_3);
		}
		else {
			bindVersion = true;

			sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_2);
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
			sb.append(DLFileVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(folderId);

		if (bindTitle) {
			queryPos.add(title);
		}

		if (bindVersion) {
			queryPos.add(version);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 */
	@Override
	public void removeByG_F_T_V(
		long groupId, long folderId, String title, String version) {

		for (DLFileVersion dlFileVersion :
				findByG_F_T_V(
					groupId, folderId, title, version, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions where groupId = &#63; and folderId = &#63; and title = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param folderId the folder ID
	 * @param title the title
	 * @param version the version
	 * @return the number of matching document library file versions
	 */
	@Override
	public int countByG_F_T_V(
		long groupId, long folderId, String title, String version) {

		title = Objects.toString(title, "");
		version = Objects.toString(version, "");

		FinderPath finderPath = _finderPathCountByG_F_T_V;

		Object[] finderArgs = new Object[] {groupId, folderId, title, version};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_DLFILEVERSION_WHERE);

			sb.append(_FINDER_COLUMN_G_F_T_V_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_F_T_V_FOLDERID_2);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_G_F_T_V_TITLE_2);
			}

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_G_F_T_V_VERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(folderId);

				if (bindTitle) {
					queryPos.add(title);
				}

				if (bindVersion) {
					queryPos.add(version);
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

	private static final String _FINDER_COLUMN_G_F_T_V_GROUPID_2 =
		"dlFileVersion.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_F_T_V_FOLDERID_2 =
		"dlFileVersion.folderId = ? AND ";

	private static final String _FINDER_COLUMN_G_F_T_V_TITLE_2 =
		"dlFileVersion.title = ? AND ";

	private static final String _FINDER_COLUMN_G_F_T_V_TITLE_3 =
		"(dlFileVersion.title IS NULL OR dlFileVersion.title = '') AND ";

	private static final String _FINDER_COLUMN_G_F_T_V_VERSION_2 =
		"dlFileVersion.version = ?";

	private static final String _FINDER_COLUMN_G_F_T_V_VERSION_3 =
		"(dlFileVersion.version IS NULL OR dlFileVersion.version = '')";

	public DLFileVersionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("size", "size_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DLFileVersion.class);

		setModelImplClass(DLFileVersionImpl.class);
		setModelPKClass(long.class);

		setTable(DLFileVersionTable.INSTANCE);
	}

	/**
	 * Caches the document library file version in the entity cache if it is enabled.
	 *
	 * @param dlFileVersion the document library file version
	 */
	@Override
	public void cacheResult(DLFileVersion dlFileVersion) {
		EntityCacheUtil.putResult(
			DLFileVersionImpl.class, dlFileVersion.getPrimaryKey(),
			dlFileVersion);

		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {dlFileVersion.getUuid(), dlFileVersion.getGroupId()},
			dlFileVersion);

		FinderCacheUtil.putResult(
			_finderPathFetchByF_V,
			new Object[] {
				dlFileVersion.getFileEntryId(), dlFileVersion.getVersion()
			},
			dlFileVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the document library file versions in the entity cache if it is enabled.
	 *
	 * @param dlFileVersions the document library file versions
	 */
	@Override
	public void cacheResult(List<DLFileVersion> dlFileVersions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dlFileVersions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DLFileVersion dlFileVersion : dlFileVersions) {
			if (EntityCacheUtil.getResult(
					DLFileVersionImpl.class, dlFileVersion.getPrimaryKey()) ==
						null) {

				cacheResult(dlFileVersion);
			}
		}
	}

	/**
	 * Clears the cache for all document library file versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(DLFileVersionImpl.class);

		FinderCacheUtil.clearCache(DLFileVersionImpl.class);
	}

	/**
	 * Clears the cache for the document library file version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DLFileVersion dlFileVersion) {
		EntityCacheUtil.removeResult(DLFileVersionImpl.class, dlFileVersion);
	}

	@Override
	public void clearCache(List<DLFileVersion> dlFileVersions) {
		for (DLFileVersion dlFileVersion : dlFileVersions) {
			EntityCacheUtil.removeResult(
				DLFileVersionImpl.class, dlFileVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(DLFileVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(DLFileVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DLFileVersionModelImpl dlFileVersionModelImpl) {

		Object[] args = new Object[] {
			dlFileVersionModelImpl.getUuid(),
			dlFileVersionModelImpl.getGroupId()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G, args, dlFileVersionModelImpl);

		args = new Object[] {
			dlFileVersionModelImpl.getFileEntryId(),
			dlFileVersionModelImpl.getVersion()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByF_V, args, dlFileVersionModelImpl);
	}

	/**
	 * Creates a new document library file version with the primary key. Does not add the document library file version to the database.
	 *
	 * @param fileVersionId the primary key for the new document library file version
	 * @return the new document library file version
	 */
	@Override
	public DLFileVersion create(long fileVersionId) {
		DLFileVersion dlFileVersion = new DLFileVersionImpl();

		dlFileVersion.setNew(true);
		dlFileVersion.setPrimaryKey(fileVersionId);

		String uuid = PortalUUIDUtil.generate();

		dlFileVersion.setUuid(uuid);

		dlFileVersion.setCompanyId(CompanyThreadLocal.getCompanyId());

		return dlFileVersion;
	}

	/**
	 * Removes the document library file version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileVersionId the primary key of the document library file version
	 * @return the document library file version that was removed
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion remove(long fileVersionId)
		throws NoSuchFileVersionException {

		return remove((Serializable)fileVersionId);
	}

	/**
	 * Removes the document library file version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the document library file version
	 * @return the document library file version that was removed
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion remove(Serializable primaryKey)
		throws NoSuchFileVersionException {

		Session session = null;

		try {
			session = openSession();

			DLFileVersion dlFileVersion = (DLFileVersion)session.get(
				DLFileVersionImpl.class, primaryKey);

			if (dlFileVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dlFileVersion);
		}
		catch (NoSuchFileVersionException noSuchEntityException) {
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
	protected DLFileVersion removeImpl(DLFileVersion dlFileVersion) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dlFileVersion)) {
				dlFileVersion = (DLFileVersion)session.get(
					DLFileVersionImpl.class, dlFileVersion.getPrimaryKeyObj());
			}

			if (dlFileVersion != null) {
				session.delete(dlFileVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dlFileVersion != null) {
			clearCache(dlFileVersion);
		}

		return dlFileVersion;
	}

	@Override
	public DLFileVersion updateImpl(DLFileVersion dlFileVersion) {
		boolean isNew = dlFileVersion.isNew();

		if (!(dlFileVersion instanceof DLFileVersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dlFileVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					dlFileVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dlFileVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DLFileVersion implementation " +
					dlFileVersion.getClass());
		}

		DLFileVersionModelImpl dlFileVersionModelImpl =
			(DLFileVersionModelImpl)dlFileVersion;

		if (Validator.isNull(dlFileVersion.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dlFileVersion.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (dlFileVersion.getCreateDate() == null)) {
			if (serviceContext == null) {
				dlFileVersion.setCreateDate(date);
			}
			else {
				dlFileVersion.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!dlFileVersionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dlFileVersion.setModifiedDate(date);
			}
			else {
				dlFileVersion.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dlFileVersion);
			}
			else {
				dlFileVersion = (DLFileVersion)session.merge(dlFileVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			DLFileVersionImpl.class, dlFileVersionModelImpl, false, true);

		cacheUniqueFindersCache(dlFileVersionModelImpl);

		if (isNew) {
			dlFileVersion.setNew(false);
		}

		dlFileVersion.resetOriginalValues();

		return dlFileVersion;
	}

	/**
	 * Returns the document library file version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the document library file version
	 * @return the document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileVersionException {

		DLFileVersion dlFileVersion = fetchByPrimaryKey(primaryKey);

		if (dlFileVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dlFileVersion;
	}

	/**
	 * Returns the document library file version with the primary key or throws a <code>NoSuchFileVersionException</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the document library file version
	 * @return the document library file version
	 * @throws NoSuchFileVersionException if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion findByPrimaryKey(long fileVersionId)
		throws NoSuchFileVersionException {

		return findByPrimaryKey((Serializable)fileVersionId);
	}

	/**
	 * Returns the document library file version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileVersionId the primary key of the document library file version
	 * @return the document library file version, or <code>null</code> if a document library file version with the primary key could not be found
	 */
	@Override
	public DLFileVersion fetchByPrimaryKey(long fileVersionId) {
		return fetchByPrimaryKey((Serializable)fileVersionId);
	}

	/**
	 * Returns all the document library file versions.
	 *
	 * @return the document library file versions
	 */
	@Override
	public List<DLFileVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @return the range of document library file versions
	 */
	@Override
	public List<DLFileVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document library file versions
	 */
	@Override
	public List<DLFileVersion> findAll(
		int start, int end,
		OrderByComparator<DLFileVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file versions
	 * @param end the upper bound of the range of document library file versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of document library file versions
	 */
	@Override
	public List<DLFileVersion> findAll(
		int start, int end, OrderByComparator<DLFileVersion> orderByComparator,
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

		List<DLFileVersion> list = null;

		if (useFinderCache) {
			list = (List<DLFileVersion>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DLFILEVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DLFILEVERSION;

				sql = sql.concat(DLFileVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DLFileVersion>)QueryUtil.list(
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
	 * Removes all the document library file versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DLFileVersion dlFileVersion : findAll()) {
			remove(dlFileVersion);
		}
	}

	/**
	 * Returns the number of document library file versions.
	 *
	 * @return the number of document library file versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DLFILEVERSION);

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
		return "fileVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DLFILEVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DLFileVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the document library file version persistence.
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

		_finderPathWithPaginationFindByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId"}, true);

		_finderPathWithoutPaginationFindByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			true);

		_finderPathCountByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			false);

		_finderPathWithPaginationFindByMimeType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMimeType",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"mimeType"}, true);

		_finderPathWithoutPaginationFindByMimeType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMimeType",
			new String[] {String.class.getName()}, new String[] {"mimeType"},
			true);

		_finderPathCountByMimeType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMimeType",
			new String[] {String.class.getName()}, new String[] {"mimeType"},
			false);

		_finderPathWithPaginationFindByC_SU = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_SU",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "storeUUID"}, true);

		_finderPathWithoutPaginationFindByC_SU = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_SU",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "storeUUID"}, true);

		_finderPathCountByC_SU = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_SU",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "storeUUID"}, false);

		_finderPathWithPaginationFindByC_NotS = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "status"}, true);

		_finderPathWithPaginationCountByC_NotS = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_NotS",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"companyId", "status"}, false);

		_finderPathFetchByF_V = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByF_V",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "version"}, true);

		_finderPathWithPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId", "status"}, true);

		_finderPathWithoutPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"fileEntryId", "status"}, true);

		_finderPathCountByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"fileEntryId", "status"}, false);

		_finderPathWithPaginationCountByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"fileEntryId", "status"}, false);

		_finderPathWithPaginationFindByLtD_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtD_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"displayDate", "status"}, true);

		_finderPathWithPaginationCountByLtD_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtD_S",
			new String[] {Date.class.getName(), Integer.class.getName()},
			new String[] {"displayDate", "status"}, false);

		_finderPathWithPaginationFindByG_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "folderId", "status"}, true);

		_finderPathWithoutPaginationFindByG_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {"groupId", "folderId", "status"}, true);

		_finderPathCountByG_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {"groupId", "folderId", "status"}, false);

		_finderPathWithPaginationFindByC_E_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_E_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId", "expirationDate", "status"}, true);

		_finderPathWithoutPaginationFindByC_E_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_E_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			},
			new String[] {"companyId", "expirationDate", "status"}, true);

		_finderPathCountByC_E_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_E_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			},
			new String[] {"companyId", "expirationDate", "status"}, false);

		_finderPathWithPaginationCountByC_E_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_E_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			},
			new String[] {"companyId", "expirationDate", "status"}, false);

		_finderPathWithPaginationFindByG_F_T_V = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_F_T_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "folderId", "title", "version"}, true);

		_finderPathWithoutPaginationFindByG_F_T_V = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_F_T_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName()
			},
			new String[] {"groupId", "folderId", "title", "version"}, true);

		_finderPathCountByG_F_T_V = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_F_T_V",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), String.class.getName()
			},
			new String[] {"groupId", "folderId", "title", "version"}, false);

		DLFileVersionUtil.setPersistence(this);
	}

	public void destroy() {
		DLFileVersionUtil.setPersistence(null);

		EntityCacheUtil.removeCache(DLFileVersionImpl.class.getName());
	}

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_DLFILEVERSION =
		"SELECT dlFileVersion FROM DLFileVersion dlFileVersion";

	private static final String _SQL_SELECT_DLFILEVERSION_WHERE =
		"SELECT dlFileVersion FROM DLFileVersion dlFileVersion WHERE ";

	private static final String _SQL_COUNT_DLFILEVERSION =
		"SELECT COUNT(dlFileVersion) FROM DLFileVersion dlFileVersion";

	private static final String _SQL_COUNT_DLFILEVERSION_WHERE =
		"SELECT COUNT(dlFileVersion) FROM DLFileVersion dlFileVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dlFileVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DLFileVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DLFileVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "size"});

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}