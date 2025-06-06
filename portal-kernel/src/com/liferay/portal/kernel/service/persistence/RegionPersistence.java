/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.model.Region;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionUtil
 * @generated
 */
@ProviderType
public interface RegionPersistence extends BasePersistence<Region> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegionUtil} to access the region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the regions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching regions
	 */
	public java.util.List<Region> findByUuid(String uuid);

	/**
	 * Returns a range of all the regions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of matching regions
	 */
	public java.util.List<Region> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the regions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first region in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the first region in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the last region in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the last region in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the regions before and after the current region in the ordered set where uuid = &#63;.
	 *
	 * @param regionId the primary key of the current region
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region[] findByUuid_PrevAndNext(
			long regionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Removes all the regions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of regions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching regions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the regions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching regions
	 */
	public java.util.List<Region> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the regions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of matching regions
	 */
	public java.util.List<Region> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the regions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first region in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the first region in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the last region in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the last region in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the regions before and after the current region in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param regionId the primary key of the current region
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region[] findByUuid_C_PrevAndNext(
			long regionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Removes all the regions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of regions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching regions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the regions where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @return the matching regions
	 */
	public java.util.List<Region> findByCountryId(long countryId);

	/**
	 * Returns a range of all the regions where countryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of matching regions
	 */
	public java.util.List<Region> findByCountryId(
		long countryId, int start, int end);

	/**
	 * Returns an ordered range of all the regions where countryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByCountryId(
		long countryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions where countryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByCountryId(
		long countryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first region in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByCountryId_First(
			long countryId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the first region in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByCountryId_First(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the last region in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByCountryId_Last(
			long countryId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the last region in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByCountryId_Last(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the regions before and after the current region in the ordered set where countryId = &#63;.
	 *
	 * @param regionId the primary key of the current region
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region[] findByCountryId_PrevAndNext(
			long regionId, long countryId,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Removes all the regions where countryId = &#63; from the database.
	 *
	 * @param countryId the country ID
	 */
	public void removeByCountryId(long countryId);

	/**
	 * Returns the number of regions where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @return the number of matching regions
	 */
	public int countByCountryId(long countryId);

	/**
	 * Returns all the regions where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching regions
	 */
	public java.util.List<Region> findByActive(boolean active);

	/**
	 * Returns a range of all the regions where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of matching regions
	 */
	public java.util.List<Region> findByActive(
		boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the regions where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first region in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByActive_First(
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the first region in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the last region in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByActive_Last(
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the last region in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the regions before and after the current region in the ordered set where active = &#63;.
	 *
	 * @param regionId the primary key of the current region
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region[] findByActive_PrevAndNext(
			long regionId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Removes all the regions where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	public void removeByActive(boolean active);

	/**
	 * Returns the number of regions where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching regions
	 */
	public int countByActive(boolean active);

	/**
	 * Returns all the regions where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @return the matching regions
	 */
	public java.util.List<Region> findByC_A(long countryId, boolean active);

	/**
	 * Returns a range of all the regions where countryId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of matching regions
	 */
	public java.util.List<Region> findByC_A(
		long countryId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the regions where countryId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByC_A(
		long countryId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions where countryId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching regions
	 */
	public java.util.List<Region> findByC_A(
		long countryId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByC_A_First(
			long countryId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByC_A_First(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByC_A_Last(
			long countryId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByC_A_Last(
		long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns the regions before and after the current region in the ordered set where countryId = &#63; and active = &#63;.
	 *
	 * @param regionId the primary key of the current region
	 * @param countryId the country ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region[] findByC_A_PrevAndNext(
			long regionId, long countryId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<Region>
				orderByComparator)
		throws NoSuchRegionException;

	/**
	 * Removes all the regions where countryId = &#63; and active = &#63; from the database.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 */
	public void removeByC_A(long countryId, boolean active);

	/**
	 * Returns the number of regions where countryId = &#63; and active = &#63;.
	 *
	 * @param countryId the country ID
	 * @param active the active
	 * @return the number of matching regions
	 */
	public int countByC_A(long countryId, boolean active);

	/**
	 * Returns the region where countryId = &#63; and regionCode = &#63; or throws a <code>NoSuchRegionException</code> if it could not be found.
	 *
	 * @param countryId the country ID
	 * @param regionCode the region code
	 * @return the matching region
	 * @throws NoSuchRegionException if a matching region could not be found
	 */
	public Region findByC_R(long countryId, String regionCode)
		throws NoSuchRegionException;

	/**
	 * Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param countryId the country ID
	 * @param regionCode the region code
	 * @return the matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByC_R(long countryId, String regionCode);

	/**
	 * Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param countryId the country ID
	 * @param regionCode the region code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching region, or <code>null</code> if a matching region could not be found
	 */
	public Region fetchByC_R(
		long countryId, String regionCode, boolean useFinderCache);

	/**
	 * Removes the region where countryId = &#63; and regionCode = &#63; from the database.
	 *
	 * @param countryId the country ID
	 * @param regionCode the region code
	 * @return the region that was removed
	 */
	public Region removeByC_R(long countryId, String regionCode)
		throws NoSuchRegionException;

	/**
	 * Returns the number of regions where countryId = &#63; and regionCode = &#63;.
	 *
	 * @param countryId the country ID
	 * @param regionCode the region code
	 * @return the number of matching regions
	 */
	public int countByC_R(long countryId, String regionCode);

	/**
	 * Caches the region in the entity cache if it is enabled.
	 *
	 * @param region the region
	 */
	public void cacheResult(Region region);

	/**
	 * Caches the regions in the entity cache if it is enabled.
	 *
	 * @param regions the regions
	 */
	public void cacheResult(java.util.List<Region> regions);

	/**
	 * Creates a new region with the primary key. Does not add the region to the database.
	 *
	 * @param regionId the primary key for the new region
	 * @return the new region
	 */
	public Region create(long regionId);

	/**
	 * Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param regionId the primary key of the region
	 * @return the region that was removed
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region remove(long regionId) throws NoSuchRegionException;

	public Region updateImpl(Region region);

	/**
	 * Returns the region with the primary key or throws a <code>NoSuchRegionException</code> if it could not be found.
	 *
	 * @param regionId the primary key of the region
	 * @return the region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	public Region findByPrimaryKey(long regionId) throws NoSuchRegionException;

	/**
	 * Returns the region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param regionId the primary key of the region
	 * @return the region, or <code>null</code> if a region with the primary key could not be found
	 */
	public Region fetchByPrimaryKey(long regionId);

	/**
	 * Returns all the regions.
	 *
	 * @return the regions
	 */
	public java.util.List<Region> findAll();

	/**
	 * Returns a range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of regions
	 */
	public java.util.List<Region> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of regions
	 */
	public java.util.List<Region> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator);

	/**
	 * Returns an ordered range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of regions
	 */
	public java.util.List<Region> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the regions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of regions.
	 *
	 * @return the number of regions
	 */
	public int countAll();

}