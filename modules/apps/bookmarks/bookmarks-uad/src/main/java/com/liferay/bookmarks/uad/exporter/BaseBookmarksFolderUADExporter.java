/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.uad.exporter;

import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.bookmarks.uad.constants.BookmarksUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the bookmarks folder UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link BookmarksFolderUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseBookmarksFolderUADExporter
	extends DynamicQueryUADExporter<BookmarksFolder> {

	@Override
	public Class<BookmarksFolder> getTypeClass() {
		return BookmarksFolder.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return bookmarksFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return BookmarksUADConstants.USER_ID_FIELD_NAMES_BOOKMARKS_FOLDER;
	}

	@Override
	protected String toXmlString(BookmarksFolder bookmarksFolder) {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bookmarks.model.BookmarksFolder");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(bookmarksFolder.getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(bookmarksFolder.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>treePath</column-name><column-value><![CDATA[");
		sb.append(bookmarksFolder.getTreePath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(bookmarksFolder.getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(bookmarksFolder.getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected BookmarksFolderLocalService bookmarksFolderLocalService;

}