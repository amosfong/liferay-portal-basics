/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.token.definition.internal;

import com.liferay.frontend.token.definition.FrontendToken;
import com.liferay.frontend.token.definition.FrontendTokenCategory;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.frontend.token.definition.FrontendTokenMapping;
import com.liferay.frontend.token.definition.FrontendTokenSet;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoader;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.URLUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.IOException;

import java.net.URL;

import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Iván Zaera
 */
public class FrontendTokenDefinitionImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testDescendantsNavigation() throws JSONException {
		JSONFactory jsonFactory = new JSONFactoryImpl();

		FrontendTokenDefinition frontendTokenDefinition =
			new FrontendTokenDefinitionImpl(
				jsonFactory.createJSONObject(_FRONTEND_TOKEN_DEFINITION_JSON),
				jsonFactory, null, "theme_id");

		Collection<FrontendTokenCategory> frontendTokenCategories =
			frontendTokenDefinition.getFrontendTokenCategories();

		Iterator<FrontendTokenCategory> frontendTokenCategoryIterator =
			frontendTokenCategories.iterator();

		FrontendTokenCategory frontendTokenCategory =
			frontendTokenCategoryIterator.next();

		Collection<FrontendTokenSet> frontendTokenSets =
			frontendTokenCategory.getFrontendTokenSets();

		Iterator<FrontendTokenSet> frontendTokenSetIterator =
			frontendTokenSets.iterator();

		FrontendTokenSet frontendTokenSet = frontendTokenSetIterator.next();

		Collection<FrontendToken> frontendTokens =
			frontendTokenSet.getFrontendTokens();

		Iterator<FrontendToken> frontendTokenIterator =
			frontendTokens.iterator();

		FrontendToken frontendToken = frontendTokenIterator.next();

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokenSets(),
			frontendTokenCategory.getFrontendTokenSets());

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokens(),
			frontendTokenCategory.getFrontendTokens());

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokens(),
			frontendTokenSet.getFrontendTokens());

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokenMappings(),
			frontendTokenCategory.getFrontendTokenMappings());

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokenMappings(),
			frontendTokenSet.getFrontendTokenMappings());

		_assertCollectionEquals(
			frontendTokenDefinition.getFrontendTokenMappings(),
			frontendToken.getFrontendTokenMappings());
	}

	@Test
	public void testParentGetters() throws JSONException {
		JSONFactory jsonFactory = new JSONFactoryImpl();

		FrontendTokenDefinition frontendTokenDefinition =
			new FrontendTokenDefinitionImpl(
				jsonFactory.createJSONObject(_FRONTEND_TOKEN_DEFINITION_JSON),
				jsonFactory, null, "theme_id");

		Collection<FrontendTokenCategory> frontendTokenCategories =
			frontendTokenDefinition.getFrontendTokenCategories();

		Iterator<FrontendTokenCategory> frontendTokenCategoryIterator =
			frontendTokenCategories.iterator();

		FrontendTokenCategory frontendTokenCategory =
			frontendTokenCategoryIterator.next();

		Assert.assertSame(
			frontendTokenDefinition,
			frontendTokenCategory.getFrontendTokenDefinition());

		Collection<FrontendTokenSet> frontendTokenSets =
			frontendTokenCategory.getFrontendTokenSets();

		Iterator<FrontendTokenSet> frontendTokenSetIterator =
			frontendTokenSets.iterator();

		FrontendTokenSet frontendTokenSet = frontendTokenSetIterator.next();

		Assert.assertSame(
			frontendTokenCategory, frontendTokenSet.getFrontendTokenCategory());

		Collection<FrontendToken> frontendTokens =
			frontendTokenSet.getFrontendTokens();

		Iterator<FrontendToken> frontendTokenIterator =
			frontendTokens.iterator();

		FrontendToken frontendToken = frontendTokenIterator.next();

		Assert.assertSame(
			frontendTokenSet, frontendToken.getFrontendTokenSet());

		Collection<FrontendTokenMapping> frontendTokenMappings =
			frontendToken.getFrontendTokenMappings();

		Iterator<FrontendTokenMapping> frontendTokenMappingIterator =
			frontendTokenMappings.iterator();

		FrontendTokenMapping frontendTokenMapping =
			frontendTokenMappingIterator.next();

		Assert.assertSame(
			frontendToken, frontendTokenMapping.getFrontendToken());
	}

	@Test
	public void testParsedModel() throws JSONException {
		JSONFactory jsonFactory = new JSONFactoryImpl();

		FrontendTokenDefinition frontendTokenDefinition =
			new FrontendTokenDefinitionImpl(
				jsonFactory.createJSONObject(_FRONTEND_TOKEN_DEFINITION_JSON),
				jsonFactory, null, "theme_id");

		Collection<FrontendTokenCategory> frontendTokenCategories =
			frontendTokenDefinition.getFrontendTokenCategories();

		Assert.assertEquals(
			frontendTokenCategories.toString(), 1,
			frontendTokenCategories.size());

		Iterator<FrontendTokenCategory> frontendTokenCategoryIterator =
			frontendTokenCategories.iterator();

		FrontendTokenCategory frontendTokenCategory =
			frontendTokenCategoryIterator.next();

		Collection<FrontendTokenSet> frontendTokenSets =
			frontendTokenCategory.getFrontendTokenSets();

		Assert.assertEquals(
			frontendTokenSets.toString(), 1, frontendTokenSets.size());

		Iterator<FrontendTokenSet> frontendTokenSetIterator =
			frontendTokenSets.iterator();

		FrontendTokenSet frontendTokenSet = frontendTokenSetIterator.next();

		Collection<FrontendToken> frontendTokens =
			frontendTokenSet.getFrontendTokens();

		Assert.assertEquals(
			frontendTokens.toString(), 1, frontendTokens.size());

		Iterator<FrontendToken> frontendTokenIterator =
			frontendTokens.iterator();

		FrontendToken frontendToken = frontendTokenIterator.next();

		Assert.assertEquals(FrontendToken.Type.STRING, frontendToken.getType());

		Assert.assertEquals("#FFF", frontendToken.getDefaultValue());

		Collection<FrontendTokenMapping> frontendTokenMappings =
			frontendToken.getFrontendTokenMappings();

		Assert.assertEquals(
			frontendTokenMappings.toString(), 1, frontendTokenMappings.size());

		Iterator<FrontendTokenMapping> frontendTokenMappingIterator =
			frontendTokenMappings.iterator();

		FrontendTokenMapping frontendTokenMapping =
			frontendTokenMappingIterator.next();

		Assert.assertEquals(
			FrontendTokenMapping.TYPE_CSS_VARIABLE,
			frontendTokenMapping.getType());

		Assert.assertEquals("white", frontendTokenMapping.getValue());
	}

	@Test
	public void testTranslateJSON() throws JSONException {
		ResourceBundleLoader resourceBundleLoader = Mockito.mock(
			ResourceBundleLoader.class);

		Package pkg = FrontendTokenDefinitionImplTest.class.getPackage();

		Mockito.when(
			resourceBundleLoader.loadResourceBundle(LocaleUtil.ENGLISH)
		).thenReturn(
			ResourceBundle.getBundle(
				pkg.getName() + ".dependencies.Language", LocaleUtil.ENGLISH)
		);

		JSONFactory jsonFactory = new JSONFactoryImpl();

		FrontendTokenDefinitionImpl frontendTokenDefinitionImpl =
			new FrontendTokenDefinitionImpl(
				jsonFactory.createJSONObject(_FRONTEND_TOKEN_DEFINITION_JSON),
				jsonFactory, resourceBundleLoader, "theme_id");

		JSONObject jsonObject = frontendTokenDefinitionImpl.getJSONObject(
			LocaleUtil.ENGLISH);

		Assert.assertEquals(
			_TRANSLATED_FRONTEND_TOKEN_DEFINITION_JSON_OBJECT.toMap(),
			jsonObject.toMap());
	}

	private void _assertCollectionEquals(
		Collection<?> expected, Collection<?> actual) {

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

	private static final String _FRONTEND_TOKEN_DEFINITION_JSON;

	private static final JSONObject
		_TRANSLATED_FRONTEND_TOKEN_DEFINITION_JSON_OBJECT;

	static {
		URL url = FrontendTokenDefinitionRegistryImplTest.class.getResource(
			"dependencies/frontend-token-definition.json");

		try {
			_FRONTEND_TOKEN_DEFINITION_JSON = URLUtil.toString(url);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		url = FrontendTokenDefinitionRegistryImplTest.class.getResource(
			"dependencies/translated-frontend-token-definition.json");

		try {
			JSONFactory jsonFactory = new JSONFactoryImpl();

			_TRANSLATED_FRONTEND_TOKEN_DEFINITION_JSON_OBJECT =
				jsonFactory.createJSONObject(URLUtil.toString(url));
		}
		catch (IOException | JSONException exception) {
			throw new RuntimeException(exception);
		}
	}

}