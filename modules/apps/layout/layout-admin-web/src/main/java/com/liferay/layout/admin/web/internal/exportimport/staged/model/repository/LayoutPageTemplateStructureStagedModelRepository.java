/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "model.class.name=com.liferay.layout.page.template.model.LayoutPageTemplateStructure",
	service = StagedModelRepository.class
)
public class LayoutPageTemplateStructureStagedModelRepository
	implements StagedModelRepository<LayoutPageTemplateStructure> {

	@Override
	public LayoutPageTemplateStructure addStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			layoutPageTemplateStructure.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutPageTemplateStructure);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(layoutPageTemplateStructure.getUuid());
		}

		return _layoutPageTemplateStructureLocalService.
			addLayoutPageTemplateStructure(
				userId, serviceContext.getScopeGroupId(),
				layoutPageTemplateStructure.getPlid(), 0,
				null, serviceContext);
	}

	@Override
	public void deleteStagedModel(
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws PortalException {

		_layoutPageTemplateStructureLocalService.
			deleteLayoutPageTemplateStructure(layoutPageTemplateStructure);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (layoutPageTemplateStructure != null) {
			deleteStagedModel(layoutPageTemplateStructure);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public LayoutPageTemplateStructure fetchMissingReference(
		String uuid, long groupId) {

		return (LayoutPageTemplateStructure)
			_stagedModelRepositoryHelper.fetchMissingReference(
				uuid, groupId, this);
	}

	@Override
	public LayoutPageTemplateStructure fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _layoutPageTemplateStructureLocalService.
			fetchLayoutPageTemplateStructureByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<LayoutPageTemplateStructure>
		fetchStagedModelsByUuidAndCompanyId(String uuid, long companyId) {

		return _layoutPageTemplateStructureLocalService.
			getLayoutPageTemplateStructuresByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _layoutPageTemplateStructureLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public LayoutPageTemplateStructure getStagedModel(long id)
		throws PortalException {

		return _layoutPageTemplateStructureLocalService.
			getLayoutPageTemplateStructure(id);
	}

	@Override
	public LayoutPageTemplateStructure saveStagedModel(
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws PortalException {

		return _layoutPageTemplateStructureLocalService.
			updateLayoutPageTemplateStructure(layoutPageTemplateStructure);
	}

	@Override
	public LayoutPageTemplateStructure updateStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws PortalException {

		return _layoutPageTemplateStructureLocalService.
			updateLayoutPageTemplateStructure(layoutPageTemplateStructure);
	}

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

}