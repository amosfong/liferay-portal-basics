/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.exportimport.test.util.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Máté Thurzó
 */
@RunWith(Arquillian.class)
public class AssetCategoryStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected Map<String, List<StagedModel>> addDependentStagedModelsMap(
			Group group)
		throws Exception {

		Map<String, List<StagedModel>> dependentStagedModelsMap =
			new HashMap<>();

		AssetVocabulary vocabulary = AssetTestUtil.addVocabulary(
			group.getGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, AssetVocabulary.class, vocabulary);

		AssetCategory category = AssetTestUtil.addCategory(
			group.getGroupId(), vocabulary.getVocabularyId());

		addDependentStagedModel(
			dependentStagedModelsMap, AssetCategory.class, category);

		return dependentStagedModelsMap;
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		List<StagedModel> vocabularyDependentStagedModels =
			dependentStagedModelsMap.get(AssetVocabulary.class.getSimpleName());

		AssetVocabulary vocabulary =
			(AssetVocabulary)vocabularyDependentStagedModels.get(0);

		List<StagedModel> categoryDependentStagedModels =
			dependentStagedModelsMap.get(AssetCategory.class.getSimpleName());

		AssetCategory category =
			(AssetCategory)categoryDependentStagedModels.get(0);

		return AssetTestUtil.addCategory(
			group.getGroupId(), vocabulary.getVocabularyId(),
			category.getCategoryId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group)
		throws PortalException {

		return AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId(
			uuid, group.getGroupId());
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return AssetCategory.class;
	}

	@Override
	protected void validateImport(
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		List<StagedModel> categoryDependentStagedModels =
			dependentStagedModelsMap.get(AssetCategory.class.getSimpleName());

		Assert.assertEquals(
			categoryDependentStagedModels.toString(), 1,
			categoryDependentStagedModels.size());

		AssetCategory category =
			(AssetCategory)categoryDependentStagedModels.get(0);

		AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId(
			category.getUuid(), group.getGroupId());

		List<StagedModel> vocabularyDependentStagedModels =
			dependentStagedModelsMap.get(AssetVocabulary.class.getSimpleName());

		Assert.assertEquals(
			vocabularyDependentStagedModels.toString(), 1,
			vocabularyDependentStagedModels.size());

		AssetVocabulary vocabulary =
			(AssetVocabulary)vocabularyDependentStagedModels.get(0);

		AssetVocabularyLocalServiceUtil.getAssetVocabularyByUuidAndGroupId(
			vocabulary.getUuid(), group.getGroupId());
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		super.validateImportedStagedModel(stagedModel, importedStagedModel);

		AssetCategory category = (AssetCategory)stagedModel;
		AssetCategory importedCategory = (AssetCategory)importedStagedModel;

		Assert.assertEquals(
			category.getExternalReferenceCode(),
			importedCategory.getExternalReferenceCode());
		Assert.assertEquals(category.getName(), importedCategory.getName());
		Assert.assertEquals(category.getTitle(), importedCategory.getTitle());
		Assert.assertEquals(
			category.getDescription(), importedCategory.getDescription());
	}

}