/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchFieldException;
import com.liferay.dynamic.data.mapping.model.DDMField;
import com.liferay.dynamic.data.mapping.model.DDMFieldTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMFieldImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMFieldModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFieldUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the ddm field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMFieldPersistence.class)
public class DDMFieldPersistenceImpl
	extends BasePersistenceImpl<DDMField> implements DDMFieldPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMFieldUtil</code> to access the ddm field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMFieldImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByStorageId;
	private FinderPath _finderPathWithoutPaginationFindByStorageId;
	private FinderPath _finderPathCountByStorageId;

	/**
	 * Returns all the ddm fields where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @return the matching ddm fields
	 */
	@Override
	public List<DDMField> findByStorageId(long storageId) {
		return findByStorageId(
			storageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm fields where storageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @return the range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStorageId(long storageId, int start, int end) {
		return findByStorageId(storageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm fields where storageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStorageId(
		long storageId, int start, int end,
		OrderByComparator<DDMField> orderByComparator) {

		return findByStorageId(storageId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm fields where storageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStorageId(
		long storageId, int start, int end,
		OrderByComparator<DDMField> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStorageId;
				finderArgs = new Object[] {storageId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStorageId;
			finderArgs = new Object[] {
				storageId, start, end, orderByComparator
			};
		}

		List<DDMField> list = null;

		if (useFinderCache) {
			list = (List<DDMField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMField ddmField : list) {
					if (storageId != ddmField.getStorageId()) {
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

			sb.append(_SQL_SELECT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_STORAGEID_STORAGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(storageId);

				list = (List<DDMField>)QueryUtil.list(
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
	 * Returns the first ddm field in the ordered set where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByStorageId_First(
			long storageId, OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByStorageId_First(
			storageId, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("storageId=");
		sb.append(storageId);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the first ddm field in the ordered set where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByStorageId_First(
		long storageId, OrderByComparator<DDMField> orderByComparator) {

		List<DDMField> list = findByStorageId(
			storageId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm field in the ordered set where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByStorageId_Last(
			long storageId, OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByStorageId_Last(storageId, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("storageId=");
		sb.append(storageId);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the last ddm field in the ordered set where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByStorageId_Last(
		long storageId, OrderByComparator<DDMField> orderByComparator) {

		int count = countByStorageId(storageId);

		if (count == 0) {
			return null;
		}

		List<DDMField> list = findByStorageId(
			storageId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm fields before and after the current ddm field in the ordered set where storageId = &#63;.
	 *
	 * @param fieldId the primary key of the current ddm field
	 * @param storageId the storage ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField[] findByStorageId_PrevAndNext(
			long fieldId, long storageId,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = findByPrimaryKey(fieldId);

		Session session = null;

		try {
			session = openSession();

			DDMField[] array = new DDMFieldImpl[3];

			array[0] = getByStorageId_PrevAndNext(
				session, ddmField, storageId, orderByComparator, true);

			array[1] = ddmField;

			array[2] = getByStorageId_PrevAndNext(
				session, ddmField, storageId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMField getByStorageId_PrevAndNext(
		Session session, DDMField ddmField, long storageId,
		OrderByComparator<DDMField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DDMFIELD_WHERE);

		sb.append(_FINDER_COLUMN_STORAGEID_STORAGEID_2);

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
			sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(storageId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ddmField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm fields where storageId = &#63; from the database.
	 *
	 * @param storageId the storage ID
	 */
	@Override
	public void removeByStorageId(long storageId) {
		for (DDMField ddmField :
				findByStorageId(
					storageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmField);
		}
	}

	/**
	 * Returns the number of ddm fields where storageId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @return the number of matching ddm fields
	 */
	@Override
	public int countByStorageId(long storageId) {
		FinderPath finderPath = _finderPathCountByStorageId;

		Object[] finderArgs = new Object[] {storageId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_STORAGEID_STORAGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(storageId);

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

	private static final String _FINDER_COLUMN_STORAGEID_STORAGEID_2 =
		"ddmField.storageId = ?";

	private FinderPath _finderPathWithPaginationFindByStructureVersionId;
	private FinderPath _finderPathWithoutPaginationFindByStructureVersionId;
	private FinderPath _finderPathCountByStructureVersionId;

	/**
	 * Returns all the ddm fields where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @return the matching ddm fields
	 */
	@Override
	public List<DDMField> findByStructureVersionId(long structureVersionId) {
		return findByStructureVersionId(
			structureVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm fields where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @return the range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStructureVersionId(
		long structureVersionId, int start, int end) {

		return findByStructureVersionId(structureVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm fields where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStructureVersionId(
		long structureVersionId, int start, int end,
		OrderByComparator<DDMField> orderByComparator) {

		return findByStructureVersionId(
			structureVersionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm fields where structureVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param structureVersionId the structure version ID
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByStructureVersionId(
		long structureVersionId, int start, int end,
		OrderByComparator<DDMField> orderByComparator, boolean useFinderCache) {

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

		List<DDMField> list = null;

		if (useFinderCache) {
			list = (List<DDMField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMField ddmField : list) {
					if (structureVersionId !=
							ddmField.getStructureVersionId()) {

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

			sb.append(_SQL_SELECT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureVersionId);

				list = (List<DDMField>)QueryUtil.list(
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
	 * Returns the first ddm field in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByStructureVersionId_First(
			long structureVersionId,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByStructureVersionId_First(
			structureVersionId, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureVersionId=");
		sb.append(structureVersionId);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the first ddm field in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByStructureVersionId_First(
		long structureVersionId,
		OrderByComparator<DDMField> orderByComparator) {

		List<DDMField> list = findByStructureVersionId(
			structureVersionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm field in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByStructureVersionId_Last(
			long structureVersionId,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByStructureVersionId_Last(
			structureVersionId, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureVersionId=");
		sb.append(structureVersionId);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the last ddm field in the ordered set where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByStructureVersionId_Last(
		long structureVersionId,
		OrderByComparator<DDMField> orderByComparator) {

		int count = countByStructureVersionId(structureVersionId);

		if (count == 0) {
			return null;
		}

		List<DDMField> list = findByStructureVersionId(
			structureVersionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm fields before and after the current ddm field in the ordered set where structureVersionId = &#63;.
	 *
	 * @param fieldId the primary key of the current ddm field
	 * @param structureVersionId the structure version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField[] findByStructureVersionId_PrevAndNext(
			long fieldId, long structureVersionId,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = findByPrimaryKey(fieldId);

		Session session = null;

		try {
			session = openSession();

			DDMField[] array = new DDMFieldImpl[3];

			array[0] = getByStructureVersionId_PrevAndNext(
				session, ddmField, structureVersionId, orderByComparator, true);

			array[1] = ddmField;

			array[2] = getByStructureVersionId_PrevAndNext(
				session, ddmField, structureVersionId, orderByComparator,
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

	protected DDMField getByStructureVersionId_PrevAndNext(
		Session session, DDMField ddmField, long structureVersionId,
		OrderByComparator<DDMField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DDMFIELD_WHERE);

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
			sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(structureVersionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ddmField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm fields where structureVersionId = &#63; from the database.
	 *
	 * @param structureVersionId the structure version ID
	 */
	@Override
	public void removeByStructureVersionId(long structureVersionId) {
		for (DDMField ddmField :
				findByStructureVersionId(
					structureVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmField);
		}
	}

	/**
	 * Returns the number of ddm fields where structureVersionId = &#63;.
	 *
	 * @param structureVersionId the structure version ID
	 * @return the number of matching ddm fields
	 */
	@Override
	public int countByStructureVersionId(long structureVersionId) {
		FinderPath finderPath = _finderPathCountByStructureVersionId;

		Object[] finderArgs = new Object[] {structureVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMFIELD_WHERE);

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

	private static final String
		_FINDER_COLUMN_STRUCTUREVERSIONID_STRUCTUREVERSIONID_2 =
			"ddmField.structureVersionId = ?";

	private FinderPath _finderPathWithPaginationFindByC_F;
	private FinderPath _finderPathWithoutPaginationFindByC_F;
	private FinderPath _finderPathCountByC_F;

	/**
	 * Returns all the ddm fields where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @return the matching ddm fields
	 */
	@Override
	public List<DDMField> findByC_F(long companyId, String fieldType) {
		return findByC_F(
			companyId, fieldType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm fields where companyId = &#63; and fieldType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @return the range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByC_F(
		long companyId, String fieldType, int start, int end) {

		return findByC_F(companyId, fieldType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm fields where companyId = &#63; and fieldType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByC_F(
		long companyId, String fieldType, int start, int end,
		OrderByComparator<DDMField> orderByComparator) {

		return findByC_F(
			companyId, fieldType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm fields where companyId = &#63; and fieldType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByC_F(
		long companyId, String fieldType, int start, int end,
		OrderByComparator<DDMField> orderByComparator, boolean useFinderCache) {

		fieldType = Objects.toString(fieldType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_F;
				finderArgs = new Object[] {companyId, fieldType};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_F;
			finderArgs = new Object[] {
				companyId, fieldType, start, end, orderByComparator
			};
		}

		List<DDMField> list = null;

		if (useFinderCache) {
			list = (List<DDMField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMField ddmField : list) {
					if ((companyId != ddmField.getCompanyId()) ||
						!fieldType.equals(ddmField.getFieldType())) {

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

			sb.append(_SQL_SELECT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

			boolean bindFieldType = false;

			if (fieldType.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_3);
			}
			else {
				bindFieldType = true;

				sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindFieldType) {
					queryPos.add(fieldType);
				}

				list = (List<DDMField>)QueryUtil.list(
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
	 * Returns the first ddm field in the ordered set where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByC_F_First(
			long companyId, String fieldType,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByC_F_First(
			companyId, fieldType, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", fieldType=");
		sb.append(fieldType);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the first ddm field in the ordered set where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByC_F_First(
		long companyId, String fieldType,
		OrderByComparator<DDMField> orderByComparator) {

		List<DDMField> list = findByC_F(
			companyId, fieldType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm field in the ordered set where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByC_F_Last(
			long companyId, String fieldType,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByC_F_Last(
			companyId, fieldType, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", fieldType=");
		sb.append(fieldType);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the last ddm field in the ordered set where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByC_F_Last(
		long companyId, String fieldType,
		OrderByComparator<DDMField> orderByComparator) {

		int count = countByC_F(companyId, fieldType);

		if (count == 0) {
			return null;
		}

		List<DDMField> list = findByC_F(
			companyId, fieldType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm fields before and after the current ddm field in the ordered set where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param fieldId the primary key of the current ddm field
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField[] findByC_F_PrevAndNext(
			long fieldId, long companyId, String fieldType,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		fieldType = Objects.toString(fieldType, "");

		DDMField ddmField = findByPrimaryKey(fieldId);

		Session session = null;

		try {
			session = openSession();

			DDMField[] array = new DDMFieldImpl[3];

			array[0] = getByC_F_PrevAndNext(
				session, ddmField, companyId, fieldType, orderByComparator,
				true);

			array[1] = ddmField;

			array[2] = getByC_F_PrevAndNext(
				session, ddmField, companyId, fieldType, orderByComparator,
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

	protected DDMField getByC_F_PrevAndNext(
		Session session, DDMField ddmField, long companyId, String fieldType,
		OrderByComparator<DDMField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_DDMFIELD_WHERE);

		sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

		boolean bindFieldType = false;

		if (fieldType.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_3);
		}
		else {
			bindFieldType = true;

			sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_2);
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
			sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindFieldType) {
			queryPos.add(fieldType);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ddmField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm fields where companyId = &#63; and fieldType = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 */
	@Override
	public void removeByC_F(long companyId, String fieldType) {
		for (DDMField ddmField :
				findByC_F(
					companyId, fieldType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmField);
		}
	}

	/**
	 * Returns the number of ddm fields where companyId = &#63; and fieldType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param fieldType the field type
	 * @return the number of matching ddm fields
	 */
	@Override
	public int countByC_F(long companyId, String fieldType) {
		fieldType = Objects.toString(fieldType, "");

		FinderPath finderPath = _finderPathCountByC_F;

		Object[] finderArgs = new Object[] {companyId, fieldType};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_C_F_COMPANYID_2);

			boolean bindFieldType = false;

			if (fieldType.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_3);
			}
			else {
				bindFieldType = true;

				sb.append(_FINDER_COLUMN_C_F_FIELDTYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindFieldType) {
					queryPos.add(fieldType);
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

	private static final String _FINDER_COLUMN_C_F_COMPANYID_2 =
		"ddmField.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_F_FIELDTYPE_2 =
		"ddmField.fieldType = ?";

	private static final String _FINDER_COLUMN_C_F_FIELDTYPE_3 =
		"(ddmField.fieldType IS NULL OR ddmField.fieldType = '')";

	private FinderPath _finderPathWithPaginationFindByS_F;
	private FinderPath _finderPathWithoutPaginationFindByS_F;
	private FinderPath _finderPathCountByS_F;

	/**
	 * Returns all the ddm fields where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @return the matching ddm fields
	 */
	@Override
	public List<DDMField> findByS_F(long storageId, String fieldName) {
		return findByS_F(
			storageId, fieldName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm fields where storageId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @return the range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByS_F(
		long storageId, String fieldName, int start, int end) {

		return findByS_F(storageId, fieldName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm fields where storageId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByS_F(
		long storageId, String fieldName, int start, int end,
		OrderByComparator<DDMField> orderByComparator) {

		return findByS_F(
			storageId, fieldName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm fields where storageId = &#63; and fieldName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm fields
	 */
	@Override
	public List<DDMField> findByS_F(
		long storageId, String fieldName, int start, int end,
		OrderByComparator<DDMField> orderByComparator, boolean useFinderCache) {

		fieldName = Objects.toString(fieldName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByS_F;
				finderArgs = new Object[] {storageId, fieldName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByS_F;
			finderArgs = new Object[] {
				storageId, fieldName, start, end, orderByComparator
			};
		}

		List<DDMField> list = null;

		if (useFinderCache) {
			list = (List<DDMField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMField ddmField : list) {
					if ((storageId != ddmField.getStorageId()) ||
						!fieldName.equals(ddmField.getFieldName())) {

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

			sb.append(_SQL_SELECT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_S_F_STORAGEID_2);

			boolean bindFieldName = false;

			if (fieldName.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_F_FIELDNAME_3);
			}
			else {
				bindFieldName = true;

				sb.append(_FINDER_COLUMN_S_F_FIELDNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(storageId);

				if (bindFieldName) {
					queryPos.add(fieldName);
				}

				list = (List<DDMField>)QueryUtil.list(
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
	 * Returns the first ddm field in the ordered set where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByS_F_First(
			long storageId, String fieldName,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByS_F_First(
			storageId, fieldName, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("storageId=");
		sb.append(storageId);

		sb.append(", fieldName=");
		sb.append(fieldName);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the first ddm field in the ordered set where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByS_F_First(
		long storageId, String fieldName,
		OrderByComparator<DDMField> orderByComparator) {

		List<DDMField> list = findByS_F(
			storageId, fieldName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm field in the ordered set where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByS_F_Last(
			long storageId, String fieldName,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByS_F_Last(
			storageId, fieldName, orderByComparator);

		if (ddmField != null) {
			return ddmField;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("storageId=");
		sb.append(storageId);

		sb.append(", fieldName=");
		sb.append(fieldName);

		sb.append("}");

		throw new NoSuchFieldException(sb.toString());
	}

	/**
	 * Returns the last ddm field in the ordered set where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByS_F_Last(
		long storageId, String fieldName,
		OrderByComparator<DDMField> orderByComparator) {

		int count = countByS_F(storageId, fieldName);

		if (count == 0) {
			return null;
		}

		List<DDMField> list = findByS_F(
			storageId, fieldName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm fields before and after the current ddm field in the ordered set where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param fieldId the primary key of the current ddm field
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField[] findByS_F_PrevAndNext(
			long fieldId, long storageId, String fieldName,
			OrderByComparator<DDMField> orderByComparator)
		throws NoSuchFieldException {

		fieldName = Objects.toString(fieldName, "");

		DDMField ddmField = findByPrimaryKey(fieldId);

		Session session = null;

		try {
			session = openSession();

			DDMField[] array = new DDMFieldImpl[3];

			array[0] = getByS_F_PrevAndNext(
				session, ddmField, storageId, fieldName, orderByComparator,
				true);

			array[1] = ddmField;

			array[2] = getByS_F_PrevAndNext(
				session, ddmField, storageId, fieldName, orderByComparator,
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

	protected DDMField getByS_F_PrevAndNext(
		Session session, DDMField ddmField, long storageId, String fieldName,
		OrderByComparator<DDMField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_DDMFIELD_WHERE);

		sb.append(_FINDER_COLUMN_S_F_STORAGEID_2);

		boolean bindFieldName = false;

		if (fieldName.isEmpty()) {
			sb.append(_FINDER_COLUMN_S_F_FIELDNAME_3);
		}
		else {
			bindFieldName = true;

			sb.append(_FINDER_COLUMN_S_F_FIELDNAME_2);
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
			sb.append(DDMFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(storageId);

		if (bindFieldName) {
			queryPos.add(fieldName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ddmField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm fields where storageId = &#63; and fieldName = &#63; from the database.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 */
	@Override
	public void removeByS_F(long storageId, String fieldName) {
		for (DDMField ddmField :
				findByS_F(
					storageId, fieldName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmField);
		}
	}

	/**
	 * Returns the number of ddm fields where storageId = &#63; and fieldName = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param fieldName the field name
	 * @return the number of matching ddm fields
	 */
	@Override
	public int countByS_F(long storageId, String fieldName) {
		fieldName = Objects.toString(fieldName, "");

		FinderPath finderPath = _finderPathCountByS_F;

		Object[] finderArgs = new Object[] {storageId, fieldName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_S_F_STORAGEID_2);

			boolean bindFieldName = false;

			if (fieldName.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_F_FIELDNAME_3);
			}
			else {
				bindFieldName = true;

				sb.append(_FINDER_COLUMN_S_F_FIELDNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(storageId);

				if (bindFieldName) {
					queryPos.add(fieldName);
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

	private static final String _FINDER_COLUMN_S_F_STORAGEID_2 =
		"ddmField.storageId = ? AND ";

	private static final String _FINDER_COLUMN_S_F_FIELDNAME_2 =
		"ddmField.fieldName = ?";

	private static final String _FINDER_COLUMN_S_F_FIELDNAME_3 =
		"(ddmField.fieldName IS NULL OR ddmField.fieldName = '')";

	private FinderPath _finderPathFetchByS_I;

	/**
	 * Returns the ddm field where storageId = &#63; and instanceId = &#63; or throws a <code>NoSuchFieldException</code> if it could not be found.
	 *
	 * @param storageId the storage ID
	 * @param instanceId the instance ID
	 * @return the matching ddm field
	 * @throws NoSuchFieldException if a matching ddm field could not be found
	 */
	@Override
	public DDMField findByS_I(long storageId, String instanceId)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByS_I(storageId, instanceId);

		if (ddmField == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("storageId=");
			sb.append(storageId);

			sb.append(", instanceId=");
			sb.append(instanceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFieldException(sb.toString());
		}

		return ddmField;
	}

	/**
	 * Returns the ddm field where storageId = &#63; and instanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param storageId the storage ID
	 * @param instanceId the instance ID
	 * @return the matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByS_I(long storageId, String instanceId) {
		return fetchByS_I(storageId, instanceId, true);
	}

	/**
	 * Returns the ddm field where storageId = &#63; and instanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param storageId the storage ID
	 * @param instanceId the instance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm field, or <code>null</code> if a matching ddm field could not be found
	 */
	@Override
	public DDMField fetchByS_I(
		long storageId, String instanceId, boolean useFinderCache) {

		instanceId = Objects.toString(instanceId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {storageId, instanceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByS_I, finderArgs, this);
		}

		if (result instanceof DDMField) {
			DDMField ddmField = (DDMField)result;

			if ((storageId != ddmField.getStorageId()) ||
				!Objects.equals(instanceId, ddmField.getInstanceId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DDMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_S_I_STORAGEID_2);

			boolean bindInstanceId = false;

			if (instanceId.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_I_INSTANCEID_3);
			}
			else {
				bindInstanceId = true;

				sb.append(_FINDER_COLUMN_S_I_INSTANCEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(storageId);

				if (bindInstanceId) {
					queryPos.add(instanceId);
				}

				List<DDMField> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByS_I, finderArgs, list);
					}
				}
				else {
					DDMField ddmField = list.get(0);

					result = ddmField;

					cacheResult(ddmField);
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
			return (DDMField)result;
		}
	}

	/**
	 * Removes the ddm field where storageId = &#63; and instanceId = &#63; from the database.
	 *
	 * @param storageId the storage ID
	 * @param instanceId the instance ID
	 * @return the ddm field that was removed
	 */
	@Override
	public DDMField removeByS_I(long storageId, String instanceId)
		throws NoSuchFieldException {

		DDMField ddmField = findByS_I(storageId, instanceId);

		return remove(ddmField);
	}

	/**
	 * Returns the number of ddm fields where storageId = &#63; and instanceId = &#63;.
	 *
	 * @param storageId the storage ID
	 * @param instanceId the instance ID
	 * @return the number of matching ddm fields
	 */
	@Override
	public int countByS_I(long storageId, String instanceId) {
		DDMField ddmField = fetchByS_I(storageId, instanceId);

		if (ddmField == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_S_I_STORAGEID_2 =
		"ddmField.storageId = ? AND ";

	private static final String _FINDER_COLUMN_S_I_INSTANCEID_2 =
		"ddmField.instanceId = ?";

	private static final String _FINDER_COLUMN_S_I_INSTANCEID_3 =
		"(ddmField.instanceId IS NULL OR ddmField.instanceId = '')";

	public DDMFieldPersistenceImpl() {
		setModelClass(DDMField.class);

		setModelImplClass(DDMFieldImpl.class);
		setModelPKClass(long.class);

		setTable(DDMFieldTable.INSTANCE);
	}

	/**
	 * Caches the ddm field in the entity cache if it is enabled.
	 *
	 * @param ddmField the ddm field
	 */
	@Override
	public void cacheResult(DDMField ddmField) {
		entityCache.putResult(
			DDMFieldImpl.class, ddmField.getPrimaryKey(), ddmField);

		finderCache.putResult(
			_finderPathFetchByS_I,
			new Object[] {ddmField.getStorageId(), ddmField.getInstanceId()},
			ddmField);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm fields in the entity cache if it is enabled.
	 *
	 * @param ddmFields the ddm fields
	 */
	@Override
	public void cacheResult(List<DDMField> ddmFields) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmFields.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMField ddmField : ddmFields) {
			if (entityCache.getResult(
					DDMFieldImpl.class, ddmField.getPrimaryKey()) == null) {

				cacheResult(ddmField);
			}
		}
	}

	/**
	 * Clears the cache for all ddm fields.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMFieldImpl.class);

		finderCache.clearCache(DDMFieldImpl.class);
	}

	/**
	 * Clears the cache for the ddm field.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMField ddmField) {
		entityCache.removeResult(DDMFieldImpl.class, ddmField);
	}

	@Override
	public void clearCache(List<DDMField> ddmFields) {
		for (DDMField ddmField : ddmFields) {
			entityCache.removeResult(DDMFieldImpl.class, ddmField);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMFieldImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDMFieldImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMFieldModelImpl ddmFieldModelImpl) {

		Object[] args = new Object[] {
			ddmFieldModelImpl.getStorageId(), ddmFieldModelImpl.getInstanceId()
		};

		finderCache.putResult(_finderPathFetchByS_I, args, ddmFieldModelImpl);
	}

	/**
	 * Creates a new ddm field with the primary key. Does not add the ddm field to the database.
	 *
	 * @param fieldId the primary key for the new ddm field
	 * @return the new ddm field
	 */
	@Override
	public DDMField create(long fieldId) {
		DDMField ddmField = new DDMFieldImpl();

		ddmField.setNew(true);
		ddmField.setPrimaryKey(fieldId);

		ddmField.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmField;
	}

	/**
	 * Removes the ddm field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fieldId the primary key of the ddm field
	 * @return the ddm field that was removed
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField remove(long fieldId) throws NoSuchFieldException {
		return remove((Serializable)fieldId);
	}

	/**
	 * Removes the ddm field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm field
	 * @return the ddm field that was removed
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField remove(Serializable primaryKey)
		throws NoSuchFieldException {

		Session session = null;

		try {
			session = openSession();

			DDMField ddmField = (DDMField)session.get(
				DDMFieldImpl.class, primaryKey);

			if (ddmField == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFieldException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmField);
		}
		catch (NoSuchFieldException noSuchEntityException) {
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
	protected DDMField removeImpl(DDMField ddmField) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmField)) {
				ddmField = (DDMField)session.get(
					DDMFieldImpl.class, ddmField.getPrimaryKeyObj());
			}

			if (ddmField != null) {
				session.delete(ddmField);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmField != null) {
			clearCache(ddmField);
		}

		return ddmField;
	}

	@Override
	public DDMField updateImpl(DDMField ddmField) {
		boolean isNew = ddmField.isNew();

		if (!(ddmField instanceof DDMFieldModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmField.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(ddmField);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmField proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMField implementation " +
					ddmField.getClass());
		}

		DDMFieldModelImpl ddmFieldModelImpl = (DDMFieldModelImpl)ddmField;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmField);
			}
			else {
				ddmField = (DDMField)session.merge(ddmField);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMFieldImpl.class, ddmFieldModelImpl, false, true);

		cacheUniqueFindersCache(ddmFieldModelImpl);

		if (isNew) {
			ddmField.setNew(false);
		}

		ddmField.resetOriginalValues();

		return ddmField;
	}

	/**
	 * Returns the ddm field with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm field
	 * @return the ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFieldException {

		DDMField ddmField = fetchByPrimaryKey(primaryKey);

		if (ddmField == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFieldException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmField;
	}

	/**
	 * Returns the ddm field with the primary key or throws a <code>NoSuchFieldException</code> if it could not be found.
	 *
	 * @param fieldId the primary key of the ddm field
	 * @return the ddm field
	 * @throws NoSuchFieldException if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField findByPrimaryKey(long fieldId) throws NoSuchFieldException {
		return findByPrimaryKey((Serializable)fieldId);
	}

	/**
	 * Returns the ddm field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fieldId the primary key of the ddm field
	 * @return the ddm field, or <code>null</code> if a ddm field with the primary key could not be found
	 */
	@Override
	public DDMField fetchByPrimaryKey(long fieldId) {
		return fetchByPrimaryKey((Serializable)fieldId);
	}

	/**
	 * Returns all the ddm fields.
	 *
	 * @return the ddm fields
	 */
	@Override
	public List<DDMField> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @return the range of ddm fields
	 */
	@Override
	public List<DDMField> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm fields
	 */
	@Override
	public List<DDMField> findAll(
		int start, int end, OrderByComparator<DDMField> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm fields
	 * @param end the upper bound of the range of ddm fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm fields
	 */
	@Override
	public List<DDMField> findAll(
		int start, int end, OrderByComparator<DDMField> orderByComparator,
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

		List<DDMField> list = null;

		if (useFinderCache) {
			list = (List<DDMField>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMFIELD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMFIELD;

				sql = sql.concat(DDMFieldModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMField>)QueryUtil.list(
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
	 * Removes all the ddm fields from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMField ddmField : findAll()) {
			remove(ddmField);
		}
	}

	/**
	 * Returns the number of ddm fields.
	 *
	 * @return the number of ddm fields
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DDMFIELD);

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
		return "fieldId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMFIELD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMFieldModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm field persistence.
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

		_finderPathWithPaginationFindByStorageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStorageId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"storageId"}, true);

		_finderPathWithoutPaginationFindByStorageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStorageId",
			new String[] {Long.class.getName()}, new String[] {"storageId"},
			true);

		_finderPathCountByStorageId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStorageId",
			new String[] {Long.class.getName()}, new String[] {"storageId"},
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

		_finderPathWithPaginationFindByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "fieldType"}, true);

		_finderPathWithoutPaginationFindByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "fieldType"}, true);

		_finderPathCountByC_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "fieldType"}, false);

		_finderPathWithPaginationFindByS_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"storageId", "fieldName"}, true);

		_finderPathWithoutPaginationFindByS_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"storageId", "fieldName"}, true);

		_finderPathCountByS_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"storageId", "fieldName"}, false);

		_finderPathFetchByS_I = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByS_I",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"storageId", "instanceId"}, true);

		DDMFieldUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMFieldUtil.setPersistence(null);

		entityCache.removeCache(DDMFieldImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMFIELD =
		"SELECT ddmField FROM DDMField ddmField";

	private static final String _SQL_SELECT_DDMFIELD_WHERE =
		"SELECT ddmField FROM DDMField ddmField WHERE ";

	private static final String _SQL_COUNT_DDMFIELD =
		"SELECT COUNT(ddmField) FROM DDMField ddmField";

	private static final String _SQL_COUNT_DDMFIELD_WHERE =
		"SELECT COUNT(ddmField) FROM DDMField ddmField WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmField.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMField exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMField exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFieldPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}