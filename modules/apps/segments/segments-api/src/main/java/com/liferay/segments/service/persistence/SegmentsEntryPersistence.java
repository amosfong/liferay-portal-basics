/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.segments.exception.NoSuchEntryException;
import com.liferay.segments.model.SegmentsEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the segments entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryUtil
 * @generated
 */
@ProviderType
public interface SegmentsEntryPersistence
	extends BasePersistence<SegmentsEntry>, CTPersistence<SegmentsEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SegmentsEntryUtil} to access the segments entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the segments entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid(String uuid);

	/**
	 * Returns a range of all the segments entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where uuid = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByUuid_PrevAndNext(
			long segmentsEntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the segments entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of segments entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching segments entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the segments entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException;

	/**
	 * Returns the segments entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the segments entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the segments entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the segments entry that was removed
	 */
	public SegmentsEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException;

	/**
	 * Returns the number of segments entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching segments entries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the segments entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the segments entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByUuid_C_PrevAndNext(
			long segmentsEntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the segments entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of segments entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching segments entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the segments entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(long groupId);

	/**
	 * Returns a range of all the segments entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where groupId = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByGroupId_PrevAndNext(
			long segmentsEntryId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(long groupId);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set of segments entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] filterFindByGroupId_PrevAndNext(
			long segmentsEntryId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(long[] groupIds);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(
		long[] groupIds, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByGroupId(
		long[] groupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns all the segments entries where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(long[] groupIds);

	/**
	 * Returns a range of all the segments entries where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long[] groupIds, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long[] groupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByGroupId(
		long[] groupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the segments entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of segments entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching segments entries
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of segments entries where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching segments entries
	 */
	public int countByGroupId(long[] groupIds);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByGroupId(long[] groupIds);

	/**
	 * Returns all the segments entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the segments entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where companyId = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByCompanyId_PrevAndNext(
			long segmentsEntryId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries where companyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyIds the company IDs
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(long[] companyIds);

	/**
	 * Returns a range of all the segments entries where companyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyIds the company IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long[] companyIds, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where companyId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyIds the company IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long[] companyIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where companyId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param companyIds the company IDs
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByCompanyId(
		long[] companyIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the segments entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of segments entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching segments entries
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns the number of segments entries where companyId = any &#63;.
	 *
	 * @param companyIds the company IDs
	 * @return the number of matching segments entries
	 */
	public int countByCompanyId(long[] companyIds);

	/**
	 * Returns all the segments entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByActive(boolean active);

	/**
	 * Returns a range of all the segments entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByActive(
		boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByActive_First(
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByActive_Last(
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where active = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByActive_PrevAndNext(
			long segmentsEntryId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the segments entries where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	public void removeByActive(boolean active);

	/**
	 * Returns the number of segments entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching segments entries
	 */
	public int countByActive(boolean active);

	/**
	 * Returns all the segments entries where source = &#63;.
	 *
	 * @param source the source
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findBySource(String source);

	/**
	 * Returns a range of all the segments entries where source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findBySource(
		String source, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findBySource(
		String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findBySource(
		String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where source = &#63;.
	 *
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findBySource_First(
			String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where source = &#63;.
	 *
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchBySource_First(
		String source,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where source = &#63;.
	 *
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findBySource_Last(
			String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where source = &#63;.
	 *
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchBySource_Last(
		String source,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where source = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findBySource_PrevAndNext(
			long segmentsEntryId, String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the segments entries where source = &#63; from the database.
	 *
	 * @param source the source
	 */
	public void removeBySource(String source);

	/**
	 * Returns the number of segments entries where source = &#63;.
	 *
	 * @param source the source
	 * @return the number of matching segments entries
	 */
	public int countBySource(String source);

	/**
	 * Returns the segments entry where groupId = &#63; and segmentsEntryKey = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryKey the segments entry key
	 * @return the matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByG_S(long groupId, String segmentsEntryKey)
		throws NoSuchEntryException;

	/**
	 * Returns the segments entry where groupId = &#63; and segmentsEntryKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryKey the segments entry key
	 * @return the matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_S(long groupId, String segmentsEntryKey);

	/**
	 * Returns the segments entry where groupId = &#63; and segmentsEntryKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryKey the segments entry key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_S(
		long groupId, String segmentsEntryKey, boolean useFinderCache);

	/**
	 * Removes the segments entry where groupId = &#63; and segmentsEntryKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryKey the segments entry key
	 * @return the segments entry that was removed
	 */
	public SegmentsEntry removeByG_S(long groupId, String segmentsEntryKey)
		throws NoSuchEntryException;

	/**
	 * Returns the number of segments entries where groupId = &#63; and segmentsEntryKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryKey the segments entry key
	 * @return the number of matching segments entries
	 */
	public int countByG_S(long groupId, String segmentsEntryKey);

	/**
	 * Returns all the segments entries where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long groupId, boolean active);

	/**
	 * Returns a range of all the segments entries where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long groupId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByG_A_First(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_A_First(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByG_A_Last(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_A_Last(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByG_A_PrevAndNext(
			long segmentsEntryId, long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long groupId, boolean active);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long groupId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permissions to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set of segments entries that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] filterFindByG_A_PrevAndNext(
			long segmentsEntryId, long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = any &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long[] groupIds, boolean active);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long[] groupIds, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permission to view where groupId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_A(
		long[] groupIds, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns all the segments entries where groupId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long[] groupIds, boolean active);

	/**
	 * Returns a range of all the segments entries where groupId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long[] groupIds, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long[] groupIds, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and active = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_A(
		long[] groupIds, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the segments entries where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 */
	public void removeByG_A(long groupId, boolean active);

	/**
	 * Returns the number of segments entries where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching segments entries
	 */
	public int countByG_A(long groupId, boolean active);

	/**
	 * Returns the number of segments entries where groupId = any &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @return the number of matching segments entries
	 */
	public int countByG_A(long[] groupIds, boolean active);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByG_A(long groupId, boolean active);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = any &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param active the active
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByG_A(long[] groupIds, boolean active);

	/**
	 * Returns all the segments entries where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long groupId, String source);

	/**
	 * Returns a range of all the segments entries where groupId = &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long groupId, String source, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long groupId, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long groupId, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByG_SRC_First(
			long groupId, String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first segments entry in the ordered set where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_SRC_First(
		long groupId, String source,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry
	 * @throws NoSuchEntryException if a matching segments entry could not be found
	 */
	public SegmentsEntry findByG_SRC_Last(
			long groupId, String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last segments entry in the ordered set where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments entry, or <code>null</code> if a matching segments entry could not be found
	 */
	public SegmentsEntry fetchByG_SRC_Last(
		long groupId, String source,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set where groupId = &#63; and source = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] findByG_SRC_PrevAndNext(
			long segmentsEntryId, long groupId, String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long groupId, String source);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long groupId, String source, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permissions to view where groupId = &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long groupId, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns the segments entries before and after the current segments entry in the ordered set of segments entries that the user has permission to view where groupId = &#63; and source = &#63;.
	 *
	 * @param segmentsEntryId the primary key of the current segments entry
	 * @param groupId the group ID
	 * @param source the source
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry[] filterFindByG_SRC_PrevAndNext(
			long segmentsEntryId, long groupId, String source,
			com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
				orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns all the segments entries that the user has permission to view where groupId = any &#63; and source = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @return the matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long[] groupIds, String source);

	/**
	 * Returns a range of all the segments entries that the user has permission to view where groupId = any &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long[] groupIds, String source, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries that the user has permission to view where groupId = any &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries that the user has permission to view
	 */
	public java.util.List<SegmentsEntry> filterFindByG_SRC(
		long[] groupIds, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns all the segments entries where groupId = any &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @return the matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long[] groupIds, String source);

	/**
	 * Returns a range of all the segments entries where groupId = any &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long[] groupIds, String source, int start, int end);

	/**
	 * Returns an ordered range of all the segments entries where groupId = any &#63; and source = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long[] groupIds, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries where groupId = &#63; and source = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments entries
	 */
	public java.util.List<SegmentsEntry> findByG_SRC(
		long[] groupIds, String source, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the segments entries where groupId = &#63; and source = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 */
	public void removeByG_SRC(long groupId, String source);

	/**
	 * Returns the number of segments entries where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @return the number of matching segments entries
	 */
	public int countByG_SRC(long groupId, String source);

	/**
	 * Returns the number of segments entries where groupId = any &#63; and source = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @return the number of matching segments entries
	 */
	public int countByG_SRC(long[] groupIds, String source);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = &#63; and source = &#63;.
	 *
	 * @param groupId the group ID
	 * @param source the source
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByG_SRC(long groupId, String source);

	/**
	 * Returns the number of segments entries that the user has permission to view where groupId = any &#63; and source = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param source the source
	 * @return the number of matching segments entries that the user has permission to view
	 */
	public int filterCountByG_SRC(long[] groupIds, String source);

	/**
	 * Caches the segments entry in the entity cache if it is enabled.
	 *
	 * @param segmentsEntry the segments entry
	 */
	public void cacheResult(SegmentsEntry segmentsEntry);

	/**
	 * Caches the segments entries in the entity cache if it is enabled.
	 *
	 * @param segmentsEntries the segments entries
	 */
	public void cacheResult(java.util.List<SegmentsEntry> segmentsEntries);

	/**
	 * Creates a new segments entry with the primary key. Does not add the segments entry to the database.
	 *
	 * @param segmentsEntryId the primary key for the new segments entry
	 * @return the new segments entry
	 */
	public SegmentsEntry create(long segmentsEntryId);

	/**
	 * Removes the segments entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param segmentsEntryId the primary key of the segments entry
	 * @return the segments entry that was removed
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry remove(long segmentsEntryId)
		throws NoSuchEntryException;

	public SegmentsEntry updateImpl(SegmentsEntry segmentsEntry);

	/**
	 * Returns the segments entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param segmentsEntryId the primary key of the segments entry
	 * @return the segments entry
	 * @throws NoSuchEntryException if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry findByPrimaryKey(long segmentsEntryId)
		throws NoSuchEntryException;

	/**
	 * Returns the segments entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param segmentsEntryId the primary key of the segments entry
	 * @return the segments entry, or <code>null</code> if a segments entry with the primary key could not be found
	 */
	public SegmentsEntry fetchByPrimaryKey(long segmentsEntryId);

	/**
	 * Returns all the segments entries.
	 *
	 * @return the segments entries
	 */
	public java.util.List<SegmentsEntry> findAll();

	/**
	 * Returns a range of all the segments entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @return the range of segments entries
	 */
	public java.util.List<SegmentsEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the segments entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of segments entries
	 */
	public java.util.List<SegmentsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the segments entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments entries
	 * @param end the upper bound of the range of segments entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of segments entries
	 */
	public java.util.List<SegmentsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SegmentsEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the segments entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of segments entries.
	 *
	 * @return the number of segments entries
	 */
	public int countAll();

}