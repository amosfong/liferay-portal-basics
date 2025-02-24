/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.info.item.provider;

import com.liferay.asset.categories.admin.web.internal.info.item.AssetCategoryInfoItemFields;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.layout.page.template.info.item.provider.DisplayPageInfoItemFieldSetProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = InfoItemFieldValuesProvider.class)
public class AssetCategoryInfoItemFieldValuesProvider
	implements InfoItemFieldValuesProvider<AssetCategory> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(
		AssetCategory assetCategory) {

		try {
			return InfoItemFieldValues.builder(
			).infoFieldValues(
				_getAssetCategoryInfoFieldValues(assetCategory)
			).infoFieldValues(
				_displayPageInfoItemFieldSetProvider.getInfoFieldValues(
					new InfoItemReference(
						AssetCategory.class.getName(),
						assetCategory.getCategoryId()),
					StringPool.BLANK, AssetCategory.class.getSimpleName(),
					assetCategory, _getThemeDisplay())
			).infoFieldValues(
				_infoItemFieldReaderFieldSetProvider.getInfoFieldValues(
					AssetCategory.class.getName(), assetCategory)
			).infoFieldValues(
				_templateInfoItemFieldSetProvider.getInfoFieldValues(
					AssetCategory.class.getName(), assetCategory)
			).infoItemReference(
				new InfoItemReference(
					AssetCategory.class.getName(),
					assetCategory.getCategoryId())
			).build();
		}
		catch (Exception exception) {
			throw new RuntimeException("Unexpected exception", exception);
		}
	}

	private Map<Locale, String> _getAssetCategoryDescriptionMap(
		AssetCategory assetCategory) {

		Map<Locale, String> descriptionMap = assetCategory.getDescriptionMap();

		descriptionMap.putIfAbsent(
			LocaleUtil.fromLanguageId(assetCategory.getDefaultLanguageId()),
			StringPool.BLANK);

		return descriptionMap;
	}

	private List<InfoFieldValue<Object>> _getAssetCategoryInfoFieldValues(
		AssetCategory assetCategory) {

		List<InfoFieldValue<Object>> assetCategoryInfoFieldValues =
			new ArrayList<>();

		assetCategoryInfoFieldValues.add(
			new InfoFieldValue<>(
				AssetCategoryInfoItemFields.nameInfoField,
				InfoLocalizedValue.<String>builder(
				).defaultLocale(
					LocaleUtil.fromLanguageId(
						assetCategory.getDefaultLanguageId())
				).values(
					assetCategory.getTitleMap()
				).build()));
		assetCategoryInfoFieldValues.add(
			new InfoFieldValue<>(
				AssetCategoryInfoItemFields.descriptionInfoField,
				InfoLocalizedValue.<String>builder(
				).defaultLocale(
					LocaleUtil.fromLanguageId(
						assetCategory.getDefaultLanguageId())
				).values(
					_getAssetCategoryDescriptionMap(assetCategory)
				).build()));

		AssetVocabulary assetVocabulary =
			_assetVocabularyLocalService.fetchAssetVocabulary(
				assetCategory.getVocabularyId());

		if (assetVocabulary != null) {
			assetCategoryInfoFieldValues.add(
				new InfoFieldValue<>(
					AssetCategoryInfoItemFields.vocabularyInfoField,
					InfoLocalizedValue.<String>builder(
					).defaultLocale(
						LocaleUtil.fromLanguageId(
							assetVocabulary.getDefaultLanguageId())
					).values(
						assetVocabulary.getTitleMap()
					).build()));
		}

		return assetCategoryInfoFieldValues;
	}

	private ThemeDisplay _getThemeDisplay() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext != null) {
			return serviceContext.getThemeDisplay();
		}

		return null;
	}

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private DisplayPageInfoItemFieldSetProvider
		_displayPageInfoItemFieldSetProvider;

	@Reference
	private InfoItemFieldReaderFieldSetProvider
		_infoItemFieldReaderFieldSetProvider;

	@Reference
	private TemplateInfoItemFieldSetProvider _templateInfoItemFieldSetProvider;

}