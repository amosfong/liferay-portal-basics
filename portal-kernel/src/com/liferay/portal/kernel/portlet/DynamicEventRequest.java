/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.EventRequest;
import javax.portlet.filter.EventRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 * @see    DynamicActionRequest
 * @see    DynamicRenderRequest
 * @see    DynamicResourceRequest
 */
public class DynamicEventRequest extends EventRequestWrapper {

	public DynamicEventRequest(EventRequest eventRequest) {
		this(eventRequest, null, true);
	}

	public DynamicEventRequest(EventRequest eventRequest, boolean inherit) {
		this(eventRequest, null, inherit);
	}

	public DynamicEventRequest(
		EventRequest eventRequest, Map<String, String[]> params) {

		this(eventRequest, params, true);
	}

	public DynamicEventRequest(
		EventRequest eventRequest, Map<String, String[]> params,
		boolean inherit) {

		super(eventRequest);

		_inherit = inherit;

		_params = new HashMap<>();

		if (params != null) {
			_params.putAll(params);
		}

		if (_inherit && (eventRequest instanceof DynamicEventRequest)) {
			DynamicEventRequest dynamicEventRequest =
				(DynamicEventRequest)eventRequest;

			setRequest(dynamicEventRequest.getRequest());

			params = dynamicEventRequest.getDynamicParameterMap();

			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String name = entry.getKey();
				String[] oldValues = entry.getValue();

				String[] curValues = _params.get(name);

				if (curValues == null) {
					_params.put(name, oldValues);
				}
				else {
					String[] newValues = ArrayUtil.append(oldValues, curValues);

					_params.put(name, newValues);
				}
			}
		}
	}

	public Map<String, String[]> getDynamicParameterMap() {
		return _params;
	}

	@Override
	public String getParameter(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameter(name);
		}

		if (ArrayUtil.isNotEmpty(values)) {
			return values[0];
		}

		return null;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<>();

		if (_inherit) {
			map.putAll(super.getParameterMap());
		}

		map.putAll(_params);

		return map;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> names = new LinkedHashSet<>();

		if (_inherit) {
			Enumeration<String> enumeration = super.getParameterNames();

			while (enumeration.hasMoreElements()) {
				names.add(enumeration.nextElement());
			}
		}

		names.addAll(_params.keySet());

		return Collections.enumeration(names);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameterValues(name);
		}

		return values;
	}

	public void setParameter(String name, String value) {
		_params.put(name, new String[] {value});
	}

	public void setParameterValues(String name, String[] values) {
		_params.put(name, values);
	}

	private final boolean _inherit;
	private final Map<String, String[]> _params;

}