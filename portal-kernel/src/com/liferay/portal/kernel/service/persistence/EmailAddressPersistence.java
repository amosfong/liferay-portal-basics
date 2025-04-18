/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.exception.NoSuchEmailAddressException;
import com.liferay.portal.kernel.model.EmailAddress;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the email address service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddressUtil
 * @generated
 */
@ProviderType
public interface EmailAddressPersistence extends BasePersistence<EmailAddress> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmailAddressUtil} to access the email address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the email addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid(String uuid);

	/**
	 * Returns a range of all the email addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where uuid = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByUuid_PrevAndNext(
			long emailAddressId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of email addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching email addresses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByUuid_C_PrevAndNext(
			long emailAddressId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the email addresses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the email addresses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where companyId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByCompanyId_PrevAndNext(
			long emailAddressId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of email addresses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the email addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByUserId(long userId);

	/**
	 * Returns a range of all the email addresses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where userId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByUserId_PrevAndNext(
			long emailAddressId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of email addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching email addresses
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C(
		long companyId, long classNameId);

	/**
	 * Returns a range of all the email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_First(
			long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_First(
		long companyId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_Last(
			long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_Last(
		long companyId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByC_C_PrevAndNext(
			long emailAddressId, long companyId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 */
	public void removeByC_C(long companyId, long classNameId);

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the number of matching email addresses
	 */
	public int countByC_C(long companyId, long classNameId);

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK);

	/**
	 * Returns a range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_C_First(
			long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_C_First(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_C_Last(
			long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_C_Last(
		long companyId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByC_C_C_PrevAndNext(
			long emailAddressId, long companyId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching email addresses
	 */
	public int countByC_C_C(long companyId, long classNameId, long classPK);

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns a range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching email addresses
	 */
	public java.util.List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_C_P_First(
			long companyId, long classNameId, long classPK, boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_C_P_First(
		long companyId, long classNameId, long classPK, boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByC_C_C_P_Last(
			long companyId, long classNameId, long classPK, boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByC_C_C_P_Last(
		long companyId, long classNameId, long classPK, boolean primary,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress[] findByC_C_C_P_PrevAndNext(
			long emailAddressId, long companyId, long classNameId, long classPK,
			boolean primary,
			com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
				orderByComparator)
		throws NoSuchEmailAddressException;

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 */
	public void removeByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the number of matching email addresses
	 */
	public int countByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary);

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchEmailAddressException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public EmailAddress findByERC_C(
			String externalReferenceCode, long companyId)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByERC_C(
		String externalReferenceCode, long companyId);

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public EmailAddress fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache);

	/**
	 * Removes the email address where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the email address that was removed
	 */
	public EmailAddress removeByERC_C(
			String externalReferenceCode, long companyId)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the number of email addresses where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public int countByERC_C(String externalReferenceCode, long companyId);

	/**
	 * Caches the email address in the entity cache if it is enabled.
	 *
	 * @param emailAddress the email address
	 */
	public void cacheResult(EmailAddress emailAddress);

	/**
	 * Caches the email addresses in the entity cache if it is enabled.
	 *
	 * @param emailAddresses the email addresses
	 */
	public void cacheResult(java.util.List<EmailAddress> emailAddresses);

	/**
	 * Creates a new email address with the primary key. Does not add the email address to the database.
	 *
	 * @param emailAddressId the primary key for the new email address
	 * @return the new email address
	 */
	public EmailAddress create(long emailAddressId);

	/**
	 * Removes the email address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address that was removed
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress remove(long emailAddressId)
		throws NoSuchEmailAddressException;

	public EmailAddress updateImpl(EmailAddress emailAddress);

	/**
	 * Returns the email address with the primary key or throws a <code>NoSuchEmailAddressException</code> if it could not be found.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public EmailAddress findByPrimaryKey(long emailAddressId)
		throws NoSuchEmailAddressException;

	/**
	 * Returns the email address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address, or <code>null</code> if a email address with the primary key could not be found
	 */
	public EmailAddress fetchByPrimaryKey(long emailAddressId);

	/**
	 * Returns all the email addresses.
	 *
	 * @return the email addresses
	 */
	public java.util.List<EmailAddress> findAll();

	/**
	 * Returns a range of all the email addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @return the range of email addresses
	 */
	public java.util.List<EmailAddress> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the email addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of email addresses
	 */
	public java.util.List<EmailAddress> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator);

	/**
	 * Returns an ordered range of all the email addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmailAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of email addresses
	 * @param end the upper bound of the range of email addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of email addresses
	 */
	public java.util.List<EmailAddress> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EmailAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the email addresses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of email addresses.
	 *
	 * @return the number of email addresses
	 */
	public int countAll();

}