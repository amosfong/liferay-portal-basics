/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.engine.client.model;

/**
 * @author Matthew Kong
 */
public class PageVisit {

	public String getDescription() {
		return _description;
	}

	public String getTitle() {
		return _title;
	}

	public int getUniqueVisitsCount() {
		return _uniqueVisitsCount;
	}

	public String getUrl() {
		return _url;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUniqueVisitsCount(int uniqueVisitsCount) {
		_uniqueVisitsCount = uniqueVisitsCount;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String _description;
	private String _title;
	private int _uniqueVisitsCount;
	private String _url;

}