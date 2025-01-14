/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import fs from 'fs/promises';
import path from 'path';

import getExportedSymbols from '../../util/getExportedSymbols.mjs';
import getExportBridgePath from './getExportBridgePath.mjs';

/**
 * Create an export bridge file that can be used by esbuild as an entry point.
 *
 * Export bridges are necessary since we need esbuild to export symbols using standard ESM syntax
 * and for that to happen it must be fed an ES module as entry point. If fed a CommonJS one esbuild
 * will refuse to export things as ESM syntax.
 */
export default async function writeExportBridge(
	overridenPackageSymbols,
	moduleName
) {
	const symbols = getExportedSymbols(overridenPackageSymbols, moduleName);

	const exportBridgePath = getExportBridgePath(moduleName);

	const namedExportSymbols = Object.keys(symbols)
		.filter((symbol) => symbol !== 'default')
		.join(',\n');

	//
	// If the exported object was generated by a harmony aware tool, we
	// directly export the symbols as is.
	//
	// Otherwise, we need to set default to the exported object so that
	// other modules can find it when they are interoperated by tools like
	// babel or webpack.
	//
	// Note that we need to use import from instead of require in the generated
	// source because otherwise esbuild fails to honor "browser" field of
	// package.json files.
	//

	const source = `
import * as __star__ from '${moduleName}';

const {
${symbols.default ? 'default: __default__,' : ''}
${namedExportSymbols}
} = __star__;

export {
${namedExportSymbols}
};
${symbols.default ? 'export default __default__;' : ''}
`;

	await fs.mkdir(path.dirname(exportBridgePath), {recursive: true});
	await fs.writeFile(exportBridgePath, source, 'utf-8');
}
