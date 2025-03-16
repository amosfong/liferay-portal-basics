/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FragmentEntryLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLinkService
 * @generated
 */
public class FragmentEntryLinkServiceWrapper
	implements FragmentEntryLinkService,
			   ServiceWrapper<FragmentEntryLinkService> {

	public FragmentEntryLinkServiceWrapper() {
		this(null);
	}

	public FragmentEntryLinkServiceWrapper(
		FragmentEntryLinkService fragmentEntryLinkService) {

		_fragmentEntryLinkService = fragmentEntryLinkService;
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink addFragmentEntryLink(
			String externalReferenceCode, long groupId,
			long originalFragmentEntryLinkId, long fragmentEntryId,
			long segmentsExperienceId, long plid, String css, String html,
			String js, String configuration, String editableValues,
			String namespace, int position, String rendererKey, int type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.addFragmentEntryLink(
			externalReferenceCode, groupId, originalFragmentEntryLinkId,
			fragmentEntryId, segmentsExperienceId, plid, css, html, js,
			configuration, editableValues, namespace, position, rendererKey,
			type, serviceContext);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink deleteFragmentEntryLink(
			long fragmentEntryLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.deleteFragmentEntryLink(
			fragmentEntryLinkId);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink deleteFragmentEntryLink(
			String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.deleteFragmentEntryLink(
			externalReferenceCode, groupId);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink
			getFragmentEntryLinkByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.
			getFragmentEntryLinkByExternalReferenceCode(
				externalReferenceCode, groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fragmentEntryLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink updateDeleted(
			long fragmentEntryLinkId, boolean deleted)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.updateDeleted(
			fragmentEntryLinkId, deleted);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.updateFragmentEntryLink(
			fragmentEntryLinkId, editableValues);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues,
			boolean updateClassedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.updateFragmentEntryLink(
			fragmentEntryLinkId, editableValues, updateClassedModel);
	}

	@Override
	public FragmentEntryLinkService getWrappedService() {
		return _fragmentEntryLinkService;
	}

	@Override
	public void setWrappedService(
		FragmentEntryLinkService fragmentEntryLinkService) {

		_fragmentEntryLinkService = fragmentEntryLinkService;
	}

	private FragmentEntryLinkService _fragmentEntryLinkService;

}