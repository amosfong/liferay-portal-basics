/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.collection.contributor.navigation.bars;

import com.liferay.fragment.contributor.BaseFragmentCollectionContributor;
import com.liferay.fragment.contributor.FragmentCollectionContributor;
import com.liferay.site.navigation.constants.SiteNavigationMenuPortletKeys;

import javax.portlet.Portlet;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "fragment.collection.key=NAVIGATION_BARS",
	service = FragmentCollectionContributor.class
)
public class NavigationBarsFragmentCollectionContributor
	extends BaseFragmentCollectionContributor {

	@Override
	public String getFragmentCollectionKey() {
		return "NAVIGATION_BARS";
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(
		target = "(javax.portlet.name=" + SiteNavigationMenuPortletKeys.SITE_NAVIGATION_MENU + ")"
	)
	private Portlet _portlet;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.fragment.collection.contributor.navigation.bars)"
	)
	private ServletContext _servletContext;

}