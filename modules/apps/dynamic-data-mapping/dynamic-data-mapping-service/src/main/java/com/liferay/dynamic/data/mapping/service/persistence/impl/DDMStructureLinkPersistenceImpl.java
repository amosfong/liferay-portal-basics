/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchStructureLinkException;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.model.DDMStructureLinkTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkUtil;
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
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the ddm structure link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMStructureLinkPersistence.class)
public class DDMStructureLinkPersistenceImpl
	extends BasePersistenceImpl<DDMStructureLink>
	implements DDMStructureLinkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMStructureLinkUtil</code> to access the ddm structure link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMStructureLinkImpl.class.getName();

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
	 * Returns all the ddm structure links where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByStructureId(long structureId) {
		return findByStructureId(
			structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @return the range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByStructureId(
		long structureId, int start, int end) {

		return findByStructureId(structureId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		return findByStructureId(
			structureId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure links where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByStructureId(
		long structureId, int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator,
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

		List<DDMStructureLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureLink ddmStructureLink : list) {
					if (structureId != ddmStructureLink.getStructureId()) {
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

			sb.append(_SQL_SELECT_DDMSTRUCTURELINK_WHERE);

			sb.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStructureLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(structureId);

				list = (List<DDMStructureLink>)QueryUtil.list(
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
	 * Returns the first ddm structure link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure link
	 * @throws NoSuchStructureLinkException if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink findByStructureId_First(
			long structureId,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByStructureId_First(
			structureId, orderByComparator);

		if (ddmStructureLink != null) {
			return ddmStructureLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStructureLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm structure link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByStructureId_First(
		long structureId,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		List<DDMStructureLink> list = findByStructureId(
			structureId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm structure link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure link
	 * @throws NoSuchStructureLinkException if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink findByStructureId_Last(
			long structureId,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByStructureId_Last(
			structureId, orderByComparator);

		if (ddmStructureLink != null) {
			return ddmStructureLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("structureId=");
		sb.append(structureId);

		sb.append("}");

		throw new NoSuchStructureLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm structure link in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByStructureId_Last(
		long structureId,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		int count = countByStructureId(structureId);

		if (count == 0) {
			return null;
		}

		List<DDMStructureLink> list = findByStructureId(
			structureId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm structure links before and after the current ddm structure link in the ordered set where structureId = &#63;.
	 *
	 * @param structureLinkId the primary key of the current ddm structure link
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm structure link
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink[] findByStructureId_PrevAndNext(
			long structureLinkId, long structureId,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = findByPrimaryKey(structureLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureLink[] array = new DDMStructureLinkImpl[3];

			array[0] = getByStructureId_PrevAndNext(
				session, ddmStructureLink, structureId, orderByComparator,
				true);

			array[1] = ddmStructureLink;

			array[2] = getByStructureId_PrevAndNext(
				session, ddmStructureLink, structureId, orderByComparator,
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

	protected DDMStructureLink getByStructureId_PrevAndNext(
		Session session, DDMStructureLink ddmStructureLink, long structureId,
		OrderByComparator<DDMStructureLink> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMSTRUCTURELINK_WHERE);

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
			sb.append(DDMStructureLinkModelImpl.ORDER_BY_JPQL);
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
						ddmStructureLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStructureLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm structure links where structureId = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 */
	@Override
	public void removeByStructureId(long structureId) {
		for (DDMStructureLink ddmStructureLink :
				findByStructureId(
					structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmStructureLink);
		}
	}

	/**
	 * Returns the number of ddm structure links where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the number of matching ddm structure links
	 */
	@Override
	public int countByStructureId(long structureId) {
		FinderPath finderPath = _finderPathCountByStructureId;

		Object[] finderArgs = new Object[] {structureId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMSTRUCTURELINK_WHERE);

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
		"ddmStructureLink.structureId = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the ddm structure links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @return the range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure links where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_C;
				finderArgs = new Object[] {classNameId, classPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_C;
			finderArgs = new Object[] {
				classNameId, classPK, start, end, orderByComparator
			};
		}

		List<DDMStructureLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureLink ddmStructureLink : list) {
					if ((classNameId != ddmStructureLink.getClassNameId()) ||
						(classPK != ddmStructureLink.getClassPK())) {

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

			sb.append(_SQL_SELECT_DDMSTRUCTURELINK_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMStructureLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<DDMStructureLink>)QueryUtil.list(
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
	 * Returns the first ddm structure link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure link
	 * @throws NoSuchStructureLinkException if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (ddmStructureLink != null) {
			return ddmStructureLink;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchStructureLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm structure link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		List<DDMStructureLink> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm structure link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure link
	 * @throws NoSuchStructureLinkException if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (ddmStructureLink != null) {
			return ddmStructureLink;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchStructureLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm structure link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<DDMStructureLink> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm structure links before and after the current ddm structure link in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param structureLinkId the primary key of the current ddm structure link
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm structure link
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink[] findByC_C_PrevAndNext(
			long structureLinkId, long classNameId, long classPK,
			OrderByComparator<DDMStructureLink> orderByComparator)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = findByPrimaryKey(structureLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureLink[] array = new DDMStructureLinkImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, ddmStructureLink, classNameId, classPK,
				orderByComparator, true);

			array[1] = ddmStructureLink;

			array[2] = getByC_C_PrevAndNext(
				session, ddmStructureLink, classNameId, classPK,
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

	protected DDMStructureLink getByC_C_PrevAndNext(
		Session session, DDMStructureLink ddmStructureLink, long classNameId,
		long classPK, OrderByComparator<DDMStructureLink> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMSTRUCTURELINK_WHERE);

		sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			sb.append(DDMStructureLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmStructureLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMStructureLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm structure links where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (DDMStructureLink ddmStructureLink :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(ddmStructureLink);
		}
	}

	/**
	 * Returns the number of ddm structure links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm structure links
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DDMSTRUCTURELINK_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"ddmStructureLink.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"ddmStructureLink.classPK = ?";

	private FinderPath _finderPathFetchByC_C_S;

	/**
	 * Returns the ddm structure link where classNameId = &#63; and classPK = &#63; and structureId = &#63; or throws a <code>NoSuchStructureLinkException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param structureId the structure ID
	 * @return the matching ddm structure link
	 * @throws NoSuchStructureLinkException if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink findByC_C_S(
			long classNameId, long classPK, long structureId)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByC_C_S(
			classNameId, classPK, structureId);

		if (ddmStructureLink == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("classNameId=");
			sb.append(classNameId);

			sb.append(", classPK=");
			sb.append(classPK);

			sb.append(", structureId=");
			sb.append(structureId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchStructureLinkException(sb.toString());
		}

		return ddmStructureLink;
	}

	/**
	 * Returns the ddm structure link where classNameId = &#63; and classPK = &#63; and structureId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param structureId the structure ID
	 * @return the matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByC_C_S(
		long classNameId, long classPK, long structureId) {

		return fetchByC_C_S(classNameId, classPK, structureId, true);
	}

	/**
	 * Returns the ddm structure link where classNameId = &#63; and classPK = &#63; and structureId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param structureId the structure ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm structure link, or <code>null</code> if a matching ddm structure link could not be found
	 */
	@Override
	public DDMStructureLink fetchByC_C_S(
		long classNameId, long classPK, long structureId,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classNameId, classPK, structureId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C_S, finderArgs, this);
		}

		if (result instanceof DDMStructureLink) {
			DDMStructureLink ddmStructureLink = (DDMStructureLink)result;

			if ((classNameId != ddmStructureLink.getClassNameId()) ||
				(classPK != ddmStructureLink.getClassPK()) ||
				(structureId != ddmStructureLink.getStructureId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_DDMSTRUCTURELINK_WHERE);

			sb.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			sb.append(_FINDER_COLUMN_C_C_S_STRUCTUREID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				queryPos.add(structureId);

				List<DDMStructureLink> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C_S, finderArgs, list);
					}
				}
				else {
					DDMStructureLink ddmStructureLink = list.get(0);

					result = ddmStructureLink;

					cacheResult(ddmStructureLink);
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
			return (DDMStructureLink)result;
		}
	}

	/**
	 * Removes the ddm structure link where classNameId = &#63; and classPK = &#63; and structureId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param structureId the structure ID
	 * @return the ddm structure link that was removed
	 */
	@Override
	public DDMStructureLink removeByC_C_S(
			long classNameId, long classPK, long structureId)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = findByC_C_S(
			classNameId, classPK, structureId);

		return remove(ddmStructureLink);
	}

	/**
	 * Returns the number of ddm structure links where classNameId = &#63; and classPK = &#63; and structureId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param structureId the structure ID
	 * @return the number of matching ddm structure links
	 */
	@Override
	public int countByC_C_S(long classNameId, long classPK, long structureId) {
		DDMStructureLink ddmStructureLink = fetchByC_C_S(
			classNameId, classPK, structureId);

		if (ddmStructureLink == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 =
		"ddmStructureLink.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 =
		"ddmStructureLink.classPK = ? AND ";

	private static final String _FINDER_COLUMN_C_C_S_STRUCTUREID_2 =
		"ddmStructureLink.structureId = ?";

	public DDMStructureLinkPersistenceImpl() {
		setModelClass(DDMStructureLink.class);

		setModelImplClass(DDMStructureLinkImpl.class);
		setModelPKClass(long.class);

		setTable(DDMStructureLinkTable.INSTANCE);
	}

	/**
	 * Caches the ddm structure link in the entity cache if it is enabled.
	 *
	 * @param ddmStructureLink the ddm structure link
	 */
	@Override
	public void cacheResult(DDMStructureLink ddmStructureLink) {
		entityCache.putResult(
			DDMStructureLinkImpl.class, ddmStructureLink.getPrimaryKey(),
			ddmStructureLink);

		finderCache.putResult(
			_finderPathFetchByC_C_S,
			new Object[] {
				ddmStructureLink.getClassNameId(),
				ddmStructureLink.getClassPK(), ddmStructureLink.getStructureId()
			},
			ddmStructureLink);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm structure links in the entity cache if it is enabled.
	 *
	 * @param ddmStructureLinks the ddm structure links
	 */
	@Override
	public void cacheResult(List<DDMStructureLink> ddmStructureLinks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmStructureLinks.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMStructureLink ddmStructureLink : ddmStructureLinks) {
			if (entityCache.getResult(
					DDMStructureLinkImpl.class,
					ddmStructureLink.getPrimaryKey()) == null) {

				cacheResult(ddmStructureLink);
			}
		}
	}

	/**
	 * Clears the cache for all ddm structure links.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMStructureLinkImpl.class);

		finderCache.clearCache(DDMStructureLinkImpl.class);
	}

	/**
	 * Clears the cache for the ddm structure link.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMStructureLink ddmStructureLink) {
		entityCache.removeResult(DDMStructureLinkImpl.class, ddmStructureLink);
	}

	@Override
	public void clearCache(List<DDMStructureLink> ddmStructureLinks) {
		for (DDMStructureLink ddmStructureLink : ddmStructureLinks) {
			entityCache.removeResult(
				DDMStructureLinkImpl.class, ddmStructureLink);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMStructureLinkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDMStructureLinkImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMStructureLinkModelImpl ddmStructureLinkModelImpl) {

		Object[] args = new Object[] {
			ddmStructureLinkModelImpl.getClassNameId(),
			ddmStructureLinkModelImpl.getClassPK(),
			ddmStructureLinkModelImpl.getStructureId()
		};

		finderCache.putResult(
			_finderPathFetchByC_C_S, args, ddmStructureLinkModelImpl);
	}

	/**
	 * Creates a new ddm structure link with the primary key. Does not add the ddm structure link to the database.
	 *
	 * @param structureLinkId the primary key for the new ddm structure link
	 * @return the new ddm structure link
	 */
	@Override
	public DDMStructureLink create(long structureLinkId) {
		DDMStructureLink ddmStructureLink = new DDMStructureLinkImpl();

		ddmStructureLink.setNew(true);
		ddmStructureLink.setPrimaryKey(structureLinkId);

		ddmStructureLink.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmStructureLink;
	}

	/**
	 * Removes the ddm structure link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param structureLinkId the primary key of the ddm structure link
	 * @return the ddm structure link that was removed
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink remove(long structureLinkId)
		throws NoSuchStructureLinkException {

		return remove((Serializable)structureLinkId);
	}

	/**
	 * Removes the ddm structure link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm structure link
	 * @return the ddm structure link that was removed
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink remove(Serializable primaryKey)
		throws NoSuchStructureLinkException {

		Session session = null;

		try {
			session = openSession();

			DDMStructureLink ddmStructureLink = (DDMStructureLink)session.get(
				DDMStructureLinkImpl.class, primaryKey);

			if (ddmStructureLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStructureLinkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmStructureLink);
		}
		catch (NoSuchStructureLinkException noSuchEntityException) {
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
	protected DDMStructureLink removeImpl(DDMStructureLink ddmStructureLink) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmStructureLink)) {
				ddmStructureLink = (DDMStructureLink)session.get(
					DDMStructureLinkImpl.class,
					ddmStructureLink.getPrimaryKeyObj());
			}

			if (ddmStructureLink != null) {
				session.delete(ddmStructureLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmStructureLink != null) {
			clearCache(ddmStructureLink);
		}

		return ddmStructureLink;
	}

	@Override
	public DDMStructureLink updateImpl(DDMStructureLink ddmStructureLink) {
		boolean isNew = ddmStructureLink.isNew();

		if (!(ddmStructureLink instanceof DDMStructureLinkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmStructureLink.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmStructureLink);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmStructureLink proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMStructureLink implementation " +
					ddmStructureLink.getClass());
		}

		DDMStructureLinkModelImpl ddmStructureLinkModelImpl =
			(DDMStructureLinkModelImpl)ddmStructureLink;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmStructureLink);
			}
			else {
				ddmStructureLink = (DDMStructureLink)session.merge(
					ddmStructureLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMStructureLinkImpl.class, ddmStructureLinkModelImpl, false, true);

		cacheUniqueFindersCache(ddmStructureLinkModelImpl);

		if (isNew) {
			ddmStructureLink.setNew(false);
		}

		ddmStructureLink.resetOriginalValues();

		return ddmStructureLink;
	}

	/**
	 * Returns the ddm structure link with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm structure link
	 * @return the ddm structure link
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStructureLinkException {

		DDMStructureLink ddmStructureLink = fetchByPrimaryKey(primaryKey);

		if (ddmStructureLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStructureLinkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmStructureLink;
	}

	/**
	 * Returns the ddm structure link with the primary key or throws a <code>NoSuchStructureLinkException</code> if it could not be found.
	 *
	 * @param structureLinkId the primary key of the ddm structure link
	 * @return the ddm structure link
	 * @throws NoSuchStructureLinkException if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink findByPrimaryKey(long structureLinkId)
		throws NoSuchStructureLinkException {

		return findByPrimaryKey((Serializable)structureLinkId);
	}

	/**
	 * Returns the ddm structure link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param structureLinkId the primary key of the ddm structure link
	 * @return the ddm structure link, or <code>null</code> if a ddm structure link with the primary key could not be found
	 */
	@Override
	public DDMStructureLink fetchByPrimaryKey(long structureLinkId) {
		return fetchByPrimaryKey((Serializable)structureLinkId);
	}

	/**
	 * Returns all the ddm structure links.
	 *
	 * @return the ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm structure links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @return the range of ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm structure links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findAll(
		int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm structure links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMStructureLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structure links
	 * @param end the upper bound of the range of ddm structure links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm structure links
	 */
	@Override
	public List<DDMStructureLink> findAll(
		int start, int end,
		OrderByComparator<DDMStructureLink> orderByComparator,
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

		List<DDMStructureLink> list = null;

		if (useFinderCache) {
			list = (List<DDMStructureLink>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMSTRUCTURELINK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMSTRUCTURELINK;

				sql = sql.concat(DDMStructureLinkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMStructureLink>)QueryUtil.list(
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
	 * Removes all the ddm structure links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMStructureLink ddmStructureLink : findAll()) {
			remove(ddmStructureLink);
		}
	}

	/**
	 * Returns the number of ddm structure links.
	 *
	 * @return the number of ddm structure links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DDMSTRUCTURELINK);

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
		return "structureLinkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMSTRUCTURELINK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMStructureLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm structure link persistence.
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

		_finderPathWithPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, true);

		_finderPathCountByC_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, false);

		_finderPathFetchByC_C_S = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"classNameId", "classPK", "structureId"}, true);

		DDMStructureLinkUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMStructureLinkUtil.setPersistence(null);

		entityCache.removeCache(DDMStructureLinkImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMSTRUCTURELINK =
		"SELECT ddmStructureLink FROM DDMStructureLink ddmStructureLink";

	private static final String _SQL_SELECT_DDMSTRUCTURELINK_WHERE =
		"SELECT ddmStructureLink FROM DDMStructureLink ddmStructureLink WHERE ";

	private static final String _SQL_COUNT_DDMSTRUCTURELINK =
		"SELECT COUNT(ddmStructureLink) FROM DDMStructureLink ddmStructureLink";

	private static final String _SQL_COUNT_DDMSTRUCTURELINK_WHERE =
		"SELECT COUNT(ddmStructureLink) FROM DDMStructureLink ddmStructureLink WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmStructureLink.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMStructureLink exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMStructureLink exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStructureLinkPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}