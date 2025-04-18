/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchStorageLinkException;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStorageLinkTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStorageLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStorageLinkUtil;
import com.liferay.dynamic.data.mapping.service.persistence.impl.constants.DDMPersistenceConstants;
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

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
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
 * The persistence implementation for the ddm storage link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMStorageLinkPersistence.class)
public class DDMStorageLinkPersistenceImpl
	extends BasePersistenceImpl<DDMStorageLink>
	implements DDMStorageLinkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMStorageLinkUtil</code> to access the ddm storage link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMStorageLinkImpl.class.getName();

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
	 * Returns all the ddm storage links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator,
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

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStorageLink ddmStorageLink : list) {
					if (!uuid.equals(ddmStorageLink.getUuid())) {
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

			sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

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
				sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
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

				list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Returns the first ddm storage link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByUuid_First(
			String uuid, OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByUuid_First(
			uuid, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm storage link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByUuid_First(
		String uuid, OrderByComparator<DDMStorageLink> orderByComparator) {

		List<DDMStorageLink> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm storage link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByUuid_Last(
			String uuid, OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByUuid_Last(
			uuid, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm storage link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByUuid_Last(
		String uuid, OrderByComparator<DDMStorageLink> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DDMStorageLink> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm storage links before and after the current ddm storage link in the ordered set where uuid = &#63;.
	 *
	 * @param storageLinkId the primary key of the current ddm storage link
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink[] findByUuid_PrevAndNext(
			long storageLinkId, String uuid,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		uuid = Objects.toString(uuid, "");

		DDMStorageLink ddmStorageLink = findByPrimaryKey(storageLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStorageLink[] array = new DDMStorageLinkImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, ddmStorageLink, uuid, orderByComparator, true);

			array[1] = ddmStorageLink;

			array[2] = getByUuid_PrevAndNext(
				session, ddmStorageLink, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMStorageLink getByUuid_PrevAndNext(
		Session session, DDMStorageLink ddmStorageLink, String uuid,
		OrderByComparator<DDMStorageLink> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

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
			sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
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
						ddmStorageLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStorageLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm storage links where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DDMStorageLink ddmStorageLink :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmStorageLink);
		}
	}

	/**
	 * Returns the number of ddm storage links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMSTORAGELINK_WHERE);

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
		"ddmStorageLink.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(ddmStorageLink.uuid IS NULL OR ddmStorageLink.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the ddm storage links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator,
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

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStorageLink ddmStorageLink : list) {
					if (!uuid.equals(ddmStorageLink.getUuid()) ||
						(companyId != ddmStorageLink.getCompanyId())) {

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

			sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

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
				sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
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

				list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Returns the first ddm storage link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm storage link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		List<DDMStorageLink> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm storage link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm storage link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DDMStorageLink> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm storage links before and after the current ddm storage link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param storageLinkId the primary key of the current ddm storage link
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink[] findByUuid_C_PrevAndNext(
			long storageLinkId, String uuid, long companyId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		uuid = Objects.toString(uuid, "");

		DDMStorageLink ddmStorageLink = findByPrimaryKey(storageLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStorageLink[] array = new DDMStorageLinkImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, ddmStorageLink, uuid, companyId, orderByComparator,
				true);

			array[1] = ddmStorageLink;

			array[2] = getByUuid_C_PrevAndNext(
				session, ddmStorageLink, uuid, companyId, orderByComparator,
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

	protected DDMStorageLink getByUuid_C_PrevAndNext(
		Session session, DDMStorageLink ddmStorageLink, String uuid,
		long companyId, OrderByComparator<DDMStorageLink> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

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
			sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
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
						ddmStorageLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStorageLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm storage links where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DDMStorageLink ddmStorageLink :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmStorageLink);
		}
	}

	/**
	 * Returns the number of ddm storage links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMSTORAGELINK_WHERE);

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
		"ddmStorageLink.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(ddmStorageLink.uuid IS NULL OR ddmStorageLink.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"ddmStorageLink.companyId = ?";

	private FinderPath _finderPathFetchByClassPK;

	/**
	 * Returns the ddm storage link where classPK = &#63; or throws a <code>NoSuchStorageLinkException</code> if it could not be found.
	 *
	 * @param classPK the class pk
	 * @return the matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByClassPK(long classPK)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByClassPK(classPK);

		if (ddmStorageLink == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("classPK=");
			sb.append(classPK);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchStorageLinkException(sb.toString());
		}

		return ddmStorageLink;
	}

	/**
	 * Returns the ddm storage link where classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classPK the class pk
	 * @return the matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByClassPK(long classPK) {
		return fetchByClassPK(classPK, true);
	}

	/**
	 * Returns the ddm storage link where classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByClassPK(long classPK, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classPK};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByClassPK, finderArgs, this);
		}

		if (result instanceof DDMStorageLink) {
			DDMStorageLink ddmStorageLink = (DDMStorageLink)result;

			if (classPK != ddmStorageLink.getClassPK()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

			sb.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classPK);

				List<DDMStorageLink> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByClassPK, finderArgs, list);
					}
				}
				else {
					DDMStorageLink ddmStorageLink = list.get(0);

					result = ddmStorageLink;

					cacheResult(ddmStorageLink);
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
			return (DDMStorageLink)result;
		}
	}

	/**
	 * Removes the ddm storage link where classPK = &#63; from the database.
	 *
	 * @param classPK the class pk
	 * @return the ddm storage link that was removed
	 */
	@Override
	public DDMStorageLink removeByClassPK(long classPK)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = findByClassPK(classPK);

		return remove(ddmStorageLink);
	}

	/**
	 * Returns the number of ddm storage links where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByClassPK(long classPK) {
		DDMStorageLink ddmStorageLink = fetchByClassPK(classPK);

		if (ddmStorageLink == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_CLASSPK_CLASSPK_2 =
		"ddmStorageLink.classPK = ?";

	private FinderPath _finderPathWithPaginationFindByStructureId;
	private FinderPath _finderPathWithoutPaginationFindByStructureId;
	private FinderPath _finderPathCountByStructureId;

	/**
	 * Returns all the ddm storage links where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureId(long structureId) {
		return findByStructureId(
			structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureId(
		long structureId, int start, int end) {

		return findByStructureId(structureId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findByStructureId(
			structureId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStructureId;
				finderArgs = new Object[] {structureId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStructureId;
			finderArgs = new Object[] {
				structureId, start, end, orderByComparator
			};
		}

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStorageLink ddmStorageLink : list) {
					if (structureId != ddmStorageLink.getStructureId()) {
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

			sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Returns the first ddm storage link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByStructureId_First(
			long structureId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByStructureId_First(
			structureId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm storage link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByStructureId_First(
		long structureId, OrderByComparator<DDMStorageLink> orderByComparator) {

		List<DDMStorageLink> list = findByStructureId(
			structureId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm storage link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByStructureId_Last(
			long structureId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByStructureId_Last(
			structureId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm storage link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByStructureId_Last(
		long structureId, OrderByComparator<DDMStorageLink> orderByComparator) {

		int count = countByStructureId(structureId);

		if (count == 0) {
			return null;
		}

		List<DDMStorageLink> list = findByStructureId(
			structureId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm storage links before and after the current ddm storage link in the ordered set where structureId = &#63;.
	 *
	 * @param storageLinkId the primary key of the current ddm storage link
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink[] findByStructureId_PrevAndNext(
			long storageLinkId, long structureId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = findByPrimaryKey(storageLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStorageLink[] array = new DDMStorageLinkImpl[3];

			array[0] = getByStructureId_PrevAndNext(
				session, ddmStorageLink, structureId, orderByComparator, true);

			array[1] = ddmStorageLink;

			array[2] = getByStructureId_PrevAndNext(
				session, ddmStorageLink, structureId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMStorageLink getByStructureId_PrevAndNext(
		Session session, DDMStorageLink ddmStorageLink, long structureId,
		OrderByComparator<DDMStorageLink> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

		sb.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

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
			sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(structureId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmStorageLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStorageLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm storage links where structureId = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 */
	@Override
	public void removeByStructureId(long structureId) {
		for (DDMStorageLink ddmStorageLink :
				findByStructureId(
					structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmStorageLink);
		}
	}

	/**
	 * Returns the number of ddm storage links where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByStructureId(long structureId) {
		FinderPath finderPath = _finderPathCountByStructureId;

		Object[] finderArgs = new Object[] {structureId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMSTORAGELINK_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

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

	private static final String _FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2 =
		"ddmStorageLink.structureId = ?";

	private FinderPath _finderPathWithPaginationFindByStructureVersionId;
	private FinderPath _finderPathWithoutPaginationFindByStructureVersionId;
	private FinderPath _finderPathCountByStructureVersionId;
	private FinderPath _finderPathWithPaginationCountByStructureVersionId;

	/**
	 * Returns all the ddm storage links where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @return the matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long structureVersionId) {

		return findByStructureVersionId(
			structureVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long structureVersionId, int start, int end) {

		return findByStructureVersionId(structureVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long structureVersionId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findByStructureVersionId(
			structureVersionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long structureVersionId, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByStructureVersionId;
				finderArgs = new Object[] {structureVersionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStructureVersionId;
			finderArgs = new Object[] {
				structureVersionId, start, end, orderByComparator
			};
		}

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStorageLink ddmStorageLink : list) {
					if (structureVersionId !=
							ddmStorageLink.getStructureVersionId()) {

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

			sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureVersionId);

				list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Returns the first ddm storage link in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByStructureVersionId_First(
			long structureVersionId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByStructureVersionId_First(
			structureVersionId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureVersionId=");
		sb.append(structureVersionId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm storage link in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByStructureVersionId_First(
		long structureVersionId,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		List<DDMStorageLink> list = findByStructureVersionId(
			structureVersionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm storage link in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link
	 * @throws NoSuchStorageLinkException if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink findByStructureVersionId_Last(
			long structureVersionId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByStructureVersionId_Last(
			structureVersionId, orderByComparator);

		if (ddmStorageLink != null) {
			return ddmStorageLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureVersionId=");
		sb.append(structureVersionId);

		sb.append("}");

		throw new NoSuchStorageLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm storage link in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm storage link, or <code>null</code> if a matching ddm storage link could not be found
	 */
	@Override
	public DDMStorageLink fetchByStructureVersionId_Last(
		long structureVersionId,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		int count = countByStructureVersionId(structureVersionId);

		if (count == 0) {
			return null;
		}

		List<DDMStorageLink> list = findByStructureVersionId(
			structureVersionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm storage links before and after the current ddm storage link in the ordered set where structureVersionId = &#63;.
	 *
	 * @param storageLinkId the primary key of the current ddm storage link
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink[] findByStructureVersionId_PrevAndNext(
			long storageLinkId, long structureVersionId,
			OrderByComparator<DDMStorageLink> orderByComparator)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = findByPrimaryKey(storageLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStorageLink[] array = new DDMStorageLinkImpl[3];

			array[0] = getByStructureVersionId_PrevAndNext(
				session, ddmStorageLink, structureVersionId, orderByComparator,
				true);

			array[1] = ddmStorageLink;

			array[2] = getByStructureVersionId_PrevAndNext(
				session, ddmStorageLink, structureVersionId, orderByComparator,
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

	protected DDMStorageLink getByStructureVersionId_PrevAndNext(
		Session session, DDMStorageLink ddmStorageLink, long structureVersionId,
		OrderByComparator<DDMStorageLink> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

		sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2);

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
			sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(structureVersionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmStorageLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStorageLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ddm storage links where structureVersionId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionIds the structure version IDs
	 * @return the matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long[] structureVersionIds) {

		return findByStructureVersionId(
			structureVersionIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links where structureVersionId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionIds the structure version IDs
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long[] structureVersionIds, int start, int end) {

		return findByStructureVersionId(structureVersionIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureVersionId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionIds the structure version IDs
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long[] structureVersionIds, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findByStructureVersionId(
			structureVersionIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links where structureVersionId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionIds the structure version IDs
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findByStructureVersionId(
		long[] structureVersionIds, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator,
		boolean useFinderCache) {

		if (structureVersionIds == null) {
			structureVersionIds = new long[0];
		}
		else if (structureVersionIds.length > 1) {
			structureVersionIds = ArrayUtil.sortedUnique(structureVersionIds);
		}

		if (structureVersionIds.length == 1) {
			return findByStructureVersionId(
				structureVersionIds[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					StringUtil.merge(structureVersionIds)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				StringUtil.merge(structureVersionIds), start, end,
				orderByComparator
			};
		}

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				_finderPathWithPaginationFindByStructureVersionId, finderArgs,
				this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStorageLink ddmStorageLink : list) {
					if (!ArrayUtil.contains(
							structureVersionIds,
							ddmStorageLink.getStructureVersionId())) {

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
					(structureVersionIds.length > databaseInMaxParameters)) {

					list = new ArrayList<DDMStorageLink>();

					long[][] structureVersionIdsPages =
						(long[][])ArrayUtil.split(
							structureVersionIds, databaseInMaxParameters);

					for (long[] structureVersionIdsPage :
							structureVersionIdsPages) {

						list.addAll(
							_findByStructureVersionId(
								structureVersionIdsPage, start, end,
								orderByComparator));
					}

					Collections.sort(list, orderByComparator);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = _findByStructureVersionId(
						structureVersionIds, start, end, orderByComparator);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByStructureVersionId,
						finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return list;
	}

	private List<DDMStorageLink> _findByStructureVersionId(
		long[] structureVersionIds, int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		List<DDMStorageLink> list = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_SELECT_DDMSTORAGELINK_WHERE);

		if (structureVersionIds.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_7);

			sb.append(StringUtil.merge(structureVersionIds));

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
			sb.append(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Removes all the ddm storage links where structureVersionId = &#63; from the database.
	 *
	 * @param structureVersionId the structure version ID
	 */
	@Override
	public void removeByStructureVersionId(long structureVersionId) {
		for (DDMStorageLink ddmStorageLink :
				findByStructureVersionId(
					structureVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmStorageLink);
		}
	}

	/**
	 * Returns the number of ddm storage links where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByStructureVersionId(long structureVersionId) {
		FinderPath finderPath = _finderPathCountByStructureVersionId;

		Object[] finderArgs = new Object[] {structureVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMSTORAGELINK_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureVersionId);

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

	/**
	 * Returns the number of ddm storage links where structureVersionId = any &#63;.
	 *
	 * @param structureVersionIds the structure version IDs
	 * @return the number of matching ddm storage links
	 */
	@Override
	public int countByStructureVersionId(long[] structureVersionIds) {
		if (structureVersionIds == null) {
			structureVersionIds = new long[0];
		}
		else if (structureVersionIds.length > 1) {
			structureVersionIds = ArrayUtil.sortedUnique(structureVersionIds);
		}

		Object[] finderArgs = new Object[] {
			StringUtil.merge(structureVersionIds)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByStructureVersionId, finderArgs,
			this);

		if (count == null) {
			try {
				if ((databaseInMaxParameters > 0) &&
					(structureVersionIds.length > databaseInMaxParameters)) {

					count = Long.valueOf(0);

					long[][] structureVersionIdsPages =
						(long[][])ArrayUtil.split(
							structureVersionIds, databaseInMaxParameters);

					for (long[] structureVersionIdsPage :
							structureVersionIdsPages) {

						count += Long.valueOf(
							_countByStructureVersionId(
								structureVersionIdsPage));
					}
				}
				else {
					count = Long.valueOf(
						_countByStructureVersionId(structureVersionIds));
				}

				finderCache.putResult(
					_finderPathWithPaginationCountByStructureVersionId,
					finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return count.intValue();
	}

	private int _countByStructureVersionId(long[] structureVersionIds) {
		Long count = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_COUNT_DDMSTORAGELINK_WHERE);

		if (structureVersionIds.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_7);

			sb.append(StringUtil.merge(structureVersionIds));

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

	private static final String
		_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2 =
			"ddmStorageLink.structureVersionId = ?";

	private static final String
		_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_7 =
			"ddmStorageLink.structureVersionId IN (";

	public DDMStorageLinkPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DDMStorageLink.class);

		setModelImplClass(DDMStorageLinkImpl.class);
		setModelPKClass(long.class);

		setTable(DDMStorageLinkTable.INSTANCE);
	}

	/**
	 * Caches the ddm storage link in the entity cache if it is enabled.
	 *
	 * @param ddmStorageLink the ddm storage link
	 */
	@Override
	public void cacheResult(DDMStorageLink ddmStorageLink) {
		entityCache.putResult(
			DDMStorageLinkImpl.class, ddmStorageLink.getPrimaryKey(),
			ddmStorageLink);

		finderCache.putResult(
			_finderPathFetchByClassPK,
			new Object[] {ddmStorageLink.getClassPK()}, ddmStorageLink);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm storage links in the entity cache if it is enabled.
	 *
	 * @param ddmStorageLinks the ddm storage links
	 */
	@Override
	public void cacheResult(List<DDMStorageLink> ddmStorageLinks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmStorageLinks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMStorageLink ddmStorageLink : ddmStorageLinks) {
			if (entityCache.getResult(
					DDMStorageLinkImpl.class, ddmStorageLink.getPrimaryKey()) ==
						null) {

				cacheResult(ddmStorageLink);
			}
		}
	}

	/**
	 * Clears the cache for all ddm storage links.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMStorageLinkImpl.class);

		finderCache.clearCache(DDMStorageLinkImpl.class);
	}

	/**
	 * Clears the cache for the ddm storage link.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMStorageLink ddmStorageLink) {
		entityCache.removeResult(DDMStorageLinkImpl.class, ddmStorageLink);
	}

	@Override
	public void clearCache(List<DDMStorageLink> ddmStorageLinks) {
		for (DDMStorageLink ddmStorageLink : ddmStorageLinks) {
			entityCache.removeResult(DDMStorageLinkImpl.class, ddmStorageLink);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMStorageLinkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDMStorageLinkImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMStorageLinkModelImpl ddmStorageLinkModelImpl) {

		Object[] args = new Object[] {ddmStorageLinkModelImpl.getClassPK()};

		finderCache.putResult(
			_finderPathFetchByClassPK, args, ddmStorageLinkModelImpl);
	}

	/**
	 * Creates a new ddm storage link with the primary key. Does not add the ddm storage link to the database.
	 *
	 * @param storageLinkId the primary key for the new ddm storage link
	 * @return the new ddm storage link
	 */
	@Override
	public DDMStorageLink create(long storageLinkId) {
		DDMStorageLink ddmStorageLink = new DDMStorageLinkImpl();

		ddmStorageLink.setNew(true);
		ddmStorageLink.setPrimaryKey(storageLinkId);

		String uuid = PortalUUIDUtil.generate();

		ddmStorageLink.setUuid(uuid);

		ddmStorageLink.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmStorageLink;
	}

	/**
	 * Removes the ddm storage link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param storageLinkId the primary key of the ddm storage link
	 * @return the ddm storage link that was removed
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink remove(long storageLinkId)
		throws NoSuchStorageLinkException {

		return remove((Serializable)storageLinkId);
	}

	/**
	 * Removes the ddm storage link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm storage link
	 * @return the ddm storage link that was removed
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink remove(Serializable primaryKey)
		throws NoSuchStorageLinkException {

		Session session = null;

		try {
			session = openSession();

			DDMStorageLink ddmStorageLink = (DDMStorageLink)session.get(
				DDMStorageLinkImpl.class, primaryKey);

			if (ddmStorageLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStorageLinkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmStorageLink);
		}
		catch (NoSuchStorageLinkException noSuchEntityException) {
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
	protected DDMStorageLink removeImpl(DDMStorageLink ddmStorageLink) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmStorageLink)) {
				ddmStorageLink = (DDMStorageLink)session.get(
					DDMStorageLinkImpl.class,
					ddmStorageLink.getPrimaryKeyObj());
			}

			if (ddmStorageLink != null) {
				session.delete(ddmStorageLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmStorageLink != null) {
			clearCache(ddmStorageLink);
		}

		return ddmStorageLink;
	}

	@Override
	public DDMStorageLink updateImpl(DDMStorageLink ddmStorageLink) {
		boolean isNew = ddmStorageLink.isNew();

		if (!(ddmStorageLink instanceof DDMStorageLinkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmStorageLink.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmStorageLink);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmStorageLink proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMStorageLink implementation " +
					ddmStorageLink.getClass());
		}

		DDMStorageLinkModelImpl ddmStorageLinkModelImpl =
			(DDMStorageLinkModelImpl)ddmStorageLink;

		if (Validator.isNull(ddmStorageLink.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ddmStorageLink.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmStorageLink);
			}
			else {
				ddmStorageLink = (DDMStorageLink)session.merge(ddmStorageLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMStorageLinkImpl.class, ddmStorageLinkModelImpl, false, true);

		cacheUniqueFindersCache(ddmStorageLinkModelImpl);

		if (isNew) {
			ddmStorageLink.setNew(false);
		}

		ddmStorageLink.resetOriginalValues();

		return ddmStorageLink;
	}

	/**
	 * Returns the ddm storage link with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm storage link
	 * @return the ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStorageLinkException {

		DDMStorageLink ddmStorageLink = fetchByPrimaryKey(primaryKey);

		if (ddmStorageLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStorageLinkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmStorageLink;
	}

	/**
	 * Returns the ddm storage link with the primary key or throws a <code>NoSuchStorageLinkException</code> if it could not be found.
	 *
	 * @param storageLinkId the primary key of the ddm storage link
	 * @return the ddm storage link
	 * @throws NoSuchStorageLinkException if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink findByPrimaryKey(long storageLinkId)
		throws NoSuchStorageLinkException {

		return findByPrimaryKey((Serializable)storageLinkId);
	}

	/**
	 * Returns the ddm storage link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param storageLinkId the primary key of the ddm storage link
	 * @return the ddm storage link, or <code>null</code> if a ddm storage link with the primary key could not be found
	 */
	@Override
	public DDMStorageLink fetchByPrimaryKey(long storageLinkId) {
		return fetchByPrimaryKey((Serializable)storageLinkId);
	}

	/**
	 * Returns all the ddm storage links.
	 *
	 * @return the ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm storage links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @return the range of ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm storage links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findAll(
		int start, int end,
		OrderByComparator<DDMStorageLink> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm storage links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStorageLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm storage links
	 * @param end the upper bound of the range of ddm storage links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm storage links
	 */
	@Override
	public List<DDMStorageLink> findAll(
		int start, int end, OrderByComparator<DDMStorageLink> orderByComparator,
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

		List<DDMStorageLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStorageLink>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMSTORAGELINK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMSTORAGELINK;

				sql = sql.concat(DDMStorageLinkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMStorageLink>)QueryUtil.list(
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
	 * Removes all the ddm storage links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMStorageLink ddmStorageLink : findAll()) {
			remove(ddmStorageLink);
		}
	}

	/**
	 * Returns the number of ddm storage links.
	 *
	 * @return the number of ddm storage links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DDMSTORAGELINK);

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
		return "storageLinkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMSTORAGELINK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMStorageLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm storage link persistence.
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

		_finderPathFetchByClassPK = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByClassPK",
			new String[] {Long.class.getName()}, new String[] {"classPK"},
			true);

		_finderPathWithPaginationFindByStructureId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStructureId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"structureId"}, true);

		_finderPathWithoutPaginationFindByStructureId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStructureId",
			new String[] {Long.class.getName()}, new String[] {"structureId"},
			true);

		_finderPathCountByStructureId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStructureId",
			new String[] {Long.class.getName()}, new String[] {"structureId"},
			false);

		_finderPathWithPaginationFindByStructureVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStructureVersionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"structureVersionId"}, true);

		_finderPathWithoutPaginationFindByStructureVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByStructureVersionId", new String[] {Long.class.getName()},
			new String[] {"structureVersionId"}, true);

		_finderPathCountByStructureVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByStructureVersionId", new String[] {Long.class.getName()},
			new String[] {"structureVersionId"}, false);

		_finderPathWithPaginationCountByStructureVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByStructureVersionId",
			new String[] {Long.class.getName()},
			new String[] {"structureVersionId"}, false);

		DDMStorageLinkUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMStorageLinkUtil.setPersistence(null);

		entityCache.removeCache(DDMStorageLinkImpl.class.getName());
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DDMSTORAGELINK =
		"SELECT ddmStorageLink FROM DDMStorageLink ddmStorageLink";

	private static final String _SQL_SELECT_DDMSTORAGELINK_WHERE =
		"SELECT ddmStorageLink FROM DDMStorageLink ddmStorageLink WHERE ";

	private static final String _SQL_COUNT_DDMSTORAGELINK =
		"SELECT COUNT(ddmStorageLink) FROM DDMStorageLink ddmStorageLink";

	private static final String _SQL_COUNT_DDMSTORAGELINK_WHERE =
		"SELECT COUNT(ddmStorageLink) FROM DDMStorageLink ddmStorageLink WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmStorageLink.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMStorageLink exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMStorageLink exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStorageLinkPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}