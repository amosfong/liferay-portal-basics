/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.test.util.BaseSearchTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
@Sync
public class DLFolderSearchTest extends BaseSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Override
	@Test
	public void testLocalizedSearch() throws Exception {
	}

	@Override
	@Test
	public void testSearchAttachments() throws Exception {
	}

	@Override
	@Test
	public void testSearchBaseModel() throws Exception {
		searchBaseModel(1);
	}

	@Override
	@Test
	public void testSearchBaseModelWithDelete() throws Exception {
		searchBaseModelWithDelete(1);
	}

	@Override
	@Test
	public void testSearchBaseModelWithTrash() throws Exception {
		searchBaseModelWithTrash(1);
	}

	@Override
	@Test
	public void testSearchByDDMStructureField() throws Exception {
	}

	@Override
	@Test
	public void testSearchComments() throws Exception {
	}

	@Override
	@Test
	public void testSearchCommentsByKeywords() throws Exception {
	}

	@Override
	@Test
	public void testSearchExpireAllVersions() throws Exception {
	}

	@Override
	@Test
	public void testSearchExpireLatestVersion() throws Exception {
	}

	@Override
	@Test
	public void testSearchMyEntries() throws Exception {
	}

	@Override
	@Test
	public void testSearchRecentEntries() throws Exception {
	}

	@Override
	@Test
	public void testSearchStatus() throws Exception {
	}

	@Override
	@Test
	public void testSearchVersions() throws Exception {
	}

	@Override
	@Test
	public void testSearchWithinDDMStructure() throws Exception {
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		DLFolder parentDLFolder = (DLFolder)parentBaseModel;

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (parentDLFolder != null) {
			folderId = parentDLFolder.getFolderId();
		}

		Folder folder = DLAppServiceUtil.addFolder(
			null, serviceContext.getScopeGroupId(), folderId, keywords,
			RandomTestUtil.randomString(), serviceContext);

		return (DLFolder)folder.getModel();
	}

	@Override
	protected void deleteBaseModel(long primaryKey) throws Exception {
		DLAppLocalServiceUtil.deleteFolder(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return DLFolder.class;
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		Folder folder = DLAppServiceUtil.addFolder(
			null, serviceContext.getScopeGroupId(),
			(Long)parentBaseModel.getPrimaryKeyObj(),
			RandomTestUtil.randomString(_FOLDER_NAME_MAX_LENGTH),
			RandomTestUtil.randomString(), serviceContext);

		return (DLFolder)folder.getModel();
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		Folder folder = DLAppServiceUtil.addFolder(
			null, serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(_FOLDER_NAME_MAX_LENGTH),
			RandomTestUtil.randomString(), serviceContext);

		return (DLFolder)folder.getModel();
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		DLFolder dlFolder = (DLFolder)baseModel;

		dlFolder.setName(keywords);

		return DLFolderLocalServiceUtil.updateDLFolder(dlFolder);
	}

	private static final int _FOLDER_NAME_MAX_LENGTH = 100;

}