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

import com.liferay.headless.commerce.admin.pricing.client.dto.v2_0.PriceModifierProductGroup;
import com.liferay.headless.commerce.admin.pricing.client.http.HttpInvoker;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Page;
import com.liferay.headless.commerce.admin.pricing.client.pagination.Pagination;
import com.liferay.headless.commerce.admin.pricing.client.resource.v2_0.PriceModifierProductGroupResource;
import com.liferay.headless.commerce.admin.pricing.client.serdes.v2_0.PriceModifierProductGroupSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
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
public abstract class BasePriceModifierProductGroupResourceTestCase {

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

		_priceModifierProductGroupResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		PriceModifierProductGroupResource.Builder builder =
			PriceModifierProductGroupResource.builder();

		priceModifierProductGroupResource = builder.authentication(
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

		PriceModifierProductGroup priceModifierProductGroup1 =
			randomPriceModifierProductGroup();

		String json = objectMapper.writeValueAsString(
			priceModifierProductGroup1);

		PriceModifierProductGroup priceModifierProductGroup2 =
			PriceModifierProductGroupSerDes.toDTO(json);

		Assert.assertTrue(
			equals(priceModifierProductGroup1, priceModifierProductGroup2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		PriceModifierProductGroup priceModifierProductGroup =
			randomPriceModifierProductGroup();

		String json1 = objectMapper.writeValueAsString(
			priceModifierProductGroup);
		String json2 = PriceModifierProductGroupSerDes.toJSON(
			priceModifierProductGroup);

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

		PriceModifierProductGroup priceModifierProductGroup =
			randomPriceModifierProductGroup();

		priceModifierProductGroup.setPriceModifierExternalReferenceCode(regex);
		priceModifierProductGroup.setProductGroupExternalReferenceCode(regex);

		String json = PriceModifierProductGroupSerDes.toJSON(
			priceModifierProductGroup);

		Assert.assertFalse(json.contains(regex));

		priceModifierProductGroup = PriceModifierProductGroupSerDes.toDTO(json);

		Assert.assertEquals(
			regex,
			priceModifierProductGroup.getPriceModifierExternalReferenceCode());
		Assert.assertEquals(
			regex,
			priceModifierProductGroup.getProductGroupExternalReferenceCode());
	}

	@Test
	public void testDeletePriceModifierProductGroup() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeletePriceModifierProductGroup() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage()
		throws Exception {

		String externalReferenceCode =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getIrrelevantExternalReferenceCode();

		Page<PriceModifierProductGroup> page =
			priceModifierProductGroupResource.
				getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
					externalReferenceCode, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantExternalReferenceCode != null) {
			PriceModifierProductGroup irrelevantPriceModifierProductGroup =
				testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
					irrelevantExternalReferenceCode,
					randomIrrelevantPriceModifierProductGroup());

			page =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						irrelevantExternalReferenceCode,
						Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPriceModifierProductGroup,
				(List<PriceModifierProductGroup>)page.getItems());
			assertValid(
				page,
				testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExpectedActions(
					irrelevantExternalReferenceCode));
		}

		PriceModifierProductGroup priceModifierProductGroup1 =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				externalReferenceCode, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup2 =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				externalReferenceCode, randomPriceModifierProductGroup());

		page =
			priceModifierProductGroupResource.
				getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
					externalReferenceCode, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			priceModifierProductGroup1,
			(List<PriceModifierProductGroup>)page.getItems());
		assertContains(
			priceModifierProductGroup2,
			(List<PriceModifierProductGroup>)page.getItems());
		assertValid(
			page,
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExpectedActions(
				externalReferenceCode));
	}

	protected Map<String, Map<String, String>>
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExpectedActions(
				String externalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExternalReferenceCode();

		Page<PriceModifierProductGroup> priceModifierProductGroupPage =
			priceModifierProductGroupResource.
				getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
					externalReferenceCode, null);

		int totalCount = GetterUtil.getInteger(
			priceModifierProductGroupPage.getTotalCount());

		PriceModifierProductGroup priceModifierProductGroup1 =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				externalReferenceCode, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup2 =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				externalReferenceCode, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup3 =
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				externalReferenceCode, randomPriceModifierProductGroup());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<PriceModifierProductGroup> page1 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)page1.getItems());

			Page<PriceModifierProductGroup> page2 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)page2.getItems());

			Page<PriceModifierProductGroup> page3 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit));

			assertContains(
				priceModifierProductGroup3,
				(List<PriceModifierProductGroup>)page3.getItems());
		}
		else {
			Page<PriceModifierProductGroup> page1 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(1, totalCount + 2));

			List<PriceModifierProductGroup> priceModifierProductGroups1 =
				(List<PriceModifierProductGroup>)page1.getItems();

			Assert.assertEquals(
				priceModifierProductGroups1.toString(), totalCount + 2,
				priceModifierProductGroups1.size());

			Page<PriceModifierProductGroup> page2 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<PriceModifierProductGroup> priceModifierProductGroups2 =
				(List<PriceModifierProductGroup>)page2.getItems();

			Assert.assertEquals(
				priceModifierProductGroups2.toString(), 1,
				priceModifierProductGroups2.size());

			Page<PriceModifierProductGroup> page3 =
				priceModifierProductGroupResource.
					getPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage(
						externalReferenceCode,
						Pagination.of(1, (int)totalCount + 3));

			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)page3.getItems());
			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)page3.getItems());
			assertContains(
				priceModifierProductGroup3,
				(List<PriceModifierProductGroup>)page3.getItems());
		}
	}

	protected PriceModifierProductGroup
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_addPriceModifierProductGroup(
				String externalReferenceCode,
				PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetPriceModifierByExternalReferenceCodePriceModifierProductGroupsPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostPriceModifierByExternalReferenceCodePriceModifierProductGroup()
		throws Exception {

		PriceModifierProductGroup randomPriceModifierProductGroup =
			randomPriceModifierProductGroup();

		PriceModifierProductGroup postPriceModifierProductGroup =
			testPostPriceModifierByExternalReferenceCodePriceModifierProductGroup_addPriceModifierProductGroup(
				randomPriceModifierProductGroup);

		assertEquals(
			randomPriceModifierProductGroup, postPriceModifierProductGroup);
		assertValid(postPriceModifierProductGroup);
	}

	protected PriceModifierProductGroup
			testPostPriceModifierByExternalReferenceCodePriceModifierProductGroup_addPriceModifierProductGroup(
				PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPage()
		throws Exception {

		Long id = testGetPriceModifierIdPriceModifierProductGroupsPage_getId();
		Long irrelevantId =
			testGetPriceModifierIdPriceModifierProductGroupsPage_getIrrelevantId();

		Page<PriceModifierProductGroup> page =
			priceModifierProductGroupResource.
				getPriceModifierIdPriceModifierProductGroupsPage(
					id, null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantId != null) {
			PriceModifierProductGroup irrelevantPriceModifierProductGroup =
				testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
					irrelevantId, randomIrrelevantPriceModifierProductGroup());

			page =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						irrelevantId, null, null,
						Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPriceModifierProductGroup,
				(List<PriceModifierProductGroup>)page.getItems());
			assertValid(
				page,
				testGetPriceModifierIdPriceModifierProductGroupsPage_getExpectedActions(
					irrelevantId));
		}

		PriceModifierProductGroup priceModifierProductGroup1 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup2 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		page =
			priceModifierProductGroupResource.
				getPriceModifierIdPriceModifierProductGroupsPage(
					id, null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			priceModifierProductGroup1,
			(List<PriceModifierProductGroup>)page.getItems());
		assertContains(
			priceModifierProductGroup2,
			(List<PriceModifierProductGroup>)page.getItems());
		assertValid(
			page,
			testGetPriceModifierIdPriceModifierProductGroupsPage_getExpectedActions(
				id));
	}

	protected Map<String, Map<String, String>>
			testGetPriceModifierIdPriceModifierProductGroupsPage_getExpectedActions(
				Long id)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetPriceModifierIdPriceModifierProductGroupsPage_getId();

		PriceModifierProductGroup priceModifierProductGroup1 =
			randomPriceModifierProductGroup();

		priceModifierProductGroup1 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, priceModifierProductGroup1);

		for (EntityField entityField : entityFields) {
			Page<PriceModifierProductGroup> page =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null,
						getFilterString(
							entityField, "between", priceModifierProductGroup1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(priceModifierProductGroup1),
				(List<PriceModifierProductGroup>)page.getItems());
		}
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithFilterDoubleEquals()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithFilterStringContains()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithFilterStringEquals()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithFilterStringStartsWith()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void
			testGetPriceModifierIdPriceModifierProductGroupsPageWithFilter(
				String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetPriceModifierIdPriceModifierProductGroupsPage_getId();

		PriceModifierProductGroup priceModifierProductGroup1 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		PriceModifierProductGroup priceModifierProductGroup2 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		for (EntityField entityField : entityFields) {
			Page<PriceModifierProductGroup> page =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null,
						getFilterString(
							entityField, operator, priceModifierProductGroup1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(priceModifierProductGroup1),
				(List<PriceModifierProductGroup>)page.getItems());
		}
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithPagination()
		throws Exception {

		Long id = testGetPriceModifierIdPriceModifierProductGroupsPage_getId();

		Page<PriceModifierProductGroup> priceModifierProductGroupPage =
			priceModifierProductGroupResource.
				getPriceModifierIdPriceModifierProductGroupsPage(
					id, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			priceModifierProductGroupPage.getTotalCount());

		PriceModifierProductGroup priceModifierProductGroup1 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup2 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		PriceModifierProductGroup priceModifierProductGroup3 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, randomPriceModifierProductGroup());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<PriceModifierProductGroup> page1 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)page1.getItems());

			Page<PriceModifierProductGroup> page2 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)page2.getItems());

			Page<PriceModifierProductGroup> page3 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				priceModifierProductGroup3,
				(List<PriceModifierProductGroup>)page3.getItems());
		}
		else {
			Page<PriceModifierProductGroup> page1 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null, Pagination.of(1, totalCount + 2), null);

			List<PriceModifierProductGroup> priceModifierProductGroups1 =
				(List<PriceModifierProductGroup>)page1.getItems();

			Assert.assertEquals(
				priceModifierProductGroups1.toString(), totalCount + 2,
				priceModifierProductGroups1.size());

			Page<PriceModifierProductGroup> page2 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null, Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<PriceModifierProductGroup> priceModifierProductGroups2 =
				(List<PriceModifierProductGroup>)page2.getItems();

			Assert.assertEquals(
				priceModifierProductGroups2.toString(), 1,
				priceModifierProductGroups2.size());

			Page<PriceModifierProductGroup> page3 =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null, Pagination.of(1, (int)totalCount + 3),
						null);

			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)page3.getItems());
			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)page3.getItems());
			assertContains(
				priceModifierProductGroup3,
				(List<PriceModifierProductGroup>)page3.getItems());
		}
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithSortDateTime()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, priceModifierProductGroup1,
			 priceModifierProductGroup2) -> {

				BeanTestUtil.setProperty(
					priceModifierProductGroup1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithSortDouble()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, priceModifierProductGroup1,
			 priceModifierProductGroup2) -> {

				BeanTestUtil.setProperty(
					priceModifierProductGroup1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					priceModifierProductGroup2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithSortInteger()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, priceModifierProductGroup1,
			 priceModifierProductGroup2) -> {

				BeanTestUtil.setProperty(
					priceModifierProductGroup1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					priceModifierProductGroup2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetPriceModifierIdPriceModifierProductGroupsPageWithSortString()
		throws Exception {

		testGetPriceModifierIdPriceModifierProductGroupsPageWithSort(
			EntityField.Type.STRING,
			(entityField, priceModifierProductGroup1,
			 priceModifierProductGroup2) -> {

				Class<?> clazz = priceModifierProductGroup1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						priceModifierProductGroup1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						priceModifierProductGroup2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						priceModifierProductGroup1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						priceModifierProductGroup2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						priceModifierProductGroup1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						priceModifierProductGroup2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetPriceModifierIdPriceModifierProductGroupsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, PriceModifierProductGroup,
				 PriceModifierProductGroup, Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long id = testGetPriceModifierIdPriceModifierProductGroupsPage_getId();

		PriceModifierProductGroup priceModifierProductGroup1 =
			randomPriceModifierProductGroup();
		PriceModifierProductGroup priceModifierProductGroup2 =
			randomPriceModifierProductGroup();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, priceModifierProductGroup1,
				priceModifierProductGroup2);
		}

		priceModifierProductGroup1 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, priceModifierProductGroup1);

		priceModifierProductGroup2 =
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				id, priceModifierProductGroup2);

		Page<PriceModifierProductGroup> page =
			priceModifierProductGroupResource.
				getPriceModifierIdPriceModifierProductGroupsPage(
					id, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<PriceModifierProductGroup> ascPage =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)ascPage.getItems());
			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)ascPage.getItems());

			Page<PriceModifierProductGroup> descPage =
				priceModifierProductGroupResource.
					getPriceModifierIdPriceModifierProductGroupsPage(
						id, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(
				priceModifierProductGroup2,
				(List<PriceModifierProductGroup>)descPage.getItems());
			assertContains(
				priceModifierProductGroup1,
				(List<PriceModifierProductGroup>)descPage.getItems());
		}
	}

	protected PriceModifierProductGroup
			testGetPriceModifierIdPriceModifierProductGroupsPage_addPriceModifierProductGroup(
				Long id, PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetPriceModifierIdPriceModifierProductGroupsPage_getId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetPriceModifierIdPriceModifierProductGroupsPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostPriceModifierIdPriceModifierProductGroup()
		throws Exception {

		PriceModifierProductGroup randomPriceModifierProductGroup =
			randomPriceModifierProductGroup();

		PriceModifierProductGroup postPriceModifierProductGroup =
			testPostPriceModifierIdPriceModifierProductGroup_addPriceModifierProductGroup(
				randomPriceModifierProductGroup);

		assertEquals(
			randomPriceModifierProductGroup, postPriceModifierProductGroup);
		assertValid(postPriceModifierProductGroup);
	}

	protected PriceModifierProductGroup
			testPostPriceModifierIdPriceModifierProductGroup_addPriceModifierProductGroup(
				PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(
		PriceModifierProductGroup priceModifierProductGroup,
		List<PriceModifierProductGroup> priceModifierProductGroups) {

		boolean contains = false;

		for (PriceModifierProductGroup item : priceModifierProductGroups) {
			if (equals(priceModifierProductGroup, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			priceModifierProductGroups + " does not contain " +
				priceModifierProductGroup,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		PriceModifierProductGroup priceModifierProductGroup1,
		PriceModifierProductGroup priceModifierProductGroup2) {

		Assert.assertTrue(
			priceModifierProductGroup1 + " does not equal " +
				priceModifierProductGroup2,
			equals(priceModifierProductGroup1, priceModifierProductGroup2));
	}

	protected void assertEquals(
		List<PriceModifierProductGroup> priceModifierProductGroups1,
		List<PriceModifierProductGroup> priceModifierProductGroups2) {

		Assert.assertEquals(
			priceModifierProductGroups1.size(),
			priceModifierProductGroups2.size());

		for (int i = 0; i < priceModifierProductGroups1.size(); i++) {
			PriceModifierProductGroup priceModifierProductGroup1 =
				priceModifierProductGroups1.get(i);
			PriceModifierProductGroup priceModifierProductGroup2 =
				priceModifierProductGroups2.get(i);

			assertEquals(
				priceModifierProductGroup1, priceModifierProductGroup2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<PriceModifierProductGroup> priceModifierProductGroups1,
		List<PriceModifierProductGroup> priceModifierProductGroups2) {

		Assert.assertEquals(
			priceModifierProductGroups1.size(),
			priceModifierProductGroups2.size());

		for (PriceModifierProductGroup priceModifierProductGroup1 :
				priceModifierProductGroups1) {

			boolean contains = false;

			for (PriceModifierProductGroup priceModifierProductGroup2 :
					priceModifierProductGroups2) {

				if (equals(
						priceModifierProductGroup1,
						priceModifierProductGroup2)) {

					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				priceModifierProductGroups2 + " does not contain " +
					priceModifierProductGroup1,
				contains);
		}
	}

	protected void assertValid(
			PriceModifierProductGroup priceModifierProductGroup)
		throws Exception {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (priceModifierProductGroup.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"priceModifierExternalReferenceCode",
					additionalAssertFieldName)) {

				if (priceModifierProductGroup.
						getPriceModifierExternalReferenceCode() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("priceModifierId", additionalAssertFieldName)) {
				if (priceModifierProductGroup.getPriceModifierId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"priceModifierProductGroupId", additionalAssertFieldName)) {

				if (priceModifierProductGroup.
						getPriceModifierProductGroupId() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("productGroup", additionalAssertFieldName)) {
				if (priceModifierProductGroup.getProductGroup() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"productGroupExternalReferenceCode",
					additionalAssertFieldName)) {

				if (priceModifierProductGroup.
						getProductGroupExternalReferenceCode() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("productGroupId", additionalAssertFieldName)) {
				if (priceModifierProductGroup.getProductGroupId() == null) {
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

	protected void assertValid(Page<PriceModifierProductGroup> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<PriceModifierProductGroup> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<PriceModifierProductGroup>
			priceModifierProductGroups = page.getItems();

		int size = priceModifierProductGroups.size();

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
						PriceModifierProductGroup.class)) {

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
		PriceModifierProductGroup priceModifierProductGroup1,
		PriceModifierProductGroup priceModifierProductGroup2) {

		if (priceModifierProductGroup1 == priceModifierProductGroup2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals(
						(Map)priceModifierProductGroup1.getActions(),
						(Map)priceModifierProductGroup2.getActions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"priceModifierExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						priceModifierProductGroup1.
							getPriceModifierExternalReferenceCode(),
						priceModifierProductGroup2.
							getPriceModifierExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priceModifierId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceModifierProductGroup1.getPriceModifierId(),
						priceModifierProductGroup2.getPriceModifierId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"priceModifierProductGroupId", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						priceModifierProductGroup1.
							getPriceModifierProductGroupId(),
						priceModifierProductGroup2.
							getPriceModifierProductGroupId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productGroup", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceModifierProductGroup1.getProductGroup(),
						priceModifierProductGroup2.getProductGroup())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"productGroupExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						priceModifierProductGroup1.
							getProductGroupExternalReferenceCode(),
						priceModifierProductGroup2.
							getProductGroupExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productGroupId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						priceModifierProductGroup1.getProductGroupId(),
						priceModifierProductGroup2.getProductGroupId())) {

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

		if (!(_priceModifierProductGroupResource instanceof
				EntityModelResource)) {

			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_priceModifierProductGroupResource;

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
		PriceModifierProductGroup priceModifierProductGroup) {

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

		if (entityFieldName.equals("priceModifierExternalReferenceCode")) {
			Object object =
				priceModifierProductGroup.
					getPriceModifierExternalReferenceCode();

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

		if (entityFieldName.equals("priceModifierId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("priceModifierProductGroupId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productGroup")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productGroupExternalReferenceCode")) {
			Object object =
				priceModifierProductGroup.
					getProductGroupExternalReferenceCode();

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

		if (entityFieldName.equals("productGroupId")) {
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

	protected PriceModifierProductGroup randomPriceModifierProductGroup()
		throws Exception {

		return new PriceModifierProductGroup() {
			{
				priceModifierExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				priceModifierId = RandomTestUtil.randomLong();
				priceModifierProductGroupId = RandomTestUtil.randomLong();
				productGroupExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productGroupId = RandomTestUtil.randomLong();
			}
		};
	}

	protected PriceModifierProductGroup
			randomIrrelevantPriceModifierProductGroup()
		throws Exception {

		PriceModifierProductGroup randomIrrelevantPriceModifierProductGroup =
			randomPriceModifierProductGroup();

		return randomIrrelevantPriceModifierProductGroup;
	}

	protected PriceModifierProductGroup randomPatchPriceModifierProductGroup()
		throws Exception {

		return randomPriceModifierProductGroup();
	}

	protected PriceModifierProductGroupResource
		priceModifierProductGroupResource;
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
		LogFactoryUtil.getLog(
			BasePriceModifierProductGroupResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.commerce.admin.pricing.resource.v2_0.
		PriceModifierProductGroupResource _priceModifierProductGroupResource;

}