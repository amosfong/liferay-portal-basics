/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.resource.v2_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.data.engine.rest.client.dto.v2_0.DataListView;
import com.liferay.data.engine.rest.client.http.HttpInvoker;
import com.liferay.data.engine.rest.client.pagination.Page;
import com.liferay.data.engine.rest.client.pagination.Pagination;
import com.liferay.data.engine.rest.client.resource.v2_0.DataListViewResource;
import com.liferay.data.engine.rest.client.serdes.v2_0.DataListViewSerDes;
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
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
public abstract class BaseDataListViewResourceTestCase {

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

		_dataListViewResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		DataListViewResource.Builder builder = DataListViewResource.builder();

		dataListViewResource = builder.authentication(
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

		DataListView dataListView1 = randomDataListView();

		String json = objectMapper.writeValueAsString(dataListView1);

		DataListView dataListView2 = DataListViewSerDes.toDTO(json);

		Assert.assertTrue(equals(dataListView1, dataListView2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		DataListView dataListView = randomDataListView();

		String json1 = objectMapper.writeValueAsString(dataListView);
		String json2 = DataListViewSerDes.toJSON(dataListView);

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

		DataListView dataListView = randomDataListView();

		dataListView.setSortField(regex);

		String json = DataListViewSerDes.toJSON(dataListView);

		Assert.assertFalse(json.contains(regex));

		dataListView = DataListViewSerDes.toDTO(json);

		Assert.assertEquals(regex, dataListView.getSortField());
	}

	@Test
	public void testDeleteDataDefinitionDataListView() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		DataListView dataListView =
			testDeleteDataDefinitionDataListView_addDataListView();

		assertHttpResponseStatusCode(
			204,
			dataListViewResource.deleteDataDefinitionDataListViewHttpResponse(
				testDeleteDataDefinitionDataListView_getDataDefinitionId(
					dataListView)));
	}

	protected Long testDeleteDataDefinitionDataListView_getDataDefinitionId(
			DataListView dataListView)
		throws Exception {

		return dataListView.getDataDefinitionId();
	}

	protected DataListView
			testDeleteDataDefinitionDataListView_addDataListView()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetDataDefinitionDataListViewsPage() throws Exception {
		Long dataDefinitionId =
			testGetDataDefinitionDataListViewsPage_getDataDefinitionId();
		Long irrelevantDataDefinitionId =
			testGetDataDefinitionDataListViewsPage_getIrrelevantDataDefinitionId();

		Page<DataListView> page =
			dataListViewResource.getDataDefinitionDataListViewsPage(
				dataDefinitionId, RandomTestUtil.randomString(),
				Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		if (irrelevantDataDefinitionId != null) {
			DataListView irrelevantDataListView =
				testGetDataDefinitionDataListViewsPage_addDataListView(
					irrelevantDataDefinitionId, randomIrrelevantDataListView());

			page = dataListViewResource.getDataDefinitionDataListViewsPage(
				irrelevantDataDefinitionId, null,
				Pagination.of(1, (int)totalCount + 1), null);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDataListView, (List<DataListView>)page.getItems());
			assertValid(
				page,
				testGetDataDefinitionDataListViewsPage_getExpectedActions(
					irrelevantDataDefinitionId));
		}

		DataListView dataListView1 =
			testGetDataDefinitionDataListViewsPage_addDataListView(
				dataDefinitionId, randomDataListView());

		DataListView dataListView2 =
			testGetDataDefinitionDataListViewsPage_addDataListView(
				dataDefinitionId, randomDataListView());

		page = dataListViewResource.getDataDefinitionDataListViewsPage(
			dataDefinitionId, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(dataListView1, (List<DataListView>)page.getItems());
		assertContains(dataListView2, (List<DataListView>)page.getItems());
		assertValid(
			page,
			testGetDataDefinitionDataListViewsPage_getExpectedActions(
				dataDefinitionId));

		dataListViewResource.deleteDataListView(dataListView1.getId());

		dataListViewResource.deleteDataListView(dataListView2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetDataDefinitionDataListViewsPage_getExpectedActions(
				Long dataDefinitionId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		Map createBatchAction = new HashMap<>();
		createBatchAction.put("method", "POST");
		createBatchAction.put(
			"href",
			"http://localhost:8080/o/data-engine/v2.0/data-definitions/{dataDefinitionId}/data-list-views/batch".
				replace(
					"{dataDefinitionId}", String.valueOf(dataDefinitionId)));

		expectedActions.put("createBatch", createBatchAction);

		return expectedActions;
	}

	@Test
	public void testGetDataDefinitionDataListViewsPageWithPagination()
		throws Exception {

		Long dataDefinitionId =
			testGetDataDefinitionDataListViewsPage_getDataDefinitionId();

		Page<DataListView> dataListViewPage =
			dataListViewResource.getDataDefinitionDataListViewsPage(
				dataDefinitionId, null, null, null);

		int totalCount = GetterUtil.getInteger(
			dataListViewPage.getTotalCount());

		DataListView dataListView1 =
			testGetDataDefinitionDataListViewsPage_addDataListView(
				dataDefinitionId, randomDataListView());

		DataListView dataListView2 =
			testGetDataDefinitionDataListViewsPage_addDataListView(
				dataDefinitionId, randomDataListView());

		DataListView dataListView3 =
			testGetDataDefinitionDataListViewsPage_addDataListView(
				dataDefinitionId, randomDataListView());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<DataListView> page1 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(dataListView1, (List<DataListView>)page1.getItems());

			Page<DataListView> page2 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			assertContains(dataListView2, (List<DataListView>)page2.getItems());

			Page<DataListView> page3 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
						pageSizeLimit),
					null);

			assertContains(dataListView3, (List<DataListView>)page3.getItems());
		}
		else {
			Page<DataListView> page1 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null, Pagination.of(1, totalCount + 2),
					null);

			List<DataListView> dataListViews1 =
				(List<DataListView>)page1.getItems();

			Assert.assertEquals(
				dataListViews1.toString(), totalCount + 2,
				dataListViews1.size());

			Page<DataListView> page2 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null, Pagination.of(2, totalCount + 2),
					null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<DataListView> dataListViews2 =
				(List<DataListView>)page2.getItems();

			Assert.assertEquals(
				dataListViews2.toString(), 1, dataListViews2.size());

			Page<DataListView> page3 =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(1, (int)totalCount + 3), null);

			assertContains(dataListView1, (List<DataListView>)page3.getItems());
			assertContains(dataListView2, (List<DataListView>)page3.getItems());
			assertContains(dataListView3, (List<DataListView>)page3.getItems());
		}
	}

	@Test
	public void testGetDataDefinitionDataListViewsPageWithSortDateTime()
		throws Exception {

		testGetDataDefinitionDataListViewsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, dataListView1, dataListView2) -> {
				BeanTestUtil.setProperty(
					dataListView1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetDataDefinitionDataListViewsPageWithSortDouble()
		throws Exception {

		testGetDataDefinitionDataListViewsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, dataListView1, dataListView2) -> {
				BeanTestUtil.setProperty(
					dataListView1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					dataListView2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetDataDefinitionDataListViewsPageWithSortInteger()
		throws Exception {

		testGetDataDefinitionDataListViewsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, dataListView1, dataListView2) -> {
				BeanTestUtil.setProperty(
					dataListView1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					dataListView2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetDataDefinitionDataListViewsPageWithSortString()
		throws Exception {

		testGetDataDefinitionDataListViewsPageWithSort(
			EntityField.Type.STRING,
			(entityField, dataListView1, dataListView2) -> {
				Class<?> clazz = dataListView1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						dataListView1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						dataListView2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						dataListView1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						dataListView2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						dataListView1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						dataListView2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetDataDefinitionDataListViewsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, DataListView, DataListView, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Long dataDefinitionId =
			testGetDataDefinitionDataListViewsPage_getDataDefinitionId();

		DataListView dataListView1 = randomDataListView();
		DataListView dataListView2 = randomDataListView();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, dataListView1, dataListView2);
		}

		dataListView1 = testGetDataDefinitionDataListViewsPage_addDataListView(
			dataDefinitionId, dataListView1);

		dataListView2 = testGetDataDefinitionDataListViewsPage_addDataListView(
			dataDefinitionId, dataListView2);

		Page<DataListView> page =
			dataListViewResource.getDataDefinitionDataListViewsPage(
				dataDefinitionId, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<DataListView> ascPage =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":asc");

			assertContains(
				dataListView1, (List<DataListView>)ascPage.getItems());
			assertContains(
				dataListView2, (List<DataListView>)ascPage.getItems());

			Page<DataListView> descPage =
				dataListViewResource.getDataDefinitionDataListViewsPage(
					dataDefinitionId, null,
					Pagination.of(1, (int)page.getTotalCount() + 1),
					entityField.getName() + ":desc");

			assertContains(
				dataListView2, (List<DataListView>)descPage.getItems());
			assertContains(
				dataListView1, (List<DataListView>)descPage.getItems());
		}
	}

	protected DataListView
			testGetDataDefinitionDataListViewsPage_addDataListView(
				Long dataDefinitionId, DataListView dataListView)
		throws Exception {

		return dataListViewResource.postDataDefinitionDataListView(
			dataDefinitionId, dataListView);
	}

	protected Long testGetDataDefinitionDataListViewsPage_getDataDefinitionId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetDataDefinitionDataListViewsPage_getIrrelevantDataDefinitionId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostDataDefinitionDataListView() throws Exception {
		DataListView randomDataListView = randomDataListView();

		DataListView postDataListView =
			testPostDataDefinitionDataListView_addDataListView(
				randomDataListView);

		assertEquals(randomDataListView, postDataListView);
		assertValid(postDataListView);
	}

	protected DataListView testPostDataDefinitionDataListView_addDataListView(
			DataListView dataListView)
		throws Exception {

		return dataListViewResource.postDataDefinitionDataListView(
			testGetDataDefinitionDataListViewsPage_getDataDefinitionId(),
			dataListView);
	}

	@Test
	public void testDeleteDataListView() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		DataListView dataListView = testDeleteDataListView_addDataListView();

		assertHttpResponseStatusCode(
			204,
			dataListViewResource.deleteDataListViewHttpResponse(
				dataListView.getId()));

		assertHttpResponseStatusCode(
			404,
			dataListViewResource.getDataListViewHttpResponse(
				dataListView.getId()));

		assertHttpResponseStatusCode(
			404, dataListViewResource.getDataListViewHttpResponse(0L));
	}

	protected DataListView testDeleteDataListView_addDataListView()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteDataListView() throws Exception {

		// No namespace

		DataListView dataListView1 =
			testGraphQLDeleteDataListView_addDataListView();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteDataListView",
						new HashMap<String, Object>() {
							{
								put("dataListViewId", dataListView1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteDataListView"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"dataListView",
					new HashMap<String, Object>() {
						{
							put("dataListViewId", dataListView1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace dataEngine_v2_0

		DataListView dataListView2 =
			testGraphQLDeleteDataListView_addDataListView();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"dataEngine_v2_0",
						new GraphQLField(
							"deleteDataListView",
							new HashMap<String, Object>() {
								{
									put(
										"dataListViewId",
										dataListView2.getId());
								}
							}))),
				"JSONObject/data", "JSONObject/dataEngine_v2_0",
				"Object/deleteDataListView"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"dataEngine_v2_0",
					new GraphQLField(
						"dataListView",
						new HashMap<String, Object>() {
							{
								put("dataListViewId", dataListView2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected DataListView testGraphQLDeleteDataListView_addDataListView()
		throws Exception {

		return testGraphQLDataListView_addDataListView();
	}

	@Test
	public void testGetDataListView() throws Exception {
		DataListView postDataListView = testGetDataListView_addDataListView();

		DataListView getDataListView = dataListViewResource.getDataListView(
			postDataListView.getId());

		assertEquals(postDataListView, getDataListView);
		assertValid(getDataListView);
	}

	protected DataListView testGetDataListView_addDataListView()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetDataListView() throws Exception {
		DataListView dataListView =
			testGraphQLGetDataListView_addDataListView();

		// No namespace

		Assert.assertTrue(
			equals(
				dataListView,
				DataListViewSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"dataListView",
								new HashMap<String, Object>() {
									{
										put(
											"dataListViewId",
											dataListView.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/dataListView"))));

		// Using the namespace dataEngine_v2_0

		Assert.assertTrue(
			equals(
				dataListView,
				DataListViewSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"dataEngine_v2_0",
								new GraphQLField(
									"dataListView",
									new HashMap<String, Object>() {
										{
											put(
												"dataListViewId",
												dataListView.getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data", "JSONObject/dataEngine_v2_0",
						"Object/dataListView"))));
	}

	@Test
	public void testGraphQLGetDataListViewNotFound() throws Exception {
		Long irrelevantDataListViewId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"dataListView",
						new HashMap<String, Object>() {
							{
								put("dataListViewId", irrelevantDataListViewId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace dataEngine_v2_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"dataEngine_v2_0",
						new GraphQLField(
							"dataListView",
							new HashMap<String, Object>() {
								{
									put(
										"dataListViewId",
										irrelevantDataListViewId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected DataListView testGraphQLGetDataListView_addDataListView()
		throws Exception {

		return testGraphQLDataListView_addDataListView();
	}

	@Test
	public void testPutDataListView() throws Exception {
		DataListView postDataListView = testPutDataListView_addDataListView();

		DataListView randomDataListView = randomDataListView();

		DataListView putDataListView = dataListViewResource.putDataListView(
			postDataListView.getId(), randomDataListView);

		assertEquals(randomDataListView, putDataListView);
		assertValid(putDataListView);

		DataListView getDataListView = dataListViewResource.getDataListView(
			putDataListView.getId());

		assertEquals(randomDataListView, getDataListView);
		assertValid(getDataListView);
	}

	protected DataListView testPutDataListView_addDataListView()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected DataListView testGraphQLDataListView_addDataListView()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		DataListView dataListView, List<DataListView> dataListViews) {

		boolean contains = false;

		for (DataListView item : dataListViews) {
			if (equals(dataListView, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			dataListViews + " does not contain " + dataListView, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		DataListView dataListView1, DataListView dataListView2) {

		Assert.assertTrue(
			dataListView1 + " does not equal " + dataListView2,
			equals(dataListView1, dataListView2));
	}

	protected void assertEquals(
		List<DataListView> dataListViews1, List<DataListView> dataListViews2) {

		Assert.assertEquals(dataListViews1.size(), dataListViews2.size());

		for (int i = 0; i < dataListViews1.size(); i++) {
			DataListView dataListView1 = dataListViews1.get(i);
			DataListView dataListView2 = dataListViews2.get(i);

			assertEquals(dataListView1, dataListView2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<DataListView> dataListViews1, List<DataListView> dataListViews2) {

		Assert.assertEquals(dataListViews1.size(), dataListViews2.size());

		for (DataListView dataListView1 : dataListViews1) {
			boolean contains = false;

			for (DataListView dataListView2 : dataListViews2) {
				if (equals(dataListView1, dataListView2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				dataListViews2 + " does not contain " + dataListView1,
				contains);
		}
	}

	protected void assertValid(DataListView dataListView) throws Exception {
		boolean valid = true;

		if (dataListView.getDateCreated() == null) {
			valid = false;
		}

		if (dataListView.getDateModified() == null) {
			valid = false;
		}

		if (dataListView.getId() == null) {
			valid = false;
		}

		if (!Objects.equals(dataListView.getSiteId(), testGroup.getGroupId())) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("appliedFilters", additionalAssertFieldName)) {
				if (dataListView.getAppliedFilters() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("dataDefinitionId", additionalAssertFieldName)) {
				if (dataListView.getDataDefinitionId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("fieldNames", additionalAssertFieldName)) {
				if (dataListView.getFieldNames() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (dataListView.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("sortField", additionalAssertFieldName)) {
				if (dataListView.getSortField() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (dataListView.getUserId() == null) {
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

	protected void assertValid(Page<DataListView> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<DataListView> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<DataListView> dataListViews = page.getItems();

		int size = dataListViews.size();

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
					com.liferay.data.engine.rest.dto.v2_0.DataListView.class)) {

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
		DataListView dataListView1, DataListView dataListView2) {

		if (dataListView1 == dataListView2) {
			return true;
		}

		if (!Objects.equals(
				dataListView1.getSiteId(), dataListView2.getSiteId())) {

			return false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("appliedFilters", additionalAssertFieldName)) {
				if (!equals(
						(Map)dataListView1.getAppliedFilters(),
						(Map)dataListView2.getAppliedFilters())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dataDefinitionId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getDataDefinitionId(),
						dataListView2.getDataDefinitionId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getDateCreated(),
						dataListView2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getDateModified(),
						dataListView2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fieldNames", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getFieldNames(),
						dataListView2.getFieldNames())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getId(), dataListView2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!equals(
						(Map)dataListView1.getName(),
						(Map)dataListView2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("sortField", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getSortField(),
						dataListView2.getSortField())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						dataListView1.getUserId(), dataListView2.getUserId())) {

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

		if (!(_dataListViewResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_dataListViewResource;

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
		EntityField entityField, String operator, DataListView dataListView) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("appliedFilters")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dataDefinitionId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				Date date = dataListView.getDateCreated();

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

				sb.append(_dateFormat.format(dataListView.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				Date date = dataListView.getDateModified();

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

				sb.append(_dateFormat.format(dataListView.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("fieldNames")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("siteId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("sortField")) {
			Object object = dataListView.getSortField();

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

		if (entityFieldName.equals("userId")) {
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

	protected DataListView randomDataListView() throws Exception {
		return new DataListView() {
			{
				dataDefinitionId = RandomTestUtil.randomLong();
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				id = RandomTestUtil.randomLong();
				siteId = testGroup.getGroupId();
				sortField = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userId = RandomTestUtil.randomLong();
			}
		};
	}

	protected DataListView randomIrrelevantDataListView() throws Exception {
		DataListView randomIrrelevantDataListView = randomDataListView();

		randomIrrelevantDataListView.setSiteId(irrelevantGroup.getGroupId());

		return randomIrrelevantDataListView;
	}

	protected DataListView randomPatchDataListView() throws Exception {
		return randomDataListView();
	}

	protected DataListViewResource dataListViewResource;
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
		LogFactoryUtil.getLog(BaseDataListViewResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.data.engine.rest.resource.v2_0.DataListViewResource
		_dataListViewResource;

}