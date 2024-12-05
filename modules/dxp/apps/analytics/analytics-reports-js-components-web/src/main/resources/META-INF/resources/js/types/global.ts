/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export enum Colors {
	Black = '#000000',
	Blue = '#4b9fff',
	Blue2 = '#66caff',
	Cyan = '#5fc8ff',
	Gray = '#e2e4ea',
	Green = '#9be169',
	Indigo = '#7785ff',
	Indigo2 = '#6675ff',
	Orange = '#ffb46e',
	Pink = '#ff73c3',
	Pink2 = '#ff73c3',
	Purple = '#af78ff',
	Red = '#ff5f5f',
	Teal = '#50d2a0',
	Yellow = '#ffd76e',
	Yellow2 = '#ffd566',
}

export enum Textures {
	DiagonalLines = 'M 10 0 L 0 10',
	HorizontalLines = 'M 0 5 L 10 5',
	VerticalLines = 'M 5 0 L 5 10',
}

export enum Individuals {
	AllIndividuals = 'ALL',
	AnonymousIndividuals = 'UNKNOWN',
	KnownIndividuals = 'KNOWN',
}

export enum RangeSelectors {
	Last24Hours = '0',
	Last7Days = '7',
	Last28Days = '28',
	Last30Days = '30',
	Last90Days = '90',
}

export enum MetricName {
	Comments = 'commentsMetric',
	Downloads = 'downloadsMetric',
	Previews = 'previewsMetric',
	Undefined = 'undefinedMetric',
	Views = 'viewsMetric',
}

export enum AssetTypes {
	Blog = 'blog',
	Document = 'document',
	Undefined = 'undefined',
	WebContent = 'journal',
}

export enum MetricType {
	Comments = 'COMMENTS',
	Downloads = 'DOWNLOADS',
	Previews = 'PREVIEWS',
	Undefined = 'UNDEFINED',
	Views = 'VIEWS',
}

export enum Alignments {
	Center = 'center',
	Left = 'left',
	Right = 'right',
}

export enum Weights {
	Bold = 'bold',
	Light = 'light',
	Normal = 'normal',
	Semibold = 'semibold',
}

export type Version = {
	createDate: string;
	version: string;
};
