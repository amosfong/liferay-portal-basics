/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.inventory.internal.resource.v1_0;

import com.liferay.account.exception.NoSuchEntryException;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryService;
import com.liferay.commerce.exception.NoSuchWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseRel;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseRelService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseAccount;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseAccountResource;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse-account.properties",
	property = "nested.field.support=true", scope = ServiceScope.PROTOTYPE,
	service = WarehouseAccountResource.class
)
public class WarehouseAccountResourceImpl
	extends BaseWarehouseAccountResourceImpl {

	@Override
	public void deleteWarehouseAccount(Long id) throws Exception {
		_commerceInventoryWarehouseRelService.
			deleteCommerceInventoryWarehouseRel(id);
	}

	@Override
	public Page<WarehouseAccount>
			getWarehouseByExternalReferenceCodeWarehouseAccountsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.
				fetchCommerceInventoryWarehouseByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchWarehouseException(
				"Unable to find warehouse with external reference code " +
					externalReferenceCode);
		}

		List<CommerceInventoryWarehouseRel> commerceInventoryWarehouseRels =
			_commerceInventoryWarehouseRelService.
				getCommerceInventoryWarehouseRels(
					AccountEntry.class.getName(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					pagination.getStartPosition(), pagination.getEndPosition(),
					null);

		int totalCount =
			_commerceInventoryWarehouseRelService.
				getCommerceInventoryWarehouseRelsCount(
					AccountEntry.class.getName(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId());

		return Page.of(
			_toWarehouseAccounts(commerceInventoryWarehouseRels), pagination,
			totalCount);
	}

	@NestedField(parentClass = Warehouse.class, value = "warehouseAccounts")
	@Override
	public Page<WarehouseAccount> getWarehouseIdWarehouseAccountsPage(
			Long id, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		List<CommerceInventoryWarehouseRel> commerceInventoryWarehouseRels =
			_commerceInventoryWarehouseRelService.
				getAccountEntryCommerceInventoryWarehouseRels(
					id, search, pagination.getStartPosition(),
					pagination.getEndPosition());

		int totalCount =
			_commerceInventoryWarehouseRelService.
				getAccountEntryCommerceInventoryWarehouseRelsCount(id, search);

		return Page.of(
			_toWarehouseAccounts(commerceInventoryWarehouseRels), pagination,
			totalCount);
	}

	@Override
	public WarehouseAccount
			postWarehouseByExternalReferenceCodeWarehouseAccount(
				String externalReferenceCode, WarehouseAccount warehouseAccount)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.
				fetchCommerceInventoryWarehouseByExternalReferenceCode(
					externalReferenceCode, contextCompany.getCompanyId());

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchWarehouseException(
				"Unable to find warehouse with external reference code " +
					externalReferenceCode);
		}

		CommerceInventoryWarehouseRel commerceInventoryWarehouseRel =
			_addCommerceInventoryWarehouseRel(
				commerceInventoryWarehouse, warehouseAccount);

		return _toWarehouseAccount(
			commerceInventoryWarehouseRel.getCommerceInventoryWarehouseRelId());
	}

	@Override
	public WarehouseAccount postWarehouseIdWarehouseAccount(
			Long id, WarehouseAccount warehouseAccount)
		throws Exception {

		CommerceInventoryWarehouseRel commerceInventoryWarehouseRel =
			_addCommerceInventoryWarehouseRel(
				_commerceInventoryWarehouseService.
					getCommerceInventoryWarehouse(id),
				warehouseAccount);

		return _toWarehouseAccount(
			commerceInventoryWarehouseRel.getCommerceInventoryWarehouseRelId());
	}

	private CommerceInventoryWarehouseRel _addCommerceInventoryWarehouseRel(
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			WarehouseAccount warehouseAccount)
		throws Exception {

		AccountEntry accountEntry = null;

		if (Validator.isNull(
				warehouseAccount.getAccountExternalReferenceCode())) {

			accountEntry = _accountEntryService.getAccountEntry(
				warehouseAccount.getAccountId());
		}
		else {
			accountEntry =
				_accountEntryService.fetchAccountEntryByExternalReferenceCode(
					commerceInventoryWarehouse.getCompanyId(),
					warehouseAccount.getAccountExternalReferenceCode());

			if (accountEntry == null) {
				throw new NoSuchEntryException(
					"Unable to find account with external reference code " +
						warehouseAccount.getAccountExternalReferenceCode());
			}
		}

		return _commerceInventoryWarehouseRelService.
			addCommerceInventoryWarehouseRel(
				AccountEntry.class.getName(), accountEntry.getAccountEntryId(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId());
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceInventoryWarehouseRel commerceInventoryWarehouseRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceInventoryWarehouseRel.getCommerceInventoryWarehouseId(),
				"deleteWarehouseAccount",
				_commerceInventoryWarehouseModelResourcePermission)
		).build();
	}

	private WarehouseAccount _toWarehouseAccount(
			Long commerceInventoryWarehouseRelId)
		throws Exception {

		CommerceInventoryWarehouseRel commerceInventoryWarehouseRel =
			_commerceInventoryWarehouseRelService.
				getCommerceInventoryWarehouseRel(
					commerceInventoryWarehouseRelId);

		return _warehouseAccountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceInventoryWarehouseRel),
				_dtoConverterRegistry, commerceInventoryWarehouseRelId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private List<WarehouseAccount> _toWarehouseAccounts(
		List<CommerceInventoryWarehouseRel> commerceInventoryWarehouseRels) {

		return transform(
			commerceInventoryWarehouseRels,
			commerceInventoryWarehouseRel -> _toWarehouseAccount(
				commerceInventoryWarehouseRel.
					getCommerceInventoryWarehouseRelId()));
	}

	@Reference
	private AccountEntryService _accountEntryService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouse)"
	)
	private ModelResourcePermission<CommerceInventoryWarehouse>
		_commerceInventoryWarehouseModelResourcePermission;

	@Reference
	private CommerceInventoryWarehouseRelService
		_commerceInventoryWarehouseRelService;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(component.name=com.liferay.headless.commerce.admin.inventory.internal.dto.v1_0.converter.WarehouseAccountDTOConverter)"
	)
	private DTOConverter<CommerceInventoryWarehouseRel, WarehouseAccount>
		_warehouseAccountDTOConverter;

}