/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.configuration.persistence.listener;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.opensearch2.configuration.OpenSearchConnectionConfiguration;
import com.liferay.portal.search.opensearch2.internal.connection.OpenSearchConnectionsHolder;

import java.util.Dictionary;
import java.util.ResourceBundle;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 * @author Petteri Karttunen
 */
@Component(
	property = "model.class.name=com.liferay.portal.search.opensearch.configuration.OpenSearchConnectionConfiguration",
	service = ConfigurationModelListener.class
)
public class OpenSearchConnectionConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onBeforeDelete(String pid)
		throws ConfigurationModelListenerException {

		try {
			_openSearchConnectionConfigurationWrapper.
				removeOpenSearchConnection(_getConnectionId(pid));
		}
		catch (Exception exception) {
			throw new ConfigurationModelListenerException(
				exception.getMessage(), OpenSearchConnectionConfiguration.class,
				getClass(), null);
		}
	}

	@Override
	public void onBeforeSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		try {
			String connectionId = StringUtil.unquote(
				(String)properties.get("connectionId"));

			_validateUniqueConnectionId(pid, connectionId);

			_validateNetworkHostAddresses(properties);
		}
		catch (Exception exception) {
			throw new ConfigurationModelListenerException(
				exception.getMessage(), OpenSearchConnectionConfiguration.class,
				getClass(), properties);
		}
	}

	@Reference
	protected ConfigurationAdmin configurationAdmin;

	private String _getConnectionId(String pid) throws Exception {
		Configuration configuration = configurationAdmin.getConfiguration(
			pid, StringPool.QUESTION);

		Dictionary<String, Object> properties = configuration.getProperties();

		if (properties != null) {
			return StringUtil.unquote((String)properties.get("connectionId"));
		}

		return null;
	}

	private String _getMessage(String key, Object... arguments) {
		try {
			return ResourceBundleUtil.getString(
				_getResourceBundle(), key, arguments);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	private ResourceBundle _getResourceBundle() {
		return ResourceBundleUtil.getBundle(
			"content.Language", LocaleThreadLocal.getThemeDisplayLocale(),
			getClass());
	}

	private void _validateNetworkHostAddresses(
			Dictionary<String, Object> properties)
		throws Exception {

		String[] networkHostAddresses = GetterUtil.getStringValues(
			properties.get("networkHostAddresses"));

		for (String networkHostAddress : networkHostAddresses) {
			if (!Validator.isBlank(networkHostAddress)) {
				return;
			}
		}

		_log.error("Unable to validate network host addresses");

		throw new Exception(
			_getMessage("please-set-at-least-one-network-host-address"));
	}

	private void _validateUniqueConnectionId(String pid, String connectionId)
		throws Exception {

		if (Validator.isBlank(connectionId)) {
			_log.error("Connection ID is blank");

			throw new Exception(_getMessage("please-set-a-connection-id"));
		}

		String filterString = String.format(
			"(&(service.factoryPid=%s)(connectionId=%s))",
			OpenSearchConnectionConfiguration.class.getName(), connectionId);

		Configuration[] configurations = configurationAdmin.listConfigurations(
			filterString);

		if (configurations == null) {
			String previousConnectionId = _getConnectionId(pid);

			if ((previousConnectionId != null) &&
				!previousConnectionId.equals(connectionId)) {

				_openSearchConnectionConfigurationWrapper.
					removeOpenSearchConnection(previousConnectionId);
			}

			return;
		}

		Configuration configuration = configurations[0];

		if (pid.equals(configuration.getPid())) {
			return;
		}

		_log.error(
			"There is already a connection with the ID: " + connectionId);

		throw new Exception(
			_getMessage(
				"there-is-already-a-connection-with-the-id-x", connectionId));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenSearchConnectionConfigurationModelListener.class);

	@Reference
	private OpenSearchConnectionsHolder
		_openSearchConnectionConfigurationWrapper;

}