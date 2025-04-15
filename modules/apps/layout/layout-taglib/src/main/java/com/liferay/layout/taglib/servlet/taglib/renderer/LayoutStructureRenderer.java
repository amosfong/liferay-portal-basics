/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.servlet.taglib.renderer;

import com.liferay.fragment.constants.FragmentWebKeys;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.DefaultFragmentRendererContext;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.ColTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.ContainerTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.RowTag;
import com.liferay.frontend.taglib.servlet.taglib.ComponentTag;
import com.liferay.layout.responsive.ResponsiveLayoutStructureUtil;
import com.liferay.layout.taglib.internal.display.context.RenderLayoutStructureDisplayContext;
import com.liferay.layout.taglib.internal.servlet.ServletContextUtil;
import com.liferay.layout.util.structure.ColumnLayoutStructureItem;
import com.liferay.layout.util.structure.ContainerStyledLayoutStructureItem;
import com.liferay.layout.util.structure.DropZoneLayoutStructureItem;
import com.liferay.layout.util.structure.FormStepContainerStyledLayoutStructureItem;
import com.liferay.layout.util.structure.FormStyledLayoutStructureItem;
import com.liferay.layout.util.structure.FragmentStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.layout.util.structure.RowStyledLayoutStructureItem;
import com.liferay.layout.util.structure.collection.EmptyCollectionOptions;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTemplate;
import com.liferay.portal.kernel.model.LayoutTemplateConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.layoutconfiguration.util.RuntimePageUtil;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author Mikel Lorza
 */
public class LayoutStructureRenderer {

	public LayoutStructureRenderer(
		HttpServletRequest httpServletRequest, LayoutStructure layoutStructure,
		String mainItemId, String mode, PageContext pageContext,
		boolean renderActionHandler, boolean showPreview) {

		_httpServletRequest = httpServletRequest;
		_layoutStructure = layoutStructure;
		_pageContext = pageContext;
		_renderActionHandler = renderActionHandler;

		_renderLayoutStructureDisplayContext =
			new RenderLayoutStructureDisplayContext(
				_httpServletRequest, _layoutStructure, mainItemId, mode,
				showPreview);
		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<LayoutStructureItemRenderTime>
		getLayoutStructureItemRenderTimes() {

		return _layoutStructureItemRenderTimes;
	}

	public void render() throws Exception {
		_renderLayoutStructure(
			_renderLayoutStructureDisplayContext.getMainChildrenItemIds());
	}

	public class LayoutStructureItemRenderTime {

		public LayoutStructureItemRenderTime(
			LayoutStructureItem layoutStructureItem, long renderTime) {

			_layoutStructureItem = layoutStructureItem;
			_renderTime = renderTime;
		}

		public LayoutStructureItem getLayoutStructureItem() {
			return _layoutStructureItem;
		}

		public long getRenderTime() {
			return _renderTime;
		}

		private final LayoutStructureItem _layoutStructureItem;
		private final long _renderTime;

	}

	private LayoutTypePortlet _getLayoutTypePortlet(
		Layout layout, LayoutTypePortlet layoutTypePortlet, String themeId) {

		String layoutTemplateId = layoutTypePortlet.getLayoutTemplateId();

		if (Validator.isNull(layoutTemplateId)) {
			return layoutTypePortlet;
		}

		LayoutTemplate layoutTemplate =
			LayoutTemplateLocalServiceUtil.getLayoutTemplate(
				layoutTemplateId, false, themeId);

		if (layoutTemplate != null) {
			return layoutTypePortlet;
		}

		layoutTypePortlet.setLayoutTemplateId(
			layout.getUserId(), PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID);

		return layoutTypePortlet;
	}

	private boolean _hasAddPermission(String className) {
		return true;
	}

	private void _renderColumnLayoutStructureItem(
			ColumnLayoutStructureItem columnLayoutStructureItem)
		throws Exception {

		RowStyledLayoutStructureItem rowStyledLayoutStructureItem =
			(RowStyledLayoutStructureItem)
				_layoutStructure.getLayoutStructureItem(
					columnLayoutStructureItem.getParentItemId());

		ColTag colTag = new ColTag();

		colTag.setCssClass(
			ResponsiveLayoutStructureUtil.getColumnCssClass(
				columnLayoutStructureItem, rowStyledLayoutStructureItem));
		colTag.setPageContext(_pageContext);

		colTag.doStartTag();

		_renderLayoutStructure(columnLayoutStructureItem.getChildrenItemIds());

		colTag.doEndTag();
	}

	private void _renderComponent(
			String componentId, Map<String, Object> context, String module)
		throws Exception {

		ComponentTag componentTag = new ComponentTag();

		componentTag.setComponentId(componentId);
		componentTag.setContext(context);
		componentTag.setModule(module);
		componentTag.setPageContext(_pageContext);
		componentTag.setServletContext(ServletContextUtil.getServletContext());

		componentTag.doStartTag();

		componentTag.doEndTag();
	}

	private void _renderContainerStyledLayoutStructureItem(
			ContainerStyledLayoutStructureItem
				containerStyledLayoutStructureItem)
		throws Exception {

		JspWriter jspWriter = _pageContext.getOut();

		String containerLinkHref =
			_renderLayoutStructureDisplayContext.getContainerLinkHref(
				containerStyledLayoutStructureItem);

		if (Validator.isNotNull(containerLinkHref)) {
			jspWriter.write("<a href=\"");
			jspWriter.write(HtmlUtil.escapeAttribute(containerLinkHref));
			jspWriter.write("\"style=\"color: inherit; text-decoration: ");
			jspWriter.write("none;\" target=\"");
			jspWriter.write(
				_renderLayoutStructureDisplayContext.getContainerLinkTarget(
					containerStyledLayoutStructureItem));
			jspWriter.write("\">");
		}

		String htmlTag = containerStyledLayoutStructureItem.getHtmlTag();

		if (Validator.isNull(htmlTag)) {
			htmlTag = "div";
		}

		jspWriter.write(StringPool.LESS_THAN);
		jspWriter.write(htmlTag);
		jspWriter.write(" class=\"");
		jspWriter.write(containerStyledLayoutStructureItem.getUniqueCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(containerStyledLayoutStructureItem.getCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(
			containerStyledLayoutStructureItem.getStyledCssClasses());

		String colorCssClasses =
			_renderLayoutStructureDisplayContext.getColorCssClasses(
				containerStyledLayoutStructureItem);

		if (Validator.isNotNull(colorCssClasses)) {
			jspWriter.write(StringPool.SPACE);
			jspWriter.write(colorCssClasses);
		}

		if (Objects.equals(
				containerStyledLayoutStructureItem.getWidthType(), "fixed")) {

			jspWriter.write(" container-fluid container-fluid-max-xl");
		}

		if (!Objects.equals(
				containerStyledLayoutStructureItem.getDisplay(), "none")) {

			if (Objects.equals(
					containerStyledLayoutStructureItem.getContentDisplay(),
					"flex-column")) {

				jspWriter.write(" d-flex flex-column");
			}
			else if (Objects.equals(
						containerStyledLayoutStructureItem.getContentDisplay(),
						"flex-row")) {

				jspWriter.write(" d-flex flex-row");
			}

			String align = containerStyledLayoutStructureItem.getAlign();

			if (Validator.isNotNull(align)) {
				jspWriter.append(StringPool.SPACE);
				jspWriter.append(align);
			}

			String flexWrap = containerStyledLayoutStructureItem.getFlexWrap();

			if (Validator.isNotNull(flexWrap)) {
				jspWriter.append(StringPool.SPACE);
				jspWriter.append(flexWrap);
			}

			String justify = containerStyledLayoutStructureItem.getJustify();

			if (Validator.isNotNull(justify)) {
				jspWriter.append(StringPool.SPACE);
				jspWriter.append(justify);
			}
		}

		jspWriter.write("\" style=\"");

		String contentVisibility =
			containerStyledLayoutStructureItem.getContentVisibility();

		if (Validator.isNotNull(contentVisibility)) {
			jspWriter.append("content-visibility:");
			jspWriter.append(contentVisibility);
			jspWriter.append(StringPool.SEMICOLON);
		}

		jspWriter.write(
			_renderLayoutStructureDisplayContext.getStyle(
				containerStyledLayoutStructureItem));
		jspWriter.write("\">");

		_renderLayoutStructure(
			containerStyledLayoutStructureItem.getChildrenItemIds());

		jspWriter.write("</");
		jspWriter.write(htmlTag);
		jspWriter.write(StringPool.GREATER_THAN);

		if (Validator.isNotNull(containerLinkHref)) {
			jspWriter.write("</a>");
		}
	}

	private void _renderDropZoneLayoutStructureItem(
			LayoutStructureItem layoutStructureItem)
		throws Exception {

		Layout layout = _themeDisplay.getLayout();

		LayoutTypePortlet layoutTypePortlet =
			_themeDisplay.getLayoutTypePortlet();

		String ppid = ParamUtil.getString(_httpServletRequest, "p_p_id");

		if (layoutTypePortlet.hasStateMax() && Validator.isNotNull(ppid)) {
			String templateContent = LayoutTemplateLocalServiceUtil.getContent(
				"max", true, _themeDisplay.getThemeId());

			if (Validator.isNotNull(templateContent)) {
				HttpServletRequest originalHttpServletRequest =
					(HttpServletRequest)_httpServletRequest.getAttribute(
						"ORIGINAL_HTTP_SERVLET_REQUEST");

				if (originalHttpServletRequest == null) {
					originalHttpServletRequest = _httpServletRequest;
				}

				List<String> ppids = StringUtil.split(
					layoutTypePortlet.getStateMax());
				String templateId =
					_themeDisplay.getThemeId() +
						LayoutTemplateConstants.STANDARD_SEPARATOR + "max";

				RuntimePageUtil.processTemplate(
					originalHttpServletRequest,
					(HttpServletResponse)_pageContext.getResponse(),
					ppids.get(0), templateId, templateContent,
					LayoutTemplateLocalServiceUtil.getLangType(
						"max", true, _themeDisplay.getThemeId()));
			}
		}
		else if (Objects.equals(
					layout.getType(), LayoutConstants.TYPE_PORTLET)) {

			layoutTypePortlet = _getLayoutTypePortlet(
				layout, _themeDisplay.getLayoutTypePortlet(),
				_themeDisplay.getThemeId());

			String layoutTemplateId = layoutTypePortlet.getLayoutTemplateId();

			if (Validator.isNull(layoutTemplateId)) {
				layoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
			}

			LayoutTemplate layoutTemplate =
				LayoutTemplateLocalServiceUtil.getLayoutTemplate(
					layoutTemplateId, false, _themeDisplay.getThemeId());

			String themeId = _themeDisplay.getThemeId();

			if (layoutTemplate != null) {
				themeId = layoutTemplate.getThemeId();
			}

			String templateContent = LayoutTemplateLocalServiceUtil.getContent(
				layoutTypePortlet.getLayoutTemplateId(), false,
				_themeDisplay.getThemeId());

			if (Validator.isNotNull(templateContent)) {
				HttpServletRequest originalHttpServletRequest =
					(HttpServletRequest)_httpServletRequest.getAttribute(
						"ORIGINAL_HTTP_SERVLET_REQUEST");

				String templateId =
					themeId + LayoutTemplateConstants.CUSTOM_SEPARATOR +
						layoutTypePortlet.getLayoutTemplateId();

				RuntimePageUtil.processTemplate(
					originalHttpServletRequest,
					(HttpServletResponse)_pageContext.getResponse(), null,
					templateId, templateContent,
					LayoutTemplateLocalServiceUtil.getLangType(
						layoutTypePortlet.getLayoutTemplateId(), false,
						_themeDisplay.getThemeId()));
			}
		}
		else {
			JspWriter jspWriter = _pageContext.getOut();

			if (Objects.equals(
					_renderLayoutStructureDisplayContext.getLayoutMode(),
					Constants.VIEW)) {

				jspWriter.write("<div class=\"layout-content portlet-layout\"");
				jspWriter.write("id=\"main-content\" role=\"main\">");
			}

			_renderLayoutStructure(layoutStructureItem.getChildrenItemIds());

			if (Objects.equals(
					_renderLayoutStructureDisplayContext.getLayoutMode(),
					Constants.VIEW)) {

				jspWriter.write("</div>");
			}
		}
	}

	private void _renderEmptyState(
			EmptyCollectionOptions emptyCollectionOptions, JspWriter jspWriter)
		throws Exception {

		if ((emptyCollectionOptions != null) &&
			!GetterUtil.getBoolean(
				emptyCollectionOptions.isDisplayMessage(), true)) {

			return;
		}

		jspWriter.write("<div class=\"c-empty-state\">");
		jspWriter.write("<div class=\"c-empty-state-text\">");

		String message = LanguageUtil.get(
			_httpServletRequest, "no-results-found");

		if ((emptyCollectionOptions != null) &&
			(emptyCollectionOptions.getMessage() != null)) {

			Map<String, String> messageMap =
				emptyCollectionOptions.getMessage();

			String customMessage = messageMap.get(
				String.valueOf(_themeDisplay.getLocale()));

			if (customMessage != null) {
				message = customMessage;
			}
		}

		jspWriter.write(message);

		jspWriter.write("</div></div>");
	}

	private void _renderFormStepContainerStyledLayoutStructureItem(
			FormStepContainerStyledLayoutStructureItem
				formStepContainerStyledLayoutStructureItem)
		throws Exception {

		JspWriter jspWriter = _pageContext.getOut();

		jspWriter.write("<div class=\"");
		jspWriter.write(
			formStepContainerStyledLayoutStructureItem.getUniqueCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(
			formStepContainerStyledLayoutStructureItem.getCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(
			formStepContainerStyledLayoutStructureItem.getStyledCssClasses());
		jspWriter.write("\" style=\"");

		jspWriter.write(
			_renderLayoutStructureDisplayContext.getStyle(
				formStepContainerStyledLayoutStructureItem));
		jspWriter.write("\">");

		List<String> childrenItemIds =
			formStepContainerStyledLayoutStructureItem.getChildrenItemIds();

		for (int i = 0; i < childrenItemIds.size(); i++) {
			jspWriter.write("<div");

			if (i != 0) {
				jspWriter.write(" class=\"d-none\"");
			}

			jspWriter.write(" data-step-index=\"");
			jspWriter.write(String.valueOf(i));
			jspWriter.write(StringPool.QUOTE);

			jspWriter.write(StringPool.GREATER_THAN);

			LayoutStructureItem layoutStructureItem =
				_layoutStructure.getLayoutStructureItem(childrenItemIds.get(i));

			_renderLayoutStructure(layoutStructureItem.getChildrenItemIds());

			jspWriter.write("</div>");
		}

		jspWriter.write("</div>");

		_renderComponent(
			"FormStepComponent" +
				formStepContainerStyledLayoutStructureItem.getItemId(),
			HashMapBuilder.<String, Object>put(
				"formId",
				formStepContainerStyledLayoutStructureItem.getParentItemId()
			).build(),
			"{FormStepHandler} from layout-taglib");
	}

	private void _renderFormStyledLayoutStructureItem(
			FormStyledLayoutStructureItem formStyledLayoutStructureItem)
		throws Exception {
	}

	private void _renderFormStyledLayoutStructureItemSuccessMessage(
			FormStyledLayoutStructureItem formStyledLayoutStructureItem)
		throws Exception {

		JspWriter jspWriter = _pageContext.getOut();

		jspWriter.write("<div class=\"bg-white font-weight-semi-bold ");
		jspWriter.write("p-5 text-3 text-center text-secondary\">");
		jspWriter.write(
			_renderLayoutStructureDisplayContext.getSuccessMessage(
				formStyledLayoutStructureItem));
		jspWriter.write("</div>");

		SessionMessages.remove(
			_httpServletRequest, formStyledLayoutStructureItem.getItemId());
	}

	private void _renderFragmentStyledLayoutStructureItem(
			FragmentStyledLayoutStructureItem fragmentStyledLayoutStructureItem)
		throws Exception {

		JspWriter jspWriter = _pageContext.getOut();

		Layout layout = _themeDisplay.getLayout();

		if (Objects.equals(layout.getType(), LayoutConstants.TYPE_PORTLET)) {
			jspWriter.write("<div class=\"master-layout-fragment\">");
		}

		if (fragmentStyledLayoutStructureItem.getFragmentEntryLinkId() > 0) {
			FragmentEntryLink fragmentEntryLink =
				FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(
					fragmentStyledLayoutStructureItem.getFragmentEntryLinkId());

			if (fragmentEntryLink != null) {
				DefaultFragmentRendererContext defaultFragmentRendererContext =
					_renderLayoutStructureDisplayContext.
						getDefaultFragmentRendererContext(
							fragmentEntryLink,
							fragmentStyledLayoutStructureItem.getItemId());

				FragmentRendererController fragmentRendererController =
					ServletContextUtil.getFragmentRendererController();

				HttpServletResponse httpServletResponse =
					(HttpServletResponse)_pageContext.getResponse();

				// LPS-164462 Call render before getting attribute value

				String html = fragmentRendererController.render(
					defaultFragmentRendererContext, _httpServletRequest,
					httpServletResponse);

				if (GetterUtil.getBoolean(
						_httpServletRequest.getAttribute(
							FragmentWebKeys.
								ACCESS_ALLOWED_TO_FRAGMENT_ENTRY_LINK_ID +
									fragmentEntryLink.getFragmentEntryLinkId()),
						true)) {

					_write(
						fragmentEntryLink, fragmentStyledLayoutStructureItem,
						jspWriter);
				}
				else {
					jspWriter.write("<div>");
				}

				jspWriter.write(html);
				jspWriter.write("</div>");
			}
		}

		if (Objects.equals(layout.getType(), LayoutConstants.TYPE_PORTLET)) {
			jspWriter.write("</div>");
		}
	}

	private void _renderLayoutStructure(List<String> childrenItemIds)
		throws Exception {

		Set<String> hiddenItemIds =
			_renderLayoutStructureDisplayContext.getHiddenItemIds();

		for (String childrenItemId : childrenItemIds) {
			LayoutStructureItem layoutStructureItem =
				_layoutStructure.getLayoutStructureItem(childrenItemId);

			if (hiddenItemIds.contains(childrenItemId)) {
				continue;
			}

			long start = System.currentTimeMillis();

			if (layoutStructureItem instanceof ColumnLayoutStructureItem) {
				_renderColumnLayoutStructureItem(
					(ColumnLayoutStructureItem)layoutStructureItem);
			}
			else if (layoutStructureItem instanceof
						ContainerStyledLayoutStructureItem) {

				ContainerStyledLayoutStructureItem
					containerStyledLayoutStructureItem =
						(ContainerStyledLayoutStructureItem)layoutStructureItem;

				if (Objects.equals(
						_renderLayoutStructureDisplayContext.getLayoutMode(),
						Constants.SEARCH) &&
					!containerStyledLayoutStructureItem.isIndexed()) {

					continue;
				}

				_renderContainerStyledLayoutStructureItem(
					containerStyledLayoutStructureItem);
			}
			else if (layoutStructureItem instanceof
						DropZoneLayoutStructureItem) {

				_renderDropZoneLayoutStructureItem(layoutStructureItem);
			}
			else if (layoutStructureItem instanceof
						FormStepContainerStyledLayoutStructureItem) {

				_renderFormStepContainerStyledLayoutStructureItem(
					(FormStepContainerStyledLayoutStructureItem)
						layoutStructureItem);
			}
			else if (layoutStructureItem instanceof
						FormStyledLayoutStructureItem) {

				FormStyledLayoutStructureItem formStyledLayoutStructureItem =
					(FormStyledLayoutStructureItem)layoutStructureItem;

				if (Objects.equals(
						_renderLayoutStructureDisplayContext.getLayoutMode(),
						Constants.SEARCH) &&
					!formStyledLayoutStructureItem.isIndexed()) {

					continue;
				}

				if (SessionMessages.contains(
						_httpServletRequest,
						formStyledLayoutStructureItem.getItemId())) {

					_renderFormStyledLayoutStructureItemSuccessMessage(
						formStyledLayoutStructureItem);
				}
				else {
					_renderFormStyledLayoutStructureItem(
						formStyledLayoutStructureItem);
				}
			}
			else if (layoutStructureItem instanceof
						FragmentStyledLayoutStructureItem) {

				FragmentStyledLayoutStructureItem
					fragmentStyledLayoutStructureItem =
						(FragmentStyledLayoutStructureItem)layoutStructureItem;

				if (Objects.equals(
						_renderLayoutStructureDisplayContext.getLayoutMode(),
						Constants.SEARCH) &&
					!fragmentStyledLayoutStructureItem.isIndexed()) {

					continue;
				}

				_renderFragmentStyledLayoutStructureItem(
					fragmentStyledLayoutStructureItem);
			}
			else if (layoutStructureItem instanceof
						RowStyledLayoutStructureItem) {

				RowStyledLayoutStructureItem rowStyledLayoutStructureItem =
					(RowStyledLayoutStructureItem)layoutStructureItem;

				if (Objects.equals(
						_renderLayoutStructureDisplayContext.getLayoutMode(),
						Constants.SEARCH) &&
					!rowStyledLayoutStructureItem.isIndexed()) {

					continue;
				}

				_renderRowStyledLayoutStructureItem(
					rowStyledLayoutStructureItem);
			}
			else {
				_renderLayoutStructure(
					layoutStructureItem.getChildrenItemIds());
			}

			_layoutStructureItemRenderTimes.add(
				new LayoutStructureItemRenderTime(
					layoutStructureItem, System.currentTimeMillis() - start));
		}
	}

	private void _renderRowStyledLayoutStructureItem(
			RowStyledLayoutStructureItem rowStyledLayoutStructureItem)
		throws Exception {

		JspWriter jspWriter = _pageContext.getOut();

		jspWriter.write("<div class=\"");
		jspWriter.write(rowStyledLayoutStructureItem.getUniqueCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(rowStyledLayoutStructureItem.getCssClass());
		jspWriter.write(StringPool.SPACE);
		jspWriter.write(rowStyledLayoutStructureItem.getStyledCssClasses());
		jspWriter.write("\" style=\"");
		jspWriter.write(
			_renderLayoutStructureDisplayContext.getStyle(
				rowStyledLayoutStructureItem));
		jspWriter.write("\">");

		if (_renderLayoutStructureDisplayContext.isIncludeContainer(
				rowStyledLayoutStructureItem)) {

			ContainerTag containerTag = new ContainerTag();

			containerTag.setCssClass("p-0");
			containerTag.setFluid(true);
			containerTag.setPageContext(_pageContext);

			containerTag.doStartTag();

			RowTag rowTag = new RowTag();

			rowTag.setCssClass(
				ResponsiveLayoutStructureUtil.getRowCssClass(
					rowStyledLayoutStructureItem));
			rowTag.setPageContext(_pageContext);

			rowTag.doStartTag();

			_renderLayoutStructure(
				rowStyledLayoutStructureItem.getChildrenItemIds());

			rowTag.doEndTag();

			containerTag.doEndTag();
		}
		else {
			RowTag rowTag = new RowTag();

			rowTag.setCssClass(
				ResponsiveLayoutStructureUtil.getRowCssClass(
					rowStyledLayoutStructureItem));
			rowTag.setPageContext(_pageContext);

			rowTag.doStartTag();

			_renderLayoutStructure(
				rowStyledLayoutStructureItem.getChildrenItemIds());

			rowTag.doEndTag();
		}

		jspWriter.write("</div>");
	}

	private void _write(
			FragmentEntryLink fragmentEntryLink,
			FragmentStyledLayoutStructureItem fragmentStyledLayoutStructureItem,
			JspWriter jspWriter)
		throws Exception {

		jspWriter.write("<div class=\"");

		if (!_renderLayoutStructureDisplayContext.includeCommonStyles(
				fragmentEntryLink)) {

			jspWriter.write(
				fragmentStyledLayoutStructureItem.getFragmentEntryLinkCssClass(
					fragmentEntryLink));
			jspWriter.write(StringPool.SPACE);
			jspWriter.write(
				fragmentStyledLayoutStructureItem.getUniqueCssClass());
			jspWriter.write(StringPool.SPACE);
			jspWriter.write(
				fragmentStyledLayoutStructureItem.getStyledCssClasses());
		}

		String colorCssClasses =
			_renderLayoutStructureDisplayContext.getColorCssClasses(
				fragmentStyledLayoutStructureItem);

		if (Validator.isNotNull(colorCssClasses)) {
			jspWriter.write(StringPool.SPACE);
			jspWriter.write(colorCssClasses);
		}

		jspWriter.write("\" style=\"");
		jspWriter.write(
			_renderLayoutStructureDisplayContext.getStyle(
				fragmentStyledLayoutStructureItem));
		jspWriter.write("\">");
	}

	private final HttpServletRequest _httpServletRequest;
	private final LayoutStructure _layoutStructure;
	private final List<LayoutStructureItemRenderTime>
		_layoutStructureItemRenderTimes = new ArrayList<>();
	private final PageContext _pageContext;
	private final boolean _renderActionHandler;
	private final RenderLayoutStructureDisplayContext
		_renderLayoutStructureDisplayContext;
	private final ThemeDisplay _themeDisplay;

}