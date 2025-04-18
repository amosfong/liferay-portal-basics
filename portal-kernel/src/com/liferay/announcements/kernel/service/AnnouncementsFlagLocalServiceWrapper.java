/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.announcements.kernel.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link AnnouncementsFlagLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsFlagLocalService
 * @generated
 */
public class AnnouncementsFlagLocalServiceWrapper
	implements AnnouncementsFlagLocalService,
			   ServiceWrapper<AnnouncementsFlagLocalService> {

	public AnnouncementsFlagLocalServiceWrapper() {
		this(null);
	}

	public AnnouncementsFlagLocalServiceWrapper(
		AnnouncementsFlagLocalService announcementsFlagLocalService) {

		_announcementsFlagLocalService = announcementsFlagLocalService;
	}

	/**
	 * Adds the announcements flag to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsFlagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was added
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
		addAnnouncementsFlag(
			com.liferay.announcements.kernel.model.AnnouncementsFlag
				announcementsFlag) {

		return _announcementsFlagLocalService.addAnnouncementsFlag(
			announcementsFlag);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag addFlag(
		long userId, long entryId, int value) {

		return _announcementsFlagLocalService.addFlag(userId, entryId, value);
	}

	/**
	 * Creates a new announcements flag with the primary key. Does not add the announcements flag to the database.
	 *
	 * @param flagId the primary key for the new announcements flag
	 * @return the new announcements flag
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
		createAnnouncementsFlag(long flagId) {

		return _announcementsFlagLocalService.createAnnouncementsFlag(flagId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the announcements flag from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsFlagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was removed
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
		deleteAnnouncementsFlag(
			com.liferay.announcements.kernel.model.AnnouncementsFlag
				announcementsFlag) {

		return _announcementsFlagLocalService.deleteAnnouncementsFlag(
			announcementsFlag);
	}

	/**
	 * Deletes the announcements flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsFlagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag that was removed
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
			deleteAnnouncementsFlag(long flagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.deleteAnnouncementsFlag(flagId);
	}

	@Override
	public void deleteFlag(
		com.liferay.announcements.kernel.model.AnnouncementsFlag flag) {

		_announcementsFlagLocalService.deleteFlag(flag);
	}

	@Override
	public void deleteFlag(long flagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_announcementsFlagLocalService.deleteFlag(flagId);
	}

	@Override
	public void deleteFlags(long entryId) {
		_announcementsFlagLocalService.deleteFlags(entryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _announcementsFlagLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _announcementsFlagLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _announcementsFlagLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _announcementsFlagLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _announcementsFlagLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _announcementsFlagLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _announcementsFlagLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _announcementsFlagLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
		fetchAnnouncementsFlag(long flagId) {

		return _announcementsFlagLocalService.fetchAnnouncementsFlag(flagId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _announcementsFlagLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the announcements flag with the primary key.
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
			getAnnouncementsFlag(long flagId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.getAnnouncementsFlag(flagId);
	}

	/**
	 * Returns a range of all the announcements flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of announcements flags
	 * @param end the upper bound of the range of announcements flags (not inclusive)
	 * @return the range of announcements flags
	 */
	@Override
	public java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsFlag>
			getAnnouncementsFlags(int start, int end) {

		return _announcementsFlagLocalService.getAnnouncementsFlags(start, end);
	}

	/**
	 * Returns the number of announcements flags.
	 *
	 * @return the number of announcements flags
	 */
	@Override
	public int getAnnouncementsFlagsCount() {
		return _announcementsFlagLocalService.getAnnouncementsFlagsCount();
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag getFlag(
			long userId, long entryId, int value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.getFlag(userId, entryId, value);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _announcementsFlagLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _announcementsFlagLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _announcementsFlagLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the announcements flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsFlagLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was updated
	 */
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsFlag
		updateAnnouncementsFlag(
			com.liferay.announcements.kernel.model.AnnouncementsFlag
				announcementsFlag) {

		return _announcementsFlagLocalService.updateAnnouncementsFlag(
			announcementsFlag);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _announcementsFlagLocalService.getBasePersistence();
	}

	@Override
	public AnnouncementsFlagLocalService getWrappedService() {
		return _announcementsFlagLocalService;
	}

	@Override
	public void setWrappedService(
		AnnouncementsFlagLocalService announcementsFlagLocalService) {

		_announcementsFlagLocalService = announcementsFlagLocalService;
	}

	private AnnouncementsFlagLocalService _announcementsFlagLocalService;

}