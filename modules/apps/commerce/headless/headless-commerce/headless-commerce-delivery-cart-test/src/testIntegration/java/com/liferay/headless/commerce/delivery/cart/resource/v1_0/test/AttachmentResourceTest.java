/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.resource.v1_0.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.constants.CommerceChannelConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.Attachment;
import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.AttachmentBase64;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;

import java.math.BigDecimal;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Stefano Motta
 */
@RunWith(Arquillian.class)
public class AttachmentResourceTest extends BaseAttachmentResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				testCompany.getCompanyId(), testGroup.getGroupId(),
				TestPropsValues.getUserId());

		AccountEntry accountEntry = _accountEntryLocalService.addAccountEntry(
			TestPropsValues.getUserId(), 0, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), null,
			RandomTestUtil.randomString() + "@liferay.com", null,
			RandomTestUtil.randomString(), "business", 1, serviceContext);

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.addCommerceCurrency(
				null, TestPropsValues.getUserId(),
				RandomTestUtil.randomString(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), BigDecimal.ONE, new HashMap<>(),
				2, 2, "HALF_EVEN", false, RandomTestUtil.nextDouble(), true);

		_commerceChannelLocalService.addCommerceChannel(
			RandomTestUtil.randomString(),
			AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT, testGroup.getGroupId(),
			RandomTestUtil.randomString(),
			CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
			commerceCurrency.getCode(), serviceContext);

		_commerceOrder = CommerceTestUtil.addB2BCommerceOrder(
			testGroup.getGroupId(), TestPropsValues.getUserId(),
			accountEntry.getAccountEntryId(),
			commerceCurrency.getCommerceCurrencyId());
	}

	@Override
	@Test
	public void testDeleteCartByExternalReferenceCodeAttachmentByExternalReferenceCodeAttachmentExternalReferenceCode()
		throws Exception {

		Attachment attachment =
			testDeleteCartByExternalReferenceCodeAttachmentByExternalReferenceCodeAttachmentExternalReferenceCode_addAttachment();

		assertHttpResponseStatusCode(
			204,
			attachmentResource.
				deleteCartByExternalReferenceCodeAttachmentByExternalReferenceCodeAttachmentExternalReferenceCodeHttpResponse(
					attachment.getExternalReferenceCode(),
					_commerceOrder.getExternalReferenceCode()));
	}

	@Override
	protected Attachment randomAttachment() throws Exception {
		return new Attachment() {
			{
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				title = RandomTestUtil.randomString() + ".jpg";
			}
		};
	}

	@Override
	protected Attachment testDeleteCartAttachment_addAttachment()
		throws Exception {

		return attachmentResource.postCartAttachmentByBase64(
			_commerceOrder.getCommerceOrderId(),
			_toAttachmentBase64(randomAttachment()));
	}

	@Override
	protected Long testDeleteCartAttachment_getCartId() throws Exception {
		return _commerceOrder.getCommerceOrderId();
	}

	@Override
	protected Attachment
			testDeleteCartByExternalReferenceCodeAttachmentByExternalReferenceCodeAttachmentExternalReferenceCode_addAttachment()
		throws Exception {

		return attachmentResource.
			postCartByExternalReferenceCodeAttachmentByBase64(
				_commerceOrder.getExternalReferenceCode(),
				_toAttachmentBase64(randomAttachment()));
	}

	@Override
	protected Attachment testGetCartAttachmentsPage_addAttachment(
			Long cartId, Attachment attachment)
		throws Exception {

		return attachmentResource.postCartAttachmentByBase64(
			cartId, _toAttachmentBase64(attachment));
	}

	@Override
	protected Long testGetCartAttachmentsPage_getCartId() throws Exception {
		return _commerceOrder.getCommerceOrderId();
	}

	@Override
	protected Attachment
			testGetCartByExternalReferenceCodeAttachmentsPage_addAttachment(
				String externalReferenceCode, Attachment attachment)
		throws Exception {

		return attachmentResource.
			postCartByExternalReferenceCodeAttachmentByBase64(
				externalReferenceCode, _toAttachmentBase64(attachment));
	}

	@Override
	protected String
			testGetCartByExternalReferenceCodeAttachmentsPage_getExternalReferenceCode()
		throws Exception {

		return _commerceOrder.getExternalReferenceCode();
	}

	@Override
	protected Attachment testGraphQLAttachment_addAttachment()
		throws Exception {

		return attachmentResource.postCartAttachmentByBase64(
			_commerceOrder.getCommerceOrderId(),
			_toAttachmentBase64(randomAttachment()));
	}

	@Override
	protected Attachment testPostCartAttachmentByBase64_addAttachment(
			Attachment attachment)
		throws Exception {

		return attachmentResource.postCartAttachmentByBase64(
			_commerceOrder.getCommerceOrderId(),
			_toAttachmentBase64(attachment));
	}

	@Override
	protected Attachment
			testPostCartByExternalReferenceCodeAttachmentByBase64_addAttachment(
				Attachment attachment)
		throws Exception {

		return attachmentResource.
			postCartByExternalReferenceCodeAttachmentByBase64(
				_commerceOrder.getExternalReferenceCode(),
				_toAttachmentBase64(attachment));
	}

	private AttachmentBase64 _toAttachmentBase64(Attachment attachment1)
		throws Exception {

		return new AttachmentBase64() {
			{
				attachment = Base64.encode(
					FileUtil.getBytes(
						AttachmentResourceTest.class,
						"dependencies/image.jpg"));
				externalReferenceCode = attachment1.getExternalReferenceCode();
				title = attachment1.getTitle();
			}
		};
	}

	@Inject
	private AccountEntryLocalService _accountEntryLocalService;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Inject
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	private CommerceOrder _commerceOrder;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

}