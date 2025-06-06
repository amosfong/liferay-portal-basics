/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the email address service. This utility wraps <code>com.liferay.portal.service.persistence.impl.EmailAddressPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddressPersistence
 * @generated
 */
public class EmailAddressUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(EmailAddress emailAddress) {
		getPersistence().clearCache(emailAddress);
	}

	/**
	 * @see BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, EmailAddress> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EmailAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EmailAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EmailAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EmailAddress update(EmailAddress emailAddress) {
		return getPersistence().update(emailAddress);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EmailAddress update(
		EmailAddress emailAddress, ServiceContext serviceContext) {

		return getPersistence().update(emailAddress, serviceContext);
	}

	/**
	 * Returns all the email addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<EmailAddress> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<EmailAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUuid_First(
			String uuid, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUuid_First(
		String uuid, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUuid_Last(
			String uuid, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUuid_Last(
		String uuid, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where uuid = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public static EmailAddress[] findByUuid_PrevAndNext(
			long emailAddressId, String uuid,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_PrevAndNext(
			emailAddressId, uuid, orderByComparator);
	}

	/**
	 * Removes all the email addresses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of email addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching email addresses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static EmailAddress[] findByUuid_C_PrevAndNext(
			long emailAddressId, String uuid, long companyId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUuid_C_PrevAndNext(
			emailAddressId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the email addresses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of email addresses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the email addresses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static List<EmailAddress> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static List<EmailAddress> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByCompanyId_First(
			long companyId, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByCompanyId_First(
		long companyId, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByCompanyId_Last(
			long companyId, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByCompanyId_Last(
		long companyId, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where companyId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public static EmailAddress[] findByCompanyId_PrevAndNext(
			long emailAddressId, long companyId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByCompanyId_PrevAndNext(
			emailAddressId, companyId, orderByComparator);
	}

	/**
	 * Removes all the email addresses where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of email addresses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the email addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

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
	public static List<EmailAddress> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static List<EmailAddress> findByUserId(
		long userId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByUserId(
		long userId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUserId_First(
			long userId, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUserId_First(
		long userId, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByUserId_Last(
			long userId, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByUserId_Last(
		long userId, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the email addresses before and after the current email address in the ordered set where userId = &#63;.
	 *
	 * @param emailAddressId the primary key of the current email address
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public static EmailAddress[] findByUserId_PrevAndNext(
			long emailAddressId, long userId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByUserId_PrevAndNext(
			emailAddressId, userId, orderByComparator);
	}

	/**
	 * Removes all the email addresses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of email addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching email addresses
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByC_C(
		long companyId, long classNameId) {

		return getPersistence().findByC_C(companyId, classNameId);
	}

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
	public static List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end) {

		return getPersistence().findByC_C(companyId, classNameId, start, end);
	}

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
	public static List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByC_C(
			companyId, classNameId, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByC_C(
		long companyId, long classNameId, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			companyId, classNameId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByC_C_First(
			long companyId, long classNameId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_First(
			companyId, classNameId, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByC_C_First(
		long companyId, long classNameId,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			companyId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByC_C_Last(
			long companyId, long classNameId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_Last(
			companyId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByC_C_Last(
		long companyId, long classNameId,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			companyId, classNameId, orderByComparator);
	}

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
	public static EmailAddress[] findByC_C_PrevAndNext(
			long emailAddressId, long companyId, long classNameId,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_PrevAndNext(
			emailAddressId, companyId, classNameId, orderByComparator);
	}

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 */
	public static void removeByC_C(long companyId, long classNameId) {
		getPersistence().removeByC_C(companyId, classNameId);
	}

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the number of matching email addresses
	 */
	public static int countByC_C(long companyId, long classNameId) {
		return getPersistence().countByC_C(companyId, classNameId);
	}

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK) {

		return getPersistence().findByC_C_C(companyId, classNameId, classPK);
	}

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
	public static List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end);
	}

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
	public static List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findByC_C_C(
		long companyId, long classNameId, long classPK, int start, int end,
		OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_C(
			companyId, classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static EmailAddress findByC_C_C_First(
			long companyId, long classNameId, long classPK,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_First(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByC_C_C_First(
		long companyId, long classNameId, long classPK,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_C_First(
			companyId, classNameId, classPK, orderByComparator);
	}

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
	public static EmailAddress findByC_C_C_Last(
			long companyId, long classNameId, long classPK,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_Last(
			companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last email address in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByC_C_C_Last(
		long companyId, long classNameId, long classPK,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_C_Last(
			companyId, classNameId, classPK, orderByComparator);
	}

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
	public static EmailAddress[] findByC_C_C_PrevAndNext(
			long emailAddressId, long companyId, long classNameId, long classPK,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_PrevAndNext(
			emailAddressId, companyId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C_C(
		long companyId, long classNameId, long classPK) {

		getPersistence().removeByC_C_C(companyId, classNameId, classPK);
	}

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching email addresses
	 */
	public static int countByC_C_C(
		long companyId, long classNameId, long classPK) {

		return getPersistence().countByC_C_C(companyId, classNameId, classPK);
	}

	/**
	 * Returns all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the matching email addresses
	 */
	public static List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary) {

		return getPersistence().findByC_C_C_P(
			companyId, classNameId, classPK, primary);
	}

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
	public static List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end) {

		return getPersistence().findByC_C_C_P(
			companyId, classNameId, classPK, primary, start, end);
	}

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
	public static List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findByC_C_C_P(
			companyId, classNameId, classPK, primary, start, end,
			orderByComparator);
	}

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
	public static List<EmailAddress> findByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary,
		int start, int end, OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_C_P(
			companyId, classNameId, classPK, primary, start, end,
			orderByComparator, useFinderCache);
	}

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
	public static EmailAddress findByC_C_C_P_First(
			long companyId, long classNameId, long classPK, boolean primary,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_P_First(
			companyId, classNameId, classPK, primary, orderByComparator);
	}

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
	public static EmailAddress fetchByC_C_C_P_First(
		long companyId, long classNameId, long classPK, boolean primary,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_C_P_First(
			companyId, classNameId, classPK, primary, orderByComparator);
	}

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
	public static EmailAddress findByC_C_C_P_Last(
			long companyId, long classNameId, long classPK, boolean primary,
			OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_P_Last(
			companyId, classNameId, classPK, primary, orderByComparator);
	}

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
	public static EmailAddress fetchByC_C_C_P_Last(
		long companyId, long classNameId, long classPK, boolean primary,
		OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().fetchByC_C_C_P_Last(
			companyId, classNameId, classPK, primary, orderByComparator);
	}

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
	public static EmailAddress[] findByC_C_C_P_PrevAndNext(
			long emailAddressId, long companyId, long classNameId, long classPK,
			boolean primary, OrderByComparator<EmailAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByC_C_C_P_PrevAndNext(
			emailAddressId, companyId, classNameId, classPK, primary,
			orderByComparator);
	}

	/**
	 * Removes all the email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 */
	public static void removeByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary) {

		getPersistence().removeByC_C_C_P(
			companyId, classNameId, classPK, primary);
	}

	/**
	 * Returns the number of email addresses where companyId = &#63; and classNameId = &#63; and classPK = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param primary the primary
	 * @return the number of matching email addresses
	 */
	public static int countByC_C_C_P(
		long companyId, long classNameId, long classPK, boolean primary) {

		return getPersistence().countByC_C_C_P(
			companyId, classNameId, classPK, primary);
	}

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or throws a <code>NoSuchEmailAddressException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching email address
	 * @throws NoSuchEmailAddressException if a matching email address could not be found
	 */
	public static EmailAddress findByERC_C(
			String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().fetchByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the email address where externalReferenceCode = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching email address, or <code>null</code> if a matching email address could not be found
	 */
	public static EmailAddress fetchByERC_C(
		String externalReferenceCode, long companyId, boolean useFinderCache) {

		return getPersistence().fetchByERC_C(
			externalReferenceCode, companyId, useFinderCache);
	}

	/**
	 * Removes the email address where externalReferenceCode = &#63; and companyId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the email address that was removed
	 */
	public static EmailAddress removeByERC_C(
			String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().removeByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Returns the number of email addresses where externalReferenceCode = &#63; and companyId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param companyId the company ID
	 * @return the number of matching email addresses
	 */
	public static int countByERC_C(
		String externalReferenceCode, long companyId) {

		return getPersistence().countByERC_C(externalReferenceCode, companyId);
	}

	/**
	 * Caches the email address in the entity cache if it is enabled.
	 *
	 * @param emailAddress the email address
	 */
	public static void cacheResult(EmailAddress emailAddress) {
		getPersistence().cacheResult(emailAddress);
	}

	/**
	 * Caches the email addresses in the entity cache if it is enabled.
	 *
	 * @param emailAddresses the email addresses
	 */
	public static void cacheResult(List<EmailAddress> emailAddresses) {
		getPersistence().cacheResult(emailAddresses);
	}

	/**
	 * Creates a new email address with the primary key. Does not add the email address to the database.
	 *
	 * @param emailAddressId the primary key for the new email address
	 * @return the new email address
	 */
	public static EmailAddress create(long emailAddressId) {
		return getPersistence().create(emailAddressId);
	}

	/**
	 * Removes the email address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address that was removed
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public static EmailAddress remove(long emailAddressId)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().remove(emailAddressId);
	}

	public static EmailAddress updateImpl(EmailAddress emailAddress) {
		return getPersistence().updateImpl(emailAddress);
	}

	/**
	 * Returns the email address with the primary key or throws a <code>NoSuchEmailAddressException</code> if it could not be found.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address
	 * @throws NoSuchEmailAddressException if a email address with the primary key could not be found
	 */
	public static EmailAddress findByPrimaryKey(long emailAddressId)
		throws com.liferay.portal.kernel.exception.NoSuchEmailAddressException {

		return getPersistence().findByPrimaryKey(emailAddressId);
	}

	/**
	 * Returns the email address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emailAddressId the primary key of the email address
	 * @return the email address, or <code>null</code> if a email address with the primary key could not be found
	 */
	public static EmailAddress fetchByPrimaryKey(long emailAddressId) {
		return getPersistence().fetchByPrimaryKey(emailAddressId);
	}

	/**
	 * Returns all the email addresses.
	 *
	 * @return the email addresses
	 */
	public static List<EmailAddress> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EmailAddress> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EmailAddress> findAll(
		int start, int end, OrderByComparator<EmailAddress> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EmailAddress> findAll(
		int start, int end, OrderByComparator<EmailAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the email addresses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of email addresses.
	 *
	 * @return the number of email addresses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EmailAddressPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(EmailAddressPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile EmailAddressPersistence _persistence;

}