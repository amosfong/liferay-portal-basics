/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.app.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.app.service.test.util.DLAppServiceTestUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.test.util.BaseDLAppTestCase;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@RunWith(Arquillian.class)
public class DLAppServiceWhenSearchingFileEntriesTest
	extends BaseDLAppTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testShouldFindFileEntryByAssetTagName() throws Exception {
		String fileName = RandomTestUtil.randomString();

		FileEntry fileEntry = DLAppServiceTestUtil.addFileEntry(
			RandomTestUtil.randomString(), group.getGroupId(),
			parentFolder.getFolderId(), fileName, fileName, null, null, null,
			new String[] {"hello", "world"});

		DLAppServiceTestUtil.search(fileEntry, "hello", true);
		DLAppServiceTestUtil.search(fileEntry, "world", true);
		DLAppServiceTestUtil.search(fileEntry, "liferay", false);
	}

	@Test
	public void testShouldFindFileEntryByAssetTagNameAfterUpdate()
		throws Exception {

		String fileName = RandomTestUtil.randomString();
		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;
		byte[] bytes = CONTENT.getBytes();

		String[] assetTagNames = {"hello", "world"};

		FileEntry fileEntry = DLAppServiceTestUtil.addFileEntry(
			RandomTestUtil.randomString(), group.getGroupId(),
			parentFolder.getFolderId(), fileName, fileName, null, null, null,
			assetTagNames);

		assetTagNames = new String[] {"hello", "world", "liferay"};

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		serviceContext.setAssetTagNames(assetTagNames);

		fileEntry = dlAppService.updateFileEntry(
			fileEntry.getFileEntryId(), fileName, ContentTypes.TEXT_PLAIN,
			fileName, StringPool.BLANK, description, changeLog,
			DLVersionNumberIncrease.MINOR, bytes, null, null, null,
			serviceContext);

		DLAppServiceTestUtil.search(fileEntry, "hello", true);
		DLAppServiceTestUtil.search(fileEntry, "world", true);
		DLAppServiceTestUtil.search(fileEntry, "liferay", true);
	}

	@Test
	public void testShouldFindFileEntryInRootFolder() throws Exception {
		DLAppServiceTestUtil.searchFile(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
	}

	@Test
	public void testShouldFindFileEntryInSubfolder() throws Exception {
		DLAppServiceTestUtil.searchFile(
			group.getGroupId(), parentFolder.getFolderId());
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

}