/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.rest.resource.v1_0.test;

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
import com.liferay.portal.search.rest.client.dto.v1_0.EmbeddingModel;
import com.liferay.portal.search.rest.client.http.HttpInvoker;
import com.liferay.portal.search.rest.client.pagination.Page;
import com.liferay.portal.search.rest.client.pagination.Pagination;
import com.liferay.portal.search.rest.client.resource.v1_0.EmbeddingModelResource;
import com.liferay.portal.search.rest.client.serdes.v1_0.EmbeddingModelSerDes;
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
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public abstract class BaseEmbeddingModelResourceTestCase {

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

		_embeddingModelResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		EmbeddingModelResource.Builder builder =
			EmbeddingModelResource.builder();

		embeddingModelResource = builder.authentication(
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

		EmbeddingModel embeddingModel1 = randomEmbeddingModel();

		String json = objectMapper.writeValueAsString(embeddingModel1);

		EmbeddingModel embeddingModel2 = EmbeddingModelSerDes.toDTO(json);

		Assert.assertTrue(equals(embeddingModel1, embeddingModel2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		EmbeddingModel embeddingModel = randomEmbeddingModel();

		String json1 = objectMapper.writeValueAsString(embeddingModel);
		String json2 = EmbeddingModelSerDes.toJSON(embeddingModel);

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

		EmbeddingModel embeddingModel = randomEmbeddingModel();

		embeddingModel.setModelId(regex);

		String json = EmbeddingModelSerDes.toJSON(embeddingModel);

		Assert.assertFalse(json.contains(regex));

		embeddingModel = EmbeddingModelSerDes.toDTO(json);

		Assert.assertEquals(regex, embeddingModel.getModelId());
	}

	@Test
	public void testGetEmbeddingEmbeddingModelsPage() throws Exception {
		Page<EmbeddingModel> page =
			embeddingModelResource.getEmbeddingEmbeddingModelsPage(
				RandomTestUtil.randomString(), null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		EmbeddingModel embeddingModel1 =
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				randomEmbeddingModel());

		EmbeddingModel embeddingModel2 =
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				randomEmbeddingModel());

		page = embeddingModelResource.getEmbeddingEmbeddingModelsPage(
			null, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(embeddingModel1, (List<EmbeddingModel>)page.getItems());
		assertContains(embeddingModel2, (List<EmbeddingModel>)page.getItems());
		assertValid(
			page, testGetEmbeddingEmbeddingModelsPage_getExpectedActions());
	}

	protected Map<String, Map<String, String>>
			testGetEmbeddingEmbeddingModelsPage_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetEmbeddingEmbeddingModelsPageWithPagination()
		throws Exception {

		Page<EmbeddingModel> embeddingModelPage =
			embeddingModelResource.getEmbeddingEmbeddingModelsPage(
				null, null, null);

		int totalCount = GetterUtil.getInteger(
			embeddingModelPage.getTotalCount());

		EmbeddingModel embeddingModel1 =
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				randomEmbeddingModel());

		EmbeddingModel embeddingModel2 =
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				randomEmbeddingModel());

		EmbeddingModel embeddingModel3 =
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				randomEmbeddingModel());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<EmbeddingModel> page1 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
						pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				embeddingModel1, (List<EmbeddingModel>)page1.getItems());

			Page<EmbeddingModel> page2 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(
				embeddingModel2, (List<EmbeddingModel>)page2.getItems());

			Page<EmbeddingModel> page3 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(
				embeddingModel3, (List<EmbeddingModel>)page3.getItems());
		}
		else {
			Page<EmbeddingModel> page1 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null, Pagination.of(1, totalCount + 2));

			List<EmbeddingModel> embeddingModels1 =
				(List<EmbeddingModel>)page1.getItems();

			Assert.assertEquals(
				embeddingModels1.toString(), totalCount + 2,
				embeddingModels1.size());

			Page<EmbeddingModel> page2 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<EmbeddingModel> embeddingModels2 =
				(List<EmbeddingModel>)page2.getItems();

			Assert.assertEquals(
				embeddingModels2.toString(), 1, embeddingModels2.size());

			Page<EmbeddingModel> page3 =
				embeddingModelResource.getEmbeddingEmbeddingModelsPage(
					null, null, Pagination.of(1, (int)totalCount + 3));

			assertContains(
				embeddingModel1, (List<EmbeddingModel>)page3.getItems());
			assertContains(
				embeddingModel2, (List<EmbeddingModel>)page3.getItems());
			assertContains(
				embeddingModel3, (List<EmbeddingModel>)page3.getItems());
		}
	}

	protected EmbeddingModel
			testGetEmbeddingEmbeddingModelsPage_addEmbeddingModel(
				EmbeddingModel embeddingModel)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		EmbeddingModel embeddingModel, List<EmbeddingModel> embeddingModels) {

		boolean contains = false;

		for (EmbeddingModel item : embeddingModels) {
			if (equals(embeddingModel, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			embeddingModels + " does not contain " + embeddingModel, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		EmbeddingModel embeddingModel1, EmbeddingModel embeddingModel2) {

		Assert.assertTrue(
			embeddingModel1 + " does not equal " + embeddingModel2,
			equals(embeddingModel1, embeddingModel2));
	}

	protected void assertEquals(
		List<EmbeddingModel> embeddingModels1,
		List<EmbeddingModel> embeddingModels2) {

		Assert.assertEquals(embeddingModels1.size(), embeddingModels2.size());

		for (int i = 0; i < embeddingModels1.size(); i++) {
			EmbeddingModel embeddingModel1 = embeddingModels1.get(i);
			EmbeddingModel embeddingModel2 = embeddingModels2.get(i);

			assertEquals(embeddingModel1, embeddingModel2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<EmbeddingModel> embeddingModels1,
		List<EmbeddingModel> embeddingModels2) {

		Assert.assertEquals(embeddingModels1.size(), embeddingModels2.size());

		for (EmbeddingModel embeddingModel1 : embeddingModels1) {
			boolean contains = false;

			for (EmbeddingModel embeddingModel2 : embeddingModels2) {
				if (equals(embeddingModel1, embeddingModel2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				embeddingModels2 + " does not contain " + embeddingModel1,
				contains);
		}
	}

	protected void assertValid(EmbeddingModel embeddingModel) throws Exception {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("modelId", additionalAssertFieldName)) {
				if (embeddingModel.getModelId() == null) {
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

	protected void assertValid(Page<EmbeddingModel> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<EmbeddingModel> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<EmbeddingModel> embeddingModels = page.getItems();

		int size = embeddingModels.size();

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
					com.liferay.portal.search.rest.dto.v1_0.EmbeddingModel.
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
		EmbeddingModel embeddingModel1, EmbeddingModel embeddingModel2) {

		if (embeddingModel1 == embeddingModel2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("modelId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						embeddingModel1.getModelId(),
						embeddingModel2.getModelId())) {

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

		if (!(_embeddingModelResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_embeddingModelResource;

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
		EmbeddingModel embeddingModel) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("modelId")) {
			Object object = embeddingModel.getModelId();

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

	protected EmbeddingModel randomEmbeddingModel() throws Exception {
		return new EmbeddingModel() {
			{
				modelId = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected EmbeddingModel randomIrrelevantEmbeddingModel() throws Exception {
		EmbeddingModel randomIrrelevantEmbeddingModel = randomEmbeddingModel();

		return randomIrrelevantEmbeddingModel;
	}

	protected EmbeddingModel randomPatchEmbeddingModel() throws Exception {
		return randomEmbeddingModel();
	}

	protected EmbeddingModelResource embeddingModelResource;
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
		LogFactoryUtil.getLog(BaseEmbeddingModelResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.portal.search.rest.resource.v1_0.EmbeddingModelResource
		_embeddingModelResource;

}