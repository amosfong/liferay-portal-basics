/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FragmentCompositionService}.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentCompositionService
 * @generated
 */
public class FragmentCompositionServiceWrapper
	implements FragmentCompositionService,
			   ServiceWrapper<FragmentCompositionService> {

	public FragmentCompositionServiceWrapper() {
		this(null);
	}

	public FragmentCompositionServiceWrapper(
		FragmentCompositionService fragmentCompositionService) {

		_fragmentCompositionService = fragmentCompositionService;
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			addFragmentComposition(
				String externalReferenceCode, long groupId,
				long fragmentCollectionId, String fragmentCompositionKey,
				String name, String description, String data,
				long previewFileEntryId, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.addFragmentComposition(
			externalReferenceCode, groupId, fragmentCollectionId,
			fragmentCompositionKey, name, description, data, previewFileEntryId,
			status, serviceContext);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			deleteFragmentComposition(long fragmentCompositionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.deleteFragmentComposition(
			fragmentCompositionId);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			deleteFragmentComposition(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.deleteFragmentComposition(
			externalReferenceCode, groupId);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
		fetchFragmentComposition(long fragmentCompositionId) {

		return _fragmentCompositionService.fetchFragmentComposition(
			fragmentCompositionId);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
		fetchFragmentComposition(long groupId, String fragmentCompositionKey) {

		return _fragmentCompositionService.fetchFragmentComposition(
			groupId, fragmentCompositionKey);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			getFragmentCompositionByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.
			getFragmentCompositionByExternalReferenceCode(
				externalReferenceCode, groupId);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentComposition>
		getFragmentCompositions(long fragmentCollectionId) {

		return _fragmentCompositionService.getFragmentCompositions(
			fragmentCollectionId);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentComposition>
		getFragmentCompositions(long fragmentCollectionId, int start, int end) {

		return _fragmentCompositionService.getFragmentCompositions(
			fragmentCollectionId, start, end);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentComposition>
		getFragmentCompositions(
			long groupId, long fragmentCollectionId, int status) {

		return _fragmentCompositionService.getFragmentCompositions(
			groupId, fragmentCollectionId, status);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentComposition>
		getFragmentCompositions(
			long groupId, long fragmentCollectionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.fragment.model.FragmentComposition>
					orderByComparator) {

		return _fragmentCompositionService.getFragmentCompositions(
			groupId, fragmentCollectionId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.fragment.model.FragmentComposition>
		getFragmentCompositions(
			long groupId, long fragmentCollectionId, String name, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.fragment.model.FragmentComposition>
					orderByComparator) {

		return _fragmentCompositionService.getFragmentCompositions(
			groupId, fragmentCollectionId, name, start, end, orderByComparator);
	}

	@Override
	public int getFragmentCompositionsCount(long fragmentCollectionId) {
		return _fragmentCompositionService.getFragmentCompositionsCount(
			fragmentCollectionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fragmentCompositionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			moveFragmentComposition(
				long fragmentCompositionId, long fragmentCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.moveFragmentComposition(
			fragmentCompositionId, fragmentCollectionId);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			updateFragmentComposition(
				long fragmentCompositionId, long previewFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.updateFragmentComposition(
			fragmentCompositionId, previewFileEntryId);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			updateFragmentComposition(
				long fragmentCompositionId, long fragmentCollectionId,
				String name, String description, String data,
				long previewFileEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.updateFragmentComposition(
			fragmentCompositionId, fragmentCollectionId, name, description,
			data, previewFileEntryId, status);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			updateFragmentComposition(long fragmentCompositionId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.updateFragmentComposition(
			fragmentCompositionId, name);
	}

	@Override
	public com.liferay.fragment.model.FragmentComposition
			updateFragmentComposition(
				long fragmentCompositionId, String name, String description,
				String data, long previewFileEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentCompositionService.updateFragmentComposition(
			fragmentCompositionId, name, description, data, previewFileEntryId,
			status);
	}

	@Override
	public FragmentCompositionService getWrappedService() {
		return _fragmentCompositionService;
	}

	@Override
	public void setWrappedService(
		FragmentCompositionService fragmentCompositionService) {

		_fragmentCompositionService = fragmentCompositionService;
	}

	private FragmentCompositionService _fragmentCompositionService;

}