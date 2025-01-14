/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Bryan Engler
 */
@ExtendedObjectClassDefinition(
	category = "search", factoryInstanceLabelAttribute = "connectionId",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.search.opensearch2.configuration.OpenSearchConnectionConfiguration",
	localization = "content/Language",
	name = "opensearch-connection-configuration-name"
)
@ProviderType
public interface OpenSearchConnectionConfiguration {

	@Meta.AD(
		deflt = "false", description = "activate-or-deactivate-this-connection",
		name = "active", required = false
	)
	public boolean active();

	@Meta.AD(
		description = "connection-id-help[opensearch]", name = "connection-id",
		required = false
	)
	public String connectionId();

	@Meta.AD(
		deflt = "http://localhost:9200",
		description = "network-host-addresses-help",
		name = "network-host-addresses", required = false
	)
	public String[] networkHostAddresses();

	@Meta.AD(
		deflt = "false", description = "authentication-enabled-help",
		name = "authentication-enabled", required = false
	)
	public boolean authenticationEnabled();

	@Meta.AD(
		deflt = "elastic", description = "username-help", name = "username",
		required = false
	)
	public String username();

	@Meta.AD(
		description = "password-help", name = "password", required = false,
		type = Meta.Type.Password
	)
	public String password();

	@Meta.AD(
		deflt = "false", description = "http-ssl-enabled-help",
		name = "http-ssl-enabled", required = false
	)
	public boolean httpSSLEnabled();

	@Meta.AD(
		deflt = "75", description = "max-connections-help",
		name = "max-connections", required = false
	)
	public int maxConnections();

	@Meta.AD(
		deflt = "75", description = "max-connections-per-route-help",
		name = "max-connections-per-route", required = false
	)
	public int maxConnectionsPerRoute();

	@Meta.AD(
		deflt = "pkcs12", description = "truststore-type-help",
		name = "truststore-type", required = false
	)
	public String truststoreType();

	@Meta.AD(
		deflt = "/path/to/localhost.p12", description = "truststore-path-help",
		name = "truststore-path", required = false
	)
	public String truststorePath();

	@Meta.AD(
		description = "truststore-password-help", name = "truststore-password",
		required = false, type = Meta.Type.Password
	)
	public String truststorePassword();

	@Meta.AD(
		description = "set-the-proxy-host-to-be-used-for-the-client-connection",
		name = "proxy-host", required = false
	)
	public String proxyHost();

	@Meta.AD(
		deflt = "0",
		description = "set-the-proxy-port-to-be-used-for-the-client-connection",
		name = "proxy-port", required = false
	)
	public int proxyPort();

	@Meta.AD(
		description = "proxy-username-help", name = "proxy-username",
		required = false
	)
	public String proxyUserName();

	@Meta.AD(
		description = "set-the-password-for-connecting-to-the-proxy",
		name = "proxy-password", required = false, type = Meta.Type.Password
	)
	public String proxyPassword();

}