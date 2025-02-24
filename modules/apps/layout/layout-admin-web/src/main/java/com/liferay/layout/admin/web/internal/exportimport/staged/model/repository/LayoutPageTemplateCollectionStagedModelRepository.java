/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "model.class.name=com.liferay.layout.page.template.model.LayoutPageTemplateCollection",
	service = StagedModelRepository.class
)
public class LayoutPageTemplateCollectionStagedModelRepository
	implements StagedModelRepository<LayoutPageTemplateCollection> {

	@Override
	public LayoutPageTemplateCollection addStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			layoutPageTemplateCollection.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutPageTemplateCollection);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(layoutPageTemplateCollection.getUuid());
		}

		return _layoutPageTemplateCollectionLocalService.
			addLayoutPageTemplateCollection(
				layoutPageTemplateCollection.getExternalReferenceCode(), userId,
				serviceContext.getScopeGroupId(),
				layoutPageTemplateCollection.
					getParentLayoutPageTemplateCollectionId(),
				layoutPageTemplateCollection.getName(),
				layoutPageTemplateCollection.getDescription(),
				layoutPageTemplateCollection.getType(), serviceContext);
	}

	@Override
	public void deleteStagedModel(
			LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws PortalException {

		_layoutPageTemplateCollectionLocalService.
			deleteLayoutPageTemplateCollection(layoutPageTemplateCollection);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (layoutPageTemplateCollection != null) {
			deleteStagedModel(layoutPageTemplateCollection);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public LayoutPageTemplateCollection fetchMissingReference(
		String uuid, long groupId) {

		return (LayoutPageTemplateCollection)
			_stagedModelRepositoryHelper.fetchMissingReference(
				uuid, groupId, this);
	}

	@Override
	public LayoutPageTemplateCollection fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _layoutPageTemplateCollectionLocalService.
			fetchLayoutPageTemplateCollectionByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<LayoutPageTemplateCollection>
		fetchStagedModelsByUuidAndCompanyId(String uuid, long companyId) {

		return _layoutPageTemplateCollectionLocalService.
			getLayoutPageTemplateCollectionsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _layoutPageTemplateCollectionLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public LayoutPageTemplateCollection getStagedModel(long classPK)
		throws PortalException {

		return _layoutPageTemplateCollectionLocalService.
			getLayoutPageTemplateCollection(classPK);
	}

	@Override
	public LayoutPageTemplateCollection saveStagedModel(
			LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws PortalException {

		return _layoutPageTemplateCollectionLocalService.
			updateLayoutPageTemplateCollection(layoutPageTemplateCollection);
	}

	@Override
	public LayoutPageTemplateCollection updateStagedModel(
			PortletDataContext portletDataContext,
			LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws PortalException {

		return _layoutPageTemplateCollectionLocalService.
			updateLayoutPageTemplateCollection(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				layoutPageTemplateCollection.getName(),
				layoutPageTemplateCollection.getDescription());
	}

	@Reference
	private LayoutPageTemplateCollectionLocalService
		_layoutPageTemplateCollectionLocalService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

}