/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.exception.NoSuchWebsiteException;
import com.liferay.portal.kernel.model.Website;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the website service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WebsiteUtil
 * @generated
 */
@ProviderType
public interface WebsitePersistence extends BasePersistence<Website> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebsiteUtil} to access the website persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the websites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching websites
	 */
	public java.util.List<Website> findByUuid(String uuid);

	/**
	 * Returns a range of all the websites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the websites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where uuid = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByUuid_PrevAndNext(
			long websiteId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of websites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching websites
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the websites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching websites
	 */
	public java.util.List<Website> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the websites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the websites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByUuid_C_PrevAndNext(
			long websiteId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of websites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching websites
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the websites where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching websites
	 */
	public java.util.List<Website> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the websites where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where companyId = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByCompanyId_PrevAndNext(
			long websiteId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of websites where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching websites
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the websites where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching websites
	 */
	public java.util.List<Website> findByUserId(long userId);

	/**
	 * Returns a range of all the websites where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByUserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the websites where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where userId = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByUserId_PrevAndNext(
			long websiteId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of websites where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching websites
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the websites where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the matching websites
	 */
	public java.util.List<Website> findByC_C(long companyId, long classNameId);

	/**
	 * Returns a range of all the websites where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByC_C(
		long companyId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C(
		long companyId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C(
		long companyId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_First(
			long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_First(
		long companyId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_Last(
			long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_Last(
		long companyId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByC_C_PrevAndNext(
			long websiteId, long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where companyId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 */
	public void removeByC_C(long companyId, long classNameId);

	/**
	 * Returns the number of websites where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the number of matching websites
	 */
	public int countByC_C(long companyId, long classNameId);

	/**
	 * Returns all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching websites
	 */
	public java.util.List<Website> findByC_C_C(
		long companyId, long classNameId, long classPK);

	/**
	 * Returns a range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_C_First(
			long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_C_First(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_C_Last(
			long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_C_Last(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByC_C_C_PrevAndNext(
			long websiteId, long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns the number of websites where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching websites
	 */
	public int countByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the matching websites
	 */
	public java.util.List<Website> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns a range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of matching websites
	 */
	public java.util.List<Website> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching websites
	 */
	public java.util.List<Website> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_C_P_First(
			long companyId, long classNameId, long classPK, boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the first website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_C_P_First(
		long companyId, long classNameId, long classPK, boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByC_C_C_P_Last(
			long companyId, long classNameId, long classPK, boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Returns the last website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByC_C_C_P_Last(
		long companyId, long classNameId, long classPK, boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns the websites before and after the current website in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param websiteId the primary key of the current website
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website[] findByC_C_C_P_PrevAndNext(
			long websiteId, long companyId, long classNameId, long classPK,
			boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<Website>
				orderByComparator)
		throws NoSuchWebsiteException;

	/**
	 * Removes all the websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 */
	public void removeByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns the number of websites where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the number of matching websites
	 */
	public int countByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns the website where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchWebsiteException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching website
	 * @throws NoSuchWebsiteException if a matching website could not be found
	 */
	public Website findByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchWebsiteException;

	/**
	 * Returns the website where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Returns the website where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching website, or <code>null</code> if a matching website could not be found
	 */
	public Website fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache);

	/**
	 * Removes the website where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the website that was removed
	 */
	public Website removeByERC_C(String externalReferenceCode, long companyId)
		throws NoSuchWebsiteException;

	/**
	 * Returns the number of websites where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching websites
	 */
	public int countByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Caches the website in the entity cache if it is enabled.
	 *
	 * @param website the website
	 */
	public void cacheResult(Website website);

	/**
	 * Caches the websites in the entity cache if it is enabled.
	 *
	 * @param websites the websites
	 */
	public void cacheResult(java.util.List<Website> websites);

	/**
	 * Creates a new website with the primary key. Does not add the website to the database.
	 *
	 * @param websiteId the primary key for the new website
	 * @return the new website
	 */
	public Website create(long websiteId);

	/**
	 * Removes the website with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param websiteId the primary key of the website
	 * @return the website that was removed
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website remove(long websiteId) throws NoSuchWebsiteException;

	public Website updateImpl(Website website);

	/**
	 * Returns the website with the primary key or throws a <code>NoSuchWebsiteException</code> if it could not be found.
	 *
	 * @param websiteId the primary key of the website
	 * @return the website
	 * @throws NoSuchWebsiteException if a website with the primary key could not be found
	 */
	public Website findByPrimaryKey(long websiteId)
		throws NoSuchWebsiteException;

	/**
	 * Returns the website with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param websiteId the primary key of the website
	 * @return the website, or <code>null</code> if a website with the primary key could not be found
	 */
	public Website fetchByPrimaryKey(long websiteId);

	/**
	 * Returns all the websites.
	 *
	 * @return the websites
	 */
	public java.util.List<Website> findAll();

	/**
	 * Returns a range of all the websites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of websites
	 */
	public java.util.List<Website> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the websites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of websites
	 */
	public java.util.List<Website> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator);

	/**
	 * Returns an ordered range of all the websites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of websites
	 */
	public java.util.List<Website> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Website>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the websites from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of websites.
	 *
	 * @return the number of websites
	 */
	public int countAll();

}