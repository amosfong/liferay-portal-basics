/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

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
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.SLA;
import com.liferay.portal.workflow.metrics.rest.client.http.HttpInvoker;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Pagination;
import com.liferay.portal.workflow.metrics.rest.client.resource.v1_0.SLAResource;
import com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0.SLASerDes;

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
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public abstract class BaseSLAResourceTestCase {

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

		_slaResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		SLAResource.Builder builder = SLAResource.builder();

		slaResource = builder.authentication(
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

		SLA sla1 = randomSLA();

		String json = objectMapper.writeValueAsString(sla1);

		SLA sla2 = SLASerDes.toDTO(json);

		Assert.assertTrue(equals(sla1, sla2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		SLA sla = randomSLA();

		String json1 = objectMapper.writeValueAsString(sla);
		String json2 = SLASerDes.toJSON(sla);

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

		SLA sla = randomSLA();

		sla.setCalendarKey(regex);
		sla.setDescription(regex);
		sla.setName(regex);

		String json = SLASerDes.toJSON(sla);

		Assert.assertFalse(json.contains(regex));

		sla = SLASerDes.toDTO(json);

		Assert.assertEquals(regex, sla.getCalendarKey());
		Assert.assertEquals(regex, sla.getDescription());
		Assert.assertEquals(regex, sla.getName());
	}

	@Test
	public void testGetProcessSLAsPage() throws Exception {
		Long processId = testGetProcessSLAsPage_getProcessId();
		Long irrelevantProcessId =
			testGetProcessSLAsPage_getIrrelevantProcessId();

		Page<SLA> page = slaResource.getProcessSLAsPage(
			processId, null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		if (irrelevantProcessId != null) {
			SLA irrelevantSLA = testGetProcessSLAsPage_addSLA(
				irrelevantProcessId, randomIrrelevantSLA());

			page = slaResource.getProcessSLAsPage(
				irrelevantProcessId, null,
				Pagination.of(1, (int)totalCount + 1));

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(irrelevantSLA, (List<SLA>)page.getItems());
			assertValid(
				page,
				testGetProcessSLAsPage_getExpectedActions(irrelevantProcessId));
		}

		SLA sla1 = testGetProcessSLAsPage_addSLA(processId, randomSLA());

		SLA sla2 = testGetProcessSLAsPage_addSLA(processId, randomSLA());

		page = slaResource.getProcessSLAsPage(
			processId, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(sla1, (List<SLA>)page.getItems());
		assertContains(sla2, (List<SLA>)page.getItems());
		assertValid(page, testGetProcessSLAsPage_getExpectedActions(processId));

		slaResource.deleteSLA(sla1.getId());

		slaResource.deleteSLA(sla2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetProcessSLAsPage_getExpectedActions(Long processId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		Map createBatchAction = new HashMap<>();
		createBatchAction.put("method", "POST");
		createBatchAction.put(
			"href",
			"http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/slas/batch".
				replace("{processId}", String.valueOf(processId)));

		expectedActions.put("createBatch", createBatchAction);

		return expectedActions;
	}

	@Test
	public void testGetProcessSLAsPageWithPagination() throws Exception {
		Long processId = testGetProcessSLAsPage_getProcessId();

		Page<SLA> slaPage = slaResource.getProcessSLAsPage(
			processId, null, null);

		int totalCount = GetterUtil.getInteger(slaPage.getTotalCount());

		SLA sla1 = testGetProcessSLAsPage_addSLA(processId, randomSLA());

		SLA sla2 = testGetProcessSLAsPage_addSLA(processId, randomSLA());

		SLA sla3 = testGetProcessSLAsPage_addSLA(processId, randomSLA());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<SLA> page1 = slaResource.getProcessSLAsPage(
				processId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
					pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(sla1, (List<SLA>)page1.getItems());

			Page<SLA> page2 = slaResource.getProcessSLAsPage(
				processId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(sla2, (List<SLA>)page2.getItems());

			Page<SLA> page3 = slaResource.getProcessSLAsPage(
				processId, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(sla3, (List<SLA>)page3.getItems());
		}
		else {
			Page<SLA> page1 = slaResource.getProcessSLAsPage(
				processId, null, Pagination.of(1, totalCount + 2));

			List<SLA> slas1 = (List<SLA>)page1.getItems();

			Assert.assertEquals(slas1.toString(), totalCount + 2, slas1.size());

			Page<SLA> page2 = slaResource.getProcessSLAsPage(
				processId, null, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<SLA> slas2 = (List<SLA>)page2.getItems();

			Assert.assertEquals(slas2.toString(), 1, slas2.size());

			Page<SLA> page3 = slaResource.getProcessSLAsPage(
				processId, null, Pagination.of(1, (int)totalCount + 3));

			assertContains(sla1, (List<SLA>)page3.getItems());
			assertContains(sla2, (List<SLA>)page3.getItems());
			assertContains(sla3, (List<SLA>)page3.getItems());
		}
	}

	protected SLA testGetProcessSLAsPage_addSLA(Long processId, SLA sla)
		throws Exception {

		return slaResource.postProcessSLA(processId, sla);
	}

	protected Long testGetProcessSLAsPage_getProcessId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProcessSLAsPage_getIrrelevantProcessId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProcessSLA() throws Exception {
		SLA randomSLA = randomSLA();

		SLA postSLA = testPostProcessSLA_addSLA(randomSLA);

		assertEquals(randomSLA, postSLA);
		assertValid(postSLA);
	}

	protected SLA testPostProcessSLA_addSLA(SLA sla) throws Exception {
		return slaResource.postProcessSLA(
			testGetProcessSLAsPage_getProcessId(), sla);
	}

	@Test
	public void testDeleteSLA() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		SLA sla = testDeleteSLA_addSLA();

		assertHttpResponseStatusCode(
			204, slaResource.deleteSLAHttpResponse(sla.getId()));

		assertHttpResponseStatusCode(
			404, slaResource.getSLAHttpResponse(sla.getId()));

		assertHttpResponseStatusCode(404, slaResource.getSLAHttpResponse(0L));
	}

	protected SLA testDeleteSLA_addSLA() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteSLA() throws Exception {

		// No namespace

		SLA sla1 = testGraphQLDeleteSLA_addSLA();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteSLA",
						new HashMap<String, Object>() {
							{
								put("slaId", sla1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteSLA"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"sLA",
					new HashMap<String, Object>() {
						{
							put("slaId", sla1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);

		// Using the namespace portalWorkflowMetrics_v1_0

		SLA sla2 = testGraphQLDeleteSLA_addSLA();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"portalWorkflowMetrics_v1_0",
						new GraphQLField(
							"deleteSLA",
							new HashMap<String, Object>() {
								{
									put("slaId", sla2.getId());
								}
							}))),
				"JSONObject/data", "JSONObject/portalWorkflowMetrics_v1_0",
				"Object/deleteSLA"));

		JSONArray errorsJSONArray2 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"portalWorkflowMetrics_v1_0",
					new GraphQLField(
						"sLA",
						new HashMap<String, Object>() {
							{
								put("slaId", sla2.getId());
							}
						},
						new GraphQLField("id")))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray2.length() > 0);
	}

	protected SLA testGraphQLDeleteSLA_addSLA() throws Exception {
		return testGraphQLSLA_addSLA();
	}

	@Test
	public void testGetSLA() throws Exception {
		SLA postSLA = testGetSLA_addSLA();

		SLA getSLA = slaResource.getSLA(postSLA.getId());

		assertEquals(postSLA, getSLA);
		assertValid(getSLA);
	}

	protected SLA testGetSLA_addSLA() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetSLA() throws Exception {
		SLA sla = testGraphQLGetSLA_addSLA();

		// No namespace

		Assert.assertTrue(
			equals(
				sla,
				SLASerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"sLA",
								new HashMap<String, Object>() {
									{
										put("slaId", sla.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/sLA"))));

		// Using the namespace portalWorkflowMetrics_v1_0

		Assert.assertTrue(
			equals(
				sla,
				SLASerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"portalWorkflowMetrics_v1_0",
								new GraphQLField(
									"sLA",
									new HashMap<String, Object>() {
										{
											put("slaId", sla.getId());
										}
									},
									getGraphQLFields()))),
						"JSONObject/data",
						"JSONObject/portalWorkflowMetrics_v1_0",
						"Object/sLA"))));
	}

	@Test
	public void testGraphQLGetSLANotFound() throws Exception {
		Long irrelevantSlaId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"sLA",
						new HashMap<String, Object>() {
							{
								put("slaId", irrelevantSlaId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Using the namespace portalWorkflowMetrics_v1_0

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"portalWorkflowMetrics_v1_0",
						new GraphQLField(
							"sLA",
							new HashMap<String, Object>() {
								{
									put("slaId", irrelevantSlaId);
								}
							},
							getGraphQLFields()))),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected SLA testGraphQLGetSLA_addSLA() throws Exception {
		return testGraphQLSLA_addSLA();
	}

	@Test
	public void testPutSLA() throws Exception {
		SLA postSLA = testPutSLA_addSLA();

		SLA randomSLA = randomSLA();

		SLA putSLA = slaResource.putSLA(postSLA.getId(), randomSLA);

		assertEquals(randomSLA, putSLA);
		assertValid(putSLA);

		SLA getSLA = slaResource.getSLA(putSLA.getId());

		assertEquals(randomSLA, getSLA);
		assertValid(getSLA);
	}

	protected SLA testPutSLA_addSLA() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected SLA testGraphQLSLA_addSLA() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(SLA sla, List<SLA> slas) {
		boolean contains = false;

		for (SLA item : slas) {
			if (equals(sla, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(slas + " does not contain " + sla, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(SLA sla1, SLA sla2) {
		Assert.assertTrue(sla1 + " does not equal " + sla2, equals(sla1, sla2));
	}

	protected void assertEquals(List<SLA> slas1, List<SLA> slas2) {
		Assert.assertEquals(slas1.size(), slas2.size());

		for (int i = 0; i < slas1.size(); i++) {
			SLA sla1 = slas1.get(i);
			SLA sla2 = slas2.get(i);

			assertEquals(sla1, sla2);
		}
	}

	protected void assertEqualsIgnoringOrder(List<SLA> slas1, List<SLA> slas2) {
		Assert.assertEquals(slas1.size(), slas2.size());

		for (SLA sla1 : slas1) {
			boolean contains = false;

			for (SLA sla2 : slas2) {
				if (equals(sla1, sla2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(slas2 + " does not contain " + sla1, contains);
		}
	}

	protected void assertValid(SLA sla) throws Exception {
		boolean valid = true;

		if (sla.getDateModified() == null) {
			valid = false;
		}

		if (sla.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("calendarKey", additionalAssertFieldName)) {
				if (sla.getCalendarKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (sla.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("duration", additionalAssertFieldName)) {
				if (sla.getDuration() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (sla.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("pauseNodeKeys", additionalAssertFieldName)) {
				if (sla.getPauseNodeKeys() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("processId", additionalAssertFieldName)) {
				if (sla.getProcessId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startNodeKeys", additionalAssertFieldName)) {
				if (sla.getStartNodeKeys() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (sla.getStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("stopNodeKeys", additionalAssertFieldName)) {
				if (sla.getStopNodeKeys() == null) {
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

	protected void assertValid(Page<SLA> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<SLA> page, Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<SLA> slas = page.getItems();

		int size = slas.size();

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
					com.liferay.portal.workflow.metrics.rest.dto.v1_0.SLA.
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

	protected boolean equals(SLA sla1, SLA sla2) {
		if (sla1 == sla2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("calendarKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getCalendarKey(), sla2.getCalendarKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getDateModified(), sla2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getDescription(), sla2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("duration", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getDuration(), sla2.getDuration())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(sla1.getId(), sla2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(sla1.getName(), sla2.getName())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("pauseNodeKeys", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getPauseNodeKeys(), sla2.getPauseNodeKeys())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("processId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getProcessId(), sla2.getProcessId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startNodeKeys", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getStartNodeKeys(), sla2.getStartNodeKeys())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(sla1.getStatus(), sla2.getStatus())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("stopNodeKeys", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						sla1.getStopNodeKeys(), sla2.getStopNodeKeys())) {

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

		if (!(_slaResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_slaResource;

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
		EntityField entityField, String operator, SLA sla) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("calendarKey")) {
			Object object = sla.getCalendarKey();

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

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				Date date = sla.getDateModified();

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

				sb.append(_dateFormat.format(sla.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			Object object = sla.getDescription();

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

		if (entityFieldName.equals("duration")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			Object object = sla.getName();

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

		if (entityFieldName.equals("pauseNodeKeys")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("processId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("startNodeKeys")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("status")) {
			sb.append(String.valueOf(sla.getStatus()));

			return sb.toString();
		}

		if (entityFieldName.equals("stopNodeKeys")) {
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

	protected SLA randomSLA() throws Exception {
		return new SLA() {
			{
				calendarKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateModified = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				duration = RandomTestUtil.randomLong();
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				processId = RandomTestUtil.randomLong();
				status = RandomTestUtil.randomInt();
			}
		};
	}

	protected SLA randomIrrelevantSLA() throws Exception {
		SLA randomIrrelevantSLA = randomSLA();

		return randomIrrelevantSLA;
	}

	protected SLA randomPatchSLA() throws Exception {
		return randomSLA();
	}

	protected SLAResource slaResource;
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
		LogFactoryUtil.getLog(BaseSLAResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.portal.workflow.metrics.rest.resource.v1_0.SLAResource
		_slaResource;

}