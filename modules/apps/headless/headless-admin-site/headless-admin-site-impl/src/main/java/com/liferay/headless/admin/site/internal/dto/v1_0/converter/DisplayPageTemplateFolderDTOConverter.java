/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.internal.dto.v1_0.converter;

import com.liferay.headless.admin.site.dto.v1_0.DisplayPageTemplateFolder;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bárbara Cabrera
 */
@Component(
	property = "dto.class.name=com.liferay.portal.kernel.model.LayoutPageTemplateCollection",
	service = DTOConverter.class
)
public class DisplayPageTemplateFolderDTOConverter
	implements DTOConverter
		<LayoutPageTemplateCollection, DisplayPageTemplateFolder> {

	@Override
	public String getContentType() {
		return DisplayPageTemplateFolder.class.getSimpleName();
	}

	@Override
	public DisplayPageTemplateFolder toDTO(
			DTOConverterContext dtoConverterContext,
			LayoutPageTemplateCollection layoutPageTemplateCollection)
		throws Exception {

		return new DisplayPageTemplateFolder() {
			{
				setDateCreated(layoutPageTemplateCollection::getCreateDate);
				setDateModified(layoutPageTemplateCollection::getModifiedDate);
				setDescription(layoutPageTemplateCollection::getDescription);
				setExternalReferenceCode(
					layoutPageTemplateCollection::getExternalReferenceCode);
				setKey(
					layoutPageTemplateCollection::
						getLayoutPageTemplateCollectionKey);
				setName(layoutPageTemplateCollection::getName);
				setParentDisplayPageTemplateFolderExternalReferenceCode(
					() -> {
						LayoutPageTemplateCollection
							parentLayoutPageTemplateCollection =
								_layoutPageTemplateCollectionService.
									fetchLayoutPageTemplateCollection(
										layoutPageTemplateCollection.
											getParentLayoutPageTemplateCollectionId());

						if (parentLayoutPageTemplateCollection == null) {
							return null;
						}

						return parentLayoutPageTemplateCollection.
							getExternalReferenceCode();
					});
				setUuid(layoutPageTemplateCollection::getUuid);
			}
		};
	}

	@Reference
	private LayoutPageTemplateCollectionService
		_layoutPageTemplateCollectionService;

}