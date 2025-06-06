/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence;

import com.liferay.dynamic.data.mapping.exception.NoSuchTemplateVersionException;
import com.liferay.dynamic.data.mapping.model.DDMTemplateVersion;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ddm template version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateVersionUtil
 * @generated
 */
@ProviderType
public interface DDMTemplateVersionPersistence
	extends BasePersistence<DDMTemplateVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMTemplateVersionUtil} to access the ddm template version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the ddm template versions where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @return the matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByTemplateId(long templateId);

	/**
	 * Returns a range of all the ddm template versions where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @return the range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByTemplateId(
		long templateId, int start, int end);

	/**
	 * Returns an ordered range of all the ddm template versions where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByTemplateId(
		long templateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm template versions where templateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByTemplateId(
		long templateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template version in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template version
	 * @throws NoSuchTemplateVersionException if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion findByTemplateId_First(
			long templateId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the first ddm template version in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByTemplateId_First(
		long templateId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns the last ddm template version in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template version
	 * @throws NoSuchTemplateVersionException if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion findByTemplateId_Last(
			long templateId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the last ddm template version in the ordered set where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByTemplateId_Last(
		long templateId,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns the ddm template versions before and after the current ddm template version in the ordered set where templateId = &#63;.
	 *
	 * @param templateVersionId the primary key of the current ddm template version
	 * @param templateId the template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template version
	 * @throws NoSuchTemplateVersionException if a ddm template version with the primary key could not be found
	 */
	public DDMTemplateVersion[] findByTemplateId_PrevAndNext(
			long templateVersionId, long templateId,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Removes all the ddm template versions where templateId = &#63; from the database.
	 *
	 * @param templateId the template ID
	 */
	public void removeByTemplateId(long templateId);

	/**
	 * Returns the number of ddm template versions where templateId = &#63;.
	 *
	 * @param templateId the template ID
	 * @return the number of matching ddm template versions
	 */
	public int countByTemplateId(long templateId);

	/**
	 * Returns the ddm template version where templateId = &#63; and version = &#63; or throws a <code>NoSuchTemplateVersionException</code> if it could not be found.
	 *
	 * @param templateId the template ID
	 * @param version the version
	 * @return the matching ddm template version
	 * @throws NoSuchTemplateVersionException if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion findByT_V(long templateId, String version)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the ddm template version where templateId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param templateId the template ID
	 * @param version the version
	 * @return the matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByT_V(long templateId, String version);

	/**
	 * Returns the ddm template version where templateId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param templateId the template ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByT_V(
		long templateId, String version, boolean useFinderCache);

	/**
	 * Removes the ddm template version where templateId = &#63; and version = &#63; from the database.
	 *
	 * @param templateId the template ID
	 * @param version the version
	 * @return the ddm template version that was removed
	 */
	public DDMTemplateVersion removeByT_V(long templateId, String version)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the number of ddm template versions where templateId = &#63; and version = &#63;.
	 *
	 * @param templateId the template ID
	 * @param version the version
	 * @return the number of matching ddm template versions
	 */
	public int countByT_V(long templateId, String version);

	/**
	 * Returns all the ddm template versions where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @return the matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByT_S(
		long templateId, int status);

	/**
	 * Returns a range of all the ddm template versions where templateId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @return the range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByT_S(
		long templateId, int status, int start, int end);

	/**
	 * Returns an ordered range of all the ddm template versions where templateId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByT_S(
		long templateId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm template versions where templateId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findByT_S(
		long templateId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ddm template version in the ordered set where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template version
	 * @throws NoSuchTemplateVersionException if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion findByT_S_First(
			long templateId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the first ddm template version in the ordered set where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByT_S_First(
		long templateId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns the last ddm template version in the ordered set where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template version
	 * @throws NoSuchTemplateVersionException if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion findByT_S_Last(
			long templateId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the last ddm template version in the ordered set where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ddm template version, or <code>null</code> if a matching ddm template version could not be found
	 */
	public DDMTemplateVersion fetchByT_S_Last(
		long templateId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns the ddm template versions before and after the current ddm template version in the ordered set where templateId = &#63; and status = &#63;.
	 *
	 * @param templateVersionId the primary key of the current ddm template version
	 * @param templateId the template ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ddm template version
	 * @throws NoSuchTemplateVersionException if a ddm template version with the primary key could not be found
	 */
	public DDMTemplateVersion[] findByT_S_PrevAndNext(
			long templateVersionId, long templateId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
				orderByComparator)
		throws NoSuchTemplateVersionException;

	/**
	 * Removes all the ddm template versions where templateId = &#63; and status = &#63; from the database.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 */
	public void removeByT_S(long templateId, int status);

	/**
	 * Returns the number of ddm template versions where templateId = &#63; and status = &#63;.
	 *
	 * @param templateId the template ID
	 * @param status the status
	 * @return the number of matching ddm template versions
	 */
	public int countByT_S(long templateId, int status);

	/**
	 * Caches the ddm template version in the entity cache if it is enabled.
	 *
	 * @param ddmTemplateVersion the ddm template version
	 */
	public void cacheResult(DDMTemplateVersion ddmTemplateVersion);

	/**
	 * Caches the ddm template versions in the entity cache if it is enabled.
	 *
	 * @param ddmTemplateVersions the ddm template versions
	 */
	public void cacheResult(
		java.util.List<DDMTemplateVersion> ddmTemplateVersions);

	/**
	 * Creates a new ddm template version with the primary key. Does not add the ddm template version to the database.
	 *
	 * @param templateVersionId the primary key for the new ddm template version
	 * @return the new ddm template version
	 */
	public DDMTemplateVersion create(long templateVersionId);

	/**
	 * Removes the ddm template version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateVersionId the primary key of the ddm template version
	 * @return the ddm template version that was removed
	 * @throws NoSuchTemplateVersionException if a ddm template version with the primary key could not be found
	 */
	public DDMTemplateVersion remove(long templateVersionId)
		throws NoSuchTemplateVersionException;

	public DDMTemplateVersion updateImpl(DDMTemplateVersion ddmTemplateVersion);

	/**
	 * Returns the ddm template version with the primary key or throws a <code>NoSuchTemplateVersionException</code> if it could not be found.
	 *
	 * @param templateVersionId the primary key of the ddm template version
	 * @return the ddm template version
	 * @throws NoSuchTemplateVersionException if a ddm template version with the primary key could not be found
	 */
	public DDMTemplateVersion findByPrimaryKey(long templateVersionId)
		throws NoSuchTemplateVersionException;

	/**
	 * Returns the ddm template version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param templateVersionId the primary key of the ddm template version
	 * @return the ddm template version, or <code>null</code> if a ddm template version with the primary key could not be found
	 */
	public DDMTemplateVersion fetchByPrimaryKey(long templateVersionId);

	/**
	 * Returns all the ddm template versions.
	 *
	 * @return the ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findAll();

	/**
	 * Returns a range of all the ddm template versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @return the range of ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the ddm template versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ddm template versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMTemplateVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm template versions
	 * @param end the upper bound of the range of ddm template versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm template versions
	 */
	public java.util.List<DDMTemplateVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDMTemplateVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddm template versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ddm template versions.
	 *
	 * @return the number of ddm template versions
	 */
	public int countAll();

}