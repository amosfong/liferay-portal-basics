/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.configuration.admin.display;

import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.configuration.admin.display.ConfigurationFormRenderer;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CamelCaseUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.configuration.SemanticSearchConfiguration;
import com.liferay.portal.search.configuration.SemanticSearchConfigurationProvider;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingRetriever;
import com.liferay.portal.search.web.internal.display.context.SemanticSearchCompanyConfigurationDisplayContext;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = ConfigurationFormRenderer.class)
public class SemanticSearchConfigurationFormRenderer
	implements ConfigurationFormRenderer {

	@Override
	public String getPid() {
		return SemanticSearchConfiguration.class.getName();
	}

	@Override
	public Map<String, Object> getRequestParameters(
		HttpServletRequest httpServletRequest) {

		return HashMapBuilder.<String, Object>put(
			"textEmbeddingCacheTimeout",
			ParamUtil.getInteger(
				httpServletRequest, "textEmbeddingCacheTimeout")
		).put(
			"textEmbeddingProviderConfigurationJSONs",
			StringUtil.split(
				ParamUtil.getString(
					httpServletRequest,
					"textEmbeddingProviderConfigurationJSONs"),
				CharPool.PIPE)
		).put(
			"textEmbeddingsEnabled",
			ParamUtil.getBoolean(httpServletRequest, "textEmbeddingsEnabled")
		).build();
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		if (!FeatureFlagManagerUtil.isEnabled("LPS-122920")) {
			PrintWriter printWriter = httpServletResponse.getWriter();

			printWriter.print(
				"<div class=\"alert alert-info\">This feature is not " +
					"available.</div>");

			return;
		}

		SemanticSearchCompanyConfigurationDisplayContext
			semanticSearchCompanyConfigurationDisplayContext =
				new SemanticSearchCompanyConfigurationDisplayContext();

		semanticSearchCompanyConfigurationDisplayContext.
			setAvailableModelClassNames(
				_getAvailableModelClassNames(httpServletRequest));
		semanticSearchCompanyConfigurationDisplayContext.
			setAvailableEmbeddingVectorDimensions(
				_getAvailableEmbeddingVectorDimensions());
		semanticSearchCompanyConfigurationDisplayContext.
			setAvailableLanguageDisplayNames(
				_getAvailableLanguageDisplayNames(httpServletRequest));
		semanticSearchCompanyConfigurationDisplayContext.
			setAvailableTextEmbeddingProviders(
				_getAvailableTextEmbeddingProviders(httpServletRequest));
		semanticSearchCompanyConfigurationDisplayContext.
			setAvailableTextTruncationStrategies(
				_getAvailableTextTruncationStrategies(httpServletRequest));

		SemanticSearchConfiguration semanticSearchConfiguration =
			_getSemanticSearchConfiguration(httpServletRequest);

		semanticSearchCompanyConfigurationDisplayContext.
			setTextEmbeddingCacheTimeout(
				semanticSearchConfiguration.textEmbeddingCacheTimeout());
		semanticSearchCompanyConfigurationDisplayContext.
			setTextEmbeddingsEnabled(
				semanticSearchConfiguration.textEmbeddingsEnabled());
		semanticSearchCompanyConfigurationDisplayContext.
			setTextEmbeddingProviderConfigurationJSONs(
				semanticSearchConfiguration.
					textEmbeddingProviderConfigurationJSONs());

		httpServletRequest.setAttribute(
			SemanticSearchCompanyConfigurationDisplayContext.class.getName(),
			semanticSearchCompanyConfigurationDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/semantic_search/configuration.jsp");
	}

	private List<String> _getAvailableEmbeddingVectorDimensions() {
		return Arrays.asList(
			ArrayUtil.toStringArray(
				_searchEngineInformation.getEmbeddingVectorDimensions()));
	}

	private Map<String, String> _getAvailableLanguageDisplayNames(
		HttpServletRequest httpServletRequest) {

		Map<String, String> availableLanguageDisplayNames = new HashMap<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		for (Locale locale :
				_language.getCompanyAvailableLocales(
					themeDisplay.getCompanyId())) {

			availableLanguageDisplayNames.put(
				LocaleUtil.toLanguageId(locale),
				locale.getDisplayName(themeDisplay.getLocale()));
		}

		return _sortByValue(availableLanguageDisplayNames);
	}

	private Map<String, String> _getAvailableModelClassNames(
		HttpServletRequest httpServletRequest) {

		return _sortByValue(
			HashMapBuilder.put(
				"com.liferay.blogs.model.BlogsEntry",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.blogs.model.BlogsEntry")
			).put(
				"com.liferay.document.library.kernel.model.DLFileEntry",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.document.library.kernel." +
						"model.DLFileEntry")
			).put(
				"com.liferay.journal.model.JournalArticle",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.journal.model.JournalArticle")
			).put(
				"com.liferay.knowledge.base.model.KBArticle",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.knowledge.base.model.KBArticle")
			).put(
				"com.liferay.message.boards.model.MBMessage",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.message.boards.model.MBMessage")
			).put(
				"com.liferay.wiki.model.WikiPage",
				_language.get(
					httpServletRequest,
					"model.resource.com.liferay.wiki.model.WikiPage")
			).build());
	}

	private Map<String, String> _getAvailableTextEmbeddingProviders(
		HttpServletRequest httpServletRequest) {

		Map<String, String> availableTextEmbeddingProviders = new TreeMap<>();

		ListUtil.isNotEmptyForEach(
			_textEmbeddingRetriever.getAvailableProviderNames(),
			name -> availableTextEmbeddingProviders.put(
				name,
				_language.get(
					httpServletRequest, CamelCaseUtil.fromCamelCase(name))));

		return availableTextEmbeddingProviders;
	}

	private Map<String, String> _getAvailableTextTruncationStrategies(
		HttpServletRequest httpServletRequest) {

		return LinkedHashMapBuilder.put(
			"beginning", _language.get(httpServletRequest, "beginning")
		).put(
			"middle", _language.get(httpServletRequest, "middle")
		).put(
			"end", _language.get(httpServletRequest, "end")
		).build();
	}

	private SemanticSearchConfiguration _getSemanticSearchConfiguration(
		HttpServletRequest httpServletRequest) {

		if (Objects.equals(
				_portal.getPortletId(httpServletRequest),
				ConfigurationAdminPortletKeys.INSTANCE_SETTINGS)) {

			return _semanticSearchConfigurationProvider.getCompanyConfiguration(
				_portal.getCompanyId(httpServletRequest));
		}

		return _semanticSearchConfigurationProvider.getSystemConfiguration();
	}

	private Map<String, String> _sortByValue(Map<String, String> map) {
		Map<String, String> sortedValues = new LinkedHashMap<>();

		for (Map.Entry<String, String> entry :
				ListUtil.sort(
					new ArrayList<>(map.entrySet()),
					Map.Entry.comparingByValue())) {

			sortedValues.put(entry.getKey(), entry.getValue());
		}

		return sortedValues;
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private SearchEngineInformation _searchEngineInformation;

	@Reference
	private SemanticSearchConfigurationProvider
		_semanticSearchConfigurationProvider;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.search.web)",
		unbind = "-"
	)
	private ServletContext _servletContext;

	@Reference
	private TextEmbeddingRetriever _textEmbeddingRetriever;

}