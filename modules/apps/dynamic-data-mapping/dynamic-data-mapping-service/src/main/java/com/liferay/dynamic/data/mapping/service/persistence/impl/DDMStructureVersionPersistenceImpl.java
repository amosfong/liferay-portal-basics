/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchStructureVersionException;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersionTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionUtil;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

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
 * The persistence implementation for the ddm structure version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMStructureVersionPersistence.class)
public class DDMStructureVersionPersistenceImpl
	extends BasePersistenceImpl<DDMStructureVersion>
	implements DDMStructureVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMStructureVersionUtil</code> to access the ddm structure version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMStructureVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByStructureId;
	private FinderPath _finderPathWithoutPaginationFindByStructureId;
	private FinderPath _finderPathCountByStructureId;

	/**
	 * Returns all the ddm structure versions where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(long structureId) {
		return findByStructureId(
			structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @return the range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(
		long structureId, int start, int end) {

		return findByStructureId(structureId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		return findByStructureId(
			structureId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		List<DDMStructureVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureVersion ddmStructureVersion : list) {
					if (structureId != ddmStructureVersion.getStructureId()) {
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

			sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				list = (List<DDMStructureVersion>)QueryUtil.list(
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
	 * Returns the first ddm structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure version
	 * @throws NoSuchStructureVersionException if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByStructureId_First(
			long structureId,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByStructureId_First(
			structureId, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStructureVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByStructureId_First(
		long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		List<DDMStructureVersion> list = findByStructureId(
			structureId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure version
	 * @throws NoSuchStructureVersionException if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByStructureId_Last(
			long structureId,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByStructureId_Last(
			structureId, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStructureVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByStructureId_Last(
		long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		int count = countByStructureId(structureId);

		if (count == 0) {
			return null;
		}

		List<DDMStructureVersion> list = findByStructureId(
			structureId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm structure versions before and after the current ddm structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureVersionId the primary key of the current ddm structure version
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm structure version
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion[] findByStructureId_PrevAndNext(
			long structureVersionId, long structureId,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = findByPrimaryKey(
			structureVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion[] array = new DDMStructureVersionImpl[3];

			array[0] = getByStructureId_PrevAndNext(
				session, ddmStructureVersion, structureId, orderByComparator,
				true);

			array[1] = ddmStructureVersion;

			array[2] = getByStructureId_PrevAndNext(
				session, ddmStructureVersion, structureId, orderByComparator,
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

	protected DDMStructureVersion getByStructureId_PrevAndNext(
		Session session, DDMStructureVersion ddmStructureVersion,
		long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

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
			sb.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
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
						ddmStructureVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStructureVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm structure versions where structureId = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 */
	@Override
	public void removeByStructureId(long structureId) {
		for (DDMStructureVersion ddmStructureVersion :
				findByStructureId(
					structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of ddm structure versions where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the number of matching ddm structure versions
	 */
	@Override
	public int countByStructureId(long structureId) {
		FinderPath finderPath = _finderPathCountByStructureId;

		Object[] finderArgs = new Object[] {structureId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMSTRUCTUREVERSION_WHERE);

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
		"ddmStructureVersion.structureId = ?";

	private FinderPath _finderPathFetchByS_V;

	/**
	 * Returns the ddm structure version where structureId = &#63; and version = &#63; or throws a <code>NoSuchStructureVersionException</code> if it could not be found.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the matching ddm structure version
	 * @throws NoSuchStructureVersionException if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_V(long structureId, String version)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByS_V(
			structureId, version);

		if (ddmStructureVersion == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("structureId=");
			sb.append(structureId);

			sb.append(", version=");
			sb.append(version);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchStructureVersionException(sb.toString());
		}

		return ddmStructureVersion;
	}

	/**
	 * Returns the ddm structure version where structureId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_V(long structureId, String version) {
		return fetchByS_V(structureId, version, true);
	}

	/**
	 * Returns the ddm structure version where structureId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_V(
		long structureId, String version, boolean useFinderCache) {

		version = Objects.toString(version, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {structureId, version};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByS_V, finderArgs, this);
		}

		if (result instanceof DDMStructureVersion) {
			DDMStructureVersion ddmStructureVersion =
				(DDMStructureVersion)result;

			if ((structureId != ddmStructureVersion.getStructureId()) ||
				!Objects.equals(version, ddmStructureVersion.getVersion())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			sb.append(_FINDER_COLUMN_S_V_STRUCTUREID_2);

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_S_V_VERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				if (bindVersion) {
					queryPos.add(version);
				}

				List<DDMStructureVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByS_V, finderArgs, list);
					}
				}
				else {
					DDMStructureVersion ddmStructureVersion = list.get(0);

					result = ddmStructureVersion;

					cacheResult(ddmStructureVersion);
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
			return (DDMStructureVersion)result;
		}
	}

	/**
	 * Removes the ddm structure version where structureId = &#63; and version = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the ddm structure version that was removed
	 */
	@Override
	public DDMStructureVersion removeByS_V(long structureId, String version)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = findByS_V(
			structureId, version);

		return remove(ddmStructureVersion);
	}

	/**
	 * Returns the number of ddm structure versions where structureId = &#63; and version = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the number of matching ddm structure versions
	 */
	@Override
	public int countByS_V(long structureId, String version) {
		DDMStructureVersion ddmStructureVersion = fetchByS_V(
			structureId, version);

		if (ddmStructureVersion == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_S_V_STRUCTUREID_2 =
		"ddmStructureVersion.structureId = ? AND ";

	private static final String _FINDER_COLUMN_S_V_VERSION_2 =
		"ddmStructureVersion.version = ?";

	private static final String _FINDER_COLUMN_S_V_VERSION_3 =
		"(ddmStructureVersion.version IS NULL OR ddmStructureVersion.version = '')";

	private FinderPath _finderPathWithPaginationFindByS_S;
	private FinderPath _finderPathWithoutPaginationFindByS_S;
	private FinderPath _finderPathCountByS_S;

	/**
	 * Returns all the ddm structure versions where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @return the matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(long structureId, int status) {
		return findByS_S(
			structureId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @return the range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(
		long structureId, int status, int start, int end) {

		return findByS_S(structureId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(
		long structureId, int status, int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		return findByS_S(
			structureId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(
		long structureId, int status, int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByS_S;
				finderArgs = new Object[] {structureId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByS_S;
			finderArgs = new Object[] {
				structureId, status, start, end, orderByComparator
			};
		}

		List<DDMStructureVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureVersion ddmStructureVersion : list) {
					if ((structureId != ddmStructureVersion.getStructureId()) ||
						(status != ddmStructureVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			sb.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

			sb.append(_FINDER_COLUMN_S_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				queryPos.add(status);

				list = (List<DDMStructureVersion>)QueryUtil.list(
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
	 * Returns the first ddm structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure version
	 * @throws NoSuchStructureVersionException if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_S_First(
			long structureId, int status,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByS_S_First(
			structureId, status, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchStructureVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_S_First(
		long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		List<DDMStructureVersion> list = findByS_S(
			structureId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure version
	 * @throws NoSuchStructureVersionException if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_S_Last(
			long structureId, int status,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByS_S_Last(
			structureId, status, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchStructureVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure version, or <code>null</code> if a matching ddm structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_S_Last(
		long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		int count = countByS_S(structureId, status);

		if (count == 0) {
			return null;
		}

		List<DDMStructureVersion> list = findByS_S(
			structureId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm structure versions before and after the current ddm structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureVersionId the primary key of the current ddm structure version
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm structure version
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion[] findByS_S_PrevAndNext(
			long structureVersionId, long structureId, int status,
			OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = findByPrimaryKey(
			structureVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion[] array = new DDMStructureVersionImpl[3];

			array[0] = getByS_S_PrevAndNext(
				session, ddmStructureVersion, structureId, status,
				orderByComparator, true);

			array[1] = ddmStructureVersion;

			array[2] = getByS_S_PrevAndNext(
				session, ddmStructureVersion, structureId, status,
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

	protected DDMStructureVersion getByS_S_PrevAndNext(
		Session session, DDMStructureVersion ddmStructureVersion,
		long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

		sb.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

		sb.append(_FINDER_COLUMN_S_S_STATUS_2);

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
			sb.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(structureId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmStructureVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStructureVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm structure versions where structureId = &#63; and status = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 */
	@Override
	public void removeByS_S(long structureId, int status) {
		for (DDMStructureVersion ddmStructureVersion :
				findByS_S(
					structureId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of ddm structure versions where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @return the number of matching ddm structure versions
	 */
	@Override
	public int countByS_S(long structureId, int status) {
		FinderPath finderPath = _finderPathCountByS_S;

		Object[] finderArgs = new Object[] {structureId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMSTRUCTUREVERSION_WHERE);

			sb.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

			sb.append(_FINDER_COLUMN_S_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				queryPos.add(status);

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

	private static final String _FINDER_COLUMN_S_S_STRUCTUREID_2 =
		"ddmStructureVersion.structureId = ? AND ";

	private static final String _FINDER_COLUMN_S_S_STATUS_2 =
		"ddmStructureVersion.status = ?";

	public DDMStructureVersionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DDMStructureVersion.class);

		setModelImplClass(DDMStructureVersionImpl.class);
		setModelPKClass(long.class);

		setTable(DDMStructureVersionTable.INSTANCE);
	}

	/**
	 * Caches the ddm structure version in the entity cache if it is enabled.
	 *
	 * @param ddmStructureVersion the ddm structure version
	 */
	@Override
	public void cacheResult(DDMStructureVersion ddmStructureVersion) {
		entityCache.putResult(
			DDMStructureVersionImpl.class, ddmStructureVersion.getPrimaryKey(),
			ddmStructureVersion);

		finderCache.putResult(
			_finderPathFetchByS_V,
			new Object[] {
				ddmStructureVersion.getStructureId(),
				ddmStructureVersion.getVersion()
			},
			ddmStructureVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm structure versions in the entity cache if it is enabled.
	 *
	 * @param ddmStructureVersions the ddm structure versions
	 */
	@Override
	public void cacheResult(List<DDMStructureVersion> ddmStructureVersions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmStructureVersions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMStructureVersion ddmStructureVersion : ddmStructureVersions) {
			DDMStructureVersion cachedDDMStructureVersion =
				(DDMStructureVersion)entityCache.getResult(
					DDMStructureVersionImpl.class,
					ddmStructureVersion.getPrimaryKey());

			if (cachedDDMStructureVersion == null) {
				cacheResult(ddmStructureVersion);
			}
			else {
				DDMStructureVersionModelImpl ddmStructureVersionModelImpl =
					(DDMStructureVersionModelImpl)ddmStructureVersion;
				DDMStructureVersionModelImpl
					cachedDDMStructureVersionModelImpl =
						(DDMStructureVersionModelImpl)cachedDDMStructureVersion;

				ddmStructureVersionModelImpl.setDDMForm(
					cachedDDMStructureVersionModelImpl.getDDMForm());
			}
		}
	}

	/**
	 * Clears the cache for all ddm structure versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMStructureVersionImpl.class);

		finderCache.clearCache(DDMStructureVersionImpl.class);
	}

	/**
	 * Clears the cache for the ddm structure version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMStructureVersion ddmStructureVersion) {
		entityCache.removeResult(
			DDMStructureVersionImpl.class, ddmStructureVersion);
	}

	@Override
	public void clearCache(List<DDMStructureVersion> ddmStructureVersions) {
		for (DDMStructureVersion ddmStructureVersion : ddmStructureVersions) {
			entityCache.removeResult(
				DDMStructureVersionImpl.class, ddmStructureVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMStructureVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDMStructureVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMStructureVersionModelImpl ddmStructureVersionModelImpl) {

		Object[] args = new Object[] {
			ddmStructureVersionModelImpl.getStructureId(),
			ddmStructureVersionModelImpl.getVersion()
		};

		finderCache.putResult(
			_finderPathFetchByS_V, args, ddmStructureVersionModelImpl);
	}

	/**
	 * Creates a new ddm structure version with the primary key. Does not add the ddm structure version to the database.
	 *
	 * @param structureVersionId the primary key for the new ddm structure version
	 * @return the new ddm structure version
	 */
	@Override
	public DDMStructureVersion create(long structureVersionId) {
		DDMStructureVersion ddmStructureVersion = new DDMStructureVersionImpl();

		ddmStructureVersion.setNew(true);
		ddmStructureVersion.setPrimaryKey(structureVersionId);

		ddmStructureVersion.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmStructureVersion;
	}

	/**
	 * Removes the ddm structure version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param structureVersionId the primary key of the ddm structure version
	 * @return the ddm structure version that was removed
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion remove(long structureVersionId)
		throws NoSuchStructureVersionException {

		return remove((Serializable)structureVersionId);
	}

	/**
	 * Removes the ddm structure version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm structure version
	 * @return the ddm structure version that was removed
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion remove(Serializable primaryKey)
		throws NoSuchStructureVersionException {

		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion ddmStructureVersion =
				(DDMStructureVersion)session.get(
					DDMStructureVersionImpl.class, primaryKey);

			if (ddmStructureVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStructureVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmStructureVersion);
		}
		catch (NoSuchStructureVersionException noSuchEntityException) {
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
	protected DDMStructureVersion removeImpl(
		DDMStructureVersion ddmStructureVersion) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmStructureVersion)) {
				ddmStructureVersion = (DDMStructureVersion)session.get(
					DDMStructureVersionImpl.class,
					ddmStructureVersion.getPrimaryKeyObj());
			}

			if (ddmStructureVersion != null) {
				session.delete(ddmStructureVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmStructureVersion != null) {
			clearCache(ddmStructureVersion);
		}

		return ddmStructureVersion;
	}

	@Override
	public DDMStructureVersion updateImpl(
		DDMStructureVersion ddmStructureVersion) {

		boolean isNew = ddmStructureVersion.isNew();

		if (!(ddmStructureVersion instanceof DDMStructureVersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmStructureVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmStructureVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmStructureVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMStructureVersion implementation " +
					ddmStructureVersion.getClass());
		}

		DDMStructureVersionModelImpl ddmStructureVersionModelImpl =
			(DDMStructureVersionModelImpl)ddmStructureVersion;

		if (isNew && (ddmStructureVersion.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				ddmStructureVersion.setCreateDate(date);
			}
			else {
				ddmStructureVersion.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmStructureVersion);
			}
			else {
				ddmStructureVersion = (DDMStructureVersion)session.merge(
					ddmStructureVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMStructureVersionImpl.class, ddmStructureVersionModelImpl, false,
			true);

		cacheUniqueFindersCache(ddmStructureVersionModelImpl);

		if (isNew) {
			ddmStructureVersion.setNew(false);
		}

		ddmStructureVersion.resetOriginalValues();

		return ddmStructureVersion;
	}

	/**
	 * Returns the ddm structure version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm structure version
	 * @return the ddm structure version
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStructureVersionException {

		DDMStructureVersion ddmStructureVersion = fetchByPrimaryKey(primaryKey);

		if (ddmStructureVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStructureVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmStructureVersion;
	}

	/**
	 * Returns the ddm structure version with the primary key or throws a <code>NoSuchStructureVersionException</code> if it could not be found.
	 *
	 * @param structureVersionId the primary key of the ddm structure version
	 * @return the ddm structure version
	 * @throws NoSuchStructureVersionException if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion findByPrimaryKey(long structureVersionId)
		throws NoSuchStructureVersionException {

		return findByPrimaryKey((Serializable)structureVersionId);
	}

	/**
	 * Returns the ddm structure version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param structureVersionId the primary key of the ddm structure version
	 * @return the ddm structure version, or <code>null</code> if a ddm structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion fetchByPrimaryKey(long structureVersionId) {
		return fetchByPrimaryKey((Serializable)structureVersionId);
	}

	/**
	 * Returns all the ddm structure versions.
	 *
	 * @return the ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @return the range of ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure versions
	 * @param end the upper bound of the range of ddm structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		List<DDMStructureVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMSTRUCTUREVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMSTRUCTUREVERSION;

				sql = sql.concat(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMStructureVersion>)QueryUtil.list(
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
	 * Removes all the ddm structure versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMStructureVersion ddmStructureVersion : findAll()) {
			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of ddm structure versions.
	 *
	 * @return the number of ddm structure versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_DDMSTRUCTUREVERSION);

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
		return "structureVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMSTRUCTUREVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMStructureVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm structure version persistence.
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

		_finderPathFetchByS_V = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByS_V",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"structureId", "version"}, true);

		_finderPathWithPaginationFindByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"structureId", "status"}, true);

		_finderPathWithoutPaginationFindByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"structureId", "status"}, true);

		_finderPathCountByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"structureId", "status"}, false);

		DDMStructureVersionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMStructureVersionUtil.setPersistence(null);

		entityCache.removeCache(DDMStructureVersionImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMSTRUCTUREVERSION =
		"SELECT ddmStructureVersion FROM DDMStructureVersion ddmStructureVersion";

	private static final String _SQL_SELECT_DDMSTRUCTUREVERSION_WHERE =
		"SELECT ddmStructureVersion FROM DDMStructureVersion ddmStructureVersion WHERE ";

	private static final String _SQL_COUNT_DDMSTRUCTUREVERSION =
		"SELECT COUNT(ddmStructureVersion) FROM DDMStructureVersion ddmStructureVersion";

	private static final String _SQL_COUNT_DDMSTRUCTUREVERSION_WHERE =
		"SELECT COUNT(ddmStructureVersion) FROM DDMStructureVersion ddmStructureVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmStructureVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMStructureVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMStructureVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStructureVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}