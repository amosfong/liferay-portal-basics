/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.saml.persistence.exception.NoSuchSpIdpConnectionException;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.model.SamlSpIdpConnectionTable;
import com.liferay.saml.persistence.model.impl.SamlSpIdpConnectionImpl;
import com.liferay.saml.persistence.model.impl.SamlSpIdpConnectionModelImpl;
import com.liferay.saml.persistence.service.persistence.SamlSpIdpConnectionPersistence;
import com.liferay.saml.persistence.service.persistence.SamlSpIdpConnectionUtil;
import com.liferay.saml.persistence.service.persistence.impl.constants.SamlPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
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
 * The persistence implementation for the saml sp idp connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @generated
 */
@Component(service = SamlSpIdpConnectionPersistence.class)
public class SamlSpIdpConnectionPersistenceImpl
	extends BasePersistenceImpl<SamlSpIdpConnection>
	implements SamlSpIdpConnectionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SamlSpIdpConnectionUtil</code> to access the saml sp idp connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SamlSpIdpConnectionImpl.class.getName();

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
	 * Returns all the saml sp idp connections where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp idp connections where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @return the range of matching saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp idp connections where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<SamlSpIdpConnection> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the saml sp idp connections where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<SamlSpIdpConnection> orderByComparator,
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

		List<SamlSpIdpConnection> list = null;

		if (useFinderCache) {
			list = (List<SamlSpIdpConnection>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SamlSpIdpConnection samlSpIdpConnection : list) {
					if (companyId != samlSpIdpConnection.getCompanyId()) {
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

			sb.append(_SQL_SELECT_SAMLSPIDPCONNECTION_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SamlSpIdpConnectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<SamlSpIdpConnection>)QueryUtil.list(
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
	 * Returns the first saml sp idp connection in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection findByCompanyId_First(
			long companyId,
			OrderByComparator<SamlSpIdpConnection> orderByComparator)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (samlSpIdpConnection != null) {
			return samlSpIdpConnection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchSpIdpConnectionException(sb.toString());
	}

	/**
	 * Returns the first saml sp idp connection in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml sp idp connection, or <code>null</code> if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection fetchByCompanyId_First(
		long companyId,
		OrderByComparator<SamlSpIdpConnection> orderByComparator) {

		List<SamlSpIdpConnection> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last saml sp idp connection in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection findByCompanyId_Last(
			long companyId,
			OrderByComparator<SamlSpIdpConnection> orderByComparator)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (samlSpIdpConnection != null) {
			return samlSpIdpConnection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchSpIdpConnectionException(sb.toString());
	}

	/**
	 * Returns the last saml sp idp connection in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml sp idp connection, or <code>null</code> if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<SamlSpIdpConnection> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<SamlSpIdpConnection> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the saml sp idp connections before and after the current saml sp idp connection in the ordered set where companyId = &#63;.
	 *
	 * @param samlSpIdpConnectionId the primary key of the current saml sp idp connection
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection[] findByCompanyId_PrevAndNext(
			long samlSpIdpConnectionId, long companyId,
			OrderByComparator<SamlSpIdpConnection> orderByComparator)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = findByPrimaryKey(
			samlSpIdpConnectionId);

		Session session = null;

		try {
			session = openSession();

			SamlSpIdpConnection[] array = new SamlSpIdpConnectionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, samlSpIdpConnection, companyId, orderByComparator,
				true);

			array[1] = samlSpIdpConnection;

			array[2] = getByCompanyId_PrevAndNext(
				session, samlSpIdpConnection, companyId, orderByComparator,
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

	protected SamlSpIdpConnection getByCompanyId_PrevAndNext(
		Session session, SamlSpIdpConnection samlSpIdpConnection,
		long companyId,
		OrderByComparator<SamlSpIdpConnection> orderByComparator,
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

		sb.append(_SQL_SELECT_SAMLSPIDPCONNECTION_WHERE);

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
			sb.append(SamlSpIdpConnectionModelImpl.ORDER_BY_JPQL);
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
						samlSpIdpConnection)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SamlSpIdpConnection> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the saml sp idp connections where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (SamlSpIdpConnection samlSpIdpConnection :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(samlSpIdpConnection);
		}
	}

	/**
	 * Returns the number of saml sp idp connections where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching saml sp idp connections
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SAMLSPIDPCONNECTION_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"samlSpIdpConnection.companyId = ?";

	private FinderPath _finderPathFetchByC_SIEI;

	/**
	 * Returns the saml sp idp connection where companyId = &#63; and samlIdpEntityId = &#63; or throws a <code>NoSuchSpIdpConnectionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param samlIdpEntityId the saml idp entity ID
	 * @return the matching saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection findByC_SIEI(
			long companyId, String samlIdpEntityId)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = fetchByC_SIEI(
			companyId, samlIdpEntityId);

		if (samlSpIdpConnection == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", samlIdpEntityId=");
			sb.append(samlIdpEntityId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSpIdpConnectionException(sb.toString());
		}

		return samlSpIdpConnection;
	}

	/**
	 * Returns the saml sp idp connection where companyId = &#63; and samlIdpEntityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param samlIdpEntityId the saml idp entity ID
	 * @return the matching saml sp idp connection, or <code>null</code> if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection fetchByC_SIEI(
		long companyId, String samlIdpEntityId) {

		return fetchByC_SIEI(companyId, samlIdpEntityId, true);
	}

	/**
	 * Returns the saml sp idp connection where companyId = &#63; and samlIdpEntityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching saml sp idp connection, or <code>null</code> if a matching saml sp idp connection could not be found
	 */
	@Override
	public SamlSpIdpConnection fetchByC_SIEI(
		long companyId, String samlIdpEntityId, boolean useFinderCache) {

		samlIdpEntityId = Objects.toString(samlIdpEntityId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, samlIdpEntityId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_SIEI, finderArgs, this);
		}

		if (result instanceof SamlSpIdpConnection) {
			SamlSpIdpConnection samlSpIdpConnection =
				(SamlSpIdpConnection)result;

			if ((companyId != samlSpIdpConnection.getCompanyId()) ||
				!Objects.equals(
					samlIdpEntityId,
					samlSpIdpConnection.getSamlIdpEntityId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SAMLSPIDPCONNECTION_WHERE);

			sb.append(_FINDER_COLUMN_C_SIEI_COMPANYID_2);

			boolean bindSamlIdpEntityId = false;

			if (samlIdpEntityId.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_SIEI_SAMLIDPENTITYID_3);
			}
			else {
				bindSamlIdpEntityId = true;

				sb.append(_FINDER_COLUMN_C_SIEI_SAMLIDPENTITYID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindSamlIdpEntityId) {
					queryPos.add(samlIdpEntityId);
				}

				List<SamlSpIdpConnection> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_SIEI, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, samlIdpEntityId
								};
							}

							_log.warn(
								"SamlSpIdpConnectionPersistenceImpl.fetchByC_SIEI(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SamlSpIdpConnection samlSpIdpConnection = list.get(0);

					result = samlSpIdpConnection;

					cacheResult(samlSpIdpConnection);
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
			return (SamlSpIdpConnection)result;
		}
	}

	/**
	 * Removes the saml sp idp connection where companyId = &#63; and samlIdpEntityId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param samlIdpEntityId the saml idp entity ID
	 * @return the saml sp idp connection that was removed
	 */
	@Override
	public SamlSpIdpConnection removeByC_SIEI(
			long companyId, String samlIdpEntityId)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = findByC_SIEI(
			companyId, samlIdpEntityId);

		return remove(samlSpIdpConnection);
	}

	/**
	 * Returns the number of saml sp idp connections where companyId = &#63; and samlIdpEntityId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param samlIdpEntityId the saml idp entity ID
	 * @return the number of matching saml sp idp connections
	 */
	@Override
	public int countByC_SIEI(long companyId, String samlIdpEntityId) {
		SamlSpIdpConnection samlSpIdpConnection = fetchByC_SIEI(
			companyId, samlIdpEntityId);

		if (samlSpIdpConnection == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_C_SIEI_COMPANYID_2 =
		"samlSpIdpConnection.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_SIEI_SAMLIDPENTITYID_2 =
		"samlSpIdpConnection.samlIdpEntityId = ?";

	private static final String _FINDER_COLUMN_C_SIEI_SAMLIDPENTITYID_3 =
		"(samlSpIdpConnection.samlIdpEntityId IS NULL OR samlSpIdpConnection.samlIdpEntityId = '')";

	public SamlSpIdpConnectionPersistenceImpl() {
		setModelClass(SamlSpIdpConnection.class);

		setModelImplClass(SamlSpIdpConnectionImpl.class);
		setModelPKClass(long.class);

		setTable(SamlSpIdpConnectionTable.INSTANCE);
	}

	/**
	 * Caches the saml sp idp connection in the entity cache if it is enabled.
	 *
	 * @param samlSpIdpConnection the saml sp idp connection
	 */
	@Override
	public void cacheResult(SamlSpIdpConnection samlSpIdpConnection) {
		entityCache.putResult(
			SamlSpIdpConnectionImpl.class, samlSpIdpConnection.getPrimaryKey(),
			samlSpIdpConnection);

		finderCache.putResult(
			_finderPathFetchByC_SIEI,
			new Object[] {
				samlSpIdpConnection.getCompanyId(),
				samlSpIdpConnection.getSamlIdpEntityId()
			},
			samlSpIdpConnection);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the saml sp idp connections in the entity cache if it is enabled.
	 *
	 * @param samlSpIdpConnections the saml sp idp connections
	 */
	@Override
	public void cacheResult(List<SamlSpIdpConnection> samlSpIdpConnections) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (samlSpIdpConnections.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SamlSpIdpConnection samlSpIdpConnection : samlSpIdpConnections) {
			if (entityCache.getResult(
					SamlSpIdpConnectionImpl.class,
					samlSpIdpConnection.getPrimaryKey()) == null) {

				cacheResult(samlSpIdpConnection);
			}
		}
	}

	/**
	 * Clears the cache for all saml sp idp connections.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SamlSpIdpConnectionImpl.class);

		finderCache.clearCache(SamlSpIdpConnectionImpl.class);
	}

	/**
	 * Clears the cache for the saml sp idp connection.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlSpIdpConnection samlSpIdpConnection) {
		entityCache.removeResult(
			SamlSpIdpConnectionImpl.class, samlSpIdpConnection);
	}

	@Override
	public void clearCache(List<SamlSpIdpConnection> samlSpIdpConnections) {
		for (SamlSpIdpConnection samlSpIdpConnection : samlSpIdpConnections) {
			entityCache.removeResult(
				SamlSpIdpConnectionImpl.class, samlSpIdpConnection);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SamlSpIdpConnectionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SamlSpIdpConnectionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SamlSpIdpConnectionModelImpl samlSpIdpConnectionModelImpl) {

		Object[] args = new Object[] {
			samlSpIdpConnectionModelImpl.getCompanyId(),
			samlSpIdpConnectionModelImpl.getSamlIdpEntityId()
		};

		finderCache.putResult(
			_finderPathFetchByC_SIEI, args, samlSpIdpConnectionModelImpl);
	}

	/**
	 * Creates a new saml sp idp connection with the primary key. Does not add the saml sp idp connection to the database.
	 *
	 * @param samlSpIdpConnectionId the primary key for the new saml sp idp connection
	 * @return the new saml sp idp connection
	 */
	@Override
	public SamlSpIdpConnection create(long samlSpIdpConnectionId) {
		SamlSpIdpConnection samlSpIdpConnection = new SamlSpIdpConnectionImpl();

		samlSpIdpConnection.setNew(true);
		samlSpIdpConnection.setPrimaryKey(samlSpIdpConnectionId);

		samlSpIdpConnection.setCompanyId(CompanyThreadLocal.getCompanyId());

		return samlSpIdpConnection;
	}

	/**
	 * Removes the saml sp idp connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	 * @return the saml sp idp connection that was removed
	 * @throws NoSuchSpIdpConnectionException if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection remove(long samlSpIdpConnectionId)
		throws NoSuchSpIdpConnectionException {

		return remove((Serializable)samlSpIdpConnectionId);
	}

	/**
	 * Removes the saml sp idp connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml sp idp connection
	 * @return the saml sp idp connection that was removed
	 * @throws NoSuchSpIdpConnectionException if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection remove(Serializable primaryKey)
		throws NoSuchSpIdpConnectionException {

		Session session = null;

		try {
			session = openSession();

			SamlSpIdpConnection samlSpIdpConnection =
				(SamlSpIdpConnection)session.get(
					SamlSpIdpConnectionImpl.class, primaryKey);

			if (samlSpIdpConnection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSpIdpConnectionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(samlSpIdpConnection);
		}
		catch (NoSuchSpIdpConnectionException noSuchEntityException) {
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
	protected SamlSpIdpConnection removeImpl(
		SamlSpIdpConnection samlSpIdpConnection) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlSpIdpConnection)) {
				samlSpIdpConnection = (SamlSpIdpConnection)session.get(
					SamlSpIdpConnectionImpl.class,
					samlSpIdpConnection.getPrimaryKeyObj());
			}

			if (samlSpIdpConnection != null) {
				session.delete(samlSpIdpConnection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (samlSpIdpConnection != null) {
			clearCache(samlSpIdpConnection);
		}

		return samlSpIdpConnection;
	}

	@Override
	public SamlSpIdpConnection updateImpl(
		SamlSpIdpConnection samlSpIdpConnection) {

		boolean isNew = samlSpIdpConnection.isNew();

		if (!(samlSpIdpConnection instanceof SamlSpIdpConnectionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(samlSpIdpConnection.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					samlSpIdpConnection);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in samlSpIdpConnection proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SamlSpIdpConnection implementation " +
					samlSpIdpConnection.getClass());
		}

		SamlSpIdpConnectionModelImpl samlSpIdpConnectionModelImpl =
			(SamlSpIdpConnectionModelImpl)samlSpIdpConnection;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (samlSpIdpConnection.getCreateDate() == null)) {
			if (serviceContext == null) {
				samlSpIdpConnection.setCreateDate(date);
			}
			else {
				samlSpIdpConnection.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!samlSpIdpConnectionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				samlSpIdpConnection.setModifiedDate(date);
			}
			else {
				samlSpIdpConnection.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(samlSpIdpConnection);
			}
			else {
				samlSpIdpConnection = (SamlSpIdpConnection)session.merge(
					samlSpIdpConnection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SamlSpIdpConnectionImpl.class, samlSpIdpConnectionModelImpl, false,
			true);

		cacheUniqueFindersCache(samlSpIdpConnectionModelImpl);

		if (isNew) {
			samlSpIdpConnection.setNew(false);
		}

		samlSpIdpConnection.resetOriginalValues();

		return samlSpIdpConnection;
	}

	/**
	 * Returns the saml sp idp connection with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp idp connection
	 * @return the saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSpIdpConnectionException {

		SamlSpIdpConnection samlSpIdpConnection = fetchByPrimaryKey(primaryKey);

		if (samlSpIdpConnection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSpIdpConnectionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return samlSpIdpConnection;
	}

	/**
	 * Returns the saml sp idp connection with the primary key or throws a <code>NoSuchSpIdpConnectionException</code> if it could not be found.
	 *
	 * @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	 * @return the saml sp idp connection
	 * @throws NoSuchSpIdpConnectionException if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection findByPrimaryKey(long samlSpIdpConnectionId)
		throws NoSuchSpIdpConnectionException {

		return findByPrimaryKey((Serializable)samlSpIdpConnectionId);
	}

	/**
	 * Returns the saml sp idp connection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	 * @return the saml sp idp connection, or <code>null</code> if a saml sp idp connection with the primary key could not be found
	 */
	@Override
	public SamlSpIdpConnection fetchByPrimaryKey(long samlSpIdpConnectionId) {
		return fetchByPrimaryKey((Serializable)samlSpIdpConnectionId);
	}

	/**
	 * Returns all the saml sp idp connections.
	 *
	 * @return the saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp idp connections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @return the range of saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp idp connections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findAll(
		int start, int end,
		OrderByComparator<SamlSpIdpConnection> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the saml sp idp connections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SamlSpIdpConnectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp idp connections
	 * @param end the upper bound of the range of saml sp idp connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of saml sp idp connections
	 */
	@Override
	public List<SamlSpIdpConnection> findAll(
		int start, int end,
		OrderByComparator<SamlSpIdpConnection> orderByComparator,
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

		List<SamlSpIdpConnection> list = null;

		if (useFinderCache) {
			list = (List<SamlSpIdpConnection>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SAMLSPIDPCONNECTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLSPIDPCONNECTION;

				sql = sql.concat(SamlSpIdpConnectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SamlSpIdpConnection>)QueryUtil.list(
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
	 * Removes all the saml sp idp connections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SamlSpIdpConnection samlSpIdpConnection : findAll()) {
			remove(samlSpIdpConnection);
		}
	}

	/**
	 * Returns the number of saml sp idp connections.
	 *
	 * @return the number of saml sp idp connections
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
					_SQL_COUNT_SAMLSPIDPCONNECTION);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "samlSpIdpConnectionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SAMLSPIDPCONNECTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SamlSpIdpConnectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the saml sp idp connection persistence.
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

		_finderPathFetchByC_SIEI = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_SIEI",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "samlIdpEntityId"}, true);

		SamlSpIdpConnectionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SamlSpIdpConnectionUtil.setPersistence(null);

		entityCache.removeCache(SamlSpIdpConnectionImpl.class.getName());
	}

	@Override
	@Reference(
		target = SamlPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = SamlPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = SamlPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SAMLSPIDPCONNECTION =
		"SELECT samlSpIdpConnection FROM SamlSpIdpConnection samlSpIdpConnection";

	private static final String _SQL_SELECT_SAMLSPIDPCONNECTION_WHERE =
		"SELECT samlSpIdpConnection FROM SamlSpIdpConnection samlSpIdpConnection WHERE ";

	private static final String _SQL_COUNT_SAMLSPIDPCONNECTION =
		"SELECT COUNT(samlSpIdpConnection) FROM SamlSpIdpConnection samlSpIdpConnection";

	private static final String _SQL_COUNT_SAMLSPIDPCONNECTION_WHERE =
		"SELECT COUNT(samlSpIdpConnection) FROM SamlSpIdpConnection samlSpIdpConnection WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "samlSpIdpConnection.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SamlSpIdpConnection exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SamlSpIdpConnection exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SamlSpIdpConnectionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}