/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service.persistence.impl;

import com.liferay.fragment.exception.DuplicateFragmentCompositionExternalReferenceCodeException;
import com.liferay.fragment.exception.NoSuchCompositionException;
import com.liferay.fragment.model.FragmentComposition;
import com.liferay.fragment.model.FragmentCompositionTable;
import com.liferay.fragment.model.impl.FragmentCompositionImpl;
import com.liferay.fragment.model.impl.FragmentCompositionModelImpl;
import com.liferay.fragment.service.persistence.FragmentCompositionPersistence;
import com.liferay.fragment.service.persistence.FragmentCompositionUtil;
import com.liferay.fragment.service.persistence.impl.constants.FragmentPersistenceConstants;
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ContentTypes;
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
 * The persistence implementation for the fragment composition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FragmentCompositionPersistence.class)
public class FragmentCompositionPersistenceImpl
	extends BasePersistenceImpl<FragmentComposition>
	implements FragmentCompositionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FragmentCompositionUtil</code> to access the fragment composition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FragmentCompositionImpl.class.getName();

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
	 * Returns all the fragment compositions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if (!uuid.equals(fragmentComposition.getUuid())) {
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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

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
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
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

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByUuid_First(
			String uuid,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByUuid_First(
			uuid, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUuid_First(
		String uuid, OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByUuid_Last(
			String uuid,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByUuid_Last(
			uuid, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUuid_Last(
		String uuid, OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where uuid = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByUuid_PrevAndNext(
			long fragmentCompositionId, String uuid,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		uuid = Objects.toString(uuid, "");

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, fragmentComposition, uuid, orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByUuid_PrevAndNext(
				session, fragmentComposition, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FragmentComposition getByUuid_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, String uuid,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
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
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FragmentComposition fragmentComposition :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

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
		"fragmentComposition.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(fragmentComposition.uuid IS NULL OR fragmentComposition.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the fragment composition where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCompositionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByUUID_G(String uuid, long groupId)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByUUID_G(uuid, groupId);

		if (fragmentComposition == null) {
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

			throw new NoSuchCompositionException(sb.toString());
		}

		return fragmentComposition;
	}

	/**
	 * Returns the fragment composition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the fragment composition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof FragmentComposition) {
			FragmentComposition fragmentComposition =
				(FragmentComposition)result;

			if (!Objects.equals(uuid, fragmentComposition.getUuid()) ||
				(groupId != fragmentComposition.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

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

				List<FragmentComposition> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					FragmentComposition fragmentComposition = list.get(0);

					result = fragmentComposition;

					cacheResult(fragmentComposition);
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
			return (FragmentComposition)result;
		}
	}

	/**
	 * Removes the fragment composition where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fragment composition that was removed
	 */
	@Override
	public FragmentComposition removeByUUID_G(String uuid, long groupId)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByUUID_G(uuid, groupId);

		return remove(fragmentComposition);
	}

	/**
	 * Returns the number of fragment compositions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FragmentComposition fragmentComposition = fetchByUUID_G(uuid, groupId);

		if (fragmentComposition == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"fragmentComposition.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(fragmentComposition.uuid IS NULL OR fragmentComposition.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"fragmentComposition.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the fragment compositions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if (!uuid.equals(fragmentComposition.getUuid()) ||
						(companyId != fragmentComposition.getCompanyId())) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

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
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
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

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByUuid_C_PrevAndNext(
			long fragmentCompositionId, String uuid, long companyId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		uuid = Objects.toString(uuid, "");

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, fragmentComposition, uuid, companyId,
				orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByUuid_C_PrevAndNext(
				session, fragmentComposition, uuid, companyId,
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

	protected FragmentComposition getByUuid_C_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, String uuid,
		long companyId,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
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
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FragmentComposition fragmentComposition :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

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
		"fragmentComposition.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(fragmentComposition.uuid IS NULL OR fragmentComposition.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"fragmentComposition.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the fragment compositions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if (groupId != fragmentComposition.getGroupId()) {
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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByGroupId_First(
			long groupId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByGroupId_First(
			groupId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByGroupId_First(
		long groupId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByGroupId_Last(
			long groupId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByGroupId_Last(
		long groupId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where groupId = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByGroupId_PrevAndNext(
			long fragmentCompositionId, long groupId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, fragmentComposition, groupId, orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByGroupId_PrevAndNext(
				session, fragmentComposition, groupId, orderByComparator,
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

	protected FragmentComposition getByGroupId_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, long groupId,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FragmentComposition fragmentComposition :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"fragmentComposition.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByFragmentCollectionId;
	private FinderPath _finderPathWithoutPaginationFindByFragmentCollectionId;
	private FinderPath _finderPathCountByFragmentCollectionId;

	/**
	 * Returns all the fragment compositions where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByFragmentCollectionId(
		long fragmentCollectionId) {

		return findByFragmentCollectionId(
			fragmentCollectionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end) {

		return findByFragmentCollectionId(
			fragmentCollectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByFragmentCollectionId(
			fragmentCollectionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByFragmentCollectionId(
		long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByFragmentCollectionId;
				finderArgs = new Object[] {fragmentCollectionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFragmentCollectionId;
			finderArgs = new Object[] {
				fragmentCollectionId, start, end, orderByComparator
			};
		}

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if (fragmentCollectionId !=
							fragmentComposition.getFragmentCollectionId()) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(
				_FINDER_COLUMN_FRAGMENTCOLLECTIONID_FRAGMENTCOLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fragmentCollectionId);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByFragmentCollectionId_First(
			long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition =
			fetchByFragmentCollectionId_First(
				fragmentCollectionId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByFragmentCollectionId_First(
		long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByFragmentCollectionId(
			fragmentCollectionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByFragmentCollectionId_Last(
			long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition =
			fetchByFragmentCollectionId_Last(
				fragmentCollectionId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByFragmentCollectionId_Last(
		long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByFragmentCollectionId(fragmentCollectionId);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByFragmentCollectionId(
			fragmentCollectionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByFragmentCollectionId_PrevAndNext(
			long fragmentCompositionId, long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByFragmentCollectionId_PrevAndNext(
				session, fragmentComposition, fragmentCollectionId,
				orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByFragmentCollectionId_PrevAndNext(
				session, fragmentComposition, fragmentCollectionId,
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

	protected FragmentComposition getByFragmentCollectionId_PrevAndNext(
		Session session, FragmentComposition fragmentComposition,
		long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_FRAGMENTCOLLECTIONID_FRAGMENTCOLLECTIONID_2);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fragmentCollectionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where fragmentCollectionId = &#63; from the database.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 */
	@Override
	public void removeByFragmentCollectionId(long fragmentCollectionId) {
		for (FragmentComposition fragmentComposition :
				findByFragmentCollectionId(
					fragmentCollectionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByFragmentCollectionId(long fragmentCollectionId) {
		FinderPath finderPath = _finderPathCountByFragmentCollectionId;

		Object[] finderArgs = new Object[] {fragmentCollectionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(
				_FINDER_COLUMN_FRAGMENTCOLLECTIONID_FRAGMENTCOLLECTIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fragmentCollectionId);

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
		_FINDER_COLUMN_FRAGMENTCOLLECTIONID_FRAGMENTCOLLECTIONID_2 =
			"fragmentComposition.fragmentCollectionId = ?";

	private FinderPath _finderPathWithPaginationFindByG_FCI;
	private FinderPath _finderPathWithoutPaginationFindByG_FCI;
	private FinderPath _finderPathCountByG_FCI;

	/**
	 * Returns all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI(
		long groupId, long fragmentCollectionId) {

		return findByG_FCI(
			groupId, fragmentCollectionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end) {

		return findByG_FCI(groupId, fragmentCollectionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByG_FCI(
			groupId, fragmentCollectionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI(
		long groupId, long fragmentCollectionId, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_FCI;
				finderArgs = new Object[] {groupId, fragmentCollectionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_FCI;
			finderArgs = new Object[] {
				groupId, fragmentCollectionId, start, end, orderByComparator
			};
		}

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if ((groupId != fragmentComposition.getGroupId()) ||
						(fragmentCollectionId !=
							fragmentComposition.getFragmentCollectionId())) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_FRAGMENTCOLLECTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_First(
			long groupId, long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_First(
			groupId, fragmentCollectionId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_First(
		long groupId, long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByG_FCI(
			groupId, fragmentCollectionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_Last(
			long groupId, long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_Last(
			groupId, fragmentCollectionId, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_Last(
		long groupId, long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByG_FCI(groupId, fragmentCollectionId);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByG_FCI(
			groupId, fragmentCollectionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByG_FCI_PrevAndNext(
			long fragmentCompositionId, long groupId, long fragmentCollectionId,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByG_FCI_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByG_FCI_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
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

	protected FragmentComposition getByG_FCI_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, long groupId,
		long fragmentCollectionId,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_G_FCI_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_FCI_FRAGMENTCOLLECTIONID_2);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(fragmentCollectionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 */
	@Override
	public void removeByG_FCI(long groupId, long fragmentCollectionId) {
		for (FragmentComposition fragmentComposition :
				findByG_FCI(
					groupId, fragmentCollectionId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63; and fragmentCollectionId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByG_FCI(long groupId, long fragmentCollectionId) {
		FinderPath finderPath = _finderPathCountByG_FCI;

		Object[] finderArgs = new Object[] {groupId, fragmentCollectionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_FRAGMENTCOLLECTIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

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

	private static final String _FINDER_COLUMN_G_FCI_GROUPID_2 =
		"fragmentComposition.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_FRAGMENTCOLLECTIONID_2 =
		"fragmentComposition.fragmentCollectionId = ?";

	private FinderPath _finderPathFetchByG_FCK;

	/**
	 * Returns the fragment composition where groupId = &#63; and fragmentCompositionKey = &#63; or throws a <code>NoSuchCompositionException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param fragmentCompositionKey the fragment composition key
	 * @return the matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCK(
			long groupId, String fragmentCompositionKey)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCK(
			groupId, fragmentCompositionKey);

		if (fragmentComposition == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append(", fragmentCompositionKey=");
			sb.append(fragmentCompositionKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompositionException(sb.toString());
		}

		return fragmentComposition;
	}

	/**
	 * Returns the fragment composition where groupId = &#63; and fragmentCompositionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fragmentCompositionKey the fragment composition key
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCK(
		long groupId, String fragmentCompositionKey) {

		return fetchByG_FCK(groupId, fragmentCompositionKey, true);
	}

	/**
	 * Returns the fragment composition where groupId = &#63; and fragmentCompositionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fragmentCompositionKey the fragment composition key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCK(
		long groupId, String fragmentCompositionKey, boolean useFinderCache) {

		fragmentCompositionKey = Objects.toString(fragmentCompositionKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, fragmentCompositionKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByG_FCK, finderArgs, this);
		}

		if (result instanceof FragmentComposition) {
			FragmentComposition fragmentComposition =
				(FragmentComposition)result;

			if ((groupId != fragmentComposition.getGroupId()) ||
				!Objects.equals(
					fragmentCompositionKey,
					fragmentComposition.getFragmentCompositionKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCK_GROUPID_2);

			boolean bindFragmentCompositionKey = false;

			if (fragmentCompositionKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_FCK_FRAGMENTCOMPOSITIONKEY_3);
			}
			else {
				bindFragmentCompositionKey = true;

				sb.append(_FINDER_COLUMN_G_FCK_FRAGMENTCOMPOSITIONKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindFragmentCompositionKey) {
					queryPos.add(fragmentCompositionKey);
				}

				List<FragmentComposition> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByG_FCK, finderArgs, list);
					}
				}
				else {
					FragmentComposition fragmentComposition = list.get(0);

					result = fragmentComposition;

					cacheResult(fragmentComposition);
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
			return (FragmentComposition)result;
		}
	}

	/**
	 * Removes the fragment composition where groupId = &#63; and fragmentCompositionKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCompositionKey the fragment composition key
	 * @return the fragment composition that was removed
	 */
	@Override
	public FragmentComposition removeByG_FCK(
			long groupId, String fragmentCompositionKey)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByG_FCK(
			groupId, fragmentCompositionKey);

		return remove(fragmentComposition);
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63; and fragmentCompositionKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCompositionKey the fragment composition key
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByG_FCK(long groupId, String fragmentCompositionKey) {
		FragmentComposition fragmentComposition = fetchByG_FCK(
			groupId, fragmentCompositionKey);

		if (fragmentComposition == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_G_FCK_GROUPID_2 =
		"fragmentComposition.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCK_FRAGMENTCOMPOSITIONKEY_2 =
		"fragmentComposition.fragmentCompositionKey = ?";

	private static final String _FINDER_COLUMN_G_FCK_FRAGMENTCOMPOSITIONKEY_3 =
		"(fragmentComposition.fragmentCompositionKey IS NULL OR fragmentComposition.fragmentCompositionKey = '')";

	private FinderPath _finderPathWithPaginationFindByG_FCI_LikeN;
	private FinderPath _finderPathWithPaginationCountByG_FCI_LikeN;

	/**
	 * Returns all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		return findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end) {

		return findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end, OrderByComparator<FragmentComposition> orderByComparator) {

		return findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name, int start,
		int end, OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByG_FCI_LikeN;
		finderArgs = new Object[] {
			groupId, fragmentCollectionId, name, start, end, orderByComparator
		};

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if ((groupId != fragmentComposition.getGroupId()) ||
						(fragmentCollectionId !=
							fragmentComposition.getFragmentCollectionId()) ||
						!StringUtil.wildcardMatches(
							fragmentComposition.getName(), name, '_', '%', '\\',
							true)) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_FRAGMENTCOLLECTIONID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_LikeN_First(
			long groupId, long fragmentCollectionId, String name,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_LikeN_First(
			groupId, fragmentCollectionId, name, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_LikeN_First(
		long groupId, long fragmentCollectionId, String name,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_LikeN_Last(
			long groupId, long fragmentCollectionId, String name,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_LikeN_Last(
			groupId, fragmentCollectionId, name, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_LikeN_Last(
		long groupId, long fragmentCollectionId, String name,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByG_FCI_LikeN(groupId, fragmentCollectionId, name);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByG_FCI_LikeN(
			groupId, fragmentCollectionId, name, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByG_FCI_LikeN_PrevAndNext(
			long fragmentCompositionId, long groupId, long fragmentCollectionId,
			String name,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		name = Objects.toString(name, "");

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByG_FCI_LikeN_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				name, orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByG_FCI_LikeN_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FragmentComposition getByG_FCI_LikeN_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, long groupId,
		long fragmentCollectionId, String name,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_G_FCI_LIKEN_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_FCI_LIKEN_FRAGMENTCOLLECTIONID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_2);
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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(fragmentCollectionId);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 */
	@Override
	public void removeByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		for (FragmentComposition fragmentComposition :
				findByG_FCI_LikeN(
					groupId, fragmentCollectionId, name, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByG_FCI_LikeN(
		long groupId, long fragmentCollectionId, String name) {

		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByG_FCI_LikeN;

		Object[] finderArgs = new Object[] {
			groupId, fragmentCollectionId, name
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_FRAGMENTCOLLECTIONID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				if (bindName) {
					queryPos.add(name);
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

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_GROUPID_2 =
		"fragmentComposition.groupId = ? AND ";

	private static final String
		_FINDER_COLUMN_G_FCI_LIKEN_FRAGMENTCOLLECTIONID_2 =
			"fragmentComposition.fragmentCollectionId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_NAME_2 =
		"fragmentComposition.name LIKE ?";

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_NAME_3 =
		"(fragmentComposition.name IS NULL OR fragmentComposition.name LIKE '')";

	private FinderPath _finderPathWithPaginationFindByG_FCI_S;
	private FinderPath _finderPathWithoutPaginationFindByG_FCI_S;
	private FinderPath _finderPathCountByG_FCI_S;

	/**
	 * Returns all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		return findByG_FCI_S(
			groupId, fragmentCollectionId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start,
		int end) {

		return findByG_FCI_S(
			groupId, fragmentCollectionId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByG_FCI_S(
			groupId, fragmentCollectionId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_S(
		long groupId, long fragmentCollectionId, int status, int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_FCI_S;
				finderArgs = new Object[] {
					groupId, fragmentCollectionId, status
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_FCI_S;
			finderArgs = new Object[] {
				groupId, fragmentCollectionId, status, start, end,
				orderByComparator
			};
		}

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if ((groupId != fragmentComposition.getGroupId()) ||
						(fragmentCollectionId !=
							fragmentComposition.getFragmentCollectionId()) ||
						(status != fragmentComposition.getStatus())) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_S_FRAGMENTCOLLECTIONID_2);

			sb.append(_FINDER_COLUMN_G_FCI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				queryPos.add(status);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_S_First(
			long groupId, long fragmentCollectionId, int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_S_First(
			groupId, fragmentCollectionId, status, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_S_First(
		long groupId, long fragmentCollectionId, int status,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByG_FCI_S(
			groupId, fragmentCollectionId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_S_Last(
			long groupId, long fragmentCollectionId, int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_S_Last(
			groupId, fragmentCollectionId, status, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_S_Last(
		long groupId, long fragmentCollectionId, int status,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByG_FCI_S(groupId, fragmentCollectionId, status);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByG_FCI_S(
			groupId, fragmentCollectionId, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByG_FCI_S_PrevAndNext(
			long fragmentCompositionId, long groupId, long fragmentCollectionId,
			int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByG_FCI_S_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				status, orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByG_FCI_S_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
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

	protected FragmentComposition getByG_FCI_S_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, long groupId,
		long fragmentCollectionId, int status,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_G_FCI_S_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_FCI_S_FRAGMENTCOLLECTIONID_2);

		sb.append(_FINDER_COLUMN_G_FCI_S_STATUS_2);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(fragmentCollectionId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 */
	@Override
	public void removeByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		for (FragmentComposition fragmentComposition :
				findByG_FCI_S(
					groupId, fragmentCollectionId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param status the status
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByG_FCI_S(
		long groupId, long fragmentCollectionId, int status) {

		FinderPath finderPath = _finderPathCountByG_FCI_S;

		Object[] finderArgs = new Object[] {
			groupId, fragmentCollectionId, status
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_S_FRAGMENTCOLLECTIONID_2);

			sb.append(_FINDER_COLUMN_G_FCI_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

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

	private static final String _FINDER_COLUMN_G_FCI_S_GROUPID_2 =
		"fragmentComposition.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_S_FRAGMENTCOLLECTIONID_2 =
		"fragmentComposition.fragmentCollectionId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_S_STATUS_2 =
		"fragmentComposition.status = ?";

	private FinderPath _finderPathWithPaginationFindByG_FCI_LikeN_S;
	private FinderPath _finderPathWithPaginationCountByG_FCI_LikeN_S;

	/**
	 * Returns all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @return the matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		return findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end) {

		return findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fragment compositions
	 */
	@Override
	public List<FragmentComposition> findByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status,
		int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByG_FCI_LikeN_S;
		finderArgs = new Object[] {
			groupId, fragmentCollectionId, name, status, start, end,
			orderByComparator
		};

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentComposition fragmentComposition : list) {
					if ((groupId != fragmentComposition.getGroupId()) ||
						(fragmentCollectionId !=
							fragmentComposition.getFragmentCollectionId()) ||
						!StringUtil.wildcardMatches(
							fragmentComposition.getName(), name, '_', '%', '\\',
							true) ||
						(status != fragmentComposition.getStatus())) {

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

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_FRAGMENTCOLLECTIONID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_2);
			}

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				if (bindName) {
					queryPos.add(name);
				}

				queryPos.add(status);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_LikeN_S_First(
			long groupId, long fragmentCollectionId, String name, int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_LikeN_S_First(
			groupId, fragmentCollectionId, name, status, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the first fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_LikeN_S_First(
		long groupId, long fragmentCollectionId, String name, int status,
		OrderByComparator<FragmentComposition> orderByComparator) {

		List<FragmentComposition> list = findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByG_FCI_LikeN_S_Last(
			long groupId, long fragmentCollectionId, String name, int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByG_FCI_LikeN_S_Last(
			groupId, fragmentCollectionId, name, status, orderByComparator);

		if (fragmentComposition != null) {
			return fragmentComposition;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", fragmentCollectionId=");
		sb.append(fragmentCollectionId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchCompositionException(sb.toString());
	}

	/**
	 * Returns the last fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByG_FCI_LikeN_S_Last(
		long groupId, long fragmentCollectionId, String name, int status,
		OrderByComparator<FragmentComposition> orderByComparator) {

		int count = countByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status);

		if (count == 0) {
			return null;
		}

		List<FragmentComposition> list = findByG_FCI_LikeN_S(
			groupId, fragmentCollectionId, name, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment compositions before and after the current fragment composition in the ordered set where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param fragmentCompositionId the primary key of the current fragment composition
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition[] findByG_FCI_LikeN_S_PrevAndNext(
			long fragmentCompositionId, long groupId, long fragmentCollectionId,
			String name, int status,
			OrderByComparator<FragmentComposition> orderByComparator)
		throws NoSuchCompositionException {

		name = Objects.toString(name, "");

		FragmentComposition fragmentComposition = findByPrimaryKey(
			fragmentCompositionId);

		Session session = null;

		try {
			session = openSession();

			FragmentComposition[] array = new FragmentCompositionImpl[3];

			array[0] = getByG_FCI_LikeN_S_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				name, status, orderByComparator, true);

			array[1] = fragmentComposition;

			array[2] = getByG_FCI_LikeN_S_PrevAndNext(
				session, fragmentComposition, groupId, fragmentCollectionId,
				name, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FragmentComposition getByG_FCI_LikeN_S_PrevAndNext(
		Session session, FragmentComposition fragmentComposition, long groupId,
		long fragmentCollectionId, String name, int status,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

		sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_FRAGMENTCOLLECTIONID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_2);
		}

		sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_STATUS_2);

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
			sb.append(FragmentCompositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(fragmentCollectionId);

		if (bindName) {
			queryPos.add(name);
		}

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fragmentComposition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FragmentComposition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 */
	@Override
	public void removeByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		for (FragmentComposition fragmentComposition :
				findByG_FCI_LikeN_S(
					groupId, fragmentCollectionId, name, status,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions where groupId = &#63; and fragmentCollectionId = &#63; and name LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentCollectionId the fragment collection ID
	 * @param name the name
	 * @param status the status
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByG_FCI_LikeN_S(
		long groupId, long fragmentCollectionId, String name, int status) {

		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByG_FCI_LikeN_S;

		Object[] finderArgs = new Object[] {
			groupId, fragmentCollectionId, name, status
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_FRAGMENTCOMPOSITION_WHERE);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_FRAGMENTCOLLECTIONID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_NAME_2);
			}

			sb.append(_FINDER_COLUMN_G_FCI_LIKEN_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(fragmentCollectionId);

				if (bindName) {
					queryPos.add(name);
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

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_S_GROUPID_2 =
		"fragmentComposition.groupId = ? AND ";

	private static final String
		_FINDER_COLUMN_G_FCI_LIKEN_S_FRAGMENTCOLLECTIONID_2 =
			"fragmentComposition.fragmentCollectionId = ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_S_NAME_2 =
		"fragmentComposition.name LIKE ? AND ";

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_S_NAME_3 =
		"(fragmentComposition.name IS NULL OR fragmentComposition.name LIKE '') AND ";

	private static final String _FINDER_COLUMN_G_FCI_LIKEN_S_STATUS_2 =
		"fragmentComposition.status = ?";

	private FinderPath _finderPathFetchByERC_G;

	/**
	 * Returns the fragment composition where externalReferenceCode = &#63; and groupId = &#63; or throws a <code>NoSuchCompositionException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the matching fragment composition
	 * @throws NoSuchCompositionException if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition findByERC_G(
			String externalReferenceCode, long groupId)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByERC_G(
			externalReferenceCode, groupId);

		if (fragmentComposition == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("externalReferenceCode=");
			sb.append(externalReferenceCode);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompositionException(sb.toString());
		}

		return fragmentComposition;
	}

	/**
	 * Returns the fragment composition where externalReferenceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByERC_G(
		String externalReferenceCode, long groupId) {

		return fetchByERC_G(externalReferenceCode, groupId, true);
	}

	/**
	 * Returns the fragment composition where externalReferenceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fragment composition, or <code>null</code> if a matching fragment composition could not be found
	 */
	@Override
	public FragmentComposition fetchByERC_G(
		String externalReferenceCode, long groupId, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {externalReferenceCode, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByERC_G, finderArgs, this);
		}

		if (result instanceof FragmentComposition) {
			FragmentComposition fragmentComposition =
				(FragmentComposition)result;

			if (!Objects.equals(
					externalReferenceCode,
					fragmentComposition.getExternalReferenceCode()) ||
				(groupId != fragmentComposition.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION_WHERE);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_ERC_G_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_ERC_G_EXTERNALREFERENCECODE_2);
			}

			sb.append(_FINDER_COLUMN_ERC_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				queryPos.add(groupId);

				List<FragmentComposition> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByERC_G, finderArgs, list);
					}
				}
				else {
					FragmentComposition fragmentComposition = list.get(0);

					result = fragmentComposition;

					cacheResult(fragmentComposition);
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
			return (FragmentComposition)result;
		}
	}

	/**
	 * Removes the fragment composition where externalReferenceCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the fragment composition that was removed
	 */
	@Override
	public FragmentComposition removeByERC_G(
			String externalReferenceCode, long groupId)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = findByERC_G(
			externalReferenceCode, groupId);

		return remove(fragmentComposition);
	}

	/**
	 * Returns the number of fragment compositions where externalReferenceCode = &#63; and groupId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the number of matching fragment compositions
	 */
	@Override
	public int countByERC_G(String externalReferenceCode, long groupId) {
		FragmentComposition fragmentComposition = fetchByERC_G(
			externalReferenceCode, groupId);

		if (fragmentComposition == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_ERC_G_EXTERNALREFERENCECODE_2 =
		"fragmentComposition.externalReferenceCode = ? AND ";

	private static final String _FINDER_COLUMN_ERC_G_EXTERNALREFERENCECODE_3 =
		"(fragmentComposition.externalReferenceCode IS NULL OR fragmentComposition.externalReferenceCode = '') AND ";

	private static final String _FINDER_COLUMN_ERC_G_GROUPID_2 =
		"fragmentComposition.groupId = ?";

	public FragmentCompositionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("data", "data_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FragmentComposition.class);

		setModelImplClass(FragmentCompositionImpl.class);
		setModelPKClass(long.class);

		setTable(FragmentCompositionTable.INSTANCE);
	}

	/**
	 * Caches the fragment composition in the entity cache if it is enabled.
	 *
	 * @param fragmentComposition the fragment composition
	 */
	@Override
	public void cacheResult(FragmentComposition fragmentComposition) {
		entityCache.putResult(
			FragmentCompositionImpl.class, fragmentComposition.getPrimaryKey(),
			fragmentComposition);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				fragmentComposition.getUuid(), fragmentComposition.getGroupId()
			},
			fragmentComposition);

		finderCache.putResult(
			_finderPathFetchByG_FCK,
			new Object[] {
				fragmentComposition.getGroupId(),
				fragmentComposition.getFragmentCompositionKey()
			},
			fragmentComposition);

		finderCache.putResult(
			_finderPathFetchByERC_G,
			new Object[] {
				fragmentComposition.getExternalReferenceCode(),
				fragmentComposition.getGroupId()
			},
			fragmentComposition);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fragment compositions in the entity cache if it is enabled.
	 *
	 * @param fragmentCompositions the fragment compositions
	 */
	@Override
	public void cacheResult(List<FragmentComposition> fragmentCompositions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (fragmentCompositions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FragmentComposition fragmentComposition : fragmentCompositions) {
			if (entityCache.getResult(
					FragmentCompositionImpl.class,
					fragmentComposition.getPrimaryKey()) == null) {

				cacheResult(fragmentComposition);
			}
		}
	}

	/**
	 * Clears the cache for all fragment compositions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FragmentCompositionImpl.class);

		finderCache.clearCache(FragmentCompositionImpl.class);
	}

	/**
	 * Clears the cache for the fragment composition.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FragmentComposition fragmentComposition) {
		entityCache.removeResult(
			FragmentCompositionImpl.class, fragmentComposition);
	}

	@Override
	public void clearCache(List<FragmentComposition> fragmentCompositions) {
		for (FragmentComposition fragmentComposition : fragmentCompositions) {
			entityCache.removeResult(
				FragmentCompositionImpl.class, fragmentComposition);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FragmentCompositionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FragmentCompositionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FragmentCompositionModelImpl fragmentCompositionModelImpl) {

		Object[] args = new Object[] {
			fragmentCompositionModelImpl.getUuid(),
			fragmentCompositionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args, fragmentCompositionModelImpl);

		args = new Object[] {
			fragmentCompositionModelImpl.getGroupId(),
			fragmentCompositionModelImpl.getFragmentCompositionKey()
		};

		finderCache.putResult(
			_finderPathFetchByG_FCK, args, fragmentCompositionModelImpl);

		args = new Object[] {
			fragmentCompositionModelImpl.getExternalReferenceCode(),
			fragmentCompositionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByERC_G, args, fragmentCompositionModelImpl);
	}

	/**
	 * Creates a new fragment composition with the primary key. Does not add the fragment composition to the database.
	 *
	 * @param fragmentCompositionId the primary key for the new fragment composition
	 * @return the new fragment composition
	 */
	@Override
	public FragmentComposition create(long fragmentCompositionId) {
		FragmentComposition fragmentComposition = new FragmentCompositionImpl();

		fragmentComposition.setNew(true);
		fragmentComposition.setPrimaryKey(fragmentCompositionId);

		String uuid = PortalUUIDUtil.generate();

		fragmentComposition.setUuid(uuid);

		fragmentComposition.setCompanyId(CompanyThreadLocal.getCompanyId());

		return fragmentComposition;
	}

	/**
	 * Removes the fragment composition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentCompositionId the primary key of the fragment composition
	 * @return the fragment composition that was removed
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition remove(long fragmentCompositionId)
		throws NoSuchCompositionException {

		return remove((Serializable)fragmentCompositionId);
	}

	/**
	 * Removes the fragment composition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fragment composition
	 * @return the fragment composition that was removed
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition remove(Serializable primaryKey)
		throws NoSuchCompositionException {

		Session session = null;

		try {
			session = openSession();

			FragmentComposition fragmentComposition =
				(FragmentComposition)session.get(
					FragmentCompositionImpl.class, primaryKey);

			if (fragmentComposition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompositionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fragmentComposition);
		}
		catch (NoSuchCompositionException noSuchEntityException) {
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
	protected FragmentComposition removeImpl(
		FragmentComposition fragmentComposition) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fragmentComposition)) {
				fragmentComposition = (FragmentComposition)session.get(
					FragmentCompositionImpl.class,
					fragmentComposition.getPrimaryKeyObj());
			}

			if (fragmentComposition != null) {
				session.delete(fragmentComposition);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (fragmentComposition != null) {
			clearCache(fragmentComposition);
		}

		return fragmentComposition;
	}

	@Override
	public FragmentComposition updateImpl(
		FragmentComposition fragmentComposition) {

		boolean isNew = fragmentComposition.isNew();

		if (!(fragmentComposition instanceof FragmentCompositionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(fragmentComposition.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					fragmentComposition);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in fragmentComposition proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FragmentComposition implementation " +
					fragmentComposition.getClass());
		}

		FragmentCompositionModelImpl fragmentCompositionModelImpl =
			(FragmentCompositionModelImpl)fragmentComposition;

		if (Validator.isNull(fragmentComposition.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			fragmentComposition.setUuid(uuid);
		}

		if (Validator.isNull(fragmentComposition.getExternalReferenceCode())) {
			fragmentComposition.setExternalReferenceCode(
				fragmentComposition.getUuid());
		}
		else {
			if (!Objects.equals(
					fragmentCompositionModelImpl.getColumnOriginalValue(
						"externalReferenceCode"),
					fragmentComposition.getExternalReferenceCode())) {

				long userId = GetterUtil.getLong(
					PrincipalThreadLocal.getName());

				if (userId > 0) {
					long companyId = fragmentComposition.getCompanyId();

					long groupId = fragmentComposition.getGroupId();

					long classPK = 0;

					if (!isNew) {
						classPK = fragmentComposition.getPrimaryKey();
					}

					try {
						fragmentComposition.setExternalReferenceCode(
							SanitizerUtil.sanitize(
								companyId, groupId, userId,
								FragmentComposition.class.getName(), classPK,
								ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
								fragmentComposition.getExternalReferenceCode(),
								null));
					}
					catch (SanitizerException sanitizerException) {
						throw new SystemException(sanitizerException);
					}
				}
			}

			FragmentComposition ercFragmentComposition = fetchByERC_G(
				fragmentComposition.getExternalReferenceCode(),
				fragmentComposition.getGroupId());

			if (isNew) {
				if (ercFragmentComposition != null) {
					throw new DuplicateFragmentCompositionExternalReferenceCodeException(
						"Duplicate fragment composition with external reference code " +
							fragmentComposition.getExternalReferenceCode() +
								" and group " +
									fragmentComposition.getGroupId());
				}
			}
			else {
				if ((ercFragmentComposition != null) &&
					(fragmentComposition.getFragmentCompositionId() !=
						ercFragmentComposition.getFragmentCompositionId())) {

					throw new DuplicateFragmentCompositionExternalReferenceCodeException(
						"Duplicate fragment composition with external reference code " +
							fragmentComposition.getExternalReferenceCode() +
								" and group " +
									fragmentComposition.getGroupId());
				}
			}
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (fragmentComposition.getCreateDate() == null)) {
			if (serviceContext == null) {
				fragmentComposition.setCreateDate(date);
			}
			else {
				fragmentComposition.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!fragmentCompositionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				fragmentComposition.setModifiedDate(date);
			}
			else {
				fragmentComposition.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(fragmentComposition);
			}
			else {
				fragmentComposition = (FragmentComposition)session.merge(
					fragmentComposition);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FragmentCompositionImpl.class, fragmentCompositionModelImpl, false,
			true);

		cacheUniqueFindersCache(fragmentCompositionModelImpl);

		if (isNew) {
			fragmentComposition.setNew(false);
		}

		fragmentComposition.resetOriginalValues();

		return fragmentComposition;
	}

	/**
	 * Returns the fragment composition with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fragment composition
	 * @return the fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompositionException {

		FragmentComposition fragmentComposition = fetchByPrimaryKey(primaryKey);

		if (fragmentComposition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompositionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fragmentComposition;
	}

	/**
	 * Returns the fragment composition with the primary key or throws a <code>NoSuchCompositionException</code> if it could not be found.
	 *
	 * @param fragmentCompositionId the primary key of the fragment composition
	 * @return the fragment composition
	 * @throws NoSuchCompositionException if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition findByPrimaryKey(long fragmentCompositionId)
		throws NoSuchCompositionException {

		return findByPrimaryKey((Serializable)fragmentCompositionId);
	}

	/**
	 * Returns the fragment composition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fragmentCompositionId the primary key of the fragment composition
	 * @return the fragment composition, or <code>null</code> if a fragment composition with the primary key could not be found
	 */
	@Override
	public FragmentComposition fetchByPrimaryKey(long fragmentCompositionId) {
		return fetchByPrimaryKey((Serializable)fragmentCompositionId);
	}

	/**
	 * Returns all the fragment compositions.
	 *
	 * @return the fragment compositions
	 */
	@Override
	public List<FragmentComposition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment compositions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @return the range of fragment compositions
	 */
	@Override
	public List<FragmentComposition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment compositions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fragment compositions
	 */
	@Override
	public List<FragmentComposition> findAll(
		int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment compositions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FragmentCompositionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment compositions
	 * @param end the upper bound of the range of fragment compositions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fragment compositions
	 */
	@Override
	public List<FragmentComposition> findAll(
		int start, int end,
		OrderByComparator<FragmentComposition> orderByComparator,
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

		List<FragmentComposition> list = null;

		if (useFinderCache) {
			list = (List<FragmentComposition>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FRAGMENTCOMPOSITION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FRAGMENTCOMPOSITION;

				sql = sql.concat(FragmentCompositionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FragmentComposition>)QueryUtil.list(
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
	 * Removes all the fragment compositions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FragmentComposition fragmentComposition : findAll()) {
			remove(fragmentComposition);
		}
	}

	/**
	 * Returns the number of fragment compositions.
	 *
	 * @return the number of fragment compositions
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
					_SQL_COUNT_FRAGMENTCOMPOSITION);

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
		return "fragmentCompositionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FRAGMENTCOMPOSITION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FragmentCompositionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fragment composition persistence.
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

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByFragmentCollectionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFragmentCollectionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"fragmentCollectionId"}, true);

		_finderPathWithoutPaginationFindByFragmentCollectionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFragmentCollectionId", new String[] {Long.class.getName()},
			new String[] {"fragmentCollectionId"}, true);

		_finderPathCountByFragmentCollectionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFragmentCollectionId", new String[] {Long.class.getName()},
			new String[] {"fragmentCollectionId"}, false);

		_finderPathWithPaginationFindByG_FCI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_FCI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId"}, true);

		_finderPathWithoutPaginationFindByG_FCI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_FCI",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "fragmentCollectionId"}, true);

		_finderPathCountByG_FCI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_FCI",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "fragmentCollectionId"}, false);

		_finderPathFetchByG_FCK = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByG_FCK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "fragmentCompositionKey"}, true);

		_finderPathWithPaginationFindByG_FCI_LikeN = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_FCI_LikeN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "name"}, true);

		_finderPathWithPaginationCountByG_FCI_LikeN = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_FCI_LikeN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "name"}, false);

		_finderPathWithPaginationFindByG_FCI_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_FCI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "status"}, true);

		_finderPathWithoutPaginationFindByG_FCI_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_FCI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "status"}, true);

		_finderPathCountByG_FCI_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_FCI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "status"}, false);

		_finderPathWithPaginationFindByG_FCI_LikeN_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_FCI_LikeN_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "name", "status"},
			true);

		_finderPathWithPaginationCountByG_FCI_LikeN_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_FCI_LikeN_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			new String[] {"groupId", "fragmentCollectionId", "name", "status"},
			false);

		_finderPathFetchByERC_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByERC_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"externalReferenceCode", "groupId"}, true);

		FragmentCompositionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FragmentCompositionUtil.setPersistence(null);

		entityCache.removeCache(FragmentCompositionImpl.class.getName());
	}

	@Override
	@Reference(
		target = FragmentPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = FragmentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = FragmentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FRAGMENTCOMPOSITION =
		"SELECT fragmentComposition FROM FragmentComposition fragmentComposition";

	private static final String _SQL_SELECT_FRAGMENTCOMPOSITION_WHERE =
		"SELECT fragmentComposition FROM FragmentComposition fragmentComposition WHERE ";

	private static final String _SQL_COUNT_FRAGMENTCOMPOSITION =
		"SELECT COUNT(fragmentComposition) FROM FragmentComposition fragmentComposition";

	private static final String _SQL_COUNT_FRAGMENTCOMPOSITION_WHERE =
		"SELECT COUNT(fragmentComposition) FROM FragmentComposition fragmentComposition WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fragmentComposition.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FragmentComposition exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FragmentComposition exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentCompositionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "data"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}