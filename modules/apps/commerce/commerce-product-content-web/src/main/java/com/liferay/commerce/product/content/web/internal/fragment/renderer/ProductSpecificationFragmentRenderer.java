/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.content.web.internal.fragment.renderer;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueLocalService;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = FragmentRenderer.class)
public class ProductSpecificationFragmentRenderer implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "commerce-product";
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getClass());

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				StringUtil.read(
					getClass(),
					"/com/liferay/commerce/product/content/web/internal" +
						"/fragment/renderer/product_specification" +
							"/dependencies/configuration.json"));

			return _fragmentEntryConfigurationParser.translateConfiguration(
				jsonObject, resourceBundle);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}

			return StringPool.BLANK;
		}
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "product-specification");
	}

	@Override
	public boolean isSelectable(HttpServletRequest httpServletRequest) {
		return true;
	}

	@Override
	public void render(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(
				"/fragment/renderer/product_specification/page.jsp");

		Object infoItem = httpServletRequest.getAttribute(
			InfoDisplayWebKeys.INFO_ITEM);

		try {
			if (!(infoItem instanceof CPDefinition)) {
				if (_isEditMode(httpServletRequest)) {
					_printPortletMessageInfo(
						httpServletRequest, httpServletResponse,
						"the-product-specification-component-will-be-shown-" +
							"here");
				}

				return;
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			String label = StringPool.BLANK;

			CPDefinition cpDefinition = (CPDefinition)infoItem;

			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					_cpDefinitionSpecificationOptionValueLocalService.
						fetchCPDefinitionSpecificationOptionValue(
							cpDefinition.getCPDefinitionId(),
							GetterUtil.getString(
								_getConfigurationValue(
									fragmentRendererContext.
										getFragmentEntryLink(),
									"key")));

			if (cpDefinitionSpecificationOptionValue != null) {
				CPSpecificationOption cpSpecificationOption =
					cpDefinitionSpecificationOptionValue.
						getCPSpecificationOption();

				label = cpSpecificationOption.getTitle(
					themeDisplay.getLanguageId());
			}

			httpServletRequest.setAttribute(
				"liferay-commerce:product-specification:label", label);

			httpServletRequest.setAttribute(
				"liferay-commerce:product-specification:labelElementType",
				GetterUtil.getString(
					_getConfigurationValue(
						fragmentRendererContext.getFragmentEntryLink(),
						"labelElementType")));

			String namespace = (String)httpServletRequest.getAttribute(
				"liferay-commerce:product-specification:namespace");

			if (Validator.isNull(namespace)) {
				PortletDisplay portletDisplay =
					themeDisplay.getPortletDisplay();

				namespace = portletDisplay.getNamespace();

				httpServletRequest.setAttribute(
					"liferay-commerce:product-specification:namespace",
					namespace);
			}

			httpServletRequest.setAttribute(
				"liferay-commerce:product-specification:showLabel",
				GetterUtil.getBoolean(
					_getConfigurationValue(
						fragmentRendererContext.getFragmentEntryLink(),
						"showLabel")));

			String value = StringPool.BLANK;

			if (cpDefinitionSpecificationOptionValue != null) {
				value = cpDefinitionSpecificationOptionValue.getValue(
					themeDisplay.getLanguageId());
			}

			httpServletRequest.setAttribute(
				"liferay-commerce:product-specification:value", value);

			httpServletRequest.setAttribute(
				"liferay-commerce:product-specification:valueElementType",
				GetterUtil.getString(
					_getConfigurationValue(
						fragmentRendererContext.getFragmentEntryLink(),
						"valueElementType")));

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Object _getConfigurationValue(
		FragmentEntryLink fragmentEntryLink, String name) {

		return _fragmentEntryConfigurationParser.getFieldValue(
			fragmentEntryLink.getConfiguration(),
			fragmentEntryLink.getEditableValues(),
			LocaleUtil.getMostRelevantLocale(), name);
	}

	private boolean _isEditMode(HttpServletRequest httpServletRequest) {
		HttpServletRequest originalHttpServletRequest =
			_portal.getOriginalServletRequest(httpServletRequest);

		String layoutMode = ParamUtil.getString(
			originalHttpServletRequest, "p_l_mode", Constants.VIEW);

		if (layoutMode.equals(Constants.EDIT)) {
			return true;
		}

		return false;
	}

	private void _printPortletMessageInfo(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String message) {

		try {
			PrintWriter printWriter = httpServletResponse.getWriter();

			StringBundler sb = new StringBundler(3);

			sb.append("<div class=\"portlet-msg-info\">");

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			sb.append(themeDisplay.translate(message));

			sb.append("</div>");

			printWriter.write(sb.toString());
		}
		catch (IOException ioException) {
			if (_log.isDebugEnabled()) {
				_log.debug(ioException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductSpecificationFragmentRenderer.class);

	@Reference
	private CPDefinitionSpecificationOptionValueLocalService
		_cpDefinitionSpecificationOptionValueLocalService;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.content.web)"
	)
	private ServletContext _servletContext;

}