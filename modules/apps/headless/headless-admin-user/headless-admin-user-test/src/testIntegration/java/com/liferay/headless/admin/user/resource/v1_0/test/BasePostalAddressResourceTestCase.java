/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.admin.user.client.dto.v1_0.PostalAddress;
import com.liferay.headless.admin.user.client.http.HttpInvoker;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.resource.v1_0.PostalAddressResource;
import com.liferay.headless.admin.user.client.serdes.v1_0.PostalAddressSerDes;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
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
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BasePostalAddressResourceTestCase {

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

		_postalAddressResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		PostalAddressResource.Builder builder = PostalAddressResource.builder();

		postalAddressResource = builder.authentication(
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

		PostalAddress postalAddress1 = randomPostalAddress();

		String json = objectMapper.writeValueAsString(postalAddress1);

		PostalAddress postalAddress2 = PostalAddressSerDes.toDTO(json);

		Assert.assertTrue(equals(postalAddress1, postalAddress2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		PostalAddress postalAddress = randomPostalAddress();

		String json1 = objectMapper.writeValueAsString(postalAddress);
		String json2 = PostalAddressSerDes.toJSON(postalAddress);

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

		PostalAddress postalAddress = randomPostalAddress();

		postalAddress.setAddressCountry(regex);
		postalAddress.setAddressLocality(regex);
		postalAddress.setAddressRegion(regex);
		postalAddress.setAddressType(regex);
		postalAddress.setExternalReferenceCode(regex);
		postalAddress.setName(regex);
		postalAddress.setPhoneNumber(regex);
		postalAddress.setPostalCode(regex);
		postalAddress.setStreetAddressLine1(regex);
		postalAddress.setStreetAddressLine2(regex);
		postalAddress.setStreetAddressLine3(regex);

		String json = PostalAddressSerDes.toJSON(postalAddress);

		Assert.assertFalse(json.contains(regex));

		postalAddress = PostalAddressSerDes.toDTO(json);

		Assert.assertEquals(regex, postalAddress.getAddressCountry());
		Assert.assertEquals(regex, postalAddress.getAddressLocality());
		Assert.assertEquals(regex, postalAddress.getAddressRegion());
		Assert.assertEquals(regex, postalAddress.getAddressType());
		Assert.assertEquals(regex, postalAddress.getExternalReferenceCode());
		Assert.assertEquals(regex, postalAddress.getName());
		Assert.assertEquals(regex, postalAddress.getPhoneNumber());
		Assert.assertEquals(regex, postalAddress.getPostalCode());
		Assert.assertEquals(regex, postalAddress.getStreetAddressLine1());
		Assert.assertEquals(regex, postalAddress.getStreetAddressLine2());
		Assert.assertEquals(regex, postalAddress.getStreetAddressLine3());
	}

	@Test
	public void testGetAccountByExternalReferenceCodePostalAddressesPage()
		throws Exception {

		String externalReferenceCode =
			testGetAccountByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetAccountByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode();

		Page<PostalAddress> page =
			postalAddressResource.
				getAccountByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			PostalAddress irrelevantPostalAddress =
				testGetAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
					irrelevantExternalReferenceCode,
					randomIrrelevantPostalAddress());

			page =
				postalAddressResource.
					getAccountByExternalReferenceCodePostalAddressesPage(
						irrelevantExternalReferenceCode);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		PostalAddress postalAddress1 =
			testGetAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		page =
			postalAddressResource.
				getAccountByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				externalReferenceCode));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PostalAddress
			testGetAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				String externalReferenceCode, PostalAddress postalAddress)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountPostalAddressesPage() throws Exception {
		Long accountId = testGetAccountPostalAddressesPage_getAccountId();
		Long irrelevantAccountId =
			testGetAccountPostalAddressesPage_getIrrelevantAccountId();

		Page<PostalAddress> page =
			postalAddressResource.getAccountPostalAddressesPage(accountId);

		long totalCount = page.getTotalCount();

		if (irrelevantAccountId != null) {
			PostalAddress irrelevantPostalAddress =
				testGetAccountPostalAddressesPage_addPostalAddress(
					irrelevantAccountId, randomIrrelevantPostalAddress());

			page = postalAddressResource.getAccountPostalAddressesPage(
				irrelevantAccountId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetAccountPostalAddressesPage_getExpectedActions(
					irrelevantAccountId));
		}

		PostalAddress postalAddress1 =
			testGetAccountPostalAddressesPage_addPostalAddress(
				accountId, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetAccountPostalAddressesPage_addPostalAddress(
				accountId, randomPostalAddress());

		page = postalAddressResource.getAccountPostalAddressesPage(accountId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetAccountPostalAddressesPage_getExpectedActions(accountId));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetAccountPostalAddressesPage_getExpectedActions(Long accountId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		Map createBatchAction = new HashMap<>();
		createBatchAction.put("method", "POST");
		createBatchAction.put(
			"href",
			"http://localhost:8080/o/headless-admin-user/v1.0/accounts/{accountId}/postal-addresses/batch".
				replace("{accountId}", String.valueOf(accountId)));

		expectedActions.put("createBatch", createBatchAction);

		return expectedActions;
	}

	protected PostalAddress testGetAccountPostalAddressesPage_addPostalAddress(
			Long accountId, PostalAddress postalAddress)
		throws Exception {

		return postalAddressResource.postAccountPostalAddress(
			accountId, postalAddress);
	}

	protected Long testGetAccountPostalAddressesPage_getAccountId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetAccountPostalAddressesPage_getIrrelevantAccountId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountPostalAddress() throws Exception {
		PostalAddress randomPostalAddress = randomPostalAddress();

		PostalAddress postPostalAddress =
			testPostAccountPostalAddress_addPostalAddress(randomPostalAddress);

		assertEquals(randomPostalAddress, postPostalAddress);
		assertValid(postPostalAddress);
	}

	protected PostalAddress testPostAccountPostalAddress_addPostalAddress(
			PostalAddress postalAddress)
		throws Exception {

		return postalAddressResource.postAccountPostalAddress(
			testGetAccountPostalAddressesPage_getAccountId(), postalAddress);
	}

	@Test
	public void testGetOrganizationByExternalReferenceCodePostalAddressesPage()
		throws Exception {

		String externalReferenceCode =
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode();

		Page<PostalAddress> page =
			postalAddressResource.
				getOrganizationByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			PostalAddress irrelevantPostalAddress =
				testGetOrganizationByExternalReferenceCodePostalAddressesPage_addPostalAddress(
					irrelevantExternalReferenceCode,
					randomIrrelevantPostalAddress());

			page =
				postalAddressResource.
					getOrganizationByExternalReferenceCodePostalAddressesPage(
						irrelevantExternalReferenceCode);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetOrganizationByExternalReferenceCodePostalAddressesPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		PostalAddress postalAddress1 =
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		page =
			postalAddressResource.
				getOrganizationByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				externalReferenceCode));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PostalAddress
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				String externalReferenceCode, PostalAddress postalAddress)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetOrganizationByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testGetOrganizationPostalAddressesPage() throws Exception {
		String organizationId =
			testGetOrganizationPostalAddressesPage_getOrganizationId();
		String irrelevantOrganizationId =
			testGetOrganizationPostalAddressesPage_getIrrelevantOrganizationId();

		Page<PostalAddress> page =
			postalAddressResource.getOrganizationPostalAddressesPage(
				organizationId);

		long totalCount = page.getTotalCount();

		if (irrelevantOrganizationId != null) {
			PostalAddress irrelevantPostalAddress =
				testGetOrganizationPostalAddressesPage_addPostalAddress(
					irrelevantOrganizationId, randomIrrelevantPostalAddress());

			page = postalAddressResource.getOrganizationPostalAddressesPage(
				irrelevantOrganizationId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetOrganizationPostalAddressesPage_getExpectedActions(
					irrelevantOrganizationId));
		}

		PostalAddress postalAddress1 =
			testGetOrganizationPostalAddressesPage_addPostalAddress(
				organizationId, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetOrganizationPostalAddressesPage_addPostalAddress(
				organizationId, randomPostalAddress());

		page = postalAddressResource.getOrganizationPostalAddressesPage(
			organizationId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetOrganizationPostalAddressesPage_getExpectedActions(
				organizationId));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetOrganizationPostalAddressesPage_getExpectedActions(
				String organizationId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PostalAddress
			testGetOrganizationPostalAddressesPage_addPostalAddress(
				String organizationId, PostalAddress postalAddress)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetOrganizationPostalAddressesPage_getOrganizationId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetOrganizationPostalAddressesPage_getIrrelevantOrganizationId()
		throws Exception {

		return null;
	}

	@Test
	public void testDeletePostalAddressByExternalReferenceCode()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		PostalAddress postalAddress =
			testDeletePostalAddressByExternalReferenceCode_addPostalAddress();

		assertHttpResponseStatusCode(
			204,
			postalAddressResource.
				deletePostalAddressByExternalReferenceCodeHttpResponse(
					postalAddress.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			postalAddressResource.
				getPostalAddressByExternalReferenceCodeHttpResponse(
					postalAddress.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			postalAddressResource.
				getPostalAddressByExternalReferenceCodeHttpResponse(
					postalAddress.getExternalReferenceCode()));
	}

	protected PostalAddress
			testDeletePostalAddressByExternalReferenceCode_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetPostalAddressByExternalReferenceCode() throws Exception {
		PostalAddress postPostalAddress =
			testGetPostalAddressByExternalReferenceCode_addPostalAddress();

		PostalAddress getPostalAddress =
			postalAddressResource.getPostalAddressByExternalReferenceCode(
				postPostalAddress.getExternalReferenceCode());

		assertEquals(postPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);
	}

	protected PostalAddress
			testGetPostalAddressByExternalReferenceCode_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetPostalAddressByExternalReferenceCode()
		throws Exception {

		PostalAddress postalAddress =
			testGraphQLGetPostalAddressByExternalReferenceCode_addPostalAddress();

		// No namespace

		Assert.assertTrue(
			equals(
				postalAddress,
				PostalAddressSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"postalAddressByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"externalReferenceCode",
											"\"" +
												postalAddress.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/postalAddressByExternalReferenceCode"))));

		// Using the namespace headlessAdminUser_v1_0

		Assert.assertTrue(
			equals(
				postalAddress,
				PostalAddressSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessAdminUser_v1_0",
								new GraphQLField(
									"postalAddressByExternalReferenceCode",
									new HashMap<String, Object>() {
										{
											put(
												"externalReferenceCode",
												"\"" +
													postalAddress.
														getExternalReferenceCode() +
															"\"");
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/headlessAdminUser_v1_0",
						"Object/postalAddressByExternalReferenceCode"))));
	}

	@Test
	public void testGraphQLGetPostalAddressByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"postalAddressByExternalReferenceCode",
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

		// Using the namespace headlessAdminUser_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessAdminUser_v1_0",
						new GraphQLField(
							"postalAddressByExternalReferenceCode",
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

	protected PostalAddress
			testGraphQLGetPostalAddressByExternalReferenceCode_addPostalAddress()
		throws Exception {

		return testGraphQLPostalAddress_addPostalAddress();
	}

	@Test
	public void testPatchPostalAddressByExternalReferenceCode()
		throws Exception {

		PostalAddress postPostalAddress =
			testPatchPostalAddressByExternalReferenceCode_addPostalAddress();

		PostalAddress randomPatchPostalAddress = randomPatchPostalAddress();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		PostalAddress patchPostalAddress =
			postalAddressResource.patchPostalAddressByExternalReferenceCode(
				postPostalAddress.getExternalReferenceCode(),
				randomPatchPostalAddress);

		PostalAddress expectedPatchPostalAddress = postPostalAddress.clone();

		BeanTestUtil.copyProperties(
			randomPatchPostalAddress, expectedPatchPostalAddress);

		PostalAddress getPostalAddress =
			postalAddressResource.getPostalAddressByExternalReferenceCode(
				patchPostalAddress.getExternalReferenceCode());

		assertEquals(expectedPatchPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);
	}

	protected PostalAddress
			testPatchPostalAddressByExternalReferenceCode_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutPostalAddressByExternalReferenceCode() throws Exception {
		PostalAddress postPostalAddress =
			testPutPostalAddressByExternalReferenceCode_addPostalAddress();

		PostalAddress randomPostalAddress = randomPostalAddress();

		PostalAddress putPostalAddress =
			postalAddressResource.putPostalAddressByExternalReferenceCode(
				postPostalAddress.getExternalReferenceCode(),
				randomPostalAddress);

		assertEquals(randomPostalAddress, putPostalAddress);
		assertValid(putPostalAddress);

		PostalAddress getPostalAddress =
			postalAddressResource.getPostalAddressByExternalReferenceCode(
				putPostalAddress.getExternalReferenceCode());

		assertEquals(randomPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);

		PostalAddress newPostalAddress =
			testPutPostalAddressByExternalReferenceCode_createPostalAddress();

		putPostalAddress =
			postalAddressResource.putPostalAddressByExternalReferenceCode(
				newPostalAddress.getExternalReferenceCode(), newPostalAddress);

		assertEquals(newPostalAddress, putPostalAddress);
		assertValid(putPostalAddress);

		getPostalAddress =
			postalAddressResource.getPostalAddressByExternalReferenceCode(
				putPostalAddress.getExternalReferenceCode());

		assertEquals(newPostalAddress, getPostalAddress);

		Assert.assertEquals(
			newPostalAddress.getExternalReferenceCode(),
			putPostalAddress.getExternalReferenceCode());
	}

	protected PostalAddress
			testPutPostalAddressByExternalReferenceCode_createPostalAddress()
		throws Exception {

		return randomPostalAddress();
	}

	protected PostalAddress
			testPutPostalAddressByExternalReferenceCode_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeletePostalAddress() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		PostalAddress postalAddress =
			testDeletePostalAddress_addPostalAddress();

		assertHttpResponseStatusCode(
			204,
			postalAddressResource.deletePostalAddressHttpResponse(
				postalAddress.getId()));

		assertHttpResponseStatusCode(
			404,
			postalAddressResource.getPostalAddressHttpResponse(
				postalAddress.getId()));

		assertHttpResponseStatusCode(
			404, postalAddressResource.getPostalAddressHttpResponse(0L));
	}

	protected PostalAddress testDeletePostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeletePostalAddress() throws Exception {

		// No namespace

		PostalAddress postalAddress1 =
			testGraphQLDeletePostalAddress_addPostalAddress();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deletePostalAddress",
						new HashMap<String, Object>() {
							{
								put("postalAddressId", postalAddress1.getId());
							}
						})),
				"JSONObject/data", "Object/deletePostalAddress"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"postalAddress",
					new HashMap<String, Object>() {
						{
							put("postalAddressId", postalAddress1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace headlessAdminUser_v1_0

		PostalAddress postalAddress2 =
			testGraphQLDeletePostalAddress_addPostalAddress();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"headlessAdminUser_v1_0",
						new GraphQLField(
							"deletePostalAddress",
							new HashMap<String, Object>() {
								{
									put(
										"postalAddressId",
										postalAddress2.getId());
								}
							}))),
				"JSONObject/data", "JSONObject/headlessAdminUser_v1_0",
				"Object/deletePostalAddress"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"headlessAdminUser_v1_0",
					new GraphQLField(
						"postalAddress",
						new HashMap<String, Object>() {
							{
								put("postalAddressId", postalAddress2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected PostalAddress testGraphQLDeletePostalAddress_addPostalAddress()
		throws Exception {

		return testGraphQLPostalAddress_addPostalAddress();
	}

	@Test
	public void testGetPostalAddress() throws Exception {
		PostalAddress postPostalAddress =
			testGetPostalAddress_addPostalAddress();

		PostalAddress getPostalAddress = postalAddressResource.getPostalAddress(
			postPostalAddress.getId());

		assertEquals(postPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);
	}

	protected PostalAddress testGetPostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetPostalAddress() throws Exception {
		PostalAddress postalAddress =
			testGraphQLGetPostalAddress_addPostalAddress();

		// No namespace

		Assert.assertTrue(
			equals(
				postalAddress,
				PostalAddressSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"postalAddress",
								new HashMap<String, Object>() {
									{
										put(
											"postalAddressId",
											postalAddress.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/postalAddress"))));

		// Using the namespace headlessAdminUser_v1_0

		Assert.assertTrue(
			equals(
				postalAddress,
				PostalAddressSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessAdminUser_v1_0",
								new GraphQLField(
									"postalAddress",
									new HashMap<String, Object>() {
										{
											put(
												"postalAddressId",
												postalAddress.getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/headlessAdminUser_v1_0",
						"Object/postalAddress"))));
	}

	@Test
	public void testGraphQLGetPostalAddressNotFound() throws Exception {
		Long irrelevantPostalAddressId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"postalAddress",
						new HashMap<String, Object>() {
							{
								put(
									"postalAddressId",
									irrelevantPostalAddressId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessAdminUser_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessAdminUser_v1_0",
						new GraphQLField(
							"postalAddress",
							new HashMap<String, Object>() {
								{
									put(
										"postalAddressId",
										irrelevantPostalAddressId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected PostalAddress testGraphQLGetPostalAddress_addPostalAddress()
		throws Exception {

		return testGraphQLPostalAddress_addPostalAddress();
	}

	@Test
	public void testPatchPostalAddress() throws Exception {
		PostalAddress postPostalAddress =
			testPatchPostalAddress_addPostalAddress();

		PostalAddress randomPatchPostalAddress = randomPatchPostalAddress();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		PostalAddress patchPostalAddress =
			postalAddressResource.patchPostalAddress(
				postPostalAddress.getId(), randomPatchPostalAddress);

		PostalAddress expectedPatchPostalAddress = postPostalAddress.clone();

		BeanTestUtil.copyProperties(
			randomPatchPostalAddress, expectedPatchPostalAddress);

		PostalAddress getPostalAddress = postalAddressResource.getPostalAddress(
			patchPostalAddress.getId());

		assertEquals(expectedPatchPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);
	}

	protected PostalAddress testPatchPostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutPostalAddress() throws Exception {
		PostalAddress postPostalAddress =
			testPutPostalAddress_addPostalAddress();

		PostalAddress randomPostalAddress = randomPostalAddress();

		PostalAddress putPostalAddress = postalAddressResource.putPostalAddress(
			postPostalAddress.getId(), randomPostalAddress);

		assertEquals(randomPostalAddress, putPostalAddress);
		assertValid(putPostalAddress);

		PostalAddress getPostalAddress = postalAddressResource.getPostalAddress(
			putPostalAddress.getId());

		assertEquals(randomPostalAddress, getPostalAddress);
		assertValid(getPostalAddress);
	}

	protected PostalAddress testPutPostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetUserAccountByExternalReferenceCodePostalAddressesPage()
		throws Exception {

		String externalReferenceCode =
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode();

		Page<PostalAddress> page =
			postalAddressResource.
				getUserAccountByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			PostalAddress irrelevantPostalAddress =
				testGetUserAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
					irrelevantExternalReferenceCode,
					randomIrrelevantPostalAddress());

			page =
				postalAddressResource.
					getUserAccountByExternalReferenceCodePostalAddressesPage(
						irrelevantExternalReferenceCode);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetUserAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		PostalAddress postalAddress1 =
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				externalReferenceCode, randomPostalAddress());

		page =
			postalAddressResource.
				getUserAccountByExternalReferenceCodePostalAddressesPage(
					externalReferenceCode);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				externalReferenceCode));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PostalAddress
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_addPostalAddress(
				String externalReferenceCode, PostalAddress postalAddress)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetUserAccountByExternalReferenceCodePostalAddressesPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testGetUserAccountPostalAddressesPage() throws Exception {
		Long userAccountId =
			testGetUserAccountPostalAddressesPage_getUserAccountId();
		Long irrelevantUserAccountId =
			testGetUserAccountPostalAddressesPage_getIrrelevantUserAccountId();

		Page<PostalAddress> page =
			postalAddressResource.getUserAccountPostalAddressesPage(
				userAccountId);

		long totalCount = page.getTotalCount();

		if (irrelevantUserAccountId != null) {
			PostalAddress irrelevantPostalAddress =
				testGetUserAccountPostalAddressesPage_addPostalAddress(
					irrelevantUserAccountId, randomIrrelevantPostalAddress());

			page = postalAddressResource.getUserAccountPostalAddressesPage(
				irrelevantUserAccountId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPostalAddress, (List<PostalAddress>)page.getItems());
			assertValid(
				page,
				testGetUserAccountPostalAddressesPage_getExpectedActions(
					irrelevantUserAccountId));
		}

		PostalAddress postalAddress1 =
			testGetUserAccountPostalAddressesPage_addPostalAddress(
				userAccountId, randomPostalAddress());

		PostalAddress postalAddress2 =
			testGetUserAccountPostalAddressesPage_addPostalAddress(
				userAccountId, randomPostalAddress());

		page = postalAddressResource.getUserAccountPostalAddressesPage(
			userAccountId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(postalAddress1, (List<PostalAddress>)page.getItems());
		assertContains(postalAddress2, (List<PostalAddress>)page.getItems());
		assertValid(
			page,
			testGetUserAccountPostalAddressesPage_getExpectedActions(
				userAccountId));

		postalAddressResource.deletePostalAddress(postalAddress1.getId());

		postalAddressResource.deletePostalAddress(postalAddress2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetUserAccountPostalAddressesPage_getExpectedActions(
				Long userAccountId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PostalAddress
			testGetUserAccountPostalAddressesPage_addPostalAddress(
				Long userAccountId, PostalAddress postalAddress)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetUserAccountPostalAddressesPage_getUserAccountId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetUserAccountPostalAddressesPage_getIrrelevantUserAccountId()
		throws Exception {

		return null;
	}

	protected PostalAddress testGraphQLPostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		PostalAddress postalAddress, List<PostalAddress> postalAddresses) {

		boolean contains = false;

		for (PostalAddress item : postalAddresses) {
			if (equals(postalAddress, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			postalAddresses + " does not contain " + postalAddress, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		PostalAddress postalAddress1, PostalAddress postalAddress2) {

		Assert.assertTrue(
			postalAddress1 + " does not equal " + postalAddress2,
			equals(postalAddress1, postalAddress2));
	}

	protected void assertEquals(
		List<PostalAddress> postalAddresses1,
		List<PostalAddress> postalAddresses2) {

		Assert.assertEquals(postalAddresses1.size(), postalAddresses2.size());

		for (int i = 0; i < postalAddresses1.size(); i++) {
			PostalAddress postalAddress1 = postalAddresses1.get(i);
			PostalAddress postalAddress2 = postalAddresses2.get(i);

			assertEquals(postalAddress1, postalAddress2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<PostalAddress> postalAddresses1,
		List<PostalAddress> postalAddresses2) {

		Assert.assertEquals(postalAddresses1.size(), postalAddresses2.size());

		for (PostalAddress postalAddress1 : postalAddresses1) {
			boolean contains = false;

			for (PostalAddress postalAddress2 : postalAddresses2) {
				if (equals(postalAddress1, postalAddress2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				postalAddresses2 + " does not contain " + postalAddress1,
				contains);
		}
	}

	protected void assertValid(PostalAddress postalAddress) throws Exception {
		boolean valid = true;

		if (postalAddress.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("addressCountry", additionalAssertFieldName)) {
				if (postalAddress.getAddressCountry() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"addressCountry_i18n", additionalAssertFieldName)) {

				if (postalAddress.getAddressCountry_i18n() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("addressLocality", additionalAssertFieldName)) {
				if (postalAddress.getAddressLocality() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("addressRegion", additionalAssertFieldName)) {
				if (postalAddress.getAddressRegion() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("addressType", additionalAssertFieldName)) {
				if (postalAddress.getAddressType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (postalAddress.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (postalAddress.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("phoneNumber", additionalAssertFieldName)) {
				if (postalAddress.getPhoneNumber() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("postalCode", additionalAssertFieldName)) {
				if (postalAddress.getPostalCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("primary", additionalAssertFieldName)) {
				if (postalAddress.getPrimary() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine1", additionalAssertFieldName)) {

				if (postalAddress.getStreetAddressLine1() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine2", additionalAssertFieldName)) {

				if (postalAddress.getStreetAddressLine2() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine3", additionalAssertFieldName)) {

				if (postalAddress.getStreetAddressLine3() == null) {
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

	protected void assertValid(Page<PostalAddress> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<PostalAddress> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<PostalAddress> postalAddresses = page.getItems();

		int size = postalAddresses.size();

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
					com.liferay.headless.admin.user.dto.v1_0.PostalAddress.
						class)) {

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

	protected boolean equals(
		PostalAddress postalAddress1, PostalAddress postalAddress2) {

		if (postalAddress1 == postalAddress2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("addressCountry", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getAddressCountry(),
						postalAddress2.getAddressCountry())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"addressCountry_i18n", additionalAssertFieldName)) {

				if (!equals(
						(Map)postalAddress1.getAddressCountry_i18n(),
						(Map)postalAddress2.getAddressCountry_i18n())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressLocality", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getAddressLocality(),
						postalAddress2.getAddressLocality())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressRegion", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getAddressRegion(),
						postalAddress2.getAddressRegion())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getAddressType(),
						postalAddress2.getAddressType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						postalAddress1.getExternalReferenceCode(),
						postalAddress2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getId(), postalAddress2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getName(), postalAddress2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("phoneNumber", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getPhoneNumber(),
						postalAddress2.getPhoneNumber())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("postalCode", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getPostalCode(),
						postalAddress2.getPostalCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("primary", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						postalAddress1.getPrimary(),
						postalAddress2.getPrimary())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine1", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						postalAddress1.getStreetAddressLine1(),
						postalAddress2.getStreetAddressLine1())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine2", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						postalAddress1.getStreetAddressLine2(),
						postalAddress2.getStreetAddressLine2())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"streetAddressLine3", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						postalAddress1.getStreetAddressLine3(),
						postalAddress2.getStreetAddressLine3())) {

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

		if (!(_postalAddressResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_postalAddressResource;

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
		EntityField entityField, String operator, PostalAddress postalAddress) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("addressCountry")) {
			Object object = postalAddress.getAddressCountry();

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

		if (entityFieldName.equals("addressCountry_i18n")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("addressLocality")) {
			Object object = postalAddress.getAddressLocality();

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

		if (entityFieldName.equals("addressRegion")) {
			Object object = postalAddress.getAddressRegion();

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

		if (entityFieldName.equals("addressType")) {
			Object object = postalAddress.getAddressType();

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

		if (entityFieldName.equals("externalReferenceCode")) {
			Object object = postalAddress.getExternalReferenceCode();

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
			Object object = postalAddress.getName();

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

		if (entityFieldName.equals("phoneNumber")) {
			Object object = postalAddress.getPhoneNumber();

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

		if (entityFieldName.equals("postalCode")) {
			Object object = postalAddress.getPostalCode();

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

		if (entityFieldName.equals("primary")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("streetAddressLine1")) {
			Object object = postalAddress.getStreetAddressLine1();

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

		if (entityFieldName.equals("streetAddressLine2")) {
			Object object = postalAddress.getStreetAddressLine2();

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

		if (entityFieldName.equals("streetAddressLine3")) {
			Object object = postalAddress.getStreetAddressLine3();

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

	protected PostalAddress randomPostalAddress() throws Exception {
		return new PostalAddress() {
			{
				addressCountry = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				addressLocality = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				addressRegion = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				addressType = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				phoneNumber = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				postalCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				primary = RandomTestUtil.randomBoolean();
				streetAddressLine1 = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				streetAddressLine2 = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				streetAddressLine3 = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected PostalAddress randomIrrelevantPostalAddress() throws Exception {
		PostalAddress randomIrrelevantPostalAddress = randomPostalAddress();

		return randomIrrelevantPostalAddress;
	}

	protected PostalAddress randomPatchPostalAddress() throws Exception {
		return randomPostalAddress();
	}

	protected PostalAddressResource postalAddressResource;
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
		LogFactoryUtil.getLog(BasePostalAddressResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.admin.user.resource.v1_0.PostalAddressResource
		_postalAddressResource;

}