/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {EDITABLE_FRAGMENT_ENTRY_PROCESSOR} from '../config/constants/editableFragmentEntryProcessor';
import selectEditableValues from './selectEditableValues';

import type {FragmentEntryLinkMap} from '../actions/addFragmentEntryLinks';

export default function selectEditableValue(
	{fragmentEntryLinks}: {fragmentEntryLinks: FragmentEntryLinkMap},
	fragmentEntryLinkId: string,
	editableId: string,
	processorType = EDITABLE_FRAGMENT_ENTRY_PROCESSOR
) {
	const editableValues = selectEditableValues(
		{fragmentEntryLinks},
		fragmentEntryLinkId
	);

	return (
		(editableValues[processorType] &&
			editableValues[processorType][editableId]) ||
		{}
	);
}
