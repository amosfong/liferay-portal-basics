/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.ratings.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.test.util.DLTestUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.ratings.test.util.BaseRatingsTestCase;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class DLFileEntryRatingsTest extends BaseRatingsTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		DLFolder folder = (DLFolder)parentBaseModel;

		return DLTestUtil.addDLFileEntry(folder.getFolderId());
	}

	@Override
	protected BaseModel<?> deleteBaseModel(
			BaseModel<?> baseModel, ServiceContext serviceContext)
		throws Exception {

		return DLFileEntryLocalServiceUtil.deleteFileEntry(
			(DLFileEntry)baseModel);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return DLFileEntry.class;
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return DLTestUtil.addDLFolder(group.getGroupId());
	}

}