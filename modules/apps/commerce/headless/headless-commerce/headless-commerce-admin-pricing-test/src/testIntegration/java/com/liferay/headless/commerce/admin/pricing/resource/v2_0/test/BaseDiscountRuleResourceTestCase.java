/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.resource.v2_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.admin.pricing.client.dto.v2_0.DiscountRule;
import com.liferay.headless.commerce.admin.pricing.client.http.HttpInvoker;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Page;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Pagination;
import com.liferay.headless.commerce.admin.pricing.client.resource.v2_0.DiscountRuleResource;
import com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0.DiscountRuleSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
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
import com.liferay.portal.search.test.rule.SearchTestRule;
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
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public abstract class BaseDiscountRuleResourceTestCase {

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

		_discountRuleResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		DiscountRuleResource.Builder builder = DiscountRuleResource.builder();

		discountRuleResource = builder.authentication(
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

		DiscountRule discountRule1 = randomDiscountRule();

		String json = objectMapper.writeValueAsString(discountRule1);

		DiscountRule discountRule2 = DiscountRuleSerDes.toDTO(json);

		Assert.assertTrue(equals(discountRule1, discountRule2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		DiscountRule discountRule = randomDiscountRule();

		String json1 = objectMapper.writeValueAsString(discountRule);
		String json2 = DiscountRuleSerDes.toJSON(discountRule);

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

		DiscountRule discountRule = randomDiscountRule();

		discountRule.setName(regex);
		discountRule.setType(regex);
		discountRule.setTypeSettings(regex);

		String json = DiscountRuleSerDes.toJSON(discountRule);

		Assert.assertFalse(json.contains(regex));

		discountRule = DiscountRuleSerDes.toDTO(json);

		Assert.assertEquals(regex, discountRule.getName());
		Assert.assertEquals(regex, discountRule.getType());
		Assert.assertEquals(regex, discountRule.getTypeSettings());
	}

	@Test
	public void testDeleteDiscountRule() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		DiscountRule discountRule = testDeleteDiscountRule_addDiscountRule();

		assertHttpResponseStatusCode(
			204,
			discountRuleResource.deleteDiscountRuleHttpResponse(
				discountRule.getId()));

		assertHttpResponseStatusCode(
			404,
			discountRuleResource.getDiscountRuleHttpResponse(
				discountRule.getId()));

		assertHttpResponseStatusCode(
			404,
			discountRuleResource.getDiscountRuleHttpResponse(
				discountRule.getId()));
	}

	protected DiscountRule testDeleteDiscountRule_addDiscountRule()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteDiscountRule() throws Exception {

		// No namespace

		DiscountRule discountRule1 =
			testGraphQLDeleteDiscountRule_addDiscountRule();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteDiscountRule",
						new HashMap<String, Object>() {
							{
								put("id", discountRule1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteDiscountRule"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"discountRule",
					new HashMap<String, Object>() {
						{
							put("id", discountRule1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace headlessCommerceAdminPricing_v2_0

		DiscountRule discountRule2 =
			testGraphQLDeleteDiscountRule_addDiscountRule();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"headlessCommerceAdminPricing_v2_0",
						new GraphQLField(
							"deleteDiscountRule",
							new HashMap<String, Object>() {
								{
									put("id", discountRule2.getId());
								}
							}))),
				"JSONObject/data",
				"JSONObject/headlessCommerceAdminPricing_v2_0",
				"Object/deleteDiscountRule"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"headlessCommerceAdminPricing_v2_0",
					new GraphQLField(
						"discountRule",
						new HashMap<String, Object>() {
							{
								put("id", discountRule2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected DiscountRule testGraphQLDeleteDiscountRule_addDiscountRule()
		throws Exception {

		return testGraphQLDiscountRule_addDiscountRule();
	}

	@Test
	public void testGetDiscountRule() throws Exception {
		DiscountRule postDiscountRule = testGetDiscountRule_addDiscountRule();

		DiscountRule getDiscountRule = discountRuleResource.getDiscountRule(
			postDiscountRule.getId());

		assertEquals(postDiscountRule, getDiscountRule);
		assertValid(getDiscountRule);
	}

	protected DiscountRule testGetDiscountRule_addDiscountRule()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetDiscountRule() throws Exception {
		DiscountRule discountRule =
			testGraphQLGetDiscountRule_addDiscountRule();

		// No namespace

		Assert.assertTrue(
			equals(
				discountRule,
				DiscountRuleSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"discountRule",
								new HashMap<String, Object>() {
									{
										put("id", discountRule.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/discountRule"))));

		// Using the namespace headlessCommerceAdminPricing_v2_0

		Assert.assertTrue(
			equals(
				discountRule,
				DiscountRuleSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessCommerceAdminPricing_v2_0",
								new GraphQLField(
									"discountRule",
									new HashMap<String, Object>() {
										{
											put("id", discountRule.getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data",
						"JSONObject/headlessCommerceAdminPricing_v2_0",
						"Object/discountRule"))));
	}

	@Test
	public void testGraphQLGetDiscountRuleNotFound() throws Exception {
		Long irrelevantId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"discountRule",
						new HashMap<String, Object>() {
							{
								put("id", irrelevantId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessCommerceAdminPricing_v2_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessCommerceAdminPricing_v2_0",
						new GraphQLField(
							"discountRule",
							new HashMap<String, Object>() {
								{
									put("id", irrelevantId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected DiscountRule testGraphQLGetDiscountRule_addDiscountRule()
		throws Exception {

		return testGraphQLDiscountRule_addDiscountRule();
	}

	@Test
	public void testPatchDiscountRule() throws Exception {
		DiscountRule postDiscountRule = testPatchDiscountRule_addDiscountRule();

		DiscountRule randomPatchDiscountRule = randomPatchDiscountRule();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		DiscountRule patchDiscountRule = discountRuleResource.patchDiscountRule(
			postDiscountRule.getId(), randomPatchDiscountRule);

		DiscountRule expectedPatchDiscountRule = postDiscountRule.clone();

		BeanTestUtil.copyProperties(
			randomPatchDiscountRule, expectedPatchDiscountRule);

		DiscountRule getDiscountRule = discountRuleResource.getDiscountRule(
			patchDiscountRule.getId());

		assertEquals(expectedPatchDiscountRule, getDiscountRule);
		assertValid(getDiscountRule);
	}

	protected DiscountRule testPatchDiscountRule_addDiscountRule()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetDiscountByExternalReferenceCodeDiscountRulesPage()
		throws Exception {

		String externalReferenceCode =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getIrrelevantExternalReferenceCode();

		Page<DiscountRule> page =
			discountRuleResource.
				getDiscountByExternalReferenceCodeDiscountRulesPage(
					externalReferenceCode, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			DiscountRule irrelevantDiscountRule =
				testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
					irrelevantExternalReferenceCode,
					randomIrrelevantDiscountRule());

			page =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						irrelevantExternalReferenceCode,
						Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDiscountRule, (List<DiscountRule>)page.getItems());
			assertValid(
				page,
				testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		DiscountRule discountRule1 =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				externalReferenceCode, randomDiscountRule());

		DiscountRule discountRule2 =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				externalReferenceCode, randomDiscountRule());

		page =
			discountRuleResource.
				getDiscountByExternalReferenceCodeDiscountRulesPage(
					externalReferenceCode, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(discountRule1, (List<DiscountRule>)page.getItems());
		assertContains(discountRule2, (List<DiscountRule>)page.getItems());
		assertValid(
			page,
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExpectedActions(
				externalReferenceCode));

		discountRuleResource.deleteDiscountRule(discountRule1.getId());

		discountRuleResource.deleteDiscountRule(discountRule2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetDiscountByExternalReferenceCodeDiscountRulesPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExternalReferenceCode();

		Page<DiscountRule> discountRulePage =
			discountRuleResource.
				getDiscountByExternalReferenceCodeDiscountRulesPage(
					externalReferenceCode, null);

		int totalCount = GetterUtil.getInteger(
			discountRulePage.getTotalCount());

		DiscountRule discountRule1 =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				externalReferenceCode, randomDiscountRule());

		DiscountRule discountRule2 =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				externalReferenceCode, randomDiscountRule());

		DiscountRule discountRule3 =
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				externalReferenceCode, randomDiscountRule());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<DiscountRule> page1 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(discountRule1, (List<DiscountRule>)page1.getItems());

			Page<DiscountRule> page2 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(discountRule2, (List<DiscountRule>)page2.getItems());

			Page<DiscountRule> page3 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(discountRule3, (List<DiscountRule>)page3.getItems());
		}
		else {
			Page<DiscountRule> page1 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(1, totalCount + 2));

			List<DiscountRule> discountRules1 =
				(List<DiscountRule>)page1.getItems();

			Assert.assertEquals(
				discountRules1.toString(), totalCount + 2,
				discountRules1.size());

			Page<DiscountRule> page2 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<DiscountRule> discountRules2 =
				(List<DiscountRule>)page2.getItems();

			Assert.assertEquals(
				discountRules2.toString(), 1, discountRules2.size());

			Page<DiscountRule> page3 =
				discountRuleResource.
					getDiscountByExternalReferenceCodeDiscountRulesPage(
						externalReferenceCode,
						Pagination.of(1, (int)totalCount + 3));

			assertContains(discountRule1, (List<DiscountRule>)page3.getItems());
			assertContains(discountRule2, (List<DiscountRule>)page3.getItems());
			assertContains(discountRule3, (List<DiscountRule>)page3.getItems());
		}
	}

	protected DiscountRule
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_addDiscountRule(
				String externalReferenceCode, DiscountRule discountRule)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetDiscountByExternalReferenceCodeDiscountRulesPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostDiscountByExternalReferenceCodeDiscountRule()
		throws Exception {

		DiscountRule randomDiscountRule = randomDiscountRule();

		DiscountRule postDiscountRule =
			testPostDiscountByExternalReferenceCodeDiscountRule_addDiscountRule(
				randomDiscountRule);

		assertEquals(randomDiscountRule, postDiscountRule);
		assertValid(postDiscountRule);
	}

	protected DiscountRule
			testPostDiscountByExternalReferenceCodeDiscountRule_addDiscountRule(
				DiscountRule discountRule)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetDiscountIdDiscountRulesPage() throws Exception {
		Long id = testGetDiscountIdDiscountRulesPage_getId();
		Long irrelevantId =
			testGetDiscountIdDiscountRulesPage_getIrrelevantId();

		Page<DiscountRule> page =
			discountRuleResource.getDiscountIdDiscountRulesPage(
				id, null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantId != null) {
			DiscountRule irrelevantDiscountRule =
				testGetDiscountIdDiscountRulesPage_addDiscountRule(
					irrelevantId, randomIrrelevantDiscountRule());

			page = discountRuleResource.getDiscountIdDiscountRulesPage(
				irrelevantId, null, null, Pagination.of(1, (int)totalCount + 1),
				null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDiscountRule, (List<DiscountRule>)page.getItems());
			assertValid(
				page,
				testGetDiscountIdDiscountRulesPage_getExpectedActions(
					irrelevantId));
		}

		DiscountRule discountRule1 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		DiscountRule discountRule2 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		page = discountRuleResource.getDiscountIdDiscountRulesPage(
			id, null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(discountRule1, (List<DiscountRule>)page.getItems());
		assertContains(discountRule2, (List<DiscountRule>)page.getItems());
		assertValid(
			page, testGetDiscountIdDiscountRulesPage_getExpectedActions(id));

		discountRuleResource.deleteDiscountRule(discountRule1.getId());

		discountRuleResource.deleteDiscountRule(discountRule2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetDiscountIdDiscountRulesPage_getExpectedActions(Long id)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetDiscountIdDiscountRulesPage_getId();

		DiscountRule discountRule1 = randomDiscountRule();

		discountRule1 = testGetDiscountIdDiscountRulesPage_addDiscountRule(
			id, discountRule1);

		for (EntityField entityField : entityFields) {
			Page<DiscountRule> page =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null,
					getFilterString(entityField, "between", discountRule1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(discountRule1),
				(List<DiscountRule>)page.getItems());
		}
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithFilterDoubleEquals()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithFilterStringContains()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithFilterStringEquals()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithFilterStringStartsWith()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void testGetDiscountIdDiscountRulesPageWithFilter(
			String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetDiscountIdDiscountRulesPage_getId();

		DiscountRule discountRule1 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		DiscountRule discountRule2 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		for (EntityField entityField : entityFields) {
			Page<DiscountRule> page =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null,
					getFilterString(entityField, operator, discountRule1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(discountRule1),
				(List<DiscountRule>)page.getItems());
		}
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithPagination()
		throws Exception {

		Long id = testGetDiscountIdDiscountRulesPage_getId();

		Page<DiscountRule> discountRulePage =
			discountRuleResource.getDiscountIdDiscountRulesPage(
				id, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			discountRulePage.getTotalCount());

		DiscountRule discountRule1 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		DiscountRule discountRule2 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		DiscountRule discountRule3 =
			testGetDiscountIdDiscountRulesPage_addDiscountRule(
				id, randomDiscountRule());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<DiscountRule> page1 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(discountRule1, (List<DiscountRule>)page1.getItems());

			Page<DiscountRule> page2 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			assertContains(discountRule2, (List<DiscountRule>)page2.getItems());

			Page<DiscountRule> page3 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			assertContains(discountRule3, (List<DiscountRule>)page3.getItems());
		}
		else {
			Page<DiscountRule> page1 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null, Pagination.of(1, totalCount + 2), null);

			List<DiscountRule> discountRules1 =
				(List<DiscountRule>)page1.getItems();

			Assert.assertEquals(
				discountRules1.toString(), totalCount + 2,
				discountRules1.size());

			Page<DiscountRule> page2 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null, Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<DiscountRule> discountRules2 =
				(List<DiscountRule>)page2.getItems();

			Assert.assertEquals(
				discountRules2.toString(), 1, discountRules2.size());

			Page<DiscountRule> page3 =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null, Pagination.of(1, (int)totalCount + 3),
					null);

			assertContains(discountRule1, (List<DiscountRule>)page3.getItems());
			assertContains(discountRule2, (List<DiscountRule>)page3.getItems());
			assertContains(discountRule3, (List<DiscountRule>)page3.getItems());
		}
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithSortDateTime()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, discountRule1, discountRule2) -> {
				BeanTestUtil.setProperty(
					discountRule1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithSortDouble()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, discountRule1, discountRule2) -> {
				BeanTestUtil.setProperty(
					discountRule1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					discountRule2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithSortInteger()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, discountRule1, discountRule2) -> {
				BeanTestUtil.setProperty(
					discountRule1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					discountRule2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetDiscountIdDiscountRulesPageWithSortString()
		throws Exception {

		testGetDiscountIdDiscountRulesPageWithSort(
			EntityField.Type.STRING,
			(entityField, discountRule1, discountRule2) -> {
				Class<?> clazz = discountRule1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						discountRule1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						discountRule2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						discountRule1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						discountRule2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						discountRule1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						discountRule2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetDiscountIdDiscountRulesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, DiscountRule, DiscountRule, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetDiscountIdDiscountRulesPage_getId();

		DiscountRule discountRule1 = randomDiscountRule();
		DiscountRule discountRule2 = randomDiscountRule();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, discountRule1, discountRule2);
		}

		discountRule1 = testGetDiscountIdDiscountRulesPage_addDiscountRule(
			id, discountRule1);

		discountRule2 = testGetDiscountIdDiscountRulesPage_addDiscountRule(
			id, discountRule2);

		Page<DiscountRule> page =
			discountRuleResource.getDiscountIdDiscountRulesPage(
				id, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<DiscountRule> ascPage =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null,
					Pagination.of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":asc");

			assertContains(
				discountRule1, (List<DiscountRule>)ascPage.getItems());
			assertContains(
				discountRule2, (List<DiscountRule>)ascPage.getItems());

			Page<DiscountRule> descPage =
				discountRuleResource.getDiscountIdDiscountRulesPage(
					id, null, null,
					Pagination.of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":desc");

			assertContains(
				discountRule2, (List<DiscountRule>)descPage.getItems());
			assertContains(
				discountRule1, (List<DiscountRule>)descPage.getItems());
		}
	}

	protected DiscountRule testGetDiscountIdDiscountRulesPage_addDiscountRule(
			Long id, DiscountRule discountRule)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetDiscountIdDiscountRulesPage_getId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetDiscountIdDiscountRulesPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostDiscountIdDiscountRule() throws Exception {
		DiscountRule randomDiscountRule = randomDiscountRule();

		DiscountRule postDiscountRule =
			testPostDiscountIdDiscountRule_addDiscountRule(randomDiscountRule);

		assertEquals(randomDiscountRule, postDiscountRule);
		assertValid(postDiscountRule);
	}

	protected DiscountRule testPostDiscountIdDiscountRule_addDiscountRule(
			DiscountRule discountRule)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected DiscountRule testGraphQLDiscountRule_addDiscountRule()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		DiscountRule discountRule, List<DiscountRule> discountRules) {

		boolean contains = false;

		for (DiscountRule item : discountRules) {
			if (equals(discountRule, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			discountRules + " does not contain " + discountRule, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		DiscountRule discountRule1, DiscountRule discountRule2) {

		Assert.assertTrue(
			discountRule1 + " does not equal " + discountRule2,
			equals(discountRule1, discountRule2));
	}

	protected void assertEquals(
		List<DiscountRule> discountRules1, List<DiscountRule> discountRules2) {

		Assert.assertEquals(discountRules1.size(), discountRules2.size());

		for (int i = 0; i < discountRules1.size(); i++) {
			DiscountRule discountRule1 = discountRules1.get(i);
			DiscountRule discountRule2 = discountRules2.get(i);

			assertEquals(discountRule1, discountRule2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<DiscountRule> discountRules1, List<DiscountRule> discountRules2) {

		Assert.assertEquals(discountRules1.size(), discountRules2.size());

		for (DiscountRule discountRule1 : discountRules1) {
			boolean contains = false;

			for (DiscountRule discountRule2 : discountRules2) {
				if (equals(discountRule1, discountRule2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				discountRules2 + " does not contain " + discountRule1,
				contains);
		}
	}

	protected void assertValid(DiscountRule discountRule) throws Exception {
		boolean valid = true;

		if (discountRule.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (discountRule.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("discountId", additionalAssertFieldName)) {
				if (discountRule.getDiscountId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (discountRule.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (discountRule.getType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("typeSettings", additionalAssertFieldName)) {
				if (discountRule.getTypeSettings() == null) {
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

	protected void assertValid(Page<DiscountRule> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<DiscountRule> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<DiscountRule> discountRules = page.getItems();

		int size = discountRules.size();

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
					com.liferay.headless.commerce.admin.pricing.dto.v2_0.
						DiscountRule.class)) {

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
		DiscountRule discountRule1, DiscountRule discountRule2) {

		if (discountRule1 == discountRule2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals(
						(Map)discountRule1.getActions(),
						(Map)discountRule2.getActions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("discountId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						discountRule1.getDiscountId(),
						discountRule2.getDiscountId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						discountRule1.getId(), discountRule2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						discountRule1.getName(), discountRule2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						discountRule1.getType(), discountRule2.getType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("typeSettings", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						discountRule1.getTypeSettings(),
						discountRule2.getTypeSettings())) {

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

		if (!(_discountRuleResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_discountRuleResource;

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
		EntityField entityField, String operator, DiscountRule discountRule) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("actions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("discountId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			Object object = discountRule.getName();

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

		if (entityFieldName.equals("type")) {
			Object object = discountRule.getType();

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

		if (entityFieldName.equals("typeSettings")) {
			Object object = discountRule.getTypeSettings();

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

	protected DiscountRule randomDiscountRule() throws Exception {
		return new DiscountRule() {
			{
				discountId = RandomTestUtil.randomLong();
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				type = StringUtil.toLowerCase(RandomTestUtil.randomString());
				typeSettings = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected DiscountRule randomIrrelevantDiscountRule() throws Exception {
		DiscountRule randomIrrelevantDiscountRule = randomDiscountRule();

		return randomIrrelevantDiscountRule;
	}

	protected DiscountRule randomPatchDiscountRule() throws Exception {
		return randomDiscountRule();
	}

	protected DiscountRuleResource discountRuleResource;
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
		LogFactoryUtil.getLog(BaseDiscountRuleResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.commerce.admin.pricing.resource.v2_0.
		DiscountRuleResource _discountRuleResource;

}