/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductShippingConfigurationUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductShippingConfigurationResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-shipping-configuration.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = ProductShippingConfigurationResource.class
)
@CTAware
public class ProductShippingConfigurationResourceImpl
	extends BaseProductShippingConfigurationResourceImpl {

	@Override
	public ProductShippingConfiguration
			getProductByExternalReferenceCodeShippingConfiguration(
				String externalReferenceCode)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find product with external reference code " +
					externalReferenceCode);
		}

		return _toProductShippingConfiguration(
			cpDefinition.getCPDefinitionId());
	}

	@NestedField(parentClass = Product.class, value = "shippingConfiguration")
	@Override
	public ProductShippingConfiguration getProductIdShippingConfiguration(
			@NestedFieldId(value = "productId") Long id)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find product with ID " + id);
		}

		return _toProductShippingConfiguration(
			cpDefinition.getCPDefinitionId());
	}

	@Override
	public Response patchProductByExternalReferenceCodeShippingConfiguration(
			String externalReferenceCode,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find product with external reference code " +
					externalReferenceCode);
		}

		_updateProductShippingConfiguration(
			cpDefinition, productShippingConfiguration);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchProductIdShippingConfiguration(
			Long id, ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find product with ID " + id);
		}

		_updateProductShippingConfiguration(
			cpDefinition, productShippingConfiguration);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	private ProductShippingConfiguration _toProductShippingConfiguration(
			Long cpDefinitionId)
		throws Exception {

		return _productShippingConfigurationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				cpDefinitionId, contextAcceptLanguage.getPreferredLocale()));
	}

	private ProductShippingConfiguration _updateProductShippingConfiguration(
			CPDefinition cpDefinition,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		cpDefinition =
			ProductShippingConfigurationUtil.updateCPDefinitionShippingInfo(
				_cpDefinitionService, productShippingConfiguration,
				cpDefinition,
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));

		return _toProductShippingConfiguration(
			cpDefinition.getCPDefinitionId());
	}

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter.ProductShippingConfigurationDTOConverter)"
	)
	private DTOConverter<CPDefinition, ProductShippingConfiguration>
		_productShippingConfigurationDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}