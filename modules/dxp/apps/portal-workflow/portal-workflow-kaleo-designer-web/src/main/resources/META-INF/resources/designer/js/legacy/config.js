/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function () {
	AUI().applyConfig({
		groups: {
			'kaleo-designer': {
				base: MODULE_PATH + '/designer/js/legacy/',
				combine: Liferay.AUI.getCombine(),
				filter: Liferay.AUI.getFilterConfig(),
				modules: {
					'liferay-kaleo-designer-autocomplete-util': {
						path: 'autocomplete_util.js',
						requires: ['autocomplete', 'autocomplete-highlighters'],
					},
					'liferay-kaleo-designer-definition-diagram-controller': {
						path: 'definition_diagram_controller.js',
						requires: [
							'liferay-kaleo-designer-field-normalizer',
							'liferay-kaleo-designer-utils',
						],
					},
					'liferay-kaleo-designer-dialogs': {
						path: 'dialogs.js',
						requires: ['liferay-util-window'],
					},
					'liferay-kaleo-designer-editors': {
						path: 'editors.js',
						requires: [
							'aui-ace-editor',
							'aui-ace-editor-mode-xml',
							'aui-base',
							'aui-datatype',
							'aui-node',
							'liferay-kaleo-designer-autocomplete-util',
							'liferay-kaleo-designer-utils',
						],
					},
					'liferay-kaleo-designer-field-normalizer': {
						path: 'field_normalizer.js',
						requires: ['liferay-kaleo-designer-remote-services'],
					},
					'liferay-kaleo-designer-nodes': {
						path: 'nodes.js',
						requires: [
							'aui-datatable',
							'aui-datatype',
							'aui-diagram-builder',
							'liferay-kaleo-designer-editors',
							'liferay-kaleo-designer-utils',
						],
					},
					'liferay-kaleo-designer-remote-services': {
						path: 'remote_services.js',
						requires: ['aui-io'],
					},
					'liferay-kaleo-designer-templates': {
						path: 'templates.js',
						requires: ['aui-tpl-snippets-deprecated'],
					},
					'liferay-kaleo-designer-utils': {
						path: 'utils.js',
						requires: [],
					},
					'liferay-kaleo-designer-xml-definition': {
						path: 'xml_definition.js',
						requires: [
							'aui-base',
							'aui-component',
							'dataschema-xml',
							'datatype-xml',
						],
					},
					'liferay-kaleo-designer-xml-definition-serializer': {
						path: 'xml_definition_serializer.js',
						requires: ['escape', 'liferay-kaleo-designer-xml-util'],
					},
					'liferay-kaleo-designer-xml-util': {
						path: 'xml_util.js',
						requires: ['aui-base'],
					},
					'liferay-portlet-kaleo-designer': {
						path: 'main.js',
						requires: [
							'aui-ace-editor',
							'aui-ace-editor-mode-xml',
							'aui-tpl-snippets-deprecated',
							'dataschema-xml',
							'datasource',
							'datatype-xml',
							'event-valuechange',
							'io-form',
							'liferay-kaleo-designer-autocomplete-util',
							'liferay-kaleo-designer-editors',
							'liferay-kaleo-designer-nodes',
							'liferay-kaleo-designer-remote-services',
							'liferay-kaleo-designer-utils',
							'liferay-kaleo-designer-xml-util',
							'liferay-util-window',
						],
					},
				},
				root: MODULE_PATH + '/designer/js/legacy/',
			},
		},
	});
})();
