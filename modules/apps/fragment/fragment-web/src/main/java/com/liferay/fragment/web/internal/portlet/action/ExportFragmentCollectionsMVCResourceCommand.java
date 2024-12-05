/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.service.FragmentCollectionService;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactory;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/export_fragment_collections"
	},
	service = MVCResourceCommand.class
)
public class ExportFragmentCollectionsMVCResourceCommand
	implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		long[] exportFragmentCollectionIds = null;

		long fragmentCollectionId = ParamUtil.getLong(
			resourceRequest, "fragmentCollectionId");

		if (fragmentCollectionId > 0) {
			exportFragmentCollectionIds = new long[] {fragmentCollectionId};
		}
		else {
			exportFragmentCollectionIds = ParamUtil.getLongValues(
				resourceRequest, "rowIds");
		}

		try {
			List<FragmentCollection> fragmentCollections = new ArrayList<>();

			for (long exportFragmentCollectionId :
					exportFragmentCollectionIds) {

				fragmentCollections.add(
					_fragmentCollectionService.fetchFragmentCollection(
						exportFragmentCollectionId));
			}

			ZipWriter zipWriter = _zipWriterFactory.getZipWriter();

			for (FragmentCollection fragmentCollection : fragmentCollections) {
				fragmentCollection.populateZipWriter(zipWriter);
			}

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse,
				"collections-" + Time.getTimestamp() + ".zip",
				new FileInputStream(zipWriter.getFile()),
				ContentTypes.APPLICATION_ZIP);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}

		return false;
	}

	@Reference
	private FragmentCollectionService _fragmentCollectionService;

	@Reference
	private ZipWriterFactory _zipWriterFactory;

}