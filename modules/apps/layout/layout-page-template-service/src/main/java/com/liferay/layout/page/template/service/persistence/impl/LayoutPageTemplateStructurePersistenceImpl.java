/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.service.persistence.impl;

import com.liferay.layout.page.template.exception.NoSuchPageTemplateStructureException;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureTable;
import com.liferay.layout.page.template.model.impl.LayoutPageTemplateStructureImpl;
import com.liferay.layout.page.template.model.impl.LayoutPageTemplateStructureModelImpl;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateStructurePersistence;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateStructureUtil;
import com.liferay.layout.page.template.service.persistence.impl.constants.LayoutPersistenceConstants;
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
 * The persistence implementation for the layout page template structure service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LayoutPageTemplateStructurePersistence.class)
public class LayoutPageTemplateStructurePersistenceImpl
	extends BasePersistenceImpl<LayoutPageTemplateStructure>
	implements LayoutPageTemplateStructurePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LayoutPageTemplateStructureUtil</code> to access the layout page template structure persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LayoutPageTemplateStructureImpl.class.getName();

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
	 * Returns all the layout page template structures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template structures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @return the range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		List<LayoutPageTemplateStructure> list = null;

		if (useFinderCache) {
			list = (List<LayoutPageTemplateStructure>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateStructure layoutPageTemplateStructure :
						list) {

					if (!uuid.equals(layoutPageTemplateStructure.getUuid())) {
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

			sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
				sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
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

				list = (List<LayoutPageTemplateStructure>)QueryUtil.list(
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
	 * Returns the first layout page template structure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByUuid_First(
			String uuid,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByUuid_First(uuid, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the first layout page template structure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUuid_First(
		String uuid,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		List<LayoutPageTemplateStructure> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template structure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByUuid_Last(
			String uuid,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByUuid_Last(uuid, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the last layout page template structure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUuid_Last(
		String uuid,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateStructure> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template structures before and after the current layout page template structure in the ordered set where uuid = &#63;.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the current layout page template structure
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure[] findByUuid_PrevAndNext(
			long layoutPageTemplateStructureId, String uuid,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		uuid = Objects.toString(uuid, "");

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			findByPrimaryKey(layoutPageTemplateStructureId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateStructure[] array =
				new LayoutPageTemplateStructureImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, layoutPageTemplateStructure, uuid, orderByComparator,
				true);

			array[1] = layoutPageTemplateStructure;

			array[2] = getByUuid_PrevAndNext(
				session, layoutPageTemplateStructure, uuid, orderByComparator,
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

	protected LayoutPageTemplateStructure getByUuid_PrevAndNext(
		Session session,
		LayoutPageTemplateStructure layoutPageTemplateStructure, String uuid,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
			sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
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
						layoutPageTemplateStructure)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutPageTemplateStructure> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template structures where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutPageTemplateStructure);
		}
	}

	/**
	 * Returns the number of layout page template structures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout page template structures
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
		"layoutPageTemplateStructure.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(layoutPageTemplateStructure.uuid IS NULL OR layoutPageTemplateStructure.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the layout page template structure where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchPageTemplateStructureException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure = fetchByUUID_G(
			uuid, groupId);

		if (layoutPageTemplateStructure == null) {
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

			throw new NoSuchPageTemplateStructureException(sb.toString());
		}

		return layoutPageTemplateStructure;
	}

	/**
	 * Returns the layout page template structure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUUID_G(
		String uuid, long groupId) {

		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the layout page template structure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUUID_G(
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

		if (result instanceof LayoutPageTemplateStructure) {
			LayoutPageTemplateStructure layoutPageTemplateStructure =
				(LayoutPageTemplateStructure)result;

			if (!Objects.equals(uuid, layoutPageTemplateStructure.getUuid()) ||
				(groupId != layoutPageTemplateStructure.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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

				List<LayoutPageTemplateStructure> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					LayoutPageTemplateStructure layoutPageTemplateStructure =
						list.get(0);

					result = layoutPageTemplateStructure;

					cacheResult(layoutPageTemplateStructure);
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
			return (LayoutPageTemplateStructure)result;
		}
	}

	/**
	 * Removes the layout page template structure where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the layout page template structure that was removed
	 */
	@Override
	public LayoutPageTemplateStructure removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure = findByUUID_G(
			uuid, groupId);

		return remove(layoutPageTemplateStructure);
	}

	/**
	 * Returns the number of layout page template structures where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching layout page template structures
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		LayoutPageTemplateStructure layoutPageTemplateStructure = fetchByUUID_G(
			uuid, groupId);

		if (layoutPageTemplateStructure == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"layoutPageTemplateStructure.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(layoutPageTemplateStructure.uuid IS NULL OR layoutPageTemplateStructure.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"layoutPageTemplateStructure.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the layout page template structures where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template structures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @return the range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		List<LayoutPageTemplateStructure> list = null;

		if (useFinderCache) {
			list = (List<LayoutPageTemplateStructure>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateStructure layoutPageTemplateStructure :
						list) {

					if (!uuid.equals(layoutPageTemplateStructure.getUuid()) ||
						(companyId !=
							layoutPageTemplateStructure.getCompanyId())) {

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

			sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
				sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
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

				list = (List<LayoutPageTemplateStructure>)QueryUtil.list(
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
	 * Returns the first layout page template structure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the first layout page template structure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		List<LayoutPageTemplateStructure> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template structure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the last layout page template structure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateStructure> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template structures before and after the current layout page template structure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the current layout page template structure
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure[] findByUuid_C_PrevAndNext(
			long layoutPageTemplateStructureId, String uuid, long companyId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		uuid = Objects.toString(uuid, "");

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			findByPrimaryKey(layoutPageTemplateStructureId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateStructure[] array =
				new LayoutPageTemplateStructureImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, layoutPageTemplateStructure, uuid, companyId,
				orderByComparator, true);

			array[1] = layoutPageTemplateStructure;

			array[2] = getByUuid_C_PrevAndNext(
				session, layoutPageTemplateStructure, uuid, companyId,
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

	protected LayoutPageTemplateStructure getByUuid_C_PrevAndNext(
		Session session,
		LayoutPageTemplateStructure layoutPageTemplateStructure, String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
			sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
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
						layoutPageTemplateStructure)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutPageTemplateStructure> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template structures where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(layoutPageTemplateStructure);
		}
	}

	/**
	 * Returns the number of layout page template structures where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout page template structures
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
		"layoutPageTemplateStructure.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(layoutPageTemplateStructure.uuid IS NULL OR layoutPageTemplateStructure.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"layoutPageTemplateStructure.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the layout page template structures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template structures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @return the range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template structures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		List<LayoutPageTemplateStructure> list = null;

		if (useFinderCache) {
			list = (List<LayoutPageTemplateStructure>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateStructure layoutPageTemplateStructure :
						list) {

					if (groupId != layoutPageTemplateStructure.getGroupId()) {
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

			sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<LayoutPageTemplateStructure>)QueryUtil.list(
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
	 * Returns the first layout page template structure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByGroupId_First(
			long groupId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByGroupId_First(groupId, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the first layout page template structure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByGroupId_First(
		long groupId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		List<LayoutPageTemplateStructure> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template structure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByGroupId_Last(
			long groupId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByGroupId_Last(groupId, orderByComparator);

		if (layoutPageTemplateStructure != null) {
			return layoutPageTemplateStructure;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchPageTemplateStructureException(sb.toString());
	}

	/**
	 * Returns the last layout page template structure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByGroupId_Last(
		long groupId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateStructure> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template structures before and after the current layout page template structure in the ordered set where groupId = &#63;.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the current layout page template structure
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure[] findByGroupId_PrevAndNext(
			long layoutPageTemplateStructureId, long groupId,
			OrderByComparator<LayoutPageTemplateStructure> orderByComparator)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			findByPrimaryKey(layoutPageTemplateStructureId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateStructure[] array =
				new LayoutPageTemplateStructureImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, layoutPageTemplateStructure, groupId,
				orderByComparator, true);

			array[1] = layoutPageTemplateStructure;

			array[2] = getByGroupId_PrevAndNext(
				session, layoutPageTemplateStructure, groupId,
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

	protected LayoutPageTemplateStructure getByGroupId_PrevAndNext(
		Session session,
		LayoutPageTemplateStructure layoutPageTemplateStructure, long groupId,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
			sb.append(LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
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
						layoutPageTemplateStructure)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutPageTemplateStructure> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template structures where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutPageTemplateStructure);
		}
	}

	/**
	 * Returns the number of layout page template structures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout page template structures
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

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
		"layoutPageTemplateStructure.groupId = ?";

	private FinderPath _finderPathFetchByG_P;

	/**
	 * Returns the layout page template structure where groupId = &#63; and plid = &#63; or throws a <code>NoSuchPageTemplateStructureException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the matching layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByG_P(long groupId, long plid)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure = fetchByG_P(
			groupId, plid);

		if (layoutPageTemplateStructure == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append(", plid=");
			sb.append(plid);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPageTemplateStructureException(sb.toString());
		}

		return layoutPageTemplateStructure;
	}

	/**
	 * Returns the layout page template structure where groupId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByG_P(long groupId, long plid) {
		return fetchByG_P(groupId, plid, true);
	}

	/**
	 * Returns the layout page template structure where groupId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout page template structure, or <code>null</code> if a matching layout page template structure could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByG_P(
		long groupId, long plid, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, plid};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByG_P, finderArgs, this);
		}

		if (result instanceof LayoutPageTemplateStructure) {
			LayoutPageTemplateStructure layoutPageTemplateStructure =
				(LayoutPageTemplateStructure)result;

			if ((groupId != layoutPageTemplateStructure.getGroupId()) ||
				(plid != layoutPageTemplateStructure.getPlid())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE);

			sb.append(_FINDER_COLUMN_G_P_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_PLID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plid);

				List<LayoutPageTemplateStructure> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByG_P, finderArgs, list);
					}
				}
				else {
					LayoutPageTemplateStructure layoutPageTemplateStructure =
						list.get(0);

					result = layoutPageTemplateStructure;

					cacheResult(layoutPageTemplateStructure);
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
			return (LayoutPageTemplateStructure)result;
		}
	}

	/**
	 * Removes the layout page template structure where groupId = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the layout page template structure that was removed
	 */
	@Override
	public LayoutPageTemplateStructure removeByG_P(long groupId, long plid)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure = findByG_P(
			groupId, plid);

		return remove(layoutPageTemplateStructure);
	}

	/**
	 * Returns the number of layout page template structures where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the number of matching layout page template structures
	 */
	@Override
	public int countByG_P(long groupId, long plid) {
		LayoutPageTemplateStructure layoutPageTemplateStructure = fetchByG_P(
			groupId, plid);

		if (layoutPageTemplateStructure == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 =
		"layoutPageTemplateStructure.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_PLID_2 =
		"layoutPageTemplateStructure.plid = ?";

	public LayoutPageTemplateStructurePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LayoutPageTemplateStructure.class);

		setModelImplClass(LayoutPageTemplateStructureImpl.class);
		setModelPKClass(long.class);

		setTable(LayoutPageTemplateStructureTable.INSTANCE);
	}

	/**
	 * Caches the layout page template structure in the entity cache if it is enabled.
	 *
	 * @param layoutPageTemplateStructure the layout page template structure
	 */
	@Override
	public void cacheResult(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		entityCache.putResult(
			LayoutPageTemplateStructureImpl.class,
			layoutPageTemplateStructure.getPrimaryKey(),
			layoutPageTemplateStructure);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				layoutPageTemplateStructure.getUuid(),
				layoutPageTemplateStructure.getGroupId()
			},
			layoutPageTemplateStructure);

		finderCache.putResult(
			_finderPathFetchByG_P,
			new Object[] {
				layoutPageTemplateStructure.getGroupId(),
				layoutPageTemplateStructure.getPlid()
			},
			layoutPageTemplateStructure);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the layout page template structures in the entity cache if it is enabled.
	 *
	 * @param layoutPageTemplateStructures the layout page template structures
	 */
	@Override
	public void cacheResult(
		List<LayoutPageTemplateStructure> layoutPageTemplateStructures) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (layoutPageTemplateStructures.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				layoutPageTemplateStructures) {

			if (entityCache.getResult(
					LayoutPageTemplateStructureImpl.class,
					layoutPageTemplateStructure.getPrimaryKey()) == null) {

				cacheResult(layoutPageTemplateStructure);
			}
		}
	}

	/**
	 * Clears the cache for all layout page template structures.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LayoutPageTemplateStructureImpl.class);

		finderCache.clearCache(LayoutPageTemplateStructureImpl.class);
	}

	/**
	 * Clears the cache for the layout page template structure.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		entityCache.removeResult(
			LayoutPageTemplateStructureImpl.class, layoutPageTemplateStructure);
	}

	@Override
	public void clearCache(
		List<LayoutPageTemplateStructure> layoutPageTemplateStructures) {

		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				layoutPageTemplateStructures) {

			entityCache.removeResult(
				LayoutPageTemplateStructureImpl.class,
				layoutPageTemplateStructure);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(LayoutPageTemplateStructureImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				LayoutPageTemplateStructureImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutPageTemplateStructureModelImpl
			layoutPageTemplateStructureModelImpl) {

		Object[] args = new Object[] {
			layoutPageTemplateStructureModelImpl.getUuid(),
			layoutPageTemplateStructureModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args,
			layoutPageTemplateStructureModelImpl);

		args = new Object[] {
			layoutPageTemplateStructureModelImpl.getGroupId(),
			layoutPageTemplateStructureModelImpl.getPlid()
		};

		finderCache.putResult(
			_finderPathFetchByG_P, args, layoutPageTemplateStructureModelImpl);
	}

	/**
	 * Creates a new layout page template structure with the primary key. Does not add the layout page template structure to the database.
	 *
	 * @param layoutPageTemplateStructureId the primary key for the new layout page template structure
	 * @return the new layout page template structure
	 */
	@Override
	public LayoutPageTemplateStructure create(
		long layoutPageTemplateStructureId) {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			new LayoutPageTemplateStructureImpl();

		layoutPageTemplateStructure.setNew(true);
		layoutPageTemplateStructure.setPrimaryKey(
			layoutPageTemplateStructureId);

		String uuid = PortalUUIDUtil.generate();

		layoutPageTemplateStructure.setUuid(uuid);

		layoutPageTemplateStructure.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return layoutPageTemplateStructure;
	}

	/**
	 * Removes the layout page template structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the layout page template structure
	 * @return the layout page template structure that was removed
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure remove(
			long layoutPageTemplateStructureId)
		throws NoSuchPageTemplateStructureException {

		return remove((Serializable)layoutPageTemplateStructureId);
	}

	/**
	 * Removes the layout page template structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout page template structure
	 * @return the layout page template structure that was removed
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure remove(Serializable primaryKey)
		throws NoSuchPageTemplateStructureException {

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateStructure layoutPageTemplateStructure =
				(LayoutPageTemplateStructure)session.get(
					LayoutPageTemplateStructureImpl.class, primaryKey);

			if (layoutPageTemplateStructure == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageTemplateStructureException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(layoutPageTemplateStructure);
		}
		catch (NoSuchPageTemplateStructureException noSuchEntityException) {
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
	protected LayoutPageTemplateStructure removeImpl(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutPageTemplateStructure)) {
				layoutPageTemplateStructure =
					(LayoutPageTemplateStructure)session.get(
						LayoutPageTemplateStructureImpl.class,
						layoutPageTemplateStructure.getPrimaryKeyObj());
			}

			if (layoutPageTemplateStructure != null) {
				session.delete(layoutPageTemplateStructure);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (layoutPageTemplateStructure != null) {
			clearCache(layoutPageTemplateStructure);
		}

		return layoutPageTemplateStructure;
	}

	@Override
	public LayoutPageTemplateStructure updateImpl(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		boolean isNew = layoutPageTemplateStructure.isNew();

		if (!(layoutPageTemplateStructure instanceof
				LayoutPageTemplateStructureModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					layoutPageTemplateStructure.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					layoutPageTemplateStructure);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutPageTemplateStructure proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutPageTemplateStructure implementation " +
					layoutPageTemplateStructure.getClass());
		}

		LayoutPageTemplateStructureModelImpl
			layoutPageTemplateStructureModelImpl =
				(LayoutPageTemplateStructureModelImpl)
					layoutPageTemplateStructure;

		if (Validator.isNull(layoutPageTemplateStructure.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			layoutPageTemplateStructure.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (layoutPageTemplateStructure.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutPageTemplateStructure.setCreateDate(date);
			}
			else {
				layoutPageTemplateStructure.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!layoutPageTemplateStructureModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutPageTemplateStructure.setModifiedDate(date);
			}
			else {
				layoutPageTemplateStructure.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(layoutPageTemplateStructure);
			}
			else {
				layoutPageTemplateStructure =
					(LayoutPageTemplateStructure)session.merge(
						layoutPageTemplateStructure);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			LayoutPageTemplateStructureImpl.class,
			layoutPageTemplateStructureModelImpl, false, true);

		cacheUniqueFindersCache(layoutPageTemplateStructureModelImpl);

		if (isNew) {
			layoutPageTemplateStructure.setNew(false);
		}

		layoutPageTemplateStructure.resetOriginalValues();

		return layoutPageTemplateStructure;
	}

	/**
	 * Returns the layout page template structure with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout page template structure
	 * @return the layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageTemplateStructureException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchByPrimaryKey(primaryKey);

		if (layoutPageTemplateStructure == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageTemplateStructureException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return layoutPageTemplateStructure;
	}

	/**
	 * Returns the layout page template structure with the primary key or throws a <code>NoSuchPageTemplateStructureException</code> if it could not be found.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the layout page template structure
	 * @return the layout page template structure
	 * @throws NoSuchPageTemplateStructureException if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure findByPrimaryKey(
			long layoutPageTemplateStructureId)
		throws NoSuchPageTemplateStructureException {

		return findByPrimaryKey((Serializable)layoutPageTemplateStructureId);
	}

	/**
	 * Returns the layout page template structure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutPageTemplateStructureId the primary key of the layout page template structure
	 * @return the layout page template structure, or <code>null</code> if a layout page template structure with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateStructure fetchByPrimaryKey(
		long layoutPageTemplateStructureId) {

		return fetchByPrimaryKey((Serializable)layoutPageTemplateStructureId);
	}

	/**
	 * Returns all the layout page template structures.
	 *
	 * @return the layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @return the range of layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findAll(
		int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPageTemplateStructureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template structures
	 * @param end the upper bound of the range of layout page template structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of layout page template structures
	 */
	@Override
	public List<LayoutPageTemplateStructure> findAll(
		int start, int end,
		OrderByComparator<LayoutPageTemplateStructure> orderByComparator,
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

		List<LayoutPageTemplateStructure> list = null;

		if (useFinderCache) {
			list = (List<LayoutPageTemplateStructure>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE;

				sql = sql.concat(
					LayoutPageTemplateStructureModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LayoutPageTemplateStructure>)QueryUtil.list(
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
	 * Removes all the layout page template structures from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutPageTemplateStructure layoutPageTemplateStructure :
				findAll()) {

			remove(layoutPageTemplateStructure);
		}
	}

	/**
	 * Returns the number of layout page template structures.
	 *
	 * @return the number of layout page template structures
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
					_SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE);

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
		return "layoutPageTemplateStructureId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutPageTemplateStructureModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout page template structure persistence.
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

		_finderPathFetchByG_P = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "plid"}, true);

		LayoutPageTemplateStructureUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		LayoutPageTemplateStructureUtil.setPersistence(null);

		entityCache.removeCache(
			LayoutPageTemplateStructureImpl.class.getName());
	}

	@Override
	@Reference(
		target = LayoutPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = LayoutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LayoutPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE =
		"SELECT layoutPageTemplateStructure FROM LayoutPageTemplateStructure layoutPageTemplateStructure";

	private static final String _SQL_SELECT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE =
		"SELECT layoutPageTemplateStructure FROM LayoutPageTemplateStructure layoutPageTemplateStructure WHERE ";

	private static final String _SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE =
		"SELECT COUNT(layoutPageTemplateStructure) FROM LayoutPageTemplateStructure layoutPageTemplateStructure";

	private static final String _SQL_COUNT_LAYOUTPAGETEMPLATESTRUCTURE_WHERE =
		"SELECT COUNT(layoutPageTemplateStructure) FROM LayoutPageTemplateStructure layoutPageTemplateStructure WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"layoutPageTemplateStructure.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LayoutPageTemplateStructure exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LayoutPageTemplateStructure exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutPageTemplateStructurePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}