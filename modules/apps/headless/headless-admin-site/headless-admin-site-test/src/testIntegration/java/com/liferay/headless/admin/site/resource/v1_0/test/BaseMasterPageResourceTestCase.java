/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.admin.site.client.dto.v1_0.ContentPageSpecification;
import com.liferay.headless.admin.site.client.dto.v1_0.MasterPage;
import com.liferay.headless.admin.site.client.http.HttpInvoker;
import com.liferay.headless.admin.site.client.pagination.Page;
import com.liferay.headless.admin.site.client.pagination.Pagination;
import com.liferay.headless.admin.site.client.permission.Permission;
import com.liferay.headless.admin.site.client.resource.v1_0.MasterPageResource;
import com.liferay.headless.admin.site.client.serdes.v1_0.MasterPageSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
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
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public abstract class BaseMasterPageResourceTestCase {

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

		_masterPageResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		MasterPageResource.Builder builder = MasterPageResource.builder();

		masterPageResource = builder.authentication(
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

		MasterPage masterPage1 = randomMasterPage();

		String json = objectMapper.writeValueAsString(masterPage1);

		MasterPage masterPage2 = MasterPageSerDes.toDTO(json);

		Assert.assertTrue(equals(masterPage1, masterPage2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		MasterPage masterPage = randomMasterPage();

		String json1 = objectMapper.writeValueAsString(masterPage);
		String json2 = MasterPageSerDes.toJSON(masterPage);

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

		MasterPage masterPage = randomMasterPage();

		masterPage.setCreatorExternalReferenceCode(regex);
		masterPage.setExternalReferenceCode(regex);
		masterPage.setKey(regex);
		masterPage.setName(regex);
		masterPage.setUuid(regex);

		String json = MasterPageSerDes.toJSON(masterPage);

		Assert.assertFalse(json.contains(regex));

		masterPage = MasterPageSerDes.toDTO(json);

		Assert.assertEquals(
			regex, masterPage.getCreatorExternalReferenceCode());
		Assert.assertEquals(regex, masterPage.getExternalReferenceCode());
		Assert.assertEquals(regex, masterPage.getKey());
		Assert.assertEquals(regex, masterPage.getName());
		Assert.assertEquals(regex, masterPage.getUuid());
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPage()
		throws Exception {

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode();
		String irrelevantSiteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getIrrelevantSiteExternalReferenceCode();

		Page<MasterPage> page =
			masterPageResource.
				getSiteSiteByExternalReferenceCodeMasterPagesPage(
					siteExternalReferenceCode, null, null, null,
					Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantSiteExternalReferenceCode != null) {
			MasterPage irrelevantMasterPage =
				testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
					irrelevantSiteExternalReferenceCode,
					randomIrrelevantMasterPage());

			page =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						irrelevantSiteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantMasterPage, (List<MasterPage>)page.getItems());
			assertValid(
				page,
				testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getExpectedActions(
					irrelevantSiteExternalReferenceCode));
		}

		MasterPage masterPage1 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		MasterPage masterPage2 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		page =
			masterPageResource.
				getSiteSiteByExternalReferenceCodeMasterPagesPage(
					siteExternalReferenceCode, null, null, null,
					Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(masterPage1, (List<MasterPage>)page.getItems());
		assertContains(masterPage2, (List<MasterPage>)page.getItems());
		assertValid(
			page,
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getExpectedActions(
				siteExternalReferenceCode));
	}

	protected Map<String, Map<String, String>>
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getExpectedActions(
				String siteExternalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode();

		MasterPage masterPage1 = randomMasterPage();

		masterPage1 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, masterPage1);

		for (EntityField entityField : entityFields) {
			Page<MasterPage> page =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null,
						getFilterString(entityField, "between", masterPage1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(masterPage1),
				(List<MasterPage>)page.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilterDoubleEquals()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilterStringContains()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilterStringEquals()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilterStringStartsWith()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void
			testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithFilter(
				String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode();

		MasterPage masterPage1 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		MasterPage masterPage2 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		for (EntityField entityField : entityFields) {
			Page<MasterPage> page =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null,
						getFilterString(entityField, operator, masterPage1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(masterPage1),
				(List<MasterPage>)page.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithPagination()
		throws Exception {

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode();

		Page<MasterPage> masterPagePage =
			masterPageResource.
				getSiteSiteByExternalReferenceCodeMasterPagesPage(
					siteExternalReferenceCode, null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(masterPagePage.getTotalCount());

		MasterPage masterPage1 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		MasterPage masterPage2 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		MasterPage masterPage3 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, randomMasterPage());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<MasterPage> page1 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(masterPage1, (List<MasterPage>)page1.getItems());

			Page<MasterPage> page2 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(masterPage2, (List<MasterPage>)page2.getItems());

			Page<MasterPage> page3 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(masterPage3, (List<MasterPage>)page3.getItems());
		}
		else {
			Page<MasterPage> page1 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, totalCount + 2), null);

			List<MasterPage> masterPages1 = (List<MasterPage>)page1.getItems();

			Assert.assertEquals(
				masterPages1.toString(), totalCount + 2, masterPages1.size());

			Page<MasterPage> page2 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<MasterPage> masterPages2 = (List<MasterPage>)page2.getItems();

			Assert.assertEquals(
				masterPages2.toString(), 1, masterPages2.size());

			Page<MasterPage> page3 =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)totalCount + 3), null);

			assertContains(masterPage1, (List<MasterPage>)page3.getItems());
			assertContains(masterPage2, (List<MasterPage>)page3.getItems());
			assertContains(masterPage3, (List<MasterPage>)page3.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSortDateTime()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, masterPage1, masterPage2) -> {
				BeanTestUtil.setProperty(
					masterPage1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSortDouble()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, masterPage1, masterPage2) -> {
				BeanTestUtil.setProperty(
					masterPage1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					masterPage2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSortInteger()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, masterPage1, masterPage2) -> {
				BeanTestUtil.setProperty(masterPage1, entityField.getName(), 0);
				BeanTestUtil.setProperty(masterPage2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSortString()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSort(
			EntityField.Type.STRING,
			(entityField, masterPage1, masterPage2) -> {
				Class<?> clazz = masterPage1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						masterPage1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						masterPage2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						masterPage1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						masterPage2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						masterPage1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						masterPage2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void
			testGetSiteSiteByExternalReferenceCodeMasterPagesPageWithSort(
				EntityField.Type type,
				UnsafeTriConsumer
					<EntityField, MasterPage, MasterPage, Exception>
						unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode();

		MasterPage masterPage1 = randomMasterPage();
		MasterPage masterPage2 = randomMasterPage();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, masterPage1, masterPage2);
		}

		masterPage1 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, masterPage1);

		masterPage2 =
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				siteExternalReferenceCode, masterPage2);

		Page<MasterPage> page =
			masterPageResource.
				getSiteSiteByExternalReferenceCodeMasterPagesPage(
					siteExternalReferenceCode, null, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<MasterPage> ascPage =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(masterPage1, (List<MasterPage>)ascPage.getItems());
			assertContains(masterPage2, (List<MasterPage>)ascPage.getItems());

			Page<MasterPage> descPage =
				masterPageResource.
					getSiteSiteByExternalReferenceCodeMasterPagesPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(masterPage2, (List<MasterPage>)descPage.getItems());
			assertContains(masterPage1, (List<MasterPage>)descPage.getItems());
		}
	}

	protected MasterPage
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_addMasterPage(
				String siteExternalReferenceCode, MasterPage masterPage)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getSiteExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodeMasterPagesPage_getIrrelevantSiteExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		MasterPage randomMasterPage = randomMasterPage();

		MasterPage postMasterPage =
			testPostSiteSiteByExternalReferenceCodeMasterPage_addMasterPage(
				randomMasterPage);

		assertEquals(randomMasterPage, postMasterPage);
		assertValid(postMasterPage);
	}

	protected MasterPage
			testPostSiteSiteByExternalReferenceCodeMasterPage_addMasterPage(
				MasterPage masterPage)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPagePermissionsPage()
		throws Exception {

		MasterPage postMasterPage =
			testGetSiteSiteByExternalReferenceCodeMasterPagePermissionsPage_addMasterPage();

		Page<Permission> page =
			masterPageResource.
				getSiteSiteByExternalReferenceCodeMasterPagePermissionsPage(
					testGroup.getExternalReferenceCode(), RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected MasterPage
			testGetSiteSiteByExternalReferenceCodeMasterPagePermissionsPage_addMasterPage()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutSiteSiteByExternalReferenceCodeMasterPagePermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		MasterPage masterPage =
			testPutSiteSiteByExternalReferenceCodeMasterPagePermissionsPage_addMasterPage();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			masterPageResource.
				putSiteSiteByExternalReferenceCodeMasterPagePermissionsPageHttpResponse(
					null,
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"PERMISSIONS"});
								setRoleName(role.getName());
							}
						}
					}));

		assertHttpResponseStatusCode(
			404,
			masterPageResource.
				putSiteSiteByExternalReferenceCodeMasterPagePermissionsPageHttpResponse(
					null,
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"-"});
								setRoleName("-");
							}
						}
					}));
	}

	protected MasterPage
			testPutSiteSiteByExternalReferenceCodeMasterPagePermissionsPage_addMasterPage()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodeMasterPageNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testPatchSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutSiteSiteByExternalReferenceCodeMasterPage()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteExternalReferenceCodeMasterPagePermissionsPage()
		throws Exception {

		MasterPage postMasterPage =
			testGetSiteSiteExternalReferenceCodeMasterPagePermissionsPage_addMasterPage();

		Page<Permission> page =
			masterPageResource.
				getSiteSiteExternalReferenceCodeMasterPagePermissionsPage(
					testGroup.getExternalReferenceCode(),
					postMasterPage.getExternalReferenceCode(),
					RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected MasterPage
			testGetSiteSiteExternalReferenceCodeMasterPagePermissionsPage_addMasterPage()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutSiteSiteExternalReferenceCodeMasterPagePermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		MasterPage masterPage =
			testPutSiteSiteExternalReferenceCodeMasterPagePermissionsPage_addMasterPage();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			masterPageResource.
				putSiteSiteExternalReferenceCodeMasterPagePermissionsPageHttpResponse(
					null, null));

		assertHttpResponseStatusCode(
			404,
			masterPageResource.
				putSiteSiteExternalReferenceCodeMasterPagePermissionsPageHttpResponse(
					null, null));
	}

	protected MasterPage
			testPutSiteSiteExternalReferenceCodeMasterPagePermissionsPage_addMasterPage()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	@Test
	public void testPostSiteSiteByExternalReferenceCodeMasterPagePageSpecification()
		throws Exception {

		Assert.assertTrue(true);
	}

	protected void assertContains(
		MasterPage masterPage, List<MasterPage> masterPages) {

		boolean contains = false;

		for (MasterPage item : masterPages) {
			if (equals(masterPage, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			masterPages + " does not contain " + masterPage, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		MasterPage masterPage1, MasterPage masterPage2) {

		Assert.assertTrue(
			masterPage1 + " does not equal " + masterPage2,
			equals(masterPage1, masterPage2));
	}

	protected void assertEquals(
		List<MasterPage> masterPages1, List<MasterPage> masterPages2) {

		Assert.assertEquals(masterPages1.size(), masterPages2.size());

		for (int i = 0; i < masterPages1.size(); i++) {
			MasterPage masterPage1 = masterPages1.get(i);
			MasterPage masterPage2 = masterPages2.get(i);

			assertEquals(masterPage1, masterPage2);
		}
	}

	protected void assertEquals(
		ContentPageSpecification contentPageSpecification1,
		ContentPageSpecification contentPageSpecification2) {

		Assert.assertTrue(
			contentPageSpecification1 + " does not equal " +
				contentPageSpecification2,
			equals(contentPageSpecification1, contentPageSpecification2));
	}

	protected void assertEqualsIgnoringOrder(
		List<MasterPage> masterPages1, List<MasterPage> masterPages2) {

		Assert.assertEquals(masterPages1.size(), masterPages2.size());

		for (MasterPage masterPage1 : masterPages1) {
			boolean contains = false;

			for (MasterPage masterPage2 : masterPages2) {
				if (equals(masterPage1, masterPage2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				masterPages2 + " does not contain " + masterPage1, contains);
		}
	}

	protected void assertValid(MasterPage masterPage) throws Exception {
		boolean valid = true;

		if (masterPage.getDateCreated() == null) {
			valid = false;
		}

		if (masterPage.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (masterPage.getCreator() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"creatorExternalReferenceCode",
					additionalAssertFieldName)) {

				if (masterPage.getCreatorExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("datePublished", additionalAssertFieldName)) {
				if (masterPage.getDatePublished() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (masterPage.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (masterPage.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"keywordItemExternalReferences",
					additionalAssertFieldName)) {

				if (masterPage.getKeywordItemExternalReferences() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("keywords", additionalAssertFieldName)) {
				if (masterPage.getKeywords() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("markedAsDefault", additionalAssertFieldName)) {
				if (masterPage.getMarkedAsDefault() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (masterPage.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"pageSpecifications", additionalAssertFieldName)) {

				if (masterPage.getPageSpecifications() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"taxonomyCategories", additionalAssertFieldName)) {

				if (masterPage.getTaxonomyCategories() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"taxonomyCategoryItemExternalReferences",
					additionalAssertFieldName)) {

				if (masterPage.getTaxonomyCategoryItemExternalReferences() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("thumbnail", additionalAssertFieldName)) {
				if (masterPage.getThumbnail() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (masterPage.getUuid() == null) {
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

	protected void assertValid(Page<MasterPage> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<MasterPage> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<MasterPage> masterPages = page.getItems();

		int size = masterPages.size();

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

	protected void assertValid(
		ContentPageSpecification contentPageSpecification) {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalContentPageSpecificationAssertFieldNames()) {

			if (Objects.equals("pageExperiences", additionalAssertFieldName)) {
				if (contentPageSpecification.getPageExperiences() == null) {
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

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected String[] getAdditionalContentPageSpecificationAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.headless.admin.site.dto.v1_0.MasterPage.
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

	protected boolean equals(MasterPage masterPage1, MasterPage masterPage2) {
		if (masterPage1 == masterPage2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getCreator(), masterPage2.getCreator())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"creatorExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getCreatorExternalReferenceCode(),
						masterPage2.getCreatorExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getDateCreated(),
						masterPage2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getDateModified(),
						masterPage2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("datePublished", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getDatePublished(),
						masterPage2.getDatePublished())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getExternalReferenceCode(),
						masterPage2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getKey(), masterPage2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"keywordItemExternalReferences",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getKeywordItemExternalReferences(),
						masterPage2.getKeywordItemExternalReferences())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("keywords", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getKeywords(), masterPage2.getKeywords())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("markedAsDefault", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getMarkedAsDefault(),
						masterPage2.getMarkedAsDefault())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getName(), masterPage2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"pageSpecifications", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getPageSpecifications(),
						masterPage2.getPageSpecifications())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"taxonomyCategories", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getTaxonomyCategories(),
						masterPage2.getTaxonomyCategories())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"taxonomyCategoryItemExternalReferences",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						masterPage1.getTaxonomyCategoryItemExternalReferences(),
						masterPage2.
							getTaxonomyCategoryItemExternalReferences())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("thumbnail", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getThumbnail(),
						masterPage2.getThumbnail())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						masterPage1.getUuid(), masterPage2.getUuid())) {

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

	protected boolean equals(
		ContentPageSpecification contentPageSpecification1,
		ContentPageSpecification contentPageSpecification2) {

		if (contentPageSpecification1 == contentPageSpecification2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalContentPageSpecificationAssertFieldNames()) {

			if (Objects.equals("pageExperiences", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contentPageSpecification1.getPageExperiences(),
						contentPageSpecification2.getPageExperiences())) {

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

		if (!(_masterPageResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_masterPageResource;

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
		EntityField entityField, String operator, MasterPage masterPage) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("creator")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("creatorExternalReferenceCode")) {
			Object object = masterPage.getCreatorExternalReferenceCode();

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

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				Date date = masterPage.getDateCreated();

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

				sb.append(_dateFormat.format(masterPage.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				Date date = masterPage.getDateModified();

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

				sb.append(_dateFormat.format(masterPage.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("datePublished")) {
			if (operator.equals("between")) {
				Date date = masterPage.getDatePublished();

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

				sb.append(_dateFormat.format(masterPage.getDatePublished()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			Object object = masterPage.getExternalReferenceCode();

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

		if (entityFieldName.equals("key")) {
			Object object = masterPage.getKey();

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

		if (entityFieldName.equals("keywordItemExternalReferences")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("keywords")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("markedAsDefault")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			Object object = masterPage.getName();

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

		if (entityFieldName.equals("pageSpecifications")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("taxonomyCategories")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("taxonomyCategoryItemExternalReferences")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("thumbnail")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("uuid")) {
			Object object = masterPage.getUuid();

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

	protected MasterPage randomMasterPage() throws Exception {
		return new MasterPage() {
			{
				creatorExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				datePublished = RandomTestUtil.nextDate();
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				markedAsDefault = RandomTestUtil.randomBoolean();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				uuid = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected MasterPage randomIrrelevantMasterPage() throws Exception {
		MasterPage randomIrrelevantMasterPage = randomMasterPage();

		return randomIrrelevantMasterPage;
	}

	protected MasterPage randomPatchMasterPage() throws Exception {
		return randomMasterPage();
	}

	protected ContentPageSpecification randomContentPageSpecification()
		throws Exception {

		return new ContentPageSpecification() {
			{
			}
		};
	}

	protected MasterPageResource masterPageResource;
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
		LogFactoryUtil.getLog(BaseMasterPageResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.admin.site.resource.v1_0.MasterPageResource
		_masterPageResource;

}