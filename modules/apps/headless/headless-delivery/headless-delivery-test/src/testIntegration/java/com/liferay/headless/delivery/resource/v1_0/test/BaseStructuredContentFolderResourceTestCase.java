/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalServiceUtil;
import com.liferay.headless.delivery.client.dto.v1_0.Field;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.http.HttpInvoker;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.permission.Permission;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;
import com.liferay.headless.delivery.client.serdes.v1_0.StructuredContentFolderSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
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
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseStructuredContentFolderResourceTestCase {

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

		testDepotEntry = DepotEntryLocalServiceUtil.addDepotEntry(
			Collections.singletonMap(
				LocaleUtil.getDefault(), RandomTestUtil.randomString()),
			null,
			new ServiceContext() {
				{
					setCompanyId(testGroup.getCompanyId());
					setUserId(TestPropsValues.getUserId());
				}
			});

		_structuredContentFolderResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		structuredContentFolderResource = builder.authentication(
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

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();

		String json = objectMapper.writeValueAsString(structuredContentFolder1);

		StructuredContentFolder structuredContentFolder2 =
			StructuredContentFolderSerDes.toDTO(json);

		Assert.assertTrue(
			equals(structuredContentFolder1, structuredContentFolder2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		StructuredContentFolder structuredContentFolder =
			randomStructuredContentFolder();

		String json1 = objectMapper.writeValueAsString(structuredContentFolder);
		String json2 = StructuredContentFolderSerDes.toJSON(
			structuredContentFolder);

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

		StructuredContentFolder structuredContentFolder =
			randomStructuredContentFolder();

		structuredContentFolder.setAssetLibraryKey(regex);
		structuredContentFolder.setDescription(regex);
		structuredContentFolder.setExternalReferenceCode(regex);
		structuredContentFolder.setName(regex);

		String json = StructuredContentFolderSerDes.toJSON(
			structuredContentFolder);

		Assert.assertFalse(json.contains(regex));

		structuredContentFolder = StructuredContentFolderSerDes.toDTO(json);

		Assert.assertEquals(
			regex, structuredContentFolder.getAssetLibraryKey());
		Assert.assertEquals(regex, structuredContentFolder.getDescription());
		Assert.assertEquals(
			regex, structuredContentFolder.getExternalReferenceCode());
		Assert.assertEquals(regex, structuredContentFolder.getName());
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPage()
		throws Exception {

		Long assetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId();
		Long irrelevantAssetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getIrrelevantAssetLibraryId();

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFoldersPage(
					assetLibraryId, null, null, null, null,
					Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantAssetLibraryId != null) {
			StructuredContentFolder irrelevantStructuredContentFolder =
				testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
					irrelevantAssetLibraryId,
					randomIrrelevantStructuredContentFolder());

			page =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						irrelevantAssetLibraryId, null, null, null, null,
						Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantStructuredContentFolder,
				(List<StructuredContentFolder>)page.getItems());
			assertValid(
				page,
				testGetAssetLibraryStructuredContentFoldersPage_getExpectedActions(
					irrelevantAssetLibraryId));
		}

		StructuredContentFolder structuredContentFolder1 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		page =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFoldersPage(
					assetLibraryId, null, null, null, null,
					Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			structuredContentFolder1,
			(List<StructuredContentFolder>)page.getItems());
		assertContains(
			structuredContentFolder2,
			(List<StructuredContentFolder>)page.getItems());
		assertValid(
			page,
			testGetAssetLibraryStructuredContentFoldersPage_getExpectedActions(
				assetLibraryId));

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder1.getId());

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetAssetLibraryStructuredContentFoldersPage_getExpectedActions(
				Long assetLibraryId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		Map createBatchAction = new HashMap<>();
		createBatchAction.put("method", "POST");
		createBatchAction.put(
			"href",
			"http://localhost:8080/o/headless-delivery/v1.0/asset-libraries/{assetLibraryId}/structured-content-folders/batch".
				replace("{assetLibraryId}", String.valueOf(assetLibraryId)));

		expectedActions.put("createBatch", createBatchAction);

		return expectedActions;
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long assetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();

		structuredContentFolder1 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, structuredContentFolder1);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null,
						getFilterString(
							entityField, "between", structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithFilterDoubleEquals()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithFilterStringContains()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithFilterStringEquals()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithFilterStringStartsWith()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void testGetAssetLibraryStructuredContentFoldersPageWithFilter(
			String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long assetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId();

		StructuredContentFolder structuredContentFolder1 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder2 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null,
						getFilterString(
							entityField, operator, structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithPagination()
		throws Exception {

		Long assetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId();

		Page<StructuredContentFolder> structuredContentFolderPage =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFoldersPage(
					assetLibraryId, null, null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			structuredContentFolderPage.getTotalCount());

		StructuredContentFolder structuredContentFolder1 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder3 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, randomStructuredContentFolder());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page1.getItems());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page2.getItems());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
		else {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(1, totalCount + 2), null);

			List<StructuredContentFolder> structuredContentFolders1 =
				(List<StructuredContentFolder>)page1.getItems();

			Assert.assertEquals(
				structuredContentFolders1.toString(), totalCount + 2,
				structuredContentFolders1.size());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<StructuredContentFolder> structuredContentFolders2 =
				(List<StructuredContentFolder>)page2.getItems();

			Assert.assertEquals(
				structuredContentFolders2.toString(), 1,
				structuredContentFolders2.size());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(1, (int)totalCount + 3), null);

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithSortDateTime()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithSortDouble()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithSortInteger()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetAssetLibraryStructuredContentFoldersPageWithSortString()
		throws Exception {

		testGetAssetLibraryStructuredContentFoldersPageWithSort(
			EntityField.Type.STRING,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				Class<?> clazz = structuredContentFolder1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetAssetLibraryStructuredContentFoldersPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, StructuredContentFolder, StructuredContentFolder,
				 Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long assetLibraryId =
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();
		StructuredContentFolder structuredContentFolder2 =
			randomStructuredContentFolder();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, structuredContentFolder1,
				structuredContentFolder2);
		}

		structuredContentFolder1 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, structuredContentFolder1);

		structuredContentFolder2 =
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				assetLibraryId, structuredContentFolder2);

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFoldersPage(
					assetLibraryId, null, null, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> ascPage =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)ascPage.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)ascPage.getItems());

			Page<StructuredContentFolder> descPage =
				structuredContentFolderResource.
					getAssetLibraryStructuredContentFoldersPage(
						assetLibraryId, null, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)descPage.getItems());
			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)descPage.getItems());
		}
	}

	protected StructuredContentFolder
			testGetAssetLibraryStructuredContentFoldersPage_addStructuredContentFolder(
				Long assetLibraryId,
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.
			postAssetLibraryStructuredContentFolder(
				assetLibraryId, structuredContentFolder);
	}

	protected Long
			testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId()
		throws Exception {

		return testDepotEntry.getDepotEntryId();
	}

	protected Long
			testGetAssetLibraryStructuredContentFoldersPage_getIrrelevantAssetLibraryId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAssetLibraryStructuredContentFolder() throws Exception {
		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder postStructuredContentFolder =
			testPostAssetLibraryStructuredContentFolder_addStructuredContentFolder(
				randomStructuredContentFolder);

		assertEquals(
			randomStructuredContentFolder, postStructuredContentFolder);
		assertValid(postStructuredContentFolder);
	}

	protected StructuredContentFolder
			testPostAssetLibraryStructuredContentFolder_addStructuredContentFolder(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.
			postAssetLibraryStructuredContentFolder(
				testGetAssetLibraryStructuredContentFoldersPage_getAssetLibraryId(),
				structuredContentFolder);
	}

	@Test
	public void testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		assertHttpResponseStatusCode(
			204,
			structuredContentFolderResource.
				deleteAssetLibraryStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					structuredContentFolder.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					structuredContentFolder.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					structuredContentFolder.getExternalReferenceCode()));
	}

	protected Long
			testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected StructuredContentFolder
			testDeleteAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetAssetLibraryStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder postStructuredContentFolder =
			testGetAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderByExternalReferenceCode(
					testGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					postStructuredContentFolder.getExternalReferenceCode());

		assertEquals(postStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);
	}

	protected Long
			testGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected StructuredContentFolder
			testGetAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder structuredContentFolder =
			testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		// No namespace

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"assetLibraryStructuredContentFolderByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"assetLibraryId",
											"\"" +
												testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId() +
													"\"");

										put(
											"externalReferenceCode",
											"\"" +
												structuredContentFolder.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/assetLibraryStructuredContentFolderByExternalReferenceCode"))));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessDelivery_v1_0",
								new GraphQLField(
									"assetLibraryStructuredContentFolderByExternalReferenceCode",
									new HashMap<String, Object>() {
										{
											put(
												"assetLibraryId",
												"\"" +
													testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId() +
														"\"");

											put(
												"externalReferenceCode",
												"\"" +
													structuredContentFolder.
														getExternalReferenceCode() +
															"\"");
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/headlessDelivery_v1_0",
						"Object/assetLibraryStructuredContentFolderByExternalReferenceCode"))));
	}

	protected Long
			testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"assetLibraryStructuredContentFolderByExternalReferenceCode",
						new HashMap<String, Object>() {
							{
								put(
									"assetLibraryId",
									"\"" +
										testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId() +
											"\"");
								put(
									"externalReferenceCode",
									irrelevantExternalReferenceCode);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessDelivery_v1_0",
						new GraphQLField(
							"assetLibraryStructuredContentFolderByExternalReferenceCode",
							new HashMap<String, Object>() {
								{
									put(
										"assetLibraryId",
										"\"" +
											testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId() +
												"\"");
									put(
										"externalReferenceCode",
										irrelevantExternalReferenceCode);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected StructuredContentFolder
			testGraphQLGetAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder();
	}

	@Test
	public void testPutAssetLibraryStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder postStructuredContentFolder =
			testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder putStructuredContentFolder =
			structuredContentFolderResource.
				putAssetLibraryStructuredContentFolderByExternalReferenceCode(
					testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					postStructuredContentFolder.getExternalReferenceCode(),
					randomStructuredContentFolder);

		assertEquals(randomStructuredContentFolder, putStructuredContentFolder);
		assertValid(putStructuredContentFolder);

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderByExternalReferenceCode(
					testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					putStructuredContentFolder.getExternalReferenceCode());

		assertEquals(randomStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);

		StructuredContentFolder newStructuredContentFolder =
			testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_createStructuredContentFolder();

		putStructuredContentFolder =
			structuredContentFolderResource.
				putAssetLibraryStructuredContentFolderByExternalReferenceCode(
					testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					newStructuredContentFolder.getExternalReferenceCode(),
					newStructuredContentFolder);

		assertEquals(newStructuredContentFolder, putStructuredContentFolder);
		assertValid(putStructuredContentFolder);

		getStructuredContentFolder =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderByExternalReferenceCode(
					testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId(),
					putStructuredContentFolder.getExternalReferenceCode());

		assertEquals(newStructuredContentFolder, getStructuredContentFolder);

		Assert.assertEquals(
			newStructuredContentFolder.getExternalReferenceCode(),
			putStructuredContentFolder.getExternalReferenceCode());
	}

	protected Long
			testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_getAssetLibraryId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected StructuredContentFolder
			testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_createStructuredContentFolder()
		throws Exception {

		return randomStructuredContentFolder();
	}

	protected StructuredContentFolder
			testPutAssetLibraryStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetAssetLibraryStructuredContentFolderPermissionsPage()
		throws Exception {

		Page<Permission> page =
			structuredContentFolderResource.
				getAssetLibraryStructuredContentFolderPermissionsPage(
					testDepotEntry.getDepotEntryId(), RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected StructuredContentFolder
			testGetAssetLibraryStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return testPostAssetLibraryStructuredContentFolder_addStructuredContentFolder(
			randomStructuredContentFolder());
	}

	@Test
	public void testPutAssetLibraryStructuredContentFolderPermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testPutAssetLibraryStructuredContentFolderPermissionsPage_addStructuredContentFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			structuredContentFolderResource.
				putAssetLibraryStructuredContentFolderPermissionsPageHttpResponse(
					testDepotEntry.getDepotEntryId(),
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
			structuredContentFolderResource.
				putAssetLibraryStructuredContentFolderPermissionsPageHttpResponse(
					testDepotEntry.getDepotEntryId(),
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"-"});
								setRoleName("-");
							}
						}
					}));
	}

	protected StructuredContentFolder
			testPutAssetLibraryStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.
			postAssetLibraryStructuredContentFolder(
				testDepotEntry.getDepotEntryId(),
				randomStructuredContentFolder());
	}

	@Test
	public void testGetSiteStructuredContentFoldersPage() throws Exception {
		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();
		Long irrelevantSiteId =
			testGetSiteStructuredContentFoldersPage_getIrrelevantSiteId();

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.getSiteStructuredContentFoldersPage(
				siteId, null, null, null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantSiteId != null) {
			StructuredContentFolder irrelevantStructuredContentFolder =
				testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
					irrelevantSiteId,
					randomIrrelevantStructuredContentFolder());

			page =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						irrelevantSiteId, null, null, null, null,
						Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantStructuredContentFolder,
				(List<StructuredContentFolder>)page.getItems());
			assertValid(
				page,
				testGetSiteStructuredContentFoldersPage_getExpectedActions(
					irrelevantSiteId));
		}

		StructuredContentFolder structuredContentFolder1 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		page =
			structuredContentFolderResource.getSiteStructuredContentFoldersPage(
				siteId, null, null, null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			structuredContentFolder1,
			(List<StructuredContentFolder>)page.getItems());
		assertContains(
			structuredContentFolder2,
			(List<StructuredContentFolder>)page.getItems());
		assertValid(
			page,
			testGetSiteStructuredContentFoldersPage_getExpectedActions(siteId));

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder1.getId());

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetSiteStructuredContentFoldersPage_getExpectedActions(
				Long siteId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		Map createBatchAction = new HashMap<>();
		createBatchAction.put("method", "POST");
		createBatchAction.put(
			"href",
			"http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/structured-content-folders/batch".
				replace("{siteId}", String.valueOf(siteId)));

		expectedActions.put("createBatch", createBatchAction);

		return expectedActions;
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();

		structuredContentFolder1 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, structuredContentFolder1);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null,
						getFilterString(
							entityField, "between", structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithFilterDoubleEquals()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithFilterStringContains()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithFilterStringEquals()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithFilterStringStartsWith()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void testGetSiteStructuredContentFoldersPageWithFilter(
			String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();

		StructuredContentFolder structuredContentFolder1 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder2 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null,
						getFilterString(
							entityField, operator, structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithPagination()
		throws Exception {

		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();

		Page<StructuredContentFolder> structuredContentFolderPage =
			structuredContentFolderResource.getSiteStructuredContentFoldersPage(
				siteId, null, null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			structuredContentFolderPage.getTotalCount());

		StructuredContentFolder structuredContentFolder1 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder3 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, randomStructuredContentFolder());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page1.getItems());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page2.getItems());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
		else {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(1, totalCount + 2), null);

			List<StructuredContentFolder> structuredContentFolders1 =
				(List<StructuredContentFolder>)page1.getItems();

			Assert.assertEquals(
				structuredContentFolders1.toString(), totalCount + 2,
				structuredContentFolders1.size());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<StructuredContentFolder> structuredContentFolders2 =
				(List<StructuredContentFolder>)page2.getItems();

			Assert.assertEquals(
				structuredContentFolders2.toString(), 1,
				structuredContentFolders2.size());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(1, (int)totalCount + 3), null);

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithSortDateTime()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithSortDouble()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithSortInteger()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetSiteStructuredContentFoldersPageWithSortString()
		throws Exception {

		testGetSiteStructuredContentFoldersPageWithSort(
			EntityField.Type.STRING,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				Class<?> clazz = structuredContentFolder1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetSiteStructuredContentFoldersPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, StructuredContentFolder, StructuredContentFolder,
				 Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();
		StructuredContentFolder structuredContentFolder2 =
			randomStructuredContentFolder();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, structuredContentFolder1,
				structuredContentFolder2);
		}

		structuredContentFolder1 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, structuredContentFolder1);

		structuredContentFolder2 =
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				siteId, structuredContentFolder2);

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.getSiteStructuredContentFoldersPage(
				siteId, null, null, null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> ascPage =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)ascPage.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)ascPage.getItems());

			Page<StructuredContentFolder> descPage =
				structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						siteId, null, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)descPage.getItems());
			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)descPage.getItems());
		}
	}

	protected StructuredContentFolder
			testGetSiteStructuredContentFoldersPage_addStructuredContentFolder(
				Long siteId, StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			siteId, structuredContentFolder);
	}

	protected Long testGetSiteStructuredContentFoldersPage_getSiteId()
		throws Exception {

		return testGroup.getGroupId();
	}

	protected Long testGetSiteStructuredContentFoldersPage_getIrrelevantSiteId()
		throws Exception {

		return irrelevantGroup.getGroupId();
	}

	@Test
	public void testGraphQLGetSiteStructuredContentFoldersPage()
		throws Exception {

		Long siteId = testGetSiteStructuredContentFoldersPage_getSiteId();

		GraphQLField graphQLField = new GraphQLField(
			"structuredContentFolders",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);

					put("siteKey", "\"" + siteId + "\"");
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		// No namespace

		JSONObject structuredContentFoldersJSONObject =
			JSONUtil.getValueAsJSONObject(
				invokeGraphQLQuery(graphQLField), "JSONObject/data",
				"JSONObject/structuredContentFolders");

		long totalCount = structuredContentFoldersJSONObject.getLong(
			"totalCount");

		StructuredContentFolder structuredContentFolder1 =
			testGraphQLGetSiteStructuredContentFoldersPage_addStructuredContentFolder();
		StructuredContentFolder structuredContentFolder2 =
			testGraphQLGetSiteStructuredContentFoldersPage_addStructuredContentFolder();

		structuredContentFoldersJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/structuredContentFolders");

		Assert.assertEquals(
			totalCount + 2,
			structuredContentFoldersJSONObject.getLong("totalCount"));

		assertContains(
			structuredContentFolder1,
			Arrays.asList(
				StructuredContentFolderSerDes.toDTOs(
					structuredContentFoldersJSONObject.getString("items"))));
		assertContains(
			structuredContentFolder2,
			Arrays.asList(
				StructuredContentFolderSerDes.toDTOs(
					structuredContentFoldersJSONObject.getString("items"))));

		// Using the namespace headlessDelivery_v1_0

		structuredContentFoldersJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(
				new GraphQLField("headlessDelivery_v1_0", graphQLField)),
			"JSONObject/data", "JSONObject/headlessDelivery_v1_0",
			"JSONObject/structuredContentFolders");

		Assert.assertEquals(
			totalCount + 2,
			structuredContentFoldersJSONObject.getLong("totalCount"));

		assertContains(
			structuredContentFolder1,
			Arrays.asList(
				StructuredContentFolderSerDes.toDTOs(
					structuredContentFoldersJSONObject.getString("items"))));
		assertContains(
			structuredContentFolder2,
			Arrays.asList(
				StructuredContentFolderSerDes.toDTOs(
					structuredContentFoldersJSONObject.getString("items"))));
	}

	protected StructuredContentFolder
			testGraphQLGetSiteStructuredContentFoldersPage_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder();
	}

	@Test
	public void testPostSiteStructuredContentFolder() throws Exception {
		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder postStructuredContentFolder =
			testPostSiteStructuredContentFolder_addStructuredContentFolder(
				randomStructuredContentFolder);

		assertEquals(
			randomStructuredContentFolder, postStructuredContentFolder);
		assertValid(postStructuredContentFolder);
	}

	protected StructuredContentFolder
			testPostSiteStructuredContentFolder_addStructuredContentFolder(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGetSiteStructuredContentFoldersPage_getSiteId(),
			structuredContentFolder);
	}

	@Test
	public void testGraphQLPostSiteStructuredContentFolder() throws Exception {
		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder structuredContentFolder =
			testGraphQLStructuredContentFolder_addStructuredContentFolder(
				randomStructuredContentFolder);

		Assert.assertTrue(
			equals(randomStructuredContentFolder, structuredContentFolder));
	}

	@Test
	public void testDeleteSiteStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testDeleteSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		assertHttpResponseStatusCode(
			204,
			structuredContentFolderResource.
				deleteSiteStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						structuredContentFolder),
					structuredContentFolder.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getSiteStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						structuredContentFolder),
					structuredContentFolder.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getSiteStructuredContentFolderByExternalReferenceCodeHttpResponse(
					testDeleteSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						structuredContentFolder),
					structuredContentFolder.getExternalReferenceCode()));
	}

	protected Long
			testDeleteSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolder.getSiteId();
	}

	protected StructuredContentFolder
			testDeleteSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetSiteStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder postStructuredContentFolder =
			testGetSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.
				getSiteStructuredContentFolderByExternalReferenceCode(
					testGetSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						postStructuredContentFolder),
					postStructuredContentFolder.getExternalReferenceCode());

		assertEquals(postStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);
	}

	protected Long
			testGetSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolder.getSiteId();
	}

	protected StructuredContentFolder
			testGetSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder structuredContentFolder =
			testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		// No namespace

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"structuredContentFolderByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"siteKey",
											"\"" +
												testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
													structuredContentFolder) +
														"\"");

										put(
											"externalReferenceCode",
											"\"" +
												structuredContentFolder.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/structuredContentFolderByExternalReferenceCode"))));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessDelivery_v1_0",
								new GraphQLField(
									"structuredContentFolderByExternalReferenceCode",
									new HashMap<String, Object>() {
										{
											put(
												"siteKey",
												"\"" +
													testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
														structuredContentFolder) +
															"\"");

											put(
												"externalReferenceCode",
												"\"" +
													structuredContentFolder.
														getExternalReferenceCode() +
															"\"");
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/headlessDelivery_v1_0",
						"Object/structuredContentFolderByExternalReferenceCode"))));
	}

	protected Long
			testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolder.getSiteId();
	}

	@Test
	public void testGraphQLGetSiteStructuredContentFolderByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"structuredContentFolderByExternalReferenceCode",
						new HashMap<String, Object>() {
							{
								put(
									"siteKey",
									"\"" + irrelevantGroup.getGroupId() + "\"");
								put(
									"externalReferenceCode",
									irrelevantExternalReferenceCode);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessDelivery_v1_0",
						new GraphQLField(
							"structuredContentFolderByExternalReferenceCode",
							new HashMap<String, Object>() {
								{
									put(
										"siteKey",
										"\"" + irrelevantGroup.getGroupId() +
											"\"");
									put(
										"externalReferenceCode",
										irrelevantExternalReferenceCode);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected StructuredContentFolder
			testGraphQLGetSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder();
	}

	@Test
	public void testPutSiteStructuredContentFolderByExternalReferenceCode()
		throws Exception {

		StructuredContentFolder postStructuredContentFolder =
			testPutSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder();

		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder putStructuredContentFolder =
			structuredContentFolderResource.
				putSiteStructuredContentFolderByExternalReferenceCode(
					testPutSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						postStructuredContentFolder),
					postStructuredContentFolder.getExternalReferenceCode(),
					randomStructuredContentFolder);

		assertEquals(randomStructuredContentFolder, putStructuredContentFolder);
		assertValid(putStructuredContentFolder);

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.
				getSiteStructuredContentFolderByExternalReferenceCode(
					testPutSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						putStructuredContentFolder),
					putStructuredContentFolder.getExternalReferenceCode());

		assertEquals(randomStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);

		StructuredContentFolder newStructuredContentFolder =
			testPutSiteStructuredContentFolderByExternalReferenceCode_createStructuredContentFolder();

		putStructuredContentFolder =
			structuredContentFolderResource.
				putSiteStructuredContentFolderByExternalReferenceCode(
					testPutSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						newStructuredContentFolder),
					newStructuredContentFolder.getExternalReferenceCode(),
					newStructuredContentFolder);

		assertEquals(newStructuredContentFolder, putStructuredContentFolder);
		assertValid(putStructuredContentFolder);

		getStructuredContentFolder =
			structuredContentFolderResource.
				getSiteStructuredContentFolderByExternalReferenceCode(
					testPutSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
						putStructuredContentFolder),
					putStructuredContentFolder.getExternalReferenceCode());

		assertEquals(newStructuredContentFolder, getStructuredContentFolder);

		Assert.assertEquals(
			newStructuredContentFolder.getExternalReferenceCode(),
			putStructuredContentFolder.getExternalReferenceCode());
	}

	protected Long
			testPutSiteStructuredContentFolderByExternalReferenceCode_getSiteId(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolder.getSiteId();
	}

	protected StructuredContentFolder
			testPutSiteStructuredContentFolderByExternalReferenceCode_createStructuredContentFolder()
		throws Exception {

		return randomStructuredContentFolder();
	}

	protected StructuredContentFolder
			testPutSiteStructuredContentFolderByExternalReferenceCode_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetSiteStructuredContentFolderPermissionsPage()
		throws Exception {

		Page<Permission> page =
			structuredContentFolderResource.
				getSiteStructuredContentFolderPermissionsPage(
					testGroup.getGroupId(), RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected StructuredContentFolder
			testGetSiteStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return testPostSiteStructuredContentFolder_addStructuredContentFolder(
			randomStructuredContentFolder());
	}

	@Test
	public void testPutSiteStructuredContentFolderPermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testPutSiteStructuredContentFolderPermissionsPage_addStructuredContentFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			structuredContentFolderResource.
				putSiteStructuredContentFolderPermissionsPageHttpResponse(
					structuredContentFolder.getSiteId(),
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
			structuredContentFolderResource.
				putSiteStructuredContentFolderPermissionsPageHttpResponse(
					structuredContentFolder.getSiteId(),
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"-"});
								setRoleName("-");
							}
						}
					}));
	}

	protected StructuredContentFolder
			testPutSiteStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetStructuredContentFolderPermissionsPage()
		throws Exception {

		StructuredContentFolder postStructuredContentFolder =
			testGetStructuredContentFolderPermissionsPage_addStructuredContentFolder();

		Page<Permission> page =
			structuredContentFolderResource.
				getStructuredContentFolderPermissionsPage(
					postStructuredContentFolder.getId(), RoleConstants.GUEST);

		Assert.assertNotNull(page);
	}

	protected StructuredContentFolder
			testGetStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return testPostStructuredContentFolderStructuredContentFolder_addStructuredContentFolder(
			randomStructuredContentFolder());
	}

	@Test
	public void testPutStructuredContentFolderPermissionsPage()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testPutStructuredContentFolderPermissionsPage_addStructuredContentFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			structuredContentFolderResource.
				putStructuredContentFolderPermissionsPageHttpResponse(
					structuredContentFolder.getId(),
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"VIEW"});
								setRoleName(role.getName());
							}
						}
					}));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				putStructuredContentFolderPermissionsPageHttpResponse(
					0L,
					new Permission[] {
						new Permission() {
							{
								setActionIds(new String[] {"-"});
								setRoleName("-");
							}
						}
					}));
	}

	protected StructuredContentFolder
			testPutStructuredContentFolderPermissionsPage_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPage()
		throws Exception {

		Long parentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId();
		Long irrelevantParentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getIrrelevantParentStructuredContentFolderId();

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.
				getStructuredContentFolderStructuredContentFoldersPage(
					parentStructuredContentFolderId, null, null, null,
					Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantParentStructuredContentFolderId != null) {
			StructuredContentFolder irrelevantStructuredContentFolder =
				testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
					irrelevantParentStructuredContentFolderId,
					randomIrrelevantStructuredContentFolder());

			page =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						irrelevantParentStructuredContentFolderId, null, null,
						null, Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantStructuredContentFolder,
				(List<StructuredContentFolder>)page.getItems());
			assertValid(
				page,
				testGetStructuredContentFolderStructuredContentFoldersPage_getExpectedActions(
					irrelevantParentStructuredContentFolderId));
		}

		StructuredContentFolder structuredContentFolder1 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		page =
			structuredContentFolderResource.
				getStructuredContentFolderStructuredContentFoldersPage(
					parentStructuredContentFolderId, null, null, null,
					Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			structuredContentFolder1,
			(List<StructuredContentFolder>)page.getItems());
		assertContains(
			structuredContentFolder2,
			(List<StructuredContentFolder>)page.getItems());
		assertValid(
			page,
			testGetStructuredContentFolderStructuredContentFoldersPage_getExpectedActions(
				parentStructuredContentFolderId));

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder1.getId());

		structuredContentFolderResource.deleteStructuredContentFolder(
			structuredContentFolder2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetStructuredContentFolderStructuredContentFoldersPage_getExpectedActions(
				Long parentStructuredContentFolderId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();

		structuredContentFolder1 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId, structuredContentFolder1);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null,
						getFilterString(
							entityField, "between", structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithFilterDoubleEquals()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithFilterStringContains()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithFilter(
			"contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithFilterStringEquals()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithFilter(
			"eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithFilterStringStartsWith()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithFilter(
			"startswith", EntityField.Type.STRING);
	}

	protected void
			testGetStructuredContentFolderStructuredContentFoldersPageWithFilter(
				String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId();

		StructuredContentFolder structuredContentFolder1 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder2 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> page =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null,
						getFilterString(
							entityField, operator, structuredContentFolder1),
						Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(structuredContentFolder1),
				(List<StructuredContentFolder>)page.getItems());
		}
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithPagination()
		throws Exception {

		Long parentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId();

		Page<StructuredContentFolder> structuredContentFolderPage =
			structuredContentFolderResource.
				getStructuredContentFolderStructuredContentFoldersPage(
					parentStructuredContentFolderId, null, null, null, null,
					null);

		int totalCount = GetterUtil.getInteger(
			structuredContentFolderPage.getTotalCount());

		StructuredContentFolder structuredContentFolder1 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder2 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		StructuredContentFolder structuredContentFolder3 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId,
				randomStructuredContentFolder());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page1.getItems());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page2.getItems());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(
							(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
							pageSizeLimit),
						null);

			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
		else {
			Page<StructuredContentFolder> page1 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(1, totalCount + 2), null);

			List<StructuredContentFolder> structuredContentFolders1 =
				(List<StructuredContentFolder>)page1.getItems();

			Assert.assertEquals(
				structuredContentFolders1.toString(), totalCount + 2,
				structuredContentFolders1.size());

			Page<StructuredContentFolder> page2 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<StructuredContentFolder> structuredContentFolders2 =
				(List<StructuredContentFolder>)page2.getItems();

			Assert.assertEquals(
				structuredContentFolders2.toString(), 1,
				structuredContentFolders2.size());

			Page<StructuredContentFolder> page3 =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(1, (int)totalCount + 3), null);

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)page3.getItems());
			assertContains(
				structuredContentFolder3,
				(List<StructuredContentFolder>)page3.getItems());
		}
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithSortDateTime()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithSortDouble()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithSortInteger()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				BeanTestUtil.setProperty(
					structuredContentFolder1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					structuredContentFolder2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetStructuredContentFolderStructuredContentFoldersPageWithSortString()
		throws Exception {

		testGetStructuredContentFolderStructuredContentFoldersPageWithSort(
			EntityField.Type.STRING,
			(entityField, structuredContentFolder1, structuredContentFolder2) ->{
				Class<?> clazz = structuredContentFolder1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						structuredContentFolder1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						structuredContentFolder2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void
			testGetStructuredContentFolderStructuredContentFoldersPageWithSort(
				EntityField.Type type,
				UnsafeTriConsumer
					<EntityField, StructuredContentFolder,
					 StructuredContentFolder, Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentStructuredContentFolderId =
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId();

		StructuredContentFolder structuredContentFolder1 =
			randomStructuredContentFolder();
		StructuredContentFolder structuredContentFolder2 =
			randomStructuredContentFolder();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, structuredContentFolder1,
				structuredContentFolder2);
		}

		structuredContentFolder1 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId, structuredContentFolder1);

		structuredContentFolder2 =
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				parentStructuredContentFolderId, structuredContentFolder2);

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.
				getStructuredContentFolderStructuredContentFoldersPage(
					parentStructuredContentFolderId, null, null, null, null,
					null);

		for (EntityField entityField : entityFields) {
			Page<StructuredContentFolder> ascPage =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":asc");

			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)ascPage.getItems());
			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)ascPage.getItems());

			Page<StructuredContentFolder> descPage =
				structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null, null,
						Pagination.of(1, (int)page.getTotalCount() + 1),
						entityField.getName() + ":desc");

			assertContains(
				structuredContentFolder2,
				(List<StructuredContentFolder>)descPage.getItems());
			assertContains(
				structuredContentFolder1,
				(List<StructuredContentFolder>)descPage.getItems());
		}
	}

	protected StructuredContentFolder
			testGetStructuredContentFolderStructuredContentFoldersPage_addStructuredContentFolder(
				Long parentStructuredContentFolderId,
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.
			postStructuredContentFolderStructuredContentFolder(
				parentStructuredContentFolderId, structuredContentFolder);
	}

	protected Long
			testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetStructuredContentFolderStructuredContentFoldersPage_getIrrelevantParentStructuredContentFolderId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostStructuredContentFolderStructuredContentFolder()
		throws Exception {

		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder postStructuredContentFolder =
			testPostStructuredContentFolderStructuredContentFolder_addStructuredContentFolder(
				randomStructuredContentFolder);

		assertEquals(
			randomStructuredContentFolder, postStructuredContentFolder);
		assertValid(postStructuredContentFolder);
	}

	protected StructuredContentFolder
			testPostStructuredContentFolderStructuredContentFolder_addStructuredContentFolder(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		return structuredContentFolderResource.
			postStructuredContentFolderStructuredContentFolder(
				testGetStructuredContentFolderStructuredContentFoldersPage_getParentStructuredContentFolderId(),
				structuredContentFolder);
	}

	@Test
	public void testDeleteStructuredContentFolder() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testDeleteStructuredContentFolder_addStructuredContentFolder();

		assertHttpResponseStatusCode(
			204,
			structuredContentFolderResource.
				deleteStructuredContentFolderHttpResponse(
					structuredContentFolder.getId()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getStructuredContentFolderHttpResponse(
					structuredContentFolder.getId()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				getStructuredContentFolderHttpResponse(0L));
	}

	protected StructuredContentFolder
			testDeleteStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGraphQLDeleteStructuredContentFolder() throws Exception {

		// No namespace

		StructuredContentFolder structuredContentFolder1 =
			testGraphQLDeleteStructuredContentFolder_addStructuredContentFolder();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteStructuredContentFolder",
						new HashMap<String, Object>() {
							{
								put(
									"structuredContentFolderId",
									structuredContentFolder1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteStructuredContentFolder"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"structuredContentFolder",
					new HashMap<String, Object>() {
						{
							put(
								"structuredContentFolderId",
								structuredContentFolder1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace headlessDelivery_v1_0

		StructuredContentFolder structuredContentFolder2 =
			testGraphQLDeleteStructuredContentFolder_addStructuredContentFolder();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"headlessDelivery_v1_0",
						new GraphQLField(
							"deleteStructuredContentFolder",
							new HashMap<String, Object>() {
								{
									put(
										"structuredContentFolderId",
										structuredContentFolder2.getId());
								}
							}))),
				"JSONObject/data", "JSONObject/headlessDelivery_v1_0",
				"Object/deleteStructuredContentFolder"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"headlessDelivery_v1_0",
					new GraphQLField(
						"structuredContentFolder",
						new HashMap<String, Object>() {
							{
								put(
									"structuredContentFolderId",
									structuredContentFolder2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected StructuredContentFolder
			testGraphQLDeleteStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder();
	}

	@Test
	public void testGetStructuredContentFolder() throws Exception {
		StructuredContentFolder postStructuredContentFolder =
			testGetStructuredContentFolder_addStructuredContentFolder();

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.getStructuredContentFolder(
				postStructuredContentFolder.getId());

		assertEquals(postStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);
	}

	protected StructuredContentFolder
			testGetStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testGraphQLGetStructuredContentFolder() throws Exception {
		StructuredContentFolder structuredContentFolder =
			testGraphQLGetStructuredContentFolder_addStructuredContentFolder();

		// No namespace

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"structuredContentFolder",
								new HashMap<String, Object>() {
									{
										put(
											"structuredContentFolderId",
											structuredContentFolder.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/structuredContentFolder"))));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertTrue(
			equals(
				structuredContentFolder,
				StructuredContentFolderSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"headlessDelivery_v1_0",
								new GraphQLField(
									"structuredContentFolder",
									new HashMap<String, Object>() {
										{
											put(
												"structuredContentFolderId",
												structuredContentFolder.
													getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/headlessDelivery_v1_0",
						"Object/structuredContentFolder"))));
	}

	@Test
	public void testGraphQLGetStructuredContentFolderNotFound()
		throws Exception {

		Long irrelevantStructuredContentFolderId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"structuredContentFolder",
						new HashMap<String, Object>() {
							{
								put(
									"structuredContentFolderId",
									irrelevantStructuredContentFolderId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace headlessDelivery_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"headlessDelivery_v1_0",
						new GraphQLField(
							"structuredContentFolder",
							new HashMap<String, Object>() {
								{
									put(
										"structuredContentFolderId",
										irrelevantStructuredContentFolderId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected StructuredContentFolder
			testGraphQLGetStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder();
	}

	@Test
	public void testPatchStructuredContentFolder() throws Exception {
		StructuredContentFolder postStructuredContentFolder =
			testPatchStructuredContentFolder_addStructuredContentFolder();

		StructuredContentFolder randomPatchStructuredContentFolder =
			randomPatchStructuredContentFolder();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder patchStructuredContentFolder =
			structuredContentFolderResource.patchStructuredContentFolder(
				postStructuredContentFolder.getId(),
				randomPatchStructuredContentFolder);

		StructuredContentFolder expectedPatchStructuredContentFolder =
			postStructuredContentFolder.clone();

		BeanTestUtil.copyProperties(
			randomPatchStructuredContentFolder,
			expectedPatchStructuredContentFolder);

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.getStructuredContentFolder(
				patchStructuredContentFolder.getId());

		assertEquals(
			expectedPatchStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);
	}

	protected StructuredContentFolder
			testPatchStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testPutStructuredContentFolder() throws Exception {
		StructuredContentFolder postStructuredContentFolder =
			testPutStructuredContentFolder_addStructuredContentFolder();

		StructuredContentFolder randomStructuredContentFolder =
			randomStructuredContentFolder();

		StructuredContentFolder putStructuredContentFolder =
			structuredContentFolderResource.putStructuredContentFolder(
				postStructuredContentFolder.getId(),
				randomStructuredContentFolder);

		assertEquals(randomStructuredContentFolder, putStructuredContentFolder);
		assertValid(putStructuredContentFolder);

		StructuredContentFolder getStructuredContentFolder =
			structuredContentFolderResource.getStructuredContentFolder(
				putStructuredContentFolder.getId());

		assertEquals(randomStructuredContentFolder, getStructuredContentFolder);
		assertValid(getStructuredContentFolder);
	}

	protected StructuredContentFolder
			testPutStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testPutStructuredContentFolderSubscribe() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testPutStructuredContentFolderSubscribe_addStructuredContentFolder();

		assertHttpResponseStatusCode(
			204,
			structuredContentFolderResource.
				putStructuredContentFolderSubscribeHttpResponse(
					structuredContentFolder.getId()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				putStructuredContentFolderSubscribeHttpResponse(0L));
	}

	protected StructuredContentFolder
			testPutStructuredContentFolderSubscribe_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Test
	public void testPutStructuredContentFolderUnsubscribe() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		StructuredContentFolder structuredContentFolder =
			testPutStructuredContentFolderUnsubscribe_addStructuredContentFolder();

		assertHttpResponseStatusCode(
			204,
			structuredContentFolderResource.
				putStructuredContentFolderUnsubscribeHttpResponse(
					structuredContentFolder.getId()));

		assertHttpResponseStatusCode(
			404,
			structuredContentFolderResource.
				putStructuredContentFolderUnsubscribeHttpResponse(0L));
	}

	protected StructuredContentFolder
			testPutStructuredContentFolderUnsubscribe_addStructuredContentFolder()
		throws Exception {

		return structuredContentFolderResource.postSiteStructuredContentFolder(
			testGroup.getGroupId(), randomStructuredContentFolder());
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void appendGraphQLFieldValue(StringBuilder sb, Object value)
		throws Exception {

		if (value instanceof Object[]) {
			StringBuilder arraySB = new StringBuilder("[");

			for (Object object : (Object[])value) {
				if (arraySB.length() > 1) {
					arraySB.append(", ");
				}

				arraySB.append("{");

				Class<?> clazz = object.getClass();

				for (java.lang.reflect.Field field :
						getDeclaredFields(clazz.getSuperclass())) {

					arraySB.append(field.getName());
					arraySB.append(": ");

					appendGraphQLFieldValue(arraySB, field.get(object));

					arraySB.append(", ");
				}

				arraySB.setLength(arraySB.length() - 2);

				arraySB.append("}");
			}

			arraySB.append("]");

			sb.append(arraySB.toString());
		}
		else if (value instanceof String) {
			sb.append("\"");
			sb.append(value);
			sb.append("\"");
		}
		else {
			sb.append(value);
		}
	}

	protected StructuredContentFolder
			testGraphQLStructuredContentFolder_addStructuredContentFolder()
		throws Exception {

		return testGraphQLStructuredContentFolder_addStructuredContentFolder(
			randomStructuredContentFolder());
	}

	protected StructuredContentFolder
			testGraphQLStructuredContentFolder_addStructuredContentFolder(
				StructuredContentFolder structuredContentFolder)
		throws Exception {

		JSONDeserializer<StructuredContentFolder> jsonDeserializer =
			JSONFactoryUtil.createJSONDeserializer();

		StringBuilder sb = new StringBuilder("{");

		for (java.lang.reflect.Field field :
				getDeclaredFields(StructuredContentFolder.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append(field.getName());
			sb.append(": ");

			appendGraphQLFieldValue(sb, field.get(structuredContentFolder));
		}

		sb.append("}");

		List<GraphQLField> graphQLFields = getGraphQLFields();

		graphQLFields.add(new GraphQLField("externalReferenceCode"));

		graphQLFields.add(new GraphQLField("id"));

		return jsonDeserializer.deserialize(
			JSONUtil.getValueAsString(
				invokeGraphQLMutation(
					new GraphQLField(
						"createSiteStructuredContentFolder",
						new HashMap<String, Object>() {
							{
								put(
									"siteKey",
									"\"" + testGroup.getGroupId() + "\"");
								put("structuredContentFolder", sb.toString());
							}
						},
						graphQLFields)),
				"JSONObject/data",
				"JSONObject/createSiteStructuredContentFolder"),
			StructuredContentFolder.class);
	}

	protected void assertContains(
		StructuredContentFolder structuredContentFolder,
		List<StructuredContentFolder> structuredContentFolders) {

		boolean contains = false;

		for (StructuredContentFolder item : structuredContentFolders) {
			if (equals(structuredContentFolder, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			structuredContentFolders + " does not contain " +
				structuredContentFolder,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		StructuredContentFolder structuredContentFolder1,
		StructuredContentFolder structuredContentFolder2) {

		Assert.assertTrue(
			structuredContentFolder1 + " does not equal " +
				structuredContentFolder2,
			equals(structuredContentFolder1, structuredContentFolder2));
	}

	protected void assertEquals(
		List<StructuredContentFolder> structuredContentFolders1,
		List<StructuredContentFolder> structuredContentFolders2) {

		Assert.assertEquals(
			structuredContentFolders1.size(), structuredContentFolders2.size());

		for (int i = 0; i < structuredContentFolders1.size(); i++) {
			StructuredContentFolder structuredContentFolder1 =
				structuredContentFolders1.get(i);
			StructuredContentFolder structuredContentFolder2 =
				structuredContentFolders2.get(i);

			assertEquals(structuredContentFolder1, structuredContentFolder2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<StructuredContentFolder> structuredContentFolders1,
		List<StructuredContentFolder> structuredContentFolders2) {

		Assert.assertEquals(
			structuredContentFolders1.size(), structuredContentFolders2.size());

		for (StructuredContentFolder structuredContentFolder1 :
				structuredContentFolders1) {

			boolean contains = false;

			for (StructuredContentFolder structuredContentFolder2 :
					structuredContentFolders2) {

				if (equals(
						structuredContentFolder1, structuredContentFolder2)) {

					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				structuredContentFolders2 + " does not contain " +
					structuredContentFolder1,
				contains);
		}
	}

	protected void assertValid(StructuredContentFolder structuredContentFolder)
		throws Exception {

		boolean valid = true;

		if (structuredContentFolder.getDateCreated() == null) {
			valid = false;
		}

		if (structuredContentFolder.getDateModified() == null) {
			valid = false;
		}

		if (structuredContentFolder.getId() == null) {
			valid = false;
		}

		com.liferay.portal.kernel.model.Group group = testDepotEntry.getGroup();

		if (!Objects.equals(
				structuredContentFolder.getAssetLibraryKey(),
				group.getGroupKey()) &&
			!Objects.equals(
				structuredContentFolder.getSiteId(), testGroup.getGroupId())) {

			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (structuredContentFolder.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("assetLibraryKey", additionalAssertFieldName)) {
				if (structuredContentFolder.getAssetLibraryKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (structuredContentFolder.getCreator() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("customFields", additionalAssertFieldName)) {
				if (structuredContentFolder.getCustomFields() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (structuredContentFolder.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (structuredContentFolder.getExternalReferenceCode() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (structuredContentFolder.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfStructuredContentFolders",
					additionalAssertFieldName)) {

				if (structuredContentFolder.
						getNumberOfStructuredContentFolders() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfStructuredContents", additionalAssertFieldName)) {

				if (structuredContentFolder.getNumberOfStructuredContents() ==
						null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"parentStructuredContentFolderId",
					additionalAssertFieldName)) {

				if (structuredContentFolder.
						getParentStructuredContentFolderId() == null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("subscribed", additionalAssertFieldName)) {
				if (structuredContentFolder.getSubscribed() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("viewableBy", additionalAssertFieldName)) {
				if (structuredContentFolder.getViewableBy() == null) {
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

	protected void assertValid(Page<StructuredContentFolder> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<StructuredContentFolder> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<StructuredContentFolder> structuredContentFolders =
			page.getItems();

		int size = structuredContentFolders.size();

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

		graphQLFields.add(new GraphQLField("siteId"));

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.headless.delivery.dto.v1_0.
						StructuredContentFolder.class)) {

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
		StructuredContentFolder structuredContentFolder1,
		StructuredContentFolder structuredContentFolder2) {

		if (structuredContentFolder1 == structuredContentFolder2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals(
						(Map)structuredContentFolder1.getActions(),
						(Map)structuredContentFolder2.getActions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("creator", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getCreator(),
						structuredContentFolder2.getCreator())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("customFields", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getCustomFields(),
						structuredContentFolder2.getCustomFields())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getDateCreated(),
						structuredContentFolder2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getDateModified(),
						structuredContentFolder2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getDescription(),
						structuredContentFolder2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						structuredContentFolder1.getExternalReferenceCode(),
						structuredContentFolder2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getId(),
						structuredContentFolder2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getName(),
						structuredContentFolder2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfStructuredContentFolders",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						structuredContentFolder1.
							getNumberOfStructuredContentFolders(),
						structuredContentFolder2.
							getNumberOfStructuredContentFolders())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfStructuredContents", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						structuredContentFolder1.
							getNumberOfStructuredContents(),
						structuredContentFolder2.
							getNumberOfStructuredContents())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"parentStructuredContentFolderId",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						structuredContentFolder1.
							getParentStructuredContentFolderId(),
						structuredContentFolder2.
							getParentStructuredContentFolderId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("subscribed", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getSubscribed(),
						structuredContentFolder2.getSubscribed())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("viewableBy", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						structuredContentFolder1.getViewableBy(),
						structuredContentFolder2.getViewableBy())) {

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

		if (!(_structuredContentFolderResource instanceof
				EntityModelResource)) {

			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_structuredContentFolderResource;

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
		StructuredContentFolder structuredContentFolder) {

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

		if (entityFieldName.equals("assetLibraryKey")) {
			Object object = structuredContentFolder.getAssetLibraryKey();

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

		if (entityFieldName.equals("creator")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("customFields")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				Date date = structuredContentFolder.getDateCreated();

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
						structuredContentFolder.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				Date date = structuredContentFolder.getDateModified();

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
						structuredContentFolder.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			Object object = structuredContentFolder.getDescription();

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
			Object object = structuredContentFolder.getExternalReferenceCode();

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
			Object object = structuredContentFolder.getName();

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

		if (entityFieldName.equals("numberOfStructuredContentFolders")) {
			sb.append(
				String.valueOf(
					structuredContentFolder.
						getNumberOfStructuredContentFolders()));

			return sb.toString();
		}

		if (entityFieldName.equals("numberOfStructuredContents")) {
			sb.append(
				String.valueOf(
					structuredContentFolder.getNumberOfStructuredContents()));

			return sb.toString();
		}

		if (entityFieldName.equals("parentStructuredContentFolderId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("siteId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("subscribed")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("viewableBy")) {
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

	protected StructuredContentFolder randomStructuredContentFolder()
		throws Exception {

		return new StructuredContentFolder() {
			{
				assetLibraryKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				numberOfStructuredContentFolders = RandomTestUtil.randomInt();
				numberOfStructuredContents = RandomTestUtil.randomInt();
				parentStructuredContentFolderId = RandomTestUtil.randomLong();
				siteId = testGroup.getGroupId();
				subscribed = RandomTestUtil.randomBoolean();
			}
		};
	}

	protected StructuredContentFolder randomIrrelevantStructuredContentFolder()
		throws Exception {

		StructuredContentFolder randomIrrelevantStructuredContentFolder =
			randomStructuredContentFolder();

		randomIrrelevantStructuredContentFolder.setSiteId(
			irrelevantGroup.getGroupId());

		return randomIrrelevantStructuredContentFolder;
	}

	protected StructuredContentFolder randomPatchStructuredContentFolder()
		throws Exception {

		return randomStructuredContentFolder();
	}

	protected StructuredContentFolderResource structuredContentFolderResource;
	protected com.liferay.portal.kernel.model.Group irrelevantGroup;
	protected com.liferay.portal.kernel.model.Company testCompany;
	protected DepotEntry testDepotEntry;
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
			BaseStructuredContentFolderResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.headless.delivery.resource.v1_0.
			StructuredContentFolderResource _structuredContentFolderResource;

}