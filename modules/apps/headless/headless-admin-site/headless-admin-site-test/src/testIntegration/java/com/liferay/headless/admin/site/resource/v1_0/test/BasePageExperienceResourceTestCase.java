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

import com.liferay.headless.admin.site.client.dto.v1_0.PageExperience;
import com.liferay.headless.admin.site.client.http.HttpInvoker;
import com.liferay.headless.admin.site.client.pagination.Page;
import com.liferay.headless.admin.site.client.resource.v1_0.PageExperienceResource;
import com.liferay.headless.admin.site.client.serdes.v1_0.PageExperienceSerDes;
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
public abstract class BasePageExperienceResourceTestCase {

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

		_pageExperienceResource.setContextCompany(testCompany);

		com.liferay.portal.kernel.model.User testCompanyAdminUser =
			UserTestUtil.getAdminUser(testCompany.getCompanyId());

		PageExperienceResource.Builder builder =
			PageExperienceResource.builder();

		pageExperienceResource = builder.authentication(
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

		PageExperience pageExperience1 = randomPageExperience();

		String json = objectMapper.writeValueAsString(pageExperience1);

		PageExperience pageExperience2 = PageExperienceSerDes.toDTO(json);

		Assert.assertTrue(equals(pageExperience1, pageExperience2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		PageExperience pageExperience = randomPageExperience();

		String json1 = objectMapper.writeValueAsString(pageExperience);
		String json2 = PageExperienceSerDes.toJSON(pageExperience);

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

		PageExperience pageExperience = randomPageExperience();

		pageExperience.setExternalReferenceCode(regex);
		pageExperience.setKey(regex);
		pageExperience.setSegmentExternalReferenceCode(regex);
		pageExperience.setSitePageExternalReferenceCode(regex);

		String json = PageExperienceSerDes.toJSON(pageExperience);

		Assert.assertFalse(json.contains(regex));

		pageExperience = PageExperienceSerDes.toDTO(json);

		Assert.assertEquals(regex, pageExperience.getExternalReferenceCode());
		Assert.assertEquals(regex, pageExperience.getKey());
		Assert.assertEquals(
			regex, pageExperience.getSegmentExternalReferenceCode());
		Assert.assertEquals(
			regex, pageExperience.getSitePageExternalReferenceCode());
	}

	@Test
	public void testDeleteSiteSiteByExternalReferenceCodePageExperience()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodePageExperience()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodePageExperience()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetSiteSiteByExternalReferenceCodePageExperienceNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testPatchSiteSiteByExternalReferenceCodePageExperience()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutSiteSiteByExternalReferenceCodePageExperience()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage()
		throws Exception {

		String siteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getSiteExternalReferenceCode();
		String irrelevantSiteExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getIrrelevantSiteExternalReferenceCode();
		String pageSpecificationExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getPageSpecificationExternalReferenceCode();
		String irrelevantPageSpecificationExternalReferenceCode =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getIrrelevantPageSpecificationExternalReferenceCode();

		Page<PageExperience> page =
			pageExperienceResource.
				getSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode);

		long totalCount = page.getTotalCount();

		if ((irrelevantSiteExternalReferenceCode != null) &&
			(irrelevantPageSpecificationExternalReferenceCode != null)) {

			PageExperience irrelevantPageExperience =
				testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_addPageExperience(
					irrelevantSiteExternalReferenceCode,
					irrelevantPageSpecificationExternalReferenceCode,
					randomIrrelevantPageExperience());

			page =
				pageExperienceResource.
					getSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage(
						irrelevantSiteExternalReferenceCode,
						irrelevantPageSpecificationExternalReferenceCode);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantPageExperience,
				(List<PageExperience>)page.getItems());
			assertValid(
				page,
				testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getExpectedActions(
					irrelevantSiteExternalReferenceCode,
					irrelevantPageSpecificationExternalReferenceCode));
		}

		PageExperience pageExperience1 =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_addPageExperience(
				siteExternalReferenceCode,
				pageSpecificationExternalReferenceCode, randomPageExperience());

		PageExperience pageExperience2 =
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_addPageExperience(
				siteExternalReferenceCode,
				pageSpecificationExternalReferenceCode, randomPageExperience());

		page =
			pageExperienceResource.
				getSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage(
					siteExternalReferenceCode,
					pageSpecificationExternalReferenceCode);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(pageExperience1, (List<PageExperience>)page.getItems());
		assertContains(pageExperience2, (List<PageExperience>)page.getItems());
		assertValid(
			page,
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getExpectedActions(
				siteExternalReferenceCode,
				pageSpecificationExternalReferenceCode));
	}

	protected Map<String, Map<String, String>>
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getExpectedActions(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected PageExperience
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_addPageExperience(
				String siteExternalReferenceCode,
				String pageSpecificationExternalReferenceCode,
				PageExperience pageExperience)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getSiteExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getIrrelevantSiteExternalReferenceCode()
		throws Exception {

		return null;
	}

	protected String
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getPageSpecificationExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetSiteSiteByExternalReferenceCodePageSpecificationPageExperiencesPage_getIrrelevantPageSpecificationExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostSiteSiteByExternalReferenceCodePageSpecificationPageExperience()
		throws Exception {

		PageExperience randomPageExperience = randomPageExperience();

		PageExperience postPageExperience =
			testPostSiteSiteByExternalReferenceCodePageSpecificationPageExperience_addPageExperience(
				randomPageExperience);

		assertEquals(randomPageExperience, postPageExperience);
		assertValid(postPageExperience);
	}

	protected PageExperience
			testPostSiteSiteByExternalReferenceCodePageSpecificationPageExperience_addPageExperience(
				PageExperience pageExperience)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		PageExperience pageExperience, List<PageExperience> pageExperiences) {

		boolean contains = false;

		for (PageExperience item : pageExperiences) {
			if (equals(pageExperience, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			pageExperiences + " does not contain " + pageExperience, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		PageExperience pageExperience1, PageExperience pageExperience2) {

		Assert.assertTrue(
			pageExperience1 + " does not equal " + pageExperience2,
			equals(pageExperience1, pageExperience2));
	}

	protected void assertEquals(
		List<PageExperience> pageExperiences1,
		List<PageExperience> pageExperiences2) {

		Assert.assertEquals(pageExperiences1.size(), pageExperiences2.size());

		for (int i = 0; i < pageExperiences1.size(); i++) {
			PageExperience pageExperience1 = pageExperiences1.get(i);
			PageExperience pageExperience2 = pageExperiences2.get(i);

			assertEquals(pageExperience1, pageExperience2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<PageExperience> pageExperiences1,
		List<PageExperience> pageExperiences2) {

		Assert.assertEquals(pageExperiences1.size(), pageExperiences2.size());

		for (PageExperience pageExperience1 : pageExperiences1) {
			boolean contains = false;

			for (PageExperience pageExperience2 : pageExperiences2) {
				if (equals(pageExperience1, pageExperience2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				pageExperiences2 + " does not contain " + pageExperience1,
				contains);
		}
	}

	protected void assertValid(PageExperience pageExperience) throws Exception {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (pageExperience.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (pageExperience.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name_i18n", additionalAssertFieldName)) {
				if (pageExperience.getName_i18n() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("pageElements", additionalAssertFieldName)) {
				if (pageExperience.getPageElements() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("pageRules", additionalAssertFieldName)) {
				if (pageExperience.getPageRules() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (pageExperience.getPriority() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"segmentExternalReferenceCode",
					additionalAssertFieldName)) {

				if (pageExperience.getSegmentExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"sitePageExternalReferenceCode",
					additionalAssertFieldName)) {

				if (pageExperience.getSitePageExternalReferenceCode() == null) {
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

	protected void assertValid(Page<PageExperience> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<PageExperience> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<PageExperience> pageExperiences = page.getItems();

		int size = pageExperiences.size();

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
					com.liferay.headless.admin.site.dto.v1_0.PageExperience.
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
		PageExperience pageExperience1, PageExperience pageExperience2) {

		if (pageExperience1 == pageExperience2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						pageExperience1.getExternalReferenceCode(),
						pageExperience2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						pageExperience1.getKey(), pageExperience2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name_i18n", additionalAssertFieldName)) {
				if (!equals(
						(Map)pageExperience1.getName_i18n(),
						(Map)pageExperience2.getName_i18n())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("pageElements", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						pageExperience1.getPageElements(),
						pageExperience2.getPageElements())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("pageRules", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						pageExperience1.getPageRules(),
						pageExperience2.getPageRules())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						pageExperience1.getPriority(),
						pageExperience2.getPriority())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"segmentExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						pageExperience1.getSegmentExternalReferenceCode(),
						pageExperience2.getSegmentExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"sitePageExternalReferenceCode",
					additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						pageExperience1.getSitePageExternalReferenceCode(),
						pageExperience2.getSitePageExternalReferenceCode())) {

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

		if (!(_pageExperienceResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_pageExperienceResource;

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
		PageExperience pageExperience) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("externalReferenceCode")) {
			Object object = pageExperience.getExternalReferenceCode();

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
			Object object = pageExperience.getKey();

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

		if (entityFieldName.equals("name_i18n")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("pageElements")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("pageRules")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("priority")) {
			sb.append(String.valueOf(pageExperience.getPriority()));

			return sb.toString();
		}

		if (entityFieldName.equals("segmentExternalReferenceCode")) {
			Object object = pageExperience.getSegmentExternalReferenceCode();

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

		if (entityFieldName.equals("sitePageExternalReferenceCode")) {
			Object object = pageExperience.getSitePageExternalReferenceCode();

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

	protected PageExperience randomPageExperience() throws Exception {
		return new PageExperience() {
			{
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				priority = RandomTestUtil.randomInt();
				segmentExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				sitePageExternalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected PageExperience randomIrrelevantPageExperience() throws Exception {
		PageExperience randomIrrelevantPageExperience = randomPageExperience();

		return randomIrrelevantPageExperience;
	}

	protected PageExperience randomPatchPageExperience() throws Exception {
		return randomPageExperience();
	}

	protected PageExperienceResource pageExperienceResource;
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
		LogFactoryUtil.getLog(BasePageExperienceResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.admin.site.resource.v1_0.PageExperienceResource
		_pageExperienceResource;

}