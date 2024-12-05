/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import getGlobalImports from '../configuration/getGlobalImports.mjs';
import getLanguageJSON from '../configuration/getLanguageJSON.mjs';
import getOverridenPackageSymbols from '../configuration/getOverridenPackageSymbols.mjs';
import getProjectDescription from '../configuration/getProjectDescription.mjs';
import getProjectEntryPoints from '../configuration/getProjectEntryPoints.mjs';
import getProjectExports from '../configuration/getProjectExports.mjs';
import getProjectNpmScriptsConfig from '../configuration/getProjectNpmScriptsConfig.mjs';
import getProjectWebContextPath from '../configuration/getProjectWebContextPath.mjs';
import writeExportBridges from './amd/writeExportBridges.mjs';
import writeMainBridge from './amd/writeMainBridge.mjs';
import writeManifestJson from './amd/writeManifestJson.mjs';
import writePackageJson from './amd/writePackageJson.mjs';
import processCSSFiles from './css/processCSSFiles.mjs';
import writeCSSExportsLoaderModules from './cssLoad/writeCSSExportsLoaderModules.mjs';
import bundleCSSExports from './esbuild/bundleCSSExports.mjs';
import bundleJavaScriptExports from './esbuild/bundleJavaScriptExports.mjs';
import bundleJavaScriptMain from './esbuild/bundleJavaScriptMain.mjs';
import runNpmScripts from './npmscripts/runNpmScripts.mjs';
import processSassFiles from './sass/processSassFiles.mjs';
import writeTimings from './writeTimings.mjs';

export default async function main() {
	const start = Date.now();

	const [
		globalImports,
		languageJSON,
		overridenPackageSymbols,
		projectDescription,
		projectEntryPoints,
		projectExports,
		projectNpmScriptsConfig,
		projectWebContextPath,
	] = await Promise.all([
		getGlobalImports(),
		getLanguageJSON(),
		getOverridenPackageSymbols(),
		getProjectDescription(),
		getProjectEntryPoints(),
		getProjectExports(),
		getProjectNpmScriptsConfig(),
		getProjectWebContextPath(),
	]);

	const endConfig = Date.now();

	await Promise.all([

		// JavaScript exports bundling

		bundleJavaScriptMain(
			globalImports,
			languageJSON,
			overridenPackageSymbols,
			projectEntryPoints,
			projectWebContextPath
		),
		bundleJavaScriptExports(
			globalImports,
			overridenPackageSymbols,
			projectExports,
			projectWebContextPath
		),

		// CSS exports bundling

		bundleCSSExports(projectExports),
		writeCSSExportsLoaderModules(projectExports, projectWebContextPath),

		// AMD bridging

		writeMainBridge(
			projectDescription,
			projectEntryPoints,
			projectWebContextPath
		),
		writeExportBridges(
			projectDescription,
			projectExports,
			projectWebContextPath
		),
		writeManifestJson(
			projectDescription,
			projectEntryPoints,
			projectExports
		),
		writePackageJson(
			projectDescription,
			projectEntryPoints,
			projectExports
		),

		// CSS processing

		processCSSFiles(),
		processSassFiles(),

		// Rest of legacy build

		runNpmScripts(projectNpmScriptsConfig),
	]);

	await writeTimings(start, endConfig);
}
