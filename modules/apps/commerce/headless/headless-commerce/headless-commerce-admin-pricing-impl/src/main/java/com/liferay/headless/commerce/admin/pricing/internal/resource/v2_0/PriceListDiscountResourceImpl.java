/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListDiscountRel;
import com.liferay.commerce.price.list.service.CommercePriceListDiscountRelService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListDiscount;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.PriceListDiscountUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.PriceListDiscountResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/price-list-discount.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = PriceListDiscountResource.class
)
public class PriceListDiscountResourceImpl
	extends BasePriceListDiscountResourceImpl {

	@Override
	public void deletePriceListDiscount(Long id) throws Exception {
		_commercePriceListDiscountRelService.deleteCommercePriceListDiscountRel(
			id);
	}

	@Override
	public Page<PriceListDiscount>
			getPriceListByExternalReferenceCodePriceListDiscountsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.
				fetchCommercePriceListByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		List<CommercePriceListDiscountRel> commercePriceListDiscountRels =
			_commercePriceListDiscountRelService.
				getCommercePriceListDiscountRels(
					commercePriceList.getCommercePriceListId(),
					pagination.getStartPosition(), pagination.getEndPosition(),
					null);

		int totalCount =
			_commercePriceListDiscountRelService.
				getCommercePriceListDiscountRelsCount(
					commercePriceList.getCommercePriceListId());

		return Page.of(
			_toPriceListDiscounts(commercePriceListDiscountRels), pagination,
			totalCount);
	}

	@NestedField(parentClass = PriceList.class, value = "priceListDiscounts")
	@Override
	public Page<PriceListDiscount> getPriceListIdPriceListDiscountsPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommercePriceListDiscountRel> commercePriceListDiscountRels =
			_commercePriceListDiscountRelService.
				getCommercePriceListDiscountRels(
					id, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

		int totalCount =
			_commercePriceListDiscountRelService.
				getCommercePriceListDiscountRelsCount(id);

		return Page.of(
			_toPriceListDiscounts(commercePriceListDiscountRels), pagination,
			totalCount);
	}

	@Override
	public PriceListDiscount
			postPriceListByExternalReferenceCodePriceListDiscount(
				String externalReferenceCode,
				PriceListDiscount priceListDiscount)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.
				fetchCommercePriceListByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		CommercePriceListDiscountRel commercePriceListDiscountRel =
			PriceListDiscountUtil.addCommercePriceListDiscountRel(
				_commerceDiscountService, _commercePriceListDiscountRelService,
				priceListDiscount, commercePriceList, _serviceContextHelper);

		return _toPriceListDiscount(
			commercePriceListDiscountRel.getCommercePriceListDiscountRelId());
	}

	@Override
	public PriceListDiscount postPriceListIdPriceListDiscount(
			Long id, PriceListDiscount priceListDiscount)
		throws Exception {

		CommercePriceListDiscountRel commercePriceListDiscountRel =
			PriceListDiscountUtil.addCommercePriceListDiscountRel(
				_commerceDiscountService, _commercePriceListDiscountRelService,
				priceListDiscount,
				_commercePriceListService.getCommercePriceList(id),
				_serviceContextHelper);

		return _toPriceListDiscount(
			commercePriceListDiscountRel.getCommercePriceListDiscountRelId());
	}

	private PriceListDiscount _toPriceListDiscount(
			Long commercePriceListDiscountRelId)
		throws Exception {

		return _priceListDiscountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				commercePriceListDiscountRelId,
				contextAcceptLanguage.getPreferredLocale()));
	}

	private List<PriceListDiscount> _toPriceListDiscounts(
			List<CommercePriceListDiscountRel> commercePriceListDiscountRels)
		throws Exception {

		List<PriceListDiscount> priceListDiscounts = new ArrayList<>();

		for (CommercePriceListDiscountRel commercePriceListDiscountRel :
				commercePriceListDiscountRels) {

			priceListDiscounts.add(
				_toPriceListDiscount(
					commercePriceListDiscountRel.
						getCommercePriceListDiscountRelId()));
		}

		return priceListDiscounts;
	}

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private CommercePriceListDiscountRelService
		_commercePriceListDiscountRelService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.PriceListDiscountDTOConverter)"
	)
	private DTOConverter<CommercePriceListDiscountRel, PriceListDiscount>
		_priceListDiscountDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}