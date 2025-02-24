/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.test.util.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.test.util.internal.exportimport.staged.model.repository.DummyStagedModelRepository;
import com.liferay.exportimport.test.util.model.Dummy;
import com.liferay.exportimport.test.util.model.DummyFolder;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(service = StagedModelDataHandler.class)
public class DummyFolderStagedModelDataHandler
	extends BaseStagedModelDataHandler<DummyFolder> {

	public static final String[] CLASS_NAMES = {DummyFolder.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public boolean isEnabled(long companyId) {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, DummyFolder dummyFolder)
		throws Exception {

		Element dummyFolderElement = portletDataContext.getExportDataElement(
			dummyFolder);

		DummyStagedModelRepository dummyStagedModelRepository =
			(DummyStagedModelRepository)_dummyStagedModelRepository;

		List<Dummy> dummies = dummyStagedModelRepository.fetchDummiesByFolderId(
			dummyFolder.getId());

		for (Dummy dummy : dummies) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, dummyFolder, dummy,
				PortletDataContext.REFERENCE_TYPE_STRONG);
		}

		portletDataContext.addClassedModel(
			dummyFolderElement, ExportImportPathUtil.getModelPath(dummyFolder),
			dummyFolder);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, DummyFolder dummyFolder)
		throws Exception {

		DummyFolder importedDummyFolder = (DummyFolder)dummyFolder.clone();

		importedDummyFolder.setGroupId(portletDataContext.getScopeGroupId());

		DummyFolder existingDummyFolder =
			_dummyFolderStagedModelRepository.fetchStagedModelByUuidAndGroupId(
				importedDummyFolder.getUuid(),
				portletDataContext.getScopeGroupId());

		if ((existingDummyFolder == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			_dummyFolderStagedModelRepository.addStagedModel(
				portletDataContext, importedDummyFolder);
		}
		else {
			importedDummyFolder.setId(existingDummyFolder.getId());

			_dummyFolderStagedModelRepository.updateStagedModel(
				portletDataContext, importedDummyFolder);
		}
	}

	@Override
	protected StagedModelRepository<DummyFolder> getStagedModelRepository() {
		return _dummyFolderStagedModelRepository;
	}

	@Reference(
		target = "(model.class.name=com.liferay.exportimport.test.util.model.DummyFolder)"
	)
	private StagedModelRepository<DummyFolder>
		_dummyFolderStagedModelRepository;

	@Reference(
		target = "(model.class.name=com.liferay.exportimport.test.util.model.Dummy)"
	)
	private StagedModelRepository<Dummy> _dummyStagedModelRepository;

	private boolean _enabled = true;

}