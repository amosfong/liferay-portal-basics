/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.scim.rest.resource.v1_0.test;

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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.scim.rest.client.dto.v1_0.Group;
import com.liferay.scim.rest.client.dto.v1_0.User;
import com.liferay.scim.rest.client.http.HttpInvoker;
import com.liferay.scim.rest.client.pagination.Page;
import com.liferay.scim.rest.client.resource.v1_0.UserResource;
import com.liferay.scim.rest.client.serdes.v1_0.UserSerDes;

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
 * @author Olivér Kecskeméty
 * @generated
 */
@Generated("")
public abstract class BaseUserResourceTestCase {

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

		_userResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		UserResource.Builder builder = UserResource.builder();

		userResource = builder.authentication(
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

		User user1 = randomUser();

		String json = objectMapper.writeValueAsString(user1);

		User user2 = UserSerDes.toDTO(json);

		Assert.assertTrue(equals(user1, user2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		User user = randomUser();

		String json1 = objectMapper.writeValueAsString(user);
		String json2 = UserSerDes.toJSON(user);

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

		User user = randomUser();

		user.setDisplayName(regex);
		user.setExternalId(regex);
		user.setId(regex);
		user.setLocale(regex);
		user.setNickName(regex);
		user.setPassword(regex);
		user.setPreferredLanguage(regex);
		user.setProfileUrl(regex);
		user.setTimezone(regex);
		user.setTitle(regex);
		user.setUserName(regex);
		user.setUserType(regex);

		String json = UserSerDes.toJSON(user);

		Assert.assertFalse(json.contains(regex));

		user = UserSerDes.toDTO(json);

		Assert.assertEquals(regex, user.getDisplayName());
		Assert.assertEquals(regex, user.getExternalId());
		Assert.assertEquals(regex, user.getId());
		Assert.assertEquals(regex, user.getLocale());
		Assert.assertEquals(regex, user.getNickName());
		Assert.assertEquals(regex, user.getPassword());
		Assert.assertEquals(regex, user.getPreferredLanguage());
		Assert.assertEquals(regex, user.getProfileUrl());
		Assert.assertEquals(regex, user.getTimezone());
		Assert.assertEquals(regex, user.getTitle());
		Assert.assertEquals(regex, user.getUserName());
		Assert.assertEquals(regex, user.getUserType());
	}

	@Test
	public void testGetV2Users() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostV2User() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostV2UserSearch() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteV2User() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		User user = testDeleteV2User_addUser();

		assertHttpResponseStatusCode(
			204, userResource.deleteV2UserHttpResponse(user.getId()));
	}

	protected User testDeleteV2User_addUser() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetV2UserById() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutV2User() throws Exception {
		Assert.assertTrue(false);
	}

	protected User testGraphQLUser_addUser() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(User user, List<User> users) {
		boolean contains = false;

		for (User item : users) {
			if (equals(user, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(users + " does not contain " + user, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(User user1, User user2) {
		Assert.assertTrue(
			user1 + " does not equal " + user2, equals(user1, user2));
	}

	protected void assertEquals(List<User> users1, List<User> users2) {
		Assert.assertEquals(users1.size(), users2.size());

		for (int i = 0; i < users1.size(); i++) {
			User user1 = users1.get(i);
			User user2 = users2.get(i);

			assertEquals(user1, user2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<User> users1, List<User> users2) {

		Assert.assertEquals(users1.size(), users2.size());

		for (User user1 : users1) {
			boolean contains = false;

			for (User user2 : users2) {
				if (equals(user1, user2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(users2 + " does not contain " + user1, contains);
		}
	}

	protected void assertValid(User user) throws Exception {
		boolean valid = true;

		if (user.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (user.getActive() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("addresses", additionalAssertFieldName)) {
				if (user.getAddresses() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("displayName", additionalAssertFieldName)) {
				if (user.getDisplayName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("emails", additionalAssertFieldName)) {
				if (user.getEmails() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entitlements", additionalAssertFieldName)) {
				if (user.getEntitlements() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalId", additionalAssertFieldName)) {
				if (user.getExternalId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("groups", additionalAssertFieldName)) {
				if (user.getGroups() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("ims", additionalAssertFieldName)) {
				if (user.getIms() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("locale", additionalAssertFieldName)) {
				if (user.getLocale() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("meta", additionalAssertFieldName)) {
				if (user.getMeta() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (user.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("nickName", additionalAssertFieldName)) {
				if (user.getNickName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("password", additionalAssertFieldName)) {
				if (user.getPassword() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("phoneNumbers", additionalAssertFieldName)) {
				if (user.getPhoneNumbers() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("photos", additionalAssertFieldName)) {
				if (user.getPhotos() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"preferredLanguage", additionalAssertFieldName)) {

				if (user.getPreferredLanguage() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("profileUrl", additionalAssertFieldName)) {
				if (user.getProfileUrl() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("roles", additionalAssertFieldName)) {
				if (user.getRoles() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("schemas", additionalAssertFieldName)) {
				if (user.getSchemas() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("timezone", additionalAssertFieldName)) {
				if (user.getTimezone() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (user.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"urn_ietf_params_scim_schemas_extension_liferay_2_0_User",
					additionalAssertFieldName)) {

				if (user.
						getUrn_ietf_params_scim_schemas_extension_liferay_2_0_User() ==
							null) {

					valid = false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (user.getUserName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userType", additionalAssertFieldName)) {
				if (user.getUserType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("x509Certificates", additionalAssertFieldName)) {
				if (user.getX509Certificates() == null) {
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

	protected void assertValid(Page<User> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<User> page, Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<User> users = page.getItems();

		int size = users.size();

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
				getDeclaredFields(com.liferay.scim.rest.dto.v1_0.User.class)) {

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

	protected boolean equals(User user1, User user2) {
		if (user1 == user2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getActive(), user2.getActive())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("addresses", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getAddresses(), user2.getAddresses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("displayName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getDisplayName(), user2.getDisplayName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("emails", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getEmails(), user2.getEmails())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("entitlements", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getEntitlements(), user2.getEntitlements())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getExternalId(), user2.getExternalId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("groups", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getGroups(), user2.getGroups())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getId(), user2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("ims", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getIms(), user2.getIms())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("locale", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getLocale(), user2.getLocale())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("meta", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getMeta(), user2.getMeta())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getName(), user2.getName())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("nickName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getNickName(), user2.getNickName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("password", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getPassword(), user2.getPassword())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("phoneNumbers", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getPhoneNumbers(), user2.getPhoneNumbers())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("photos", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getPhotos(), user2.getPhotos())) {
					return false;
				}

				continue;
			}

			if (Objects.equals(
					"preferredLanguage", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						user1.getPreferredLanguage(),
						user2.getPreferredLanguage())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("profileUrl", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getProfileUrl(), user2.getProfileUrl())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("roles", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getRoles(), user2.getRoles())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("schemas", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getSchemas(), user2.getSchemas())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("timezone", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getTimezone(), user2.getTimezone())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!Objects.deepEquals(user1.getTitle(), user2.getTitle())) {
					return false;
				}

				continue;
			}

			if (Objects.equals(
					"urn_ietf_params_scim_schemas_extension_liferay_2_0_User",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						user1.
							getUrn_ietf_params_scim_schemas_extension_liferay_2_0_User(),
						user2.
							getUrn_ietf_params_scim_schemas_extension_liferay_2_0_User())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getUserName(), user2.getUserName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getUserType(), user2.getUserType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("x509Certificates", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						user1.getX509Certificates(),
						user2.getX509Certificates())) {

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

		if (!(_userResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_userResource;

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
		EntityField entityField, String operator, User user) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("active")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("addresses")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("displayName")) {
			Object object = user.getDisplayName();

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

		if (entityFieldName.equals("emails")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("entitlements")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("externalId")) {
			Object object = user.getExternalId();

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

		if (entityFieldName.equals("groups")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			Object object = user.getId();

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

		if (entityFieldName.equals("ims")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("locale")) {
			Object object = user.getLocale();

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

		if (entityFieldName.equals("meta")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("nickName")) {
			Object object = user.getNickName();

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

		if (entityFieldName.equals("password")) {
			Object object = user.getPassword();

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

		if (entityFieldName.equals("phoneNumbers")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("photos")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("preferredLanguage")) {
			Object object = user.getPreferredLanguage();

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

		if (entityFieldName.equals("profileUrl")) {
			Object object = user.getProfileUrl();

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

		if (entityFieldName.equals("roles")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("schemas")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("timezone")) {
			Object object = user.getTimezone();

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

		if (entityFieldName.equals("title")) {
			Object object = user.getTitle();

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
				"urn_ietf_params_scim_schemas_extension_liferay_2_0_User")) {

			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("userName")) {
			Object object = user.getUserName();

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

		if (entityFieldName.equals("userType")) {
			Object object = user.getUserType();

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

		if (entityFieldName.equals("x509Certificates")) {
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

	protected User randomUser() throws Exception {
		return new User() {
			{
				active = RandomTestUtil.randomBoolean();
				displayName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				externalId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = StringUtil.toLowerCase(RandomTestUtil.randomString());
				locale = StringUtil.toLowerCase(RandomTestUtil.randomString());
				nickName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				password = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				preferredLanguage = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				profileUrl = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				timezone = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				title = StringUtil.toLowerCase(RandomTestUtil.randomString());
				userName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userType = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected User randomIrrelevantUser() throws Exception {
		User randomIrrelevantUser = randomUser();

		return randomIrrelevantUser;
	}

	protected User randomPatchUser() throws Exception {
		return randomUser();
	}

	protected UserResource userResource;
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
		LogFactoryUtil.getLog(BaseUserResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.scim.rest.resource.v1_0.UserResource _userResource;

}