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
import com.liferay.portal.kernel.exception.NoSuchVirtualHostException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.model.VirtualHostTable;
import com.liferay.portal.kernel.service.persistence.VirtualHostPersistence;
import com.liferay.portal.kernel.service.persistence.VirtualHostUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.VirtualHostImpl;
import com.liferay.portal.model.impl.VirtualHostModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the virtual host service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VirtualHostPersistenceImpl
	extends BasePersistenceImpl<VirtualHost> implements VirtualHostPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VirtualHostUtil</code> to access the virtual host persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VirtualHostImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the virtual hosts where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual hosts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator,
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

		List<VirtualHost> list = null;

		if (useFinderCache) {
			list = (List<VirtualHost>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VirtualHost virtualHost : list) {
					if (companyId != virtualHost.getCompanyId()) {
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

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<VirtualHost>)QueryUtil.list(
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
	 * Returns the first virtual host in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByCompanyId_First(
			long companyId, OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the first virtual host in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByCompanyId_First(
		long companyId, OrderByComparator<VirtualHost> orderByComparator) {

		List<VirtualHost> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual host in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByCompanyId_Last(
			long companyId, OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the last virtual host in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByCompanyId_Last(
		long companyId, OrderByComparator<VirtualHost> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<VirtualHost> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual hosts before and after the current virtual host in the ordered set where companyId = &#63;.
	 *
	 * @param virtualHostId the primary key of the current virtual host
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual host
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost[] findByCompanyId_PrevAndNext(
			long virtualHostId, long companyId,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = findByPrimaryKey(virtualHostId);

		Session session = null;

		try {
			session = openSession();

			VirtualHost[] array = new VirtualHostImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, virtualHost, companyId, orderByComparator, true);

			array[1] = virtualHost;

			array[2] = getByCompanyId_PrevAndNext(
				session, virtualHost, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected VirtualHost getByCompanyId_PrevAndNext(
		Session session, VirtualHost virtualHost, long companyId,
		OrderByComparator<VirtualHost> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

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
			sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(virtualHost)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VirtualHost> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the virtual hosts where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (VirtualHost virtualHost :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(virtualHost);
		}
	}

	/**
	 * Returns the number of virtual hosts where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_VIRTUALHOST_WHERE);

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
		"virtualHost.companyId = ?";

	private FinderPath _finderPathFetchByHostname;

	/**
	 * Returns the virtual host where hostname = &#63; or throws a <code>NoSuchVirtualHostException</code> if it could not be found.
	 *
	 * @param hostname the hostname
	 * @return the matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByHostname(String hostname)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByHostname(hostname);

		if (virtualHost == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("hostname=");
			sb.append(hostname);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchVirtualHostException(sb.toString());
		}

		return virtualHost;
	}

	/**
	 * Returns the virtual host where hostname = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param hostname the hostname
	 * @return the matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByHostname(String hostname) {
		return fetchByHostname(hostname, true);
	}

	/**
	 * Returns the virtual host where hostname = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param hostname the hostname
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByHostname(
		String hostname, boolean useFinderCache) {

		hostname = Objects.toString(hostname, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {hostname};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByHostname, finderArgs, this);
		}

		if (result instanceof VirtualHost) {
			VirtualHost virtualHost = (VirtualHost)result;

			if (!Objects.equals(hostname, virtualHost.getHostname())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			boolean bindHostname = false;

			if (hostname.isEmpty()) {
				sb.append(_FINDER_COLUMN_HOSTNAME_HOSTNAME_3);
			}
			else {
				bindHostname = true;

				sb.append(_FINDER_COLUMN_HOSTNAME_HOSTNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindHostname) {
					queryPos.add(hostname);
				}

				List<VirtualHost> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByHostname, finderArgs, list);
					}
				}
				else {
					VirtualHost virtualHost = list.get(0);

					result = virtualHost;

					cacheResult(virtualHost);
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
			return (VirtualHost)result;
		}
	}

	/**
	 * Removes the virtual host where hostname = &#63; from the database.
	 *
	 * @param hostname the hostname
	 * @return the virtual host that was removed
	 */
	@Override
	public VirtualHost removeByHostname(String hostname)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = findByHostname(hostname);

		return remove(virtualHost);
	}

	/**
	 * Returns the number of virtual hosts where hostname = &#63;.
	 *
	 * @param hostname the hostname
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByHostname(String hostname) {
		VirtualHost virtualHost = fetchByHostname(hostname);

		if (virtualHost == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_HOSTNAME_HOSTNAME_2 =
		"virtualHost.hostname = ?";

	private static final String _FINDER_COLUMN_HOSTNAME_HOSTNAME_3 =
		"(virtualHost.hostname IS NULL OR virtualHost.hostname = '')";

	private FinderPath _finderPathWithPaginationFindByC_L;
	private FinderPath _finderPathWithoutPaginationFindByC_L;
	private FinderPath _finderPathCountByC_L;

	/**
	 * Returns all the virtual hosts where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @return the matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByC_L(long companyId, long layoutSetId) {
		return findByC_L(
			companyId, layoutSetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual hosts where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByC_L(
		long companyId, long layoutSetId, int start, int end) {

		return findByC_L(companyId, layoutSetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByC_L(
		long companyId, long layoutSetId, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator) {

		return findByC_L(
			companyId, layoutSetId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByC_L(
		long companyId, long layoutSetId, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_L;
				finderArgs = new Object[] {companyId, layoutSetId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_L;
			finderArgs = new Object[] {
				companyId, layoutSetId, start, end, orderByComparator
			};
		}

		List<VirtualHost> list = null;

		if (useFinderCache) {
			list = (List<VirtualHost>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VirtualHost virtualHost : list) {
					if ((companyId != virtualHost.getCompanyId()) ||
						(layoutSetId != virtualHost.getLayoutSetId())) {

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

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_L_LAYOUTSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(layoutSetId);

				list = (List<VirtualHost>)QueryUtil.list(
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
	 * Returns the first virtual host in the ordered set where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByC_L_First(
			long companyId, long layoutSetId,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByC_L_First(
			companyId, layoutSetId, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetId=");
		sb.append(layoutSetId);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the first virtual host in the ordered set where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByC_L_First(
		long companyId, long layoutSetId,
		OrderByComparator<VirtualHost> orderByComparator) {

		List<VirtualHost> list = findByC_L(
			companyId, layoutSetId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual host in the ordered set where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByC_L_Last(
			long companyId, long layoutSetId,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByC_L_Last(
			companyId, layoutSetId, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetId=");
		sb.append(layoutSetId);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the last virtual host in the ordered set where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByC_L_Last(
		long companyId, long layoutSetId,
		OrderByComparator<VirtualHost> orderByComparator) {

		int count = countByC_L(companyId, layoutSetId);

		if (count == 0) {
			return null;
		}

		List<VirtualHost> list = findByC_L(
			companyId, layoutSetId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual hosts before and after the current virtual host in the ordered set where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param virtualHostId the primary key of the current virtual host
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual host
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost[] findByC_L_PrevAndNext(
			long virtualHostId, long companyId, long layoutSetId,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = findByPrimaryKey(virtualHostId);

		Session session = null;

		try {
			session = openSession();

			VirtualHost[] array = new VirtualHostImpl[3];

			array[0] = getByC_L_PrevAndNext(
				session, virtualHost, companyId, layoutSetId, orderByComparator,
				true);

			array[1] = virtualHost;

			array[2] = getByC_L_PrevAndNext(
				session, virtualHost, companyId, layoutSetId, orderByComparator,
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

	protected VirtualHost getByC_L_PrevAndNext(
		Session session, VirtualHost virtualHost, long companyId,
		long layoutSetId, OrderByComparator<VirtualHost> orderByComparator,
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

		sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

		sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_L_LAYOUTSETID_2);

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
			sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		queryPos.add(layoutSetId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(virtualHost)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VirtualHost> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the virtual hosts where companyId = &#63; and layoutSetId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 */
	@Override
	public void removeByC_L(long companyId, long layoutSetId) {
		for (VirtualHost virtualHost :
				findByC_L(
					companyId, layoutSetId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(virtualHost);
		}
	}

	/**
	 * Returns the number of virtual hosts where companyId = &#63; and layoutSetId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByC_L(long companyId, long layoutSetId) {
		FinderPath finderPath = _finderPathCountByC_L;

		Object[] finderArgs = new Object[] {companyId, layoutSetId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_L_LAYOUTSETID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(layoutSetId);

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

	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 =
		"virtualHost.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_L_LAYOUTSETID_2 =
		"virtualHost.layoutSetId = ?";

	private FinderPath _finderPathWithPaginationFindByNotL_H;
	private FinderPath _finderPathWithPaginationCountByNotL_H;

	/**
	 * Returns all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @return the matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(long layoutSetId, String hostname) {
		return findByNotL_H(
			layoutSetId, hostname, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String hostname, int start, int end) {

		return findByNotL_H(layoutSetId, hostname, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String hostname, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator) {

		return findByNotL_H(
			layoutSetId, hostname, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String hostname, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator,
		boolean useFinderCache) {

		hostname = Objects.toString(hostname, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByNotL_H;
		finderArgs = new Object[] {
			layoutSetId, hostname, start, end, orderByComparator
		};

		List<VirtualHost> list = null;

		if (useFinderCache) {
			list = (List<VirtualHost>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VirtualHost virtualHost : list) {
					if ((layoutSetId == virtualHost.getLayoutSetId()) ||
						!hostname.equals(virtualHost.getHostname())) {

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

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_NOTL_H_LAYOUTSETID_2);

			boolean bindHostname = false;

			if (hostname.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_3);
			}
			else {
				bindHostname = true;

				sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(layoutSetId);

				if (bindHostname) {
					queryPos.add(hostname);
				}

				list = (List<VirtualHost>)QueryUtil.list(
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
	 * Returns the first virtual host in the ordered set where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByNotL_H_First(
			long layoutSetId, String hostname,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByNotL_H_First(
			layoutSetId, hostname, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetId!=");
		sb.append(layoutSetId);

		sb.append(", hostname=");
		sb.append(hostname);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the first virtual host in the ordered set where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByNotL_H_First(
		long layoutSetId, String hostname,
		OrderByComparator<VirtualHost> orderByComparator) {

		List<VirtualHost> list = findByNotL_H(
			layoutSetId, hostname, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last virtual host in the ordered set where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByNotL_H_Last(
			long layoutSetId, String hostname,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByNotL_H_Last(
			layoutSetId, hostname, orderByComparator);

		if (virtualHost != null) {
			return virtualHost;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetId!=");
		sb.append(layoutSetId);

		sb.append(", hostname=");
		sb.append(hostname);

		sb.append("}");

		throw new NoSuchVirtualHostException(sb.toString());
	}

	/**
	 * Returns the last virtual host in the ordered set where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByNotL_H_Last(
		long layoutSetId, String hostname,
		OrderByComparator<VirtualHost> orderByComparator) {

		int count = countByNotL_H(layoutSetId, hostname);

		if (count == 0) {
			return null;
		}

		List<VirtualHost> list = findByNotL_H(
			layoutSetId, hostname, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the virtual hosts before and after the current virtual host in the ordered set where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param virtualHostId the primary key of the current virtual host
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next virtual host
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost[] findByNotL_H_PrevAndNext(
			long virtualHostId, long layoutSetId, String hostname,
			OrderByComparator<VirtualHost> orderByComparator)
		throws NoSuchVirtualHostException {

		hostname = Objects.toString(hostname, "");

		VirtualHost virtualHost = findByPrimaryKey(virtualHostId);

		Session session = null;

		try {
			session = openSession();

			VirtualHost[] array = new VirtualHostImpl[3];

			array[0] = getByNotL_H_PrevAndNext(
				session, virtualHost, layoutSetId, hostname, orderByComparator,
				true);

			array[1] = virtualHost;

			array[2] = getByNotL_H_PrevAndNext(
				session, virtualHost, layoutSetId, hostname, orderByComparator,
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

	protected VirtualHost getByNotL_H_PrevAndNext(
		Session session, VirtualHost virtualHost, long layoutSetId,
		String hostname, OrderByComparator<VirtualHost> orderByComparator,
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

		sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

		sb.append(_FINDER_COLUMN_NOTL_H_LAYOUTSETID_2);

		boolean bindHostname = false;

		if (hostname.isEmpty()) {
			sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_3);
		}
		else {
			bindHostname = true;

			sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_2);
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
			sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(layoutSetId);

		if (bindHostname) {
			queryPos.add(hostname);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(virtualHost)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<VirtualHost> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the virtual hosts where layoutSetId &ne; &#63; and hostname = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostnames the hostnames
	 * @return the matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String[] hostnames) {

		return findByNotL_H(
			layoutSetId, hostnames, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostnames the hostnames
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String[] hostnames, int start, int end) {

		return findByNotL_H(layoutSetId, hostnames, start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostnames the hostnames
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String[] hostnames, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator) {

		return findByNotL_H(
			layoutSetId, hostnames, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostnames the hostnames
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching virtual hosts
	 */
	@Override
	public List<VirtualHost> findByNotL_H(
		long layoutSetId, String[] hostnames, int start, int end,
		OrderByComparator<VirtualHost> orderByComparator,
		boolean useFinderCache) {

		if (hostnames == null) {
			hostnames = new String[0];
		}
		else if (hostnames.length > 1) {
			for (int i = 0; i < hostnames.length; i++) {
				hostnames[i] = Objects.toString(hostnames[i], "");
			}

			hostnames = ArrayUtil.sortedUnique(hostnames);
		}

		if (hostnames.length == 1) {
			return findByNotL_H(
				layoutSetId, hostnames[0], start, end, orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					layoutSetId, StringUtil.merge(hostnames)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				layoutSetId, StringUtil.merge(hostnames), start, end,
				orderByComparator
			};
		}

		List<VirtualHost> list = null;

		if (useFinderCache) {
			list = (List<VirtualHost>)FinderCacheUtil.getResult(
				_finderPathWithPaginationFindByNotL_H, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (VirtualHost virtualHost : list) {
					if ((layoutSetId == virtualHost.getLayoutSetId()) ||
						!ArrayUtil.contains(
							hostnames, virtualHost.getHostname())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_NOTL_H_LAYOUTSETID_2);

			if (hostnames.length > 0) {
				sb.append("(");

				for (int i = 0; i < hostnames.length; i++) {
					String hostname = hostnames[i];

					if (hostname.isEmpty()) {
						sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_3);
					}
					else {
						sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_2);
					}

					if ((i + 1) < hostnames.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VirtualHostModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(layoutSetId);

				for (String hostname : hostnames) {
					if ((hostname != null) && !hostname.isEmpty()) {
						queryPos.add(hostname);
					}
				}

				list = (List<VirtualHost>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationFindByNotL_H, finderArgs,
						list);
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
	 * Removes all the virtual hosts where layoutSetId &ne; &#63; and hostname = &#63; from the database.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 */
	@Override
	public void removeByNotL_H(long layoutSetId, String hostname) {
		for (VirtualHost virtualHost :
				findByNotL_H(
					layoutSetId, hostname, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(virtualHost);
		}
	}

	/**
	 * Returns the number of virtual hosts where layoutSetId &ne; &#63; and hostname = &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostname the hostname
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByNotL_H(long layoutSetId, String hostname) {
		hostname = Objects.toString(hostname, "");

		FinderPath finderPath = _finderPathWithPaginationCountByNotL_H;

		Object[] finderArgs = new Object[] {layoutSetId, hostname};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_NOTL_H_LAYOUTSETID_2);

			boolean bindHostname = false;

			if (hostname.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_3);
			}
			else {
				bindHostname = true;

				sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(layoutSetId);

				if (bindHostname) {
					queryPos.add(hostname);
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
	 * Returns the number of virtual hosts where layoutSetId &ne; &#63; and hostname = any &#63;.
	 *
	 * @param layoutSetId the layout set ID
	 * @param hostnames the hostnames
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByNotL_H(long layoutSetId, String[] hostnames) {
		if (hostnames == null) {
			hostnames = new String[0];
		}
		else if (hostnames.length > 1) {
			for (int i = 0; i < hostnames.length; i++) {
				hostnames[i] = Objects.toString(hostnames[i], "");
			}

			hostnames = ArrayUtil.sortedUnique(hostnames);
		}

		Object[] finderArgs = new Object[] {
			layoutSetId, StringUtil.merge(hostnames)
		};

		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathWithPaginationCountByNotL_H, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_NOTL_H_LAYOUTSETID_2);

			if (hostnames.length > 0) {
				sb.append("(");

				for (int i = 0; i < hostnames.length; i++) {
					String hostname = hostnames[i];

					if (hostname.isEmpty()) {
						sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_3);
					}
					else {
						sb.append(_FINDER_COLUMN_NOTL_H_HOSTNAME_2);
					}

					if ((i + 1) < hostnames.length) {
						sb.append(WHERE_OR);
					}
				}

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

				queryPos.add(layoutSetId);

				for (String hostname : hostnames) {
					if ((hostname != null) && !hostname.isEmpty()) {
						queryPos.add(hostname);
					}
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathWithPaginationCountByNotL_H, finderArgs, count);
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

	private static final String _FINDER_COLUMN_NOTL_H_LAYOUTSETID_2 =
		"virtualHost.layoutSetId != ? AND ";

	private static final String _FINDER_COLUMN_NOTL_H_HOSTNAME_2 =
		"virtualHost.hostname = ?";

	private static final String _FINDER_COLUMN_NOTL_H_HOSTNAME_3 =
		"(virtualHost.hostname IS NULL OR virtualHost.hostname = '')";

	private FinderPath _finderPathFetchByC_L_D;

	/**
	 * Returns the virtual host where companyId = &#63; and layoutSetId = &#63; and defaultVirtualHost = &#63; or throws a <code>NoSuchVirtualHostException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param defaultVirtualHost the default virtual host
	 * @return the matching virtual host
	 * @throws NoSuchVirtualHostException if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost findByC_L_D(
			long companyId, long layoutSetId, boolean defaultVirtualHost)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByC_L_D(
			companyId, layoutSetId, defaultVirtualHost);

		if (virtualHost == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", layoutSetId=");
			sb.append(layoutSetId);

			sb.append(", defaultVirtualHost=");
			sb.append(defaultVirtualHost);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchVirtualHostException(sb.toString());
		}

		return virtualHost;
	}

	/**
	 * Returns the virtual host where companyId = &#63; and layoutSetId = &#63; and defaultVirtualHost = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param defaultVirtualHost the default virtual host
	 * @return the matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByC_L_D(
		long companyId, long layoutSetId, boolean defaultVirtualHost) {

		return fetchByC_L_D(companyId, layoutSetId, defaultVirtualHost, true);
	}

	/**
	 * Returns the virtual host where companyId = &#63; and layoutSetId = &#63; and defaultVirtualHost = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param defaultVirtualHost the default virtual host
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching virtual host, or <code>null</code> if a matching virtual host could not be found
	 */
	@Override
	public VirtualHost fetchByC_L_D(
		long companyId, long layoutSetId, boolean defaultVirtualHost,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {
				companyId, layoutSetId, defaultVirtualHost
			};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByC_L_D, finderArgs, this);
		}

		if (result instanceof VirtualHost) {
			VirtualHost virtualHost = (VirtualHost)result;

			if ((companyId != virtualHost.getCompanyId()) ||
				(layoutSetId != virtualHost.getLayoutSetId()) ||
				(defaultVirtualHost != virtualHost.isDefaultVirtualHost())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_VIRTUALHOST_WHERE);

			sb.append(_FINDER_COLUMN_C_L_D_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_L_D_LAYOUTSETID_2);

			sb.append(_FINDER_COLUMN_C_L_D_DEFAULTVIRTUALHOST_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(layoutSetId);

				queryPos.add(defaultVirtualHost);

				List<VirtualHost> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByC_L_D, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, layoutSetId, defaultVirtualHost
								};
							}

							_log.warn(
								"VirtualHostPersistenceImpl.fetchByC_L_D(long, long, boolean, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					VirtualHost virtualHost = list.get(0);

					result = virtualHost;

					cacheResult(virtualHost);
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
			return (VirtualHost)result;
		}
	}

	/**
	 * Removes the virtual host where companyId = &#63; and layoutSetId = &#63; and defaultVirtualHost = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param defaultVirtualHost the default virtual host
	 * @return the virtual host that was removed
	 */
	@Override
	public VirtualHost removeByC_L_D(
			long companyId, long layoutSetId, boolean defaultVirtualHost)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = findByC_L_D(
			companyId, layoutSetId, defaultVirtualHost);

		return remove(virtualHost);
	}

	/**
	 * Returns the number of virtual hosts where companyId = &#63; and layoutSetId = &#63; and defaultVirtualHost = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetId the layout set ID
	 * @param defaultVirtualHost the default virtual host
	 * @return the number of matching virtual hosts
	 */
	@Override
	public int countByC_L_D(
		long companyId, long layoutSetId, boolean defaultVirtualHost) {

		VirtualHost virtualHost = fetchByC_L_D(
			companyId, layoutSetId, defaultVirtualHost);

		if (virtualHost == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_C_L_D_COMPANYID_2 =
		"virtualHost.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_L_D_LAYOUTSETID_2 =
		"virtualHost.layoutSetId = ? AND ";

	private static final String _FINDER_COLUMN_C_L_D_DEFAULTVIRTUALHOST_2 =
		"virtualHost.defaultVirtualHost = ?";

	public VirtualHostPersistenceImpl() {
		setModelClass(VirtualHost.class);

		setModelImplClass(VirtualHostImpl.class);
		setModelPKClass(long.class);

		setTable(VirtualHostTable.INSTANCE);
	}

	/**
	 * Caches the virtual host in the entity cache if it is enabled.
	 *
	 * @param virtualHost the virtual host
	 */
	@Override
	public void cacheResult(VirtualHost virtualHost) {
		EntityCacheUtil.putResult(
			VirtualHostImpl.class, virtualHost.getPrimaryKey(), virtualHost);

		FinderCacheUtil.putResult(
			_finderPathFetchByHostname,
			new Object[] {virtualHost.getHostname()}, virtualHost);

		FinderCacheUtil.putResult(
			_finderPathFetchByC_L_D,
			new Object[] {
				virtualHost.getCompanyId(), virtualHost.getLayoutSetId(),
				virtualHost.isDefaultVirtualHost()
			},
			virtualHost);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the virtual hosts in the entity cache if it is enabled.
	 *
	 * @param virtualHosts the virtual hosts
	 */
	@Override
	public void cacheResult(List<VirtualHost> virtualHosts) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (virtualHosts.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (VirtualHost virtualHost : virtualHosts) {
			if (EntityCacheUtil.getResult(
					VirtualHostImpl.class, virtualHost.getPrimaryKey()) ==
						null) {

				cacheResult(virtualHost);
			}
		}
	}

	/**
	 * Clears the cache for all virtual hosts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(VirtualHostImpl.class);

		FinderCacheUtil.clearCache(VirtualHostImpl.class);
	}

	/**
	 * Clears the cache for the virtual host.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VirtualHost virtualHost) {
		EntityCacheUtil.removeResult(VirtualHostImpl.class, virtualHost);
	}

	@Override
	public void clearCache(List<VirtualHost> virtualHosts) {
		for (VirtualHost virtualHost : virtualHosts) {
			EntityCacheUtil.removeResult(VirtualHostImpl.class, virtualHost);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(VirtualHostImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(VirtualHostImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		VirtualHostModelImpl virtualHostModelImpl) {

		Object[] args = new Object[] {virtualHostModelImpl.getHostname()};

		FinderCacheUtil.putResult(
			_finderPathFetchByHostname, args, virtualHostModelImpl);

		args = new Object[] {
			virtualHostModelImpl.getCompanyId(),
			virtualHostModelImpl.getLayoutSetId(),
			virtualHostModelImpl.isDefaultVirtualHost()
		};

		FinderCacheUtil.putResult(
			_finderPathFetchByC_L_D, args, virtualHostModelImpl);
	}

	/**
	 * Creates a new virtual host with the primary key. Does not add the virtual host to the database.
	 *
	 * @param virtualHostId the primary key for the new virtual host
	 * @return the new virtual host
	 */
	@Override
	public VirtualHost create(long virtualHostId) {
		VirtualHost virtualHost = new VirtualHostImpl();

		virtualHost.setNew(true);
		virtualHost.setPrimaryKey(virtualHostId);

		return virtualHost;
	}

	/**
	 * Removes the virtual host with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param virtualHostId the primary key of the virtual host
	 * @return the virtual host that was removed
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost remove(long virtualHostId)
		throws NoSuchVirtualHostException {

		return remove((Serializable)virtualHostId);
	}

	/**
	 * Removes the virtual host with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the virtual host
	 * @return the virtual host that was removed
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost remove(Serializable primaryKey)
		throws NoSuchVirtualHostException {

		Session session = null;

		try {
			session = openSession();

			VirtualHost virtualHost = (VirtualHost)session.get(
				VirtualHostImpl.class, primaryKey);

			if (virtualHost == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVirtualHostException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(virtualHost);
		}
		catch (NoSuchVirtualHostException noSuchEntityException) {
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
	protected VirtualHost removeImpl(VirtualHost virtualHost) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(virtualHost)) {
				virtualHost = (VirtualHost)session.get(
					VirtualHostImpl.class, virtualHost.getPrimaryKeyObj());
			}

			if (virtualHost != null) {
				session.delete(virtualHost);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (virtualHost != null) {
			clearCache(virtualHost);
		}

		return virtualHost;
	}

	@Override
	public VirtualHost updateImpl(VirtualHost virtualHost) {
		boolean isNew = virtualHost.isNew();

		if (!(virtualHost instanceof VirtualHostModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(virtualHost.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(virtualHost);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in virtualHost proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom VirtualHost implementation " +
					virtualHost.getClass());
		}

		VirtualHostModelImpl virtualHostModelImpl =
			(VirtualHostModelImpl)virtualHost;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(virtualHost);
			}
			else {
				virtualHost = (VirtualHost)session.merge(virtualHost);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			VirtualHostImpl.class, virtualHostModelImpl, false, true);

		cacheUniqueFindersCache(virtualHostModelImpl);

		if (isNew) {
			virtualHost.setNew(false);
		}

		virtualHost.resetOriginalValues();

		return virtualHost;
	}

	/**
	 * Returns the virtual host with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the virtual host
	 * @return the virtual host
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVirtualHostException {

		VirtualHost virtualHost = fetchByPrimaryKey(primaryKey);

		if (virtualHost == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVirtualHostException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return virtualHost;
	}

	/**
	 * Returns the virtual host with the primary key or throws a <code>NoSuchVirtualHostException</code> if it could not be found.
	 *
	 * @param virtualHostId the primary key of the virtual host
	 * @return the virtual host
	 * @throws NoSuchVirtualHostException if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost findByPrimaryKey(long virtualHostId)
		throws NoSuchVirtualHostException {

		return findByPrimaryKey((Serializable)virtualHostId);
	}

	/**
	 * Returns the virtual host with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param virtualHostId the primary key of the virtual host
	 * @return the virtual host, or <code>null</code> if a virtual host with the primary key could not be found
	 */
	@Override
	public VirtualHost fetchByPrimaryKey(long virtualHostId) {
		return fetchByPrimaryKey((Serializable)virtualHostId);
	}

	/**
	 * Returns all the virtual hosts.
	 *
	 * @return the virtual hosts
	 */
	@Override
	public List<VirtualHost> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the virtual hosts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @return the range of virtual hosts
	 */
	@Override
	public List<VirtualHost> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the virtual hosts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of virtual hosts
	 */
	@Override
	public List<VirtualHost> findAll(
		int start, int end, OrderByComparator<VirtualHost> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the virtual hosts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VirtualHostModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of virtual hosts
	 * @param end the upper bound of the range of virtual hosts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of virtual hosts
	 */
	@Override
	public List<VirtualHost> findAll(
		int start, int end, OrderByComparator<VirtualHost> orderByComparator,
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

		List<VirtualHost> list = null;

		if (useFinderCache) {
			list = (List<VirtualHost>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VIRTUALHOST);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VIRTUALHOST;

				sql = sql.concat(VirtualHostModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<VirtualHost>)QueryUtil.list(
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
	 * Removes all the virtual hosts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VirtualHost virtualHost : findAll()) {
			remove(virtualHost);
		}
	}

	/**
	 * Returns the number of virtual hosts.
	 *
	 * @return the number of virtual hosts
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VIRTUALHOST);

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
		return "virtualHostId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VIRTUALHOST;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VirtualHostModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the virtual host persistence.
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

		_finderPathFetchByHostname = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByHostname",
			new String[] {String.class.getName()}, new String[] {"hostname"},
			true);

		_finderPathWithPaginationFindByC_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "layoutSetId"}, true);

		_finderPathWithoutPaginationFindByC_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"companyId", "layoutSetId"}, true);

		_finderPathCountByC_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"companyId", "layoutSetId"}, false);

		_finderPathWithPaginationFindByNotL_H = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNotL_H",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"layoutSetId", "hostname"}, true);

		_finderPathWithPaginationCountByNotL_H = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByNotL_H",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"layoutSetId", "hostname"}, false);

		_finderPathFetchByC_L_D = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_L_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			new String[] {"companyId", "layoutSetId", "defaultVirtualHost"},
			true);

		VirtualHostUtil.setPersistence(this);
	}

	public void destroy() {
		VirtualHostUtil.setPersistence(null);

		EntityCacheUtil.removeCache(VirtualHostImpl.class.getName());
	}

	private static final String _SQL_SELECT_VIRTUALHOST =
		"SELECT virtualHost FROM VirtualHost virtualHost";

	private static final String _SQL_SELECT_VIRTUALHOST_WHERE =
		"SELECT virtualHost FROM VirtualHost virtualHost WHERE ";

	private static final String _SQL_COUNT_VIRTUALHOST =
		"SELECT COUNT(virtualHost) FROM VirtualHost virtualHost";

	private static final String _SQL_COUNT_VIRTUALHOST_WHERE =
		"SELECT COUNT(virtualHost) FROM VirtualHost virtualHost WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "virtualHost.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No VirtualHost exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No VirtualHost exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		VirtualHostPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}