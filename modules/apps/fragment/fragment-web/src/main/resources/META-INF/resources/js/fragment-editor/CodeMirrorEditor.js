/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import {CodeMirror} from '@liferay/frontend-js-codemirror-web';
import {CodeMirrorKeyboardMessage} from 'frontend-js-components-web';
import React, {useEffect, useMemo, useRef, useState} from 'react';

const AUTOCOMPLETE_EXCLUDED_KEYS = new Set([
	' ',
	',',
	';',
	'Alt',
	'AltGraph',
	'AltRight',
	'ArrowDown',
	'ArrowLeft',
	'ArrowRight',
	'ArrowUp',
	'Backspace',
	'Control',
	'Enter',
	'Escape',
	'Delete',
	'Meta',
	'Return',
	'Shift',
]);

const MODES = {
	css: {
		name: 'CSS',
		type: 'text/css',
	},
	html: {
		hint: (cm, options) => {
			const {
				customDataAttributes,
				customEntities,
				customEntitiesSymbolsRegex,
				customTags,
			} = options;

			const cursor = cm.getCursor();
			const token = cm.getTokenAt(cursor);

			if (token.type && token.type !== 'string') {
				const content = token.string;

				const htmlCompletion = CodeMirror.hint.html(cm, options);

				if (!htmlCompletion) {
					return;
				}

				const resultSet = new Set(htmlCompletion.list);

				if (
					token.type === 'attribute' &&
					token.string.startsWith('data')
				) {
					customDataAttributes.forEach((item) => {
						let attributeName = `data-${item}`;
						let attributeValue = '';

						if (attributeName.indexOf(':') !== -1) {
							attributeValue = attributeName.substring(
								attributeName.indexOf(':') + 1
							);

							attributeName = attributeName.substring(
								0,
								attributeName.indexOf(':')
							);
						}

						if (
							attributeName.startsWith(content) &&
							!resultSet.has(attributeName)
						) {
							resultSet.add({
								displayText: `${attributeName}${
									attributeValue ? ':' + attributeValue : ''
								}`,
								text: `${attributeName}="${attributeValue}"`,
							});
						}
					});
				}
				else {
					customTags.forEach((item) => {
						if (
							item.name.startsWith(content) &&
							!resultSet.has(item.content)
						) {
							resultSet.add({
								displayText: item.name,
								text: item.content,
							});
						}
					});
				}

				return {
					...htmlCompletion,
					list: Array.from(resultSet),
				};
			}
			else if (customEntities && customEntitiesSymbolsRegex) {
				const line = cm.getLine(cursor.line).slice(0, cursor.ch);

				const match = (
					line.match(new RegExp(customEntitiesSymbolsRegex, 'g')) ||
					[]
				).pop();

				if (!match) {
					return;
				}

				const customEntity = customEntities.find((entity) =>
					match.startsWith(entity.start)
				);

				const content = match.slice(customEntity.start.length);

				const results = customEntity.content
					.filter((entityContent) =>
						entityContent.startsWith(content)
					)
					.map(
						(entityContent) =>
							`${customEntity.start}${entityContent}`
					);

				return {
					from: CodeMirror.Pos(cursor.line, cursor.ch - match.length),
					list: results,
					to: CodeMirror.Pos(cursor.line, cursor.ch),
				};
			}
		},
		name: 'HTML',
		type: 'text/html',
	},
	javascript: {
		name: 'JavaScript',
		type: 'text/javascript',
	},
	json: {
		name: 'JSON',
		type: 'application/json',
	},
};

const escapeChars = (string) => string.replace(/[.*+\-?^${}()|[\]\\]/g, '\\$&');

const noop = () => {};

const FixedText = ({helpText, texts = []}) => {
	return (
		<div className="source-editor__fixed-text">
			<code
				className="source-editor__fixed-text__content"
				style={{whiteSpace: 'pre-line'}}
			>
				{texts.join('\n')}
			</code>

			{helpText && (
				<span
					className="float-right source-editor__fixed-text__help"
					data-title={helpText}
				>
					<ClayIcon
						className="icon-monospaced"
						symbol="question-circle-full"
					/>
				</span>
			)}
		</div>
	);
};

const CodeMirrorEditor = ({
	customDataAttributes,
	customEntities,
	customTags,
	onChange = noop,
	mode = 'html',
	codeFooterText,
	codeHeaderTexts,
	codeHeaderHelpText,
	content = '',
	readOnly,
	showHeader = true,
}) => {
	const editorRef = useRef();
	const ref = useRef();
	const [isEnabled, setIsEnabled] = useState(true);
	const [isFocused, setIsFocused] = useState(false);

	const customEntitiesSymbolsRegex = useMemo(() => {
		if (!customEntities) {
			return;
		}

		return `${customEntities
			.map((entity) => {
				const start = escapeChars(entity.start);
				const end = escapeChars(entity.end);

				return `${start}((?!\\s|${end}).)*(?:${end})?`;
			})
			.join('|')}$`;
	}, [customEntities]);

	useEffect(() => {
		if (ref.current) {
			const hasEnabledTabKey = ({state: {keyMaps}}) =>
				keyMaps.every((key) => key.name !== 'tabKey');

			const codeMirror = CodeMirror(ref.current, {
				autoCloseTags: true,
				autoRefresh: true,
				extraKeys: {
					'Ctrl-M'(cm) {
						const tabKeyIsEnabled = hasEnabledTabKey(cm);

						setIsEnabled(tabKeyIsEnabled);

						if (tabKeyIsEnabled) {
							cm.addKeyMap({
								'Shift-Tab': false,
								'Tab': false,
								'name': 'tabKey',
							});
						}
						else {
							cm.removeKeyMap('tabKey');
						}
					},
					'Ctrl-Space': readOnly ? '' : 'autocomplete',
				},
				foldGutter: true,
				gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
				hintOptions: {
					completeSingle: false,
					customDataAttributes,
					customEntities,
					customEntitiesSymbolsRegex,
					customTags,
					hint: MODES[mode].hint,
				},
				indentWithTabs: true,
				inputStyle: 'contenteditable',
				lineNumbers: true,
				matchBrackets: true,
				mode: {globalVars: true, name: MODES[mode].type},
				readOnly,
				showHint: !readOnly,
				tabSize: 2,
				value: content,
				viewportMargin: Infinity,
			});

			codeMirror.on('change', (cm) => {
				onChange(cm.getValue());
			});

			codeMirror.on('keyup', (cm, event) => {
				if (
					!readOnly &&
					!cm.state.completionActive &&
					!AUTOCOMPLETE_EXCLUDED_KEYS.has(event.key)
				) {
					codeMirror.showHint();
				}
			});

			codeMirror.on('focus', (cm) => {
				setIsFocused(true);

				if (hasEnabledTabKey(cm)) {
					cm.addKeyMap({
						'Shift-Tab': false,
						'Tab': false,
						'name': 'tabKey',
					});
				}
			});

			codeMirror.on('blur', () => setIsFocused(false));

			editorRef.current = codeMirror;
		}
	}, [ref]); // eslint-disable-line

	useEffect(() => {
		if (editorRef.current) {
			editorRef.current.setOption('mode', {
				globalVars: true,
				name: MODES[mode].type,
			});

			editorRef.current.setOption('readOnly', readOnly);

			editorRef.current.setOption('hintOptions', {
				...editorRef.current.getOption('hintOptions'),
				customEntities,
				customEntitiesSymbolsRegex,
				customTags,
			});
		}
	}, [
		customEntities,
		customEntitiesSymbolsRegex,
		customTags,
		mode,
		readOnly,
	]);

	useEffect(() => {
		if (editorRef.current) {
			editorRef.current.setValue(content);
		}
	}, [content]);

	return (
		<>
			{showHeader && (
				<nav className="source-editor-toolbar tbar">
					<ul className="tbar-nav">
						<li className="source-editor-toolbar__syntax tbar-item tbar-item-expand text-center">
							{MODES[mode].name}
						</li>
					</ul>
				</nav>
			)}

			{(codeHeaderHelpText || codeHeaderTexts) && (
				<FixedText
					helpText={codeHeaderHelpText}
					texts={codeHeaderTexts}
				/>
			)}

			<div className="d-flex flex-column flex-grow-1 overflow-hidden position-relative">
				{isFocused && !readOnly ? (
					<CodeMirrorKeyboardMessage keyIsEnabled={isEnabled} />
				) : null}

				<div
					aria-label={
						readOnly
							? null
							: Liferay.Language.get(
									'use-ctrl-m-to-enable-or-disable-the-tab-key'
								)
					}
					className="codemirror-editor-wrapper h-100"
					ref={ref}
				></div>
			</div>

			{codeFooterText && <FixedText texts={[codeFooterText]} />}
		</>
	);
};

export default CodeMirrorEditor;
