/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.service.change.tracking.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.test.util.BaseTableReferenceDefinitionTestCase;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.trash.model.TrashEntry;
import com.liferay.trash.service.TrashEntryLocalService;
import com.liferay.trash.service.TrashVersionLocalService;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Gislayne Vitorino
 */
@RunWith(Arquillian.class)
public class TrashVersionTableReferenceDefinitionTest
	extends BaseTableReferenceDefinitionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_journalArticle = JournalTestUtil.addArticle(
			TestPropsValues.getUserId(), group.getGroupId(), 0);

		_trashEntry = _trashEntryLocalService.addTrashEntry(
			TestPropsValues.getUserId(), _journalArticle.getGroupId(),
			JournalArticle.class.getName(),
			_journalArticle.getResourcePrimKey(), _journalArticle.getUuid(),
			null, _journalArticle.getStatus(), null,
			UnicodePropertiesBuilder.put(
				"title", _journalArticle.getArticleId()
			).build());
	}

	@Override
	protected CTModel<?> addCTModel() throws Exception {
		return _trashVersionLocalService.addTrashVersion(
			_trashEntry.getEntryId(), JournalArticle.class.getName(),
			_journalArticle.getId(), WorkflowConstants.STATUS_APPROVED, null);
	}

	private JournalArticle _journalArticle;
	private TrashEntry _trashEntry;

	@Inject
	private TrashEntryLocalService _trashEntryLocalService;

	@Inject
	private TrashVersionLocalService _trashVersionLocalService;

}