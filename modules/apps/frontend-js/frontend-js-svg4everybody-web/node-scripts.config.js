/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const path = require('path');

module.exports = {
	customBuild: {
		esbuild: {
			bundle: true,
			entryNames: 'index',
			entryPoints: [
				path.resolve(
					'src',
					'main',
					'resources',
					'META-INF',
					'resources',
					'svg4everybody.js'
				),
			],
			outdir: path.resolve(
				'build',
				'node',
				'packageRunBuild',
				'resources'
			),
			sourcemap: true,
			target: ['es2020'],
		},
	},
};