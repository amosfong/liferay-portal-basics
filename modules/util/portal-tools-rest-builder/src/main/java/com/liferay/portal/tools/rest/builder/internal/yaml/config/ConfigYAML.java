/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.rest.builder.internal.yaml.config;

/**
 * @author Peter Shin
 */
public class ConfigYAML {

	public String getApiDir() {
		return _apiDir;
	}

	public String getApiPackagePath() {
		return _apiPackagePath;
	}

	public Application getApplication() {
		return _application;
	}

	public String getAuthor() {
		return _author;
	}

	public String getClientDir() {
		return _clientDir;
	}

	public String getClientMavenGroupId() {
		return _clientMavenGroupId;
	}

	public int getCompatibilityVersion() {
		return _compatibilityVersion;
	}

	public String getGraphQLNamespace() {
		return _graphQLNamespace;
	}

	public String getImplDir() {
		return _implDir;
	}

	public String getLicenseName() {
		return _licenseName;
	}

	public String getLicenseURL() {
		return _licenseURL;
	}

	public String getResourceApplicationSelect() {
		return _resourceApplicationSelect;
	}

	public String getTestDir() {
		return _testDir;
	}

	public boolean isChangeTrackingEnabled() {
		return _changeTrackingEnabled;
	}

	public boolean isForceClientVersionDescription() {
		return _forceClientVersionDescription;
	}

	public boolean isForcePredictableContentApplicationXML() {
		return _forcePredictableContentApplicationXML;
	}

	public boolean isForcePredictableOperationId() {
		return _forcePredictableOperationId;
	}

	public boolean isForcePredictableSchemaPropertyName() {
		return _forcePredictableSchemaPropertyName;
	}

	public boolean isGenerateActionProviders() {
		return _generateActionProviders;
	}

	public boolean isGenerateBatch() {
		return _generateBatch;
	}

	public boolean isGenerateClientJS() {
		return _generateClientJS;
	}

	public boolean isGenerateGraphQL() {
		return _generateGraphQL;
	}

	public boolean isGenerateOpenAPI() {
		return _generateOpenAPI;
	}

	public boolean isGenerateREST() {
		return _generateREST;
	}

	public boolean isLiferayEnterpriseApp() {
		return _liferayEnterpriseApp;
	}

	public boolean isWarningsEnabled() {
		return _warningsEnabled;
	}

	public void setApiDir(String apiDir) {
		_apiDir = apiDir;
	}

	public void setApiPackagePath(String apiPackagePath) {
		_apiPackagePath = apiPackagePath;
	}

	public void setApplication(Application application) {
		_application = application;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public void setChangeTrackingEnabled(boolean changeTrackingEnabled) {
		_changeTrackingEnabled = changeTrackingEnabled;
	}

	public void setClientDir(String clientDir) {
		_clientDir = clientDir;
	}

	public void setClientMavenGroupId(String clientMavenGroupId) {
		_clientMavenGroupId = clientMavenGroupId;
	}

	public void setCompatibilityVersion(int compatibilityVersion) {
		_compatibilityVersion = compatibilityVersion;
	}

	public void setForceClientVersionDescription(
		boolean forceClientVersionDescription) {

		_forceClientVersionDescription = forceClientVersionDescription;
	}

	public void setForcePredictableContentApplicationXML(
		boolean forcePredictableContentApplicationXML) {

		_forcePredictableContentApplicationXML =
			forcePredictableContentApplicationXML;
	}

	public void setForcePredictableOperationId(
		boolean forcePredictableOperationId) {

		_forcePredictableOperationId = forcePredictableOperationId;
	}

	public void setForcePredictableSchemaPropertyName(
		boolean forcePredictableSchemaPropertyName) {

		_forcePredictableSchemaPropertyName =
			forcePredictableSchemaPropertyName;
	}

	public void setGenerateActionProviders(boolean generateActionProviders) {
		_generateActionProviders = generateActionProviders;
	}

	public void setGenerateBatch(boolean generateBatch) {
		_generateBatch = generateBatch;
	}

	public void setGenerateClientJS(boolean generateClientJS) {
		_generateClientJS = generateClientJS;
	}

	public void setGenerateGraphQL(boolean generateGraphQL) {
		_generateGraphQL = generateGraphQL;
	}

	public void setGenerateOpenAPI(boolean generateOpenAPI) {
		_generateOpenAPI = generateOpenAPI;
	}

	public void setGenerateREST(boolean generateREST) {
		_generateREST = generateREST;
	}

	public void setGraphQLNamespace(String graphQLNamespace) {
		_graphQLNamespace = graphQLNamespace;
	}

	public void setImplDir(String implDir) {
		_implDir = implDir;
	}

	public void setLicenseName(String licenseName) {
		_licenseName = licenseName;
	}

	public void setLicenseURL(String licenseURL) {
		_licenseURL = licenseURL;
	}

	public void setLiferayEnterpriseApp(boolean liferayEnterpriseApp) {
		_liferayEnterpriseApp = liferayEnterpriseApp;
	}

	public void setResourceApplicationSelect(String resourceApplicationSelect) {
		_resourceApplicationSelect = resourceApplicationSelect;
	}

	public void setTestDir(String testDir) {
		_testDir = testDir;
	}

	public void setWarningsEnabled(boolean warningsEnabled) {
		_warningsEnabled = warningsEnabled;
	}

	private String _apiDir;
	private String _apiPackagePath;
	private Application _application;
	private String _author;
	private boolean _changeTrackingEnabled;
	private String _clientDir;
	private String _clientMavenGroupId;
	private int _compatibilityVersion = 1;
	private boolean _forceClientVersionDescription = true;
	private boolean _forcePredictableContentApplicationXML = true;
	private boolean _forcePredictableOperationId;
	private boolean _forcePredictableSchemaPropertyName = true;
	private boolean _generateActionProviders;
	private boolean _generateBatch = true;
	private boolean _generateClientJS;
	private boolean _generateGraphQL = true;
	private boolean _generateOpenAPI = true;
	private boolean _generateREST = true;
	private String _graphQLNamespace;
	private String _implDir = "src/main/java";
	private String _licenseName = "Apache 2.0";
	private String _licenseURL =
		"http://www.apache.org/licenses/LICENSE-2.0.html";
	private boolean _liferayEnterpriseApp;
	private String _resourceApplicationSelect;
	private String _testDir;
	private boolean _warningsEnabled = true;

}