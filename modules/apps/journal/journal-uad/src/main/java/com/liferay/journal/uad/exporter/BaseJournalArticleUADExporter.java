/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.uad.exporter;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.uad.constants.JournalUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the journal article UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link JournalArticleUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseJournalArticleUADExporter
	extends DynamicQueryUADExporter<JournalArticle> {

	@Override
	public Class<JournalArticle> getTypeClass() {
		return JournalArticle.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return journalArticleLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return JournalUADConstants.USER_ID_FIELD_NAMES_JOURNAL_ARTICLE;
	}

	@Override
	protected String toXmlString(JournalArticle journalArticle) {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.journal.model.JournalArticle");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(journalArticle.getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(journalArticle.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>urlTitle</column-name><column-value><![CDATA[");
		sb.append(journalArticle.getUrlTitle());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected JournalArticleLocalService journalArticleLocalService;

}