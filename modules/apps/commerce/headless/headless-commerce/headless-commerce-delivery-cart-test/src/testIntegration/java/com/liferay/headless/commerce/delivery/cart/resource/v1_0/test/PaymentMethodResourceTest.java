/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalServiceUtil;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.PaymentMethod;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Andrea Sbarra
 * @author Crescenzo Rega
 */
@RunWith(Arquillian.class)
public class PaymentMethodResourceTest
	extends BasePaymentMethodResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			testGroup.getCompanyId());

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			testGroup.getGroupId(), _commerceCurrency.getCode());

		_engineKeys = new ArrayList(
			Arrays.asList(
				"authorize-net", "mercanet", "money-order", "paypal",
				"test-payment-method"));
		_siteAdminUser = UserTestUtil.addGroupAdminUser(testGroup);

		_user = UserTestUtil.addUser(testCompany);

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			testCompany.getCompanyId(), testGroup.getGroupId(),
			_user.getUserId());

		_setUpPermissionThreadLocal();
		_setUpPrincipalThreadLocal();
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		PrincipalThreadLocal.setName(_originalName);
	}

	@Ignore
	@Override
	@Test
	public void testGetCartByExternalReferenceCodePaymentMethodsPage()
		throws Exception {
	}

	@Ignore
	@Override
	@Test
	public void testGetCartPaymentMethodsPage() throws Exception {
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"key"};
	}

	@Override
	protected PaymentMethod randomPaymentMethod() throws Exception {
		return new PaymentMethod() {
			{
				description = RandomTestUtil.randomString();
				key = _getRandomEngineKey();
				name = RandomTestUtil.randomString();
			}
		};
	}

	@Override
	protected PaymentMethod
			testGetCartByExternalReferenceCodePaymentMethodsPage_addPaymentMethod(
				String externalReferenceCode, PaymentMethod paymentMethod)
		throws Exception {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			CommercePaymentMethodGroupRelLocalServiceUtil.
				addCommercePaymentMethodGroupRel(
					_user.getUserId(), _commerceChannel.getGroupId(),
					Collections.singletonMap(
						LocaleUtil.US, paymentMethod.getName()),
					Collections.singletonMap(
						LocaleUtil.US, paymentMethod.getDescription()),
					true, null, paymentMethod.getKey(), 1, null);

		_commercePaymentMethodGroupRels.add(commercePaymentMethodGroupRel);

		return new PaymentMethod() {
			{
				description = commercePaymentMethodGroupRel.getDescription(
					LocaleUtil.US);
				key = commercePaymentMethodGroupRel.getPaymentIntegrationKey();
				name = commercePaymentMethodGroupRel.getName(LocaleUtil.US);
			}
		};
	}

	@Override
	protected String
			testGetCartByExternalReferenceCodePaymentMethodsPage_getExternalReferenceCode()
		throws Exception {

		CommerceOrder commerceOrder = _addCommerceOrder();

		return commerceOrder.getExternalReferenceCode();
	}

	@Override
	protected PaymentMethod testGetCartPaymentMethodsPage_addPaymentMethod(
			Long cartId, PaymentMethod paymentMethod)
		throws Exception {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			CommercePaymentMethodGroupRelLocalServiceUtil.
				addCommercePaymentMethodGroupRel(
					_user.getUserId(), _commerceChannel.getGroupId(),
					Collections.singletonMap(
						LocaleUtil.US, paymentMethod.getName()),
					Collections.singletonMap(
						LocaleUtil.US, paymentMethod.getDescription()),
					true, null, paymentMethod.getKey(), 1, null);

		_commercePaymentMethodGroupRels.add(commercePaymentMethodGroupRel);

		return new PaymentMethod() {
			{
				description = commercePaymentMethodGroupRel.getDescription(
					LocaleUtil.US);
				key = commercePaymentMethodGroupRel.getPaymentIntegrationKey();
				name = commercePaymentMethodGroupRel.getName(LocaleUtil.US);
			}
		};
	}

	@Override
	protected Long testGetCartPaymentMethodsPage_getCartId() throws Exception {
		CommerceOrder commerceOrder = _addCommerceOrder();

		return commerceOrder.getCommerceOrderId();
	}

	private CommerceOrder _addCommerceOrder() throws Exception {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_commerceCurrency);

		_cpInstance = CPTestUtil.addCPInstanceWithRandomSku(
			testGroup.getGroupId());

		_commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				_serviceContext);

		_commerceInventoryWarehouseItem =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_user.getUserId(), _commerceInventoryWarehouse, BigDecimal.TEN,
				_cpInstance.getSku(), StringPool.BLANK);

		_commerceChannelRel = CommerceTestUtil.addWarehouseCommerceChannelRel(
			_commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceTestUtil.addCommerceOrderItem(
			_commerceOrder.getCommerceOrderId(), _cpInstance.getCPInstanceId(),
			BigDecimal.ONE);

		return _commerceOrder;
	}

	private String _getRandomEngineKey() {
		Random random = new Random();

		return _engineKeys.remove(random.nextInt(_engineKeys.size()));
	}

	private void _setUpPermissionThreadLocal() {
		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_siteAdminUser));
	}

	private void _setUpPrincipalThreadLocal() {
		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(_siteAdminUser.getUserId());
	}

	@DeleteAfterTestRun
	private CommerceChannel _commerceChannel;

	@DeleteAfterTestRun
	private CommerceChannelRel _commerceChannelRel;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@DeleteAfterTestRun
	private CommerceInventoryWarehouse _commerceInventoryWarehouse;

	@DeleteAfterTestRun
	private CommerceInventoryWarehouseItem _commerceInventoryWarehouseItem;

	@DeleteAfterTestRun
	private CommerceOrder _commerceOrder;

	@DeleteAfterTestRun
	private List<CommercePaymentMethodGroupRel>
		_commercePaymentMethodGroupRels = new ArrayList<>();

	@DeleteAfterTestRun
	private CPInstance _cpInstance;

	private List<String> _engineKeys;
	private String _originalName;
	private PermissionChecker _originalPermissionChecker;
	private ServiceContext _serviceContext;

	@DeleteAfterTestRun
	private User _siteAdminUser;

	@DeleteAfterTestRun
	private User _user;

}