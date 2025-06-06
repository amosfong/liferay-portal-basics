/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchFormInstanceRecordVersionException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersionTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordVersionPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordVersionUtil;
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

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the ddm form instance record version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMFormInstanceRecordVersionPersistence.class)
public class DDMFormInstanceRecordVersionPersistenceImpl
	extends BasePersistenceImpl<DDMFormInstanceRecordVersion>
	implements DDMFormInstanceRecordVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMFormInstanceRecordVersionUtil</code> to access the ddm form instance record version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMFormInstanceRecordVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByFormInstanceRecordId;
	private FinderPath _finderPathWithoutPaginationFindByFormInstanceRecordId;
	private FinderPath _finderPathCountByFormInstanceRecordId;

	/**
	 * Returns all the ddm form instance record versions where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @return the matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByFormInstanceRecordId(
		long formInstanceRecordId) {

		return findByFormInstanceRecordId(
			formInstanceRecordId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions where formInstanceRecordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByFormInstanceRecordId(
		long formInstanceRecordId, int start, int end) {

		return findByFormInstanceRecordId(
			formInstanceRecordId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceRecordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByFormInstanceRecordId(
		long formInstanceRecordId, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findByFormInstanceRecordId(
			formInstanceRecordId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceRecordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByFormInstanceRecordId(
		long formInstanceRecordId, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByFormInstanceRecordId;
				finderArgs = new Object[] {formInstanceRecordId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFormInstanceRecordId;
			finderArgs = new Object[] {
				formInstanceRecordId, start, end, orderByComparator
			};
		}

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
						list) {

					if (formInstanceRecordId !=
							ddmFormInstanceRecordVersion.
								getFormInstanceRecordId()) {

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

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(
				_FINDER_COLUMN_FORMINSTANCERECORDID_FORMINSTANCERECORDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordId);

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Returns the first ddm form instance record version in the ordered set where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByFormInstanceRecordId_First(
			long formInstanceRecordId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByFormInstanceRecordId_First(
				formInstanceRecordId, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordId=");
		sb.append(formInstanceRecordId);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm form instance record version in the ordered set where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByFormInstanceRecordId_First(
		long formInstanceRecordId,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		List<DDMFormInstanceRecordVersion> list = findByFormInstanceRecordId(
			formInstanceRecordId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByFormInstanceRecordId_Last(
			long formInstanceRecordId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByFormInstanceRecordId_Last(
				formInstanceRecordId, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordId=");
		sb.append(formInstanceRecordId);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByFormInstanceRecordId_Last(
		long formInstanceRecordId,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		int count = countByFormInstanceRecordId(formInstanceRecordId);

		if (count == 0) {
			return null;
		}

		List<DDMFormInstanceRecordVersion> list = findByFormInstanceRecordId(
			formInstanceRecordId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm form instance record versions before and after the current ddm form instance record version in the ordered set where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the primary key of the current ddm form instance record version
	 * @param formInstanceRecordId the form instance record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion[]
			findByFormInstanceRecordId_PrevAndNext(
				long formInstanceRecordVersionId, long formInstanceRecordId,
				OrderByComparator<DDMFormInstanceRecordVersion>
					orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			findByPrimaryKey(formInstanceRecordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion[] array =
				new DDMFormInstanceRecordVersionImpl[3];

			array[0] = getByFormInstanceRecordId_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceRecordId,
				orderByComparator, true);

			array[1] = ddmFormInstanceRecordVersion;

			array[2] = getByFormInstanceRecordId_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceRecordId,
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

	protected DDMFormInstanceRecordVersion
		getByFormInstanceRecordId_PrevAndNext(
			Session session,
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion,
			long formInstanceRecordId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

		sb.append(_FINDER_COLUMN_FORMINSTANCERECORDID_FORMINSTANCERECORDID_2);

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
			sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formInstanceRecordId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmFormInstanceRecordVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMFormInstanceRecordVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm form instance record versions where formInstanceRecordId = &#63; from the database.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 */
	@Override
	public void removeByFormInstanceRecordId(long formInstanceRecordId) {
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findByFormInstanceRecordId(
					formInstanceRecordId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions where formInstanceRecordId = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByFormInstanceRecordId(long formInstanceRecordId) {
		FinderPath finderPath = _finderPathCountByFormInstanceRecordId;

		Object[] finderArgs = new Object[] {formInstanceRecordId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(
				_FINDER_COLUMN_FORMINSTANCERECORDID_FORMINSTANCERECORDID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordId);

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
		_FINDER_COLUMN_FORMINSTANCERECORDID_FORMINSTANCERECORDID_2 =
			"ddmFormInstanceRecordVersion.formInstanceRecordId = ?";

	private FinderPath _finderPathWithPaginationFindByU_F;
	private FinderPath _finderPathWithoutPaginationFindByU_F;
	private FinderPath _finderPathCountByU_F;

	/**
	 * Returns all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F(
		long userId, long formInstanceId) {

		return findByU_F(
			userId, formInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F(
		long userId, long formInstanceId, int start, int end) {

		return findByU_F(userId, formInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F(
		long userId, long formInstanceId, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findByU_F(
			userId, formInstanceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F(
		long userId, long formInstanceId, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_F;
				finderArgs = new Object[] {userId, formInstanceId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_F;
			finderArgs = new Object[] {
				userId, formInstanceId, start, end, orderByComparator
			};
		}

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
						list) {

					if ((userId != ddmFormInstanceRecordVersion.getUserId()) ||
						(formInstanceId !=
							ddmFormInstanceRecordVersion.getFormInstanceId())) {

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

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_U_F_USERID_2);

			sb.append(_FINDER_COLUMN_U_F_FORMINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(formInstanceId);

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Returns the first ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByU_F_First(
			long userId, long formInstanceId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByU_F_First(userId, formInstanceId, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", formInstanceId=");
		sb.append(formInstanceId);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByU_F_First(
		long userId, long formInstanceId,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		List<DDMFormInstanceRecordVersion> list = findByU_F(
			userId, formInstanceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByU_F_Last(
			long userId, long formInstanceId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByU_F_Last(userId, formInstanceId, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", formInstanceId=");
		sb.append(formInstanceId);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByU_F_Last(
		long userId, long formInstanceId,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		int count = countByU_F(userId, formInstanceId);

		if (count == 0) {
			return null;
		}

		List<DDMFormInstanceRecordVersion> list = findByU_F(
			userId, formInstanceId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm form instance record versions before and after the current ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the primary key of the current ddm form instance record version
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion[] findByU_F_PrevAndNext(
			long formInstanceRecordVersionId, long userId, long formInstanceId,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			findByPrimaryKey(formInstanceRecordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion[] array =
				new DDMFormInstanceRecordVersionImpl[3];

			array[0] = getByU_F_PrevAndNext(
				session, ddmFormInstanceRecordVersion, userId, formInstanceId,
				orderByComparator, true);

			array[1] = ddmFormInstanceRecordVersion;

			array[2] = getByU_F_PrevAndNext(
				session, ddmFormInstanceRecordVersion, userId, formInstanceId,
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

	protected DDMFormInstanceRecordVersion getByU_F_PrevAndNext(
		Session session,
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion, long userId,
		long formInstanceId,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

		sb.append(_FINDER_COLUMN_U_F_USERID_2);

		sb.append(_FINDER_COLUMN_U_F_FORMINSTANCEID_2);

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
			sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(formInstanceId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmFormInstanceRecordVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMFormInstanceRecordVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 */
	@Override
	public void removeByU_F(long userId, long formInstanceId) {
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findByU_F(
					userId, formInstanceId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions where userId = &#63; and formInstanceId = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByU_F(long userId, long formInstanceId) {
		FinderPath finderPath = _finderPathCountByU_F;

		Object[] finderArgs = new Object[] {userId, formInstanceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_U_F_USERID_2);

			sb.append(_FINDER_COLUMN_U_F_FORMINSTANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(formInstanceId);

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

	private static final String _FINDER_COLUMN_U_F_USERID_2 =
		"ddmFormInstanceRecordVersion.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_F_FORMINSTANCEID_2 =
		"ddmFormInstanceRecordVersion.formInstanceId = ?";

	private FinderPath _finderPathWithPaginationFindByF_F;
	private FinderPath _finderPathWithoutPaginationFindByF_F;
	private FinderPath _finderPathCountByF_F;

	/**
	 * Returns all the ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @return the matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_F(
		long formInstanceId, String formInstanceVersion) {

		return findByF_F(
			formInstanceId, formInstanceVersion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_F(
		long formInstanceId, String formInstanceVersion, int start, int end) {

		return findByF_F(formInstanceId, formInstanceVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_F(
		long formInstanceId, String formInstanceVersion, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findByF_F(
			formInstanceId, formInstanceVersion, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_F(
		long formInstanceId, String formInstanceVersion, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean useFinderCache) {

		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByF_F;
				finderArgs = new Object[] {formInstanceId, formInstanceVersion};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByF_F;
			finderArgs = new Object[] {
				formInstanceId, formInstanceVersion, start, end,
				orderByComparator
			};
		}

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
						list) {

					if ((formInstanceId !=
							ddmFormInstanceRecordVersion.getFormInstanceId()) ||
						!formInstanceVersion.equals(
							ddmFormInstanceRecordVersion.
								getFormInstanceVersion())) {

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

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEID_2);

			boolean bindFormInstanceVersion = false;

			if (formInstanceVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_3);
			}
			else {
				bindFormInstanceVersion = true;

				sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceId);

				if (bindFormInstanceVersion) {
					queryPos.add(formInstanceVersion);
				}

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Returns the first ddm form instance record version in the ordered set where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByF_F_First(
			long formInstanceId, String formInstanceVersion,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByF_F_First(
				formInstanceId, formInstanceVersion, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceId=");
		sb.append(formInstanceId);

		sb.append(", formInstanceVersion=");
		sb.append(formInstanceVersion);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm form instance record version in the ordered set where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_F_First(
		long formInstanceId, String formInstanceVersion,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		List<DDMFormInstanceRecordVersion> list = findByF_F(
			formInstanceId, formInstanceVersion, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByF_F_Last(
			long formInstanceId, String formInstanceVersion,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByF_F_Last(
				formInstanceId, formInstanceVersion, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceId=");
		sb.append(formInstanceId);

		sb.append(", formInstanceVersion=");
		sb.append(formInstanceVersion);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_F_Last(
		long formInstanceId, String formInstanceVersion,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		int count = countByF_F(formInstanceId, formInstanceVersion);

		if (count == 0) {
			return null;
		}

		List<DDMFormInstanceRecordVersion> list = findByF_F(
			formInstanceId, formInstanceVersion, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm form instance record versions before and after the current ddm form instance record version in the ordered set where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceRecordVersionId the primary key of the current ddm form instance record version
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion[] findByF_F_PrevAndNext(
			long formInstanceRecordVersionId, long formInstanceId,
			String formInstanceVersion,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			findByPrimaryKey(formInstanceRecordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion[] array =
				new DDMFormInstanceRecordVersionImpl[3];

			array[0] = getByF_F_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceId,
				formInstanceVersion, orderByComparator, true);

			array[1] = ddmFormInstanceRecordVersion;

			array[2] = getByF_F_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceId,
				formInstanceVersion, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMFormInstanceRecordVersion getByF_F_PrevAndNext(
		Session session,
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion,
		long formInstanceId, String formInstanceVersion,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

		sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEID_2);

		boolean bindFormInstanceVersion = false;

		if (formInstanceVersion.isEmpty()) {
			sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_3);
		}
		else {
			bindFormInstanceVersion = true;

			sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_2);
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
			sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formInstanceId);

		if (bindFormInstanceVersion) {
			queryPos.add(formInstanceVersion);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmFormInstanceRecordVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMFormInstanceRecordVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63; from the database.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 */
	@Override
	public void removeByF_F(long formInstanceId, String formInstanceVersion) {
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findByF_F(
					formInstanceId, formInstanceVersion, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions where formInstanceId = &#63; and formInstanceVersion = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByF_F(long formInstanceId, String formInstanceVersion) {
		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		FinderPath finderPath = _finderPathCountByF_F;

		Object[] finderArgs = new Object[] {
			formInstanceId, formInstanceVersion
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEID_2);

			boolean bindFormInstanceVersion = false;

			if (formInstanceVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_3);
			}
			else {
				bindFormInstanceVersion = true;

				sb.append(_FINDER_COLUMN_F_F_FORMINSTANCEVERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceId);

				if (bindFormInstanceVersion) {
					queryPos.add(formInstanceVersion);
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

	private static final String _FINDER_COLUMN_F_F_FORMINSTANCEID_2 =
		"ddmFormInstanceRecordVersion.formInstanceId = ? AND ";

	private static final String _FINDER_COLUMN_F_F_FORMINSTANCEVERSION_2 =
		"ddmFormInstanceRecordVersion.formInstanceVersion = ?";

	private static final String _FINDER_COLUMN_F_F_FORMINSTANCEVERSION_3 =
		"(ddmFormInstanceRecordVersion.formInstanceVersion IS NULL OR ddmFormInstanceRecordVersion.formInstanceVersion = '')";

	private FinderPath _finderPathFetchByF_V;

	/**
	 * Returns the ddm form instance record version where formInstanceRecordId = &#63; and version = &#63; or throws a <code>NoSuchFormInstanceRecordVersionException</code> if it could not be found.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param version the version
	 * @return the matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByF_V(
			long formInstanceRecordId, String version)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = fetchByF_V(
			formInstanceRecordId, version);

		if (ddmFormInstanceRecordVersion == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formInstanceRecordId=");
			sb.append(formInstanceRecordId);

			sb.append(", version=");
			sb.append(version);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormInstanceRecordVersionException(sb.toString());
		}

		return ddmFormInstanceRecordVersion;
	}

	/**
	 * Returns the ddm form instance record version where formInstanceRecordId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param version the version
	 * @return the matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_V(
		long formInstanceRecordId, String version) {

		return fetchByF_V(formInstanceRecordId, version, true);
	}

	/**
	 * Returns the ddm form instance record version where formInstanceRecordId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_V(
		long formInstanceRecordId, String version, boolean useFinderCache) {

		version = Objects.toString(version, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formInstanceRecordId, version};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByF_V, finderArgs, this);
		}

		if (result instanceof DDMFormInstanceRecordVersion) {
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
				(DDMFormInstanceRecordVersion)result;

			if ((formInstanceRecordId !=
					ddmFormInstanceRecordVersion.getFormInstanceRecordId()) ||
				!Objects.equals(
					version, ddmFormInstanceRecordVersion.getVersion())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_V_FORMINSTANCERECORDID_2);

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

				queryPos.add(formInstanceRecordId);

				if (bindVersion) {
					queryPos.add(version);
				}

				List<DDMFormInstanceRecordVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByF_V, finderArgs, list);
					}
				}
				else {
					DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
						list.get(0);

					result = ddmFormInstanceRecordVersion;

					cacheResult(ddmFormInstanceRecordVersion);
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
			return (DDMFormInstanceRecordVersion)result;
		}
	}

	/**
	 * Removes the ddm form instance record version where formInstanceRecordId = &#63; and version = &#63; from the database.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param version the version
	 * @return the ddm form instance record version that was removed
	 */
	@Override
	public DDMFormInstanceRecordVersion removeByF_V(
			long formInstanceRecordId, String version)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = findByF_V(
			formInstanceRecordId, version);

		return remove(ddmFormInstanceRecordVersion);
	}

	/**
	 * Returns the number of ddm form instance record versions where formInstanceRecordId = &#63; and version = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param version the version
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByF_V(long formInstanceRecordId, String version) {
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = fetchByF_V(
			formInstanceRecordId, version);

		if (ddmFormInstanceRecordVersion == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_F_V_FORMINSTANCERECORDID_2 =
		"ddmFormInstanceRecordVersion.formInstanceRecordId = ? AND ";

	private static final String _FINDER_COLUMN_F_V_VERSION_2 =
		"ddmFormInstanceRecordVersion.version = ?";

	private static final String _FINDER_COLUMN_F_V_VERSION_3 =
		"(ddmFormInstanceRecordVersion.version IS NULL OR ddmFormInstanceRecordVersion.version = '')";

	private FinderPath _finderPathWithPaginationFindByF_S;
	private FinderPath _finderPathWithoutPaginationFindByF_S;
	private FinderPath _finderPathCountByF_S;

	/**
	 * Returns all the ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @return the matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_S(
		long formInstanceRecordId, int status) {

		return findByF_S(
			formInstanceRecordId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_S(
		long formInstanceRecordId, int status, int start, int end) {

		return findByF_S(formInstanceRecordId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_S(
		long formInstanceRecordId, int status, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findByF_S(
			formInstanceRecordId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByF_S(
		long formInstanceRecordId, int status, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByF_S;
				finderArgs = new Object[] {formInstanceRecordId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByF_S;
			finderArgs = new Object[] {
				formInstanceRecordId, status, start, end, orderByComparator
			};
		}

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
						list) {

					if ((formInstanceRecordId !=
							ddmFormInstanceRecordVersion.
								getFormInstanceRecordId()) ||
						(status != ddmFormInstanceRecordVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FORMINSTANCERECORDID_2);

			sb.append(_FINDER_COLUMN_F_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordId);

				queryPos.add(status);

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Returns the first ddm form instance record version in the ordered set where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByF_S_First(
			long formInstanceRecordId, int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByF_S_First(formInstanceRecordId, status, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordId=");
		sb.append(formInstanceRecordId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm form instance record version in the ordered set where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_S_First(
		long formInstanceRecordId, int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		List<DDMFormInstanceRecordVersion> list = findByF_S(
			formInstanceRecordId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByF_S_Last(
			long formInstanceRecordId, int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByF_S_Last(formInstanceRecordId, status, orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordId=");
		sb.append(formInstanceRecordId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByF_S_Last(
		long formInstanceRecordId, int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		int count = countByF_S(formInstanceRecordId, status);

		if (count == 0) {
			return null;
		}

		List<DDMFormInstanceRecordVersion> list = findByF_S(
			formInstanceRecordId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm form instance record versions before and after the current ddm form instance record version in the ordered set where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordVersionId the primary key of the current ddm form instance record version
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion[] findByF_S_PrevAndNext(
			long formInstanceRecordVersionId, long formInstanceRecordId,
			int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			findByPrimaryKey(formInstanceRecordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion[] array =
				new DDMFormInstanceRecordVersionImpl[3];

			array[0] = getByF_S_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceRecordId,
				status, orderByComparator, true);

			array[1] = ddmFormInstanceRecordVersion;

			array[2] = getByF_S_PrevAndNext(
				session, ddmFormInstanceRecordVersion, formInstanceRecordId,
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

	protected DDMFormInstanceRecordVersion getByF_S_PrevAndNext(
		Session session,
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion,
		long formInstanceRecordId, int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

		sb.append(_FINDER_COLUMN_F_S_FORMINSTANCERECORDID_2);

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
			sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formInstanceRecordId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmFormInstanceRecordVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMFormInstanceRecordVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63; from the database.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 */
	@Override
	public void removeByF_S(long formInstanceRecordId, int status) {
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findByF_S(
					formInstanceRecordId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions where formInstanceRecordId = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordId the form instance record ID
	 * @param status the status
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByF_S(long formInstanceRecordId, int status) {
		FinderPath finderPath = _finderPathCountByF_S;

		Object[] finderArgs = new Object[] {formInstanceRecordId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FORMINSTANCERECORDID_2);

			sb.append(_FINDER_COLUMN_F_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordId);

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

	private static final String _FINDER_COLUMN_F_S_FORMINSTANCERECORDID_2 =
		"ddmFormInstanceRecordVersion.formInstanceRecordId = ? AND ";

	private static final String _FINDER_COLUMN_F_S_STATUS_2 =
		"ddmFormInstanceRecordVersion.status = ?";

	private FinderPath _finderPathWithPaginationFindByU_F_F_S;
	private FinderPath _finderPathWithoutPaginationFindByU_F_F_S;
	private FinderPath _finderPathCountByU_F_F_S;

	/**
	 * Returns all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @return the matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status) {

		return findByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status, int start, int end) {

		return findByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status, int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean useFinderCache) {

		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_F_F_S;
				finderArgs = new Object[] {
					userId, formInstanceId, formInstanceVersion, status
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_F_F_S;
			finderArgs = new Object[] {
				userId, formInstanceId, formInstanceVersion, status, start, end,
				orderByComparator
			};
		}

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
						list) {

					if ((userId != ddmFormInstanceRecordVersion.getUserId()) ||
						(formInstanceId !=
							ddmFormInstanceRecordVersion.getFormInstanceId()) ||
						!formInstanceVersion.equals(
							ddmFormInstanceRecordVersion.
								getFormInstanceVersion()) ||
						(status != ddmFormInstanceRecordVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_U_F_F_S_USERID_2);

			sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEID_2);

			boolean bindFormInstanceVersion = false;

			if (formInstanceVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_3);
			}
			else {
				bindFormInstanceVersion = true;

				sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_2);
			}

			sb.append(_FINDER_COLUMN_U_F_F_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(formInstanceId);

				if (bindFormInstanceVersion) {
					queryPos.add(formInstanceVersion);
				}

				queryPos.add(status);

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Returns the first ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByU_F_F_S_First(
			long userId, long formInstanceId, String formInstanceVersion,
			int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByU_F_F_S_First(
				userId, formInstanceId, formInstanceVersion, status,
				orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", formInstanceId=");
		sb.append(formInstanceId);

		sb.append(", formInstanceVersion=");
		sb.append(formInstanceVersion);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the first ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByU_F_F_S_First(
		long userId, long formInstanceId, String formInstanceVersion,
		int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		List<DDMFormInstanceRecordVersion> list = findByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByU_F_F_S_Last(
			long userId, long formInstanceId, String formInstanceVersion,
			int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByU_F_F_S_Last(
				userId, formInstanceId, formInstanceVersion, status,
				orderByComparator);

		if (ddmFormInstanceRecordVersion != null) {
			return ddmFormInstanceRecordVersion;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", formInstanceId=");
		sb.append(formInstanceId);

		sb.append(", formInstanceVersion=");
		sb.append(formInstanceVersion);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormInstanceRecordVersionException(sb.toString());
	}

	/**
	 * Returns the last ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm form instance record version, or <code>null</code> if a matching ddm form instance record version could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByU_F_F_S_Last(
		long userId, long formInstanceId, String formInstanceVersion,
		int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		int count = countByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status);

		if (count == 0) {
			return null;
		}

		List<DDMFormInstanceRecordVersion> list = findByU_F_F_S(
			userId, formInstanceId, formInstanceVersion, status, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm form instance record versions before and after the current ddm form instance record version in the ordered set where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param formInstanceRecordVersionId the primary key of the current ddm form instance record version
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion[] findByU_F_F_S_PrevAndNext(
			long formInstanceRecordVersionId, long userId, long formInstanceId,
			String formInstanceVersion, int status,
			OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator)
		throws NoSuchFormInstanceRecordVersionException {

		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			findByPrimaryKey(formInstanceRecordVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion[] array =
				new DDMFormInstanceRecordVersionImpl[3];

			array[0] = getByU_F_F_S_PrevAndNext(
				session, ddmFormInstanceRecordVersion, userId, formInstanceId,
				formInstanceVersion, status, orderByComparator, true);

			array[1] = ddmFormInstanceRecordVersion;

			array[2] = getByU_F_F_S_PrevAndNext(
				session, ddmFormInstanceRecordVersion, userId, formInstanceId,
				formInstanceVersion, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMFormInstanceRecordVersion getByU_F_F_S_PrevAndNext(
		Session session,
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion, long userId,
		long formInstanceId, String formInstanceVersion, int status,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE);

		sb.append(_FINDER_COLUMN_U_F_F_S_USERID_2);

		sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEID_2);

		boolean bindFormInstanceVersion = false;

		if (formInstanceVersion.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_3);
		}
		else {
			bindFormInstanceVersion = true;

			sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_2);
		}

		sb.append(_FINDER_COLUMN_U_F_F_S_STATUS_2);

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
			sb.append(DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(formInstanceId);

		if (bindFormInstanceVersion) {
			queryPos.add(formInstanceVersion);
		}

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmFormInstanceRecordVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMFormInstanceRecordVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 */
	@Override
	public void removeByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status) {

		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findByU_F_F_S(
					userId, formInstanceId, formInstanceVersion, status,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions where userId = &#63; and formInstanceId = &#63; and formInstanceVersion = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param formInstanceId the form instance ID
	 * @param formInstanceVersion the form instance version
	 * @param status the status
	 * @return the number of matching ddm form instance record versions
	 */
	@Override
	public int countByU_F_F_S(
		long userId, long formInstanceId, String formInstanceVersion,
		int status) {

		formInstanceVersion = Objects.toString(formInstanceVersion, "");

		FinderPath finderPath = _finderPathCountByU_F_F_S;

		Object[] finderArgs = new Object[] {
			userId, formInstanceId, formInstanceVersion, status
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE);

			sb.append(_FINDER_COLUMN_U_F_F_S_USERID_2);

			sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEID_2);

			boolean bindFormInstanceVersion = false;

			if (formInstanceVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_3);
			}
			else {
				bindFormInstanceVersion = true;

				sb.append(_FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_2);
			}

			sb.append(_FINDER_COLUMN_U_F_F_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(formInstanceId);

				if (bindFormInstanceVersion) {
					queryPos.add(formInstanceVersion);
				}

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

	private static final String _FINDER_COLUMN_U_F_F_S_USERID_2 =
		"ddmFormInstanceRecordVersion.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_F_F_S_FORMINSTANCEID_2 =
		"ddmFormInstanceRecordVersion.formInstanceId = ? AND ";

	private static final String _FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_2 =
		"ddmFormInstanceRecordVersion.formInstanceVersion = ? AND ";

	private static final String _FINDER_COLUMN_U_F_F_S_FORMINSTANCEVERSION_3 =
		"(ddmFormInstanceRecordVersion.formInstanceVersion IS NULL OR ddmFormInstanceRecordVersion.formInstanceVersion = '') AND ";

	private static final String _FINDER_COLUMN_U_F_F_S_STATUS_2 =
		"ddmFormInstanceRecordVersion.status = ?";

	public DDMFormInstanceRecordVersionPersistenceImpl() {
		setModelClass(DDMFormInstanceRecordVersion.class);

		setModelImplClass(DDMFormInstanceRecordVersionImpl.class);
		setModelPKClass(long.class);

		setTable(DDMFormInstanceRecordVersionTable.INSTANCE);
	}

	/**
	 * Caches the ddm form instance record version in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 */
	@Override
	public void cacheResult(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		entityCache.putResult(
			DDMFormInstanceRecordVersionImpl.class,
			ddmFormInstanceRecordVersion.getPrimaryKey(),
			ddmFormInstanceRecordVersion);

		finderCache.putResult(
			_finderPathFetchByF_V,
			new Object[] {
				ddmFormInstanceRecordVersion.getFormInstanceRecordId(),
				ddmFormInstanceRecordVersion.getVersion()
			},
			ddmFormInstanceRecordVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm form instance record versions in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceRecordVersions the ddm form instance record versions
	 */
	@Override
	public void cacheResult(
		List<DDMFormInstanceRecordVersion> ddmFormInstanceRecordVersions) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmFormInstanceRecordVersions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				ddmFormInstanceRecordVersions) {

			if (entityCache.getResult(
					DDMFormInstanceRecordVersionImpl.class,
					ddmFormInstanceRecordVersion.getPrimaryKey()) == null) {

				cacheResult(ddmFormInstanceRecordVersion);
			}
		}
	}

	/**
	 * Clears the cache for all ddm form instance record versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMFormInstanceRecordVersionImpl.class);

		finderCache.clearCache(DDMFormInstanceRecordVersionImpl.class);
	}

	/**
	 * Clears the cache for the ddm form instance record version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		entityCache.removeResult(
			DDMFormInstanceRecordVersionImpl.class,
			ddmFormInstanceRecordVersion);
	}

	@Override
	public void clearCache(
		List<DDMFormInstanceRecordVersion> ddmFormInstanceRecordVersions) {

		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				ddmFormInstanceRecordVersions) {

			entityCache.removeResult(
				DDMFormInstanceRecordVersionImpl.class,
				ddmFormInstanceRecordVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMFormInstanceRecordVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				DDMFormInstanceRecordVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMFormInstanceRecordVersionModelImpl
			ddmFormInstanceRecordVersionModelImpl) {

		Object[] args = new Object[] {
			ddmFormInstanceRecordVersionModelImpl.getFormInstanceRecordId(),
			ddmFormInstanceRecordVersionModelImpl.getVersion()
		};

		finderCache.putResult(
			_finderPathFetchByF_V, args, ddmFormInstanceRecordVersionModelImpl);
	}

	/**
	 * Creates a new ddm form instance record version with the primary key. Does not add the ddm form instance record version to the database.
	 *
	 * @param formInstanceRecordVersionId the primary key for the new ddm form instance record version
	 * @return the new ddm form instance record version
	 */
	@Override
	public DDMFormInstanceRecordVersion create(
		long formInstanceRecordVersionId) {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			new DDMFormInstanceRecordVersionImpl();

		ddmFormInstanceRecordVersion.setNew(true);
		ddmFormInstanceRecordVersion.setPrimaryKey(formInstanceRecordVersionId);

		ddmFormInstanceRecordVersion.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return ddmFormInstanceRecordVersion;
	}

	/**
	 * Removes the ddm form instance record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion remove(long formInstanceRecordVersionId)
		throws NoSuchFormInstanceRecordVersionException {

		return remove((Serializable)formInstanceRecordVersionId);
	}

	/**
	 * Removes the ddm form instance record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion remove(Serializable primaryKey)
		throws NoSuchFormInstanceRecordVersionException {

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
				(DDMFormInstanceRecordVersion)session.get(
					DDMFormInstanceRecordVersionImpl.class, primaryKey);

			if (ddmFormInstanceRecordVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormInstanceRecordVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmFormInstanceRecordVersion);
		}
		catch (NoSuchFormInstanceRecordVersionException noSuchEntityException) {
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
	protected DDMFormInstanceRecordVersion removeImpl(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmFormInstanceRecordVersion)) {
				ddmFormInstanceRecordVersion =
					(DDMFormInstanceRecordVersion)session.get(
						DDMFormInstanceRecordVersionImpl.class,
						ddmFormInstanceRecordVersion.getPrimaryKeyObj());
			}

			if (ddmFormInstanceRecordVersion != null) {
				session.delete(ddmFormInstanceRecordVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmFormInstanceRecordVersion != null) {
			clearCache(ddmFormInstanceRecordVersion);
		}

		return ddmFormInstanceRecordVersion;
	}

	@Override
	public DDMFormInstanceRecordVersion updateImpl(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		boolean isNew = ddmFormInstanceRecordVersion.isNew();

		if (!(ddmFormInstanceRecordVersion instanceof
				DDMFormInstanceRecordVersionModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					ddmFormInstanceRecordVersion.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmFormInstanceRecordVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmFormInstanceRecordVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMFormInstanceRecordVersion implementation " +
					ddmFormInstanceRecordVersion.getClass());
		}

		DDMFormInstanceRecordVersionModelImpl
			ddmFormInstanceRecordVersionModelImpl =
				(DDMFormInstanceRecordVersionModelImpl)
					ddmFormInstanceRecordVersion;

		if (isNew && (ddmFormInstanceRecordVersion.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				ddmFormInstanceRecordVersion.setCreateDate(date);
			}
			else {
				ddmFormInstanceRecordVersion.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmFormInstanceRecordVersion);
			}
			else {
				ddmFormInstanceRecordVersion =
					(DDMFormInstanceRecordVersion)session.merge(
						ddmFormInstanceRecordVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMFormInstanceRecordVersionImpl.class,
			ddmFormInstanceRecordVersionModelImpl, false, true);

		cacheUniqueFindersCache(ddmFormInstanceRecordVersionModelImpl);

		if (isNew) {
			ddmFormInstanceRecordVersion.setNew(false);
		}

		ddmFormInstanceRecordVersion.resetOriginalValues();

		return ddmFormInstanceRecordVersion;
	}

	/**
	 * Returns the ddm form instance record version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm form instance record version
	 * @return the ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchFormInstanceRecordVersionException {

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion =
			fetchByPrimaryKey(primaryKey);

		if (ddmFormInstanceRecordVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormInstanceRecordVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmFormInstanceRecordVersion;
	}

	/**
	 * Returns the ddm form instance record version with the primary key or throws a <code>NoSuchFormInstanceRecordVersionException</code> if it could not be found.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version
	 * @throws NoSuchFormInstanceRecordVersionException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion findByPrimaryKey(
			long formInstanceRecordVersionId)
		throws NoSuchFormInstanceRecordVersionException {

		return findByPrimaryKey((Serializable)formInstanceRecordVersionId);
	}

	/**
	 * Returns the ddm form instance record version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version, or <code>null</code> if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion fetchByPrimaryKey(
		long formInstanceRecordVersionId) {

		return fetchByPrimaryKey((Serializable)formInstanceRecordVersionId);
	}

	/**
	 * Returns all the ddm form instance record versions.
	 *
	 * @return the ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm form instance record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm form instance record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceRecordVersion> orderByComparator,
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

		List<DDMFormInstanceRecordVersion> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceRecordVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMFORMINSTANCERECORDVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMFORMINSTANCERECORDVERSION;

				sql = sql.concat(
					DDMFormInstanceRecordVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMFormInstanceRecordVersion>)QueryUtil.list(
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
	 * Removes all the ddm form instance record versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion :
				findAll()) {

			remove(ddmFormInstanceRecordVersion);
		}
	}

	/**
	 * Returns the number of ddm form instance record versions.
	 *
	 * @return the number of ddm form instance record versions
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
					_SQL_COUNT_DDMFORMINSTANCERECORDVERSION);

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
		return "formInstanceRecordVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMFORMINSTANCERECORDVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMFormInstanceRecordVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm form instance record version persistence.
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

		_finderPathWithPaginationFindByFormInstanceRecordId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFormInstanceRecordId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"formInstanceRecordId"}, true);

		_finderPathWithoutPaginationFindByFormInstanceRecordId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFormInstanceRecordId", new String[] {Long.class.getName()},
			new String[] {"formInstanceRecordId"}, true);

		_finderPathCountByFormInstanceRecordId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFormInstanceRecordId", new String[] {Long.class.getName()},
			new String[] {"formInstanceRecordId"}, false);

		_finderPathWithPaginationFindByU_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "formInstanceId"}, true);

		_finderPathWithoutPaginationFindByU_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_F",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "formInstanceId"}, true);

		_finderPathCountByU_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_F",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "formInstanceId"}, false);

		_finderPathWithPaginationFindByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"formInstanceId", "formInstanceVersion"}, true);

		_finderPathWithoutPaginationFindByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formInstanceId", "formInstanceVersion"}, true);

		_finderPathCountByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formInstanceId", "formInstanceVersion"}, false);

		_finderPathFetchByF_V = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByF_V",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formInstanceRecordId", "version"}, true);

		_finderPathWithPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"formInstanceRecordId", "status"}, true);

		_finderPathWithoutPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"formInstanceRecordId", "status"}, true);

		_finderPathCountByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"formInstanceRecordId", "status"}, false);

		_finderPathWithPaginationFindByU_F_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_F_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {
				"userId", "formInstanceId", "formInstanceVersion", "status"
			},
			true);

		_finderPathWithoutPaginationFindByU_F_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_F_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			new String[] {
				"userId", "formInstanceId", "formInstanceVersion", "status"
			},
			true);

		_finderPathCountByU_F_F_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_F_F_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			new String[] {
				"userId", "formInstanceId", "formInstanceVersion", "status"
			},
			false);

		DDMFormInstanceRecordVersionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMFormInstanceRecordVersionUtil.setPersistence(null);

		entityCache.removeCache(
			DDMFormInstanceRecordVersionImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMFORMINSTANCERECORDVERSION =
		"SELECT ddmFormInstanceRecordVersion FROM DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion";

	private static final String _SQL_SELECT_DDMFORMINSTANCERECORDVERSION_WHERE =
		"SELECT ddmFormInstanceRecordVersion FROM DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion WHERE ";

	private static final String _SQL_COUNT_DDMFORMINSTANCERECORDVERSION =
		"SELECT COUNT(ddmFormInstanceRecordVersion) FROM DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion";

	private static final String _SQL_COUNT_DDMFORMINSTANCERECORDVERSION_WHERE =
		"SELECT COUNT(ddmFormInstanceRecordVersion) FROM DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"ddmFormInstanceRecordVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMFormInstanceRecordVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMFormInstanceRecordVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceRecordVersionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}