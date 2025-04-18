/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExportImportService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportService
 * @generated
 */
public class ExportImportServiceWrapper
	implements ExportImportService, ServiceWrapper<ExportImportService> {

	public ExportImportServiceWrapper() {
		this(null);
	}

	public ExportImportServiceWrapper(ExportImportService exportImportService) {
		_exportImportService = exportImportService;
	}

	@Override
	public java.io.File exportLayoutsAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.exportLayoutsAsFile(
			exportImportConfiguration);
	}

	@Override
	public long exportLayoutsAsFileInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.exportLayoutsAsFileInBackground(
			exportImportConfiguration);
	}

	@Override
	public long exportLayoutsAsFileInBackground(
			long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.exportLayoutsAsFileInBackground(
			exportImportConfigurationId);
	}

	@Override
	public java.io.File exportPortletInfoAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.exportPortletInfoAsFile(
			exportImportConfiguration);
	}

	@Override
	public long exportPortletInfoAsFileInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.exportPortletInfoAsFileInBackground(
			exportImportConfiguration);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _exportImportService.getOSGiServiceIdentifier();
	}

	@Override
	public void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		_exportImportService.importLayouts(exportImportConfiguration, file);
	}

	@Override
	public void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		_exportImportService.importLayouts(
			exportImportConfiguration, inputStream);
	}

	@Override
	public long importLayoutsInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.importLayoutsInBackground(
			exportImportConfiguration, file);
	}

	@Override
	public long importLayoutsInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.importLayoutsInBackground(
			exportImportConfiguration, inputStream);
	}

	@Override
	public void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		_exportImportService.importPortletInfo(exportImportConfiguration, file);
	}

	@Override
	public void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		_exportImportService.importPortletInfo(
			exportImportConfiguration, inputStream);
	}

	@Override
	public long importPortletInfoInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.importPortletInfoInBackground(
			exportImportConfiguration, file);
	}

	@Override
	public long importPortletInfoInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.importPortletInfoInBackground(
			exportImportConfiguration, inputStream);
	}

	@Override
	public com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.validateImportLayoutsFile(
			exportImportConfiguration, file);
	}

	@Override
	public com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.validateImportLayoutsFile(
			exportImportConfiguration, inputStream);
	}

	@Override
	public com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.validateImportPortletInfo(
			exportImportConfiguration, file);
	}

	@Override
	public com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _exportImportService.validateImportPortletInfo(
			exportImportConfiguration, inputStream);
	}

	@Override
	public ExportImportService getWrappedService() {
		return _exportImportService;
	}

	@Override
	public void setWrappedService(ExportImportService exportImportService) {
		_exportImportService = exportImportService;
	}

	private ExportImportService _exportImportService;

}