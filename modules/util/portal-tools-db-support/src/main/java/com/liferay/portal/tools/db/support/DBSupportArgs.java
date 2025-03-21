/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.db.support;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Objects;
import java.util.Properties;

/**
 * @author Andrea Di Giorgi
 */
public class DBSupportArgs {

	public String getPassword() throws IOException {
		Properties properties = _readProperties();

		return properties.getProperty("jdbc.default.password", _password);
	}

	public File getPropertiesFile() {
		return _propertiesFile;
	}

	public String getUrl() throws IOException {
		Properties properties = _readProperties();

		return properties.getProperty("jdbc.default.url", _url);
	}

	public String getUserName() throws IOException {
		Properties properties = _readProperties();

		return properties.getProperty("jdbc.default.username", _userName);
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setPropertiesFile(File propertiesFile) throws IOException {
		_propertiesFile = propertiesFile;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	protected boolean isHelp() {
		return _help;
	}

	private Properties _readProperties() throws IOException {
		if (Objects.equals(_cachedPropertiesFile, _propertiesFile)) {
			return _cachedProperties;
		}

		_cachedProperties.clear();

		if (_propertiesFile != null) {
			try (FileInputStream fileInputStream = new FileInputStream(
					_propertiesFile)) {

				_cachedProperties.load(fileInputStream);
			}
		}

		_cachedPropertiesFile = _propertiesFile;

		return _cachedProperties;
	}

	private final Properties _cachedProperties = new Properties();
	private File _cachedPropertiesFile;

	@Parameter(
		description = "Print this message.", help = true,
		names = {"-h", "--help"}
	)
	private boolean _help;

	@Parameter(
		description = "The user password for connecting to the Liferay database.",
		names = {"-p", "--password"}, password = true
	)
	private String _password;

	@Parameter(
		converter = FileConverter.class,
		description = "The portal-ext.properties file which contains the JDBC settings for connecting to the Liferay database.",
		names = {"-f", "--properties-file"}
	)
	private File _propertiesFile;

	@Parameter(
		description = "The JDBC URL for connecting to the Liferay database.",
		names = {"-u", "--url"}
	)
	private String _url;

	@Parameter(
		description = "The user name for connecting to the Liferay database.",
		names = {"-s", "--user-name"}
	)
	private String _userName;

}