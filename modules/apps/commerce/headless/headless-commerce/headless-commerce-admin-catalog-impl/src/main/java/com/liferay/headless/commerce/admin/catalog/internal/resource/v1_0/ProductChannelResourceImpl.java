/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductChannel;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductChannelResource;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-channel.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = ProductChannelResource.class
)
@CTAware
public class ProductChannelResourceImpl extends BaseProductChannelResourceImpl {

	@Override
	public void deleteProductChannel(Long id) throws Exception {
		_commerceChannelRelService.deleteCommerceChannelRel(id);
	}

	@Override
	public Page<ProductChannel>
			getProductByExternalReferenceCodeProductChannelsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.
				fetchCPDefinitionByCProductExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (cpDefinition == null) {
			return Page.of(Collections.emptyList());
		}

		return _getProductChannelsPage(
			cpDefinition.getCPDefinitionId(), pagination);
	}

	@Override
	public ProductChannel getProductChannel(Long id) throws Exception {
		return _toProductChannel(
			_commerceChannelRelService.getCommerceChannelRel(id));
	}

	@NestedField(parentClass = Product.class, value = "productChannels")
	@Override
	public Page<ProductChannel> getProductIdProductChannelsPage(
			@NestedFieldId(value = "productId") Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			_cpDefinitionService.fetchCPDefinitionByCProductId(id);

		if (cpDefinition == null) {
			return Page.of(Collections.emptyList());
		}

		return _getProductChannelsPage(
			cpDefinition.getCPDefinitionId(), pagination);
	}

	private Page<ProductChannel> _getProductChannelsPage(
			long id, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_commerceChannelRelService.getCommerceChannelRels(
					CPDefinition.class.getName(), id, null,
					pagination.getStartPosition(), pagination.getEndPosition()),
				this::_toProductChannel),
			pagination,
			_commerceChannelRelService.getCommerceChannelRelsCount(
				CPDefinition.class.getName(), id));
	}

	private ProductChannel _toProductChannel(
			CommerceChannelRel commerceChannelRel)
		throws Exception {

		CommerceChannel commerceChannel =
			commerceChannelRel.getCommerceChannel();

		return new ProductChannel() {
			{
				setChannelId(commerceChannel::getCommerceChannelId);
				setCurrencyCode(commerceChannel::getCommerceCurrencyCode);
				setExternalReferenceCode(
					commerceChannel::getExternalReferenceCode);
				setId(commerceChannelRel::getCommerceChannelRelId);
				setName(commerceChannel::getName);
				setType(commerceChannel::getType);
			}
		};
	}

	@Reference
	private CommerceChannelRelService _commerceChannelRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

}