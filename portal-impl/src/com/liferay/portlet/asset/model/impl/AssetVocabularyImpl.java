/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.util.List;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Juan Fernández
 */
public class AssetVocabularyImpl extends AssetVocabularyBaseImpl {

	@Override
	public List<AssetCategory> getCategories() {
		return AssetCategoryLocalServiceUtil.getVocabularyCategories(
			getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	@Override
	public int getCategoriesCount() {
		return AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(
			getVocabularyId());
	}

	@Override
	public long[] getRequiredClassNameIds() {
		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		return vocabularySettingsHelper.getRequiredClassNameIds();
	}

	@Override
	public long[] getSelectedClassNameIds() {
		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		return vocabularySettingsHelper.getClassNameIds();
	}

	@Override
	public long[] getSelectedClassTypePKs() {
		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		return vocabularySettingsHelper.getClassTypePKs();
	}

	@Override
	public String getSettings() {
		if (_vocabularySettingsHelper == null) {
			return super.getSettings();
		}

		return _vocabularySettingsHelper.toString();
	}

	@Override
	public String getTitle(String languageId) {
		String value = super.getTitle(languageId);

		if (Validator.isNull(value)) {
			value = getName();
		}

		return value;
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		String value = super.getTitle(languageId, useDefault);

		if (Validator.isNull(value)) {
			value = getName();
		}

		return value;
	}

	@Override
	public String getUnambiguousTitle(
			List<AssetVocabulary> vocabularies, long groupId, Locale locale)
		throws PortalException {

		if (getGroupId() == groupId) {
			return getTitle(locale);
		}

		boolean hasAmbiguousTitle = ListUtil.exists(
			vocabularies,
			vocabulary -> {
				String title = vocabulary.getTitle(locale);

				if (title.equals(getTitle(locale)) &&
					(vocabulary.getVocabularyId() != getVocabularyId())) {

					return true;
				}

				return false;
			});

		if (hasAmbiguousTitle) {
			Group group = GroupLocalServiceUtil.getGroup(getGroupId());

			return group.getUnambiguousName(getTitle(locale), locale);
		}

		return getTitle(locale);
	}

	@Override
	public boolean hasMoreThanOneCategorySelected(long[] categoryIds) {
		int count = ListUtil.count(
			getCategories(),
			assetCategory -> ArrayUtil.contains(
				categoryIds, assetCategory.getCategoryId()));

		if (count > 1) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAssociatedToClassNameId(long classNameId) {
		return isAssociatedToClassNameIdAndClassTypePK(
			classNameId, AssetCategoryConstants.ALL_CLASS_TYPE_PK);
	}

	@Override
	public boolean isAssociatedToClassNameIdAndClassTypePK(
		long classNameId, long classTypePK) {

		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		return vocabularySettingsHelper.hasClassNameIdAndClassTypePK(
			classNameId, classTypePK);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #isMissingRequiredCategory(long, long, long[], long)}
	 */
	@Deprecated
	@Override
	public boolean isMissingRequiredCategory(
		long classNameId, long classTypePK, long[] categoryIds) {

		return isMissingRequiredCategory(
			classNameId, classTypePK, categoryIds,
			GroupThreadLocal.getGroupId());
	}

	@Override
	public boolean isMissingRequiredCategory(
		long classNameId, long classTypePK, long[] categoryIds, long groupId) {

		if (!isRequired(classNameId, classTypePK, groupId)) {
			return false;
		}

		return !ListUtil.exists(
			getCategories(),
			assetCategory -> ArrayUtil.contains(
				categoryIds, assetCategory.getCategoryId()));
	}

	@Override
	public boolean isMultiValued() {
		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		return vocabularySettingsHelper.isMultiValued();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #isRequired(long, long, long)}
	 */
	@Deprecated
	@Override
	public boolean isRequired(long classNameId, long classTypePK) {
		return isRequired(
			classNameId, classTypePK, GroupThreadLocal.getGroupId());
	}

	@Override
	public boolean isRequired(
		long classNameId, long classTypePK, long groupId) {

		AssetVocabularySettingsHelper vocabularySettingsHelper =
			getVocabularySettingsHelper();

		Group currentGroup = GroupLocalServiceUtil.fetchGroup(groupId);

		if ((currentGroup != null) && currentGroup.isDepot()) {
			if (vocabularySettingsHelper.isClassNameIdAndClassTypePKRequired(
					classNameId, classTypePK) ||
				vocabularySettingsHelper.
					isClassNameIdAndClassTypePKDepotRequired(
						classNameId, classTypePK)) {

				return true;
			}

			return false;
		}

		Group vocabularyGroup = GroupLocalServiceUtil.fetchGroup(getGroupId());

		if ((vocabularyGroup != null) && vocabularyGroup.isDepot()) {
			return vocabularySettingsHelper.
				isClassNameIdAndClassTypePKDepotRequired(
					classNameId, classTypePK);
		}

		return vocabularySettingsHelper.isClassNameIdAndClassTypePKRequired(
			classNameId, classTypePK);
	}

	@Override
	public void setSettings(String settings) {
		_vocabularySettingsHelper = null;

		super.setSettings(settings);
	}

	protected AssetVocabularySettingsHelper getVocabularySettingsHelper() {
		if (_vocabularySettingsHelper == null) {
			_vocabularySettingsHelper = new AssetVocabularySettingsHelper(
				super.getSettings());
		}

		return _vocabularySettingsHelper;
	}

	private AssetVocabularySettingsHelper _vocabularySettingsHelper;

}