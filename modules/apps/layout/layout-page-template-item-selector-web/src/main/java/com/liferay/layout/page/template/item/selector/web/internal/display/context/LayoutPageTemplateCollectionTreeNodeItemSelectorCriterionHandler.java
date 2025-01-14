/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.item.selector.web.internal.display.context;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;
import com.liferay.layout.page.template.item.selector.criterion.LayoutPageTemplateCollectionTreeNodeItemSelectorCriterion;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Bárbara Cabrera
 */
@Component(service = ItemSelectorCriterionHandler.class)
public class LayoutPageTemplateCollectionTreeNodeItemSelectorCriterionHandler
	extends BaseItemSelectorCriterionHandler
		<LayoutPageTemplateCollectionTreeNodeItemSelectorCriterion> {

	@Override
	public Class<LayoutPageTemplateCollectionTreeNodeItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return LayoutPageTemplateCollectionTreeNodeItemSelectorCriterion.class;
	}

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

	@Deactivate
	@Override
	protected void deactivate() {
		super.deactivate();
	}

}