/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.site.client.serdes.v1_0;

import com.liferay.headless.admin.site.client.dto.v1_0.ContentPageTemplate;
import com.liferay.headless.admin.site.client.dto.v1_0.ItemExternalReference;
import com.liferay.headless.admin.site.client.dto.v1_0.Keyword;
import com.liferay.headless.admin.site.client.dto.v1_0.PageSpecification;
import com.liferay.headless.admin.site.client.dto.v1_0.PageTemplate;
import com.liferay.headless.admin.site.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.site.client.dto.v1_0.WidgetPageTemplate;
import com.liferay.headless.admin.site.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Rubén Pulido
 * @generated
 */
@Generated("")
public class PageTemplateSerDes {

	public static PageTemplate toDTO(String json) {
		PageTemplateJSONParser pageTemplateJSONParser =
			new PageTemplateJSONParser();

		return pageTemplateJSONParser.parseToDTO(json);
	}

	public static PageTemplate[] toDTOs(String json) {
		PageTemplateJSONParser pageTemplateJSONParser =
			new PageTemplateJSONParser();

		return pageTemplateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(PageTemplate pageTemplate) {
		if (pageTemplate == null) {
			return "null";
		}

		PageTemplate.Type type = pageTemplate.getType();

		if (type != null) {
			String typeString = type.toString();

			if (typeString.equals("ContentPageTemplate")) {
				return ContentPageTemplateSerDes.toJSON(
					(ContentPageTemplate)pageTemplate);
			}

			if (typeString.equals("WidgetPageTemplate")) {
				return WidgetPageTemplateSerDes.toJSON(
					(WidgetPageTemplate)pageTemplate);
			}

			throw new IllegalArgumentException("Unknown type " + typeString);
		}
		else {
			throw new IllegalArgumentException("Missing type parameter");
		}
	}

	public static Map<String, Object> toMap(String json) {
		PageTemplateJSONParser pageTemplateJSONParser =
			new PageTemplateJSONParser();

		return pageTemplateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(PageTemplate pageTemplate) {
		if (pageTemplate == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (pageTemplate.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put("creator", String.valueOf(pageTemplate.getCreator()));
		}

		if (pageTemplate.getCreatorExternalReferenceCode() == null) {
			map.put("creatorExternalReferenceCode", null);
		}
		else {
			map.put(
				"creatorExternalReferenceCode",
				String.valueOf(pageTemplate.getCreatorExternalReferenceCode()));
		}

		if (pageTemplate.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(pageTemplate.getDateCreated()));
		}

		if (pageTemplate.getDateModified() == null) {
			map.put("dateModified", null);
		}
		else {
			map.put(
				"dateModified",
				liferayToJSONDateFormat.format(pageTemplate.getDateModified()));
		}

		if (pageTemplate.getDatePublished() == null) {
			map.put("datePublished", null);
		}
		else {
			map.put(
				"datePublished",
				liferayToJSONDateFormat.format(
					pageTemplate.getDatePublished()));
		}

		if (pageTemplate.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(pageTemplate.getExternalReferenceCode()));
		}

		if (pageTemplate.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(pageTemplate.getKey()));
		}

		if (pageTemplate.getKeywordItemExternalReferences() == null) {
			map.put("keywordItemExternalReferences", null);
		}
		else {
			map.put(
				"keywordItemExternalReferences",
				String.valueOf(
					pageTemplate.getKeywordItemExternalReferences()));
		}

		if (pageTemplate.getKeywords() == null) {
			map.put("keywords", null);
		}
		else {
			map.put("keywords", String.valueOf(pageTemplate.getKeywords()));
		}

		if (pageTemplate.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(pageTemplate.getName()));
		}

		if (pageTemplate.getPageSpecifications() == null) {
			map.put("pageSpecifications", null);
		}
		else {
			map.put(
				"pageSpecifications",
				String.valueOf(pageTemplate.getPageSpecifications()));
		}

		if (pageTemplate.getPageTemplateSet() == null) {
			map.put("pageTemplateSet", null);
		}
		else {
			map.put(
				"pageTemplateSet",
				String.valueOf(pageTemplate.getPageTemplateSet()));
		}

		if (pageTemplate.getPageTemplateSettings() == null) {
			map.put("pageTemplateSettings", null);
		}
		else {
			map.put(
				"pageTemplateSettings",
				String.valueOf(pageTemplate.getPageTemplateSettings()));
		}

		if (pageTemplate.getTaxonomyCategories() == null) {
			map.put("taxonomyCategories", null);
		}
		else {
			map.put(
				"taxonomyCategories",
				String.valueOf(pageTemplate.getTaxonomyCategories()));
		}

		if (pageTemplate.getTaxonomyCategoryItemExternalReferences() == null) {
			map.put("taxonomyCategoryItemExternalReferences", null);
		}
		else {
			map.put(
				"taxonomyCategoryItemExternalReferences",
				String.valueOf(
					pageTemplate.getTaxonomyCategoryItemExternalReferences()));
		}

		if (pageTemplate.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(pageTemplate.getType()));
		}

		if (pageTemplate.getUuid() == null) {
			map.put("uuid", null);
		}
		else {
			map.put("uuid", String.valueOf(pageTemplate.getUuid()));
		}

		return map;
	}

	public static class PageTemplateJSONParser
		extends BaseJSONParser<PageTemplate> {

		@Override
		protected PageTemplate createDTO() {
			return null;
		}

		@Override
		protected PageTemplate[] createDTOArray(int size) {
			return new PageTemplate[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "creator")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "creatorExternalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "datePublished")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "keywordItemExternalReferences")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "keywords")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "pageSpecifications")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "pageTemplateSet")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "pageTemplateSettings")) {

				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategories")) {

				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"taxonomyCategoryItemExternalReferences")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				return false;
			}

			return false;
		}

		@Override
		public PageTemplate parseToDTO(String json) {
			Map<String, Object> jsonMap = parseToMap(json);

			Object type = jsonMap.get("type");

			if (type != null) {
				String typeString = type.toString();

				if (typeString.equals("ContentPageTemplate")) {
					return ContentPageTemplate.toDTO(json);
				}

				if (typeString.equals("WidgetPageTemplate")) {
					return WidgetPageTemplate.toDTO(json);
				}

				throw new IllegalArgumentException(
					"Unknown type " + typeString);
			}
			else {
				throw new IllegalArgumentException("Missing type parameter");
			}
		}

		@Override
		protected void setField(
			PageTemplate pageTemplate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "creatorExternalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					pageTemplate.setCreatorExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "datePublished")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setDatePublished(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					pageTemplate.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "keywordItemExternalReferences")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					ItemExternalReference[] keywordItemExternalReferencesArray =
						new ItemExternalReference[jsonParserFieldValues.length];

					for (int i = 0;
						 i < keywordItemExternalReferencesArray.length; i++) {

						keywordItemExternalReferencesArray[i] =
							ItemExternalReferenceSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					pageTemplate.setKeywordItemExternalReferences(
						keywordItemExternalReferencesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "keywords")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Keyword[] keywordsArray =
						new Keyword[jsonParserFieldValues.length];

					for (int i = 0; i < keywordsArray.length; i++) {
						keywordsArray[i] = KeywordSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					pageTemplate.setKeywords(keywordsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "pageSpecifications")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					PageSpecification[] pageSpecificationsArray =
						new PageSpecification[jsonParserFieldValues.length];

					for (int i = 0; i < pageSpecificationsArray.length; i++) {
						pageSpecificationsArray[i] =
							PageSpecificationSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					pageTemplate.setPageSpecifications(pageSpecificationsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "pageTemplateSet")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setPageTemplateSet(
						PageTemplateSetSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "pageTemplateSettings")) {

				if (jsonParserFieldValue != null) {
					pageTemplate.setPageTemplateSettings(
						PageTemplateSettingsSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategories")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					TaxonomyCategory[] taxonomyCategoriesArray =
						new TaxonomyCategory[jsonParserFieldValues.length];

					for (int i = 0; i < taxonomyCategoriesArray.length; i++) {
						taxonomyCategoriesArray[i] =
							TaxonomyCategorySerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					pageTemplate.setTaxonomyCategories(taxonomyCategoriesArray);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"taxonomyCategoryItemExternalReferences")) {

				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					ItemExternalReference[]
						taxonomyCategoryItemExternalReferencesArray =
							new ItemExternalReference
								[jsonParserFieldValues.length];

					for (int i = 0;
						 i < taxonomyCategoryItemExternalReferencesArray.length;
						 i++) {

						taxonomyCategoryItemExternalReferencesArray[i] =
							ItemExternalReferenceSerDes.toDTO(
								(String)jsonParserFieldValues[i]);
					}

					pageTemplate.setTaxonomyCategoryItemExternalReferences(
						taxonomyCategoryItemExternalReferencesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setType(
						PageTemplate.Type.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				if (jsonParserFieldValue != null) {
					pageTemplate.setUuid((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}