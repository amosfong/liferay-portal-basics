/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {JSXElementConstructor} from 'react';

type LocalizedValue<T> = Liferay.Language.LocalizedValue<T>;

export enum EFilterType {
	CLIENT_EXTENSION = 'CLIENT_EXTENSION',
	DATE_RANGE = 'DATE_RANGE',
	SELECTION = 'SELECTION',
}

export enum EFieldFormat {
	DATE = 'date',
	DATE_TIME = 'date-time',
	INT32 = 'int32',
	INT64 = 'int64',
}

export enum EFieldType {
	ARRAY = 'array',
	INTEGER = 'integer',
	OBJECT = 'object',
	STRING = 'string',
}

export enum ESelectionFilterSourceType {
	OBJECT_PICKLIST = 'OBJECT_PICKLIST',
	API_REST_APPLICATION = 'API_REST_APPLICATION',
}

export interface IBaseVisualizationMode<Mode extends string> {
	label: string;
	mode: Mode;
	thumbnail: string;
	visualizationModeId: string;
}

export interface ICards extends IBaseVisualizationMode<'cards'> {}
export interface IList extends IBaseVisualizationMode<'list'> {}
export interface ITable extends IBaseVisualizationMode<'table'> {}

export type TVisualizationMode = ICards | IList | ITable;

export interface IDataSet {
	actions: {
		delete: {
			href: string;
			method: string;
		};
		update: {
			href: string;
			method: string;
		};
	};
	additionalAPIURLParameters?: string;
	creationActionsOrder?: string;
	defaultItemsPerPage: number;
	defaultVisualizationMode?: string;
	description?: string;
	externalReferenceCode: string;
	filtersOrder?: string;
	id: string;
	itemActionsOrder?: string;
	label: string;
	listOfItemsPerPage: string;
	restApplication: string;
	restEndpoint: string;
	restSchema: string;
	sortsOrder?: string;
	tableSectionsOrder?: string;
}

export interface IField {
	children?: Array<IField>;
	format?: EFieldFormat;
	id?: string;
	label?: string;
	name: string;
	selected?: boolean;
	sortable?: boolean;
	type?: string;
	visible?: boolean;
}

export interface IDataSetTableSection extends IOrderable {
	contextPath: string;
	externalReferenceCode: string;
	fieldName: string;
	label: string;
	label_i18n: LocalizedValue<string>;
	renderer: string;
	rendererLabel?: string;
	sortable: boolean;
	type: string;
}

export interface IFieldTreeItem extends IField {
	children?: IFieldTreeItem[];
	disabled?: boolean;
	initialChildren?: IFieldTreeItem[];
	query?: string;
	savedId?: string;
	selected?: boolean;
}

export interface IFilter extends IOrderable {
	fieldName: string;
	filterType?: EFilterType;
	include?: boolean;
	itemKey?: string;
	itemLabel?: string;
	label: string;
	label_i18n: LocalizedValue<string>;
	multiple?: boolean;
	preselectedValues?: any;
	restApplication?: string;
	restEndpoint?: string;
	restSchema?: string;
	source?: string;
	sourceType?: ESelectionFilterSourceType;
	type: string;
}

export interface IFilterTypeProps {
	Component: {
		Body: JSXElementConstructor<any>;
		Header: JSXElementConstructor<any>;
	};
	availableFieldsFilter: (field: IField) => boolean;
	displayType: string;
	fdsViewRelationship: string;
	fdsViewRelationshipId: string;
	label: string;
	url: string;
}

export interface IClientExtensionFilter extends IFilter {
	clientExtensionEntryERC: string;
}

export interface IDateFilter extends IFilter {
	from: string;
	to: string;
}

export interface ISelectionFilter extends IFilter {
	include: boolean;
	itemKey: string;
	itemLabel: string;
	multiple: boolean;
	preselectedValues: any;
	restApplication: string;
	restEndpoint: string;
	restSchema: string;
	source: string;
	sourceType: ESelectionFilterSourceType;
}

export interface IOrderable {
	dateCreated: string;
	id: number;
}

export interface IPickList {
	externalReferenceCode: string;
	id: string;
	listTypeEntries: IListTypeEntry[];
	name: string;
	name_i18n: {
		[key: string]: string;
	};
}

export interface IListTypeEntry {
	externalReferenceCode: string;
	id: number;
	key: string;
	name: string;
	name_i18n: {
		[key: string]: string;
	};
}
