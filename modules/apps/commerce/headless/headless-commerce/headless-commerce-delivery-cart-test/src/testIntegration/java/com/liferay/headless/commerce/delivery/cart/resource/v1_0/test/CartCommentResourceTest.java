/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.resource.v1_0.test;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.test.util.CommerceAccountTestUtil;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderNoteLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.CartComment;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Andrea Sbarra
 * @author Crescenzo Rega
 */
@RunWith(Arquillian.class)
public class CartCommentResourceTest extends BaseCartCommentResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_user = UserTestUtil.addUser(testCompany);

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_user));

		PrincipalThreadLocal.setName(_user.getUserId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			testCompany.getCompanyId(), testGroup.getGroupId(),
			_user.getUserId());

		_accountEntry = CommerceAccountTestUtil.addBusinessAccountEntry(
			_serviceContext.getUserId(), "Test Business Account", null, null,
			null, null, _serviceContext);

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			testGroup.getCompanyId());

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			testGroup.getGroupId(), _commerceCurrency.getCode());
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		if (_commerceOrder != null) {
			_commerceOrderNoteLocalService.deleteCommerceOrderNotes(
				_commerceOrder.getCommerceOrderId());

			_commerceOrderLocalService.deleteCommerceOrder(_commerceOrder);
		}

		if (_accountEntry != null) {
			_accountEntryLocalService.deleteAccountEntry(_accountEntry);
		}
	}

	@Override
	@Test
	public void testPutCartCommentByExternalReferenceCode() throws Exception {
		CartComment postCartComment =
			testPutCartCommentByExternalReferenceCode_addCartComment();

		CartComment randomCartComment = randomCartComment();

		CartComment putCartComment =
			cartCommentResource.putCartCommentByExternalReferenceCode(
				postCartComment.getExternalReferenceCode(), randomCartComment);

		assertEquals(randomCartComment, putCartComment);
		assertValid(putCartComment);

		CartComment getCartComment =
			cartCommentResource.getCartCommentByExternalReferenceCode(
				putCartComment.getExternalReferenceCode());

		assertEquals(randomCartComment, getCartComment);
		assertValid(getCartComment);
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"content", "restricted"};
	}

	@Override
	protected CartComment randomCartComment() throws Exception {
		return new CartComment() {
			{
				content = StringUtil.toLowerCase(RandomTestUtil.randomString());
				orderId = _getCommerceOrder().getCommerceOrderId();
				restricted = false;
			}
		};
	}

	@Override
	protected CartComment testDeleteCartComment_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment
			testDeleteCartCommentByExternalReferenceCode_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment
			testGetCartByExternalReferenceCodeCommentsPage_addCartComment(
				String externalReferenceCode, CartComment cartComment)
		throws Exception {

		return cartCommentResource.postCartByExternalReferenceCodeComment(
			externalReferenceCode, cartComment);
	}

	@Override
	protected String
			testGetCartByExternalReferenceCodeCommentsPage_getExternalReferenceCode()
		throws Exception {

		CommerceOrder commerceOrder = _getCommerceOrder();

		return commerceOrder.getExternalReferenceCode();
	}

	@Override
	protected CartComment testGetCartComment_addCartComment() throws Exception {
		return _addCartComment();
	}

	@Override
	protected CartComment
			testGetCartCommentByExternalReferenceCode_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment testGetCartCommentsPage_addCartComment(
			Long cartId, CartComment cartComment)
		throws Exception {

		return cartCommentResource.postCartComment(cartId, cartComment);
	}

	@Override
	protected Long testGetCartCommentsPage_getCartId() throws Exception {
		CommerceOrder commerceOrder = _getCommerceOrder();

		return commerceOrder.getCommerceOrderId();
	}

	@Override
	protected CartComment testGraphQLCartComment_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment
			testGraphQLGetCartCommentByExternalReferenceCode_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment testPatchCartComment_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment
			testPatchCartCommentByExternalReferenceCode_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	@Override
	protected CartComment
			testPostCartByExternalReferenceCodeComment_addCartComment(
				CartComment cartComment)
		throws Exception {

		return cartCommentResource.postCartByExternalReferenceCodeComment(
			_commerceOrder.getExternalReferenceCode(), cartComment);
	}

	@Override
	protected CartComment testPostCartComment_addCartComment(
			CartComment cartComment)
		throws Exception {

		return cartCommentResource.postCartComment(
			cartComment.getOrderId(), cartComment);
	}

	@Override
	protected CartComment testPutCartComment_addCartComment() throws Exception {
		return _addCartComment();
	}

	@Override
	protected CartComment
			testPutCartCommentByExternalReferenceCode_addCartComment()
		throws Exception {

		return _addCartComment();
	}

	private CartComment _addCartComment() throws Exception {
		CommerceOrderNote commerceOrderNote = _getCommerceOrderNote();

		return new CartComment() {
			{
				author = commerceOrderNote.getUserName();
				content = commerceOrderNote.getContent();
				externalReferenceCode =
					commerceOrderNote.getExternalReferenceCode();
				id = commerceOrderNote.getCommerceOrderNoteId();
				orderId = commerceOrderNote.getCommerceOrderId();
				restricted = commerceOrderNote.isRestricted();
			}
		};
	}

	private CommerceOrder _getCommerceOrder() throws Exception {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderLocalService.addCommerceOrder(
			_user.getUserId(), _commerceChannel.getGroupId(),
			_accountEntry.getAccountEntryId(), _commerceCurrency.getCode(), 0);

		return _commerceOrder;
	}

	private CommerceOrderNote _getCommerceOrderNote() throws Exception {
		_commerceOrderNote =
			_commerceOrderNoteLocalService.addCommerceOrderNote(
				_getCommerceOrder().getCommerceOrderId(),
				RandomTestUtil.randomString(), false, _serviceContext);

		return _commerceOrderNote;
	}

	private AccountEntry _accountEntry;

	@Inject
	private AccountEntryLocalService _accountEntryLocalService;

	@DeleteAfterTestRun
	private CommerceChannel _commerceChannel;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	private CommerceOrder _commerceOrder;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	private CommerceOrderNote _commerceOrderNote;

	@Inject
	private CommerceOrderNoteLocalService _commerceOrderNoteLocalService;

	private ServiceContext _serviceContext;

	@DeleteAfterTestRun
	private User _user;

}