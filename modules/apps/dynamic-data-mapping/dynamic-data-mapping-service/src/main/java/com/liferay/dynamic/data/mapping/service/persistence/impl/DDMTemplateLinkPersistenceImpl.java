/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchTemplateLinkException;
import com.liferay.dynamic.data.mapping.model.DDMTemplateLink;
import com.liferay.dynamic.data.mapping.model.DDMTemplateLinkTable;
import com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkUtil;
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
 * The persistence implementation for the ddm template link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMTemplateLinkPersistence.class)
public class DDMTemplateLinkPersistenceImpl
	extends BasePersistenceImpl<DDMTemplateLink>
	implements DDMTemplateLinkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMTemplateLinkUtil</code> to access the ddm template link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMTemplateLinkImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByTemplateId;
	private FinderPath _finderPathWithoutPaginationFindByTemplateId;
	private FinderPath _finderPathCountByTemplateId;

	/**
	 * Returns all the ddm template links where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @return the matching ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findByTemplateId(long templateId) {
		return findByTemplateId(
			templateId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm template links where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @return the range of matching ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findByTemplateId(
		long templateId, int start, int end) {

		return findByTemplateId(templateId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm template links where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findByTemplateId(
		long templateId, int start, int end,
		OrderByComparator<DDMTemplateLink> orderByComparator) {

		return findByTemplateId(
			templateId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm template links where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findByTemplateId(
		long templateId, int start, int end,
		OrderByComparator<DDMTemplateLink> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTemplateId;
				finderArgs = new Object[] {templateId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTemplateId;
			finderArgs = new Object[] {
				templateId, start, end, orderByComparator
			};
		}

		List<DDMTemplateLink> list = null;

		if (useFinderCache) {
			list = (List<DDMTemplateLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMTemplateLink ddmTemplateLink : list) {
					if (templateId != ddmTemplateLink.getTemplateId()) {
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

			sb.append(_SQL_SELECT_DDMTEMPLATELINK_WHERE);

			sb.append(_FINDER_COLUMN_TEMPLATEID_TEMPLATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DDMTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(templateId);

				list = (List<DDMTemplateLink>)QueryUtil.list(
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
	 * Returns the first ddm template link in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template link
	 * @throws NoSuchTemplateLinkException if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink findByTemplateId_First(
			long templateId,
			OrderByComparator<DDMTemplateLink> orderByComparator)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = fetchByTemplateId_First(
			templateId, orderByComparator);

		if (ddmTemplateLink != null) {
			return ddmTemplateLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("templateId=");
		sb.append(templateId);

		sb.append("}");

		throw new NoSuchTemplateLinkException(sb.toString());
	}

	/**
	 * Returns the first ddm template link in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template link, or <code>null</code> if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink fetchByTemplateId_First(
		long templateId, OrderByComparator<DDMTemplateLink> orderByComparator) {

		List<DDMTemplateLink> list = findByTemplateId(
			templateId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ddm template link in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template link
	 * @throws NoSuchTemplateLinkException if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink findByTemplateId_Last(
			long templateId,
			OrderByComparator<DDMTemplateLink> orderByComparator)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = fetchByTemplateId_Last(
			templateId, orderByComparator);

		if (ddmTemplateLink != null) {
			return ddmTemplateLink;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("templateId=");
		sb.append(templateId);

		sb.append("}");

		throw new NoSuchTemplateLinkException(sb.toString());
	}

	/**
	 * Returns the last ddm template link in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template link, or <code>null</code> if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink fetchByTemplateId_Last(
		long templateId, OrderByComparator<DDMTemplateLink> orderByComparator) {

		int count = countByTemplateId(templateId);

		if (count == 0) {
			return null;
		}

		List<DDMTemplateLink> list = findByTemplateId(
			templateId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ddm template links before and after the current ddm template link in the ordered set where templateId = &#63;.
	 *
	 * @param templateLinkId the primary key of the current ddm template link
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template link
	 * @throws NoSuchTemplateLinkException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink[] findByTemplateId_PrevAndNext(
			long templateLinkId, long templateId,
			OrderByComparator<DDMTemplateLink> orderByComparator)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = findByPrimaryKey(templateLinkId);

		Session session = null;

		try {
			session = openSession();

			DDMTemplateLink[] array = new DDMTemplateLinkImpl[3];

			array[0] = getByTemplateId_PrevAndNext(
				session, ddmTemplateLink, templateId, orderByComparator, true);

			array[1] = ddmTemplateLink;

			array[2] = getByTemplateId_PrevAndNext(
				session, ddmTemplateLink, templateId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMTemplateLink getByTemplateId_PrevAndNext(
		Session session, DDMTemplateLink ddmTemplateLink, long templateId,
		OrderByComparator<DDMTemplateLink> orderByComparator,
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

		sb.append(_SQL_SELECT_DDMTEMPLATELINK_WHERE);

		sb.append(_FINDER_COLUMN_TEMPLATEID_TEMPLATEID_2);

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
			sb.append(DDMTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(templateId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ddmTemplateLink)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DDMTemplateLink> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ddm template links where templateId = &#63; from the database.
	 *
	 * @param templateId the template ID
	 */
	@Override
	public void removeByTemplateId(long templateId) {
		for (DDMTemplateLink ddmTemplateLink :
				findByTemplateId(
					templateId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ddmTemplateLink);
		}
	}

	/**
	 * Returns the number of ddm template links where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @return the number of matching ddm template links
	 */
	@Override
	public int countByTemplateId(long templateId) {
		FinderPath finderPath = _finderPathCountByTemplateId;

		Object[] finderArgs = new Object[] {templateId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMTEMPLATELINK_WHERE);

			sb.append(_FINDER_COLUMN_TEMPLATEID_TEMPLATEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(templateId);

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

	private static final String _FINDER_COLUMN_TEMPLATEID_TEMPLATEID_2 =
		"ddmTemplateLink.templateId = ?";

	private FinderPath _finderPathFetchByC_C;

	/**
	 * Returns the ddm template link where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchTemplateLinkException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm template link
	 * @throws NoSuchTemplateLinkException if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink findByC_C(long classNameId, long classPK)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = fetchByC_C(classNameId, classPK);

		if (ddmTemplateLink == null) {
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

			throw new NoSuchTemplateLinkException(sb.toString());
		}

		return ddmTemplateLink;
	}

	/**
	 * Returns the ddm template link where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm template link, or <code>null</code> if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink fetchByC_C(long classNameId, long classPK) {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the ddm template link where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template link, or <code>null</code> if a matching ddm template link could not be found
	 */
	@Override
	public DDMTemplateLink fetchByC_C(
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

		if (result instanceof DDMTemplateLink) {
			DDMTemplateLink ddmTemplateLink = (DDMTemplateLink)result;

			if ((classNameId != ddmTemplateLink.getClassNameId()) ||
				(classPK != ddmTemplateLink.getClassPK())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DDMTEMPLATELINK_WHERE);

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

				List<DDMTemplateLink> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C, finderArgs, list);
					}
				}
				else {
					DDMTemplateLink ddmTemplateLink = list.get(0);

					result = ddmTemplateLink;

					cacheResult(ddmTemplateLink);
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
			return (DDMTemplateLink)result;
		}
	}

	/**
	 * Removes the ddm template link where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the ddm template link that was removed
	 */
	@Override
	public DDMTemplateLink removeByC_C(long classNameId, long classPK)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = findByC_C(classNameId, classPK);

		return remove(ddmTemplateLink);
	}

	/**
	 * Returns the number of ddm template links where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm template links
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		DDMTemplateLink ddmTemplateLink = fetchByC_C(classNameId, classPK);

		if (ddmTemplateLink == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"ddmTemplateLink.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"ddmTemplateLink.classPK = ?";

	public DDMTemplateLinkPersistenceImpl() {
		setModelClass(DDMTemplateLink.class);

		setModelImplClass(DDMTemplateLinkImpl.class);
		setModelPKClass(long.class);

		setTable(DDMTemplateLinkTable.INSTANCE);
	}

	/**
	 * Caches the ddm template link in the entity cache if it is enabled.
	 *
	 * @param ddmTemplateLink the ddm template link
	 */
	@Override
	public void cacheResult(DDMTemplateLink ddmTemplateLink) {
		entityCache.putResult(
			DDMTemplateLinkImpl.class, ddmTemplateLink.getPrimaryKey(),
			ddmTemplateLink);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				ddmTemplateLink.getClassNameId(), ddmTemplateLink.getClassPK()
			},
			ddmTemplateLink);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ddm template links in the entity cache if it is enabled.
	 *
	 * @param ddmTemplateLinks the ddm template links
	 */
	@Override
	public void cacheResult(List<DDMTemplateLink> ddmTemplateLinks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ddmTemplateLinks.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DDMTemplateLink ddmTemplateLink : ddmTemplateLinks) {
			if (entityCache.getResult(
					DDMTemplateLinkImpl.class,
					ddmTemplateLink.getPrimaryKey()) == null) {

				cacheResult(ddmTemplateLink);
			}
		}
	}

	/**
	 * Clears the cache for all ddm template links.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMTemplateLinkImpl.class);

		finderCache.clearCache(DDMTemplateLinkImpl.class);
	}

	/**
	 * Clears the cache for the ddm template link.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMTemplateLink ddmTemplateLink) {
		entityCache.removeResult(DDMTemplateLinkImpl.class, ddmTemplateLink);
	}

	@Override
	public void clearCache(List<DDMTemplateLink> ddmTemplateLinks) {
		for (DDMTemplateLink ddmTemplateLink : ddmTemplateLinks) {
			entityCache.removeResult(
				DDMTemplateLinkImpl.class, ddmTemplateLink);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DDMTemplateLinkImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDMTemplateLinkImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMTemplateLinkModelImpl ddmTemplateLinkModelImpl) {

		Object[] args = new Object[] {
			ddmTemplateLinkModelImpl.getClassNameId(),
			ddmTemplateLinkModelImpl.getClassPK()
		};

		finderCache.putResult(
			_finderPathFetchByC_C, args, ddmTemplateLinkModelImpl);
	}

	/**
	 * Creates a new ddm template link with the primary key. Does not add the ddm template link to the database.
	 *
	 * @param templateLinkId the primary key for the new ddm template link
	 * @return the new ddm template link
	 */
	@Override
	public DDMTemplateLink create(long templateLinkId) {
		DDMTemplateLink ddmTemplateLink = new DDMTemplateLinkImpl();

		ddmTemplateLink.setNew(true);
		ddmTemplateLink.setPrimaryKey(templateLinkId);

		ddmTemplateLink.setCompanyId(CompanyThreadLocal.getCompanyId());

		return ddmTemplateLink;
	}

	/**
	 * Removes the ddm template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateLinkId the primary key of the ddm template link
	 * @return the ddm template link that was removed
	 * @throws NoSuchTemplateLinkException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink remove(long templateLinkId)
		throws NoSuchTemplateLinkException {

		return remove((Serializable)templateLinkId);
	}

	/**
	 * Removes the ddm template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm template link
	 * @return the ddm template link that was removed
	 * @throws NoSuchTemplateLinkException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink remove(Serializable primaryKey)
		throws NoSuchTemplateLinkException {

		Session session = null;

		try {
			session = openSession();

			DDMTemplateLink ddmTemplateLink = (DDMTemplateLink)session.get(
				DDMTemplateLinkImpl.class, primaryKey);

			if (ddmTemplateLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTemplateLinkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmTemplateLink);
		}
		catch (NoSuchTemplateLinkException noSuchEntityException) {
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
	protected DDMTemplateLink removeImpl(DDMTemplateLink ddmTemplateLink) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmTemplateLink)) {
				ddmTemplateLink = (DDMTemplateLink)session.get(
					DDMTemplateLinkImpl.class,
					ddmTemplateLink.getPrimaryKeyObj());
			}

			if (ddmTemplateLink != null) {
				session.delete(ddmTemplateLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmTemplateLink != null) {
			clearCache(ddmTemplateLink);
		}

		return ddmTemplateLink;
	}

	@Override
	public DDMTemplateLink updateImpl(DDMTemplateLink ddmTemplateLink) {
		boolean isNew = ddmTemplateLink.isNew();

		if (!(ddmTemplateLink instanceof DDMTemplateLinkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmTemplateLink.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmTemplateLink);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmTemplateLink proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMTemplateLink implementation " +
					ddmTemplateLink.getClass());
		}

		DDMTemplateLinkModelImpl ddmTemplateLinkModelImpl =
			(DDMTemplateLinkModelImpl)ddmTemplateLink;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddmTemplateLink);
			}
			else {
				ddmTemplateLink = (DDMTemplateLink)session.merge(
					ddmTemplateLink);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDMTemplateLinkImpl.class, ddmTemplateLinkModelImpl, false, true);

		cacheUniqueFindersCache(ddmTemplateLinkModelImpl);

		if (isNew) {
			ddmTemplateLink.setNew(false);
		}

		ddmTemplateLink.resetOriginalValues();

		return ddmTemplateLink;
	}

	/**
	 * Returns the ddm template link with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm template link
	 * @return the ddm template link
	 * @throws NoSuchTemplateLinkException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTemplateLinkException {

		DDMTemplateLink ddmTemplateLink = fetchByPrimaryKey(primaryKey);

		if (ddmTemplateLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTemplateLinkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmTemplateLink;
	}

	/**
	 * Returns the ddm template link with the primary key or throws a <code>NoSuchTemplateLinkException</code> if it could not be found.
	 *
	 * @param templateLinkId the primary key of the ddm template link
	 * @return the ddm template link
	 * @throws NoSuchTemplateLinkException if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink findByPrimaryKey(long templateLinkId)
		throws NoSuchTemplateLinkException {

		return findByPrimaryKey((Serializable)templateLinkId);
	}

	/**
	 * Returns the ddm template link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param templateLinkId the primary key of the ddm template link
	 * @return the ddm template link, or <code>null</code> if a ddm template link with the primary key could not be found
	 */
	@Override
	public DDMTemplateLink fetchByPrimaryKey(long templateLinkId) {
		return fetchByPrimaryKey((Serializable)templateLinkId);
	}

	/**
	 * Returns all the ddm template links.
	 *
	 * @return the ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddm template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @return the range of ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddm template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findAll(
		int start, int end,
		OrderByComparator<DDMTemplateLink> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddm template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template links
	 * @param end the upper bound of the range of ddm template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm template links
	 */
	@Override
	public List<DDMTemplateLink> findAll(
		int start, int end,
		OrderByComparator<DDMTemplateLink> orderByComparator,
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

		List<DDMTemplateLink> list = null;

		if (useFinderCache) {
			list = (List<DDMTemplateLink>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMTEMPLATELINK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMTEMPLATELINK;

				sql = sql.concat(DDMTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMTemplateLink>)QueryUtil.list(
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
	 * Removes all the ddm template links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMTemplateLink ddmTemplateLink : findAll()) {
			remove(ddmTemplateLink);
		}
	}

	/**
	 * Returns the number of ddm template links.
	 *
	 * @return the number of ddm template links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DDMTEMPLATELINK);

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
		return "templateLinkId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMTEMPLATELINK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMTemplateLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm template link persistence.
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

		_finderPathWithPaginationFindByTemplateId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTemplateId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"templateId"}, true);

		_finderPathWithoutPaginationFindByTemplateId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTemplateId",
			new String[] {Long.class.getName()}, new String[] {"templateId"},
			true);

		_finderPathCountByTemplateId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTemplateId",
			new String[] {Long.class.getName()}, new String[] {"templateId"},
			false);

		_finderPathFetchByC_C = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"classNameId", "classPK"}, true);

		DDMTemplateLinkUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DDMTemplateLinkUtil.setPersistence(null);

		entityCache.removeCache(DDMTemplateLinkImpl.class.getName());
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

	private static final String _SQL_SELECT_DDMTEMPLATELINK =
		"SELECT ddmTemplateLink FROM DDMTemplateLink ddmTemplateLink";

	private static final String _SQL_SELECT_DDMTEMPLATELINK_WHERE =
		"SELECT ddmTemplateLink FROM DDMTemplateLink ddmTemplateLink WHERE ";

	private static final String _SQL_COUNT_DDMTEMPLATELINK =
		"SELECT COUNT(ddmTemplateLink) FROM DDMTemplateLink ddmTemplateLink";

	private static final String _SQL_COUNT_DDMTEMPLATELINK_WHERE =
		"SELECT COUNT(ddmTemplateLink) FROM DDMTemplateLink ddmTemplateLink WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmTemplateLink.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMTemplateLink exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMTemplateLink exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMTemplateLinkPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}