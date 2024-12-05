/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductSpecificationUtil {

	public static CPDefinitionSpecificationOptionValue
			addCPDefinitionSpecificationOptionValue(
				CPDefinitionSpecificationOptionValueService
					cpDefinitionSpecificationOptionValueService,
				CPOptionCategoryService cpOptionCategoryService,
				CPSpecificationOptionService cpSpecificationOptionService,
				long cpDefinitionId, ProductSpecification productSpecification,
				ServiceContext serviceContext)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			cpSpecificationOptionService.fetchCPSpecificationOption(
				serviceContext.getCompanyId(),
				FriendlyURLNormalizerUtil.normalize(
					productSpecification.getSpecificationKey()));

		if (cpSpecificationOption == null) {
			cpSpecificationOption =
				cpSpecificationOptionService.
					fetchCPSpecificationOptionByExternalReferenceCode(
						GetterUtil.getString(
							productSpecification.
								getSpecificationExternalReferenceCode()),
						serviceContext.getCompanyId());
		}

		return cpDefinitionSpecificationOptionValueService.
			addCPDefinitionSpecificationOptionValue(
				GetterUtil.getString(
					productSpecification.getExternalReferenceCode()),
				cpDefinitionId,
				_getCPSpecificationOptionId(
					cpOptionCategoryService, cpSpecificationOption,
					cpSpecificationOptionService, productSpecification,
					serviceContext),
				_getCPOptionCategoryId(
					cpOptionCategoryService, cpSpecificationOption,
					productSpecification, serviceContext),
				GetterUtil.get(productSpecification.getPriority(), 0D),
				LanguageUtils.getLocalizedMap(productSpecification.getValue()),
				serviceContext);
	}

	public static CPDefinitionSpecificationOptionValue
			updateCPDefinitionSpecificationOptionValue(
				CPDefinitionSpecificationOptionValueService
					cpDefinitionSpecificationOptionValueService,
				CPDefinitionSpecificationOptionValue
					cpDefinitionSpecificationOptionValue,
				CPOptionCategoryService cpOptionCategoryService,
				CPSpecificationOptionService cpSpecificationOptionService,
				ProductSpecification productSpecification,
				ServiceContext serviceContext)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			cpSpecificationOptionService.fetchCPSpecificationOption(
				serviceContext.getCompanyId(),
				FriendlyURLNormalizerUtil.normalize(
					productSpecification.getSpecificationKey()));

		return cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				GetterUtil.getString(
					productSpecification.getExternalReferenceCode(),
					cpDefinitionSpecificationOptionValue.
						getExternalReferenceCode()),
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId(),
				_getCPOptionCategoryId(
					cpOptionCategoryService, cpSpecificationOption,
					productSpecification, serviceContext),
				GetterUtil.getString(
					productSpecification.getKey(),
					cpDefinitionSpecificationOptionValue.getKey()),
				GetterUtil.get(
					productSpecification.getPriority(),
					cpDefinitionSpecificationOptionValue.getPriority()),
				LanguageUtils.getLocalizedMap(productSpecification.getValue()),
				serviceContext);
	}

	private static long _getCPOptionCategoryId(
			CPOptionCategoryService cpOptionCategoryService,
			CPSpecificationOption cpSpecificationOption,
			ProductSpecification productSpecification,
			ServiceContext serviceContext)
		throws PortalException {

		CPOptionCategory cpOptionCategory =
			cpOptionCategoryService.
				fetchCPOptionCategoryByExternalReferenceCode(
					GetterUtil.getString(
						productSpecification.
							getOptionCategoryExternalReferenceCode()),
					serviceContext.getCompanyId());

		if (cpOptionCategory != null) {
			return cpOptionCategory.getCPOptionCategoryId();
		}

		cpOptionCategory = cpOptionCategoryService.fetchCPOptionCategory(
			GetterUtil.getLong(productSpecification.getOptionCategoryId()));

		if (cpOptionCategory != null) {
			return cpOptionCategory.getCPOptionCategoryId();
		}

		if (cpSpecificationOption != null) {
			return cpSpecificationOption.getCPOptionCategoryId();
		}

		return 0;
	}

	private static long _getCPSpecificationOptionId(
			CPOptionCategoryService cpOptionCategoryService,
			CPSpecificationOption cpSpecificationOption,
			CPSpecificationOptionService cpSpecificationOptionService,
			ProductSpecification productSpecification,
			ServiceContext serviceContext)
		throws PortalException {

		if (cpSpecificationOption == null) {
			cpSpecificationOption =
				cpSpecificationOptionService.addCPSpecificationOption(
					GetterUtil.getString(
						productSpecification.
							getSpecificationExternalReferenceCode()),
					_getCPOptionCategoryId(
						cpOptionCategoryService, cpSpecificationOption,
						productSpecification, serviceContext),
					null,
					LanguageUtils.getLocalizedMap(
						productSpecification.getLabel()),
					LanguageUtils.getLocalizedMap(
						productSpecification.getLabel()),
					false, productSpecification.getSpecificationKey(),
					GetterUtil.getDouble(
						productSpecification.getSpecificationPriority()),
					serviceContext);
		}

		return cpSpecificationOption.getCPSpecificationOptionId();
	}

}