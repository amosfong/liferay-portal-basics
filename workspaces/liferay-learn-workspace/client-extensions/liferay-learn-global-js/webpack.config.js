/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const path = require('path');

module.exports = {
	entry: ['./src/index.js'],
	mode: 'production',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.js$/,
				use: {
					loader: 'babel-loader',
					options: {
						presets: ['@babel/preset-env'],
					},
				},
			},
			{
				test: /\.css$/,
				use: [{loader: 'style-loader'}, {loader: 'css-loader'}],
			},
		],
	},
	output: {
		filename: '[name]-[hash].js',
		globalObject: 'this',
		library: {
			name: 'navigation',
			type: 'umd',
		},
		path: path.resolve(__dirname, 'build/'),
	},
};
