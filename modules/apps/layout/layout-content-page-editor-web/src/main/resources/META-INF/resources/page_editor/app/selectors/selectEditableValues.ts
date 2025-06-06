/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import type {FragmentEntryLinkMap} from '../actions/addFragmentEntryLinks';

export default function selectEditableValues(
	{fragmentEntryLinks}: {fragmentEntryLinks: FragmentEntryLinkMap},
	fragmentEntryLinkId: string
) {
	const fragmentEntryLink = fragmentEntryLinks[fragmentEntryLinkId];

	return (fragmentEntryLink && fragmentEntryLink.editableValues) || {};
}
