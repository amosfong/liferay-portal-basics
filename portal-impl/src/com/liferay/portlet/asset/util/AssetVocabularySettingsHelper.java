/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.util;

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author José Manuel Navarro
 */
public class AssetVocabularySettingsHelper {

	public static final long[] DEFAULT_SELECTED_CLASS_NAME_IDS = {
		AssetCategoryConstants.ALL_CLASS_NAME_ID
	};

	public static final long[] DEFAULT_SELECTED_CLASS_TYPE_PKS = {
		AssetCategoryConstants.ALL_CLASS_TYPE_PK
	};

	public AssetVocabularySettingsHelper() {
		_unicodeProperties = new UnicodeProperties(true);
	}

	public AssetVocabularySettingsHelper(String propertiesString) {
		this();

		_unicodeProperties.fastLoad(propertiesString);
	}

	public long[] getClassNameIds() {
		return getClassNameIds(getClassNameIdsAndClassTypePKs());
	}

	public long[] getClassTypePKs() {
		return getClassTypePKs(getClassNameIdsAndClassTypePKs());
	}

	public long[] getRequiredClassNameIds() {
		String[] classNameIdsAndClassTypePKs =
			getRequiredClassNameIdsAndClassTypePKs();

		return getClassNameIds(classNameIdsAndClassTypePKs);
	}

	public long[] getRequiredClassTypePKs() {
		String[] classNameIdsAndClassTypePKs =
			getRequiredClassNameIdsAndClassTypePKs();

		return getClassTypePKs(classNameIdsAndClassTypePKs);
	}

	public boolean hasClassNameIdAndClassTypePK(
		long classNameId, long classTypePK) {

		return isClassNameIdAndClassTypePKSpecified(
			classNameId, classTypePK, getClassNameIdsAndClassTypePKs());
	}

	public boolean isClassNameIdAndClassTypePKDepotRequired(
		long classNameId, long classTypePK) {

		return isClassNameIdAndClassTypePKSpecified(
			classNameId, classTypePK,
			getDepotRequiredClassNameIdsAndClassTypePKs());
	}

	public boolean isClassNameIdAndClassTypePKRequired(
		long classNameId, long classTypePK) {

		return isClassNameIdAndClassTypePKSpecified(
			classNameId, classTypePK, getRequiredClassNameIdsAndClassTypePKs());
	}

	public boolean isMultiValued() {
		String value = _unicodeProperties.getProperty(_KEY_MULTI_VALUED);

		return GetterUtil.getBoolean(value, true);
	}

	public void setClassNameIdsAndClassTypePKs(
		long[] classNameIds, long[] classTypePKs, boolean[] requireds) {

		boolean[] depotRequireds = new boolean[requireds.length];

		Arrays.fill(depotRequireds, false);

		setClassNameIdsAndClassTypePKs(
			classNameIds, classTypePKs, depotRequireds, requireds);
	}

	public void setClassNameIdsAndClassTypePKs(
		long[] classNameIds, long[] classTypePKs, boolean[] depotRequireds,
		boolean[] requireds) {

		Set<String> depotRequiredClassNameIds = new LinkedHashSet<>();
		Set<String> requiredClassNameIds = new LinkedHashSet<>();
		Set<String> selectedClassNameIds = new LinkedHashSet<>();

		for (int i = 0; i < classNameIds.length; ++i) {
			long classNameId = classNameIds[i];
			long classTypePK = classTypePKs[i];
			boolean depotRequired = depotRequireds[i];
			boolean required = requireds[i];

			String classNameIdAndClassTypePK = getClassNameIdAndClassTypePK(
				classNameId, classTypePK);

			if (classNameIdAndClassTypePK.equals(
					AssetCategoryConstants.
						ALL_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS) &&
				(required || depotRequired)) {

				if (required) {
					requiredClassNameIds.clear();

					requiredClassNameIds.add(classNameIdAndClassTypePK);
				}

				if (depotRequired) {
					depotRequiredClassNameIds.clear();

					depotRequiredClassNameIds.add(classNameIdAndClassTypePK);
				}

				selectedClassNameIds.clear();

				selectedClassNameIds.add(classNameIdAndClassTypePK);

				break;
			}

			if (depotRequired) {
				depotRequiredClassNameIds.add(classNameIdAndClassTypePK);
			}

			if (required) {
				requiredClassNameIds.add(classNameIdAndClassTypePK);
			}

			selectedClassNameIds.add(classNameIdAndClassTypePK);
		}

		if (selectedClassNameIds.contains(
				AssetCategoryConstants.ALL_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS)) {

			selectedClassNameIds.clear();

			selectedClassNameIds.add(
				AssetCategoryConstants.ALL_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS);

			selectedClassNameIds.addAll(requiredClassNameIds);
		}

		_unicodeProperties.setProperty(
			_KEY_DEPOT_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS,
			StringUtil.merge(depotRequiredClassNameIds));
		_unicodeProperties.setProperty(
			_KEY_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS,
			StringUtil.merge(requiredClassNameIds));
		_unicodeProperties.setProperty(
			_KEY_SELECTED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS,
			StringUtil.merge(selectedClassNameIds));
	}

	public void setMultiValued(boolean multiValued) {
		_unicodeProperties.setProperty(
			_KEY_MULTI_VALUED, String.valueOf(multiValued));
	}

	@Override
	public String toString() {
		return _unicodeProperties.toString();
	}

	protected long getClassNameId(String classNameIdAndClassTypePK) {
		String[] parts = StringUtil.split(
			classNameIdAndClassTypePK, CharPool.COLON);

		return GetterUtil.getLong(parts[0]);
	}

	protected String getClassNameIdAndClassTypePK(
		long classNameId, long classTypePK) {

		return StringBundler.concat(classNameId, StringPool.COLON, classTypePK);
	}

	protected long[] getClassNameIds(String[] classNameIdsAndClassTypePKs) {
		long[] classNameIds = new long[classNameIdsAndClassTypePKs.length];

		for (int i = 0; i < classNameIdsAndClassTypePKs.length; i++) {
			long classNameId = getClassNameId(classNameIdsAndClassTypePKs[i]);

			classNameIds[i] = classNameId;
		}

		return classNameIds;
	}

	protected String[] getClassNameIdsAndClassTypePKs() {
		String value = _unicodeProperties.getProperty(
			_KEY_SELECTED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS);

		if (Validator.isNull(value)) {
			return new String[] {
				getClassNameIdAndClassTypePK(
					AssetCategoryConstants.ALL_CLASS_NAME_ID,
					AssetCategoryConstants.ALL_CLASS_TYPE_PK)
			};
		}

		return StringUtil.split(value);
	}

	protected long getClassTypePK(String classNameIdAndClassTypePK) {
		String[] parts = StringUtil.split(
			classNameIdAndClassTypePK, CharPool.COLON);

		if (parts.length == 1) {
			return AssetCategoryConstants.ALL_CLASS_TYPE_PK;
		}

		return GetterUtil.getLong(parts[1]);
	}

	protected long[] getClassTypePKs(String[] classNameIdsAndClassTypePKs) {
		long[] classTypePKs = new long[classNameIdsAndClassTypePKs.length];

		for (int i = 0; i < classNameIdsAndClassTypePKs.length; i++) {
			long classTypePK = getClassTypePK(classNameIdsAndClassTypePKs[i]);

			classTypePKs[i] = classTypePK;
		}

		return classTypePKs;
	}

	protected String[] getDepotRequiredClassNameIdsAndClassTypePKs() {
		String value = _unicodeProperties.getProperty(
			_KEY_DEPOT_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS);

		if (Validator.isNull(value)) {
			return new String[0];
		}

		return StringUtil.split(value);
	}

	protected String[] getRequiredClassNameIdsAndClassTypePKs() {
		String value = _unicodeProperties.getProperty(
			_KEY_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS);

		if (Validator.isNull(value)) {
			return new String[0];
		}

		return StringUtil.split(value);
	}

	protected boolean isClassNameIdAndClassTypePKSpecified(
		long classNameId, long classTypePK,
		String[] classNameIdsAndClassTypePKs) {

		if (classNameIdsAndClassTypePKs.length == 0) {
			return false;
		}

		if (classNameIdsAndClassTypePKs[0].equals(
				AssetCategoryConstants.ALL_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS) ||
			ArrayUtil.contains(
				classNameIdsAndClassTypePKs,
				getClassNameIdAndClassTypePK(classNameId, classTypePK))) {

			return true;
		}

		String classNameIdAndAllClassTypePK = getClassNameIdAndClassTypePK(
			classNameId, AssetCategoryConstants.ALL_CLASS_TYPE_PK);

		return ArrayUtil.contains(
			classNameIdsAndClassTypePKs, classNameIdAndAllClassTypePK);
	}

	private static final String
		_KEY_DEPOT_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS =
			"depotRequiredClassNameIds";

	private static final String _KEY_MULTI_VALUED = "multiValued";

	private static final String
		_KEY_REQUIRED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS =
			"requiredClassNameIds";

	private static final String
		_KEY_SELECTED_CLASS_NAME_IDS_AND_CLASS_TYPE_PKS =
			"selectedClassNameIds";

	private final UnicodeProperties _unicodeProperties;

}