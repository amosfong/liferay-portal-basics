/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.payment.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.admin.payment.dto.v1_0.Payment;
import com.liferay.headless.commerce.admin.payment.resource.v1_0.PaymentResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

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

	public static void setPaymentResourceComponentServiceObjects(
		ComponentServiceObjects<PaymentResource>
			paymentResourceComponentServiceObjects) {

		_paymentResourceComponentServiceObjects =
			paymentResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {payments(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public PaymentPage payments(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_paymentResourceComponentServiceObjects,
			this::_populateResourceContext,
			paymentResource -> new PaymentPage(
				paymentResource.getPaymentsPage(
					search,
					_filterBiFunction.apply(paymentResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(paymentResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {paymentByExternalReferenceCode(externalReferenceCode: ___){actions, amount, amountFormatted, author, callbackURL, cancelURL, channelId, comment, createDate, currencyCode, errorMessages, externalReferenceCode, id, languageId, payload, paymentIntegrationKey, paymentIntegrationType, paymentStatus, paymentStatusStatus, reasonKey, reasonName, redirectURL, relatedItemId, relatedItemName, relatedItemNameLabel, transactionCode, type, typeLabel}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Payment paymentByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_paymentResourceComponentServiceObjects,
			this::_populateResourceContext,
			paymentResource ->
				paymentResource.getPaymentByExternalReferenceCode(
					externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {payment(id: ___){actions, amount, amountFormatted, author, callbackURL, cancelURL, channelId, comment, createDate, currencyCode, errorMessages, externalReferenceCode, id, languageId, payload, paymentIntegrationKey, paymentIntegrationType, paymentStatus, paymentStatusStatus, reasonKey, reasonName, redirectURL, relatedItemId, relatedItemName, relatedItemNameLabel, transactionCode, type, typeLabel}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Payment payment(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_paymentResourceComponentServiceObjects,
			this::_populateResourceContext,
			paymentResource -> paymentResource.getPayment(id));
	}

	@GraphQLName("PaymentPage")
	public class PaymentPage {

		public PaymentPage(Page paymentPage) {
			actions = paymentPage.getActions();

			items = paymentPage.getItems();
			lastPage = paymentPage.getLastPage();
			page = paymentPage.getPage();
			pageSize = paymentPage.getPageSize();
			totalCount = paymentPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Payment> items;

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

	private void _populateResourceContext(PaymentResource paymentResource)
		throws Exception {

		paymentResource.setContextAcceptLanguage(_acceptLanguage);
		paymentResource.setContextCompany(_company);
		paymentResource.setContextHttpServletRequest(_httpServletRequest);
		paymentResource.setContextHttpServletResponse(_httpServletResponse);
		paymentResource.setContextUriInfo(_uriInfo);
		paymentResource.setContextUser(_user);
		paymentResource.setGroupLocalService(_groupLocalService);
		paymentResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<PaymentResource>
		_paymentResourceComponentServiceObjects;

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