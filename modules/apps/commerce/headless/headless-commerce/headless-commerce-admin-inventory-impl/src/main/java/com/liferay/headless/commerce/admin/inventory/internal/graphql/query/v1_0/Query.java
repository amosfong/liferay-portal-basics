/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.inventory.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Channel;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.OrderType;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.ReplenishmentItem;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseAccount;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseAccountGroup;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseChannel;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseOrderType;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.AccountResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.ChannelResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.OrderTypeResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.ReplenishmentItemResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseAccountGroupResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseAccountResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseChannelResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseItemResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseOrderTypeResource;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class Query {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<AccountGroupResource>
			accountGroupResourceComponentServiceObjects) {

		_accountGroupResourceComponentServiceObjects =
			accountGroupResourceComponentServiceObjects;
	}

	public static void setChannelResourceComponentServiceObjects(
		ComponentServiceObjects<ChannelResource>
			channelResourceComponentServiceObjects) {

		_channelResourceComponentServiceObjects =
			channelResourceComponentServiceObjects;
	}

	public static void setOrderTypeResourceComponentServiceObjects(
		ComponentServiceObjects<OrderTypeResource>
			orderTypeResourceComponentServiceObjects) {

		_orderTypeResourceComponentServiceObjects =
			orderTypeResourceComponentServiceObjects;
	}

	public static void setReplenishmentItemResourceComponentServiceObjects(
		ComponentServiceObjects<ReplenishmentItemResource>
			replenishmentItemResourceComponentServiceObjects) {

		_replenishmentItemResourceComponentServiceObjects =
			replenishmentItemResourceComponentServiceObjects;
	}

	public static void setWarehouseResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseResource>
			warehouseResourceComponentServiceObjects) {

		_warehouseResourceComponentServiceObjects =
			warehouseResourceComponentServiceObjects;
	}

	public static void setWarehouseAccountResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseAccountResource>
			warehouseAccountResourceComponentServiceObjects) {

		_warehouseAccountResourceComponentServiceObjects =
			warehouseAccountResourceComponentServiceObjects;
	}

	public static void setWarehouseAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseAccountGroupResource>
			warehouseAccountGroupResourceComponentServiceObjects) {

		_warehouseAccountGroupResourceComponentServiceObjects =
			warehouseAccountGroupResourceComponentServiceObjects;
	}

	public static void setWarehouseChannelResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseChannelResource>
			warehouseChannelResourceComponentServiceObjects) {

		_warehouseChannelResourceComponentServiceObjects =
			warehouseChannelResourceComponentServiceObjects;
	}

	public static void setWarehouseItemResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseItemResource>
			warehouseItemResourceComponentServiceObjects) {

		_warehouseItemResourceComponentServiceObjects =
			warehouseItemResourceComponentServiceObjects;
	}

	public static void setWarehouseOrderTypeResourceComponentServiceObjects(
		ComponentServiceObjects<WarehouseOrderTypeResource>
			warehouseOrderTypeResourceComponentServiceObjects) {

		_warehouseOrderTypeResourceComponentServiceObjects =
			warehouseOrderTypeResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseAccountAccount(warehouseAccountId: ___){id, logoId, name}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Account warehouseAccountAccount(
			@GraphQLName("warehouseAccountId") Long warehouseAccountId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.getWarehouseAccountAccount(
				warehouseAccountId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseAccountGroupAccountGroup(warehouseAccountGroupId: ___){id, name}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountGroup warehouseAccountGroupAccountGroup(
			@GraphQLName("warehouseAccountGroupId") Long
				warehouseAccountGroupId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.getWarehouseAccountGroupAccountGroup(
					warehouseAccountGroupId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseChannelChannel(warehouseChannelId: ___){currencyCode, externalReferenceCode, id, name, siteGroupId, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Channel warehouseChannelChannel(
			@GraphQLName("warehouseChannelId") Long warehouseChannelId)
		throws Exception {

		return _applyComponentServiceObjects(
			_channelResourceComponentServiceObjects,
			this::_populateResourceContext,
			channelResource -> channelResource.getWarehouseChannelChannel(
				warehouseChannelId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseOrderTypeOrderType(warehouseOrderTypeId: ___){id, name}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public OrderType warehouseOrderTypeOrderType(
			@GraphQLName("warehouseOrderTypeId") Long warehouseOrderTypeId)
		throws Exception {

		return _applyComponentServiceObjects(
			_orderTypeResourceComponentServiceObjects,
			this::_populateResourceContext,
			orderTypeResource ->
				orderTypeResource.getWarehouseOrderTypeOrderType(
					warehouseOrderTypeId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {replenishmentItemByExternalReferenceCode(externalReferenceCode: ___){availabilityDate, externalReferenceCode, id, quantity, sku, unitOfMeasureKey, warehouseId}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ReplenishmentItem replenishmentItemByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_replenishmentItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			replenishmentItemResource ->
				replenishmentItemResource.
					getReplenishmentItemByExternalReferenceCode(
						externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {replenishmentItem(replenishmentItemId: ___){availabilityDate, externalReferenceCode, id, quantity, sku, unitOfMeasureKey, warehouseId}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ReplenishmentItem replenishmentItem(
			@GraphQLName("replenishmentItemId") Long replenishmentItemId)
		throws Exception {

		return _applyComponentServiceObjects(
			_replenishmentItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			replenishmentItemResource ->
				replenishmentItemResource.getReplenishmentItem(
					replenishmentItemId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {replenishmentItems(page: ___, pageSize: ___, sku: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ReplenishmentItemPage replenishmentItems(
			@GraphQLName("sku") String sku,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_replenishmentItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			replenishmentItemResource -> new ReplenishmentItemPage(
				replenishmentItemResource.getReplenishmentItemsPage(
					sku, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdReplenishmentItems(page: ___, pageSize: ___, warehouseId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ReplenishmentItemPage warehouseIdReplenishmentItems(
			@GraphQLName("warehouseId") Long warehouseId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_replenishmentItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			replenishmentItemResource -> new ReplenishmentItemPage(
				replenishmentItemResource.getWarehouseIdReplenishmentItemsPage(
					warehouseId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouses(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehousePage warehouses(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> new WarehousePage(
				warehouseResource.getWarehousesPage(
					search,
					_filterBiFunction.apply(warehouseResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(warehouseResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCode(externalReferenceCode: ___){actions, active, city, countryISOCode, description, externalReferenceCode, id, latitude, longitude, name, regionISOCode, street1, street2, street3, type, warehouseItems, zip}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Warehouse warehouseByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource ->
				warehouseResource.getWarehouseByExternalReferenceCode(
					externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseId(id: ___){actions, active, city, countryISOCode, description, externalReferenceCode, id, latitude, longitude, name, regionISOCode, street1, street2, street3, type, warehouseItems, zip}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Warehouse warehouseId(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_warehouseResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseResource -> warehouseResource.getWarehouseId(id));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCodeWarehouseAccounts(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseAccountPage
			warehouseByExternalReferenceCodeWarehouseAccounts(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseAccountResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseAccountResource -> new WarehouseAccountPage(
				warehouseAccountResource.
					getWarehouseByExternalReferenceCodeWarehouseAccountsPage(
						externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdWarehouseAccounts(filter: ___, id: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseAccountPage warehouseIdWarehouseAccounts(
			@GraphQLName("id") Long id, @GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseAccountResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseAccountResource -> new WarehouseAccountPage(
				warehouseAccountResource.getWarehouseIdWarehouseAccountsPage(
					id, search,
					_filterBiFunction.apply(
						warehouseAccountResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						warehouseAccountResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCodeWarehouseAccountGroups(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseAccountGroupPage
			warehouseByExternalReferenceCodeWarehouseAccountGroups(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseAccountGroupResource -> new WarehouseAccountGroupPage(
				warehouseAccountGroupResource.
					getWarehouseByExternalReferenceCodeWarehouseAccountGroupsPage(
						externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdWarehouseAccountGroups(filter: ___, id: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseAccountGroupPage warehouseIdWarehouseAccountGroups(
			@GraphQLName("id") Long id, @GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseAccountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseAccountGroupResource -> new WarehouseAccountGroupPage(
				warehouseAccountGroupResource.
					getWarehouseIdWarehouseAccountGroupsPage(
						id, search,
						_filterBiFunction.apply(
							warehouseAccountGroupResource, filterString),
						Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							warehouseAccountGroupResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCodeWarehouseChannels(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseChannelPage
			warehouseByExternalReferenceCodeWarehouseChannels(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseChannelResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseChannelResource -> new WarehouseChannelPage(
				warehouseChannelResource.
					getWarehouseByExternalReferenceCodeWarehouseChannelsPage(
						externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdWarehouseChannels(filter: ___, id: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseChannelPage warehouseIdWarehouseChannels(
			@GraphQLName("id") Long id, @GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseChannelResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseChannelResource -> new WarehouseChannelPage(
				warehouseChannelResource.getWarehouseIdWarehouseChannelsPage(
					id, search,
					_filterBiFunction.apply(
						warehouseChannelResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						warehouseChannelResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseItemByExternalReferenceCode(externalReferenceCode: ___){externalReferenceCode, id, modifiedDate, quantity, reservedQuantity, sku, unitOfMeasureKey, warehouseExternalReferenceCode, warehouseId}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseItem warehouseItemByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseItemResource ->
				warehouseItemResource.getWarehouseItemByExternalReferenceCode(
					externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseItemsUpdated(end: ___, page: ___, pageSize: ___, start: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseItemPage warehouseItemsUpdated(
			@GraphQLName("end") Date end, @GraphQLName("start") Date start,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseItemResource -> new WarehouseItemPage(
				warehouseItemResource.getWarehouseItemsUpdatedPage(
					end, start, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseItem(id: ___){externalReferenceCode, id, modifiedDate, quantity, reservedQuantity, sku, unitOfMeasureKey, warehouseExternalReferenceCode, warehouseId}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseItem warehouseItem(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseItemResource -> warehouseItemResource.getWarehouseItem(
				id));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCodeWarehouseItems(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseItemPage warehouseByExternalReferenceCodeWarehouseItems(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseItemResource -> new WarehouseItemPage(
				warehouseItemResource.
					getWarehouseByExternalReferenceCodeWarehouseItemsPage(
						externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdWarehouseItems(id: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseItemPage warehouseIdWarehouseItems(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseItemResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseItemResource -> new WarehouseItemPage(
				warehouseItemResource.getWarehouseIdWarehouseItemsPage(
					id, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseByExternalReferenceCodeWarehouseOrderTypes(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseOrderTypePage
			warehouseByExternalReferenceCodeWarehouseOrderTypes(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseOrderTypeResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseOrderTypeResource -> new WarehouseOrderTypePage(
				warehouseOrderTypeResource.
					getWarehouseByExternalReferenceCodeWarehouseOrderTypesPage(
						externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {warehouseIdWarehouseOrderTypes(filter: ___, id: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public WarehouseOrderTypePage warehouseIdWarehouseOrderTypes(
			@GraphQLName("id") Long id, @GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_warehouseOrderTypeResourceComponentServiceObjects,
			this::_populateResourceContext,
			warehouseOrderTypeResource -> new WarehouseOrderTypePage(
				warehouseOrderTypeResource.
					getWarehouseIdWarehouseOrderTypesPage(
						id, search,
						_filterBiFunction.apply(
							warehouseOrderTypeResource, filterString),
						Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							warehouseOrderTypeResource, sortsString))));
	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class GetWarehouseByExternalReferenceCodeTypeExtension {

		public GetWarehouseByExternalReferenceCodeTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public Warehouse warehouseByExternalReferenceCode() throws Exception {
			return _applyComponentServiceObjects(
				_warehouseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseResource ->
					warehouseResource.getWarehouseByExternalReferenceCode(
						_replenishmentItem.getExternalReferenceCode()));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class GetWarehouseItemByExternalReferenceCodeTypeExtension {

		public GetWarehouseItemByExternalReferenceCodeTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseItem warehouseItemByExternalReferenceCode()
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseItemResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseItemResource ->
					warehouseItemResource.
						getWarehouseItemByExternalReferenceCode(
							_replenishmentItem.getExternalReferenceCode()));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class
		GetWarehouseByExternalReferenceCodeWarehouseAccountGroupsPageTypeExtension {

		public GetWarehouseByExternalReferenceCodeWarehouseAccountGroupsPageTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseAccountGroupPage
				warehouseByExternalReferenceCodeWarehouseAccountGroups(
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseAccountGroupResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseAccountGroupResource -> new WarehouseAccountGroupPage(
					warehouseAccountGroupResource.
						getWarehouseByExternalReferenceCodeWarehouseAccountGroupsPage(
							_replenishmentItem.getExternalReferenceCode(),
							Pagination.of(page, pageSize))));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class
		GetWarehouseByExternalReferenceCodeWarehouseOrderTypesPageTypeExtension {

		public GetWarehouseByExternalReferenceCodeWarehouseOrderTypesPageTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseOrderTypePage
				warehouseByExternalReferenceCodeWarehouseOrderTypes(
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseOrderTypeResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseOrderTypeResource -> new WarehouseOrderTypePage(
					warehouseOrderTypeResource.
						getWarehouseByExternalReferenceCodeWarehouseOrderTypesPage(
							_replenishmentItem.getExternalReferenceCode(),
							Pagination.of(page, pageSize))));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class
		GetWarehouseByExternalReferenceCodeWarehouseAccountsPageTypeExtension {

		public GetWarehouseByExternalReferenceCodeWarehouseAccountsPageTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseAccountPage
				warehouseByExternalReferenceCodeWarehouseAccounts(
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseAccountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseAccountResource -> new WarehouseAccountPage(
					warehouseAccountResource.
						getWarehouseByExternalReferenceCodeWarehouseAccountsPage(
							_replenishmentItem.getExternalReferenceCode(),
							Pagination.of(page, pageSize))));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class
		GetWarehouseByExternalReferenceCodeWarehouseItemsPageTypeExtension {

		public GetWarehouseByExternalReferenceCodeWarehouseItemsPageTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseItemPage warehouseByExternalReferenceCodeWarehouseItems(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseItemResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseItemResource -> new WarehouseItemPage(
					warehouseItemResource.
						getWarehouseByExternalReferenceCodeWarehouseItemsPage(
							_replenishmentItem.getExternalReferenceCode(),
							Pagination.of(page, pageSize))));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(ReplenishmentItem.class)
	public class
		GetWarehouseByExternalReferenceCodeWarehouseChannelsPageTypeExtension {

		public GetWarehouseByExternalReferenceCodeWarehouseChannelsPageTypeExtension(
			ReplenishmentItem replenishmentItem) {

			_replenishmentItem = replenishmentItem;
		}

		@GraphQLField
		public WarehouseChannelPage
				warehouseByExternalReferenceCodeWarehouseChannels(
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_warehouseChannelResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				warehouseChannelResource -> new WarehouseChannelPage(
					warehouseChannelResource.
						getWarehouseByExternalReferenceCodeWarehouseChannelsPage(
							_replenishmentItem.getExternalReferenceCode(),
							Pagination.of(page, pageSize))));
		}

		private ReplenishmentItem _replenishmentItem;

	}

	@GraphQLTypeExtension(Warehouse.class)
	public class GetReplenishmentItemByExternalReferenceCodeTypeExtension {

		public GetReplenishmentItemByExternalReferenceCodeTypeExtension(
			Warehouse warehouse) {

			_warehouse = warehouse;
		}

		@GraphQLField
		public ReplenishmentItem replenishmentItemByExternalReferenceCode()
			throws Exception {

			return _applyComponentServiceObjects(
				_replenishmentItemResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				replenishmentItemResource ->
					replenishmentItemResource.
						getReplenishmentItemByExternalReferenceCode(
							_warehouse.getExternalReferenceCode()));
		}

		private Warehouse _warehouse;

	}

	@GraphQLName("AccountPage")
	public class AccountPage {

		public AccountPage(Page accountPage) {
			actions = accountPage.getActions();

			items = accountPage.getItems();
			lastPage = accountPage.getLastPage();
			page = accountPage.getPage();
			pageSize = accountPage.getPageSize();
			totalCount = accountPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Account> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("AccountGroupPage")
	public class AccountGroupPage {

		public AccountGroupPage(Page accountGroupPage) {
			actions = accountGroupPage.getActions();

			items = accountGroupPage.getItems();
			lastPage = accountGroupPage.getLastPage();
			page = accountGroupPage.getPage();
			pageSize = accountGroupPage.getPageSize();
			totalCount = accountGroupPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<AccountGroup> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ChannelPage")
	public class ChannelPage {

		public ChannelPage(Page channelPage) {
			actions = channelPage.getActions();

			items = channelPage.getItems();
			lastPage = channelPage.getLastPage();
			page = channelPage.getPage();
			pageSize = channelPage.getPageSize();
			totalCount = channelPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Channel> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("OrderTypePage")
	public class OrderTypePage {

		public OrderTypePage(Page orderTypePage) {
			actions = orderTypePage.getActions();

			items = orderTypePage.getItems();
			lastPage = orderTypePage.getLastPage();
			page = orderTypePage.getPage();
			pageSize = orderTypePage.getPageSize();
			totalCount = orderTypePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<OrderType> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ReplenishmentItemPage")
	public class ReplenishmentItemPage {

		public ReplenishmentItemPage(Page replenishmentItemPage) {
			actions = replenishmentItemPage.getActions();

			items = replenishmentItemPage.getItems();
			lastPage = replenishmentItemPage.getLastPage();
			page = replenishmentItemPage.getPage();
			pageSize = replenishmentItemPage.getPageSize();
			totalCount = replenishmentItemPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<ReplenishmentItem> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehousePage")
	public class WarehousePage {

		public WarehousePage(Page warehousePage) {
			actions = warehousePage.getActions();

			items = warehousePage.getItems();
			lastPage = warehousePage.getLastPage();
			page = warehousePage.getPage();
			pageSize = warehousePage.getPageSize();
			totalCount = warehousePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Warehouse> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehouseAccountPage")
	public class WarehouseAccountPage {

		public WarehouseAccountPage(Page warehouseAccountPage) {
			actions = warehouseAccountPage.getActions();

			items = warehouseAccountPage.getItems();
			lastPage = warehouseAccountPage.getLastPage();
			page = warehouseAccountPage.getPage();
			pageSize = warehouseAccountPage.getPageSize();
			totalCount = warehouseAccountPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<WarehouseAccount> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehouseAccountGroupPage")
	public class WarehouseAccountGroupPage {

		public WarehouseAccountGroupPage(Page warehouseAccountGroupPage) {
			actions = warehouseAccountGroupPage.getActions();

			items = warehouseAccountGroupPage.getItems();
			lastPage = warehouseAccountGroupPage.getLastPage();
			page = warehouseAccountGroupPage.getPage();
			pageSize = warehouseAccountGroupPage.getPageSize();
			totalCount = warehouseAccountGroupPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<WarehouseAccountGroup> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehouseChannelPage")
	public class WarehouseChannelPage {

		public WarehouseChannelPage(Page warehouseChannelPage) {
			actions = warehouseChannelPage.getActions();

			items = warehouseChannelPage.getItems();
			lastPage = warehouseChannelPage.getLastPage();
			page = warehouseChannelPage.getPage();
			pageSize = warehouseChannelPage.getPageSize();
			totalCount = warehouseChannelPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<WarehouseChannel> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehouseItemPage")
	public class WarehouseItemPage {

		public WarehouseItemPage(Page warehouseItemPage) {
			actions = warehouseItemPage.getActions();

			items = warehouseItemPage.getItems();
			lastPage = warehouseItemPage.getLastPage();
			page = warehouseItemPage.getPage();
			pageSize = warehouseItemPage.getPageSize();
			totalCount = warehouseItemPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<WarehouseItem> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("WarehouseOrderTypePage")
	public class WarehouseOrderTypePage {

		public WarehouseOrderTypePage(Page warehouseOrderTypePage) {
			actions = warehouseOrderTypePage.getActions();

			items = warehouseOrderTypePage.getItems();
			lastPage = warehouseOrderTypePage.getLastPage();
			page = warehouseOrderTypePage.getPage();
			pageSize = warehouseOrderTypePage.getPageSize();
			totalCount = warehouseOrderTypePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<WarehouseOrderType> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextAcceptLanguage(_acceptLanguage);
		accountResource.setContextCompany(_company);
		accountResource.setContextHttpServletRequest(_httpServletRequest);
		accountResource.setContextHttpServletResponse(_httpServletResponse);
		accountResource.setContextUriInfo(_uriInfo);
		accountResource.setContextUser(_user);
		accountResource.setGroupLocalService(_groupLocalService);
		accountResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			AccountGroupResource accountGroupResource)
		throws Exception {

		accountGroupResource.setContextAcceptLanguage(_acceptLanguage);
		accountGroupResource.setContextCompany(_company);
		accountGroupResource.setContextHttpServletRequest(_httpServletRequest);
		accountGroupResource.setContextHttpServletResponse(
			_httpServletResponse);
		accountGroupResource.setContextUriInfo(_uriInfo);
		accountGroupResource.setContextUser(_user);
		accountGroupResource.setGroupLocalService(_groupLocalService);
		accountGroupResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(ChannelResource channelResource)
		throws Exception {

		channelResource.setContextAcceptLanguage(_acceptLanguage);
		channelResource.setContextCompany(_company);
		channelResource.setContextHttpServletRequest(_httpServletRequest);
		channelResource.setContextHttpServletResponse(_httpServletResponse);
		channelResource.setContextUriInfo(_uriInfo);
		channelResource.setContextUser(_user);
		channelResource.setGroupLocalService(_groupLocalService);
		channelResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(OrderTypeResource orderTypeResource)
		throws Exception {

		orderTypeResource.setContextAcceptLanguage(_acceptLanguage);
		orderTypeResource.setContextCompany(_company);
		orderTypeResource.setContextHttpServletRequest(_httpServletRequest);
		orderTypeResource.setContextHttpServletResponse(_httpServletResponse);
		orderTypeResource.setContextUriInfo(_uriInfo);
		orderTypeResource.setContextUser(_user);
		orderTypeResource.setGroupLocalService(_groupLocalService);
		orderTypeResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			ReplenishmentItemResource replenishmentItemResource)
		throws Exception {

		replenishmentItemResource.setContextAcceptLanguage(_acceptLanguage);
		replenishmentItemResource.setContextCompany(_company);
		replenishmentItemResource.setContextHttpServletRequest(
			_httpServletRequest);
		replenishmentItemResource.setContextHttpServletResponse(
			_httpServletResponse);
		replenishmentItemResource.setContextUriInfo(_uriInfo);
		replenishmentItemResource.setContextUser(_user);
		replenishmentItemResource.setGroupLocalService(_groupLocalService);
		replenishmentItemResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(WarehouseResource warehouseResource)
		throws Exception {

		warehouseResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseResource.setContextCompany(_company);
		warehouseResource.setContextHttpServletRequest(_httpServletRequest);
		warehouseResource.setContextHttpServletResponse(_httpServletResponse);
		warehouseResource.setContextUriInfo(_uriInfo);
		warehouseResource.setContextUser(_user);
		warehouseResource.setGroupLocalService(_groupLocalService);
		warehouseResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WarehouseAccountResource warehouseAccountResource)
		throws Exception {

		warehouseAccountResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseAccountResource.setContextCompany(_company);
		warehouseAccountResource.setContextHttpServletRequest(
			_httpServletRequest);
		warehouseAccountResource.setContextHttpServletResponse(
			_httpServletResponse);
		warehouseAccountResource.setContextUriInfo(_uriInfo);
		warehouseAccountResource.setContextUser(_user);
		warehouseAccountResource.setGroupLocalService(_groupLocalService);
		warehouseAccountResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WarehouseAccountGroupResource warehouseAccountGroupResource)
		throws Exception {

		warehouseAccountGroupResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseAccountGroupResource.setContextCompany(_company);
		warehouseAccountGroupResource.setContextHttpServletRequest(
			_httpServletRequest);
		warehouseAccountGroupResource.setContextHttpServletResponse(
			_httpServletResponse);
		warehouseAccountGroupResource.setContextUriInfo(_uriInfo);
		warehouseAccountGroupResource.setContextUser(_user);
		warehouseAccountGroupResource.setGroupLocalService(_groupLocalService);
		warehouseAccountGroupResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WarehouseChannelResource warehouseChannelResource)
		throws Exception {

		warehouseChannelResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseChannelResource.setContextCompany(_company);
		warehouseChannelResource.setContextHttpServletRequest(
			_httpServletRequest);
		warehouseChannelResource.setContextHttpServletResponse(
			_httpServletResponse);
		warehouseChannelResource.setContextUriInfo(_uriInfo);
		warehouseChannelResource.setContextUser(_user);
		warehouseChannelResource.setGroupLocalService(_groupLocalService);
		warehouseChannelResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WarehouseItemResource warehouseItemResource)
		throws Exception {

		warehouseItemResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseItemResource.setContextCompany(_company);
		warehouseItemResource.setContextHttpServletRequest(_httpServletRequest);
		warehouseItemResource.setContextHttpServletResponse(
			_httpServletResponse);
		warehouseItemResource.setContextUriInfo(_uriInfo);
		warehouseItemResource.setContextUser(_user);
		warehouseItemResource.setGroupLocalService(_groupLocalService);
		warehouseItemResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(
			WarehouseOrderTypeResource warehouseOrderTypeResource)
		throws Exception {

		warehouseOrderTypeResource.setContextAcceptLanguage(_acceptLanguage);
		warehouseOrderTypeResource.setContextCompany(_company);
		warehouseOrderTypeResource.setContextHttpServletRequest(
			_httpServletRequest);
		warehouseOrderTypeResource.setContextHttpServletResponse(
			_httpServletResponse);
		warehouseOrderTypeResource.setContextUriInfo(_uriInfo);
		warehouseOrderTypeResource.setContextUser(_user);
		warehouseOrderTypeResource.setGroupLocalService(_groupLocalService);
		warehouseOrderTypeResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountGroupResource>
		_accountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<ChannelResource>
		_channelResourceComponentServiceObjects;
	private static ComponentServiceObjects<OrderTypeResource>
		_orderTypeResourceComponentServiceObjects;
	private static ComponentServiceObjects<ReplenishmentItemResource>
		_replenishmentItemResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseResource>
		_warehouseResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseAccountResource>
		_warehouseAccountResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseAccountGroupResource>
		_warehouseAccountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseChannelResource>
		_warehouseChannelResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseItemResource>
		_warehouseItemResourceComponentServiceObjects;
	private static ComponentServiceObjects<WarehouseOrderTypeResource>
		_warehouseOrderTypeResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}