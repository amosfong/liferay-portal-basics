/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.admin.pricing.client.dto.v1_0.PriceListAccountGroup;
import com.liferay.headless.commerce.admin.pricing.client.http.HttpInvoker;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Page;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Pagination;
import com.liferay.headless.commerce.admin.pricing.client.resource.v1_0.PriceListAccountGroupResource;
import com.liferay.headless.commerce.admin.pricing.client.serdes.v1_0.PriceListAccountGroupSerDes;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
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
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public abstract class BasePriceListAccountGroupResourceTestCase {

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

		_priceListAccountGroupResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		PriceListAccountGroupResource.Builder builder =
			PriceListAccountGroupResource.builder();

		priceListAccountGroupResource = builder.authentication(
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

		PriceListAccountGroup priceListAccountGroup1 =
			randomPriceListAccountGroup();

		String json = objectMapper.writeValueAsString(priceListAccountGroup1);

		PriceListAccountGroup priceListAccountGroup2 =
			PriceListAccountGroupSerDes.toDTO(json);

		Assert.assertTrue(
			equals(priceListAccountGroup1, priceListAccountGroup2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		PriceListAccountGroup priceListAccountGroup =
			randomPriceListAccountGroup();

		String json1 = objectMapper.writeValueAsString(priceListAccountGroup);
		String json2 = PriceListAccountGroupSerDes.toJSON(
			priceListAccountGroup);

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

		PriceListAccountGroup priceListAccountGroup =
			randomPriceListAccountGroup();

		priceListAccountGroup.setAccountGroupExternalReferenceCode(regex);
		priceListAccountGroup.setPriceListExternalReferenceCode(regex);

		String json = PriceListAccountGroupSerDes.toJSON(priceListAccountGroup);

		Assert.assertFalse(json.contains(regex));

		priceListAccountGroup = PriceListAccountGroupSerDes.toDTO(json);

		Assert.assertEquals(
			regex,
			priceListAccountGroup.getAccountGroupExternalReferenceCode());
		Assert.assertEquals(
			regex, priceListAccountGroup.getPriceListExternalReferenceCode());
	}

	@Test
	public void testDeletePriceListAccountGroup() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		PriceListAccountGroup priceListAccountGroup =
			testDeletePriceListAccountGroup_addPriceListAccountGroup();

		assertHttpResponseStatusCode(
			204,
			priceListAccountGroupResource.
				deletePriceListAccountGroupHttpResponse(
					priceListAccountGroup.getId()));
	}

	protected PriceListAccountGroup
			testDeletePriceListAccountGroup_addPriceListAccountGroup()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeletePriceListAccountGroup() throws Exception {

		// No namespace

		PriceListAccountGroup priceListAccountGroup1 =
			testGraphQLDeletePriceListAccountGroup_addPriceListAccountGroup();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deletePriceListAccountGroup",
						new HashMap<String, Object>() {
							{
								put("id", priceListAccountGroup1.getId());
							}
						})),
				"JSONObject/data", "Object/deletePriceListAccountGroup"));

		// Using the namespace headlessCommerceAdminPricing_v1_0

		PriceListAccountGroup priceListAccountGroup2 =
			testGraphQLDeletePriceListAccountGroup_addPriceListAccountGroup();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"headlessCommerceAdminPricing_v1_0",
						new GraphQLField(
							"deletePriceListAccountGroup",
							new HashMap<String, Object>() {
								{
									put("id", priceListAccountGroup2.getId());
								}
							}))),
				"JSONObject/data",
				"JSONObject/headlessCommerceAdminPricing_v1_0",
				"Object/deletePriceListAccountGroup"));
	}

	protected PriceListAccountGroup
			testGraphQLDeletePriceListAccountGroup_addPriceListAccountGroup()
		throws Exception {

		return testGraphQLPriceListAccountGroup_addPriceListAccountGroup();
	}

	@Test
	public void testGetPriceListByExternalReferenceCodePriceListAccountGroupPage()
		throws Exception {

		String externalReferenceCode =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getIrrelevantExternalReferenceCode();

		Page<PriceListAccountGroup> page =
			priceListAccountGroupResource.
				getPriceListByExternalReferenceCodePriceListAccountGroupPage(
					externalReferenceCode, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			PriceListAccountGroup irrelevantPriceListAccountGroup =
				testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
					irrelevantExternalReferenceCode,
					randomIrrelevantPriceListAccountGroup());

			page =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						irrelevantExternalReferenceCode,
						Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPriceListAccountGroup,
				(List<PriceListAccountGroup>)page.getItems());
			assertValid(
				page,
				testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		PriceListAccountGroup priceListAccountGroup1 =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				externalReferenceCode, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup2 =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				externalReferenceCode, randomPriceListAccountGroup());

		page =
			priceListAccountGroupResource.
				getPriceListByExternalReferenceCodePriceListAccountGroupPage(
					externalReferenceCode, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			priceListAccountGroup1,
			(List<PriceListAccountGroup>)page.getItems());
		assertContains(
			priceListAccountGroup2,
			(List<PriceListAccountGroup>)page.getItems());
		assertValid(
			page,
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExpectedActions(
				externalReferenceCode));

		priceListAccountGroupResource.deletePriceListAccountGroup(
			priceListAccountGroup1.getId());

		priceListAccountGroupResource.deletePriceListAccountGroup(
			priceListAccountGroup2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetPriceListByExternalReferenceCodePriceListAccountGroupPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExternalReferenceCode();

		Page<PriceListAccountGroup> priceListAccountGroupPage =
			priceListAccountGroupResource.
				getPriceListByExternalReferenceCodePriceListAccountGroupPage(
					externalReferenceCode, null);

		int totalCount = GetterUtil.getInteger(
			priceListAccountGroupPage.getTotalCount());

		PriceListAccountGroup priceListAccountGroup1 =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				externalReferenceCode, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup2 =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				externalReferenceCode, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup3 =
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				externalReferenceCode, randomPriceListAccountGroup());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<PriceListAccountGroup> page1 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				priceListAccountGroup1,
				(List<PriceListAccountGroup>)page1.getItems());

			Page<PriceListAccountGroup> page2 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceListAccountGroup2,
				(List<PriceListAccountGroup>)page2.getItems());

			Page<PriceListAccountGroup> page3 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceListAccountGroup3,
				(List<PriceListAccountGroup>)page3.getItems());
		}
		else {
			Page<PriceListAccountGroup> page1 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(1, totalCount + 2));

			List<PriceListAccountGroup> priceListAccountGroups1 =
				(List<PriceListAccountGroup>)page1.getItems();

			Assert.assertEquals(
				priceListAccountGroups1.toString(), totalCount + 2,
				priceListAccountGroups1.size());

			Page<PriceListAccountGroup> page2 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<PriceListAccountGroup> priceListAccountGroups2 =
				(List<PriceListAccountGroup>)page2.getItems();

			Assert.assertEquals(
				priceListAccountGroups2.toString(), 1,
				priceListAccountGroups2.size());

			Page<PriceListAccountGroup> page3 =
				priceListAccountGroupResource.
					getPriceListByExternalReferenceCodePriceListAccountGroupPage(
						externalReferenceCode,
						Pagination.of(1, (int)totalCount + 3));

			assertContains(
				priceListAccountGroup1,
				(List<PriceListAccountGroup>)page3.getItems());
			assertContains(
				priceListAccountGroup2,
				(List<PriceListAccountGroup>)page3.getItems());
			assertContains(
				priceListAccountGroup3,
				(List<PriceListAccountGroup>)page3.getItems());
		}
	}

	protected PriceListAccountGroup
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_addPriceListAccountGroup(
				String externalReferenceCode,
				PriceListAccountGroup priceListAccountGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetPriceListByExternalReferenceCodePriceListAccountGroupPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostPriceListByExternalReferenceCodePriceListAccountGroup()
		throws Exception {

		PriceListAccountGroup randomPriceListAccountGroup =
			randomPriceListAccountGroup();

		PriceListAccountGroup postPriceListAccountGroup =
			testPostPriceListByExternalReferenceCodePriceListAccountGroup_addPriceListAccountGroup(
				randomPriceListAccountGroup);

		assertEquals(randomPriceListAccountGroup, postPriceListAccountGroup);
		assertValid(postPriceListAccountGroup);
	}

	protected PriceListAccountGroup
			testPostPriceListByExternalReferenceCodePriceListAccountGroup_addPriceListAccountGroup(
				PriceListAccountGroup priceListAccountGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetPriceListIdPriceListAccountGroupsPage()
		throws Exception {

		Long id = testGetPriceListIdPriceListAccountGroupsPage_getId();
		Long irrelevantId =
			testGetPriceListIdPriceListAccountGroupsPage_getIrrelevantId();

		Page<PriceListAccountGroup> page =
			priceListAccountGroupResource.
				getPriceListIdPriceListAccountGroupsPage(
					id, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantId != null) {
			PriceListAccountGroup irrelevantPriceListAccountGroup =
				testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
					irrelevantId, randomIrrelevantPriceListAccountGroup());

			page =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						irrelevantId, Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPriceListAccountGroup,
				(List<PriceListAccountGroup>)page.getItems());
			assertValid(
				page,
				testGetPriceListIdPriceListAccountGroupsPage_getExpectedActions(
					irrelevantId));
		}

		PriceListAccountGroup priceListAccountGroup1 =
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				id, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup2 =
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				id, randomPriceListAccountGroup());

		page =
			priceListAccountGroupResource.
				getPriceListIdPriceListAccountGroupsPage(
					id, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			priceListAccountGroup1,
			(List<PriceListAccountGroup>)page.getItems());
		assertContains(
			priceListAccountGroup2,
			(List<PriceListAccountGroup>)page.getItems());
		assertValid(
			page,
			testGetPriceListIdPriceListAccountGroupsPage_getExpectedActions(
				id));

		priceListAccountGroupResource.deletePriceListAccountGroup(
			priceListAccountGroup1.getId());

		priceListAccountGroupResource.deletePriceListAccountGroup(
			priceListAccountGroup2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetPriceListIdPriceListAccountGroupsPage_getExpectedActions(
				Long id)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetPriceListIdPriceListAccountGroupsPageWithPagination()
		throws Exception {

		Long id = testGetPriceListIdPriceListAccountGroupsPage_getId();

		Page<PriceListAccountGroup> priceListAccountGroupPage =
			priceListAccountGroupResource.
				getPriceListIdPriceListAccountGroupsPage(id, null);

		int totalCount = GetterUtil.getInteger(
			priceListAccountGroupPage.getTotalCount());

		PriceListAccountGroup priceListAccountGroup1 =
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				id, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup2 =
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				id, randomPriceListAccountGroup());

		PriceListAccountGroup priceListAccountGroup3 =
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				id, randomPriceListAccountGroup());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<PriceListAccountGroup> page1 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				priceListAccountGroup1,
				(List<PriceListAccountGroup>)page1.getItems());

			Page<PriceListAccountGroup> page2 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceListAccountGroup2,
				(List<PriceListAccountGroup>)page2.getItems());

			Page<PriceListAccountGroup> page3 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceListAccountGroup3,
				(List<PriceListAccountGroup>)page3.getItems());
		}
		else {
			Page<PriceListAccountGroup> page1 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id, Pagination.of(1, totalCount + 2));

			List<PriceListAccountGroup> priceListAccountGroups1 =
				(List<PriceListAccountGroup>)page1.getItems();

			Assert.assertEquals(
				priceListAccountGroups1.toString(), totalCount + 2,
				priceListAccountGroups1.size());

			Page<PriceListAccountGroup> page2 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<PriceListAccountGroup> priceListAccountGroups2 =
				(List<PriceListAccountGroup>)page2.getItems();

			Assert.assertEquals(
				priceListAccountGroups2.toString(), 1,
				priceListAccountGroups2.size());

			Page<PriceListAccountGroup> page3 =
				priceListAccountGroupResource.
					getPriceListIdPriceListAccountGroupsPage(
						id, Pagination.of(1, (int)totalCount + 3));

			assertContains(
				priceListAccountGroup1,
				(List<PriceListAccountGroup>)page3.getItems());
			assertContains(
				priceListAccountGroup2,
				(List<PriceListAccountGroup>)page3.getItems());
			assertContains(
				priceListAccountGroup3,
				(List<PriceListAccountGroup>)page3.getItems());
		}
	}

	protected PriceListAccountGroup
			testGetPriceListIdPriceListAccountGroupsPage_addPriceListAccountGroup(
				Long id, PriceListAccountGroup priceListAccountGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetPriceListIdPriceListAccountGroupsPage_getId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetPriceListIdPriceListAccountGroupsPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostPriceListIdPriceListAccountGroup() throws Exception {
		PriceListAccountGroup randomPriceListAccountGroup =
			randomPriceListAccountGroup();

		PriceListAccountGroup postPriceListAccountGroup =
			testPostPriceListIdPriceListAccountGroup_addPriceListAccountGroup(
				randomPriceListAccountGroup);

		assertEquals(randomPriceListAccountGroup, postPriceListAccountGroup);
		assertValid(postPriceListAccountGroup);
	}

	protected PriceListAccountGroup
			testPostPriceListIdPriceListAccountGroup_addPriceListAccountGroup(
				PriceListAccountGroup priceListAccountGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected PriceListAccountGroup
			testGraphQLPriceListAccountGroup_addPriceListAccountGroup()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		PriceListAccountGroup priceListAccountGroup,
		List<PriceListAccountGroup> priceListAccountGroups) {

		boolean contains = false;

		for (PriceListAccountGroup item : priceListAccountGroups) {
			if (equals(priceListAccountGroup, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			priceListAccountGroups + " does not contain " +
				priceListAccountGroup,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		PriceListAccountGroup priceListAccountGroup1,
		PriceListAccountGroup priceListAccountGroup2) {

		Assert.assertTrue(
			priceListAccountGroup1 + " does not equal " +
				priceListAccountGroup2,
			equals(priceListAccountGroup1, priceListAccountGroup2));
	}

	protected void assertEquals(
		List<PriceListAccountGroup> priceListAccountGroups1,
		List<PriceListAccountGroup> priceListAccountGroups2) {

		Assert.assertEquals(
			priceListAccountGroups1.size(), priceListAccountGroups2.size());

		for (int i = 0; i < priceListAccountGroups1.size(); i++) {
			PriceListAccountGroup priceListAccountGroup1 =
				priceListAccountGroups1.get(i);
			PriceListAccountGroup priceListAccountGroup2 =
				priceListAccountGroups2.get(i);

			assertEquals(priceListAccountGroup1, priceListAccountGroup2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<PriceListAccountGroup> priceListAccountGroups1,
		List<PriceListAccountGroup> priceListAccountGroups2) {

		Assert.assertEquals(
			priceListAccountGroups1.size(), priceListAccountGroups2.size());

		for (PriceListAccountGroup priceListAccountGroup1 :
				priceListAccountGroups1) {

			boolean contains = false;

			for (PriceListAccountGroup priceListAccountGroup2 :
					priceListAccountGroups2) {

				if (equals(priceListAccountGroup1, priceListAccountGroup2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				priceListAccountGroups2 + " does not contain " +
					priceListAccountGroup1,
				contains);
		}
	}

	protected void assertValid(PriceListAccountGroup priceListAccountGroup)
		throws Exception {

		boolean valid = true;

		if (priceListAccountGroup.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"accountGroupExternalReferenceCode",
					additionalAssertFieldName)) {

				if (priceListAccountGroup.
						getAccountGroupExternalReferenceCode() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("accountGroupId", additionalAssertFieldName)) {
				if (priceListAccountGroup.getAccountGroupId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("order", additionalAssertFieldName)) {
				if (priceListAccountGroup.getOrder() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"priceListExternalReferenceCode",
					additionalAssertFieldName)) {

				if (priceListAccountGroup.getPriceListExternalReferenceCode() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("priceListId", additionalAssertFieldName)) {
				if (priceListAccountGroup.getPriceListId() == null) {
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

	protected void assertValid(Page<PriceListAccountGroup> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<PriceListAccountGroup> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<PriceListAccountGroup> priceListAccountGroups =
			page.getItems();

		int size = priceListAccountGroups.size();

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
					com.liferay.headless.commerce.admin.pricing.dto.v1_0.
						PriceListAccountGroup.class)) {

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
		PriceListAccountGroup priceListAccountGroup1,
		PriceListAccountGroup priceListAccountGroup2) {

		if (priceListAccountGroup1 == priceListAccountGroup2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"accountGroupExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						priceListAccountGroup1.
							getAccountGroupExternalReferenceCode(),
						priceListAccountGroup2.
							getAccountGroupExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("accountGroupId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceListAccountGroup1.getAccountGroupId(),
						priceListAccountGroup2.getAccountGroupId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceListAccountGroup1.getId(),
						priceListAccountGroup2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("order", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceListAccountGroup1.getOrder(),
						priceListAccountGroup2.getOrder())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"priceListExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						priceListAccountGroup1.
							getPriceListExternalReferenceCode(),
						priceListAccountGroup2.
							getPriceListExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priceListId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceListAccountGroup1.getPriceListId(),
						priceListAccountGroup2.getPriceListId())) {

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

		if (!(_priceListAccountGroupResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_priceListAccountGroupResource;

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
		EntityField entityField, String operator,
		PriceListAccountGroup priceListAccountGroup) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accountGroupExternalReferenceCode")) {
			Object object =
				priceListAccountGroup.getAccountGroupExternalReferenceCode();

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

		if (entityFieldName.equals("accountGroupId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("order")) {
			sb.append(String.valueOf(priceListAccountGroup.getOrder()));

			return sb.toString();
		}

		if (entityFieldName.equals("priceListExternalReferenceCode")) {
			Object object =
				priceListAccountGroup.getPriceListExternalReferenceCode();

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

		if (entityFieldName.equals("priceListId")) {
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

	protected PriceListAccountGroup randomPriceListAccountGroup()
		throws Exception {

		return new PriceListAccountGroup() {
			{
				accountGroupExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				accountGroupId = RandomTestUtil.randomLong();
				id = RandomTestUtil.randomLong();
				order = RandomTestUtil.randomInt();
				priceListExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				priceListId = RandomTestUtil.randomLong();
			}
		};
	}

	protected PriceListAccountGroup randomIrrelevantPriceListAccountGroup()
		throws Exception {

		PriceListAccountGroup randomIrrelevantPriceListAccountGroup =
			randomPriceListAccountGroup();

		return randomIrrelevantPriceListAccountGroup;
	}

	protected PriceListAccountGroup randomPatchPriceListAccountGroup()
		throws Exception {

		return randomPriceListAccountGroup();
	}

	protected PriceListAccountGroupResource priceListAccountGroupResource;
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
		LogFactoryUtil.getLog(BasePriceListAccountGroupResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.commerce.admin.pricing.resource.v1_0.
		PriceListAccountGroupResource _priceListAccountGroupResource;

}