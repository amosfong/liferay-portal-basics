/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CodeMirror} from '@liferay/frontend-js-codemirror-web';
import {CodeMirrorKeyboardMessage} from 'frontend-js-components-web';
import PropTypes from 'prop-types';
import React, {useEffect, useImperativeHandle, useRef, useState} from 'react';

const CodeMirrorEditor = React.forwardRef(
	({autocompleteData, content, inputChannel, mode, onChange}, ref) => {
		const [editor, setEditor] = useState();
		const [editorWrapper, setEditorWrapper] = useState();
		const initialContentRef = useRef(content);
		const [isEnabled, setIsEnabled] = useState(true);
		const [isFocused, setIsFocused] = useState(null);

		useImperativeHandle(
			ref,
			() => {
				return {
					setValue(value) {
						editor.setValue(value);
					},
				};
			},
			[editor]
		);

		useEffect(() => {
			if (!editorWrapper) {
				return;
			}

			const hasEnabledTabKey = ({state: {keyMaps}}) =>
				keyMaps.every((key) => key.name !== 'tabKey');

			const codeMirror = CodeMirror(editorWrapper, {
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
					'Ctrl-Space': 'autocomplete',
				},
				foldGutter: true,
				gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
				indentWithTabs: true,
				inputStyle: 'contenteditable',
				lineNumbers: true,
				matchBrackets: true,
				showHint: true,
				tabSize: 2,
				value: initialContentRef.current,
				viewportMargin: Infinity,
			});

			setEditor(codeMirror);

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
		}, [editorWrapper]);

		useEffect(() => {
			if (editor) {
				const variableStart = '${';
				const variableEnd = '}';

				let wordList = [];

				try {
					wordList = Object.keys(autocompleteData.variables)
						.sort()
						.map((word) => ({
							lowerCaseWord: word.toLowerCase(),
							word,
						}));
				}
				catch (error) {
					if (process.env.NODE_ENV === 'development') {
						console.error(
							'Error loading editor autocomplete data',
							error
						);
					}
				}

				const getWordContext = (cm) => {
					const currentRange = cm.findWordAt({
						...cm.getCursor(),
						sticky: 'before',
						xRel: 0,
					});

					const getRange = (range) => {
						return cm.getRange(range.anchor, range.head);
					};

					return {
						current: getRange(currentRange),
						next: getRange(
							cm.findWordAt(
								cm.findPosH(currentRange.head, 1, 'char')
							)
						),
						previous: getRange(
							cm.findWordAt(
								cm.findPosH(currentRange.anchor, -1, 'char')
							)
						),
					};
				};

				const hint = (cm) => {
					const {current, next, previous} = getWordContext(cm);
					const currentLowerCase = current.toLowerCase();
					const cursorPosition = cm.getCursor();

					const closeVariable = next !== variableEnd;
					const openVariable =
						current !== variableStart && previous !== variableStart;

					if (current === variableStart) {
						return {
							from: cursorPosition,
							list: wordList.map(({word}) => ({
								displayText: word,
								text: `${word}${
									closeVariable ? variableEnd : ''
								}`,
							})),
						};
					}

					return {
						from: {
							...cursorPosition,
							ch: cursorPosition.ch - current.length,
						},
						list: wordList
							.map(({lowerCaseWord, word}) => ({
								index: lowerCaseWord.indexOf(currentLowerCase),
								lowerCaseWord,
								word,
							}))
							.filter(({index}) => index >= 0)
							.sort(
								({index: indexA}, {index: indexB}) =>
									indexA - indexB
							)
							.map(({word}) => ({
								displayText: word,
								text: `${
									openVariable ? variableStart : ''
								}${word}${closeVariable ? variableEnd : ''}`,
							})),
						to: cursorPosition,
					};
				};

				editor.setOption('hintOptions', {
					completeSingle: false,
					hint: variableStart || variableEnd ? hint : null,
				});

				const handleEditorChange = (cm) => {
					const {current} = getWordContext(cm);

					if (current === variableStart) {
						cm.showHint();
					}
				};

				editor.on('change', handleEditorChange);

				return () => {
					editor.off('change', handleEditorChange);
				};
			}
		}, [autocompleteData, editor]);

		useEffect(() => {
			if (editor) {
				editor.setOption('mode', mode);
			}
		}, [editor, mode]);

		useEffect(() => {
			if (!editor) {
				return;
			}

			const handleChange = () => {
				onChange(editor.getValue());
			};

			editor.on('change', handleChange);

			return () => {
				editor.off('change', handleChange);
			};
		}, [editor, onChange]);

		useEffect(() => {
			if (editor && editor.getValue() !== content) {
				editor.setValue(content);
			}
		}, [content, editor]);

		useEffect(() => {
			if (inputChannel) {
				const removeListener = inputChannel.onData((data) => {
					editor?.replaceSelection(data);
				});

				return removeListener;
			}
		}, [editor, inputChannel]);

		return (
			<div className="d-flex flex-column flex-grow-1 position-relative">
				{isFocused ? (
					<CodeMirrorKeyboardMessage keyIsEnabled={isEnabled} />
				) : null}

				<div
					aria-label={Liferay.Language.get(
						'use-ctrl-m-to-enable-or-disable-the-tab-key'
					)}
					className="ddm_template_editor__CodeMirrorEditor"
					ref={setEditorWrapper}
				/>
			</div>
		);
	}
);

export default CodeMirrorEditor;

CodeMirrorEditor.propTypes = {
	autocompleteData: PropTypes.object.isRequired,
	content: PropTypes.string.isRequired,
	inputChannel: PropTypes.shape({
		onData: PropTypes.func.isRequired,
	}),
	mode: PropTypes.oneOfType([
		PropTypes.string,
		PropTypes.shape({
			globalVars: PropTypes.bool.isRequired,
			name: PropTypes.string.isRequired,
		}),
	]),
	onChange: PropTypes.func.isRequired,
};
