/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ESLint} from 'eslint';
import fg from 'fast-glob';
import * as fs from 'fs/promises';
import micromatch from 'micromatch';
import path from 'path';
import prettier from 'prettier';
import stylelint from 'stylelint';

import {getRootDir} from '../util/constants.mjs';
import fileExists from '../util/fileExists.mjs';
import {readIgnoreFile} from '../util/readIgnoreFile.mjs';
import {ID_END, ID_START} from './jsp/getPaddedReplacement.mjs';
import processJSP from './jsp/processJSP.mjs';
import {SCRIPTLET_CONTENT} from './jsp/substituteTags.mjs';
import {BLOCK_CLOSE, BLOCK_OPEN} from './jsp/tagReplacements.mjs';
import {FILLER_CHAR, SPACE_CHAR, TAB_CHAR} from './jsp/toFiller.mjs';
import logError from './logError.mjs';

const PRETTIER_IGNORE_FILE = '.prettierignore';
const ESLINT_IGNORE_FILE = '.eslintignore';
const GIT_IGNORE_FILE = '.gitignore';

const EXTENSIONS = ['graphql', 'js', 'jsp', 'jspf', 'mjs', 'scss', 'ts', 'tsx'];

const FALLBACK_FILE_PATH = '__fallback__.js';

/**
 * @param string[]|undefined filesToFormat
 * List of files to format (if undefined all files are formatted).
 *
 * @returns string|undefined
 * A string with the result of the format operation (empty if nothing was formatted or had errors)
 * or undefined if no files were checked.
 */
export default async function format(
	fix,
	filesToFormat = undefined,
	{emitSuppressed} = {}
) {
	const suppressedErrors = await fs.readFile(
		path.join(import.meta.dirname, 'suppressed_errors.txt'),
		'utf-8'
	);

	const rootDir = await getRootDir();

	const filepaths = await getFilePaths(rootDir, filesToFormat);

	if (!filepaths.length) {
		return undefined;
	}

	const errMessages = [];
	const fixedFiles = [];

	// Configure tools

	const [eslintConfig, prettierConfig, stylelintConfig] = await Promise.all([
		getEslintConfig(rootDir),
		getPrettierConfig(rootDir),
		getStylelintConfig(rootDir),
	]);

	const eslintCLI = new ESLint({
		baseConfig: eslintConfig,
		fix: true,
		ignorePath: path.join(rootDir, ESLINT_IGNORE_FILE),
		resolvePluginsRelativeTo: rootDir,
	});

	// Define tool helpers

	async function formatWithPrettier(input, filepath, configOverride = {}) {
		return await prettier.format(input, {
			...prettierConfig,
			...configOverride,
			filepath,
		});
	}

	async function formatWithEslint(input, filepath) {
		const relativePath = path.relative(rootDir, filepath);
		const [lintResult = {}] = await eslintCLI.lintText(input, {
			filePath: filepath,
		});

		const {messages = [], output} = lintResult;

		const filteredErrors = emitSuppressed
			? messages
			: messages.filter(
					(item) =>
						!suppressedErrors.includes(
							`${relativePath}:${item.message}\n`
						)
				);

		if (filteredErrors?.length) {
			errMessages[filepath] = errMessages[filepath] || [];
			errMessages[filepath].push(...filteredErrors);
		}

		return output ?? input;
	}

	async function formatWithStyleLint(input, filepath) {
		const extName = path.extname(filepath);

		const {output, results} = await stylelint.lint({
			code: input,
			codeFilename: filepath,
			config: stylelintConfig,
			fix: true,
			syntax: extName.replace('.', ''),
		});

		if (results?.length) {
			results.forEach((result) => {
				if (result.warnings.length) {
					errMessages[filepath] = errMessages[filepath] || [];
					errMessages[filepath].push(
						...result.warnings.map(
							({column, line, rule, text}) => ({
								column,
								filepath,
								line,
								message: text,
								ruleId: rule,
								severity: 2,
							})
						)
					);
				}
			});
		}

		return output.endsWith('\n') ? output : `${output}\n`;
	}

	// Run the format process

	for (const filepath of filepaths) {
		if (!(await fileExists(filepath))) {
			continue;
		}

		const source = await fs.readFile(filepath, 'utf8');

		if (!source.length) {
			continue;
		}

		let transformedContent = source;

		try {
			switch (path.extname(filepath)) {
				case '.jsp':
				case '.jspf': {
					transformedContent = await processJSP(
						source,
						async (input) => {
							return await formatWithPrettier(
								input,
								FALLBACK_FILE_PATH,
								{
									commentIgnorePatterns: [
										BLOCK_CLOSE,
										BLOCK_OPEN,
										FILLER_CHAR,
										ID_END,
										ID_START,
										SCRIPTLET_CONTENT,
										SPACE_CHAR,
										TAB_CHAR,
									],
								}
							);
						}
					);
					break;
				}

				case '.css':
				case '.scss': {
					transformedContent = await formatWithPrettier(
						transformedContent,
						filepath
					);
					transformedContent = await formatWithStyleLint(
						transformedContent,
						filepath
					);
					break;
				}

				default: {
					transformedContent = await formatWithPrettier(
						transformedContent,
						filepath
					);
					transformedContent = await formatWithEslint(
						transformedContent,
						filepath
					);
				}
			}
		}
		catch (error) {

			// eslint-disable-next-line no-console
			console.log(`🚨 ${filepath}: ${error}`);
		}

		if (transformedContent !== source) {
			if (fix) {
				await fs.writeFile(filepath, transformedContent);

				fixedFiles.push(filepath);
			}
			else {
				errMessages[filepath] = errMessages[filepath] || [];
				errMessages[filepath].push({
					column: 1,
					filepath,
					line: 1,
					message: 'File has format errors.',
					ruleId: '(format check)',
					severity: 2,
				});
			}
		}
	}

	// Return summary

	let summary = '';

	if (Object.keys(errMessages).length) {
		summary += logError(errMessages, true);
	}

	if (fixedFiles.length) {
		summary += `• The following files were automatically formatted:\n`;
		summary += fixedFiles.map((file) => `  · ${file}`).join('\n');
	}

	return summary;
}

async function getFilePaths(rootDir, filesToFormat) {
	const workspacesDir = path.join(rootDir, '..', 'workspaces');
	const playwrightDir = path.join(rootDir, 'test', 'playwright');

	const [rootIgnored, workspacesIgnored, playwrightIgnored] =
		await Promise.all([
			getIgnoredFiles(rootDir),
			getIgnoredFiles(workspacesDir),
			getIgnoredFiles(playwrightDir),
		]);

	let filepaths = [];

	if (!filesToFormat) {
		filepaths = (
			await Promise.all([
				getFilesToCheck(rootDir, rootIgnored),
				getFilesToCheck(workspacesDir, workspacesIgnored),
				getFilesToCheck(playwrightDir, playwrightIgnored),
			])
		).flat();
	}
	else {
		for (const file of filesToFormat) {
			if (file.startsWith('modules/test/playwright/')) {
				filepaths.push(
					...micromatch(
						[file],
						EXTENSIONS.map((ext) => `**/*.${ext}`),
						{ignore: playwrightIgnored}
					)
				);
			}

			if (file.startsWith('modules/')) {
				filepaths.push(
					...micromatch(
						[file],
						EXTENSIONS.map((ext) => `**/*.${ext}`),
						{ignore: rootIgnored}
					)
				);
			}

			if (file.startsWith('workspaces/')) {
				filepaths.push(
					...micromatch(
						[file],
						EXTENSIONS.map((ext) => `**/*.${ext}`),
						{ignore: workspacesIgnored}
					)
				);
			}
		}

		filepaths = filepaths.map(

			// make sure the path is absolute

			(filepath) => path.join(rootDir, '..', filepath)
		);
	}

	return filepaths;
}

async function getIgnoredFiles(rootDir) {
	const eslintIgnoreFilePath = path.join(rootDir, ESLINT_IGNORE_FILE);
	const prettierIgnoreFilePath = path.join(rootDir, PRETTIER_IGNORE_FILE);
	const gitIgnoreFilePath = path.join(rootDir, GIT_IGNORE_FILE);

	const [eslintIgnores, prettierIgnores, gitIgnores] = await Promise.all([
		readIgnoreFile(eslintIgnoreFilePath),
		readIgnoreFile(prettierIgnoreFilePath),
		readIgnoreFile(gitIgnoreFilePath),
	]);

	return [
		'**/src/test/**',
		'**/build_gradle/**',
		...gitIgnores,
		...eslintIgnores,
		...prettierIgnores,
	].map((ignore) => {
		if (ignore.startsWith('*') && !ignore.startsWith('**')) {
			ignore = `**/${ignore}`;
		}

		if (!ignore.startsWith('*')) {
			ignore = `**${ignore.startsWith('/') ? '' : '/'}${ignore}`;
		}

		if (!ignore.endsWith('**') && !ignore.includes('.')) {
			ignore = `${ignore}${ignore.endsWith('/') ? '' : '/'}**`;
		}

		return ignore;
	});
}

async function getFilesToCheck(rootDir, ignore = []) {
	const files = await fg(
		[
			'**/*.',
			'*.{graphql,js,mjs,scss,ts,tsx}',
			'**/*.{graphql,js,mjs,scss,ts,tsx}',
			'**/src/**/*.{jsp,jspf}',
		],
		{
			cwd: rootDir,
			dot: true,
			ignore,
		}
	);

	return files.map((filepath) => path.join(rootDir, filepath));
}

async function getEslintConfig(rootDir) {
	const eslintConfigPath = path.join(rootDir, '.eslintrc.js');

	const {default: eslintConfig} = await import('file://' + eslintConfigPath);

	return eslintConfig;
}

async function getPrettierConfig(rootDir) {
	const prettierConfigPath = path.join(rootDir, '.prettierrc.js');

	const {default: prettierConfig} = await import(
		'file://' + prettierConfigPath
	);

	return prettierConfig;
}

async function getStylelintConfig(rootDir) {
	const stylelintConfigPath = path.join(rootDir, '.stylelintrc.js');

	const {default: stylelintConfig} = await import(
		'file://' + stylelintConfigPath
	);

	return stylelintConfig;
}
