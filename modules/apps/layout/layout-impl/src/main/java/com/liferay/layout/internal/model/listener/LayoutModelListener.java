/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.model.listener;

import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.layout.friendly.url.LayoutFriendlyURLEntryHelper;
import com.liferay.layout.model.LayoutLocalization;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.layout.service.LayoutLocalizationLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.LayoutRevisionLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 * @author David Truong
 */
@Component(service = ModelListener.class)
public class LayoutModelListener extends BaseModelListener<Layout> {

	@Override
	public void onAfterCreate(Layout layout) {
	}

	@Override
	public void onAfterRemove(Layout layout) {
		if (layout == null) {
			return;
		}

		_friendlyURLEntryLocalService.deleteFriendlyURLEntry(
			layout.getGroupId(),
			_layoutFriendlyURLEntryHelper.getClassNameId(
				layout.isPrivateLayout()),
			layout.getPlid());

		if (layout.isTypeAssetDisplay()) {
			_friendlyURLEntryLocalService.deleteFriendlyURLEntry(
				layout.getGroupId(),
				_layoutFriendlyURLEntryHelper.getClassNameId(
					!layout.isPrivateLayout()),
				layout.getPlid());
		}

		for (Locale locale :
				_language.getAvailableLocales(layout.getGroupId())) {

			LayoutLocalization layoutLocalization =
				_layoutLocalizationLocalService.fetchLayoutLocalization(
					layout.getGroupId(), LocaleUtil.toLanguageId(locale),
					layout.getPlid());

			if (layoutLocalization != null) {
				_layoutLocalizationLocalService.deleteLayoutLocalization(
					layoutLocalization);
			}
		}
	}

	@Override
	public void onAfterUpdate(Layout originalLayout, Layout layout) {
	}

	@Override
	public void onBeforeRemove(Layout layout) throws ModelListenerException {
		if (layout == null) {
			return;
		}

		_layoutClassedModelUsageLocalService.
			deleteLayoutClassedModelUsagesByPlid(layout.getPlid());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutModelListener.class);

	@Reference
	private FriendlyURLEntryLocalService _friendlyURLEntryLocalService;

	@Reference
	private Language _language;

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Reference
	private LayoutFriendlyURLEntryHelper _layoutFriendlyURLEntryHelper;

	@Reference
	private LayoutLocalizationLocalService _layoutLocalizationLocalService;

	@Reference
	private LayoutRevisionLocalService _layoutRevisionLocalService;

	@Reference
	private Portal _portal;

}