/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.test.util.BaseSearchTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.Locale;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author István András Dézsi
 * @author Tibor Lipusz
 */
@RunWith(Arquillian.class)
public class AssetCategorySearchTest extends BaseSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Override
	@Test
	public void testSearchAttachments() throws Exception {
	}

	@Override
	@Test
	public void testSearchBaseModelWithTrash() throws Exception {
	}

	@Override
	@Test
	public void testSearchByDDMStructureField() throws Exception {
	}

	@Override
	@Test
	public void testSearchByKeywordsInsideParentBaseModel() throws Exception {
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
			BaseModel<?> parentBaseModel, boolean approved,
			Map<Locale, String> keywordsMap, ServiceContext serviceContext)
		throws Exception {

		AssetVocabulary vocabulary = (AssetVocabulary)parentBaseModel;

		return AssetCategoryServiceUtil.addCategory(
			serviceContext.getScopeGroupId(),
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, keywordsMap,
			null, vocabulary.getVocabularyId(), null, serviceContext);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		AssetVocabulary vocabulary = (AssetVocabulary)parentBaseModel;

		return AssetCategoryServiceUtil.addCategory(
			serviceContext.getScopeGroupId(), keywords,
			vocabulary.getVocabularyId(), serviceContext);
	}

	@Override
	protected void deleteBaseModel(long primaryKey) throws Exception {
		AssetCategoryServiceUtil.deleteCategory(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return AssetCategory.class;
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return AssetVocabularyServiceUtil.addVocabulary(
			serviceContext.getScopeGroupId(), RandomTestUtil.randomString(),
			serviceContext);
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

		AssetCategory category = (AssetCategory)baseModel;

		category.setTitle(keywords);

		return AssetCategoryLocalServiceUtil.updateAssetCategory(category);
	}

}