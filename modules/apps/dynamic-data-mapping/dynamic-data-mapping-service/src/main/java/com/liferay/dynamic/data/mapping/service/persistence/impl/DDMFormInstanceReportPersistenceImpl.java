/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchFormInstanceReportException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportUtil;
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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the ddm form instance report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMFormInstanceReportPersistence.class)
public class DDMFormInstanceReportPersistenceImpl
	extends BasePersistenceImpl<DDMFormInstanceReport>
	implements DDMFormInstanceReportPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMFormInstanceReportUtil</code> to access the ddm form instance report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMFormInstanceReportImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByFormInstanceId;

	/**
	 * Returns the ddm form instance report where formInstanceId = &#63; or throws a <code>NoSuchFormInstanceReportException</code> if it could not be found.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report
	 * @throws NoSuchFormInstanceReportException if a matching ddm form instance report could not be found
	 */
	@Override
	public DDMFormInstanceReport findByFormInstanceId(long formInstanceId)
		throws NoSuchFormInstanceReportException {

		DDMFormInstanceReport ddmFormInstanceReport = fetchByFormInstanceId(
			formInstanceId);

		if (ddmFormInstanceReport == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formInstanceId=");
			sb.append(formInstanceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormInstanceReportException(sb.toString());
		}

		return ddmFormInstanceReport;
	}

	/**
	 * Returns the ddm form instance report where formInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report, or <code>null</code> if a matching ddm form instance report could not be found
	 */
	@Override
	public DDMFormInstanceReport fetchByFormInstanceId(long formInstanceId) {
		return fetchByFormInstanceId(formInstanceId, true);
	}

	/**
	 * Returns the ddm form instance report where formInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm form instance report, or <code>null</code> if a matching ddm form instance report could not be found
	 */
	@Override
	public DDMFormInstanceReport fetchByFormInstanceId(
		long formInstanceId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formInstanceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFormInstanceId, finderArgs, this);
		}

		if (result instanceof DDMFormInstanceReport) {
			DDMFormInstanceReport ddmFormInstanceReport =
				(DDMFormInstanceReport)result;

			if (formInstanceId != ddmFormInstanceReport.getFormInstanceId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DDMFORMINSTANCEREPORT_WHERE);

			sb.append(_FINDER_COLUMN_FORMINSTANCEID_FORMINSTANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceId);

				List<DDMFormInstanceReport> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFormInstanceId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {formInstanceId};
							}

							_log.warn(
								"DDMFormInstanceReportPersistenceImpl.fetchByFormInstanceId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DDMFormInstanceReport ddmFormInstanceReport = list.get(0);

					result = ddmFormInstanceReport;

					cacheResult(ddmFormInstanceReport);
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
			return (DDMFormInstanceReport)result;
		}
	}

	/**
	 * Removes the ddm form instance report where formInstanceId = &#63; from the database.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the ddm form instance report that was removed
	 */
	@Override
	public DDMFormInstanceReport removeByFormInstanceId(long formInstanceId)
		throws NoSuchFormInstanceReportException {

		DDMFormInstanceReport ddmFormInstanceReport = findByFormInstanceId(
			formInstanceId);

		return remove(ddmFormInstanceReport);
	}

	/**
	 * Returns the number of ddm form instance reports where formInstanceId = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the number of matching ddm form instance reports
	 */
	@Override
	public int countByFormInstanceId(long formInstanceId) {
		DDMFormInstanceReport ddmFormInstanceReport = fetchByFormInstanceId(
			formInstanceId);

		if (ddmFormInstanceReport == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_FORMINSTANCEID_FORMINSTANCEID_2 =
		"ddmFormInstanceReport.formInstanceId = ?";

	public DDMFormInstanceReportPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("data", "data_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DDMFormInstanceReport.class);

		setModelImplClass(DDMFormInstanceReportImpl.class);
		setModelPKClass(long.class);

		setTable(DDMFormInstanceReportTable.INSTANCE);
	}

	/**
	 * Caches the ddm form instance report in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReport the ddm form instance report
	 */
	@Override
	public void cacheResult(DDMFormInstanceReport ddmFormInstanceReport) {
		entityCache.putResult(
			DDMFormInstanceReportImpl.class,
			ddmFormInstanceReport.getPrimaryKey(), ddmFormInstanceReport);

		finderCache.putResult(
			_finderPathFetchByFormInstanceId,
			new Object[] {ddmFormInstanceReport.getFormInstanceId()},
			ddmFormInstanceReport);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm form instance reports in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReports the ddm form instance reports
	 */
	@Override
	public void cacheResult(
		List<DDMFormInstanceReport> ddmFormInstanceReports) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmFormInstanceReports.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMFormInstanceReport ddmFormInstanceReport :
				ddmFormInstanceReports) {

			if (entityCache.getResult(
					DDMFormInstanceReportImpl.class,
					ddmFormInstanceReport.getPrimaryKey()) == null) {

				cacheResult(ddmFormInstanceReport);
			}
		}
	}

	/**
	 * Clears the cache for all ddm form instance reports.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMFormInstanceReportImpl.class);

		finderCache.clearCache(DDMFormInstanceReportImpl.class);
	}

	/**
	 * Clears the cache for the ddm form instance report.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMFormInstanceReport ddmFormInstanceReport) {
		entityCache.removeResult(
			DDMFormInstanceReportImpl.class, ddmFormInstanceReport);
	}

	@Override
	public void clearCache(List<DDMFormInstanceReport> ddmFormInstanceReports) {
		for (DDMFormInstanceReport ddmFormInstanceReport :
				ddmFormInstanceReports) {

			entityCache.removeResult(
				DDMFormInstanceReportImpl.class, ddmFormInstanceReport);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMFormInstanceReportImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				DDMFormInstanceReportImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMFormInstanceReportModelImpl ddmFormInstanceReportModelImpl) {

		Object[] args = new Object[] {
			ddmFormInstanceReportModelImpl.getFormInstanceId()
		};

		finderCache.putResult(
			_finderPathFetchByFormInstanceId, args,
			ddmFormInstanceReportModelImpl);
	}

	/**
	 * Creates a new ddm form instance report with the primary key. Does not add the ddm form instance report to the database.
	 *
	 * @param formInstanceReportId the primary key for the new ddm form instance report
	 * @return the new ddm form instance report
	 */
	@Override
	public DDMFormInstanceReport create(long formInstanceReportId) {
		DDMFormInstanceReport ddmFormInstanceReport =
			new DDMFormInstanceReportImpl();

		ddmFormInstanceReport.setNew(true);
		ddmFormInstanceReport.setPrimaryKey(formInstanceReportId);

		ddmFormInstanceReport.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmFormInstanceReport;
	}

	/**
	 * Removes the ddm form instance report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportId the primary key of the ddm form instance report
	 * @return the ddm form instance report that was removed
	 * @throws NoSuchFormInstanceReportException if a ddm form instance report with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReport remove(long formInstanceReportId)
		throws NoSuchFormInstanceReportException {

		return remove((Serializable)formInstanceReportId);
	}

	/**
	 * Removes the ddm form instance report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm form instance report
	 * @return the ddm form instance report that was removed
	 * @throws NoSuchFormInstanceReportException if a ddm form instance report with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReport remove(Serializable primaryKey)
		throws NoSuchFormInstanceReportException {

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceReport ddmFormInstanceReport =
				(DDMFormInstanceReport)session.get(
					DDMFormInstanceReportImpl.class, primaryKey);

			if (ddmFormInstanceReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormInstanceReportException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmFormInstanceReport);
		}
		catch (NoSuchFormInstanceReportException noSuchEntityException) {
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
	protected DDMFormInstanceReport removeImpl(
		DDMFormInstanceReport ddmFormInstanceReport) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmFormInstanceReport)) {
				ddmFormInstanceReport = (DDMFormInstanceReport)session.get(
					DDMFormInstanceReportImpl.class,
					ddmFormInstanceReport.getPrimaryKeyObj());
			}

			if (ddmFormInstanceReport != null) {
				session.delete(ddmFormInstanceReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmFormInstanceReport != null) {
			clearCache(ddmFormInstanceReport);
		}

		return ddmFormInstanceReport;
	}

	@Override
	public DDMFormInstanceReport updateImpl(
		DDMFormInstanceReport ddmFormInstanceReport) {

		boolean isNew = ddmFormInstanceReport.isNew();

		if (!(ddmFormInstanceReport instanceof
				DDMFormInstanceReportModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmFormInstanceReport.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmFormInstanceReport);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmFormInstanceReport proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMFormInstanceReport implementation " +
					ddmFormInstanceReport.getClass());
		}

		DDMFormInstanceReportModelImpl ddmFormInstanceReportModelImpl =
			(DDMFormInstanceReportModelImpl)ddmFormInstanceReport;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (ddmFormInstanceReport.getCreateDate() == null)) {
			if (serviceContext == null) {
				ddmFormInstanceReport.setCreateDate(date);
			}
			else {
				ddmFormInstanceReport.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!ddmFormInstanceReportModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ddmFormInstanceReport.setModifiedDate(date);
			}
			else {
				ddmFormInstanceReport.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmFormInstanceReport);
			}
			else {
				ddmFormInstanceReport = (DDMFormInstanceReport)session.merge(
					ddmFormInstanceReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMFormInstanceReportImpl.class, ddmFormInstanceReportModelImpl,
			false, true);

		cacheUniqueFindersCache(ddmFormInstanceReportModelImpl);

		if (isNew) {
			ddmFormInstanceReport.setNew(false);
		}

		ddmFormInstanceReport.resetOriginalValues();

		return ddmFormInstanceReport;
	}

	/**
	 * Returns the ddm form instance report with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm form instance report
	 * @return the ddm form instance report
	 * @throws NoSuchFormInstanceReportException if a ddm form instance report with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormInstanceReportException {

		DDMFormInstanceReport ddmFormInstanceReport = fetchByPrimaryKey(
			primaryKey);

		if (ddmFormInstanceReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormInstanceReportException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmFormInstanceReport;
	}

	/**
	 * Returns the ddm form instance report with the primary key or throws a <code>NoSuchFormInstanceReportException</code> if it could not be found.
	 *
	 * @param formInstanceReportId the primary key of the ddm form instance report
	 * @return the ddm form instance report
	 * @throws NoSuchFormInstanceReportException if a ddm form instance report with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReport findByPrimaryKey(long formInstanceReportId)
		throws NoSuchFormInstanceReportException {

		return findByPrimaryKey((Serializable)formInstanceReportId);
	}

	/**
	 * Returns the ddm form instance report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formInstanceReportId the primary key of the ddm form instance report
	 * @return the ddm form instance report, or <code>null</code> if a ddm form instance report with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReport fetchByPrimaryKey(long formInstanceReportId) {
		return fetchByPrimaryKey((Serializable)formInstanceReportId);
	}

	/**
	 * Returns all the ddm form instance reports.
	 *
	 * @return the ddm form instance reports
	 */
	@Override
	public List<DDMFormInstanceReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance reports
	 * @param end the upper bound of the range of ddm form instance reports (not inclusive)
	 * @return the range of ddm form instance reports
	 */
	@Override
	public List<DDMFormInstanceReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance reports
	 * @param end the upper bound of the range of ddm form instance reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm form instance reports
	 */
	@Override
	public List<DDMFormInstanceReport> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReport> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance reports
	 * @param end the upper bound of the range of ddm form instance reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm form instance reports
	 */
	@Override
	public List<DDMFormInstanceReport> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReport> orderByComparator,
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

		List<DDMFormInstanceReport> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceReport>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMFORMINSTANCEREPORT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMFORMINSTANCEREPORT;

				sql = sql.concat(DDMFormInstanceReportModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMFormInstanceReport>)QueryUtil.list(
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
	 * Removes all the ddm form instance reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMFormInstanceReport ddmFormInstanceReport : findAll()) {
			remove(ddmFormInstanceReport);
		}
	}

	/**
	 * Returns the number of ddm form instance reports.
	 *
	 * @return the number of ddm form instance reports
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
					_SQL_COUNT_DDMFORMINSTANCEREPORT);

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
		return "formInstanceReportId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMFORMINSTANCEREPORT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMFormInstanceReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm form instance report persistence.
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

		_finderPathFetchByFormInstanceId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFormInstanceId",
			new String[] {Long.class.getName()},
			new String[] {"formInstanceId"}, true);

		DDMFormInstanceReportUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMFormInstanceReportUtil.setPersistence(null);

		entityCache.removeCache(DDMFormInstanceReportImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMFORMINSTANCEREPORT =
		"SELECT ddmFormInstanceReport FROM DDMFormInstanceReport ddmFormInstanceReport";

	private static final String _SQL_SELECT_DDMFORMINSTANCEREPORT_WHERE =
		"SELECT ddmFormInstanceReport FROM DDMFormInstanceReport ddmFormInstanceReport WHERE ";

	private static final String _SQL_COUNT_DDMFORMINSTANCEREPORT =
		"SELECT COUNT(ddmFormInstanceReport) FROM DDMFormInstanceReport ddmFormInstanceReport";

	private static final String _SQL_COUNT_DDMFORMINSTANCEREPORT_WHERE =
		"SELECT COUNT(ddmFormInstanceReport) FROM DDMFormInstanceReport ddmFormInstanceReport WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"ddmFormInstanceReport.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMFormInstanceReport exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMFormInstanceReport exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceReportPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"data"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}