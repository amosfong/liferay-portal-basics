/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.internal.servlet;

import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.fragment.helper.FragmentEntryLinkHelper;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.layout.helper.structure.LayoutStructureRulesHelper;
import com.liferay.layout.provider.LayoutStructureProvider;
import com.liferay.layout.taglib.internal.helper.LayoutClassedModelUsagesHelper;
import com.liferay.layout.util.LayoutsTree;
import com.liferay.portal.kernel.module.service.Snapshot;

import javax.servlet.ServletContext;

/**
 * @author Chema Balsas
 */
public class ServletContextUtil {

	public static FragmentEntryConfigurationParser
		getFragmentEntryConfigurationParser() {

		return _fragmentEntryConfigurationParserSnapshot.get();
	}

	public static FragmentEntryLinkHelper getFragmentEntryLinkHelper() {
		return _fragmentEntryLinkHelperSnapshot.get();
	}

	public static FragmentEntryProcessorHelper
		getFragmentEntryProcessorHelper() {

		return _fragmentEntryProcessorHelperSnapshot.get();
	}

	public static FragmentRendererController getFragmentRendererController() {
		return _fragmentRendererControllerSnapshot.get();
	}

	public static FrontendTokenDefinitionRegistry
		getFrontendTokenDefinitionRegistry() {

		return _frontendTokenDefinitionRegistrySnapshot.get();
	}

	public static LayoutClassedModelUsagesHelper
		getLayoutClassedModelUsagesHelper() {

		return _layoutClassedModelUsagesHelperSnapshot.get();
	}

	public static LayoutsTree getLayoutsTree() {
		return _layoutsTreeSnapshot.get();
	}

	public static LayoutStructureProvider getLayoutStructureHelper() {
		return _layoutStructureProviderSnapshot.get();
	}

	public static LayoutStructureRulesHelper getLayoutStructureRulesHelper() {
		return _layoutStructureRulesHelperSnapshot.get();
	}

	public static ServletContext getServletContext() {
		return _servletContextSnapshot.get();
	}

	private static final Snapshot<FragmentEntryConfigurationParser>
		_fragmentEntryConfigurationParserSnapshot = new Snapshot<>(
			ServletContextUtil.class, FragmentEntryConfigurationParser.class);
	private static final Snapshot<FragmentEntryLinkHelper>
		_fragmentEntryLinkHelperSnapshot = new Snapshot<>(
			ServletContextUtil.class, FragmentEntryLinkHelper.class);
	private static final Snapshot<FragmentEntryProcessorHelper>
		_fragmentEntryProcessorHelperSnapshot = new Snapshot<>(
			ServletContextUtil.class, FragmentEntryProcessorHelper.class);
	private static final Snapshot<FragmentRendererController>
		_fragmentRendererControllerSnapshot = new Snapshot<>(
			ServletContextUtil.class, FragmentRendererController.class);
	private static final Snapshot<FrontendTokenDefinitionRegistry>
		_frontendTokenDefinitionRegistrySnapshot = new Snapshot<>(
			ServletContextUtil.class, FrontendTokenDefinitionRegistry.class);
	private static final Snapshot<LayoutClassedModelUsagesHelper>
		_layoutClassedModelUsagesHelperSnapshot = new Snapshot<>(
			ServletContextUtil.class, LayoutClassedModelUsagesHelper.class);
	private static final Snapshot<LayoutsTree> _layoutsTreeSnapshot =
		new Snapshot<>(ServletContextUtil.class, LayoutsTree.class);
	private static final Snapshot<LayoutStructureProvider>
		_layoutStructureProviderSnapshot = new Snapshot<>(
			ServletContextUtil.class, LayoutStructureProvider.class);
	private static final Snapshot<LayoutStructureRulesHelper>
		_layoutStructureRulesHelperSnapshot = new Snapshot<>(
			ServletContextUtil.class, LayoutStructureRulesHelper.class);
	private static final Snapshot<ServletContext> _servletContextSnapshot =
		new Snapshot<>(
			ServletContextUtil.class, ServletContext.class,
			"(osgi.web.symbolicname=com.liferay.layout.taglib)");

}