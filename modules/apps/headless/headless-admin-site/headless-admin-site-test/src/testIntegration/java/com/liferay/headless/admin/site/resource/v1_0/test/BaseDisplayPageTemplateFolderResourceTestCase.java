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

import com.liferay.headless.admin.site.client.dto.v1_0.DisplayPageTemplateFolder;
import com.liferay.headless.admin.site.client.http.HttpInvoker;
import com.liferay.headless.admin.site.client.pagination.Page;
import com.liferay.headless.admin.site.client.pagination.Pagination;
import com.liferay.headless.admin.site.client.permission.Permission;
import com.liferay.headless.admin.site.client.resource.v1_0.DisplayPageTemplateFolderResource;
import com.liferay.headless.admin.site.client.serdes.v1_0.DisplayPageTemplateFolderSerDes;
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
public abstract class BaseDisplayPageTemplateFolderResourceTestCase {

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

		_displayPageTemplateFolderResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		DisplayPageTemplateFolderResource.Builder builder =
			DisplayPageTemplateFolderResource.builder();

		displayPageTemplateFolderResource = builder.authentication(
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

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			randomDisplayPageTemplateFolder();

		String json = objectMapper.writeValueAsString(
			displayPageTemplateFolder1);

		DisplayPageTemplateFolder displayPageTemplateFolder2 =
			DisplayPageTemplateFolderSerDes.toDTO(json);

		Assert.assertTrue(
			equals(displayPageTemplateFolder1, displayPageTemplateFolder2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		DisplayPageTemplateFolder displayPageTemplateFolder =
			randomDisplayPageTemplateFolder();

		String json1 = objectMapper.writeValueAsString(
			displayPageTemplateFolder);
		String json2 = DisplayPageTemplateFolderSerDes.toJSON(
			displayPageTemplateFolder);

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

		DisplayPageTemplateFolder displayPageTemplateFolder =
			randomDisplayPageTemplateFolder();

		displayPageTemplateFolder.setCreatorExternalReferenceCode(regex);
		displayPageTemplateFolder.setDescription(regex);
		displayPageTemplateFolder.setExternalReferenceCode(regex);
		displayPageTemplateFolder.setKey(regex);
		displayPageTemplateFolder.setName(regex);
		displayPageTemplateFolder.
			setParentDisplayPageTemplateFolderExternalReferenceCode(regex);
		displayPageTemplateFolder.setUuid(regex);

		String json = DisplayPageTemplateFolderSerDes.toJSON(
			displayPageTemplateFolder);

		Assert.assertFalse(json.contains(regex));

		displayPageTemplateFolder = DisplayPageTemplateFolderSerDes.toDTO(json);

		Assert.assertEquals(
			regex, displayPageTemplateFolder.getCreatorExternalReferenceCode());
		Assert.assertEquals(regex, displayPageTemplateFolder.getDescription());
		Assert.assertEquals(
			regex, displayPageTemplateFolder.getExternalReferenceCode());
		Assert.assertEquals(regex, displayPageTemplateFolder.getKey());
		Assert.assertEquals(regex, displayPageTemplateFolder.getName());
		Assert.assertEquals(
			regex,
			displayPageTemplateFolder.
				getParentDisplayPageTemplateFolderExternalReferenceCode());
		Assert.assertEquals(regex, displayPageTemplateFolder.getUuid());
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage()
		throws Exception {

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode();
		String irrelevantSiteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getIrrelevantSiteExternalReferenceCode();

		Page<DisplayPageTemplateFolder> page =
			displayPageTemplateFolderResource.
				getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
					siteExternalReferenceCode, null, null, null,
					Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantSiteExternalReferenceCode != null) {
			DisplayPageTemplateFolder irrelevantDisplayPageTemplateFolder =
				testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
					irrelevantSiteExternalReferenceCode,
					randomIrrelevantDisplayPageTemplateFolder());

			page =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						irrelevantSiteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDisplayPageTemplateFolder,
				(List<DisplayPageTemplateFolder>)page.getItems());
			assertValid(
				page,
				testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getExpectedActions(
					irrelevantSiteExternalReferenceCode));
		}

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		DisplayPageTemplateFolder displayPageTemplateFolder2 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		page =
			displayPageTemplateFolderResource.
				getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
					siteExternalReferenceCode, null, null, null,
					Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			displayPageTemplateFolder1,
			(List<DisplayPageTemplateFolder>)page.getItems());
		assertContains(
			displayPageTemplateFolder2,
			(List<DisplayPageTemplateFolder>)page.getItems());
		assertValid(
			page,
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getExpectedActions(
				siteExternalReferenceCode));
	}

	protected Map<String, Map<String, String>>
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getExpectedActions(
				String siteExternalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode();

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			randomDisplayPageTemplateFolder();

		displayPageTemplateFolder1 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, displayPageTemplateFolder1);

		for (EntityField entityField : entityFields) {
			Page<DisplayPageTemplateFolder> page =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null,
						getFilterString(
							entityField, "between", displayPageTemplateFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(displayPageTemplateFolder1),
				(List<DisplayPageTemplateFolder>)page.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilterDoubleEquals()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilterStringContains()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilterStringEquals()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilterStringStartsWith()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithFilter(
				String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode();

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		DisplayPageTemplateFolder displayPageTemplateFolder2 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		for (EntityField entityField : entityFields) {
			Page<DisplayPageTemplateFolder> page =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null,
						getFilterString(
							entityField, operator, displayPageTemplateFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(displayPageTemplateFolder1),
				(List<DisplayPageTemplateFolder>)page.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithPagination()
		throws Exception {

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode();

		Page<DisplayPageTemplateFolder> displayPageTemplateFolderPage =
			displayPageTemplateFolderResource.
				getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
					siteExternalReferenceCode, null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			displayPageTemplateFolderPage.getTotalCount());

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		DisplayPageTemplateFolder displayPageTemplateFolder2 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		DisplayPageTemplateFolder displayPageTemplateFolder3 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, randomDisplayPageTemplateFolder());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<DisplayPageTemplateFolder> page1 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				displayPageTemplateFolder1,
				(List<DisplayPageTemplateFolder>)page1.getItems());

			Page<DisplayPageTemplateFolder> page2 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				displayPageTemplateFolder2,
				(List<DisplayPageTemplateFolder>)page2.getItems());

			Page<DisplayPageTemplateFolder> page3 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				displayPageTemplateFolder3,
				(List<DisplayPageTemplateFolder>)page3.getItems());
		}
		else {
			Page<DisplayPageTemplateFolder> page1 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, totalCount + 2), null);

			List<DisplayPageTemplateFolder> displayPageTemplateFolders1 =
				(List<DisplayPageTemplateFolder>)page1.getItems();

			Assert.assertEquals(
				displayPageTemplateFolders1.toString(), totalCount + 2,
				displayPageTemplateFolders1.size());

			Page<DisplayPageTemplateFolder> page2 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<DisplayPageTemplateFolder> displayPageTemplateFolders2 =
				(List<DisplayPageTemplateFolder>)page2.getItems();

			Assert.assertEquals(
				displayPageTemplateFolders2.toString(), 1,
				displayPageTemplateFolders2.size());

			Page<DisplayPageTemplateFolder> page3 =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)totalCount + 3), null);

			assertContains(
				displayPageTemplateFolder1,
				(List<DisplayPageTemplateFolder>)page3.getItems());
			assertContains(
				displayPageTemplateFolder2,
				(List<DisplayPageTemplateFolder>)page3.getItems());
			assertContains(
				displayPageTemplateFolder3,
				(List<DisplayPageTemplateFolder>)page3.getItems());
		}
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSortDateTime()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, displayPageTemplateFolder1,
			 displayPageTemplateFolder2) -> {

				BeanTestUtil.setProperty(
					displayPageTemplateFolder1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSortDouble()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, displayPageTemplateFolder1,
			 displayPageTemplateFolder2) -> {

				BeanTestUtil.setProperty(
					displayPageTemplateFolder1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					displayPageTemplateFolder2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSortInteger()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, displayPageTemplateFolder1,
			 displayPageTemplateFolder2) -> {

				BeanTestUtil.setProperty(
					displayPageTemplateFolder1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					displayPageTemplateFolder2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSortString()
		throws Exception {

		testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSort(
			EntityField.Type.STRING,
			(entityField, displayPageTemplateFolder1,
			 displayPageTemplateFolder2) -> {

				Class<?> clazz = displayPageTemplateFolder1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						displayPageTemplateFolder1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						displayPageTemplateFolder2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						displayPageTemplateFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						displayPageTemplateFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						displayPageTemplateFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						displayPageTemplateFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPageWithSort(
				EntityField.Type type,
				UnsafeTriConsumer
					<EntityField, DisplayPageTemplateFolder,
					 DisplayPageTemplateFolder, Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode();

		DisplayPageTemplateFolder displayPageTemplateFolder1 =
			randomDisplayPageTemplateFolder();
		DisplayPageTemplateFolder displayPageTemplateFolder2 =
			randomDisplayPageTemplateFolder();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, displayPageTemplateFolder1,
				displayPageTemplateFolder2);
		}

		displayPageTemplateFolder1 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, displayPageTemplateFolder1);

		displayPageTemplateFolder2 =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				siteExternalReferenceCode, displayPageTemplateFolder2);

		Page<DisplayPageTemplateFolder> page =
			displayPageTemplateFolderResource.
				getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
					siteExternalReferenceCode, null, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<DisplayPageTemplateFolder> ascPage =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(
				displayPageTemplateFolder1,
				(List<DisplayPageTemplateFolder>)ascPage.getItems());
			assertContains(
				displayPageTemplateFolder2,
				(List<DisplayPageTemplateFolder>)ascPage.getItems());

			Page<DisplayPageTemplateFolder> descPage =
				displayPageTemplateFolderResource.
					getSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage(
						siteExternalReferenceCode, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(
				displayPageTemplateFolder2,
				(List<DisplayPageTemplateFolder>)descPage.getItems());
			assertContains(
				displayPageTemplateFolder1,
				(List<DisplayPageTemplateFolder>)descPage.getItems());
		}
	}

	protected DisplayPageTemplateFolder
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_addDisplayPageTemplateFolder(
				String siteExternalReferenceCode,
				DisplayPageTemplateFolder displayPageTemplateFolder)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getSiteExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFoldersPage_getIrrelevantSiteExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		DisplayPageTemplateFolder randomDisplayPageTemplateFolder =
			randomDisplayPageTemplateFolder();

		DisplayPageTemplateFolder postDisplayPageTemplateFolder =
			testPostSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder_addDisplayPageTemplateFolder(
				randomDisplayPageTemplateFolder);

		assertEquals(
			randomDisplayPageTemplateFolder, postDisplayPageTemplateFolder);
		assertValid(postDisplayPageTemplateFolder);
	}

	protected DisplayPageTemplateFolder
			testPostSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder_addDisplayPageTemplateFolder(
				DisplayPageTemplateFolder displayPageTemplateFolder)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage()
		throws Exception {

		DisplayPageTemplateFolder postDisplayPageTemplateFolder =
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder();

		Page<Permission> page =
			displayPageTemplateFolderResource.
				getSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage(
					testGroup.getExternalReferenceCode(), RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected DisplayPageTemplateFolder
			testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		DisplayPageTemplateFolder displayPageTemplateFolder =
			testPutSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			displayPageTemplateFolderResource.
				putSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPageHttpResponse(
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
			displayPageTemplateFolderResource.
				putSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPageHttpResponse(
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

	protected DisplayPageTemplateFolder
			testPutSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodeDisplayPageTemplateFolderNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testPatchSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutSiteSiteByExternalReferenceCodeDisplayPageTemplateFolder()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage()
		throws Exception {

		DisplayPageTemplateFolder postDisplayPageTemplateFolder =
			testGetSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder();

		Page<Permission> page =
			displayPageTemplateFolderResource.
				getSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage(
					testGroup.getExternalReferenceCode(),
					postDisplayPageTemplateFolder.getExternalReferenceCode(),
					RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected DisplayPageTemplateFolder
			testGetSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		DisplayPageTemplateFolder displayPageTemplateFolder =
			testPutSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			displayPageTemplateFolderResource.
				putSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPageHttpResponse(
					null, null));

		assertHttpResponseStatusCode(
			404,
			displayPageTemplateFolderResource.
				putSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPageHttpResponse(
					null, null));
	}

	protected DisplayPageTemplateFolder
			testPutSiteSiteExternalReferenceCodeDisplayPageTemplateFolderPermissionsPage_addDisplayPageTemplateFolder()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(
		DisplayPageTemplateFolder displayPageTemplateFolder,
		List<DisplayPageTemplateFolder> displayPageTemplateFolders) {

		boolean contains = false;

		for (DisplayPageTemplateFolder item : displayPageTemplateFolders) {
			if (equals(displayPageTemplateFolder, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			displayPageTemplateFolders + " does not contain " +
				displayPageTemplateFolder,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		DisplayPageTemplateFolder displayPageTemplateFolder1,
		DisplayPageTemplateFolder displayPageTemplateFolder2) {

		Assert.assertTrue(
			displayPageTemplateFolder1 + " does not equal " +
				displayPageTemplateFolder2,
			equals(displayPageTemplateFolder1, displayPageTemplateFolder2));
	}

	protected void assertEquals(
		List<DisplayPageTemplateFolder> displayPageTemplateFolders1,
		List<DisplayPageTemplateFolder> displayPageTemplateFolders2) {

		Assert.assertEquals(
			displayPageTemplateFolders1.size(),
			displayPageTemplateFolders2.size());

		for (int i = 0; i < displayPageTemplateFolders1.size(); i++) {
			DisplayPageTemplateFolder displayPageTemplateFolder1 =
				displayPageTemplateFolders1.get(i);
			DisplayPageTemplateFolder displayPageTemplateFolder2 =
				displayPageTemplateFolders2.get(i);

			assertEquals(
				displayPageTemplateFolder1, displayPageTemplateFolder2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<DisplayPageTemplateFolder> displayPageTemplateFolders1,
		List<DisplayPageTemplateFolder> displayPageTemplateFolders2) {

		Assert.assertEquals(
			displayPageTemplateFolders1.size(),
			displayPageTemplateFolders2.size());

		for (DisplayPageTemplateFolder displayPageTemplateFolder1 :
				displayPageTemplateFolders1) {

			boolean contains = false;

			for (DisplayPageTemplateFolder displayPageTemplateFolder2 :
					displayPageTemplateFolders2) {

				if (equals(
						displayPageTemplateFolder1,
						displayPageTemplateFolder2)) {

					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				displayPageTemplateFolders2 + " does not contain " +
					displayPageTemplateFolder1,
				contains);
		}
	}

	protected void assertValid(
			DisplayPageTemplateFolder displayPageTemplateFolder)
		throws Exception {

		boolean valid = true;

		if (displayPageTemplateFolder.getDateCreated() == null) {
			valid = false;
		}

		if (displayPageTemplateFolder.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (displayPageTemplateFolder.getCreator() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"creatorExternalReferenceCode",
					additionalAssertFieldName)) {

				if (displayPageTemplateFolder.
						getCreatorExternalReferenceCode() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (displayPageTemplateFolder.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (displayPageTemplateFolder.getExternalReferenceCode() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (displayPageTemplateFolder.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (displayPageTemplateFolder.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"parentDisplayPageTemplateFolderExternalReferenceCode",
					additionalAssertFieldName)) {

				if (displayPageTemplateFolder.
						getParentDisplayPageTemplateFolderExternalReferenceCode() ==
							null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (displayPageTemplateFolder.getUuid() == null) {
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

	protected void assertValid(Page<DisplayPageTemplateFolder> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<DisplayPageTemplateFolder> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<DisplayPageTemplateFolder>
			displayPageTemplateFolders = page.getItems();

		int size = displayPageTemplateFolders.size();

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
					com.liferay.headless.admin.site.dto.v1_0.
						DisplayPageTemplateFolder.class)) {

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
		DisplayPageTemplateFolder displayPageTemplateFolder1,
		DisplayPageTemplateFolder displayPageTemplateFolder2) {

		if (displayPageTemplateFolder1 == displayPageTemplateFolder2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getCreator(),
						displayPageTemplateFolder2.getCreator())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"creatorExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						displayPageTemplateFolder1.
							getCreatorExternalReferenceCode(),
						displayPageTemplateFolder2.
							getCreatorExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getDateCreated(),
						displayPageTemplateFolder2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getDateModified(),
						displayPageTemplateFolder2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getDescription(),
						displayPageTemplateFolder2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getExternalReferenceCode(),
						displayPageTemplateFolder2.
							getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getKey(),
						displayPageTemplateFolder2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getName(),
						displayPageTemplateFolder2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"parentDisplayPageTemplateFolderExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						displayPageTemplateFolder1.
							getParentDisplayPageTemplateFolderExternalReferenceCode(),
						displayPageTemplateFolder2.
							getParentDisplayPageTemplateFolderExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						displayPageTemplateFolder1.getUuid(),
						displayPageTemplateFolder2.getUuid())) {

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

		if (!(_displayPageTemplateFolderResource instanceof
				EntityModelResource)) {

			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_displayPageTemplateFolderResource;

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
		DisplayPageTemplateFolder displayPageTemplateFolder) {

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
			Object object =
				displayPageTemplateFolder.getCreatorExternalReferenceCode();

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
				Date date = displayPageTemplateFolder.getDateCreated();

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
					_dateFormat.format(
						displayPageTemplateFolder.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				Date date = displayPageTemplateFolder.getDateModified();

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
					_dateFormat.format(
						displayPageTemplateFolder.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			Object object = displayPageTemplateFolder.getDescription();

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
			Object object =
				displayPageTemplateFolder.getExternalReferenceCode();

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
			Object object = displayPageTemplateFolder.getKey();

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

		if (entityFieldName.equals("name")) {
			Object object = displayPageTemplateFolder.getName();

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

		if (entityFieldName.equals(
				"parentDisplayPageTemplateFolderExternalReferenceCode")) {

			Object object =
				displayPageTemplateFolder.
					getParentDisplayPageTemplateFolderExternalReferenceCode();

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

		if (entityFieldName.equals("uuid")) {
			Object object = displayPageTemplateFolder.getUuid();

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

	protected DisplayPageTemplateFolder randomDisplayPageTemplateFolder()
		throws Exception {

		return new DisplayPageTemplateFolder() {
			{
				creatorExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				parentDisplayPageTemplateFolderExternalReferenceCode =
					StringUtil.toLowerCase(RandomTestUtil.randomString());
				uuid = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected DisplayPageTemplateFolder
			randomIrrelevantDisplayPageTemplateFolder()
		throws Exception {

		DisplayPageTemplateFolder randomIrrelevantDisplayPageTemplateFolder =
			randomDisplayPageTemplateFolder();

		return randomIrrelevantDisplayPageTemplateFolder;
	}

	protected DisplayPageTemplateFolder randomPatchDisplayPageTemplateFolder()
		throws Exception {

		return randomDisplayPageTemplateFolder();
	}

	protected DisplayPageTemplateFolderResource
		displayPageTemplateFolderResource;
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
			BaseDisplayPageTemplateFolderResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.admin.site.resource.v1_0.
		DisplayPageTemplateFolderResource _displayPageTemplateFolderResource;

}