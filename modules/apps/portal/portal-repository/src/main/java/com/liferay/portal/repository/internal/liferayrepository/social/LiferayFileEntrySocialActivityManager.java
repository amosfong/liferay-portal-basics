/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.internal.liferayrepository.social;

import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.social.BaseSocialActivityManager;
import com.liferay.portal.kernel.social.SocialActivityManager;
import com.liferay.social.kernel.service.SocialActivityLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = "model.class.name=com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry",
	service = SocialActivityManager.class
)
public class LiferayFileEntrySocialActivityManager
	extends BaseSocialActivityManager<FileEntry> {

	@Override
	protected String getClassName(FileEntry classedModel) {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	protected SocialActivityLocalService getSocialActivityLocalService() {
		return _socialActivityLocalService;
	}

	@Reference
	private SocialActivityLocalService _socialActivityLocalService;

}