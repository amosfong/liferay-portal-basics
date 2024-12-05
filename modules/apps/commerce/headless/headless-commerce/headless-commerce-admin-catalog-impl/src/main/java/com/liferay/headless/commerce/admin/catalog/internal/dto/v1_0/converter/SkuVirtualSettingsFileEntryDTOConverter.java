/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.account.constants.AccountConstants;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.type.virtual.model.CPDVirtualSettingFileEntry;
import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDVirtualSettingFileEntryLocalService;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SkuVirtualSettingsFileEntry;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	property = "dto.class.name=com.liferay.commerce.product.type.virtual.model.CPDVirtualSettingFileEntry",
	service = DTOConverter.class
)
public class SkuVirtualSettingsFileEntryDTOConverter
	implements DTOConverter
		<CPDVirtualSettingFileEntry, SkuVirtualSettingsFileEntry> {

	@Override
	public String getContentType() {
		return SkuVirtualSettingsFileEntry.class.getSimpleName();
	}

	@Override
	public SkuVirtualSettingsFileEntry toDTO(
			DTOConverterContext dtoConverterContext)
		throws Exception {

		CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry =
			_cpdVirtualSettingFileEntryLocalService.
				getCPDVirtualSettingFileEntry(
					(Long)dtoConverterContext.getId());

		return new SkuVirtualSettingsFileEntry() {
			{
				setActions(dtoConverterContext::getActions);
				setId(
					cpdVirtualSettingFileEntry::
						getCPDefinitionVirtualSettingFileEntryId);
				setSrc(
					() -> {
						long fileEntryId =
							cpdVirtualSettingFileEntry.getFileEntryId();

						if (fileEntryId == 0) {
							return null;
						}

						CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
							_cpDefinitionVirtualSettingLocalService.
								getCPDefinitionVirtualSetting(
									cpdVirtualSettingFileEntry.
										getCPDefinitionVirtualSettingId());

						return _commerceMediaResolver.
							getDownloadVirtualProductURL(
								CPInstance.class.getName(),
								cpDefinitionVirtualSetting.getClassPK(),
								AccountConstants.ACCOUNT_ENTRY_ID_ADMIN,
								fileEntryId);
					});
				setUrl(
					() -> {
						if (Validator.isBlank(
								cpdVirtualSettingFileEntry.getUrl())) {

							return null;
						}

						return cpdVirtualSettingFileEntry.getUrl();
					});
				setVersion(
					() -> {
						if (Validator.isBlank(
								cpdVirtualSettingFileEntry.getVersion())) {

							return null;
						}

						return cpdVirtualSettingFileEntry.getVersion();
					});
			}
		};
	}

	@Reference
	private CommerceMediaResolver _commerceMediaResolver;

	@Reference
	private CPDefinitionVirtualSettingLocalService
		_cpDefinitionVirtualSettingLocalService;

	@Reference
	private CPDVirtualSettingFileEntryLocalService
		_cpdVirtualSettingFileEntryLocalService;

}