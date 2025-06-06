/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.service.persistence.impl;

import com.liferay.friendly.url.exception.NoSuchFriendlyURLEntryMappingException;
import com.liferay.friendly.url.model.FriendlyURLEntryMapping;
import com.liferay.friendly.url.model.FriendlyURLEntryMappingTable;
import com.liferay.friendly.url.model.impl.FriendlyURLEntryMappingImpl;
import com.liferay.friendly.url.model.impl.FriendlyURLEntryMappingModelImpl;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryMappingPersistence;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryMappingUtil;
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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the friendly url entry mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FriendlyURLEntryMappingPersistence.class)
public class FriendlyURLEntryMappingPersistenceImpl
	extends BasePersistenceImpl<FriendlyURLEntryMapping>
	implements FriendlyURLEntryMappingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FriendlyURLEntryMappingUtil</code> to access the friendly url entry mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FriendlyURLEntryMappingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByC_C;

	/**
	 * Returns the friendly url entry mapping where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchFriendlyURLEntryMappingException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching friendly url entry mapping
	 * @throws NoSuchFriendlyURLEntryMappingException if a matching friendly url entry mapping could not be found
	 */
	@Override
	public FriendlyURLEntryMapping findByC_C(long classNameId, long classPK)
		throws NoSuchFriendlyURLEntryMappingException {

		FriendlyURLEntryMapping friendlyURLEntryMapping = fetchByC_C(
			classNameId, classPK);

		if (friendlyURLEntryMapping == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("classNameId=");
			sb.append(classNameId);

			sb.append(", classPK=");
			sb.append(classPK);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFriendlyURLEntryMappingException(sb.toString());
		}

		return friendlyURLEntryMapping;
	}

	/**
	 * Returns the friendly url entry mapping where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching friendly url entry mapping, or <code>null</code> if a matching friendly url entry mapping could not be found
	 */
	@Override
	public FriendlyURLEntryMapping fetchByC_C(long classNameId, long classPK) {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the friendly url entry mapping where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching friendly url entry mapping, or <code>null</code> if a matching friendly url entry mapping could not be found
	 */
	@Override
	public FriendlyURLEntryMapping fetchByC_C(
		long classNameId, long classPK, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classNameId, classPK};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof FriendlyURLEntryMapping) {
			FriendlyURLEntryMapping friendlyURLEntryMapping =
				(FriendlyURLEntryMapping)result;

			if ((classNameId != friendlyURLEntryMapping.getClassNameId()) ||
				(classPK != friendlyURLEntryMapping.getClassPK())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FRIENDLYURLENTRYMAPPING_WHERE);

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

				List<FriendlyURLEntryMapping> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C, finderArgs, list);
					}
				}
				else {
					FriendlyURLEntryMapping friendlyURLEntryMapping = list.get(
						0);

					result = friendlyURLEntryMapping;

					cacheResult(friendlyURLEntryMapping);
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
			return (FriendlyURLEntryMapping)result;
		}
	}

	/**
	 * Removes the friendly url entry mapping where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the friendly url entry mapping that was removed
	 */
	@Override
	public FriendlyURLEntryMapping removeByC_C(long classNameId, long classPK)
		throws NoSuchFriendlyURLEntryMappingException {

		FriendlyURLEntryMapping friendlyURLEntryMapping = findByC_C(
			classNameId, classPK);

		return remove(friendlyURLEntryMapping);
	}

	/**
	 * Returns the number of friendly url entry mappings where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching friendly url entry mappings
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FriendlyURLEntryMapping friendlyURLEntryMapping = fetchByC_C(
			classNameId, classPK);

		if (friendlyURLEntryMapping == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"friendlyURLEntryMapping.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"friendlyURLEntryMapping.classPK = ?";

	public FriendlyURLEntryMappingPersistenceImpl() {
		setModelClass(FriendlyURLEntryMapping.class);

		setModelImplClass(FriendlyURLEntryMappingImpl.class);
		setModelPKClass(long.class);

		setTable(FriendlyURLEntryMappingTable.INSTANCE);
	}

	/**
	 * Caches the friendly url entry mapping in the entity cache if it is enabled.
	 *
	 * @param friendlyURLEntryMapping the friendly url entry mapping
	 */
	@Override
	public void cacheResult(FriendlyURLEntryMapping friendlyURLEntryMapping) {
		entityCache.putResult(
			FriendlyURLEntryMappingImpl.class,
			friendlyURLEntryMapping.getPrimaryKey(), friendlyURLEntryMapping);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				friendlyURLEntryMapping.getClassNameId(),
				friendlyURLEntryMapping.getClassPK()
			},
			friendlyURLEntryMapping);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the friendly url entry mappings in the entity cache if it is enabled.
	 *
	 * @param friendlyURLEntryMappings the friendly url entry mappings
	 */
	@Override
	public void cacheResult(
		List<FriendlyURLEntryMapping> friendlyURLEntryMappings) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (friendlyURLEntryMappings.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FriendlyURLEntryMapping friendlyURLEntryMapping :
				friendlyURLEntryMappings) {

			if (entityCache.getResult(
					FriendlyURLEntryMappingImpl.class,
					friendlyURLEntryMapping.getPrimaryKey()) == null) {

				cacheResult(friendlyURLEntryMapping);
			}
		}
	}

	/**
	 * Clears the cache for all friendly url entry mappings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FriendlyURLEntryMappingImpl.class);

		finderCache.clearCache(FriendlyURLEntryMappingImpl.class);
	}

	/**
	 * Clears the cache for the friendly url entry mapping.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FriendlyURLEntryMapping friendlyURLEntryMapping) {
		entityCache.removeResult(
			FriendlyURLEntryMappingImpl.class, friendlyURLEntryMapping);
	}

	@Override
	public void clearCache(
		List<FriendlyURLEntryMapping> friendlyURLEntryMappings) {

		for (FriendlyURLEntryMapping friendlyURLEntryMapping :
				friendlyURLEntryMappings) {

			entityCache.removeResult(
				FriendlyURLEntryMappingImpl.class, friendlyURLEntryMapping);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FriendlyURLEntryMappingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				FriendlyURLEntryMappingImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FriendlyURLEntryMappingModelImpl friendlyURLEntryMappingModelImpl) {

		Object[] args = new Object[] {
			friendlyURLEntryMappingModelImpl.getClassNameId(),
			friendlyURLEntryMappingModelImpl.getClassPK()
		};

		finderCache.putResult(
			_finderPathFetchByC_C, args, friendlyURLEntryMappingModelImpl);
	}

	/**
	 * Creates a new friendly url entry mapping with the primary key. Does not add the friendly url entry mapping to the database.
	 *
	 * @param friendlyURLEntryMappingId the primary key for the new friendly url entry mapping
	 * @return the new friendly url entry mapping
	 */
	@Override
	public FriendlyURLEntryMapping create(long friendlyURLEntryMappingId) {
		FriendlyURLEntryMapping friendlyURLEntryMapping =
			new FriendlyURLEntryMappingImpl();

		friendlyURLEntryMapping.setNew(true);
		friendlyURLEntryMapping.setPrimaryKey(friendlyURLEntryMappingId);

		friendlyURLEntryMapping.setCompanyId(CompanyThreadLocal.getCompanyId());

		return friendlyURLEntryMapping;
	}

	/**
	 * Removes the friendly url entry mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntryMappingId the primary key of the friendly url entry mapping
	 * @return the friendly url entry mapping that was removed
	 * @throws NoSuchFriendlyURLEntryMappingException if a friendly url entry mapping with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntryMapping remove(long friendlyURLEntryMappingId)
		throws NoSuchFriendlyURLEntryMappingException {

		return remove((Serializable)friendlyURLEntryMappingId);
	}

	/**
	 * Removes the friendly url entry mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the friendly url entry mapping
	 * @return the friendly url entry mapping that was removed
	 * @throws NoSuchFriendlyURLEntryMappingException if a friendly url entry mapping with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntryMapping remove(Serializable primaryKey)
		throws NoSuchFriendlyURLEntryMappingException {

		Session session = null;

		try {
			session = openSession();

			FriendlyURLEntryMapping friendlyURLEntryMapping =
				(FriendlyURLEntryMapping)session.get(
					FriendlyURLEntryMappingImpl.class, primaryKey);

			if (friendlyURLEntryMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFriendlyURLEntryMappingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(friendlyURLEntryMapping);
		}
		catch (NoSuchFriendlyURLEntryMappingException noSuchEntityException) {
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
	protected FriendlyURLEntryMapping removeImpl(
		FriendlyURLEntryMapping friendlyURLEntryMapping) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(friendlyURLEntryMapping)) {
				friendlyURLEntryMapping = (FriendlyURLEntryMapping)session.get(
					FriendlyURLEntryMappingImpl.class,
					friendlyURLEntryMapping.getPrimaryKeyObj());
			}

			if (friendlyURLEntryMapping != null) {
				session.delete(friendlyURLEntryMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (friendlyURLEntryMapping != null) {
			clearCache(friendlyURLEntryMapping);
		}

		return friendlyURLEntryMapping;
	}

	@Override
	public FriendlyURLEntryMapping updateImpl(
		FriendlyURLEntryMapping friendlyURLEntryMapping) {

		boolean isNew = friendlyURLEntryMapping.isNew();

		if (!(friendlyURLEntryMapping instanceof
				FriendlyURLEntryMappingModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(friendlyURLEntryMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					friendlyURLEntryMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in friendlyURLEntryMapping proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FriendlyURLEntryMapping implementation " +
					friendlyURLEntryMapping.getClass());
		}

		FriendlyURLEntryMappingModelImpl friendlyURLEntryMappingModelImpl =
			(FriendlyURLEntryMappingModelImpl)friendlyURLEntryMapping;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(friendlyURLEntryMapping);
			}
			else {
				friendlyURLEntryMapping =
					(FriendlyURLEntryMapping)session.merge(
						friendlyURLEntryMapping);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FriendlyURLEntryMappingImpl.class, friendlyURLEntryMappingModelImpl,
			false, true);

		cacheUniqueFindersCache(friendlyURLEntryMappingModelImpl);

		if (isNew) {
			friendlyURLEntryMapping.setNew(false);
		}

		friendlyURLEntryMapping.resetOriginalValues();

		return friendlyURLEntryMapping;
	}

	/**
	 * Returns the friendly url entry mapping with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the friendly url entry mapping
	 * @return the friendly url entry mapping
	 * @throws NoSuchFriendlyURLEntryMappingException if a friendly url entry mapping with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntryMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFriendlyURLEntryMappingException {

		FriendlyURLEntryMapping friendlyURLEntryMapping = fetchByPrimaryKey(
			primaryKey);

		if (friendlyURLEntryMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFriendlyURLEntryMappingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return friendlyURLEntryMapping;
	}

	/**
	 * Returns the friendly url entry mapping with the primary key or throws a <code>NoSuchFriendlyURLEntryMappingException</code> if it could not be found.
	 *
	 * @param friendlyURLEntryMappingId the primary key of the friendly url entry mapping
	 * @return the friendly url entry mapping
	 * @throws NoSuchFriendlyURLEntryMappingException if a friendly url entry mapping with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntryMapping findByPrimaryKey(
			long friendlyURLEntryMappingId)
		throws NoSuchFriendlyURLEntryMappingException {

		return findByPrimaryKey((Serializable)friendlyURLEntryMappingId);
	}

	/**
	 * Returns the friendly url entry mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param friendlyURLEntryMappingId the primary key of the friendly url entry mapping
	 * @return the friendly url entry mapping, or <code>null</code> if a friendly url entry mapping with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntryMapping fetchByPrimaryKey(
		long friendlyURLEntryMappingId) {

		return fetchByPrimaryKey((Serializable)friendlyURLEntryMappingId);
	}

	/**
	 * Returns all the friendly url entry mappings.
	 *
	 * @return the friendly url entry mappings
	 */
	@Override
	public List<FriendlyURLEntryMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the friendly url entry mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entry mappings
	 * @param end the upper bound of the range of friendly url entry mappings (not inclusive)
	 * @return the range of friendly url entry mappings
	 */
	@Override
	public List<FriendlyURLEntryMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the friendly url entry mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entry mappings
	 * @param end the upper bound of the range of friendly url entry mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of friendly url entry mappings
	 */
	@Override
	public List<FriendlyURLEntryMapping> findAll(
		int start, int end,
		OrderByComparator<FriendlyURLEntryMapping> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the friendly url entry mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FriendlyURLEntryMappingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entry mappings
	 * @param end the upper bound of the range of friendly url entry mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of friendly url entry mappings
	 */
	@Override
	public List<FriendlyURLEntryMapping> findAll(
		int start, int end,
		OrderByComparator<FriendlyURLEntryMapping> orderByComparator,
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

		List<FriendlyURLEntryMapping> list = null;

		if (useFinderCache) {
			list = (List<FriendlyURLEntryMapping>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FRIENDLYURLENTRYMAPPING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FRIENDLYURLENTRYMAPPING;

				sql = sql.concat(
					FriendlyURLEntryMappingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FriendlyURLEntryMapping>)QueryUtil.list(
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
	 * Removes all the friendly url entry mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FriendlyURLEntryMapping friendlyURLEntryMapping : findAll()) {
			remove(friendlyURLEntryMapping);
		}
	}

	/**
	 * Returns the number of friendly url entry mappings.
	 *
	 * @return the number of friendly url entry mappings
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
					_SQL_COUNT_FRIENDLYURLENTRYMAPPING);

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
		return "friendlyURLEntryMappingId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FRIENDLYURLENTRYMAPPING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FriendlyURLEntryMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the friendly url entry mapping persistence.
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

		_finderPathFetchByC_C = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, true);

		FriendlyURLEntryMappingUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FriendlyURLEntryMappingUtil.setPersistence(null);

		entityCache.removeCache(FriendlyURLEntryMappingImpl.class.getName());
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

	private static final String _SQL_SELECT_FRIENDLYURLENTRYMAPPING =
		"SELECT friendlyURLEntryMapping FROM FriendlyURLEntryMapping friendlyURLEntryMapping";

	private static final String _SQL_SELECT_FRIENDLYURLENTRYMAPPING_WHERE =
		"SELECT friendlyURLEntryMapping FROM FriendlyURLEntryMapping friendlyURLEntryMapping WHERE ";

	private static final String _SQL_COUNT_FRIENDLYURLENTRYMAPPING =
		"SELECT COUNT(friendlyURLEntryMapping) FROM FriendlyURLEntryMapping friendlyURLEntryMapping";

	private static final String _SQL_COUNT_FRIENDLYURLENTRYMAPPING_WHERE =
		"SELECT COUNT(friendlyURLEntryMapping) FROM FriendlyURLEntryMapping friendlyURLEntryMapping WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"friendlyURLEntryMapping.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FriendlyURLEntryMapping exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FriendlyURLEntryMapping exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FriendlyURLEntryMappingPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}