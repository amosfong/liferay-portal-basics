/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.exception.NoSuchLayoutPrototypeException;
import com.liferay.portal.kernel.model.LayoutPrototype;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the layout prototype service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPrototypeUtil
 * @generated
 */
@ProviderType
public interface LayoutPrototypePersistence
	extends BasePersistence<LayoutPrototype> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutPrototypeUtil} to access the layout prototype persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the layout prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid(String uuid);

	/**
	 * Returns a range of all the layout prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns an ordered range of all the layout prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first layout prototype in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the first layout prototype in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the last layout prototype in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the last layout prototype in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set where uuid = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] findByUuid_PrevAndNext(
			long layoutPrototypeId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns all the layout prototypes that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid(String uuid);

	/**
	 * Returns a range of all the layout prototypes that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set of layout prototypes that the user has permission to view where uuid = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] filterFindByUuid_PrevAndNext(
			long layoutPrototypeId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Removes all the layout prototypes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of layout prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout prototypes
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of layout prototypes that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout prototypes that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the layout prototypes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns an ordered range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the first layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the last layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the last layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] findByUuid_C_PrevAndNext(
			long layoutPrototypeId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns all the layout prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the layout prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set of layout prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] filterFindByUuid_C_PrevAndNext(
			long layoutPrototypeId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Removes all the layout prototypes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of layout prototypes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout prototypes
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of layout prototypes that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout prototypes that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the layout prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the layout prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns an ordered range of all the layout prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first layout prototype in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the first layout prototype in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the last layout prototype in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the last layout prototype in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set where companyId = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] findByCompanyId_PrevAndNext(
			long layoutPrototypeId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns all the layout prototypes that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByCompanyId(
		long companyId);

	/**
	 * Returns a range of all the layout prototypes that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set of layout prototypes that the user has permission to view where companyId = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] filterFindByCompanyId_PrevAndNext(
			long layoutPrototypeId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Removes all the layout prototypes where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of layout prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching layout prototypes
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns the number of layout prototypes that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching layout prototypes that the user has permission to view
	 */
	public int filterCountByCompanyId(long companyId);

	/**
	 * Returns all the layout prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByC_A(
		long companyId, boolean active);

	/**
	 * Returns a range of all the layout prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByC_A(
		long companyId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns an ordered range of all the layout prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout prototypes
	 */
	public java.util.List<LayoutPrototype> findByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByC_A_First(
			long companyId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the first layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the last layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype
	 * @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	 */
	public LayoutPrototype findByC_A_Last(
			long companyId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the last layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public LayoutPrototype fetchByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] findByC_A_PrevAndNext(
			long layoutPrototypeId, long companyId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns all the layout prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByC_A(
		long companyId, boolean active);

	/**
	 * Returns a range of all the layout prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByC_A(
		long companyId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes that the user has permissions to view where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout prototypes that the user has permission to view
	 */
	public java.util.List<LayoutPrototype> filterFindByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns the layout prototypes before and after the current layout prototype in the ordered set of layout prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	 *
	 * @param layoutPrototypeId the primary key of the current layout prototype
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype[] filterFindByC_A_PrevAndNext(
			long layoutPrototypeId, long companyId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
				orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Removes all the layout prototypes where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 */
	public void removeByC_A(long companyId, boolean active);

	/**
	 * Returns the number of layout prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the number of matching layout prototypes
	 */
	public int countByC_A(long companyId, boolean active);

	/**
	 * Returns the number of layout prototypes that the user has permission to view where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the number of matching layout prototypes that the user has permission to view
	 */
	public int filterCountByC_A(long companyId, boolean active);

	/**
	 * Caches the layout prototype in the entity cache if it is enabled.
	 *
	 * @param layoutPrototype the layout prototype
	 */
	public void cacheResult(LayoutPrototype layoutPrototype);

	/**
	 * Caches the layout prototypes in the entity cache if it is enabled.
	 *
	 * @param layoutPrototypes the layout prototypes
	 */
	public void cacheResult(java.util.List<LayoutPrototype> layoutPrototypes);

	/**
	 * Creates a new layout prototype with the primary key. Does not add the layout prototype to the database.
	 *
	 * @param layoutPrototypeId the primary key for the new layout prototype
	 * @return the new layout prototype
	 */
	public LayoutPrototype create(long layoutPrototypeId);

	/**
	 * Removes the layout prototype with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPrototypeId the primary key of the layout prototype
	 * @return the layout prototype that was removed
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype remove(long layoutPrototypeId)
		throws NoSuchLayoutPrototypeException;

	public LayoutPrototype updateImpl(LayoutPrototype layoutPrototype);

	/**
	 * Returns the layout prototype with the primary key or throws a <code>NoSuchLayoutPrototypeException</code> if it could not be found.
	 *
	 * @param layoutPrototypeId the primary key of the layout prototype
	 * @return the layout prototype
	 * @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype findByPrimaryKey(long layoutPrototypeId)
		throws NoSuchLayoutPrototypeException;

	/**
	 * Returns the layout prototype with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutPrototypeId the primary key of the layout prototype
	 * @return the layout prototype, or <code>null</code> if a layout prototype with the primary key could not be found
	 */
	public LayoutPrototype fetchByPrimaryKey(long layoutPrototypeId);

	/**
	 * Returns all the layout prototypes.
	 *
	 * @return the layout prototypes
	 */
	public java.util.List<LayoutPrototype> findAll();

	/**
	 * Returns a range of all the layout prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of layout prototypes
	 */
	public java.util.List<LayoutPrototype> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the layout prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout prototypes
	 */
	public java.util.List<LayoutPrototype> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator);

	/**
	 * Returns an ordered range of all the layout prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of layout prototypes
	 */
	public java.util.List<LayoutPrototype> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the layout prototypes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of layout prototypes.
	 *
	 * @return the number of layout prototypes
	 */
	public int countAll();

}