/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.cart.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.delivery.cart.client.dto.v1_0.CartItem;
import com.liferay.headless.commerce.delivery.cart.client.http.HttpInvoker;
import com.liferay.headless.commerce.delivery.cart.client.pagination.Page;
import com.liferay.headless.commerce.delivery.cart.client.pagination.Pagination;
import com.liferay.headless.commerce.delivery.cart.client.resource.v1_0.CartItemResource;
import com.liferay.headless.commerce.delivery.cart.client.serdes.v1_0.CartItemSerDes;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public abstract class BaseCartItemResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_cartItemResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		CartItemResource.Builder builder = CartItemResource.builder();

		cartItemResource = builder.authentication(
			testCompanyAdminUser.getEmailAddress(),
			PropsValues.DEFAULT_ADMIN_PASSWORD
		).endpoint(
			testCompany.getVirtualHostname(), 8080, "http"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		CartItem cartItem1 = randomCartItem();

		String json = objectMapper.writeValueAsString(cartItem1);

		CartItem cartItem2 = CartItemSerDes.toDTO(json);

		Assert.assertTrue(equals(cartItem1, cartItem2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		CartItem cartItem = randomCartItem();

		String json1 = objectMapper.writeValueAsString(cartItem);
		String json2 = CartItemSerDes.toJSON(cartItem);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	protected ObjectMapper getClientSerDesObjectMapper() {
		return new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		CartItem cartItem = randomCartItem();

		cartItem.setAdaptiveMediaImageHTMLTag(regex);
		cartItem.setDeliveryGroup(regex);
		cartItem.setDeliveryGroupName(regex);
		cartItem.setExternalReferenceCode(regex);
		cartItem.setName(regex);
		cartItem.setOptions(regex);
		cartItem.setReplacedSku(regex);
		cartItem.setReplacedSkuExternalReferenceCode(regex);
		cartItem.setShippingAddressExternalReferenceCode(regex);
		cartItem.setSku(regex);
		cartItem.setThumbnail(regex);
		cartItem.setUnitOfMeasure(regex);

		String json = CartItemSerDes.toJSON(cartItem);

		Assert.assertFalse(json.contains(regex));

		cartItem = CartItemSerDes.toDTO(json);

		Assert.assertEquals(regex, cartItem.getAdaptiveMediaImageHTMLTag());
		Assert.assertEquals(regex, cartItem.getDeliveryGroup());
		Assert.assertEquals(regex, cartItem.getDeliveryGroupName());
		Assert.assertEquals(regex, cartItem.getExternalReferenceCode());
		Assert.assertEquals(regex, cartItem.getName());
		Assert.assertEquals(regex, cartItem.getOptions());
		Assert.assertEquals(regex, cartItem.getReplacedSku());
		Assert.assertEquals(
			regex, cartItem.getReplacedSkuExternalReferenceCode());
		Assert.assertEquals(
			regex, cartItem.getShippingAddressExternalReferenceCode());
		Assert.assertEquals(regex, cartItem.getSku());
		Assert.assertEquals(regex, cartItem.getThumbnail());
		Assert.assertEquals(regex, cartItem.getUnitOfMeasure());
	}

	@Test
	public void testDeleteCartItemByExternalReferenceCode() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		CartItem cartItem =
			testDeleteCartItemByExternalReferenceCode_addCartItem();

		assertHttpResponseStatusCode(
			204,
			cartItemResource.deleteCartItemByExternalReferenceCodeHttpResponse(
				cartItem.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			cartItemResource.getCartItemByExternalReferenceCodeHttpResponse(
				cartItem.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			cartItemResource.getCartItemByExternalReferenceCodeHttpResponse(
				cartItem.getExternalReferenceCode()));
	}

	protected CartItem testDeleteCartItemByExternalReferenceCode_addCartItem()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetCartItemByExternalReferenceCode() throws Exception {
		CartItem postCartItem =
			testGetCartItemByExternalReferenceCode_addCartItem();

		CartItem getCartItem =
			cartItemResource.getCartItemByExternalReferenceCode(
				postCartItem.getExternalReferenceCode());

		assertEquals(postCartItem, getCartItem);
		assertValid(getCartItem);
	}

	protected CartItem testGetCartItemByExternalReferenceCode_addCartItem()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetCartItemByExternalReferenceCode()
		throws Exception {

		CartItem cartItem =
			testGraphQLGetCartItemByExternalReferenceCode_addCartItem();

		// No namespace

		Assert.assertTrue(
			equals(
				cartItem,
				CartItemSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"cartItemByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"externalReferenceCode",
											"\"" +
												cartItem.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/cartItemByExternalReferenceCode"))));

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		Assert.assertTrue(
			equals(
				cartItem,
				CartItemSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessCommerceDeliveryCart_v1_0",
								new GraphQLField(
									"cartItemByExternalReferenceCode",
									new HashMap<String, Object>() {
										{
											put(
												"externalReferenceCode",
												"\"" +
													cartItem.
														getExternalReferenceCode() +
															"\"");
										}
									},
									getGraphQLFields()))),
						"JSONObject/data",
						"JSONObject/headlessCommerceDeliveryCart_v1_0",
						"Object/cartItemByExternalReferenceCode"))));
	}

	@Test
	public void testGraphQLGetCartItemByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"cartItemByExternalReferenceCode",
						new HashMap<String, Object>() {
							{
								put(
									"externalReferenceCode",
									irrelevantExternalReferenceCode);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessCommerceDeliveryCart_v1_0",
						new GraphQLField(
							"cartItemByExternalReferenceCode",
							new HashMap<String, Object>() {
								{
									put(
										"externalReferenceCode",
										irrelevantExternalReferenceCode);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected CartItem
			testGraphQLGetCartItemByExternalReferenceCode_addCartItem()
		throws Exception {

		return testGraphQLCartItem_addCartItem();
	}

	@Test
	public void testPatchCartItemByExternalReferenceCode() throws Exception {
		CartItem postCartItem =
			testPatchCartItemByExternalReferenceCode_addCartItem();

		CartItem randomPatchCartItem = randomPatchCartItem();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		CartItem patchCartItem =
			cartItemResource.patchCartItemByExternalReferenceCode(
				postCartItem.getExternalReferenceCode(), randomPatchCartItem);

		CartItem expectedPatchCartItem = postCartItem.clone();

		BeanTestUtil.copyProperties(randomPatchCartItem, expectedPatchCartItem);

		CartItem getCartItem =
			cartItemResource.getCartItemByExternalReferenceCode(
				patchCartItem.getExternalReferenceCode());

		assertEquals(expectedPatchCartItem, getCartItem);
		assertValid(getCartItem);
	}

	protected CartItem testPatchCartItemByExternalReferenceCode_addCartItem()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutCartItemByExternalReferenceCode() throws Exception {
		CartItem postCartItem =
			testPutCartItemByExternalReferenceCode_addCartItem();

		CartItem randomCartItem = randomCartItem();

		CartItem putCartItem =
			cartItemResource.putCartItemByExternalReferenceCode(
				postCartItem.getExternalReferenceCode(), randomCartItem);

		assertEquals(randomCartItem, putCartItem);
		assertValid(putCartItem);

		CartItem getCartItem =
			cartItemResource.getCartItemByExternalReferenceCode(
				putCartItem.getExternalReferenceCode());

		assertEquals(randomCartItem, getCartItem);
		assertValid(getCartItem);

		CartItem newCartItem =
			testPutCartItemByExternalReferenceCode_createCartItem();

		putCartItem = cartItemResource.putCartItemByExternalReferenceCode(
			newCartItem.getExternalReferenceCode(), newCartItem);

		assertEquals(newCartItem, putCartItem);
		assertValid(putCartItem);

		getCartItem = cartItemResource.getCartItemByExternalReferenceCode(
			putCartItem.getExternalReferenceCode());

		assertEquals(newCartItem, getCartItem);

		Assert.assertEquals(
			newCartItem.getExternalReferenceCode(),
			putCartItem.getExternalReferenceCode());
	}

	protected CartItem testPutCartItemByExternalReferenceCode_createCartItem()
		throws Exception {

		return randomCartItem();
	}

	protected CartItem testPutCartItemByExternalReferenceCode_addCartItem()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteCartItem() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		CartItem cartItem = testDeleteCartItem_addCartItem();

		assertHttpResponseStatusCode(
			204, cartItemResource.deleteCartItemHttpResponse(cartItem.getId()));

		assertHttpResponseStatusCode(
			404, cartItemResource.getCartItemHttpResponse(cartItem.getId()));

		assertHttpResponseStatusCode(
			404, cartItemResource.getCartItemHttpResponse(0L));
	}

	protected CartItem testDeleteCartItem_addCartItem() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteCartItem() throws Exception {

		// No namespace

		CartItem cartItem1 = testGraphQLDeleteCartItem_addCartItem();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteCartItem",
						new HashMap<String, Object>() {
							{
								put("cartItemId", cartItem1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteCartItem"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"cartItem",
					new HashMap<String, Object>() {
						{
							put("cartItemId", cartItem1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		CartItem cartItem2 = testGraphQLDeleteCartItem_addCartItem();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"headlessCommerceDeliveryCart_v1_0",
						new GraphQLField(
							"deleteCartItem",
							new HashMap<String, Object>() {
								{
									put("cartItemId", cartItem2.getId());
								}
							}))),
				"JSONObject/data",
				"JSONObject/headlessCommerceDeliveryCart_v1_0",
				"Object/deleteCartItem"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"headlessCommerceDeliveryCart_v1_0",
					new GraphQLField(
						"cartItem",
						new HashMap<String, Object>() {
							{
								put("cartItemId", cartItem2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected CartItem testGraphQLDeleteCartItem_addCartItem()
		throws Exception {

		return testGraphQLCartItem_addCartItem();
	}

	@Test
	public void testGetCartItem() throws Exception {
		CartItem postCartItem = testGetCartItem_addCartItem();

		CartItem getCartItem = cartItemResource.getCartItem(
			postCartItem.getId());

		assertEquals(postCartItem, getCartItem);
		assertValid(getCartItem);
	}

	protected CartItem testGetCartItem_addCartItem() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetCartItem() throws Exception {
		CartItem cartItem = testGraphQLGetCartItem_addCartItem();

		// No namespace

		Assert.assertTrue(
			equals(
				cartItem,
				CartItemSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"cartItem",
								new HashMap<String, Object>() {
									{
										put("cartItemId", cartItem.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/cartItem"))));

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		Assert.assertTrue(
			equals(
				cartItem,
				CartItemSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessCommerceDeliveryCart_v1_0",
								new GraphQLField(
									"cartItem",
									new HashMap<String, Object>() {
										{
											put("cartItemId", cartItem.getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data",
						"JSONObject/headlessCommerceDeliveryCart_v1_0",
						"Object/cartItem"))));
	}

	@Test
	public void testGraphQLGetCartItemNotFound() throws Exception {
		Long irrelevantCartItemId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"cartItem",
						new HashMap<String, Object>() {
							{
								put("cartItemId", irrelevantCartItemId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessCommerceDeliveryCart_v1_0",
						new GraphQLField(
							"cartItem",
							new HashMap<String, Object>() {
								{
									put("cartItemId", irrelevantCartItemId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected CartItem testGraphQLGetCartItem_addCartItem() throws Exception {
		return testGraphQLCartItem_addCartItem();
	}

	@Test
	public void testPatchCartItem() throws Exception {
		CartItem postCartItem = testPatchCartItem_addCartItem();

		CartItem randomPatchCartItem = randomPatchCartItem();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		CartItem patchCartItem = cartItemResource.patchCartItem(
			postCartItem.getId(), randomPatchCartItem);

		CartItem expectedPatchCartItem = postCartItem.clone();

		BeanTestUtil.copyProperties(randomPatchCartItem, expectedPatchCartItem);

		CartItem getCartItem = cartItemResource.getCartItem(
			patchCartItem.getId());

		assertEquals(expectedPatchCartItem, getCartItem);
		assertValid(getCartItem);
	}

	protected CartItem testPatchCartItem_addCartItem() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutCartItem() throws Exception {
		CartItem postCartItem = testPutCartItem_addCartItem();

		CartItem randomCartItem = randomCartItem();

		CartItem putCartItem = cartItemResource.putCartItem(
			postCartItem.getId(), randomCartItem);

		assertEquals(randomCartItem, putCartItem);
		assertValid(putCartItem);

		CartItem getCartItem = cartItemResource.getCartItem(
			putCartItem.getId());

		assertEquals(randomCartItem, getCartItem);
		assertValid(getCartItem);
	}

	protected CartItem testPutCartItem_addCartItem() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetCartByExternalReferenceCodeItemsPage() throws Exception {
		String externalReferenceCode =
			testGetCartByExternalReferenceCodeItemsPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetCartByExternalReferenceCodeItemsPage_getIrrelevantExternalReferenceCode();

		Page<CartItem> page =
			cartItemResource.getCartByExternalReferenceCodeItemsPage(
				externalReferenceCode, null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			CartItem irrelevantCartItem =
				testGetCartByExternalReferenceCodeItemsPage_addCartItem(
					irrelevantExternalReferenceCode,
					randomIrrelevantCartItem());

			page = cartItemResource.getCartByExternalReferenceCodeItemsPage(
				irrelevantExternalReferenceCode, null,
				Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(irrelevantCartItem, (List<CartItem>)page.getItems());
			assertValid(
				page,
				testGetCartByExternalReferenceCodeItemsPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		CartItem cartItem1 =
			testGetCartByExternalReferenceCodeItemsPage_addCartItem(
				externalReferenceCode, randomCartItem());

		CartItem cartItem2 =
			testGetCartByExternalReferenceCodeItemsPage_addCartItem(
				externalReferenceCode, randomCartItem());

		page = cartItemResource.getCartByExternalReferenceCodeItemsPage(
			externalReferenceCode, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(cartItem1, (List<CartItem>)page.getItems());
		assertContains(cartItem2, (List<CartItem>)page.getItems());
		assertValid(
			page,
			testGetCartByExternalReferenceCodeItemsPage_getExpectedActions(
				externalReferenceCode));

		cartItemResource.deleteCartItem(cartItem1.getId());

		cartItemResource.deleteCartItem(cartItem2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetCartByExternalReferenceCodeItemsPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetCartByExternalReferenceCodeItemsPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetCartByExternalReferenceCodeItemsPage_getExternalReferenceCode();

		Page<CartItem> cartItemPage =
			cartItemResource.getCartByExternalReferenceCodeItemsPage(
				externalReferenceCode, null, null);

		int totalCount = GetterUtil.getInteger(cartItemPage.getTotalCount());

		CartItem cartItem1 =
			testGetCartByExternalReferenceCodeItemsPage_addCartItem(
				externalReferenceCode, randomCartItem());

		CartItem cartItem2 =
			testGetCartByExternalReferenceCodeItemsPage_addCartItem(
				externalReferenceCode, randomCartItem());

		CartItem cartItem3 =
			testGetCartByExternalReferenceCodeItemsPage_addCartItem(
				externalReferenceCode, randomCartItem());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<CartItem> page1 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
						pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(cartItem1, (List<CartItem>)page1.getItems());

			Page<CartItem> page2 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(cartItem2, (List<CartItem>)page2.getItems());

			Page<CartItem> page3 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(cartItem3, (List<CartItem>)page3.getItems());
		}
		else {
			Page<CartItem> page1 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(1, totalCount + 2));

			List<CartItem> cartItems1 = (List<CartItem>)page1.getItems();

			Assert.assertEquals(
				cartItems1.toString(), totalCount + 2, cartItems1.size());

			Page<CartItem> page2 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<CartItem> cartItems2 = (List<CartItem>)page2.getItems();

			Assert.assertEquals(cartItems2.toString(), 1, cartItems2.size());

			Page<CartItem> page3 =
				cartItemResource.getCartByExternalReferenceCodeItemsPage(
					externalReferenceCode, null,
					Pagination.of(1, (int)totalCount + 3));

			assertContains(cartItem1, (List<CartItem>)page3.getItems());
			assertContains(cartItem2, (List<CartItem>)page3.getItems());
			assertContains(cartItem3, (List<CartItem>)page3.getItems());
		}
	}

	protected CartItem testGetCartByExternalReferenceCodeItemsPage_addCartItem(
			String externalReferenceCode, CartItem cartItem)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetCartByExternalReferenceCodeItemsPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetCartByExternalReferenceCodeItemsPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostCartByExternalReferenceCodeItem() throws Exception {
		CartItem randomCartItem = randomCartItem();

		CartItem postCartItem =
			testPostCartByExternalReferenceCodeItem_addCartItem(randomCartItem);

		assertEquals(randomCartItem, postCartItem);
		assertValid(postCartItem);
	}

	protected CartItem testPostCartByExternalReferenceCodeItem_addCartItem(
			CartItem cartItem)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetCartItemsPage() throws Exception {
		Long cartId = testGetCartItemsPage_getCartId();
		Long irrelevantCartId = testGetCartItemsPage_getIrrelevantCartId();

		Page<CartItem> page = cartItemResource.getCartItemsPage(
			cartId, null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantCartId != null) {
			CartItem irrelevantCartItem = testGetCartItemsPage_addCartItem(
				irrelevantCartId, randomIrrelevantCartItem());

			page = cartItemResource.getCartItemsPage(
				irrelevantCartId, null, Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(irrelevantCartItem, (List<CartItem>)page.getItems());
			assertValid(
				page,
				testGetCartItemsPage_getExpectedActions(irrelevantCartId));
		}

		CartItem cartItem1 = testGetCartItemsPage_addCartItem(
			cartId, randomCartItem());

		CartItem cartItem2 = testGetCartItemsPage_addCartItem(
			cartId, randomCartItem());

		page = cartItemResource.getCartItemsPage(
			cartId, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(cartItem1, (List<CartItem>)page.getItems());
		assertContains(cartItem2, (List<CartItem>)page.getItems());
		assertValid(page, testGetCartItemsPage_getExpectedActions(cartId));

		cartItemResource.deleteCartItem(cartItem1.getId());

		cartItemResource.deleteCartItem(cartItem2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetCartItemsPage_getExpectedActions(Long cartId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetCartItemsPageWithPagination() throws Exception {
		Long cartId = testGetCartItemsPage_getCartId();

		Page<CartItem> cartItemPage = cartItemResource.getCartItemsPage(
			cartId, null, null);

		int totalCount = GetterUtil.getInteger(cartItemPage.getTotalCount());

		CartItem cartItem1 = testGetCartItemsPage_addCartItem(
			cartId, randomCartItem());

		CartItem cartItem2 = testGetCartItemsPage_addCartItem(
			cartId, randomCartItem());

		CartItem cartItem3 = testGetCartItemsPage_addCartItem(
			cartId, randomCartItem());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<CartItem> page1 = cartItemResource.getCartItemsPage(
				cartId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
					pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(cartItem1, (List<CartItem>)page1.getItems());

			Page<CartItem> page2 = cartItemResource.getCartItemsPage(
				cartId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(cartItem2, (List<CartItem>)page2.getItems());

			Page<CartItem> page3 = cartItemResource.getCartItemsPage(
				cartId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(cartItem3, (List<CartItem>)page3.getItems());
		}
		else {
			Page<CartItem> page1 = cartItemResource.getCartItemsPage(
				cartId, null, Pagination.of(1, totalCount + 2));

			List<CartItem> cartItems1 = (List<CartItem>)page1.getItems();

			Assert.assertEquals(
				cartItems1.toString(), totalCount + 2, cartItems1.size());

			Page<CartItem> page2 = cartItemResource.getCartItemsPage(
				cartId, null, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<CartItem> cartItems2 = (List<CartItem>)page2.getItems();

			Assert.assertEquals(cartItems2.toString(), 1, cartItems2.size());

			Page<CartItem> page3 = cartItemResource.getCartItemsPage(
				cartId, null, Pagination.of(1, (int)totalCount + 3));

			assertContains(cartItem1, (List<CartItem>)page3.getItems());
			assertContains(cartItem2, (List<CartItem>)page3.getItems());
			assertContains(cartItem3, (List<CartItem>)page3.getItems());
		}
	}

	protected CartItem testGetCartItemsPage_addCartItem(
			Long cartId, CartItem cartItem)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetCartItemsPage_getCartId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetCartItemsPage_getIrrelevantCartId() throws Exception {
		return null;
	}

	@Test
	public void testGraphQLGetCartItemsPage() throws Exception {
		Long cartId = testGetCartItemsPage_getCartId();

		GraphQLField graphQLField = new GraphQLField(
			"cartItems",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);

					put("cartId", cartId);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		// No namespace

		JSONObject cartItemsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/cartItems");

		long totalCount = cartItemsJSONObject.getLong("totalCount");

		CartItem cartItem1 = testGraphQLGetCartItemsPage_addCartItem();
		CartItem cartItem2 = testGraphQLGetCartItemsPage_addCartItem();

		cartItemsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/cartItems");

		Assert.assertEquals(
			totalCount + 2, cartItemsJSONObject.getLong("totalCount"));

		assertContains(
			cartItem1,
			Arrays.asList(
				CartItemSerDes.toDTOs(cartItemsJSONObject.getString("items"))));
		assertContains(
			cartItem2,
			Arrays.asList(
				CartItemSerDes.toDTOs(cartItemsJSONObject.getString("items"))));

		// Using the namespace headlessCommerceDeliveryCart_v1_0

		cartItemsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(
				new GraphQLField(
					"headlessCommerceDeliveryCart_v1_0", graphQLField)),
			"JSONObject/data", "JSONObject/headlessCommerceDeliveryCart_v1_0",
			"JSONObject/cartItems");

		Assert.assertEquals(
			totalCount + 2, cartItemsJSONObject.getLong("totalCount"));

		assertContains(
			cartItem1,
			Arrays.asList(
				CartItemSerDes.toDTOs(cartItemsJSONObject.getString("items"))));
		assertContains(
			cartItem2,
			Arrays.asList(
				CartItemSerDes.toDTOs(cartItemsJSONObject.getString("items"))));
	}

	protected CartItem testGraphQLGetCartItemsPage_addCartItem()
		throws Exception {

		return testGraphQLCartItem_addCartItem();
	}

	@Test
	public void testPostCartItem() throws Exception {
		CartItem randomCartItem = randomCartItem();

		CartItem postCartItem = testPostCartItem_addCartItem(randomCartItem);

		assertEquals(randomCartItem, postCartItem);
		assertValid(postCartItem);
	}

	protected CartItem testPostCartItem_addCartItem(CartItem cartItem)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected CartItem testGraphQLCartItem_addCartItem() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(CartItem cartItem, List<CartItem> cartItems) {
		boolean contains = false;

		for (CartItem item : cartItems) {
			if (equals(cartItem, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			cartItems + " does not contain " + cartItem, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(CartItem cartItem1, CartItem cartItem2) {
		Assert.assertTrue(
			cartItem1 + " does not equal " + cartItem2,
			equals(cartItem1, cartItem2));
	}

	protected void assertEquals(
		List<CartItem> cartItems1, List<CartItem> cartItems2) {

		Assert.assertEquals(cartItems1.size(), cartItems2.size());

		for (int i = 0; i < cartItems1.size(); i++) {
			CartItem cartItem1 = cartItems1.get(i);
			CartItem cartItem2 = cartItems2.get(i);

			assertEquals(cartItem1, cartItem2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<CartItem> cartItems1, List<CartItem> cartItems2) {

		Assert.assertEquals(cartItems1.size(), cartItems2.size());

		for (CartItem cartItem1 : cartItems1) {
			boolean contains = false;

			for (CartItem cartItem2 : cartItems2) {
				if (equals(cartItem1, cartItem2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				cartItems2 + " does not contain " + cartItem1, contains);
		}
	}

	protected void assertValid(CartItem cartItem) throws Exception {
		boolean valid = true;

		if (cartItem.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"adaptiveMediaImageHTMLTag", additionalAssertFieldName)) {

				if (cartItem.getAdaptiveMediaImageHTMLTag() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("cartItems", additionalAssertFieldName)) {
				if (cartItem.getCartItems() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("customFields", additionalAssertFieldName)) {
				if (cartItem.getCustomFields() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("deliveryGroup", additionalAssertFieldName)) {
				if (cartItem.getDeliveryGroup() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"deliveryGroupName", additionalAssertFieldName)) {

				if (cartItem.getDeliveryGroupName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("errorMessages", additionalAssertFieldName)) {
				if (cartItem.getErrorMessages() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (cartItem.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (cartItem.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (cartItem.getOptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("parentCartItemId", additionalAssertFieldName)) {
				if (cartItem.getParentCartItemId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("price", additionalAssertFieldName)) {
				if (cartItem.getPrice() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (cartItem.getProductId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productURLs", additionalAssertFieldName)) {
				if (cartItem.getProductURLs() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("quantity", additionalAssertFieldName)) {
				if (cartItem.getQuantity() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("replacedSku", additionalAssertFieldName)) {
				if (cartItem.getReplacedSku() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"replacedSkuExternalReferenceCode",
					additionalAssertFieldName)) {

				if (cartItem.getReplacedSkuExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("replacedSkuId", additionalAssertFieldName)) {
				if (cartItem.getReplacedSkuId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"requestedDeliveryDate", additionalAssertFieldName)) {

				if (cartItem.getRequestedDeliveryDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("settings", additionalAssertFieldName)) {
				if (cartItem.getSettings() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("shippingAddress", additionalAssertFieldName)) {
				if (cartItem.getShippingAddress() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"shippingAddressExternalReferenceCode",
					additionalAssertFieldName)) {

				if (cartItem.getShippingAddressExternalReferenceCode() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"shippingAddressId", additionalAssertFieldName)) {

				if (cartItem.getShippingAddressId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("sku", additionalAssertFieldName)) {
				if (cartItem.getSku() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("skuId", additionalAssertFieldName)) {
				if (cartItem.getSkuId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("skuUnitOfMeasure", additionalAssertFieldName)) {
				if (cartItem.getSkuUnitOfMeasure() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("subscription", additionalAssertFieldName)) {
				if (cartItem.getSubscription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("thumbnail", additionalAssertFieldName)) {
				if (cartItem.getThumbnail() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("unitOfMeasure", additionalAssertFieldName)) {
				if (cartItem.getUnitOfMeasure() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("valid", additionalAssertFieldName)) {
				if (cartItem.getValid() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<CartItem> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<CartItem> page, Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<CartItem> cartItems = page.getItems();

		int size = cartItems.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);

		assertValid(page.getActions(), expectedActions);
	}

	protected void assertValid(
		Map<String, Map<String, String>> actions1,
		Map<String, Map<String, String>> actions2) {

		for (String key : actions2.keySet()) {
			Map action = actions1.get(key);

			Assert.assertNotNull(key + " does not contain an action", action);

			Map<String, String> expectedAction = actions2.get(key);

			Assert.assertEquals(
				expectedAction.get("method"), action.get("method"));
			Assert.assertEquals(expectedAction.get("href"), action.get("href"));
		}
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.headless.commerce.delivery.cart.dto.v1_0.
						CartItem.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(CartItem cartItem1, CartItem cartItem2) {
		if (cartItem1 == cartItem2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"adaptiveMediaImageHTMLTag", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getAdaptiveMediaImageHTMLTag(),
						cartItem2.getAdaptiveMediaImageHTMLTag())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("cartItems", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getCartItems(), cartItem2.getCartItems())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("customFields", additionalAssertFieldName)) {
				if (!equals(
						(Map)cartItem1.getCustomFields(),
						(Map)cartItem2.getCustomFields())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("deliveryGroup", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getDeliveryGroup(),
						cartItem2.getDeliveryGroup())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"deliveryGroupName", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getDeliveryGroupName(),
						cartItem2.getDeliveryGroupName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("errorMessages", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getErrorMessages(),
						cartItem2.getErrorMessages())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getExternalReferenceCode(),
						cartItem2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(cartItem1.getId(), cartItem2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getName(), cartItem2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getOptions(), cartItem2.getOptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("parentCartItemId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getParentCartItemId(),
						cartItem2.getParentCartItemId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("price", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getPrice(), cartItem2.getPrice())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getProductId(), cartItem2.getProductId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productURLs", additionalAssertFieldName)) {
				if (!equals(
						(Map)cartItem1.getProductURLs(),
						(Map)cartItem2.getProductURLs())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("quantity", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getQuantity(), cartItem2.getQuantity())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("replacedSku", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getReplacedSku(),
						cartItem2.getReplacedSku())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"replacedSkuExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getReplacedSkuExternalReferenceCode(),
						cartItem2.getReplacedSkuExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("replacedSkuId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getReplacedSkuId(),
						cartItem2.getReplacedSkuId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"requestedDeliveryDate", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getRequestedDeliveryDate(),
						cartItem2.getRequestedDeliveryDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("settings", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getSettings(), cartItem2.getSettings())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("shippingAddress", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getShippingAddress(),
						cartItem2.getShippingAddress())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"shippingAddressExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getShippingAddressExternalReferenceCode(),
						cartItem2.getShippingAddressExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"shippingAddressId", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						cartItem1.getShippingAddressId(),
						cartItem2.getShippingAddressId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("sku", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getSku(), cartItem2.getSku())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("skuId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getSkuId(), cartItem2.getSkuId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("skuUnitOfMeasure", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getSkuUnitOfMeasure(),
						cartItem2.getSkuUnitOfMeasure())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("subscription", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getSubscription(),
						cartItem2.getSubscription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("thumbnail", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getThumbnail(), cartItem2.getThumbnail())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("unitOfMeasure", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getUnitOfMeasure(),
						cartItem2.getUnitOfMeasure())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("valid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						cartItem1.getValid(), cartItem2.getValid())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		if (clazz.getClassLoader() == null) {
			return new java.lang.reflect.Field[0];
		}

		return TransformUtil.transform(
			ReflectionUtil.getDeclaredFields(clazz),
			field -> {
				if (field.isSynthetic()) {
					return null;
				}

				return field;
			},
			java.lang.reflect.Field.class);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_cartItemResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_cartItemResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		if (entityModel == null) {
			return Collections.emptyList();
		}

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		return TransformUtil.transform(
			getEntityFields(),
			entityField -> {
				if (!Objects.equals(entityField.getType(), type) ||
					ArrayUtil.contains(
						getIgnoredEntityFieldNames(), entityField.getName())) {

					return null;
				}

				return entityField;
			});
	}

	protected String getFilterString(
		EntityField entityField, String operator, CartItem cartItem) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("adaptiveMediaImageHTMLTag")) {
			Object object = cartItem.getAdaptiveMediaImageHTMLTag();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("cartItems")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("customFields")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("deliveryGroup")) {
			Object object = cartItem.getDeliveryGroup();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("deliveryGroupName")) {
			Object object = cartItem.getDeliveryGroupName();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("errorMessages")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			Object object = cartItem.getExternalReferenceCode();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			Object object = cartItem.getName();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("options")) {
			Object object = cartItem.getOptions();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("parentCartItemId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("price")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productURLs")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("quantity")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("replacedSku")) {
			Object object = cartItem.getReplacedSku();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("replacedSkuExternalReferenceCode")) {
			Object object = cartItem.getReplacedSkuExternalReferenceCode();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("replacedSkuId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("requestedDeliveryDate")) {
			if (operator.equals("between")) {
				Date date = cartItem.getRequestedDeliveryDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(cartItem.getRequestedDeliveryDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("settings")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("shippingAddress")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("shippingAddressExternalReferenceCode")) {
			Object object = cartItem.getShippingAddressExternalReferenceCode();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("shippingAddressId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("sku")) {
			Object object = cartItem.getSku();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("skuId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("skuUnitOfMeasure")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("subscription")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("thumbnail")) {
			Object object = cartItem.getThumbnail();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("unitOfMeasure")) {
			Object object = cartItem.getUnitOfMeasure();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("valid")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword(
			"test@liferay.com:" + PropsValues.DEFAULT_ADMIN_PASSWORD);

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected CartItem randomCartItem() throws Exception {
		return new CartItem() {
			{
				adaptiveMediaImageHTMLTag = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				deliveryGroup = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				deliveryGroupName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				options = StringUtil.toLowerCase(RandomTestUtil.randomString());
				parentCartItemId = RandomTestUtil.randomLong();
				productId = RandomTestUtil.randomLong();
				replacedSku = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				replacedSkuExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				replacedSkuId = RandomTestUtil.randomLong();
				requestedDeliveryDate = RandomTestUtil.nextDate();
				shippingAddressExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				shippingAddressId = RandomTestUtil.randomLong();
				sku = StringUtil.toLowerCase(RandomTestUtil.randomString());
				skuId = RandomTestUtil.randomLong();
				subscription = RandomTestUtil.randomBoolean();
				thumbnail = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				unitOfMeasure = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				valid = RandomTestUtil.randomBoolean();
			}
		};
	}

	protected CartItem randomIrrelevantCartItem() throws Exception {
		CartItem randomIrrelevantCartItem = randomCartItem();

		return randomIrrelevantCartItem;
	}

	protected CartItem randomPatchCartItem() throws Exception {
		return randomCartItem();
	}

	protected CartItemResource cartItemResource;
	protected com.liferay.portal.kernel.model.Group irrelevantGroup;
	protected com.liferay.portal.kernel.model.Company testCompany;
	protected com.liferay.portal.kernel.model.Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = source.getClass();

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					_getAllDeclaredFields(sourceClass)) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				try {
					Method setMethod = _getMethod(
						targetClass, field.getName(), "set",
						getMethod.getReturnType());

					setMethod.invoke(target, getMethod.invoke(source));
				}
				catch (Exception e) {
					continue;
				}
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static List<java.lang.reflect.Field> _getAllDeclaredFields(
			Class<?> clazz) {

			List<java.lang.reflect.Field> fields = new ArrayList<>();

			while ((clazz != null) && (clazz != Object.class)) {
				for (java.lang.reflect.Field field :
						clazz.getDeclaredFields()) {

					fields.add(field);
				}

				clazz = clazz.getSuperclass();
			}

			return fields;
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseCartItemResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.headless.commerce.delivery.cart.resource.v1_0.
			CartItemResource _cartItemResource;

}