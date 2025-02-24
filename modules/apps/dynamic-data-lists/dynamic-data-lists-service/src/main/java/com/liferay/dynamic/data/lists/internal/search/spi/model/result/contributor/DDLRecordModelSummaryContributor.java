/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.search.spi.model.result.contributor;

import com.liferay.dynamic.data.lists.constants.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoader;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Marcela Cunha
 */
public class DDLRecordModelSummaryContributor
	implements ModelSummaryContributor {

	public DDLRecordModelSummaryContributor(
		DDLRecordSetLocalService ddlRecordSetLocalService, Language language) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
		_language = language;
	}

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		Summary summary = new Summary(
			_getTitle(GetterUtil.getLong(document.get("recordSetId")), locale),
			document.get(
				locale,
				StringBundler.concat(
					Field.SNIPPET, StringPool.UNDERLINE, Field.DESCRIPTION),
				Field.DESCRIPTION));

		summary.setMaxContentLength(200);

		return summary;
	}

	private String _getLanguageKey(DDLRecordSet ddlRecordSet) {
		if (ddlRecordSet.getScope() ==
				DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS) {

			return "record-for-list-x";
		}

		return "form-record-for-form-x";
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		ResourceBundleLoader resourceBundleLoader =
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader();

		return resourceBundleLoader.loadResourceBundle(locale);
	}

	private String _getTitle(long ddlRecordSetId, Locale locale) {
		try {
			DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.getRecordSet(
				ddlRecordSetId);

			String recordSetName = ddlRecordSet.getName(locale);

			return _language.format(
				_getResourceBundle(locale), _getLanguageKey(ddlRecordSet),
				recordSetName, false);
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDLRecordModelSummaryContributor.class);

	private final DDLRecordSetLocalService _ddlRecordSetLocalService;
	private final Language _language;

}