/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link ImageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImageLocalService
 * @generated
 */
public class ImageLocalServiceWrapper
	implements ImageLocalService, ServiceWrapper<ImageLocalService> {

	public ImageLocalServiceWrapper() {
		this(null);
	}

	public ImageLocalServiceWrapper(ImageLocalService imageLocalService) {
		_imageLocalService = imageLocalService;
	}

	/**
	 * Adds the image to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param image the image
	 * @return the image that was added
	 */
	@Override
	public com.liferay.portal.kernel.model.Image addImage(
		com.liferay.portal.kernel.model.Image image) {

		return _imageLocalService.addImage(image);
	}

	/**
	 * Creates a new image with the primary key. Does not add the image to the database.
	 *
	 * @param imageId the primary key for the new image
	 * @return the new image
	 */
	@Override
	public com.liferay.portal.kernel.model.Image createImage(long imageId) {
		return _imageLocalService.createImage(imageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the image from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param image the image
	 * @return the image that was removed
	 */
	@Override
	public com.liferay.portal.kernel.model.Image deleteImage(
		com.liferay.portal.kernel.model.Image image) {

		return _imageLocalService.deleteImage(image);
	}

	/**
	 * Deletes the image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param imageId the primary key of the image
	 * @return the image that was removed
	 * @throws PortalException if a image with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Image deleteImage(long imageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.deleteImage(imageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _imageLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _imageLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _imageLocalService.dynamicQuery();
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

		return _imageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ImageModelImpl</code>.
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

		return _imageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ImageModelImpl</code>.
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

		return _imageLocalService.dynamicQuery(
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

		return _imageLocalService.dynamicQueryCount(dynamicQuery);
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

		return _imageLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.kernel.model.Image fetchImage(long imageId) {
		return _imageLocalService.fetchImage(imageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _imageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.model.Image getCompanyLogo(long imageId) {
		return _imageLocalService.getCompanyLogo(imageId);
	}

	/**
	 * Returns the image with the primary key.
	 *
	 * @param imageId the primary key of the image
	 * @return the image
	 * @throws PortalException if a image with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.kernel.model.Image getImage(long imageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.getImage(imageId);
	}

	@Override
	public java.io.InputStream getImageInputStream(
			long companyId, long imageId, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.getImageInputStream(companyId, imageId, type);
	}

	@Override
	public com.liferay.portal.kernel.model.Image getImageOrDefault(
		long imageId) {

		return _imageLocalService.getImageOrDefault(imageId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Image> getImages() {
		return _imageLocalService.getImages();
	}

	/**
	 * Returns a range of all the images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ImageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of images
	 * @param end the upper bound of the range of images (not inclusive)
	 * @return the range of images
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Image> getImages(
		int start, int end) {

		return _imageLocalService.getImages(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Image>
		getImagesBySize(int size) {

		return _imageLocalService.getImagesBySize(size);
	}

	/**
	 * Returns the number of images.
	 *
	 * @return the number of images
	 */
	@Override
	public int getImagesCount() {
		return _imageLocalService.getImagesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _imageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _imageLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.Image moveImage(
			long imageId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.moveImage(imageId, bytes);
	}

	/**
	 * Updates the image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param image the image
	 * @return the image that was updated
	 */
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
		com.liferay.portal.kernel.model.Image image) {

		return _imageLocalService.updateImage(image);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #updateImage(long, long, byte[])}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long imageId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(imageId, bytes);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #updateImage(long, long, byte[], String, int, int, int)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long imageId, byte[] bytes, String type, int height, int width,
			int size)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(
			imageId, bytes, type, height, width, size);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #updateImage(long, long, File)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long imageId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(imageId, file);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #updateImage(long, long, InputStream)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long imageId, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(imageId, inputStream);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #updateImage(long, long, InputStream, boolean)}
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long imageId, java.io.InputStream inputStream,
			boolean cleanUpStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(
			imageId, inputStream, cleanUpStream);
	}

	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long companyId, long imageId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(companyId, imageId, bytes);
	}

	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long companyId, long imageId, byte[] bytes, String type, int height,
			int width, int size)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(
			companyId, imageId, bytes, type, height, width, size);
	}

	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long companyId, long imageId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(companyId, imageId, file);
	}

	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long companyId, long imageId, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(companyId, imageId, inputStream);
	}

	@Override
	public com.liferay.portal.kernel.model.Image updateImage(
			long companyId, long imageId, java.io.InputStream inputStream,
			boolean cleanUpStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _imageLocalService.updateImage(
			companyId, imageId, inputStream, cleanUpStream);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _imageLocalService.getBasePersistence();
	}

	@Override
	public ImageLocalService getWrappedService() {
		return _imageLocalService;
	}

	@Override
	public void setWrappedService(ImageLocalService imageLocalService) {
		_imageLocalService = imageLocalService;
	}

	private ImageLocalService _imageLocalService;

}