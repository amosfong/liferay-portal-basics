/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence;

import com.liferay.dynamic.data.mapping.exception.NoSuchTemplateException;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ddm template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateUtil
 * @generated
 */
@ProviderType
public interface DDMTemplatePersistence extends BasePersistence<DDMTemplate> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMTemplateUtil} to access the ddm template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the ddm templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid(String uuid);

	/**
	 * Returns a range of all the ddm templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where uuid = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByUuid_PrevAndNext(
			long templateId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of ddm templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching ddm templates
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the ddm template where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTemplateException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException;

	/**
	 * Returns the ddm template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the ddm template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the ddm template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the ddm template that was removed
	 */
	public DDMTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException;

	/**
	 * Returns the number of ddm templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching ddm templates
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the ddm templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the ddm templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByUuid_C_PrevAndNext(
			long templateId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of ddm templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching ddm templates
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the ddm templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByGroupId(long groupId);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByGroupId_PrevAndNext(
			long templateId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByGroupId(long groupId);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByGroupId_PrevAndNext(
			long templateId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of ddm templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching ddm templates
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns all the ddm templates where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByClassPK(long classPK);

	/**
	 * Returns a range of all the ddm templates where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByClassPK(
		long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByClassPK(
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByClassPK(
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByClassPK_First(
			long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByClassPK_First(
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByClassPK_Last(
			long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByClassPK_Last(
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where classPK = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByClassPK_PrevAndNext(
			long templateId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where classPK = &#63; from the database.
	 *
	 * @param classPK the class pk
	 */
	public void removeByClassPK(long classPK);

	/**
	 * Returns the number of ddm templates where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the number of matching ddm templates
	 */
	public int countByClassPK(long classPK);

	/**
	 * Returns all the ddm templates where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByTemplateKey(String templateKey);

	/**
	 * Returns a range of all the ddm templates where templateKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param templateKey the template key
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByTemplateKey(
		String templateKey, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where templateKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param templateKey the template key
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByTemplateKey(
		String templateKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where templateKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param templateKey the template key
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByTemplateKey(
		String templateKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByTemplateKey_First(
			String templateKey,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByTemplateKey_First(
		String templateKey,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByTemplateKey_Last(
			String templateKey,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByTemplateKey_Last(
		String templateKey,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where templateKey = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param templateKey the template key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByTemplateKey_PrevAndNext(
			long templateId, String templateKey,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where templateKey = &#63; from the database.
	 *
	 * @param templateKey the template key
	 */
	public void removeByTemplateKey(String templateKey);

	/**
	 * Returns the number of ddm templates where templateKey = &#63;.
	 *
	 * @param templateKey the template key
	 * @return the number of matching ddm templates
	 */
	public int countByTemplateKey(String templateKey);

	/**
	 * Returns all the ddm templates where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByType(String type);

	/**
	 * Returns a range of all the ddm templates where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByType(
		String type, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByType_First(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByType_First(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByType_Last(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByType_Last(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where type = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByType_PrevAndNext(
			long templateId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeByType(String type);

	/**
	 * Returns the number of ddm templates where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching ddm templates
	 */
	public int countByType(String type);

	/**
	 * Returns all the ddm templates where language = &#63;.
	 *
	 * @param language the language
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByLanguage(String language);

	/**
	 * Returns a range of all the ddm templates where language = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param language the language
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByLanguage(
		String language, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where language = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param language the language
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByLanguage(
		String language, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where language = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param language the language
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByLanguage(
		String language, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByLanguage_First(
			String language,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByLanguage_First(
		String language,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByLanguage_Last(
			String language,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByLanguage_Last(
		String language,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where language = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByLanguage_PrevAndNext(
			long templateId, String language,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where language = &#63; from the database.
	 *
	 * @param language the language
	 */
	public void removeByLanguage(String language);

	/**
	 * Returns the number of ddm templates where language = &#63;.
	 *
	 * @param language the language
	 * @return the number of matching ddm templates
	 */
	public int countByLanguage(String language);

	/**
	 * Returns the ddm template where smallImageId = &#63; or throws a <code>NoSuchTemplateException</code> if it could not be found.
	 *
	 * @param smallImageId the small image ID
	 * @return the matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findBySmallImageId(long smallImageId)
		throws NoSuchTemplateException;

	/**
	 * Returns the ddm template where smallImageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param smallImageId the small image ID
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchBySmallImageId(long smallImageId);

	/**
	 * Returns the ddm template where smallImageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param smallImageId the small image ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchBySmallImageId(
		long smallImageId, boolean useFinderCache);

	/**
	 * Removes the ddm template where smallImageId = &#63; from the database.
	 *
	 * @param smallImageId the small image ID
	 * @return the ddm template that was removed
	 */
	public DDMTemplate removeBySmallImageId(long smallImageId)
		throws NoSuchTemplateException;

	/**
	 * Returns the number of ddm templates where smallImageId = &#63;.
	 *
	 * @param smallImageId the small image ID
	 * @return the number of matching ddm templates
	 */
	public int countBySmallImageId(long smallImageId);

	/**
	 * Returns all the ddm templates where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C(
		long groupId, long classNameId);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C(
		long groupId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_First(
			long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_Last(
			long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByG_C_PrevAndNext(
			long templateId, long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C(
		long groupId, long classNameId);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C(
		long groupId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByG_C_PrevAndNext(
			long templateId, long groupId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where groupId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 */
	public void removeByG_C(long groupId, long classNameId);

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the number of matching ddm templates
	 */
	public int countByG_C(long groupId, long classNameId);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_C(long groupId, long classNameId);

	/**
	 * Returns all the ddm templates where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(long groupId, long classPK);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long groupId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long groupId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long groupId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_CPK_First(
			long groupId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_CPK_First(
		long groupId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_CPK_Last(
			long groupId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_CPK_Last(
		long groupId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63; and classPK = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByG_CPK_PrevAndNext(
			long templateId, long groupId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long groupId, long classPK);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long groupId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long groupId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63; and classPK = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByG_CPK_PrevAndNext(
			long templateId, long groupId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = any &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long[] groupIds, long classPK);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = any &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long[] groupIds, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permission to view where groupId = any &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_CPK(
		long[] groupIds, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns all the ddm templates where groupId = any &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long[] groupIds, long classPK);

	/**
	 * Returns a range of all the ddm templates where groupId = any &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long[] groupIds, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = any &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long[] groupIds, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classPK = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_CPK(
		long[] groupIds, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddm templates where groupId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 */
	public void removeByG_CPK(long groupId, long classPK);

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates
	 */
	public int countByG_CPK(long groupId, long classPK);

	/**
	 * Returns the number of ddm templates where groupId = any &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @return the number of matching ddm templates
	 */
	public int countByG_CPK(long[] groupIds, long classPK);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_CPK(long groupId, long classPK);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = any &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classPK the class pk
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_CPK(long[] groupIds, long classPK);

	/**
	 * Returns all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long groupId, long classNameId, long classPK);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_First(
			long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_First(
		long groupId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_Last(
			long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_Last(
		long groupId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByG_C_C_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long groupId, long classNameId, long classPK);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long groupId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByG_C_C_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long[] groupIds, long classNameId, long classPK);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long[] groupIds, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permission to view where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C(
		long[] groupIds, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns all the ddm templates where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long[] groupIds, long classNameId, long classPK);

	/**
	 * Returns a range of all the ddm templates where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long[] groupIds, long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long[] groupIds, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C(
		long[] groupIds, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByG_C_C(long groupId, long classNameId, long classPK);

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates
	 */
	public int countByG_C_C(long groupId, long classNameId, long classPK);

	/**
	 * Returns the number of ddm templates where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates
	 */
	public int countByG_C_C(long[] groupIds, long classNameId, long classPK);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_C_C(long groupId, long classNameId, long classPK);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = any &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_C_C(
		long[] groupIds, long classNameId, long classPK);

	/**
	 * Returns the ddm template where groupId = &#63; and classNameId = &#63; and templateKey = &#63; or throws a <code>NoSuchTemplateException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param templateKey the template key
	 * @return the matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_T(
			long groupId, long classNameId, String templateKey)
		throws NoSuchTemplateException;

	/**
	 * Returns the ddm template where groupId = &#63; and classNameId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param templateKey the template key
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_T(
		long groupId, long classNameId, String templateKey);

	/**
	 * Returns the ddm template where groupId = &#63; and classNameId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param templateKey the template key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_T(
		long groupId, long classNameId, String templateKey,
		boolean useFinderCache);

	/**
	 * Removes the ddm template where groupId = &#63; and classNameId = &#63; and templateKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param templateKey the template key
	 * @return the ddm template that was removed
	 */
	public DDMTemplate removeByG_C_T(
			long groupId, long classNameId, String templateKey)
		throws NoSuchTemplateException;

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classNameId = &#63; and templateKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param templateKey the template key
	 * @return the number of matching ddm templates
	 */
	public int countByG_C_T(long groupId, long classNameId, String templateKey);

	/**
	 * Returns all the ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByC_C_T(
		long classNameId, long classPK, String type);

	/**
	 * Returns a range of all the ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByC_C_T(
		long classNameId, long classPK, String type, int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByC_C_T(
		long classNameId, long classPK, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByC_C_T(
		long classNameId, long classPK, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByC_C_T_First(
			long classNameId, long classPK, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByC_C_T_First(
		long classNameId, long classPK, String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByC_C_T_Last(
			long classNameId, long classPK, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByC_C_T_Last(
		long classNameId, long classPK, String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByC_C_T_PrevAndNext(
			long templateId, long classNameId, long classPK, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public void removeByC_C_T(long classNameId, long classPK, String type);

	/**
	 * Returns the number of ddm templates where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching ddm templates
	 */
	public int countByC_C_T(long classNameId, long classPK, String type);

	/**
	 * Returns all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T(
		long groupId, long classNameId, long classPK, String type);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T(
		long groupId, long classNameId, long classPK, String type, int start,
		int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T(
		long groupId, long classNameId, long classPK, String type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T(
		long groupId, long classNameId, long classPK, String type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_T_First(
			long groupId, long classNameId, long classPK, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_T_First(
		long groupId, long classNameId, long classPK, String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_T_Last(
			long groupId, long classNameId, long classPK, String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_T_Last(
		long groupId, long classNameId, long classPK, String type,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByG_C_C_T_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T(
		long groupId, long classNameId, long classPK, String type);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T(
		long groupId, long classNameId, long classPK, String type, int start,
		int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T(
		long groupId, long classNameId, long classPK, String type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByG_C_C_T_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public void removeByG_C_C_T(
		long groupId, long classNameId, long classPK, String type);

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching ddm templates
	 */
	public int countByG_C_C_T(
		long groupId, long classNameId, long classPK, String type);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_C_C_T(
		long groupId, long classNameId, long classPK, String type);

	/**
	 * Returns all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @return the matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode);

	/**
	 * Returns a range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode,
		int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm templates
	 */
	public java.util.List<DDMTemplate> findByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_T_M_First(
			long groupId, long classNameId, long classPK, String type,
			String mode,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the first ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_T_M_First(
		long groupId, long classNameId, long classPK, String type, String mode,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByG_C_C_T_M_Last(
			long groupId, long classNameId, long classPK, String type,
			String mode,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns the last ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByG_C_C_T_M_Last(
		long groupId, long classNameId, long classPK, String type, String mode,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] findByG_C_C_T_M_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			String type, String mode,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Returns all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @return the matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode);

	/**
	 * Returns a range of all the ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode,
		int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates that the user has permissions to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm templates that the user has permission to view
	 */
	public java.util.List<DDMTemplate> filterFindByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns the ddm templates before and after the current ddm template in the ordered set of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param templateId the primary key of the current ddm template
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate[] filterFindByG_C_C_T_M_PrevAndNext(
			long templateId, long groupId, long classNameId, long classPK,
			String type, String mode,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
				orderByComparator)
		throws NoSuchTemplateException;

	/**
	 * Removes all the ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 */
	public void removeByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode);

	/**
	 * Returns the number of ddm templates where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @return the number of matching ddm templates
	 */
	public int countByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode);

	/**
	 * Returns the number of ddm templates that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and mode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param mode the mode
	 * @return the number of matching ddm templates that the user has permission to view
	 */
	public int filterCountByG_C_C_T_M(
		long groupId, long classNameId, long classPK, String type, String mode);

	/**
	 * Returns the ddm template where externalReferenceCode = &#63; and groupId = &#63; or throws a <code>NoSuchTemplateException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the matching ddm template
	 * @throws NoSuchTemplateException if a matching ddm template could not be found
	 */
	public DDMTemplate findByERC_G(String externalReferenceCode, long groupId)
		throws NoSuchTemplateException;

	/**
	 * Returns the ddm template where externalReferenceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByERC_G(String externalReferenceCode, long groupId);

	/**
	 * Returns the ddm template where externalReferenceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	public DDMTemplate fetchByERC_G(
		String externalReferenceCode, long groupId, boolean useFinderCache);

	/**
	 * Removes the ddm template where externalReferenceCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the ddm template that was removed
	 */
	public DDMTemplate removeByERC_G(String externalReferenceCode, long groupId)
		throws NoSuchTemplateException;

	/**
	 * Returns the number of ddm templates where externalReferenceCode = &#63; and groupId = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @return the number of matching ddm templates
	 */
	public int countByERC_G(String externalReferenceCode, long groupId);

	/**
	 * Caches the ddm template in the entity cache if it is enabled.
	 *
	 * @param ddmTemplate the ddm template
	 */
	public void cacheResult(DDMTemplate ddmTemplate);

	/**
	 * Caches the ddm templates in the entity cache if it is enabled.
	 *
	 * @param ddmTemplates the ddm templates
	 */
	public void cacheResult(java.util.List<DDMTemplate> ddmTemplates);

	/**
	 * Creates a new ddm template with the primary key. Does not add the ddm template to the database.
	 *
	 * @param templateId the primary key for the new ddm template
	 * @return the new ddm template
	 */
	public DDMTemplate create(long templateId);

	/**
	 * Removes the ddm template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateId the primary key of the ddm template
	 * @return the ddm template that was removed
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate remove(long templateId) throws NoSuchTemplateException;

	public DDMTemplate updateImpl(DDMTemplate ddmTemplate);

	/**
	 * Returns the ddm template with the primary key or throws a <code>NoSuchTemplateException</code> if it could not be found.
	 *
	 * @param templateId the primary key of the ddm template
	 * @return the ddm template
	 * @throws NoSuchTemplateException if a ddm template with the primary key could not be found
	 */
	public DDMTemplate findByPrimaryKey(long templateId)
		throws NoSuchTemplateException;

	/**
	 * Returns the ddm template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param templateId the primary key of the ddm template
	 * @return the ddm template, or <code>null</code> if a ddm template with the primary key could not be found
	 */
	public DDMTemplate fetchByPrimaryKey(long templateId);

	/**
	 * Returns all the ddm templates.
	 *
	 * @return the ddm templates
	 */
	public java.util.List<DDMTemplate> findAll();

	/**
	 * Returns a range of all the ddm templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of ddm templates
	 */
	public java.util.List<DDMTemplate> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the ddm templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm templates
	 */
	public java.util.List<DDMTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm templates
	 */
	public java.util.List<DDMTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddm templates from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ddm templates.
	 *
	 * @return the number of ddm templates
	 */
	public int countAll();

}