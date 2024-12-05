/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.channel.internal.dto.v1_0.converter;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.headless.commerce.admin.channel.dto.v1_0.DefaultProductDisplayPage;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.settings.FallbackKeysSettingsUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Danny Situ
 */
@Component(
	property = "dto.class.name=com.liferay.headless.commerce.admin.channel.dto.v1_0.DefaultProductDisplayPage",
	service = DTOConverter.class
)
public class DefaultProductDisplayPageDTOConverter
	implements DTOConverter<ModifiableSettings, DefaultProductDisplayPage> {

	@Override
	public String getContentType() {
		return DefaultProductDisplayPage.class.getSimpleName();
	}

	@Override
	public DefaultProductDisplayPage toDTO(
			DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannel(
				(long)dtoConverterContext.getId());

		Settings settings = FallbackKeysSettingsUtil.getSettings(
			new GroupServiceSettingsLocator(
				commerceChannel.getGroupId(),
				CPConstants.RESOURCE_NAME_CP_DISPLAY_LAYOUT));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		return new DefaultProductDisplayPage() {
			{
				setActions(dtoConverterContext::getActions);
				setPageUuid(
					() -> modifiableSettings.getValue(
						"productLayoutUuid", StringPool.BLANK));
			}
		};
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

}