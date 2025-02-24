/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.video.internal.item.selector.criterion;

import com.liferay.document.library.display.context.DLUIItemKeys;
import com.liferay.document.library.item.selector.criterion.DLItemSelectorCriterionCreationMenuRestriction;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.video.internal.constants.DLVideoConstants;
import com.liferay.item.selector.criteria.video.criterion.VideoItemSelectorCriterion;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"model.class.name=com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion",
		"model.class.name=com.liferay.item.selector.criteria.video.criterion.VideoItemSelectorCriterion"
	},
	service = DLItemSelectorCriterionCreationMenuRestriction.class
)
public class DLVideoItemSelectorCriterionCreationMenuRestriction
	implements DLItemSelectorCriterionCreationMenuRestriction
		<VideoItemSelectorCriterion> {

	@Override
	public Set<String> getAllowedCreationMenuUIItemKeys() {
		return _allowedCreationMenuUIItemKeys;
	}

	private static final Set<String> _allowedCreationMenuUIItemKeys =
		SetUtil.fromArray(
			DLFileEntryType.class.getSimpleName() +
				DLVideoConstants.DL_FILE_ENTRY_TYPE_KEY,
			DLUIItemKeys.ADD_FOLDER, DLUIItemKeys.UPLOAD);

}