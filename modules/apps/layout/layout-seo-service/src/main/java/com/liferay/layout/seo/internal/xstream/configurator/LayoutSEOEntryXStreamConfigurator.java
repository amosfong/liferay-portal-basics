/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.internal.xstream.configurator;

import com.liferay.exportimport.kernel.xstream.XStreamAlias;
import com.liferay.exportimport.kernel.xstream.XStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamType;
import com.liferay.layout.seo.model.impl.LayoutSEOEntryImpl;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.xstream.configurator.XStreamConfigurator;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(service = XStreamConfigurator.class)
public class LayoutSEOEntryXStreamConfigurator implements XStreamConfigurator {

	@Override
	public List<XStreamType> getAllowedXStreamTypes() {
		return null;
	}

	@Override
	public List<XStreamAlias> getXStreamAliases() {
		return ListUtil.fromArray(_xStreamAliases);
	}

	@Override
	public List<XStreamConverter> getXStreamConverters() {
		return null;
	}

	@Activate
	protected void activate() {
		_xStreamAliases = new XStreamAlias[] {
			new XStreamAlias(LayoutSEOEntryImpl.class, "LayoutSEOEntry")
		};
	}

	private XStreamAlias[] _xStreamAliases;

}