/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.graphql.servlet.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.test.util.HTTPTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;
import com.liferay.portal.vulcan.internal.test.util.PaginationConfigurationTestUtil;

import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * @author Luis Miguel Barcos
 */
@RunWith(Arquillian.class)
public class GraphQLServletTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		Bundle bundle = FrameworkUtil.getBundle(GraphQLServletTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_testDTO = new TestDTO();

		TestServletData testServletData = new TestServletData(_testDTO);

		_serviceRegistration = bundleContext.registerService(
			ServletData.class, testServletData, null);
	}

	@After
	public void tearDown() {
		_serviceRegistration.unregister();
	}

	@Test
	public void testMutation() throws Exception {
		TestDTO testDTO = new TestDTO();

		_assertEquals(
			false, testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"createTestDTO",
						Collections.singletonMap(
							"testDTO", _toGraphQLString(testDTO)),
						new GraphQLField("id"), new GraphQLField("map"),
						new GraphQLField("string")),
					"mutation"),
				"JSONObject/data", "JSONObject/createTestDTO"));
	}

	@Test
	public void testMutationWithGraphQLNamespace() throws Exception {

		// With namespace

		TestDTO testDTO = new TestDTO();

		_assertEquals(
			false, testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"testPath_v1_0",
						new GraphQLField(
							"createTestDTO",
							Collections.singletonMap(
								"testDTO", _toGraphQLString(testDTO)),
							new GraphQLField("id"), new GraphQLField("map"),
							new GraphQLField("string"))),
					"mutation"),
				"JSONObject/data", "JSONObject/testPath_v1_0",
				"JSONObject/createTestDTO"));

		// Without namespace (backwards compatibility)

		testDTO = new TestDTO();

		_assertEquals(
			false, testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"createTestDTO",
						Collections.singletonMap(
							"testDTO", _toGraphQLString(testDTO)),
						new GraphQLField("id"), new GraphQLField("map"),
						new GraphQLField("string")),
					"mutation"),
				"JSONObject/data", "JSONObject/createTestDTO"));
	}

	@Test
	public void testQuery() throws Exception {
		_assertEquals(
			true, _testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"testDTO", new GraphQLField("extendedString"),
						new GraphQLField("id"), new GraphQLField("map"),
						new GraphQLField("string")),
					"query"),
				"JSONObject/data", "JSONObject/testDTO"));
	}

	@Test
	public void testQueryDepthLimit() throws Exception {
		Configuration[] configurations = _configurationAdmin.listConfigurations(
			StringBundler.concat(
				"(&(companyId=", TestPropsValues.getCompanyId(),
				")(service.factoryPid=com.liferay.portal.vulcan.internal.",
				"configuration.HeadlessAPICompanyConfiguration.scoped))"));

		Configuration factoryConfiguration =
			_configurationAdmin.createFactoryConfiguration(
				"com.liferay.portal.vulcan.internal.configuration." +
					"HeadlessAPICompanyConfiguration.scoped",
				StringPool.QUESTION);

		try {
			factoryConfiguration.update(
				HashMapDictionaryBuilder.<String, Object>put(
					"companyId", TestPropsValues.getCompanyId()
				).put(
					"queryDepthLimit", 1
				).build());

			JSONObject jsonObject = _invoke(
				new GraphQLField(
					"testDTO", new GraphQLField("extendedString"),
					new GraphQLField("id"), new GraphQLField("string")),
				"query");

			Assert.assertNull(
				JSONUtil.getValueAsJSONObject(jsonObject, "JSONObject/data"));
			JSONAssert.assertEquals(
				JSONUtil.put(
					"extensions",
					JSONUtil.put(
						"code", "Bad Request"
					).put(
						"exception", JSONUtil.put("errno", 400)
					)
				).put(
					"message",
					"Depth 2 is greater than the query depth limit of 1"
				).toString(),
				JSONUtil.getValueAsString(
					jsonObject, "JSONArray/errors", "Object/0"),
				JSONCompareMode.LENIENT);

			factoryConfiguration.update(
				HashMapDictionaryBuilder.<String, Object>put(
					"companyId", TestPropsValues.getCompanyId()
				).put(
					"queryDepthLimit", 2
				).build());

			_assertEquals(
				true, _testDTO,
				JSONUtil.getValueAsJSONObject(
					_invoke(
						new GraphQLField(
							"testDTO", new GraphQLField("extendedString"),
							new GraphQLField("id"), new GraphQLField("map"),
							new GraphQLField("string")),
						"query"),
					"JSONObject/data", "JSONObject/testDTO"));
		}
		finally {
			if (configurations == null) {
				factoryConfiguration.delete();
			}
			else {
				Configuration configuration = configurations[0];

				factoryConfiguration.update(configuration.getProperties());
			}
		}
	}

	@Test
	public void testQueryPagination() throws Exception {

		// Default limited page size and limited page size requested

		_test(1, 20, null, null);
		_test(1, 5, null, 5);
		_test(1, 30, null, 30);
		_test(1, 20, null, null);
		_test(1, 15, null, 15);
		_test(1, 30, null, 30);
		_test(1, 40, null, 40);
		_test(2, 20, 2, null);
		_test(3, 20, 3, null);

		// Default limited page size and unlimited page size requested

		_test(1, 500, null, -1);
		_test(1, 500, null, 0);
		_test(1, 500, -1, null);
		_test(1, 500, 0, null);

		// Limited page size configured and limited page size requested

		PaginationConfigurationTestUtil.withPageSizeLimit(
			10, () -> _test(1, 10, null, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			10, () -> _test(1, 5, null, 5));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			10, () -> _test(1, 10, null, 30));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			30, () -> _test(1, 20, null, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			30, () -> _test(1, 15, null, 15));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			30, () -> _test(1, 30, null, 30));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			30, () -> _test(1, 30, null, 40));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(2, 20, 2, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(3, 20, 3, null));

		// Limited page size configured and unlimited page size requested

		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(1, 50, null, -1));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(1, 50, null, 0));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(1, 50, -1, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			50, () -> _test(1, 50, 0, null));

		// Unlimited page size configured and limited page size requested

		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(1, 20, null, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(1, 25, null, 25));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(2, 20, 2, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(2, 25, 2, 25));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(1, 20, null, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(1, 25, null, 25));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(2, 20, 2, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(2, 25, 2, 25));

		// Unlimited page size configured and unlimited page size requested

		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(-1, -1, -1, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(-1, -1, 0, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(-1, -1, null, -1));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			-1, () -> _test(-1, -1, null, 0));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(-1, -1, -1, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(-1, -1, 0, null));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(-1, -1, null, -1));
		PaginationConfigurationTestUtil.withPageSizeLimit(
			0, () -> _test(-1, -1, null, 0));
	}

	@Test
	public void testQueryWithGraphQLNamespace() throws Exception {

		// With namespace

		_assertEquals(
			true, _testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"testPath_v1_0",
						new GraphQLField(
							"testDTO", new GraphQLField("extendedString"),
							new GraphQLField("id"), new GraphQLField("map"),
							new GraphQLField("string"))),
					"query"),
				"JSONObject/data", "JSONObject/testPath_v1_0",
				"JSONObject/testDTO"));

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField(
						"testPath_v1_0",
						new GraphQLField(
							"testNoPermissionOverDTO", new GraphQLField("id"))),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField(
						"testPath_v1_0",
						new GraphQLField(
							"testNotFoundDTO", new GraphQLField("id"))),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		Assert.assertEquals(
			"Forbidden",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField(
						"testPath_v1_0",
						new GraphQLField(
							"testUnauthorizedUser", new GraphQLField("id"))),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		// Without namespace (backwards compatibility)

		_assertEquals(
			true, _testDTO,
			JSONUtil.getValueAsJSONObject(
				_invoke(
					new GraphQLField(
						"testDTO", new GraphQLField("extendedString"),
						new GraphQLField("id"), new GraphQLField("map"),
						new GraphQLField("string")),
					"query"),
				"JSONObject/data", "JSONObject/testDTO"));

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField(
						"testNoPermissionOverDTO", new GraphQLField("id")),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField("testNotFoundDTO", new GraphQLField("id")),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));

		Assert.assertEquals(
			"Forbidden",
			JSONUtil.getValueAsString(
				_invoke(
					new GraphQLField(
						"testUnauthorizedUser", new GraphQLField("id")),
					"query"),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testSchema() throws Exception {

		// Mutation fields

		JSONArray mutationFieldsJSONArray = JSONUtil.getValueAsJSONArray(
			_invoke(
				new GraphQLField(
					"__schema",
					new GraphQLField(
						"mutationType",
						new GraphQLField(
							"fields(includeDeprecated: true)",
							new GraphQLField("deprecationReason"),
							new GraphQLField("isDeprecated"),
							new GraphQLField("name"),
							new GraphQLField(
								"type",
								new GraphQLField(
									"fields",
									new GraphQLField("deprecationReason"),
									new GraphQLField("isDeprecated"),
									new GraphQLField("name")))))),
				"query"),
			"JSONObject/data", "JSONObject/__schema", "JSONObject/mutationType",
			"JSONArray/fields");

		_assertGraphQLSchemaField(
			true, mutationFieldsJSONArray, true, "createTestDTO");
		_assertGraphQLSchemaField(
			false, mutationFieldsJSONArray, true, "testPath_v1_0");
		_assertGraphQLSchemaField(
			false,
			JSONUtil.getValueAsJSONArray(
				_getJSONObject(mutationFieldsJSONArray, "testPath_v1_0"),
				"JSONObject/type", "JSONArray/fields"),
			true, "createTestDTO");

		// Query fields

		JSONArray queryFieldsJSONArray = JSONUtil.getValueAsJSONArray(
			_invoke(
				new GraphQLField(
					"__schema",
					new GraphQLField(
						"queryType",
						new GraphQLField(
							"fields(includeDeprecated: true)",
							new GraphQLField("deprecationReason"),
							new GraphQLField("isDeprecated"),
							new GraphQLField("name"),
							new GraphQLField(
								"type",
								new GraphQLField(
									"fields",
									new GraphQLField("deprecationReason"),
									new GraphQLField("isDeprecated"),
									new GraphQLField("name")))))),
				"query"),
			"JSONObject/data", "JSONObject/__schema", "JSONObject/queryType",
			"JSONArray/fields");

		_assertGraphQLSchemaField(true, queryFieldsJSONArray, false, "testDTO");
		_assertGraphQLSchemaField(
			true, queryFieldsJSONArray, false, "testDTOPage");

		JSONArray namespacedQueryFieldsJSONArray = JSONUtil.getValueAsJSONArray(
			_getJSONObject(queryFieldsJSONArray, "testPath_v1_0"),
			"JSONObject/type", "JSONArray/fields");

		_assertGraphQLSchemaField(
			false, namespacedQueryFieldsJSONArray, false, "testDTO");
		_assertGraphQLSchemaField(
			false, namespacedQueryFieldsJSONArray, false, "testDTOPage");
	}

	public static class TestDTO {

		public TestDTO() {
			this(
				RandomTestUtil.randomString(), RandomTestUtil.randomLong(),
				HashMapBuilder.put(
					"a" + RandomTestUtil.randomString(),
					RandomTestUtil.randomString()
				).put(
					"a" + RandomTestUtil.randomString(),
					RandomTestUtil.randomString()
				).build(),
				RandomTestUtil.randomString());
		}

		public TestDTO(
			String extendedString, long id, Map<String, String> map,
			String string) {

			_extendedString = extendedString;

			this.id = id;
			this.map = map;
			this.string = string;
		}

		public String getExtendedString() {
			return _extendedString;
		}

		public long getId() {
			return id;
		}

		public Map<String, String> getMap() {
			return map;
		}

		public String getString() {
			return string;
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		protected long id;

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		protected Map<String, String> map;

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		protected String string;

		private String _extendedString;

	}

	public static class TestDTOPage {

		public TestDTOPage(int page, int pageSize) {
			this.page = page;
			this.pageSize = pageSize;
		}

		public int getPage() {
			return page;
		}

		public int getPageSize() {
			return pageSize;
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		protected int page;

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		protected int pageSize;

	}

	public static class TestMutation {

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTO createTestDTO(@GraphQLName("testDTO") TestDTO testDTO) {
			return testDTO;
		}

	}

	public static class TestQuery {

		public TestQuery() {
		}

		public TestQuery(TestDTO testDTO) {
			_testDTO = testDTO;
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTO testDTO() {
			return _testDTO;
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTOPage testDTOPage(
			@GraphQLName("page") int page,
			@GraphQLName("pageSize") int pageSize) {

			return new TestDTOPage(page, pageSize);
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTO testNoPermissionOverDTO()
			throws PrincipalException.MustHavePermission {

			throw new PrincipalException.MustHavePermission(
				0L, StringUtil.randomString());
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTO testNotFoundDTO() {
			throw new NotFoundException();
		}

		@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
		public TestDTO testUnauthorizedUser() throws SecurityException {
			throw new SecurityException();
		}

		@GraphQLTypeExtension(TestDTO.class)
		public class TestGraphQLTypeExtension {

			public TestGraphQLTypeExtension(TestDTO testDTO) {
				_testDTO = testDTO;
			}

			@com.liferay.portal.vulcan.graphql.annotation.GraphQLField
			public String extendedString() {
				return _testDTO.getExtendedString();
			}

			private final TestDTO _testDTO;

		}

		private static TestDTO _testDTO;
		private static TestDTOPage _testDTOPage;

	}

	public class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
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

	public class TestServletData implements ServletData {

		public TestServletData(TestDTO testDTO) {
			_testQuery = new TestQuery(testDTO);
		}

		@Override
		public String getApplicationName() {
			return "test";
		}

		@Override
		public Object getMutation() {
			return _testMutation;
		}

		@Override
		public String getPath() {
			return "/test-path-graphql/v1_0";
		}

		@Override
		public TestQuery getQuery() {
			return _testQuery;
		}

		@Override
		public boolean isJaxRsResourceInvocation() {
			return false;
		}

		private final TestMutation _testMutation = new TestMutation();
		private final TestQuery _testQuery;

	}

	private void _appendGraphQLFieldValue(StringBuilder sb, Object value) {
		if (value instanceof Map) {
			Map<String, Object> map = (Map)value;

			sb.append("{");

			StringBuilder stringBuilder = new StringBuilder();

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (stringBuilder.length() > 1) {
					stringBuilder.append(", ");
				}

				stringBuilder.append(entry.getKey());
				stringBuilder.append(": ");

				_appendGraphQLFieldValue(stringBuilder, entry.getValue());
			}

			sb.append(stringBuilder.toString());
			sb.append("}");
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

	private void _assertEquals(
		boolean assertExtendedProperties, TestDTO expectedTestDTO,
		JSONObject jsonObject) {

		if (assertExtendedProperties) {
			Assert.assertEquals(
				expectedTestDTO.getExtendedString(),
				jsonObject.get("extendedString"));
		}

		Assert.assertEquals(expectedTestDTO.getId(), jsonObject.get("id"));
		Assert.assertEquals(
			expectedTestDTO.getMap(),
			JSONUtil.toStringMap(jsonObject.getJSONObject("map")));
		Assert.assertEquals(
			expectedTestDTO.getString(), jsonObject.get("string"));
	}

	private void _assertGraphQLSchemaField(
			boolean deprecated, JSONArray fieldsJSONArray, boolean mutation,
			String operationName)
		throws Exception {

		JSONAssert.assertEquals(
			JSONUtil.put(
				"deprecationReason",
				() -> {
					if (!deprecated) {
						return null;
					}

					return _getDeprecationReason(mutation, operationName);
				}
			).put(
				"isDeprecated", deprecated
			).put(
				"name", operationName
			).toString(),
			String.valueOf(_getJSONObject(fieldsJSONArray, operationName)),
			JSONCompareMode.LENIENT);
	}

	private String _getDeprecationReason(
		boolean mutation, String operationName) {

		StringBuilder sb = new StringBuilder(
			"This field is deprecated. Access to ");

		if (mutation) {
			sb.append("mutation is available at mutation/");
		}
		else {
			sb.append("query is available at query/");
		}

		sb.append("testPath_v1_0/");
		sb.append(operationName);

		return sb.toString();
	}

	private JSONObject _getJSONObject(
		JSONArray jsonArray, String operationName) {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (StringUtil.equals(
					operationName, jsonObject.getString("name"))) {

				return jsonObject;
			}
		}

		return null;
	}

	private JSONObject _invoke(GraphQLField graphQLField, String type)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(type, graphQLField);

		return HTTPTestUtil.invokeToJSONObject(
			JSONUtil.put(
				"query", queryGraphQLField.toString()
			).toString(),
			"graphql", Http.Method.POST);
	}

	private void _test(
			int expectedPage, int expectedPageSize, Integer requestPage,
			Integer requestPageSize)
		throws Exception {

		JSONObject jsonObject = JSONUtil.getValueAsJSONObject(
			_invoke(
				new GraphQLField(
					"testDTOPage",
					HashMapBuilder.put(
						"page", (Object)requestPage
					).put(
						"pageSize", requestPageSize
					).build(),
					new GraphQLField("page"), new GraphQLField("pageSize")),
				"query"),
			"JSONObject/data", "JSONObject/testDTOPage");

		Assert.assertEquals(expectedPage, jsonObject.getInt("page"));
		Assert.assertEquals(expectedPageSize, jsonObject.getInt("pageSize"));
	}

	private String _toGraphQLString(TestDTO testDTO) throws Exception {
		StringBuilder sb = new StringBuilder("{");

		for (Field field : ReflectionUtil.getDeclaredFields(TestDTO.class)) {
			if (ArrayUtil.isEmpty(
					field.getAnnotationsByType(
						com.liferay.portal.vulcan.graphql.annotation.
							GraphQLField.class))) {

				continue;
			}

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append(field.getName());
			sb.append(": ");

			_appendGraphQLFieldValue(sb, field.get(testDTO));
		}

		sb.append("}");

		return sb.toString();
	}

	@Inject
	private ConfigurationAdmin _configurationAdmin;

	private ServiceRegistration<ServletData> _serviceRegistration;
	private TestDTO _testDTO;

}