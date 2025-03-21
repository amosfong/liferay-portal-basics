/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.webdav;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.text.Format;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class BaseResourceImpl implements Resource {

	public BaseResourceImpl(String parentPath, long name, long displayName) {
		this(parentPath, String.valueOf(name), String.valueOf(displayName));
	}

	public BaseResourceImpl(String parentPath, long name, String displayName) {
		this(parentPath, String.valueOf(name), displayName);
	}

	public BaseResourceImpl(
		String parentPath, String name, String displayName) {

		this(parentPath, name, displayName, null, null);
	}

	public BaseResourceImpl(
		String parentPath, String name, String displayName, Date createDate,
		Date modifiedDate) {

		this(parentPath, name, displayName, createDate, modifiedDate, 0);
	}

	public BaseResourceImpl(
		String parentPath, String name, String displayName, Date createDate,
		Date modifiedDate, long size) {

		_displayName = displayName;
		_size = size;

		String href = HttpComponentsUtil.encodePath(parentPath);

		if (Validator.isNotNull(name)) {
			href += StringPool.SLASH + URLCodec.encodeURL(name, true);
		}

		_href = href;

		if (createDate == null) {
			_createDate = new Date();
		}
		else {
			_createDate = createDate;
		}

		if (modifiedDate == null) {
			_modifiedDate = new Date();
		}
		else {
			_modifiedDate = modifiedDate;
		}
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public InputStream getContentAsStream() throws WebDAVException {
		return null;
	}

	@Override
	public String getContentType() {
		return ContentTypes.HTTPD_UNIX_DIRECTORY;
	}

	@Override
	public String getCreateDateString() {
		return _createDateFormat.format(_createDate);
	}

	@Override
	public String getDisplayName() {
		return _displayName;
	}

	@Override
	public String getHREF() {
		return _href;
	}

	@Override
	public Lock getLock() {
		return null;
	}

	@Override
	public Object getModel() {
		return _model;
	}

	@Override
	public String getModifiedDate() {
		return _modifiedDateFormat.format(_modifiedDate);
	}

	@Override
	public long getPrimaryKey() {
		return _primaryKey;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public boolean isCollection() {
		return true;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	@Override
	public void setModel(Object model) {
		_model = model;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		_primaryKey = primaryKey;
	}

	private static final Format _createDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'", LocaleUtil.US, TimeZoneUtil.GMT);
	private static final Format _modifiedDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss zzz", LocaleUtil.US, TimeZoneUtil.GMT);

	private String _className;
	private final Date _createDate;
	private final String _displayName;
	private final String _href;
	private Object _model;
	private final Date _modifiedDate;
	private long _primaryKey = -1;
	private final long _size;

}